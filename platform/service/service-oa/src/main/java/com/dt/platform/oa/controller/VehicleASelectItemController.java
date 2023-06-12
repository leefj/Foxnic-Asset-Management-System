package com.dt.platform.oa.controller;

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


import com.dt.platform.proxy.oa.VehicleASelectItemServiceProxy;
import com.dt.platform.domain.oa.meta.VehicleASelectItemVOMeta;
import com.dt.platform.domain.oa.VehicleASelectItem;
import com.dt.platform.domain.oa.VehicleASelectItemVO;
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
import com.dt.platform.domain.oa.meta.VehicleASelectItemMeta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.oa.service.IVehicleASelectItemService;
import com.github.foxnic.api.validate.annotations.NotNull;

/**
 * <p>
 * 车辆数据接口控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-06-10 14:08:13
*/

@InDoc
@Api(tags = "车辆数据")
@RestController("OaVehicleASelectItemController")
public class VehicleASelectItemController extends SuperController {

	@Autowired
	private IVehicleASelectItemService vehicleASelectItemService;

	/**
	 * 添加车辆数据
	*/
	@ApiOperation(value = "添加车辆数据")
	@ApiImplicitParams({
		@ApiImplicitParam(name = VehicleASelectItemVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = VehicleASelectItemVOMeta.HANDLE_ID , value = "处理" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = VehicleASelectItemVOMeta.ASSET_ID , value = "车辆" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true , ignorePrimaryKey = true)
	@ApiOperationSupport(order=1 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = VehicleASelectItemServiceProxy.INSERT , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(VehicleASelectItemServiceProxy.INSERT)
	public Result insert(VehicleASelectItemVO vehicleASelectItemVO) {
		
		Result result=vehicleASelectItemService.insert(vehicleASelectItemVO,false);
		return result;
	}



	/**
	 * 删除车辆数据
	*/
	@ApiOperation(value = "删除车辆数据")
	@ApiImplicitParams({
		@ApiImplicitParam(name = VehicleASelectItemVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class)
	})
	@ApiOperationSupport(order=2 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = VehicleASelectItemServiceProxy.DELETE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(VehicleASelectItemServiceProxy.DELETE)
	public Result deleteById(String id) {
		
		this.validator().asserts(id).require("缺少id值");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}
		// 引用校验
		ReferCause cause =  vehicleASelectItemService.hasRefers(id);
		// 判断是否可以删除
		this.validator().asserts(cause.hasRefer()).requireEqual("不允许删除当前记录："+cause.message(),false);
		if(this.validator().failure()) {
			return this.validator().getFirstResult().messageLevel4Confirm();
		}
		Result result=vehicleASelectItemService.deleteByIdLogical(id);
		return result;
	}


	/**
	 * 批量删除车辆数据 <br>
	 * 联合主键时，请自行调整实现
	*/
	@ApiOperation(value = "批量删除车辆数据")
	@ApiImplicitParams({
		@ApiImplicitParam(name = VehicleASelectItemVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
	})
	@ApiOperationSupport(order=3 , author="金杰 , maillank@qq.com") 
	@SentinelResource(value = VehicleASelectItemServiceProxy.DELETE_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(VehicleASelectItemServiceProxy.DELETE_BY_IDS)
	public Result deleteByIds(List<String> ids) {
		
		// 参数校验
		this.validator().asserts(ids).require("缺少ids参数");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}

		// 查询引用
		Map<String, ReferCause> causeMap = vehicleASelectItemService.hasRefers(ids);
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
			Result result=vehicleASelectItemService.deleteByIdsLogical(canDeleteIds);
			return result;
		} else if (canDeleteIds.size()>0 && canDeleteIds.size() < ids.size()) {
			// 如果部分行可以删除
			Result result=vehicleASelectItemService.deleteByIdsLogical(canDeleteIds);
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
	 * 更新车辆数据
	*/
	@ApiOperation(value = "更新车辆数据")
	@ApiImplicitParams({
		@ApiImplicitParam(name = VehicleASelectItemVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = VehicleASelectItemVOMeta.HANDLE_ID , value = "处理" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = VehicleASelectItemVOMeta.ASSET_ID , value = "车辆" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
	@ApiOperationSupport( order=4 , author="金杰 , maillank@qq.com" ,  ignoreParameters = { VehicleASelectItemVOMeta.PAGE_INDEX , VehicleASelectItemVOMeta.PAGE_SIZE , VehicleASelectItemVOMeta.SEARCH_FIELD , VehicleASelectItemVOMeta.FUZZY_FIELD , VehicleASelectItemVOMeta.SEARCH_VALUE , VehicleASelectItemVOMeta.DIRTY_FIELDS , VehicleASelectItemVOMeta.SORT_FIELD , VehicleASelectItemVOMeta.SORT_TYPE , VehicleASelectItemVOMeta.DATA_ORIGIN , VehicleASelectItemVOMeta.QUERY_LOGIC , VehicleASelectItemVOMeta.REQUEST_ACTION , VehicleASelectItemVOMeta.IDS } )
	@SentinelResource(value = VehicleASelectItemServiceProxy.UPDATE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(VehicleASelectItemServiceProxy.UPDATE)
	public Result update(VehicleASelectItemVO vehicleASelectItemVO) {
		
		Result result=vehicleASelectItemService.update(vehicleASelectItemVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 保存车辆数据
	*/
	@ApiOperation(value = "保存车辆数据")
	@ApiImplicitParams({
		@ApiImplicitParam(name = VehicleASelectItemVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = VehicleASelectItemVOMeta.HANDLE_ID , value = "处理" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = VehicleASelectItemVOMeta.ASSET_ID , value = "车辆" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
	@ApiOperationSupport(order=5 ,  ignoreParameters = { VehicleASelectItemVOMeta.PAGE_INDEX , VehicleASelectItemVOMeta.PAGE_SIZE , VehicleASelectItemVOMeta.SEARCH_FIELD , VehicleASelectItemVOMeta.FUZZY_FIELD , VehicleASelectItemVOMeta.SEARCH_VALUE , VehicleASelectItemVOMeta.DIRTY_FIELDS , VehicleASelectItemVOMeta.SORT_FIELD , VehicleASelectItemVOMeta.SORT_TYPE , VehicleASelectItemVOMeta.DATA_ORIGIN , VehicleASelectItemVOMeta.QUERY_LOGIC , VehicleASelectItemVOMeta.REQUEST_ACTION , VehicleASelectItemVOMeta.IDS } )
	@SentinelResource(value = VehicleASelectItemServiceProxy.SAVE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(VehicleASelectItemServiceProxy.SAVE)
	public Result save(VehicleASelectItemVO vehicleASelectItemVO) {
		
		Result result=vehicleASelectItemService.save(vehicleASelectItemVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 获取车辆数据
	*/
	@ApiOperation(value = "获取车辆数据")
	@ApiImplicitParams({
		@ApiImplicitParam(name = VehicleASelectItemVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "1"),
	})
	@ApiOperationSupport(order=6 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = VehicleASelectItemServiceProxy.GET_BY_ID , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(VehicleASelectItemServiceProxy.GET_BY_ID)
	public Result<VehicleASelectItem> getById(String id) {
		
		Result<VehicleASelectItem> result=new Result<>();
		VehicleASelectItem vehicleASelectItem=vehicleASelectItemService.getById(id);
		result.success(true).data(vehicleASelectItem);
		return result;
	}


	/**
	 * 批量获取车辆数据 <br>
	 * 联合主键时，请自行调整实现
	*/
		@ApiOperation(value = "批量获取车辆数据")
		@ApiImplicitParams({
				@ApiImplicitParam(name = VehicleASelectItemVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
		})
		@ApiOperationSupport(order=3 , author="金杰 , maillank@qq.com") 
		@SentinelResource(value = VehicleASelectItemServiceProxy.GET_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(VehicleASelectItemServiceProxy.GET_BY_IDS)
	public Result<List<VehicleASelectItem>> getByIds(List<String> ids) {
		
		Result<List<VehicleASelectItem>> result=new Result<>();
		List<VehicleASelectItem> list=vehicleASelectItemService.queryListByIds(ids);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 查询车辆数据
	*/
	@ApiOperation(value = "查询车辆数据")
	@ApiImplicitParams({
		@ApiImplicitParam(name = VehicleASelectItemVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = VehicleASelectItemVOMeta.HANDLE_ID , value = "处理" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = VehicleASelectItemVOMeta.ASSET_ID , value = "车辆" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=5 , author="金杰 , maillank@qq.com" ,  ignoreParameters = { VehicleASelectItemVOMeta.PAGE_INDEX , VehicleASelectItemVOMeta.PAGE_SIZE } )
	@SentinelResource(value = VehicleASelectItemServiceProxy.QUERY_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(VehicleASelectItemServiceProxy.QUERY_LIST)
	public Result<List<VehicleASelectItem>> queryList(VehicleASelectItemVO sample) {
		
		Result<List<VehicleASelectItem>> result=new Result<>();
		List<VehicleASelectItem> list=vehicleASelectItemService.queryList(sample);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 分页查询车辆数据
	*/
	@ApiOperation(value = "分页查询车辆数据")
	@ApiImplicitParams({
		@ApiImplicitParam(name = VehicleASelectItemVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = VehicleASelectItemVOMeta.HANDLE_ID , value = "处理" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = VehicleASelectItemVOMeta.ASSET_ID , value = "车辆" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=8 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = VehicleASelectItemServiceProxy.QUERY_PAGED_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(VehicleASelectItemServiceProxy.QUERY_PAGED_LIST)
	public Result<PagedList<VehicleASelectItem>> queryPagedList(VehicleASelectItemVO sample) {
		
		Result<PagedList<VehicleASelectItem>> result=new Result<>();
		PagedList<VehicleASelectItem> list=vehicleASelectItemService.queryPagedList(sample,sample.getPageSize(),sample.getPageIndex());
		result.success(true).data(list);
		return result;
	}





}