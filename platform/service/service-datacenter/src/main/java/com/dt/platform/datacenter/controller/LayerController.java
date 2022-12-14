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
import com.dt.platform.proxy.datacenter.LayerServiceProxy;
import com.dt.platform.domain.datacenter.meta.LayerVOMeta;
import com.dt.platform.domain.datacenter.Layer;
import com.dt.platform.domain.datacenter.LayerVO;
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
import com.dt.platform.domain.datacenter.meta.LayerMeta;
import com.dt.platform.domain.datacenter.Area;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.datacenter.service.ILayerService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ?????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-07-22 21:41:05
 */
@Api(tags = "??????")
@ApiSort(0)
@RestController("DcLayerController")
public class LayerController extends SuperController {

    @Autowired
    private ILayerService layerService;

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = LayerVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "565457784187916288"),
		@ApiImplicitParam(name = LayerVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????1"),
		@ApiImplicitParam(name = LayerVOMeta.AREA_ID, value = "??????", required = false, dataTypeClass = String.class, example = "575433967289761792"),
		@ApiImplicitParam(name = LayerVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = LayerVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = LayerServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(LayerServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(LayerVO layerVO) {
        Result result = layerService.insert(layerVO, false);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = LayerVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "565457784187916288")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = LayerServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(LayerServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  layerService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = layerService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ?????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = LayerVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = LayerServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(LayerServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = layerService.hasRefers(ids);
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
            Result result = layerService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = layerService.deleteByIdsLogical(canDeleteIds);
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
		@ApiImplicitParam(name = LayerVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "565457784187916288"),
		@ApiImplicitParam(name = LayerVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????1"),
		@ApiImplicitParam(name = LayerVOMeta.AREA_ID, value = "??????", required = false, dataTypeClass = String.class, example = "575433967289761792"),
		@ApiImplicitParam(name = LayerVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = LayerVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { LayerVOMeta.PAGE_INDEX, LayerVOMeta.PAGE_SIZE, LayerVOMeta.SEARCH_FIELD, LayerVOMeta.FUZZY_FIELD, LayerVOMeta.SEARCH_VALUE, LayerVOMeta.DIRTY_FIELDS, LayerVOMeta.SORT_FIELD, LayerVOMeta.SORT_TYPE, LayerVOMeta.IDS })
    @SentinelResource(value = LayerServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(LayerServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(LayerVO layerVO) {
        Result result = layerService.update(layerVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = LayerVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "565457784187916288"),
		@ApiImplicitParam(name = LayerVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????1"),
		@ApiImplicitParam(name = LayerVOMeta.AREA_ID, value = "??????", required = false, dataTypeClass = String.class, example = "575433967289761792"),
		@ApiImplicitParam(name = LayerVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = LayerVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { LayerVOMeta.PAGE_INDEX, LayerVOMeta.PAGE_SIZE, LayerVOMeta.SEARCH_FIELD, LayerVOMeta.FUZZY_FIELD, LayerVOMeta.SEARCH_VALUE, LayerVOMeta.DIRTY_FIELDS, LayerVOMeta.SORT_FIELD, LayerVOMeta.SORT_TYPE, LayerVOMeta.IDS })
    @SentinelResource(value = LayerServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(LayerServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(LayerVO layerVO) {
        Result result = layerService.save(layerVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = LayerVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = LayerServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(LayerServiceProxy.GET_BY_ID)
    public Result<Layer> getById(String id) {
        Result<Layer> result = new Result<>();
        Layer layer = layerService.getById(id);
        // join ???????????????
        layerService.dao().fill(layer).with(LayerMeta.AREA).execute();
        result.success(true).data(layer);
        return result;
    }

    /**
     * ?????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = LayerVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = LayerServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(LayerServiceProxy.GET_BY_IDS)
    public Result<List<Layer>> getByIds(List<String> ids) {
        Result<List<Layer>> result = new Result<>();
        List<Layer> list = layerService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = LayerVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "565457784187916288"),
		@ApiImplicitParam(name = LayerVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????1"),
		@ApiImplicitParam(name = LayerVOMeta.AREA_ID, value = "??????", required = false, dataTypeClass = String.class, example = "575433967289761792"),
		@ApiImplicitParam(name = LayerVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = LayerVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { LayerVOMeta.PAGE_INDEX, LayerVOMeta.PAGE_SIZE })
    @SentinelResource(value = LayerServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(LayerServiceProxy.QUERY_LIST)
    public Result<List<Layer>> queryList(LayerVO sample) {
        Result<List<Layer>> result = new Result<>();
        List<Layer> list = layerService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = LayerVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "565457784187916288"),
		@ApiImplicitParam(name = LayerVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????1"),
		@ApiImplicitParam(name = LayerVOMeta.AREA_ID, value = "??????", required = false, dataTypeClass = String.class, example = "575433967289761792"),
		@ApiImplicitParam(name = LayerVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = LayerVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = LayerServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(LayerServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<Layer>> queryPagedList(LayerVO sample) {
        Result<PagedList<Layer>> result = new Result<>();
        PagedList<Layer> list = layerService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        layerService.dao().fill(list).with(LayerMeta.AREA).execute();
        result.success(true).data(list);
        return result;
    }
}
