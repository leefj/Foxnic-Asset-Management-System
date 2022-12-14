package com.dt.platform.eam.controller;

import java.util.List;
import com.dt.platform.domain.eam.*;
import com.github.foxnic.commons.collection.CollectorUtil;
import org.github.foxnic.web.domain.hrm.Person;
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
import com.dt.platform.proxy.eam.MaintainTaskServiceProxy;
import com.dt.platform.domain.eam.meta.MaintainTaskVOMeta;
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
import com.dt.platform.domain.eam.meta.MaintainTaskMeta;
import java.math.BigDecimal;
import org.github.foxnic.web.domain.system.DictItem;
import org.github.foxnic.web.domain.hrm.Employee;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IMaintainTaskService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-06-06 21:41:15
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("EamMaintainTaskController")
public class MaintainTaskController extends SuperController {

    @Autowired
    private IMaintainTaskService maintainTaskService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintainTaskVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "586286133193740288"),
		@ApiImplicitParam(name = MaintainTaskVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.OVERDUE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.PLAN_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.PLAN_NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.PLAN_INFO, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.PLAN_NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.PLAN_MAINTAIN_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.PLAN_CYCLE_METHOD, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.PLAN_TOTAL_COST, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = MaintainTaskVOMeta.GROUP_ID, value = "??????", required = false, dataTypeClass = String.class, example = "584133248423034880"),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ASSET_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ASSET_NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ASSET_MODEL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ASSET_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ASSET_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ASSET_SN, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.EXECUTOR_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "558321538131034112"),
		@ApiImplicitParam(name = MaintainTaskVOMeta.PLAN_START_TIME, value = "???????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ACT_START_TIME, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ACT_FINISH_TIME, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ACT_TOTAL_COST, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = MaintainTaskVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = MaintainTaskServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintainTaskServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(MaintainTaskVO maintainTaskVO) {
        Result result = maintainTaskService.insert(maintainTaskVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintainTaskVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "586286133193740288")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = MaintainTaskServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintainTaskServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = maintainTaskService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintainTaskVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = MaintainTaskServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintainTaskServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = maintainTaskService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintainTaskVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "586286133193740288"),
		@ApiImplicitParam(name = MaintainTaskVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.OVERDUE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.PLAN_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.PLAN_NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.PLAN_INFO, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.PLAN_NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.PLAN_MAINTAIN_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.PLAN_CYCLE_METHOD, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.PLAN_TOTAL_COST, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = MaintainTaskVOMeta.GROUP_ID, value = "??????", required = false, dataTypeClass = String.class, example = "584133248423034880"),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ASSET_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ASSET_NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ASSET_MODEL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ASSET_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ASSET_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ASSET_SN, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.EXECUTOR_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "558321538131034112"),
		@ApiImplicitParam(name = MaintainTaskVOMeta.PLAN_START_TIME, value = "???????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ACT_START_TIME, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ACT_FINISH_TIME, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ACT_TOTAL_COST, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = MaintainTaskVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { MaintainTaskVOMeta.PAGE_INDEX, MaintainTaskVOMeta.PAGE_SIZE, MaintainTaskVOMeta.SEARCH_FIELD, MaintainTaskVOMeta.FUZZY_FIELD, MaintainTaskVOMeta.SEARCH_VALUE, MaintainTaskVOMeta.DIRTY_FIELDS, MaintainTaskVOMeta.SORT_FIELD, MaintainTaskVOMeta.SORT_TYPE, MaintainTaskVOMeta.IDS })
    @SentinelResource(value = MaintainTaskServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintainTaskServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(MaintainTaskVO maintainTaskVO) {
        Result result = maintainTaskService.update(maintainTaskVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintainTaskVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "586286133193740288"),
		@ApiImplicitParam(name = MaintainTaskVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.OVERDUE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.PLAN_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.PLAN_NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.PLAN_INFO, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.PLAN_NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.PLAN_MAINTAIN_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.PLAN_CYCLE_METHOD, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.PLAN_TOTAL_COST, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = MaintainTaskVOMeta.GROUP_ID, value = "??????", required = false, dataTypeClass = String.class, example = "584133248423034880"),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ASSET_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ASSET_NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ASSET_MODEL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ASSET_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ASSET_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ASSET_SN, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.EXECUTOR_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "558321538131034112"),
		@ApiImplicitParam(name = MaintainTaskVOMeta.PLAN_START_TIME, value = "???????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ACT_START_TIME, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ACT_FINISH_TIME, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ACT_TOTAL_COST, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = MaintainTaskVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { MaintainTaskVOMeta.PAGE_INDEX, MaintainTaskVOMeta.PAGE_SIZE, MaintainTaskVOMeta.SEARCH_FIELD, MaintainTaskVOMeta.FUZZY_FIELD, MaintainTaskVOMeta.SEARCH_VALUE, MaintainTaskVOMeta.DIRTY_FIELDS, MaintainTaskVOMeta.SORT_FIELD, MaintainTaskVOMeta.SORT_TYPE, MaintainTaskVOMeta.IDS })
    @SentinelResource(value = MaintainTaskServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintainTaskServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(MaintainTaskVO maintainTaskVO) {
        Result result = maintainTaskService.save(maintainTaskVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintainTaskVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = MaintainTaskServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintainTaskServiceProxy.GET_BY_ID)
    public Result<MaintainTask> getById(String id) {
        Result<MaintainTask> result = new Result<>();
        MaintainTask maintainTask = maintainTaskService.getById(id);
        // join ???????????????
        maintainTaskService.dao().fill(maintainTask).with("originator").with("executor").with(MaintainTaskMeta.MAINTAIN_GROUP).with(MaintainTaskMeta.MAINTAIN_TYPE_DICT).execute();
        maintainTaskService.dao().join(maintainTask.getExecutor(), Person.class);
        maintainTaskService.dao().join(maintainTask.getOriginator(), Person.class);
        result.success(true).data(maintainTask);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintainTaskVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = MaintainTaskServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintainTaskServiceProxy.GET_BY_IDS)
    public Result<List<MaintainTask>> getByIds(List<String> ids) {
        Result<List<MaintainTask>> result = new Result<>();
        List<MaintainTask> list = maintainTaskService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintainTaskVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "586286133193740288"),
		@ApiImplicitParam(name = MaintainTaskVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.OVERDUE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.PLAN_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.PLAN_NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.PLAN_INFO, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.PLAN_NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.PLAN_MAINTAIN_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.PLAN_CYCLE_METHOD, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.PLAN_TOTAL_COST, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = MaintainTaskVOMeta.GROUP_ID, value = "??????", required = false, dataTypeClass = String.class, example = "584133248423034880"),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ASSET_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ASSET_NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ASSET_MODEL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ASSET_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ASSET_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ASSET_SN, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.EXECUTOR_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "558321538131034112"),
		@ApiImplicitParam(name = MaintainTaskVOMeta.PLAN_START_TIME, value = "???????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ACT_START_TIME, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ACT_FINISH_TIME, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ACT_TOTAL_COST, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = MaintainTaskVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { MaintainTaskVOMeta.PAGE_INDEX, MaintainTaskVOMeta.PAGE_SIZE })
    @SentinelResource(value = MaintainTaskServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintainTaskServiceProxy.QUERY_LIST)
    public Result<List<MaintainTask>> queryList(MaintainTaskVO sample) {
        Result<List<MaintainTask>> result = new Result<>();
        List<MaintainTask> list = maintainTaskService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintainTaskVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "586286133193740288"),
		@ApiImplicitParam(name = MaintainTaskVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.OVERDUE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.PLAN_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.PLAN_NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.PLAN_INFO, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.PLAN_NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.PLAN_MAINTAIN_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.PLAN_CYCLE_METHOD, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.PLAN_TOTAL_COST, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = MaintainTaskVOMeta.GROUP_ID, value = "??????", required = false, dataTypeClass = String.class, example = "584133248423034880"),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ASSET_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ASSET_NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ASSET_MODEL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ASSET_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ASSET_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ASSET_SN, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.EXECUTOR_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "558321538131034112"),
		@ApiImplicitParam(name = MaintainTaskVOMeta.PLAN_START_TIME, value = "???????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ACT_START_TIME, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ACT_FINISH_TIME, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ACT_TOTAL_COST, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = MaintainTaskVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainTaskVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = MaintainTaskServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintainTaskServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<MaintainTask>> queryPagedList(MaintainTaskVO sample) {
        Result<PagedList<MaintainTask>> result = new Result<>();
        PagedList<MaintainTask> list = maintainTaskService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        maintainTaskService.dao().fill(list).with("originator").with("executor").with(MaintainTaskMeta.MAINTAIN_GROUP).with(MaintainTaskMeta.MAINTAIN_TYPE_DICT).execute();
        List<Employee> originator = CollectorUtil.collectList(list.getList(), MaintainTask::getOriginator);
        maintainTaskService.dao().join(originator, Person.class);
        List<Employee> executorUser = CollectorUtil.collectList(list.getList(), MaintainTask::getExecutor);
        maintainTaskService.dao().join(executorUser, Person.class);
        result.success(true).data(list);
        return result;
    }

    /**
     */
    @ApiOperation(value = "")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintainTaskVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 9)
    @SentinelResource(value = MaintainTaskServiceProxy.BATCH_CANCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintainTaskServiceProxy.BATCH_CANCEL)
    public Result batchCancel(List<String> ids) {
        return maintainTaskService.batchCancel(ids);
    }

    @ApiOperation(value = "")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintainTaskVOMeta.ID, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 9)
    @SentinelResource(value = MaintainTaskServiceProxy.CANCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintainTaskServiceProxy.CANCEL)
    public Result cancel(String id) {
        return maintainTaskService.cancel(id);
    }

    @ApiOperation(value = "")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintainTaskVOMeta.ID, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 9)
    @SentinelResource(value = MaintainTaskServiceProxy.FINISH, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintainTaskServiceProxy.FINISH)
    public Result finish(String id) {
        return maintainTaskService.finish(id);
    }

    /**
     */
    @ApiOperation(value = "")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintainTaskVOMeta.ID, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 10)
    @SentinelResource(value = MaintainTaskServiceProxy.EXECUTE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintainTaskServiceProxy.EXECUTE)
    public Result execute(String id) {
        return maintainTaskService.execute(id);
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = MaintainTaskServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(MaintainTaskServiceProxy.EXPORT_EXCEL)
    public void exportExcel(MaintainTaskVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = maintainTaskService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = MaintainTaskServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(MaintainTaskServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = maintainTaskService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = MaintainTaskServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(MaintainTaskServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = maintainTaskService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
