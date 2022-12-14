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
import com.dt.platform.proxy.ops.MonitorTplTypeServiceProxy;
import com.dt.platform.domain.ops.meta.MonitorTplTypeVOMeta;
import com.dt.platform.domain.ops.MonitorTplType;
import com.dt.platform.domain.ops.MonitorTplTypeVO;
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
import com.dt.platform.domain.ops.meta.MonitorTplTypeMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.IMonitorTplTypeService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-07-13 07:15:58
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("OpsMonitorTplTypeController")
public class MonitorTplTypeController extends SuperController {

    @Autowired
    private IMonitorTplTypeService monitorTplTypeService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorTplTypeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorTplTypeVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = MonitorTplTypeVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "host"),
		@ApiImplicitParam(name = MonitorTplTypeVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "????????????")
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = MonitorTplTypeServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorTplTypeServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(MonitorTplTypeVO monitorTplTypeVO) {
        Result result = monitorTplTypeService.insert(monitorTplTypeVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorTplTypeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = MonitorTplTypeServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorTplTypeServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  monitorTplTypeService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = monitorTplTypeService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorTplTypeVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = MonitorTplTypeServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorTplTypeServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = monitorTplTypeService.hasRefers(ids);
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
            Result result = monitorTplTypeService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = monitorTplTypeService.deleteByIdsLogical(canDeleteIds);
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
		@ApiImplicitParam(name = MonitorTplTypeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorTplTypeVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = MonitorTplTypeVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "host"),
		@ApiImplicitParam(name = MonitorTplTypeVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "????????????")
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { MonitorTplTypeVOMeta.PAGE_INDEX, MonitorTplTypeVOMeta.PAGE_SIZE, MonitorTplTypeVOMeta.SEARCH_FIELD, MonitorTplTypeVOMeta.FUZZY_FIELD, MonitorTplTypeVOMeta.SEARCH_VALUE, MonitorTplTypeVOMeta.DIRTY_FIELDS, MonitorTplTypeVOMeta.SORT_FIELD, MonitorTplTypeVOMeta.SORT_TYPE, MonitorTplTypeVOMeta.IDS })
    @SentinelResource(value = MonitorTplTypeServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorTplTypeServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(MonitorTplTypeVO monitorTplTypeVO) {
        Result result = monitorTplTypeService.update(monitorTplTypeVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorTplTypeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorTplTypeVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = MonitorTplTypeVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "host"),
		@ApiImplicitParam(name = MonitorTplTypeVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "????????????")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { MonitorTplTypeVOMeta.PAGE_INDEX, MonitorTplTypeVOMeta.PAGE_SIZE, MonitorTplTypeVOMeta.SEARCH_FIELD, MonitorTplTypeVOMeta.FUZZY_FIELD, MonitorTplTypeVOMeta.SEARCH_VALUE, MonitorTplTypeVOMeta.DIRTY_FIELDS, MonitorTplTypeVOMeta.SORT_FIELD, MonitorTplTypeVOMeta.SORT_TYPE, MonitorTplTypeVOMeta.IDS })
    @SentinelResource(value = MonitorTplTypeServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorTplTypeServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(MonitorTplTypeVO monitorTplTypeVO) {
        Result result = monitorTplTypeService.save(monitorTplTypeVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorTplTypeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = MonitorTplTypeServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorTplTypeServiceProxy.GET_BY_ID)
    public Result<MonitorTplType> getById(String id) {
        Result<MonitorTplType> result = new Result<>();
        MonitorTplType monitorTplType = monitorTplTypeService.getById(id);
        result.success(true).data(monitorTplType);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorTplTypeVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = MonitorTplTypeServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorTplTypeServiceProxy.GET_BY_IDS)
    public Result<List<MonitorTplType>> getByIds(List<String> ids) {
        Result<List<MonitorTplType>> result = new Result<>();
        List<MonitorTplType> list = monitorTplTypeService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorTplTypeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorTplTypeVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = MonitorTplTypeVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "host"),
		@ApiImplicitParam(name = MonitorTplTypeVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "????????????")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { MonitorTplTypeVOMeta.PAGE_INDEX, MonitorTplTypeVOMeta.PAGE_SIZE })
    @SentinelResource(value = MonitorTplTypeServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorTplTypeServiceProxy.QUERY_LIST)
    public Result<List<MonitorTplType>> queryList(MonitorTplTypeVO sample) {
        Result<List<MonitorTplType>> result = new Result<>();
        List<MonitorTplType> list = monitorTplTypeService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MonitorTplTypeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = MonitorTplTypeVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = MonitorTplTypeVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "host"),
		@ApiImplicitParam(name = MonitorTplTypeVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "????????????")
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = MonitorTplTypeServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MonitorTplTypeServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<MonitorTplType>> queryPagedList(MonitorTplTypeVO sample) {
        Result<PagedList<MonitorTplType>> result = new Result<>();
        PagedList<MonitorTplType> list = monitorTplTypeService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }
}
