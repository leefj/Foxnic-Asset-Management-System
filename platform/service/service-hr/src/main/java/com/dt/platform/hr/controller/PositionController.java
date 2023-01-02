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


import com.dt.platform.proxy.hr.PositionServiceProxy;
import com.dt.platform.domain.hr.meta.PositionVOMeta;
import com.dt.platform.domain.hr.Position;
import com.dt.platform.domain.hr.PositionVO;
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
import com.dt.platform.domain.hr.meta.PositionMeta;
import com.dt.platform.domain.hr.PositionType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.hr.service.IPositionService;
import com.github.foxnic.api.validate.annotations.NotNull;

/**
 * <p>
 * 工作岗位接口控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-01-02 14:12:25
*/

@InDoc
@Api(tags = "工作岗位")
@RestController("HrPositionController")
public class PositionController extends SuperController {

	@Autowired
	private IPositionService positionService;


	/**
	 * 添加工作岗位
	*/
	@ApiOperation(value = "添加工作岗位")
	@ApiImplicitParams({
		@ApiImplicitParam(name = PositionVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "660760911509716992"),
		@ApiImplicitParam(name = PositionVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class , example = "系统管理员"),
		@ApiImplicitParam(name = PositionVOMeta.TYPE , value = "类型" , required = false , dataTypeClass=String.class , example = "660564350964072448"),
		@ApiImplicitParam(name = PositionVOMeta.NOTE , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true , ignorePrimaryKey = true)
	@ApiOperationSupport(order=1 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = PositionServiceProxy.INSERT , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(PositionServiceProxy.INSERT)
	public Result insert(PositionVO positionVO) {
		Result result=positionService.insert(positionVO,false);
		return result;
	}



	/**
	 * 删除工作岗位
	*/
	@ApiOperation(value = "删除工作岗位")
	@ApiImplicitParams({
		@ApiImplicitParam(name = PositionVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "660760911509716992")
	})
	@ApiOperationSupport(order=2 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = PositionServiceProxy.DELETE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(PositionServiceProxy.DELETE)
	public Result deleteById(String id) {
		this.validator().asserts(id).require("缺少id值");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}
		// 引用校验
		ReferCause cause =  positionService.hasRefers(id);
		// 判断是否可以删除
		this.validator().asserts(cause.hasRefer()).requireEqual("不允许删除当前记录："+cause.message(),false);
		if(this.validator().failure()) {
			return this.validator().getFirstResult().messageLevel4Confirm();
		}
		Result result=positionService.deleteByIdLogical(id);
		return result;
	}


	/**
	 * 批量删除工作岗位 <br>
	 * 联合主键时，请自行调整实现
	*/
	@ApiOperation(value = "批量删除工作岗位")
	@ApiImplicitParams({
		@ApiImplicitParam(name = PositionVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
	})
	@ApiOperationSupport(order=3 , author="金杰 , maillank@qq.com") 
	@SentinelResource(value = PositionServiceProxy.DELETE_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(PositionServiceProxy.DELETE_BY_IDS)
	public Result deleteByIds(List<String> ids) {

		// 参数校验
		this.validator().asserts(ids).require("缺少ids参数");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}

		// 查询引用
		Map<String, ReferCause> causeMap = positionService.hasRefers(ids);
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
			Result result=positionService.deleteByIdsLogical(canDeleteIds);
			return result;
		} else if (canDeleteIds.size()>0 && canDeleteIds.size() < ids.size()) {
			// 如果部分行可以删除
			Result result=positionService.deleteByIdsLogical(canDeleteIds);
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
	 * 更新工作岗位
	*/
	@ApiOperation(value = "更新工作岗位")
	@ApiImplicitParams({
		@ApiImplicitParam(name = PositionVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "660760911509716992"),
		@ApiImplicitParam(name = PositionVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class , example = "系统管理员"),
		@ApiImplicitParam(name = PositionVOMeta.TYPE , value = "类型" , required = false , dataTypeClass=String.class , example = "660564350964072448"),
		@ApiImplicitParam(name = PositionVOMeta.NOTE , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
	@ApiOperationSupport( order=4 , author="金杰 , maillank@qq.com" ,  ignoreParameters = { PositionVOMeta.PAGE_INDEX , PositionVOMeta.PAGE_SIZE , PositionVOMeta.SEARCH_FIELD , PositionVOMeta.FUZZY_FIELD , PositionVOMeta.SEARCH_VALUE , PositionVOMeta.DIRTY_FIELDS , PositionVOMeta.SORT_FIELD , PositionVOMeta.SORT_TYPE , PositionVOMeta.DATA_ORIGIN , PositionVOMeta.QUERY_LOGIC , PositionVOMeta.IDS } )
	@SentinelResource(value = PositionServiceProxy.UPDATE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(PositionServiceProxy.UPDATE)
	public Result update(PositionVO positionVO) {
		Result result=positionService.update(positionVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 保存工作岗位
	*/
	@ApiOperation(value = "保存工作岗位")
	@ApiImplicitParams({
		@ApiImplicitParam(name = PositionVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "660760911509716992"),
		@ApiImplicitParam(name = PositionVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class , example = "系统管理员"),
		@ApiImplicitParam(name = PositionVOMeta.TYPE , value = "类型" , required = false , dataTypeClass=String.class , example = "660564350964072448"),
		@ApiImplicitParam(name = PositionVOMeta.NOTE , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
	@ApiOperationSupport(order=5 ,  ignoreParameters = { PositionVOMeta.PAGE_INDEX , PositionVOMeta.PAGE_SIZE , PositionVOMeta.SEARCH_FIELD , PositionVOMeta.FUZZY_FIELD , PositionVOMeta.SEARCH_VALUE , PositionVOMeta.DIRTY_FIELDS , PositionVOMeta.SORT_FIELD , PositionVOMeta.SORT_TYPE , PositionVOMeta.DATA_ORIGIN , PositionVOMeta.QUERY_LOGIC , PositionVOMeta.IDS } )
	@SentinelResource(value = PositionServiceProxy.SAVE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(PositionServiceProxy.SAVE)
	public Result save(PositionVO positionVO) {
		Result result=positionService.save(positionVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 获取工作岗位
	*/
	@ApiOperation(value = "获取工作岗位")
	@ApiImplicitParams({
		@ApiImplicitParam(name = PositionVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "1"),
	})
	@ApiOperationSupport(order=6 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = PositionServiceProxy.GET_BY_ID , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(PositionServiceProxy.GET_BY_ID)
	public Result<Position> getById(String id) {
		Result<Position> result=new Result<>();
		Position position=positionService.getById(id);
		// join 关联的对象
		positionService.dao().fill(position)
			.with(PositionMeta.POSITION_TYPE)
			.execute();
		result.success(true).data(position);
		return result;
	}


	/**
	 * 批量获取工作岗位 <br>
	 * 联合主键时，请自行调整实现
	*/
		@ApiOperation(value = "批量获取工作岗位")
		@ApiImplicitParams({
				@ApiImplicitParam(name = PositionVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
		})
		@ApiOperationSupport(order=3 , author="金杰 , maillank@qq.com") 
		@SentinelResource(value = PositionServiceProxy.GET_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(PositionServiceProxy.GET_BY_IDS)
	public Result<List<Position>> getByIds(List<String> ids) {
		Result<List<Position>> result=new Result<>();
		List<Position> list=positionService.queryListByIds(ids);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 查询工作岗位
	*/
	@ApiOperation(value = "查询工作岗位")
	@ApiImplicitParams({
		@ApiImplicitParam(name = PositionVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "660760911509716992"),
		@ApiImplicitParam(name = PositionVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class , example = "系统管理员"),
		@ApiImplicitParam(name = PositionVOMeta.TYPE , value = "类型" , required = false , dataTypeClass=String.class , example = "660564350964072448"),
		@ApiImplicitParam(name = PositionVOMeta.NOTE , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=5 , author="金杰 , maillank@qq.com" ,  ignoreParameters = { PositionVOMeta.PAGE_INDEX , PositionVOMeta.PAGE_SIZE } )
	@SentinelResource(value = PositionServiceProxy.QUERY_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(PositionServiceProxy.QUERY_LIST)
	public Result<List<Position>> queryList(PositionVO sample) {
		Result<List<Position>> result=new Result<>();
		List<Position> list=positionService.queryList(sample);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 分页查询工作岗位
	*/
	@ApiOperation(value = "分页查询工作岗位")
	@ApiImplicitParams({
		@ApiImplicitParam(name = PositionVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "660760911509716992"),
		@ApiImplicitParam(name = PositionVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class , example = "系统管理员"),
		@ApiImplicitParam(name = PositionVOMeta.TYPE , value = "类型" , required = false , dataTypeClass=String.class , example = "660564350964072448"),
		@ApiImplicitParam(name = PositionVOMeta.NOTE , value = "备注" , required = false , dataTypeClass=String.class),
	})
	@ApiOperationSupport(order=8 , author="金杰 , maillank@qq.com")
	@SentinelResource(value = PositionServiceProxy.QUERY_PAGED_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(PositionServiceProxy.QUERY_PAGED_LIST)
	public Result<PagedList<Position>> queryPagedList(PositionVO sample) {
		Result<PagedList<Position>> result=new Result<>();
		PagedList<Position> list=positionService.queryPagedList(sample,sample.getPageSize(),sample.getPageIndex());
		// join 关联的对象
		positionService.dao().fill(list)
			.with(PositionMeta.POSITION_TYPE)
			.execute();
		result.success(true).data(list);
		return result;
	}






}