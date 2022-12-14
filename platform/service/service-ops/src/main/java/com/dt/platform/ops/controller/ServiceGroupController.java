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
import com.dt.platform.proxy.ops.ServiceGroupServiceProxy;
import com.dt.platform.domain.ops.meta.ServiceGroupVOMeta;
import com.dt.platform.domain.ops.ServiceGroup;
import com.dt.platform.domain.ops.ServiceGroupVO;
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
import com.dt.platform.domain.ops.meta.ServiceGroupMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.IServiceGroupService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-07-12 22:05:33
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("OpsServiceGroupController")
public class ServiceGroupController extends SuperController {

    @Autowired
    private IServiceGroupService serviceGroupService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServiceGroupVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = ServiceGroupVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "db"),
		@ApiImplicitParam(name = ServiceGroupVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "?????????")
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = ServiceGroupServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServiceGroupServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(ServiceGroupVO serviceGroupVO) {
        Result result = serviceGroupService.insert(serviceGroupVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServiceGroupVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = ServiceGroupServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServiceGroupServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  serviceGroupService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = serviceGroupService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServiceGroupVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = ServiceGroupServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServiceGroupServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = serviceGroupService.hasRefers(ids);
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
            Result result = serviceGroupService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = serviceGroupService.deleteByIdsLogical(canDeleteIds);
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
		@ApiImplicitParam(name = ServiceGroupVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = ServiceGroupVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "db"),
		@ApiImplicitParam(name = ServiceGroupVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "?????????")
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { ServiceGroupVOMeta.PAGE_INDEX, ServiceGroupVOMeta.PAGE_SIZE, ServiceGroupVOMeta.SEARCH_FIELD, ServiceGroupVOMeta.FUZZY_FIELD, ServiceGroupVOMeta.SEARCH_VALUE, ServiceGroupVOMeta.DIRTY_FIELDS, ServiceGroupVOMeta.SORT_FIELD, ServiceGroupVOMeta.SORT_TYPE, ServiceGroupVOMeta.IDS })
    @SentinelResource(value = ServiceGroupServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServiceGroupServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(ServiceGroupVO serviceGroupVO) {
        Result result = serviceGroupService.update(serviceGroupVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServiceGroupVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = ServiceGroupVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "db"),
		@ApiImplicitParam(name = ServiceGroupVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "?????????")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { ServiceGroupVOMeta.PAGE_INDEX, ServiceGroupVOMeta.PAGE_SIZE, ServiceGroupVOMeta.SEARCH_FIELD, ServiceGroupVOMeta.FUZZY_FIELD, ServiceGroupVOMeta.SEARCH_VALUE, ServiceGroupVOMeta.DIRTY_FIELDS, ServiceGroupVOMeta.SORT_FIELD, ServiceGroupVOMeta.SORT_TYPE, ServiceGroupVOMeta.IDS })
    @SentinelResource(value = ServiceGroupServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServiceGroupServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(ServiceGroupVO serviceGroupVO) {
        Result result = serviceGroupService.save(serviceGroupVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServiceGroupVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = ServiceGroupServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServiceGroupServiceProxy.GET_BY_ID)
    public Result<ServiceGroup> getById(String id) {
        Result<ServiceGroup> result = new Result<>();
        ServiceGroup serviceGroup = serviceGroupService.getById(id);
        result.success(true).data(serviceGroup);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServiceGroupVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = ServiceGroupServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServiceGroupServiceProxy.GET_BY_IDS)
    public Result<List<ServiceGroup>> getByIds(List<String> ids) {
        Result<List<ServiceGroup>> result = new Result<>();
        List<ServiceGroup> list = serviceGroupService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServiceGroupVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = ServiceGroupVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "db"),
		@ApiImplicitParam(name = ServiceGroupVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "?????????")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { ServiceGroupVOMeta.PAGE_INDEX, ServiceGroupVOMeta.PAGE_SIZE })
    @SentinelResource(value = ServiceGroupServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServiceGroupServiceProxy.QUERY_LIST)
    public Result<List<ServiceGroup>> queryList(ServiceGroupVO sample) {
        Result<List<ServiceGroup>> result = new Result<>();
        List<ServiceGroup> list = serviceGroupService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServiceGroupVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = ServiceGroupVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "db"),
		@ApiImplicitParam(name = ServiceGroupVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "?????????")
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = ServiceGroupServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServiceGroupServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<ServiceGroup>> queryPagedList(ServiceGroupVO sample) {
        Result<PagedList<ServiceGroup>> result = new Result<>();
        PagedList<ServiceGroup> list = serviceGroupService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }
}
