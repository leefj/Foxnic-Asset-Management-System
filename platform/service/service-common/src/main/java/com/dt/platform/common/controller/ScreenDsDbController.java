package com.dt.platform.common.controller;

import java.util.*;

import com.dt.platform.domain.common.meta.ScreenDsApiVOMeta;
import com.dt.platform.proxy.common.ScreenDsApiServiceProxy;
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


import com.dt.platform.proxy.common.ScreenDsDbServiceProxy;
import com.dt.platform.domain.common.meta.ScreenDsDbVOMeta;
import com.dt.platform.domain.common.ScreenDsDb;
import com.dt.platform.domain.common.ScreenDsDbVO;
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
import com.dt.platform.domain.common.meta.ScreenDsDbMeta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.common.service.IScreenDsDbService;
import com.github.foxnic.api.validate.annotations.NotNull;

/**
 * <p>
 * 数据库接口控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-10-28 07:56:45
*/

@InDoc
@Api(tags = "数据库")
@RestController("SysScreenDsDbController")
public class ScreenDsDbController extends SuperController {

	@Autowired
	private IScreenDsDbService screenDsDbService;

	/**
	 * 添加数据库
	*/
	@ApiOperation(value = "添加数据库")
	@ApiImplicitParams({
		@ApiImplicitParam(name = ScreenDsDbVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = ScreenDsDbVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ScreenDsDbVOMeta.TYPE , value = "类型" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ScreenDsDbVOMeta.DS_TYPE , value = "数据库类型" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ScreenDsDbVOMeta.USER , value = "账户" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ScreenDsDbVOMeta.PWD , value = "密码" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ScreenDsDbVOMeta.URI , value = "uri" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ScreenDsDbVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ScreenDsDbVOMeta.UPDATE_BY , value = "修改人ID" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true , ignorePrimaryKey = true)
	@ApiOperationSupport(order=1 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = ScreenDsDbServiceProxy.INSERT , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(ScreenDsDbServiceProxy.INSERT)
	public Result insert(ScreenDsDbVO screenDsDbVO) {
		
		Result result=screenDsDbService.insert(screenDsDbVO,false);
		return result;
	}



	/**
	 * 删除数据库
	*/
	@ApiOperation(value = "删除数据库")
	@ApiImplicitParams({
		@ApiImplicitParam(name = ScreenDsDbVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class)
	})
	@ApiOperationSupport(order=2 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = ScreenDsDbServiceProxy.DELETE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(ScreenDsDbServiceProxy.DELETE)
	public Result deleteById(String id) {
		
		this.validator().asserts(id).require("缺少id值");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}
		// 引用校验
		ReferCause cause =  screenDsDbService.hasRefers(id);
		// 判断是否可以删除
		this.validator().asserts(cause.hasRefer()).requireEqual("不允许删除当前记录："+cause.message(),false);
		if(this.validator().failure()) {
			return this.validator().getFirstResult().messageLevel4Confirm();
		}
		Result result=screenDsDbService.deleteByIdLogical(id);
		return result;
	}

	/**
	 * 测试
	 */
	@ApiOperation(value = "测试")
	@ApiImplicitParams({
			@ApiImplicitParam(name = ScreenDsDbVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class)
	})
	@ApiOperationSupport(order=2 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = ScreenDsDbServiceProxy.TEST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(ScreenDsDbServiceProxy.TEST)
	public Result test(String id) {
		return screenDsDbService.test(id);
	}


	/**
	 * 批量删除数据库 <br>
	 * 联合主键时，请自行调整实现
	*/
	@ApiOperation(value = "批量删除数据库")
	@ApiImplicitParams({
		@ApiImplicitParam(name = ScreenDsDbVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
	})
	@ApiOperationSupport(order=3 , author="金杰 , maillank@qq.com") 
	@SentinelResource(value = ScreenDsDbServiceProxy.DELETE_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(ScreenDsDbServiceProxy.DELETE_BY_IDS)
	public Result deleteByIds(List<String> ids) {
		
		// 参数校验
		this.validator().asserts(ids).require("缺少ids参数");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}

		// 查询引用
		Map<String, ReferCause> causeMap = screenDsDbService.hasRefers(ids);
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
			Result result=screenDsDbService.deleteByIdsLogical(canDeleteIds);
			return result;
		} else if (canDeleteIds.size()>0 && canDeleteIds.size() < ids.size()) {
			// 如果部分行可以删除
			Result result=screenDsDbService.deleteByIdsLogical(canDeleteIds);
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
	 * 更新数据库
	*/
	@ApiOperation(value = "更新数据库")
	@ApiImplicitParams({
		@ApiImplicitParam(name = ScreenDsDbVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = ScreenDsDbVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ScreenDsDbVOMeta.TYPE , value = "类型" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ScreenDsDbVOMeta.DS_TYPE , value = "数据库类型" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ScreenDsDbVOMeta.USER , value = "账户" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ScreenDsDbVOMeta.PWD , value = "密码" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ScreenDsDbVOMeta.URI , value = "uri" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ScreenDsDbVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ScreenDsDbVOMeta.UPDATE_BY , value = "修改人ID" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
	@ApiOperationSupport( order=4 , author="金杰 , maillank@qq.com" ,  ignoreParameters = { ScreenDsDbVOMeta.PAGE_INDEX , ScreenDsDbVOMeta.PAGE_SIZE , ScreenDsDbVOMeta.SEARCH_FIELD , ScreenDsDbVOMeta.FUZZY_FIELD , ScreenDsDbVOMeta.SEARCH_VALUE , ScreenDsDbVOMeta.DIRTY_FIELDS , ScreenDsDbVOMeta.SORT_FIELD , ScreenDsDbVOMeta.SORT_TYPE , ScreenDsDbVOMeta.DATA_ORIGIN , ScreenDsDbVOMeta.QUERY_LOGIC , ScreenDsDbVOMeta.REQUEST_ACTION , ScreenDsDbVOMeta.IDS } )
	@SentinelResource(value = ScreenDsDbServiceProxy.UPDATE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(ScreenDsDbServiceProxy.UPDATE)
	public Result update(ScreenDsDbVO screenDsDbVO) {
		
		Result result=screenDsDbService.update(screenDsDbVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 保存数据库
	*/
	@ApiOperation(value = "保存数据库")
	@ApiImplicitParams({
		@ApiImplicitParam(name = ScreenDsDbVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = ScreenDsDbVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ScreenDsDbVOMeta.TYPE , value = "类型" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ScreenDsDbVOMeta.DS_TYPE , value = "数据库类型" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ScreenDsDbVOMeta.USER , value = "账户" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ScreenDsDbVOMeta.PWD , value = "密码" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ScreenDsDbVOMeta.URI , value = "uri" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ScreenDsDbVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ScreenDsDbVOMeta.UPDATE_BY , value = "修改人ID" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
	@ApiOperationSupport(order=5 ,  ignoreParameters = { ScreenDsDbVOMeta.PAGE_INDEX , ScreenDsDbVOMeta.PAGE_SIZE , ScreenDsDbVOMeta.SEARCH_FIELD , ScreenDsDbVOMeta.FUZZY_FIELD , ScreenDsDbVOMeta.SEARCH_VALUE , ScreenDsDbVOMeta.DIRTY_FIELDS , ScreenDsDbVOMeta.SORT_FIELD , ScreenDsDbVOMeta.SORT_TYPE , ScreenDsDbVOMeta.DATA_ORIGIN , ScreenDsDbVOMeta.QUERY_LOGIC , ScreenDsDbVOMeta.REQUEST_ACTION , ScreenDsDbVOMeta.IDS } )
	@SentinelResource(value = ScreenDsDbServiceProxy.SAVE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(ScreenDsDbServiceProxy.SAVE)
	public Result save(ScreenDsDbVO screenDsDbVO) {
		
		Result result=screenDsDbService.save(screenDsDbVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 获取数据库
	*/
	@ApiOperation(value = "获取数据库")
	@ApiImplicitParams({
		@ApiImplicitParam(name = ScreenDsDbVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "1"),
	})
	@ApiOperationSupport(order=6 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = ScreenDsDbServiceProxy.GET_BY_ID , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(ScreenDsDbServiceProxy.GET_BY_ID)
	public Result<ScreenDsDb> getById(String id) {
		
		Result<ScreenDsDb> result=new Result<>();
		ScreenDsDb screenDsDb=screenDsDbService.getById(id);
		result.success(true).data(screenDsDb);
		return result;
	}


	/**
	 * 批量获取数据库 <br>
	 * 联合主键时，请自行调整实现
	*/
		@ApiOperation(value = "批量获取数据库")
		@ApiImplicitParams({
				@ApiImplicitParam(name = ScreenDsDbVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
		})
		@ApiOperationSupport(order=3 , author="金杰 , maillank@qq.com") 
		@SentinelResource(value = ScreenDsDbServiceProxy.GET_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(ScreenDsDbServiceProxy.GET_BY_IDS)
	public Result<List<ScreenDsDb>> getByIds(List<String> ids) {
		
		Result<List<ScreenDsDb>> result=new Result<>();
		List<ScreenDsDb> list=screenDsDbService.queryListByIds(ids);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 查询数据库
	*/
	@ApiOperation(value = "查询数据库")
	@ApiImplicitParams({
		@ApiImplicitParam(name = ScreenDsDbVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = ScreenDsDbVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ScreenDsDbVOMeta.TYPE , value = "类型" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ScreenDsDbVOMeta.DS_TYPE , value = "数据库类型" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ScreenDsDbVOMeta.USER , value = "账户" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ScreenDsDbVOMeta.PWD , value = "密码" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ScreenDsDbVOMeta.URI , value = "uri" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ScreenDsDbVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ScreenDsDbVOMeta.UPDATE_BY , value = "修改人ID" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=5 , author="金杰 , maillank@qq.com" ,  ignoreParameters = { ScreenDsDbVOMeta.PAGE_INDEX , ScreenDsDbVOMeta.PAGE_SIZE } )
	@SentinelResource(value = ScreenDsDbServiceProxy.QUERY_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(ScreenDsDbServiceProxy.QUERY_LIST)
	public Result<List<ScreenDsDb>> queryList(ScreenDsDbVO sample) {
		
		Result<List<ScreenDsDb>> result=new Result<>();
		List<ScreenDsDb> list=screenDsDbService.queryList(sample);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 分页查询数据库
	*/
	@ApiOperation(value = "分页查询数据库")
	@ApiImplicitParams({
		@ApiImplicitParam(name = ScreenDsDbVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = ScreenDsDbVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ScreenDsDbVOMeta.TYPE , value = "类型" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ScreenDsDbVOMeta.DS_TYPE , value = "数据库类型" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ScreenDsDbVOMeta.USER , value = "账户" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ScreenDsDbVOMeta.PWD , value = "密码" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ScreenDsDbVOMeta.URI , value = "uri" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ScreenDsDbVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = ScreenDsDbVOMeta.UPDATE_BY , value = "修改人ID" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=8 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = ScreenDsDbServiceProxy.QUERY_PAGED_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(ScreenDsDbServiceProxy.QUERY_PAGED_LIST)
	public Result<PagedList<ScreenDsDb>> queryPagedList(ScreenDsDbVO sample) {
		
		Result<PagedList<ScreenDsDb>> result=new Result<>();
		PagedList<ScreenDsDb> list=screenDsDbService.queryPagedList(sample,sample.getPageSize(),sample.getPageIndex());
		result.success(true).data(list);
		return result;
	}





}