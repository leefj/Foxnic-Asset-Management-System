package com.dt.platform.eam.controller;


import java.util.*;
import org.github.foxnic.web.framework.web.SuperController;
import com.github.foxnic.commons.collection.CollectorUtil;
import com.github.foxnic.dao.entity.ReferCause;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import com.github.foxnic.api.swagger.InDoc;
import org.github.foxnic.web.framework.sentinel.SentinelExceptionUtil;
import com.github.foxnic.api.swagger.ApiParamSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;


import com.dt.platform.proxy.eam.InventoryFeedbackServiceProxy;
import com.dt.platform.domain.eam.meta.InventoryFeedbackVOMeta;
import com.dt.platform.domain.eam.InventoryFeedback;
import com.dt.platform.domain.eam.InventoryFeedbackVO;
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
import com.dt.platform.domain.eam.meta.InventoryFeedbackMeta;
import org.github.foxnic.web.domain.hrm.Employee;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IInventoryFeedbackService;
import com.github.foxnic.api.validate.annotations.NotNull;

/**
 * <p>
 * 盘点反馈接口控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2022-11-08 07:00:15
*/

@InDoc
@Api(tags = "盘点反馈")
@RestController("EamInventoryFeedbackController")
public class InventoryFeedbackController extends SuperController {

	@Autowired
	private IInventoryFeedbackService inventoryFeedbackService;


	/**
	 * 添加盘点反馈
	*/
	@ApiOperation(value = "添加盘点反馈")
	@ApiImplicitParams({
		@ApiImplicitParam(name = InventoryFeedbackVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = InventoryFeedbackVOMeta.INVENTORY_ID , value = "盘点" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = InventoryFeedbackVOMeta.STATUS , value = "状态" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = InventoryFeedbackVOMeta.USER_ID , value = "用户" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = InventoryFeedbackVOMeta.CONTENT , value = "内容" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = InventoryFeedbackVOMeta.PICTURE_ID , value = "照片" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = InventoryFeedbackVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true , ignorePrimaryKey = true)
	@ApiOperationSupport(order=1 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = InventoryFeedbackServiceProxy.INSERT , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(InventoryFeedbackServiceProxy.INSERT)
	public Result insert(InventoryFeedbackVO inventoryFeedbackVO) {
		Result result=inventoryFeedbackService.insert(inventoryFeedbackVO,false);
		return result;
	}



	/**
	 * 删除盘点反馈
	*/
	@ApiOperation(value = "删除盘点反馈")
	@ApiImplicitParams({
		@ApiImplicitParam(name = InventoryFeedbackVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class)
	})
	@ApiOperationSupport(order=2 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = InventoryFeedbackServiceProxy.DELETE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(InventoryFeedbackServiceProxy.DELETE)
	public Result deleteById(String id) {
		this.validator().asserts(id).require("缺少id值");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}
		// 引用校验
		ReferCause cause =  inventoryFeedbackService.hasRefers(id);
		// 判断是否可以删除
		this.validator().asserts(cause.hasRefer()).requireEqual("不允许删除当前记录："+cause.message(),false);
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}
		Result result=inventoryFeedbackService.deleteByIdLogical(id);
		return result;
	}


	/**
	 * 批量删除盘点反馈 <br>
	 * 联合主键时，请自行调整实现
	*/
	@ApiOperation(value = "批量删除盘点反馈")
	@ApiImplicitParams({
		@ApiImplicitParam(name = InventoryFeedbackVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
	})
	@ApiOperationSupport(order=3 , author="金杰 , maillank@qq.com") 
	@SentinelResource(value = InventoryFeedbackServiceProxy.DELETE_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(InventoryFeedbackServiceProxy.DELETE_BY_IDS)
	public Result deleteByIds(List<String> ids) {

		// 参数校验
		this.validator().asserts(ids).require("缺少ids参数");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}

		// 查询引用
		Map<String, ReferCause> causeMap = inventoryFeedbackService.hasRefers(ids);
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
			Result result=inventoryFeedbackService.deleteByIdsLogical(canDeleteIds);
			return result;
		} else if (canDeleteIds.size()>0 && canDeleteIds.size() < ids.size()) {
			// 如果部分行可以删除
			Result result=inventoryFeedbackService.deleteByIdsLogical(canDeleteIds);
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
	 * 更新盘点反馈
	*/
	@ApiOperation(value = "更新盘点反馈")
	@ApiImplicitParams({
		@ApiImplicitParam(name = InventoryFeedbackVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = InventoryFeedbackVOMeta.INVENTORY_ID , value = "盘点" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = InventoryFeedbackVOMeta.STATUS , value = "状态" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = InventoryFeedbackVOMeta.USER_ID , value = "用户" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = InventoryFeedbackVOMeta.CONTENT , value = "内容" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = InventoryFeedbackVOMeta.PICTURE_ID , value = "照片" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = InventoryFeedbackVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
	@ApiOperationSupport( order=4 , author="金杰 , maillank@qq.com" ,  ignoreParameters = { InventoryFeedbackVOMeta.PAGE_INDEX , InventoryFeedbackVOMeta.PAGE_SIZE , InventoryFeedbackVOMeta.SEARCH_FIELD , InventoryFeedbackVOMeta.FUZZY_FIELD , InventoryFeedbackVOMeta.SEARCH_VALUE , InventoryFeedbackVOMeta.DIRTY_FIELDS , InventoryFeedbackVOMeta.SORT_FIELD , InventoryFeedbackVOMeta.SORT_TYPE , InventoryFeedbackVOMeta.IDS } )
	@SentinelResource(value = InventoryFeedbackServiceProxy.UPDATE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(InventoryFeedbackServiceProxy.UPDATE)
	public Result update(InventoryFeedbackVO inventoryFeedbackVO) {
		Result result=inventoryFeedbackService.update(inventoryFeedbackVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 保存盘点反馈
	*/
	@ApiOperation(value = "保存盘点反馈")
	@ApiImplicitParams({
		@ApiImplicitParam(name = InventoryFeedbackVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = InventoryFeedbackVOMeta.INVENTORY_ID , value = "盘点" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = InventoryFeedbackVOMeta.STATUS , value = "状态" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = InventoryFeedbackVOMeta.USER_ID , value = "用户" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = InventoryFeedbackVOMeta.CONTENT , value = "内容" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = InventoryFeedbackVOMeta.PICTURE_ID , value = "照片" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = InventoryFeedbackVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
	@ApiOperationSupport(order=5 ,  ignoreParameters = { InventoryFeedbackVOMeta.PAGE_INDEX , InventoryFeedbackVOMeta.PAGE_SIZE , InventoryFeedbackVOMeta.SEARCH_FIELD , InventoryFeedbackVOMeta.FUZZY_FIELD , InventoryFeedbackVOMeta.SEARCH_VALUE , InventoryFeedbackVOMeta.DIRTY_FIELDS , InventoryFeedbackVOMeta.SORT_FIELD , InventoryFeedbackVOMeta.SORT_TYPE , InventoryFeedbackVOMeta.IDS } )
	@SentinelResource(value = InventoryFeedbackServiceProxy.SAVE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(InventoryFeedbackServiceProxy.SAVE)
	public Result save(InventoryFeedbackVO inventoryFeedbackVO) {
		Result result=inventoryFeedbackService.save(inventoryFeedbackVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 获取盘点反馈
	*/
	@ApiOperation(value = "获取盘点反馈")
	@ApiImplicitParams({
		@ApiImplicitParam(name = InventoryFeedbackVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "1"),
	})
	@ApiOperationSupport(order=6 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = InventoryFeedbackServiceProxy.GET_BY_ID , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(InventoryFeedbackServiceProxy.GET_BY_ID)
	public Result<InventoryFeedback> getById(String id) {
		Result<InventoryFeedback> result=new Result<>();
		InventoryFeedback inventoryFeedback=inventoryFeedbackService.getById(id);
		// join 关联的对象
		inventoryFeedbackService.dao().fill(inventoryFeedback)
			.with("user")
			.execute();
		result.success(true).data(inventoryFeedback);
		return result;
	}


	/**
	 * 批量获取盘点反馈 <br>
	 * 联合主键时，请自行调整实现
	*/
		@ApiOperation(value = "批量获取盘点反馈")
		@ApiImplicitParams({
				@ApiImplicitParam(name = InventoryFeedbackVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
		})
		@ApiOperationSupport(order=3 , author="金杰 , maillank@qq.com") 
		@SentinelResource(value = InventoryFeedbackServiceProxy.GET_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(InventoryFeedbackServiceProxy.GET_BY_IDS)
	public Result<List<InventoryFeedback>> getByIds(List<String> ids) {
		Result<List<InventoryFeedback>> result=new Result<>();
		List<InventoryFeedback> list=inventoryFeedbackService.queryListByIds(ids);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 查询盘点反馈
	*/
	@ApiOperation(value = "查询盘点反馈")
	@ApiImplicitParams({
		@ApiImplicitParam(name = InventoryFeedbackVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = InventoryFeedbackVOMeta.INVENTORY_ID , value = "盘点" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = InventoryFeedbackVOMeta.STATUS , value = "状态" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = InventoryFeedbackVOMeta.USER_ID , value = "用户" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = InventoryFeedbackVOMeta.CONTENT , value = "内容" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = InventoryFeedbackVOMeta.PICTURE_ID , value = "照片" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = InventoryFeedbackVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=5 , author="金杰 , maillank@qq.com" ,  ignoreParameters = { InventoryFeedbackVOMeta.PAGE_INDEX , InventoryFeedbackVOMeta.PAGE_SIZE } )
	@SentinelResource(value = InventoryFeedbackServiceProxy.QUERY_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(InventoryFeedbackServiceProxy.QUERY_LIST)
	public Result<List<InventoryFeedback>> queryList(InventoryFeedbackVO sample) {
		Result<List<InventoryFeedback>> result=new Result<>();
		List<InventoryFeedback> list=inventoryFeedbackService.queryList(sample);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 分页查询盘点反馈
	*/
	@ApiOperation(value = "分页查询盘点反馈")
	@ApiImplicitParams({
		@ApiImplicitParam(name = InventoryFeedbackVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = InventoryFeedbackVOMeta.INVENTORY_ID , value = "盘点" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = InventoryFeedbackVOMeta.STATUS , value = "状态" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = InventoryFeedbackVOMeta.USER_ID , value = "用户" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = InventoryFeedbackVOMeta.CONTENT , value = "内容" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = InventoryFeedbackVOMeta.PICTURE_ID , value = "照片" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = InventoryFeedbackVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=8 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = InventoryFeedbackServiceProxy.QUERY_PAGED_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(InventoryFeedbackServiceProxy.QUERY_PAGED_LIST)
	public Result<PagedList<InventoryFeedback>> queryPagedList(InventoryFeedbackVO sample) {
		Result<PagedList<InventoryFeedback>> result=new Result<>();
		PagedList<InventoryFeedback> list=inventoryFeedbackService.queryPagedList(sample,sample.getPageSize(),sample.getPageIndex());
		// join 关联的对象
		inventoryFeedbackService.dao().fill(list)
			.with("user")
			.execute();
		result.success(true).data(list);
		return result;
	}






}