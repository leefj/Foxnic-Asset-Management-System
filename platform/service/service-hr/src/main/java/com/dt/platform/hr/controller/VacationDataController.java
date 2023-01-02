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


import com.dt.platform.proxy.hr.VacationDataServiceProxy;
import com.dt.platform.domain.hr.meta.VacationDataVOMeta;
import com.dt.platform.domain.hr.VacationData;
import com.dt.platform.domain.hr.VacationDataVO;
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
import com.dt.platform.domain.hr.meta.VacationDataMeta;
import java.math.BigDecimal;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.hr.service.IVacationDataService;
import com.github.foxnic.api.validate.annotations.NotNull;

/**
 * <p>
 * 人员休假接口控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-01-02 14:35:28
*/

@InDoc
@Api(tags = "人员休假")
@RestController("HrVacationDataController")
public class VacationDataController extends SuperController {

	@Autowired
	private IVacationDataService vacationDataService;


	/**
	 * 添加人员休假
	*/
	@ApiOperation(value = "添加人员休假")
	@ApiImplicitParams({
		@ApiImplicitParam(name = VacationDataVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = VacationDataVOMeta.EMPLOYEE_ID , value = "人员" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = VacationDataVOMeta.EMPLOYEE_NAME , value = "姓名" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = VacationDataVOMeta.EMPLOYEE_NUMBER , value = "工号" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = VacationDataVOMeta.TYPE , value = "休假类型" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = VacationDataVOMeta.CONTENT , value = "内容" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = VacationDataVOMeta.DURATION , value = "时长" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = VacationDataVOMeta.S_TIME , value = "开始时间" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = VacationDataVOMeta.E_TIME , value = "结束时间" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = VacationDataVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true , ignorePrimaryKey = true)
	@ApiOperationSupport(order=1 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = VacationDataServiceProxy.INSERT , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(VacationDataServiceProxy.INSERT)
	public Result insert(VacationDataVO vacationDataVO) {
		Result result=vacationDataService.insert(vacationDataVO,false);
		return result;
	}



	/**
	 * 删除人员休假
	*/
	@ApiOperation(value = "删除人员休假")
	@ApiImplicitParams({
		@ApiImplicitParam(name = VacationDataVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class)
	})
	@ApiOperationSupport(order=2 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = VacationDataServiceProxy.DELETE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(VacationDataServiceProxy.DELETE)
	public Result deleteById(String id) {
		this.validator().asserts(id).require("缺少id值");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}
		// 引用校验
		ReferCause cause =  vacationDataService.hasRefers(id);
		// 判断是否可以删除
		this.validator().asserts(cause.hasRefer()).requireEqual("不允许删除当前记录："+cause.message(),false);
		if(this.validator().failure()) {
			return this.validator().getFirstResult().messageLevel4Confirm();
		}
		Result result=vacationDataService.deleteByIdLogical(id);
		return result;
	}


	/**
	 * 批量删除人员休假 <br>
	 * 联合主键时，请自行调整实现
	*/
	@ApiOperation(value = "批量删除人员休假")
	@ApiImplicitParams({
		@ApiImplicitParam(name = VacationDataVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
	})
	@ApiOperationSupport(order=3 , author="金杰 , maillank@qq.com") 
	@SentinelResource(value = VacationDataServiceProxy.DELETE_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(VacationDataServiceProxy.DELETE_BY_IDS)
	public Result deleteByIds(List<String> ids) {

		// 参数校验
		this.validator().asserts(ids).require("缺少ids参数");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}

		// 查询引用
		Map<String, ReferCause> causeMap = vacationDataService.hasRefers(ids);
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
			Result result=vacationDataService.deleteByIdsLogical(canDeleteIds);
			return result;
		} else if (canDeleteIds.size()>0 && canDeleteIds.size() < ids.size()) {
			// 如果部分行可以删除
			Result result=vacationDataService.deleteByIdsLogical(canDeleteIds);
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
	 * 更新人员休假
	*/
	@ApiOperation(value = "更新人员休假")
	@ApiImplicitParams({
		@ApiImplicitParam(name = VacationDataVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = VacationDataVOMeta.EMPLOYEE_ID , value = "人员" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = VacationDataVOMeta.EMPLOYEE_NAME , value = "姓名" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = VacationDataVOMeta.EMPLOYEE_NUMBER , value = "工号" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = VacationDataVOMeta.TYPE , value = "休假类型" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = VacationDataVOMeta.CONTENT , value = "内容" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = VacationDataVOMeta.DURATION , value = "时长" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = VacationDataVOMeta.S_TIME , value = "开始时间" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = VacationDataVOMeta.E_TIME , value = "结束时间" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = VacationDataVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
	@ApiOperationSupport( order=4 , author="金杰 , maillank@qq.com" ,  ignoreParameters = { VacationDataVOMeta.PAGE_INDEX , VacationDataVOMeta.PAGE_SIZE , VacationDataVOMeta.SEARCH_FIELD , VacationDataVOMeta.FUZZY_FIELD , VacationDataVOMeta.SEARCH_VALUE , VacationDataVOMeta.DIRTY_FIELDS , VacationDataVOMeta.SORT_FIELD , VacationDataVOMeta.SORT_TYPE , VacationDataVOMeta.DATA_ORIGIN , VacationDataVOMeta.QUERY_LOGIC , VacationDataVOMeta.IDS } )
	@SentinelResource(value = VacationDataServiceProxy.UPDATE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(VacationDataServiceProxy.UPDATE)
	public Result update(VacationDataVO vacationDataVO) {
		Result result=vacationDataService.update(vacationDataVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 保存人员休假
	*/
	@ApiOperation(value = "保存人员休假")
	@ApiImplicitParams({
		@ApiImplicitParam(name = VacationDataVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = VacationDataVOMeta.EMPLOYEE_ID , value = "人员" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = VacationDataVOMeta.EMPLOYEE_NAME , value = "姓名" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = VacationDataVOMeta.EMPLOYEE_NUMBER , value = "工号" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = VacationDataVOMeta.TYPE , value = "休假类型" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = VacationDataVOMeta.CONTENT , value = "内容" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = VacationDataVOMeta.DURATION , value = "时长" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = VacationDataVOMeta.S_TIME , value = "开始时间" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = VacationDataVOMeta.E_TIME , value = "结束时间" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = VacationDataVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
	@ApiOperationSupport(order=5 ,  ignoreParameters = { VacationDataVOMeta.PAGE_INDEX , VacationDataVOMeta.PAGE_SIZE , VacationDataVOMeta.SEARCH_FIELD , VacationDataVOMeta.FUZZY_FIELD , VacationDataVOMeta.SEARCH_VALUE , VacationDataVOMeta.DIRTY_FIELDS , VacationDataVOMeta.SORT_FIELD , VacationDataVOMeta.SORT_TYPE , VacationDataVOMeta.DATA_ORIGIN , VacationDataVOMeta.QUERY_LOGIC , VacationDataVOMeta.IDS } )
	@SentinelResource(value = VacationDataServiceProxy.SAVE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(VacationDataServiceProxy.SAVE)
	public Result save(VacationDataVO vacationDataVO) {
		Result result=vacationDataService.save(vacationDataVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 获取人员休假
	*/
	@ApiOperation(value = "获取人员休假")
	@ApiImplicitParams({
		@ApiImplicitParam(name = VacationDataVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "1"),
	})
	@ApiOperationSupport(order=6 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = VacationDataServiceProxy.GET_BY_ID , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(VacationDataServiceProxy.GET_BY_ID)
	public Result<VacationData> getById(String id) {
		Result<VacationData> result=new Result<>();
		VacationData vacationData=vacationDataService.getById(id);
		result.success(true).data(vacationData);
		return result;
	}


	/**
	 * 批量获取人员休假 <br>
	 * 联合主键时，请自行调整实现
	*/
		@ApiOperation(value = "批量获取人员休假")
		@ApiImplicitParams({
				@ApiImplicitParam(name = VacationDataVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
		})
		@ApiOperationSupport(order=3 , author="金杰 , maillank@qq.com") 
		@SentinelResource(value = VacationDataServiceProxy.GET_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(VacationDataServiceProxy.GET_BY_IDS)
	public Result<List<VacationData>> getByIds(List<String> ids) {
		Result<List<VacationData>> result=new Result<>();
		List<VacationData> list=vacationDataService.queryListByIds(ids);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 查询人员休假
	*/
	@ApiOperation(value = "查询人员休假")
	@ApiImplicitParams({
		@ApiImplicitParam(name = VacationDataVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = VacationDataVOMeta.EMPLOYEE_ID , value = "人员" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = VacationDataVOMeta.EMPLOYEE_NAME , value = "姓名" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = VacationDataVOMeta.EMPLOYEE_NUMBER , value = "工号" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = VacationDataVOMeta.TYPE , value = "休假类型" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = VacationDataVOMeta.CONTENT , value = "内容" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = VacationDataVOMeta.DURATION , value = "时长" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = VacationDataVOMeta.S_TIME , value = "开始时间" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = VacationDataVOMeta.E_TIME , value = "结束时间" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = VacationDataVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=5 , author="金杰 , maillank@qq.com" ,  ignoreParameters = { VacationDataVOMeta.PAGE_INDEX , VacationDataVOMeta.PAGE_SIZE } )
	@SentinelResource(value = VacationDataServiceProxy.QUERY_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(VacationDataServiceProxy.QUERY_LIST)
	public Result<List<VacationData>> queryList(VacationDataVO sample) {
		Result<List<VacationData>> result=new Result<>();
		List<VacationData> list=vacationDataService.queryList(sample);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 分页查询人员休假
	*/
	@ApiOperation(value = "分页查询人员休假")
	@ApiImplicitParams({
		@ApiImplicitParam(name = VacationDataVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = VacationDataVOMeta.EMPLOYEE_ID , value = "人员" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = VacationDataVOMeta.EMPLOYEE_NAME , value = "姓名" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = VacationDataVOMeta.EMPLOYEE_NUMBER , value = "工号" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = VacationDataVOMeta.TYPE , value = "休假类型" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = VacationDataVOMeta.CONTENT , value = "内容" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = VacationDataVOMeta.DURATION , value = "时长" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = VacationDataVOMeta.S_TIME , value = "开始时间" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = VacationDataVOMeta.E_TIME , value = "结束时间" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = VacationDataVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=8 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = VacationDataServiceProxy.QUERY_PAGED_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(VacationDataServiceProxy.QUERY_PAGED_LIST)
	public Result<PagedList<VacationData>> queryPagedList(VacationDataVO sample) {
		Result<PagedList<VacationData>> result=new Result<>();
		PagedList<VacationData> list=vacationDataService.queryPagedList(sample,sample.getPageSize(),sample.getPageIndex());
		result.success(true).data(list);
		return result;
	}






}