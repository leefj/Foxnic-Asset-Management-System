package com.dt.platform.ops.controller;

import java.util.List;
import com.dt.platform.domain.ops.Voucher;
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
import com.dt.platform.proxy.ops.VoucherHistoryServiceProxy;
import com.dt.platform.domain.ops.meta.VoucherHistoryVOMeta;
import com.dt.platform.domain.ops.VoucherHistory;
import com.dt.platform.domain.ops.VoucherHistoryVO;
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
import com.dt.platform.domain.ops.meta.VoucherHistoryMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.IVoucherHistoryService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2021-09-04 00:03:34
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("OpsVoucherHistoryController")
public class VoucherHistoryController extends SuperController {

    @Autowired
    private IVoucherHistoryService voucherHistoryService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = VoucherHistoryVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "486324711886688256"),
		@ApiImplicitParam(name = VoucherHistoryVOMeta.VOUCHER_ID, value = "??????", required = false, dataTypeClass = String.class, example = "486324684015538176"),
		@ApiImplicitParam(name = VoucherHistoryVOMeta.USER_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "db2inst1"),
		@ApiImplicitParam(name = VoucherHistoryVOMeta.VOUCHER, value = "??????", required = false, dataTypeClass = String.class, example = "1212asfadf"),
		@ApiImplicitParam(name = VoucherHistoryVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1212")
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = VoucherHistoryServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(VoucherHistoryServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(VoucherHistoryVO voucherHistoryVO) {
        Result result = voucherHistoryService.insert(voucherHistoryVO);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = VoucherHistoryVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "486324711886688256")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = VoucherHistoryServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(VoucherHistoryServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = voucherHistoryService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = VoucherHistoryVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = VoucherHistoryServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(VoucherHistoryServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = voucherHistoryService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = VoucherHistoryVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "486324711886688256"),
		@ApiImplicitParam(name = VoucherHistoryVOMeta.VOUCHER_ID, value = "??????", required = false, dataTypeClass = String.class, example = "486324684015538176"),
		@ApiImplicitParam(name = VoucherHistoryVOMeta.USER_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "db2inst1"),
		@ApiImplicitParam(name = VoucherHistoryVOMeta.VOUCHER, value = "??????", required = false, dataTypeClass = String.class, example = "1212asfadf"),
		@ApiImplicitParam(name = VoucherHistoryVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1212")
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { VoucherHistoryVOMeta.PAGE_INDEX, VoucherHistoryVOMeta.PAGE_SIZE, VoucherHistoryVOMeta.SEARCH_FIELD, VoucherHistoryVOMeta.FUZZY_FIELD, VoucherHistoryVOMeta.SEARCH_VALUE, VoucherHistoryVOMeta.SORT_FIELD, VoucherHistoryVOMeta.SORT_TYPE, VoucherHistoryVOMeta.IDS })
    @SentinelResource(value = VoucherHistoryServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(VoucherHistoryServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(VoucherHistoryVO voucherHistoryVO) {
        Result result = voucherHistoryService.update(voucherHistoryVO, SaveMode.NOT_NULL_FIELDS);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = VoucherHistoryVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "486324711886688256"),
		@ApiImplicitParam(name = VoucherHistoryVOMeta.VOUCHER_ID, value = "??????", required = false, dataTypeClass = String.class, example = "486324684015538176"),
		@ApiImplicitParam(name = VoucherHistoryVOMeta.USER_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "db2inst1"),
		@ApiImplicitParam(name = VoucherHistoryVOMeta.VOUCHER, value = "??????", required = false, dataTypeClass = String.class, example = "1212asfadf"),
		@ApiImplicitParam(name = VoucherHistoryVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1212")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { VoucherHistoryVOMeta.PAGE_INDEX, VoucherHistoryVOMeta.PAGE_SIZE, VoucherHistoryVOMeta.SEARCH_FIELD, VoucherHistoryVOMeta.FUZZY_FIELD, VoucherHistoryVOMeta.SEARCH_VALUE, VoucherHistoryVOMeta.SORT_FIELD, VoucherHistoryVOMeta.SORT_TYPE, VoucherHistoryVOMeta.IDS })
    @SentinelResource(value = VoucherHistoryServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(VoucherHistoryServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(VoucherHistoryVO voucherHistoryVO) {
        Result result = voucherHistoryService.save(voucherHistoryVO, SaveMode.NOT_NULL_FIELDS);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = VoucherHistoryVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = VoucherHistoryServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(VoucherHistoryServiceProxy.GET_BY_ID)
    public Result<VoucherHistory> getById(String id) {
        Result<VoucherHistory> result = new Result<>();
        VoucherHistory voucherHistory = voucherHistoryService.getById(id);
        result.success(true).data(voucherHistory);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = VoucherHistoryVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = VoucherHistoryServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(VoucherHistoryServiceProxy.GET_BY_IDS)
    public Result<List<VoucherHistory>> getByIds(List<String> ids) {
        Result<List<VoucherHistory>> result = new Result<>();
        List<VoucherHistory> list = voucherHistoryService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = VoucherHistoryVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "486324711886688256"),
		@ApiImplicitParam(name = VoucherHistoryVOMeta.VOUCHER_ID, value = "??????", required = false, dataTypeClass = String.class, example = "486324684015538176"),
		@ApiImplicitParam(name = VoucherHistoryVOMeta.USER_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "db2inst1"),
		@ApiImplicitParam(name = VoucherHistoryVOMeta.VOUCHER, value = "??????", required = false, dataTypeClass = String.class, example = "1212asfadf"),
		@ApiImplicitParam(name = VoucherHistoryVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1212")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { VoucherHistoryVOMeta.PAGE_INDEX, VoucherHistoryVOMeta.PAGE_SIZE })
    @SentinelResource(value = VoucherHistoryServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(VoucherHistoryServiceProxy.QUERY_LIST)
    public Result<List<VoucherHistory>> queryList(VoucherHistoryVO sample) {
        Result<List<VoucherHistory>> result = new Result<>();
        List<VoucherHistory> list = voucherHistoryService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = VoucherHistoryVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "486324711886688256"),
		@ApiImplicitParam(name = VoucherHistoryVOMeta.VOUCHER_ID, value = "??????", required = false, dataTypeClass = String.class, example = "486324684015538176"),
		@ApiImplicitParam(name = VoucherHistoryVOMeta.USER_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "db2inst1"),
		@ApiImplicitParam(name = VoucherHistoryVOMeta.VOUCHER, value = "??????", required = false, dataTypeClass = String.class, example = "1212asfadf"),
		@ApiImplicitParam(name = VoucherHistoryVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1212")
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = VoucherHistoryServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(VoucherHistoryServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<VoucherHistory>> queryPagedList(VoucherHistoryVO sample) {
        Result<PagedList<VoucherHistory>> result = new Result<>();
        PagedList<VoucherHistory> list = voucherHistoryService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = VoucherHistoryServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(VoucherHistoryServiceProxy.EXPORT_EXCEL)
    public void exportExcel(VoucherHistoryVO sample, HttpServletResponse response) throws Exception {
        // ?????? Excel ??????
        ExcelWriter ew = voucherHistoryService.exportExcel(sample);
        // ??????
        DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = VoucherHistoryServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(VoucherHistoryServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        // ?????? Excel ??????
        ExcelWriter ew = voucherHistoryService.exportExcelTemplate();
        // ??????
        DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
    }

    @SentinelResource(value = VoucherHistoryServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(VoucherHistoryServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = voucherHistoryService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
