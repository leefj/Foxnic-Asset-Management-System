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
import com.dt.platform.proxy.ops.MonitorTplGraphServiceProxy;
import com.dt.platform.domain.ops.meta.MonitorTplGraphVOMeta;
import com.dt.platform.domain.ops.MonitorTplGraph;
import com.dt.platform.domain.ops.MonitorTplGraphVO;
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
import com.dt.platform.domain.ops.meta.MonitorTplGraphMeta;
import com.dt.platform.domain.ops.MonitorTpl;
import com.dt.platform.domain.ops.MonitorTplGraphItem;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.IMonitorTplGraphService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-07-14 16:30:58
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("OpsMonitorTplGraphController")
public class MonitorTplGraphController extends SuperController {

    @Autowired
    private IMonitorTplGraphService monitorTplGraphService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "CPU????????????"),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.TPL_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "tpl_host_linux_script"),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.GRAPH_WIDTH, value = "??????", required = false, dataTypeClass = Integer.class, example = "900"),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.GRAPH_HEIGHT, value = "??????", required = false, dataTypeClass = Integer.class, example = "200"),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.GRAPH_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "line"),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.DS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.LABEL, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class, example = "800"),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = MonitorTplGraphServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorTplGraphServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(MonitorTplGraphVO monitorTplGraphVO) {
        Result result = monitorTplGraphService.insert(monitorTplGraphVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = MonitorTplGraphServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorTplGraphServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  monitorTplGraphService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = monitorTplGraphService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = MonitorTplGraphServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorTplGraphServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = monitorTplGraphService.hasRefers(ids);
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
            Result result = monitorTplGraphService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = monitorTplGraphService.deleteByIdsLogical(canDeleteIds);
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
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "CPU????????????"),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.TPL_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "tpl_host_linux_script"),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.GRAPH_WIDTH, value = "??????", required = false, dataTypeClass = Integer.class, example = "900"),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.GRAPH_HEIGHT, value = "??????", required = false, dataTypeClass = Integer.class, example = "200"),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.GRAPH_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "line"),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.DS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.LABEL, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class, example = "800"),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { MonitorTplGraphVOMeta.PAGE_INDEX, MonitorTplGraphVOMeta.PAGE_SIZE, MonitorTplGraphVOMeta.SEARCH_FIELD, MonitorTplGraphVOMeta.FUZZY_FIELD, MonitorTplGraphVOMeta.SEARCH_VALUE, MonitorTplGraphVOMeta.DIRTY_FIELDS, MonitorTplGraphVOMeta.SORT_FIELD, MonitorTplGraphVOMeta.SORT_TYPE, MonitorTplGraphVOMeta.IDS })
    @SentinelResource(value = MonitorTplGraphServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorTplGraphServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(MonitorTplGraphVO monitorTplGraphVO) {
        Result result = monitorTplGraphService.update(monitorTplGraphVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "CPU????????????"),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.TPL_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "tpl_host_linux_script"),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.GRAPH_WIDTH, value = "??????", required = false, dataTypeClass = Integer.class, example = "900"),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.GRAPH_HEIGHT, value = "??????", required = false, dataTypeClass = Integer.class, example = "200"),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.GRAPH_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "line"),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.DS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.LABEL, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class, example = "800"),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { MonitorTplGraphVOMeta.PAGE_INDEX, MonitorTplGraphVOMeta.PAGE_SIZE, MonitorTplGraphVOMeta.SEARCH_FIELD, MonitorTplGraphVOMeta.FUZZY_FIELD, MonitorTplGraphVOMeta.SEARCH_VALUE, MonitorTplGraphVOMeta.DIRTY_FIELDS, MonitorTplGraphVOMeta.SORT_FIELD, MonitorTplGraphVOMeta.SORT_TYPE, MonitorTplGraphVOMeta.IDS })
    @SentinelResource(value = MonitorTplGraphServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorTplGraphServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(MonitorTplGraphVO monitorTplGraphVO) {
        Result result = monitorTplGraphService.save(monitorTplGraphVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = MonitorTplGraphServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorTplGraphServiceProxy.GET_BY_ID)
    public Result<MonitorTplGraph> getById(String id) {
        Result<MonitorTplGraph> result = new Result<>();
        MonitorTplGraph monitorTplGraph = monitorTplGraphService.getById(id);
        // join ???????????????
        monitorTplGraphService.dao().fill(monitorTplGraph).with(MonitorTplGraphMeta.TPL).execute();
        result.success(true).data(monitorTplGraph);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = MonitorTplGraphServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorTplGraphServiceProxy.GET_BY_IDS)
    public Result<List<MonitorTplGraph>> getByIds(List<String> ids) {
        Result<List<MonitorTplGraph>> result = new Result<>();
        List<MonitorTplGraph> list = monitorTplGraphService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "CPU????????????"),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.TPL_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "tpl_host_linux_script"),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.GRAPH_WIDTH, value = "??????", required = false, dataTypeClass = Integer.class, example = "900"),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.GRAPH_HEIGHT, value = "??????", required = false, dataTypeClass = Integer.class, example = "200"),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.GRAPH_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "line"),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.DS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.LABEL, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class, example = "800"),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { MonitorTplGraphVOMeta.PAGE_INDEX, MonitorTplGraphVOMeta.PAGE_SIZE })
    @SentinelResource(value = MonitorTplGraphServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorTplGraphServiceProxy.QUERY_LIST)
    public Result<List<MonitorTplGraph>> queryList(MonitorTplGraphVO sample) {
        Result<List<MonitorTplGraph>> result = new Result<>();
        List<MonitorTplGraph> list = monitorTplGraphService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "CPU????????????"),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.TPL_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "tpl_host_linux_script"),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.GRAPH_WIDTH, value = "??????", required = false, dataTypeClass = Integer.class, example = "900"),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.GRAPH_HEIGHT, value = "??????", required = false, dataTypeClass = Integer.class, example = "200"),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.GRAPH_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "line"),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.DS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.LABEL, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class, example = "800"),
		@ApiImplicitParam(name = MonitorTplGraphVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = MonitorTplGraphServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorTplGraphServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<MonitorTplGraph>> queryPagedList(MonitorTplGraphVO sample) {
        Result<PagedList<MonitorTplGraph>> result = new Result<>();
        PagedList<MonitorTplGraph> list = monitorTplGraphService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        monitorTplGraphService.dao().fill(list).with(MonitorTplGraphMeta.TPL).execute();
        result.success(true).data(list);
        return result;
    }
}
