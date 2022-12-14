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
import com.dt.platform.proxy.ops.MonitorObjectGrafanaServiceProxy;
import com.dt.platform.domain.ops.meta.MonitorObjectGrafanaVOMeta;
import com.dt.platform.domain.ops.MonitorObjectGrafana;
import com.dt.platform.domain.ops.MonitorObjectGrafanaVO;
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
import com.dt.platform.domain.ops.meta.MonitorObjectGrafanaMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.IMonitorObjectGrafanaService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * grafana?????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-07-10 07:25:52
 */
@Api(tags = "grafana??????")
@ApiSort(0)
@RestController("OpsMonitorObjectGrafanaController")
public class MonitorObjectGrafanaController extends SuperController {

    @Autowired
    private IMonitorObjectGrafanaService monitorObjectGrafanaService;

    /**
     * ??????grafana??????
     */
    @ApiOperation(value = "??????grafana??????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorObjectGrafanaVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorObjectGrafanaVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorObjectGrafanaVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorObjectGrafanaVOMeta.URL, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorObjectGrafanaVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = MonitorObjectGrafanaServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorObjectGrafanaServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(MonitorObjectGrafanaVO monitorObjectGrafanaVO) {
        Result result = monitorObjectGrafanaService.insert(monitorObjectGrafanaVO, false);
        return result;
    }

    /**
     * ??????grafana??????
     */
    @ApiOperation(value = "??????grafana??????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorObjectGrafanaVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = MonitorObjectGrafanaServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorObjectGrafanaServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  monitorObjectGrafanaService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = monitorObjectGrafanaService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ????????????grafana?????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????grafana??????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorObjectGrafanaVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = MonitorObjectGrafanaServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorObjectGrafanaServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = monitorObjectGrafanaService.hasRefers(ids);
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
            Result result = monitorObjectGrafanaService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = monitorObjectGrafanaService.deleteByIdsLogical(canDeleteIds);
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
     * ??????grafana??????
     */
    @ApiOperation(value = "??????grafana??????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorObjectGrafanaVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorObjectGrafanaVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorObjectGrafanaVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorObjectGrafanaVOMeta.URL, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorObjectGrafanaVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { MonitorObjectGrafanaVOMeta.PAGE_INDEX, MonitorObjectGrafanaVOMeta.PAGE_SIZE, MonitorObjectGrafanaVOMeta.SEARCH_FIELD, MonitorObjectGrafanaVOMeta.FUZZY_FIELD, MonitorObjectGrafanaVOMeta.SEARCH_VALUE, MonitorObjectGrafanaVOMeta.DIRTY_FIELDS, MonitorObjectGrafanaVOMeta.SORT_FIELD, MonitorObjectGrafanaVOMeta.SORT_TYPE, MonitorObjectGrafanaVOMeta.IDS })
    @SentinelResource(value = MonitorObjectGrafanaServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorObjectGrafanaServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(MonitorObjectGrafanaVO monitorObjectGrafanaVO) {
        Result result = monitorObjectGrafanaService.update(monitorObjectGrafanaVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????grafana??????
     */
    @ApiOperation(value = "??????grafana??????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorObjectGrafanaVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorObjectGrafanaVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorObjectGrafanaVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorObjectGrafanaVOMeta.URL, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorObjectGrafanaVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { MonitorObjectGrafanaVOMeta.PAGE_INDEX, MonitorObjectGrafanaVOMeta.PAGE_SIZE, MonitorObjectGrafanaVOMeta.SEARCH_FIELD, MonitorObjectGrafanaVOMeta.FUZZY_FIELD, MonitorObjectGrafanaVOMeta.SEARCH_VALUE, MonitorObjectGrafanaVOMeta.DIRTY_FIELDS, MonitorObjectGrafanaVOMeta.SORT_FIELD, MonitorObjectGrafanaVOMeta.SORT_TYPE, MonitorObjectGrafanaVOMeta.IDS })
    @SentinelResource(value = MonitorObjectGrafanaServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorObjectGrafanaServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(MonitorObjectGrafanaVO monitorObjectGrafanaVO) {
        Result result = monitorObjectGrafanaService.save(monitorObjectGrafanaVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????grafana??????
     */
    @ApiOperation(value = "??????grafana??????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorObjectGrafanaVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = MonitorObjectGrafanaServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorObjectGrafanaServiceProxy.GET_BY_ID)
    public Result<MonitorObjectGrafana> getById(String id) {
        Result<MonitorObjectGrafana> result = new Result<>();
        MonitorObjectGrafana monitorObjectGrafana = monitorObjectGrafanaService.getById(id);
        result.success(true).data(monitorObjectGrafana);
        return result;
    }

    /**
     * ????????????grafana?????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????grafana??????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorObjectGrafanaVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = MonitorObjectGrafanaServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorObjectGrafanaServiceProxy.GET_BY_IDS)
    public Result<List<MonitorObjectGrafana>> getByIds(List<String> ids) {
        Result<List<MonitorObjectGrafana>> result = new Result<>();
        List<MonitorObjectGrafana> list = monitorObjectGrafanaService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????grafana??????
     */
    @ApiOperation(value = "??????grafana??????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorObjectGrafanaVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorObjectGrafanaVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorObjectGrafanaVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorObjectGrafanaVOMeta.URL, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorObjectGrafanaVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { MonitorObjectGrafanaVOMeta.PAGE_INDEX, MonitorObjectGrafanaVOMeta.PAGE_SIZE })
    @SentinelResource(value = MonitorObjectGrafanaServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorObjectGrafanaServiceProxy.QUERY_LIST)
    public Result<List<MonitorObjectGrafana>> queryList(MonitorObjectGrafanaVO sample) {
        Result<List<MonitorObjectGrafana>> result = new Result<>();
        List<MonitorObjectGrafana> list = monitorObjectGrafanaService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????grafana??????
     */
    @ApiOperation(value = "????????????grafana??????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorObjectGrafanaVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorObjectGrafanaVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorObjectGrafanaVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorObjectGrafanaVOMeta.URL, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorObjectGrafanaVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = MonitorObjectGrafanaServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorObjectGrafanaServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<MonitorObjectGrafana>> queryPagedList(MonitorObjectGrafanaVO sample) {
        Result<PagedList<MonitorObjectGrafana>> result = new Result<>();
        PagedList<MonitorObjectGrafana> list = monitorObjectGrafanaService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }
}
