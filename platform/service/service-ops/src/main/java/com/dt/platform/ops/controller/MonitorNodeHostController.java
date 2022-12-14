package com.dt.platform.ops.controller;

import java.util.List;
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
import com.dt.platform.proxy.ops.MonitorNodeHostServiceProxy;
import com.dt.platform.domain.ops.meta.MonitorNodeHostVOMeta;
import com.dt.platform.domain.ops.MonitorNodeHost;
import com.dt.platform.domain.ops.MonitorNodeHostVO;
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
import com.dt.platform.domain.ops.meta.MonitorNodeHostMeta;
import java.math.BigDecimal;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.IMonitorNodeHostService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-02-08 13:14:45
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("OpsMonitorNodeHostController")
public class MonitorNodeHostController extends SuperController {

    @Autowired
    private IMonitorNodeHostService monitorNodeHostService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.NODE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.HOSTNAME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.BOOTTIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.OS_VERION, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.ARCH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.CPU, value = "CPU??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.CPU_FRE, value = "CPU??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.MEMORY, value = "??????(M)", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.CPU_SYS, value = "cpuSys", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.CPU_USER, value = "cpuUser", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.CPU_WAIT, value = "cpuWait", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.CPU_IDLE, value = "CPU?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.CPU_USED, value = "CPU?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.OS_LOAD, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.OS_LOAD5, value = "????????????5", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.OS_LOAD15, value = "????????????15", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.MEMORY_USED, value = "???????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.VMEMORY_USED, value = "?????????????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.NETWORK_FLOW_UP, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.NETWORK_FLOW_DOWN, value = "?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.PROCESS_CNT, value = "?????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.P_MEMORY_USED, value = "??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.V_MEMORY_USED, value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.INFO, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.RECORD_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = MonitorNodeHostServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeHostServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(MonitorNodeHostVO monitorNodeHostVO) {
        Result result = monitorNodeHostService.insert(monitorNodeHostVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = MonitorNodeHostServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeHostServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = monitorNodeHostService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = MonitorNodeHostServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeHostServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = monitorNodeHostService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.NODE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.HOSTNAME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.BOOTTIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.OS_VERION, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.ARCH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.CPU, value = "CPU??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.CPU_FRE, value = "CPU??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.MEMORY, value = "??????(M)", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.CPU_SYS, value = "cpuSys", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.CPU_USER, value = "cpuUser", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.CPU_WAIT, value = "cpuWait", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.CPU_IDLE, value = "CPU?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.CPU_USED, value = "CPU?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.OS_LOAD, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.OS_LOAD5, value = "????????????5", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.OS_LOAD15, value = "????????????15", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.MEMORY_USED, value = "???????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.VMEMORY_USED, value = "?????????????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.NETWORK_FLOW_UP, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.NETWORK_FLOW_DOWN, value = "?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.PROCESS_CNT, value = "?????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.P_MEMORY_USED, value = "??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.V_MEMORY_USED, value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.INFO, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.RECORD_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { MonitorNodeHostVOMeta.PAGE_INDEX, MonitorNodeHostVOMeta.PAGE_SIZE, MonitorNodeHostVOMeta.SEARCH_FIELD, MonitorNodeHostVOMeta.FUZZY_FIELD, MonitorNodeHostVOMeta.SEARCH_VALUE, MonitorNodeHostVOMeta.DIRTY_FIELDS, MonitorNodeHostVOMeta.SORT_FIELD, MonitorNodeHostVOMeta.SORT_TYPE, MonitorNodeHostVOMeta.IDS })
    @SentinelResource(value = MonitorNodeHostServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeHostServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(MonitorNodeHostVO monitorNodeHostVO) {
        Result result = monitorNodeHostService.update(monitorNodeHostVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.NODE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.HOSTNAME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.BOOTTIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.OS_VERION, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.ARCH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.CPU, value = "CPU??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.CPU_FRE, value = "CPU??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.MEMORY, value = "??????(M)", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.CPU_SYS, value = "cpuSys", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.CPU_USER, value = "cpuUser", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.CPU_WAIT, value = "cpuWait", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.CPU_IDLE, value = "CPU?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.CPU_USED, value = "CPU?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.OS_LOAD, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.OS_LOAD5, value = "????????????5", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.OS_LOAD15, value = "????????????15", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.MEMORY_USED, value = "???????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.VMEMORY_USED, value = "?????????????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.NETWORK_FLOW_UP, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.NETWORK_FLOW_DOWN, value = "?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.PROCESS_CNT, value = "?????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.P_MEMORY_USED, value = "??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.V_MEMORY_USED, value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.INFO, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.RECORD_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { MonitorNodeHostVOMeta.PAGE_INDEX, MonitorNodeHostVOMeta.PAGE_SIZE, MonitorNodeHostVOMeta.SEARCH_FIELD, MonitorNodeHostVOMeta.FUZZY_FIELD, MonitorNodeHostVOMeta.SEARCH_VALUE, MonitorNodeHostVOMeta.DIRTY_FIELDS, MonitorNodeHostVOMeta.SORT_FIELD, MonitorNodeHostVOMeta.SORT_TYPE, MonitorNodeHostVOMeta.IDS })
    @SentinelResource(value = MonitorNodeHostServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeHostServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(MonitorNodeHostVO monitorNodeHostVO) {
        Result result = monitorNodeHostService.save(monitorNodeHostVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = MonitorNodeHostServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeHostServiceProxy.GET_BY_ID)
    public Result<MonitorNodeHost> getById(String id) {
        Result<MonitorNodeHost> result = new Result<>();
        MonitorNodeHost monitorNodeHost = monitorNodeHostService.getById(id);
        result.success(true).data(monitorNodeHost);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = MonitorNodeHostServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeHostServiceProxy.GET_BY_IDS)
    public Result<List<MonitorNodeHost>> getByIds(List<String> ids) {
        Result<List<MonitorNodeHost>> result = new Result<>();
        List<MonitorNodeHost> list = monitorNodeHostService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.NODE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.HOSTNAME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.BOOTTIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.OS_VERION, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.ARCH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.CPU, value = "CPU??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.CPU_FRE, value = "CPU??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.MEMORY, value = "??????(M)", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.CPU_SYS, value = "cpuSys", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.CPU_USER, value = "cpuUser", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.CPU_WAIT, value = "cpuWait", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.CPU_IDLE, value = "CPU?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.CPU_USED, value = "CPU?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.OS_LOAD, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.OS_LOAD5, value = "????????????5", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.OS_LOAD15, value = "????????????15", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.MEMORY_USED, value = "???????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.VMEMORY_USED, value = "?????????????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.NETWORK_FLOW_UP, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.NETWORK_FLOW_DOWN, value = "?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.PROCESS_CNT, value = "?????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.P_MEMORY_USED, value = "??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.V_MEMORY_USED, value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.INFO, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.RECORD_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { MonitorNodeHostVOMeta.PAGE_INDEX, MonitorNodeHostVOMeta.PAGE_SIZE })
    @SentinelResource(value = MonitorNodeHostServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeHostServiceProxy.QUERY_LIST)
    public Result<List<MonitorNodeHost>> queryList(MonitorNodeHostVO sample) {
        Result<List<MonitorNodeHost>> result = new Result<>();
        List<MonitorNodeHost> list = monitorNodeHostService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.NODE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.HOSTNAME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.BOOTTIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.OS_VERION, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.ARCH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.CPU, value = "CPU??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.CPU_FRE, value = "CPU??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.MEMORY, value = "??????(M)", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.CPU_SYS, value = "cpuSys", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.CPU_USER, value = "cpuUser", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.CPU_WAIT, value = "cpuWait", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.CPU_IDLE, value = "CPU?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.CPU_USED, value = "CPU?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.OS_LOAD, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.OS_LOAD5, value = "????????????5", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.OS_LOAD15, value = "????????????15", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.MEMORY_USED, value = "???????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.VMEMORY_USED, value = "?????????????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.NETWORK_FLOW_UP, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.NETWORK_FLOW_DOWN, value = "?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.PROCESS_CNT, value = "?????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.P_MEMORY_USED, value = "??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.V_MEMORY_USED, value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.INFO, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.RECORD_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorNodeHostVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = MonitorNodeHostServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeHostServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<MonitorNodeHost>> queryPagedList(MonitorNodeHostVO sample) {
        Result<PagedList<MonitorNodeHost>> result = new Result<>();
        PagedList<MonitorNodeHost> list = monitorNodeHostService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = MonitorNodeHostServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(MonitorNodeHostServiceProxy.EXPORT_EXCEL)
    public void exportExcel(MonitorNodeHostVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = monitorNodeHostService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = MonitorNodeHostServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(MonitorNodeHostServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = monitorNodeHostService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = MonitorNodeHostServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(MonitorNodeHostServiceProxy.IMPORT_EXCEL)
    public Result importExcel(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
        // ?????????????????????
        Map<String, MultipartFile> map = request.getFileMap();
        InputStream input = null;
        for (MultipartFile mf : map.values()) {
            input = StreamUtil.bytes2input(mf.getBytes());
            break;
        }
        if (input == null) {
            return ErrorDesc.failure().message("?????????????????????");
        }
        List<ValidateResult> errors = monitorNodeHostService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
