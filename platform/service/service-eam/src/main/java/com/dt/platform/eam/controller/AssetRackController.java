package com.dt.platform.eam.controller;

import java.util.List;
import java.util.ArrayList;
import com.dt.platform.domain.datacenter.meta.RackMeta;
import com.dt.platform.domain.eam.AssetRackInfo;
import com.dt.platform.domain.eam.Position;
import com.github.foxnic.commons.lang.StringUtil;
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
import com.dt.platform.proxy.eam.AssetRackServiceProxy;
import com.dt.platform.domain.eam.meta.AssetRackVOMeta;
import com.dt.platform.domain.eam.AssetRack;
import com.dt.platform.domain.eam.AssetRackVO;
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
import com.dt.platform.domain.eam.meta.AssetRackMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IAssetRackService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-08-27 22:14:55
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("EamAssetRackController")
public class AssetRackController extends SuperController {

    @Autowired
    private IAssetRackService assetRackService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetRackVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackVOMeta.RACK_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackVOMeta.PARENT_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackVOMeta.HIERARCHY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackVOMeta.HIERARCHY_NAME, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetRackVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = AssetRackServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetRackServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(AssetRackVO assetRackVO) {
        assetRackVO.setSort(9999);
        if (StringUtil.isBlank(assetRackVO.getParentId())) {
            assetRackVO.setParentId("0");
        }
        Result result = assetRackService.insert(assetRackVO, false);
        if (result.success()) {
            AssetRack currentPosition = new AssetRack();
            currentPosition.setId(assetRackVO.getId());
            if ("0".equals(assetRackVO.getParentId())) {
                currentPosition.setHierarchy(assetRackVO.getId());
                currentPosition.setHierarchyName(assetRackVO.getName());
            } else {
                AssetRack parentPosition = assetRackService.getById(assetRackVO.getParentId());
                currentPosition.setHierarchy(parentPosition.getHierarchy() + "/" + assetRackVO.getId());
                currentPosition.setHierarchyName(parentPosition.getHierarchyName() + "/" + assetRackVO.getName());
            }
            assetRackService.update(currentPosition, SaveMode.NOT_NULL_FIELDS);
            assetRackVO.setHierarchy(currentPosition.getHierarchy());
            assetRackVO.setHierarchyName(currentPosition.getHierarchyName());
            result.data(assetRackVO);
        }
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetRackVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = AssetRackServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetRackServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        List<AssetRack> list = assetRackService.queryList(AssetRack.create().setParentId(id));
        if (list.size() > 0) {
            Result<Position> result = new Result<>();
            result.success(false).message("????????????????????????");
            return result;
        }
        // ????????????
        ReferCause cause =  assetRackService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = assetRackService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetRackVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetRackServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetRackServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = assetRackService.hasRefers(ids);
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
            Result result = assetRackService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = assetRackService.deleteByIdsLogical(canDeleteIds);
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
		@ApiImplicitParam(name = AssetRackVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackVOMeta.RACK_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackVOMeta.PARENT_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackVOMeta.HIERARCHY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackVOMeta.HIERARCHY_NAME, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetRackVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { AssetRackVOMeta.PAGE_INDEX, AssetRackVOMeta.PAGE_SIZE, AssetRackVOMeta.SEARCH_FIELD, AssetRackVOMeta.FUZZY_FIELD, AssetRackVOMeta.SEARCH_VALUE, AssetRackVOMeta.DIRTY_FIELDS, AssetRackVOMeta.SORT_FIELD, AssetRackVOMeta.SORT_TYPE, AssetRackVOMeta.IDS })
    @SentinelResource(value = AssetRackServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetRackServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(AssetRackVO assetRackVO) {
        Result result = assetRackService.update(assetRackVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        if (result.isSuccess()) {
            // ??????????????????
            return assetRackService.updateHierarchy(assetRackVO.getId());
        }
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetRackVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackVOMeta.RACK_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackVOMeta.PARENT_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackVOMeta.HIERARCHY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackVOMeta.HIERARCHY_NAME, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetRackVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetRackVOMeta.PAGE_INDEX, AssetRackVOMeta.PAGE_SIZE, AssetRackVOMeta.SEARCH_FIELD, AssetRackVOMeta.FUZZY_FIELD, AssetRackVOMeta.SEARCH_VALUE, AssetRackVOMeta.DIRTY_FIELDS, AssetRackVOMeta.SORT_FIELD, AssetRackVOMeta.SORT_TYPE, AssetRackVOMeta.IDS })
    @SentinelResource(value = AssetRackServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetRackServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(AssetRackVO assetRackVO) {
        Result result = assetRackService.save(assetRackVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        if (result.isSuccess()) {
            // ??????????????????
            return assetRackService.updateHierarchy(assetRackVO.getId());
        }
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetRackVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = AssetRackServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetRackServiceProxy.GET_BY_ID)
    public Result<AssetRack> getById(String id) {
        Result<AssetRack> result = new Result<>();
        AssetRack assetRack = assetRackService.getById(id);
        // join ???????????????
        assetRackService.dao().fill(assetRack).with(AssetRackMeta.RACK_INFO).execute();
        result.success(true).data(assetRack);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetRackVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetRackServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetRackServiceProxy.GET_BY_IDS)
    public Result<List<AssetRack>> getByIds(List<String> ids) {
        Result<List<AssetRack>> result = new Result<>();
        List<AssetRack> list = assetRackService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetRackVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackVOMeta.RACK_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackVOMeta.PARENT_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackVOMeta.HIERARCHY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackVOMeta.HIERARCHY_NAME, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetRackVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetRackVOMeta.PAGE_INDEX, AssetRackVOMeta.PAGE_SIZE })
    @SentinelResource(value = AssetRackServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetRackServiceProxy.QUERY_LIST)
    public Result<List<AssetRack>> queryList(AssetRackVO sample) {
        Result<List<AssetRack>> result = new Result<>();
        List<AssetRack> list = assetRackService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetRackVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackVOMeta.RACK_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackVOMeta.PARENT_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackVOMeta.HIERARCHY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackVOMeta.HIERARCHY_NAME, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetRackVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetRackVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = AssetRackServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetRackServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<AssetRack>> queryPagedList(AssetRackVO sample) {
        Result<PagedList<AssetRack>> result = new Result<>();
        PagedList<AssetRack> list = assetRackService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }
}
