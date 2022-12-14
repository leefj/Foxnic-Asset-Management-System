package com.dt.platform.eam.controller;

import java.util.List;
import com.dt.platform.domain.eam.*;
import com.dt.platform.domain.eam.meta.*;
import com.dt.platform.eam.service.IGoodsStockService;
import com.dt.platform.proxy.eam.AssetCollectionServiceProxy;
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
import com.dt.platform.proxy.eam.AssetStockGoodsInServiceProxy;
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
import java.math.BigDecimal;
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
import com.dt.platform.eam.service.IAssetStockGoodsInService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ??????????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-04-21 13:05:36
 */
@Api(tags = "???????????????")
@ApiSort(0)
@RestController("EamAssetStockGoodsInController")
public class AssetStockGoodsInController extends SuperController {

    @Autowired
    private IAssetStockGoodsInService assetStockGoodsInService;

    @Autowired
    private IGoodsStockService goodsStockService;

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "569508271568715776"),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.OWNER_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.BATCH_CODE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.STOCK_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "t1"),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.WAREHOUSE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "569147473092673536"),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.MANAGER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.RECEIVER_USER_NAME, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.AMOUNT, value = "?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.OWN_COMPANY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.SUPPLIER_NAME, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.SOURCE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "donation"),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.PURCHASE_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-04-12 12:00:00"),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.ATTACH_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.CHS_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.CHS_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.CHS_VERSION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.CHANGE_INSTANCE_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.SUMMARY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.LATEST_APPROVER_ID, value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.LATEST_APPROVER_NAME, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.NEXT_APPROVER_IDS, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.NEXT_APPROVER_NAMES, value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.APPROVAL_OPINION, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = AssetStockGoodsInServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStockGoodsInServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(AssetStockGoodsInVO assetStockGoodsInVO) {
        Result result = assetStockGoodsInService.insert(assetStockGoodsInVO, false);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "569508271568715776")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = AssetStockGoodsInServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStockGoodsInServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = assetStockGoodsInService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ??????????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "???????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetStockGoodsInServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStockGoodsInServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = assetStockGoodsInService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "569508271568715776"),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.OWNER_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.BATCH_CODE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.STOCK_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "t1"),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.WAREHOUSE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "569147473092673536"),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.MANAGER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.RECEIVER_USER_NAME, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.AMOUNT, value = "?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.OWN_COMPANY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.SUPPLIER_NAME, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.SOURCE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "donation"),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.PURCHASE_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-04-12 12:00:00"),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.ATTACH_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.CHS_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.CHS_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.CHS_VERSION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.CHANGE_INSTANCE_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.SUMMARY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.LATEST_APPROVER_ID, value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.LATEST_APPROVER_NAME, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.NEXT_APPROVER_IDS, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.NEXT_APPROVER_NAMES, value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.APPROVAL_OPINION, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { AssetStockGoodsInVOMeta.PAGE_INDEX, AssetStockGoodsInVOMeta.PAGE_SIZE, AssetStockGoodsInVOMeta.SEARCH_FIELD, AssetStockGoodsInVOMeta.FUZZY_FIELD, AssetStockGoodsInVOMeta.SEARCH_VALUE, AssetStockGoodsInVOMeta.DIRTY_FIELDS, AssetStockGoodsInVOMeta.SORT_FIELD, AssetStockGoodsInVOMeta.SORT_TYPE, AssetStockGoodsInVOMeta.IDS })
    @SentinelResource(value = AssetStockGoodsInServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStockGoodsInServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(AssetStockGoodsInVO assetStockGoodsInVO) {
        Result result = assetStockGoodsInService.update(assetStockGoodsInVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "569508271568715776"),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.OWNER_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.BATCH_CODE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.STOCK_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "t1"),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.WAREHOUSE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "569147473092673536"),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.MANAGER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.RECEIVER_USER_NAME, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.AMOUNT, value = "?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.OWN_COMPANY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.SUPPLIER_NAME, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.SOURCE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "donation"),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.PURCHASE_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-04-12 12:00:00"),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.ATTACH_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.CHS_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.CHS_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.CHS_VERSION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.CHANGE_INSTANCE_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.SUMMARY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.LATEST_APPROVER_ID, value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.LATEST_APPROVER_NAME, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.NEXT_APPROVER_IDS, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.NEXT_APPROVER_NAMES, value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.APPROVAL_OPINION, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetStockGoodsInVOMeta.PAGE_INDEX, AssetStockGoodsInVOMeta.PAGE_SIZE, AssetStockGoodsInVOMeta.SEARCH_FIELD, AssetStockGoodsInVOMeta.FUZZY_FIELD, AssetStockGoodsInVOMeta.SEARCH_VALUE, AssetStockGoodsInVOMeta.DIRTY_FIELDS, AssetStockGoodsInVOMeta.SORT_FIELD, AssetStockGoodsInVOMeta.SORT_TYPE, AssetStockGoodsInVOMeta.IDS })
    @SentinelResource(value = AssetStockGoodsInServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStockGoodsInServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(AssetStockGoodsInVO assetStockGoodsInVO) {
        Result result = assetStockGoodsInService.save(assetStockGoodsInVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = AssetStockGoodsInServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStockGoodsInServiceProxy.GET_BY_ID)
    public Result<AssetStockGoodsIn> getById(String id) {
        Result<AssetStockGoodsIn> result = new Result<>();
        AssetStockGoodsIn assetStockGoodsIn = assetStockGoodsInService.getById(id);
        // join ???????????????
        assetStockGoodsInService.dao().fill(assetStockGoodsIn).with("ownerCompany").with("originator").with("manager").with(AssetStockGoodsInMeta.GOODS_LIST).with(AssetStockGoodsInMeta.WAREHOUSE).with(AssetStockGoodsInMeta.SOURCE).with(AssetStockGoodsInMeta.STOCK_TYPE_DICT).execute();
        assetStockGoodsInService.dao().join(assetStockGoodsIn.getOriginator(), Person.class);
        assetStockGoodsInService.dao().join(assetStockGoodsIn.getManager(), Person.class);
        GoodsStockVO vo = new GoodsStockVO();
        vo.setPageIndex(1);
        vo.setPageSize(1000);
        vo.setOwnerTmpId(id);
        PagedList<GoodsStock> list = goodsStockService.queryPagedListBySelected(vo, "", "reset");
        goodsStockService.dao().fill(list).with("ownerCompany").with("useOrganization").with("manager").with("originator").with(GoodsStockMeta.CATEGORY).with(GoodsStockMeta.GOODS).with(GoodsStockMeta.SOURCE).with(GoodsStockMeta.WAREHOUSE).with(GoodsMeta.CATEGORY).with(GoodsStockMeta.BRAND).with(GoodsMeta.MANUFACTURER).execute();
        assetStockGoodsIn.setGoodsList(list.getList());
        result.success(true).data(assetStockGoodsIn);
        return result;
    }

    /**
     * ??????????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "???????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetStockGoodsInServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStockGoodsInServiceProxy.GET_BY_IDS)
    public Result<List<AssetStockGoodsIn>> getByIds(List<String> ids) {
        Result<List<AssetStockGoodsIn>> result = new Result<>();
        List<AssetStockGoodsIn> list = assetStockGoodsInService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "569508271568715776"),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.OWNER_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.BATCH_CODE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.STOCK_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "t1"),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.WAREHOUSE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "569147473092673536"),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.MANAGER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.RECEIVER_USER_NAME, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.AMOUNT, value = "?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.OWN_COMPANY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.SUPPLIER_NAME, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.SOURCE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "donation"),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.PURCHASE_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-04-12 12:00:00"),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.ATTACH_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.CHS_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.CHS_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.CHS_VERSION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.CHANGE_INSTANCE_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.SUMMARY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.LATEST_APPROVER_ID, value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.LATEST_APPROVER_NAME, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.NEXT_APPROVER_IDS, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.NEXT_APPROVER_NAMES, value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.APPROVAL_OPINION, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetStockGoodsInVOMeta.PAGE_INDEX, AssetStockGoodsInVOMeta.PAGE_SIZE })
    @SentinelResource(value = AssetStockGoodsInServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStockGoodsInServiceProxy.QUERY_LIST)
    public Result<List<AssetStockGoodsIn>> queryList(AssetStockGoodsInVO sample) {
        Result<List<AssetStockGoodsIn>> result = new Result<>();
        List<AssetStockGoodsIn> list = assetStockGoodsInService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ???????????????????????????
     */
    @ApiOperation(value = "???????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "569508271568715776"),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.OWNER_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.BATCH_CODE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.STOCK_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "t1"),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.WAREHOUSE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "569147473092673536"),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.MANAGER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.RECEIVER_USER_NAME, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.AMOUNT, value = "?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.OWN_COMPANY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.SUPPLIER_NAME, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.SOURCE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "donation"),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.PURCHASE_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-04-12 12:00:00"),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.ATTACH_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.CHS_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.CHS_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.CHS_VERSION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.CHANGE_INSTANCE_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.SUMMARY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.LATEST_APPROVER_ID, value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.LATEST_APPROVER_NAME, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.NEXT_APPROVER_IDS, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.NEXT_APPROVER_NAMES, value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.APPROVAL_OPINION, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = AssetStockGoodsInServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetStockGoodsInServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<AssetStockGoodsIn>> queryPagedList(AssetStockGoodsInVO sample) {
        Result<PagedList<AssetStockGoodsIn>> result = new Result<>();
        PagedList<AssetStockGoodsIn> list = assetStockGoodsInService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        assetStockGoodsInService.dao().fill(list).with("ownerCompany").with("originator").with("manager").with(AssetStockGoodsInMeta.GOODS_LIST).with(AssetStockGoodsInMeta.WAREHOUSE).with(AssetStockGoodsInMeta.SOURCE).with(AssetStockGoodsInMeta.STOCK_TYPE_DICT).execute();
        List<Employee> originatorList = CollectorUtil.collectList(list, AssetStockGoodsIn::getOriginator);
        assetStockGoodsInService.dao().join(originatorList, Person.class);
        List<Employee> managerList = CollectorUtil.collectList(list, AssetStockGoodsIn::getManager);
        assetStockGoodsInService.dao().join(managerList, Person.class);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????
     */
    @ApiOperation(value = "??????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetStockGoodsInVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 13)
    @SentinelResource(value = AssetStockGoodsInServiceProxy.CONFIRM_OPERATION, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetStockGoodsInServiceProxy.CONFIRM_OPERATION)
    public Result confirmOperation(String id) {
        return assetStockGoodsInService.confirmOperation(id);
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = AssetStockGoodsInServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetStockGoodsInServiceProxy.EXPORT_EXCEL)
    public void exportExcel(AssetStockGoodsInVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = assetStockGoodsInService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = AssetStockGoodsInServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetStockGoodsInServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = assetStockGoodsInService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = AssetStockGoodsInServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetStockGoodsInServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = assetStockGoodsInService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
