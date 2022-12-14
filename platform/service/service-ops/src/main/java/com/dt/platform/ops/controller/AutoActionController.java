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
import com.dt.platform.proxy.ops.AutoActionServiceProxy;
import com.dt.platform.domain.ops.meta.AutoActionVOMeta;
import com.dt.platform.domain.ops.AutoAction;
import com.dt.platform.domain.ops.AutoActionVO;
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
import com.dt.platform.domain.ops.meta.AutoActionMeta;
import com.dt.platform.domain.ops.AutoActionScript;
import com.dt.platform.domain.ops.AutoActionFile;
import org.github.foxnic.web.domain.system.DictItem;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.IAutoActionService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-08-23 19:33:17
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("OpsAutoActionController")
public class AutoActionController extends SuperController {

    @Autowired
    private IAutoActionService autoActionService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoActionVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "613756265390145536"),
		@ApiImplicitParam(name = AutoActionVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????1"),
		@ApiImplicitParam(name = AutoActionVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = AutoActionVOMeta.TYPE, value = "??????", required = false, dataTypeClass = String.class, example = "host"),
		@ApiImplicitParam(name = AutoActionVOMeta.TPL_VERSION, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoActionVOMeta.SUPPORT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoActionVOMeta.INFO, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoActionVOMeta.FILE_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoActionVOMeta.NODE_NUMBER_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoActionVOMeta.CONF_CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AutoActionVOMeta.EXAMPLE_CONF_CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoActionVOMeta.EXECUTE_CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AutoActionVOMeta.EXECUTE_TOOL, value = "????????????", required = false, dataTypeClass = String.class, example = "ops_tool"),
		@ApiImplicitParam(name = AutoActionVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "12")
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = AutoActionServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoActionServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(AutoActionVO autoActionVO) {
        Result result = autoActionService.insert(autoActionVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoActionVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "613756265390145536")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = AutoActionServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoActionServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  autoActionService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = autoActionService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoActionVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AutoActionServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoActionServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = autoActionService.hasRefers(ids);
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
            Result result = autoActionService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = autoActionService.deleteByIdsLogical(canDeleteIds);
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
		@ApiImplicitParam(name = AutoActionVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "613756265390145536"),
		@ApiImplicitParam(name = AutoActionVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????1"),
		@ApiImplicitParam(name = AutoActionVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = AutoActionVOMeta.TYPE, value = "??????", required = false, dataTypeClass = String.class, example = "host"),
		@ApiImplicitParam(name = AutoActionVOMeta.TPL_VERSION, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoActionVOMeta.SUPPORT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoActionVOMeta.INFO, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoActionVOMeta.FILE_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoActionVOMeta.NODE_NUMBER_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoActionVOMeta.CONF_CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AutoActionVOMeta.EXAMPLE_CONF_CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoActionVOMeta.EXECUTE_CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AutoActionVOMeta.EXECUTE_TOOL, value = "????????????", required = false, dataTypeClass = String.class, example = "ops_tool"),
		@ApiImplicitParam(name = AutoActionVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "12")
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { AutoActionVOMeta.PAGE_INDEX, AutoActionVOMeta.PAGE_SIZE, AutoActionVOMeta.SEARCH_FIELD, AutoActionVOMeta.FUZZY_FIELD, AutoActionVOMeta.SEARCH_VALUE, AutoActionVOMeta.DIRTY_FIELDS, AutoActionVOMeta.SORT_FIELD, AutoActionVOMeta.SORT_TYPE, AutoActionVOMeta.IDS })
    @SentinelResource(value = AutoActionServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoActionServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(AutoActionVO autoActionVO) {
        Result result = autoActionService.update(autoActionVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoActionVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "613756265390145536"),
		@ApiImplicitParam(name = AutoActionVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????1"),
		@ApiImplicitParam(name = AutoActionVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = AutoActionVOMeta.TYPE, value = "??????", required = false, dataTypeClass = String.class, example = "host"),
		@ApiImplicitParam(name = AutoActionVOMeta.TPL_VERSION, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoActionVOMeta.SUPPORT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoActionVOMeta.INFO, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoActionVOMeta.FILE_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoActionVOMeta.NODE_NUMBER_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoActionVOMeta.CONF_CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AutoActionVOMeta.EXAMPLE_CONF_CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoActionVOMeta.EXECUTE_CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AutoActionVOMeta.EXECUTE_TOOL, value = "????????????", required = false, dataTypeClass = String.class, example = "ops_tool"),
		@ApiImplicitParam(name = AutoActionVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "12")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AutoActionVOMeta.PAGE_INDEX, AutoActionVOMeta.PAGE_SIZE, AutoActionVOMeta.SEARCH_FIELD, AutoActionVOMeta.FUZZY_FIELD, AutoActionVOMeta.SEARCH_VALUE, AutoActionVOMeta.DIRTY_FIELDS, AutoActionVOMeta.SORT_FIELD, AutoActionVOMeta.SORT_TYPE, AutoActionVOMeta.IDS })
    @SentinelResource(value = AutoActionServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoActionServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(AutoActionVO autoActionVO) {
        Result result = autoActionService.save(autoActionVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoActionVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = AutoActionServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoActionServiceProxy.GET_BY_ID)
    public Result<AutoAction> getById(String id) {
        Result<AutoAction> result = new Result<>();
        AutoAction autoAction = autoActionService.getById(id);
        // join ???????????????
        autoActionService.dao().fill(autoAction).with(AutoActionMeta.AUTO_ACTION_SCRIPT_LIST).with(AutoActionMeta.AUTO_ACTION_FILE_LIST).execute();
        result.success(true).data(autoAction);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoActionVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AutoActionServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoActionServiceProxy.GET_BY_IDS)
    public Result<List<AutoAction>> getByIds(List<String> ids) {
        Result<List<AutoAction>> result = new Result<>();
        List<AutoAction> list = autoActionService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoActionVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "613756265390145536"),
		@ApiImplicitParam(name = AutoActionVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????1"),
		@ApiImplicitParam(name = AutoActionVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = AutoActionVOMeta.TYPE, value = "??????", required = false, dataTypeClass = String.class, example = "host"),
		@ApiImplicitParam(name = AutoActionVOMeta.TPL_VERSION, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoActionVOMeta.SUPPORT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoActionVOMeta.INFO, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoActionVOMeta.FILE_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoActionVOMeta.NODE_NUMBER_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoActionVOMeta.CONF_CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AutoActionVOMeta.EXAMPLE_CONF_CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoActionVOMeta.EXECUTE_CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AutoActionVOMeta.EXECUTE_TOOL, value = "????????????", required = false, dataTypeClass = String.class, example = "ops_tool"),
		@ApiImplicitParam(name = AutoActionVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "12")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AutoActionVOMeta.PAGE_INDEX, AutoActionVOMeta.PAGE_SIZE })
    @SentinelResource(value = AutoActionServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoActionServiceProxy.QUERY_LIST)
    public Result<List<AutoAction>> queryList(AutoActionVO sample) {
        Result<List<AutoAction>> result = new Result<>();
        List<AutoAction> list = autoActionService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoActionVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "613756265390145536"),
		@ApiImplicitParam(name = AutoActionVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????1"),
		@ApiImplicitParam(name = AutoActionVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = AutoActionVOMeta.TYPE, value = "??????", required = false, dataTypeClass = String.class, example = "host"),
		@ApiImplicitParam(name = AutoActionVOMeta.TPL_VERSION, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoActionVOMeta.SUPPORT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoActionVOMeta.INFO, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoActionVOMeta.FILE_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoActionVOMeta.NODE_NUMBER_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoActionVOMeta.CONF_CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AutoActionVOMeta.EXAMPLE_CONF_CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoActionVOMeta.EXECUTE_CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AutoActionVOMeta.EXECUTE_TOOL, value = "????????????", required = false, dataTypeClass = String.class, example = "ops_tool"),
		@ApiImplicitParam(name = AutoActionVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "12")
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = AutoActionServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoActionServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<AutoAction>> queryPagedList(AutoActionVO sample) {
        Result<PagedList<AutoAction>> result = new Result<>();
        PagedList<AutoAction> list = autoActionService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        autoActionService.dao().fill(list).with(AutoActionMeta.AUTO_ACTION_SCRIPT_LIST).with(AutoActionMeta.AUTO_ACTION_FILE_LIST).execute();
        result.success(true).data(list);
        return result;
    }
}
