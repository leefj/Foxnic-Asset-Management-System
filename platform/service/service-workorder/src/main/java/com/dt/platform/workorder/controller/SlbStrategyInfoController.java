package com.dt.platform.workorder.controller;

import java.util.List;
import java.util.ArrayList;
import com.github.foxnic.commons.collection.CollectorUtil;
import com.github.foxnic.dao.entity.ReferCause;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.github.foxnic.web.framework.web.SuperController;
import org.github.foxnic.web.framework.sentinel.SentinelExceptionUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.proxy.workorder.SlbStrategyInfoServiceProxy;
import com.dt.platform.domain.workorder.meta.SlbStrategyInfoVOMeta;
import com.dt.platform.domain.workorder.SlbStrategyInfo;
import com.dt.platform.domain.workorder.SlbStrategyInfoVO;
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
import com.dt.platform.domain.workorder.meta.SlbStrategyInfoMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.workorder.service.ISlbStrategyInfoService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-07-27 20:38:50
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("WoSlbStrategyInfoController")
public class SlbStrategyInfoController extends SuperController {

    @Autowired
    private ISlbStrategyInfoService slbStrategyInfoService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "604774031408771072"),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.OWNER_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.SERVICE_NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.SERVICE_IP, value = "??????IP", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.NODE_IP, value = "??????IP", required = false, dataTypeClass = String.class, example = "121.43.103.102"),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.PORT, value = "??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.BALANCE_STRATEGY, value = "????????????", required = false, dataTypeClass = String.class, example = "average_weight"),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.SESSION_KEEP, value = "????????????", required = false, dataTypeClass = String.class, example = "yes"),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.SESSION_KEEP_METHOD, value = "????????????", required = false, dataTypeClass = String.class, example = "session"),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.USED_PROTOCOL_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "http"),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "12")
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = SlbStrategyInfoServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SlbStrategyInfoServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(SlbStrategyInfoVO slbStrategyInfoVO) {
        Result result = slbStrategyInfoService.insert(slbStrategyInfoVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "604774031408771072")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = SlbStrategyInfoServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SlbStrategyInfoServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  slbStrategyInfoService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = slbStrategyInfoService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = SlbStrategyInfoServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SlbStrategyInfoServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = slbStrategyInfoService.hasRefers(ids);
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
            Result result = slbStrategyInfoService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = slbStrategyInfoService.deleteByIdsLogical(canDeleteIds);
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
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "604774031408771072"),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.OWNER_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.SERVICE_NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.SERVICE_IP, value = "??????IP", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.NODE_IP, value = "??????IP", required = false, dataTypeClass = String.class, example = "121.43.103.102"),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.PORT, value = "??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.BALANCE_STRATEGY, value = "????????????", required = false, dataTypeClass = String.class, example = "average_weight"),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.SESSION_KEEP, value = "????????????", required = false, dataTypeClass = String.class, example = "yes"),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.SESSION_KEEP_METHOD, value = "????????????", required = false, dataTypeClass = String.class, example = "session"),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.USED_PROTOCOL_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "http"),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "12")
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { SlbStrategyInfoVOMeta.PAGE_INDEX, SlbStrategyInfoVOMeta.PAGE_SIZE, SlbStrategyInfoVOMeta.SEARCH_FIELD, SlbStrategyInfoVOMeta.FUZZY_FIELD, SlbStrategyInfoVOMeta.SEARCH_VALUE, SlbStrategyInfoVOMeta.DIRTY_FIELDS, SlbStrategyInfoVOMeta.SORT_FIELD, SlbStrategyInfoVOMeta.SORT_TYPE, SlbStrategyInfoVOMeta.IDS })
    @SentinelResource(value = SlbStrategyInfoServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SlbStrategyInfoServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(SlbStrategyInfoVO slbStrategyInfoVO) {
        Result result = slbStrategyInfoService.update(slbStrategyInfoVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "604774031408771072"),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.OWNER_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.SERVICE_NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.SERVICE_IP, value = "??????IP", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.NODE_IP, value = "??????IP", required = false, dataTypeClass = String.class, example = "121.43.103.102"),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.PORT, value = "??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.BALANCE_STRATEGY, value = "????????????", required = false, dataTypeClass = String.class, example = "average_weight"),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.SESSION_KEEP, value = "????????????", required = false, dataTypeClass = String.class, example = "yes"),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.SESSION_KEEP_METHOD, value = "????????????", required = false, dataTypeClass = String.class, example = "session"),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.USED_PROTOCOL_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "http"),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "12")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { SlbStrategyInfoVOMeta.PAGE_INDEX, SlbStrategyInfoVOMeta.PAGE_SIZE, SlbStrategyInfoVOMeta.SEARCH_FIELD, SlbStrategyInfoVOMeta.FUZZY_FIELD, SlbStrategyInfoVOMeta.SEARCH_VALUE, SlbStrategyInfoVOMeta.DIRTY_FIELDS, SlbStrategyInfoVOMeta.SORT_FIELD, SlbStrategyInfoVOMeta.SORT_TYPE, SlbStrategyInfoVOMeta.IDS })
    @SentinelResource(value = SlbStrategyInfoServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SlbStrategyInfoServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(SlbStrategyInfoVO slbStrategyInfoVO) {
        Result result = slbStrategyInfoService.save(slbStrategyInfoVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = SlbStrategyInfoServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SlbStrategyInfoServiceProxy.GET_BY_ID)
    public Result<SlbStrategyInfo> getById(String id) {
        Result<SlbStrategyInfo> result = new Result<>();
        SlbStrategyInfo slbStrategyInfo = slbStrategyInfoService.getById(id);
        result.success(true).data(slbStrategyInfo);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = SlbStrategyInfoServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SlbStrategyInfoServiceProxy.GET_BY_IDS)
    public Result<List<SlbStrategyInfo>> getByIds(List<String> ids) {
        Result<List<SlbStrategyInfo>> result = new Result<>();
        List<SlbStrategyInfo> list = slbStrategyInfoService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "604774031408771072"),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.OWNER_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.SERVICE_NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.SERVICE_IP, value = "??????IP", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.NODE_IP, value = "??????IP", required = false, dataTypeClass = String.class, example = "121.43.103.102"),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.PORT, value = "??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.BALANCE_STRATEGY, value = "????????????", required = false, dataTypeClass = String.class, example = "average_weight"),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.SESSION_KEEP, value = "????????????", required = false, dataTypeClass = String.class, example = "yes"),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.SESSION_KEEP_METHOD, value = "????????????", required = false, dataTypeClass = String.class, example = "session"),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.USED_PROTOCOL_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "http"),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "12")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { SlbStrategyInfoVOMeta.PAGE_INDEX, SlbStrategyInfoVOMeta.PAGE_SIZE })
    @SentinelResource(value = SlbStrategyInfoServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SlbStrategyInfoServiceProxy.QUERY_LIST)
    public Result<List<SlbStrategyInfo>> queryList(SlbStrategyInfoVO sample) {
        Result<List<SlbStrategyInfo>> result = new Result<>();
        List<SlbStrategyInfo> list = slbStrategyInfoService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "604774031408771072"),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.OWNER_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.SERVICE_NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.SERVICE_IP, value = "??????IP", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.NODE_IP, value = "??????IP", required = false, dataTypeClass = String.class, example = "121.43.103.102"),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.PORT, value = "??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.BALANCE_STRATEGY, value = "????????????", required = false, dataTypeClass = String.class, example = "average_weight"),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.SESSION_KEEP, value = "????????????", required = false, dataTypeClass = String.class, example = "yes"),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.SESSION_KEEP_METHOD, value = "????????????", required = false, dataTypeClass = String.class, example = "session"),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.USED_PROTOCOL_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "http"),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyInfoVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "12")
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = SlbStrategyInfoServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SlbStrategyInfoServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<SlbStrategyInfo>> queryPagedList(SlbStrategyInfoVO sample) {
        Result<PagedList<SlbStrategyInfo>> result = new Result<>();
        PagedList<SlbStrategyInfo> list = slbStrategyInfoService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }
}
