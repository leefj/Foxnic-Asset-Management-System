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
import com.dt.platform.proxy.workorder.ServiceDeskServiceProxy;
import com.dt.platform.domain.workorder.meta.ServiceDeskVOMeta;
import com.dt.platform.domain.workorder.ServiceDesk;
import com.dt.platform.domain.workorder.ServiceDeskVO;
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
import com.dt.platform.domain.workorder.meta.ServiceDeskMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.workorder.service.IServiceDeskService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-07-07 07:36:40
 */
@Api(tags = "?????????")
@ApiSort(0)
@RestController("WoServiceDeskController")
public class ServiceDeskController extends SuperController {

    @Autowired
    private IServiceDeskService serviceDeskService;

    /**
     * ???????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServiceDeskVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.PID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.LABEL, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.TYPE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.PROCESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.PERM, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.IMAGE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.PARAMETER, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = ServiceDeskServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServiceDeskServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(ServiceDeskVO serviceDeskVO) {
        Result result = serviceDeskService.insert(serviceDeskVO, false);
        return result;
    }

    /**
     * ???????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServiceDeskVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = ServiceDeskServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServiceDeskServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  serviceDeskService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = serviceDeskService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServiceDeskVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = ServiceDeskServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServiceDeskServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = serviceDeskService.hasRefers(ids);
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
            Result result = serviceDeskService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = serviceDeskService.deleteByIdsLogical(canDeleteIds);
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
     * ???????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServiceDeskVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.PID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.LABEL, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.TYPE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.PROCESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.PERM, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.IMAGE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.PARAMETER, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { ServiceDeskVOMeta.PAGE_INDEX, ServiceDeskVOMeta.PAGE_SIZE, ServiceDeskVOMeta.SEARCH_FIELD, ServiceDeskVOMeta.FUZZY_FIELD, ServiceDeskVOMeta.SEARCH_VALUE, ServiceDeskVOMeta.DIRTY_FIELDS, ServiceDeskVOMeta.SORT_FIELD, ServiceDeskVOMeta.SORT_TYPE, ServiceDeskVOMeta.IDS })
    @SentinelResource(value = ServiceDeskServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServiceDeskServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(ServiceDeskVO serviceDeskVO) {
        Result result = serviceDeskService.update(serviceDeskVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ???????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServiceDeskVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.PID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.LABEL, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.TYPE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.PROCESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.PERM, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.IMAGE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.PARAMETER, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { ServiceDeskVOMeta.PAGE_INDEX, ServiceDeskVOMeta.PAGE_SIZE, ServiceDeskVOMeta.SEARCH_FIELD, ServiceDeskVOMeta.FUZZY_FIELD, ServiceDeskVOMeta.SEARCH_VALUE, ServiceDeskVOMeta.DIRTY_FIELDS, ServiceDeskVOMeta.SORT_FIELD, ServiceDeskVOMeta.SORT_TYPE, ServiceDeskVOMeta.IDS })
    @SentinelResource(value = ServiceDeskServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServiceDeskServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(ServiceDeskVO serviceDeskVO) {
        Result result = serviceDeskService.save(serviceDeskVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ???????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServiceDeskVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = ServiceDeskServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServiceDeskServiceProxy.GET_BY_ID)
    public Result<ServiceDesk> getById(String id) {
        Result<ServiceDesk> result = new Result<>();
        ServiceDesk serviceDesk = serviceDeskService.getById(id);
        result.success(true).data(serviceDesk);
        return result;
    }

    /**
     * ????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServiceDeskVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = ServiceDeskServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServiceDeskServiceProxy.GET_BY_IDS)
    public Result<List<ServiceDesk>> getByIds(List<String> ids) {
        Result<List<ServiceDesk>> result = new Result<>();
        List<ServiceDesk> list = serviceDeskService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ???????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServiceDeskVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.PID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.LABEL, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.TYPE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.PROCESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.PERM, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.IMAGE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.PARAMETER, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { ServiceDeskVOMeta.PAGE_INDEX, ServiceDeskVOMeta.PAGE_SIZE })
    @SentinelResource(value = ServiceDeskServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServiceDeskServiceProxy.QUERY_LIST)
    public Result<List<ServiceDesk>> queryList(ServiceDeskVO sample) {
        Result<List<ServiceDesk>> result = new Result<>();
        List<ServiceDesk> list = serviceDeskService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServiceDeskVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.PID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.LABEL, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.TYPE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.PROCESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.PERM, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.IMAGE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.PARAMETER, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceDeskVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = ServiceDeskServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServiceDeskServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<ServiceDesk>> queryPagedList(ServiceDeskVO sample) {
        Result<PagedList<ServiceDesk>> result = new Result<>();
        PagedList<ServiceDesk> list = serviceDeskService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }
}
