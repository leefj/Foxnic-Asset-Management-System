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
import com.dt.platform.proxy.workorder.ServerOsTypeServiceProxy;
import com.dt.platform.domain.workorder.meta.ServerOsTypeVOMeta;
import com.dt.platform.domain.workorder.ServerOsType;
import com.dt.platform.domain.workorder.ServerOsTypeVO;
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
import com.dt.platform.domain.workorder.meta.ServerOsTypeMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.workorder.service.IServerOsTypeService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-07-13 14:56:12
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("WoServerOsTypeController")
public class ServerOsTypeController extends SuperController {

    @Autowired
    private IServerOsTypeService serverOsTypeService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServerOsTypeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "596634761368174592"),
		@ApiImplicitParam(name = ServerOsTypeVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "RedHat 7.9 "),
		@ApiImplicitParam(name = ServerOsTypeVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServerOsTypeVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class, example = "1")
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = ServerOsTypeServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServerOsTypeServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(ServerOsTypeVO serverOsTypeVO) {
        Result result = serverOsTypeService.insert(serverOsTypeVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServerOsTypeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "596634761368174592")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = ServerOsTypeServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServerOsTypeServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  serverOsTypeService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = serverOsTypeService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServerOsTypeVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = ServerOsTypeServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServerOsTypeServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = serverOsTypeService.hasRefers(ids);
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
            Result result = serverOsTypeService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = serverOsTypeService.deleteByIdsLogical(canDeleteIds);
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
		@ApiImplicitParam(name = ServerOsTypeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "596634761368174592"),
		@ApiImplicitParam(name = ServerOsTypeVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "RedHat 7.9 "),
		@ApiImplicitParam(name = ServerOsTypeVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServerOsTypeVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class, example = "1")
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { ServerOsTypeVOMeta.PAGE_INDEX, ServerOsTypeVOMeta.PAGE_SIZE, ServerOsTypeVOMeta.SEARCH_FIELD, ServerOsTypeVOMeta.FUZZY_FIELD, ServerOsTypeVOMeta.SEARCH_VALUE, ServerOsTypeVOMeta.DIRTY_FIELDS, ServerOsTypeVOMeta.SORT_FIELD, ServerOsTypeVOMeta.SORT_TYPE, ServerOsTypeVOMeta.IDS })
    @SentinelResource(value = ServerOsTypeServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServerOsTypeServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(ServerOsTypeVO serverOsTypeVO) {
        Result result = serverOsTypeService.update(serverOsTypeVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServerOsTypeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "596634761368174592"),
		@ApiImplicitParam(name = ServerOsTypeVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "RedHat 7.9 "),
		@ApiImplicitParam(name = ServerOsTypeVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServerOsTypeVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class, example = "1")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { ServerOsTypeVOMeta.PAGE_INDEX, ServerOsTypeVOMeta.PAGE_SIZE, ServerOsTypeVOMeta.SEARCH_FIELD, ServerOsTypeVOMeta.FUZZY_FIELD, ServerOsTypeVOMeta.SEARCH_VALUE, ServerOsTypeVOMeta.DIRTY_FIELDS, ServerOsTypeVOMeta.SORT_FIELD, ServerOsTypeVOMeta.SORT_TYPE, ServerOsTypeVOMeta.IDS })
    @SentinelResource(value = ServerOsTypeServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServerOsTypeServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(ServerOsTypeVO serverOsTypeVO) {
        Result result = serverOsTypeService.save(serverOsTypeVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServerOsTypeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = ServerOsTypeServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServerOsTypeServiceProxy.GET_BY_ID)
    public Result<ServerOsType> getById(String id) {
        Result<ServerOsType> result = new Result<>();
        ServerOsType serverOsType = serverOsTypeService.getById(id);
        result.success(true).data(serverOsType);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServerOsTypeVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = ServerOsTypeServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServerOsTypeServiceProxy.GET_BY_IDS)
    public Result<List<ServerOsType>> getByIds(List<String> ids) {
        Result<List<ServerOsType>> result = new Result<>();
        List<ServerOsType> list = serverOsTypeService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServerOsTypeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "596634761368174592"),
		@ApiImplicitParam(name = ServerOsTypeVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "RedHat 7.9 "),
		@ApiImplicitParam(name = ServerOsTypeVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServerOsTypeVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class, example = "1")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { ServerOsTypeVOMeta.PAGE_INDEX, ServerOsTypeVOMeta.PAGE_SIZE })
    @SentinelResource(value = ServerOsTypeServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServerOsTypeServiceProxy.QUERY_LIST)
    public Result<List<ServerOsType>> queryList(ServerOsTypeVO sample) {
        Result<List<ServerOsType>> result = new Result<>();
        List<ServerOsType> list = serverOsTypeService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServerOsTypeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "596634761368174592"),
		@ApiImplicitParam(name = ServerOsTypeVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "RedHat 7.9 "),
		@ApiImplicitParam(name = ServerOsTypeVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServerOsTypeVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class, example = "1")
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = ServerOsTypeServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServerOsTypeServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<ServerOsType>> queryPagedList(ServerOsTypeVO sample) {
        Result<PagedList<ServerOsType>> result = new Result<>();
        PagedList<ServerOsType> list = serverOsTypeService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }
}
