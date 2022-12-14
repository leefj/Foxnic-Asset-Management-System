package com.dt.platform.eam.controller;

import java.util.List;
import com.dt.platform.domain.eam.*;
import com.dt.platform.eam.service.IMaintainProjectSelectService;
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
import com.dt.platform.proxy.eam.MaintainPlanServiceProxy;
import com.dt.platform.domain.eam.meta.MaintainPlanVOMeta;
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
import com.dt.platform.domain.eam.meta.MaintainPlanMeta;
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
import com.dt.platform.eam.service.IMaintainPlanService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-06-05 09:51:44
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("EamMaintainPlanController")
public class MaintainPlanController extends SuperController {

    @Autowired
    private IMaintainPlanService maintainPlanService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintainPlanVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "585374391123054592"),
		@ApiImplicitParam(name = MaintainPlanVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "121"),
		@ApiImplicitParam(name = MaintainPlanVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = MaintainPlanVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = MaintainPlanVOMeta.GROUP_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "584133248423034880"),
		@ApiImplicitParam(name = MaintainPlanVOMeta.MAINTAIN_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "default"),
		@ApiImplicitParam(name = MaintainPlanVOMeta.CYCLE_METHOD, value = "????????????", required = false, dataTypeClass = String.class, example = "once"),
		@ApiImplicitParam(name = MaintainPlanVOMeta.ACTION_CYCLE_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainPlanVOMeta.START_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MaintainPlanVOMeta.END_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MaintainPlanVOMeta.TOTAL_COST, value = "?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MaintainPlanVOMeta.INFO, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainPlanVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainPlanVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = MaintainPlanServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintainPlanServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(MaintainPlanVO maintainPlanVO) {
        Result result = maintainPlanService.insert(maintainPlanVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintainPlanVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "585374391123054592")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = MaintainPlanServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintainPlanServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = maintainPlanService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintainPlanVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = MaintainPlanServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintainPlanServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = maintainPlanService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintainPlanVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "585374391123054592"),
		@ApiImplicitParam(name = MaintainPlanVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "121"),
		@ApiImplicitParam(name = MaintainPlanVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = MaintainPlanVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = MaintainPlanVOMeta.GROUP_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "584133248423034880"),
		@ApiImplicitParam(name = MaintainPlanVOMeta.MAINTAIN_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "default"),
		@ApiImplicitParam(name = MaintainPlanVOMeta.CYCLE_METHOD, value = "????????????", required = false, dataTypeClass = String.class, example = "once"),
		@ApiImplicitParam(name = MaintainPlanVOMeta.ACTION_CYCLE_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainPlanVOMeta.START_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MaintainPlanVOMeta.END_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MaintainPlanVOMeta.TOTAL_COST, value = "?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MaintainPlanVOMeta.INFO, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainPlanVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainPlanVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { MaintainPlanVOMeta.PAGE_INDEX, MaintainPlanVOMeta.PAGE_SIZE, MaintainPlanVOMeta.SEARCH_FIELD, MaintainPlanVOMeta.FUZZY_FIELD, MaintainPlanVOMeta.SEARCH_VALUE, MaintainPlanVOMeta.DIRTY_FIELDS, MaintainPlanVOMeta.SORT_FIELD, MaintainPlanVOMeta.SORT_TYPE, MaintainPlanVOMeta.IDS })
    @SentinelResource(value = MaintainPlanServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintainPlanServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(MaintainPlanVO maintainPlanVO) {
        Result result = maintainPlanService.update(maintainPlanVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintainPlanVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "585374391123054592"),
		@ApiImplicitParam(name = MaintainPlanVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "121"),
		@ApiImplicitParam(name = MaintainPlanVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = MaintainPlanVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = MaintainPlanVOMeta.GROUP_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "584133248423034880"),
		@ApiImplicitParam(name = MaintainPlanVOMeta.MAINTAIN_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "default"),
		@ApiImplicitParam(name = MaintainPlanVOMeta.CYCLE_METHOD, value = "????????????", required = false, dataTypeClass = String.class, example = "once"),
		@ApiImplicitParam(name = MaintainPlanVOMeta.ACTION_CYCLE_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainPlanVOMeta.START_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MaintainPlanVOMeta.END_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MaintainPlanVOMeta.TOTAL_COST, value = "?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MaintainPlanVOMeta.INFO, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainPlanVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainPlanVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { MaintainPlanVOMeta.PAGE_INDEX, MaintainPlanVOMeta.PAGE_SIZE, MaintainPlanVOMeta.SEARCH_FIELD, MaintainPlanVOMeta.FUZZY_FIELD, MaintainPlanVOMeta.SEARCH_VALUE, MaintainPlanVOMeta.DIRTY_FIELDS, MaintainPlanVOMeta.SORT_FIELD, MaintainPlanVOMeta.SORT_TYPE, MaintainPlanVOMeta.IDS })
    @SentinelResource(value = MaintainPlanServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintainPlanServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(MaintainPlanVO maintainPlanVO) {
        Result result = maintainPlanService.save(maintainPlanVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintainPlanVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = MaintainPlanServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintainPlanServiceProxy.GET_BY_ID)
    public Result<MaintainPlan> getById(String id) {
        Result<MaintainPlan> result = new Result<>();
        MaintainPlan maintainPlan = maintainPlanService.getById(id);
        // join ???????????????
        maintainPlanService.dao().fill(maintainPlan).with("originator").with(MaintainPlanMeta.MAINTAIN_TYPE_DICT).with(MaintainPlanMeta.MAINTAIN_GROUP).with(MaintainPlanMeta.ACTION_CRONTAB).execute();
        result.success(true).data(maintainPlan);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintainPlanVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = MaintainPlanServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintainPlanServiceProxy.GET_BY_IDS)
    public Result<List<MaintainPlan>> getByIds(List<String> ids) {
        Result<List<MaintainPlan>> result = new Result<>();
        List<MaintainPlan> list = maintainPlanService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintainPlanVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "585374391123054592"),
		@ApiImplicitParam(name = MaintainPlanVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "121"),
		@ApiImplicitParam(name = MaintainPlanVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = MaintainPlanVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = MaintainPlanVOMeta.GROUP_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "584133248423034880"),
		@ApiImplicitParam(name = MaintainPlanVOMeta.MAINTAIN_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "default"),
		@ApiImplicitParam(name = MaintainPlanVOMeta.CYCLE_METHOD, value = "????????????", required = false, dataTypeClass = String.class, example = "once"),
		@ApiImplicitParam(name = MaintainPlanVOMeta.ACTION_CYCLE_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainPlanVOMeta.START_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MaintainPlanVOMeta.END_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MaintainPlanVOMeta.TOTAL_COST, value = "?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MaintainPlanVOMeta.INFO, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainPlanVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainPlanVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { MaintainPlanVOMeta.PAGE_INDEX, MaintainPlanVOMeta.PAGE_SIZE })
    @SentinelResource(value = MaintainPlanServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintainPlanServiceProxy.QUERY_LIST)
    public Result<List<MaintainPlan>> queryList(MaintainPlanVO sample) {
        Result<List<MaintainPlan>> result = new Result<>();
        List<MaintainPlan> list = maintainPlanService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintainPlanVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "585374391123054592"),
		@ApiImplicitParam(name = MaintainPlanVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "121"),
		@ApiImplicitParam(name = MaintainPlanVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = MaintainPlanVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = MaintainPlanVOMeta.GROUP_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "584133248423034880"),
		@ApiImplicitParam(name = MaintainPlanVOMeta.MAINTAIN_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "default"),
		@ApiImplicitParam(name = MaintainPlanVOMeta.CYCLE_METHOD, value = "????????????", required = false, dataTypeClass = String.class, example = "once"),
		@ApiImplicitParam(name = MaintainPlanVOMeta.ACTION_CYCLE_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainPlanVOMeta.START_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MaintainPlanVOMeta.END_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MaintainPlanVOMeta.TOTAL_COST, value = "?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = MaintainPlanVOMeta.INFO, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainPlanVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainPlanVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = MaintainPlanServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintainPlanServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<MaintainPlan>> queryPagedList(MaintainPlanVO sample) {
        Result<PagedList<MaintainPlan>> result = new Result<>();
        PagedList<MaintainPlan> list = maintainPlanService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        maintainPlanService.dao().fill(list).with("originator").with(MaintainPlanMeta.MAINTAIN_TYPE_DICT).with(MaintainPlanMeta.MAINTAIN_GROUP).execute();
        List<Employee> originator = CollectorUtil.collectList(list.getList(), MaintainPlan::getOriginator);
        maintainPlanService.dao().join(originator, Person.class);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????
     */
    @ApiOperation(value = "??????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintainPlanVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 9)
    @SentinelResource(value = MaintainPlanServiceProxy.START, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintainPlanServiceProxy.START)
    public Result start(String id) {
        return maintainPlanService.start(id);
    }

    /**
     * ??????
     */
    @ApiOperation(value = "??????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintainPlanVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 10)
    @SentinelResource(value = MaintainPlanServiceProxy.STOP, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintainPlanServiceProxy.STOP)
    public Result stop(String id) {
        return maintainPlanService.stop(id);
    }

    /**
     * ??????
     */
    @ApiOperation(value = "??????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintainPlanVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 11)
    @SentinelResource(value = MaintainPlanServiceProxy.EXECUTE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintainPlanServiceProxy.EXECUTE)
    public Result execute(String id) {
        return maintainPlanService.execute(id);
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = MaintainPlanServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(MaintainPlanServiceProxy.EXPORT_EXCEL)
    public void exportExcel(MaintainPlanVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = maintainPlanService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = MaintainPlanServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(MaintainPlanServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = maintainPlanService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = MaintainPlanServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(MaintainPlanServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = maintainPlanService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
