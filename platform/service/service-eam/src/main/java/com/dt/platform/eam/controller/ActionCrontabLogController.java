package com.dt.platform.eam.controller;

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
import com.dt.platform.proxy.eam.ActionCrontabLogServiceProxy;
import com.dt.platform.domain.eam.meta.ActionCrontabLogVOMeta;
import com.dt.platform.domain.eam.ActionCrontabLog;
import com.dt.platform.domain.eam.ActionCrontabLogVO;
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
import com.dt.platform.domain.eam.meta.ActionCrontabLogMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IActionCrontabLogService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ?????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-06-03 20:31:05
 */
@Api(tags = "??????")
@ApiSort(0)
@RestController("EamActionCrontabLogController")
public class ActionCrontabLogController extends SuperController {

    @Autowired
    private IActionCrontabLogService actionCrontabLogService;

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ActionCrontabLogVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = ActionCrontabLogVOMeta.CRONTAB_ID, value = "crontab", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ActionCrontabLogVOMeta.EXECUTION_RESULT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ActionCrontabLogVOMeta.RECORD_TIME, value = "????????????", required = false, dataTypeClass = Date.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = ActionCrontabLogServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ActionCrontabLogServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(ActionCrontabLogVO actionCrontabLogVO) {
        Result result = actionCrontabLogService.insert(actionCrontabLogVO, false);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ActionCrontabLogVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = ActionCrontabLogServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ActionCrontabLogServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = actionCrontabLogService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ?????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ActionCrontabLogVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = ActionCrontabLogServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ActionCrontabLogServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = actionCrontabLogService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ActionCrontabLogVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = ActionCrontabLogVOMeta.CRONTAB_ID, value = "crontab", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ActionCrontabLogVOMeta.EXECUTION_RESULT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ActionCrontabLogVOMeta.RECORD_TIME, value = "????????????", required = false, dataTypeClass = Date.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { ActionCrontabLogVOMeta.PAGE_INDEX, ActionCrontabLogVOMeta.PAGE_SIZE, ActionCrontabLogVOMeta.SEARCH_FIELD, ActionCrontabLogVOMeta.FUZZY_FIELD, ActionCrontabLogVOMeta.SEARCH_VALUE, ActionCrontabLogVOMeta.DIRTY_FIELDS, ActionCrontabLogVOMeta.SORT_FIELD, ActionCrontabLogVOMeta.SORT_TYPE, ActionCrontabLogVOMeta.IDS })
    @SentinelResource(value = ActionCrontabLogServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ActionCrontabLogServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(ActionCrontabLogVO actionCrontabLogVO) {
        Result result = actionCrontabLogService.update(actionCrontabLogVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ActionCrontabLogVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = ActionCrontabLogVOMeta.CRONTAB_ID, value = "crontab", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ActionCrontabLogVOMeta.EXECUTION_RESULT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ActionCrontabLogVOMeta.RECORD_TIME, value = "????????????", required = false, dataTypeClass = Date.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { ActionCrontabLogVOMeta.PAGE_INDEX, ActionCrontabLogVOMeta.PAGE_SIZE, ActionCrontabLogVOMeta.SEARCH_FIELD, ActionCrontabLogVOMeta.FUZZY_FIELD, ActionCrontabLogVOMeta.SEARCH_VALUE, ActionCrontabLogVOMeta.DIRTY_FIELDS, ActionCrontabLogVOMeta.SORT_FIELD, ActionCrontabLogVOMeta.SORT_TYPE, ActionCrontabLogVOMeta.IDS })
    @SentinelResource(value = ActionCrontabLogServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ActionCrontabLogServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(ActionCrontabLogVO actionCrontabLogVO) {
        Result result = actionCrontabLogService.save(actionCrontabLogVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ActionCrontabLogVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = ActionCrontabLogServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ActionCrontabLogServiceProxy.GET_BY_ID)
    public Result<ActionCrontabLog> getById(String id) {
        Result<ActionCrontabLog> result = new Result<>();
        ActionCrontabLog actionCrontabLog = actionCrontabLogService.getById(id);
        result.success(true).data(actionCrontabLog);
        return result;
    }

    /**
     * ?????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ActionCrontabLogVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = ActionCrontabLogServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ActionCrontabLogServiceProxy.GET_BY_IDS)
    public Result<List<ActionCrontabLog>> getByIds(List<String> ids) {
        Result<List<ActionCrontabLog>> result = new Result<>();
        List<ActionCrontabLog> list = actionCrontabLogService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ActionCrontabLogVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = ActionCrontabLogVOMeta.CRONTAB_ID, value = "crontab", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ActionCrontabLogVOMeta.EXECUTION_RESULT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ActionCrontabLogVOMeta.RECORD_TIME, value = "????????????", required = false, dataTypeClass = Date.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { ActionCrontabLogVOMeta.PAGE_INDEX, ActionCrontabLogVOMeta.PAGE_SIZE })
    @SentinelResource(value = ActionCrontabLogServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ActionCrontabLogServiceProxy.QUERY_LIST)
    public Result<List<ActionCrontabLog>> queryList(ActionCrontabLogVO sample) {
        Result<List<ActionCrontabLog>> result = new Result<>();
        List<ActionCrontabLog> list = actionCrontabLogService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ActionCrontabLogVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = ActionCrontabLogVOMeta.CRONTAB_ID, value = "crontab", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ActionCrontabLogVOMeta.EXECUTION_RESULT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ActionCrontabLogVOMeta.RECORD_TIME, value = "????????????", required = false, dataTypeClass = Date.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = ActionCrontabLogServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ActionCrontabLogServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<ActionCrontabLog>> queryPagedList(ActionCrontabLogVO sample) {
        Result<PagedList<ActionCrontabLog>> result = new Result<>();
        PagedList<ActionCrontabLog> list = actionCrontabLogService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = ActionCrontabLogServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(ActionCrontabLogServiceProxy.EXPORT_EXCEL)
    public void exportExcel(ActionCrontabLogVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = actionCrontabLogService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = ActionCrontabLogServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(ActionCrontabLogServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = actionCrontabLogService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = ActionCrontabLogServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(ActionCrontabLogServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = actionCrontabLogService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
