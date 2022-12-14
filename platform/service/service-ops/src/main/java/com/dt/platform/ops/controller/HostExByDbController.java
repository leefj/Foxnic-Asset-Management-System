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
import com.dt.platform.proxy.ops.HostExByDbServiceProxy;
import com.dt.platform.domain.ops.meta.HostExByDbVOMeta;
import com.dt.platform.domain.ops.HostExByDb;
import com.dt.platform.domain.ops.HostExByDbVO;
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
import com.dt.platform.domain.ops.meta.HostExByDbMeta;
import com.dt.platform.domain.ops.Host;
import com.dt.platform.domain.ops.meta.HostMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.IHostExByDbService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ??????????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-09-11 16:23:27
 */
@Api(tags = "???????????????")
@ApiSort(0)
@RestController("OpsHostExByDbController")
public class HostExByDbController extends SuperController {

    @Autowired
    private IHostExByDbService hostExByDbService;

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = HostExByDbVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = HostExByDbVOMeta.HOST_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = HostExByDbVOMeta.NOTE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = HostExByDbServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(HostExByDbServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(HostExByDbVO hostExByDbVO) {
        Result result = hostExByDbService.insert(hostExByDbVO, false);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = HostExByDbVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = HostExByDbServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(HostExByDbServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  hostExByDbService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = hostExByDbService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ??????????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "???????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = HostExByDbVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = HostExByDbServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(HostExByDbServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = hostExByDbService.hasRefers(ids);
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
            Result result = hostExByDbService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = hostExByDbService.deleteByIdsLogical(canDeleteIds);
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
		@ApiImplicitParam(name = HostExByDbVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = HostExByDbVOMeta.HOST_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = HostExByDbVOMeta.NOTE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { HostExByDbVOMeta.PAGE_INDEX, HostExByDbVOMeta.PAGE_SIZE, HostExByDbVOMeta.SEARCH_FIELD, HostExByDbVOMeta.FUZZY_FIELD, HostExByDbVOMeta.SEARCH_VALUE, HostExByDbVOMeta.DIRTY_FIELDS, HostExByDbVOMeta.SORT_FIELD, HostExByDbVOMeta.SORT_TYPE, HostExByDbVOMeta.IDS })
    @SentinelResource(value = HostExByDbServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(HostExByDbServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(HostExByDbVO hostExByDbVO) {
        Result result = hostExByDbService.update(hostExByDbVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = HostExByDbVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = HostExByDbVOMeta.HOST_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = HostExByDbVOMeta.NOTE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { HostExByDbVOMeta.PAGE_INDEX, HostExByDbVOMeta.PAGE_SIZE, HostExByDbVOMeta.SEARCH_FIELD, HostExByDbVOMeta.FUZZY_FIELD, HostExByDbVOMeta.SEARCH_VALUE, HostExByDbVOMeta.DIRTY_FIELDS, HostExByDbVOMeta.SORT_FIELD, HostExByDbVOMeta.SORT_TYPE, HostExByDbVOMeta.IDS })
    @SentinelResource(value = HostExByDbServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(HostExByDbServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(HostExByDbVO hostExByDbVO) {
        Result result = hostExByDbService.save(hostExByDbVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = HostExByDbVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = HostExByDbServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(HostExByDbServiceProxy.GET_BY_ID)
    public Result<HostExByDb> getById(String id) {
        Result<HostExByDb> result = new Result<>();
        HostExByDb hostExByDb = hostExByDbService.getById(id);
        // join ???????????????
        hostExByDbService.dao().fill(hostExByDb).with(HostExByDbMeta.HOST).with(HostExByDbMeta.HOST).execute();
        result.success(true).data(hostExByDb);
        return result;
    }

    /**
     * ??????????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "???????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = HostExByDbVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = HostExByDbServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(HostExByDbServiceProxy.GET_BY_IDS)
    public Result<List<HostExByDb>> getByIds(List<String> ids) {
        Result<List<HostExByDb>> result = new Result<>();
        List<HostExByDb> list = hostExByDbService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = HostExByDbVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = HostExByDbVOMeta.HOST_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = HostExByDbVOMeta.NOTE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { HostExByDbVOMeta.PAGE_INDEX, HostExByDbVOMeta.PAGE_SIZE })
    @SentinelResource(value = HostExByDbServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(HostExByDbServiceProxy.QUERY_LIST)
    public Result<List<HostExByDb>> queryList(HostExByDbVO sample) {
        Result<List<HostExByDb>> result = new Result<>();
        List<HostExByDb> list = hostExByDbService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ???????????????????????????
     */
    @ApiOperation(value = "???????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = HostExByDbVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = HostExByDbVOMeta.HOST_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = HostExByDbVOMeta.NOTE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = HostExByDbServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(HostExByDbServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<HostExByDb>> queryPagedList(HostExByDbVO sample) {
        Result<PagedList<HostExByDb>> result = new Result<>();
        PagedList<HostExByDb> list = hostExByDbService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        hostExByDbService.dao().fill(list).with(HostExByDbMeta.HOST).with(HostExByDbMeta.HOST).execute();
        result.success(true).data(list);
        return result;
    }
}
