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


import com.dt.platform.proxy.hr.RankServiceProxy;
import com.dt.platform.domain.hr.meta.RankVOMeta;
import com.dt.platform.domain.hr.Rank;
import com.dt.platform.domain.hr.RankVO;
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
import com.dt.platform.domain.hr.meta.RankMeta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.hr.service.IRankService;
import com.github.foxnic.api.validate.annotations.NotNull;

/**
 * <p>
 * 职级序列接口控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-01-02 14:12:45
*/

@InDoc
@Api(tags = "职级序列")
@RestController("HrRankController")
public class RankController extends SuperController {

	@Autowired
	private IRankService rankService;


	/**
	 * 添加职级序列
	*/
	@ApiOperation(value = "添加职级序列")
	@ApiImplicitParams({
		@ApiImplicitParam(name = RankVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "660564391397163008"),
		@ApiImplicitParam(name = RankVOMeta.CODE , value = "职级序列" , required = false , dataTypeClass=String.class , example = "1"),
		@ApiImplicitParam(name = RankVOMeta.SORT , value = "排序" , required = false , dataTypeClass=Integer.class , example = "1"),
		@ApiImplicitParam(name = RankVOMeta.NOTE , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true , ignorePrimaryKey = true)
	@ApiOperationSupport(order=1 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = RankServiceProxy.INSERT , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(RankServiceProxy.INSERT)
	public Result insert(RankVO rankVO) {
		Result result=rankService.insert(rankVO,false);
		return result;
	}



	/**
	 * 删除职级序列
	*/
	@ApiOperation(value = "删除职级序列")
	@ApiImplicitParams({
		@ApiImplicitParam(name = RankVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "660564391397163008")
	})
	@ApiOperationSupport(order=2 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = RankServiceProxy.DELETE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(RankServiceProxy.DELETE)
	public Result deleteById(String id) {
		this.validator().asserts(id).require("缺少id值");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}
		// 引用校验
		ReferCause cause =  rankService.hasRefers(id);
		// 判断是否可以删除
		this.validator().asserts(cause.hasRefer()).requireEqual("不允许删除当前记录："+cause.message(),false);
		if(this.validator().failure()) {
			return this.validator().getFirstResult().messageLevel4Confirm();
		}
		Result result=rankService.deleteByIdLogical(id);
		return result;
	}


	/**
	 * 批量删除职级序列 <br>
	 * 联合主键时，请自行调整实现
	*/
	@ApiOperation(value = "批量删除职级序列")
	@ApiImplicitParams({
		@ApiImplicitParam(name = RankVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
	})
	@ApiOperationSupport(order=3 , author="金杰 , maillank@qq.com") 
	@SentinelResource(value = RankServiceProxy.DELETE_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(RankServiceProxy.DELETE_BY_IDS)
	public Result deleteByIds(List<String> ids) {

		// 参数校验
		this.validator().asserts(ids).require("缺少ids参数");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}

		// 查询引用
		Map<String, ReferCause> causeMap = rankService.hasRefers(ids);
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
			Result result=rankService.deleteByIdsLogical(canDeleteIds);
			return result;
		} else if (canDeleteIds.size()>0 && canDeleteIds.size() < ids.size()) {
			// 如果部分行可以删除
			Result result=rankService.deleteByIdsLogical(canDeleteIds);
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
	 * 更新职级序列
	*/
	@ApiOperation(value = "更新职级序列")
	@ApiImplicitParams({
		@ApiImplicitParam(name = RankVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "660564391397163008"),
		@ApiImplicitParam(name = RankVOMeta.CODE , value = "职级序列" , required = false , dataTypeClass=String.class , example = "1"),
		@ApiImplicitParam(name = RankVOMeta.SORT , value = "排序" , required = false , dataTypeClass=Integer.class , example = "1"),
		@ApiImplicitParam(name = RankVOMeta.NOTE , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
	@ApiOperationSupport( order=4 , author="金杰 , maillank@qq.com" ,  ignoreParameters = { RankVOMeta.PAGE_INDEX , RankVOMeta.PAGE_SIZE , RankVOMeta.SEARCH_FIELD , RankVOMeta.FUZZY_FIELD , RankVOMeta.SEARCH_VALUE , RankVOMeta.DIRTY_FIELDS , RankVOMeta.SORT_FIELD , RankVOMeta.SORT_TYPE , RankVOMeta.DATA_ORIGIN , RankVOMeta.QUERY_LOGIC , RankVOMeta.IDS } )
	@SentinelResource(value = RankServiceProxy.UPDATE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(RankServiceProxy.UPDATE)
	public Result update(RankVO rankVO) {
		Result result=rankService.update(rankVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 保存职级序列
	*/
	@ApiOperation(value = "保存职级序列")
	@ApiImplicitParams({
		@ApiImplicitParam(name = RankVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "660564391397163008"),
		@ApiImplicitParam(name = RankVOMeta.CODE , value = "职级序列" , required = false , dataTypeClass=String.class , example = "1"),
		@ApiImplicitParam(name = RankVOMeta.SORT , value = "排序" , required = false , dataTypeClass=Integer.class , example = "1"),
		@ApiImplicitParam(name = RankVOMeta.NOTE , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
	@ApiOperationSupport(order=5 ,  ignoreParameters = { RankVOMeta.PAGE_INDEX , RankVOMeta.PAGE_SIZE , RankVOMeta.SEARCH_FIELD , RankVOMeta.FUZZY_FIELD , RankVOMeta.SEARCH_VALUE , RankVOMeta.DIRTY_FIELDS , RankVOMeta.SORT_FIELD , RankVOMeta.SORT_TYPE , RankVOMeta.DATA_ORIGIN , RankVOMeta.QUERY_LOGIC , RankVOMeta.IDS } )
	@SentinelResource(value = RankServiceProxy.SAVE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(RankServiceProxy.SAVE)
	public Result save(RankVO rankVO) {
		Result result=rankService.save(rankVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 获取职级序列
	*/
	@ApiOperation(value = "获取职级序列")
	@ApiImplicitParams({
		@ApiImplicitParam(name = RankVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "1"),
	})
	@ApiOperationSupport(order=6 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = RankServiceProxy.GET_BY_ID , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(RankServiceProxy.GET_BY_ID)
	public Result<Rank> getById(String id) {
		Result<Rank> result=new Result<>();
		Rank rank=rankService.getById(id);
		result.success(true).data(rank);
		return result;
	}


	/**
	 * 批量获取职级序列 <br>
	 * 联合主键时，请自行调整实现
	*/
		@ApiOperation(value = "批量获取职级序列")
		@ApiImplicitParams({
				@ApiImplicitParam(name = RankVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
		})
		@ApiOperationSupport(order=3 , author="金杰 , maillank@qq.com") 
		@SentinelResource(value = RankServiceProxy.GET_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(RankServiceProxy.GET_BY_IDS)
	public Result<List<Rank>> getByIds(List<String> ids) {
		Result<List<Rank>> result=new Result<>();
		List<Rank> list=rankService.queryListByIds(ids);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 查询职级序列
	*/
	@ApiOperation(value = "查询职级序列")
	@ApiImplicitParams({
		@ApiImplicitParam(name = RankVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "660564391397163008"),
		@ApiImplicitParam(name = RankVOMeta.CODE , value = "职级序列" , required = false , dataTypeClass=String.class , example = "1"),
		@ApiImplicitParam(name = RankVOMeta.SORT , value = "排序" , required = false , dataTypeClass=Integer.class , example = "1"),
		@ApiImplicitParam(name = RankVOMeta.NOTE , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=5 , author="金杰 , maillank@qq.com" ,  ignoreParameters = { RankVOMeta.PAGE_INDEX , RankVOMeta.PAGE_SIZE } )
	@SentinelResource(value = RankServiceProxy.QUERY_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(RankServiceProxy.QUERY_LIST)
	public Result<List<Rank>> queryList(RankVO sample) {
		Result<List<Rank>> result=new Result<>();
		List<Rank> list=rankService.queryList(sample);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 分页查询职级序列
	*/
	@ApiOperation(value = "分页查询职级序列")
	@ApiImplicitParams({
		@ApiImplicitParam(name = RankVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "660564391397163008"),
		@ApiImplicitParam(name = RankVOMeta.CODE , value = "职级序列" , required = false , dataTypeClass=String.class , example = "1"),
		@ApiImplicitParam(name = RankVOMeta.SORT , value = "排序" , required = false , dataTypeClass=Integer.class , example = "1"),
		@ApiImplicitParam(name = RankVOMeta.NOTE , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=8 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = RankServiceProxy.QUERY_PAGED_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(RankServiceProxy.QUERY_PAGED_LIST)
	public Result<PagedList<Rank>> queryPagedList(RankVO sample) {
		Result<PagedList<Rank>> result=new Result<>();
		PagedList<Rank> list=rankService.queryPagedList(sample,sample.getPageSize(),sample.getPageIndex());
		result.success(true).data(list);
		return result;
	}






}