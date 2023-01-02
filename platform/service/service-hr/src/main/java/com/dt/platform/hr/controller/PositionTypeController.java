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


import com.dt.platform.proxy.hr.PositionTypeServiceProxy;
import com.dt.platform.domain.hr.meta.PositionTypeVOMeta;
import com.dt.platform.domain.hr.PositionType;
import com.dt.platform.domain.hr.PositionTypeVO;
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
import com.dt.platform.domain.hr.meta.PositionTypeMeta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.hr.service.IPositionTypeService;
import com.github.foxnic.api.validate.annotations.NotNull;

/**
 * <p>
 * 岗位类型接口控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-01-02 14:12:33
*/

@InDoc
@Api(tags = "岗位类型")
@RestController("HrPositionTypeController")
public class PositionTypeController extends SuperController {

	@Autowired
	private IPositionTypeService positionTypeService;


	/**
	 * 添加岗位类型
	*/
	@ApiOperation(value = "添加岗位类型")
	@ApiImplicitParams({
		@ApiImplicitParam(name = PositionTypeVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "660564289781760000"),
		@ApiImplicitParam(name = PositionTypeVOMeta.CODE , value = "岗位编码" , required = false , dataTypeClass=String.class , example = "manage"),
		@ApiImplicitParam(name = PositionTypeVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class , example = "管理岗"),
		@ApiImplicitParam(name = PositionTypeVOMeta.NOTE , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true , ignorePrimaryKey = true)
	@ApiOperationSupport(order=1 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = PositionTypeServiceProxy.INSERT , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(PositionTypeServiceProxy.INSERT)
	public Result insert(PositionTypeVO positionTypeVO) {
		Result result=positionTypeService.insert(positionTypeVO,false);
		return result;
	}



	/**
	 * 删除岗位类型
	*/
	@ApiOperation(value = "删除岗位类型")
	@ApiImplicitParams({
		@ApiImplicitParam(name = PositionTypeVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "660564289781760000")
	})
	@ApiOperationSupport(order=2 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = PositionTypeServiceProxy.DELETE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(PositionTypeServiceProxy.DELETE)
	public Result deleteById(String id) {
		this.validator().asserts(id).require("缺少id值");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}
		// 引用校验
		ReferCause cause =  positionTypeService.hasRefers(id);
		// 判断是否可以删除
		this.validator().asserts(cause.hasRefer()).requireEqual("不允许删除当前记录："+cause.message(),false);
		if(this.validator().failure()) {
			return this.validator().getFirstResult().messageLevel4Confirm();
		}
		Result result=positionTypeService.deleteByIdLogical(id);
		return result;
	}


	/**
	 * 批量删除岗位类型 <br>
	 * 联合主键时，请自行调整实现
	*/
	@ApiOperation(value = "批量删除岗位类型")
	@ApiImplicitParams({
		@ApiImplicitParam(name = PositionTypeVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
	})
	@ApiOperationSupport(order=3 , author="金杰 , maillank@qq.com") 
	@SentinelResource(value = PositionTypeServiceProxy.DELETE_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(PositionTypeServiceProxy.DELETE_BY_IDS)
	public Result deleteByIds(List<String> ids) {

		// 参数校验
		this.validator().asserts(ids).require("缺少ids参数");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}

		// 查询引用
		Map<String, ReferCause> causeMap = positionTypeService.hasRefers(ids);
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
			Result result=positionTypeService.deleteByIdsLogical(canDeleteIds);
			return result;
		} else if (canDeleteIds.size()>0 && canDeleteIds.size() < ids.size()) {
			// 如果部分行可以删除
			Result result=positionTypeService.deleteByIdsLogical(canDeleteIds);
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
	 * 更新岗位类型
	*/
	@ApiOperation(value = "更新岗位类型")
	@ApiImplicitParams({
		@ApiImplicitParam(name = PositionTypeVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "660564289781760000"),
		@ApiImplicitParam(name = PositionTypeVOMeta.CODE , value = "岗位编码" , required = false , dataTypeClass=String.class , example = "manage"),
		@ApiImplicitParam(name = PositionTypeVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class , example = "管理岗"),
		@ApiImplicitParam(name = PositionTypeVOMeta.NOTE , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
	@ApiOperationSupport( order=4 , author="金杰 , maillank@qq.com" ,  ignoreParameters = { PositionTypeVOMeta.PAGE_INDEX , PositionTypeVOMeta.PAGE_SIZE , PositionTypeVOMeta.SEARCH_FIELD , PositionTypeVOMeta.FUZZY_FIELD , PositionTypeVOMeta.SEARCH_VALUE , PositionTypeVOMeta.DIRTY_FIELDS , PositionTypeVOMeta.SORT_FIELD , PositionTypeVOMeta.SORT_TYPE , PositionTypeVOMeta.DATA_ORIGIN , PositionTypeVOMeta.QUERY_LOGIC , PositionTypeVOMeta.IDS } )
	@SentinelResource(value = PositionTypeServiceProxy.UPDATE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(PositionTypeServiceProxy.UPDATE)
	public Result update(PositionTypeVO positionTypeVO) {
		Result result=positionTypeService.update(positionTypeVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 保存岗位类型
	*/
	@ApiOperation(value = "保存岗位类型")
	@ApiImplicitParams({
		@ApiImplicitParam(name = PositionTypeVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "660564289781760000"),
		@ApiImplicitParam(name = PositionTypeVOMeta.CODE , value = "岗位编码" , required = false , dataTypeClass=String.class , example = "manage"),
		@ApiImplicitParam(name = PositionTypeVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class , example = "管理岗"),
		@ApiImplicitParam(name = PositionTypeVOMeta.NOTE , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
	@ApiOperationSupport(order=5 ,  ignoreParameters = { PositionTypeVOMeta.PAGE_INDEX , PositionTypeVOMeta.PAGE_SIZE , PositionTypeVOMeta.SEARCH_FIELD , PositionTypeVOMeta.FUZZY_FIELD , PositionTypeVOMeta.SEARCH_VALUE , PositionTypeVOMeta.DIRTY_FIELDS , PositionTypeVOMeta.SORT_FIELD , PositionTypeVOMeta.SORT_TYPE , PositionTypeVOMeta.DATA_ORIGIN , PositionTypeVOMeta.QUERY_LOGIC , PositionTypeVOMeta.IDS } )
	@SentinelResource(value = PositionTypeServiceProxy.SAVE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(PositionTypeServiceProxy.SAVE)
	public Result save(PositionTypeVO positionTypeVO) {
		Result result=positionTypeService.save(positionTypeVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 获取岗位类型
	*/
	@ApiOperation(value = "获取岗位类型")
	@ApiImplicitParams({
		@ApiImplicitParam(name = PositionTypeVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "1"),
	})
	@ApiOperationSupport(order=6 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = PositionTypeServiceProxy.GET_BY_ID , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(PositionTypeServiceProxy.GET_BY_ID)
	public Result<PositionType> getById(String id) {
		Result<PositionType> result=new Result<>();
		PositionType positionType=positionTypeService.getById(id);
		result.success(true).data(positionType);
		return result;
	}


	/**
	 * 批量获取岗位类型 <br>
	 * 联合主键时，请自行调整实现
	*/
		@ApiOperation(value = "批量获取岗位类型")
		@ApiImplicitParams({
				@ApiImplicitParam(name = PositionTypeVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
		})
		@ApiOperationSupport(order=3 , author="金杰 , maillank@qq.com") 
		@SentinelResource(value = PositionTypeServiceProxy.GET_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(PositionTypeServiceProxy.GET_BY_IDS)
	public Result<List<PositionType>> getByIds(List<String> ids) {
		Result<List<PositionType>> result=new Result<>();
		List<PositionType> list=positionTypeService.queryListByIds(ids);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 查询岗位类型
	*/
	@ApiOperation(value = "查询岗位类型")
	@ApiImplicitParams({
		@ApiImplicitParam(name = PositionTypeVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "660564289781760000"),
		@ApiImplicitParam(name = PositionTypeVOMeta.CODE , value = "岗位编码" , required = false , dataTypeClass=String.class , example = "manage"),
		@ApiImplicitParam(name = PositionTypeVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class , example = "管理岗"),
		@ApiImplicitParam(name = PositionTypeVOMeta.NOTE , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=5 , author="金杰 , maillank@qq.com" ,  ignoreParameters = { PositionTypeVOMeta.PAGE_INDEX , PositionTypeVOMeta.PAGE_SIZE } )
	@SentinelResource(value = PositionTypeServiceProxy.QUERY_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(PositionTypeServiceProxy.QUERY_LIST)
	public Result<List<PositionType>> queryList(PositionTypeVO sample) {
		Result<List<PositionType>> result=new Result<>();
		List<PositionType> list=positionTypeService.queryList(sample);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 分页查询岗位类型
	*/
	@ApiOperation(value = "分页查询岗位类型")
	@ApiImplicitParams({
		@ApiImplicitParam(name = PositionTypeVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "660564289781760000"),
		@ApiImplicitParam(name = PositionTypeVOMeta.CODE , value = "岗位编码" , required = false , dataTypeClass=String.class , example = "manage"),
		@ApiImplicitParam(name = PositionTypeVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class , example = "管理岗"),
		@ApiImplicitParam(name = PositionTypeVOMeta.NOTE , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=8 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = PositionTypeServiceProxy.QUERY_PAGED_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(PositionTypeServiceProxy.QUERY_PAGED_LIST)
	public Result<PagedList<PositionType>> queryPagedList(PositionTypeVO sample) {
		Result<PagedList<PositionType>> result=new Result<>();
		PagedList<PositionType> list=positionTypeService.queryPagedList(sample,sample.getPageSize(),sample.getPageIndex());
		result.success(true).data(list);
		return result;
	}






}