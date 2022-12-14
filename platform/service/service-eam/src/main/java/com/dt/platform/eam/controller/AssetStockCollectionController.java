package com.dt.platform.eam.controller;

import java.util.List;
import com.dt.platform.domain.eam.*;
import com.dt.platform.domain.eam.meta.AssetDataChangeVOMeta;
import com.dt.platform.proxy.eam.AssetDataChangeServiceProxy;
import com.github.foxnic.commons.collection.CollectorUtil;
import org.github.foxnic.web.domain.changes.ProcessApproveVO;
import org.github.foxnic.web.domain.hrm.Person;
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
import com.dt.platform.proxy.eam.AssetStockCollectionServiceProxy;
import com.dt.platform.domain.eam.meta.AssetStockCollectionVOMeta;
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
import com.dt.platform.domain.eam.meta.AssetStockCollectionMeta;
import org.github.foxnic.web.domain.hrm.Organization;
import org.github.foxnic.web.domain.hrm.Employee;
import com.dt.platform.domain.eam.meta.AssetCollectionMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IAssetStockCollectionService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2021-11-29 13:22:20
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("EamAssetStockCollectionController")
public class AssetStockCollectionController extends SuperController {

    @Autowired
    private IAssetStockCollectionService assetStockCollectionService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "517688803410841600"),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.OWNER_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.USE_ORGANIZATION_ID, value = "???????????????/??????", required = false, dataTypeClass = String.class, example = "500994919175819264"),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.USE_USER_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "488736068133208064"),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.POSITION_ID, value = "???????????????", required = false, dataTypeClass = String.class, example = "472024543184027649"),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.POSITION_DETAIL, value = "????????????", required = false, dataTypeClass = String.class, example = "?????????????????????"),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.COLLECTION_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = "2021-11-08 12:00:00"),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = AssetStockCollectionServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStockCollectionServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(AssetStockCollectionVO assetStockCollectionVO) {
        Result result = assetStockCollectionService.insert(assetStockCollectionVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "517688803410841600")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = AssetStockCollectionServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStockCollectionServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = assetStockCollectionService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetStockCollectionServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStockCollectionServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = assetStockCollectionService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "517688803410841600"),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.OWNER_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.USE_ORGANIZATION_ID, value = "???????????????/??????", required = false, dataTypeClass = String.class, example = "500994919175819264"),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.USE_USER_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "488736068133208064"),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.POSITION_ID, value = "???????????????", required = false, dataTypeClass = String.class, example = "472024543184027649"),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.POSITION_DETAIL, value = "????????????", required = false, dataTypeClass = String.class, example = "?????????????????????"),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.COLLECTION_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = "2021-11-08 12:00:00"),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { AssetStockCollectionVOMeta.PAGE_INDEX, AssetStockCollectionVOMeta.PAGE_SIZE, AssetStockCollectionVOMeta.SEARCH_FIELD, AssetStockCollectionVOMeta.FUZZY_FIELD, AssetStockCollectionVOMeta.SEARCH_VALUE, AssetStockCollectionVOMeta.DIRTY_FIELDS, AssetStockCollectionVOMeta.SORT_FIELD, AssetStockCollectionVOMeta.SORT_TYPE, AssetStockCollectionVOMeta.IDS })
    @SentinelResource(value = AssetStockCollectionServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStockCollectionServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(AssetStockCollectionVO assetStockCollectionVO) {
        Result result = assetStockCollectionService.update(assetStockCollectionVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "517688803410841600"),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.OWNER_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.USE_ORGANIZATION_ID, value = "???????????????/??????", required = false, dataTypeClass = String.class, example = "500994919175819264"),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.USE_USER_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "488736068133208064"),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.POSITION_ID, value = "???????????????", required = false, dataTypeClass = String.class, example = "472024543184027649"),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.POSITION_DETAIL, value = "????????????", required = false, dataTypeClass = String.class, example = "?????????????????????"),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.COLLECTION_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = "2021-11-08 12:00:00"),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetStockCollectionVOMeta.PAGE_INDEX, AssetStockCollectionVOMeta.PAGE_SIZE, AssetStockCollectionVOMeta.SEARCH_FIELD, AssetStockCollectionVOMeta.FUZZY_FIELD, AssetStockCollectionVOMeta.SEARCH_VALUE, AssetStockCollectionVOMeta.DIRTY_FIELDS, AssetStockCollectionVOMeta.SORT_FIELD, AssetStockCollectionVOMeta.SORT_TYPE, AssetStockCollectionVOMeta.IDS })
    @SentinelResource(value = AssetStockCollectionServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStockCollectionServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(AssetStockCollectionVO assetStockCollectionVO) {
        Result result = assetStockCollectionService.save(assetStockCollectionVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = AssetStockCollectionServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStockCollectionServiceProxy.GET_BY_ID)
    public Result<AssetStockCollection> getById(String id) {
        Result<AssetStockCollection> result = new Result<>();
        AssetStockCollection assetStockCollection = assetStockCollectionService.getById(id);
        // join ???????????????
        assetStockCollectionService.dao().fill(assetStockCollection).with("originator").with("useOrganization").with("useUser").with(AssetCollectionMeta.POSITION).execute();
        result.success(true).data(assetStockCollection);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetStockCollectionServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStockCollectionServiceProxy.GET_BY_IDS)
    public Result<List<AssetStockCollection>> getByIds(List<String> ids) {
        Result<List<AssetStockCollection>> result = new Result<>();
        List<AssetStockCollection> list = assetStockCollectionService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "517688803410841600"),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.OWNER_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.USE_ORGANIZATION_ID, value = "???????????????/??????", required = false, dataTypeClass = String.class, example = "500994919175819264"),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.USE_USER_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "488736068133208064"),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.POSITION_ID, value = "???????????????", required = false, dataTypeClass = String.class, example = "472024543184027649"),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.POSITION_DETAIL, value = "????????????", required = false, dataTypeClass = String.class, example = "?????????????????????"),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.COLLECTION_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = "2021-11-08 12:00:00"),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetStockCollectionVOMeta.PAGE_INDEX, AssetStockCollectionVOMeta.PAGE_SIZE })
    @SentinelResource(value = AssetStockCollectionServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStockCollectionServiceProxy.QUERY_LIST)
    public Result<List<AssetStockCollection>> queryList(AssetStockCollectionVO sample) {
        Result<List<AssetStockCollection>> result = new Result<>();
        List<AssetStockCollection> list = assetStockCollectionService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "517688803410841600"),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.OWNER_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.USE_ORGANIZATION_ID, value = "???????????????/??????", required = false, dataTypeClass = String.class, example = "500994919175819264"),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.USE_USER_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "488736068133208064"),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.POSITION_ID, value = "???????????????", required = false, dataTypeClass = String.class, example = "472024543184027649"),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.POSITION_DETAIL, value = "????????????", required = false, dataTypeClass = String.class, example = "?????????????????????"),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.COLLECTION_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = "2021-11-08 12:00:00"),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = AssetStockCollectionServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStockCollectionServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<AssetStockCollection>> queryPagedList(AssetStockCollectionVO sample) {
        Result<PagedList<AssetStockCollection>> result = new Result<>();
        PagedList<AssetStockCollection> list = assetStockCollectionService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        assetStockCollectionService.dao().fill(list).with("originator").with("useOrganization").with("useUser").with(AssetCollectionMeta.POSITION).execute();
        // ????????????
        List<Employee> employees = CollectorUtil.collectList(list, AssetStockCollection::getUseUser);
        assetStockCollectionService.dao().join(employees, Person.class);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockCollectionVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 13)
    @SentinelResource(value = AssetStockCollectionServiceProxy.CONFIRM_OPERATION, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetStockCollectionServiceProxy.CONFIRM_OPERATION)
    public Result confirmOperation(String id) {
        return assetStockCollectionService.confirmOperation(id);
    }

    /**
     * ??????
     */
    @ApiOperation(value = "??????")
    @ApiOperationSupport(order = 16)
    @SentinelResource(value = AssetStockCollectionServiceProxy.STOCK_COLLECTION, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetStockCollectionServiceProxy.STOCK_COLLECTION)
    public Result stockCollection(String assetOwnerId, String assetSelectedCode, String sourceAssetId, String cnt) {
        return assetStockCollectionService.stockCollection(assetOwnerId, assetSelectedCode, sourceAssetId, Integer.parseInt(cnt));
    }

    /**
     * ??????
     */
    @ApiOperation(value = "??????")
    @ApiOperationSupport(order = 17)
    @SentinelResource(value = AssetStockCollectionServiceProxy.STOCK_DISTRIBUTE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetStockCollectionServiceProxy.STOCK_DISTRIBUTE)
    public Result stockDistribute(String assetOwnerId, String assetSelectedCode, String sourceAssetId, String cnt) {
        return assetStockCollectionService.stockDistribute(assetOwnerId, assetSelectedCode, sourceAssetId, Integer.parseInt(cnt));
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = AssetStockCollectionServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetStockCollectionServiceProxy.EXPORT_EXCEL)
    public void exportExcel(AssetStockCollectionVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = assetStockCollectionService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = AssetStockCollectionServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetStockCollectionServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = assetStockCollectionService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = AssetStockCollectionServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetStockCollectionServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = assetStockCollectionService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
