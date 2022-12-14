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
import com.dt.platform.proxy.eam.PurchaseCheckServiceProxy;
import com.dt.platform.domain.eam.meta.PurchaseCheckVOMeta;
import com.dt.platform.domain.eam.PurchaseCheck;
import com.dt.platform.domain.eam.PurchaseCheckVO;
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
import com.dt.platform.domain.eam.meta.PurchaseCheckMeta;
import com.dt.platform.domain.eam.Supplier;
import com.dt.platform.domain.eam.PurchaseApply;
import org.github.foxnic.web.domain.hrm.Organization;
import org.github.foxnic.web.domain.hrm.Employee;
import com.dt.platform.domain.eam.PurchaseOrder;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IPurchaseCheckService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-04-16 23:19:16
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("EamPurchaseCheckController")
public class PurchaseCheckController extends SuperController {

    @Autowired
    private IPurchaseCheckService purchaseCheckService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = PurchaseCheckVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.APPLY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.CODE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.SUPPLIER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.CHECK_ORG_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.CHECK_USER_NAME, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.RECEIVE_DATE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.CHECK_DATE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.CHECK_INFORMATION, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = PurchaseCheckServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(PurchaseCheckServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(PurchaseCheckVO purchaseCheckVO) {
        Result result = purchaseCheckService.insert(purchaseCheckVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = PurchaseCheckVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = PurchaseCheckServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(PurchaseCheckServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = purchaseCheckService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = PurchaseCheckVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = PurchaseCheckServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(PurchaseCheckServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = purchaseCheckService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = PurchaseCheckVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.APPLY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.CODE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.SUPPLIER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.CHECK_ORG_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.CHECK_USER_NAME, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.RECEIVE_DATE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.CHECK_DATE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.CHECK_INFORMATION, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { PurchaseCheckVOMeta.PAGE_INDEX, PurchaseCheckVOMeta.PAGE_SIZE, PurchaseCheckVOMeta.SEARCH_FIELD, PurchaseCheckVOMeta.FUZZY_FIELD, PurchaseCheckVOMeta.SEARCH_VALUE, PurchaseCheckVOMeta.DIRTY_FIELDS, PurchaseCheckVOMeta.SORT_FIELD, PurchaseCheckVOMeta.SORT_TYPE, PurchaseCheckVOMeta.IDS })
    @SentinelResource(value = PurchaseCheckServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(PurchaseCheckServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(PurchaseCheckVO purchaseCheckVO) {
        Result result = purchaseCheckService.update(purchaseCheckVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = PurchaseCheckVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.APPLY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.CODE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.SUPPLIER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.CHECK_ORG_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.CHECK_USER_NAME, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.RECEIVE_DATE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.CHECK_DATE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.CHECK_INFORMATION, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { PurchaseCheckVOMeta.PAGE_INDEX, PurchaseCheckVOMeta.PAGE_SIZE, PurchaseCheckVOMeta.SEARCH_FIELD, PurchaseCheckVOMeta.FUZZY_FIELD, PurchaseCheckVOMeta.SEARCH_VALUE, PurchaseCheckVOMeta.DIRTY_FIELDS, PurchaseCheckVOMeta.SORT_FIELD, PurchaseCheckVOMeta.SORT_TYPE, PurchaseCheckVOMeta.IDS })
    @SentinelResource(value = PurchaseCheckServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(PurchaseCheckServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(PurchaseCheckVO purchaseCheckVO) {
        Result result = purchaseCheckService.save(purchaseCheckVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = PurchaseCheckVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = PurchaseCheckServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(PurchaseCheckServiceProxy.GET_BY_ID)
    public Result<PurchaseCheck> getById(String id) {
        Result<PurchaseCheck> result = new Result<>();
        PurchaseCheck purchaseCheck = purchaseCheckService.getById(id);
        // join ???????????????
        purchaseCheckService.dao().fill(purchaseCheck).with("checkOrg").with(PurchaseCheckMeta.SUPPLIER).with(PurchaseCheckMeta.PURCHASE_APPLY).execute();
        result.success(true).data(purchaseCheck);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = PurchaseCheckVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = PurchaseCheckServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(PurchaseCheckServiceProxy.GET_BY_IDS)
    public Result<List<PurchaseCheck>> getByIds(List<String> ids) {
        Result<List<PurchaseCheck>> result = new Result<>();
        List<PurchaseCheck> list = purchaseCheckService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = PurchaseCheckVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.APPLY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.CODE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.SUPPLIER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.CHECK_ORG_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.CHECK_USER_NAME, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.RECEIVE_DATE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.CHECK_DATE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.CHECK_INFORMATION, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { PurchaseCheckVOMeta.PAGE_INDEX, PurchaseCheckVOMeta.PAGE_SIZE })
    @SentinelResource(value = PurchaseCheckServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(PurchaseCheckServiceProxy.QUERY_LIST)
    public Result<List<PurchaseCheck>> queryList(PurchaseCheckVO sample) {
        Result<List<PurchaseCheck>> result = new Result<>();
        List<PurchaseCheck> list = purchaseCheckService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = PurchaseCheckVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.APPLY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.CODE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.SUPPLIER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.CHECK_ORG_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.CHECK_USER_NAME, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.RECEIVE_DATE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.CHECK_DATE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.CHECK_INFORMATION, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseCheckVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = PurchaseCheckServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(PurchaseCheckServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<PurchaseCheck>> queryPagedList(PurchaseCheckVO sample) {
        Result<PagedList<PurchaseCheck>> result = new Result<>();
        PagedList<PurchaseCheck> list = purchaseCheckService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        purchaseCheckService.dao().fill(list).with("checkOrg").with(PurchaseCheckMeta.SUPPLIER).with(PurchaseCheckMeta.PURCHASE_APPLY).execute();
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = PurchaseCheckServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(PurchaseCheckServiceProxy.EXPORT_EXCEL)
    public void exportExcel(PurchaseCheckVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = purchaseCheckService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = PurchaseCheckServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(PurchaseCheckServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = purchaseCheckService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = PurchaseCheckServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(PurchaseCheckServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = purchaseCheckService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
