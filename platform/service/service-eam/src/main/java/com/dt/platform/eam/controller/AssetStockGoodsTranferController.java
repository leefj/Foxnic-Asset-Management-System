package com.dt.platform.eam.controller;

import java.util.List;
import com.dt.platform.domain.eam.*;
import com.dt.platform.domain.eam.meta.*;
import com.dt.platform.eam.service.IGoodsStockService;
import com.dt.platform.proxy.eam.AssetStockGoodsAdjustServiceProxy;
import com.github.foxnic.commons.collection.CollectorUtil;
import org.github.foxnic.web.domain.changes.ProcessApproveVO;
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
import com.dt.platform.proxy.eam.AssetStockGoodsTranferServiceProxy;
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
import org.github.foxnic.web.domain.hrm.Employee;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IAssetStockGoodsTranferService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-04-24 12:15:19
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("EamAssetStockGoodsTranferController")
public class AssetStockGoodsTranferController extends SuperController {

    @Autowired
    private IAssetStockGoodsTranferService assetStockGoodsTranferService;

    @Autowired
    private IGoodsStockService goodsStockService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.OWNER_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.WAREHOUSE_OUT_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.WAREHOUSE_IN_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.ATTACH_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.CHS_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.CHS_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.CHS_VERSION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.CHANGE_INSTANCE_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.SUMMARY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.LATEST_APPROVER_ID, value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.LATEST_APPROVER_NAME, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.NEXT_APPROVER_IDS, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.NEXT_APPROVER_NAMES, value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.APPROVAL_OPINION, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = AssetStockGoodsTranferServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStockGoodsTranferServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(AssetStockGoodsTranferVO assetStockGoodsTranferVO) {
        Result result = assetStockGoodsTranferService.insert(assetStockGoodsTranferVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = AssetStockGoodsTranferServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStockGoodsTranferServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = assetStockGoodsTranferService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetStockGoodsTranferServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStockGoodsTranferServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = assetStockGoodsTranferService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.OWNER_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.WAREHOUSE_OUT_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.WAREHOUSE_IN_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.ATTACH_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.CHS_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.CHS_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.CHS_VERSION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.CHANGE_INSTANCE_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.SUMMARY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.LATEST_APPROVER_ID, value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.LATEST_APPROVER_NAME, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.NEXT_APPROVER_IDS, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.NEXT_APPROVER_NAMES, value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.APPROVAL_OPINION, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { AssetStockGoodsTranferVOMeta.PAGE_INDEX, AssetStockGoodsTranferVOMeta.PAGE_SIZE, AssetStockGoodsTranferVOMeta.SEARCH_FIELD, AssetStockGoodsTranferVOMeta.FUZZY_FIELD, AssetStockGoodsTranferVOMeta.SEARCH_VALUE, AssetStockGoodsTranferVOMeta.DIRTY_FIELDS, AssetStockGoodsTranferVOMeta.SORT_FIELD, AssetStockGoodsTranferVOMeta.SORT_TYPE, AssetStockGoodsTranferVOMeta.IDS })
    @SentinelResource(value = AssetStockGoodsTranferServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStockGoodsTranferServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(AssetStockGoodsTranferVO assetStockGoodsTranferVO) {
        Result result = assetStockGoodsTranferService.update(assetStockGoodsTranferVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.OWNER_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.WAREHOUSE_OUT_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.WAREHOUSE_IN_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.ATTACH_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.CHS_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.CHS_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.CHS_VERSION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.CHANGE_INSTANCE_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.SUMMARY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.LATEST_APPROVER_ID, value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.LATEST_APPROVER_NAME, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.NEXT_APPROVER_IDS, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.NEXT_APPROVER_NAMES, value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.APPROVAL_OPINION, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetStockGoodsTranferVOMeta.PAGE_INDEX, AssetStockGoodsTranferVOMeta.PAGE_SIZE, AssetStockGoodsTranferVOMeta.SEARCH_FIELD, AssetStockGoodsTranferVOMeta.FUZZY_FIELD, AssetStockGoodsTranferVOMeta.SEARCH_VALUE, AssetStockGoodsTranferVOMeta.DIRTY_FIELDS, AssetStockGoodsTranferVOMeta.SORT_FIELD, AssetStockGoodsTranferVOMeta.SORT_TYPE, AssetStockGoodsTranferVOMeta.IDS })
    @SentinelResource(value = AssetStockGoodsTranferServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStockGoodsTranferServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(AssetStockGoodsTranferVO assetStockGoodsTranferVO) {
        Result result = assetStockGoodsTranferService.save(assetStockGoodsTranferVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = AssetStockGoodsTranferServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStockGoodsTranferServiceProxy.GET_BY_ID)
    public Result<AssetStockGoodsTranfer> getById(String id) {
        Result<AssetStockGoodsTranfer> result = new Result<>();
        AssetStockGoodsTranfer assetStockGoodsTranfer = assetStockGoodsTranferService.getById(id);
        // join ???????????????
        assetStockGoodsTranferService.dao().fill(assetStockGoodsTranfer).with("originator").with(AssetStockGoodsTranferMeta.WAREHOUSE_IN).with(AssetStockGoodsTranferMeta.WAREHOUSE_OUT).execute();
        assetStockGoodsTranferService.dao().join(assetStockGoodsTranfer.getOriginator(), Person.class);
        GoodsStockVO vo = new GoodsStockVO();
        vo.setPageIndex(1);
        vo.setPageSize(1000);
        vo.setOwnerTmpId(id);
        PagedList<GoodsStock> list = goodsStockService.queryPagedListBySelected(vo, "", "reset");
        goodsStockService.dao().fill(list).with("ownerCompany").with("useOrganization").with("manager").with("originator").with(GoodsStockMeta.CATEGORY).with(GoodsStockMeta.GOODS).with(GoodsStockMeta.SOURCE).with(GoodsStockMeta.WAREHOUSE).with(GoodsMeta.CATEGORY).with(GoodsStockMeta.BRAND).with(GoodsMeta.MANUFACTURER).execute();
        assetStockGoodsTranfer.setGoodsList(list.getList());
        result.success(true).data(assetStockGoodsTranfer);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetStockGoodsTranferServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStockGoodsTranferServiceProxy.GET_BY_IDS)
    public Result<List<AssetStockGoodsTranfer>> getByIds(List<String> ids) {
        Result<List<AssetStockGoodsTranfer>> result = new Result<>();
        List<AssetStockGoodsTranfer> list = assetStockGoodsTranferService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.OWNER_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.WAREHOUSE_OUT_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.WAREHOUSE_IN_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.ATTACH_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.CHS_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.CHS_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.CHS_VERSION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.CHANGE_INSTANCE_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.SUMMARY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.LATEST_APPROVER_ID, value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.LATEST_APPROVER_NAME, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.NEXT_APPROVER_IDS, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.NEXT_APPROVER_NAMES, value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.APPROVAL_OPINION, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetStockGoodsTranferVOMeta.PAGE_INDEX, AssetStockGoodsTranferVOMeta.PAGE_SIZE })
    @SentinelResource(value = AssetStockGoodsTranferServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStockGoodsTranferServiceProxy.QUERY_LIST)
    public Result<List<AssetStockGoodsTranfer>> queryList(AssetStockGoodsTranferVO sample) {
        Result<List<AssetStockGoodsTranfer>> result = new Result<>();
        List<AssetStockGoodsTranfer> list = assetStockGoodsTranferService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.OWNER_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.WAREHOUSE_OUT_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.WAREHOUSE_IN_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.ATTACH_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.CHS_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.CHS_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.CHS_VERSION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.CHANGE_INSTANCE_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.SUMMARY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.LATEST_APPROVER_ID, value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.LATEST_APPROVER_NAME, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.NEXT_APPROVER_IDS, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.NEXT_APPROVER_NAMES, value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.APPROVAL_OPINION, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = AssetStockGoodsTranferServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStockGoodsTranferServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<AssetStockGoodsTranfer>> queryPagedList(AssetStockGoodsTranferVO sample) {
        Result<PagedList<AssetStockGoodsTranfer>> result = new Result<>();
        PagedList<AssetStockGoodsTranfer> list = assetStockGoodsTranferService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        assetStockGoodsTranferService.dao().fill(list).with("originator").with(AssetStockGoodsTranferMeta.WAREHOUSE_IN).with(AssetStockGoodsTranferMeta.WAREHOUSE_OUT).execute();
        List<Employee> originatorList = CollectorUtil.collectList(list, AssetStockGoodsTranfer::getOriginator);
        assetStockGoodsTranferService.dao().join(originatorList, Person.class);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????
     */
    @ApiOperation(value = "??????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockGoodsTranferVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 13)
    @SentinelResource(value = AssetStockGoodsTranferServiceProxy.CONFIRM_OPERATION, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetStockGoodsTranferServiceProxy.CONFIRM_OPERATION)
    public Result confirmOperation(String id) {
        return assetStockGoodsTranferService.confirmOperation(id);
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = AssetStockGoodsTranferServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetStockGoodsTranferServiceProxy.EXPORT_EXCEL)
    public void exportExcel(AssetStockGoodsTranferVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = assetStockGoodsTranferService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = AssetStockGoodsTranferServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetStockGoodsTranferServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = assetStockGoodsTranferService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = AssetStockGoodsTranferServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetStockGoodsTranferServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = assetStockGoodsTranferService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
