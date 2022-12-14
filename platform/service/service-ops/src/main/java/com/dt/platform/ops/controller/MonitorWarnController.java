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
import com.dt.platform.proxy.ops.MonitorWarnServiceProxy;
import com.dt.platform.domain.ops.meta.MonitorWarnVOMeta;
import com.dt.platform.domain.ops.MonitorWarn;
import com.dt.platform.domain.ops.MonitorWarnVO;
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
import com.dt.platform.domain.ops.meta.MonitorWarnMeta;
import com.dt.platform.domain.ops.MonitorTpl;
import com.dt.platform.domain.ops.MonitorTplIndicator;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.IMonitorWarnService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-02-08 13:14:57
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("OpsMonitorWarnController")
public class MonitorWarnController extends SuperController {

    @Autowired
    private IMonitorWarnService monitorWarnService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorWarnVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.NODE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.NODE_VALUE_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.MONITOR_TPL_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.INDICATOR_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.INDICATOR_NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.WARN_LEVEL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.HANDLED_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.WARN_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.CONTENT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = MonitorWarnServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorWarnServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(MonitorWarnVO monitorWarnVO) {
        Result result = monitorWarnService.insert(monitorWarnVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorWarnVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = MonitorWarnServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorWarnServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = monitorWarnService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorWarnVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = MonitorWarnServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorWarnServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = monitorWarnService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorWarnVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.NODE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.NODE_VALUE_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.MONITOR_TPL_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.INDICATOR_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.INDICATOR_NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.WARN_LEVEL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.HANDLED_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.WARN_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.CONTENT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { MonitorWarnVOMeta.PAGE_INDEX, MonitorWarnVOMeta.PAGE_SIZE, MonitorWarnVOMeta.SEARCH_FIELD, MonitorWarnVOMeta.FUZZY_FIELD, MonitorWarnVOMeta.SEARCH_VALUE, MonitorWarnVOMeta.DIRTY_FIELDS, MonitorWarnVOMeta.SORT_FIELD, MonitorWarnVOMeta.SORT_TYPE, MonitorWarnVOMeta.IDS })
    @SentinelResource(value = MonitorWarnServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorWarnServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(MonitorWarnVO monitorWarnVO) {
        Result result = monitorWarnService.update(monitorWarnVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorWarnVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.NODE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.NODE_VALUE_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.MONITOR_TPL_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.INDICATOR_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.INDICATOR_NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.WARN_LEVEL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.HANDLED_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.WARN_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.CONTENT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { MonitorWarnVOMeta.PAGE_INDEX, MonitorWarnVOMeta.PAGE_SIZE, MonitorWarnVOMeta.SEARCH_FIELD, MonitorWarnVOMeta.FUZZY_FIELD, MonitorWarnVOMeta.SEARCH_VALUE, MonitorWarnVOMeta.DIRTY_FIELDS, MonitorWarnVOMeta.SORT_FIELD, MonitorWarnVOMeta.SORT_TYPE, MonitorWarnVOMeta.IDS })
    @SentinelResource(value = MonitorWarnServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorWarnServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(MonitorWarnVO monitorWarnVO) {
        Result result = monitorWarnService.save(monitorWarnVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorWarnVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = MonitorWarnServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorWarnServiceProxy.GET_BY_ID)
    public Result<MonitorWarn> getById(String id) {
        Result<MonitorWarn> result = new Result<>();
        MonitorWarn monitorWarn = monitorWarnService.getById(id);
        result.success(true).data(monitorWarn);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorWarnVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = MonitorWarnServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorWarnServiceProxy.GET_BY_IDS)
    public Result<List<MonitorWarn>> getByIds(List<String> ids) {
        Result<List<MonitorWarn>> result = new Result<>();
        List<MonitorWarn> list = monitorWarnService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorWarnVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.NODE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.NODE_VALUE_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.MONITOR_TPL_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.INDICATOR_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.INDICATOR_NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.WARN_LEVEL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.HANDLED_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.WARN_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.CONTENT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { MonitorWarnVOMeta.PAGE_INDEX, MonitorWarnVOMeta.PAGE_SIZE })
    @SentinelResource(value = MonitorWarnServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorWarnServiceProxy.QUERY_LIST)
    public Result<List<MonitorWarn>> queryList(MonitorWarnVO sample) {
        Result<List<MonitorWarn>> result = new Result<>();
        List<MonitorWarn> list = monitorWarnService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorWarnVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.NODE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.NODE_VALUE_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.MONITOR_TPL_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.INDICATOR_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.INDICATOR_NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.WARN_LEVEL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.HANDLED_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.WARN_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.CONTENT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorWarnVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = MonitorWarnServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorWarnServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<MonitorWarn>> queryPagedList(MonitorWarnVO sample) {
        Result<PagedList<MonitorWarn>> result = new Result<>();
        PagedList<MonitorWarn> list = monitorWarnService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = MonitorWarnServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(MonitorWarnServiceProxy.EXPORT_EXCEL)
    public void exportExcel(MonitorWarnVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = monitorWarnService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = MonitorWarnServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(MonitorWarnServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = monitorWarnService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = MonitorWarnServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(MonitorWarnServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = monitorWarnService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
