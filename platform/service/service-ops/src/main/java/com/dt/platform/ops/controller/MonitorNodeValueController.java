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
import com.dt.platform.proxy.ops.MonitorNodeValueServiceProxy;
import com.dt.platform.domain.ops.meta.MonitorNodeValueVOMeta;
import com.dt.platform.domain.ops.MonitorNodeValue;
import com.dt.platform.domain.ops.MonitorNodeValueVO;
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
import com.dt.platform.domain.ops.meta.MonitorNodeValueMeta;
import java.math.BigDecimal;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.IMonitorNodeValueService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-02-20 14:46:23
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("OpsMonitorNodeValueController")
public class MonitorNodeValueController extends SuperController {

    @Autowired
    private IMonitorNodeValueService monitorNodeValueService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "545950298741407744"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.NODE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "2571f8e4-89a4-11ec-bf3e-00163e1b60a7"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.MONITOR_TPL_CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "tpl_host_linux_script"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.RESULT_STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "sucess"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.RESULT_MESSAGE, value = "????????????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.INDICATOR_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "os.arch"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.HOSTNAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.OS_DATETIME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.BOOTTIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.OS_VERION, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.ARCH, value = "??????", required = false, dataTypeClass = String.class, example = "x86_64"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CPU_NUMBER, value = "CPU??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CPU_FREE, value = "CPU??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CPU_SYS, value = "cpuSys", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CPU_USER, value = "cpuUser", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CPU_WAIT, value = "cpuWait", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CPU_IDLE, value = "CPU?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CPU_USED, value = "CPU?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.OS_LOAD, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.OS_LOAD5, value = "????????????5", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.OS_LOAD15, value = "????????????15", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.NETWORK_FLOW_UP, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.NETWORK_FLOW_DOWN, value = "?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.PROCESS_CNT, value = "?????????", required = false, dataTypeClass = Integer.class, example = "0"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.P_MEMORY_SIZE, value = "????????????(M)", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.V_MEMORY_SIZE, value = "????????????(M)", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.P_MEMORY_USED, value = "?????????????????????", required = false, dataTypeClass = Long.class, example = "0"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.V_MEMORY_USED, value = "?????????????????????", required = false, dataTypeClass = Long.class, example = "0"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.INFO, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LABEL1, value = "??????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LABEL2, value = "??????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LABEL3, value = "??????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CODE1, value = "??????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CODE2, value = "??????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CODE3, value = "??????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.VALUE_NUMBER1, value = "??????1", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.VALUE_NUMBER2, value = "??????2", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.VALUE_NUMBER3, value = "??????3", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.VALUE_STR1, value = "?????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.VALUE_STR2, value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.VALUE_STR3, value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.VALUE_INT1, value = "??????1", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.VALUE_INT2, value = "??????2", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.VALUE_INT3, value = "??????3", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_LABEL1, value = "?????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_LABEL2, value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_LABEL3, value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_CODE1, value = "?????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_CODE2, value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_CODE3, value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_VALUE_NUMBER1, value = "?????????1", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_VALUE_NUMBER2, value = "?????????2", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_VALUE_NUMBER3, value = "?????????3", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_VALUE_STR1, value = "????????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_VALUE_STR2, value = "????????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_VALUE_STR3, value = "????????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_VALUE_INT1, value = "?????????1", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_VALUE_INT2, value = "?????????2", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_VALUE_INT3, value = "?????????3", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.UID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.IS_CONNECTED, value = "????????????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.RECORD_TIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-02-15 12:51:34")
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = MonitorNodeValueServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeValueServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(MonitorNodeValueVO monitorNodeValueVO) {
        Result result = monitorNodeValueService.insert(monitorNodeValueVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "545950298741407744")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = MonitorNodeValueServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeValueServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = monitorNodeValueService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = MonitorNodeValueServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeValueServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = monitorNodeValueService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "545950298741407744"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.NODE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "2571f8e4-89a4-11ec-bf3e-00163e1b60a7"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.MONITOR_TPL_CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "tpl_host_linux_script"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.RESULT_STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "sucess"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.RESULT_MESSAGE, value = "????????????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.INDICATOR_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "os.arch"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.HOSTNAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.OS_DATETIME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.BOOTTIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.OS_VERION, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.ARCH, value = "??????", required = false, dataTypeClass = String.class, example = "x86_64"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CPU_NUMBER, value = "CPU??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CPU_FREE, value = "CPU??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CPU_SYS, value = "cpuSys", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CPU_USER, value = "cpuUser", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CPU_WAIT, value = "cpuWait", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CPU_IDLE, value = "CPU?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CPU_USED, value = "CPU?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.OS_LOAD, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.OS_LOAD5, value = "????????????5", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.OS_LOAD15, value = "????????????15", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.NETWORK_FLOW_UP, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.NETWORK_FLOW_DOWN, value = "?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.PROCESS_CNT, value = "?????????", required = false, dataTypeClass = Integer.class, example = "0"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.P_MEMORY_SIZE, value = "????????????(M)", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.V_MEMORY_SIZE, value = "????????????(M)", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.P_MEMORY_USED, value = "?????????????????????", required = false, dataTypeClass = Long.class, example = "0"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.V_MEMORY_USED, value = "?????????????????????", required = false, dataTypeClass = Long.class, example = "0"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.INFO, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LABEL1, value = "??????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LABEL2, value = "??????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LABEL3, value = "??????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CODE1, value = "??????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CODE2, value = "??????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CODE3, value = "??????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.VALUE_NUMBER1, value = "??????1", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.VALUE_NUMBER2, value = "??????2", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.VALUE_NUMBER3, value = "??????3", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.VALUE_STR1, value = "?????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.VALUE_STR2, value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.VALUE_STR3, value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.VALUE_INT1, value = "??????1", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.VALUE_INT2, value = "??????2", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.VALUE_INT3, value = "??????3", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_LABEL1, value = "?????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_LABEL2, value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_LABEL3, value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_CODE1, value = "?????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_CODE2, value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_CODE3, value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_VALUE_NUMBER1, value = "?????????1", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_VALUE_NUMBER2, value = "?????????2", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_VALUE_NUMBER3, value = "?????????3", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_VALUE_STR1, value = "????????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_VALUE_STR2, value = "????????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_VALUE_STR3, value = "????????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_VALUE_INT1, value = "?????????1", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_VALUE_INT2, value = "?????????2", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_VALUE_INT3, value = "?????????3", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.UID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.IS_CONNECTED, value = "????????????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.RECORD_TIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-02-15 12:51:34")
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { MonitorNodeValueVOMeta.PAGE_INDEX, MonitorNodeValueVOMeta.PAGE_SIZE, MonitorNodeValueVOMeta.SEARCH_FIELD, MonitorNodeValueVOMeta.FUZZY_FIELD, MonitorNodeValueVOMeta.SEARCH_VALUE, MonitorNodeValueVOMeta.DIRTY_FIELDS, MonitorNodeValueVOMeta.SORT_FIELD, MonitorNodeValueVOMeta.SORT_TYPE, MonitorNodeValueVOMeta.IDS })
    @SentinelResource(value = MonitorNodeValueServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeValueServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(MonitorNodeValueVO monitorNodeValueVO) {
        Result result = monitorNodeValueService.update(monitorNodeValueVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "545950298741407744"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.NODE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "2571f8e4-89a4-11ec-bf3e-00163e1b60a7"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.MONITOR_TPL_CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "tpl_host_linux_script"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.RESULT_STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "sucess"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.RESULT_MESSAGE, value = "????????????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.INDICATOR_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "os.arch"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.HOSTNAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.OS_DATETIME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.BOOTTIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.OS_VERION, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.ARCH, value = "??????", required = false, dataTypeClass = String.class, example = "x86_64"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CPU_NUMBER, value = "CPU??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CPU_FREE, value = "CPU??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CPU_SYS, value = "cpuSys", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CPU_USER, value = "cpuUser", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CPU_WAIT, value = "cpuWait", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CPU_IDLE, value = "CPU?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CPU_USED, value = "CPU?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.OS_LOAD, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.OS_LOAD5, value = "????????????5", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.OS_LOAD15, value = "????????????15", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.NETWORK_FLOW_UP, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.NETWORK_FLOW_DOWN, value = "?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.PROCESS_CNT, value = "?????????", required = false, dataTypeClass = Integer.class, example = "0"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.P_MEMORY_SIZE, value = "????????????(M)", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.V_MEMORY_SIZE, value = "????????????(M)", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.P_MEMORY_USED, value = "?????????????????????", required = false, dataTypeClass = Long.class, example = "0"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.V_MEMORY_USED, value = "?????????????????????", required = false, dataTypeClass = Long.class, example = "0"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.INFO, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LABEL1, value = "??????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LABEL2, value = "??????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LABEL3, value = "??????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CODE1, value = "??????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CODE2, value = "??????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CODE3, value = "??????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.VALUE_NUMBER1, value = "??????1", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.VALUE_NUMBER2, value = "??????2", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.VALUE_NUMBER3, value = "??????3", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.VALUE_STR1, value = "?????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.VALUE_STR2, value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.VALUE_STR3, value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.VALUE_INT1, value = "??????1", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.VALUE_INT2, value = "??????2", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.VALUE_INT3, value = "??????3", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_LABEL1, value = "?????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_LABEL2, value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_LABEL3, value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_CODE1, value = "?????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_CODE2, value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_CODE3, value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_VALUE_NUMBER1, value = "?????????1", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_VALUE_NUMBER2, value = "?????????2", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_VALUE_NUMBER3, value = "?????????3", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_VALUE_STR1, value = "????????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_VALUE_STR2, value = "????????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_VALUE_STR3, value = "????????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_VALUE_INT1, value = "?????????1", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_VALUE_INT2, value = "?????????2", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_VALUE_INT3, value = "?????????3", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.UID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.IS_CONNECTED, value = "????????????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.RECORD_TIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-02-15 12:51:34")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { MonitorNodeValueVOMeta.PAGE_INDEX, MonitorNodeValueVOMeta.PAGE_SIZE, MonitorNodeValueVOMeta.SEARCH_FIELD, MonitorNodeValueVOMeta.FUZZY_FIELD, MonitorNodeValueVOMeta.SEARCH_VALUE, MonitorNodeValueVOMeta.DIRTY_FIELDS, MonitorNodeValueVOMeta.SORT_FIELD, MonitorNodeValueVOMeta.SORT_TYPE, MonitorNodeValueVOMeta.IDS })
    @SentinelResource(value = MonitorNodeValueServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeValueServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(MonitorNodeValueVO monitorNodeValueVO) {
        Result result = monitorNodeValueService.save(monitorNodeValueVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = MonitorNodeValueServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeValueServiceProxy.GET_BY_ID)
    public Result<MonitorNodeValue> getById(String id) {
        Result<MonitorNodeValue> result = new Result<>();
        MonitorNodeValue monitorNodeValue = monitorNodeValueService.getById(id);
        result.success(true).data(monitorNodeValue);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = MonitorNodeValueServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeValueServiceProxy.GET_BY_IDS)
    public Result<List<MonitorNodeValue>> getByIds(List<String> ids) {
        Result<List<MonitorNodeValue>> result = new Result<>();
        List<MonitorNodeValue> list = monitorNodeValueService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "545950298741407744"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.NODE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "2571f8e4-89a4-11ec-bf3e-00163e1b60a7"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.MONITOR_TPL_CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "tpl_host_linux_script"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.RESULT_STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "sucess"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.RESULT_MESSAGE, value = "????????????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.INDICATOR_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "os.arch"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.HOSTNAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.OS_DATETIME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.BOOTTIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.OS_VERION, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.ARCH, value = "??????", required = false, dataTypeClass = String.class, example = "x86_64"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CPU_NUMBER, value = "CPU??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CPU_FREE, value = "CPU??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CPU_SYS, value = "cpuSys", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CPU_USER, value = "cpuUser", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CPU_WAIT, value = "cpuWait", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CPU_IDLE, value = "CPU?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CPU_USED, value = "CPU?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.OS_LOAD, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.OS_LOAD5, value = "????????????5", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.OS_LOAD15, value = "????????????15", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.NETWORK_FLOW_UP, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.NETWORK_FLOW_DOWN, value = "?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.PROCESS_CNT, value = "?????????", required = false, dataTypeClass = Integer.class, example = "0"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.P_MEMORY_SIZE, value = "????????????(M)", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.V_MEMORY_SIZE, value = "????????????(M)", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.P_MEMORY_USED, value = "?????????????????????", required = false, dataTypeClass = Long.class, example = "0"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.V_MEMORY_USED, value = "?????????????????????", required = false, dataTypeClass = Long.class, example = "0"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.INFO, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LABEL1, value = "??????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LABEL2, value = "??????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LABEL3, value = "??????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CODE1, value = "??????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CODE2, value = "??????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CODE3, value = "??????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.VALUE_NUMBER1, value = "??????1", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.VALUE_NUMBER2, value = "??????2", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.VALUE_NUMBER3, value = "??????3", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.VALUE_STR1, value = "?????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.VALUE_STR2, value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.VALUE_STR3, value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.VALUE_INT1, value = "??????1", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.VALUE_INT2, value = "??????2", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.VALUE_INT3, value = "??????3", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_LABEL1, value = "?????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_LABEL2, value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_LABEL3, value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_CODE1, value = "?????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_CODE2, value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_CODE3, value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_VALUE_NUMBER1, value = "?????????1", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_VALUE_NUMBER2, value = "?????????2", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_VALUE_NUMBER3, value = "?????????3", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_VALUE_STR1, value = "????????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_VALUE_STR2, value = "????????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_VALUE_STR3, value = "????????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_VALUE_INT1, value = "?????????1", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_VALUE_INT2, value = "?????????2", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_VALUE_INT3, value = "?????????3", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.UID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.IS_CONNECTED, value = "????????????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.RECORD_TIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-02-15 12:51:34")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { MonitorNodeValueVOMeta.PAGE_INDEX, MonitorNodeValueVOMeta.PAGE_SIZE })
    @SentinelResource(value = MonitorNodeValueServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeValueServiceProxy.QUERY_LIST)
    public Result<List<MonitorNodeValue>> queryList(MonitorNodeValueVO sample) {
        Result<List<MonitorNodeValue>> result = new Result<>();
        List<MonitorNodeValue> list = monitorNodeValueService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "545950298741407744"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.NODE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "2571f8e4-89a4-11ec-bf3e-00163e1b60a7"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.MONITOR_TPL_CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "tpl_host_linux_script"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.RESULT_STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "sucess"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.RESULT_MESSAGE, value = "????????????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.INDICATOR_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "os.arch"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.HOSTNAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.OS_DATETIME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.BOOTTIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.OS_VERION, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.ARCH, value = "??????", required = false, dataTypeClass = String.class, example = "x86_64"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CPU_NUMBER, value = "CPU??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CPU_FREE, value = "CPU??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CPU_SYS, value = "cpuSys", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CPU_USER, value = "cpuUser", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CPU_WAIT, value = "cpuWait", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CPU_IDLE, value = "CPU?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CPU_USED, value = "CPU?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.OS_LOAD, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.OS_LOAD5, value = "????????????5", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.OS_LOAD15, value = "????????????15", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.NETWORK_FLOW_UP, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.NETWORK_FLOW_DOWN, value = "?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.PROCESS_CNT, value = "?????????", required = false, dataTypeClass = Integer.class, example = "0"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.P_MEMORY_SIZE, value = "????????????(M)", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.V_MEMORY_SIZE, value = "????????????(M)", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.P_MEMORY_USED, value = "?????????????????????", required = false, dataTypeClass = Long.class, example = "0"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.V_MEMORY_USED, value = "?????????????????????", required = false, dataTypeClass = Long.class, example = "0"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.INFO, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LABEL1, value = "??????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LABEL2, value = "??????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LABEL3, value = "??????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CODE1, value = "??????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CODE2, value = "??????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.CODE3, value = "??????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.VALUE_NUMBER1, value = "??????1", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.VALUE_NUMBER2, value = "??????2", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.VALUE_NUMBER3, value = "??????3", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.VALUE_STR1, value = "?????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.VALUE_STR2, value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.VALUE_STR3, value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.VALUE_INT1, value = "??????1", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.VALUE_INT2, value = "??????2", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.VALUE_INT3, value = "??????3", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_LABEL1, value = "?????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_LABEL2, value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_LABEL3, value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_CODE1, value = "?????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_CODE2, value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_CODE3, value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_VALUE_NUMBER1, value = "?????????1", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_VALUE_NUMBER2, value = "?????????2", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_VALUE_NUMBER3, value = "?????????3", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_VALUE_STR1, value = "????????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_VALUE_STR2, value = "????????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_VALUE_STR3, value = "????????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_VALUE_INT1, value = "?????????1", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_VALUE_INT2, value = "?????????2", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.LIST_VALUE_INT3, value = "?????????3", required = false, dataTypeClass = Long.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.UID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.IS_CONNECTED, value = "????????????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = MonitorNodeValueVOMeta.RECORD_TIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-02-15 12:51:34")
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = MonitorNodeValueServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeValueServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<MonitorNodeValue>> queryPagedList(MonitorNodeValueVO sample) {
        Result<PagedList<MonitorNodeValue>> result = new Result<>();
        PagedList<MonitorNodeValue> list = monitorNodeValueService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = MonitorNodeValueServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(MonitorNodeValueServiceProxy.EXPORT_EXCEL)
    public void exportExcel(MonitorNodeValueVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = monitorNodeValueService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = MonitorNodeValueServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(MonitorNodeValueServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = monitorNodeValueService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = MonitorNodeValueServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(MonitorNodeValueServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = monitorNodeValueService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
