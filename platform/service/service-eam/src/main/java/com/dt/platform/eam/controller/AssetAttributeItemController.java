package com.dt.platform.eam.controller;

import java.util.HashMap;
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
import com.dt.platform.proxy.eam.AssetAttributeItemServiceProxy;
import com.dt.platform.domain.eam.meta.AssetAttributeItemVOMeta;
import com.dt.platform.domain.eam.AssetAttributeItem;
import com.dt.platform.domain.eam.AssetAttributeItemVO;
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
import com.dt.platform.domain.eam.meta.AssetAttributeItemMeta;
import com.dt.platform.domain.eam.AssetAttribute;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IAssetAttributeItemService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ????????????????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2021-09-07 14:32:58
 */
@Api(tags = "?????????????????????")
@ApiSort(0)
@RestController("EamAssetAttributeItemController")
public class AssetAttributeItemController extends SuperController {

    @Autowired
    private IAssetAttributeItemService assetAttributeItemService;

    /**
     * ???????????????????????????
     */
    @ApiOperation(value = "???????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "527a4336-0f92-11ec-ab08-00163e2e6a36"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.OWNER_CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "base"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.ATTRIBUTE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "000b5919-0f8b-11ec-ab08-00163e2e6a36"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.DIMENSION, value = "????????????", required = false, dataTypeClass = String.class, example = "maintainer"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.REQUIRED, value = "??????", required = false, dataTypeClass = String.class, example = "0"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.LAYOUT_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "3"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.LIST_SHOW, value = "????????????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.LIST_CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.LIST_SORT, value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.FORM_SHOW, value = "????????????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.LAYOUT_ROW, value = "???????????????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.LAYOUT_COLUMN, value = "???????????????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = AssetAttributeItemServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetAttributeItemServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(AssetAttributeItemVO assetAttributeItemVO) {
        Result result = assetAttributeItemService.insert(assetAttributeItemVO);
        return result;
    }

    /**
     * ???????????????????????????
     */
    @ApiOperation(value = "???????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "527a4336-0f92-11ec-ab08-00163e2e6a36")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = AssetAttributeItemServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetAttributeItemServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = assetAttributeItemService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ????????????????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "?????????????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetAttributeItemServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetAttributeItemServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = assetAttributeItemService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ???????????????????????????
     */
    @ApiOperation(value = "???????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "527a4336-0f92-11ec-ab08-00163e2e6a36"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.OWNER_CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "base"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.ATTRIBUTE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "000b5919-0f8b-11ec-ab08-00163e2e6a36"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.DIMENSION, value = "????????????", required = false, dataTypeClass = String.class, example = "maintainer"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.REQUIRED, value = "??????", required = false, dataTypeClass = String.class, example = "0"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.LAYOUT_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "3"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.LIST_SHOW, value = "????????????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.LIST_CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.LIST_SORT, value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.FORM_SHOW, value = "????????????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.LAYOUT_ROW, value = "???????????????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.LAYOUT_COLUMN, value = "???????????????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { AssetAttributeItemVOMeta.PAGE_INDEX, AssetAttributeItemVOMeta.PAGE_SIZE, AssetAttributeItemVOMeta.SEARCH_FIELD, AssetAttributeItemVOMeta.FUZZY_FIELD, AssetAttributeItemVOMeta.SEARCH_VALUE, AssetAttributeItemVOMeta.SORT_FIELD, AssetAttributeItemVOMeta.SORT_TYPE, AssetAttributeItemVOMeta.IDS })
    @SentinelResource(value = AssetAttributeItemServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetAttributeItemServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(AssetAttributeItemVO assetAttributeItemVO) {
        Result result = assetAttributeItemService.update(assetAttributeItemVO, SaveMode.NOT_NULL_FIELDS);
        return result;
    }

    /**
     * ???????????????????????????
     */
    @ApiOperation(value = "???????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "527a4336-0f92-11ec-ab08-00163e2e6a36"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.OWNER_CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "base"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.ATTRIBUTE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "000b5919-0f8b-11ec-ab08-00163e2e6a36"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.DIMENSION, value = "????????????", required = false, dataTypeClass = String.class, example = "maintainer"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.REQUIRED, value = "??????", required = false, dataTypeClass = String.class, example = "0"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.LAYOUT_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "3"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.LIST_SHOW, value = "????????????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.LIST_CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.LIST_SORT, value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.FORM_SHOW, value = "????????????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.LAYOUT_ROW, value = "???????????????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.LAYOUT_COLUMN, value = "???????????????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetAttributeItemVOMeta.PAGE_INDEX, AssetAttributeItemVOMeta.PAGE_SIZE, AssetAttributeItemVOMeta.SEARCH_FIELD, AssetAttributeItemVOMeta.FUZZY_FIELD, AssetAttributeItemVOMeta.SEARCH_VALUE, AssetAttributeItemVOMeta.SORT_FIELD, AssetAttributeItemVOMeta.SORT_TYPE, AssetAttributeItemVOMeta.IDS })
    @SentinelResource(value = AssetAttributeItemServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetAttributeItemServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(AssetAttributeItemVO assetAttributeItemVO) {
        Result result = assetAttributeItemService.save(assetAttributeItemVO, SaveMode.NOT_NULL_FIELDS);
        return result;
    }

    /**
     * ???????????????????????????
     */
    @ApiOperation(value = "???????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = AssetAttributeItemServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetAttributeItemServiceProxy.GET_BY_ID)
    public Result<AssetAttributeItem> getById(String id) {
        Result<AssetAttributeItem> result = new Result<>();
        AssetAttributeItem assetAttributeItem = assetAttributeItemService.getById(id);
        // ????????? ?????? ??????
        assetAttributeItemService.join(assetAttributeItem, AssetAttributeItemMeta.ATTRIBUTE);
        result.success(true).data(assetAttributeItem);
        return result;
    }

    /**
     * ????????????????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "?????????????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetAttributeItemServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetAttributeItemServiceProxy.GET_BY_IDS)
    public Result<List<AssetAttributeItem>> getByIds(List<String> ids) {
        Result<List<AssetAttributeItem>> result = new Result<>();
        List<AssetAttributeItem> list = assetAttributeItemService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ???????????????????????????
     */
    @ApiOperation(value = "???????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "527a4336-0f92-11ec-ab08-00163e2e6a36"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.OWNER_CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "base"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.ATTRIBUTE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "000b5919-0f8b-11ec-ab08-00163e2e6a36"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.DIMENSION, value = "????????????", required = false, dataTypeClass = String.class, example = "maintainer"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.REQUIRED, value = "??????", required = false, dataTypeClass = String.class, example = "0"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.LAYOUT_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "3"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.LIST_SHOW, value = "????????????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.LIST_CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.LIST_SORT, value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.FORM_SHOW, value = "????????????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.LAYOUT_ROW, value = "???????????????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.LAYOUT_COLUMN, value = "???????????????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetAttributeItemVOMeta.PAGE_INDEX, AssetAttributeItemVOMeta.PAGE_SIZE })
    @SentinelResource(value = AssetAttributeItemServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetAttributeItemServiceProxy.QUERY_LIST)
    public Result<List<AssetAttributeItem>> queryList(AssetAttributeItemVO sample) {
        Result<List<AssetAttributeItem>> result = new Result<>();
        List<AssetAttributeItem> list = assetAttributeItemService.queryList(sample);
        assetAttributeItemService.join(list, AssetAttributeItemMeta.ATTRIBUTE);
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????????????????????????????????
     */
    @ApiOperation(value = "?????????????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "527a4336-0f92-11ec-ab08-00163e2e6a36"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.OWNER_CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "base"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.ATTRIBUTE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "000b5919-0f8b-11ec-ab08-00163e2e6a36"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.DIMENSION, value = "????????????", required = false, dataTypeClass = String.class, example = "maintainer"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.REQUIRED, value = "??????", required = false, dataTypeClass = String.class, example = "0"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.LAYOUT_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "3"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.LIST_SHOW, value = "????????????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.LIST_CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.LIST_SORT, value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.FORM_SHOW, value = "????????????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.LAYOUT_ROW, value = "???????????????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.LAYOUT_COLUMN, value = "???????????????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = AssetAttributeItemServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetAttributeItemServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<AssetAttributeItem>> queryPagedList(AssetAttributeItemVO sample) {
        Result<PagedList<AssetAttributeItem>> result = new Result<>();
        PagedList<AssetAttributeItem> list = assetAttributeItemService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // ????????? ?????? ??????
        assetAttributeItemService.join(list, AssetAttributeItemMeta.ATTRIBUTE);
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????????????????????????????????
     */
    @ApiOperation(value = "?????????????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "527a4336-0f92-11ec-ab08-00163e2e6a36")
	})
    @ApiOperationSupport(order = 9)
    @SentinelResource(value = AssetAttributeItemServiceProxy.QUERY_FORM_COLUMN_BY_MODULE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetAttributeItemServiceProxy.QUERY_FORM_COLUMN_BY_MODULE)
    public Result<HashMap<String, List<AssetAttributeItem>>> queryFormColumnByModule(String module, String dim) {
        Result<HashMap<String, List<AssetAttributeItem>>> result = new Result<>();
        result.success(true).data(assetAttributeItemService.queryFormColumnByModule(module, dim));
        return result;
    }

    /**
     * ?????????????????????????????????
     */
    @ApiOperation(value = "?????????????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetAttributeItemVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "527a4336-0f92-11ec-ab08-00163e2e6a36")
	})
    @ApiOperationSupport(order = 10)
    @SentinelResource(value = AssetAttributeItemServiceProxy.QUERY_LIST_COLUMN_BY_MODULE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetAttributeItemServiceProxy.QUERY_LIST_COLUMN_BY_MODULE)
    public Result<HashMap<String, List<AssetAttributeItem>>> queryListColumnByModule(String module, String dim) {
        Result<HashMap<String, List<AssetAttributeItem>>> result = new Result<>();
        result.success(true).data(assetAttributeItemService.queryListColumnByModule(module, dim));
        return result;
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = AssetAttributeItemServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetAttributeItemServiceProxy.EXPORT_EXCEL)
    public void exportExcel(AssetAttributeItemVO sample, HttpServletResponse response) throws Exception {
        // ?????? Excel ??????
        ExcelWriter ew = assetAttributeItemService.exportExcel(sample);
        // ??????
        DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = AssetAttributeItemServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetAttributeItemServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        // ?????? Excel ??????
        ExcelWriter ew = assetAttributeItemService.exportExcelTemplate();
        // ??????
        DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
    }

    @SentinelResource(value = AssetAttributeItemServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetAttributeItemServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = assetAttributeItemService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
