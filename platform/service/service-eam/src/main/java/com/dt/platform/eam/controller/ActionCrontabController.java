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
import com.dt.platform.proxy.eam.ActionCrontabServiceProxy;
import com.dt.platform.domain.eam.meta.ActionCrontabVOMeta;
import com.dt.platform.domain.eam.ActionCrontab;
import com.dt.platform.domain.eam.ActionCrontabVO;
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
import com.dt.platform.domain.eam.meta.ActionCrontabMeta;
import com.dt.platform.domain.eam.ActionCrontabLog;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IActionCrontabService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-06-03 21:08:22
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("EamActionCrontabController")
public class ActionCrontabController extends SuperController {

    @Autowired
    private IActionCrontabService actionCrontabService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ActionCrontabVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "585027341697155072"),
		@ApiImplicitParam(name = ActionCrontabVOMeta.OWNER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ActionCrontabVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = ActionCrontabVOMeta.CRONTAB, value = "?????????", required = false, dataTypeClass = String.class, example = "0,2,11 * * * * ?"),
		@ApiImplicitParam(name = ActionCrontabVOMeta.START_EXECUTION_TIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-06-08 12:00:00"),
		@ApiImplicitParam(name = ActionCrontabVOMeta.FINISH_EXECUTION_TIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-06-25 12:00:00"),
		@ApiImplicitParam(name = ActionCrontabVOMeta.LAST_EXECUTION_TIME, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = ActionCrontabVOMeta.NEXT_EXECUTION_TIME, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = ActionCrontabVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = ActionCrontabServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ActionCrontabServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(ActionCrontabVO actionCrontabVO) {
        Result result = actionCrontabService.insert(actionCrontabVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ActionCrontabVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "585027341697155072")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = ActionCrontabServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ActionCrontabServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = actionCrontabService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ActionCrontabVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = ActionCrontabServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ActionCrontabServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = actionCrontabService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ActionCrontabVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "585027341697155072"),
		@ApiImplicitParam(name = ActionCrontabVOMeta.OWNER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ActionCrontabVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = ActionCrontabVOMeta.CRONTAB, value = "?????????", required = false, dataTypeClass = String.class, example = "0,2,11 * * * * ?"),
		@ApiImplicitParam(name = ActionCrontabVOMeta.START_EXECUTION_TIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-06-08 12:00:00"),
		@ApiImplicitParam(name = ActionCrontabVOMeta.FINISH_EXECUTION_TIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-06-25 12:00:00"),
		@ApiImplicitParam(name = ActionCrontabVOMeta.LAST_EXECUTION_TIME, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = ActionCrontabVOMeta.NEXT_EXECUTION_TIME, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = ActionCrontabVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { ActionCrontabVOMeta.PAGE_INDEX, ActionCrontabVOMeta.PAGE_SIZE, ActionCrontabVOMeta.SEARCH_FIELD, ActionCrontabVOMeta.FUZZY_FIELD, ActionCrontabVOMeta.SEARCH_VALUE, ActionCrontabVOMeta.DIRTY_FIELDS, ActionCrontabVOMeta.SORT_FIELD, ActionCrontabVOMeta.SORT_TYPE, ActionCrontabVOMeta.IDS })
    @SentinelResource(value = ActionCrontabServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ActionCrontabServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(ActionCrontabVO actionCrontabVO) {
        Result result = actionCrontabService.update(actionCrontabVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ActionCrontabVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "585027341697155072"),
		@ApiImplicitParam(name = ActionCrontabVOMeta.OWNER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ActionCrontabVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = ActionCrontabVOMeta.CRONTAB, value = "?????????", required = false, dataTypeClass = String.class, example = "0,2,11 * * * * ?"),
		@ApiImplicitParam(name = ActionCrontabVOMeta.START_EXECUTION_TIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-06-08 12:00:00"),
		@ApiImplicitParam(name = ActionCrontabVOMeta.FINISH_EXECUTION_TIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-06-25 12:00:00"),
		@ApiImplicitParam(name = ActionCrontabVOMeta.LAST_EXECUTION_TIME, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = ActionCrontabVOMeta.NEXT_EXECUTION_TIME, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = ActionCrontabVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { ActionCrontabVOMeta.PAGE_INDEX, ActionCrontabVOMeta.PAGE_SIZE, ActionCrontabVOMeta.SEARCH_FIELD, ActionCrontabVOMeta.FUZZY_FIELD, ActionCrontabVOMeta.SEARCH_VALUE, ActionCrontabVOMeta.DIRTY_FIELDS, ActionCrontabVOMeta.SORT_FIELD, ActionCrontabVOMeta.SORT_TYPE, ActionCrontabVOMeta.IDS })
    @SentinelResource(value = ActionCrontabServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ActionCrontabServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(ActionCrontabVO actionCrontabVO) {
        Result result = actionCrontabService.save(actionCrontabVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ActionCrontabVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = ActionCrontabServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ActionCrontabServiceProxy.GET_BY_ID)
    public Result<ActionCrontab> getById(String id) {
        Result<ActionCrontab> result = new Result<>();
        ActionCrontab actionCrontab = actionCrontabService.getById(id);
        result.success(true).data(actionCrontab);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ActionCrontabVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = ActionCrontabServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ActionCrontabServiceProxy.GET_BY_IDS)
    public Result<List<ActionCrontab>> getByIds(List<String> ids) {
        Result<List<ActionCrontab>> result = new Result<>();
        List<ActionCrontab> list = actionCrontabService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ActionCrontabVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "585027341697155072"),
		@ApiImplicitParam(name = ActionCrontabVOMeta.OWNER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ActionCrontabVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = ActionCrontabVOMeta.CRONTAB, value = "?????????", required = false, dataTypeClass = String.class, example = "0,2,11 * * * * ?"),
		@ApiImplicitParam(name = ActionCrontabVOMeta.START_EXECUTION_TIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-06-08 12:00:00"),
		@ApiImplicitParam(name = ActionCrontabVOMeta.FINISH_EXECUTION_TIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-06-25 12:00:00"),
		@ApiImplicitParam(name = ActionCrontabVOMeta.LAST_EXECUTION_TIME, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = ActionCrontabVOMeta.NEXT_EXECUTION_TIME, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = ActionCrontabVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { ActionCrontabVOMeta.PAGE_INDEX, ActionCrontabVOMeta.PAGE_SIZE })
    @SentinelResource(value = ActionCrontabServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ActionCrontabServiceProxy.QUERY_LIST)
    public Result<List<ActionCrontab>> queryList(ActionCrontabVO sample) {
        Result<List<ActionCrontab>> result = new Result<>();
        List<ActionCrontab> list = actionCrontabService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ActionCrontabVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "585027341697155072"),
		@ApiImplicitParam(name = ActionCrontabVOMeta.OWNER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ActionCrontabVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = ActionCrontabVOMeta.CRONTAB, value = "?????????", required = false, dataTypeClass = String.class, example = "0,2,11 * * * * ?"),
		@ApiImplicitParam(name = ActionCrontabVOMeta.START_EXECUTION_TIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-06-08 12:00:00"),
		@ApiImplicitParam(name = ActionCrontabVOMeta.FINISH_EXECUTION_TIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-06-25 12:00:00"),
		@ApiImplicitParam(name = ActionCrontabVOMeta.LAST_EXECUTION_TIME, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = ActionCrontabVOMeta.NEXT_EXECUTION_TIME, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = ActionCrontabVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = ActionCrontabServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ActionCrontabServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<ActionCrontab>> queryPagedList(ActionCrontabVO sample) {
        Result<PagedList<ActionCrontab>> result = new Result<>();
        PagedList<ActionCrontab> list = actionCrontabService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiOperationSupport(order = 9)
    @SentinelResource(value = ActionCrontabServiceProxy.GET_BY_OWNER_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ActionCrontabServiceProxy.GET_BY_OWNER_ID)
    public Result<ActionCrontab> getByOwnerId(String ownerId) {
        Result<ActionCrontab> result = new Result<>();
        ActionCrontab actionCrontab = actionCrontabService.getByOwnerId(ownerId);
        result.success(true).data(actionCrontab);
        return result;
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = ActionCrontabServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(ActionCrontabServiceProxy.EXPORT_EXCEL)
    public void exportExcel(ActionCrontabVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = actionCrontabService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = ActionCrontabServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(ActionCrontabServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = actionCrontabService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = ActionCrontabServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(ActionCrontabServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = actionCrontabService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
