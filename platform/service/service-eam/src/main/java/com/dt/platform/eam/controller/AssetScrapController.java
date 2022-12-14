package com.dt.platform.eam.controller;

import java.util.List;
import com.dt.platform.constants.enums.eam.AssetHandleStatusEnum;
import com.dt.platform.constants.enums.eam.AssetOperateEnum;
import com.dt.platform.domain.eam.AssetRepair;
import com.dt.platform.domain.eam.meta.*;
import com.dt.platform.proxy.eam.AssetEmployeeRepairServiceProxy;
import com.dt.platform.proxy.eam.AssetRepairServiceProxy;
import com.github.foxnic.commons.collection.CollectorUtil;
import com.github.foxnic.commons.lang.StringUtil;
import com.github.foxnic.commons.log.Logger;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.proxy.eam.AssetScrapServiceProxy;
import com.dt.platform.domain.eam.AssetScrap;
import com.dt.platform.domain.eam.AssetScrapVO;
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
import com.dt.platform.domain.eam.Asset;
import org.github.foxnic.web.domain.hrm.Employee;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IAssetScrapService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2021-09-12 13:04:21
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("EamAssetScrapController")
public class AssetScrapController extends SuperController implements BpmCallbackController {

    @Autowired
    private IAssetScrapService assetScrapService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetScrapVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.CLEAN_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.NAME, value = "????????????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.SCRAP_DATE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.CHS_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.CHS_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.CHS_VERSION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.CHANGE_INSTANCE_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.SUMMARY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.LATEST_APPROVER_ID, value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.LATEST_APPROVER_NAME, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.NEXT_APPROVER_IDS, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.NEXT_APPROVER_NAMES, value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.APPROVAL_OPINION, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = AssetScrapServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetScrapServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(AssetScrapVO assetScrapVO) {
        return assetScrapService.insert(assetScrapVO);
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetScrapVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = AssetScrapServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetScrapServiceProxy.DELETE)
    public Result deleteById(String id) {
        AssetScrap ssetScrap = assetScrapService.getById(id);
        if (AssetHandleStatusEnum.CANCEL.code().equals(ssetScrap.getStatus()) || AssetHandleStatusEnum.INCOMPLETE.code().equals(ssetScrap.getStatus())) {
            Result result = assetScrapService.deleteByIdLogical(id);
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
		@ApiImplicitParam(name = AssetScrapVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetScrapServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetScrapServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = assetScrapService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetScrapVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.CLEAN_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.NAME, value = "????????????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.SCRAP_DATE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.CHS_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.CHS_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.CHS_VERSION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.CHANGE_INSTANCE_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.SUMMARY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.LATEST_APPROVER_ID, value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.LATEST_APPROVER_NAME, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.NEXT_APPROVER_IDS, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.NEXT_APPROVER_NAMES, value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.APPROVAL_OPINION, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { AssetScrapVOMeta.PAGE_INDEX, AssetScrapVOMeta.PAGE_SIZE, AssetScrapVOMeta.SEARCH_FIELD, AssetScrapVOMeta.FUZZY_FIELD, AssetScrapVOMeta.SEARCH_VALUE, AssetScrapVOMeta.SORT_FIELD, AssetScrapVOMeta.SORT_TYPE, AssetScrapVOMeta.IDS })
    @SentinelResource(value = AssetScrapServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetScrapServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(AssetScrapVO assetScrapVO) {
        AssetScrap assetScrap = assetScrapService.getById(assetScrapVO.getId());
        if (AssetHandleStatusEnum.COMPLETE.code().equals(assetScrap.getStatus()) || AssetHandleStatusEnum.APPROVAL.code().equals(assetScrap.getStatus())) {
            return ErrorDesc.failure().message("???????????????????????????");
        }
        Result result = assetScrapService.update(assetScrapVO, SaveMode.NOT_NULL_FIELDS);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetScrapVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.CLEAN_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.NAME, value = "????????????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.SCRAP_DATE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.CHS_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.CHS_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.CHS_VERSION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.CHANGE_INSTANCE_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.SUMMARY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.LATEST_APPROVER_ID, value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.LATEST_APPROVER_NAME, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.NEXT_APPROVER_IDS, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.NEXT_APPROVER_NAMES, value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.APPROVAL_OPINION, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetScrapVOMeta.PAGE_INDEX, AssetScrapVOMeta.PAGE_SIZE, AssetScrapVOMeta.SEARCH_FIELD, AssetScrapVOMeta.FUZZY_FIELD, AssetScrapVOMeta.SEARCH_VALUE, AssetScrapVOMeta.SORT_FIELD, AssetScrapVOMeta.SORT_TYPE, AssetScrapVOMeta.IDS })
    @SentinelResource(value = AssetScrapServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetScrapServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(AssetScrapVO assetScrapVO) {
        Result result = assetScrapService.save(assetScrapVO, SaveMode.NOT_NULL_FIELDS);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetScrapVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = AssetScrapVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.CLEAN_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.SCRAP_DATE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.CHS_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.CHS_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.CHS_VERSION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.CHANGE_INSTANCE_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.SUMMARY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.LATEST_APPROVER_ID, value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.LATEST_APPROVER_NAME, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.NEXT_APPROVER_IDS, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.NEXT_APPROVER_NAMES, value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.APPROVAL_OPINION, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = AssetScrapServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetScrapServiceProxy.GET_BY_ID)
    public Result<AssetScrap> getById(String id) {
        Result<AssetScrap> result = new Result<>();
        AssetScrap assetScrap = assetScrapService.getById(id);
        assetScrapService.join(assetScrap, AssetScrapMeta.ORIGINATOR);
        assetScrapService.join(assetScrap, AssetScrapMeta.METHOD_DICT);

        assetScrapService.dao().join(assetScrap.getOriginator(), Person.class);
        result.success(true).data(assetScrap);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetScrapVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.CLEAN_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.SCRAP_DATE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.CHS_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.CHS_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.CHS_VERSION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.CHANGE_INSTANCE_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.SUMMARY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.LATEST_APPROVER_ID, value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.LATEST_APPROVER_NAME, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.NEXT_APPROVER_IDS, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.NEXT_APPROVER_NAMES, value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.APPROVAL_OPINION, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetScrapServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetScrapServiceProxy.GET_BY_IDS)
    public Result<List<AssetScrap>> getByIds(List<String> ids) {
        Result<List<AssetScrap>> result = new Result<>();
        List<AssetScrap> list = assetScrapService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetScrapVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.CLEAN_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.NAME, value = "????????????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.SCRAP_DATE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetScrapVOMeta.PAGE_INDEX, AssetScrapVOMeta.PAGE_SIZE })
    @SentinelResource(value = AssetScrapServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetScrapServiceProxy.QUERY_LIST)
    public Result<List<AssetScrap>> queryList(AssetScrapVO sample) {
        Result<List<AssetScrap>> result = new Result<>();
        List<AssetScrap> list = assetScrapService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetScrapVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.CLEAN_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.SCRAP_DATE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.CHS_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.CHS_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.CHS_VERSION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.CHANGE_INSTANCE_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.SUMMARY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.LATEST_APPROVER_ID, value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.LATEST_APPROVER_NAME, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.NEXT_APPROVER_IDS, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.NEXT_APPROVER_NAMES, value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetScrapVOMeta.APPROVAL_OPINION, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = AssetScrapServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetScrapServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<AssetScrap>> queryPagedList(AssetScrapVO sample) {
        Result<PagedList<AssetScrap>> result = new Result<>();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String pageType = request.getParameter("pageType");
        // ????????????
        String dp = "";
        if (!StringUtil.isBlank(pageType) && "approval".equals(pageType)) {
            dp = AssetOperateEnum.EAM_ASSET_SCRAP.code() + "_approving";
        } else {
            dp = AssetOperateEnum.EAM_ASSET_SCRAP.code();
        }
        Logger.info("pageType" + pageType + ",dp:" + dp);
        PagedList<AssetScrap> list = assetScrapService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex(), dp);
        assetScrapService.join(list, AssetScrapMeta.ORIGINATOR);
        assetScrapService.join(list, AssetScrapMeta.METHOD_DICT);
        List<Employee> employees = CollectorUtil.collectList(list, AssetScrap::getOriginator);
        assetScrapService.dao().join(employees, Person.class);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetScrapVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 13)
    @SentinelResource(value = AssetScrapServiceProxy.CONFIRM_OPERATION, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetScrapServiceProxy.CONFIRM_OPERATION)
    public Result confirmOperation(String id) {
        return assetScrapService.confirmOperation(id);
    }

    /**
     * ??????
     */
    @ApiOperation(value = "??????")
    @ApiOperationSupport(order = 16)
    @SentinelResource(value = AssetScrapServiceProxy.CLEAN_OUT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetScrapServiceProxy.CLEAN_OUT)
    public Result cleanOut(String id) {
        return assetScrapService.cleanOut(id);
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = AssetScrapServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetScrapServiceProxy.EXPORT_EXCEL)
    public void exportExcel(AssetScrapVO sample, HttpServletResponse response) throws Exception {
        // ?????? Excel ??????
        ExcelWriter ew = assetScrapService.exportExcel(sample);
        // ??????
        DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = AssetScrapServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetScrapServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        // ?????? Excel ??????
        ExcelWriter ew = assetScrapService.exportExcelTemplate();
        // ??????
        DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
    }

    @SentinelResource(value = AssetScrapServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetScrapServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = assetScrapService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }

    /**
     *  ??????????????????
     */
    @SentinelResource(value = AssetScrapServiceProxy.BPM_CALLBACK, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetScrapServiceProxy.BPM_CALLBACK)
    public BpmActionResult onProcessCallback(BpmEvent event) {
        return assetScrapService.onProcessCallback(event);
    }
}
