package com.dt.platform.ops.controller;

import java.util.List;
import java.util.ArrayList;
import com.github.foxnic.commons.collection.CollectorUtil;
import com.github.foxnic.dao.entity.ReferCause;
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
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-07-13 07:13:15
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("OpsMonitorVoucherController")
public class MonitorVoucherController extends SuperController {

    @Autowired
    private IMonitorVoucherService monitorVoucherService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorVoucherVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorVoucherVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "shop??????????????????"),
		@ApiImplicitParam(name = MonitorVoucherVOMeta.ACCOUNT, value = "??????", required = false, dataTypeClass = String.class, example = "root"),
		@ApiImplicitParam(name = MonitorVoucherVOMeta.VOUCHER, value = "??????", required = false, dataTypeClass = String.class, example = "RootOracle123456789@"),
		@ApiImplicitParam(name = MonitorVoucherVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "shop??????????????????")
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = MonitorVoucherServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorVoucherServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(MonitorVoucherVO monitorVoucherVO) {
        Result result = monitorVoucherService.insert(monitorVoucherVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorVoucherVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = MonitorVoucherServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorVoucherServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  monitorVoucherService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = monitorVoucherService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorVoucherVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = MonitorVoucherServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorVoucherServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = monitorVoucherService.hasRefers(ids);
        // ?????????????????????ID???
        List<String> canDeleteIds = new ArrayList<>();
        for (Map.Entry<String, ReferCause> e : causeMap.entrySet()) {
            if (!e.getValue().hasRefer()) {
                canDeleteIds.add(e.getKey());
            }
        }
        // ????????????
        if (canDeleteIds.isEmpty()) {
            // ?????????????????????????????????
            return ErrorDesc.failure().message("????????????????????????????????????").data(0)
				.addErrors(CollectorUtil.collectArray(CollectorUtil.filter(causeMap.values(),(e)->{return e.hasRefer();}),ReferCause::message,String.class))
				.messageLevel4Confirm();
        } else if (canDeleteIds.size() == ids.size()) {
            // ????????????????????????
            Result result = monitorVoucherService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = monitorVoucherService.deleteByIdsLogical(canDeleteIds);
            if (result.failure()) {
                return result;
            } else {
                return ErrorDesc.success().message("????????? " + canDeleteIds.size() + " ??????????????? " + (ids.size() - canDeleteIds.size()) + " ?????????????????????").data(canDeleteIds.size())
					.addErrors(CollectorUtil.collectArray(CollectorUtil.filter(causeMap.values(),(e)->{return e.hasRefer();}),ReferCause::message,String.class))
					.messageLevel4Confirm();
            }
        } else {
            // ?????????????????????????????????
            return ErrorDesc.success().message("?????????????????????");
        }
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorVoucherVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorVoucherVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "shop??????????????????"),
		@ApiImplicitParam(name = MonitorVoucherVOMeta.ACCOUNT, value = "??????", required = false, dataTypeClass = String.class, example = "root"),
		@ApiImplicitParam(name = MonitorVoucherVOMeta.VOUCHER, value = "??????", required = false, dataTypeClass = String.class, example = "RootOracle123456789@"),
		@ApiImplicitParam(name = MonitorVoucherVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "shop??????????????????")
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { MonitorVoucherVOMeta.PAGE_INDEX, MonitorVoucherVOMeta.PAGE_SIZE, MonitorVoucherVOMeta.SEARCH_FIELD, MonitorVoucherVOMeta.FUZZY_FIELD, MonitorVoucherVOMeta.SEARCH_VALUE, MonitorVoucherVOMeta.DIRTY_FIELDS, MonitorVoucherVOMeta.SORT_FIELD, MonitorVoucherVOMeta.SORT_TYPE, MonitorVoucherVOMeta.IDS })
    @SentinelResource(value = MonitorVoucherServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorVoucherServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(MonitorVoucherVO monitorVoucherVO) {
        Result result = monitorVoucherService.update(monitorVoucherVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorVoucherVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorVoucherVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "shop??????????????????"),
		@ApiImplicitParam(name = MonitorVoucherVOMeta.ACCOUNT, value = "??????", required = false, dataTypeClass = String.class, example = "root"),
		@ApiImplicitParam(name = MonitorVoucherVOMeta.VOUCHER, value = "??????", required = false, dataTypeClass = String.class, example = "RootOracle123456789@"),
		@ApiImplicitParam(name = MonitorVoucherVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "shop??????????????????")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { MonitorVoucherVOMeta.PAGE_INDEX, MonitorVoucherVOMeta.PAGE_SIZE, MonitorVoucherVOMeta.SEARCH_FIELD, MonitorVoucherVOMeta.FUZZY_FIELD, MonitorVoucherVOMeta.SEARCH_VALUE, MonitorVoucherVOMeta.DIRTY_FIELDS, MonitorVoucherVOMeta.SORT_FIELD, MonitorVoucherVOMeta.SORT_TYPE, MonitorVoucherVOMeta.IDS })
    @SentinelResource(value = MonitorVoucherServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorVoucherServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(MonitorVoucherVO monitorVoucherVO) {
        Result result = monitorVoucherService.save(monitorVoucherVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorVoucherVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = MonitorVoucherServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorVoucherServiceProxy.GET_BY_ID)
    public Result<MonitorVoucher> getById(String id) {
        Result<MonitorVoucher> result = new Result<>();
        MonitorVoucher monitorVoucher = monitorVoucherService.getById(id);
        result.success(true).data(monitorVoucher);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorVoucherVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = MonitorVoucherServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorVoucherServiceProxy.GET_BY_IDS)
    public Result<List<MonitorVoucher>> getByIds(List<String> ids) {
        Result<List<MonitorVoucher>> result = new Result<>();
        List<MonitorVoucher> list = monitorVoucherService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorVoucherVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorVoucherVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "shop??????????????????"),
		@ApiImplicitParam(name = MonitorVoucherVOMeta.ACCOUNT, value = "??????", required = false, dataTypeClass = String.class, example = "root"),
		@ApiImplicitParam(name = MonitorVoucherVOMeta.VOUCHER, value = "??????", required = false, dataTypeClass = String.class, example = "RootOracle123456789@"),
		@ApiImplicitParam(name = MonitorVoucherVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "shop??????????????????")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { MonitorVoucherVOMeta.PAGE_INDEX, MonitorVoucherVOMeta.PAGE_SIZE })
    @SentinelResource(value = MonitorVoucherServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorVoucherServiceProxy.QUERY_LIST)
    public Result<List<MonitorVoucher>> queryList(MonitorVoucherVO sample) {
        Result<List<MonitorVoucher>> result = new Result<>();
        List<MonitorVoucher> list = monitorVoucherService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorVoucherVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorVoucherVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "shop??????????????????"),
		@ApiImplicitParam(name = MonitorVoucherVOMeta.ACCOUNT, value = "??????", required = false, dataTypeClass = String.class, example = "root"),
		@ApiImplicitParam(name = MonitorVoucherVOMeta.VOUCHER, value = "??????", required = false, dataTypeClass = String.class, example = "RootOracle123456789@"),
		@ApiImplicitParam(name = MonitorVoucherVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "shop??????????????????")
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = MonitorVoucherServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorVoucherServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<MonitorVoucher>> queryPagedList(MonitorVoucherVO sample) {
        Result<PagedList<MonitorVoucher>> result = new Result<>();
        PagedList<MonitorVoucher> list = monitorVoucherService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }
}
