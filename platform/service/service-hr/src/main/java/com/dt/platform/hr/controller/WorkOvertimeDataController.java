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


import com.dt.platform.proxy.hr.WorkOvertimeDataServiceProxy;
import com.dt.platform.domain.hr.meta.WorkOvertimeDataVOMeta;
import com.dt.platform.domain.hr.WorkOvertimeData;
import com.dt.platform.domain.hr.WorkOvertimeDataVO;
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
import com.dt.platform.domain.hr.meta.WorkOvertimeDataMeta;
import java.math.BigDecimal;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.hr.service.IWorkOvertimeDataService;
import com.github.foxnic.api.validate.annotations.NotNull;

/**
 * <p>
 * 人员加班接口控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-01-02 14:37:42
*/

@InDoc
@Api(tags = "人员加班")
@RestController("HrWorkOvertimeDataController")
public class WorkOvertimeDataController extends SuperController {

	@Autowired
	private IWorkOvertimeDataService workOvertimeDataService;


	/**
	 * 添加人员加班
	*/
	@ApiOperation(value = "添加人员加班")
	@ApiImplicitParams({
		@ApiImplicitParam(name = WorkOvertimeDataVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = WorkOvertimeDataVOMeta.EMPLOYEE_ID , value = "人员" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = WorkOvertimeDataVOMeta.EMPLOYEE_NAME , value = "姓名" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = WorkOvertimeDataVOMeta.EMPLOYEE_NUMBER , value = "工号" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = WorkOvertimeDataVOMeta.CONTENT , value = "内容" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = WorkOvertimeDataVOMeta.DURATION , value = "加班时长" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = WorkOvertimeDataVOMeta.S_TIME , value = "开始时间" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = WorkOvertimeDataVOMeta.E_TIME , value = "结束时间" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = WorkOvertimeDataVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true , ignorePrimaryKey = true)
	@ApiOperationSupport(order=1 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = WorkOvertimeDataServiceProxy.INSERT , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(WorkOvertimeDataServiceProxy.INSERT)
	public Result insert(WorkOvertimeDataVO workOvertimeDataVO) {
		Result result=workOvertimeDataService.insert(workOvertimeDataVO,false);
		return result;
	}



	/**
	 * 删除人员加班
	*/
	@ApiOperation(value = "删除人员加班")
	@ApiImplicitParams({
		@ApiImplicitParam(name = WorkOvertimeDataVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class)
	})
	@ApiOperationSupport(order=2 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = WorkOvertimeDataServiceProxy.DELETE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(WorkOvertimeDataServiceProxy.DELETE)
	public Result deleteById(String id) {
		this.validator().asserts(id).require("缺少id值");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}
		// 引用校验
		ReferCause cause =  workOvertimeDataService.hasRefers(id);
		// 判断是否可以删除
		this.validator().asserts(cause.hasRefer()).requireEqual("不允许删除当前记录："+cause.message(),false);
		if(this.validator().failure()) {
			return this.validator().getFirstResult().messageLevel4Confirm();
		}
		Result result=workOvertimeDataService.deleteByIdLogical(id);
		return result;
	}


	/**
	 * 批量删除人员加班 <br>
	 * 联合主键时，请自行调整实现
	*/
	@ApiOperation(value = "批量删除人员加班")
	@ApiImplicitParams({
		@ApiImplicitParam(name = WorkOvertimeDataVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
	})
	@ApiOperationSupport(order=3 , author="金杰 , maillank@qq.com") 
	@SentinelResource(value = WorkOvertimeDataServiceProxy.DELETE_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(WorkOvertimeDataServiceProxy.DELETE_BY_IDS)
	public Result deleteByIds(List<String> ids) {

		// 参数校验
		this.validator().asserts(ids).require("缺少ids参数");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}

		// 查询引用
		Map<String, ReferCause> causeMap = workOvertimeDataService.hasRefers(ids);
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
			Result result=workOvertimeDataService.deleteByIdsLogical(canDeleteIds);
			return result;
		} else if (canDeleteIds.size()>0 && canDeleteIds.size() < ids.size()) {
			// 如果部分行可以删除
			Result result=workOvertimeDataService.deleteByIdsLogical(canDeleteIds);
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
	 * 更新人员加班
	*/
	@ApiOperation(value = "更新人员加班")
	@ApiImplicitParams({
		@ApiImplicitParam(name = WorkOvertimeDataVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = WorkOvertimeDataVOMeta.EMPLOYEE_ID , value = "人员" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = WorkOvertimeDataVOMeta.EMPLOYEE_NAME , value = "姓名" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = WorkOvertimeDataVOMeta.EMPLOYEE_NUMBER , value = "工号" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = WorkOvertimeDataVOMeta.CONTENT , value = "内容" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = WorkOvertimeDataVOMeta.DURATION , value = "加班时长" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = WorkOvertimeDataVOMeta.S_TIME , value = "开始时间" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = WorkOvertimeDataVOMeta.E_TIME , value = "结束时间" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = WorkOvertimeDataVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
	@ApiOperationSupport( order=4 , author="金杰 , maillank@qq.com" ,  ignoreParameters = { WorkOvertimeDataVOMeta.PAGE_INDEX , WorkOvertimeDataVOMeta.PAGE_SIZE , WorkOvertimeDataVOMeta.SEARCH_FIELD , WorkOvertimeDataVOMeta.FUZZY_FIELD , WorkOvertimeDataVOMeta.SEARCH_VALUE , WorkOvertimeDataVOMeta.DIRTY_FIELDS , WorkOvertimeDataVOMeta.SORT_FIELD , WorkOvertimeDataVOMeta.SORT_TYPE , WorkOvertimeDataVOMeta.DATA_ORIGIN , WorkOvertimeDataVOMeta.QUERY_LOGIC , WorkOvertimeDataVOMeta.IDS } )
	@SentinelResource(value = WorkOvertimeDataServiceProxy.UPDATE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(WorkOvertimeDataServiceProxy.UPDATE)
	public Result update(WorkOvertimeDataVO workOvertimeDataVO) {
		Result result=workOvertimeDataService.update(workOvertimeDataVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 保存人员加班
	*/
	@ApiOperation(value = "保存人员加班")
	@ApiImplicitParams({
		@ApiImplicitParam(name = WorkOvertimeDataVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = WorkOvertimeDataVOMeta.EMPLOYEE_ID , value = "人员" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = WorkOvertimeDataVOMeta.EMPLOYEE_NAME , value = "姓名" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = WorkOvertimeDataVOMeta.EMPLOYEE_NUMBER , value = "工号" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = WorkOvertimeDataVOMeta.CONTENT , value = "内容" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = WorkOvertimeDataVOMeta.DURATION , value = "加班时长" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = WorkOvertimeDataVOMeta.S_TIME , value = "开始时间" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = WorkOvertimeDataVOMeta.E_TIME , value = "结束时间" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = WorkOvertimeDataVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
	@ApiOperationSupport(order=5 ,  ignoreParameters = { WorkOvertimeDataVOMeta.PAGE_INDEX , WorkOvertimeDataVOMeta.PAGE_SIZE , WorkOvertimeDataVOMeta.SEARCH_FIELD , WorkOvertimeDataVOMeta.FUZZY_FIELD , WorkOvertimeDataVOMeta.SEARCH_VALUE , WorkOvertimeDataVOMeta.DIRTY_FIELDS , WorkOvertimeDataVOMeta.SORT_FIELD , WorkOvertimeDataVOMeta.SORT_TYPE , WorkOvertimeDataVOMeta.DATA_ORIGIN , WorkOvertimeDataVOMeta.QUERY_LOGIC , WorkOvertimeDataVOMeta.IDS } )
	@SentinelResource(value = WorkOvertimeDataServiceProxy.SAVE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(WorkOvertimeDataServiceProxy.SAVE)
	public Result save(WorkOvertimeDataVO workOvertimeDataVO) {
		Result result=workOvertimeDataService.save(workOvertimeDataVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 获取人员加班
	*/
	@ApiOperation(value = "获取人员加班")
	@ApiImplicitParams({
		@ApiImplicitParam(name = WorkOvertimeDataVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "1"),
	})
	@ApiOperationSupport(order=6 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = WorkOvertimeDataServiceProxy.GET_BY_ID , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(WorkOvertimeDataServiceProxy.GET_BY_ID)
	public Result<WorkOvertimeData> getById(String id) {
		Result<WorkOvertimeData> result=new Result<>();
		WorkOvertimeData workOvertimeData=workOvertimeDataService.getById(id);
		result.success(true).data(workOvertimeData);
		return result;
	}


	/**
	 * 批量获取人员加班 <br>
	 * 联合主键时，请自行调整实现
	*/
		@ApiOperation(value = "批量获取人员加班")
		@ApiImplicitParams({
				@ApiImplicitParam(name = WorkOvertimeDataVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
		})
		@ApiOperationSupport(order=3 , author="金杰 , maillank@qq.com") 
		@SentinelResource(value = WorkOvertimeDataServiceProxy.GET_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(WorkOvertimeDataServiceProxy.GET_BY_IDS)
	public Result<List<WorkOvertimeData>> getByIds(List<String> ids) {
		Result<List<WorkOvertimeData>> result=new Result<>();
		List<WorkOvertimeData> list=workOvertimeDataService.queryListByIds(ids);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 查询人员加班
	*/
	@ApiOperation(value = "查询人员加班")
	@ApiImplicitParams({
		@ApiImplicitParam(name = WorkOvertimeDataVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = WorkOvertimeDataVOMeta.EMPLOYEE_ID , value = "人员" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = WorkOvertimeDataVOMeta.EMPLOYEE_NAME , value = "姓名" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = WorkOvertimeDataVOMeta.EMPLOYEE_NUMBER , value = "工号" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = WorkOvertimeDataVOMeta.CONTENT , value = "内容" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = WorkOvertimeDataVOMeta.DURATION , value = "加班时长" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = WorkOvertimeDataVOMeta.S_TIME , value = "开始时间" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = WorkOvertimeDataVOMeta.E_TIME , value = "结束时间" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = WorkOvertimeDataVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=5 , author="金杰 , maillank@qq.com" ,  ignoreParameters = { WorkOvertimeDataVOMeta.PAGE_INDEX , WorkOvertimeDataVOMeta.PAGE_SIZE } )
	@SentinelResource(value = WorkOvertimeDataServiceProxy.QUERY_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(WorkOvertimeDataServiceProxy.QUERY_LIST)
	public Result<List<WorkOvertimeData>> queryList(WorkOvertimeDataVO sample) {
		Result<List<WorkOvertimeData>> result=new Result<>();
		List<WorkOvertimeData> list=workOvertimeDataService.queryList(sample);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 分页查询人员加班
	*/
	@ApiOperation(value = "分页查询人员加班")
	@ApiImplicitParams({
		@ApiImplicitParam(name = WorkOvertimeDataVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = WorkOvertimeDataVOMeta.EMPLOYEE_ID , value = "人员" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = WorkOvertimeDataVOMeta.EMPLOYEE_NAME , value = "姓名" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = WorkOvertimeDataVOMeta.EMPLOYEE_NUMBER , value = "工号" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = WorkOvertimeDataVOMeta.CONTENT , value = "内容" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = WorkOvertimeDataVOMeta.DURATION , value = "加班时长" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = WorkOvertimeDataVOMeta.S_TIME , value = "开始时间" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = WorkOvertimeDataVOMeta.E_TIME , value = "结束时间" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = WorkOvertimeDataVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=8 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = WorkOvertimeDataServiceProxy.QUERY_PAGED_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(WorkOvertimeDataServiceProxy.QUERY_PAGED_LIST)
	public Result<PagedList<WorkOvertimeData>> queryPagedList(WorkOvertimeDataVO sample) {
		Result<PagedList<WorkOvertimeData>> result=new Result<>();
		PagedList<WorkOvertimeData> list=workOvertimeDataService.queryPagedList(sample,sample.getPageSize(),sample.getPageIndex());
		result.success(true).data(list);
		return result;
	}






}