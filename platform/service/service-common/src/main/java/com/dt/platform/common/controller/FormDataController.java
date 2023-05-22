package com.dt.platform.common.controller;

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


import com.dt.platform.proxy.common.FormDataServiceProxy;
import com.dt.platform.domain.common.meta.FormDataVOMeta;
import com.dt.platform.domain.common.FormData;
import com.dt.platform.domain.common.FormDataVO;
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
import com.dt.platform.domain.common.meta.FormDataMeta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.common.service.IFormDataService;
import com.github.foxnic.api.validate.annotations.NotNull;

/**
 * <p>
 * 表单数据接口控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-05-21 12:52:32
*/

@InDoc
@Api(tags = "表单数据")
@RestController("SysFormDataController")
public class FormDataController extends SuperController {

	@Autowired
	private IFormDataService formDataService;

	/**
	 * 添加表单数据
	*/
	@ApiOperation(value = "添加表单数据")
	@ApiImplicitParams({
		@ApiImplicitParam(name = FormDataVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = FormDataVOMeta.DEF_ID , value = "表单定义" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = FormDataVOMeta.FORM_ID , value = "表单" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = FormDataVOMeta.DESIGNER_DATA , value = "设计" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = FormDataVOMeta.DATA , value = "数据" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true , ignorePrimaryKey = true)
	@ApiOperationSupport(order=1 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = FormDataServiceProxy.INSERT , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(FormDataServiceProxy.INSERT)
	public Result insert(FormDataVO formDataVO) {
		
		Result result=formDataService.insert(formDataVO,false);
		return result;
	}



	/**
	 * 删除表单数据
	*/
	@ApiOperation(value = "删除表单数据")
	@ApiImplicitParams({
		@ApiImplicitParam(name = FormDataVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class)
	})
	@ApiOperationSupport(order=2 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = FormDataServiceProxy.DELETE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(FormDataServiceProxy.DELETE)
	public Result deleteById(String id) {
		
		this.validator().asserts(id).require("缺少id值");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}
		// 引用校验
		ReferCause cause =  formDataService.hasRefers(id);
		// 判断是否可以删除
		this.validator().asserts(cause.hasRefer()).requireEqual("不允许删除当前记录："+cause.message(),false);
		if(this.validator().failure()) {
			return this.validator().getFirstResult().messageLevel4Confirm();
		}
		Result result=formDataService.deleteByIdLogical(id);
		return result;
	}


	/**
	 * 批量删除表单数据 <br>
	 * 联合主键时，请自行调整实现
	*/
	@ApiOperation(value = "批量删除表单数据")
	@ApiImplicitParams({
		@ApiImplicitParam(name = FormDataVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
	})
	@ApiOperationSupport(order=3 , author="金杰 , maillank@qq.com") 
	@SentinelResource(value = FormDataServiceProxy.DELETE_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(FormDataServiceProxy.DELETE_BY_IDS)
	public Result deleteByIds(List<String> ids) {
		
		// 参数校验
		this.validator().asserts(ids).require("缺少ids参数");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}

		// 查询引用
		Map<String, ReferCause> causeMap = formDataService.hasRefers(ids);
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
			Result result=formDataService.deleteByIdsLogical(canDeleteIds);
			return result;
		} else if (canDeleteIds.size()>0 && canDeleteIds.size() < ids.size()) {
			// 如果部分行可以删除
			Result result=formDataService.deleteByIdsLogical(canDeleteIds);
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
	 * 更新表单数据
	*/
	@ApiOperation(value = "更新表单数据")
	@ApiImplicitParams({
		@ApiImplicitParam(name = FormDataVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = FormDataVOMeta.DEF_ID , value = "表单定义" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = FormDataVOMeta.FORM_ID , value = "表单" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = FormDataVOMeta.DESIGNER_DATA , value = "设计" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = FormDataVOMeta.DATA , value = "数据" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
	@ApiOperationSupport( order=4 , author="金杰 , maillank@qq.com" ,  ignoreParameters = { FormDataVOMeta.PAGE_INDEX , FormDataVOMeta.PAGE_SIZE , FormDataVOMeta.SEARCH_FIELD , FormDataVOMeta.FUZZY_FIELD , FormDataVOMeta.SEARCH_VALUE , FormDataVOMeta.DIRTY_FIELDS , FormDataVOMeta.SORT_FIELD , FormDataVOMeta.SORT_TYPE , FormDataVOMeta.DATA_ORIGIN , FormDataVOMeta.QUERY_LOGIC , FormDataVOMeta.REQUEST_ACTION , FormDataVOMeta.IDS } )
	@SentinelResource(value = FormDataServiceProxy.UPDATE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(FormDataServiceProxy.UPDATE)
	public Result update(FormDataVO formDataVO) {
		
		Result result=formDataService.update(formDataVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 保存表单数据
	*/
	@ApiOperation(value = "保存表单数据")
	@ApiImplicitParams({
		@ApiImplicitParam(name = FormDataVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = FormDataVOMeta.DEF_ID , value = "表单定义" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = FormDataVOMeta.FORM_ID , value = "表单" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = FormDataVOMeta.DESIGNER_DATA , value = "设计" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = FormDataVOMeta.DATA , value = "数据" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
	@ApiOperationSupport(order=5 ,  ignoreParameters = { FormDataVOMeta.PAGE_INDEX , FormDataVOMeta.PAGE_SIZE , FormDataVOMeta.SEARCH_FIELD , FormDataVOMeta.FUZZY_FIELD , FormDataVOMeta.SEARCH_VALUE , FormDataVOMeta.DIRTY_FIELDS , FormDataVOMeta.SORT_FIELD , FormDataVOMeta.SORT_TYPE , FormDataVOMeta.DATA_ORIGIN , FormDataVOMeta.QUERY_LOGIC , FormDataVOMeta.REQUEST_ACTION , FormDataVOMeta.IDS } )
	@SentinelResource(value = FormDataServiceProxy.SAVE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(FormDataServiceProxy.SAVE)
	public Result save(FormDataVO formDataVO) {
		
		Result result=formDataService.save(formDataVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 获取表单数据
	*/
	@ApiOperation(value = "获取表单数据")
	@ApiImplicitParams({
		@ApiImplicitParam(name = FormDataVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "1"),
	})
	@ApiOperationSupport(order=6 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = FormDataServiceProxy.GET_BY_ID , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(FormDataServiceProxy.GET_BY_ID)
	public Result<FormData> getById(String id) {
		
		Result<FormData> result=new Result<>();
		FormData formData=formDataService.getById(id);
		result.success(true).data(formData);
		return result;
	}


	/**
	 * 批量获取表单数据 <br>
	 * 联合主键时，请自行调整实现
	*/
		@ApiOperation(value = "批量获取表单数据")
		@ApiImplicitParams({
				@ApiImplicitParam(name = FormDataVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
		})
		@ApiOperationSupport(order=3 , author="金杰 , maillank@qq.com") 
		@SentinelResource(value = FormDataServiceProxy.GET_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(FormDataServiceProxy.GET_BY_IDS)
	public Result<List<FormData>> getByIds(List<String> ids) {
		
		Result<List<FormData>> result=new Result<>();
		List<FormData> list=formDataService.queryListByIds(ids);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 查询表单数据
	*/
	@ApiOperation(value = "查询表单数据")
	@ApiImplicitParams({
		@ApiImplicitParam(name = FormDataVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = FormDataVOMeta.DEF_ID , value = "表单定义" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = FormDataVOMeta.FORM_ID , value = "表单" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = FormDataVOMeta.DESIGNER_DATA , value = "设计" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = FormDataVOMeta.DATA , value = "数据" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=5 , author="金杰 , maillank@qq.com" ,  ignoreParameters = { FormDataVOMeta.PAGE_INDEX , FormDataVOMeta.PAGE_SIZE } )
	@SentinelResource(value = FormDataServiceProxy.QUERY_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(FormDataServiceProxy.QUERY_LIST)
	public Result<List<FormData>> queryList(FormDataVO sample) {
		
		Result<List<FormData>> result=new Result<>();
		List<FormData> list=formDataService.queryList(sample);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 分页查询表单数据
	*/
	@ApiOperation(value = "分页查询表单数据")
	@ApiImplicitParams({
		@ApiImplicitParam(name = FormDataVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = FormDataVOMeta.DEF_ID , value = "表单定义" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = FormDataVOMeta.FORM_ID , value = "表单" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = FormDataVOMeta.DESIGNER_DATA , value = "设计" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = FormDataVOMeta.DATA , value = "数据" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=8 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = FormDataServiceProxy.QUERY_PAGED_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(FormDataServiceProxy.QUERY_PAGED_LIST)
	public Result<PagedList<FormData>> queryPagedList(FormDataVO sample) {
		
		Result<PagedList<FormData>> result=new Result<>();
		PagedList<FormData> list=formDataService.queryPagedList(sample,sample.getPageSize(),sample.getPageIndex());
		result.success(true).data(list);
		return result;
	}





}