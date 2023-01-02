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


import com.dt.platform.proxy.hr.BusinessTravelDataServiceProxy;
import com.dt.platform.domain.hr.meta.BusinessTravelDataVOMeta;
import com.dt.platform.domain.hr.BusinessTravelData;
import com.dt.platform.domain.hr.BusinessTravelDataVO;
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
import com.dt.platform.domain.hr.meta.BusinessTravelDataMeta;
import java.math.BigDecimal;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.hr.service.IBusinessTravelDataService;
import com.github.foxnic.api.validate.annotations.NotNull;

/**
 * <p>
 * 人员出差接口控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-01-02 14:31:45
*/

@InDoc
@Api(tags = "人员出差")
@RestController("HrBusinessTravelDataController")
public class BusinessTravelDataController extends SuperController {

	@Autowired
	private IBusinessTravelDataService businessTravelDataService;


	/**
	 * 添加人员出差
	*/
	@ApiOperation(value = "添加人员出差")
	@ApiImplicitParams({
		@ApiImplicitParam(name = BusinessTravelDataVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = BusinessTravelDataVOMeta.EMPLOYEE_ID , value = "人员" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = BusinessTravelDataVOMeta.EMPLOYEE_NAME , value = "姓名" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = BusinessTravelDataVOMeta.EMPLOYEE_NUMBER , value = "工号" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = BusinessTravelDataVOMeta.CONTENT , value = "内容" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = BusinessTravelDataVOMeta.DURATION , value = "时长" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = BusinessTravelDataVOMeta.S_TIME , value = "开始时间" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = BusinessTravelDataVOMeta.E_TIME , value = "结束时间" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = BusinessTravelDataVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true , ignorePrimaryKey = true)
	@ApiOperationSupport(order=1 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = BusinessTravelDataServiceProxy.INSERT , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(BusinessTravelDataServiceProxy.INSERT)
	public Result insert(BusinessTravelDataVO businessTravelDataVO) {
		Result result=businessTravelDataService.insert(businessTravelDataVO,false);
		return result;
	}



	/**
	 * 删除人员出差
	*/
	@ApiOperation(value = "删除人员出差")
	@ApiImplicitParams({
		@ApiImplicitParam(name = BusinessTravelDataVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class)
	})
	@ApiOperationSupport(order=2 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = BusinessTravelDataServiceProxy.DELETE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(BusinessTravelDataServiceProxy.DELETE)
	public Result deleteById(String id) {
		this.validator().asserts(id).require("缺少id值");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}
		// 引用校验
		ReferCause cause =  businessTravelDataService.hasRefers(id);
		// 判断是否可以删除
		this.validator().asserts(cause.hasRefer()).requireEqual("不允许删除当前记录："+cause.message(),false);
		if(this.validator().failure()) {
			return this.validator().getFirstResult().messageLevel4Confirm();
		}
		Result result=businessTravelDataService.deleteByIdLogical(id);
		return result;
	}


	/**
	 * 批量删除人员出差 <br>
	 * 联合主键时，请自行调整实现
	*/
	@ApiOperation(value = "批量删除人员出差")
	@ApiImplicitParams({
		@ApiImplicitParam(name = BusinessTravelDataVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
	})
	@ApiOperationSupport(order=3 , author="金杰 , maillank@qq.com") 
	@SentinelResource(value = BusinessTravelDataServiceProxy.DELETE_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(BusinessTravelDataServiceProxy.DELETE_BY_IDS)
	public Result deleteByIds(List<String> ids) {

		// 参数校验
		this.validator().asserts(ids).require("缺少ids参数");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}

		// 查询引用
		Map<String, ReferCause> causeMap = businessTravelDataService.hasRefers(ids);
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
			Result result=businessTravelDataService.deleteByIdsLogical(canDeleteIds);
			return result;
		} else if (canDeleteIds.size()>0 && canDeleteIds.size() < ids.size()) {
			// 如果部分行可以删除
			Result result=businessTravelDataService.deleteByIdsLogical(canDeleteIds);
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
	 * 更新人员出差
	*/
	@ApiOperation(value = "更新人员出差")
	@ApiImplicitParams({
		@ApiImplicitParam(name = BusinessTravelDataVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = BusinessTravelDataVOMeta.EMPLOYEE_ID , value = "人员" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = BusinessTravelDataVOMeta.EMPLOYEE_NAME , value = "姓名" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = BusinessTravelDataVOMeta.EMPLOYEE_NUMBER , value = "工号" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = BusinessTravelDataVOMeta.CONTENT , value = "内容" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = BusinessTravelDataVOMeta.DURATION , value = "时长" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = BusinessTravelDataVOMeta.S_TIME , value = "开始时间" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = BusinessTravelDataVOMeta.E_TIME , value = "结束时间" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = BusinessTravelDataVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
	@ApiOperationSupport( order=4 , author="金杰 , maillank@qq.com" ,  ignoreParameters = { BusinessTravelDataVOMeta.PAGE_INDEX , BusinessTravelDataVOMeta.PAGE_SIZE , BusinessTravelDataVOMeta.SEARCH_FIELD , BusinessTravelDataVOMeta.FUZZY_FIELD , BusinessTravelDataVOMeta.SEARCH_VALUE , BusinessTravelDataVOMeta.DIRTY_FIELDS , BusinessTravelDataVOMeta.SORT_FIELD , BusinessTravelDataVOMeta.SORT_TYPE , BusinessTravelDataVOMeta.DATA_ORIGIN , BusinessTravelDataVOMeta.QUERY_LOGIC , BusinessTravelDataVOMeta.IDS } )
	@SentinelResource(value = BusinessTravelDataServiceProxy.UPDATE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(BusinessTravelDataServiceProxy.UPDATE)
	public Result update(BusinessTravelDataVO businessTravelDataVO) {
		Result result=businessTravelDataService.update(businessTravelDataVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 保存人员出差
	*/
	@ApiOperation(value = "保存人员出差")
	@ApiImplicitParams({
		@ApiImplicitParam(name = BusinessTravelDataVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = BusinessTravelDataVOMeta.EMPLOYEE_ID , value = "人员" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = BusinessTravelDataVOMeta.EMPLOYEE_NAME , value = "姓名" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = BusinessTravelDataVOMeta.EMPLOYEE_NUMBER , value = "工号" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = BusinessTravelDataVOMeta.CONTENT , value = "内容" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = BusinessTravelDataVOMeta.DURATION , value = "时长" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = BusinessTravelDataVOMeta.S_TIME , value = "开始时间" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = BusinessTravelDataVOMeta.E_TIME , value = "结束时间" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = BusinessTravelDataVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
	@ApiOperationSupport(order=5 ,  ignoreParameters = { BusinessTravelDataVOMeta.PAGE_INDEX , BusinessTravelDataVOMeta.PAGE_SIZE , BusinessTravelDataVOMeta.SEARCH_FIELD , BusinessTravelDataVOMeta.FUZZY_FIELD , BusinessTravelDataVOMeta.SEARCH_VALUE , BusinessTravelDataVOMeta.DIRTY_FIELDS , BusinessTravelDataVOMeta.SORT_FIELD , BusinessTravelDataVOMeta.SORT_TYPE , BusinessTravelDataVOMeta.DATA_ORIGIN , BusinessTravelDataVOMeta.QUERY_LOGIC , BusinessTravelDataVOMeta.IDS } )
	@SentinelResource(value = BusinessTravelDataServiceProxy.SAVE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(BusinessTravelDataServiceProxy.SAVE)
	public Result save(BusinessTravelDataVO businessTravelDataVO) {
		Result result=businessTravelDataService.save(businessTravelDataVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 获取人员出差
	*/
	@ApiOperation(value = "获取人员出差")
	@ApiImplicitParams({
		@ApiImplicitParam(name = BusinessTravelDataVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "1"),
	})
	@ApiOperationSupport(order=6 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = BusinessTravelDataServiceProxy.GET_BY_ID , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(BusinessTravelDataServiceProxy.GET_BY_ID)
	public Result<BusinessTravelData> getById(String id) {
		Result<BusinessTravelData> result=new Result<>();
		BusinessTravelData businessTravelData=businessTravelDataService.getById(id);
		result.success(true).data(businessTravelData);
		return result;
	}


	/**
	 * 批量获取人员出差 <br>
	 * 联合主键时，请自行调整实现
	*/
		@ApiOperation(value = "批量获取人员出差")
		@ApiImplicitParams({
				@ApiImplicitParam(name = BusinessTravelDataVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
		})
		@ApiOperationSupport(order=3 , author="金杰 , maillank@qq.com") 
		@SentinelResource(value = BusinessTravelDataServiceProxy.GET_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(BusinessTravelDataServiceProxy.GET_BY_IDS)
	public Result<List<BusinessTravelData>> getByIds(List<String> ids) {
		Result<List<BusinessTravelData>> result=new Result<>();
		List<BusinessTravelData> list=businessTravelDataService.queryListByIds(ids);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 查询人员出差
	*/
	@ApiOperation(value = "查询人员出差")
	@ApiImplicitParams({
		@ApiImplicitParam(name = BusinessTravelDataVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = BusinessTravelDataVOMeta.EMPLOYEE_ID , value = "人员" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = BusinessTravelDataVOMeta.EMPLOYEE_NAME , value = "姓名" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = BusinessTravelDataVOMeta.EMPLOYEE_NUMBER , value = "工号" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = BusinessTravelDataVOMeta.CONTENT , value = "内容" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = BusinessTravelDataVOMeta.DURATION , value = "时长" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = BusinessTravelDataVOMeta.S_TIME , value = "开始时间" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = BusinessTravelDataVOMeta.E_TIME , value = "结束时间" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = BusinessTravelDataVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=5 , author="金杰 , maillank@qq.com" ,  ignoreParameters = { BusinessTravelDataVOMeta.PAGE_INDEX , BusinessTravelDataVOMeta.PAGE_SIZE } )
	@SentinelResource(value = BusinessTravelDataServiceProxy.QUERY_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(BusinessTravelDataServiceProxy.QUERY_LIST)
	public Result<List<BusinessTravelData>> queryList(BusinessTravelDataVO sample) {
		Result<List<BusinessTravelData>> result=new Result<>();
		List<BusinessTravelData> list=businessTravelDataService.queryList(sample);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 分页查询人员出差
	*/
	@ApiOperation(value = "分页查询人员出差")
	@ApiImplicitParams({
		@ApiImplicitParam(name = BusinessTravelDataVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = BusinessTravelDataVOMeta.EMPLOYEE_ID , value = "人员" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = BusinessTravelDataVOMeta.EMPLOYEE_NAME , value = "姓名" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = BusinessTravelDataVOMeta.EMPLOYEE_NUMBER , value = "工号" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = BusinessTravelDataVOMeta.CONTENT , value = "内容" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = BusinessTravelDataVOMeta.DURATION , value = "时长" , required = false , dataTypeClass=BigDecimal.class),
		@ApiImplicitParam(name = BusinessTravelDataVOMeta.S_TIME , value = "开始时间" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = BusinessTravelDataVOMeta.E_TIME , value = "结束时间" , required = false , dataTypeClass=Date.class),
		@ApiImplicitParam(name = BusinessTravelDataVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=8 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = BusinessTravelDataServiceProxy.QUERY_PAGED_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(BusinessTravelDataServiceProxy.QUERY_PAGED_LIST)
	public Result<PagedList<BusinessTravelData>> queryPagedList(BusinessTravelDataVO sample) {
		Result<PagedList<BusinessTravelData>> result=new Result<>();
		PagedList<BusinessTravelData> list=businessTravelDataService.queryPagedList(sample,sample.getPageSize(),sample.getPageIndex());
		result.success(true).data(list);
		return result;
	}






}