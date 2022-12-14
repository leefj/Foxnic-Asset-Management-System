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
import com.dt.platform.proxy.ops.MonitorNodeGroupServiceProxy;
import com.dt.platform.domain.ops.meta.MonitorNodeGroupVOMeta;
import com.dt.platform.domain.ops.MonitorNodeGroup;
import com.dt.platform.domain.ops.MonitorNodeGroupVO;
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
import com.dt.platform.domain.ops.meta.MonitorNodeGroupMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.IMonitorNodeGroupService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-07-13 07:13:47
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("OpsMonitorNodeGroupController")
public class MonitorNodeGroupController extends SuperController {

    @Autowired
    private IMonitorNodeGroupService monitorNodeGroupService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeGroupVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "543027008297238528"),
		@ApiImplicitParam(name = MonitorNodeGroupVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "Linux??????"),
		@ApiImplicitParam(name = MonitorNodeGroupVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "Linux??????")
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = MonitorNodeGroupServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeGroupServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(MonitorNodeGroupVO monitorNodeGroupVO) {
        Result result = monitorNodeGroupService.insert(monitorNodeGroupVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeGroupVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "543027008297238528")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = MonitorNodeGroupServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeGroupServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  monitorNodeGroupService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = monitorNodeGroupService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeGroupVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = MonitorNodeGroupServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeGroupServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = monitorNodeGroupService.hasRefers(ids);
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
            Result result = monitorNodeGroupService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = monitorNodeGroupService.deleteByIdsLogical(canDeleteIds);
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
		@ApiImplicitParam(name = MonitorNodeGroupVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "543027008297238528"),
		@ApiImplicitParam(name = MonitorNodeGroupVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "Linux??????"),
		@ApiImplicitParam(name = MonitorNodeGroupVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "Linux??????")
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { MonitorNodeGroupVOMeta.PAGE_INDEX, MonitorNodeGroupVOMeta.PAGE_SIZE, MonitorNodeGroupVOMeta.SEARCH_FIELD, MonitorNodeGroupVOMeta.FUZZY_FIELD, MonitorNodeGroupVOMeta.SEARCH_VALUE, MonitorNodeGroupVOMeta.DIRTY_FIELDS, MonitorNodeGroupVOMeta.SORT_FIELD, MonitorNodeGroupVOMeta.SORT_TYPE, MonitorNodeGroupVOMeta.IDS })
    @SentinelResource(value = MonitorNodeGroupServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeGroupServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(MonitorNodeGroupVO monitorNodeGroupVO) {
        Result result = monitorNodeGroupService.update(monitorNodeGroupVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeGroupVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "543027008297238528"),
		@ApiImplicitParam(name = MonitorNodeGroupVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "Linux??????"),
		@ApiImplicitParam(name = MonitorNodeGroupVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "Linux??????")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { MonitorNodeGroupVOMeta.PAGE_INDEX, MonitorNodeGroupVOMeta.PAGE_SIZE, MonitorNodeGroupVOMeta.SEARCH_FIELD, MonitorNodeGroupVOMeta.FUZZY_FIELD, MonitorNodeGroupVOMeta.SEARCH_VALUE, MonitorNodeGroupVOMeta.DIRTY_FIELDS, MonitorNodeGroupVOMeta.SORT_FIELD, MonitorNodeGroupVOMeta.SORT_TYPE, MonitorNodeGroupVOMeta.IDS })
    @SentinelResource(value = MonitorNodeGroupServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeGroupServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(MonitorNodeGroupVO monitorNodeGroupVO) {
        Result result = monitorNodeGroupService.save(monitorNodeGroupVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeGroupVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = MonitorNodeGroupServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeGroupServiceProxy.GET_BY_ID)
    public Result<MonitorNodeGroup> getById(String id) {
        Result<MonitorNodeGroup> result = new Result<>();
        MonitorNodeGroup monitorNodeGroup = monitorNodeGroupService.getById(id);
        result.success(true).data(monitorNodeGroup);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeGroupVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = MonitorNodeGroupServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeGroupServiceProxy.GET_BY_IDS)
    public Result<List<MonitorNodeGroup>> getByIds(List<String> ids) {
        Result<List<MonitorNodeGroup>> result = new Result<>();
        List<MonitorNodeGroup> list = monitorNodeGroupService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeGroupVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "543027008297238528"),
		@ApiImplicitParam(name = MonitorNodeGroupVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "Linux??????"),
		@ApiImplicitParam(name = MonitorNodeGroupVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "Linux??????")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { MonitorNodeGroupVOMeta.PAGE_INDEX, MonitorNodeGroupVOMeta.PAGE_SIZE })
    @SentinelResource(value = MonitorNodeGroupServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeGroupServiceProxy.QUERY_LIST)
    public Result<List<MonitorNodeGroup>> queryList(MonitorNodeGroupVO sample) {
        Result<List<MonitorNodeGroup>> result = new Result<>();
        List<MonitorNodeGroup> list = monitorNodeGroupService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorNodeGroupVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "543027008297238528"),
		@ApiImplicitParam(name = MonitorNodeGroupVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "Linux??????"),
		@ApiImplicitParam(name = MonitorNodeGroupVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "Linux??????")
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = MonitorNodeGroupServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorNodeGroupServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<MonitorNodeGroup>> queryPagedList(MonitorNodeGroupVO sample) {
        Result<PagedList<MonitorNodeGroup>> result = new Result<>();
        PagedList<MonitorNodeGroup> list = monitorNodeGroupService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }
}
