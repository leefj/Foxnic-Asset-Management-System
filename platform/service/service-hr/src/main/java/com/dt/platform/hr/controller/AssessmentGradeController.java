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


import com.dt.platform.proxy.hr.AssessmentGradeServiceProxy;
import com.dt.platform.domain.hr.meta.AssessmentGradeVOMeta;
import com.dt.platform.domain.hr.AssessmentGrade;
import com.dt.platform.domain.hr.AssessmentGradeVO;
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
import com.dt.platform.domain.hr.meta.AssessmentGradeMeta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.hr.service.IAssessmentGradeService;
import com.github.foxnic.api.validate.annotations.NotNull;

/**
 * <p>
 * 考核评级接口控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-01-02 14:11:51
*/

@InDoc
@Api(tags = "考核评级")
@RestController("HrAssessmentGradeController")
public class AssessmentGradeController extends SuperController {

	@Autowired
	private IAssessmentGradeService assessmentGradeService;


	/**
	 * 添加考核评级
	*/
	@ApiOperation(value = "添加考核评级")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssessmentGradeVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "661524775796998144"),
		@ApiImplicitParam(name = AssessmentGradeVOMeta.CODE , value = "编码" , required = false , dataTypeClass=String.class , example = "D"),
		@ApiImplicitParam(name = AssessmentGradeVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class , example = "D"),
		@ApiImplicitParam(name = AssessmentGradeVOMeta.SORT , value = "排序" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = AssessmentGradeVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true , ignorePrimaryKey = true)
	@ApiOperationSupport(order=1 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = AssessmentGradeServiceProxy.INSERT , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssessmentGradeServiceProxy.INSERT)
	public Result insert(AssessmentGradeVO assessmentGradeVO) {
		Result result=assessmentGradeService.insert(assessmentGradeVO,false);
		return result;
	}



	/**
	 * 删除考核评级
	*/
	@ApiOperation(value = "删除考核评级")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssessmentGradeVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "661524775796998144")
	})
	@ApiOperationSupport(order=2 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = AssessmentGradeServiceProxy.DELETE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssessmentGradeServiceProxy.DELETE)
	public Result deleteById(String id) {
		this.validator().asserts(id).require("缺少id值");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}
		// 引用校验
		ReferCause cause =  assessmentGradeService.hasRefers(id);
		// 判断是否可以删除
		this.validator().asserts(cause.hasRefer()).requireEqual("不允许删除当前记录："+cause.message(),false);
		if(this.validator().failure()) {
			return this.validator().getFirstResult().messageLevel4Confirm();
		}
		Result result=assessmentGradeService.deleteByIdLogical(id);
		return result;
	}


	/**
	 * 批量删除考核评级 <br>
	 * 联合主键时，请自行调整实现
	*/
	@ApiOperation(value = "批量删除考核评级")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssessmentGradeVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
	})
	@ApiOperationSupport(order=3 , author="金杰 , maillank@qq.com") 
	@SentinelResource(value = AssessmentGradeServiceProxy.DELETE_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssessmentGradeServiceProxy.DELETE_BY_IDS)
	public Result deleteByIds(List<String> ids) {

		// 参数校验
		this.validator().asserts(ids).require("缺少ids参数");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}

		// 查询引用
		Map<String, ReferCause> causeMap = assessmentGradeService.hasRefers(ids);
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
			Result result=assessmentGradeService.deleteByIdsLogical(canDeleteIds);
			return result;
		} else if (canDeleteIds.size()>0 && canDeleteIds.size() < ids.size()) {
			// 如果部分行可以删除
			Result result=assessmentGradeService.deleteByIdsLogical(canDeleteIds);
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
	 * 更新考核评级
	*/
	@ApiOperation(value = "更新考核评级")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssessmentGradeVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "661524775796998144"),
		@ApiImplicitParam(name = AssessmentGradeVOMeta.CODE , value = "编码" , required = false , dataTypeClass=String.class , example = "D"),
		@ApiImplicitParam(name = AssessmentGradeVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class , example = "D"),
		@ApiImplicitParam(name = AssessmentGradeVOMeta.SORT , value = "排序" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = AssessmentGradeVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
	@ApiOperationSupport( order=4 , author="金杰 , maillank@qq.com" ,  ignoreParameters = { AssessmentGradeVOMeta.PAGE_INDEX , AssessmentGradeVOMeta.PAGE_SIZE , AssessmentGradeVOMeta.SEARCH_FIELD , AssessmentGradeVOMeta.FUZZY_FIELD , AssessmentGradeVOMeta.SEARCH_VALUE , AssessmentGradeVOMeta.DIRTY_FIELDS , AssessmentGradeVOMeta.SORT_FIELD , AssessmentGradeVOMeta.SORT_TYPE , AssessmentGradeVOMeta.DATA_ORIGIN , AssessmentGradeVOMeta.QUERY_LOGIC , AssessmentGradeVOMeta.IDS } )
	@SentinelResource(value = AssessmentGradeServiceProxy.UPDATE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssessmentGradeServiceProxy.UPDATE)
	public Result update(AssessmentGradeVO assessmentGradeVO) {
		Result result=assessmentGradeService.update(assessmentGradeVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 保存考核评级
	*/
	@ApiOperation(value = "保存考核评级")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssessmentGradeVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "661524775796998144"),
		@ApiImplicitParam(name = AssessmentGradeVOMeta.CODE , value = "编码" , required = false , dataTypeClass=String.class , example = "D"),
		@ApiImplicitParam(name = AssessmentGradeVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class , example = "D"),
		@ApiImplicitParam(name = AssessmentGradeVOMeta.SORT , value = "排序" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = AssessmentGradeVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
	@ApiOperationSupport(order=5 ,  ignoreParameters = { AssessmentGradeVOMeta.PAGE_INDEX , AssessmentGradeVOMeta.PAGE_SIZE , AssessmentGradeVOMeta.SEARCH_FIELD , AssessmentGradeVOMeta.FUZZY_FIELD , AssessmentGradeVOMeta.SEARCH_VALUE , AssessmentGradeVOMeta.DIRTY_FIELDS , AssessmentGradeVOMeta.SORT_FIELD , AssessmentGradeVOMeta.SORT_TYPE , AssessmentGradeVOMeta.DATA_ORIGIN , AssessmentGradeVOMeta.QUERY_LOGIC , AssessmentGradeVOMeta.IDS } )
	@SentinelResource(value = AssessmentGradeServiceProxy.SAVE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssessmentGradeServiceProxy.SAVE)
	public Result save(AssessmentGradeVO assessmentGradeVO) {
		Result result=assessmentGradeService.save(assessmentGradeVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 获取考核评级
	*/
	@ApiOperation(value = "获取考核评级")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssessmentGradeVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "1"),
	})
	@ApiOperationSupport(order=6 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = AssessmentGradeServiceProxy.GET_BY_ID , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssessmentGradeServiceProxy.GET_BY_ID)
	public Result<AssessmentGrade> getById(String id) {
		Result<AssessmentGrade> result=new Result<>();
		AssessmentGrade assessmentGrade=assessmentGradeService.getById(id);
		result.success(true).data(assessmentGrade);
		return result;
	}


	/**
	 * 批量获取考核评级 <br>
	 * 联合主键时，请自行调整实现
	*/
		@ApiOperation(value = "批量获取考核评级")
		@ApiImplicitParams({
				@ApiImplicitParam(name = AssessmentGradeVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
		})
		@ApiOperationSupport(order=3 , author="金杰 , maillank@qq.com") 
		@SentinelResource(value = AssessmentGradeServiceProxy.GET_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssessmentGradeServiceProxy.GET_BY_IDS)
	public Result<List<AssessmentGrade>> getByIds(List<String> ids) {
		Result<List<AssessmentGrade>> result=new Result<>();
		List<AssessmentGrade> list=assessmentGradeService.queryListByIds(ids);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 查询考核评级
	*/
	@ApiOperation(value = "查询考核评级")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssessmentGradeVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "661524775796998144"),
		@ApiImplicitParam(name = AssessmentGradeVOMeta.CODE , value = "编码" , required = false , dataTypeClass=String.class , example = "D"),
		@ApiImplicitParam(name = AssessmentGradeVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class , example = "D"),
		@ApiImplicitParam(name = AssessmentGradeVOMeta.SORT , value = "排序" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = AssessmentGradeVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=5 , author="金杰 , maillank@qq.com" ,  ignoreParameters = { AssessmentGradeVOMeta.PAGE_INDEX , AssessmentGradeVOMeta.PAGE_SIZE } )
	@SentinelResource(value = AssessmentGradeServiceProxy.QUERY_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssessmentGradeServiceProxy.QUERY_LIST)
	public Result<List<AssessmentGrade>> queryList(AssessmentGradeVO sample) {
		Result<List<AssessmentGrade>> result=new Result<>();
		List<AssessmentGrade> list=assessmentGradeService.queryList(sample);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 分页查询考核评级
	*/
	@ApiOperation(value = "分页查询考核评级")
	@ApiImplicitParams({
		@ApiImplicitParam(name = AssessmentGradeVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "661524775796998144"),
		@ApiImplicitParam(name = AssessmentGradeVOMeta.CODE , value = "编码" , required = false , dataTypeClass=String.class , example = "D"),
		@ApiImplicitParam(name = AssessmentGradeVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class , example = "D"),
		@ApiImplicitParam(name = AssessmentGradeVOMeta.SORT , value = "排序" , required = false , dataTypeClass=Integer.class),
		@ApiImplicitParam(name = AssessmentGradeVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=8 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = AssessmentGradeServiceProxy.QUERY_PAGED_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(AssessmentGradeServiceProxy.QUERY_PAGED_LIST)
	public Result<PagedList<AssessmentGrade>> queryPagedList(AssessmentGradeVO sample) {
		Result<PagedList<AssessmentGrade>> result=new Result<>();
		PagedList<AssessmentGrade> list=assessmentGradeService.queryPagedList(sample,sample.getPageSize(),sample.getPageIndex());
		result.success(true).data(list);
		return result;
	}






}