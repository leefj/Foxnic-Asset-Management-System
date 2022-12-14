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
import com.dt.platform.proxy.ops.MonitorTplIndicatorServiceProxy;
import com.dt.platform.domain.ops.meta.MonitorTplIndicatorVOMeta;
import com.dt.platform.domain.ops.MonitorTplIndicator;
import com.dt.platform.domain.ops.MonitorTplIndicatorVO;
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
import com.dt.platform.domain.ops.meta.MonitorTplIndicatorMeta;
import com.dt.platform.domain.ops.MonitorTpl;
import com.dt.platform.domain.ops.MonitorTplIndicatorType;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.IMonitorTplIndicatorService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-07-14 16:33:31
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("OpsMonitorTplIndicatorController")
public class MonitorTplIndicatorController extends SuperController {

    @Autowired
    private IMonitorTplIndicatorService monitorTplIndicatorService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "100"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "os.load"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.MONITOR_TPL_CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "tpl_host_linux_script"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.MONITOR_METHOD, value = "????????????", required = false, dataTypeClass = String.class, example = "script"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.INDICATOR_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "system"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.VALUE_COLUMN_ROWS, value = "?????????", required = false, dataTypeClass = String.class, example = "single"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.VALUE_COLUMN_COLS, value = "?????????", required = false, dataTypeClass = String.class, example = "single"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.VALUE_COLUMN_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "number"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.VALUE_COLUMN, value = "????????????", required = false, dataTypeClass = String.class, example = "os_load"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.VALUE_COLUMN_MAP, value = "??????????????????", required = false, dataTypeClass = String.class, example = "os_load"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.VALUE_COLUMN_NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.TIME_OUT, value = "??????(???)", required = false, dataTypeClass = Integer.class, example = "15"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.INTERVAL_TIME, value = "????????????(??????", required = false, dataTypeClass = Integer.class, example = "180"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.DATA_KEEP_DAY, value = "??????????????????", required = false, dataTypeClass = Integer.class, example = "365"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.COMMAND, value = "??????", required = false, dataTypeClass = String.class, example = "uptime|awk -F \":\" '{print $NF}'|awk -F \",\" '{print $1}'"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.COMMAND_VALUE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.INDICATOR_VARIABLE, value = "??????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.SNMP_OID, value = "snmp?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.LABEL, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.ITEM_SORT, value = "??????", required = false, dataTypeClass = Integer.class, example = "800"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "????????????")
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = MonitorTplIndicatorServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorTplIndicatorServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(MonitorTplIndicatorVO monitorTplIndicatorVO) {
        Result result = monitorTplIndicatorService.insert(monitorTplIndicatorVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "100")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = MonitorTplIndicatorServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorTplIndicatorServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  monitorTplIndicatorService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = monitorTplIndicatorService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = MonitorTplIndicatorServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorTplIndicatorServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = monitorTplIndicatorService.hasRefers(ids);
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
            Result result = monitorTplIndicatorService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = monitorTplIndicatorService.deleteByIdsLogical(canDeleteIds);
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
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "100"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "os.load"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.MONITOR_TPL_CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "tpl_host_linux_script"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.MONITOR_METHOD, value = "????????????", required = false, dataTypeClass = String.class, example = "script"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.INDICATOR_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "system"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.VALUE_COLUMN_ROWS, value = "?????????", required = false, dataTypeClass = String.class, example = "single"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.VALUE_COLUMN_COLS, value = "?????????", required = false, dataTypeClass = String.class, example = "single"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.VALUE_COLUMN_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "number"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.VALUE_COLUMN, value = "????????????", required = false, dataTypeClass = String.class, example = "os_load"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.VALUE_COLUMN_MAP, value = "??????????????????", required = false, dataTypeClass = String.class, example = "os_load"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.VALUE_COLUMN_NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.TIME_OUT, value = "??????(???)", required = false, dataTypeClass = Integer.class, example = "15"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.INTERVAL_TIME, value = "????????????(??????", required = false, dataTypeClass = Integer.class, example = "180"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.DATA_KEEP_DAY, value = "??????????????????", required = false, dataTypeClass = Integer.class, example = "365"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.COMMAND, value = "??????", required = false, dataTypeClass = String.class, example = "uptime|awk -F \":\" '{print $NF}'|awk -F \",\" '{print $1}'"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.COMMAND_VALUE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.INDICATOR_VARIABLE, value = "??????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.SNMP_OID, value = "snmp?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.LABEL, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.ITEM_SORT, value = "??????", required = false, dataTypeClass = Integer.class, example = "800"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "????????????")
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { MonitorTplIndicatorVOMeta.PAGE_INDEX, MonitorTplIndicatorVOMeta.PAGE_SIZE, MonitorTplIndicatorVOMeta.SEARCH_FIELD, MonitorTplIndicatorVOMeta.FUZZY_FIELD, MonitorTplIndicatorVOMeta.SEARCH_VALUE, MonitorTplIndicatorVOMeta.DIRTY_FIELDS, MonitorTplIndicatorVOMeta.SORT_FIELD, MonitorTplIndicatorVOMeta.SORT_TYPE, MonitorTplIndicatorVOMeta.IDS })
    @SentinelResource(value = MonitorTplIndicatorServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorTplIndicatorServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(MonitorTplIndicatorVO monitorTplIndicatorVO) {
        Result result = monitorTplIndicatorService.update(monitorTplIndicatorVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "100"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "os.load"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.MONITOR_TPL_CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "tpl_host_linux_script"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.MONITOR_METHOD, value = "????????????", required = false, dataTypeClass = String.class, example = "script"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.INDICATOR_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "system"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.VALUE_COLUMN_ROWS, value = "?????????", required = false, dataTypeClass = String.class, example = "single"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.VALUE_COLUMN_COLS, value = "?????????", required = false, dataTypeClass = String.class, example = "single"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.VALUE_COLUMN_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "number"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.VALUE_COLUMN, value = "????????????", required = false, dataTypeClass = String.class, example = "os_load"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.VALUE_COLUMN_MAP, value = "??????????????????", required = false, dataTypeClass = String.class, example = "os_load"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.VALUE_COLUMN_NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.TIME_OUT, value = "??????(???)", required = false, dataTypeClass = Integer.class, example = "15"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.INTERVAL_TIME, value = "????????????(??????", required = false, dataTypeClass = Integer.class, example = "180"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.DATA_KEEP_DAY, value = "??????????????????", required = false, dataTypeClass = Integer.class, example = "365"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.COMMAND, value = "??????", required = false, dataTypeClass = String.class, example = "uptime|awk -F \":\" '{print $NF}'|awk -F \",\" '{print $1}'"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.COMMAND_VALUE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.INDICATOR_VARIABLE, value = "??????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.SNMP_OID, value = "snmp?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.LABEL, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.ITEM_SORT, value = "??????", required = false, dataTypeClass = Integer.class, example = "800"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "????????????")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { MonitorTplIndicatorVOMeta.PAGE_INDEX, MonitorTplIndicatorVOMeta.PAGE_SIZE, MonitorTplIndicatorVOMeta.SEARCH_FIELD, MonitorTplIndicatorVOMeta.FUZZY_FIELD, MonitorTplIndicatorVOMeta.SEARCH_VALUE, MonitorTplIndicatorVOMeta.DIRTY_FIELDS, MonitorTplIndicatorVOMeta.SORT_FIELD, MonitorTplIndicatorVOMeta.SORT_TYPE, MonitorTplIndicatorVOMeta.IDS })
    @SentinelResource(value = MonitorTplIndicatorServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorTplIndicatorServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(MonitorTplIndicatorVO monitorTplIndicatorVO) {
        Result result = monitorTplIndicatorService.save(monitorTplIndicatorVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = MonitorTplIndicatorServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorTplIndicatorServiceProxy.GET_BY_ID)
    public Result<MonitorTplIndicator> getById(String id) {
        Result<MonitorTplIndicator> result = new Result<>();
        MonitorTplIndicator monitorTplIndicator = monitorTplIndicatorService.getById(id);
        // join ???????????????
        monitorTplIndicatorService.dao().fill(monitorTplIndicator).with(MonitorTplIndicatorMeta.TPL).with(MonitorTplIndicatorMeta.MONITOR_INDICATOR_TYPE).execute();
        result.success(true).data(monitorTplIndicator);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = MonitorTplIndicatorServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorTplIndicatorServiceProxy.GET_BY_IDS)
    public Result<List<MonitorTplIndicator>> getByIds(List<String> ids) {
        Result<List<MonitorTplIndicator>> result = new Result<>();
        List<MonitorTplIndicator> list = monitorTplIndicatorService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "100"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "os.load"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.MONITOR_TPL_CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "tpl_host_linux_script"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.MONITOR_METHOD, value = "????????????", required = false, dataTypeClass = String.class, example = "script"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.INDICATOR_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "system"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.VALUE_COLUMN_ROWS, value = "?????????", required = false, dataTypeClass = String.class, example = "single"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.VALUE_COLUMN_COLS, value = "?????????", required = false, dataTypeClass = String.class, example = "single"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.VALUE_COLUMN_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "number"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.VALUE_COLUMN, value = "????????????", required = false, dataTypeClass = String.class, example = "os_load"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.VALUE_COLUMN_MAP, value = "??????????????????", required = false, dataTypeClass = String.class, example = "os_load"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.VALUE_COLUMN_NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.TIME_OUT, value = "??????(???)", required = false, dataTypeClass = Integer.class, example = "15"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.INTERVAL_TIME, value = "????????????(??????", required = false, dataTypeClass = Integer.class, example = "180"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.DATA_KEEP_DAY, value = "??????????????????", required = false, dataTypeClass = Integer.class, example = "365"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.COMMAND, value = "??????", required = false, dataTypeClass = String.class, example = "uptime|awk -F \":\" '{print $NF}'|awk -F \",\" '{print $1}'"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.COMMAND_VALUE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.INDICATOR_VARIABLE, value = "??????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.SNMP_OID, value = "snmp?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.LABEL, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.ITEM_SORT, value = "??????", required = false, dataTypeClass = Integer.class, example = "800"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "????????????")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { MonitorTplIndicatorVOMeta.PAGE_INDEX, MonitorTplIndicatorVOMeta.PAGE_SIZE })
    @SentinelResource(value = MonitorTplIndicatorServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorTplIndicatorServiceProxy.QUERY_LIST)
    public Result<List<MonitorTplIndicator>> queryList(MonitorTplIndicatorVO sample) {
        Result<List<MonitorTplIndicator>> result = new Result<>();
        List<MonitorTplIndicator> list = monitorTplIndicatorService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "100"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "os.load"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.MONITOR_TPL_CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "tpl_host_linux_script"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.MONITOR_METHOD, value = "????????????", required = false, dataTypeClass = String.class, example = "script"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.INDICATOR_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "system"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.VALUE_COLUMN_ROWS, value = "?????????", required = false, dataTypeClass = String.class, example = "single"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.VALUE_COLUMN_COLS, value = "?????????", required = false, dataTypeClass = String.class, example = "single"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.VALUE_COLUMN_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "number"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.VALUE_COLUMN, value = "????????????", required = false, dataTypeClass = String.class, example = "os_load"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.VALUE_COLUMN_MAP, value = "??????????????????", required = false, dataTypeClass = String.class, example = "os_load"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.VALUE_COLUMN_NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.TIME_OUT, value = "??????(???)", required = false, dataTypeClass = Integer.class, example = "15"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.INTERVAL_TIME, value = "????????????(??????", required = false, dataTypeClass = Integer.class, example = "180"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.DATA_KEEP_DAY, value = "??????????????????", required = false, dataTypeClass = Integer.class, example = "365"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.COMMAND, value = "??????", required = false, dataTypeClass = String.class, example = "uptime|awk -F \":\" '{print $NF}'|awk -F \",\" '{print $1}'"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.COMMAND_VALUE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.INDICATOR_VARIABLE, value = "??????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.SNMP_OID, value = "snmp?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.LABEL, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.ITEM_SORT, value = "??????", required = false, dataTypeClass = Integer.class, example = "800"),
		@ApiImplicitParam(name = MonitorTplIndicatorVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "????????????")
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = MonitorTplIndicatorServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorTplIndicatorServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<MonitorTplIndicator>> queryPagedList(MonitorTplIndicatorVO sample) {
        Result<PagedList<MonitorTplIndicator>> result = new Result<>();
        PagedList<MonitorTplIndicator> list = monitorTplIndicatorService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        monitorTplIndicatorService.dao().fill(list).with(MonitorTplIndicatorMeta.TPL).with(MonitorTplIndicatorMeta.MONITOR_INDICATOR_TYPE).execute();
        result.success(true).data(list);
        return result;
    }
}
