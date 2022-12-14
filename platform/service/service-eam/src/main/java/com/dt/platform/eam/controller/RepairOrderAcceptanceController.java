package com.dt.platform.eam.controller;

import java.util.List;
import com.dt.platform.domain.eam.meta.RepairOrderActVOMeta;
import com.dt.platform.proxy.eam.RepairOrderActServiceProxy;
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
import com.dt.platform.proxy.eam.RepairOrderAcceptanceServiceProxy;
import com.dt.platform.domain.eam.meta.RepairOrderAcceptanceVOMeta;
import com.dt.platform.domain.eam.RepairOrderAcceptance;
import com.dt.platform.domain.eam.RepairOrderAcceptanceVO;
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
import com.dt.platform.domain.eam.meta.RepairOrderAcceptanceMeta;
import java.math.BigDecimal;
import com.dt.platform.domain.eam.RepairOrder;
import com.dt.platform.domain.eam.RepairOrderAct;
import org.github.foxnic.web.domain.hrm.Employee;
import org.github.foxnic.web.domain.system.DictItem;
import com.dt.platform.domain.eam.RepairCategoryTpl;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IRepairOrderAcceptanceService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-06-01 07:17:16
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("EamRepairOrderAcceptanceController")
public class RepairOrderAcceptanceController extends SuperController {

    @Autowired
    private IRepairOrderAcceptanceService repairOrderAcceptanceService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "584061685996716032"),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.ORDER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.ORDER_ACT_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.RESULT_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "repaired"),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.ACCEPTER_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "558321538131034112"),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.CATEGORY_TPL_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "583710499342909440"),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.ACTUAL_COST, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.FINISH_TIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-05-31 12:00:00"),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.NOTES, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.PICTURE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = RepairOrderAcceptanceServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairOrderAcceptanceServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(RepairOrderAcceptanceVO repairOrderAcceptanceVO) {
        Result result = repairOrderAcceptanceService.insert(repairOrderAcceptanceVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "584061685996716032")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = RepairOrderAcceptanceServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairOrderAcceptanceServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = repairOrderAcceptanceService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = RepairOrderAcceptanceServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairOrderAcceptanceServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = repairOrderAcceptanceService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "584061685996716032"),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.ORDER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.ORDER_ACT_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.RESULT_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "repaired"),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.ACCEPTER_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "558321538131034112"),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.CATEGORY_TPL_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "583710499342909440"),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.ACTUAL_COST, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.FINISH_TIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-05-31 12:00:00"),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.NOTES, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.PICTURE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { RepairOrderAcceptanceVOMeta.PAGE_INDEX, RepairOrderAcceptanceVOMeta.PAGE_SIZE, RepairOrderAcceptanceVOMeta.SEARCH_FIELD, RepairOrderAcceptanceVOMeta.FUZZY_FIELD, RepairOrderAcceptanceVOMeta.SEARCH_VALUE, RepairOrderAcceptanceVOMeta.DIRTY_FIELDS, RepairOrderAcceptanceVOMeta.SORT_FIELD, RepairOrderAcceptanceVOMeta.SORT_TYPE, RepairOrderAcceptanceVOMeta.IDS })
    @SentinelResource(value = RepairOrderAcceptanceServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairOrderAcceptanceServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(RepairOrderAcceptanceVO repairOrderAcceptanceVO) {
        Result result = repairOrderAcceptanceService.update(repairOrderAcceptanceVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "584061685996716032"),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.ORDER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.ORDER_ACT_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.RESULT_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "repaired"),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.ACCEPTER_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "558321538131034112"),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.CATEGORY_TPL_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "583710499342909440"),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.ACTUAL_COST, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.FINISH_TIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-05-31 12:00:00"),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.NOTES, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.PICTURE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { RepairOrderAcceptanceVOMeta.PAGE_INDEX, RepairOrderAcceptanceVOMeta.PAGE_SIZE, RepairOrderAcceptanceVOMeta.SEARCH_FIELD, RepairOrderAcceptanceVOMeta.FUZZY_FIELD, RepairOrderAcceptanceVOMeta.SEARCH_VALUE, RepairOrderAcceptanceVOMeta.DIRTY_FIELDS, RepairOrderAcceptanceVOMeta.SORT_FIELD, RepairOrderAcceptanceVOMeta.SORT_TYPE, RepairOrderAcceptanceVOMeta.IDS })
    @SentinelResource(value = RepairOrderAcceptanceServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairOrderAcceptanceServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(RepairOrderAcceptanceVO repairOrderAcceptanceVO) {
        Result result = repairOrderAcceptanceService.save(repairOrderAcceptanceVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = RepairOrderAcceptanceServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairOrderAcceptanceServiceProxy.GET_BY_ID)
    public Result<RepairOrderAcceptance> getById(String id) {
        Result<RepairOrderAcceptance> result = new Result<>();
        RepairOrderAcceptance repairOrderAcceptance = repairOrderAcceptanceService.getById(id);
        // join ???????????????
        repairOrderAcceptanceService.dao().fill(repairOrderAcceptance).with("originator").with("accepter").with(RepairOrderAcceptanceMeta.RESULT_TYPE_DICT).with(RepairOrderAcceptanceMeta.CATEGORY_TPL).with(RepairOrderAcceptanceMeta.ORDER).execute();
        repairOrderAcceptanceService.dao().join(repairOrderAcceptance.getAccepter(), Person.class);
        repairOrderAcceptanceService.dao().join(repairOrderAcceptance.getOriginator(), Person.class);
        result.success(true).data(repairOrderAcceptance);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = RepairOrderAcceptanceServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairOrderAcceptanceServiceProxy.GET_BY_IDS)
    public Result<List<RepairOrderAcceptance>> getByIds(List<String> ids) {
        Result<List<RepairOrderAcceptance>> result = new Result<>();
        List<RepairOrderAcceptance> list = repairOrderAcceptanceService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "584061685996716032"),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.ORDER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.ORDER_ACT_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.RESULT_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "repaired"),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.ACCEPTER_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "558321538131034112"),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.CATEGORY_TPL_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "583710499342909440"),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.ACTUAL_COST, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.FINISH_TIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-05-31 12:00:00"),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.NOTES, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.PICTURE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { RepairOrderAcceptanceVOMeta.PAGE_INDEX, RepairOrderAcceptanceVOMeta.PAGE_SIZE })
    @SentinelResource(value = RepairOrderAcceptanceServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairOrderAcceptanceServiceProxy.QUERY_LIST)
    public Result<List<RepairOrderAcceptance>> queryList(RepairOrderAcceptanceVO sample) {
        Result<List<RepairOrderAcceptance>> result = new Result<>();
        List<RepairOrderAcceptance> list = repairOrderAcceptanceService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "584061685996716032"),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.ORDER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.ORDER_ACT_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.RESULT_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "repaired"),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.ACCEPTER_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "558321538131034112"),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.CATEGORY_TPL_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "583710499342909440"),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.ACTUAL_COST, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.FINISH_TIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-05-31 12:00:00"),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.NOTES, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.PICTURE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = RepairOrderAcceptanceServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairOrderAcceptanceServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<RepairOrderAcceptance>> queryPagedList(RepairOrderAcceptanceVO sample) {
        Result<PagedList<RepairOrderAcceptance>> result = new Result<>();
        PagedList<RepairOrderAcceptance> list = repairOrderAcceptanceService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        repairOrderAcceptanceService.dao().fill(list).with("originator").with("accepter").with(RepairOrderAcceptanceMeta.RESULT_TYPE_DICT).with(RepairOrderAcceptanceMeta.CATEGORY_TPL).with(RepairOrderAcceptanceMeta.ORDER).execute();
        List<Employee> originator = CollectorUtil.collectList(list.getList(), RepairOrderAcceptance::getOriginator);
        repairOrderAcceptanceService.dao().join(originator, Person.class);
        List<Employee> executor = CollectorUtil.collectList(list.getList(), RepairOrderAcceptance::getAccepter);
        repairOrderAcceptanceService.dao().join(executor, Person.class);
        result.success(true).data(list);
        return result;
    }

    @ApiOperation(value = "")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairOrderAcceptanceVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "584022872884772864")
	})
    @ApiOperationSupport(order = 11)
    @SentinelResource(value = RepairOrderAcceptanceServiceProxy.ACCEPTANCE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairOrderAcceptanceServiceProxy.ACCEPTANCE)
    public Result acceptance(String id) {
        Result result = repairOrderAcceptanceService.acceptance(id);
        return result;
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = RepairOrderAcceptanceServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(RepairOrderAcceptanceServiceProxy.EXPORT_EXCEL)
    public void exportExcel(RepairOrderAcceptanceVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = repairOrderAcceptanceService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = RepairOrderAcceptanceServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(RepairOrderAcceptanceServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = repairOrderAcceptanceService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = RepairOrderAcceptanceServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(RepairOrderAcceptanceServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = repairOrderAcceptanceService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
