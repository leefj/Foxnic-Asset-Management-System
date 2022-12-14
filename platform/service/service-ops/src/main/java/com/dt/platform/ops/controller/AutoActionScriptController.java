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
import com.dt.platform.proxy.ops.AutoActionScriptServiceProxy;
import com.dt.platform.domain.ops.meta.AutoActionScriptVOMeta;
import com.dt.platform.domain.ops.AutoActionScript;
import com.dt.platform.domain.ops.AutoActionScriptVO;
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
import com.dt.platform.domain.ops.meta.AutoActionScriptMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.IAutoActionScriptService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-08-22 12:51:30
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("OpsAutoActionScriptController")
public class AutoActionScriptController extends SuperController {

    @Autowired
    private IAutoActionScriptService autoActionScriptService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoActionScriptVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "613733471919013888"),
		@ApiImplicitParam(name = AutoActionScriptVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AutoActionScriptVOMeta.FILE_NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoActionScriptVOMeta.FILE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "613733462595076097"),
		@ApiImplicitParam(name = AutoActionScriptVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = AutoActionScriptServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoActionScriptServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(AutoActionScriptVO autoActionScriptVO) {
        Result result = autoActionScriptService.insert(autoActionScriptVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoActionScriptVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "613733471919013888")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = AutoActionScriptServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoActionScriptServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  autoActionScriptService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = autoActionScriptService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoActionScriptVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AutoActionScriptServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoActionScriptServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = autoActionScriptService.hasRefers(ids);
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
            Result result = autoActionScriptService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = autoActionScriptService.deleteByIdsLogical(canDeleteIds);
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
		@ApiImplicitParam(name = AutoActionScriptVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "613733471919013888"),
		@ApiImplicitParam(name = AutoActionScriptVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AutoActionScriptVOMeta.FILE_NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoActionScriptVOMeta.FILE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "613733462595076097"),
		@ApiImplicitParam(name = AutoActionScriptVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { AutoActionScriptVOMeta.PAGE_INDEX, AutoActionScriptVOMeta.PAGE_SIZE, AutoActionScriptVOMeta.SEARCH_FIELD, AutoActionScriptVOMeta.FUZZY_FIELD, AutoActionScriptVOMeta.SEARCH_VALUE, AutoActionScriptVOMeta.DIRTY_FIELDS, AutoActionScriptVOMeta.SORT_FIELD, AutoActionScriptVOMeta.SORT_TYPE, AutoActionScriptVOMeta.IDS })
    @SentinelResource(value = AutoActionScriptServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoActionScriptServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(AutoActionScriptVO autoActionScriptVO) {
        Result result = autoActionScriptService.update(autoActionScriptVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoActionScriptVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "613733471919013888"),
		@ApiImplicitParam(name = AutoActionScriptVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AutoActionScriptVOMeta.FILE_NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoActionScriptVOMeta.FILE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "613733462595076097"),
		@ApiImplicitParam(name = AutoActionScriptVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AutoActionScriptVOMeta.PAGE_INDEX, AutoActionScriptVOMeta.PAGE_SIZE, AutoActionScriptVOMeta.SEARCH_FIELD, AutoActionScriptVOMeta.FUZZY_FIELD, AutoActionScriptVOMeta.SEARCH_VALUE, AutoActionScriptVOMeta.DIRTY_FIELDS, AutoActionScriptVOMeta.SORT_FIELD, AutoActionScriptVOMeta.SORT_TYPE, AutoActionScriptVOMeta.IDS })
    @SentinelResource(value = AutoActionScriptServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoActionScriptServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(AutoActionScriptVO autoActionScriptVO) {
        Result result = autoActionScriptService.save(autoActionScriptVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoActionScriptVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = AutoActionScriptServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoActionScriptServiceProxy.GET_BY_ID)
    public Result<AutoActionScript> getById(String id) {
        Result<AutoActionScript> result = new Result<>();
        AutoActionScript autoActionScript = autoActionScriptService.getById(id);
        result.success(true).data(autoActionScript);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoActionScriptVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AutoActionScriptServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoActionScriptServiceProxy.GET_BY_IDS)
    public Result<List<AutoActionScript>> getByIds(List<String> ids) {
        Result<List<AutoActionScript>> result = new Result<>();
        List<AutoActionScript> list = autoActionScriptService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoActionScriptVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "613733471919013888"),
		@ApiImplicitParam(name = AutoActionScriptVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AutoActionScriptVOMeta.FILE_NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoActionScriptVOMeta.FILE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "613733462595076097"),
		@ApiImplicitParam(name = AutoActionScriptVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AutoActionScriptVOMeta.PAGE_INDEX, AutoActionScriptVOMeta.PAGE_SIZE })
    @SentinelResource(value = AutoActionScriptServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoActionScriptServiceProxy.QUERY_LIST)
    public Result<List<AutoActionScript>> queryList(AutoActionScriptVO sample) {
        Result<List<AutoActionScript>> result = new Result<>();
        List<AutoActionScript> list = autoActionScriptService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoActionScriptVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "613733471919013888"),
		@ApiImplicitParam(name = AutoActionScriptVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AutoActionScriptVOMeta.FILE_NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoActionScriptVOMeta.FILE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "613733462595076097"),
		@ApiImplicitParam(name = AutoActionScriptVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = AutoActionScriptServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoActionScriptServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<AutoActionScript>> queryPagedList(AutoActionScriptVO sample) {
        Result<PagedList<AutoActionScript>> result = new Result<>();
        PagedList<AutoActionScript> list = autoActionScriptService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }
}
