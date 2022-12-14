package com.dt.platform.ops.controller;

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
import com.dt.platform.proxy.ops.AutoTaskLogServiceProxy;
import com.dt.platform.domain.ops.meta.AutoTaskLogVOMeta;
import com.dt.platform.domain.ops.AutoTaskLog;
import com.dt.platform.domain.ops.AutoTaskLogVO;
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
import com.dt.platform.domain.ops.meta.AutoTaskLogMeta;
import com.dt.platform.domain.ops.AutoTask;
import com.dt.platform.domain.ops.AutoAction;
import com.dt.platform.domain.ops.AutoNode;
import com.dt.platform.domain.ops.AutoTaskMLog;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.IAutoTaskLogService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-08-24 13:48:41
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("OpsAutoTaskLogController")
public class AutoTaskLogController extends SuperController {

    @Autowired
    private IAutoTaskLogService autoTaskLogService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoTaskLogVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "614532689201987584"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.TASK_ID, value = "??????", required = false, dataTypeClass = String.class, example = "614532481739128832"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.M_LOG_ID, value = "??????", required = false, dataTypeClass = String.class, example = "614532688249880576"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.ACTION_ID, value = "??????", required = false, dataTypeClass = String.class, example = "614531850873864192"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.NODE_IP, value = "IP", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.NODE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "614532378768965632"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "failed"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.STIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-08-23 06:53:31"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.ETIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-08-23 06:58:31"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.RECORD_CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "Connect to host by sftp error!"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.CONTENT_DETAIL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.RECORD_TIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-08-23 06:53:31"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = AutoTaskLogServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoTaskLogServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(AutoTaskLogVO autoTaskLogVO) {
        Result result = autoTaskLogService.insert(autoTaskLogVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoTaskLogVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "614532689201987584")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = AutoTaskLogServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoTaskLogServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  autoTaskLogService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = autoTaskLogService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoTaskLogVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AutoTaskLogServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoTaskLogServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = autoTaskLogService.hasRefers(ids);
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
            Result result = autoTaskLogService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = autoTaskLogService.deleteByIdsLogical(canDeleteIds);
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
		@ApiImplicitParam(name = AutoTaskLogVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "614532689201987584"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.TASK_ID, value = "??????", required = false, dataTypeClass = String.class, example = "614532481739128832"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.M_LOG_ID, value = "??????", required = false, dataTypeClass = String.class, example = "614532688249880576"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.ACTION_ID, value = "??????", required = false, dataTypeClass = String.class, example = "614531850873864192"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.NODE_IP, value = "IP", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.NODE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "614532378768965632"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "failed"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.STIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-08-23 06:53:31"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.ETIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-08-23 06:58:31"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.RECORD_CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "Connect to host by sftp error!"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.CONTENT_DETAIL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.RECORD_TIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-08-23 06:53:31"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { AutoTaskLogVOMeta.PAGE_INDEX, AutoTaskLogVOMeta.PAGE_SIZE, AutoTaskLogVOMeta.SEARCH_FIELD, AutoTaskLogVOMeta.FUZZY_FIELD, AutoTaskLogVOMeta.SEARCH_VALUE, AutoTaskLogVOMeta.DIRTY_FIELDS, AutoTaskLogVOMeta.SORT_FIELD, AutoTaskLogVOMeta.SORT_TYPE, AutoTaskLogVOMeta.IDS })
    @SentinelResource(value = AutoTaskLogServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoTaskLogServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(AutoTaskLogVO autoTaskLogVO) {
        Result result = autoTaskLogService.update(autoTaskLogVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoTaskLogVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "614532689201987584"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.TASK_ID, value = "??????", required = false, dataTypeClass = String.class, example = "614532481739128832"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.M_LOG_ID, value = "??????", required = false, dataTypeClass = String.class, example = "614532688249880576"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.ACTION_ID, value = "??????", required = false, dataTypeClass = String.class, example = "614531850873864192"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.NODE_IP, value = "IP", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.NODE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "614532378768965632"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "failed"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.STIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-08-23 06:53:31"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.ETIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-08-23 06:58:31"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.RECORD_CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "Connect to host by sftp error!"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.CONTENT_DETAIL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.RECORD_TIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-08-23 06:53:31"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AutoTaskLogVOMeta.PAGE_INDEX, AutoTaskLogVOMeta.PAGE_SIZE, AutoTaskLogVOMeta.SEARCH_FIELD, AutoTaskLogVOMeta.FUZZY_FIELD, AutoTaskLogVOMeta.SEARCH_VALUE, AutoTaskLogVOMeta.DIRTY_FIELDS, AutoTaskLogVOMeta.SORT_FIELD, AutoTaskLogVOMeta.SORT_TYPE, AutoTaskLogVOMeta.IDS })
    @SentinelResource(value = AutoTaskLogServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoTaskLogServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(AutoTaskLogVO autoTaskLogVO) {
        Result result = autoTaskLogService.save(autoTaskLogVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoTaskLogVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = AutoTaskLogServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoTaskLogServiceProxy.GET_BY_ID)
    public Result<AutoTaskLog> getById(String id) {
        Result<AutoTaskLog> result = new Result<>();
        AutoTaskLog autoTaskLog = autoTaskLogService.getById(id);
        // join ???????????????
        autoTaskLogService.dao().fill(autoTaskLog).with(AutoTaskLogMeta.M_LOG).with(AutoTaskLogMeta.NODE).with(AutoTaskLogMeta.TASK).with(AutoTaskLogMeta.ACTION).execute();
        result.success(true).data(autoTaskLog);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoTaskLogVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AutoTaskLogServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoTaskLogServiceProxy.GET_BY_IDS)
    public Result<List<AutoTaskLog>> getByIds(List<String> ids) {
        Result<List<AutoTaskLog>> result = new Result<>();
        List<AutoTaskLog> list = autoTaskLogService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoTaskLogVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "614532689201987584"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.TASK_ID, value = "??????", required = false, dataTypeClass = String.class, example = "614532481739128832"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.M_LOG_ID, value = "??????", required = false, dataTypeClass = String.class, example = "614532688249880576"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.ACTION_ID, value = "??????", required = false, dataTypeClass = String.class, example = "614531850873864192"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.NODE_IP, value = "IP", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.NODE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "614532378768965632"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "failed"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.STIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-08-23 06:53:31"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.ETIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-08-23 06:58:31"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.RECORD_CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "Connect to host by sftp error!"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.CONTENT_DETAIL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.RECORD_TIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-08-23 06:53:31"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AutoTaskLogVOMeta.PAGE_INDEX, AutoTaskLogVOMeta.PAGE_SIZE })
    @SentinelResource(value = AutoTaskLogServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoTaskLogServiceProxy.QUERY_LIST)
    public Result<List<AutoTaskLog>> queryList(AutoTaskLogVO sample) {
        Result<List<AutoTaskLog>> result = new Result<>();
        List<AutoTaskLog> list = autoTaskLogService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoTaskLogVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "614532689201987584"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.TASK_ID, value = "??????", required = false, dataTypeClass = String.class, example = "614532481739128832"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.M_LOG_ID, value = "??????", required = false, dataTypeClass = String.class, example = "614532688249880576"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.ACTION_ID, value = "??????", required = false, dataTypeClass = String.class, example = "614531850873864192"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.NODE_IP, value = "IP", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.NODE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "614532378768965632"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "failed"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.STIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-08-23 06:53:31"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.ETIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-08-23 06:58:31"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.RECORD_CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "Connect to host by sftp error!"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.CONTENT_DETAIL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.RECORD_TIME, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-08-23 06:53:31"),
		@ApiImplicitParam(name = AutoTaskLogVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = AutoTaskLogServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoTaskLogServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<AutoTaskLog>> queryPagedList(AutoTaskLogVO sample) {
        Result<PagedList<AutoTaskLog>> result = new Result<>();
        PagedList<AutoTaskLog> list = autoTaskLogService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        autoTaskLogService.dao().fill(list).with(AutoTaskLogMeta.M_LOG).with(AutoTaskLogMeta.NODE).with(AutoTaskLogMeta.TASK).with(AutoTaskLogMeta.ACTION).execute();
        result.success(true).data(list);
        return result;
    }
}
