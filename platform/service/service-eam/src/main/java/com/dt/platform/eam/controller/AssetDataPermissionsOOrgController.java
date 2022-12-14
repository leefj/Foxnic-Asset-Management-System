package com.dt.platform.eam.controller;

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
import com.dt.platform.proxy.eam.AssetDataPermissionsOOrgServiceProxy;
import com.dt.platform.domain.eam.meta.AssetDataPermissionsOOrgVOMeta;
import com.dt.platform.domain.eam.AssetDataPermissionsOOrg;
import com.dt.platform.domain.eam.AssetDataPermissionsOOrgVO;
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
import com.dt.platform.domain.eam.meta.AssetDataPermissionsOOrgMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IAssetDataPermissionsOOrgService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ?????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-08-17 06:31:21
 */
@Api(tags = "??????")
@ApiSort(0)
@RestController("EamAssetDataPermissionsOOrgController")
public class AssetDataPermissionsOOrgController extends SuperController {

    @Autowired
    private IAssetDataPermissionsOOrgService assetDataPermissionsOOrgService;

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataPermissionsOOrgVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsOOrgVOMeta.PERMISSION_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsOOrgVOMeta.VALUE, value = "???", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = AssetDataPermissionsOOrgServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataPermissionsOOrgServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(AssetDataPermissionsOOrgVO assetDataPermissionsOOrgVO) {
        Result result = assetDataPermissionsOOrgService.insert(assetDataPermissionsOOrgVO, false);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataPermissionsOOrgVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = AssetDataPermissionsOOrgServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataPermissionsOOrgServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  assetDataPermissionsOOrgService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = assetDataPermissionsOOrgService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ?????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataPermissionsOOrgVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetDataPermissionsOOrgServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataPermissionsOOrgServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = assetDataPermissionsOOrgService.hasRefers(ids);
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
            Result result = assetDataPermissionsOOrgService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = assetDataPermissionsOOrgService.deleteByIdsLogical(canDeleteIds);
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
		@ApiImplicitParam(name = AssetDataPermissionsOOrgVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsOOrgVOMeta.PERMISSION_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsOOrgVOMeta.VALUE, value = "???", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { AssetDataPermissionsOOrgVOMeta.PAGE_INDEX, AssetDataPermissionsOOrgVOMeta.PAGE_SIZE, AssetDataPermissionsOOrgVOMeta.SEARCH_FIELD, AssetDataPermissionsOOrgVOMeta.FUZZY_FIELD, AssetDataPermissionsOOrgVOMeta.SEARCH_VALUE, AssetDataPermissionsOOrgVOMeta.DIRTY_FIELDS, AssetDataPermissionsOOrgVOMeta.SORT_FIELD, AssetDataPermissionsOOrgVOMeta.SORT_TYPE, AssetDataPermissionsOOrgVOMeta.IDS })
    @SentinelResource(value = AssetDataPermissionsOOrgServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataPermissionsOOrgServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(AssetDataPermissionsOOrgVO assetDataPermissionsOOrgVO) {
        Result result = assetDataPermissionsOOrgService.update(assetDataPermissionsOOrgVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataPermissionsOOrgVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsOOrgVOMeta.PERMISSION_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsOOrgVOMeta.VALUE, value = "???", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetDataPermissionsOOrgVOMeta.PAGE_INDEX, AssetDataPermissionsOOrgVOMeta.PAGE_SIZE, AssetDataPermissionsOOrgVOMeta.SEARCH_FIELD, AssetDataPermissionsOOrgVOMeta.FUZZY_FIELD, AssetDataPermissionsOOrgVOMeta.SEARCH_VALUE, AssetDataPermissionsOOrgVOMeta.DIRTY_FIELDS, AssetDataPermissionsOOrgVOMeta.SORT_FIELD, AssetDataPermissionsOOrgVOMeta.SORT_TYPE, AssetDataPermissionsOOrgVOMeta.IDS })
    @SentinelResource(value = AssetDataPermissionsOOrgServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataPermissionsOOrgServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(AssetDataPermissionsOOrgVO assetDataPermissionsOOrgVO) {
        Result result = assetDataPermissionsOOrgService.save(assetDataPermissionsOOrgVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataPermissionsOOrgVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = AssetDataPermissionsOOrgServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataPermissionsOOrgServiceProxy.GET_BY_ID)
    public Result<AssetDataPermissionsOOrg> getById(String id) {
        Result<AssetDataPermissionsOOrg> result = new Result<>();
        AssetDataPermissionsOOrg assetDataPermissionsOOrg = assetDataPermissionsOOrgService.getById(id);
        result.success(true).data(assetDataPermissionsOOrg);
        return result;
    }

    /**
     * ?????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataPermissionsOOrgVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetDataPermissionsOOrgServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataPermissionsOOrgServiceProxy.GET_BY_IDS)
    public Result<List<AssetDataPermissionsOOrg>> getByIds(List<String> ids) {
        Result<List<AssetDataPermissionsOOrg>> result = new Result<>();
        List<AssetDataPermissionsOOrg> list = assetDataPermissionsOOrgService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataPermissionsOOrgVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsOOrgVOMeta.PERMISSION_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsOOrgVOMeta.VALUE, value = "???", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetDataPermissionsOOrgVOMeta.PAGE_INDEX, AssetDataPermissionsOOrgVOMeta.PAGE_SIZE })
    @SentinelResource(value = AssetDataPermissionsOOrgServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataPermissionsOOrgServiceProxy.QUERY_LIST)
    public Result<List<AssetDataPermissionsOOrg>> queryList(AssetDataPermissionsOOrgVO sample) {
        Result<List<AssetDataPermissionsOOrg>> result = new Result<>();
        List<AssetDataPermissionsOOrg> list = assetDataPermissionsOOrgService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataPermissionsOOrgVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsOOrgVOMeta.PERMISSION_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsOOrgVOMeta.VALUE, value = "???", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = AssetDataPermissionsOOrgServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataPermissionsOOrgServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<AssetDataPermissionsOOrg>> queryPagedList(AssetDataPermissionsOOrgVO sample) {
        Result<PagedList<AssetDataPermissionsOOrg>> result = new Result<>();
        PagedList<AssetDataPermissionsOOrg> list = assetDataPermissionsOOrgService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }
}
