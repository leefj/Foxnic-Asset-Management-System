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
import com.dt.platform.proxy.eam.AssetDataPermissionsPositionServiceProxy;
import com.dt.platform.domain.eam.meta.AssetDataPermissionsPositionVOMeta;
import com.dt.platform.domain.eam.AssetDataPermissionsPosition;
import com.dt.platform.domain.eam.AssetDataPermissionsPositionVO;
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
import com.dt.platform.domain.eam.meta.AssetDataPermissionsPositionMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IAssetDataPermissionsPositionService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ?????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2021-12-17 09:15:33
 */
@Api(tags = "??????")
@ApiSort(0)
@RestController("EamAssetDataPermissionsPositionController")
public class AssetDataPermissionsPositionController extends SuperController {

    @Autowired
    private IAssetDataPermissionsPositionService assetDataPermissionsPositionService;

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataPermissionsPositionVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "524147910955241472"),
		@ApiImplicitParam(name = AssetDataPermissionsPositionVOMeta.PERMISSION_ID, value = "??????", required = false, dataTypeClass = String.class, example = "523894324979568640"),
		@ApiImplicitParam(name = AssetDataPermissionsPositionVOMeta.VALUE, value = "???", required = false, dataTypeClass = String.class, example = "472024559755722753")
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = AssetDataPermissionsPositionServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataPermissionsPositionServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(AssetDataPermissionsPositionVO assetDataPermissionsPositionVO) {
        Result result = assetDataPermissionsPositionService.insert(assetDataPermissionsPositionVO, false);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataPermissionsPositionVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "524147910955241472")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = AssetDataPermissionsPositionServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataPermissionsPositionServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = assetDataPermissionsPositionService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ?????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataPermissionsPositionVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetDataPermissionsPositionServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataPermissionsPositionServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = assetDataPermissionsPositionService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataPermissionsPositionVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "524147910955241472"),
		@ApiImplicitParam(name = AssetDataPermissionsPositionVOMeta.PERMISSION_ID, value = "??????", required = false, dataTypeClass = String.class, example = "523894324979568640"),
		@ApiImplicitParam(name = AssetDataPermissionsPositionVOMeta.VALUE, value = "???", required = false, dataTypeClass = String.class, example = "472024559755722753")
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { AssetDataPermissionsPositionVOMeta.PAGE_INDEX, AssetDataPermissionsPositionVOMeta.PAGE_SIZE, AssetDataPermissionsPositionVOMeta.SEARCH_FIELD, AssetDataPermissionsPositionVOMeta.FUZZY_FIELD, AssetDataPermissionsPositionVOMeta.SEARCH_VALUE, AssetDataPermissionsPositionVOMeta.DIRTY_FIELDS, AssetDataPermissionsPositionVOMeta.SORT_FIELD, AssetDataPermissionsPositionVOMeta.SORT_TYPE, AssetDataPermissionsPositionVOMeta.IDS })
    @SentinelResource(value = AssetDataPermissionsPositionServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataPermissionsPositionServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(AssetDataPermissionsPositionVO assetDataPermissionsPositionVO) {
        Result result = assetDataPermissionsPositionService.update(assetDataPermissionsPositionVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataPermissionsPositionVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "524147910955241472"),
		@ApiImplicitParam(name = AssetDataPermissionsPositionVOMeta.PERMISSION_ID, value = "??????", required = false, dataTypeClass = String.class, example = "523894324979568640"),
		@ApiImplicitParam(name = AssetDataPermissionsPositionVOMeta.VALUE, value = "???", required = false, dataTypeClass = String.class, example = "472024559755722753")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetDataPermissionsPositionVOMeta.PAGE_INDEX, AssetDataPermissionsPositionVOMeta.PAGE_SIZE, AssetDataPermissionsPositionVOMeta.SEARCH_FIELD, AssetDataPermissionsPositionVOMeta.FUZZY_FIELD, AssetDataPermissionsPositionVOMeta.SEARCH_VALUE, AssetDataPermissionsPositionVOMeta.DIRTY_FIELDS, AssetDataPermissionsPositionVOMeta.SORT_FIELD, AssetDataPermissionsPositionVOMeta.SORT_TYPE, AssetDataPermissionsPositionVOMeta.IDS })
    @SentinelResource(value = AssetDataPermissionsPositionServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataPermissionsPositionServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(AssetDataPermissionsPositionVO assetDataPermissionsPositionVO) {
        Result result = assetDataPermissionsPositionService.save(assetDataPermissionsPositionVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataPermissionsPositionVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = AssetDataPermissionsPositionServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataPermissionsPositionServiceProxy.GET_BY_ID)
    public Result<AssetDataPermissionsPosition> getById(String id) {
        Result<AssetDataPermissionsPosition> result = new Result<>();
        AssetDataPermissionsPosition assetDataPermissionsPosition = assetDataPermissionsPositionService.getById(id);
        result.success(true).data(assetDataPermissionsPosition);
        return result;
    }

    /**
     * ?????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataPermissionsPositionVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetDataPermissionsPositionServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataPermissionsPositionServiceProxy.GET_BY_IDS)
    public Result<List<AssetDataPermissionsPosition>> getByIds(List<String> ids) {
        Result<List<AssetDataPermissionsPosition>> result = new Result<>();
        List<AssetDataPermissionsPosition> list = assetDataPermissionsPositionService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataPermissionsPositionVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "524147910955241472"),
		@ApiImplicitParam(name = AssetDataPermissionsPositionVOMeta.PERMISSION_ID, value = "??????", required = false, dataTypeClass = String.class, example = "523894324979568640"),
		@ApiImplicitParam(name = AssetDataPermissionsPositionVOMeta.VALUE, value = "???", required = false, dataTypeClass = String.class, example = "472024559755722753")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetDataPermissionsPositionVOMeta.PAGE_INDEX, AssetDataPermissionsPositionVOMeta.PAGE_SIZE })
    @SentinelResource(value = AssetDataPermissionsPositionServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataPermissionsPositionServiceProxy.QUERY_LIST)
    public Result<List<AssetDataPermissionsPosition>> queryList(AssetDataPermissionsPositionVO sample) {
        Result<List<AssetDataPermissionsPosition>> result = new Result<>();
        List<AssetDataPermissionsPosition> list = assetDataPermissionsPositionService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataPermissionsPositionVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "524147910955241472"),
		@ApiImplicitParam(name = AssetDataPermissionsPositionVOMeta.PERMISSION_ID, value = "??????", required = false, dataTypeClass = String.class, example = "523894324979568640"),
		@ApiImplicitParam(name = AssetDataPermissionsPositionVOMeta.VALUE, value = "???", required = false, dataTypeClass = String.class, example = "472024559755722753")
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = AssetDataPermissionsPositionServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataPermissionsPositionServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<AssetDataPermissionsPosition>> queryPagedList(AssetDataPermissionsPositionVO sample) {
        Result<PagedList<AssetDataPermissionsPosition>> result = new Result<>();
        PagedList<AssetDataPermissionsPosition> list = assetDataPermissionsPositionService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = AssetDataPermissionsPositionServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetDataPermissionsPositionServiceProxy.EXPORT_EXCEL)
    public void exportExcel(AssetDataPermissionsPositionVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = assetDataPermissionsPositionService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = AssetDataPermissionsPositionServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetDataPermissionsPositionServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = assetDataPermissionsPositionService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = AssetDataPermissionsPositionServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetDataPermissionsPositionServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = assetDataPermissionsPositionService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
