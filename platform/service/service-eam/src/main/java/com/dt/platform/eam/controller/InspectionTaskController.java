package com.dt.platform.eam.controller;

import java.util.List;
import com.dt.platform.domain.eam.meta.MaintainTaskVOMeta;
import com.dt.platform.proxy.eam.MaintainTaskServiceProxy;
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
import com.dt.platform.proxy.eam.InspectionTaskServiceProxy;
import com.dt.platform.domain.eam.meta.InspectionTaskVOMeta;
import com.dt.platform.domain.eam.InspectionTask;
import com.dt.platform.domain.eam.InspectionTaskVO;
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
import com.dt.platform.domain.eam.meta.InspectionTaskMeta;
import java.math.BigDecimal;
import com.dt.platform.domain.eam.InspectionPlan;
import com.dt.platform.domain.eam.InspectionPoint;
import com.dt.platform.domain.eam.InspectionTaskPoint;
import org.github.foxnic.web.domain.system.DictItem;
import com.dt.platform.domain.eam.InspectionGroup;
import org.github.foxnic.web.domain.hrm.Employee;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IInspectionTaskService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-06-13 10:28:04
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("EamInspectionTaskController")
public class InspectionTaskController extends SuperController {

    @Autowired
    private IInspectionTaskService inspectionTaskService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InspectionTaskVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.PLAN_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.TASK_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.TASK_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.PLAN_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.PLAN_NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.PLAN_INSPECTION_METHOD, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.PLAN_COMPLETION_TIME, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.PLAN_NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.GROUP_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.EXECUTOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.PLAN_START_TIME, value = "???????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.ACT_START_TIME, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.ACT_FINISH_TIME, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.ACT_TOTAL_COST, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = InspectionTaskServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionTaskServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(InspectionTaskVO inspectionTaskVO) {
        Result result = inspectionTaskService.insert(inspectionTaskVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InspectionTaskVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = InspectionTaskServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionTaskServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = inspectionTaskService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InspectionTaskVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = InspectionTaskServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionTaskServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = inspectionTaskService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InspectionTaskVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.PLAN_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.TASK_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.TASK_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.PLAN_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.PLAN_NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.PLAN_INSPECTION_METHOD, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.PLAN_COMPLETION_TIME, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.PLAN_NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.GROUP_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.EXECUTOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.PLAN_START_TIME, value = "???????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.ACT_START_TIME, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.ACT_FINISH_TIME, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.ACT_TOTAL_COST, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { InspectionTaskVOMeta.PAGE_INDEX, InspectionTaskVOMeta.PAGE_SIZE, InspectionTaskVOMeta.SEARCH_FIELD, InspectionTaskVOMeta.FUZZY_FIELD, InspectionTaskVOMeta.SEARCH_VALUE, InspectionTaskVOMeta.DIRTY_FIELDS, InspectionTaskVOMeta.SORT_FIELD, InspectionTaskVOMeta.SORT_TYPE, InspectionTaskVOMeta.IDS })
    @SentinelResource(value = InspectionTaskServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionTaskServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(InspectionTaskVO inspectionTaskVO) {
        Result result = inspectionTaskService.update(inspectionTaskVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InspectionTaskVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.PLAN_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.TASK_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.TASK_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.PLAN_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.PLAN_NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.PLAN_INSPECTION_METHOD, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.PLAN_COMPLETION_TIME, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.PLAN_NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.GROUP_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.EXECUTOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.PLAN_START_TIME, value = "???????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.ACT_START_TIME, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.ACT_FINISH_TIME, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.ACT_TOTAL_COST, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { InspectionTaskVOMeta.PAGE_INDEX, InspectionTaskVOMeta.PAGE_SIZE, InspectionTaskVOMeta.SEARCH_FIELD, InspectionTaskVOMeta.FUZZY_FIELD, InspectionTaskVOMeta.SEARCH_VALUE, InspectionTaskVOMeta.DIRTY_FIELDS, InspectionTaskVOMeta.SORT_FIELD, InspectionTaskVOMeta.SORT_TYPE, InspectionTaskVOMeta.IDS })
    @SentinelResource(value = InspectionTaskServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionTaskServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(InspectionTaskVO inspectionTaskVO) {
        Result result = inspectionTaskService.save(inspectionTaskVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InspectionTaskVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = InspectionTaskServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionTaskServiceProxy.GET_BY_ID)
    public Result<InspectionTask> getById(String id) {
        Result<InspectionTask> result = new Result<>();
        InspectionTask inspectionTask = inspectionTaskService.getById(id);
        // join ???????????????
        inspectionTaskService.dao().fill(inspectionTask).with("originator").with(InspectionTaskMeta.INSPECTION_GROUP).with(InspectionTaskMeta.EXECUTOR).execute();
        result.success(true).data(inspectionTask);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InspectionTaskVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = InspectionTaskServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionTaskServiceProxy.GET_BY_IDS)
    public Result<List<InspectionTask>> getByIds(List<String> ids) {
        Result<List<InspectionTask>> result = new Result<>();
        List<InspectionTask> list = inspectionTaskService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InspectionTaskVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.PLAN_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.TASK_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.TASK_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.PLAN_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.PLAN_NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.PLAN_INSPECTION_METHOD, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.PLAN_COMPLETION_TIME, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.PLAN_NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.GROUP_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.EXECUTOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.PLAN_START_TIME, value = "???????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.ACT_START_TIME, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.ACT_FINISH_TIME, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.ACT_TOTAL_COST, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { InspectionTaskVOMeta.PAGE_INDEX, InspectionTaskVOMeta.PAGE_SIZE })
    @SentinelResource(value = InspectionTaskServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionTaskServiceProxy.QUERY_LIST)
    public Result<List<InspectionTask>> queryList(InspectionTaskVO sample) {
        Result<List<InspectionTask>> result = new Result<>();
        List<InspectionTask> list = inspectionTaskService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InspectionTaskVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.PLAN_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.TASK_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.TASK_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.PLAN_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.PLAN_NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.PLAN_INSPECTION_METHOD, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.PLAN_COMPLETION_TIME, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.PLAN_NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.GROUP_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.EXECUTOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.PLAN_START_TIME, value = "???????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.ACT_START_TIME, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.ACT_FINISH_TIME, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.ACT_TOTAL_COST, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionTaskVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = InspectionTaskServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionTaskServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<InspectionTask>> queryPagedList(InspectionTaskVO sample) {
        Result<PagedList<InspectionTask>> result = new Result<>();
        PagedList<InspectionTask> list = inspectionTaskService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        inspectionTaskService.dao().fill(list).with("originator").with(InspectionTaskMeta.INSPECTION_GROUP).with(InspectionTaskMeta.EXECUTOR).execute();
        result.success(true).data(list);
        return result;
    }

    @ApiOperation(value = "")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InspectionTaskVOMeta.ID, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 9)
    @SentinelResource(value = InspectionTaskServiceProxy.CANCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionTaskServiceProxy.CANCEL)
    public Result cancel(String id) {
        return inspectionTaskService.cancel(id);
    }

    @ApiOperation(value = "")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InspectionTaskVOMeta.ID, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 9)
    @SentinelResource(value = InspectionTaskServiceProxy.FINISH, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionTaskServiceProxy.FINISH)
    public Result finish(String id) {
        return inspectionTaskService.finish(id);
    }

    /**
     */
    @ApiOperation(value = "")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InspectionTaskVOMeta.ID, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 10)
    @SentinelResource(value = InspectionTaskServiceProxy.EXECUTE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionTaskServiceProxy.EXECUTE)
    public Result execute(String id) {
        return inspectionTaskService.execute(id);
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = InspectionTaskServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(InspectionTaskServiceProxy.EXPORT_EXCEL)
    public void exportExcel(InspectionTaskVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = inspectionTaskService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = InspectionTaskServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(InspectionTaskServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = inspectionTaskService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = InspectionTaskServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(InspectionTaskServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = inspectionTaskService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
