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
import com.dt.platform.proxy.eam.AssetDataPermissionsCatalogServiceProxy;
import com.dt.platform.domain.eam.meta.AssetDataPermissionsCatalogVOMeta;
import com.dt.platform.domain.eam.AssetDataPermissionsCatalog;
import com.dt.platform.domain.eam.AssetDataPermissionsCatalogVO;
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
import com.dt.platform.domain.eam.meta.AssetDataPermissionsCatalogMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IAssetDataPermissionsCatalogService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ?????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2021-12-16 15:22:58
 */
@Api(tags = "??????")
@ApiSort(0)
@RestController("EamAssetDataPermissionsCatalogController")
public class AssetDataPermissionsCatalogController extends SuperController {

    @Autowired
    private IAssetDataPermissionsCatalogService assetDataPermissionsCatalogService;

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataPermissionsCatalogVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsCatalogVOMeta.PERMISSION_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsCatalogVOMeta.VALUE, value = "???", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = AssetDataPermissionsCatalogServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataPermissionsCatalogServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(AssetDataPermissionsCatalogVO assetDataPermissionsCatalogVO) {
        Result result = assetDataPermissionsCatalogService.insert(assetDataPermissionsCatalogVO, false);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataPermissionsCatalogVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = AssetDataPermissionsCatalogServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataPermissionsCatalogServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = assetDataPermissionsCatalogService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ?????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataPermissionsCatalogVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetDataPermissionsCatalogServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataPermissionsCatalogServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = assetDataPermissionsCatalogService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataPermissionsCatalogVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsCatalogVOMeta.PERMISSION_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsCatalogVOMeta.VALUE, value = "???", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { AssetDataPermissionsCatalogVOMeta.PAGE_INDEX, AssetDataPermissionsCatalogVOMeta.PAGE_SIZE, AssetDataPermissionsCatalogVOMeta.SEARCH_FIELD, AssetDataPermissionsCatalogVOMeta.FUZZY_FIELD, AssetDataPermissionsCatalogVOMeta.SEARCH_VALUE, AssetDataPermissionsCatalogVOMeta.DIRTY_FIELDS, AssetDataPermissionsCatalogVOMeta.SORT_FIELD, AssetDataPermissionsCatalogVOMeta.SORT_TYPE, AssetDataPermissionsCatalogVOMeta.IDS })
    @SentinelResource(value = AssetDataPermissionsCatalogServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataPermissionsCatalogServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(AssetDataPermissionsCatalogVO assetDataPermissionsCatalogVO) {
        Result result = assetDataPermissionsCatalogService.update(assetDataPermissionsCatalogVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataPermissionsCatalogVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsCatalogVOMeta.PERMISSION_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsCatalogVOMeta.VALUE, value = "???", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetDataPermissionsCatalogVOMeta.PAGE_INDEX, AssetDataPermissionsCatalogVOMeta.PAGE_SIZE, AssetDataPermissionsCatalogVOMeta.SEARCH_FIELD, AssetDataPermissionsCatalogVOMeta.FUZZY_FIELD, AssetDataPermissionsCatalogVOMeta.SEARCH_VALUE, AssetDataPermissionsCatalogVOMeta.DIRTY_FIELDS, AssetDataPermissionsCatalogVOMeta.SORT_FIELD, AssetDataPermissionsCatalogVOMeta.SORT_TYPE, AssetDataPermissionsCatalogVOMeta.IDS })
    @SentinelResource(value = AssetDataPermissionsCatalogServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataPermissionsCatalogServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(AssetDataPermissionsCatalogVO assetDataPermissionsCatalogVO) {
        Result result = assetDataPermissionsCatalogService.save(assetDataPermissionsCatalogVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataPermissionsCatalogVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = AssetDataPermissionsCatalogServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataPermissionsCatalogServiceProxy.GET_BY_ID)
    public Result<AssetDataPermissionsCatalog> getById(String id) {
        Result<AssetDataPermissionsCatalog> result = new Result<>();
        AssetDataPermissionsCatalog assetDataPermissionsCatalog = assetDataPermissionsCatalogService.getById(id);
        result.success(true).data(assetDataPermissionsCatalog);
        return result;
    }

    /**
     * ?????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataPermissionsCatalogVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetDataPermissionsCatalogServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataPermissionsCatalogServiceProxy.GET_BY_IDS)
    public Result<List<AssetDataPermissionsCatalog>> getByIds(List<String> ids) {
        Result<List<AssetDataPermissionsCatalog>> result = new Result<>();
        List<AssetDataPermissionsCatalog> list = assetDataPermissionsCatalogService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataPermissionsCatalogVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsCatalogVOMeta.PERMISSION_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsCatalogVOMeta.VALUE, value = "???", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetDataPermissionsCatalogVOMeta.PAGE_INDEX, AssetDataPermissionsCatalogVOMeta.PAGE_SIZE })
    @SentinelResource(value = AssetDataPermissionsCatalogServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataPermissionsCatalogServiceProxy.QUERY_LIST)
    public Result<List<AssetDataPermissionsCatalog>> queryList(AssetDataPermissionsCatalogVO sample) {
        Result<List<AssetDataPermissionsCatalog>> result = new Result<>();
        List<AssetDataPermissionsCatalog> list = assetDataPermissionsCatalogService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataPermissionsCatalogVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsCatalogVOMeta.PERMISSION_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsCatalogVOMeta.VALUE, value = "???", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = AssetDataPermissionsCatalogServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataPermissionsCatalogServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<AssetDataPermissionsCatalog>> queryPagedList(AssetDataPermissionsCatalogVO sample) {
        Result<PagedList<AssetDataPermissionsCatalog>> result = new Result<>();
        PagedList<AssetDataPermissionsCatalog> list = assetDataPermissionsCatalogService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = AssetDataPermissionsCatalogServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetDataPermissionsCatalogServiceProxy.EXPORT_EXCEL)
    public void exportExcel(AssetDataPermissionsCatalogVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = assetDataPermissionsCatalogService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = AssetDataPermissionsCatalogServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetDataPermissionsCatalogServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = assetDataPermissionsCatalogService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = AssetDataPermissionsCatalogServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetDataPermissionsCatalogServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = assetDataPermissionsCatalogService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
