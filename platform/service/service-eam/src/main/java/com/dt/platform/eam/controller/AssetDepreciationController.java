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
import com.dt.platform.proxy.eam.AssetDepreciationServiceProxy;
import com.dt.platform.domain.eam.meta.AssetDepreciationVOMeta;
import com.dt.platform.domain.eam.AssetDepreciation;
import com.dt.platform.domain.eam.AssetDepreciationVO;
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
import com.dt.platform.domain.eam.meta.AssetDepreciationMeta;
import java.math.BigDecimal;
import org.github.foxnic.web.domain.pcm.Catalog;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IAssetDepreciationService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-05-03 14:39:47
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("EamAssetDepreciationController")
public class AssetDepreciationController extends SuperController {

    @Autowired
    private IAssetDepreciationService assetDepreciationService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetDepreciationVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "573537666012807168"),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "?????????"),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.METHOD, value = "????????????", required = false, dataTypeClass = String.class, example = "average_age"),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.PRE_RESIDUAL_RATE, value = "???????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.OWN_COMPANY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.CATEGORY_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "486918386278731776,486918387960647681,486918449189097473,486918029091803137,486918073320738816,486918122771582977,486918164815286273,501736778307207168,486918240887377921,501737031911604224,486918241663324161,486918242527350784,501737164271255553,501737200690397185,501737355338579969"),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.RESIDUAL_VALUE_SELECT, value = "????????????", required = false, dataTypeClass = String.class, example = "follow_asset"),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.FIRST_DEPRECIATION_METHOD, value = "??????????????????", required = false, dataTypeClass = String.class, example = "purchase_next_month"),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = AssetDepreciationServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDepreciationServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(AssetDepreciationVO assetDepreciationVO) {
        Result result = assetDepreciationService.insert(assetDepreciationVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetDepreciationVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "573537666012807168")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = AssetDepreciationServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDepreciationServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = assetDepreciationService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetDepreciationVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetDepreciationServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDepreciationServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = assetDepreciationService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetDepreciationVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "573537666012807168"),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "?????????"),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.METHOD, value = "????????????", required = false, dataTypeClass = String.class, example = "average_age"),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.PRE_RESIDUAL_RATE, value = "???????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.OWN_COMPANY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.CATEGORY_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "486918386278731776,486918387960647681,486918449189097473,486918029091803137,486918073320738816,486918122771582977,486918164815286273,501736778307207168,486918240887377921,501737031911604224,486918241663324161,486918242527350784,501737164271255553,501737200690397185,501737355338579969"),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.RESIDUAL_VALUE_SELECT, value = "????????????", required = false, dataTypeClass = String.class, example = "follow_asset"),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.FIRST_DEPRECIATION_METHOD, value = "??????????????????", required = false, dataTypeClass = String.class, example = "purchase_next_month"),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { AssetDepreciationVOMeta.PAGE_INDEX, AssetDepreciationVOMeta.PAGE_SIZE, AssetDepreciationVOMeta.SEARCH_FIELD, AssetDepreciationVOMeta.FUZZY_FIELD, AssetDepreciationVOMeta.SEARCH_VALUE, AssetDepreciationVOMeta.DIRTY_FIELDS, AssetDepreciationVOMeta.SORT_FIELD, AssetDepreciationVOMeta.SORT_TYPE, AssetDepreciationVOMeta.IDS })
    @SentinelResource(value = AssetDepreciationServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDepreciationServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(AssetDepreciationVO assetDepreciationVO) {
        Result result = assetDepreciationService.update(assetDepreciationVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetDepreciationVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "573537666012807168"),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "?????????"),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.METHOD, value = "????????????", required = false, dataTypeClass = String.class, example = "average_age"),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.PRE_RESIDUAL_RATE, value = "???????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.OWN_COMPANY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.CATEGORY_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "486918386278731776,486918387960647681,486918449189097473,486918029091803137,486918073320738816,486918122771582977,486918164815286273,501736778307207168,486918240887377921,501737031911604224,486918241663324161,486918242527350784,501737164271255553,501737200690397185,501737355338579969"),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.RESIDUAL_VALUE_SELECT, value = "????????????", required = false, dataTypeClass = String.class, example = "follow_asset"),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.FIRST_DEPRECIATION_METHOD, value = "??????????????????", required = false, dataTypeClass = String.class, example = "purchase_next_month"),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetDepreciationVOMeta.PAGE_INDEX, AssetDepreciationVOMeta.PAGE_SIZE, AssetDepreciationVOMeta.SEARCH_FIELD, AssetDepreciationVOMeta.FUZZY_FIELD, AssetDepreciationVOMeta.SEARCH_VALUE, AssetDepreciationVOMeta.DIRTY_FIELDS, AssetDepreciationVOMeta.SORT_FIELD, AssetDepreciationVOMeta.SORT_TYPE, AssetDepreciationVOMeta.IDS })
    @SentinelResource(value = AssetDepreciationServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDepreciationServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(AssetDepreciationVO assetDepreciationVO) {
        Result result = assetDepreciationService.save(assetDepreciationVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetDepreciationVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = AssetDepreciationServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDepreciationServiceProxy.GET_BY_ID)
    public Result<AssetDepreciation> getById(String id) {
        Result<AssetDepreciation> result = new Result<>();
        AssetDepreciation assetDepreciation = assetDepreciationService.getById(id);
        // join ???????????????
        assetDepreciationService.dao().fill(assetDepreciation).with(AssetDepreciationMeta.CATEGORY).execute();
        result.success(true).data(assetDepreciation);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetDepreciationVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetDepreciationServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDepreciationServiceProxy.GET_BY_IDS)
    public Result<List<AssetDepreciation>> getByIds(List<String> ids) {
        Result<List<AssetDepreciation>> result = new Result<>();
        List<AssetDepreciation> list = assetDepreciationService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetDepreciationVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "573537666012807168"),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "?????????"),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.METHOD, value = "????????????", required = false, dataTypeClass = String.class, example = "average_age"),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.PRE_RESIDUAL_RATE, value = "???????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.OWN_COMPANY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.CATEGORY_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "486918386278731776,486918387960647681,486918449189097473,486918029091803137,486918073320738816,486918122771582977,486918164815286273,501736778307207168,486918240887377921,501737031911604224,486918241663324161,486918242527350784,501737164271255553,501737200690397185,501737355338579969"),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.RESIDUAL_VALUE_SELECT, value = "????????????", required = false, dataTypeClass = String.class, example = "follow_asset"),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.FIRST_DEPRECIATION_METHOD, value = "??????????????????", required = false, dataTypeClass = String.class, example = "purchase_next_month"),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetDepreciationVOMeta.PAGE_INDEX, AssetDepreciationVOMeta.PAGE_SIZE })
    @SentinelResource(value = AssetDepreciationServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDepreciationServiceProxy.QUERY_LIST)
    public Result<List<AssetDepreciation>> queryList(AssetDepreciationVO sample) {
        Result<List<AssetDepreciation>> result = new Result<>();
        List<AssetDepreciation> list = assetDepreciationService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetDepreciationVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "573537666012807168"),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "?????????"),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.METHOD, value = "????????????", required = false, dataTypeClass = String.class, example = "average_age"),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.PRE_RESIDUAL_RATE, value = "???????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.OWN_COMPANY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.CATEGORY_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "486918386278731776,486918387960647681,486918449189097473,486918029091803137,486918073320738816,486918122771582977,486918164815286273,501736778307207168,486918240887377921,501737031911604224,486918241663324161,486918242527350784,501737164271255553,501737200690397185,501737355338579969"),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.RESIDUAL_VALUE_SELECT, value = "????????????", required = false, dataTypeClass = String.class, example = "follow_asset"),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.FIRST_DEPRECIATION_METHOD, value = "??????????????????", required = false, dataTypeClass = String.class, example = "purchase_next_month"),
		@ApiImplicitParam(name = AssetDepreciationVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = AssetDepreciationServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDepreciationServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<AssetDepreciation>> queryPagedList(AssetDepreciationVO sample) {
        Result<PagedList<AssetDepreciation>> result = new Result<>();
        PagedList<AssetDepreciation> list = assetDepreciationService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        assetDepreciationService.dao().fill(list).with(AssetDepreciationMeta.CATEGORY).execute();
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = AssetDepreciationServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetDepreciationServiceProxy.EXPORT_EXCEL)
    public void exportExcel(AssetDepreciationVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = assetDepreciationService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = AssetDepreciationServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetDepreciationServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = assetDepreciationService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = AssetDepreciationServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetDepreciationServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = assetDepreciationService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
