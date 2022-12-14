package com.dt.platform.eam.controller;

import java.util.List;
import java.util.ArrayList;
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
import com.dt.platform.proxy.eam.AssetRackInfoServiceProxy;
import com.dt.platform.domain.eam.meta.AssetRackInfoVOMeta;
import com.dt.platform.domain.eam.AssetRackInfo;
import com.dt.platform.domain.eam.AssetRackInfoVO;
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
import com.dt.platform.domain.eam.meta.AssetRackInfoMeta;
import java.math.BigDecimal;
import org.github.foxnic.web.domain.system.DictItem;
import com.dt.platform.domain.datacenter.meta.RackMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IAssetRackInfoService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ?????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-08-27 23:10:12
 */
@Api(tags = "??????")
@ApiSort(0)
@RestController("EamAssetRackInfoController")
public class AssetRackInfoController extends SuperController {

    @Autowired
    private IAssetRackInfoService assetRackInfoService;

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetRackInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.RACK_CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.RACK_TYPE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.RACK_NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.ENVIRONMENT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.RACK_USED_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.RACK_CAPTICAL, value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.U_POSTION_NUMBER, value = "U?????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.PDU_NUMBER, value = "PDU??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.JUMPER_NUMBER, value = "?????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.CONTRACT_POWER, value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.EQUIPMENT_NUMBER, value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.EXPIRE_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.RACK_LABEL1, value = "??????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.RACK_LABEL2, value = "??????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.RACK_NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = AssetRackInfoServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetRackInfoServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(AssetRackInfoVO assetRackInfoVO) {
        Result result = assetRackInfoService.insert(assetRackInfoVO, false);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetRackInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = AssetRackInfoServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetRackInfoServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  assetRackInfoService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = assetRackInfoService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ?????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetRackInfoVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetRackInfoServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetRackInfoServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = assetRackInfoService.hasRefers(ids);
        // ?????????????????????ID???
        List<String> canDeleteIds = new ArrayList<>();
        for (Map.Entry<String, ReferCause> e : causeMap.entrySet()) {
            if (!e.getValue().hasRefer()) {
                canDeleteIds.add(e.getKey());
            }
        }
        // ????????????
        if (canDeleteIds.isEmpty()) {
            // ?????????????????????????????????
            return ErrorDesc.failure().message("????????????????????????????????????").data(0)
				.addErrors(CollectorUtil.collectArray(CollectorUtil.filter(causeMap.values(),(e)->{return e.hasRefer();}),ReferCause::message,String.class))
				.messageLevel4Confirm();
        } else if (canDeleteIds.size() == ids.size()) {
            // ????????????????????????
            Result result = assetRackInfoService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = assetRackInfoService.deleteByIdsLogical(canDeleteIds);
            if (result.failure()) {
                return result;
            } else {
                return ErrorDesc.success().message("????????? " + canDeleteIds.size() + " ??????????????? " + (ids.size() - canDeleteIds.size()) + " ?????????????????????").data(canDeleteIds.size())
					.addErrors(CollectorUtil.collectArray(CollectorUtil.filter(causeMap.values(),(e)->{return e.hasRefer();}),ReferCause::message,String.class))
					.messageLevel4Confirm();
            }
        } else {
            // ?????????????????????????????????
            return ErrorDesc.success().message("?????????????????????");
        }
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetRackInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.RACK_CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.RACK_TYPE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.RACK_NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.ENVIRONMENT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.RACK_USED_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.RACK_CAPTICAL, value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.U_POSTION_NUMBER, value = "U?????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.PDU_NUMBER, value = "PDU??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.JUMPER_NUMBER, value = "?????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.CONTRACT_POWER, value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.EQUIPMENT_NUMBER, value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.EXPIRE_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.RACK_LABEL1, value = "??????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.RACK_LABEL2, value = "??????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.RACK_NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { AssetRackInfoVOMeta.PAGE_INDEX, AssetRackInfoVOMeta.PAGE_SIZE, AssetRackInfoVOMeta.SEARCH_FIELD, AssetRackInfoVOMeta.FUZZY_FIELD, AssetRackInfoVOMeta.SEARCH_VALUE, AssetRackInfoVOMeta.DIRTY_FIELDS, AssetRackInfoVOMeta.SORT_FIELD, AssetRackInfoVOMeta.SORT_TYPE, AssetRackInfoVOMeta.IDS })
    @SentinelResource(value = AssetRackInfoServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetRackInfoServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(AssetRackInfoVO assetRackInfoVO) {
        Result result = assetRackInfoService.update(assetRackInfoVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetRackInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.RACK_CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.RACK_TYPE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.RACK_NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.ENVIRONMENT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.RACK_USED_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.RACK_CAPTICAL, value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.U_POSTION_NUMBER, value = "U?????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.PDU_NUMBER, value = "PDU??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.JUMPER_NUMBER, value = "?????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.CONTRACT_POWER, value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.EQUIPMENT_NUMBER, value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.EXPIRE_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.RACK_LABEL1, value = "??????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.RACK_LABEL2, value = "??????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.RACK_NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetRackInfoVOMeta.PAGE_INDEX, AssetRackInfoVOMeta.PAGE_SIZE, AssetRackInfoVOMeta.SEARCH_FIELD, AssetRackInfoVOMeta.FUZZY_FIELD, AssetRackInfoVOMeta.SEARCH_VALUE, AssetRackInfoVOMeta.DIRTY_FIELDS, AssetRackInfoVOMeta.SORT_FIELD, AssetRackInfoVOMeta.SORT_TYPE, AssetRackInfoVOMeta.IDS })
    @SentinelResource(value = AssetRackInfoServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetRackInfoServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(AssetRackInfoVO assetRackInfoVO) {
        Result result = assetRackInfoService.save(assetRackInfoVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetRackInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = AssetRackInfoServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetRackInfoServiceProxy.GET_BY_ID)
    public Result<AssetRackInfo> getById(String id) {
        Result<AssetRackInfo> result = new Result<>();
        AssetRackInfo assetRackInfo = assetRackInfoService.getById(id);
        // join ???????????????
        assetRackInfoService.dao().fill(assetRackInfo).with(RackMeta.STATUS_DICT).with(RackMeta.TYPE_DICT).with(RackMeta.USED_TYPE_DICT).with(RackMeta.ENVIRONMENT_DICT).execute();
        result.success(true).data(assetRackInfo);
        return result;
    }

    /**
     * ?????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetRackInfoVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetRackInfoServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetRackInfoServiceProxy.GET_BY_IDS)
    public Result<List<AssetRackInfo>> getByIds(List<String> ids) {
        Result<List<AssetRackInfo>> result = new Result<>();
        List<AssetRackInfo> list = assetRackInfoService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetRackInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.RACK_CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.RACK_TYPE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.RACK_NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.ENVIRONMENT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.RACK_USED_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.RACK_CAPTICAL, value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.U_POSTION_NUMBER, value = "U?????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.PDU_NUMBER, value = "PDU??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.JUMPER_NUMBER, value = "?????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.CONTRACT_POWER, value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.EQUIPMENT_NUMBER, value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.EXPIRE_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.RACK_LABEL1, value = "??????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.RACK_LABEL2, value = "??????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.RACK_NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetRackInfoVOMeta.PAGE_INDEX, AssetRackInfoVOMeta.PAGE_SIZE })
    @SentinelResource(value = AssetRackInfoServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetRackInfoServiceProxy.QUERY_LIST)
    public Result<List<AssetRackInfo>> queryList(AssetRackInfoVO sample) {
        Result<List<AssetRackInfo>> result = new Result<>();
        List<AssetRackInfo> list = assetRackInfoService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetRackInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.RACK_CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.RACK_TYPE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.RACK_NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.ENVIRONMENT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.RACK_USED_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.RACK_CAPTICAL, value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.U_POSTION_NUMBER, value = "U?????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.PDU_NUMBER, value = "PDU??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.JUMPER_NUMBER, value = "?????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.CONTRACT_POWER, value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.EQUIPMENT_NUMBER, value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.EXPIRE_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.RACK_LABEL1, value = "??????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.RACK_LABEL2, value = "??????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackInfoVOMeta.RACK_NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = AssetRackInfoServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetRackInfoServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<AssetRackInfo>> queryPagedList(AssetRackInfoVO sample) {
        Result<PagedList<AssetRackInfo>> result = new Result<>();
        PagedList<AssetRackInfo> list = assetRackInfoService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        assetRackInfoService.dao().fill(list).with(RackMeta.STATUS_DICT).with(RackMeta.TYPE_DICT).with(RackMeta.USED_TYPE_DICT).with(RackMeta.ENVIRONMENT_DICT).execute();
        result.success(true).data(list);
        return result;
    }
}
