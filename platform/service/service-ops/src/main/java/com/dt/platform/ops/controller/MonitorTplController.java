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
import com.dt.platform.proxy.ops.MonitorTplServiceProxy;
import com.dt.platform.domain.ops.meta.MonitorTplVOMeta;
import com.dt.platform.domain.ops.MonitorTpl;
import com.dt.platform.domain.ops.MonitorTplVO;
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
import com.dt.platform.domain.ops.meta.MonitorTplMeta;
import com.dt.platform.domain.ops.MonitorTplType;
import com.dt.platform.domain.ops.MonitorTplIndicator;
import com.dt.platform.domain.ops.MonitorTplGraph;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.IMonitorTplService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-07-14 16:26:06
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("OpsMonitorTplController")
public class MonitorTplController extends SuperController {

    @Autowired
    private IMonitorTplService monitorTplService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorTplVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorTplVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "Linux??????zabbix??????"),
		@ApiImplicitParam(name = MonitorTplVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "tpl_host_linux_zabbix"),
		@ApiImplicitParam(name = MonitorTplVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = MonitorTplVOMeta.TYPE, value = "??????", required = false, dataTypeClass = String.class, example = "host"),
		@ApiImplicitParam(name = MonitorTplVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "Linux??????????????????")
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = MonitorTplServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorTplServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(MonitorTplVO monitorTplVO) {
        Result result = monitorTplService.insert(monitorTplVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorTplVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = MonitorTplServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorTplServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  monitorTplService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = monitorTplService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorTplVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = MonitorTplServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorTplServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = monitorTplService.hasRefers(ids);
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
            Result result = monitorTplService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = monitorTplService.deleteByIdsLogical(canDeleteIds);
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
		@ApiImplicitParam(name = MonitorTplVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorTplVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "Linux??????zabbix??????"),
		@ApiImplicitParam(name = MonitorTplVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "tpl_host_linux_zabbix"),
		@ApiImplicitParam(name = MonitorTplVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = MonitorTplVOMeta.TYPE, value = "??????", required = false, dataTypeClass = String.class, example = "host"),
		@ApiImplicitParam(name = MonitorTplVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "Linux??????????????????")
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { MonitorTplVOMeta.PAGE_INDEX, MonitorTplVOMeta.PAGE_SIZE, MonitorTplVOMeta.SEARCH_FIELD, MonitorTplVOMeta.FUZZY_FIELD, MonitorTplVOMeta.SEARCH_VALUE, MonitorTplVOMeta.DIRTY_FIELDS, MonitorTplVOMeta.SORT_FIELD, MonitorTplVOMeta.SORT_TYPE, MonitorTplVOMeta.IDS })
    @SentinelResource(value = MonitorTplServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorTplServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(MonitorTplVO monitorTplVO) {
        Result result = monitorTplService.update(monitorTplVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorTplVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorTplVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "Linux??????zabbix??????"),
		@ApiImplicitParam(name = MonitorTplVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "tpl_host_linux_zabbix"),
		@ApiImplicitParam(name = MonitorTplVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = MonitorTplVOMeta.TYPE, value = "??????", required = false, dataTypeClass = String.class, example = "host"),
		@ApiImplicitParam(name = MonitorTplVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "Linux??????????????????")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { MonitorTplVOMeta.PAGE_INDEX, MonitorTplVOMeta.PAGE_SIZE, MonitorTplVOMeta.SEARCH_FIELD, MonitorTplVOMeta.FUZZY_FIELD, MonitorTplVOMeta.SEARCH_VALUE, MonitorTplVOMeta.DIRTY_FIELDS, MonitorTplVOMeta.SORT_FIELD, MonitorTplVOMeta.SORT_TYPE, MonitorTplVOMeta.IDS })
    @SentinelResource(value = MonitorTplServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorTplServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(MonitorTplVO monitorTplVO) {
        Result result = monitorTplService.save(monitorTplVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorTplVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = MonitorTplServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorTplServiceProxy.GET_BY_ID)
    public Result<MonitorTpl> getById(String id) {
        Result<MonitorTpl> result = new Result<>();
        MonitorTpl monitorTpl = monitorTplService.getById(id);
        // join ???????????????
        monitorTplService.dao().fill(monitorTpl).with(MonitorTplMeta.TPL_TYPE).execute();
        result.success(true).data(monitorTpl);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorTplVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = MonitorTplServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorTplServiceProxy.GET_BY_IDS)
    public Result<List<MonitorTpl>> getByIds(List<String> ids) {
        Result<List<MonitorTpl>> result = new Result<>();
        List<MonitorTpl> list = monitorTplService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorTplVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorTplVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "Linux??????zabbix??????"),
		@ApiImplicitParam(name = MonitorTplVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "tpl_host_linux_zabbix"),
		@ApiImplicitParam(name = MonitorTplVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = MonitorTplVOMeta.TYPE, value = "??????", required = false, dataTypeClass = String.class, example = "host"),
		@ApiImplicitParam(name = MonitorTplVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "Linux??????????????????")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { MonitorTplVOMeta.PAGE_INDEX, MonitorTplVOMeta.PAGE_SIZE })
    @SentinelResource(value = MonitorTplServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorTplServiceProxy.QUERY_LIST)
    public Result<List<MonitorTpl>> queryList(MonitorTplVO sample) {
        Result<List<MonitorTpl>> result = new Result<>();
        List<MonitorTpl> list = monitorTplService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorTplVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorTplVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "Linux??????zabbix??????"),
		@ApiImplicitParam(name = MonitorTplVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "tpl_host_linux_zabbix"),
		@ApiImplicitParam(name = MonitorTplVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = MonitorTplVOMeta.TYPE, value = "??????", required = false, dataTypeClass = String.class, example = "host"),
		@ApiImplicitParam(name = MonitorTplVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "Linux??????????????????")
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = MonitorTplServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorTplServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<MonitorTpl>> queryPagedList(MonitorTplVO sample) {
        Result<PagedList<MonitorTpl>> result = new Result<>();
        PagedList<MonitorTpl> list = monitorTplService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        monitorTplService.dao().fill(list).with(MonitorTplMeta.TPL_TYPE).execute();
        result.success(true).data(list);
        return result;
    }
}
