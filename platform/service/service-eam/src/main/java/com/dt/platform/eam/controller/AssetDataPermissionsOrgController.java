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
import com.dt.platform.proxy.eam.AssetDataPermissionsOrgServiceProxy;
import com.dt.platform.domain.eam.meta.AssetDataPermissionsOrgVOMeta;
import com.dt.platform.domain.eam.AssetDataPermissionsOrg;
import com.dt.platform.domain.eam.AssetDataPermissionsOrgVO;
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
import com.dt.platform.domain.eam.meta.AssetDataPermissionsOrgMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IAssetDataPermissionsOrgService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ?????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2021-12-16 15:24:09
 */
@Api(tags = "??????")
@ApiSort(0)
@RestController("EamAssetDataPermissionsOrgController")
public class AssetDataPermissionsOrgController extends SuperController {

    @Autowired
    private IAssetDataPermissionsOrgService assetDataPermissionsOrgService;

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataPermissionsOrgVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsOrgVOMeta.PERMISSION_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsOrgVOMeta.VALUE, value = "???", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = AssetDataPermissionsOrgServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataPermissionsOrgServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(AssetDataPermissionsOrgVO assetDataPermissionsOrgVO) {
        Result result = assetDataPermissionsOrgService.insert(assetDataPermissionsOrgVO, false);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataPermissionsOrgVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = AssetDataPermissionsOrgServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataPermissionsOrgServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = assetDataPermissionsOrgService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ?????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataPermissionsOrgVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetDataPermissionsOrgServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataPermissionsOrgServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = assetDataPermissionsOrgService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataPermissionsOrgVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsOrgVOMeta.PERMISSION_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsOrgVOMeta.VALUE, value = "???", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { AssetDataPermissionsOrgVOMeta.PAGE_INDEX, AssetDataPermissionsOrgVOMeta.PAGE_SIZE, AssetDataPermissionsOrgVOMeta.SEARCH_FIELD, AssetDataPermissionsOrgVOMeta.FUZZY_FIELD, AssetDataPermissionsOrgVOMeta.SEARCH_VALUE, AssetDataPermissionsOrgVOMeta.DIRTY_FIELDS, AssetDataPermissionsOrgVOMeta.SORT_FIELD, AssetDataPermissionsOrgVOMeta.SORT_TYPE, AssetDataPermissionsOrgVOMeta.IDS })
    @SentinelResource(value = AssetDataPermissionsOrgServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataPermissionsOrgServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(AssetDataPermissionsOrgVO assetDataPermissionsOrgVO) {
        Result result = assetDataPermissionsOrgService.update(assetDataPermissionsOrgVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataPermissionsOrgVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsOrgVOMeta.PERMISSION_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsOrgVOMeta.VALUE, value = "???", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetDataPermissionsOrgVOMeta.PAGE_INDEX, AssetDataPermissionsOrgVOMeta.PAGE_SIZE, AssetDataPermissionsOrgVOMeta.SEARCH_FIELD, AssetDataPermissionsOrgVOMeta.FUZZY_FIELD, AssetDataPermissionsOrgVOMeta.SEARCH_VALUE, AssetDataPermissionsOrgVOMeta.DIRTY_FIELDS, AssetDataPermissionsOrgVOMeta.SORT_FIELD, AssetDataPermissionsOrgVOMeta.SORT_TYPE, AssetDataPermissionsOrgVOMeta.IDS })
    @SentinelResource(value = AssetDataPermissionsOrgServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataPermissionsOrgServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(AssetDataPermissionsOrgVO assetDataPermissionsOrgVO) {
        Result result = assetDataPermissionsOrgService.save(assetDataPermissionsOrgVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataPermissionsOrgVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = AssetDataPermissionsOrgServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataPermissionsOrgServiceProxy.GET_BY_ID)
    public Result<AssetDataPermissionsOrg> getById(String id) {
        Result<AssetDataPermissionsOrg> result = new Result<>();
        AssetDataPermissionsOrg assetDataPermissionsOrg = assetDataPermissionsOrgService.getById(id);
        result.success(true).data(assetDataPermissionsOrg);
        return result;
    }

    /**
     * ?????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataPermissionsOrgVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetDataPermissionsOrgServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataPermissionsOrgServiceProxy.GET_BY_IDS)
    public Result<List<AssetDataPermissionsOrg>> getByIds(List<String> ids) {
        Result<List<AssetDataPermissionsOrg>> result = new Result<>();
        List<AssetDataPermissionsOrg> list = assetDataPermissionsOrgService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataPermissionsOrgVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsOrgVOMeta.PERMISSION_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsOrgVOMeta.VALUE, value = "???", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetDataPermissionsOrgVOMeta.PAGE_INDEX, AssetDataPermissionsOrgVOMeta.PAGE_SIZE })
    @SentinelResource(value = AssetDataPermissionsOrgServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataPermissionsOrgServiceProxy.QUERY_LIST)
    public Result<List<AssetDataPermissionsOrg>> queryList(AssetDataPermissionsOrgVO sample) {
        Result<List<AssetDataPermissionsOrg>> result = new Result<>();
        List<AssetDataPermissionsOrg> list = assetDataPermissionsOrgService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataPermissionsOrgVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsOrgVOMeta.PERMISSION_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsOrgVOMeta.VALUE, value = "???", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = AssetDataPermissionsOrgServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataPermissionsOrgServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<AssetDataPermissionsOrg>> queryPagedList(AssetDataPermissionsOrgVO sample) {
        Result<PagedList<AssetDataPermissionsOrg>> result = new Result<>();
        PagedList<AssetDataPermissionsOrg> list = assetDataPermissionsOrgService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = AssetDataPermissionsOrgServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetDataPermissionsOrgServiceProxy.EXPORT_EXCEL)
    public void exportExcel(AssetDataPermissionsOrgVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = assetDataPermissionsOrgService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = AssetDataPermissionsOrgServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetDataPermissionsOrgServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = assetDataPermissionsOrgService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = AssetDataPermissionsOrgServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetDataPermissionsOrgServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = assetDataPermissionsOrgService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
