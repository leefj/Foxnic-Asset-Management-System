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
import com.dt.platform.proxy.ops.MonitorObjectServiceProxy;
import com.dt.platform.domain.ops.meta.MonitorObjectVOMeta;
import com.dt.platform.domain.ops.MonitorObject;
import com.dt.platform.domain.ops.MonitorObjectVO;
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
import com.dt.platform.domain.ops.meta.MonitorObjectMeta;
import com.dt.platform.domain.ops.MonitorObjectGroup;
import com.dt.platform.domain.ops.MonitorObjectModel;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.IMonitorObjectService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-07-10 09:04:00
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("OpsMonitorObjectController")
public class MonitorObjectController extends SuperController {

    @Autowired
    private IMonitorObjectService monitorObjectService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorObjectVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "598430433176195072"),
		@ApiImplicitParam(name = MonitorObjectVOMeta.OBJECT_MODEL_ID, value = "??????", required = false, dataTypeClass = String.class, example = "598422473460940800"),
		@ApiImplicitParam(name = MonitorObjectVOMeta.GROUP_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "middleware"),
		@ApiImplicitParam(name = MonitorObjectVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "121212"),
		@ApiImplicitParam(name = MonitorObjectVOMeta.IP, value = "IP", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = MonitorObjectServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorObjectServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(MonitorObjectVO monitorObjectVO) {
        Result result = monitorObjectService.insert(monitorObjectVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorObjectVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "598430433176195072")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = MonitorObjectServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorObjectServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  monitorObjectService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = monitorObjectService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorObjectVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = MonitorObjectServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorObjectServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = monitorObjectService.hasRefers(ids);
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
            Result result = monitorObjectService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = monitorObjectService.deleteByIdsLogical(canDeleteIds);
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
		@ApiImplicitParam(name = MonitorObjectVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "598430433176195072"),
		@ApiImplicitParam(name = MonitorObjectVOMeta.OBJECT_MODEL_ID, value = "??????", required = false, dataTypeClass = String.class, example = "598422473460940800"),
		@ApiImplicitParam(name = MonitorObjectVOMeta.GROUP_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "middleware"),
		@ApiImplicitParam(name = MonitorObjectVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "121212"),
		@ApiImplicitParam(name = MonitorObjectVOMeta.IP, value = "IP", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { MonitorObjectVOMeta.PAGE_INDEX, MonitorObjectVOMeta.PAGE_SIZE, MonitorObjectVOMeta.SEARCH_FIELD, MonitorObjectVOMeta.FUZZY_FIELD, MonitorObjectVOMeta.SEARCH_VALUE, MonitorObjectVOMeta.DIRTY_FIELDS, MonitorObjectVOMeta.SORT_FIELD, MonitorObjectVOMeta.SORT_TYPE, MonitorObjectVOMeta.IDS })
    @SentinelResource(value = MonitorObjectServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorObjectServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(MonitorObjectVO monitorObjectVO) {
        Result result = monitorObjectService.update(monitorObjectVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorObjectVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "598430433176195072"),
		@ApiImplicitParam(name = MonitorObjectVOMeta.OBJECT_MODEL_ID, value = "??????", required = false, dataTypeClass = String.class, example = "598422473460940800"),
		@ApiImplicitParam(name = MonitorObjectVOMeta.GROUP_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "middleware"),
		@ApiImplicitParam(name = MonitorObjectVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "121212"),
		@ApiImplicitParam(name = MonitorObjectVOMeta.IP, value = "IP", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { MonitorObjectVOMeta.PAGE_INDEX, MonitorObjectVOMeta.PAGE_SIZE, MonitorObjectVOMeta.SEARCH_FIELD, MonitorObjectVOMeta.FUZZY_FIELD, MonitorObjectVOMeta.SEARCH_VALUE, MonitorObjectVOMeta.DIRTY_FIELDS, MonitorObjectVOMeta.SORT_FIELD, MonitorObjectVOMeta.SORT_TYPE, MonitorObjectVOMeta.IDS })
    @SentinelResource(value = MonitorObjectServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorObjectServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(MonitorObjectVO monitorObjectVO) {
        Result result = monitorObjectService.save(monitorObjectVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorObjectVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = MonitorObjectServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorObjectServiceProxy.GET_BY_ID)
    public Result<MonitorObject> getById(String id) {
        Result<MonitorObject> result = new Result<>();
        MonitorObject monitorObject = monitorObjectService.getById(id);
        // join ???????????????
        monitorObjectService.dao().fill(monitorObject).with(MonitorObjectMeta.GROUP).with(MonitorObjectMeta.OBJECT_MODEL).execute();
        result.success(true).data(monitorObject);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorObjectVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = MonitorObjectServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorObjectServiceProxy.GET_BY_IDS)
    public Result<List<MonitorObject>> getByIds(List<String> ids) {
        Result<List<MonitorObject>> result = new Result<>();
        List<MonitorObject> list = monitorObjectService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorObjectVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "598430433176195072"),
		@ApiImplicitParam(name = MonitorObjectVOMeta.OBJECT_MODEL_ID, value = "??????", required = false, dataTypeClass = String.class, example = "598422473460940800"),
		@ApiImplicitParam(name = MonitorObjectVOMeta.GROUP_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "middleware"),
		@ApiImplicitParam(name = MonitorObjectVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "121212"),
		@ApiImplicitParam(name = MonitorObjectVOMeta.IP, value = "IP", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { MonitorObjectVOMeta.PAGE_INDEX, MonitorObjectVOMeta.PAGE_SIZE })
    @SentinelResource(value = MonitorObjectServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorObjectServiceProxy.QUERY_LIST)
    public Result<List<MonitorObject>> queryList(MonitorObjectVO sample) {
        Result<List<MonitorObject>> result = new Result<>();
        List<MonitorObject> list = monitorObjectService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorObjectVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "598430433176195072"),
		@ApiImplicitParam(name = MonitorObjectVOMeta.OBJECT_MODEL_ID, value = "??????", required = false, dataTypeClass = String.class, example = "598422473460940800"),
		@ApiImplicitParam(name = MonitorObjectVOMeta.GROUP_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "middleware"),
		@ApiImplicitParam(name = MonitorObjectVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "121212"),
		@ApiImplicitParam(name = MonitorObjectVOMeta.IP, value = "IP", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = MonitorObjectServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorObjectServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<MonitorObject>> queryPagedList(MonitorObjectVO sample) {
        Result<PagedList<MonitorObject>> result = new Result<>();
        PagedList<MonitorObject> list = monitorObjectService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        monitorObjectService.dao().fill(list).with(MonitorObjectMeta.GROUP).with(MonitorObjectMeta.OBJECT_MODEL).execute();
        result.success(true).data(list);
        return result;
    }
}
