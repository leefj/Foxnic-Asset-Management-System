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
import com.dt.platform.proxy.workorder.NetworkStrategyInfoServiceProxy;
import com.dt.platform.domain.workorder.meta.NetworkStrategyInfoVOMeta;
import com.dt.platform.domain.workorder.NetworkStrategyInfo;
import com.dt.platform.domain.workorder.NetworkStrategyInfoVO;
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
import com.dt.platform.domain.workorder.meta.NetworkStrategyInfoMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.workorder.service.INetworkStrategyInfoService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-07-06 06:16:46
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("WoNetworkStrategyInfoController")
public class NetworkStrategyInfoController extends SuperController {

    @Autowired
    private INetworkStrategyInfoService networkStrategyInfoService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "596613755035451392"),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.SOURCE_NAME, value = "?????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.OWNER_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.SOURCE_IP, value = "???IP", required = false, dataTypeClass = String.class, example = "192.168.1.1"),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.TARGET_NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.TARGET_IP, value = "??????IP", required = false, dataTypeClass = String.class, example = "192.168.1.1"),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.TARGET_PORTS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.USED_PROTOCOL_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "tcp"),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.SESSION_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "long"),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = NetworkStrategyInfoServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(NetworkStrategyInfoServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(NetworkStrategyInfoVO networkStrategyInfoVO) {
        Result result = networkStrategyInfoService.insert(networkStrategyInfoVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "596613755035451392")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = NetworkStrategyInfoServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(NetworkStrategyInfoServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  networkStrategyInfoService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = networkStrategyInfoService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = NetworkStrategyInfoServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(NetworkStrategyInfoServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = networkStrategyInfoService.hasRefers(ids);
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
            Result result = networkStrategyInfoService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = networkStrategyInfoService.deleteByIdsLogical(canDeleteIds);
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
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "596613755035451392"),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.SOURCE_NAME, value = "?????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.OWNER_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.SOURCE_IP, value = "???IP", required = false, dataTypeClass = String.class, example = "192.168.1.1"),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.TARGET_NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.TARGET_IP, value = "??????IP", required = false, dataTypeClass = String.class, example = "192.168.1.1"),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.TARGET_PORTS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.USED_PROTOCOL_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "tcp"),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.SESSION_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "long"),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { NetworkStrategyInfoVOMeta.PAGE_INDEX, NetworkStrategyInfoVOMeta.PAGE_SIZE, NetworkStrategyInfoVOMeta.SEARCH_FIELD, NetworkStrategyInfoVOMeta.FUZZY_FIELD, NetworkStrategyInfoVOMeta.SEARCH_VALUE, NetworkStrategyInfoVOMeta.DIRTY_FIELDS, NetworkStrategyInfoVOMeta.SORT_FIELD, NetworkStrategyInfoVOMeta.SORT_TYPE, NetworkStrategyInfoVOMeta.IDS })
    @SentinelResource(value = NetworkStrategyInfoServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(NetworkStrategyInfoServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(NetworkStrategyInfoVO networkStrategyInfoVO) {
        Result result = networkStrategyInfoService.update(networkStrategyInfoVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "596613755035451392"),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.SOURCE_NAME, value = "?????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.OWNER_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.SOURCE_IP, value = "???IP", required = false, dataTypeClass = String.class, example = "192.168.1.1"),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.TARGET_NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.TARGET_IP, value = "??????IP", required = false, dataTypeClass = String.class, example = "192.168.1.1"),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.TARGET_PORTS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.USED_PROTOCOL_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "tcp"),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.SESSION_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "long"),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { NetworkStrategyInfoVOMeta.PAGE_INDEX, NetworkStrategyInfoVOMeta.PAGE_SIZE, NetworkStrategyInfoVOMeta.SEARCH_FIELD, NetworkStrategyInfoVOMeta.FUZZY_FIELD, NetworkStrategyInfoVOMeta.SEARCH_VALUE, NetworkStrategyInfoVOMeta.DIRTY_FIELDS, NetworkStrategyInfoVOMeta.SORT_FIELD, NetworkStrategyInfoVOMeta.SORT_TYPE, NetworkStrategyInfoVOMeta.IDS })
    @SentinelResource(value = NetworkStrategyInfoServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(NetworkStrategyInfoServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(NetworkStrategyInfoVO networkStrategyInfoVO) {
        Result result = networkStrategyInfoService.save(networkStrategyInfoVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = NetworkStrategyInfoServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(NetworkStrategyInfoServiceProxy.GET_BY_ID)
    public Result<NetworkStrategyInfo> getById(String id) {
        Result<NetworkStrategyInfo> result = new Result<>();
        NetworkStrategyInfo networkStrategyInfo = networkStrategyInfoService.getById(id);
        result.success(true).data(networkStrategyInfo);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = NetworkStrategyInfoServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(NetworkStrategyInfoServiceProxy.GET_BY_IDS)
    public Result<List<NetworkStrategyInfo>> getByIds(List<String> ids) {
        Result<List<NetworkStrategyInfo>> result = new Result<>();
        List<NetworkStrategyInfo> list = networkStrategyInfoService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "596613755035451392"),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.SOURCE_NAME, value = "?????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.OWNER_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.SOURCE_IP, value = "???IP", required = false, dataTypeClass = String.class, example = "192.168.1.1"),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.TARGET_NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.TARGET_IP, value = "??????IP", required = false, dataTypeClass = String.class, example = "192.168.1.1"),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.TARGET_PORTS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.USED_PROTOCOL_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "tcp"),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.SESSION_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "long"),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { NetworkStrategyInfoVOMeta.PAGE_INDEX, NetworkStrategyInfoVOMeta.PAGE_SIZE })
    @SentinelResource(value = NetworkStrategyInfoServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(NetworkStrategyInfoServiceProxy.QUERY_LIST)
    public Result<List<NetworkStrategyInfo>> queryList(NetworkStrategyInfoVO sample) {
        Result<List<NetworkStrategyInfo>> result = new Result<>();
        List<NetworkStrategyInfo> list = networkStrategyInfoService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "596613755035451392"),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.SOURCE_NAME, value = "?????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.OWNER_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.SOURCE_IP, value = "???IP", required = false, dataTypeClass = String.class, example = "192.168.1.1"),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.TARGET_NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.TARGET_IP, value = "??????IP", required = false, dataTypeClass = String.class, example = "192.168.1.1"),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.TARGET_PORTS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.USED_PROTOCOL_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "tcp"),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.SESSION_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "long"),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = NetworkStrategyInfoVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = NetworkStrategyInfoServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(NetworkStrategyInfoServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<NetworkStrategyInfo>> queryPagedList(NetworkStrategyInfoVO sample) {
        Result<PagedList<NetworkStrategyInfo>> result = new Result<>();
        PagedList<NetworkStrategyInfo> list = networkStrategyInfoService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }
}
