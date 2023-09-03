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


import com.dt.platform.proxy.ops.AutoGroupServiceProxy;
import com.dt.platform.domain.ops.meta.AutoGroupVOMeta;
import com.dt.platform.domain.ops.AutoGroup;
import com.dt.platform.domain.ops.AutoGroupVO;
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
import com.dt.platform.domain.ops.meta.AutoGroupMeta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.IAutoGroupService;
import com.github.foxnic.api.validate.annotations.NotNull;

/**
 * <p>
 * 节点分组接口控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-09-01 15:21:32
*/

@InDoc
@Api(tags = "节点分组")
@RestController("OpsAutoGroupController")
public class AutoGroupController extends SuperController {

	@Autowired
	private IAutoGroupService autoGroupService;

	/**
	 * 添加节点分组
	*/
	@ApiOperation(value = "添加节点分组")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AutoGroupVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "749694127301459968"),
		@ApiImplicitParam(name = AutoGroupVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class , example = "默认分组"),
		@ApiImplicitParam(name = AutoGroupVOMeta.STATUS , value = "状态" , required = false , dataTypeClass=String.class , example = "enable"),
		@ApiImplicitParam(name = AutoGroupVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class , example = "11"),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true , ignorePrimaryKey = true)
	@ApiOperationSupport(order=1 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = AutoGroupServiceProxy.INSERT , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AutoGroupServiceProxy.INSERT)
	public Result insert(AutoGroupVO autoGroupVO) {
		
		Result result=autoGroupService.insert(autoGroupVO,false);
		return result;
	}



	/**
	 * 删除节点分组
	*/
	@ApiOperation(value = "删除节点分组")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AutoGroupVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "749694127301459968")
	})
	@ApiOperationSupport(order=2 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = AutoGroupServiceProxy.DELETE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AutoGroupServiceProxy.DELETE)
	public Result deleteById(String id) {
		
		this.validator().asserts(id).require("缺少id值");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}
		// 引用校验
		ReferCause cause =  autoGroupService.hasRefers(id);
		// 判断是否可以删除
		this.validator().asserts(cause.hasRefer()).requireEqual("不允许删除当前记录："+cause.message(),false);
		if(this.validator().failure()) {
			return this.validator().getFirstResult().messageLevel4Confirm();
		}
		Result result=autoGroupService.deleteByIdLogical(id);
		return result;
	}


	/**
	 * 批量删除节点分组 <br>
	 * 联合主键时，请自行调整实现
	*/
	@ApiOperation(value = "批量删除节点分组")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AutoGroupVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
	})
	@ApiOperationSupport(order=3 , author="金杰 , maillank@qq.com") 
	@SentinelResource(value = AutoGroupServiceProxy.DELETE_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AutoGroupServiceProxy.DELETE_BY_IDS)
	public Result deleteByIds(List<String> ids) {
		
		// 参数校验
		this.validator().asserts(ids).require("缺少ids参数");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}

		// 查询引用
		Map<String, ReferCause> causeMap = autoGroupService.hasRefers(ids);
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
			Result result=autoGroupService.deleteByIdsLogical(canDeleteIds);
			return result;
		} else if (canDeleteIds.size()>0 && canDeleteIds.size() < ids.size()) {
			// 如果部分行可以删除
			Result result=autoGroupService.deleteByIdsLogical(canDeleteIds);
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
	 * 更新节点分组
	*/
	@ApiOperation(value = "更新节点分组")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AutoGroupVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "749694127301459968"),
		@ApiImplicitParam(name = AutoGroupVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class , example = "默认分组"),
		@ApiImplicitParam(name = AutoGroupVOMeta.STATUS , value = "状态" , required = false , dataTypeClass=String.class , example = "enable"),
		@ApiImplicitParam(name = AutoGroupVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class , example = "11"),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
	@ApiOperationSupport( order=4 , author="金杰 , maillank@qq.com" ,  ignoreParameters = { AutoGroupVOMeta.PAGE_INDEX , AutoGroupVOMeta.PAGE_SIZE , AutoGroupVOMeta.SEARCH_FIELD , AutoGroupVOMeta.FUZZY_FIELD , AutoGroupVOMeta.SEARCH_VALUE , AutoGroupVOMeta.DIRTY_FIELDS , AutoGroupVOMeta.SORT_FIELD , AutoGroupVOMeta.SORT_TYPE , AutoGroupVOMeta.DATA_ORIGIN , AutoGroupVOMeta.QUERY_LOGIC , AutoGroupVOMeta.REQUEST_ACTION , AutoGroupVOMeta.IDS } )
	@SentinelResource(value = AutoGroupServiceProxy.UPDATE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AutoGroupServiceProxy.UPDATE)
	public Result update(AutoGroupVO autoGroupVO) {
		
		Result result=autoGroupService.update(autoGroupVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 保存节点分组
	*/
	@ApiOperation(value = "保存节点分组")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AutoGroupVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "749694127301459968"),
		@ApiImplicitParam(name = AutoGroupVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class , example = "默认分组"),
		@ApiImplicitParam(name = AutoGroupVOMeta.STATUS , value = "状态" , required = false , dataTypeClass=String.class , example = "enable"),
		@ApiImplicitParam(name = AutoGroupVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class , example = "11"),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
	@ApiOperationSupport(order=5 ,  ignoreParameters = { AutoGroupVOMeta.PAGE_INDEX , AutoGroupVOMeta.PAGE_SIZE , AutoGroupVOMeta.SEARCH_FIELD , AutoGroupVOMeta.FUZZY_FIELD , AutoGroupVOMeta.SEARCH_VALUE , AutoGroupVOMeta.DIRTY_FIELDS , AutoGroupVOMeta.SORT_FIELD , AutoGroupVOMeta.SORT_TYPE , AutoGroupVOMeta.DATA_ORIGIN , AutoGroupVOMeta.QUERY_LOGIC , AutoGroupVOMeta.REQUEST_ACTION , AutoGroupVOMeta.IDS } )
	@SentinelResource(value = AutoGroupServiceProxy.SAVE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AutoGroupServiceProxy.SAVE)
	public Result save(AutoGroupVO autoGroupVO) {
		
		Result result=autoGroupService.save(autoGroupVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 获取节点分组
	*/
	@ApiOperation(value = "获取节点分组")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AutoGroupVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "1"),
	})
	@ApiOperationSupport(order=6 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = AutoGroupServiceProxy.GET_BY_ID , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AutoGroupServiceProxy.GET_BY_ID)
	public Result<AutoGroup> getById(String id) {
		
		Result<AutoGroup> result=new Result<>();
		AutoGroup autoGroup=autoGroupService.getById(id);
		result.success(true).data(autoGroup);
		return result;
	}


	/**
	 * 批量获取节点分组 <br>
	 * 联合主键时，请自行调整实现
	*/
		@ApiOperation(value = "批量获取节点分组")
		@ApiImplicitParams({
				@ApiImplicitParam(name = AutoGroupVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
		})
		@ApiOperationSupport(order=3 , author="金杰 , maillank@qq.com") 
		@SentinelResource(value = AutoGroupServiceProxy.GET_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AutoGroupServiceProxy.GET_BY_IDS)
	public Result<List<AutoGroup>> getByIds(List<String> ids) {
		
		Result<List<AutoGroup>> result=new Result<>();
		List<AutoGroup> list=autoGroupService.queryListByIds(ids);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 查询节点分组
	*/
	@ApiOperation(value = "查询节点分组")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AutoGroupVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "749694127301459968"),
		@ApiImplicitParam(name = AutoGroupVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class , example = "默认分组"),
		@ApiImplicitParam(name = AutoGroupVOMeta.STATUS , value = "状态" , required = false , dataTypeClass=String.class , example = "enable"),
		@ApiImplicitParam(name = AutoGroupVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class , example = "11"),
	})
	@ApiOperationSupport(order=5 , author="金杰 , maillank@qq.com" ,  ignoreParameters = { AutoGroupVOMeta.PAGE_INDEX , AutoGroupVOMeta.PAGE_SIZE } )
	@SentinelResource(value = AutoGroupServiceProxy.QUERY_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AutoGroupServiceProxy.QUERY_LIST)
	public Result<List<AutoGroup>> queryList(AutoGroupVO sample) {
		
		Result<List<AutoGroup>> result=new Result<>();
		List<AutoGroup> list=autoGroupService.queryList(sample);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 分页查询节点分组
	*/
	@ApiOperation(value = "分页查询节点分组")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AutoGroupVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "749694127301459968"),
		@ApiImplicitParam(name = AutoGroupVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class , example = "默认分组"),
		@ApiImplicitParam(name = AutoGroupVOMeta.STATUS , value = "状态" , required = false , dataTypeClass=String.class , example = "enable"),
		@ApiImplicitParam(name = AutoGroupVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class , example = "11"),
	})
	@ApiOperationSupport(order=8 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = AutoGroupServiceProxy.QUERY_PAGED_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AutoGroupServiceProxy.QUERY_PAGED_LIST)
	public Result<PagedList<AutoGroup>> queryPagedList(AutoGroupVO sample) {
		
		Result<PagedList<AutoGroup>> result=new Result<>();
		PagedList<AutoGroup> list=autoGroupService.queryPagedList(sample,sample.getPageSize(),sample.getPageIndex());
		result.success(true).data(list);
		return result;
	}





}