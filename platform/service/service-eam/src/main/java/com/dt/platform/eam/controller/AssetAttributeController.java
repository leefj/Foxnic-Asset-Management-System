package com.dt.platform.eam.controller;

import java.util.List;
import com.dt.platform.domain.eam.AssetAttributeItem;
import com.dt.platform.domain.eam.meta.AssetAttributeItemVOMeta;
import com.dt.platform.proxy.eam.AssetAttributeItemServiceProxy;
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
import com.dt.platform.proxy.eam.AssetAttributeServiceProxy;
import com.dt.platform.domain.eam.meta.AssetAttributeVOMeta;
import com.dt.platform.domain.eam.AssetAttribute;
import com.dt.platform.domain.eam.AssetAttributeVO;
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
import com.dt.platform.domain.eam.meta.AssetAttributeMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IAssetAttributeService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ?????????????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2021-09-12 21:51:26
 */
@Api(tags = "??????????????????")
@ApiSort(0)
@RestController("EamAssetAttributeController")
public class AssetAttributeController extends SuperController {

    @Autowired
    private IAssetAttributeService assetAttributeService;

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetAttributeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "000b5919-0f8b-11ec-ab08-00163e2e6a36"),
		@ApiImplicitParam(name = AssetAttributeVOMeta.CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "maintainer_name"),
		@ApiImplicitParam(name = AssetAttributeVOMeta.LABEL, value = "????????????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = AssetAttributeVOMeta.VALUE_TYPE, value = "???????????????", required = false, dataTypeClass = String.class, example = "string"),
		@ApiImplicitParam(name = AssetAttributeVOMeta.VALUE_PATH, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetAttributeVOMeta.LABEL_NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetAttributeVOMeta.DIMENSION, value = "????????????", required = false, dataTypeClass = String.class, example = "maintainer"),
		@ApiImplicitParam(name = AssetAttributeVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "valid"),
		@ApiImplicitParam(name = AssetAttributeVOMeta.REQUIRED, value = "????????????", required = false, dataTypeClass = String.class, example = "0"),
		@ApiImplicitParam(name = AssetAttributeVOMeta.REQUIRED_MODIFY, value = "????????????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = AssetAttributeVOMeta.COMPONENT_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "text_input"),
		@ApiImplicitParam(name = AssetAttributeVOMeta.COMPONENT_CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetAttributeVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetAttributeVOMeta.OWNER, value = "??????", required = false, dataTypeClass = String.class, example = "asset")
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = AssetAttributeServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetAttributeServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(AssetAttributeVO assetAttributeVO) {
        Result result = assetAttributeService.insert(assetAttributeVO);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetAttributeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "000b5919-0f8b-11ec-ab08-00163e2e6a36")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = AssetAttributeServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetAttributeServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = assetAttributeService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????????
     */
    @ApiOperation(value = "???????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = "owner", value = "??????", required = true, dataTypeClass = String.class, example = "asset"),
		@ApiImplicitParam(name = "itemOwner", value = "??????", required = true, dataTypeClass = String.class, example = "asset")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetAttributeItemVOMeta.PAGE_INDEX, AssetAttributeItemVOMeta.PAGE_SIZE })
    @SentinelResource(value = AssetAttributeServiceProxy.QUERY_ATTRIBUTE_OWNER_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetAttributeServiceProxy.QUERY_ATTRIBUTE_OWNER_LIST)
    public Result<List<AssetAttribute>> queryAttributeOwnerList(String owner, String itemOwner) {
        Result<List<AssetAttribute>> result = new Result<>();
        List<AssetAttribute> list = assetAttributeService.queryAttributeOwnerList(owner, itemOwner);
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????????????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "??????????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetAttributeVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetAttributeServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetAttributeServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = assetAttributeService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetAttributeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "000b5919-0f8b-11ec-ab08-00163e2e6a36"),
		@ApiImplicitParam(name = AssetAttributeVOMeta.CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "maintainer_name"),
		@ApiImplicitParam(name = AssetAttributeVOMeta.LABEL, value = "????????????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = AssetAttributeVOMeta.VALUE_TYPE, value = "???????????????", required = false, dataTypeClass = String.class, example = "string"),
		@ApiImplicitParam(name = AssetAttributeVOMeta.VALUE_PATH, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetAttributeVOMeta.LABEL_NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetAttributeVOMeta.DIMENSION, value = "????????????", required = false, dataTypeClass = String.class, example = "maintainer"),
		@ApiImplicitParam(name = AssetAttributeVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "valid"),
		@ApiImplicitParam(name = AssetAttributeVOMeta.REQUIRED, value = "????????????", required = false, dataTypeClass = String.class, example = "0"),
		@ApiImplicitParam(name = AssetAttributeVOMeta.REQUIRED_MODIFY, value = "????????????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = AssetAttributeVOMeta.COMPONENT_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "text_input"),
		@ApiImplicitParam(name = AssetAttributeVOMeta.COMPONENT_CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetAttributeVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetAttributeVOMeta.OWNER, value = "??????", required = false, dataTypeClass = String.class, example = "asset")
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { AssetAttributeVOMeta.PAGE_INDEX, AssetAttributeVOMeta.PAGE_SIZE, AssetAttributeVOMeta.SEARCH_FIELD, AssetAttributeVOMeta.FUZZY_FIELD, AssetAttributeVOMeta.SEARCH_VALUE, AssetAttributeVOMeta.SORT_FIELD, AssetAttributeVOMeta.SORT_TYPE, AssetAttributeVOMeta.IDS })
    @SentinelResource(value = AssetAttributeServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetAttributeServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(AssetAttributeVO assetAttributeVO) {
        Result result = assetAttributeService.update(assetAttributeVO, SaveMode.NOT_NULL_FIELDS);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetAttributeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "000b5919-0f8b-11ec-ab08-00163e2e6a36"),
		@ApiImplicitParam(name = AssetAttributeVOMeta.CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "maintainer_name"),
		@ApiImplicitParam(name = AssetAttributeVOMeta.LABEL, value = "????????????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = AssetAttributeVOMeta.VALUE_TYPE, value = "???????????????", required = false, dataTypeClass = String.class, example = "string"),
		@ApiImplicitParam(name = AssetAttributeVOMeta.VALUE_PATH, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetAttributeVOMeta.LABEL_NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetAttributeVOMeta.DIMENSION, value = "????????????", required = false, dataTypeClass = String.class, example = "maintainer"),
		@ApiImplicitParam(name = AssetAttributeVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "valid"),
		@ApiImplicitParam(name = AssetAttributeVOMeta.REQUIRED, value = "????????????", required = false, dataTypeClass = String.class, example = "0"),
		@ApiImplicitParam(name = AssetAttributeVOMeta.REQUIRED_MODIFY, value = "????????????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = AssetAttributeVOMeta.COMPONENT_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "text_input"),
		@ApiImplicitParam(name = AssetAttributeVOMeta.COMPONENT_CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetAttributeVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetAttributeVOMeta.OWNER, value = "??????", required = false, dataTypeClass = String.class, example = "asset")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetAttributeVOMeta.PAGE_INDEX, AssetAttributeVOMeta.PAGE_SIZE, AssetAttributeVOMeta.SEARCH_FIELD, AssetAttributeVOMeta.FUZZY_FIELD, AssetAttributeVOMeta.SEARCH_VALUE, AssetAttributeVOMeta.SORT_FIELD, AssetAttributeVOMeta.SORT_TYPE, AssetAttributeVOMeta.IDS })
    @SentinelResource(value = AssetAttributeServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetAttributeServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(AssetAttributeVO assetAttributeVO) {
        Result result = assetAttributeService.save(assetAttributeVO, SaveMode.NOT_NULL_FIELDS);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetAttributeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = AssetAttributeServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetAttributeServiceProxy.GET_BY_ID)
    public Result<AssetAttribute> getById(String id) {
        Result<AssetAttribute> result = new Result<>();
        AssetAttribute assetAttribute = assetAttributeService.getById(id);
        result.success(true).data(assetAttribute);
        return result;
    }

    /**
     * ?????????????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "??????????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetAttributeVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetAttributeServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetAttributeServiceProxy.GET_BY_IDS)
    public Result<List<AssetAttribute>> getByIds(List<String> ids) {
        Result<List<AssetAttribute>> result = new Result<>();
        List<AssetAttribute> list = assetAttributeService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetAttributeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "000b5919-0f8b-11ec-ab08-00163e2e6a36"),
		@ApiImplicitParam(name = AssetAttributeVOMeta.CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "maintainer_name"),
		@ApiImplicitParam(name = AssetAttributeVOMeta.LABEL, value = "????????????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = AssetAttributeVOMeta.VALUE_TYPE, value = "???????????????", required = false, dataTypeClass = String.class, example = "string"),
		@ApiImplicitParam(name = AssetAttributeVOMeta.VALUE_PATH, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetAttributeVOMeta.LABEL_NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetAttributeVOMeta.DIMENSION, value = "????????????", required = false, dataTypeClass = String.class, example = "maintainer"),
		@ApiImplicitParam(name = AssetAttributeVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "valid"),
		@ApiImplicitParam(name = AssetAttributeVOMeta.REQUIRED, value = "????????????", required = false, dataTypeClass = String.class, example = "0"),
		@ApiImplicitParam(name = AssetAttributeVOMeta.REQUIRED_MODIFY, value = "????????????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = AssetAttributeVOMeta.COMPONENT_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "text_input"),
		@ApiImplicitParam(name = AssetAttributeVOMeta.COMPONENT_CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetAttributeVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetAttributeVOMeta.OWNER, value = "??????", required = false, dataTypeClass = String.class, example = "asset")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetAttributeVOMeta.PAGE_INDEX, AssetAttributeVOMeta.PAGE_SIZE })
    @SentinelResource(value = AssetAttributeServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetAttributeServiceProxy.QUERY_LIST)
    public Result<List<AssetAttribute>> queryList(AssetAttributeVO sample) {
        Result<List<AssetAttribute>> result = new Result<>();
        List<AssetAttribute> list = assetAttributeService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????????????????
     */
    @ApiOperation(value = "??????????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetAttributeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "000b5919-0f8b-11ec-ab08-00163e2e6a36"),
		@ApiImplicitParam(name = AssetAttributeVOMeta.CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "maintainer_name"),
		@ApiImplicitParam(name = AssetAttributeVOMeta.LABEL, value = "????????????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = AssetAttributeVOMeta.VALUE_TYPE, value = "???????????????", required = false, dataTypeClass = String.class, example = "string"),
		@ApiImplicitParam(name = AssetAttributeVOMeta.VALUE_PATH, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetAttributeVOMeta.LABEL_NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetAttributeVOMeta.DIMENSION, value = "????????????", required = false, dataTypeClass = String.class, example = "maintainer"),
		@ApiImplicitParam(name = AssetAttributeVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "valid"),
		@ApiImplicitParam(name = AssetAttributeVOMeta.REQUIRED, value = "????????????", required = false, dataTypeClass = String.class, example = "0"),
		@ApiImplicitParam(name = AssetAttributeVOMeta.REQUIRED_MODIFY, value = "????????????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = AssetAttributeVOMeta.COMPONENT_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "text_input"),
		@ApiImplicitParam(name = AssetAttributeVOMeta.COMPONENT_CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetAttributeVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetAttributeVOMeta.OWNER, value = "??????", required = false, dataTypeClass = String.class, example = "asset")
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = AssetAttributeServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetAttributeServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<AssetAttribute>> queryPagedList(AssetAttributeVO sample) {
        Result<PagedList<AssetAttribute>> result = new Result<>();
        PagedList<AssetAttribute> list = assetAttributeService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = AssetAttributeServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetAttributeServiceProxy.EXPORT_EXCEL)
    public void exportExcel(AssetAttributeVO sample, HttpServletResponse response) throws Exception {
        // ?????? Excel ??????
        ExcelWriter ew = assetAttributeService.exportExcel(sample);
        // ??????
        DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = AssetAttributeServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetAttributeServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        // ?????? Excel ??????
        ExcelWriter ew = assetAttributeService.exportExcelTemplate();
        // ??????
        DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
    }

    @SentinelResource(value = AssetAttributeServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetAttributeServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = assetAttributeService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
