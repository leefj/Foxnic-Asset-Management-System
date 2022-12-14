package com.dt.platform.vehicle.controller;

import java.util.List;
import com.dt.platform.domain.vehicle.meta.ApplyMeta;
import com.dt.platform.domain.vehicle.meta.ApplyVOMeta;
import com.dt.platform.proxy.vehicle.ApplyServiceProxy;
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
import com.dt.platform.proxy.vehicle.MaintenanceServiceProxy;
import com.dt.platform.domain.vehicle.meta.MaintenanceVOMeta;
import com.dt.platform.domain.vehicle.Maintenance;
import com.dt.platform.domain.vehicle.MaintenanceVO;
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
import com.dt.platform.domain.vehicle.meta.MaintenanceMeta;
import java.math.BigDecimal;
import com.dt.platform.domain.vehicle.Info;
import org.github.foxnic.web.domain.hrm.Employee;
import org.github.foxnic.web.domain.system.DictItem;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.vehicle.service.IMaintenanceService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ?????????????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-04-02 19:58:39
 */
@Api(tags = "??????????????????")
@ApiSort(0)
@RestController("VehicleMaintenanceController")
public class MaintenanceController extends SuperController {

    @Autowired
    private IMaintenanceService maintenanceService;

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintenanceVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "562630687803314176"),
		@ApiImplicitParam(name = MaintenanceVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintenanceVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintenanceVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintenanceVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = MaintenanceVOMeta.REPAIR_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintenanceVOMeta.TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "Insurance"),
		@ApiImplicitParam(name = MaintenanceVOMeta.PLAN_FINISH_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class, example = "2022-03-29 12:00:00"),
		@ApiImplicitParam(name = MaintenanceVOMeta.ACTUAL_FINISH_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MaintenanceVOMeta.COST, value = "??????", required = false, dataTypeClass = BigDecimal.class, example = "1212.00"),
		@ApiImplicitParam(name = MaintenanceVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = MaintenanceVOMeta.REPORT_USER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintenanceVOMeta.REPORT_USER_NAME, value = "?????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = MaintenanceVOMeta.PICTURE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "562630674318626816"),
		@ApiImplicitParam(name = MaintenanceVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintenanceVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MaintenanceVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = MaintenanceServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintenanceServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(MaintenanceVO maintenanceVO) {
        Result result = maintenanceService.insert(maintenanceVO, false);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintenanceVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "562630687803314176")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = MaintenanceServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintenanceServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = maintenanceService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ?????????????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "??????????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintenanceVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = MaintenanceServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintenanceServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = maintenanceService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintenanceVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "562630687803314176"),
		@ApiImplicitParam(name = MaintenanceVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintenanceVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintenanceVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintenanceVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = MaintenanceVOMeta.REPAIR_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintenanceVOMeta.TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "Insurance"),
		@ApiImplicitParam(name = MaintenanceVOMeta.PLAN_FINISH_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class, example = "2022-03-29 12:00:00"),
		@ApiImplicitParam(name = MaintenanceVOMeta.ACTUAL_FINISH_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MaintenanceVOMeta.COST, value = "??????", required = false, dataTypeClass = BigDecimal.class, example = "1212.00"),
		@ApiImplicitParam(name = MaintenanceVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = MaintenanceVOMeta.REPORT_USER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintenanceVOMeta.REPORT_USER_NAME, value = "?????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = MaintenanceVOMeta.PICTURE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "562630674318626816"),
		@ApiImplicitParam(name = MaintenanceVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintenanceVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MaintenanceVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { MaintenanceVOMeta.PAGE_INDEX, MaintenanceVOMeta.PAGE_SIZE, MaintenanceVOMeta.SEARCH_FIELD, MaintenanceVOMeta.FUZZY_FIELD, MaintenanceVOMeta.SEARCH_VALUE, MaintenanceVOMeta.DIRTY_FIELDS, MaintenanceVOMeta.SORT_FIELD, MaintenanceVOMeta.SORT_TYPE, MaintenanceVOMeta.IDS })
    @SentinelResource(value = MaintenanceServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintenanceServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(MaintenanceVO maintenanceVO) {
        Result result = maintenanceService.update(maintenanceVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintenanceVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "562630687803314176"),
		@ApiImplicitParam(name = MaintenanceVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintenanceVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintenanceVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintenanceVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = MaintenanceVOMeta.REPAIR_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintenanceVOMeta.TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "Insurance"),
		@ApiImplicitParam(name = MaintenanceVOMeta.PLAN_FINISH_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class, example = "2022-03-29 12:00:00"),
		@ApiImplicitParam(name = MaintenanceVOMeta.ACTUAL_FINISH_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MaintenanceVOMeta.COST, value = "??????", required = false, dataTypeClass = BigDecimal.class, example = "1212.00"),
		@ApiImplicitParam(name = MaintenanceVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = MaintenanceVOMeta.REPORT_USER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintenanceVOMeta.REPORT_USER_NAME, value = "?????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = MaintenanceVOMeta.PICTURE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "562630674318626816"),
		@ApiImplicitParam(name = MaintenanceVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintenanceVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MaintenanceVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { MaintenanceVOMeta.PAGE_INDEX, MaintenanceVOMeta.PAGE_SIZE, MaintenanceVOMeta.SEARCH_FIELD, MaintenanceVOMeta.FUZZY_FIELD, MaintenanceVOMeta.SEARCH_VALUE, MaintenanceVOMeta.DIRTY_FIELDS, MaintenanceVOMeta.SORT_FIELD, MaintenanceVOMeta.SORT_TYPE, MaintenanceVOMeta.IDS })
    @SentinelResource(value = MaintenanceServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintenanceServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(MaintenanceVO maintenanceVO) {
        Result result = maintenanceService.save(maintenanceVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintenanceVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = MaintenanceServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintenanceServiceProxy.GET_BY_ID)
    public Result<Maintenance> getById(String id) {
        Result<Maintenance> result = new Result<>();
        Maintenance maintenance = maintenanceService.getById(id);
        // join ???????????????
        maintenanceService.dao().fill(maintenance).with(MaintenanceMeta.MAINTENANCE_DICT).with(ApplyMeta.VEHICLE_INFO_LIST).execute();
        result.success(true).data(maintenance);
        return result;
    }

    /**
     * ?????????????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "??????????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintenanceVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = MaintenanceServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintenanceServiceProxy.GET_BY_IDS)
    public Result<List<Maintenance>> getByIds(List<String> ids) {
        Result<List<Maintenance>> result = new Result<>();
        List<Maintenance> list = maintenanceService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintenanceVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "562630687803314176"),
		@ApiImplicitParam(name = MaintenanceVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintenanceVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintenanceVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintenanceVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = MaintenanceVOMeta.REPAIR_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintenanceVOMeta.TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "Insurance"),
		@ApiImplicitParam(name = MaintenanceVOMeta.PLAN_FINISH_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class, example = "2022-03-29 12:00:00"),
		@ApiImplicitParam(name = MaintenanceVOMeta.ACTUAL_FINISH_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MaintenanceVOMeta.COST, value = "??????", required = false, dataTypeClass = BigDecimal.class, example = "1212.00"),
		@ApiImplicitParam(name = MaintenanceVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = MaintenanceVOMeta.REPORT_USER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintenanceVOMeta.REPORT_USER_NAME, value = "?????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = MaintenanceVOMeta.PICTURE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "562630674318626816"),
		@ApiImplicitParam(name = MaintenanceVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintenanceVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MaintenanceVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { MaintenanceVOMeta.PAGE_INDEX, MaintenanceVOMeta.PAGE_SIZE })
    @SentinelResource(value = MaintenanceServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintenanceServiceProxy.QUERY_LIST)
    public Result<List<Maintenance>> queryList(MaintenanceVO sample) {
        Result<List<Maintenance>> result = new Result<>();
        List<Maintenance> list = maintenanceService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????????????????
     */
    @ApiOperation(value = "??????????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintenanceVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "562630687803314176"),
		@ApiImplicitParam(name = MaintenanceVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintenanceVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintenanceVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintenanceVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = MaintenanceVOMeta.REPAIR_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintenanceVOMeta.TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "Insurance"),
		@ApiImplicitParam(name = MaintenanceVOMeta.PLAN_FINISH_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class, example = "2022-03-29 12:00:00"),
		@ApiImplicitParam(name = MaintenanceVOMeta.ACTUAL_FINISH_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MaintenanceVOMeta.COST, value = "??????", required = false, dataTypeClass = BigDecimal.class, example = "1212.00"),
		@ApiImplicitParam(name = MaintenanceVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = MaintenanceVOMeta.REPORT_USER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintenanceVOMeta.REPORT_USER_NAME, value = "?????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = MaintenanceVOMeta.PICTURE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "562630674318626816"),
		@ApiImplicitParam(name = MaintenanceVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintenanceVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = MaintenanceVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = MaintenanceServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintenanceServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<Maintenance>> queryPagedList(MaintenanceVO sample) {
        Result<PagedList<Maintenance>> result = new Result<>();
        PagedList<Maintenance> list = maintenanceService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        maintenanceService.dao().fill(list).with(MaintenanceMeta.MAINTENANCE_DICT).with(ApplyMeta.VEHICLE_INFO_LIST).execute();
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintenanceVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "562620443333234688")
	})
    @ApiOperationSupport(order = 9)
    @SentinelResource(value = MaintenanceServiceProxy.CONFIRM, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintenanceServiceProxy.CONFIRM)
    public Result confirm(String id) {
        Result result = maintenanceService.confirm(id);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintenanceVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "562620443333234688")
	})
    @ApiOperationSupport(order = 10)
    @SentinelResource(value = MaintenanceServiceProxy.CANCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintenanceServiceProxy.CANCEL)
    public Result cancel(String id) {
        Result result = maintenanceService.cancel(id);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintenanceVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "562620443333234688")
	})
    @ApiOperationSupport(order = 10)
    @SentinelResource(value = MaintenanceServiceProxy.FINISH, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintenanceServiceProxy.FINISH)
    public Result finish(String id) {
        Result result = maintenanceService.finish(id);
        return result;
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = MaintenanceServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(MaintenanceServiceProxy.EXPORT_EXCEL)
    public void exportExcel(MaintenanceVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = maintenanceService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = MaintenanceServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(MaintenanceServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = maintenanceService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = MaintenanceServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(MaintenanceServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = maintenanceService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
