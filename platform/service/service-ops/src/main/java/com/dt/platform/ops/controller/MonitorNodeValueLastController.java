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
import com.dt.platform.proxy.ops.MonitorNodeValueLastServiceProxy;
import com.dt.platform.domain.ops.meta.MonitorNodeValueLastVOMeta;
import com.dt.platform.domain.ops.MonitorNodeValueLast;
import com.dt.platform.domain.ops.MonitorNodeValueLastVO;
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
import com.dt.platform.domain.ops.meta.MonitorNodeValueLastMeta;
import java.math.BigDecimal;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.IMonitorNodeValueLastService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ?????????????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-02-20 14:46:45
 */
@Api(tags = "??????????????????")
@ApiSort(0)
@RestController("OpsMonitorNodeValueLastController")
public class MonitorNodeValueLastController extends SuperController {

    @Autowired
    private IMonitorNodeValueLastService monitorNodeValueLastService;

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "547135053407191040"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.NODE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "ee07abf6-89a3-11ec-bf3e-00163e1b60a7"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.MONITOR_TPL_CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "tpl_host_linux_script"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.RESULT_STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "sucess"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.RESULT_MESSAGE, value = "????????????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.INDICATOR_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "os.arch"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.HOSTNAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.OS_DATETIME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.BOOTTIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.OS_VERION, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.ARCH, value = "??????", required = false, dataTypeClass = String.class, example = "x86_64"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CPU_NUMBER, value = "CPU??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CPU_FREE, value = "CPU??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CPU_SYS, value = "cpuSys", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CPU_USER, value = "cpuUser", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CPU_WAIT, value = "cpuWait", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CPU_IDLE, value = "CPU?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CPU_USED, value = "CPU?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.OS_LOAD, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.OS_LOAD5, value = "????????????5", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.OS_LOAD15, value = "????????????15", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.NETWORK_FLOW_UP, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.NETWORK_FLOW_DOWN, value = "?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.PROCESS_CNT, value = "?????????", required = false, dataTypeClass = Integer.class, example = "0"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.P_MEMORY_SIZE, value = "????????????(M)", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.V_MEMORY_SIZE, value = "????????????(M)", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.P_MEMORY_USED, value = "?????????????????????", required = false, dataTypeClass = Long.class, example = "0"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.V_MEMORY_USED, value = "?????????????????????", required = false, dataTypeClass = Long.class, example = "0"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.INFO, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LABEL1, value = "??????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LABEL2, value = "??????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LABEL3, value = "??????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CODE1, value = "??????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CODE2, value = "??????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CODE3, value = "??????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.VALUE_NUMBER1, value = "??????1", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.VALUE_NUMBER2, value = "??????2", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.VALUE_NUMBER3, value = "??????3", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.VALUE_STR1, value = "?????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.VALUE_STR2, value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.VALUE_STR3, value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.VALUE_INT1, value = "??????1", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.VALUE_INT2, value = "??????2", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.VALUE_INT3, value = "??????3", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_LABEL1, value = "?????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_LABEL2, value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_LABEL3, value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_CODE1, value = "?????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_CODE2, value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_CODE3, value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_VALUE_NUMBER1, value = "?????????1", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_VALUE_NUMBER2, value = "?????????2", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_VALUE_NUMBER3, value = "?????????3", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_VALUE_STR1, value = "????????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_VALUE_STR2, value = "????????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_VALUE_STR3, value = "????????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_VALUE_INT1, value = "?????????1", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_VALUE_INT2, value = "?????????2", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_VALUE_INT3, value = "?????????3", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.UID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.IS_CONNECTED, value = "????????????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.RECORD_TIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-02-18 07:19:22")
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = MonitorNodeValueLastServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeValueLastServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(MonitorNodeValueLastVO monitorNodeValueLastVO) {
        Result result = monitorNodeValueLastService.insert(monitorNodeValueLastVO, false);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "547135053407191040")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = MonitorNodeValueLastServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeValueLastServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = monitorNodeValueLastService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ?????????????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "??????????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = MonitorNodeValueLastServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeValueLastServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = monitorNodeValueLastService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "547135053407191040"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.NODE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "ee07abf6-89a3-11ec-bf3e-00163e1b60a7"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.MONITOR_TPL_CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "tpl_host_linux_script"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.RESULT_STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "sucess"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.RESULT_MESSAGE, value = "????????????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.INDICATOR_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "os.arch"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.HOSTNAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.OS_DATETIME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.BOOTTIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.OS_VERION, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.ARCH, value = "??????", required = false, dataTypeClass = String.class, example = "x86_64"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CPU_NUMBER, value = "CPU??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CPU_FREE, value = "CPU??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CPU_SYS, value = "cpuSys", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CPU_USER, value = "cpuUser", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CPU_WAIT, value = "cpuWait", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CPU_IDLE, value = "CPU?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CPU_USED, value = "CPU?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.OS_LOAD, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.OS_LOAD5, value = "????????????5", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.OS_LOAD15, value = "????????????15", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.NETWORK_FLOW_UP, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.NETWORK_FLOW_DOWN, value = "?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.PROCESS_CNT, value = "?????????", required = false, dataTypeClass = Integer.class, example = "0"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.P_MEMORY_SIZE, value = "????????????(M)", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.V_MEMORY_SIZE, value = "????????????(M)", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.P_MEMORY_USED, value = "?????????????????????", required = false, dataTypeClass = Long.class, example = "0"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.V_MEMORY_USED, value = "?????????????????????", required = false, dataTypeClass = Long.class, example = "0"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.INFO, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LABEL1, value = "??????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LABEL2, value = "??????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LABEL3, value = "??????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CODE1, value = "??????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CODE2, value = "??????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CODE3, value = "??????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.VALUE_NUMBER1, value = "??????1", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.VALUE_NUMBER2, value = "??????2", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.VALUE_NUMBER3, value = "??????3", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.VALUE_STR1, value = "?????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.VALUE_STR2, value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.VALUE_STR3, value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.VALUE_INT1, value = "??????1", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.VALUE_INT2, value = "??????2", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.VALUE_INT3, value = "??????3", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_LABEL1, value = "?????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_LABEL2, value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_LABEL3, value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_CODE1, value = "?????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_CODE2, value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_CODE3, value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_VALUE_NUMBER1, value = "?????????1", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_VALUE_NUMBER2, value = "?????????2", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_VALUE_NUMBER3, value = "?????????3", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_VALUE_STR1, value = "????????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_VALUE_STR2, value = "????????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_VALUE_STR3, value = "????????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_VALUE_INT1, value = "?????????1", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_VALUE_INT2, value = "?????????2", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_VALUE_INT3, value = "?????????3", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.UID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.IS_CONNECTED, value = "????????????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.RECORD_TIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-02-18 07:19:22")
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { MonitorNodeValueLastVOMeta.PAGE_INDEX, MonitorNodeValueLastVOMeta.PAGE_SIZE, MonitorNodeValueLastVOMeta.SEARCH_FIELD, MonitorNodeValueLastVOMeta.FUZZY_FIELD, MonitorNodeValueLastVOMeta.SEARCH_VALUE, MonitorNodeValueLastVOMeta.DIRTY_FIELDS, MonitorNodeValueLastVOMeta.SORT_FIELD, MonitorNodeValueLastVOMeta.SORT_TYPE, MonitorNodeValueLastVOMeta.IDS })
    @SentinelResource(value = MonitorNodeValueLastServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeValueLastServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(MonitorNodeValueLastVO monitorNodeValueLastVO) {
        Result result = monitorNodeValueLastService.update(monitorNodeValueLastVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "547135053407191040"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.NODE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "ee07abf6-89a3-11ec-bf3e-00163e1b60a7"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.MONITOR_TPL_CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "tpl_host_linux_script"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.RESULT_STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "sucess"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.RESULT_MESSAGE, value = "????????????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.INDICATOR_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "os.arch"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.HOSTNAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.OS_DATETIME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.BOOTTIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.OS_VERION, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.ARCH, value = "??????", required = false, dataTypeClass = String.class, example = "x86_64"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CPU_NUMBER, value = "CPU??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CPU_FREE, value = "CPU??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CPU_SYS, value = "cpuSys", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CPU_USER, value = "cpuUser", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CPU_WAIT, value = "cpuWait", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CPU_IDLE, value = "CPU?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CPU_USED, value = "CPU?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.OS_LOAD, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.OS_LOAD5, value = "????????????5", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.OS_LOAD15, value = "????????????15", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.NETWORK_FLOW_UP, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.NETWORK_FLOW_DOWN, value = "?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.PROCESS_CNT, value = "?????????", required = false, dataTypeClass = Integer.class, example = "0"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.P_MEMORY_SIZE, value = "????????????(M)", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.V_MEMORY_SIZE, value = "????????????(M)", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.P_MEMORY_USED, value = "?????????????????????", required = false, dataTypeClass = Long.class, example = "0"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.V_MEMORY_USED, value = "?????????????????????", required = false, dataTypeClass = Long.class, example = "0"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.INFO, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LABEL1, value = "??????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LABEL2, value = "??????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LABEL3, value = "??????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CODE1, value = "??????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CODE2, value = "??????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CODE3, value = "??????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.VALUE_NUMBER1, value = "??????1", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.VALUE_NUMBER2, value = "??????2", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.VALUE_NUMBER3, value = "??????3", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.VALUE_STR1, value = "?????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.VALUE_STR2, value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.VALUE_STR3, value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.VALUE_INT1, value = "??????1", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.VALUE_INT2, value = "??????2", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.VALUE_INT3, value = "??????3", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_LABEL1, value = "?????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_LABEL2, value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_LABEL3, value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_CODE1, value = "?????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_CODE2, value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_CODE3, value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_VALUE_NUMBER1, value = "?????????1", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_VALUE_NUMBER2, value = "?????????2", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_VALUE_NUMBER3, value = "?????????3", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_VALUE_STR1, value = "????????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_VALUE_STR2, value = "????????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_VALUE_STR3, value = "????????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_VALUE_INT1, value = "?????????1", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_VALUE_INT2, value = "?????????2", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_VALUE_INT3, value = "?????????3", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.UID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.IS_CONNECTED, value = "????????????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.RECORD_TIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-02-18 07:19:22")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { MonitorNodeValueLastVOMeta.PAGE_INDEX, MonitorNodeValueLastVOMeta.PAGE_SIZE, MonitorNodeValueLastVOMeta.SEARCH_FIELD, MonitorNodeValueLastVOMeta.FUZZY_FIELD, MonitorNodeValueLastVOMeta.SEARCH_VALUE, MonitorNodeValueLastVOMeta.DIRTY_FIELDS, MonitorNodeValueLastVOMeta.SORT_FIELD, MonitorNodeValueLastVOMeta.SORT_TYPE, MonitorNodeValueLastVOMeta.IDS })
    @SentinelResource(value = MonitorNodeValueLastServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeValueLastServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(MonitorNodeValueLastVO monitorNodeValueLastVO) {
        Result result = monitorNodeValueLastService.save(monitorNodeValueLastVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = MonitorNodeValueLastServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeValueLastServiceProxy.GET_BY_ID)
    public Result<MonitorNodeValueLast> getById(String id) {
        Result<MonitorNodeValueLast> result = new Result<>();
        MonitorNodeValueLast monitorNodeValueLast = monitorNodeValueLastService.getById(id);
        result.success(true).data(monitorNodeValueLast);
        return result;
    }

    /**
     * ?????????????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "??????????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = MonitorNodeValueLastServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeValueLastServiceProxy.GET_BY_IDS)
    public Result<List<MonitorNodeValueLast>> getByIds(List<String> ids) {
        Result<List<MonitorNodeValueLast>> result = new Result<>();
        List<MonitorNodeValueLast> list = monitorNodeValueLastService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "547135053407191040"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.NODE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "ee07abf6-89a3-11ec-bf3e-00163e1b60a7"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.MONITOR_TPL_CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "tpl_host_linux_script"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.RESULT_STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "sucess"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.RESULT_MESSAGE, value = "????????????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.INDICATOR_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "os.arch"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.HOSTNAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.OS_DATETIME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.BOOTTIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.OS_VERION, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.ARCH, value = "??????", required = false, dataTypeClass = String.class, example = "x86_64"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CPU_NUMBER, value = "CPU??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CPU_FREE, value = "CPU??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CPU_SYS, value = "cpuSys", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CPU_USER, value = "cpuUser", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CPU_WAIT, value = "cpuWait", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CPU_IDLE, value = "CPU?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CPU_USED, value = "CPU?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.OS_LOAD, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.OS_LOAD5, value = "????????????5", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.OS_LOAD15, value = "????????????15", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.NETWORK_FLOW_UP, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.NETWORK_FLOW_DOWN, value = "?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.PROCESS_CNT, value = "?????????", required = false, dataTypeClass = Integer.class, example = "0"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.P_MEMORY_SIZE, value = "????????????(M)", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.V_MEMORY_SIZE, value = "????????????(M)", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.P_MEMORY_USED, value = "?????????????????????", required = false, dataTypeClass = Long.class, example = "0"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.V_MEMORY_USED, value = "?????????????????????", required = false, dataTypeClass = Long.class, example = "0"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.INFO, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LABEL1, value = "??????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LABEL2, value = "??????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LABEL3, value = "??????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CODE1, value = "??????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CODE2, value = "??????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CODE3, value = "??????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.VALUE_NUMBER1, value = "??????1", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.VALUE_NUMBER2, value = "??????2", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.VALUE_NUMBER3, value = "??????3", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.VALUE_STR1, value = "?????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.VALUE_STR2, value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.VALUE_STR3, value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.VALUE_INT1, value = "??????1", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.VALUE_INT2, value = "??????2", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.VALUE_INT3, value = "??????3", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_LABEL1, value = "?????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_LABEL2, value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_LABEL3, value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_CODE1, value = "?????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_CODE2, value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_CODE3, value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_VALUE_NUMBER1, value = "?????????1", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_VALUE_NUMBER2, value = "?????????2", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_VALUE_NUMBER3, value = "?????????3", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_VALUE_STR1, value = "????????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_VALUE_STR2, value = "????????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_VALUE_STR3, value = "????????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_VALUE_INT1, value = "?????????1", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_VALUE_INT2, value = "?????????2", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_VALUE_INT3, value = "?????????3", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.UID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.IS_CONNECTED, value = "????????????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.RECORD_TIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-02-18 07:19:22")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { MonitorNodeValueLastVOMeta.PAGE_INDEX, MonitorNodeValueLastVOMeta.PAGE_SIZE })
    @SentinelResource(value = MonitorNodeValueLastServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeValueLastServiceProxy.QUERY_LIST)
    public Result<List<MonitorNodeValueLast>> queryList(MonitorNodeValueLastVO sample) {
        Result<List<MonitorNodeValueLast>> result = new Result<>();
        List<MonitorNodeValueLast> list = monitorNodeValueLastService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????????????????
     */
    @ApiOperation(value = "??????????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "547135053407191040"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.NODE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "ee07abf6-89a3-11ec-bf3e-00163e1b60a7"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.MONITOR_TPL_CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "tpl_host_linux_script"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.RESULT_STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "sucess"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.RESULT_MESSAGE, value = "????????????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.INDICATOR_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "os.arch"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.HOSTNAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.OS_DATETIME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.BOOTTIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.OS_VERION, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.ARCH, value = "??????", required = false, dataTypeClass = String.class, example = "x86_64"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CPU_NUMBER, value = "CPU??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CPU_FREE, value = "CPU??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CPU_SYS, value = "cpuSys", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CPU_USER, value = "cpuUser", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CPU_WAIT, value = "cpuWait", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CPU_IDLE, value = "CPU?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CPU_USED, value = "CPU?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.OS_LOAD, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.OS_LOAD5, value = "????????????5", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.OS_LOAD15, value = "????????????15", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.NETWORK_FLOW_UP, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.NETWORK_FLOW_DOWN, value = "?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.PROCESS_CNT, value = "?????????", required = false, dataTypeClass = Integer.class, example = "0"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.P_MEMORY_SIZE, value = "????????????(M)", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.V_MEMORY_SIZE, value = "????????????(M)", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.P_MEMORY_USED, value = "?????????????????????", required = false, dataTypeClass = Long.class, example = "0"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.V_MEMORY_USED, value = "?????????????????????", required = false, dataTypeClass = Long.class, example = "0"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.INFO, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LABEL1, value = "??????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LABEL2, value = "??????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LABEL3, value = "??????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CODE1, value = "??????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CODE2, value = "??????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.CODE3, value = "??????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.VALUE_NUMBER1, value = "??????1", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.VALUE_NUMBER2, value = "??????2", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.VALUE_NUMBER3, value = "??????3", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.VALUE_STR1, value = "?????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.VALUE_STR2, value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.VALUE_STR3, value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.VALUE_INT1, value = "??????1", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.VALUE_INT2, value = "??????2", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.VALUE_INT3, value = "??????3", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_LABEL1, value = "?????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_LABEL2, value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_LABEL3, value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_CODE1, value = "?????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_CODE2, value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_CODE3, value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_VALUE_NUMBER1, value = "?????????1", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_VALUE_NUMBER2, value = "?????????2", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_VALUE_NUMBER3, value = "?????????3", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_VALUE_STR1, value = "????????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_VALUE_STR2, value = "????????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_VALUE_STR3, value = "????????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_VALUE_INT1, value = "?????????1", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_VALUE_INT2, value = "?????????2", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.LIST_VALUE_INT3, value = "?????????3", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.UID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.IS_CONNECTED, value = "????????????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = MonitorNodeValueLastVOMeta.RECORD_TIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-02-18 07:19:22")
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = MonitorNodeValueLastServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeValueLastServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<MonitorNodeValueLast>> queryPagedList(MonitorNodeValueLastVO sample) {
        Result<PagedList<MonitorNodeValueLast>> result = new Result<>();
        PagedList<MonitorNodeValueLast> list = monitorNodeValueLastService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = MonitorNodeValueLastServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(MonitorNodeValueLastServiceProxy.EXPORT_EXCEL)
    public void exportExcel(MonitorNodeValueLastVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = monitorNodeValueLastService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = MonitorNodeValueLastServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(MonitorNodeValueLastServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = monitorNodeValueLastService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = MonitorNodeValueLastServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(MonitorNodeValueLastServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = monitorNodeValueLastService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
