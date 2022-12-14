package com.dt.platform.eam.controller;

import java.util.List;
import com.dt.platform.domain.eam.AssetTranfer;
import com.dt.platform.domain.eam.meta.AssetTranferVOMeta;
import com.dt.platform.proxy.eam.AssetRepairServiceProxy;
import com.dt.platform.proxy.eam.AssetTranferServiceProxy;
import com.github.foxnic.commons.collection.CollectorUtil;
import org.github.foxnic.web.domain.bpm.BpmActionResult;
import org.github.foxnic.web.domain.bpm.BpmEvent;
import org.github.foxnic.web.domain.changes.ProcessApproveVO;
import org.github.foxnic.web.domain.hrm.Person;
import org.github.foxnic.web.proxy.bpm.BpmCallbackController;
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
import com.dt.platform.proxy.eam.AssetStorageServiceProxy;
import com.dt.platform.domain.eam.meta.AssetStorageVOMeta;
import com.dt.platform.domain.eam.AssetStorage;
import com.dt.platform.domain.eam.AssetStorageVO;
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
import com.dt.platform.domain.eam.meta.AssetStorageMeta;
import com.dt.platform.domain.eam.Asset;
import org.github.foxnic.web.domain.hrm.Organization;
import org.github.foxnic.web.domain.hrm.Employee;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IAssetStorageService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-05-19 13:26:20
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("EamAssetStorageController")
public class AssetStorageController extends SuperController implements BpmCallbackController {

    @Autowired
    private IAssetStorageService assetStorageService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetStorageVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.SUPPLIER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.SUPPLIER_INFO, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.OWN_COMPANY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.MANAGER_USER_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.LOCATION_NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.CUSTOM_DATA, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetStorageVOMeta.SELECTED_CODE", value = "??????", required = false, dataTypeClass = String.class, example = "1653021634000"),
		@ApiImplicitParam(name = AssetStorageVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "1653021634000")
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = AssetStorageServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStorageServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(AssetStorageVO assetStorageVO) {
        Result result = assetStorageService.insert(assetStorageVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetStorageVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = AssetStorageServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStorageServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = assetStorageService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetStorageVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetStorageServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStorageServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = assetStorageService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetStorageVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.SUPPLIER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.SUPPLIER_INFO, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.OWN_COMPANY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.MANAGER_USER_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.LOCATION_NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.CUSTOM_DATA, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetStorageVOMeta.SELECTED_CODE", value = "??????", required = false, dataTypeClass = String.class, example = "1653021634000"),
		@ApiImplicitParam(name = AssetStorageVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "1653021634000")
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { AssetStorageVOMeta.PAGE_INDEX, AssetStorageVOMeta.PAGE_SIZE, AssetStorageVOMeta.SEARCH_FIELD, AssetStorageVOMeta.FUZZY_FIELD, AssetStorageVOMeta.SEARCH_VALUE, AssetStorageVOMeta.DIRTY_FIELDS, AssetStorageVOMeta.SORT_FIELD, AssetStorageVOMeta.SORT_TYPE, AssetStorageVOMeta.IDS })
    @SentinelResource(value = AssetStorageServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStorageServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(AssetStorageVO assetStorageVO) {
        Result result = assetStorageService.update(assetStorageVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetStorageVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.SUPPLIER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.SUPPLIER_INFO, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.OWN_COMPANY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.MANAGER_USER_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.LOCATION_NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.CUSTOM_DATA, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetStorageVOMeta.SELECTED_CODE", value = "??????", required = false, dataTypeClass = String.class, example = "1653021634000"),
		@ApiImplicitParam(name = AssetStorageVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "1653021634000")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetStorageVOMeta.PAGE_INDEX, AssetStorageVOMeta.PAGE_SIZE, AssetStorageVOMeta.SEARCH_FIELD, AssetStorageVOMeta.FUZZY_FIELD, AssetStorageVOMeta.SEARCH_VALUE, AssetStorageVOMeta.DIRTY_FIELDS, AssetStorageVOMeta.SORT_FIELD, AssetStorageVOMeta.SORT_TYPE, AssetStorageVOMeta.IDS })
    @SentinelResource(value = AssetStorageServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStorageServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(AssetStorageVO assetStorageVO) {
        Result result = assetStorageService.save(assetStorageVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetStorageVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = AssetStorageServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStorageServiceProxy.GET_BY_ID)
    public Result<AssetStorage> getById(String id) {
        Result<AssetStorage> result = new Result<>();
        AssetStorage assetStorage = assetStorageService.getById(id);
        // join ???????????????
        assetStorageService.dao().fill(assetStorage).with("originator").with("ownerCompany").with(AssetStorageMeta.SUPPLIER).with("managerUser").execute();
        assetStorageService.dao().join(assetStorage.getOriginator(), Person.class);
        assetStorageService.dao().join(assetStorage.getManagerUser(), Person.class);
        result.success(true).data(assetStorage);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetStorageVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetStorageServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStorageServiceProxy.GET_BY_IDS)
    public Result<List<AssetStorage>> getByIds(List<String> ids) {
        Result<List<AssetStorage>> result = new Result<>();
        List<AssetStorage> list = assetStorageService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetStorageVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.SUPPLIER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.SUPPLIER_INFO, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.OWN_COMPANY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.MANAGER_USER_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.LOCATION_NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.CUSTOM_DATA, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetStorageVOMeta.SELECTED_CODE", value = "??????", required = false, dataTypeClass = String.class, example = "1653021634000"),
		@ApiImplicitParam(name = AssetStorageVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "1653021634000")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetStorageVOMeta.PAGE_INDEX, AssetStorageVOMeta.PAGE_SIZE })
    @SentinelResource(value = AssetStorageServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStorageServiceProxy.QUERY_LIST)
    public Result<List<AssetStorage>> queryList(AssetStorageVO sample) {
        Result<List<AssetStorage>> result = new Result<>();
        List<AssetStorage> list = assetStorageService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetStorageVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.SUPPLIER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.SUPPLIER_INFO, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.OWN_COMPANY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.MANAGER_USER_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.LOCATION_NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStorageVOMeta.CUSTOM_DATA, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetStorageVOMeta.SELECTED_CODE", value = "??????", required = false, dataTypeClass = String.class, example = "1653021634000"),
		@ApiImplicitParam(name = AssetStorageVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "1653021634000")
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = AssetStorageServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStorageServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<AssetStorage>> queryPagedList(AssetStorageVO sample) {
        Result<PagedList<AssetStorage>> result = new Result<>();
        PagedList<AssetStorage> list = assetStorageService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        assetStorageService.dao().fill(list).with("ownerCompany").with(AssetStorageMeta.SUPPLIER).with("originator").with("managerUser").execute();
        List<Employee> managers = CollectorUtil.collectList(list, AssetStorage::getManagerUser);
        assetStorageService.dao().join(managers, Person.class);
        List<Employee> originators = CollectorUtil.collectList(list, AssetStorage::getOriginator);
        assetStorageService.dao().join(originators, Person.class);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????
     */
    @ApiOperation(value = "??????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetStorageVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 13)
    @SentinelResource(value = AssetStorageServiceProxy.CONFIRM_OPERATION, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetStorageServiceProxy.CONFIRM_OPERATION)
    public Result confirmOperation(String id) {
        return assetStorageService.confirmOperation(id);
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = AssetStorageServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetStorageServiceProxy.EXPORT_EXCEL)
    public void exportExcel(AssetStorageVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = assetStorageService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = AssetStorageServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetStorageServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = assetStorageService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     *  ??????????????????
     */
    @SentinelResource(value = AssetStorageServiceProxy.BPM_CALLBACK, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStorageServiceProxy.BPM_CALLBACK)
    public BpmActionResult onProcessCallback(BpmEvent event) {
        return assetStorageService.onProcessCallback(event);
    }

    @SentinelResource(value = AssetStorageServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetStorageServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = assetStorageService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
