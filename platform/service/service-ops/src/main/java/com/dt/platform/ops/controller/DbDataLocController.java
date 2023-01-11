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


import com.dt.platform.proxy.ops.DbDataLocServiceProxy;
import com.dt.platform.domain.ops.meta.DbDataLocVOMeta;
import com.dt.platform.domain.ops.DbDataLoc;
import com.dt.platform.domain.ops.DbDataLocVO;
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
import com.dt.platform.domain.ops.meta.DbDataLocMeta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.IDbDataLocService;
import com.github.foxnic.api.validate.annotations.NotNull;

/**
 * <p>
 * 数据库存放位置接口控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-01-11 11:21:31
*/

@InDoc
@Api(tags = "数据库存放位置")
@RestController("OpsDbDataLocController")
public class DbDataLocController extends SuperController {

	@Autowired
	private IDbDataLocService dbDataLocService;


	/**
	 * 添加数据库存放位置
	*/
	@ApiOperation(value = "添加数据库存放位置")
	@ApiImplicitParams({
		@ApiImplicitParam(name = DbDataLocVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = DbDataLocVOMeta.LOC_ID , value = "存放位置" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = DbDataLocVOMeta.DB_INFO_ID , value = "数据库" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true , ignorePrimaryKey = true)
	@ApiOperationSupport(order=1 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = DbDataLocServiceProxy.INSERT , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(DbDataLocServiceProxy.INSERT)
	public Result insert(DbDataLocVO dbDataLocVO) {
		Result result=dbDataLocService.insert(dbDataLocVO,false);
		return result;
	}



	/**
	 * 删除数据库存放位置
	*/
	@ApiOperation(value = "删除数据库存放位置")
	@ApiImplicitParams({
		@ApiImplicitParam(name = DbDataLocVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class)
	})
	@ApiOperationSupport(order=2 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = DbDataLocServiceProxy.DELETE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(DbDataLocServiceProxy.DELETE)
	public Result deleteById(String id) {
		this.validator().asserts(id).require("缺少id值");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}
		// 引用校验
		ReferCause cause =  dbDataLocService.hasRefers(id);
		// 判断是否可以删除
		this.validator().asserts(cause.hasRefer()).requireEqual("不允许删除当前记录："+cause.message(),false);
		if(this.validator().failure()) {
			return this.validator().getFirstResult().messageLevel4Confirm();
		}
		Result result=dbDataLocService.deleteByIdLogical(id);
		return result;
	}


	/**
	 * 批量删除数据库存放位置 <br>
	 * 联合主键时，请自行调整实现
	*/
	@ApiOperation(value = "批量删除数据库存放位置")
	@ApiImplicitParams({
		@ApiImplicitParam(name = DbDataLocVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
	})
	@ApiOperationSupport(order=3 , author="金杰 , maillank@qq.com") 
	@SentinelResource(value = DbDataLocServiceProxy.DELETE_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(DbDataLocServiceProxy.DELETE_BY_IDS)
	public Result deleteByIds(List<String> ids) {

		// 参数校验
		this.validator().asserts(ids).require("缺少ids参数");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}

		// 查询引用
		Map<String, ReferCause> causeMap = dbDataLocService.hasRefers(ids);
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
			Result result=dbDataLocService.deleteByIdsLogical(canDeleteIds);
			return result;
		} else if (canDeleteIds.size()>0 && canDeleteIds.size() < ids.size()) {
			// 如果部分行可以删除
			Result result=dbDataLocService.deleteByIdsLogical(canDeleteIds);
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
	 * 更新数据库存放位置
	*/
	@ApiOperation(value = "更新数据库存放位置")
	@ApiImplicitParams({
		@ApiImplicitParam(name = DbDataLocVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = DbDataLocVOMeta.LOC_ID , value = "存放位置" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = DbDataLocVOMeta.DB_INFO_ID , value = "数据库" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
	@ApiOperationSupport( order=4 , author="金杰 , maillank@qq.com" ,  ignoreParameters = { DbDataLocVOMeta.PAGE_INDEX , DbDataLocVOMeta.PAGE_SIZE , DbDataLocVOMeta.SEARCH_FIELD , DbDataLocVOMeta.FUZZY_FIELD , DbDataLocVOMeta.SEARCH_VALUE , DbDataLocVOMeta.DIRTY_FIELDS , DbDataLocVOMeta.SORT_FIELD , DbDataLocVOMeta.SORT_TYPE , DbDataLocVOMeta.DATA_ORIGIN , DbDataLocVOMeta.QUERY_LOGIC , DbDataLocVOMeta.IDS } )
	@SentinelResource(value = DbDataLocServiceProxy.UPDATE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(DbDataLocServiceProxy.UPDATE)
	public Result update(DbDataLocVO dbDataLocVO) {
		Result result=dbDataLocService.update(dbDataLocVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 保存数据库存放位置
	*/
	@ApiOperation(value = "保存数据库存放位置")
	@ApiImplicitParams({
		@ApiImplicitParam(name = DbDataLocVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = DbDataLocVOMeta.LOC_ID , value = "存放位置" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = DbDataLocVOMeta.DB_INFO_ID , value = "数据库" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
	@ApiOperationSupport(order=5 ,  ignoreParameters = { DbDataLocVOMeta.PAGE_INDEX , DbDataLocVOMeta.PAGE_SIZE , DbDataLocVOMeta.SEARCH_FIELD , DbDataLocVOMeta.FUZZY_FIELD , DbDataLocVOMeta.SEARCH_VALUE , DbDataLocVOMeta.DIRTY_FIELDS , DbDataLocVOMeta.SORT_FIELD , DbDataLocVOMeta.SORT_TYPE , DbDataLocVOMeta.DATA_ORIGIN , DbDataLocVOMeta.QUERY_LOGIC , DbDataLocVOMeta.IDS } )
	@SentinelResource(value = DbDataLocServiceProxy.SAVE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(DbDataLocServiceProxy.SAVE)
	public Result save(DbDataLocVO dbDataLocVO) {
		Result result=dbDataLocService.save(dbDataLocVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 获取数据库存放位置
	*/
	@ApiOperation(value = "获取数据库存放位置")
	@ApiImplicitParams({
		@ApiImplicitParam(name = DbDataLocVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "1"),
	})
	@ApiOperationSupport(order=6 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = DbDataLocServiceProxy.GET_BY_ID , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(DbDataLocServiceProxy.GET_BY_ID)
	public Result<DbDataLoc> getById(String id) {
		Result<DbDataLoc> result=new Result<>();
		DbDataLoc dbDataLoc=dbDataLocService.getById(id);
		result.success(true).data(dbDataLoc);
		return result;
	}


	/**
	 * 批量获取数据库存放位置 <br>
	 * 联合主键时，请自行调整实现
	*/
		@ApiOperation(value = "批量获取数据库存放位置")
		@ApiImplicitParams({
				@ApiImplicitParam(name = DbDataLocVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
		})
		@ApiOperationSupport(order=3 , author="金杰 , maillank@qq.com") 
		@SentinelResource(value = DbDataLocServiceProxy.GET_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(DbDataLocServiceProxy.GET_BY_IDS)
	public Result<List<DbDataLoc>> getByIds(List<String> ids) {
		Result<List<DbDataLoc>> result=new Result<>();
		List<DbDataLoc> list=dbDataLocService.queryListByIds(ids);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 查询数据库存放位置
	*/
	@ApiOperation(value = "查询数据库存放位置")
	@ApiImplicitParams({
		@ApiImplicitParam(name = DbDataLocVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = DbDataLocVOMeta.LOC_ID , value = "存放位置" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = DbDataLocVOMeta.DB_INFO_ID , value = "数据库" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=5 , author="金杰 , maillank@qq.com" ,  ignoreParameters = { DbDataLocVOMeta.PAGE_INDEX , DbDataLocVOMeta.PAGE_SIZE } )
	@SentinelResource(value = DbDataLocServiceProxy.QUERY_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(DbDataLocServiceProxy.QUERY_LIST)
	public Result<List<DbDataLoc>> queryList(DbDataLocVO sample) {
		Result<List<DbDataLoc>> result=new Result<>();
		List<DbDataLoc> list=dbDataLocService.queryList(sample);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 分页查询数据库存放位置
	*/
	@ApiOperation(value = "分页查询数据库存放位置")
	@ApiImplicitParams({
		@ApiImplicitParam(name = DbDataLocVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = DbDataLocVOMeta.LOC_ID , value = "存放位置" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = DbDataLocVOMeta.DB_INFO_ID , value = "数据库" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=8 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = DbDataLocServiceProxy.QUERY_PAGED_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(DbDataLocServiceProxy.QUERY_PAGED_LIST)
	public Result<PagedList<DbDataLoc>> queryPagedList(DbDataLocVO sample) {
		Result<PagedList<DbDataLoc>> result=new Result<>();
		PagedList<DbDataLoc> list=dbDataLocService.queryPagedList(sample,sample.getPageSize(),sample.getPageIndex());
		result.success(true).data(list);
		return result;
	}






}