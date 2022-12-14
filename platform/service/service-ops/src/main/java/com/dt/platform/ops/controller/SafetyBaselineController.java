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
import com.dt.platform.proxy.ops.SafetyBaselineServiceProxy;
import com.dt.platform.domain.ops.meta.SafetyBaselineVOMeta;
import com.dt.platform.domain.ops.SafetyBaseline;
import com.dt.platform.domain.ops.SafetyBaselineVO;
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
import com.dt.platform.domain.ops.meta.SafetyBaselineMeta;
import com.dt.platform.domain.ops.SoftwareBaseType;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.ISafetyBaselineService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-09-16 08:37:37
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("OpsSafetyBaselineController")
public class SafetyBaselineController extends SuperController {

    @Autowired
    private ISafetyBaselineService safetyBaselineService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SafetyBaselineVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "623074744979161088"),
		@ApiImplicitParam(name = SafetyBaselineVOMeta.BASE_TYPE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "593841519329288192"),
		@ApiImplicitParam(name = SafetyBaselineVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = SafetyBaselineVOMeta.BASE_VERSION, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = SafetyBaselineVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "effect"),
		@ApiImplicitParam(name = SafetyBaselineVOMeta.USAGE_SCENARIOS, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = SafetyBaselineVOMeta.FILE_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "623074709520515072"),
		@ApiImplicitParam(name = SafetyBaselineVOMeta.CHECK_FILE_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "623074721516224512"),
		@ApiImplicitParam(name = SafetyBaselineVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1212")
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = SafetyBaselineServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SafetyBaselineServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(SafetyBaselineVO safetyBaselineVO) {
        Result result = safetyBaselineService.insert(safetyBaselineVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SafetyBaselineVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "623074744979161088")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = SafetyBaselineServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SafetyBaselineServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  safetyBaselineService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = safetyBaselineService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SafetyBaselineVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = SafetyBaselineServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SafetyBaselineServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = safetyBaselineService.hasRefers(ids);
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
            Result result = safetyBaselineService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = safetyBaselineService.deleteByIdsLogical(canDeleteIds);
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
		@ApiImplicitParam(name = SafetyBaselineVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "623074744979161088"),
		@ApiImplicitParam(name = SafetyBaselineVOMeta.BASE_TYPE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "593841519329288192"),
		@ApiImplicitParam(name = SafetyBaselineVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = SafetyBaselineVOMeta.BASE_VERSION, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = SafetyBaselineVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "effect"),
		@ApiImplicitParam(name = SafetyBaselineVOMeta.USAGE_SCENARIOS, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = SafetyBaselineVOMeta.FILE_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "623074709520515072"),
		@ApiImplicitParam(name = SafetyBaselineVOMeta.CHECK_FILE_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "623074721516224512"),
		@ApiImplicitParam(name = SafetyBaselineVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1212")
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { SafetyBaselineVOMeta.PAGE_INDEX, SafetyBaselineVOMeta.PAGE_SIZE, SafetyBaselineVOMeta.SEARCH_FIELD, SafetyBaselineVOMeta.FUZZY_FIELD, SafetyBaselineVOMeta.SEARCH_VALUE, SafetyBaselineVOMeta.DIRTY_FIELDS, SafetyBaselineVOMeta.SORT_FIELD, SafetyBaselineVOMeta.SORT_TYPE, SafetyBaselineVOMeta.IDS })
    @SentinelResource(value = SafetyBaselineServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SafetyBaselineServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(SafetyBaselineVO safetyBaselineVO) {
        Result result = safetyBaselineService.update(safetyBaselineVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SafetyBaselineVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "623074744979161088"),
		@ApiImplicitParam(name = SafetyBaselineVOMeta.BASE_TYPE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "593841519329288192"),
		@ApiImplicitParam(name = SafetyBaselineVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = SafetyBaselineVOMeta.BASE_VERSION, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = SafetyBaselineVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "effect"),
		@ApiImplicitParam(name = SafetyBaselineVOMeta.USAGE_SCENARIOS, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = SafetyBaselineVOMeta.FILE_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "623074709520515072"),
		@ApiImplicitParam(name = SafetyBaselineVOMeta.CHECK_FILE_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "623074721516224512"),
		@ApiImplicitParam(name = SafetyBaselineVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1212")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { SafetyBaselineVOMeta.PAGE_INDEX, SafetyBaselineVOMeta.PAGE_SIZE, SafetyBaselineVOMeta.SEARCH_FIELD, SafetyBaselineVOMeta.FUZZY_FIELD, SafetyBaselineVOMeta.SEARCH_VALUE, SafetyBaselineVOMeta.DIRTY_FIELDS, SafetyBaselineVOMeta.SORT_FIELD, SafetyBaselineVOMeta.SORT_TYPE, SafetyBaselineVOMeta.IDS })
    @SentinelResource(value = SafetyBaselineServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SafetyBaselineServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(SafetyBaselineVO safetyBaselineVO) {
        Result result = safetyBaselineService.save(safetyBaselineVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SafetyBaselineVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = SafetyBaselineServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SafetyBaselineServiceProxy.GET_BY_ID)
    public Result<SafetyBaseline> getById(String id) {
        Result<SafetyBaseline> result = new Result<>();
        SafetyBaseline safetyBaseline = safetyBaselineService.getById(id);
        // join ???????????????
        safetyBaselineService.dao().fill(safetyBaseline).with(SafetyBaselineMeta.BASE_TYPE).execute();
        result.success(true).data(safetyBaseline);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SafetyBaselineVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = SafetyBaselineServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SafetyBaselineServiceProxy.GET_BY_IDS)
    public Result<List<SafetyBaseline>> getByIds(List<String> ids) {
        Result<List<SafetyBaseline>> result = new Result<>();
        List<SafetyBaseline> list = safetyBaselineService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SafetyBaselineVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "623074744979161088"),
		@ApiImplicitParam(name = SafetyBaselineVOMeta.BASE_TYPE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "593841519329288192"),
		@ApiImplicitParam(name = SafetyBaselineVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = SafetyBaselineVOMeta.BASE_VERSION, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = SafetyBaselineVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "effect"),
		@ApiImplicitParam(name = SafetyBaselineVOMeta.USAGE_SCENARIOS, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = SafetyBaselineVOMeta.FILE_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "623074709520515072"),
		@ApiImplicitParam(name = SafetyBaselineVOMeta.CHECK_FILE_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "623074721516224512"),
		@ApiImplicitParam(name = SafetyBaselineVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1212")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { SafetyBaselineVOMeta.PAGE_INDEX, SafetyBaselineVOMeta.PAGE_SIZE })
    @SentinelResource(value = SafetyBaselineServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SafetyBaselineServiceProxy.QUERY_LIST)
    public Result<List<SafetyBaseline>> queryList(SafetyBaselineVO sample) {
        Result<List<SafetyBaseline>> result = new Result<>();
        List<SafetyBaseline> list = safetyBaselineService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SafetyBaselineVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "623074744979161088"),
		@ApiImplicitParam(name = SafetyBaselineVOMeta.BASE_TYPE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "593841519329288192"),
		@ApiImplicitParam(name = SafetyBaselineVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = SafetyBaselineVOMeta.BASE_VERSION, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = SafetyBaselineVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "effect"),
		@ApiImplicitParam(name = SafetyBaselineVOMeta.USAGE_SCENARIOS, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = SafetyBaselineVOMeta.FILE_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "623074709520515072"),
		@ApiImplicitParam(name = SafetyBaselineVOMeta.CHECK_FILE_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "623074721516224512"),
		@ApiImplicitParam(name = SafetyBaselineVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1212")
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = SafetyBaselineServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SafetyBaselineServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<SafetyBaseline>> queryPagedList(SafetyBaselineVO sample) {
        Result<PagedList<SafetyBaseline>> result = new Result<>();
        PagedList<SafetyBaseline> list = safetyBaselineService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        safetyBaselineService.dao().fill(list).with(SafetyBaselineMeta.BASE_TYPE).execute();
        result.success(true).data(list);
        return result;
    }
}
