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
import com.dt.platform.proxy.ops.HostPositionServiceProxy;
import com.dt.platform.domain.ops.meta.HostPositionVOMeta;
import com.dt.platform.domain.ops.HostPosition;
import com.dt.platform.domain.ops.HostPositionVO;
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
import com.dt.platform.domain.ops.meta.HostPositionMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.IHostPositionService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-07-12 22:04:48
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("OpsHostPositionController")
public class HostPositionController extends SuperController {

    @Autowired
    private IHostPositionService hostPositionService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = HostPositionVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "478242968168304640"),
		@ApiImplicitParam(name = HostPositionVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????1"),
		@ApiImplicitParam(name = HostPositionVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = HostPositionServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(HostPositionServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(HostPositionVO hostPositionVO) {
        Result result = hostPositionService.insert(hostPositionVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = HostPositionVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "478242968168304640")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = HostPositionServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(HostPositionServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  hostPositionService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = hostPositionService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = HostPositionVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = HostPositionServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(HostPositionServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = hostPositionService.hasRefers(ids);
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
            Result result = hostPositionService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = hostPositionService.deleteByIdsLogical(canDeleteIds);
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
		@ApiImplicitParam(name = HostPositionVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "478242968168304640"),
		@ApiImplicitParam(name = HostPositionVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????1"),
		@ApiImplicitParam(name = HostPositionVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { HostPositionVOMeta.PAGE_INDEX, HostPositionVOMeta.PAGE_SIZE, HostPositionVOMeta.SEARCH_FIELD, HostPositionVOMeta.FUZZY_FIELD, HostPositionVOMeta.SEARCH_VALUE, HostPositionVOMeta.DIRTY_FIELDS, HostPositionVOMeta.SORT_FIELD, HostPositionVOMeta.SORT_TYPE, HostPositionVOMeta.IDS })
    @SentinelResource(value = HostPositionServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(HostPositionServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(HostPositionVO hostPositionVO) {
        Result result = hostPositionService.update(hostPositionVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = HostPositionVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "478242968168304640"),
		@ApiImplicitParam(name = HostPositionVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????1"),
		@ApiImplicitParam(name = HostPositionVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { HostPositionVOMeta.PAGE_INDEX, HostPositionVOMeta.PAGE_SIZE, HostPositionVOMeta.SEARCH_FIELD, HostPositionVOMeta.FUZZY_FIELD, HostPositionVOMeta.SEARCH_VALUE, HostPositionVOMeta.DIRTY_FIELDS, HostPositionVOMeta.SORT_FIELD, HostPositionVOMeta.SORT_TYPE, HostPositionVOMeta.IDS })
    @SentinelResource(value = HostPositionServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(HostPositionServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(HostPositionVO hostPositionVO) {
        Result result = hostPositionService.save(hostPositionVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = HostPositionVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = HostPositionServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(HostPositionServiceProxy.GET_BY_ID)
    public Result<HostPosition> getById(String id) {
        Result<HostPosition> result = new Result<>();
        HostPosition hostPosition = hostPositionService.getById(id);
        result.success(true).data(hostPosition);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = HostPositionVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = HostPositionServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(HostPositionServiceProxy.GET_BY_IDS)
    public Result<List<HostPosition>> getByIds(List<String> ids) {
        Result<List<HostPosition>> result = new Result<>();
        List<HostPosition> list = hostPositionService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = HostPositionVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "478242968168304640"),
		@ApiImplicitParam(name = HostPositionVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????1"),
		@ApiImplicitParam(name = HostPositionVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { HostPositionVOMeta.PAGE_INDEX, HostPositionVOMeta.PAGE_SIZE })
    @SentinelResource(value = HostPositionServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(HostPositionServiceProxy.QUERY_LIST)
    public Result<List<HostPosition>> queryList(HostPositionVO sample) {
        Result<List<HostPosition>> result = new Result<>();
        List<HostPosition> list = hostPositionService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = HostPositionVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "478242968168304640"),
		@ApiImplicitParam(name = HostPositionVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????1"),
		@ApiImplicitParam(name = HostPositionVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = HostPositionServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(HostPositionServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<HostPosition>> queryPagedList(HostPositionVO sample) {
        Result<PagedList<HostPosition>> result = new Result<>();
        PagedList<HostPosition> list = hostPositionService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }
}
