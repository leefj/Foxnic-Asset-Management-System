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
import com.dt.platform.proxy.ops.SoftwareBaseTypeServiceProxy;
import com.dt.platform.domain.ops.meta.SoftwareBaseTypeVOMeta;
import com.dt.platform.domain.ops.SoftwareBaseType;
import com.dt.platform.domain.ops.SoftwareBaseTypeVO;
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
import com.dt.platform.domain.ops.meta.SoftwareBaseTypeMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.ISoftwareBaseTypeService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ?????????????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-09-16 08:29:28
 */
@Api(tags = "??????????????????")
@ApiSort(0)
@RestController("OpsSoftwareBaseTypeController")
public class SoftwareBaseTypeController extends SuperController {

    @Autowired
    private ISoftwareBaseTypeService softwareBaseTypeService;

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SoftwareBaseTypeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "593841519329288192"),
		@ApiImplicitParam(name = SoftwareBaseTypeVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = SoftwareBaseTypeVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = SoftwareBaseTypeServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SoftwareBaseTypeServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(SoftwareBaseTypeVO softwareBaseTypeVO) {
        Result result = softwareBaseTypeService.insert(softwareBaseTypeVO, false);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SoftwareBaseTypeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "593841519329288192")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = SoftwareBaseTypeServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SoftwareBaseTypeServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  softwareBaseTypeService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = softwareBaseTypeService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ?????????????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "??????????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SoftwareBaseTypeVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = SoftwareBaseTypeServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SoftwareBaseTypeServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = softwareBaseTypeService.hasRefers(ids);
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
            Result result = softwareBaseTypeService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = softwareBaseTypeService.deleteByIdsLogical(canDeleteIds);
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
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SoftwareBaseTypeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "593841519329288192"),
		@ApiImplicitParam(name = SoftwareBaseTypeVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = SoftwareBaseTypeVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { SoftwareBaseTypeVOMeta.PAGE_INDEX, SoftwareBaseTypeVOMeta.PAGE_SIZE, SoftwareBaseTypeVOMeta.SEARCH_FIELD, SoftwareBaseTypeVOMeta.FUZZY_FIELD, SoftwareBaseTypeVOMeta.SEARCH_VALUE, SoftwareBaseTypeVOMeta.DIRTY_FIELDS, SoftwareBaseTypeVOMeta.SORT_FIELD, SoftwareBaseTypeVOMeta.SORT_TYPE, SoftwareBaseTypeVOMeta.IDS })
    @SentinelResource(value = SoftwareBaseTypeServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SoftwareBaseTypeServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(SoftwareBaseTypeVO softwareBaseTypeVO) {
        Result result = softwareBaseTypeService.update(softwareBaseTypeVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SoftwareBaseTypeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "593841519329288192"),
		@ApiImplicitParam(name = SoftwareBaseTypeVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = SoftwareBaseTypeVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { SoftwareBaseTypeVOMeta.PAGE_INDEX, SoftwareBaseTypeVOMeta.PAGE_SIZE, SoftwareBaseTypeVOMeta.SEARCH_FIELD, SoftwareBaseTypeVOMeta.FUZZY_FIELD, SoftwareBaseTypeVOMeta.SEARCH_VALUE, SoftwareBaseTypeVOMeta.DIRTY_FIELDS, SoftwareBaseTypeVOMeta.SORT_FIELD, SoftwareBaseTypeVOMeta.SORT_TYPE, SoftwareBaseTypeVOMeta.IDS })
    @SentinelResource(value = SoftwareBaseTypeServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SoftwareBaseTypeServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(SoftwareBaseTypeVO softwareBaseTypeVO) {
        Result result = softwareBaseTypeService.save(softwareBaseTypeVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SoftwareBaseTypeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = SoftwareBaseTypeServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SoftwareBaseTypeServiceProxy.GET_BY_ID)
    public Result<SoftwareBaseType> getById(String id) {
        Result<SoftwareBaseType> result = new Result<>();
        SoftwareBaseType softwareBaseType = softwareBaseTypeService.getById(id);
        result.success(true).data(softwareBaseType);
        return result;
    }

    /**
     * ?????????????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "??????????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SoftwareBaseTypeVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = SoftwareBaseTypeServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SoftwareBaseTypeServiceProxy.GET_BY_IDS)
    public Result<List<SoftwareBaseType>> getByIds(List<String> ids) {
        Result<List<SoftwareBaseType>> result = new Result<>();
        List<SoftwareBaseType> list = softwareBaseTypeService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SoftwareBaseTypeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "593841519329288192"),
		@ApiImplicitParam(name = SoftwareBaseTypeVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = SoftwareBaseTypeVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { SoftwareBaseTypeVOMeta.PAGE_INDEX, SoftwareBaseTypeVOMeta.PAGE_SIZE })
    @SentinelResource(value = SoftwareBaseTypeServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SoftwareBaseTypeServiceProxy.QUERY_LIST)
    public Result<List<SoftwareBaseType>> queryList(SoftwareBaseTypeVO sample) {
        Result<List<SoftwareBaseType>> result = new Result<>();
        List<SoftwareBaseType> list = softwareBaseTypeService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????????????????
     */
    @ApiOperation(value = "??????????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SoftwareBaseTypeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "593841519329288192"),
		@ApiImplicitParam(name = SoftwareBaseTypeVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = SoftwareBaseTypeVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = SoftwareBaseTypeServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SoftwareBaseTypeServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<SoftwareBaseType>> queryPagedList(SoftwareBaseTypeVO sample) {
        Result<PagedList<SoftwareBaseType>> result = new Result<>();
        PagedList<SoftwareBaseType> list = softwareBaseTypeService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }
}
