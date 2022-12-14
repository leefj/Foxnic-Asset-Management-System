package com.dt.platform.eam.controller;

import java.util.ArrayList;
import java.util.List;
import com.dt.platform.domain.eam.Inventory;
import com.dt.platform.domain.eam.meta.AssetMeta;
import com.dt.platform.domain.eam.meta.InventoryMeta;
import com.dt.platform.eam.service.IAssetService;
import com.github.foxnic.commons.collection.CollectorUtil;
import com.github.foxnic.sql.expr.ConditionExpr;
import org.github.foxnic.web.domain.hrm.Employee;
import org.github.foxnic.web.domain.hrm.Person;
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
import com.dt.platform.proxy.eam.InventoryAssetServiceProxy;
import com.dt.platform.domain.eam.meta.InventoryAssetVOMeta;
import com.dt.platform.domain.eam.InventoryAsset;
import com.dt.platform.domain.eam.InventoryAssetVO;
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
import com.dt.platform.domain.eam.meta.InventoryAssetMeta;
import com.dt.platform.domain.eam.Asset;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IInventoryAssetService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-01-05 12:58:04
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("EamInventoryAssetController")
public class InventoryAssetController extends SuperController {

    @Autowired
    private IInventoryAssetService inventoryAssetService;

    @Autowired
    private IAssetService assetService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = InventoryAssetVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "66476b1b-6dd0-11ec-bf3e-00163e1b60a7"),
		@ApiImplicitParam(name = InventoryAssetVOMeta.INVENTORY_ID, value = "??????", required = false, dataTypeClass = String.class, example = "531058564924444672"),
		@ApiImplicitParam(name = InventoryAssetVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "loss"),
		@ApiImplicitParam(name = InventoryAssetVOMeta.ASSET_ID, value = "??????", required = false, dataTypeClass = String.class, example = "518094137103220736"),
		@ApiImplicitParam(name = InventoryAssetVOMeta.OPER_EMPL_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InventoryAssetVOMeta.OPER_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = InventoryAssetVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_PLUS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_LOSS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_PLUS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_LOSS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_PLUS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_LOSS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_PLUS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_LOSS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_PLUS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_LOSS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.SOURCE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InventoryAssetVOMeta.ASSET_PLUS_ACTION_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "none"),
		@ApiImplicitParam(name = InventoryAssetVOMeta.ASSET_LOSS_ACTION_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "none"),
		@ApiImplicitParam(name = InventoryAssetVOMeta.SOURCE, value = "????????????", required = false, dataTypeClass = String.class, example = "asset"),
		@ApiImplicitParam(name = InventoryAssetVOMeta.PICTURE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InventoryAssetVOMeta.FLAG, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = InventoryAssetServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InventoryAssetServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(InventoryAssetVO inventoryAssetVO) {
        Result result = inventoryAssetService.insert(inventoryAssetVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = InventoryAssetVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "66476b1b-6dd0-11ec-bf3e-00163e1b60a7")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = InventoryAssetServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InventoryAssetServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = inventoryAssetService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = InventoryAssetVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = InventoryAssetServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InventoryAssetServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = inventoryAssetService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = InventoryAssetVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "66476b1b-6dd0-11ec-bf3e-00163e1b60a7"),
		@ApiImplicitParam(name = InventoryAssetVOMeta.INVENTORY_ID, value = "??????", required = false, dataTypeClass = String.class, example = "531058564924444672"),
		@ApiImplicitParam(name = InventoryAssetVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "loss"),
		@ApiImplicitParam(name = InventoryAssetVOMeta.ASSET_ID, value = "??????", required = false, dataTypeClass = String.class, example = "518094137103220736"),
		@ApiImplicitParam(name = InventoryAssetVOMeta.OPER_EMPL_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InventoryAssetVOMeta.OPER_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = InventoryAssetVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_PLUS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_LOSS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_PLUS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_LOSS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_PLUS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_LOSS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_PLUS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_LOSS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_PLUS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_LOSS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.SOURCE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InventoryAssetVOMeta.ASSET_PLUS_ACTION_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "none"),
		@ApiImplicitParam(name = InventoryAssetVOMeta.ASSET_LOSS_ACTION_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "none"),
		@ApiImplicitParam(name = InventoryAssetVOMeta.SOURCE, value = "????????????", required = false, dataTypeClass = String.class, example = "asset"),
		@ApiImplicitParam(name = InventoryAssetVOMeta.PICTURE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InventoryAssetVOMeta.FLAG, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { InventoryAssetVOMeta.PAGE_INDEX, InventoryAssetVOMeta.PAGE_SIZE, InventoryAssetVOMeta.SEARCH_FIELD, InventoryAssetVOMeta.FUZZY_FIELD, InventoryAssetVOMeta.SEARCH_VALUE, InventoryAssetVOMeta.DIRTY_FIELDS, InventoryAssetVOMeta.SORT_FIELD, InventoryAssetVOMeta.SORT_TYPE, InventoryAssetVOMeta.IDS })
    @SentinelResource(value = InventoryAssetServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InventoryAssetServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(InventoryAssetVO inventoryAssetVO) {
        Result result = inventoryAssetService.update(inventoryAssetVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = InventoryAssetVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "66476b1b-6dd0-11ec-bf3e-00163e1b60a7"),
		@ApiImplicitParam(name = InventoryAssetVOMeta.INVENTORY_ID, value = "??????", required = false, dataTypeClass = String.class, example = "531058564924444672"),
		@ApiImplicitParam(name = InventoryAssetVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "loss"),
		@ApiImplicitParam(name = InventoryAssetVOMeta.ASSET_ID, value = "??????", required = false, dataTypeClass = String.class, example = "518094137103220736"),
		@ApiImplicitParam(name = InventoryAssetVOMeta.OPER_EMPL_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InventoryAssetVOMeta.OPER_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = InventoryAssetVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_PLUS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_LOSS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_PLUS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_LOSS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_PLUS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_LOSS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_PLUS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_LOSS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_PLUS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_LOSS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.SOURCE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InventoryAssetVOMeta.ASSET_PLUS_ACTION_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "none"),
		@ApiImplicitParam(name = InventoryAssetVOMeta.ASSET_LOSS_ACTION_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "none"),
		@ApiImplicitParam(name = InventoryAssetVOMeta.SOURCE, value = "????????????", required = false, dataTypeClass = String.class, example = "asset"),
		@ApiImplicitParam(name = InventoryAssetVOMeta.PICTURE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InventoryAssetVOMeta.FLAG, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { InventoryAssetVOMeta.PAGE_INDEX, InventoryAssetVOMeta.PAGE_SIZE, InventoryAssetVOMeta.SEARCH_FIELD, InventoryAssetVOMeta.FUZZY_FIELD, InventoryAssetVOMeta.SEARCH_VALUE, InventoryAssetVOMeta.DIRTY_FIELDS, InventoryAssetVOMeta.SORT_FIELD, InventoryAssetVOMeta.SORT_TYPE, InventoryAssetVOMeta.IDS })
    @SentinelResource(value = InventoryAssetServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InventoryAssetServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(InventoryAssetVO inventoryAssetVO) {
        Result result = inventoryAssetService.save(inventoryAssetVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = InventoryAssetVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = InventoryAssetServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InventoryAssetServiceProxy.GET_BY_ID)
    public Result<InventoryAsset> getById(String id) {
        Result<InventoryAsset> result = new Result<>();
        InventoryAsset inventoryAsset = inventoryAssetService.getById(id);
        // join ???????????????
        inventoryAssetService.dao().fill(inventoryAsset).with(InventoryAssetMeta.ASSET).with(InventoryAssetMeta.INVENTORY).with(InventoryAssetMeta.OPERATER).execute();
        inventoryAssetService.dao().join(inventoryAsset.getOperater(), Person.class);
        assetService.dao().fill(inventoryAsset.getAsset()).with(AssetMeta.CATEGORY).with(AssetMeta.CATEGORY_FINANCE).with(AssetMeta.GOODS).with(AssetMeta.MANUFACTURER).with(AssetMeta.POSITION).with(AssetMeta.MAINTNAINER).with(AssetMeta.REGION).with(AssetMeta.SUPPLIER).with(AssetMeta.OWNER_COMPANY).with(AssetMeta.USE_ORGANIZATION).with(AssetMeta.MANAGER).with(AssetMeta.USE_USER).with(AssetMeta.ORIGINATOR).with(AssetMeta.RACK).with(AssetMeta.SOURCE).with(AssetMeta.SAFETY_LEVEL).with(AssetMeta.EQUIPMENT_ENVIRONMENT).with(AssetMeta.ASSET_MAINTENANCE_STATUS).execute();
        inventoryAssetService.dao().join(inventoryAsset.getOperater(), Person.class);
        result.success(true).data(inventoryAsset);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = InventoryAssetVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = InventoryAssetServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InventoryAssetServiceProxy.GET_BY_IDS)
    public Result<List<InventoryAsset>> getByIds(List<String> ids) {
        Result<List<InventoryAsset>> result = new Result<>();
        List<InventoryAsset> list = inventoryAssetService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = InventoryAssetVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "66476b1b-6dd0-11ec-bf3e-00163e1b60a7"),
		@ApiImplicitParam(name = InventoryAssetVOMeta.INVENTORY_ID, value = "??????", required = false, dataTypeClass = String.class, example = "531058564924444672"),
		@ApiImplicitParam(name = InventoryAssetVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "loss"),
		@ApiImplicitParam(name = InventoryAssetVOMeta.ASSET_ID, value = "??????", required = false, dataTypeClass = String.class, example = "518094137103220736"),
		@ApiImplicitParam(name = InventoryAssetVOMeta.OPER_EMPL_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InventoryAssetVOMeta.OPER_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = InventoryAssetVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_PLUS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_LOSS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_PLUS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_LOSS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_PLUS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_LOSS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_PLUS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_LOSS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_PLUS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_LOSS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.SOURCE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InventoryAssetVOMeta.ASSET_PLUS_ACTION_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "none"),
		@ApiImplicitParam(name = InventoryAssetVOMeta.ASSET_LOSS_ACTION_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "none"),
		@ApiImplicitParam(name = InventoryAssetVOMeta.SOURCE, value = "????????????", required = false, dataTypeClass = String.class, example = "asset"),
		@ApiImplicitParam(name = InventoryAssetVOMeta.PICTURE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InventoryAssetVOMeta.FLAG, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { InventoryAssetVOMeta.PAGE_INDEX, InventoryAssetVOMeta.PAGE_SIZE })
    @SentinelResource(value = InventoryAssetServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InventoryAssetServiceProxy.QUERY_LIST)
    public Result<List<InventoryAsset>> queryList(InventoryAssetVO sample) {
        Result<List<InventoryAsset>> result = new Result<>();
        List<InventoryAsset> list = inventoryAssetService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = InventoryAssetVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "66476b1b-6dd0-11ec-bf3e-00163e1b60a7"),
		@ApiImplicitParam(name = InventoryAssetVOMeta.INVENTORY_ID, value = "??????", required = false, dataTypeClass = String.class, example = "531058564924444672"),
		@ApiImplicitParam(name = InventoryAssetVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "loss"),
		@ApiImplicitParam(name = InventoryAssetVOMeta.ASSET_ID, value = "??????", required = false, dataTypeClass = String.class, example = "518094137103220736"),
		@ApiImplicitParam(name = InventoryAssetVOMeta.OPER_EMPL_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InventoryAssetVOMeta.OPER_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = InventoryAssetVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_PLUS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_LOSS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_PLUS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_LOSS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_PLUS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_LOSS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_PLUS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_LOSS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_PLUS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.ASSET_LOSS_ACTION_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "InventoryAssetVOMeta.SOURCE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InventoryAssetVOMeta.ASSET_PLUS_ACTION_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "none"),
		@ApiImplicitParam(name = InventoryAssetVOMeta.ASSET_LOSS_ACTION_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "none"),
		@ApiImplicitParam(name = InventoryAssetVOMeta.SOURCE, value = "????????????", required = false, dataTypeClass = String.class, example = "asset"),
		@ApiImplicitParam(name = InventoryAssetVOMeta.PICTURE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InventoryAssetVOMeta.FLAG, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = InventoryAssetServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InventoryAssetServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<InventoryAsset>> queryPagedList(InventoryAssetVO sample) {
        Result<PagedList<InventoryAsset>> result = new Result<>();

        ConditionExpr condition=new ConditionExpr();
        condition.and( " asset_id in (select id from eam_asset where deleted=0)");
        PagedList<InventoryAsset> list = inventoryAssetService.queryPagedList(sample, condition,sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        inventoryAssetService.dao().fill(list).with(InventoryAssetMeta.OPERATER).with(InventoryAssetMeta.ASSET).execute();
        List<Employee> operList = CollectorUtil.collectList(list.getList(), InventoryAsset::getOperater);
        inventoryAssetService.dao().join(operList, Person.class);
        List<Asset> assetList = CollectorUtil.collectList(list.getList(), InventoryAsset::getAsset);
        assetService.joinData(assetList);
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = InventoryAssetServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(InventoryAssetServiceProxy.EXPORT_EXCEL)
    public void exportExcel(InventoryAssetVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = inventoryAssetService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = InventoryAssetServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(InventoryAssetServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = inventoryAssetService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = InventoryAssetServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(InventoryAssetServiceProxy.IMPORT_EXCEL)
    public Result importExcel(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
        // ?????????????????????
        Map<String, MultipartFile> map = request.getFileMap();
        InputStream input = null;
        for (MultipartFile mf : map.values()) {
            input = StreamUtil.bytes2input(mf.getBytes());
            break;
        }
        if (input == null) {
            return ErrorDesc.failure().message("?????????????????????");
        }
        List<ValidateResult> errors = inventoryAssetService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
