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
import com.dt.platform.proxy.eam.AssetStockGoodsDetailServiceProxy;
import com.dt.platform.domain.eam.meta.AssetStockGoodsDetailVOMeta;
import com.dt.platform.domain.eam.AssetStockGoodsDetail;
import com.dt.platform.domain.eam.AssetStockGoodsDetailVO;
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
import com.dt.platform.domain.eam.meta.AssetStockGoodsDetailMeta;
import java.math.BigDecimal;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IAssetStockGoodsDetailService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ?????????????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-04-21 06:06:17
 */
@Api(tags = "??????????????????")
@ApiSort(0)
@RestController("EamAssetStockGoodsDetailController")
public class AssetStockGoodsDetailController extends SuperController {

    @Autowired
    private IAssetStockGoodsDetailService assetStockGoodsDetailService;

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockGoodsDetailVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsDetailVOMeta.GOODS_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsDetailVOMeta.STOCK_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsDetailVOMeta.IN_NUMBER, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetStockGoodsDetailVOMeta.CUR_NUMBER, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetStockGoodsDetailVOMeta.UNIT_PRICE, value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetStockGoodsDetailVOMeta.AMOUNT, value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetStockGoodsDetailVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = AssetStockGoodsDetailServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStockGoodsDetailServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(AssetStockGoodsDetailVO assetStockGoodsDetailVO) {
        Result result = assetStockGoodsDetailService.insert(assetStockGoodsDetailVO, false);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockGoodsDetailVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = AssetStockGoodsDetailServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStockGoodsDetailServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = assetStockGoodsDetailService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ?????????????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "??????????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockGoodsDetailVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetStockGoodsDetailServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStockGoodsDetailServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = assetStockGoodsDetailService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockGoodsDetailVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsDetailVOMeta.GOODS_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsDetailVOMeta.STOCK_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsDetailVOMeta.IN_NUMBER, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetStockGoodsDetailVOMeta.CUR_NUMBER, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetStockGoodsDetailVOMeta.UNIT_PRICE, value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetStockGoodsDetailVOMeta.AMOUNT, value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetStockGoodsDetailVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { AssetStockGoodsDetailVOMeta.PAGE_INDEX, AssetStockGoodsDetailVOMeta.PAGE_SIZE, AssetStockGoodsDetailVOMeta.SEARCH_FIELD, AssetStockGoodsDetailVOMeta.FUZZY_FIELD, AssetStockGoodsDetailVOMeta.SEARCH_VALUE, AssetStockGoodsDetailVOMeta.DIRTY_FIELDS, AssetStockGoodsDetailVOMeta.SORT_FIELD, AssetStockGoodsDetailVOMeta.SORT_TYPE, AssetStockGoodsDetailVOMeta.IDS })
    @SentinelResource(value = AssetStockGoodsDetailServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStockGoodsDetailServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(AssetStockGoodsDetailVO assetStockGoodsDetailVO) {
        Result result = assetStockGoodsDetailService.update(assetStockGoodsDetailVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockGoodsDetailVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsDetailVOMeta.GOODS_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsDetailVOMeta.STOCK_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsDetailVOMeta.IN_NUMBER, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetStockGoodsDetailVOMeta.CUR_NUMBER, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetStockGoodsDetailVOMeta.UNIT_PRICE, value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetStockGoodsDetailVOMeta.AMOUNT, value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetStockGoodsDetailVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetStockGoodsDetailVOMeta.PAGE_INDEX, AssetStockGoodsDetailVOMeta.PAGE_SIZE, AssetStockGoodsDetailVOMeta.SEARCH_FIELD, AssetStockGoodsDetailVOMeta.FUZZY_FIELD, AssetStockGoodsDetailVOMeta.SEARCH_VALUE, AssetStockGoodsDetailVOMeta.DIRTY_FIELDS, AssetStockGoodsDetailVOMeta.SORT_FIELD, AssetStockGoodsDetailVOMeta.SORT_TYPE, AssetStockGoodsDetailVOMeta.IDS })
    @SentinelResource(value = AssetStockGoodsDetailServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStockGoodsDetailServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(AssetStockGoodsDetailVO assetStockGoodsDetailVO) {
        Result result = assetStockGoodsDetailService.save(assetStockGoodsDetailVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockGoodsDetailVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = AssetStockGoodsDetailServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStockGoodsDetailServiceProxy.GET_BY_ID)
    public Result<AssetStockGoodsDetail> getById(String id) {
        Result<AssetStockGoodsDetail> result = new Result<>();
        AssetStockGoodsDetail assetStockGoodsDetail = assetStockGoodsDetailService.getById(id);
        result.success(true).data(assetStockGoodsDetail);
        return result;
    }

    /**
     * ?????????????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "??????????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockGoodsDetailVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetStockGoodsDetailServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStockGoodsDetailServiceProxy.GET_BY_IDS)
    public Result<List<AssetStockGoodsDetail>> getByIds(List<String> ids) {
        Result<List<AssetStockGoodsDetail>> result = new Result<>();
        List<AssetStockGoodsDetail> list = assetStockGoodsDetailService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockGoodsDetailVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsDetailVOMeta.GOODS_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsDetailVOMeta.STOCK_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsDetailVOMeta.IN_NUMBER, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetStockGoodsDetailVOMeta.CUR_NUMBER, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetStockGoodsDetailVOMeta.UNIT_PRICE, value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetStockGoodsDetailVOMeta.AMOUNT, value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetStockGoodsDetailVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetStockGoodsDetailVOMeta.PAGE_INDEX, AssetStockGoodsDetailVOMeta.PAGE_SIZE })
    @SentinelResource(value = AssetStockGoodsDetailServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStockGoodsDetailServiceProxy.QUERY_LIST)
    public Result<List<AssetStockGoodsDetail>> queryList(AssetStockGoodsDetailVO sample) {
        Result<List<AssetStockGoodsDetail>> result = new Result<>();
        List<AssetStockGoodsDetail> list = assetStockGoodsDetailService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????????????????
     */
    @ApiOperation(value = "??????????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockGoodsDetailVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsDetailVOMeta.GOODS_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsDetailVOMeta.STOCK_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsDetailVOMeta.IN_NUMBER, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetStockGoodsDetailVOMeta.CUR_NUMBER, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetStockGoodsDetailVOMeta.UNIT_PRICE, value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetStockGoodsDetailVOMeta.AMOUNT, value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetStockGoodsDetailVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = AssetStockGoodsDetailServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStockGoodsDetailServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<AssetStockGoodsDetail>> queryPagedList(AssetStockGoodsDetailVO sample) {
        Result<PagedList<AssetStockGoodsDetail>> result = new Result<>();
        PagedList<AssetStockGoodsDetail> list = assetStockGoodsDetailService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = AssetStockGoodsDetailServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetStockGoodsDetailServiceProxy.EXPORT_EXCEL)
    public void exportExcel(AssetStockGoodsDetailVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = assetStockGoodsDetailService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = AssetStockGoodsDetailServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetStockGoodsDetailServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = assetStockGoodsDetailService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = AssetStockGoodsDetailServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetStockGoodsDetailServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = assetStockGoodsDetailService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
