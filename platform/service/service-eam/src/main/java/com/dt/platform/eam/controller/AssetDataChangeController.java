package com.dt.platform.eam.controller;

import java.util.ArrayList;
import java.util.List;
import com.dt.platform.constants.enums.eam.AssetAttributeDimensionEnum;
import com.dt.platform.constants.enums.eam.AssetHandleStatusEnum;
import com.dt.platform.domain.eam.*;
import com.dt.platform.domain.eam.meta.AssetBorrowVOMeta;
import com.dt.platform.domain.eam.meta.AssetMeta;
import com.dt.platform.eam.service.IAssetService;
import com.dt.platform.proxy.eam.AssetBorrowServiceProxy;
import com.github.foxnic.commons.collection.CollectorUtil;
import com.github.foxnic.commons.lang.StringUtil;
import org.github.foxnic.web.domain.changes.ProcessApproveVO;
import org.github.foxnic.web.domain.changes.meta.ExampleOrderVOMeta;
import org.github.foxnic.web.domain.hrm.Person;
import org.github.foxnic.web.proxy.changes.ExampleOrderServiceProxy;
import com.github.foxnic.commons.collection.CollectorUtil;
import com.github.foxnic.dao.entity.ReferCause;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.github.foxnic.web.framework.web.SuperController;
import org.github.foxnic.web.framework.sentinel.SentinelExceptionUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.proxy.eam.AssetDataChangeServiceProxy;
import com.dt.platform.domain.eam.meta.AssetDataChangeVOMeta;
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
import com.dt.platform.domain.eam.meta.AssetDataChangeMeta;
import org.github.foxnic.web.domain.hrm.Employee;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IAssetDataChangeService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2021-10-08 16:01:26
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("EamAssetDataChangeController")
public class AssetDataChangeController extends SuperController {

    @Autowired
    private IAssetDataChangeService assetDataChangeService;

    @Autowired
    private IAssetService assetService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataChangeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.CHANGE_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.CHANGE_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.CHANGE_NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = AssetDataChangeServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataChangeServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(AssetDataChangeRecordVO assetDataChangeVO) {
        return assetDataChangeService.insertRecord(assetDataChangeVO);
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataChangeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = AssetDataChangeServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataChangeServiceProxy.DELETE)
    public Result deleteById(String id) {
        AssetDataChange assetDataChange = assetDataChangeService.getById(id);
        if (AssetHandleStatusEnum.CANCEL.code().equals(assetDataChange.getStatus()) || AssetHandleStatusEnum.INCOMPLETE.code().equals(assetDataChange.getStatus())) {
            Result result = assetDataChangeService.deleteByIdLogical(id);
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
		@ApiImplicitParam(name = AssetDataChangeVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetDataChangeServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataChangeServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = assetDataChangeService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataChangeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.CHANGE_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.ASSET_CHANGE_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.CHANGE_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.CHANGE_NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { AssetDataChangeVOMeta.PAGE_INDEX, AssetDataChangeVOMeta.PAGE_SIZE, AssetDataChangeVOMeta.SEARCH_FIELD, AssetDataChangeVOMeta.FUZZY_FIELD, AssetDataChangeVOMeta.SEARCH_VALUE, AssetDataChangeVOMeta.SORT_FIELD, AssetDataChangeVOMeta.SORT_TYPE, AssetDataChangeVOMeta.IDS })
    @SentinelResource(value = AssetDataChangeServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataChangeServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(AssetDataChangeRecordVO assetDataChangeVO) {
        return assetDataChangeService.updateRecord(assetDataChangeVO);
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataChangeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.BUSINESS_NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.CHANGE_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.ASSET_CHANGE_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.CHANGE_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.CHANGE_NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.CHS_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.CHS_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.CHS_VERSION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.CHANGE_INSTANCE_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.SUMMARY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.LATEST_APPROVER_ID, value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.LATEST_APPROVER_NAME, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.NEXT_APPROVER_IDS, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.NEXT_APPROVER_NAMES, value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.APPROVAL_OPINION, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetDataChangeVOMeta.PAGE_INDEX, AssetDataChangeVOMeta.PAGE_SIZE, AssetDataChangeVOMeta.SEARCH_FIELD, AssetDataChangeVOMeta.FUZZY_FIELD, AssetDataChangeVOMeta.SEARCH_VALUE, AssetDataChangeVOMeta.SORT_FIELD, AssetDataChangeVOMeta.SORT_TYPE, AssetDataChangeVOMeta.IDS })
    @SentinelResource(value = AssetDataChangeServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataChangeServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(AssetDataChangeVO assetDataChangeVO) {
        Result result = assetDataChangeService.save(assetDataChangeVO, SaveMode.NOT_NULL_FIELDS);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataChangeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = AssetDataChangeServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataChangeServiceProxy.GET_BY_ID)
    public Result<AssetDataChange> getById(String id) {
        Result<AssetDataChange> result = new Result<>();
        AssetDataChange assetDataChange = assetDataChangeService.getById(id);
        // ????????? ????????? ??????
        assetDataChangeService.join(assetDataChange, AssetDataChangeMeta.ORIGINATOR);
        assetDataChangeService.join(assetDataChange, AssetDataChangeMeta.CHANGE_DATA);
        // ????????? ???????????? ??????
        assetService.join(assetDataChange.getChangeData(), AssetMeta.CATEGORY);
        // ????????? ???????????? ??????
        assetService.join(assetDataChange.getChangeData(), AssetMeta.GOODS);
        // ????????? ?????? ??????
        assetService.join(assetDataChange.getChangeData(), AssetMeta.MANUFACTURER);
        // ????????? ?????? ??????
        assetService.join(assetDataChange.getChangeData(), AssetMeta.POSITION);
        // ????????? ?????? ??????
        assetService.join(assetDataChange.getChangeData(), AssetMeta.WAREHOUSE);
        // ????????? ?????? ??????
        assetService.join(assetDataChange.getChangeData(), AssetMeta.SOURCE);
        // ????????? ????????? ??????
        assetService.join(assetDataChange.getChangeData(), AssetMeta.MAINTNAINER);
        // ????????? ???????????? ??????
        assetService.join(assetDataChange.getChangeData(), AssetMeta.CATEGORY_FINANCE);
        assetService.join(assetDataChange.getChangeData(), AssetMeta.RACK);
        // ????????? ????????? ??????
        assetService.join(assetDataChange.getChangeData(), AssetMeta.SUPPLIER);
        assetService.join(assetDataChange.getChangeData(), AssetMeta.SAFETY_LEVEL);
        assetService.join(assetDataChange.getChangeData(), AssetMeta.EQUIPMENT_ENVIRONMENT);
        assetService.join(assetDataChange.getChangeData(), AssetMeta.OWNER_COMPANY);
        assetService.join(assetDataChange.getChangeData(), AssetMeta.USE_ORGANIZATION);
        assetService.join(assetDataChange.getChangeData(), AssetMeta.MANAGER);
        assetService.join(assetDataChange.getChangeData(), AssetMeta.USE_USER);
        assetService.join(assetDataChange.getChangeData(), AssetMeta.ORIGINATOR);
        result.success(true).data(assetDataChange);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataChangeVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetDataChangeServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataChangeServiceProxy.GET_BY_IDS)
    public Result<List<AssetDataChange>> getByIds(List<String> ids) {
        Result<List<AssetDataChange>> result = new Result<>();
        List<AssetDataChange> list = assetDataChangeService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataChangeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.BUSINESS_NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.CHANGE_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.ASSET_CHANGE_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.CHANGE_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.CHANGE_NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "ces "),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "E001"),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.CHS_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.CHS_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.CHS_VERSION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.CHANGE_INSTANCE_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.SUMMARY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.LATEST_APPROVER_ID, value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.LATEST_APPROVER_NAME, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.NEXT_APPROVER_IDS, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.NEXT_APPROVER_NAMES, value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.APPROVAL_OPINION, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetDataChangeVOMeta.PAGE_INDEX, AssetDataChangeVOMeta.PAGE_SIZE })
    @SentinelResource(value = AssetDataChangeServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataChangeServiceProxy.QUERY_LIST)
    public Result<List<AssetDataChange>> queryList(AssetDataChangeVO sample) {
        Result<List<AssetDataChange>> result = new Result<>();
        List<AssetDataChange> list = assetDataChangeService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataChangeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.BUSINESS_NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.CHANGE_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.ASSET_CHANGE_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.CHANGE_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.CHANGE_NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.CHS_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.CHS_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.CHS_VERSION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.CHANGE_INSTANCE_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.SUMMARY, value = "????????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.LATEST_APPROVER_ID, value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.LATEST_APPROVER_NAME, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.NEXT_APPROVER_IDS, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.NEXT_APPROVER_NAMES, value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataChangeVOMeta.APPROVAL_OPINION, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = AssetDataChangeServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataChangeServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<AssetDataChange>> queryPagedList(AssetDataChangeVO sample) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String pageType = request.getParameter("pageType");
        Result<PagedList<AssetDataChange>> result = new Result<>();
        // ????????????
        String dp = "";
        if (!StringUtil.isBlank(pageType) && "approval".equals(pageType)) {
            dp = sample.getChangeType() + "_approving";
        } else {
            dp = sample.getChangeType();
        }
        PagedList<AssetDataChange> list = assetDataChangeService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex(), dp);
        assetDataChangeService.join(list, AssetDataChangeMeta.ORIGINATOR);
        List<Employee> employees = CollectorUtil.collectList(list, AssetDataChange::getOriginator);
        assetDataChangeService.dao().join(employees, Person.class);
        assetDataChangeService.join(list, AssetDataChangeMeta.CHANGE_DATA);
        List<Asset> assetData = CollectorUtil.collectList(list, AssetDataChange::getChangeData);
        assetService.joinData(assetData);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiOperationSupport(order = 9)
    @SentinelResource(value = AssetDataChangeServiceProxy.QUERY_DATA_CHANGE_DIMENSION_BY_CHANGE_TYPE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataChangeServiceProxy.QUERY_DATA_CHANGE_DIMENSION_BY_CHANGE_TYPE)
    public Result queryDataChangeDimensionByChangeType(String changeType) {
        Result result = new Result();
        result.success(true).data(assetDataChangeService.queryDataChangeDimensionByChangeType(changeType));
        return result;
    }

    /**
     * ??????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataChangeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 12)
    @SentinelResource(value = AssetDataChangeServiceProxy.FOR_APPROVAL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetDataChangeServiceProxy.FOR_APPROVAL)
    public Result forApproval(String id) {
        return assetDataChangeService.forApproval(id);
    }

    /**
     * ??????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataChangeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 13)
    @SentinelResource(value = AssetDataChangeServiceProxy.CONFIRM_OPERATION, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetDataChangeServiceProxy.CONFIRM_OPERATION)
    public Result confirmOperation(String id) {
        return assetDataChangeService.confirmOperation(id);
    }

    /**
     * ??????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataChangeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 14)
    @SentinelResource(value = AssetDataChangeServiceProxy.REVOKE_OPERATION, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetDataChangeServiceProxy.REVOKE_OPERATION)
    public Result revokeOperation(String id) {
        return assetDataChangeService.revokeOperation(id);
    }

    /**
     * ??????
     */
    @ApiOperation(value = "??????")
    @ApiOperationSupport(order = 15)
    @SentinelResource(value = AssetDataChangeServiceProxy.APPROVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetDataChangeServiceProxy.APPROVE)
    public Result approve(ProcessApproveVO approveVO) {
        return assetDataChangeService.approve(approveVO);
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = AssetDataChangeServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetDataChangeServiceProxy.EXPORT_EXCEL)
    public void exportExcel(AssetDataChangeVO sample, HttpServletResponse response) throws Exception {
        // ?????? Excel ??????
        ExcelWriter ew = assetDataChangeService.exportExcel(sample);
        // ??????
        DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = AssetDataChangeServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetDataChangeServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        // ?????? Excel ??????
        ExcelWriter ew = assetDataChangeService.exportExcelTemplate();
        // ??????
        DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
    }

    @SentinelResource(value = AssetDataChangeServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetDataChangeServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = assetDataChangeService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
