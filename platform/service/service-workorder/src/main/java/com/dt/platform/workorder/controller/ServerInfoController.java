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
import com.dt.platform.proxy.workorder.ServerInfoServiceProxy;
import com.dt.platform.domain.workorder.meta.ServerInfoVOMeta;
import com.dt.platform.domain.workorder.ServerInfo;
import com.dt.platform.domain.workorder.ServerInfoVO;
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
import com.dt.platform.domain.workorder.meta.ServerInfoMeta;
import com.dt.platform.domain.workorder.ServerOsType;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.workorder.service.IServerInfoService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ??????????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-07-22 06:38:45
 */
@Api(tags = "???????????????")
@ApiSort(0)
@RestController("WoServerInfoController")
public class ServerInfoController extends SuperController {

    @Autowired
    private IServerInfoService serverInfoService;

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServerInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "602750624248893440"),
		@ApiImplicitParam(name = ServerInfoVOMeta.OWNER_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServerInfoVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "???????????????"),
		@ApiImplicitParam(name = ServerInfoVOMeta.SERVER_TYPE, value = "???????????????", required = false, dataTypeClass = String.class, example = "virtual_machine"),
		@ApiImplicitParam(name = ServerInfoVOMeta.OS_VERSION, value = "????????????", required = false, dataTypeClass = String.class, example = "599614904982306816"),
		@ApiImplicitParam(name = ServerInfoVOMeta.IP, value = "IP??????", required = false, dataTypeClass = String.class, example = "192,268,1,1"),
		@ApiImplicitParam(name = ServerInfoVOMeta.DATABASE_VERSION, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServerInfoVOMeta.MIDDLEWARE_VERSION, value = "?????????", required = false, dataTypeClass = String.class, example = "Tomcat"),
		@ApiImplicitParam(name = ServerInfoVOMeta.CPU, value = "CPU???", required = false, dataTypeClass = Integer.class, example = "2"),
		@ApiImplicitParam(name = ServerInfoVOMeta.MEMORY, value = "??????(G)", required = false, dataTypeClass = Integer.class, example = "4"),
		@ApiImplicitParam(name = ServerInfoVOMeta.SPACE_SIZE, value = "??????(G)", required = false, dataTypeClass = Integer.class, example = "100"),
		@ApiImplicitParam(name = ServerInfoVOMeta.SERVER_NUMBER, value = "??????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = ServerInfoVOMeta.USERLIST, value = "????????????", required = false, dataTypeClass = String.class, example = "root"),
		@ApiImplicitParam(name = ServerInfoVOMeta.PORTLIST, value = "????????????", required = false, dataTypeClass = String.class, example = "8080"),
		@ApiImplicitParam(name = ServerInfoVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServerInfoVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = ServerInfoServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServerInfoServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(ServerInfoVO serverInfoVO) {
        Result result = serverInfoService.insert(serverInfoVO, false);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServerInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "602750624248893440")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = ServerInfoServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServerInfoServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  serverInfoService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = serverInfoService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ??????????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "???????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServerInfoVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = ServerInfoServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServerInfoServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = serverInfoService.hasRefers(ids);
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
            Result result = serverInfoService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = serverInfoService.deleteByIdsLogical(canDeleteIds);
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
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServerInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "602750624248893440"),
		@ApiImplicitParam(name = ServerInfoVOMeta.OWNER_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServerInfoVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "???????????????"),
		@ApiImplicitParam(name = ServerInfoVOMeta.SERVER_TYPE, value = "???????????????", required = false, dataTypeClass = String.class, example = "virtual_machine"),
		@ApiImplicitParam(name = ServerInfoVOMeta.OS_VERSION, value = "????????????", required = false, dataTypeClass = String.class, example = "599614904982306816"),
		@ApiImplicitParam(name = ServerInfoVOMeta.IP, value = "IP??????", required = false, dataTypeClass = String.class, example = "192,268,1,1"),
		@ApiImplicitParam(name = ServerInfoVOMeta.DATABASE_VERSION, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServerInfoVOMeta.MIDDLEWARE_VERSION, value = "?????????", required = false, dataTypeClass = String.class, example = "Tomcat"),
		@ApiImplicitParam(name = ServerInfoVOMeta.CPU, value = "CPU???", required = false, dataTypeClass = Integer.class, example = "2"),
		@ApiImplicitParam(name = ServerInfoVOMeta.MEMORY, value = "??????(G)", required = false, dataTypeClass = Integer.class, example = "4"),
		@ApiImplicitParam(name = ServerInfoVOMeta.SPACE_SIZE, value = "??????(G)", required = false, dataTypeClass = Integer.class, example = "100"),
		@ApiImplicitParam(name = ServerInfoVOMeta.SERVER_NUMBER, value = "??????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = ServerInfoVOMeta.USERLIST, value = "????????????", required = false, dataTypeClass = String.class, example = "root"),
		@ApiImplicitParam(name = ServerInfoVOMeta.PORTLIST, value = "????????????", required = false, dataTypeClass = String.class, example = "8080"),
		@ApiImplicitParam(name = ServerInfoVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServerInfoVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { ServerInfoVOMeta.PAGE_INDEX, ServerInfoVOMeta.PAGE_SIZE, ServerInfoVOMeta.SEARCH_FIELD, ServerInfoVOMeta.FUZZY_FIELD, ServerInfoVOMeta.SEARCH_VALUE, ServerInfoVOMeta.DIRTY_FIELDS, ServerInfoVOMeta.SORT_FIELD, ServerInfoVOMeta.SORT_TYPE, ServerInfoVOMeta.IDS })
    @SentinelResource(value = ServerInfoServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServerInfoServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(ServerInfoVO serverInfoVO) {
        Result result = serverInfoService.update(serverInfoVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServerInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "602750624248893440"),
		@ApiImplicitParam(name = ServerInfoVOMeta.OWNER_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServerInfoVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "???????????????"),
		@ApiImplicitParam(name = ServerInfoVOMeta.SERVER_TYPE, value = "???????????????", required = false, dataTypeClass = String.class, example = "virtual_machine"),
		@ApiImplicitParam(name = ServerInfoVOMeta.OS_VERSION, value = "????????????", required = false, dataTypeClass = String.class, example = "599614904982306816"),
		@ApiImplicitParam(name = ServerInfoVOMeta.IP, value = "IP??????", required = false, dataTypeClass = String.class, example = "192,268,1,1"),
		@ApiImplicitParam(name = ServerInfoVOMeta.DATABASE_VERSION, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServerInfoVOMeta.MIDDLEWARE_VERSION, value = "?????????", required = false, dataTypeClass = String.class, example = "Tomcat"),
		@ApiImplicitParam(name = ServerInfoVOMeta.CPU, value = "CPU???", required = false, dataTypeClass = Integer.class, example = "2"),
		@ApiImplicitParam(name = ServerInfoVOMeta.MEMORY, value = "??????(G)", required = false, dataTypeClass = Integer.class, example = "4"),
		@ApiImplicitParam(name = ServerInfoVOMeta.SPACE_SIZE, value = "??????(G)", required = false, dataTypeClass = Integer.class, example = "100"),
		@ApiImplicitParam(name = ServerInfoVOMeta.SERVER_NUMBER, value = "??????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = ServerInfoVOMeta.USERLIST, value = "????????????", required = false, dataTypeClass = String.class, example = "root"),
		@ApiImplicitParam(name = ServerInfoVOMeta.PORTLIST, value = "????????????", required = false, dataTypeClass = String.class, example = "8080"),
		@ApiImplicitParam(name = ServerInfoVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServerInfoVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { ServerInfoVOMeta.PAGE_INDEX, ServerInfoVOMeta.PAGE_SIZE, ServerInfoVOMeta.SEARCH_FIELD, ServerInfoVOMeta.FUZZY_FIELD, ServerInfoVOMeta.SEARCH_VALUE, ServerInfoVOMeta.DIRTY_FIELDS, ServerInfoVOMeta.SORT_FIELD, ServerInfoVOMeta.SORT_TYPE, ServerInfoVOMeta.IDS })
    @SentinelResource(value = ServerInfoServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServerInfoServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(ServerInfoVO serverInfoVO) {
        Result result = serverInfoService.save(serverInfoVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServerInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = ServerInfoServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServerInfoServiceProxy.GET_BY_ID)
    public Result<ServerInfo> getById(String id) {
        Result<ServerInfo> result = new Result<>();
        ServerInfo serverInfo = serverInfoService.getById(id);
        // join ???????????????
        serverInfoService.dao().fill(serverInfo).with(ServerInfoMeta.SERVER_OS_TYPE).execute();
        result.success(true).data(serverInfo);
        return result;
    }

    /**
     * ??????????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "???????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServerInfoVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = ServerInfoServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServerInfoServiceProxy.GET_BY_IDS)
    public Result<List<ServerInfo>> getByIds(List<String> ids) {
        Result<List<ServerInfo>> result = new Result<>();
        List<ServerInfo> list = serverInfoService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServerInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "602750624248893440"),
		@ApiImplicitParam(name = ServerInfoVOMeta.OWNER_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServerInfoVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "???????????????"),
		@ApiImplicitParam(name = ServerInfoVOMeta.SERVER_TYPE, value = "???????????????", required = false, dataTypeClass = String.class, example = "virtual_machine"),
		@ApiImplicitParam(name = ServerInfoVOMeta.OS_VERSION, value = "????????????", required = false, dataTypeClass = String.class, example = "599614904982306816"),
		@ApiImplicitParam(name = ServerInfoVOMeta.IP, value = "IP??????", required = false, dataTypeClass = String.class, example = "192,268,1,1"),
		@ApiImplicitParam(name = ServerInfoVOMeta.DATABASE_VERSION, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServerInfoVOMeta.MIDDLEWARE_VERSION, value = "?????????", required = false, dataTypeClass = String.class, example = "Tomcat"),
		@ApiImplicitParam(name = ServerInfoVOMeta.CPU, value = "CPU???", required = false, dataTypeClass = Integer.class, example = "2"),
		@ApiImplicitParam(name = ServerInfoVOMeta.MEMORY, value = "??????(G)", required = false, dataTypeClass = Integer.class, example = "4"),
		@ApiImplicitParam(name = ServerInfoVOMeta.SPACE_SIZE, value = "??????(G)", required = false, dataTypeClass = Integer.class, example = "100"),
		@ApiImplicitParam(name = ServerInfoVOMeta.SERVER_NUMBER, value = "??????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = ServerInfoVOMeta.USERLIST, value = "????????????", required = false, dataTypeClass = String.class, example = "root"),
		@ApiImplicitParam(name = ServerInfoVOMeta.PORTLIST, value = "????????????", required = false, dataTypeClass = String.class, example = "8080"),
		@ApiImplicitParam(name = ServerInfoVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServerInfoVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { ServerInfoVOMeta.PAGE_INDEX, ServerInfoVOMeta.PAGE_SIZE })
    @SentinelResource(value = ServerInfoServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServerInfoServiceProxy.QUERY_LIST)
    public Result<List<ServerInfo>> queryList(ServerInfoVO sample) {
        Result<List<ServerInfo>> result = new Result<>();
        List<ServerInfo> list = serverInfoService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ???????????????????????????
     */
    @ApiOperation(value = "???????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ServerInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "602750624248893440"),
		@ApiImplicitParam(name = ServerInfoVOMeta.OWNER_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServerInfoVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "???????????????"),
		@ApiImplicitParam(name = ServerInfoVOMeta.SERVER_TYPE, value = "???????????????", required = false, dataTypeClass = String.class, example = "virtual_machine"),
		@ApiImplicitParam(name = ServerInfoVOMeta.OS_VERSION, value = "????????????", required = false, dataTypeClass = String.class, example = "599614904982306816"),
		@ApiImplicitParam(name = ServerInfoVOMeta.IP, value = "IP??????", required = false, dataTypeClass = String.class, example = "192,268,1,1"),
		@ApiImplicitParam(name = ServerInfoVOMeta.DATABASE_VERSION, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServerInfoVOMeta.MIDDLEWARE_VERSION, value = "?????????", required = false, dataTypeClass = String.class, example = "Tomcat"),
		@ApiImplicitParam(name = ServerInfoVOMeta.CPU, value = "CPU???", required = false, dataTypeClass = Integer.class, example = "2"),
		@ApiImplicitParam(name = ServerInfoVOMeta.MEMORY, value = "??????(G)", required = false, dataTypeClass = Integer.class, example = "4"),
		@ApiImplicitParam(name = ServerInfoVOMeta.SPACE_SIZE, value = "??????(G)", required = false, dataTypeClass = Integer.class, example = "100"),
		@ApiImplicitParam(name = ServerInfoVOMeta.SERVER_NUMBER, value = "??????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = ServerInfoVOMeta.USERLIST, value = "????????????", required = false, dataTypeClass = String.class, example = "root"),
		@ApiImplicitParam(name = ServerInfoVOMeta.PORTLIST, value = "????????????", required = false, dataTypeClass = String.class, example = "8080"),
		@ApiImplicitParam(name = ServerInfoVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ServerInfoVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = ServerInfoServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ServerInfoServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<ServerInfo>> queryPagedList(ServerInfoVO sample) {
        Result<PagedList<ServerInfo>> result = new Result<>();
        PagedList<ServerInfo> list = serverInfoService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        serverInfoService.dao().fill(list).with(ServerInfoMeta.SERVER_OS_TYPE).execute();
        result.success(true).data(list);
        return result;
    }
}
