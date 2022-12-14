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
import com.dt.platform.proxy.ops.ServiceCategoryLabelServiceProxy;
import com.dt.platform.domain.ops.meta.ServiceCategoryLabelVOMeta;
import com.dt.platform.domain.ops.ServiceCategoryLabel;
import com.dt.platform.domain.ops.ServiceCategoryLabelVO;
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
import com.dt.platform.domain.ops.meta.ServiceCategoryLabelMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.IServiceCategoryLabelService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-09-10 08:40:52
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("OpsServiceCategoryLabelController")
public class ServiceCategoryLabelController extends SuperController {

    @Autowired
    private IServiceCategoryLabelService serviceCategoryLabelService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServiceCategoryLabelVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceCategoryLabelVOMeta.CATEGORY_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceCategoryLabelVOMeta.LABEL_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = ServiceCategoryLabelServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServiceCategoryLabelServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(ServiceCategoryLabelVO serviceCategoryLabelVO) {
        Result result = serviceCategoryLabelService.insert(serviceCategoryLabelVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServiceCategoryLabelVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = ServiceCategoryLabelServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServiceCategoryLabelServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  serviceCategoryLabelService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = serviceCategoryLabelService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServiceCategoryLabelVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = ServiceCategoryLabelServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServiceCategoryLabelServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = serviceCategoryLabelService.hasRefers(ids);
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
            Result result = serviceCategoryLabelService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = serviceCategoryLabelService.deleteByIdsLogical(canDeleteIds);
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
		@ApiImplicitParam(name = ServiceCategoryLabelVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceCategoryLabelVOMeta.CATEGORY_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceCategoryLabelVOMeta.LABEL_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { ServiceCategoryLabelVOMeta.PAGE_INDEX, ServiceCategoryLabelVOMeta.PAGE_SIZE, ServiceCategoryLabelVOMeta.SEARCH_FIELD, ServiceCategoryLabelVOMeta.FUZZY_FIELD, ServiceCategoryLabelVOMeta.SEARCH_VALUE, ServiceCategoryLabelVOMeta.DIRTY_FIELDS, ServiceCategoryLabelVOMeta.SORT_FIELD, ServiceCategoryLabelVOMeta.SORT_TYPE, ServiceCategoryLabelVOMeta.IDS })
    @SentinelResource(value = ServiceCategoryLabelServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServiceCategoryLabelServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(ServiceCategoryLabelVO serviceCategoryLabelVO) {
        Result result = serviceCategoryLabelService.update(serviceCategoryLabelVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServiceCategoryLabelVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceCategoryLabelVOMeta.CATEGORY_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceCategoryLabelVOMeta.LABEL_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { ServiceCategoryLabelVOMeta.PAGE_INDEX, ServiceCategoryLabelVOMeta.PAGE_SIZE, ServiceCategoryLabelVOMeta.SEARCH_FIELD, ServiceCategoryLabelVOMeta.FUZZY_FIELD, ServiceCategoryLabelVOMeta.SEARCH_VALUE, ServiceCategoryLabelVOMeta.DIRTY_FIELDS, ServiceCategoryLabelVOMeta.SORT_FIELD, ServiceCategoryLabelVOMeta.SORT_TYPE, ServiceCategoryLabelVOMeta.IDS })
    @SentinelResource(value = ServiceCategoryLabelServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServiceCategoryLabelServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(ServiceCategoryLabelVO serviceCategoryLabelVO) {
        Result result = serviceCategoryLabelService.save(serviceCategoryLabelVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServiceCategoryLabelVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = ServiceCategoryLabelServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServiceCategoryLabelServiceProxy.GET_BY_ID)
    public Result<ServiceCategoryLabel> getById(String id) {
        Result<ServiceCategoryLabel> result = new Result<>();
        ServiceCategoryLabel serviceCategoryLabel = serviceCategoryLabelService.getById(id);
        result.success(true).data(serviceCategoryLabel);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServiceCategoryLabelVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = ServiceCategoryLabelServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServiceCategoryLabelServiceProxy.GET_BY_IDS)
    public Result<List<ServiceCategoryLabel>> getByIds(List<String> ids) {
        Result<List<ServiceCategoryLabel>> result = new Result<>();
        List<ServiceCategoryLabel> list = serviceCategoryLabelService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServiceCategoryLabelVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceCategoryLabelVOMeta.CATEGORY_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceCategoryLabelVOMeta.LABEL_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { ServiceCategoryLabelVOMeta.PAGE_INDEX, ServiceCategoryLabelVOMeta.PAGE_SIZE })
    @SentinelResource(value = ServiceCategoryLabelServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServiceCategoryLabelServiceProxy.QUERY_LIST)
    public Result<List<ServiceCategoryLabel>> queryList(ServiceCategoryLabelVO sample) {
        Result<List<ServiceCategoryLabel>> result = new Result<>();
        List<ServiceCategoryLabel> list = serviceCategoryLabelService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServiceCategoryLabelVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceCategoryLabelVOMeta.CATEGORY_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServiceCategoryLabelVOMeta.LABEL_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = ServiceCategoryLabelServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServiceCategoryLabelServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<ServiceCategoryLabel>> queryPagedList(ServiceCategoryLabelVO sample) {
        Result<PagedList<ServiceCategoryLabel>> result = new Result<>();
        PagedList<ServiceCategoryLabel> list = serviceCategoryLabelService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }
}
