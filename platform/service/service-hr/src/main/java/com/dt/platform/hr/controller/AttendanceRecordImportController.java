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


import com.dt.platform.proxy.hr.AttendanceRecordImportServiceProxy;
import com.dt.platform.domain.hr.meta.AttendanceRecordImportVOMeta;
import com.dt.platform.domain.hr.AttendanceRecordImport;
import com.dt.platform.domain.hr.AttendanceRecordImportVO;
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
import com.dt.platform.domain.hr.meta.AttendanceRecordImportMeta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.hr.service.IAttendanceRecordImportService;
import com.github.foxnic.api.validate.annotations.NotNull;

/**
 * <p>
 * 考勤导入接口控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-01-02 14:26:30
*/

@InDoc
@Api(tags = "考勤导入")
@RestController("HrAttendanceRecordImportController")
public class AttendanceRecordImportController extends SuperController {

	@Autowired
	private IAttendanceRecordImportService attendanceRecordImportService;


	/**
	 * 添加考勤导入
	*/
	@ApiOperation(value = "添加考勤导入")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AttendanceRecordImportVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AttendanceRecordImportVOMeta.SOURCE , value = "人员" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AttendanceRecordImportVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true , ignorePrimaryKey = true)
	@ApiOperationSupport(order=1 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = AttendanceRecordImportServiceProxy.INSERT , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AttendanceRecordImportServiceProxy.INSERT)
	public Result insert(AttendanceRecordImportVO attendanceRecordImportVO) {
		Result result=attendanceRecordImportService.insert(attendanceRecordImportVO,false);
		return result;
	}



	/**
	 * 删除考勤导入
	*/
	@ApiOperation(value = "删除考勤导入")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AttendanceRecordImportVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class)
	})
	@ApiOperationSupport(order=2 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = AttendanceRecordImportServiceProxy.DELETE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AttendanceRecordImportServiceProxy.DELETE)
	public Result deleteById(String id) {
		this.validator().asserts(id).require("缺少id值");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}
		// 引用校验
		ReferCause cause =  attendanceRecordImportService.hasRefers(id);
		// 判断是否可以删除
		this.validator().asserts(cause.hasRefer()).requireEqual("不允许删除当前记录："+cause.message(),false);
		if(this.validator().failure()) {
			return this.validator().getFirstResult().messageLevel4Confirm();
		}
		Result result=attendanceRecordImportService.deleteByIdLogical(id);
		return result;
	}


	/**
	 * 批量删除考勤导入 <br>
	 * 联合主键时，请自行调整实现
	*/
	@ApiOperation(value = "批量删除考勤导入")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AttendanceRecordImportVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
	})
	@ApiOperationSupport(order=3 , author="金杰 , maillank@qq.com") 
	@SentinelResource(value = AttendanceRecordImportServiceProxy.DELETE_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AttendanceRecordImportServiceProxy.DELETE_BY_IDS)
	public Result deleteByIds(List<String> ids) {

		// 参数校验
		this.validator().asserts(ids).require("缺少ids参数");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}

		// 查询引用
		Map<String, ReferCause> causeMap = attendanceRecordImportService.hasRefers(ids);
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
			Result result=attendanceRecordImportService.deleteByIdsLogical(canDeleteIds);
			return result;
		} else if (canDeleteIds.size()>0 && canDeleteIds.size() < ids.size()) {
			// 如果部分行可以删除
			Result result=attendanceRecordImportService.deleteByIdsLogical(canDeleteIds);
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
	 * 更新考勤导入
	*/
	@ApiOperation(value = "更新考勤导入")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AttendanceRecordImportVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AttendanceRecordImportVOMeta.SOURCE , value = "人员" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AttendanceRecordImportVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
	@ApiOperationSupport( order=4 , author="金杰 , maillank@qq.com" ,  ignoreParameters = { AttendanceRecordImportVOMeta.PAGE_INDEX , AttendanceRecordImportVOMeta.PAGE_SIZE , AttendanceRecordImportVOMeta.SEARCH_FIELD , AttendanceRecordImportVOMeta.FUZZY_FIELD , AttendanceRecordImportVOMeta.SEARCH_VALUE , AttendanceRecordImportVOMeta.DIRTY_FIELDS , AttendanceRecordImportVOMeta.SORT_FIELD , AttendanceRecordImportVOMeta.SORT_TYPE , AttendanceRecordImportVOMeta.DATA_ORIGIN , AttendanceRecordImportVOMeta.QUERY_LOGIC , AttendanceRecordImportVOMeta.IDS } )
	@SentinelResource(value = AttendanceRecordImportServiceProxy.UPDATE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AttendanceRecordImportServiceProxy.UPDATE)
	public Result update(AttendanceRecordImportVO attendanceRecordImportVO) {
		Result result=attendanceRecordImportService.update(attendanceRecordImportVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 保存考勤导入
	*/
	@ApiOperation(value = "保存考勤导入")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AttendanceRecordImportVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AttendanceRecordImportVOMeta.SOURCE , value = "人员" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AttendanceRecordImportVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
	@ApiOperationSupport(order=5 ,  ignoreParameters = { AttendanceRecordImportVOMeta.PAGE_INDEX , AttendanceRecordImportVOMeta.PAGE_SIZE , AttendanceRecordImportVOMeta.SEARCH_FIELD , AttendanceRecordImportVOMeta.FUZZY_FIELD , AttendanceRecordImportVOMeta.SEARCH_VALUE , AttendanceRecordImportVOMeta.DIRTY_FIELDS , AttendanceRecordImportVOMeta.SORT_FIELD , AttendanceRecordImportVOMeta.SORT_TYPE , AttendanceRecordImportVOMeta.DATA_ORIGIN , AttendanceRecordImportVOMeta.QUERY_LOGIC , AttendanceRecordImportVOMeta.IDS } )
	@SentinelResource(value = AttendanceRecordImportServiceProxy.SAVE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AttendanceRecordImportServiceProxy.SAVE)
	public Result save(AttendanceRecordImportVO attendanceRecordImportVO) {
		Result result=attendanceRecordImportService.save(attendanceRecordImportVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 获取考勤导入
	*/
	@ApiOperation(value = "获取考勤导入")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AttendanceRecordImportVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "1"),
	})
	@ApiOperationSupport(order=6 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = AttendanceRecordImportServiceProxy.GET_BY_ID , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AttendanceRecordImportServiceProxy.GET_BY_ID)
	public Result<AttendanceRecordImport> getById(String id) {
		Result<AttendanceRecordImport> result=new Result<>();
		AttendanceRecordImport attendanceRecordImport=attendanceRecordImportService.getById(id);
		result.success(true).data(attendanceRecordImport);
		return result;
	}


	/**
	 * 批量获取考勤导入 <br>
	 * 联合主键时，请自行调整实现
	*/
		@ApiOperation(value = "批量获取考勤导入")
		@ApiImplicitParams({
				@ApiImplicitParam(name = AttendanceRecordImportVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
		})
		@ApiOperationSupport(order=3 , author="金杰 , maillank@qq.com") 
		@SentinelResource(value = AttendanceRecordImportServiceProxy.GET_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AttendanceRecordImportServiceProxy.GET_BY_IDS)
	public Result<List<AttendanceRecordImport>> getByIds(List<String> ids) {
		Result<List<AttendanceRecordImport>> result=new Result<>();
		List<AttendanceRecordImport> list=attendanceRecordImportService.queryListByIds(ids);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 查询考勤导入
	*/
	@ApiOperation(value = "查询考勤导入")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AttendanceRecordImportVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AttendanceRecordImportVOMeta.SOURCE , value = "人员" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AttendanceRecordImportVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=5 , author="金杰 , maillank@qq.com" ,  ignoreParameters = { AttendanceRecordImportVOMeta.PAGE_INDEX , AttendanceRecordImportVOMeta.PAGE_SIZE } )
	@SentinelResource(value = AttendanceRecordImportServiceProxy.QUERY_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AttendanceRecordImportServiceProxy.QUERY_LIST)
	public Result<List<AttendanceRecordImport>> queryList(AttendanceRecordImportVO sample) {
		Result<List<AttendanceRecordImport>> result=new Result<>();
		List<AttendanceRecordImport> list=attendanceRecordImportService.queryList(sample);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 分页查询考勤导入
	*/
	@ApiOperation(value = "分页查询考勤导入")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AttendanceRecordImportVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = AttendanceRecordImportVOMeta.SOURCE , value = "人员" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = AttendanceRecordImportVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=8 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = AttendanceRecordImportServiceProxy.QUERY_PAGED_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AttendanceRecordImportServiceProxy.QUERY_PAGED_LIST)
	public Result<PagedList<AttendanceRecordImport>> queryPagedList(AttendanceRecordImportVO sample) {
		Result<PagedList<AttendanceRecordImport>> result=new Result<>();
		PagedList<AttendanceRecordImport> list=attendanceRecordImportService.queryPagedList(sample,sample.getPageSize(),sample.getPageIndex());
		result.success(true).data(list);
		return result;
	}






}