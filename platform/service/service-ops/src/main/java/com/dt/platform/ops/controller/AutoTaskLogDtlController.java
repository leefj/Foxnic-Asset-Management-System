package com.dt.platform.ops.controller;

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


import com.dt.platform.proxy.ops.AutoTaskLogDtlServiceProxy;
import com.dt.platform.domain.ops.meta.AutoTaskLogDtlVOMeta;
import com.dt.platform.domain.ops.AutoTaskLogDtl;
import com.dt.platform.domain.ops.AutoTaskLogDtlVO;
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
import com.dt.platform.domain.ops.meta.AutoTaskLogDtlMeta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.IAutoTaskLogDtlService;
import com.github.foxnic.api.validate.annotations.NotNull;

/**
 * <p>
 * 明细日志接口控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-09-01 15:48:22
*/

@InDoc
@Api(tags = "明细日志")
@RestController("OpsAutoTaskLogDtlController")
public class AutoTaskLogDtlController extends SuperController {

	@Autowired
	private IAutoTaskLogDtlService autoTaskLogDtlService;

	/**
	 * 添加明细日志
	*/
	@ApiOperation(value = "添加明细日志")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AutoTaskLogDtlVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AutoTaskLogDtlVOMeta.OWNER_ID , value = "所属" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AutoTaskLogDtlVOMeta.IND , value = "序列" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = AutoTaskLogDtlVOMeta.CONTENT , value = "记录" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true , ignorePrimaryKey = true)
	@ApiOperationSupport(order=1 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = AutoTaskLogDtlServiceProxy.INSERT , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AutoTaskLogDtlServiceProxy.INSERT)
	public Result insert(AutoTaskLogDtlVO autoTaskLogDtlVO) {
		
		Result result=autoTaskLogDtlService.insert(autoTaskLogDtlVO,false);
		return result;
	}



	/**
	 * 删除明细日志
	*/
	@ApiOperation(value = "删除明细日志")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AutoTaskLogDtlVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class)
	})
	@ApiOperationSupport(order=2 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = AutoTaskLogDtlServiceProxy.DELETE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AutoTaskLogDtlServiceProxy.DELETE)
	public Result deleteById(String id) {
		
		this.validator().asserts(id).require("缺少id值");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}
		// 引用校验
		ReferCause cause =  autoTaskLogDtlService.hasRefers(id);
		// 判断是否可以删除
		this.validator().asserts(cause.hasRefer()).requireEqual("不允许删除当前记录："+cause.message(),false);
		if(this.validator().failure()) {
			return this.validator().getFirstResult().messageLevel4Confirm();
		}
		Result result=autoTaskLogDtlService.deleteByIdLogical(id);
		return result;
	}


	/**
	 * 批量删除明细日志 <br>
	 * 联合主键时，请自行调整实现
	*/
	@ApiOperation(value = "批量删除明细日志")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AutoTaskLogDtlVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
	})
	@ApiOperationSupport(order=3 , author="金杰 , maillank@qq.com") 
	@SentinelResource(value = AutoTaskLogDtlServiceProxy.DELETE_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AutoTaskLogDtlServiceProxy.DELETE_BY_IDS)
	public Result deleteByIds(List<String> ids) {
		
		// 参数校验
		this.validator().asserts(ids).require("缺少ids参数");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}

		// 查询引用
		Map<String, ReferCause> causeMap = autoTaskLogDtlService.hasRefers(ids);
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
			Result result=autoTaskLogDtlService.deleteByIdsLogical(canDeleteIds);
			return result;
		} else if (canDeleteIds.size()>0 && canDeleteIds.size() < ids.size()) {
			// 如果部分行可以删除
			Result result=autoTaskLogDtlService.deleteByIdsLogical(canDeleteIds);
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
	 * 更新明细日志
	*/
	@ApiOperation(value = "更新明细日志")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AutoTaskLogDtlVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AutoTaskLogDtlVOMeta.OWNER_ID , value = "所属" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AutoTaskLogDtlVOMeta.IND , value = "序列" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = AutoTaskLogDtlVOMeta.CONTENT , value = "记录" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
	@ApiOperationSupport( order=4 , author="金杰 , maillank@qq.com" ,  ignoreParameters = { AutoTaskLogDtlVOMeta.PAGE_INDEX , AutoTaskLogDtlVOMeta.PAGE_SIZE , AutoTaskLogDtlVOMeta.SEARCH_FIELD , AutoTaskLogDtlVOMeta.FUZZY_FIELD , AutoTaskLogDtlVOMeta.SEARCH_VALUE , AutoTaskLogDtlVOMeta.DIRTY_FIELDS , AutoTaskLogDtlVOMeta.SORT_FIELD , AutoTaskLogDtlVOMeta.SORT_TYPE , AutoTaskLogDtlVOMeta.DATA_ORIGIN , AutoTaskLogDtlVOMeta.QUERY_LOGIC , AutoTaskLogDtlVOMeta.REQUEST_ACTION , AutoTaskLogDtlVOMeta.IDS } )
	@SentinelResource(value = AutoTaskLogDtlServiceProxy.UPDATE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AutoTaskLogDtlServiceProxy.UPDATE)
	public Result update(AutoTaskLogDtlVO autoTaskLogDtlVO) {
		
		Result result=autoTaskLogDtlService.update(autoTaskLogDtlVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 保存明细日志
	*/
	@ApiOperation(value = "保存明细日志")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AutoTaskLogDtlVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AutoTaskLogDtlVOMeta.OWNER_ID , value = "所属" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AutoTaskLogDtlVOMeta.IND , value = "序列" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = AutoTaskLogDtlVOMeta.CONTENT , value = "记录" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
	@ApiOperationSupport(order=5 ,  ignoreParameters = { AutoTaskLogDtlVOMeta.PAGE_INDEX , AutoTaskLogDtlVOMeta.PAGE_SIZE , AutoTaskLogDtlVOMeta.SEARCH_FIELD , AutoTaskLogDtlVOMeta.FUZZY_FIELD , AutoTaskLogDtlVOMeta.SEARCH_VALUE , AutoTaskLogDtlVOMeta.DIRTY_FIELDS , AutoTaskLogDtlVOMeta.SORT_FIELD , AutoTaskLogDtlVOMeta.SORT_TYPE , AutoTaskLogDtlVOMeta.DATA_ORIGIN , AutoTaskLogDtlVOMeta.QUERY_LOGIC , AutoTaskLogDtlVOMeta.REQUEST_ACTION , AutoTaskLogDtlVOMeta.IDS } )
	@SentinelResource(value = AutoTaskLogDtlServiceProxy.SAVE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AutoTaskLogDtlServiceProxy.SAVE)
	public Result save(AutoTaskLogDtlVO autoTaskLogDtlVO) {
		
		Result result=autoTaskLogDtlService.save(autoTaskLogDtlVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 获取明细日志
	*/
	@ApiOperation(value = "获取明细日志")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AutoTaskLogDtlVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "1"),
	})
	@ApiOperationSupport(order=6 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = AutoTaskLogDtlServiceProxy.GET_BY_ID , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AutoTaskLogDtlServiceProxy.GET_BY_ID)
	public Result<AutoTaskLogDtl> getById(String id) {
		
		Result<AutoTaskLogDtl> result=new Result<>();
		AutoTaskLogDtl autoTaskLogDtl=autoTaskLogDtlService.getById(id);
		result.success(true).data(autoTaskLogDtl);
		return result;
	}


	/**
	 * 批量获取明细日志 <br>
	 * 联合主键时，请自行调整实现
	*/
		@ApiOperation(value = "批量获取明细日志")
		@ApiImplicitParams({
				@ApiImplicitParam(name = AutoTaskLogDtlVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
		})
		@ApiOperationSupport(order=3 , author="金杰 , maillank@qq.com") 
		@SentinelResource(value = AutoTaskLogDtlServiceProxy.GET_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AutoTaskLogDtlServiceProxy.GET_BY_IDS)
	public Result<List<AutoTaskLogDtl>> getByIds(List<String> ids) {
		
		Result<List<AutoTaskLogDtl>> result=new Result<>();
		List<AutoTaskLogDtl> list=autoTaskLogDtlService.queryListByIds(ids);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 查询明细日志
	*/
	@ApiOperation(value = "查询明细日志")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AutoTaskLogDtlVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AutoTaskLogDtlVOMeta.OWNER_ID , value = "所属" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AutoTaskLogDtlVOMeta.IND , value = "序列" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = AutoTaskLogDtlVOMeta.CONTENT , value = "记录" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=5 , author="金杰 , maillank@qq.com" ,  ignoreParameters = { AutoTaskLogDtlVOMeta.PAGE_INDEX , AutoTaskLogDtlVOMeta.PAGE_SIZE } )
	@SentinelResource(value = AutoTaskLogDtlServiceProxy.QUERY_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AutoTaskLogDtlServiceProxy.QUERY_LIST)
	public Result<List<AutoTaskLogDtl>> queryList(AutoTaskLogDtlVO sample) {
		
		Result<List<AutoTaskLogDtl>> result=new Result<>();
		List<AutoTaskLogDtl> list=autoTaskLogDtlService.queryList(sample);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 分页查询明细日志
	*/
	@ApiOperation(value = "分页查询明细日志")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AutoTaskLogDtlVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AutoTaskLogDtlVOMeta.OWNER_ID , value = "所属" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AutoTaskLogDtlVOMeta.IND , value = "序列" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = AutoTaskLogDtlVOMeta.CONTENT , value = "记录" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=8 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = AutoTaskLogDtlServiceProxy.QUERY_PAGED_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AutoTaskLogDtlServiceProxy.QUERY_PAGED_LIST)
	public Result<PagedList<AutoTaskLogDtl>> queryPagedList(AutoTaskLogDtlVO sample) {
		
		Result<PagedList<AutoTaskLogDtl>> result=new Result<>();
		PagedList<AutoTaskLogDtl> list=autoTaskLogDtlService.queryPagedList(sample,sample.getPageSize(),sample.getPageIndex());
		result.success(true).data(list);
		return result;
	}





}