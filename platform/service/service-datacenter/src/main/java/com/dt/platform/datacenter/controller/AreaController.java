package com.dt.platform.datacenter.controller;

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
import com.dt.platform.proxy.datacenter.AreaServiceProxy;
import com.dt.platform.domain.datacenter.meta.AreaVOMeta;
import com.dt.platform.domain.datacenter.Area;
import com.dt.platform.domain.datacenter.AreaVO;
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
import com.dt.platform.domain.datacenter.meta.AreaMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.datacenter.service.IAreaService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ?????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-07-22 21:40:43
 */
@Api(tags = "??????")
@ApiSort(0)
@RestController("DcAreaController")
public class AreaController extends SuperController {

    @Autowired
    private IAreaService areaService;

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AreaVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "565457749140312064"),
		@ApiImplicitParam(name = AreaVOMeta.TYPE, value = "??????", required = false, dataTypeClass = String.class, example = "datacenter"),
		@ApiImplicitParam(name = AreaVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "????????????A"),
		@ApiImplicitParam(name = AreaVOMeta.POSITION, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AreaVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = AreaServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AreaServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(AreaVO areaVO) {
        Result result = areaService.insert(areaVO, false);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AreaVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "565457749140312064")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = AreaServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AreaServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  areaService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = areaService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ?????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AreaVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AreaServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AreaServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = areaService.hasRefers(ids);
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
            Result result = areaService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = areaService.deleteByIdsLogical(canDeleteIds);
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
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AreaVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "565457749140312064"),
		@ApiImplicitParam(name = AreaVOMeta.TYPE, value = "??????", required = false, dataTypeClass = String.class, example = "datacenter"),
		@ApiImplicitParam(name = AreaVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "????????????A"),
		@ApiImplicitParam(name = AreaVOMeta.POSITION, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AreaVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { AreaVOMeta.PAGE_INDEX, AreaVOMeta.PAGE_SIZE, AreaVOMeta.SEARCH_FIELD, AreaVOMeta.FUZZY_FIELD, AreaVOMeta.SEARCH_VALUE, AreaVOMeta.DIRTY_FIELDS, AreaVOMeta.SORT_FIELD, AreaVOMeta.SORT_TYPE, AreaVOMeta.IDS })
    @SentinelResource(value = AreaServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AreaServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(AreaVO areaVO) {
        Result result = areaService.update(areaVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AreaVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "565457749140312064"),
		@ApiImplicitParam(name = AreaVOMeta.TYPE, value = "??????", required = false, dataTypeClass = String.class, example = "datacenter"),
		@ApiImplicitParam(name = AreaVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "????????????A"),
		@ApiImplicitParam(name = AreaVOMeta.POSITION, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AreaVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AreaVOMeta.PAGE_INDEX, AreaVOMeta.PAGE_SIZE, AreaVOMeta.SEARCH_FIELD, AreaVOMeta.FUZZY_FIELD, AreaVOMeta.SEARCH_VALUE, AreaVOMeta.DIRTY_FIELDS, AreaVOMeta.SORT_FIELD, AreaVOMeta.SORT_TYPE, AreaVOMeta.IDS })
    @SentinelResource(value = AreaServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AreaServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(AreaVO areaVO) {
        Result result = areaService.save(areaVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AreaVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = AreaServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AreaServiceProxy.GET_BY_ID)
    public Result<Area> getById(String id) {
        Result<Area> result = new Result<>();
        Area area = areaService.getById(id);
        result.success(true).data(area);
        return result;
    }

    /**
     * ?????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AreaVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AreaServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AreaServiceProxy.GET_BY_IDS)
    public Result<List<Area>> getByIds(List<String> ids) {
        Result<List<Area>> result = new Result<>();
        List<Area> list = areaService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AreaVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "565457749140312064"),
		@ApiImplicitParam(name = AreaVOMeta.TYPE, value = "??????", required = false, dataTypeClass = String.class, example = "datacenter"),
		@ApiImplicitParam(name = AreaVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "????????????A"),
		@ApiImplicitParam(name = AreaVOMeta.POSITION, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AreaVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AreaVOMeta.PAGE_INDEX, AreaVOMeta.PAGE_SIZE })
    @SentinelResource(value = AreaServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AreaServiceProxy.QUERY_LIST)
    public Result<List<Area>> queryList(AreaVO sample) {
        Result<List<Area>> result = new Result<>();
        List<Area> list = areaService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AreaVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "565457749140312064"),
		@ApiImplicitParam(name = AreaVOMeta.TYPE, value = "??????", required = false, dataTypeClass = String.class, example = "datacenter"),
		@ApiImplicitParam(name = AreaVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "????????????A"),
		@ApiImplicitParam(name = AreaVOMeta.POSITION, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AreaVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = AreaServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AreaServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<Area>> queryPagedList(AreaVO sample) {
        Result<PagedList<Area>> result = new Result<>();
        PagedList<Area> list = areaService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }
}
