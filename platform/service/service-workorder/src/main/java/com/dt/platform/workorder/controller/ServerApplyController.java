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
import com.dt.platform.proxy.workorder.ServerApplyServiceProxy;
import com.dt.platform.domain.workorder.meta.ServerApplyVOMeta;
import com.dt.platform.domain.workorder.ServerApply;
import com.dt.platform.domain.workorder.ServerApplyVO;
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
import com.dt.platform.domain.workorder.meta.ServerApplyMeta;
import com.dt.platform.domain.workorder.ServerInfo;
import org.github.foxnic.web.domain.hrm.Employee;
import org.github.foxnic.web.domain.bpm.ProcessInstance;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.workorder.service.IServerApplyService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ????????????????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-07-15 20:04:01
 */
@Api(tags = "?????????????????????")
@ApiSort(0)
@RestController("WoServerApplyController")
public class ServerApplyController extends SuperController implements BpmCallbackController {

    @Autowired
    private IServerApplyService serverApplyService;

    /**
     * ???????????????????????????
     */
    @ApiOperation(value = "???????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServerApplyVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "596627925189525504"),
		@ApiImplicitParam(name = ServerApplyVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServerApplyVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServerApplyVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = ServerApplyVOMeta.PROJECT_NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = ServerApplyVOMeta.ENVIRONMENT_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "prod"),
		@ApiImplicitParam(name = ServerApplyVOMeta.PERIOD_TYPE, value = "?????????", required = false, dataTypeClass = String.class, example = "permanent"),
		@ApiImplicitParam(name = ServerApplyVOMeta.EXPIRATION_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-07-05 12:00:00"),
		@ApiImplicitParam(name = ServerApplyVOMeta.APPLY_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = ServerApplyVOMeta.EXPECTED_COMPLETION_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-07-05 12:00:00"),
		@ApiImplicitParam(name = ServerApplyVOMeta.DOMAIN_NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = ServerApplyVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = ServerApplyVOMeta.FILE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServerApplyVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServerApplyVOMeta.COMPLETION_DATE, value = "????????????", required = false, dataTypeClass = Date.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = ServerApplyServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServerApplyServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(ServerApplyVO serverApplyVO) {
        Result result = serverApplyService.insert(serverApplyVO, false);
        return result;
    }

    /**
     * ???????????????????????????
     */
    @ApiOperation(value = "???????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServerApplyVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "596627925189525504")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = ServerApplyServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServerApplyServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  serverApplyService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = serverApplyService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ????????????????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "?????????????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServerApplyVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = ServerApplyServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServerApplyServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = serverApplyService.hasRefers(ids);
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
            Result result = serverApplyService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = serverApplyService.deleteByIdsLogical(canDeleteIds);
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
     * ???????????????????????????
     */
    @ApiOperation(value = "???????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServerApplyVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "596627925189525504"),
		@ApiImplicitParam(name = ServerApplyVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServerApplyVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServerApplyVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = ServerApplyVOMeta.PROJECT_NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = ServerApplyVOMeta.ENVIRONMENT_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "prod"),
		@ApiImplicitParam(name = ServerApplyVOMeta.PERIOD_TYPE, value = "?????????", required = false, dataTypeClass = String.class, example = "permanent"),
		@ApiImplicitParam(name = ServerApplyVOMeta.EXPIRATION_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-07-05 12:00:00"),
		@ApiImplicitParam(name = ServerApplyVOMeta.APPLY_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = ServerApplyVOMeta.EXPECTED_COMPLETION_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-07-05 12:00:00"),
		@ApiImplicitParam(name = ServerApplyVOMeta.DOMAIN_NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = ServerApplyVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = ServerApplyVOMeta.FILE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServerApplyVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServerApplyVOMeta.COMPLETION_DATE, value = "????????????", required = false, dataTypeClass = Date.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { ServerApplyVOMeta.PAGE_INDEX, ServerApplyVOMeta.PAGE_SIZE, ServerApplyVOMeta.SEARCH_FIELD, ServerApplyVOMeta.FUZZY_FIELD, ServerApplyVOMeta.SEARCH_VALUE, ServerApplyVOMeta.DIRTY_FIELDS, ServerApplyVOMeta.SORT_FIELD, ServerApplyVOMeta.SORT_TYPE, ServerApplyVOMeta.IDS })
    @SentinelResource(value = ServerApplyServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServerApplyServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(ServerApplyVO serverApplyVO) {
        Result result = serverApplyService.update(serverApplyVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ???????????????????????????
     */
    @ApiOperation(value = "???????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServerApplyVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "596627925189525504"),
		@ApiImplicitParam(name = ServerApplyVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServerApplyVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServerApplyVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = ServerApplyVOMeta.PROJECT_NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = ServerApplyVOMeta.ENVIRONMENT_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "prod"),
		@ApiImplicitParam(name = ServerApplyVOMeta.PERIOD_TYPE, value = "?????????", required = false, dataTypeClass = String.class, example = "permanent"),
		@ApiImplicitParam(name = ServerApplyVOMeta.EXPIRATION_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-07-05 12:00:00"),
		@ApiImplicitParam(name = ServerApplyVOMeta.APPLY_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = ServerApplyVOMeta.EXPECTED_COMPLETION_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-07-05 12:00:00"),
		@ApiImplicitParam(name = ServerApplyVOMeta.DOMAIN_NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = ServerApplyVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = ServerApplyVOMeta.FILE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServerApplyVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServerApplyVOMeta.COMPLETION_DATE, value = "????????????", required = false, dataTypeClass = Date.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { ServerApplyVOMeta.PAGE_INDEX, ServerApplyVOMeta.PAGE_SIZE, ServerApplyVOMeta.SEARCH_FIELD, ServerApplyVOMeta.FUZZY_FIELD, ServerApplyVOMeta.SEARCH_VALUE, ServerApplyVOMeta.DIRTY_FIELDS, ServerApplyVOMeta.SORT_FIELD, ServerApplyVOMeta.SORT_TYPE, ServerApplyVOMeta.IDS })
    @SentinelResource(value = ServerApplyServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServerApplyServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(ServerApplyVO serverApplyVO) {
        Result result = serverApplyService.save(serverApplyVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ???????????????????????????
     */
    @ApiOperation(value = "???????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServerApplyVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = ServerApplyServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServerApplyServiceProxy.GET_BY_ID)
    public Result<ServerApply> getById(String id) {
        Result<ServerApply> result = new Result<>();
        ServerApply serverApply = serverApplyService.getById(id);
        // join ???????????????
        serverApplyService.dao().fill(serverApply).with("originator").execute();
        result.success(true).data(serverApply);
        return result;
    }

    /**
     * ????????????????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "?????????????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServerApplyVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = ServerApplyServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServerApplyServiceProxy.GET_BY_IDS)
    public Result<List<ServerApply>> getByIds(List<String> ids) {
        Result<List<ServerApply>> result = new Result<>();
        List<ServerApply> list = serverApplyService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ???????????????????????????
     */
    @ApiOperation(value = "???????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServerApplyVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "596627925189525504"),
		@ApiImplicitParam(name = ServerApplyVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServerApplyVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServerApplyVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = ServerApplyVOMeta.PROJECT_NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = ServerApplyVOMeta.ENVIRONMENT_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "prod"),
		@ApiImplicitParam(name = ServerApplyVOMeta.PERIOD_TYPE, value = "?????????", required = false, dataTypeClass = String.class, example = "permanent"),
		@ApiImplicitParam(name = ServerApplyVOMeta.EXPIRATION_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-07-05 12:00:00"),
		@ApiImplicitParam(name = ServerApplyVOMeta.APPLY_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = ServerApplyVOMeta.EXPECTED_COMPLETION_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-07-05 12:00:00"),
		@ApiImplicitParam(name = ServerApplyVOMeta.DOMAIN_NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = ServerApplyVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = ServerApplyVOMeta.FILE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServerApplyVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServerApplyVOMeta.COMPLETION_DATE, value = "????????????", required = false, dataTypeClass = Date.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { ServerApplyVOMeta.PAGE_INDEX, ServerApplyVOMeta.PAGE_SIZE })
    @SentinelResource(value = ServerApplyServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServerApplyServiceProxy.QUERY_LIST)
    public Result<List<ServerApply>> queryList(ServerApplyVO sample) {
        Result<List<ServerApply>> result = new Result<>();
        List<ServerApply> list = serverApplyService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????????????????????????????????
     */
    @ApiOperation(value = "?????????????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServerApplyVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "596627925189525504"),
		@ApiImplicitParam(name = ServerApplyVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServerApplyVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServerApplyVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = ServerApplyVOMeta.PROJECT_NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = ServerApplyVOMeta.ENVIRONMENT_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "prod"),
		@ApiImplicitParam(name = ServerApplyVOMeta.PERIOD_TYPE, value = "?????????", required = false, dataTypeClass = String.class, example = "permanent"),
		@ApiImplicitParam(name = ServerApplyVOMeta.EXPIRATION_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-07-05 12:00:00"),
		@ApiImplicitParam(name = ServerApplyVOMeta.APPLY_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = ServerApplyVOMeta.EXPECTED_COMPLETION_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-07-05 12:00:00"),
		@ApiImplicitParam(name = ServerApplyVOMeta.DOMAIN_NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = ServerApplyVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = ServerApplyVOMeta.FILE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServerApplyVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServerApplyVOMeta.COMPLETION_DATE, value = "????????????", required = false, dataTypeClass = Date.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = ServerApplyServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServerApplyServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<ServerApply>> queryPagedList(ServerApplyVO sample) {
        Result<PagedList<ServerApply>> result = new Result<>();
        PagedList<ServerApply> list = serverApplyService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        serverApplyService.dao().fill(list).with("originator").execute();
        // ???????????????????????????
        serverApplyService.joinProcess(list);
        result.success(true).data(list);
        return result;
    }

    /**
     *  ??????????????????
     */
    @SentinelResource(value = ServerApplyServiceProxy.BPM_CALLBACK, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServerApplyServiceProxy.BPM_CALLBACK)
    public BpmActionResult onProcessCallback(BpmEvent event) {
        return serverApplyService.onProcessCallback(event);
    }
}
