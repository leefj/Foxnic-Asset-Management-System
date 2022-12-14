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
import com.dt.platform.proxy.ops.AutoTaskNodeServiceProxy;
import com.dt.platform.domain.ops.meta.AutoTaskNodeVOMeta;
import com.dt.platform.domain.ops.AutoTaskNode;
import com.dt.platform.domain.ops.AutoTaskNodeVO;
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
import com.dt.platform.domain.ops.meta.AutoTaskNodeMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.IAutoTaskNodeService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-08-22 13:41:33
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("OpsAutoTaskNodeController")
public class AutoTaskNodeController extends SuperController {

    @Autowired
    private IAutoTaskNodeService autoTaskNodeService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoTaskNodeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoTaskNodeVOMeta.TASK_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoTaskNodeVOMeta.NODE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoTaskNodeVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = AutoTaskNodeServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoTaskNodeServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(AutoTaskNodeVO autoTaskNodeVO) {
        Result result = autoTaskNodeService.insert(autoTaskNodeVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoTaskNodeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = AutoTaskNodeServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoTaskNodeServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  autoTaskNodeService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = autoTaskNodeService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoTaskNodeVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AutoTaskNodeServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoTaskNodeServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = autoTaskNodeService.hasRefers(ids);
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
            Result result = autoTaskNodeService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = autoTaskNodeService.deleteByIdsLogical(canDeleteIds);
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
		@ApiImplicitParam(name = AutoTaskNodeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoTaskNodeVOMeta.TASK_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoTaskNodeVOMeta.NODE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoTaskNodeVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { AutoTaskNodeVOMeta.PAGE_INDEX, AutoTaskNodeVOMeta.PAGE_SIZE, AutoTaskNodeVOMeta.SEARCH_FIELD, AutoTaskNodeVOMeta.FUZZY_FIELD, AutoTaskNodeVOMeta.SEARCH_VALUE, AutoTaskNodeVOMeta.DIRTY_FIELDS, AutoTaskNodeVOMeta.SORT_FIELD, AutoTaskNodeVOMeta.SORT_TYPE, AutoTaskNodeVOMeta.IDS })
    @SentinelResource(value = AutoTaskNodeServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoTaskNodeServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(AutoTaskNodeVO autoTaskNodeVO) {
        Result result = autoTaskNodeService.update(autoTaskNodeVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoTaskNodeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoTaskNodeVOMeta.TASK_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoTaskNodeVOMeta.NODE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoTaskNodeVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AutoTaskNodeVOMeta.PAGE_INDEX, AutoTaskNodeVOMeta.PAGE_SIZE, AutoTaskNodeVOMeta.SEARCH_FIELD, AutoTaskNodeVOMeta.FUZZY_FIELD, AutoTaskNodeVOMeta.SEARCH_VALUE, AutoTaskNodeVOMeta.DIRTY_FIELDS, AutoTaskNodeVOMeta.SORT_FIELD, AutoTaskNodeVOMeta.SORT_TYPE, AutoTaskNodeVOMeta.IDS })
    @SentinelResource(value = AutoTaskNodeServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoTaskNodeServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(AutoTaskNodeVO autoTaskNodeVO) {
        Result result = autoTaskNodeService.save(autoTaskNodeVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoTaskNodeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = AutoTaskNodeServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoTaskNodeServiceProxy.GET_BY_ID)
    public Result<AutoTaskNode> getById(String id) {
        Result<AutoTaskNode> result = new Result<>();
        AutoTaskNode autoTaskNode = autoTaskNodeService.getById(id);
        result.success(true).data(autoTaskNode);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoTaskNodeVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AutoTaskNodeServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoTaskNodeServiceProxy.GET_BY_IDS)
    public Result<List<AutoTaskNode>> getByIds(List<String> ids) {
        Result<List<AutoTaskNode>> result = new Result<>();
        List<AutoTaskNode> list = autoTaskNodeService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoTaskNodeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoTaskNodeVOMeta.TASK_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoTaskNodeVOMeta.NODE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoTaskNodeVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AutoTaskNodeVOMeta.PAGE_INDEX, AutoTaskNodeVOMeta.PAGE_SIZE })
    @SentinelResource(value = AutoTaskNodeServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoTaskNodeServiceProxy.QUERY_LIST)
    public Result<List<AutoTaskNode>> queryList(AutoTaskNodeVO sample) {
        Result<List<AutoTaskNode>> result = new Result<>();
        List<AutoTaskNode> list = autoTaskNodeService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoTaskNodeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoTaskNodeVOMeta.TASK_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoTaskNodeVOMeta.NODE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoTaskNodeVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = AutoTaskNodeServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoTaskNodeServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<AutoTaskNode>> queryPagedList(AutoTaskNodeVO sample) {
        Result<PagedList<AutoTaskNode>> result = new Result<>();
        PagedList<AutoTaskNode> list = autoTaskNodeService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }
}
