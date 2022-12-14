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
import com.dt.platform.proxy.eam.AssetDataPermissionsServiceProxy;
import com.dt.platform.domain.eam.meta.AssetDataPermissionsVOMeta;
import com.dt.platform.domain.eam.AssetDataPermissions;
import com.dt.platform.domain.eam.AssetDataPermissionsVO;
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
import com.dt.platform.domain.eam.meta.AssetDataPermissionsMeta;
import org.github.foxnic.web.domain.system.BusiRole;
import org.github.foxnic.web.domain.pcm.Catalog;
import org.github.foxnic.web.domain.hrm.Organization;
import com.dt.platform.domain.eam.Position;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IAssetDataPermissionsService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ?????????????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-08-17 08:50:08
 */
@Api(tags = "??????????????????")
@ApiSort(0)
@RestController("EamAssetDataPermissionsController")
public class AssetDataPermissionsController extends SuperController {

    @Autowired
    private IAssetDataPermissionsService assetDataPermissionsService;

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "523894324979568640"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????????????????????????????"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWNER_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "asset"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "data_perm_1"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ROLE_CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "eam_data_perm_default"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWN_ORG_AUTHORITY_ENABLE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWN_ORG_LOCAL_ENABLE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWN_ORG_CASCADE_ENABLE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWN_ORG_NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ORG_AUTHORITY_ENABLE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ORG_LOCAL_ENABLE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ORG_CASCADE_ENABLE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ORG_NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.CATALOG_AUTHORITY_ENABLE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.CATALOG_CASCADE_ENABLE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.CATALOG_NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.POSITION_AUTHORITY_ENABLE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.POSITION_NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.PRIORITY, value = "?????????", required = false, dataTypeClass = Integer.class, example = "100"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1212")
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = AssetDataPermissionsServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataPermissionsServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(AssetDataPermissionsVO assetDataPermissionsVO) {
        Result result = assetDataPermissionsService.insert(assetDataPermissionsVO, false);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "523894324979568640")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = AssetDataPermissionsServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataPermissionsServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  assetDataPermissionsService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = assetDataPermissionsService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ?????????????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "??????????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetDataPermissionsServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataPermissionsServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = assetDataPermissionsService.hasRefers(ids);
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
            Result result = assetDataPermissionsService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = assetDataPermissionsService.deleteByIdsLogical(canDeleteIds);
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
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "523894324979568640"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????????????????????????????"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWNER_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "asset"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "data_perm_1"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ROLE_CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "eam_data_perm_default"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWN_ORG_AUTHORITY_ENABLE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWN_ORG_LOCAL_ENABLE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWN_ORG_CASCADE_ENABLE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWN_ORG_NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ORG_AUTHORITY_ENABLE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ORG_LOCAL_ENABLE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ORG_CASCADE_ENABLE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ORG_NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.CATALOG_AUTHORITY_ENABLE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.CATALOG_CASCADE_ENABLE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.CATALOG_NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.POSITION_AUTHORITY_ENABLE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.POSITION_NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.PRIORITY, value = "?????????", required = false, dataTypeClass = Integer.class, example = "100"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1212")
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { AssetDataPermissionsVOMeta.PAGE_INDEX, AssetDataPermissionsVOMeta.PAGE_SIZE, AssetDataPermissionsVOMeta.SEARCH_FIELD, AssetDataPermissionsVOMeta.FUZZY_FIELD, AssetDataPermissionsVOMeta.SEARCH_VALUE, AssetDataPermissionsVOMeta.DIRTY_FIELDS, AssetDataPermissionsVOMeta.SORT_FIELD, AssetDataPermissionsVOMeta.SORT_TYPE, AssetDataPermissionsVOMeta.IDS })
    @SentinelResource(value = AssetDataPermissionsServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataPermissionsServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(AssetDataPermissionsVO assetDataPermissionsVO) {
        Result result = assetDataPermissionsService.update(assetDataPermissionsVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "523894324979568640"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????????????????????????????"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWNER_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "asset"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "data_perm_1"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ROLE_CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "eam_data_perm_default"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWN_ORG_AUTHORITY_ENABLE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWN_ORG_LOCAL_ENABLE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWN_ORG_CASCADE_ENABLE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWN_ORG_NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ORG_AUTHORITY_ENABLE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ORG_LOCAL_ENABLE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ORG_CASCADE_ENABLE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ORG_NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.CATALOG_AUTHORITY_ENABLE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.CATALOG_CASCADE_ENABLE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.CATALOG_NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.POSITION_AUTHORITY_ENABLE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.POSITION_NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.PRIORITY, value = "?????????", required = false, dataTypeClass = Integer.class, example = "100"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1212")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetDataPermissionsVOMeta.PAGE_INDEX, AssetDataPermissionsVOMeta.PAGE_SIZE, AssetDataPermissionsVOMeta.SEARCH_FIELD, AssetDataPermissionsVOMeta.FUZZY_FIELD, AssetDataPermissionsVOMeta.SEARCH_VALUE, AssetDataPermissionsVOMeta.DIRTY_FIELDS, AssetDataPermissionsVOMeta.SORT_FIELD, AssetDataPermissionsVOMeta.SORT_TYPE, AssetDataPermissionsVOMeta.IDS })
    @SentinelResource(value = AssetDataPermissionsServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataPermissionsServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(AssetDataPermissionsVO assetDataPermissionsVO) {
        Result result = assetDataPermissionsService.save(assetDataPermissionsVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = AssetDataPermissionsServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataPermissionsServiceProxy.GET_BY_ID)
    public Result<AssetDataPermissions> getById(String id) {
        Result<AssetDataPermissions> result = new Result<>();
        AssetDataPermissions assetDataPermissions = assetDataPermissionsService.getById(id);
        // join ???????????????
        assetDataPermissionsService.dao().fill(assetDataPermissions).with("organization").with("ownOrganization").with(AssetDataPermissionsMeta.BUSI_ROLE).with(AssetDataPermissionsMeta.POSITION).with(AssetDataPermissionsMeta.CATEGORY).execute();
        result.success(true).data(assetDataPermissions);
        return result;
    }

    /**
     * ?????????????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "??????????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetDataPermissionsServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataPermissionsServiceProxy.GET_BY_IDS)
    public Result<List<AssetDataPermissions>> getByIds(List<String> ids) {
        Result<List<AssetDataPermissions>> result = new Result<>();
        List<AssetDataPermissions> list = assetDataPermissionsService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "523894324979568640"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????????????????????????????"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWNER_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "asset"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "data_perm_1"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ROLE_CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "eam_data_perm_default"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWN_ORG_AUTHORITY_ENABLE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWN_ORG_LOCAL_ENABLE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWN_ORG_CASCADE_ENABLE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWN_ORG_NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ORG_AUTHORITY_ENABLE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ORG_LOCAL_ENABLE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ORG_CASCADE_ENABLE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ORG_NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.CATALOG_AUTHORITY_ENABLE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.CATALOG_CASCADE_ENABLE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.CATALOG_NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.POSITION_AUTHORITY_ENABLE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.POSITION_NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.PRIORITY, value = "?????????", required = false, dataTypeClass = Integer.class, example = "100"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1212")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetDataPermissionsVOMeta.PAGE_INDEX, AssetDataPermissionsVOMeta.PAGE_SIZE })
    @SentinelResource(value = AssetDataPermissionsServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataPermissionsServiceProxy.QUERY_LIST)
    public Result<List<AssetDataPermissions>> queryList(AssetDataPermissionsVO sample) {
        Result<List<AssetDataPermissions>> result = new Result<>();
        List<AssetDataPermissions> list = assetDataPermissionsService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????????????????
     */
    @ApiOperation(value = "??????????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "523894324979568640"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????????????????????????????"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWNER_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "asset"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "data_perm_1"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ROLE_CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "eam_data_perm_default"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWN_ORG_AUTHORITY_ENABLE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWN_ORG_LOCAL_ENABLE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWN_ORG_CASCADE_ENABLE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.OWN_ORG_NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ORG_AUTHORITY_ENABLE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ORG_LOCAL_ENABLE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ORG_CASCADE_ENABLE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.ORG_NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.CATALOG_AUTHORITY_ENABLE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.CATALOG_CASCADE_ENABLE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "disable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.CATALOG_NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.POSITION_AUTHORITY_ENABLE, value = "??????????????????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.POSITION_NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.PRIORITY, value = "?????????", required = false, dataTypeClass = Integer.class, example = "100"),
		@ApiImplicitParam(name = AssetDataPermissionsVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1212")
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = AssetDataPermissionsServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDataPermissionsServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<AssetDataPermissions>> queryPagedList(AssetDataPermissionsVO sample) {
        Result<PagedList<AssetDataPermissions>> result = new Result<>();
        PagedList<AssetDataPermissions> list = assetDataPermissionsService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        assetDataPermissionsService.dao().fill(list).with("organization").with("ownOrganization").with(AssetDataPermissionsMeta.BUSI_ROLE).with(AssetDataPermissionsMeta.POSITION).with(AssetDataPermissionsMeta.CATEGORY).execute();
        result.success(true).data(list);
        return result;
    }
}
