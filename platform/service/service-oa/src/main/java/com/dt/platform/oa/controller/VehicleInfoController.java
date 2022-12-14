package com.dt.platform.oa.controller;

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
import com.dt.platform.proxy.oa.VehicleInfoServiceProxy;
import com.dt.platform.domain.oa.meta.VehicleInfoVOMeta;
import com.dt.platform.domain.oa.VehicleInfo;
import com.dt.platform.domain.oa.VehicleInfoVO;
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
import com.dt.platform.domain.oa.meta.VehicleInfoMeta;
import java.math.BigDecimal;
import org.github.foxnic.web.domain.hrm.Organization;
import org.github.foxnic.web.domain.system.DictItem;
import org.github.foxnic.web.domain.hrm.Employee;
import com.dt.platform.domain.vehicle.meta.InfoMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.oa.service.IVehicleInfoService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-08-28 13:01:07
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("OaVehicleInfoController")
public class VehicleInfoController extends SuperController {

    @Autowired
    private IVehicleInfoService vehicleInfoService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = VehicleInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.VEHICLE_STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.VEHICLE_CODE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.MODEL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.REGISTRANT, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.OWNER_ORG_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.USE_ORG_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.USE_USER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.COLOR, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.ENGINE_NUMBER, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.FRAME_NUMBER, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.DRIVING_LICENSE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.KILOMETERS, value = "?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.CAR_BOAT_TAX, value = "?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.RESCUE_MONEY, value = "??????(???)", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.COMMERCIAL_INSURANCE_MONEY, value = "?????????(???)", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.INSURANCE_COMPANY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.LICENSING_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.INSURANCE_EXPIRE_DATE, value = "???????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.RESCUE_DUE_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.SCRAP_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.POSITION_DETAIL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.PICTURES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.TECHNICAL_PARAMETER, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.VEHICLE_COUNT, value = "??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = VehicleInfoServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(VehicleInfoServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(VehicleInfoVO vehicleInfoVO) {
        Result result = vehicleInfoService.insert(vehicleInfoVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = VehicleInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = VehicleInfoServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(VehicleInfoServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  vehicleInfoService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = vehicleInfoService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = VehicleInfoVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = VehicleInfoServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(VehicleInfoServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = vehicleInfoService.hasRefers(ids);
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
            Result result = vehicleInfoService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = vehicleInfoService.deleteByIdsLogical(canDeleteIds);
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
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = VehicleInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.VEHICLE_STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.VEHICLE_CODE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.MODEL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.REGISTRANT, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.OWNER_ORG_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.USE_ORG_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.USE_USER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.COLOR, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.ENGINE_NUMBER, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.FRAME_NUMBER, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.DRIVING_LICENSE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.KILOMETERS, value = "?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.CAR_BOAT_TAX, value = "?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.RESCUE_MONEY, value = "??????(???)", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.COMMERCIAL_INSURANCE_MONEY, value = "?????????(???)", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.INSURANCE_COMPANY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.LICENSING_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.INSURANCE_EXPIRE_DATE, value = "???????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.RESCUE_DUE_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.SCRAP_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.POSITION_DETAIL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.PICTURES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.TECHNICAL_PARAMETER, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.VEHICLE_COUNT, value = "??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { VehicleInfoVOMeta.PAGE_INDEX, VehicleInfoVOMeta.PAGE_SIZE, VehicleInfoVOMeta.SEARCH_FIELD, VehicleInfoVOMeta.FUZZY_FIELD, VehicleInfoVOMeta.SEARCH_VALUE, VehicleInfoVOMeta.DIRTY_FIELDS, VehicleInfoVOMeta.SORT_FIELD, VehicleInfoVOMeta.SORT_TYPE, VehicleInfoVOMeta.IDS })
    @SentinelResource(value = VehicleInfoServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(VehicleInfoServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(VehicleInfoVO vehicleInfoVO) {
        Result result = vehicleInfoService.update(vehicleInfoVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = VehicleInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.VEHICLE_STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.VEHICLE_CODE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.MODEL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.REGISTRANT, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.OWNER_ORG_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.USE_ORG_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.USE_USER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.COLOR, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.ENGINE_NUMBER, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.FRAME_NUMBER, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.DRIVING_LICENSE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.KILOMETERS, value = "?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.CAR_BOAT_TAX, value = "?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.RESCUE_MONEY, value = "??????(???)", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.COMMERCIAL_INSURANCE_MONEY, value = "?????????(???)", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.INSURANCE_COMPANY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.LICENSING_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.INSURANCE_EXPIRE_DATE, value = "???????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.RESCUE_DUE_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.SCRAP_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.POSITION_DETAIL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.PICTURES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.TECHNICAL_PARAMETER, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.VEHICLE_COUNT, value = "??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { VehicleInfoVOMeta.PAGE_INDEX, VehicleInfoVOMeta.PAGE_SIZE, VehicleInfoVOMeta.SEARCH_FIELD, VehicleInfoVOMeta.FUZZY_FIELD, VehicleInfoVOMeta.SEARCH_VALUE, VehicleInfoVOMeta.DIRTY_FIELDS, VehicleInfoVOMeta.SORT_FIELD, VehicleInfoVOMeta.SORT_TYPE, VehicleInfoVOMeta.IDS })
    @SentinelResource(value = VehicleInfoServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(VehicleInfoServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(VehicleInfoVO vehicleInfoVO) {
        Result result = vehicleInfoService.save(vehicleInfoVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = VehicleInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = VehicleInfoServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(VehicleInfoServiceProxy.GET_BY_ID)
    public Result<VehicleInfo> getById(String id) {
        Result<VehicleInfo> result = new Result<>();
        VehicleInfo vehicleInfo = vehicleInfoService.getById(id);
        // join ???????????????
        vehicleInfoService.dao().fill(vehicleInfo).with("ownerCompany").with("useOrganization").with("useUser").with(InfoMeta.VEHICLE_STATUS_DICT).with(InfoMeta.VEHICLE_TYPE_DICT).execute();
        result.success(true).data(vehicleInfo);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = VehicleInfoVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = VehicleInfoServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(VehicleInfoServiceProxy.GET_BY_IDS)
    public Result<List<VehicleInfo>> getByIds(List<String> ids) {
        Result<List<VehicleInfo>> result = new Result<>();
        List<VehicleInfo> list = vehicleInfoService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = VehicleInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.VEHICLE_STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.VEHICLE_CODE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.MODEL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.REGISTRANT, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.OWNER_ORG_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.USE_ORG_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.USE_USER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.COLOR, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.ENGINE_NUMBER, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.FRAME_NUMBER, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.DRIVING_LICENSE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.KILOMETERS, value = "?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.CAR_BOAT_TAX, value = "?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.RESCUE_MONEY, value = "??????(???)", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.COMMERCIAL_INSURANCE_MONEY, value = "?????????(???)", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.INSURANCE_COMPANY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.LICENSING_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.INSURANCE_EXPIRE_DATE, value = "???????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.RESCUE_DUE_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.SCRAP_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.POSITION_DETAIL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.PICTURES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.TECHNICAL_PARAMETER, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.VEHICLE_COUNT, value = "??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { VehicleInfoVOMeta.PAGE_INDEX, VehicleInfoVOMeta.PAGE_SIZE })
    @SentinelResource(value = VehicleInfoServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(VehicleInfoServiceProxy.QUERY_LIST)
    public Result<List<VehicleInfo>> queryList(VehicleInfoVO sample) {
        Result<List<VehicleInfo>> result = new Result<>();
        List<VehicleInfo> list = vehicleInfoService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = VehicleInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.VEHICLE_STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.VEHICLE_CODE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.MODEL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.REGISTRANT, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.OWNER_ORG_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.USE_ORG_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.USE_USER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.COLOR, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.ENGINE_NUMBER, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.FRAME_NUMBER, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.DRIVING_LICENSE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.KILOMETERS, value = "?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.CAR_BOAT_TAX, value = "?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.RESCUE_MONEY, value = "??????(???)", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.COMMERCIAL_INSURANCE_MONEY, value = "?????????(???)", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.INSURANCE_COMPANY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.LICENSING_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.INSURANCE_EXPIRE_DATE, value = "???????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.RESCUE_DUE_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.SCRAP_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.POSITION_DETAIL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.PICTURES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.TECHNICAL_PARAMETER, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.VEHICLE_COUNT, value = "??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = VehicleInfoVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = VehicleInfoServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(VehicleInfoServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<VehicleInfo>> queryPagedList(VehicleInfoVO sample) {
        Result<PagedList<VehicleInfo>> result = new Result<>();
        PagedList<VehicleInfo> list = vehicleInfoService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        vehicleInfoService.dao().fill(list).with("ownerCompany").with("useOrganization").with("useUser").with(InfoMeta.VEHICLE_STATUS_DICT).with(InfoMeta.VEHICLE_TYPE_DICT).execute();
        result.success(true).data(list);
        return result;
    }
}
