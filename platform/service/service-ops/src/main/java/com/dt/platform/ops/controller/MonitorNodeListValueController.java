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
import com.dt.platform.proxy.ops.MonitorNodeListValueServiceProxy;
import com.dt.platform.domain.ops.meta.MonitorNodeListValueVOMeta;
import com.dt.platform.domain.ops.MonitorNodeListValue;
import com.dt.platform.domain.ops.MonitorNodeListValueVO;
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
import com.dt.platform.domain.ops.meta.MonitorNodeListValueMeta;
import java.math.BigDecimal;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.IMonitorNodeListValueService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ?????????????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-02-08 13:14:53
 */
@Api(tags = "??????????????????")
@ApiSort(0)
@RestController("OpsMonitorNodeListValueController")
public class MonitorNodeListValueController extends SuperController {

    @Autowired
    private IMonitorNodeListValueService monitorNodeListValueService;

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.NODE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.INDICATOR_CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.MONITOR_TPL_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.VALUE_TYPE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.LABEL1, value = "??????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.LABEL2, value = "??????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.LABEL3, value = "??????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.CODE1, value = "??????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.CODE2, value = "??????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.VALUE_NUMBER1, value = "??????1", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.VALUE_NUMBER2, value = "??????2", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.VALUE_NUMBER3, value = "??????3", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.VALUE_STR1, value = "?????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.VALUE_STR2, value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.VALUE_STR3, value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.UID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.INFO, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.RECORD_TIME, value = "????????????", required = false, dataTypeClass = Date.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = MonitorNodeListValueServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeListValueServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(MonitorNodeListValueVO monitorNodeListValueVO) {
        Result result = monitorNodeListValueService.insert(monitorNodeListValueVO, false);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = MonitorNodeListValueServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeListValueServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = monitorNodeListValueService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ?????????????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "??????????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = MonitorNodeListValueServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeListValueServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = monitorNodeListValueService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.NODE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.INDICATOR_CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.MONITOR_TPL_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.VALUE_TYPE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.LABEL1, value = "??????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.LABEL2, value = "??????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.LABEL3, value = "??????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.CODE1, value = "??????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.CODE2, value = "??????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.VALUE_NUMBER1, value = "??????1", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.VALUE_NUMBER2, value = "??????2", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.VALUE_NUMBER3, value = "??????3", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.VALUE_STR1, value = "?????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.VALUE_STR2, value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.VALUE_STR3, value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.UID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.INFO, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.RECORD_TIME, value = "????????????", required = false, dataTypeClass = Date.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { MonitorNodeListValueVOMeta.PAGE_INDEX, MonitorNodeListValueVOMeta.PAGE_SIZE, MonitorNodeListValueVOMeta.SEARCH_FIELD, MonitorNodeListValueVOMeta.FUZZY_FIELD, MonitorNodeListValueVOMeta.SEARCH_VALUE, MonitorNodeListValueVOMeta.DIRTY_FIELDS, MonitorNodeListValueVOMeta.SORT_FIELD, MonitorNodeListValueVOMeta.SORT_TYPE, MonitorNodeListValueVOMeta.IDS })
    @SentinelResource(value = MonitorNodeListValueServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeListValueServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(MonitorNodeListValueVO monitorNodeListValueVO) {
        Result result = monitorNodeListValueService.update(monitorNodeListValueVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.NODE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.INDICATOR_CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.MONITOR_TPL_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.VALUE_TYPE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.LABEL1, value = "??????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.LABEL2, value = "??????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.LABEL3, value = "??????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.CODE1, value = "??????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.CODE2, value = "??????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.VALUE_NUMBER1, value = "??????1", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.VALUE_NUMBER2, value = "??????2", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.VALUE_NUMBER3, value = "??????3", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.VALUE_STR1, value = "?????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.VALUE_STR2, value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.VALUE_STR3, value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.UID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.INFO, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.RECORD_TIME, value = "????????????", required = false, dataTypeClass = Date.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { MonitorNodeListValueVOMeta.PAGE_INDEX, MonitorNodeListValueVOMeta.PAGE_SIZE, MonitorNodeListValueVOMeta.SEARCH_FIELD, MonitorNodeListValueVOMeta.FUZZY_FIELD, MonitorNodeListValueVOMeta.SEARCH_VALUE, MonitorNodeListValueVOMeta.DIRTY_FIELDS, MonitorNodeListValueVOMeta.SORT_FIELD, MonitorNodeListValueVOMeta.SORT_TYPE, MonitorNodeListValueVOMeta.IDS })
    @SentinelResource(value = MonitorNodeListValueServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeListValueServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(MonitorNodeListValueVO monitorNodeListValueVO) {
        Result result = monitorNodeListValueService.save(monitorNodeListValueVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = MonitorNodeListValueServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeListValueServiceProxy.GET_BY_ID)
    public Result<MonitorNodeListValue> getById(String id) {
        Result<MonitorNodeListValue> result = new Result<>();
        MonitorNodeListValue monitorNodeListValue = monitorNodeListValueService.getById(id);
        result.success(true).data(monitorNodeListValue);
        return result;
    }

    /**
     * ?????????????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "??????????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = MonitorNodeListValueServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeListValueServiceProxy.GET_BY_IDS)
    public Result<List<MonitorNodeListValue>> getByIds(List<String> ids) {
        Result<List<MonitorNodeListValue>> result = new Result<>();
        List<MonitorNodeListValue> list = monitorNodeListValueService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.NODE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.INDICATOR_CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.MONITOR_TPL_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.VALUE_TYPE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.LABEL1, value = "??????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.LABEL2, value = "??????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.LABEL3, value = "??????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.CODE1, value = "??????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.CODE2, value = "??????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.VALUE_NUMBER1, value = "??????1", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.VALUE_NUMBER2, value = "??????2", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.VALUE_NUMBER3, value = "??????3", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.VALUE_STR1, value = "?????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.VALUE_STR2, value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.VALUE_STR3, value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.UID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.INFO, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.RECORD_TIME, value = "????????????", required = false, dataTypeClass = Date.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { MonitorNodeListValueVOMeta.PAGE_INDEX, MonitorNodeListValueVOMeta.PAGE_SIZE })
    @SentinelResource(value = MonitorNodeListValueServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeListValueServiceProxy.QUERY_LIST)
    public Result<List<MonitorNodeListValue>> queryList(MonitorNodeListValueVO sample) {
        Result<List<MonitorNodeListValue>> result = new Result<>();
        List<MonitorNodeListValue> list = monitorNodeListValueService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????????????????
     */
    @ApiOperation(value = "??????????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.NODE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.INDICATOR_CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.MONITOR_TPL_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.VALUE_TYPE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.LABEL1, value = "??????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.LABEL2, value = "??????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.LABEL3, value = "??????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.CODE1, value = "??????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.CODE2, value = "??????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.VALUE_NUMBER1, value = "??????1", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.VALUE_NUMBER2, value = "??????2", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.VALUE_NUMBER3, value = "??????3", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.VALUE_STR1, value = "?????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.VALUE_STR2, value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.VALUE_STR3, value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.UID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.INFO, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorNodeListValueVOMeta.RECORD_TIME, value = "????????????", required = false, dataTypeClass = Date.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = MonitorNodeListValueServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeListValueServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<MonitorNodeListValue>> queryPagedList(MonitorNodeListValueVO sample) {
        Result<PagedList<MonitorNodeListValue>> result = new Result<>();
        PagedList<MonitorNodeListValue> list = monitorNodeListValueService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = MonitorNodeListValueServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(MonitorNodeListValueServiceProxy.EXPORT_EXCEL)
    public void exportExcel(MonitorNodeListValueVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = monitorNodeListValueService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = MonitorNodeListValueServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(MonitorNodeListValueServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = monitorNodeListValueService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = MonitorNodeListValueServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(MonitorNodeListValueServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = monitorNodeListValueService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
