package com.dt.platform.hr.controller;

import java.util.*;
import org.github.foxnic.web.framework.web.SuperController;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import com.github.foxnic.commons.collection.CollectorUtil;
import com.github.foxnic.dao.entity.ReferCause;
import com.github.foxnic.api.swagger.InDoc;
import org.github.foxnic.web.framework.sentinel.SentinelExceptionUtil;
import com.github.foxnic.api.swagger.ApiParamSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;


import com.dt.platform.proxy.hr.CertificateLevelServiceProxy;
import com.dt.platform.domain.hr.meta.CertificateLevelVOMeta;
import com.dt.platform.domain.hr.CertificateLevel;
import com.dt.platform.domain.hr.CertificateLevelVO;
import com.github.foxnic.api.transter.Result;
import com.github.foxnic.dao.data.SaveMode;
import com.github.foxnic.dao.excel.ExcelWriter;
import com.github.foxnic.springboot.web.DownloadUtil;
import com.github.foxnic.dao.data.PagedList;
import java.util.Date;
import java.sql.Timestamp;
import com.github.foxnic.api.error.ErrorDesc;
import com.github.foxnic.commons.io.StreamUtil;
import java.util.Map;
import com.github.foxnic.dao.excel.ValidateResult;
import java.io.InputStream;
import com.dt.platform.domain.hr.meta.CertificateLevelMeta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.hr.service.ICertificateLevelService;
import com.github.foxnic.api.validate.annotations.NotNull;

/**
 * <p>
 * 证书级别接口控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2024-03-03 20:27:25
*/

@InDoc
@Api(tags = "证书级别")
@RestController("HrCertificateLevelController")
public class CertificateLevelController extends SuperController {

	@Autowired
	private ICertificateLevelService certificateLevelService;

	/**
	 * 添加证书级别
	*/
	@ApiOperation(value = "添加证书级别")
	@ApiImplicitParams({
		@ApiImplicitParam(name = CertificateLevelVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "666941864850489344"),
		@ApiImplicitParam(name = CertificateLevelVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class , example = "初级"),
		@ApiImplicitParam(name = CertificateLevelVOMeta.SORT , value = "排序" , required = false , dataTypeClass=String.class , example = "1"),
		@ApiImplicitParam(name = CertificateLevelVOMeta.UPDATE_BY , value = "修改人ID" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true , ignorePrimaryKey = true)
	@ApiOperationSupport(order=1 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = CertificateLevelServiceProxy.INSERT , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(CertificateLevelServiceProxy.INSERT)
	public Result insert(CertificateLevelVO certificateLevelVO) {
		
		Result result=certificateLevelService.insert(certificateLevelVO,false);
		return result;
	}



	/**
	 * 删除证书级别
	*/
	@ApiOperation(value = "删除证书级别")
	@ApiImplicitParams({
		@ApiImplicitParam(name = CertificateLevelVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "666941864850489344")
	})
	@ApiOperationSupport(order=2 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = CertificateLevelServiceProxy.DELETE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(CertificateLevelServiceProxy.DELETE)
	public Result deleteById(String id) {
		
		this.validator().asserts(id).require("缺少id值");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}
		// 引用校验
		ReferCause cause =  certificateLevelService.hasRefers(id);
		// 判断是否可以删除
		this.validator().asserts(cause.hasRefer()).requireEqual("不允许删除当前记录："+cause.message(),false);
		if(this.validator().failure()) {
			return this.validator().getFirstResult().messageLevel4Confirm();
		}
		Result result=certificateLevelService.deleteByIdLogical(id);
		return result;
	}


	/**
	 * 批量删除证书级别 <br>
	 * 联合主键时，请自行调整实现
	*/
	@ApiOperation(value = "批量删除证书级别")
	@ApiImplicitParams({
		@ApiImplicitParam(name = CertificateLevelVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
	})
	@ApiOperationSupport(order=3 , author="金杰 , maillank@qq.com") 
	@SentinelResource(value = CertificateLevelServiceProxy.DELETE_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(CertificateLevelServiceProxy.DELETE_BY_IDS)
	public Result deleteByIds(List<String> ids) {
		
		// 参数校验
		this.validator().asserts(ids).require("缺少ids参数");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}

		// 查询引用
		Map<String, ReferCause> causeMap = certificateLevelService.hasRefers(ids);
		// 收集可以删除的ID值
		List<String> canDeleteIds = new ArrayList<>();
		for (Map.Entry<String, ReferCause> e : causeMap.entrySet()) {
			if (!e.getValue().hasRefer()) {
				canDeleteIds.add(e.getKey());
			}
		}

		// 执行删除
		if (canDeleteIds.isEmpty()) {
			// 如果没有一行可以被删除
			return ErrorDesc.failure().message("无法删除您选中的数据行：").data(0)
				.addErrors(CollectorUtil.collectArray(CollectorUtil.filter(causeMap.values(),(e)->{return e.hasRefer();}),ReferCause::message,String.class))
				.messageLevel4Confirm();
		} else if (canDeleteIds.size() == ids.size()) {
			// 如果全部可以删除
			Result result=certificateLevelService.deleteByIdsLogical(canDeleteIds);
			return result;
		} else if (canDeleteIds.size()>0 && canDeleteIds.size() < ids.size()) {
			// 如果部分行可以删除
			Result result=certificateLevelService.deleteByIdsLogical(canDeleteIds);
			if (result.failure()) {
				return result;
			} else {
				return ErrorDesc.success().message("已删除 " + canDeleteIds.size() + " 行，但另有 " + (ids.size() - canDeleteIds.size()) + " 行数据无法删除").data(canDeleteIds.size())
				.addErrors(CollectorUtil.collectArray(CollectorUtil.filter(causeMap.values(),(e)->{return e.hasRefer();}),ReferCause::message,String.class))
				.messageLevel4Confirm();
			}
		} else {
			// 理论上，这个分支不存在
			return ErrorDesc.success().message("数据删除未处理");
		}
	}

	/**
	 * 更新证书级别
	*/
	@ApiOperation(value = "更新证书级别")
	@ApiImplicitParams({
		@ApiImplicitParam(name = CertificateLevelVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "666941864850489344"),
		@ApiImplicitParam(name = CertificateLevelVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class , example = "初级"),
		@ApiImplicitParam(name = CertificateLevelVOMeta.SORT , value = "排序" , required = false , dataTypeClass=String.class , example = "1"),
		@ApiImplicitParam(name = CertificateLevelVOMeta.UPDATE_BY , value = "修改人ID" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
	@ApiOperationSupport( order=4 , author="金杰 , maillank@qq.com" ,  ignoreParameters = { CertificateLevelVOMeta.PAGE_INDEX , CertificateLevelVOMeta.PAGE_SIZE , CertificateLevelVOMeta.SEARCH_FIELD , CertificateLevelVOMeta.FUZZY_FIELD , CertificateLevelVOMeta.SEARCH_VALUE , CertificateLevelVOMeta.DIRTY_FIELDS , CertificateLevelVOMeta.SORT_FIELD , CertificateLevelVOMeta.SORT_TYPE , CertificateLevelVOMeta.DATA_ORIGIN , CertificateLevelVOMeta.QUERY_LOGIC , CertificateLevelVOMeta.REQUEST_ACTION , CertificateLevelVOMeta.IDS } )
	@SentinelResource(value = CertificateLevelServiceProxy.UPDATE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(CertificateLevelServiceProxy.UPDATE)
	public Result update(CertificateLevelVO certificateLevelVO) {
		
		Result result=certificateLevelService.update(certificateLevelVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 保存证书级别
	*/
	@ApiOperation(value = "保存证书级别")
	@ApiImplicitParams({
		@ApiImplicitParam(name = CertificateLevelVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "666941864850489344"),
		@ApiImplicitParam(name = CertificateLevelVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class , example = "初级"),
		@ApiImplicitParam(name = CertificateLevelVOMeta.SORT , value = "排序" , required = false , dataTypeClass=String.class , example = "1"),
		@ApiImplicitParam(name = CertificateLevelVOMeta.UPDATE_BY , value = "修改人ID" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
	@ApiOperationSupport(order=5 ,  ignoreParameters = { CertificateLevelVOMeta.PAGE_INDEX , CertificateLevelVOMeta.PAGE_SIZE , CertificateLevelVOMeta.SEARCH_FIELD , CertificateLevelVOMeta.FUZZY_FIELD , CertificateLevelVOMeta.SEARCH_VALUE , CertificateLevelVOMeta.DIRTY_FIELDS , CertificateLevelVOMeta.SORT_FIELD , CertificateLevelVOMeta.SORT_TYPE , CertificateLevelVOMeta.DATA_ORIGIN , CertificateLevelVOMeta.QUERY_LOGIC , CertificateLevelVOMeta.REQUEST_ACTION , CertificateLevelVOMeta.IDS } )
	@SentinelResource(value = CertificateLevelServiceProxy.SAVE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(CertificateLevelServiceProxy.SAVE)
	public Result save(CertificateLevelVO certificateLevelVO) {
		
		Result result=certificateLevelService.save(certificateLevelVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 获取证书级别
	*/
	@ApiOperation(value = "获取证书级别")
	@ApiImplicitParams({
		@ApiImplicitParam(name = CertificateLevelVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "1"),
	})
	@ApiOperationSupport(order=6 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = CertificateLevelServiceProxy.GET_BY_ID , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(CertificateLevelServiceProxy.GET_BY_ID)
	public Result<CertificateLevel> getById(String id) {
		
		Result<CertificateLevel> result=new Result<>();
		CertificateLevel certificateLevel=certificateLevelService.getById(id);
		result.success(true).data(certificateLevel);
		return result;
	}


	/**
	 * 批量获取证书级别 <br>
	 * 联合主键时，请自行调整实现
	*/
		@ApiOperation(value = "批量获取证书级别")
		@ApiImplicitParams({
				@ApiImplicitParam(name = CertificateLevelVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
		})
		@ApiOperationSupport(order=3 , author="金杰 , maillank@qq.com") 
		@SentinelResource(value = CertificateLevelServiceProxy.GET_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(CertificateLevelServiceProxy.GET_BY_IDS)
	public Result<List<CertificateLevel>> getByIds(List<String> ids) {
		
		Result<List<CertificateLevel>> result=new Result<>();
		List<CertificateLevel> list=certificateLevelService.queryListByIds(ids);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 查询证书级别
	*/
	@ApiOperation(value = "查询证书级别")
	@ApiImplicitParams({
		@ApiImplicitParam(name = CertificateLevelVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "666941864850489344"),
		@ApiImplicitParam(name = CertificateLevelVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class , example = "初级"),
		@ApiImplicitParam(name = CertificateLevelVOMeta.SORT , value = "排序" , required = false , dataTypeClass=String.class , example = "1"),
		@ApiImplicitParam(name = CertificateLevelVOMeta.UPDATE_BY , value = "修改人ID" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=5 , author="金杰 , maillank@qq.com" ,  ignoreParameters = { CertificateLevelVOMeta.PAGE_INDEX , CertificateLevelVOMeta.PAGE_SIZE } )
	@SentinelResource(value = CertificateLevelServiceProxy.QUERY_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(CertificateLevelServiceProxy.QUERY_LIST)
	public Result<List<CertificateLevel>> queryList(CertificateLevelVO sample) {
		
		Result<List<CertificateLevel>> result=new Result<>();
		List<CertificateLevel> list=certificateLevelService.queryList(sample);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 分页查询证书级别
	*/
	@ApiOperation(value = "分页查询证书级别")
	@ApiImplicitParams({
		@ApiImplicitParam(name = CertificateLevelVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "666941864850489344"),
		@ApiImplicitParam(name = CertificateLevelVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class , example = "初级"),
		@ApiImplicitParam(name = CertificateLevelVOMeta.SORT , value = "排序" , required = false , dataTypeClass=String.class , example = "1"),
		@ApiImplicitParam(name = CertificateLevelVOMeta.UPDATE_BY , value = "修改人ID" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=8 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = CertificateLevelServiceProxy.QUERY_PAGED_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(CertificateLevelServiceProxy.QUERY_PAGED_LIST)
	public Result<PagedList<CertificateLevel>> queryPagedList(CertificateLevelVO sample) {
		
		Result<PagedList<CertificateLevel>> result=new Result<>();
		PagedList<CertificateLevel> list=certificateLevelService.queryPagedList(sample,sample.getPageSize(),sample.getPageIndex());
		result.success(true).data(list);
		return result;
	}





}