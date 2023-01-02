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


import com.dt.platform.proxy.hr.AttendanceRecordServiceProxy;
import com.dt.platform.domain.hr.meta.AttendanceRecordVOMeta;
import com.dt.platform.domain.hr.AttendanceRecord;
import com.dt.platform.domain.hr.AttendanceRecordVO;
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
import com.dt.platform.domain.hr.meta.AttendanceRecordMeta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.hr.service.IAttendanceRecordService;
import com.github.foxnic.api.validate.annotations.NotNull;

/**
 * <p>
 * 考勤记录接口控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-01-02 14:24:00
*/

@InDoc
@Api(tags = "考勤记录")
@RestController("HrAttendanceRecordController")
public class AttendanceRecordController extends SuperController {

	@Autowired
	private IAttendanceRecordService attendanceRecordService;


	/**
	 * 添加考勤记录
	*/
	@ApiOperation(value = "添加考勤记录")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AttendanceRecordVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AttendanceRecordVOMeta.EMPLOYEE_ID , value = "人员" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AttendanceRecordVOMeta.EMPLOYEE_NAME , value = "姓名" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AttendanceRecordVOMeta.EMPLOYEE_NUMBER , value = "工号" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AttendanceRecordVOMeta.RCD_TIME , value = "记录时间" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = AttendanceRecordVOMeta.SOURCE , value = "来源" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AttendanceRecordVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true , ignorePrimaryKey = true)
	@ApiOperationSupport(order=1 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = AttendanceRecordServiceProxy.INSERT , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AttendanceRecordServiceProxy.INSERT)
	public Result insert(AttendanceRecordVO attendanceRecordVO) {
		Result result=attendanceRecordService.insert(attendanceRecordVO,false);
		return result;
	}



	/**
	 * 删除考勤记录
	*/
	@ApiOperation(value = "删除考勤记录")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AttendanceRecordVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class)
	})
	@ApiOperationSupport(order=2 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = AttendanceRecordServiceProxy.DELETE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AttendanceRecordServiceProxy.DELETE)
	public Result deleteById(String id) {
		this.validator().asserts(id).require("缺少id值");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}
		// 引用校验
		ReferCause cause =  attendanceRecordService.hasRefers(id);
		// 判断是否可以删除
		this.validator().asserts(cause.hasRefer()).requireEqual("不允许删除当前记录："+cause.message(),false);
		if(this.validator().failure()) {
			return this.validator().getFirstResult().messageLevel4Confirm();
		}
		Result result=attendanceRecordService.deleteByIdLogical(id);
		return result;
	}


	/**
	 * 批量删除考勤记录 <br>
	 * 联合主键时，请自行调整实现
	*/
	@ApiOperation(value = "批量删除考勤记录")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AttendanceRecordVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
	})
	@ApiOperationSupport(order=3 , author="金杰 , maillank@qq.com") 
	@SentinelResource(value = AttendanceRecordServiceProxy.DELETE_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AttendanceRecordServiceProxy.DELETE_BY_IDS)
	public Result deleteByIds(List<String> ids) {

		// 参数校验
		this.validator().asserts(ids).require("缺少ids参数");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}

		// 查询引用
		Map<String, ReferCause> causeMap = attendanceRecordService.hasRefers(ids);
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
			Result result=attendanceRecordService.deleteByIdsLogical(canDeleteIds);
			return result;
		} else if (canDeleteIds.size()>0 && canDeleteIds.size() < ids.size()) {
			// 如果部分行可以删除
			Result result=attendanceRecordService.deleteByIdsLogical(canDeleteIds);
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
	 * 更新考勤记录
	*/
	@ApiOperation(value = "更新考勤记录")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AttendanceRecordVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AttendanceRecordVOMeta.EMPLOYEE_ID , value = "人员" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AttendanceRecordVOMeta.EMPLOYEE_NAME , value = "姓名" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AttendanceRecordVOMeta.EMPLOYEE_NUMBER , value = "工号" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AttendanceRecordVOMeta.RCD_TIME , value = "记录时间" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = AttendanceRecordVOMeta.SOURCE , value = "来源" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AttendanceRecordVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
	@ApiOperationSupport( order=4 , author="金杰 , maillank@qq.com" ,  ignoreParameters = { AttendanceRecordVOMeta.PAGE_INDEX , AttendanceRecordVOMeta.PAGE_SIZE , AttendanceRecordVOMeta.SEARCH_FIELD , AttendanceRecordVOMeta.FUZZY_FIELD , AttendanceRecordVOMeta.SEARCH_VALUE , AttendanceRecordVOMeta.DIRTY_FIELDS , AttendanceRecordVOMeta.SORT_FIELD , AttendanceRecordVOMeta.SORT_TYPE , AttendanceRecordVOMeta.DATA_ORIGIN , AttendanceRecordVOMeta.QUERY_LOGIC , AttendanceRecordVOMeta.IDS } )
	@SentinelResource(value = AttendanceRecordServiceProxy.UPDATE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AttendanceRecordServiceProxy.UPDATE)
	public Result update(AttendanceRecordVO attendanceRecordVO) {
		Result result=attendanceRecordService.update(attendanceRecordVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 保存考勤记录
	*/
	@ApiOperation(value = "保存考勤记录")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AttendanceRecordVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AttendanceRecordVOMeta.EMPLOYEE_ID , value = "人员" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AttendanceRecordVOMeta.EMPLOYEE_NAME , value = "姓名" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AttendanceRecordVOMeta.EMPLOYEE_NUMBER , value = "工号" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AttendanceRecordVOMeta.RCD_TIME , value = "记录时间" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = AttendanceRecordVOMeta.SOURCE , value = "来源" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AttendanceRecordVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
	@ApiOperationSupport(order=5 ,  ignoreParameters = { AttendanceRecordVOMeta.PAGE_INDEX , AttendanceRecordVOMeta.PAGE_SIZE , AttendanceRecordVOMeta.SEARCH_FIELD , AttendanceRecordVOMeta.FUZZY_FIELD , AttendanceRecordVOMeta.SEARCH_VALUE , AttendanceRecordVOMeta.DIRTY_FIELDS , AttendanceRecordVOMeta.SORT_FIELD , AttendanceRecordVOMeta.SORT_TYPE , AttendanceRecordVOMeta.DATA_ORIGIN , AttendanceRecordVOMeta.QUERY_LOGIC , AttendanceRecordVOMeta.IDS } )
	@SentinelResource(value = AttendanceRecordServiceProxy.SAVE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AttendanceRecordServiceProxy.SAVE)
	public Result save(AttendanceRecordVO attendanceRecordVO) {
		Result result=attendanceRecordService.save(attendanceRecordVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 获取考勤记录
	*/
	@ApiOperation(value = "获取考勤记录")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AttendanceRecordVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "1"),
	})
	@ApiOperationSupport(order=6 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = AttendanceRecordServiceProxy.GET_BY_ID , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AttendanceRecordServiceProxy.GET_BY_ID)
	public Result<AttendanceRecord> getById(String id) {
		Result<AttendanceRecord> result=new Result<>();
		AttendanceRecord attendanceRecord=attendanceRecordService.getById(id);
		result.success(true).data(attendanceRecord);
		return result;
	}


	/**
	 * 批量获取考勤记录 <br>
	 * 联合主键时，请自行调整实现
	*/
		@ApiOperation(value = "批量获取考勤记录")
		@ApiImplicitParams({
				@ApiImplicitParam(name = AttendanceRecordVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
		})
		@ApiOperationSupport(order=3 , author="金杰 , maillank@qq.com") 
		@SentinelResource(value = AttendanceRecordServiceProxy.GET_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AttendanceRecordServiceProxy.GET_BY_IDS)
	public Result<List<AttendanceRecord>> getByIds(List<String> ids) {
		Result<List<AttendanceRecord>> result=new Result<>();
		List<AttendanceRecord> list=attendanceRecordService.queryListByIds(ids);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 查询考勤记录
	*/
	@ApiOperation(value = "查询考勤记录")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AttendanceRecordVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AttendanceRecordVOMeta.EMPLOYEE_ID , value = "人员" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AttendanceRecordVOMeta.EMPLOYEE_NAME , value = "姓名" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AttendanceRecordVOMeta.EMPLOYEE_NUMBER , value = "工号" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AttendanceRecordVOMeta.RCD_TIME , value = "记录时间" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = AttendanceRecordVOMeta.SOURCE , value = "来源" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AttendanceRecordVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=5 , author="金杰 , maillank@qq.com" ,  ignoreParameters = { AttendanceRecordVOMeta.PAGE_INDEX , AttendanceRecordVOMeta.PAGE_SIZE } )
	@SentinelResource(value = AttendanceRecordServiceProxy.QUERY_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AttendanceRecordServiceProxy.QUERY_LIST)
	public Result<List<AttendanceRecord>> queryList(AttendanceRecordVO sample) {
		Result<List<AttendanceRecord>> result=new Result<>();
		List<AttendanceRecord> list=attendanceRecordService.queryList(sample);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 分页查询考勤记录
	*/
	@ApiOperation(value = "分页查询考勤记录")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AttendanceRecordVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AttendanceRecordVOMeta.EMPLOYEE_ID , value = "人员" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AttendanceRecordVOMeta.EMPLOYEE_NAME , value = "姓名" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AttendanceRecordVOMeta.EMPLOYEE_NUMBER , value = "工号" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AttendanceRecordVOMeta.RCD_TIME , value = "记录时间" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = AttendanceRecordVOMeta.SOURCE , value = "来源" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AttendanceRecordVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=8 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = AttendanceRecordServiceProxy.QUERY_PAGED_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AttendanceRecordServiceProxy.QUERY_PAGED_LIST)
	public Result<PagedList<AttendanceRecord>> queryPagedList(AttendanceRecordVO sample) {
		Result<PagedList<AttendanceRecord>> result=new Result<>();
		PagedList<AttendanceRecord> list=attendanceRecordService.queryPagedList(sample,sample.getPageSize(),sample.getPageIndex());
		result.success(true).data(list);
		return result;
	}






}