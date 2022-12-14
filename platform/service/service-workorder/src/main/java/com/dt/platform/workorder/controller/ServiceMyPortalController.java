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
import com.dt.platform.proxy.workorder.ServiceMyPortalServiceProxy;
import com.dt.platform.domain.workorder.meta.ServiceMyPortalVOMeta;
import com.dt.platform.domain.workorder.ServiceMyPortal;
import com.dt.platform.domain.workorder.ServiceMyPortalVO;
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
import com.dt.platform.domain.workorder.meta.ServiceMyPortalMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.workorder.service.IServiceMyPortalService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-07-08 21:59:47
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("WoServiceMyPortalController")
public class ServiceMyPortalController extends SuperController {

    @Autowired
    private IServiceMyPortalService serviceMyPortalService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServiceMyPortalVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceMyPortalVOMeta.PORTAL_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceMyPortalVOMeta.USER_ID, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = ServiceMyPortalServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServiceMyPortalServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(ServiceMyPortalVO serviceMyPortalVO) {
        Result result = serviceMyPortalService.insert(serviceMyPortalVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServiceMyPortalVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = ServiceMyPortalServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServiceMyPortalServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  serviceMyPortalService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = serviceMyPortalService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServiceMyPortalVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = ServiceMyPortalServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServiceMyPortalServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = serviceMyPortalService.hasRefers(ids);
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
            Result result = serviceMyPortalService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = serviceMyPortalService.deleteByIdsLogical(canDeleteIds);
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
		@ApiImplicitParam(name = ServiceMyPortalVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceMyPortalVOMeta.PORTAL_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceMyPortalVOMeta.USER_ID, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { ServiceMyPortalVOMeta.PAGE_INDEX, ServiceMyPortalVOMeta.PAGE_SIZE, ServiceMyPortalVOMeta.SEARCH_FIELD, ServiceMyPortalVOMeta.FUZZY_FIELD, ServiceMyPortalVOMeta.SEARCH_VALUE, ServiceMyPortalVOMeta.DIRTY_FIELDS, ServiceMyPortalVOMeta.SORT_FIELD, ServiceMyPortalVOMeta.SORT_TYPE, ServiceMyPortalVOMeta.IDS })
    @SentinelResource(value = ServiceMyPortalServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServiceMyPortalServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(ServiceMyPortalVO serviceMyPortalVO) {
        Result result = serviceMyPortalService.update(serviceMyPortalVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServiceMyPortalVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceMyPortalVOMeta.PORTAL_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceMyPortalVOMeta.USER_ID, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { ServiceMyPortalVOMeta.PAGE_INDEX, ServiceMyPortalVOMeta.PAGE_SIZE, ServiceMyPortalVOMeta.SEARCH_FIELD, ServiceMyPortalVOMeta.FUZZY_FIELD, ServiceMyPortalVOMeta.SEARCH_VALUE, ServiceMyPortalVOMeta.DIRTY_FIELDS, ServiceMyPortalVOMeta.SORT_FIELD, ServiceMyPortalVOMeta.SORT_TYPE, ServiceMyPortalVOMeta.IDS })
    @SentinelResource(value = ServiceMyPortalServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServiceMyPortalServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(ServiceMyPortalVO serviceMyPortalVO) {
        Result result = serviceMyPortalService.save(serviceMyPortalVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServiceMyPortalVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = ServiceMyPortalServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServiceMyPortalServiceProxy.GET_BY_ID)
    public Result<ServiceMyPortal> getById(String id) {
        Result<ServiceMyPortal> result = new Result<>();
        ServiceMyPortal serviceMyPortal = serviceMyPortalService.getById(id);
        result.success(true).data(serviceMyPortal);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServiceMyPortalVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = ServiceMyPortalServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServiceMyPortalServiceProxy.GET_BY_IDS)
    public Result<List<ServiceMyPortal>> getByIds(List<String> ids) {
        Result<List<ServiceMyPortal>> result = new Result<>();
        List<ServiceMyPortal> list = serviceMyPortalService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServiceMyPortalVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceMyPortalVOMeta.PORTAL_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceMyPortalVOMeta.USER_ID, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { ServiceMyPortalVOMeta.PAGE_INDEX, ServiceMyPortalVOMeta.PAGE_SIZE })
    @SentinelResource(value = ServiceMyPortalServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServiceMyPortalServiceProxy.QUERY_LIST)
    public Result<List<ServiceMyPortal>> queryList(ServiceMyPortalVO sample) {
        Result<List<ServiceMyPortal>> result = new Result<>();
        List<ServiceMyPortal> list = serviceMyPortalService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServiceMyPortalVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceMyPortalVOMeta.PORTAL_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceMyPortalVOMeta.USER_ID, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = ServiceMyPortalServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServiceMyPortalServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<ServiceMyPortal>> queryPagedList(ServiceMyPortalVO sample) {
        Result<PagedList<ServiceMyPortal>> result = new Result<>();
        PagedList<ServiceMyPortal> list = serviceMyPortalService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }
}
