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
import com.dt.platform.proxy.ops.ServiceCategoryServiceProxy;
import com.dt.platform.domain.ops.meta.ServiceCategoryVOMeta;
import com.dt.platform.domain.ops.ServiceCategory;
import com.dt.platform.domain.ops.ServiceCategoryVO;
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
import com.dt.platform.domain.ops.meta.ServiceCategoryMeta;
import com.dt.platform.domain.ops.ServiceGroup;
import org.github.foxnic.web.domain.system.DictItem;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.IServiceCategoryService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-09-13 06:31:54
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("OpsServiceCategoryController")
public class ServiceCategoryController extends SuperController {

    @Autowired
    private IServiceCategoryService serviceCategoryService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServiceCategoryVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "473621482614816700"),
		@ApiImplicitParam(name = ServiceCategoryVOMeta.GROUP_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "db"),
		@ApiImplicitParam(name = ServiceCategoryVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "Oracle"),
		@ApiImplicitParam(name = ServiceCategoryVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "Oracle"),
		@ApiImplicitParam(name = ServiceCategoryVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = ServiceCategoryServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServiceCategoryServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(ServiceCategoryVO serviceCategoryVO) {
        Result result = serviceCategoryService.insert(serviceCategoryVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServiceCategoryVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "473621482614816700")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = ServiceCategoryServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServiceCategoryServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  serviceCategoryService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = serviceCategoryService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServiceCategoryVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = ServiceCategoryServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServiceCategoryServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = serviceCategoryService.hasRefers(ids);
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
            Result result = serviceCategoryService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = serviceCategoryService.deleteByIdsLogical(canDeleteIds);
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
		@ApiImplicitParam(name = ServiceCategoryVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "473621482614816700"),
		@ApiImplicitParam(name = ServiceCategoryVOMeta.GROUP_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "db"),
		@ApiImplicitParam(name = ServiceCategoryVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "Oracle"),
		@ApiImplicitParam(name = ServiceCategoryVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "Oracle"),
		@ApiImplicitParam(name = ServiceCategoryVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { ServiceCategoryVOMeta.PAGE_INDEX, ServiceCategoryVOMeta.PAGE_SIZE, ServiceCategoryVOMeta.SEARCH_FIELD, ServiceCategoryVOMeta.FUZZY_FIELD, ServiceCategoryVOMeta.SEARCH_VALUE, ServiceCategoryVOMeta.DIRTY_FIELDS, ServiceCategoryVOMeta.SORT_FIELD, ServiceCategoryVOMeta.SORT_TYPE, ServiceCategoryVOMeta.IDS })
    @SentinelResource(value = ServiceCategoryServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServiceCategoryServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(ServiceCategoryVO serviceCategoryVO) {
        Result result = serviceCategoryService.update(serviceCategoryVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServiceCategoryVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "473621482614816700"),
		@ApiImplicitParam(name = ServiceCategoryVOMeta.GROUP_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "db"),
		@ApiImplicitParam(name = ServiceCategoryVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "Oracle"),
		@ApiImplicitParam(name = ServiceCategoryVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "Oracle"),
		@ApiImplicitParam(name = ServiceCategoryVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { ServiceCategoryVOMeta.PAGE_INDEX, ServiceCategoryVOMeta.PAGE_SIZE, ServiceCategoryVOMeta.SEARCH_FIELD, ServiceCategoryVOMeta.FUZZY_FIELD, ServiceCategoryVOMeta.SEARCH_VALUE, ServiceCategoryVOMeta.DIRTY_FIELDS, ServiceCategoryVOMeta.SORT_FIELD, ServiceCategoryVOMeta.SORT_TYPE, ServiceCategoryVOMeta.IDS })
    @SentinelResource(value = ServiceCategoryServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServiceCategoryServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(ServiceCategoryVO serviceCategoryVO) {
        Result result = serviceCategoryService.save(serviceCategoryVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServiceCategoryVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = ServiceCategoryServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServiceCategoryServiceProxy.GET_BY_ID)
    public Result<ServiceCategory> getById(String id) {
        Result<ServiceCategory> result = new Result<>();
        ServiceCategory serviceCategory = serviceCategoryService.getById(id);
        // join ???????????????
        serviceCategoryService.dao().fill(serviceCategory).with(ServiceCategoryMeta.GROUP).with(ServiceCategoryMeta.LABEL_LIST).execute();
        result.success(true).data(serviceCategory);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServiceCategoryVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = ServiceCategoryServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServiceCategoryServiceProxy.GET_BY_IDS)
    public Result<List<ServiceCategory>> getByIds(List<String> ids) {
        Result<List<ServiceCategory>> result = new Result<>();
        List<ServiceCategory> list = serviceCategoryService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServiceCategoryVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "473621482614816700"),
		@ApiImplicitParam(name = ServiceCategoryVOMeta.GROUP_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "db"),
		@ApiImplicitParam(name = ServiceCategoryVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "Oracle"),
		@ApiImplicitParam(name = ServiceCategoryVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "Oracle"),
		@ApiImplicitParam(name = ServiceCategoryVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { ServiceCategoryVOMeta.PAGE_INDEX, ServiceCategoryVOMeta.PAGE_SIZE })
    @SentinelResource(value = ServiceCategoryServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServiceCategoryServiceProxy.QUERY_LIST)
    public Result<List<ServiceCategory>> queryList(ServiceCategoryVO sample) {
        Result<List<ServiceCategory>> result = new Result<>();
        List<ServiceCategory> list = serviceCategoryService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServiceCategoryVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "473621482614816700"),
		@ApiImplicitParam(name = ServiceCategoryVOMeta.GROUP_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "db"),
		@ApiImplicitParam(name = ServiceCategoryVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "Oracle"),
		@ApiImplicitParam(name = ServiceCategoryVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "Oracle"),
		@ApiImplicitParam(name = ServiceCategoryVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = ServiceCategoryServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServiceCategoryServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<ServiceCategory>> queryPagedList(ServiceCategoryVO sample) {
        Result<PagedList<ServiceCategory>> result = new Result<>();
        PagedList<ServiceCategory> list = serviceCategoryService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        serviceCategoryService.dao().fill(list).with(ServiceCategoryMeta.GROUP).with(ServiceCategoryMeta.LABEL_LIST).execute();
        result.success(true).data(list);
        return result;
    }
}
