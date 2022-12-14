package com.dt.platform.eam.controller;

import java.util.List;
import com.dt.platform.domain.eam.meta.AssetScrapVOMeta;
import com.dt.platform.proxy.eam.AssetScrapServiceProxy;
import com.dt.platform.proxy.eam.AssetTranferServiceProxy;
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
import com.dt.platform.proxy.eam.PurchaseApplyServiceProxy;
import com.dt.platform.domain.eam.meta.PurchaseApplyVOMeta;
import com.dt.platform.domain.eam.PurchaseApply;
import com.dt.platform.domain.eam.PurchaseApplyVO;
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
import com.dt.platform.domain.eam.meta.PurchaseApplyMeta;
import com.dt.platform.domain.eam.Supplier;
import org.github.foxnic.web.domain.hrm.Employee;
import org.github.foxnic.web.domain.hrm.Organization;
import org.github.foxnic.web.domain.changes.ChangeInstance;
import com.dt.platform.domain.eam.PurchaseOrder;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IPurchaseApplyService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-04-16 22:09:37
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("EamPurchaseApplyController")
public class PurchaseApplyController extends SuperController implements BpmCallbackController {

    @Autowired
    private IPurchaseApplyService purchaseApplyService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = PurchaseApplyVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "567098245956763648"),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "121"),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.APPLY_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.ASSET_CHECK, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.APPLY_ORG_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.SUPPLIER_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "473623647488049153"),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.HARVEST_INFORMATION, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.EXPECTED_ARRIVAL_DATE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "2022-04-12"),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.APPLY_CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.APPLY_DATE, value = "????????????", required = false, dataTypeClass = String.class, example = "2022-04-12"),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.CHS_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.CHS_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.CHS_VERSION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.CHANGE_INSTANCE_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.SUMMARY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.LATEST_APPROVER_ID, value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.LATEST_APPROVER_NAME, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.NEXT_APPROVER_IDS, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.NEXT_APPROVER_NAMES, value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.APPROVAL_OPINION, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = PurchaseApplyServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(PurchaseApplyServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(PurchaseApplyVO purchaseApplyVO) {
        Result result = purchaseApplyService.insert(purchaseApplyVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = PurchaseApplyVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "567098245956763648")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = PurchaseApplyServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(PurchaseApplyServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = purchaseApplyService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = PurchaseApplyVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = PurchaseApplyServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(PurchaseApplyServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = purchaseApplyService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = PurchaseApplyVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "567098245956763648"),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "121"),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.APPLY_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.ASSET_CHECK, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.APPLY_ORG_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.SUPPLIER_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "473623647488049153"),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.HARVEST_INFORMATION, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.EXPECTED_ARRIVAL_DATE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "2022-04-12"),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.APPLY_CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.APPLY_DATE, value = "????????????", required = false, dataTypeClass = String.class, example = "2022-04-12"),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.CHS_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.CHS_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.CHS_VERSION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.CHANGE_INSTANCE_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.SUMMARY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.LATEST_APPROVER_ID, value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.LATEST_APPROVER_NAME, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.NEXT_APPROVER_IDS, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.NEXT_APPROVER_NAMES, value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.APPROVAL_OPINION, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { PurchaseApplyVOMeta.PAGE_INDEX, PurchaseApplyVOMeta.PAGE_SIZE, PurchaseApplyVOMeta.SEARCH_FIELD, PurchaseApplyVOMeta.FUZZY_FIELD, PurchaseApplyVOMeta.SEARCH_VALUE, PurchaseApplyVOMeta.DIRTY_FIELDS, PurchaseApplyVOMeta.SORT_FIELD, PurchaseApplyVOMeta.SORT_TYPE, PurchaseApplyVOMeta.IDS })
    @SentinelResource(value = PurchaseApplyServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(PurchaseApplyServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(PurchaseApplyVO purchaseApplyVO) {
        Result result = purchaseApplyService.update(purchaseApplyVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = PurchaseApplyVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "567098245956763648"),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "121"),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.APPLY_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.ASSET_CHECK, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.APPLY_ORG_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.SUPPLIER_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "473623647488049153"),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.HARVEST_INFORMATION, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.EXPECTED_ARRIVAL_DATE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "2022-04-12"),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.APPLY_CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.APPLY_DATE, value = "????????????", required = false, dataTypeClass = String.class, example = "2022-04-12"),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.CHS_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.CHS_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.CHS_VERSION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.CHANGE_INSTANCE_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.SUMMARY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.LATEST_APPROVER_ID, value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.LATEST_APPROVER_NAME, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.NEXT_APPROVER_IDS, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.NEXT_APPROVER_NAMES, value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.APPROVAL_OPINION, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { PurchaseApplyVOMeta.PAGE_INDEX, PurchaseApplyVOMeta.PAGE_SIZE, PurchaseApplyVOMeta.SEARCH_FIELD, PurchaseApplyVOMeta.FUZZY_FIELD, PurchaseApplyVOMeta.SEARCH_VALUE, PurchaseApplyVOMeta.DIRTY_FIELDS, PurchaseApplyVOMeta.SORT_FIELD, PurchaseApplyVOMeta.SORT_TYPE, PurchaseApplyVOMeta.IDS })
    @SentinelResource(value = PurchaseApplyServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(PurchaseApplyServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(PurchaseApplyVO purchaseApplyVO) {
        Result result = purchaseApplyService.save(purchaseApplyVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = PurchaseApplyVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = PurchaseApplyServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(PurchaseApplyServiceProxy.GET_BY_ID)
    public Result<PurchaseApply> getById(String id) {
        Result<PurchaseApply> result = new Result<>();
        PurchaseApply purchaseApply = purchaseApplyService.getById(id);
        // join ???????????????
        purchaseApplyService.dao().fill(purchaseApply).with("applyOrg").with(PurchaseApplyMeta.SUPPLIER).execute();
        result.success(true).data(purchaseApply);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = PurchaseApplyVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = PurchaseApplyServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(PurchaseApplyServiceProxy.GET_BY_IDS)
    public Result<List<PurchaseApply>> getByIds(List<String> ids) {
        Result<List<PurchaseApply>> result = new Result<>();
        List<PurchaseApply> list = purchaseApplyService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = PurchaseApplyVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "567098245956763648"),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "121"),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.APPLY_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.ASSET_CHECK, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.APPLY_ORG_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.SUPPLIER_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "473623647488049153"),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.HARVEST_INFORMATION, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.EXPECTED_ARRIVAL_DATE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "2022-04-12"),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.APPLY_CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.APPLY_DATE, value = "????????????", required = false, dataTypeClass = String.class, example = "2022-04-12"),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.CHS_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.CHS_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.CHS_VERSION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.CHANGE_INSTANCE_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.SUMMARY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.LATEST_APPROVER_ID, value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.LATEST_APPROVER_NAME, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.NEXT_APPROVER_IDS, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.NEXT_APPROVER_NAMES, value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.APPROVAL_OPINION, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { PurchaseApplyVOMeta.PAGE_INDEX, PurchaseApplyVOMeta.PAGE_SIZE })
    @SentinelResource(value = PurchaseApplyServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(PurchaseApplyServiceProxy.QUERY_LIST)
    public Result<List<PurchaseApply>> queryList(PurchaseApplyVO sample) {
        Result<List<PurchaseApply>> result = new Result<>();
        List<PurchaseApply> list = purchaseApplyService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = PurchaseApplyVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "567098245956763648"),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "121"),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.APPLY_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.ASSET_CHECK, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.APPLY_ORG_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.SUPPLIER_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "473623647488049153"),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.HARVEST_INFORMATION, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.EXPECTED_ARRIVAL_DATE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "2022-04-12"),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.APPLY_CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.APPLY_DATE, value = "????????????", required = false, dataTypeClass = String.class, example = "2022-04-12"),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.CHS_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.CHS_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.CHS_VERSION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.CHANGE_INSTANCE_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.SUMMARY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.LATEST_APPROVER_ID, value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.LATEST_APPROVER_NAME, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.NEXT_APPROVER_IDS, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.NEXT_APPROVER_NAMES, value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = PurchaseApplyVOMeta.APPROVAL_OPINION, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = PurchaseApplyServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(PurchaseApplyServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<PurchaseApply>> queryPagedList(PurchaseApplyVO sample) {
        Result<PagedList<PurchaseApply>> result = new Result<>();
        PagedList<PurchaseApply> list = purchaseApplyService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        purchaseApplyService.dao().fill(list).with("applyOrg").with(PurchaseApplyMeta.SUPPLIER).execute();
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = PurchaseApplyVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 13)
    @SentinelResource(value = PurchaseApplyServiceProxy.CONFIRM_OPERATION, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(PurchaseApplyServiceProxy.CONFIRM_OPERATION)
    public Result confirmOperation(String id) {
        return purchaseApplyService.confirmOperation(id);
    }

    /**
     * ??????
     */
    @ApiOperation(value = "??????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = PurchaseApplyVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 16)
    @SentinelResource(value = PurchaseApplyServiceProxy.CHECK, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(PurchaseApplyServiceProxy.CHECK)
    public Result check(String id, String checkId) {
        return purchaseApplyService.check(id, checkId);
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = PurchaseApplyServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(PurchaseApplyServiceProxy.EXPORT_EXCEL)
    public void exportExcel(PurchaseApplyVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = purchaseApplyService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = PurchaseApplyServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(PurchaseApplyServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = purchaseApplyService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     *  ??????????????????
     */
    @SentinelResource(value = PurchaseApplyServiceProxy.BPM_CALLBACK, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(PurchaseApplyServiceProxy.BPM_CALLBACK)
    public BpmActionResult onProcessCallback(BpmEvent event) {
        return purchaseApplyService.onProcessCallback(event);
    }

    @SentinelResource(value = PurchaseApplyServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(PurchaseApplyServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = purchaseApplyService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
