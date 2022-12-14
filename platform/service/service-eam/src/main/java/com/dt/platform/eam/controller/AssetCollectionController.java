package com.dt.platform.eam.controller;

import java.util.List;
import com.dt.platform.constants.enums.eam.AssetHandleStatusEnum;
import com.dt.platform.domain.eam.*;
import com.dt.platform.domain.eam.meta.AssetBorrowVOMeta;
import com.dt.platform.proxy.eam.AssetAllocationServiceProxy;
import com.dt.platform.proxy.eam.AssetBorrowServiceProxy;
import com.github.foxnic.commons.collection.CollectorUtil;
import com.github.foxnic.commons.lang.StringUtil;
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
import com.dt.platform.proxy.eam.AssetCollectionServiceProxy;
import com.dt.platform.domain.eam.meta.AssetCollectionVOMeta;
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
import com.dt.platform.domain.eam.meta.AssetCollectionMeta;
import org.github.foxnic.web.domain.hrm.Employee;
import org.github.foxnic.web.domain.hrm.Organization;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IAssetCollectionService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2021-09-20 17:00:01
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("EamAssetCollectionController")
public class AssetCollectionController extends SuperController implements BpmCallbackController {

    @Autowired
    private IAssetCollectionService assetCollectionService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetCollectionVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "480482454377865216"),
		@ApiImplicitParam(name = AssetCollectionVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionVOMeta.USE_ORGANIZATION_ID, value = "???????????????/??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetCollectionVOMeta.USE_USER_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetCollectionVOMeta.POSITION_ID, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionVOMeta.POSITION_DETAIL, value = "????????????", required = false, dataTypeClass = String.class, example = "asdf"),
		@ApiImplicitParam(name = AssetCollectionVOMeta.COLLECTION_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = "2021-08-09 12:00:00"),
		@ApiImplicitParam(name = AssetCollectionVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "asf"),
		@ApiImplicitParam(name = AssetCollectionVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetCollectionVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = AssetCollectionServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetCollectionServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(AssetCollectionVO assetCollectionVO) {
        return assetCollectionService.insert(assetCollectionVO);
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetCollectionVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "480482454377865216")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = AssetCollectionServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetCollectionServiceProxy.DELETE)
    public Result deleteById(String id) {
        AssetCollection assetCollection = assetCollectionService.getById(id);
        if (AssetHandleStatusEnum.CANCEL.code().equals(assetCollection.getStatus()) || AssetHandleStatusEnum.INCOMPLETE.code().equals(assetCollection.getStatus())) {
            Result result = assetCollectionService.deleteByIdLogical(id);
            return result;
        } else {
            return ErrorDesc.failure().message("???????????????????????????");
        }
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetCollectionVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetCollectionServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetCollectionServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = assetCollectionService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetCollectionVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "480482454377865216"),
		@ApiImplicitParam(name = AssetCollectionVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionVOMeta.USE_ORGANIZATION_ID, value = "???????????????/??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetCollectionVOMeta.USE_USER_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetCollectionVOMeta.POSITION_ID, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionVOMeta.POSITION_DETAIL, value = "????????????", required = false, dataTypeClass = String.class, example = "asdf"),
		@ApiImplicitParam(name = AssetCollectionVOMeta.COLLECTION_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = "2021-08-09 12:00:00"),
		@ApiImplicitParam(name = AssetCollectionVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "asf"),
		@ApiImplicitParam(name = AssetCollectionVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetCollectionVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { AssetCollectionVOMeta.PAGE_INDEX, AssetCollectionVOMeta.PAGE_SIZE, AssetCollectionVOMeta.SEARCH_FIELD, AssetCollectionVOMeta.FUZZY_FIELD, AssetCollectionVOMeta.SEARCH_VALUE, AssetCollectionVOMeta.SORT_FIELD, AssetCollectionVOMeta.SORT_TYPE, AssetCollectionVOMeta.IDS })
    @SentinelResource(value = AssetCollectionServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetCollectionServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(AssetCollectionVO assetCollectionVO) {
        AssetCollection assetCollection = assetCollectionService.getById(assetCollectionVO.getId());
        if (AssetHandleStatusEnum.COMPLETE.code().equals(assetCollection.getStatus()) || AssetHandleStatusEnum.APPROVAL.code().equals(assetCollection.getStatus())) {
            return ErrorDesc.failure().message("???????????????????????????");
        }
        Result result = assetCollectionService.update(assetCollectionVO, SaveMode.NOT_NULL_FIELDS);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetCollectionVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "480482454377865216"),
		@ApiImplicitParam(name = AssetCollectionVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionVOMeta.USE_ORGANIZATION_ID, value = "???????????????/??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetCollectionVOMeta.USE_USER_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetCollectionVOMeta.POSITION_ID, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionVOMeta.POSITION_DETAIL, value = "????????????", required = false, dataTypeClass = String.class, example = "asdf"),
		@ApiImplicitParam(name = AssetCollectionVOMeta.COLLECTION_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = "2021-08-09 12:00:00"),
		@ApiImplicitParam(name = AssetCollectionVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "asf"),
		@ApiImplicitParam(name = AssetCollectionVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetCollectionVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetCollectionVOMeta.PAGE_INDEX, AssetCollectionVOMeta.PAGE_SIZE, AssetCollectionVOMeta.SEARCH_FIELD, AssetCollectionVOMeta.FUZZY_FIELD, AssetCollectionVOMeta.SEARCH_VALUE, AssetCollectionVOMeta.SORT_FIELD, AssetCollectionVOMeta.SORT_TYPE, AssetCollectionVOMeta.IDS })
    @SentinelResource(value = AssetCollectionServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetCollectionServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(AssetCollectionVO assetCollectionVO) {
        Result result = assetCollectionService.save(assetCollectionVO, SaveMode.NOT_NULL_FIELDS);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetCollectionVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = AssetCollectionServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetCollectionServiceProxy.GET_BY_ID)
    public Result<AssetCollection> getById(String id) {
        Result<AssetCollection> result = new Result<>();
        AssetCollection assetCollection = assetCollectionService.getById(id);
        // ????????? ???????????????/?????? ??????
        assetCollectionService.join(assetCollection, AssetCollectionMeta.USE_ORGANIZATION);
        // ????????? ???????????? ??????
        assetCollectionService.join(assetCollection, AssetCollectionMeta.USE_USER);
        // ????????? ???????????? ??????
        assetCollectionService.join(assetCollection, AssetCollectionMeta.POSITION);
        // ????????? ????????? ??????
        assetCollectionService.join(assetCollection, AssetCollectionMeta.ORIGINATOR);
        assetCollectionService.join(assetCollection, AssetCollectionMeta.ASSET_LIST);
        assetCollectionService.dao().join(assetCollection.getUseUser(), Person.class);
        assetCollectionService.dao().join(assetCollection.getOriginator(), Person.class);
        result.success(true).data(assetCollection);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetCollectionVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetCollectionServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetCollectionServiceProxy.GET_BY_IDS)
    public Result<List<AssetCollection>> getByIds(List<String> ids) {
        Result<List<AssetCollection>> result = new Result<>();
        List<AssetCollection> list = assetCollectionService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetCollectionVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "480482454377865216"),
		@ApiImplicitParam(name = AssetCollectionVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionVOMeta.USE_ORGANIZATION_ID, value = "???????????????/??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetCollectionVOMeta.USE_USER_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetCollectionVOMeta.POSITION_ID, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionVOMeta.POSITION_DETAIL, value = "????????????", required = false, dataTypeClass = String.class, example = "asdf"),
		@ApiImplicitParam(name = AssetCollectionVOMeta.COLLECTION_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = "2021-08-09 12:00:00"),
		@ApiImplicitParam(name = AssetCollectionVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "asf"),
		@ApiImplicitParam(name = AssetCollectionVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetCollectionVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetCollectionVOMeta.PAGE_INDEX, AssetCollectionVOMeta.PAGE_SIZE })
    @SentinelResource(value = AssetCollectionServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetCollectionServiceProxy.QUERY_LIST)
    public Result<List<AssetCollection>> queryList(AssetCollectionVO sample) {
        Result<List<AssetCollection>> result = new Result<>();
        List<AssetCollection> list = assetCollectionService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetCollectionVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "480482454377865216"),
		@ApiImplicitParam(name = AssetCollectionVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionVOMeta.USE_ORGANIZATION_ID, value = "???????????????/??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetCollectionVOMeta.USE_USER_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetCollectionVOMeta.POSITION_ID, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionVOMeta.POSITION_DETAIL, value = "????????????", required = false, dataTypeClass = String.class, example = "asdf"),
		@ApiImplicitParam(name = AssetCollectionVOMeta.COLLECTION_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = "2021-08-09 12:00:00"),
		@ApiImplicitParam(name = AssetCollectionVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "asf"),
		@ApiImplicitParam(name = AssetCollectionVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetCollectionVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = AssetCollectionServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetCollectionServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<AssetCollection>> queryPagedList(AssetCollectionVO sample) {
        Result<PagedList<AssetCollection>> result = new Result<>();
        PagedList<AssetCollection> list = assetCollectionService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // ????????? ???????????????/?????? ??????
        assetCollectionService.join(list, AssetCollectionMeta.USE_ORGANIZATION);
        // ????????? ???????????? ??????
        assetCollectionService.join(list, AssetCollectionMeta.USE_USER);
        // ????????? ???????????? ??????
        assetCollectionService.join(list, AssetCollectionMeta.POSITION);
        // ????????? ????????? ??????
        assetCollectionService.join(list, AssetCollectionMeta.ORIGINATOR);
        List<Employee> employees = CollectorUtil.collectList(list, AssetCollection::getOriginator);
        List<Employee> useUsers = CollectorUtil.collectList(list, AssetCollection::getUseUser);

        assetCollectionService.dao().join(employees, Person.class);
        assetCollectionService.dao().join(useUsers, Person.class);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetCollectionVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 13)
    @SentinelResource(value = AssetCollectionServiceProxy.CONFIRM_OPERATION, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetCollectionServiceProxy.CONFIRM_OPERATION)
    public Result confirmOperation(String id) {
        return assetCollectionService.confirmOperation(id);
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = AssetCollectionServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetCollectionServiceProxy.EXPORT_EXCEL)
    public void exportExcel(AssetCollectionVO sample, HttpServletResponse response) throws Exception {
        // ?????? Excel ??????
        ExcelWriter ew = assetCollectionService.exportExcel(sample);
        // ??????
        DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = AssetCollectionServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetCollectionServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        // ?????? Excel ??????
        ExcelWriter ew = assetCollectionService.exportExcelTemplate();
        // ??????
        DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
    }

    /**
     *  ??????????????????
     */
    @SentinelResource(value = AssetCollectionServiceProxy.BPM_CALLBACK, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetCollectionServiceProxy.BPM_CALLBACK)
    public BpmActionResult onProcessCallback(BpmEvent event) {
        return assetCollectionService.onProcessCallback(event);
    }

    @SentinelResource(value = AssetCollectionServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetCollectionServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = assetCollectionService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
