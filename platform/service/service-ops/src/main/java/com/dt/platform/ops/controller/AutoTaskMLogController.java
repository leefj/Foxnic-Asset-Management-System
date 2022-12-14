package com.dt.platform.ops.controller;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.ArrayList;
import com.dt.platform.constants.enums.eam.AssetOperateEnum;
import com.dt.platform.domain.eam.Asset;
import com.dt.platform.domain.ops.*;
import com.github.foxnic.commons.busi.id.IDGenerator;
import com.github.foxnic.sql.expr.ConditionExpr;
import org.apache.commons.io.IOUtils;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.proxy.ops.AutoTaskMLogServiceProxy;
import com.dt.platform.domain.ops.meta.AutoTaskMLogVOMeta;
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
import com.dt.platform.domain.ops.meta.AutoTaskMLogMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.IAutoTaskMLogService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-08-22 08:16:56
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("OpsAutoTaskMLogController")
public class AutoTaskMLogController extends SuperController {

    @Autowired
    private IAutoTaskMLogService autoTaskMLogService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoTaskMLogVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AutoTaskMLogVOMeta.TASK_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoTaskMLogVOMeta.ACTION_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoTaskMLogVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoTaskMLogVOMeta.CONTENT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoTaskMLogVOMeta.STIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AutoTaskMLogVOMeta.ETIME, value = "????????????", required = false, dataTypeClass = Date.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = AutoTaskMLogServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoTaskMLogServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(AutoTaskMLogVO autoTaskMLogVO) {
        Result result = autoTaskMLogService.insert(autoTaskMLogVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoTaskMLogVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "12")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = AutoTaskMLogServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoTaskMLogServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  autoTaskMLogService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = autoTaskMLogService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoTaskMLogVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AutoTaskMLogServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoTaskMLogServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = autoTaskMLogService.hasRefers(ids);
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
            Result result = autoTaskMLogService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = autoTaskMLogService.deleteByIdsLogical(canDeleteIds);
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
		@ApiImplicitParam(name = AutoTaskMLogVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AutoTaskMLogVOMeta.TASK_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoTaskMLogVOMeta.ACTION_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoTaskMLogVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoTaskMLogVOMeta.CONTENT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoTaskMLogVOMeta.STIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AutoTaskMLogVOMeta.ETIME, value = "????????????", required = false, dataTypeClass = Date.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { AutoTaskMLogVOMeta.PAGE_INDEX, AutoTaskMLogVOMeta.PAGE_SIZE, AutoTaskMLogVOMeta.SEARCH_FIELD, AutoTaskMLogVOMeta.FUZZY_FIELD, AutoTaskMLogVOMeta.SEARCH_VALUE, AutoTaskMLogVOMeta.DIRTY_FIELDS, AutoTaskMLogVOMeta.SORT_FIELD, AutoTaskMLogVOMeta.SORT_TYPE, AutoTaskMLogVOMeta.IDS })
    @SentinelResource(value = AutoTaskMLogServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoTaskMLogServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(AutoTaskMLogVO autoTaskMLogVO) {
        Result result = autoTaskMLogService.update(autoTaskMLogVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoTaskMLogVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AutoTaskMLogVOMeta.TASK_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoTaskMLogVOMeta.ACTION_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoTaskMLogVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoTaskMLogVOMeta.CONTENT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoTaskMLogVOMeta.STIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AutoTaskMLogVOMeta.ETIME, value = "????????????", required = false, dataTypeClass = Date.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AutoTaskMLogVOMeta.PAGE_INDEX, AutoTaskMLogVOMeta.PAGE_SIZE, AutoTaskMLogVOMeta.SEARCH_FIELD, AutoTaskMLogVOMeta.FUZZY_FIELD, AutoTaskMLogVOMeta.SEARCH_VALUE, AutoTaskMLogVOMeta.DIRTY_FIELDS, AutoTaskMLogVOMeta.SORT_FIELD, AutoTaskMLogVOMeta.SORT_TYPE, AutoTaskMLogVOMeta.IDS })
    @SentinelResource(value = AutoTaskMLogServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoTaskMLogServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(AutoTaskMLogVO autoTaskMLogVO) {
        Result result = autoTaskMLogService.save(autoTaskMLogVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoTaskMLogVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = AutoTaskMLogServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoTaskMLogServiceProxy.GET_BY_ID)
    public Result<AutoTaskMLog> getById(String id) {
        Result<AutoTaskMLog> result = new Result<>();
        AutoTaskMLog autoTaskMLog = autoTaskMLogService.getById(id);
        // join ???????????????
        autoTaskMLogService.dao().fill(autoTaskMLog).with(AutoTaskMLogMeta.TASK).with(AutoTaskMLogMeta.ACTION).execute();
        result.success(true).data(autoTaskMLog);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoTaskMLogVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AutoTaskMLogServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoTaskMLogServiceProxy.GET_BY_IDS)
    public Result<List<AutoTaskMLog>> getByIds(List<String> ids) {
        Result<List<AutoTaskMLog>> result = new Result<>();
        List<AutoTaskMLog> list = autoTaskMLogService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoTaskMLogVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AutoTaskMLogVOMeta.TASK_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoTaskMLogVOMeta.ACTION_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoTaskMLogVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoTaskMLogVOMeta.CONTENT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoTaskMLogVOMeta.STIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AutoTaskMLogVOMeta.ETIME, value = "????????????", required = false, dataTypeClass = Date.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AutoTaskMLogVOMeta.PAGE_INDEX, AutoTaskMLogVOMeta.PAGE_SIZE })
    @SentinelResource(value = AutoTaskMLogServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoTaskMLogServiceProxy.QUERY_LIST)
    public Result<List<AutoTaskMLog>> queryList(AutoTaskMLogVO sample) {
        Result<List<AutoTaskMLog>> result = new Result<>();
        List<AutoTaskMLog> list = autoTaskMLogService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoTaskMLogVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AutoTaskMLogVOMeta.TASK_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoTaskMLogVOMeta.ACTION_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoTaskMLogVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoTaskMLogVOMeta.CONTENT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoTaskMLogVOMeta.STIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AutoTaskMLogVOMeta.ETIME, value = "????????????", required = false, dataTypeClass = Date.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = AutoTaskMLogServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoTaskMLogServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<AutoTaskMLog>> queryPagedList(AutoTaskMLogVO sample) {
        Result<PagedList<AutoTaskMLog>> result = new Result<>();
        PagedList<AutoTaskMLog> list = autoTaskMLogService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        autoTaskMLogService.dao().fill(list).with(AutoTaskMLogMeta.TASK).with(AutoTaskMLogMeta.ACTION).execute();
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({})
    @ApiOperationSupport(order = 9)
    @SentinelResource(value = AutoTaskMLogServiceProxy.LOG_DOWNLOAD, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoTaskMLogServiceProxy.LOG_DOWNLOAD)
    public void queryPagedList(HttpServletRequest request, HttpServletResponse response, String id) {
        AutoTaskMLog log = autoTaskMLogService.logDownload(id);
        if (log != null) {
            StringBuffer logTxt = new StringBuffer();
            logTxt.append("---------????????????:" + log.getTaskId() + "----------\n");
            logTxt.append("????????????:" + log.getStatus() + "\n");
            logTxt.append("????????????:" + log.getStime() + "\n");
            logTxt.append("????????????:" + log.getEtime() + "\n");
            logTxt.append(log.getContent() + "\n");
            logTxt.append("\n\n");
            logTxt.append("---------????????????????????????----------\n");
            List<AutoTaskLog> listLog = log.getLogList();
            if (listLog != null) {
                for (int i = 0; i < listLog.size(); i++) {
                    logTxt.append("---------??????????????????----------\n");
                    logTxt.append("??????Ip:" + listLog.get(i).getNodeIp() + "\n");
                    logTxt.append("????????????:" + listLog.get(i).getStatus() + "\n");
                    logTxt.append("????????????:\n" + listLog.get(i).getContentDetail() + "\n");
                    logTxt.append("\n\n");
                    logTxt.append("---------??????????????????----------");
                    logTxt.append("\n\n\n");
                }
            }
            logTxt.append("---------??????????????????----------");
            logTxt.append("\n\n\n\n");
            try {
                InputStream inputstream = IOUtils.toInputStream(logTxt.toString(), StandardCharsets.UTF_8.name());
                response.setCharacterEncoding("UTF-8");
                response.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode("??????.txt", "UTF-8"))));
                response.setContentType("application/octet-stream");
                OutputStream out = response.getOutputStream();
                IOUtils.copy(inputstream, out);
                out.flush();
            } catch (Exception e) {
                try {
                    DownloadUtil.writeDownloadError(response, e);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
}
