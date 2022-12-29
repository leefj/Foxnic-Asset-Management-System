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


import com.dt.platform.proxy.hr.PersonContractServiceProxy;
import com.dt.platform.domain.hr.meta.PersonContractVOMeta;
import com.dt.platform.domain.hr.PersonContract;
import com.dt.platform.domain.hr.PersonContractVO;
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
import com.dt.platform.domain.hr.meta.PersonContractMeta;
import org.github.foxnic.web.domain.system.DictItem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.hr.service.IPersonContractService;
import com.github.foxnic.api.validate.annotations.NotNull;

/**
 * <p>
 * 人员合同接口控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2022-12-29 15:52:03
*/

@InDoc
@Api(tags = "人员合同")
@RestController("HrPersonContractController")
public class PersonContractController extends SuperController {

	@Autowired
	private IPersonContractService personContractService;


	/**
	 * 添加人员合同
	*/
	@ApiOperation(value = "添加人员合同")
	@ApiImplicitParams({
		@ApiImplicitParam(name = PersonContractVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = PersonContractVOMeta.PERSON_ID , value = "人员" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = PersonContractVOMeta.STATUS , value = "状态" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = PersonContractVOMeta.CONTRACT_DURATION , value = "合同周期" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = PersonContractVOMeta.CONTRACT_START_DATE , value = "合同开始时间" , required = true , dataTypeClass=Date.class),
		@ApiImplicitParam(name = PersonContractVOMeta.CONTRACT_FINISH_DATE , value = "合同结束时间" , required = true , dataTypeClass=Date.class),
		@ApiImplicitParam(name = PersonContractVOMeta.FILE_ID , value = "合同" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = PersonContractVOMeta.NOTES , value = "备注" , required = true , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true , ignorePrimaryKey = true)
	@ApiOperationSupport(order=1 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = PersonContractServiceProxy.INSERT , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(PersonContractServiceProxy.INSERT)
	public Result insert(PersonContractVO personContractVO) {
		Result result=personContractService.insert(personContractVO,false);
		return result;
	}



	/**
	 * 删除人员合同
	*/
	@ApiOperation(value = "删除人员合同")
	@ApiImplicitParams({
		@ApiImplicitParam(name = PersonContractVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class)
	})
	@ApiOperationSupport(order=2 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = PersonContractServiceProxy.DELETE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(PersonContractServiceProxy.DELETE)
	public Result deleteById(String id) {
		this.validator().asserts(id).require("缺少id值");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}
		// 引用校验
		ReferCause cause =  personContractService.hasRefers(id);
		// 判断是否可以删除
		this.validator().asserts(cause.hasRefer()).requireEqual("不允许删除当前记录："+cause.message(),false);
		if(this.validator().failure()) {
			return this.validator().getFirstResult().messageLevel4Confirm();
		}
		Result result=personContractService.deleteByIdLogical(id);
		return result;
	}


	/**
	 * 批量删除人员合同 <br>
	 * 联合主键时，请自行调整实现
	*/
	@ApiOperation(value = "批量删除人员合同")
	@ApiImplicitParams({
		@ApiImplicitParam(name = PersonContractVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
	})
	@ApiOperationSupport(order=3 , author="金杰 , maillank@qq.com") 
	@SentinelResource(value = PersonContractServiceProxy.DELETE_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(PersonContractServiceProxy.DELETE_BY_IDS)
	public Result deleteByIds(List<String> ids) {

		// 参数校验
		this.validator().asserts(ids).require("缺少ids参数");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}

		// 查询引用
		Map<String, ReferCause> causeMap = personContractService.hasRefers(ids);
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
			Result result=personContractService.deleteByIdsLogical(canDeleteIds);
			return result;
		} else if (canDeleteIds.size()>0 && canDeleteIds.size() < ids.size()) {
			// 如果部分行可以删除
			Result result=personContractService.deleteByIdsLogical(canDeleteIds);
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
	 * 更新人员合同
	*/
	@ApiOperation(value = "更新人员合同")
	@ApiImplicitParams({
		@ApiImplicitParam(name = PersonContractVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = PersonContractVOMeta.PERSON_ID , value = "人员" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = PersonContractVOMeta.STATUS , value = "状态" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = PersonContractVOMeta.CONTRACT_DURATION , value = "合同周期" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = PersonContractVOMeta.CONTRACT_START_DATE , value = "合同开始时间" , required = true , dataTypeClass=Date.class),
		@ApiImplicitParam(name = PersonContractVOMeta.CONTRACT_FINISH_DATE , value = "合同结束时间" , required = true , dataTypeClass=Date.class),
		@ApiImplicitParam(name = PersonContractVOMeta.FILE_ID , value = "合同" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = PersonContractVOMeta.NOTES , value = "备注" , required = true , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
	@ApiOperationSupport( order=4 , author="金杰 , maillank@qq.com" ,  ignoreParameters = { PersonContractVOMeta.PAGE_INDEX , PersonContractVOMeta.PAGE_SIZE , PersonContractVOMeta.SEARCH_FIELD , PersonContractVOMeta.FUZZY_FIELD , PersonContractVOMeta.SEARCH_VALUE , PersonContractVOMeta.DIRTY_FIELDS , PersonContractVOMeta.SORT_FIELD , PersonContractVOMeta.SORT_TYPE , PersonContractVOMeta.DATA_ORIGIN , PersonContractVOMeta.QUERY_LOGIC , PersonContractVOMeta.IDS } )
	@SentinelResource(value = PersonContractServiceProxy.UPDATE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(PersonContractServiceProxy.UPDATE)
	public Result update(PersonContractVO personContractVO) {
		Result result=personContractService.update(personContractVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 保存人员合同
	*/
	@ApiOperation(value = "保存人员合同")
	@ApiImplicitParams({
		@ApiImplicitParam(name = PersonContractVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = PersonContractVOMeta.PERSON_ID , value = "人员" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = PersonContractVOMeta.STATUS , value = "状态" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = PersonContractVOMeta.CONTRACT_DURATION , value = "合同周期" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = PersonContractVOMeta.CONTRACT_START_DATE , value = "合同开始时间" , required = true , dataTypeClass=Date.class),
		@ApiImplicitParam(name = PersonContractVOMeta.CONTRACT_FINISH_DATE , value = "合同结束时间" , required = true , dataTypeClass=Date.class),
		@ApiImplicitParam(name = PersonContractVOMeta.FILE_ID , value = "合同" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = PersonContractVOMeta.NOTES , value = "备注" , required = true , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
	@ApiOperationSupport(order=5 ,  ignoreParameters = { PersonContractVOMeta.PAGE_INDEX , PersonContractVOMeta.PAGE_SIZE , PersonContractVOMeta.SEARCH_FIELD , PersonContractVOMeta.FUZZY_FIELD , PersonContractVOMeta.SEARCH_VALUE , PersonContractVOMeta.DIRTY_FIELDS , PersonContractVOMeta.SORT_FIELD , PersonContractVOMeta.SORT_TYPE , PersonContractVOMeta.DATA_ORIGIN , PersonContractVOMeta.QUERY_LOGIC , PersonContractVOMeta.IDS } )
	@SentinelResource(value = PersonContractServiceProxy.SAVE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(PersonContractServiceProxy.SAVE)
	public Result save(PersonContractVO personContractVO) {
		Result result=personContractService.save(personContractVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 获取人员合同
	*/
	@ApiOperation(value = "获取人员合同")
	@ApiImplicitParams({
		@ApiImplicitParam(name = PersonContractVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "1"),
	})
	@ApiOperationSupport(order=6 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = PersonContractServiceProxy.GET_BY_ID , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(PersonContractServiceProxy.GET_BY_ID)
	public Result<PersonContract> getById(String id) {
		Result<PersonContract> result=new Result<>();
		PersonContract personContract=personContractService.getById(id);
		// join 关联的对象
		personContractService.dao().fill(personContract)
			.with(PersonContractMeta.CONTRACT_DURATION_DICT)
			.execute();
		result.success(true).data(personContract);
		return result;
	}


	/**
	 * 批量获取人员合同 <br>
	 * 联合主键时，请自行调整实现
	*/
		@ApiOperation(value = "批量获取人员合同")
		@ApiImplicitParams({
				@ApiImplicitParam(name = PersonContractVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
		})
		@ApiOperationSupport(order=3 , author="金杰 , maillank@qq.com") 
		@SentinelResource(value = PersonContractServiceProxy.GET_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(PersonContractServiceProxy.GET_BY_IDS)
	public Result<List<PersonContract>> getByIds(List<String> ids) {
		Result<List<PersonContract>> result=new Result<>();
		List<PersonContract> list=personContractService.queryListByIds(ids);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 查询人员合同
	*/
	@ApiOperation(value = "查询人员合同")
	@ApiImplicitParams({
		@ApiImplicitParam(name = PersonContractVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = PersonContractVOMeta.PERSON_ID , value = "人员" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = PersonContractVOMeta.STATUS , value = "状态" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = PersonContractVOMeta.CONTRACT_DURATION , value = "合同周期" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = PersonContractVOMeta.CONTRACT_START_DATE , value = "合同开始时间" , required = true , dataTypeClass=Date.class),
		@ApiImplicitParam(name = PersonContractVOMeta.CONTRACT_FINISH_DATE , value = "合同结束时间" , required = true , dataTypeClass=Date.class),
		@ApiImplicitParam(name = PersonContractVOMeta.FILE_ID , value = "合同" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = PersonContractVOMeta.NOTES , value = "备注" , required = true , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=5 , author="金杰 , maillank@qq.com" ,  ignoreParameters = { PersonContractVOMeta.PAGE_INDEX , PersonContractVOMeta.PAGE_SIZE } )
	@SentinelResource(value = PersonContractServiceProxy.QUERY_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(PersonContractServiceProxy.QUERY_LIST)
	public Result<List<PersonContract>> queryList(PersonContractVO sample) {
		Result<List<PersonContract>> result=new Result<>();
		List<PersonContract> list=personContractService.queryList(sample);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 分页查询人员合同
	*/
	@ApiOperation(value = "分页查询人员合同")
	@ApiImplicitParams({
		@ApiImplicitParam(name = PersonContractVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = PersonContractVOMeta.PERSON_ID , value = "人员" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = PersonContractVOMeta.STATUS , value = "状态" , required = false , dataTypeClass=String.class),
		@ApiImplicitParam(name = PersonContractVOMeta.CONTRACT_DURATION , value = "合同周期" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = PersonContractVOMeta.CONTRACT_START_DATE , value = "合同开始时间" , required = true , dataTypeClass=Date.class),
		@ApiImplicitParam(name = PersonContractVOMeta.CONTRACT_FINISH_DATE , value = "合同结束时间" , required = true , dataTypeClass=Date.class),
		@ApiImplicitParam(name = PersonContractVOMeta.FILE_ID , value = "合同" , required = true , dataTypeClass=String.class),
		@ApiImplicitParam(name = PersonContractVOMeta.NOTES , value = "备注" , required = true , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=8 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = PersonContractServiceProxy.QUERY_PAGED_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(PersonContractServiceProxy.QUERY_PAGED_LIST)
	public Result<PagedList<PersonContract>> queryPagedList(PersonContractVO sample) {
		Result<PagedList<PersonContract>> result=new Result<>();
		PagedList<PersonContract> list=personContractService.queryPagedList(sample,sample.getPageSize(),sample.getPageIndex());
		// join 关联的对象
		personContractService.dao().fill(list)
			.with(PersonContractMeta.CONTRACT_DURATION_DICT)
			.execute();
		result.success(true).data(list);
		return result;
	}






}