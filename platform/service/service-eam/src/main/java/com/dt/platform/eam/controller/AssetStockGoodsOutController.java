package com.dt.platform.eam.controller;

import java.util.List;
import com.dt.platform.domain.eam.*;
import com.dt.platform.domain.eam.meta.*;
import com.dt.platform.eam.service.IGoodsStockService;
import com.dt.platform.proxy.eam.AssetStockGoodsInServiceProxy;
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
import com.dt.platform.proxy.eam.AssetStockGoodsOutServiceProxy;
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
import org.github.foxnic.web.domain.system.DictItem;
import org.github.foxnic.web.domain.hrm.Organization;
import org.github.foxnic.web.domain.hrm.Employee;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IAssetStockGoodsOutService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-04-22 20:46:11
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("EamAssetStockGoodsOutController")
public class AssetStockGoodsOutController extends SuperController {

    @Autowired
    private IAssetStockGoodsOutService assetStockGoodsOutService;

    @Autowired
    private IGoodsStockService goodsStockService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.OWNER_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.STOCK_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.USE_OWN_COMPANY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.USE_ORGANIZATION_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.USE_USER_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.WAREHOUSE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.POSITION_DETAIL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.COLLECTION_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.ATTACH_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.CHS_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.CHS_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.CHS_VERSION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.CHANGE_INSTANCE_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.SUMMARY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.LATEST_APPROVER_ID, value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.LATEST_APPROVER_NAME, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.NEXT_APPROVER_IDS, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.NEXT_APPROVER_NAMES, value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.APPROVAL_OPINION, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = AssetStockGoodsOutServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStockGoodsOutServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(AssetStockGoodsOutVO assetStockGoodsOutVO) {
        Result result = assetStockGoodsOutService.insert(assetStockGoodsOutVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = AssetStockGoodsOutServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStockGoodsOutServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = assetStockGoodsOutService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetStockGoodsOutServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStockGoodsOutServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = assetStockGoodsOutService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.OWNER_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.STOCK_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.USE_OWN_COMPANY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.USE_ORGANIZATION_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.USE_USER_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.WAREHOUSE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.POSITION_DETAIL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.COLLECTION_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.ATTACH_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.CHS_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.CHS_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.CHS_VERSION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.CHANGE_INSTANCE_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.SUMMARY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.LATEST_APPROVER_ID, value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.LATEST_APPROVER_NAME, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.NEXT_APPROVER_IDS, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.NEXT_APPROVER_NAMES, value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.APPROVAL_OPINION, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { AssetStockGoodsOutVOMeta.PAGE_INDEX, AssetStockGoodsOutVOMeta.PAGE_SIZE, AssetStockGoodsOutVOMeta.SEARCH_FIELD, AssetStockGoodsOutVOMeta.FUZZY_FIELD, AssetStockGoodsOutVOMeta.SEARCH_VALUE, AssetStockGoodsOutVOMeta.DIRTY_FIELDS, AssetStockGoodsOutVOMeta.SORT_FIELD, AssetStockGoodsOutVOMeta.SORT_TYPE, AssetStockGoodsOutVOMeta.IDS })
    @SentinelResource(value = AssetStockGoodsOutServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStockGoodsOutServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(AssetStockGoodsOutVO assetStockGoodsOutVO) {
        Result result = assetStockGoodsOutService.update(assetStockGoodsOutVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.OWNER_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.STOCK_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.USE_OWN_COMPANY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.USE_ORGANIZATION_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.USE_USER_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.WAREHOUSE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.POSITION_DETAIL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.COLLECTION_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.ATTACH_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.CHS_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.CHS_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.CHS_VERSION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.CHANGE_INSTANCE_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.SUMMARY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.LATEST_APPROVER_ID, value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.LATEST_APPROVER_NAME, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.NEXT_APPROVER_IDS, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.NEXT_APPROVER_NAMES, value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.APPROVAL_OPINION, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetStockGoodsOutVOMeta.PAGE_INDEX, AssetStockGoodsOutVOMeta.PAGE_SIZE, AssetStockGoodsOutVOMeta.SEARCH_FIELD, AssetStockGoodsOutVOMeta.FUZZY_FIELD, AssetStockGoodsOutVOMeta.SEARCH_VALUE, AssetStockGoodsOutVOMeta.DIRTY_FIELDS, AssetStockGoodsOutVOMeta.SORT_FIELD, AssetStockGoodsOutVOMeta.SORT_TYPE, AssetStockGoodsOutVOMeta.IDS })
    @SentinelResource(value = AssetStockGoodsOutServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStockGoodsOutServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(AssetStockGoodsOutVO assetStockGoodsOutVO) {
        Result result = assetStockGoodsOutService.save(assetStockGoodsOutVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = AssetStockGoodsOutServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStockGoodsOutServiceProxy.GET_BY_ID)
    public Result<AssetStockGoodsOut> getById(String id) {
        Result<AssetStockGoodsOut> result = new Result<>();
        AssetStockGoodsOut assetStockGoodsOut = assetStockGoodsOutService.getById(id);
        // join ???????????????
        assetStockGoodsOutService.dao().fill(assetStockGoodsOut).with("useOwnCompany").with("useOrganization").with("originator").with("useUser").with(AssetStockGoodsOutMeta.WAREHOUSE).with(AssetStockGoodsOutMeta.STOCK_TYPE_DICT).execute();
        assetStockGoodsOutService.dao().join(assetStockGoodsOut.getOriginator(), Person.class);
        assetStockGoodsOutService.dao().join(assetStockGoodsOut.getUseUser(), Person.class);
        GoodsStockVO vo = new GoodsStockVO();
        vo.setPageIndex(1);
        vo.setPageSize(1000);
        vo.setOwnerTmpId(id);
        PagedList<GoodsStock> list = goodsStockService.queryPagedListBySelected(vo, "", "reset");
        goodsStockService.dao().fill(list).with("ownerCompany").with("useOrganization").with("manager").with("originator").with(GoodsStockMeta.CATEGORY).with(GoodsStockMeta.GOODS).with(GoodsStockMeta.SOURCE).with(GoodsStockMeta.WAREHOUSE).with(GoodsMeta.CATEGORY).with(GoodsStockMeta.BRAND).with(GoodsMeta.MANUFACTURER).execute();
        assetStockGoodsOut.setGoodsList(list.getList());
        result.success(true).data(assetStockGoodsOut);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetStockGoodsOutServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStockGoodsOutServiceProxy.GET_BY_IDS)
    public Result<List<AssetStockGoodsOut>> getByIds(List<String> ids) {
        Result<List<AssetStockGoodsOut>> result = new Result<>();
        List<AssetStockGoodsOut> list = assetStockGoodsOutService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.OWNER_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.STOCK_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.USE_OWN_COMPANY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.USE_ORGANIZATION_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.USE_USER_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.WAREHOUSE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.POSITION_DETAIL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.COLLECTION_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.ATTACH_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.CHS_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.CHS_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.CHS_VERSION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.CHANGE_INSTANCE_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.SUMMARY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.LATEST_APPROVER_ID, value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.LATEST_APPROVER_NAME, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.NEXT_APPROVER_IDS, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.NEXT_APPROVER_NAMES, value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.APPROVAL_OPINION, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetStockGoodsOutVOMeta.PAGE_INDEX, AssetStockGoodsOutVOMeta.PAGE_SIZE })
    @SentinelResource(value = AssetStockGoodsOutServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStockGoodsOutServiceProxy.QUERY_LIST)
    public Result<List<AssetStockGoodsOut>> queryList(AssetStockGoodsOutVO sample) {
        Result<List<AssetStockGoodsOut>> result = new Result<>();
        List<AssetStockGoodsOut> list = assetStockGoodsOutService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.OWNER_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.STOCK_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.USE_OWN_COMPANY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.USE_ORGANIZATION_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.USE_USER_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.WAREHOUSE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.POSITION_DETAIL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.COLLECTION_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.ATTACH_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.CHS_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.CHS_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.CHS_VERSION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.CHANGE_INSTANCE_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.SUMMARY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.LATEST_APPROVER_ID, value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.LATEST_APPROVER_NAME, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.NEXT_APPROVER_IDS, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.NEXT_APPROVER_NAMES, value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.APPROVAL_OPINION, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = AssetStockGoodsOutServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStockGoodsOutServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<AssetStockGoodsOut>> queryPagedList(AssetStockGoodsOutVO sample) {
        Result<PagedList<AssetStockGoodsOut>> result = new Result<>();
        PagedList<AssetStockGoodsOut> list = assetStockGoodsOutService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        assetStockGoodsOutService.dao().fill(list).with("useOwnCompany").with("useOrganization").with("originator").with("useUser").with(AssetStockGoodsOutMeta.WAREHOUSE).with(AssetStockGoodsOutMeta.STOCK_TYPE_DICT).execute();
        List<Employee> originatorList = CollectorUtil.collectList(list, AssetStockGoodsOut::getOriginator);
        assetStockGoodsOutService.dao().join(originatorList, Person.class);
        List<Employee> useUserList = CollectorUtil.collectList(list, AssetStockGoodsOut::getUseUser);
        assetStockGoodsOutService.dao().join(useUserList, Person.class);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????
     */
    @ApiOperation(value = "??????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockGoodsOutVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 13)
    @SentinelResource(value = AssetStockGoodsOutServiceProxy.CONFIRM_OPERATION, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetStockGoodsOutServiceProxy.CONFIRM_OPERATION)
    public Result confirmOperation(String id) {
        return assetStockGoodsOutService.confirmOperation(id);
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = AssetStockGoodsOutServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetStockGoodsOutServiceProxy.EXPORT_EXCEL)
    public void exportExcel(AssetStockGoodsOutVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = assetStockGoodsOutService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = AssetStockGoodsOutServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetStockGoodsOutServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = assetStockGoodsOutService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = AssetStockGoodsOutServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetStockGoodsOutServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = assetStockGoodsOutService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
