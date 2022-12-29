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


import com.dt.platform.proxy.hr.ProfessionalLevelServiceProxy;
import com.dt.platform.domain.hr.meta.ProfessionalLevelVOMeta;
import com.dt.platform.domain.hr.ProfessionalLevel;
import com.dt.platform.domain.hr.ProfessionalLevelVO;
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
import com.dt.platform.domain.hr.meta.ProfessionalLevelMeta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.hr.service.IProfessionalLevelService;
import com.github.foxnic.api.validate.annotations.NotNull;

/**
 * <p>
 * 职业评级接口控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2022-12-28 19:17:56
*/

@InDoc
@Api(tags = "职业评级")
@RestController("HrProfessionalLevelController")
public class ProfessionalLevelController extends SuperController {

	@Autowired
	private IProfessionalLevelService professionalLevelService;


	/**
	 * 添加职业评级
	*/
	@ApiOperation(value = "添加职业评级")
	@ApiImplicitParams({
		@ApiImplicitParam(name = ProfessionalLevelVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = ProfessionalLevelVOMeta.CODE , value = "编码" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ProfessionalLevelVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ProfessionalLevelVOMeta.NOTE , value = "备注" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ProfessionalLevelVOMeta.SORT , value = "排序" , required = false , dataTypeClass=Integer.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true , ignorePrimaryKey = true)
	@ApiOperationSupport(order=1 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = ProfessionalLevelServiceProxy.INSERT , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(ProfessionalLevelServiceProxy.INSERT)
	public Result insert(ProfessionalLevelVO professionalLevelVO) {
		Result result=professionalLevelService.insert(professionalLevelVO,false);
		return result;
	}



	/**
	 * 删除职业评级
	*/
	@ApiOperation(value = "删除职业评级")
	@ApiImplicitParams({
		@ApiImplicitParam(name = ProfessionalLevelVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class)
	})
	@ApiOperationSupport(order=2 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = ProfessionalLevelServiceProxy.DELETE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(ProfessionalLevelServiceProxy.DELETE)
	public Result deleteById(String id) {
		this.validator().asserts(id).require("缺少id值");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}
		// 引用校验
		ReferCause cause =  professionalLevelService.hasRefers(id);
		// 判断是否可以删除
		this.validator().asserts(cause.hasRefer()).requireEqual("不允许删除当前记录："+cause.message(),false);
		if(this.validator().failure()) {
			return this.validator().getFirstResult().messageLevel4Confirm();
		}
		Result result=professionalLevelService.deleteByIdLogical(id);
		return result;
	}


	/**
	 * 批量删除职业评级 <br>
	 * 联合主键时，请自行调整实现
	*/
	@ApiOperation(value = "批量删除职业评级")
	@ApiImplicitParams({
		@ApiImplicitParam(name = ProfessionalLevelVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
	})
	@ApiOperationSupport(order=3 , author="金杰 , maillank@qq.com") 
	@SentinelResource(value = ProfessionalLevelServiceProxy.DELETE_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(ProfessionalLevelServiceProxy.DELETE_BY_IDS)
	public Result deleteByIds(List<String> ids) {

		// 参数校验
		this.validator().asserts(ids).require("缺少ids参数");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}

		// 查询引用
		Map<String, ReferCause> causeMap = professionalLevelService.hasRefers(ids);
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
			Result result=professionalLevelService.deleteByIdsLogical(canDeleteIds);
			return result;
		} else if (canDeleteIds.size()>0 && canDeleteIds.size() < ids.size()) {
			// 如果部分行可以删除
			Result result=professionalLevelService.deleteByIdsLogical(canDeleteIds);
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
	 * 更新职业评级
	*/
	@ApiOperation(value = "更新职业评级")
	@ApiImplicitParams({
		@ApiImplicitParam(name = ProfessionalLevelVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = ProfessionalLevelVOMeta.CODE , value = "编码" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ProfessionalLevelVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ProfessionalLevelVOMeta.NOTE , value = "备注" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ProfessionalLevelVOMeta.SORT , value = "排序" , required = false , dataTypeClass=Integer.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
	@ApiOperationSupport( order=4 , author="金杰 , maillank@qq.com" ,  ignoreParameters = { ProfessionalLevelVOMeta.PAGE_INDEX , ProfessionalLevelVOMeta.PAGE_SIZE , ProfessionalLevelVOMeta.SEARCH_FIELD , ProfessionalLevelVOMeta.FUZZY_FIELD , ProfessionalLevelVOMeta.SEARCH_VALUE , ProfessionalLevelVOMeta.DIRTY_FIELDS , ProfessionalLevelVOMeta.SORT_FIELD , ProfessionalLevelVOMeta.SORT_TYPE , ProfessionalLevelVOMeta.DATA_ORIGIN , ProfessionalLevelVOMeta.QUERY_LOGIC , ProfessionalLevelVOMeta.IDS } )
	@SentinelResource(value = ProfessionalLevelServiceProxy.UPDATE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(ProfessionalLevelServiceProxy.UPDATE)
	public Result update(ProfessionalLevelVO professionalLevelVO) {
		Result result=professionalLevelService.update(professionalLevelVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 保存职业评级
	*/
	@ApiOperation(value = "保存职业评级")
	@ApiImplicitParams({
		@ApiImplicitParam(name = ProfessionalLevelVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = ProfessionalLevelVOMeta.CODE , value = "编码" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ProfessionalLevelVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ProfessionalLevelVOMeta.NOTE , value = "备注" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ProfessionalLevelVOMeta.SORT , value = "排序" , required = false , dataTypeClass=Integer.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
	@ApiOperationSupport(order=5 ,  ignoreParameters = { ProfessionalLevelVOMeta.PAGE_INDEX , ProfessionalLevelVOMeta.PAGE_SIZE , ProfessionalLevelVOMeta.SEARCH_FIELD , ProfessionalLevelVOMeta.FUZZY_FIELD , ProfessionalLevelVOMeta.SEARCH_VALUE , ProfessionalLevelVOMeta.DIRTY_FIELDS , ProfessionalLevelVOMeta.SORT_FIELD , ProfessionalLevelVOMeta.SORT_TYPE , ProfessionalLevelVOMeta.DATA_ORIGIN , ProfessionalLevelVOMeta.QUERY_LOGIC , ProfessionalLevelVOMeta.IDS } )
	@SentinelResource(value = ProfessionalLevelServiceProxy.SAVE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(ProfessionalLevelServiceProxy.SAVE)
	public Result save(ProfessionalLevelVO professionalLevelVO) {
		Result result=professionalLevelService.save(professionalLevelVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 获取职业评级
	*/
	@ApiOperation(value = "获取职业评级")
	@ApiImplicitParams({
		@ApiImplicitParam(name = ProfessionalLevelVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "1"),
	})
	@ApiOperationSupport(order=6 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = ProfessionalLevelServiceProxy.GET_BY_ID , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(ProfessionalLevelServiceProxy.GET_BY_ID)
	public Result<ProfessionalLevel> getById(String id) {
		Result<ProfessionalLevel> result=new Result<>();
		ProfessionalLevel professionalLevel=professionalLevelService.getById(id);
		result.success(true).data(professionalLevel);
		return result;
	}


	/**
	 * 批量获取职业评级 <br>
	 * 联合主键时，请自行调整实现
	*/
		@ApiOperation(value = "批量获取职业评级")
		@ApiImplicitParams({
				@ApiImplicitParam(name = ProfessionalLevelVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
		})
		@ApiOperationSupport(order=3 , author="金杰 , maillank@qq.com") 
		@SentinelResource(value = ProfessionalLevelServiceProxy.GET_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(ProfessionalLevelServiceProxy.GET_BY_IDS)
	public Result<List<ProfessionalLevel>> getByIds(List<String> ids) {
		Result<List<ProfessionalLevel>> result=new Result<>();
		List<ProfessionalLevel> list=professionalLevelService.queryListByIds(ids);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 查询职业评级
	*/
	@ApiOperation(value = "查询职业评级")
	@ApiImplicitParams({
		@ApiImplicitParam(name = ProfessionalLevelVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = ProfessionalLevelVOMeta.CODE , value = "编码" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ProfessionalLevelVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ProfessionalLevelVOMeta.NOTE , value = "备注" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ProfessionalLevelVOMeta.SORT , value = "排序" , required = false , dataTypeClass=Integer.class),
	})
	@ApiOperationSupport(order=5 , author="金杰 , maillank@qq.com" ,  ignoreParameters = { ProfessionalLevelVOMeta.PAGE_INDEX , ProfessionalLevelVOMeta.PAGE_SIZE } )
	@SentinelResource(value = ProfessionalLevelServiceProxy.QUERY_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(ProfessionalLevelServiceProxy.QUERY_LIST)
	public Result<List<ProfessionalLevel>> queryList(ProfessionalLevelVO sample) {
		Result<List<ProfessionalLevel>> result=new Result<>();
		List<ProfessionalLevel> list=professionalLevelService.queryList(sample);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 分页查询职业评级
	*/
	@ApiOperation(value = "分页查询职业评级")
	@ApiImplicitParams({
		@ApiImplicitParam(name = ProfessionalLevelVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = ProfessionalLevelVOMeta.CODE , value = "编码" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ProfessionalLevelVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ProfessionalLevelVOMeta.NOTE , value = "备注" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ProfessionalLevelVOMeta.SORT , value = "排序" , required = false , dataTypeClass=Integer.class),
	})
	@ApiOperationSupport(order=8 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = ProfessionalLevelServiceProxy.QUERY_PAGED_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(ProfessionalLevelServiceProxy.QUERY_PAGED_LIST)
	public Result<PagedList<ProfessionalLevel>> queryPagedList(ProfessionalLevelVO sample) {
		Result<PagedList<ProfessionalLevel>> result=new Result<>();
		PagedList<ProfessionalLevel> list=professionalLevelService.queryPagedList(sample,sample.getPageSize(),sample.getPageIndex());
		result.success(true).data(list);
		return result;
	}






}