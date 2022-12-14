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
import com.dt.platform.proxy.eam.AssetHandleServiceProxy;
import com.dt.platform.domain.eam.meta.AssetHandleVOMeta;
import com.dt.platform.domain.eam.AssetHandle;
import com.dt.platform.domain.eam.AssetHandleVO;
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
import com.dt.platform.domain.eam.meta.AssetHandleMeta;
import java.math.BigDecimal;
import com.dt.platform.domain.eam.Asset;
import org.github.foxnic.web.domain.hrm.Employee;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IAssetHandleService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2021-10-26 15:27:45
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("EamAssetHandleController")
public class AssetHandleController extends SuperController {

    @Autowired
    private IAssetHandleService assetHandleService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetHandleVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.CONTENT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.HANDLE_NUMBER, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.HANDLE_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.PLAN_FINISH_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.ACTUAL_FINISH_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.PICTURE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.CRD_ACTION, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = AssetHandleServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetHandleServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(AssetHandleVO assetHandleVO) {
        Result result = assetHandleService.insert(assetHandleVO);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetHandleVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = AssetHandleServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetHandleServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = assetHandleService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetHandleVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetHandleServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetHandleServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = assetHandleService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetHandleVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.CONTENT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.HANDLE_NUMBER, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.HANDLE_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.PLAN_FINISH_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.ACTUAL_FINISH_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.PICTURE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.CRD_ACTION, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { AssetHandleVOMeta.PAGE_INDEX, AssetHandleVOMeta.PAGE_SIZE, AssetHandleVOMeta.SEARCH_FIELD, AssetHandleVOMeta.FUZZY_FIELD, AssetHandleVOMeta.SEARCH_VALUE, AssetHandleVOMeta.SORT_FIELD, AssetHandleVOMeta.SORT_TYPE, AssetHandleVOMeta.IDS })
    @SentinelResource(value = AssetHandleServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetHandleServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(AssetHandleVO assetHandleVO) {
        Result result = assetHandleService.update(assetHandleVO, SaveMode.NOT_NULL_FIELDS);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetHandleVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.CONTENT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.HANDLE_NUMBER, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.HANDLE_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.PLAN_FINISH_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.ACTUAL_FINISH_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.PICTURE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.CRD_ACTION, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetHandleVOMeta.PAGE_INDEX, AssetHandleVOMeta.PAGE_SIZE, AssetHandleVOMeta.SEARCH_FIELD, AssetHandleVOMeta.FUZZY_FIELD, AssetHandleVOMeta.SEARCH_VALUE, AssetHandleVOMeta.SORT_FIELD, AssetHandleVOMeta.SORT_TYPE, AssetHandleVOMeta.IDS })
    @SentinelResource(value = AssetHandleServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetHandleServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(AssetHandleVO assetHandleVO) {
        Result result = assetHandleService.save(assetHandleVO, SaveMode.NOT_NULL_FIELDS);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetHandleVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = AssetHandleServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetHandleServiceProxy.GET_BY_ID)
    public Result<AssetHandle> getById(String id) {
        Result<AssetHandle> result = new Result<>();
        AssetHandle assetHandle = assetHandleService.getById(id);
        // join ???????????????
        assetHandleService.dao().fill(assetHandle).execute();
        result.success(true).data(assetHandle);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetHandleVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetHandleServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetHandleServiceProxy.GET_BY_IDS)
    public Result<List<AssetHandle>> getByIds(List<String> ids) {
        Result<List<AssetHandle>> result = new Result<>();
        List<AssetHandle> list = assetHandleService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetHandleVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.CONTENT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.HANDLE_NUMBER, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.HANDLE_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.PLAN_FINISH_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.ACTUAL_FINISH_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.PICTURE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.CRD_ACTION, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetHandleVOMeta.PAGE_INDEX, AssetHandleVOMeta.PAGE_SIZE })
    @SentinelResource(value = AssetHandleServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetHandleServiceProxy.QUERY_LIST)
    public Result<List<AssetHandle>> queryList(AssetHandleVO sample) {
        Result<List<AssetHandle>> result = new Result<>();
        List<AssetHandle> list = assetHandleService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetHandleVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.CONTENT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.HANDLE_NUMBER, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.HANDLE_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.PLAN_FINISH_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.ACTUAL_FINISH_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.PICTURE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.CRD_ACTION, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetHandleVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = AssetHandleServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetHandleServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<AssetHandle>> queryPagedList(AssetHandleVO sample) {
        Result<PagedList<AssetHandle>> result = new Result<>();
        PagedList<AssetHandle> list = assetHandleService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        assetHandleService.dao().fill(list).execute();
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = AssetHandleServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetHandleServiceProxy.EXPORT_EXCEL)
    public void exportExcel(AssetHandleVO sample, HttpServletResponse response) throws Exception {
        // ?????? Excel ??????
        ExcelWriter ew = assetHandleService.exportExcel(sample);
        // ??????
        DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = AssetHandleServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetHandleServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        // ?????? Excel ??????
        ExcelWriter ew = assetHandleService.exportExcelTemplate();
        // ??????
        DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
    }

    @SentinelResource(value = AssetHandleServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetHandleServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = assetHandleService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
