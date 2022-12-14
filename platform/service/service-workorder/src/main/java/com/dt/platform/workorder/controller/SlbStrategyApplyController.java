package com.dt.platform.workorder.controller;

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
import org.github.foxnic.web.constants.enums.bpm.BpmEventType;
import org.github.foxnic.web.proxy.bpm.BpmCallbackController;
import org.github.foxnic.web.domain.bpm.BpmActionResult;
import org.github.foxnic.web.domain.bpm.BpmEvent;
import com.dt.platform.proxy.workorder.SlbStrategyApplyServiceProxy;
import com.dt.platform.domain.workorder.meta.SlbStrategyApplyVOMeta;
import com.dt.platform.domain.workorder.SlbStrategyApply;
import com.dt.platform.domain.workorder.SlbStrategyApplyVO;
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
import com.dt.platform.domain.workorder.meta.SlbStrategyApplyMeta;
import com.dt.platform.domain.workorder.SlbStrategyInfo;
import org.github.foxnic.web.domain.hrm.Employee;
import org.github.foxnic.web.domain.bpm.ProcessInstance;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.workorder.service.ISlbStrategyApplyService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ?????????????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-07-15 20:04:21
 */
@Api(tags = "??????????????????")
@ApiSort(0)
@RestController("WoSlbStrategyApplyController")
public class SlbStrategyApplyController extends SuperController implements BpmCallbackController {

    @Autowired
    private ISlbStrategyApplyService slbStrategyApplyService;

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.PROJECT_NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.ENVIRONMENT_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.PERIOD_TYPE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.EXPIRATION_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.APPLY_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.EXPECTED_COMPLETION_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.CONTENT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.FILE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = SlbStrategyApplyServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SlbStrategyApplyServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(SlbStrategyApplyVO slbStrategyApplyVO) {
        Result result = slbStrategyApplyService.insert(slbStrategyApplyVO, false);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = SlbStrategyApplyServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SlbStrategyApplyServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  slbStrategyApplyService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = slbStrategyApplyService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ?????????????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "??????????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = SlbStrategyApplyServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SlbStrategyApplyServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = slbStrategyApplyService.hasRefers(ids);
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
            Result result = slbStrategyApplyService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = slbStrategyApplyService.deleteByIdsLogical(canDeleteIds);
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
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.PROJECT_NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.ENVIRONMENT_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.PERIOD_TYPE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.EXPIRATION_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.APPLY_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.EXPECTED_COMPLETION_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.CONTENT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.FILE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { SlbStrategyApplyVOMeta.PAGE_INDEX, SlbStrategyApplyVOMeta.PAGE_SIZE, SlbStrategyApplyVOMeta.SEARCH_FIELD, SlbStrategyApplyVOMeta.FUZZY_FIELD, SlbStrategyApplyVOMeta.SEARCH_VALUE, SlbStrategyApplyVOMeta.DIRTY_FIELDS, SlbStrategyApplyVOMeta.SORT_FIELD, SlbStrategyApplyVOMeta.SORT_TYPE, SlbStrategyApplyVOMeta.IDS })
    @SentinelResource(value = SlbStrategyApplyServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SlbStrategyApplyServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(SlbStrategyApplyVO slbStrategyApplyVO) {
        Result result = slbStrategyApplyService.update(slbStrategyApplyVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.PROJECT_NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.ENVIRONMENT_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.PERIOD_TYPE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.EXPIRATION_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.APPLY_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.EXPECTED_COMPLETION_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.CONTENT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.FILE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { SlbStrategyApplyVOMeta.PAGE_INDEX, SlbStrategyApplyVOMeta.PAGE_SIZE, SlbStrategyApplyVOMeta.SEARCH_FIELD, SlbStrategyApplyVOMeta.FUZZY_FIELD, SlbStrategyApplyVOMeta.SEARCH_VALUE, SlbStrategyApplyVOMeta.DIRTY_FIELDS, SlbStrategyApplyVOMeta.SORT_FIELD, SlbStrategyApplyVOMeta.SORT_TYPE, SlbStrategyApplyVOMeta.IDS })
    @SentinelResource(value = SlbStrategyApplyServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SlbStrategyApplyServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(SlbStrategyApplyVO slbStrategyApplyVO) {
        Result result = slbStrategyApplyService.save(slbStrategyApplyVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = SlbStrategyApplyServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SlbStrategyApplyServiceProxy.GET_BY_ID)
    public Result<SlbStrategyApply> getById(String id) {
        Result<SlbStrategyApply> result = new Result<>();
        SlbStrategyApply slbStrategyApply = slbStrategyApplyService.getById(id);
        // join ???????????????
        slbStrategyApplyService.dao().fill(slbStrategyApply).with("originator").execute();
        result.success(true).data(slbStrategyApply);
        return result;
    }

    /**
     * ?????????????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "??????????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = SlbStrategyApplyServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SlbStrategyApplyServiceProxy.GET_BY_IDS)
    public Result<List<SlbStrategyApply>> getByIds(List<String> ids) {
        Result<List<SlbStrategyApply>> result = new Result<>();
        List<SlbStrategyApply> list = slbStrategyApplyService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.PROJECT_NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.ENVIRONMENT_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.PERIOD_TYPE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.EXPIRATION_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.APPLY_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.EXPECTED_COMPLETION_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.CONTENT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.FILE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { SlbStrategyApplyVOMeta.PAGE_INDEX, SlbStrategyApplyVOMeta.PAGE_SIZE })
    @SentinelResource(value = SlbStrategyApplyServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SlbStrategyApplyServiceProxy.QUERY_LIST)
    public Result<List<SlbStrategyApply>> queryList(SlbStrategyApplyVO sample) {
        Result<List<SlbStrategyApply>> result = new Result<>();
        List<SlbStrategyApply> list = slbStrategyApplyService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????????????????
     */
    @ApiOperation(value = "??????????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.PROJECT_NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.ENVIRONMENT_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.PERIOD_TYPE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.EXPIRATION_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.APPLY_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.EXPECTED_COMPLETION_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.CONTENT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.FILE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SlbStrategyApplyVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = SlbStrategyApplyServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SlbStrategyApplyServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<SlbStrategyApply>> queryPagedList(SlbStrategyApplyVO sample) {
        Result<PagedList<SlbStrategyApply>> result = new Result<>();
        PagedList<SlbStrategyApply> list = slbStrategyApplyService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        slbStrategyApplyService.dao().fill(list).with("originator").execute();
        // ???????????????????????????
        slbStrategyApplyService.joinProcess(list);
        result.success(true).data(list);
        return result;
    }

    /**
     *  ??????????????????
     */
    @SentinelResource(value = SlbStrategyApplyServiceProxy.BPM_CALLBACK, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SlbStrategyApplyServiceProxy.BPM_CALLBACK)
    public BpmActionResult onProcessCallback(BpmEvent event) {
        return slbStrategyApplyService.onProcessCallback(event);
    }
}
