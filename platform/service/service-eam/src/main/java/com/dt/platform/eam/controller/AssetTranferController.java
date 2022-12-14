package com.dt.platform.eam.controller;

import java.util.List;
import com.dt.platform.constants.enums.eam.AssetHandleStatusEnum;
import com.dt.platform.domain.eam.*;
import com.dt.platform.domain.eam.meta.AssetScrapVOMeta;
import com.dt.platform.domain.ops.meta.InformationSystemMeta;
import com.dt.platform.proxy.eam.AssetRepairServiceProxy;
import com.dt.platform.proxy.eam.AssetScrapServiceProxy;
import com.dt.platform.proxy.eam.AssetStorageServiceProxy;
import com.github.foxnic.commons.collection.CollectorUtil;
import com.github.foxnic.commons.lang.StringUtil;
import org.github.foxnic.web.domain.bpm.BpmActionResult;
import org.github.foxnic.web.domain.bpm.BpmEvent;
import org.github.foxnic.web.domain.changes.ProcessApproveVO;
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
import com.dt.platform.proxy.eam.AssetTranferServiceProxy;
import com.dt.platform.domain.eam.meta.AssetTranferVOMeta;
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
import com.dt.platform.domain.eam.meta.AssetTranferMeta;
import org.github.foxnic.web.domain.hrm.Person;
import org.github.foxnic.web.domain.hrm.Employee;
import org.github.foxnic.web.domain.hrm.Organization;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IAssetTranferService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2021-09-19 07:31:48
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("EamAssetTranferController")
public class AssetTranferController extends SuperController implements BpmCallbackController {

    @Autowired
    private IAssetTranferService assetTranferService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetTranferVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "480668065630007296"),
		@ApiImplicitParam(name = AssetTranferVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.OUT_USE_ORGANIZATION_ID, value = "??????????????????/??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetTranferVOMeta.IN_USE_ORGANIZATION_ID, value = "??????????????????/??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetTranferVOMeta.MANAGER_ID, value = "???????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetTranferVOMeta.USE_USER_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.POSITION_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.POSITION_DETAIL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetTranferVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = AssetTranferServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetTranferServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(AssetTranferVO assetTranferVO) {
        return assetTranferService.insert(assetTranferVO);
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetTranferVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "480668065630007296")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = AssetTranferServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetTranferServiceProxy.DELETE)
    public Result deleteById(String id) {
        AssetTranfer assetTranfer = assetTranferService.getById(id);
        if (AssetHandleStatusEnum.CANCEL.code().equals(assetTranfer.getStatus()) || AssetHandleStatusEnum.INCOMPLETE.code().equals(assetTranfer.getStatus())) {
            Result result = assetTranferService.deleteByIdLogical(id);
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
		@ApiImplicitParam(name = AssetTranferVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetTranferServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetTranferServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = assetTranferService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetTranferVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "480668065630007296"),
		@ApiImplicitParam(name = AssetTranferVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.OUT_USE_ORGANIZATION_ID, value = "??????????????????/??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetTranferVOMeta.IN_USE_ORGANIZATION_ID, value = "??????????????????/??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetTranferVOMeta.MANAGER_ID, value = "???????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetTranferVOMeta.USE_USER_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.POSITION_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.POSITION_DETAIL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetTranferVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { AssetTranferVOMeta.PAGE_INDEX, AssetTranferVOMeta.PAGE_SIZE, AssetTranferVOMeta.SEARCH_FIELD, AssetTranferVOMeta.FUZZY_FIELD, AssetTranferVOMeta.SEARCH_VALUE, AssetTranferVOMeta.SORT_FIELD, AssetTranferVOMeta.SORT_TYPE, AssetTranferVOMeta.IDS })
    @SentinelResource(value = AssetTranferServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetTranferServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(AssetTranferVO assetTranferVO) {
        AssetTranfer assetTranfer = assetTranferService.getById(assetTranferVO.getId());
        if (AssetHandleStatusEnum.COMPLETE.code().equals(assetTranfer.getStatus()) || AssetHandleStatusEnum.APPROVAL.code().equals(assetTranfer.getStatus())) {
            return ErrorDesc.failure().message("???????????????????????????");
        }
        Result result = assetTranferService.update(assetTranferVO, SaveMode.NOT_NULL_FIELDS);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetTranferVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "480668065630007296"),
		@ApiImplicitParam(name = AssetTranferVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.OUT_USE_ORGANIZATION_ID, value = "??????????????????/??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetTranferVOMeta.IN_USE_ORGANIZATION_ID, value = "??????????????????/??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetTranferVOMeta.MANAGER_ID, value = "???????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetTranferVOMeta.USE_USER_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.POSITION_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.POSITION_DETAIL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetTranferVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetTranferVOMeta.PAGE_INDEX, AssetTranferVOMeta.PAGE_SIZE, AssetTranferVOMeta.SEARCH_FIELD, AssetTranferVOMeta.FUZZY_FIELD, AssetTranferVOMeta.SEARCH_VALUE, AssetTranferVOMeta.SORT_FIELD, AssetTranferVOMeta.SORT_TYPE, AssetTranferVOMeta.IDS })
    @SentinelResource(value = AssetTranferServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetTranferServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(AssetTranferVO assetTranferVO) {
        Result result = assetTranferService.save(assetTranferVO, SaveMode.NOT_NULL_FIELDS);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetTranferVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = AssetTranferServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetTranferServiceProxy.GET_BY_ID)
    public Result<AssetTranfer> getById(String id) {
        Result<AssetTranfer> result = new Result<>();
        AssetTranfer assetTranfer = assetTranferService.getById(id);
        // ????????? ??????????????????/?????? ??????
        assetTranferService.join(assetTranfer, AssetTranferMeta.OUT_USE_ORGANIZATION);
        // ????????? ??????????????????/?????? ??????
        assetTranferService.join(assetTranfer, AssetTranferMeta.IN_USE_ORGANIZATION);
        // ????????? ???????????? ??????
        assetTranferService.join(assetTranfer, AssetTranferMeta.POSITION);
        assetTranferService.join(assetTranfer, AssetTranferMeta.ORIGINATOR);
        assetTranferService.join(assetTranfer, AssetTranferMeta.MANAGER);
        assetTranferService.join(assetTranfer, AssetTranferMeta.USE_USER);
        assetTranferService.dao().join(assetTranfer.getOriginator(), Person.class);
        assetTranferService.dao().join(assetTranfer.getUseUser(), Person.class);
        assetTranferService.dao().join(assetTranfer.getManager(), Person.class);
        result.success(true).data(assetTranfer);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetTranferVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetTranferServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetTranferServiceProxy.GET_BY_IDS)
    public Result<List<AssetTranfer>> getByIds(List<String> ids) {
        Result<List<AssetTranfer>> result = new Result<>();
        List<AssetTranfer> list = assetTranferService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetTranferVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "480668065630007296"),
		@ApiImplicitParam(name = AssetTranferVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.OUT_USE_ORGANIZATION_ID, value = "??????????????????/??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetTranferVOMeta.IN_USE_ORGANIZATION_ID, value = "??????????????????/??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetTranferVOMeta.MANAGER_ID, value = "???????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetTranferVOMeta.USE_USER_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.POSITION_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.POSITION_DETAIL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetTranferVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetTranferVOMeta.PAGE_INDEX, AssetTranferVOMeta.PAGE_SIZE })
    @SentinelResource(value = AssetTranferServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetTranferServiceProxy.QUERY_LIST)
    public Result<List<AssetTranfer>> queryList(AssetTranferVO sample) {
        Result<List<AssetTranfer>> result = new Result<>();
        List<AssetTranfer> list = assetTranferService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetTranferVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "480668065630007296"),
		@ApiImplicitParam(name = AssetTranferVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.OUT_USE_ORGANIZATION_ID, value = "??????????????????/??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetTranferVOMeta.IN_USE_ORGANIZATION_ID, value = "??????????????????/??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetTranferVOMeta.MANAGER_ID, value = "???????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetTranferVOMeta.USE_USER_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.POSITION_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.POSITION_DETAIL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetTranferVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetTranferVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = AssetTranferServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetTranferServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<AssetTranfer>> queryPagedList(AssetTranferVO sample) {
        Result<PagedList<AssetTranfer>> result = new Result<>();
        PagedList<AssetTranfer> list = assetTranferService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // ????????? ??????????????????/?????? ??????
        assetTranferService.join(list, AssetTranferMeta.OUT_USE_ORGANIZATION);
        // ????????? ??????????????????/?????? ??????
        assetTranferService.join(list, AssetTranferMeta.IN_USE_ORGANIZATION);
        // ????????? ???????????? ??????
        assetTranferService.join(list, AssetTranferMeta.POSITION);
        assetTranferService.join(list, AssetTranferMeta.ORIGINATOR);
        assetTranferService.join(list, AssetTranferMeta.MANAGER);
        assetTranferService.join(list, AssetTranferMeta.USE_USER);
        List<Employee> employees = CollectorUtil.collectList(list, AssetTranfer::getOriginator);
        assetTranferService.dao().join(employees, Person.class);
        List<Employee> useusers = CollectorUtil.collectList(list, AssetTranfer::getUseUser);
        assetTranferService.dao().join(useusers, Person.class);
        List<Employee> managers = CollectorUtil.collectList(list, AssetTranfer::getManager);
        assetTranferService.dao().join(managers, Person.class);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetTranferVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 13)
    @SentinelResource(value = AssetTranferServiceProxy.CONFIRM_OPERATION, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetTranferServiceProxy.CONFIRM_OPERATION)
    public Result confirmOperation(String id) {
        return assetTranferService.confirmOperation(id);
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = AssetTranferServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetTranferServiceProxy.EXPORT_EXCEL)
    public void exportExcel(AssetTranferVO sample, HttpServletResponse response) throws Exception {
        // ?????? Excel ??????
        ExcelWriter ew = assetTranferService.exportExcel(sample);
        // ??????
        DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = AssetTranferServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetTranferServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        // ?????? Excel ??????
        ExcelWriter ew = assetTranferService.exportExcelTemplate();
        // ??????
        DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
    }

    /**
     *  ??????????????????
     */
    @SentinelResource(value = AssetTranferServiceProxy.BPM_CALLBACK, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetTranferServiceProxy.BPM_CALLBACK)
    public BpmActionResult onProcessCallback(BpmEvent event) {
        return assetTranferService.onProcessCallback(event);
    }

    @SentinelResource(value = AssetTranferServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetTranferServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = assetTranferService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
