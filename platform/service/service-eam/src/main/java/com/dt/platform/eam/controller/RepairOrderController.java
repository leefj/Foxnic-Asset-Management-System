package com.dt.platform.eam.controller;

import java.util.List;
import com.dt.platform.domain.eam.*;
import com.dt.platform.domain.eam.meta.AssetRepairVOMeta;
import com.dt.platform.proxy.eam.AssetRepairServiceProxy;
import com.github.foxnic.commons.collection.CollectorUtil;
import org.github.foxnic.web.domain.changes.ProcessApproveVO;
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
import com.dt.platform.proxy.eam.RepairOrderServiceProxy;
import com.dt.platform.domain.eam.meta.RepairOrderVOMeta;
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
import com.dt.platform.domain.eam.meta.RepairOrderMeta;
import java.math.BigDecimal;
import org.github.foxnic.web.domain.hrm.Employee;
import org.github.foxnic.web.domain.hrm.Organization;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IRepairOrderService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-05-30 19:33:23
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("EamRepairOrderController")
public class RepairOrderController extends SuperController {

    @Autowired
    private IRepairOrderService repairOrderService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairOrderVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.REPAIR_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.CATEGORY_TPL_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.REPAIR_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.URGENCY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.REPORT_ORG_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.REPORT_USER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.REPAIR_COST, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.PLAN_FINISH_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.PICTURE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = RepairOrderServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairOrderServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(RepairOrderVO repairOrderVO) {
        Result result = repairOrderService.insert(repairOrderVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairOrderVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = RepairOrderServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairOrderServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = repairOrderService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairOrderVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = RepairOrderServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairOrderServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = repairOrderService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairOrderVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.REPAIR_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.CATEGORY_TPL_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.REPAIR_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.URGENCY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.REPORT_ORG_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.REPORT_USER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.REPAIR_COST, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.PLAN_FINISH_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.PICTURE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { RepairOrderVOMeta.PAGE_INDEX, RepairOrderVOMeta.PAGE_SIZE, RepairOrderVOMeta.SEARCH_FIELD, RepairOrderVOMeta.FUZZY_FIELD, RepairOrderVOMeta.SEARCH_VALUE, RepairOrderVOMeta.DIRTY_FIELDS, RepairOrderVOMeta.SORT_FIELD, RepairOrderVOMeta.SORT_TYPE, RepairOrderVOMeta.IDS })
    @SentinelResource(value = RepairOrderServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairOrderServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(RepairOrderVO repairOrderVO) {
        Result result = repairOrderService.update(repairOrderVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairOrderVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.REPAIR_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.CATEGORY_TPL_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.REPAIR_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.URGENCY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.REPORT_ORG_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.REPORT_USER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.REPAIR_COST, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.PLAN_FINISH_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.PICTURE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { RepairOrderVOMeta.PAGE_INDEX, RepairOrderVOMeta.PAGE_SIZE, RepairOrderVOMeta.SEARCH_FIELD, RepairOrderVOMeta.FUZZY_FIELD, RepairOrderVOMeta.SEARCH_VALUE, RepairOrderVOMeta.DIRTY_FIELDS, RepairOrderVOMeta.SORT_FIELD, RepairOrderVOMeta.SORT_TYPE, RepairOrderVOMeta.IDS })
    @SentinelResource(value = RepairOrderServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairOrderServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(RepairOrderVO repairOrderVO) {
        Result result = repairOrderService.save(repairOrderVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairOrderVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = RepairOrderServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairOrderServiceProxy.GET_BY_ID)
    public Result<RepairOrder> getById(String id) {
        Result<RepairOrder> result = new Result<>();
        RepairOrder repairOrder = repairOrderService.getById(id);
        // join ???????????????
        repairOrderService.dao().fill(repairOrder).with("originator").with("organization").with("reportUser").with(RepairOrderMeta.CATEGORY_TPL).with(RepairOrderMeta.REPAIR_URGENCY).execute();
        repairOrderService.dao().join(repairOrder.getReportUser(), Person.class);
        repairOrderService.dao().join(repairOrder.getOriginator(), Person.class);
        result.success(true).data(repairOrder);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairOrderVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = RepairOrderServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairOrderServiceProxy.GET_BY_IDS)
    public Result<List<RepairOrder>> getByIds(List<String> ids) {
        Result<List<RepairOrder>> result = new Result<>();
        List<RepairOrder> list = repairOrderService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairOrderVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.REPAIR_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.CATEGORY_TPL_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.REPAIR_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.URGENCY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.REPORT_ORG_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.REPORT_USER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.REPAIR_COST, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.PLAN_FINISH_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.PICTURE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { RepairOrderVOMeta.PAGE_INDEX, RepairOrderVOMeta.PAGE_SIZE })
    @SentinelResource(value = RepairOrderServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairOrderServiceProxy.QUERY_LIST)
    public Result<List<RepairOrder>> queryList(RepairOrderVO sample) {
        Result<List<RepairOrder>> result = new Result<>();
        List<RepairOrder> list = repairOrderService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairOrderVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.REPAIR_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.CATEGORY_TPL_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.REPAIR_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.URGENCY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.REPORT_ORG_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.REPORT_USER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.REPAIR_COST, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.PLAN_FINISH_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.PICTURE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = RepairOrderVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = RepairOrderServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairOrderServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<RepairOrder>> queryPagedList(RepairOrderVO sample) {
        Result<PagedList<RepairOrder>> result = new Result<>();
        PagedList<RepairOrder> list = repairOrderService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        repairOrderService.dao().fill(list).with("originator").with("organization").with("reportUser").with(RepairOrderMeta.CATEGORY_TPL).with(RepairOrderMeta.REPAIR_URGENCY).execute();
        List<Employee> originator = CollectorUtil.collectList(list.getList(), RepairOrder::getOriginator);
        repairOrderService.dao().join(originator, Person.class);
        List<Employee> reportUser = CollectorUtil.collectList(list.getList(), RepairOrder::getReportUser);
        repairOrderService.dao().join(reportUser, Person.class);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairOrderVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 13)
    @SentinelResource(value = RepairOrderServiceProxy.CONFIRM_OPERATION, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(RepairOrderServiceProxy.CONFIRM_OPERATION)
    public Result confirmOperation(String id) {
        return repairOrderService.confirmOperation(id);
    }

    /**
     * ??????
     */
    @ApiOperation(value = "??????")
    @ApiOperationSupport(order = 16)
    @SentinelResource(value = RepairOrderServiceProxy.DISPATCH, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(RepairOrderServiceProxy.DISPATCH)
    public Result dispatchOrder(List<String> ids, String actId) {
        return repairOrderService.dispatchOrder(ids, actId);
    }

    /**
     * ??????
     */
    @ApiOperation(value = "????????????")
    @ApiOperationSupport(order = 17)
    @SentinelResource(value = RepairOrderServiceProxy.VALIDATE_DISPATCH_ORDER, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(RepairOrderServiceProxy.VALIDATE_DISPATCH_ORDER)
    public Result validateDispatchOrder(List<String> ids) {
        return repairOrderService.validateDispatchOrder(ids);
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = RepairOrderServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(RepairOrderServiceProxy.EXPORT_EXCEL)
    public void exportExcel(RepairOrderVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = repairOrderService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = RepairOrderServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(RepairOrderServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = repairOrderService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = RepairOrderServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(RepairOrderServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = repairOrderService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
