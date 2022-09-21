package com.dt.platform.ops.controller;


import java.util.List;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import org.github.foxnic.web.framework.web.SuperController;
import org.github.foxnic.web.framework.sentinel.SentinelExceptionUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.alibaba.csp.sentinel.annotation.SentinelResource;


import com.dt.platform.proxy.ops.MonitorVoucherServiceProxy;
import com.dt.platform.domain.ops.meta.MonitorVoucherVOMeta;
import com.dt.platform.domain.ops.MonitorVoucher;
import com.dt.platform.domain.ops.MonitorVoucherVO;
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
import com.dt.platform.domain.ops.meta.MonitorVoucherMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.IMonitorVoucherService;
import com.github.foxnic.api.validate.annotations.NotNull;

/**
 * <p>
 * 监控凭证 接口控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2022-07-13 07:13:15
*/

@Api(tags = "监控凭证")
@ApiSort(0)
@RestController("OpsMonitorVoucherController")
public class MonitorVoucherController extends SuperController {

	@Autowired
	private IMonitorVoucherService monitorVoucherService;


	/**
	 * 添加监控凭证
	*/
	@ApiOperation(value = "添加监控凭证")
	@ApiImplicitParams({
		@ApiImplicitParam(name = MonitorVoucherVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "1"),
		@ApiImplicitParam(name = MonitorVoucherVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class , example = "shop测试主机凭证"),
		@ApiImplicitParam(name = MonitorVoucherVOMeta.ACCOUNT , value = "账户" , required = false , dataTypeClass=String.class , example = "root"),
		@ApiImplicitParam(name = MonitorVoucherVOMeta.VOUCHER , value = "凭证" , required = false , dataTypeClass=String.class , example = "RootOracle123456789@"),
		@ApiImplicitParam(name = MonitorVoucherVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class , example = "shop测试主机凭证"),
	})
	@ApiOperationSupport(order=1)
	@SentinelResource(value = MonitorVoucherServiceProxy.INSERT , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(MonitorVoucherServiceProxy.INSERT)
	public Result insert(MonitorVoucherVO monitorVoucherVO) {
		Result result=monitorVoucherService.insert(monitorVoucherVO,false);
		return result;
	}



	/**
	 * 删除监控凭证
	*/
	@ApiOperation(value = "删除监控凭证")
	@ApiImplicitParams({
		@ApiImplicitParam(name = MonitorVoucherVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "1")
	})
	@ApiOperationSupport(order=2)
	@NotNull(name = MonitorVoucherVOMeta.ID)
	@SentinelResource(value = MonitorVoucherServiceProxy.DELETE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(MonitorVoucherServiceProxy.DELETE)
	public Result deleteById(String id) {
		this.validator().asserts(id).require("缺少id值");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}
		// 引用校验
		Boolean hasRefer = monitorVoucherService.hasRefers(id);
		// 判断是否可以删除
		this.validator().asserts(hasRefer).requireInList("不允许删除当前记录",false);
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}
		Result result=monitorVoucherService.deleteByIdLogical(id);
		return result;
	}


	/**
	 * 批量删除监控凭证 <br>
	 * 联合主键时，请自行调整实现
	*/
	@ApiOperation(value = "批量删除监控凭证")
	@ApiImplicitParams({
		@ApiImplicitParam(name = MonitorVoucherVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
	})
	@ApiOperationSupport(order=3) 
	@NotNull(name = MonitorVoucherVOMeta.IDS)
	@SentinelResource(value = MonitorVoucherServiceProxy.DELETE_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(MonitorVoucherServiceProxy.DELETE_BY_IDS)
	public Result deleteByIds(List<String> ids) {

		// 参数校验
		this.validator().asserts(ids).require("缺少ids参数");
		if(this.validator().failure()) {
			return this.validator().getFirstResult();
		}

		// 查询引用
		Map<String, Boolean> hasRefersMap = monitorVoucherService.hasRefers(ids);
		// 收集可以删除的ID值
		List<String> canDeleteIds = new ArrayList<>();
		for (Map.Entry<String, Boolean> e : hasRefersMap.entrySet()) {
			if (!e.getValue()) {
				canDeleteIds.add(e.getKey());
			}
		}

		// 执行删除
		if (canDeleteIds.isEmpty()) {
			// 如果没有一行可以被删除
			return ErrorDesc.failure().message("无法删除您选中的数据行");
		} else if (canDeleteIds.size() == ids.size()) {
			// 如果全部可以删除
			Result result=monitorVoucherService.deleteByIdsLogical(canDeleteIds);
			return result;
		} else if (canDeleteIds.size()>0 && canDeleteIds.size() < ids.size()) {
			// 如果部分行可以删除
			Result result=monitorVoucherService.deleteByIdsLogical(canDeleteIds);
			if (result.failure()) {
				return result;
			} else {
				return ErrorDesc.success().message("已删除 " + canDeleteIds.size() + " 行，但另有 " + (ids.size() - canDeleteIds.size()) + " 行数据无法删除").messageLevel4Confirm();
			}
		} else {
			// 理论上，这个分支不存在
			return ErrorDesc.success().message("数据删除未处理");
		}
	}

	/**
	 * 更新监控凭证
	*/
	@ApiOperation(value = "更新监控凭证")
	@ApiImplicitParams({
		@ApiImplicitParam(name = MonitorVoucherVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "1"),
		@ApiImplicitParam(name = MonitorVoucherVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class , example = "shop测试主机凭证"),
		@ApiImplicitParam(name = MonitorVoucherVOMeta.ACCOUNT , value = "账户" , required = false , dataTypeClass=String.class , example = "root"),
		@ApiImplicitParam(name = MonitorVoucherVOMeta.VOUCHER , value = "凭证" , required = false , dataTypeClass=String.class , example = "RootOracle123456789@"),
		@ApiImplicitParam(name = MonitorVoucherVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class , example = "shop测试主机凭证"),
	})
	@ApiOperationSupport( order=4 , ignoreParameters = { MonitorVoucherVOMeta.PAGE_INDEX , MonitorVoucherVOMeta.PAGE_SIZE , MonitorVoucherVOMeta.SEARCH_FIELD , MonitorVoucherVOMeta.FUZZY_FIELD , MonitorVoucherVOMeta.SEARCH_VALUE , MonitorVoucherVOMeta.DIRTY_FIELDS , MonitorVoucherVOMeta.SORT_FIELD , MonitorVoucherVOMeta.SORT_TYPE , MonitorVoucherVOMeta.IDS } )
	@NotNull(name = MonitorVoucherVOMeta.ID)
	@SentinelResource(value = MonitorVoucherServiceProxy.UPDATE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(MonitorVoucherServiceProxy.UPDATE)
	public Result update(MonitorVoucherVO monitorVoucherVO) {
		Result result=monitorVoucherService.update(monitorVoucherVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 保存监控凭证
	*/
	@ApiOperation(value = "保存监控凭证")
	@ApiImplicitParams({
		@ApiImplicitParam(name = MonitorVoucherVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "1"),
		@ApiImplicitParam(name = MonitorVoucherVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class , example = "shop测试主机凭证"),
		@ApiImplicitParam(name = MonitorVoucherVOMeta.ACCOUNT , value = "账户" , required = false , dataTypeClass=String.class , example = "root"),
		@ApiImplicitParam(name = MonitorVoucherVOMeta.VOUCHER , value = "凭证" , required = false , dataTypeClass=String.class , example = "RootOracle123456789@"),
		@ApiImplicitParam(name = MonitorVoucherVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class , example = "shop测试主机凭证"),
	})
	@ApiOperationSupport(order=5 ,  ignoreParameters = { MonitorVoucherVOMeta.PAGE_INDEX , MonitorVoucherVOMeta.PAGE_SIZE , MonitorVoucherVOMeta.SEARCH_FIELD , MonitorVoucherVOMeta.FUZZY_FIELD , MonitorVoucherVOMeta.SEARCH_VALUE , MonitorVoucherVOMeta.DIRTY_FIELDS , MonitorVoucherVOMeta.SORT_FIELD , MonitorVoucherVOMeta.SORT_TYPE , MonitorVoucherVOMeta.IDS } )
	@NotNull(name = MonitorVoucherVOMeta.ID)
	@SentinelResource(value = MonitorVoucherServiceProxy.SAVE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(MonitorVoucherServiceProxy.SAVE)
	public Result save(MonitorVoucherVO monitorVoucherVO) {
		Result result=monitorVoucherService.save(monitorVoucherVO,SaveMode.DIRTY_OR_NOT_NULL_FIELDS,false);
		return result;
	}


	/**
	 * 获取监控凭证
	*/
	@ApiOperation(value = "获取监控凭证")
	@ApiImplicitParams({
		@ApiImplicitParam(name = MonitorVoucherVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "1"),
	})
	@ApiOperationSupport(order=6)
	@NotNull(name = MonitorVoucherVOMeta.ID)
	@SentinelResource(value = MonitorVoucherServiceProxy.GET_BY_ID , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(MonitorVoucherServiceProxy.GET_BY_ID)
	public Result<MonitorVoucher> getById(String id) {
		Result<MonitorVoucher> result=new Result<>();
		MonitorVoucher monitorVoucher=monitorVoucherService.getById(id);
		result.success(true).data(monitorVoucher);
		return result;
	}


	/**
	 * 批量获取监控凭证 <br>
	 * 联合主键时，请自行调整实现
	*/
		@ApiOperation(value = "批量获取监控凭证")
		@ApiImplicitParams({
				@ApiImplicitParam(name = MonitorVoucherVOMeta.IDS , value = "主键清单" , required = true , dataTypeClass=List.class , example = "[1,3,4]")
		})
		@ApiOperationSupport(order=3) 
		@NotNull(name = MonitorVoucherVOMeta.IDS)
		@SentinelResource(value = MonitorVoucherServiceProxy.GET_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(MonitorVoucherServiceProxy.GET_BY_IDS)
	public Result<List<MonitorVoucher>> getByIds(List<String> ids) {
		Result<List<MonitorVoucher>> result=new Result<>();
		List<MonitorVoucher> list=monitorVoucherService.queryListByIds(ids);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 查询监控凭证
	*/
	@ApiOperation(value = "查询监控凭证")
	@ApiImplicitParams({
		@ApiImplicitParam(name = MonitorVoucherVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "1"),
		@ApiImplicitParam(name = MonitorVoucherVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class , example = "shop测试主机凭证"),
		@ApiImplicitParam(name = MonitorVoucherVOMeta.ACCOUNT , value = "账户" , required = false , dataTypeClass=String.class , example = "root"),
		@ApiImplicitParam(name = MonitorVoucherVOMeta.VOUCHER , value = "凭证" , required = false , dataTypeClass=String.class , example = "RootOracle123456789@"),
		@ApiImplicitParam(name = MonitorVoucherVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class , example = "shop测试主机凭证"),
	})
	@ApiOperationSupport(order=5 ,  ignoreParameters = { MonitorVoucherVOMeta.PAGE_INDEX , MonitorVoucherVOMeta.PAGE_SIZE } )
	@SentinelResource(value = MonitorVoucherServiceProxy.QUERY_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(MonitorVoucherServiceProxy.QUERY_LIST)
	public Result<List<MonitorVoucher>> queryList(MonitorVoucherVO sample) {
		Result<List<MonitorVoucher>> result=new Result<>();
		List<MonitorVoucher> list=monitorVoucherService.queryList(sample);
		result.success(true).data(list);
		return result;
	}


	/**
	 * 分页查询监控凭证
	*/
	@ApiOperation(value = "分页查询监控凭证")
	@ApiImplicitParams({
		@ApiImplicitParam(name = MonitorVoucherVOMeta.ID , value = "主键" , required = true , dataTypeClass=String.class , example = "1"),
		@ApiImplicitParam(name = MonitorVoucherVOMeta.NAME , value = "名称" , required = false , dataTypeClass=String.class , example = "shop测试主机凭证"),
		@ApiImplicitParam(name = MonitorVoucherVOMeta.ACCOUNT , value = "账户" , required = false , dataTypeClass=String.class , example = "root"),
		@ApiImplicitParam(name = MonitorVoucherVOMeta.VOUCHER , value = "凭证" , required = false , dataTypeClass=String.class , example = "RootOracle123456789@"),
		@ApiImplicitParam(name = MonitorVoucherVOMeta.NOTES , value = "备注" , required = false , dataTypeClass=String.class , example = "shop测试主机凭证"),
	})
	@ApiOperationSupport(order=8)
	@SentinelResource(value = MonitorVoucherServiceProxy.QUERY_PAGED_LIST , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
	@PostMapping(MonitorVoucherServiceProxy.QUERY_PAGED_LIST)
	public Result<PagedList<MonitorVoucher>> queryPagedList(MonitorVoucherVO sample) {
		Result<PagedList<MonitorVoucher>> result=new Result<>();
		PagedList<MonitorVoucher> list=monitorVoucherService.queryPagedList(sample,sample.getPageSize(),sample.getPageIndex());
		result.success(true).data(list);
		return result;
	}






}