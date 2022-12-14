package com.dt.platform.eam.controller;

import java.util.ArrayList;
import java.util.List;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.alibaba.fastjson.JSONArray;
import com.dt.platform.constants.enums.eam.AssetOperateEnum;
import com.dt.platform.constants.enums.eam.AssetStockGoodsOwnerEnum;
import com.dt.platform.constants.enums.eam.AssetStockTypeEnum;
import com.dt.platform.domain.eam.*;
import com.dt.platform.proxy.eam.AssetReportServiceProxy;
import com.github.foxnic.commons.collection.CollectorUtil;
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
import com.dt.platform.proxy.eam.GoodsStockServiceProxy;
import com.dt.platform.domain.eam.meta.GoodsStockVOMeta;
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
import com.dt.platform.domain.eam.meta.GoodsStockMeta;
import java.math.BigDecimal;
import org.github.foxnic.web.domain.hrm.Organization;
import org.github.foxnic.web.domain.system.DictItem;
import org.github.foxnic.web.domain.hrm.Employee;
import com.dt.platform.domain.eam.meta.GoodsMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IGoodsStockService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-04-21 11:41:53
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("EamGoodsStockController")
public class GoodsStockController extends SuperController {

    @Autowired
    private IGoodsStockService goodsStockService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = GoodsStockVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "569477481375989760"),
		@ApiImplicitParam(name = GoodsStockVOMeta.OWNER_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.OWNER_TMP_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.OWNER_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.OWNER_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.CATEGORY_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "479383892382449664"),
		@ApiImplicitParam(name = GoodsStockVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = GoodsStockVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "?????????"),
		@ApiImplicitParam(name = GoodsStockVOMeta.MODEL, value = "????????????", required = false, dataTypeClass = String.class, example = "?????????model"),
		@ApiImplicitParam(name = GoodsStockVOMeta.CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = GoodsStockVOMeta.BAR_CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "121212"),
		@ApiImplicitParam(name = GoodsStockVOMeta.MANUFACTURER_ID, value = "??????", required = false, dataTypeClass = String.class, example = "471669992140570624"),
		@ApiImplicitParam(name = GoodsStockVOMeta.BRAND_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "569147035945533440"),
		@ApiImplicitParam(name = GoodsStockVOMeta.UNIT_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = GoodsStockVOMeta.UNIT, value = "????????????", required = false, dataTypeClass = String.class, example = "???"),
		@ApiImplicitParam(name = GoodsStockVOMeta.STOCK_MIN, value = "??????????????????", required = false, dataTypeClass = BigDecimal.class, example = "0"),
		@ApiImplicitParam(name = GoodsStockVOMeta.STOCK_MAX, value = "??????????????????", required = false, dataTypeClass = BigDecimal.class, example = "500"),
		@ApiImplicitParam(name = GoodsStockVOMeta.STOCK_SECURITY, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "200"),
		@ApiImplicitParam(name = GoodsStockVOMeta.PICTURE_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.BATCH_CODE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.OWN_COMPANY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.USE_ORG_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.SUPPLIER_NAME, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.WAREHOUSE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.SOURCE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.GOODS_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.STOCK_IN_NUMBER, value = "???????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = GoodsStockVOMeta.STOCK_CUR_NUMBER, value = "??????????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = GoodsStockVOMeta.AMOUNT, value = "?????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = GoodsStockVOMeta.MANAGER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.STORAGE_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = GoodsStockServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(GoodsStockServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(GoodsStockVO goodsStockVO) {
        Result result = goodsStockService.insert(goodsStockVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = GoodsStockVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "569477481375989760")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = GoodsStockServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(GoodsStockServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = goodsStockService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = GoodsStockVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = GoodsStockServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(GoodsStockServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = goodsStockService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = GoodsStockVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "569477481375989760"),
		@ApiImplicitParam(name = GoodsStockVOMeta.OWNER_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.OWNER_TMP_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.OWNER_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.OWNER_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.CATEGORY_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "479383892382449664"),
		@ApiImplicitParam(name = GoodsStockVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = GoodsStockVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "?????????"),
		@ApiImplicitParam(name = GoodsStockVOMeta.MODEL, value = "????????????", required = false, dataTypeClass = String.class, example = "?????????model"),
		@ApiImplicitParam(name = GoodsStockVOMeta.CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = GoodsStockVOMeta.BAR_CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "121212"),
		@ApiImplicitParam(name = GoodsStockVOMeta.MANUFACTURER_ID, value = "??????", required = false, dataTypeClass = String.class, example = "471669992140570624"),
		@ApiImplicitParam(name = GoodsStockVOMeta.BRAND_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "569147035945533440"),
		@ApiImplicitParam(name = GoodsStockVOMeta.UNIT_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = GoodsStockVOMeta.UNIT, value = "????????????", required = false, dataTypeClass = String.class, example = "???"),
		@ApiImplicitParam(name = GoodsStockVOMeta.STOCK_MIN, value = "??????????????????", required = false, dataTypeClass = BigDecimal.class, example = "0"),
		@ApiImplicitParam(name = GoodsStockVOMeta.STOCK_MAX, value = "??????????????????", required = false, dataTypeClass = BigDecimal.class, example = "500"),
		@ApiImplicitParam(name = GoodsStockVOMeta.STOCK_SECURITY, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "200"),
		@ApiImplicitParam(name = GoodsStockVOMeta.PICTURE_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.BATCH_CODE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.OWN_COMPANY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.USE_ORG_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.SUPPLIER_NAME, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.WAREHOUSE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.SOURCE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.GOODS_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.STOCK_IN_NUMBER, value = "???????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = GoodsStockVOMeta.STOCK_CUR_NUMBER, value = "??????????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = GoodsStockVOMeta.AMOUNT, value = "?????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = GoodsStockVOMeta.MANAGER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.STORAGE_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { GoodsStockVOMeta.PAGE_INDEX, GoodsStockVOMeta.PAGE_SIZE, GoodsStockVOMeta.SEARCH_FIELD, GoodsStockVOMeta.FUZZY_FIELD, GoodsStockVOMeta.SEARCH_VALUE, GoodsStockVOMeta.DIRTY_FIELDS, GoodsStockVOMeta.SORT_FIELD, GoodsStockVOMeta.SORT_TYPE, GoodsStockVOMeta.IDS })
    @SentinelResource(value = GoodsStockServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(GoodsStockServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(GoodsStockVO goodsStockVO) {
        Result result = goodsStockService.update(goodsStockVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = GoodsStockVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "569477481375989760"),
		@ApiImplicitParam(name = GoodsStockVOMeta.OWNER_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.OWNER_TMP_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.OWNER_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.OWNER_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.CATEGORY_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "479383892382449664"),
		@ApiImplicitParam(name = GoodsStockVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = GoodsStockVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "?????????"),
		@ApiImplicitParam(name = GoodsStockVOMeta.MODEL, value = "????????????", required = false, dataTypeClass = String.class, example = "?????????model"),
		@ApiImplicitParam(name = GoodsStockVOMeta.CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = GoodsStockVOMeta.BAR_CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "121212"),
		@ApiImplicitParam(name = GoodsStockVOMeta.MANUFACTURER_ID, value = "??????", required = false, dataTypeClass = String.class, example = "471669992140570624"),
		@ApiImplicitParam(name = GoodsStockVOMeta.BRAND_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "569147035945533440"),
		@ApiImplicitParam(name = GoodsStockVOMeta.UNIT_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = GoodsStockVOMeta.UNIT, value = "????????????", required = false, dataTypeClass = String.class, example = "???"),
		@ApiImplicitParam(name = GoodsStockVOMeta.STOCK_MIN, value = "??????????????????", required = false, dataTypeClass = BigDecimal.class, example = "0"),
		@ApiImplicitParam(name = GoodsStockVOMeta.STOCK_MAX, value = "??????????????????", required = false, dataTypeClass = BigDecimal.class, example = "500"),
		@ApiImplicitParam(name = GoodsStockVOMeta.STOCK_SECURITY, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "200"),
		@ApiImplicitParam(name = GoodsStockVOMeta.PICTURE_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.BATCH_CODE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.OWN_COMPANY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.USE_ORG_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.SUPPLIER_NAME, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.WAREHOUSE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.SOURCE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.GOODS_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.STOCK_IN_NUMBER, value = "???????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = GoodsStockVOMeta.STOCK_CUR_NUMBER, value = "??????????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = GoodsStockVOMeta.AMOUNT, value = "?????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = GoodsStockVOMeta.MANAGER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.STORAGE_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { GoodsStockVOMeta.PAGE_INDEX, GoodsStockVOMeta.PAGE_SIZE, GoodsStockVOMeta.SEARCH_FIELD, GoodsStockVOMeta.FUZZY_FIELD, GoodsStockVOMeta.SEARCH_VALUE, GoodsStockVOMeta.DIRTY_FIELDS, GoodsStockVOMeta.SORT_FIELD, GoodsStockVOMeta.SORT_TYPE, GoodsStockVOMeta.IDS })
    @SentinelResource(value = GoodsStockServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(GoodsStockServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(GoodsStockVO goodsStockVO) {
        Result result = goodsStockService.save(goodsStockVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = GoodsStockVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = GoodsStockServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(GoodsStockServiceProxy.GET_BY_ID)
    public Result<GoodsStock> getById(String id) {
        Result<GoodsStock> result = new Result<>();
        GoodsStock goodsStock = goodsStockService.getById(id);
        // join ???????????????
        goodsStockService.dao().fill(goodsStock).with("ownerCompany").with("useOrganization").with("manager").with("originator").with(GoodsStockMeta.CATEGORY).with(GoodsStockMeta.GOODS).with(GoodsStockMeta.SOURCE).with(GoodsStockMeta.WAREHOUSE).with(GoodsMeta.CATEGORY).with(GoodsStockMeta.BRAND).with(GoodsMeta.MANUFACTURER).execute();
        result.success(true).data(goodsStock);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = GoodsStockVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = GoodsStockServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(GoodsStockServiceProxy.GET_BY_IDS)
    public Result<List<GoodsStock>> getByIds(List<String> ids) {
        Result<List<GoodsStock>> result = new Result<>();
        List<GoodsStock> list = goodsStockService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = GoodsStockVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "569477481375989760"),
		@ApiImplicitParam(name = GoodsStockVOMeta.OWNER_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.OWNER_TMP_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.OWNER_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.OWNER_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.CATEGORY_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "479383892382449664"),
		@ApiImplicitParam(name = GoodsStockVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = GoodsStockVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "?????????"),
		@ApiImplicitParam(name = GoodsStockVOMeta.MODEL, value = "????????????", required = false, dataTypeClass = String.class, example = "?????????model"),
		@ApiImplicitParam(name = GoodsStockVOMeta.CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = GoodsStockVOMeta.BAR_CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "121212"),
		@ApiImplicitParam(name = GoodsStockVOMeta.MANUFACTURER_ID, value = "??????", required = false, dataTypeClass = String.class, example = "471669992140570624"),
		@ApiImplicitParam(name = GoodsStockVOMeta.BRAND_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "569147035945533440"),
		@ApiImplicitParam(name = GoodsStockVOMeta.UNIT_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = GoodsStockVOMeta.UNIT, value = "????????????", required = false, dataTypeClass = String.class, example = "???"),
		@ApiImplicitParam(name = GoodsStockVOMeta.STOCK_MIN, value = "??????????????????", required = false, dataTypeClass = BigDecimal.class, example = "0"),
		@ApiImplicitParam(name = GoodsStockVOMeta.STOCK_MAX, value = "??????????????????", required = false, dataTypeClass = BigDecimal.class, example = "500"),
		@ApiImplicitParam(name = GoodsStockVOMeta.STOCK_SECURITY, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "200"),
		@ApiImplicitParam(name = GoodsStockVOMeta.PICTURE_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.BATCH_CODE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.OWN_COMPANY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.USE_ORG_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.SUPPLIER_NAME, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.WAREHOUSE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.SOURCE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.GOODS_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.STOCK_IN_NUMBER, value = "???????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = GoodsStockVOMeta.STOCK_CUR_NUMBER, value = "??????????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = GoodsStockVOMeta.AMOUNT, value = "?????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = GoodsStockVOMeta.MANAGER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.STORAGE_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { GoodsStockVOMeta.PAGE_INDEX, GoodsStockVOMeta.PAGE_SIZE })
    @SentinelResource(value = GoodsStockServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(GoodsStockServiceProxy.QUERY_LIST)
    public Result<List<GoodsStock>> queryList(GoodsStockVO sample) {
        Result<List<GoodsStock>> result = new Result<>();
        List<GoodsStock> list = goodsStockService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = GoodsStockVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "569477481375989760"),
		@ApiImplicitParam(name = GoodsStockVOMeta.OWNER_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.OWNER_TMP_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.OWNER_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.OWNER_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.CATEGORY_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "479383892382449664"),
		@ApiImplicitParam(name = GoodsStockVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = GoodsStockVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "?????????"),
		@ApiImplicitParam(name = GoodsStockVOMeta.MODEL, value = "????????????", required = false, dataTypeClass = String.class, example = "?????????model"),
		@ApiImplicitParam(name = GoodsStockVOMeta.CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = GoodsStockVOMeta.BAR_CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "121212"),
		@ApiImplicitParam(name = GoodsStockVOMeta.MANUFACTURER_ID, value = "??????", required = false, dataTypeClass = String.class, example = "471669992140570624"),
		@ApiImplicitParam(name = GoodsStockVOMeta.BRAND_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "569147035945533440"),
		@ApiImplicitParam(name = GoodsStockVOMeta.UNIT_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = GoodsStockVOMeta.UNIT, value = "????????????", required = false, dataTypeClass = String.class, example = "???"),
		@ApiImplicitParam(name = GoodsStockVOMeta.STOCK_MIN, value = "??????????????????", required = false, dataTypeClass = BigDecimal.class, example = "0"),
		@ApiImplicitParam(name = GoodsStockVOMeta.STOCK_MAX, value = "??????????????????", required = false, dataTypeClass = BigDecimal.class, example = "500"),
		@ApiImplicitParam(name = GoodsStockVOMeta.STOCK_SECURITY, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "200"),
		@ApiImplicitParam(name = GoodsStockVOMeta.PICTURE_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.BATCH_CODE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.OWN_COMPANY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.USE_ORG_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.SUPPLIER_NAME, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.WAREHOUSE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.SOURCE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.GOODS_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.STOCK_IN_NUMBER, value = "???????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = GoodsStockVOMeta.STOCK_CUR_NUMBER, value = "??????????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = GoodsStockVOMeta.AMOUNT, value = "?????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = GoodsStockVOMeta.MANAGER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.STORAGE_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = GoodsStockVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = GoodsStockServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(GoodsStockServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<GoodsStock>> queryPagedList(GoodsStockVO sample) {
        Result<PagedList<GoodsStock>> result = new Result<>();
        PagedList<GoodsStock> list = goodsStockService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        goodsStockService.dao().fill(list).with("ownerCompany").with("useOrganization").with("manager").with("originator").with(GoodsStockMeta.CATEGORY).with(GoodsStockMeta.GOODS).with(GoodsStockMeta.SOURCE).with(GoodsStockMeta.WAREHOUSE).with(GoodsMeta.CATEGORY).with(GoodsStockMeta.BRAND).with(GoodsMeta.MANUFACTURER).execute();
        result.success(true).data(list);
        List<Employee> originatorList = CollectorUtil.collectList(list, GoodsStock::getOriginator);
        goodsStockService.dao().join(originatorList, Person.class);
        return result;
    }

    @ApiOperationSupport(order = 9)
    @SentinelResource(value = GoodsStockServiceProxy.QUERY_PAGED_LIST_BY_SELECTED, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(GoodsStockServiceProxy.QUERY_PAGED_LIST_BY_SELECTED)
    public Result<PagedList<GoodsStock>> queryPagedListBySelected(GoodsStockVO sample, String operType, String dataType) {
        Result<PagedList<GoodsStock>> result = new Result<>();
        PagedList<GoodsStock> list = goodsStockService.queryPagedListBySelected(sample, operType, dataType);
        // join ???????????????
        goodsStockService.dao().fill(list).with("ownerCompany").with("useOrganization").with("manager").with("originator").with(GoodsStockMeta.CATEGORY).with(GoodsStockMeta.GOODS).with(GoodsStockMeta.SOURCE).with(GoodsStockMeta.WAREHOUSE).with(GoodsMeta.CATEGORY).with(GoodsStockMeta.BRAND).with(GoodsMeta.MANUFACTURER).execute();
        List<Employee> originatorList = CollectorUtil.collectList(list, GoodsStock::getOriginator);
        goodsStockService.dao().join(originatorList, Person.class);
        result.success(true).data(list);
        return result;
    }

    @ApiOperationSupport(order = 9)
    @SentinelResource(value = GoodsStockServiceProxy.QUERY_PAGED_LIST_BY_SELECT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(GoodsStockServiceProxy.QUERY_PAGED_LIST_BY_SELECT)
    public Result<PagedList<GoodsStock>> queryPagedListBySelect(GoodsStockVO sample, String assetSearcbContent) {
        Result<PagedList<GoodsStock>> result = new Result<>();
        PagedList<GoodsStock> list = goodsStockService.queryPagedListBySelect(sample, assetSearcbContent);
        // join ???????????????
        goodsStockService.dao().fill(list).with("ownerCompany").with("useOrganization").with("manager").with("originator").with(GoodsStockMeta.CATEGORY).with(GoodsStockMeta.GOODS).with(GoodsStockMeta.SOURCE).with(GoodsStockMeta.WAREHOUSE).with(GoodsMeta.CATEGORY).with(GoodsStockMeta.BRAND).with(GoodsMeta.MANUFACTURER).execute();
        List<Employee> originatorList = CollectorUtil.collectList(list, GoodsStock::getOriginator);
        goodsStockService.dao().join(originatorList, Person.class);
        result.success(true).data(list);
        return result;
    }

    @ApiOperationSupport(order = 10)
    @SentinelResource(value = GoodsStockServiceProxy.QUERY_MIN_STOCK_WARN, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(GoodsStockServiceProxy.QUERY_MIN_STOCK_WARN)
    public Result<PagedList<GoodsStock>> queryMinStockWarn(GoodsStockVO sample) {
        Result<PagedList<GoodsStock>> result = new Result<>();
        PagedList<GoodsStock> list = goodsStockService.queryMinStockWarn(sample);
        result.success(true).data(list);
        return result;
    }

    @ApiOperationSupport(order = 11)
    @SentinelResource(value = GoodsStockServiceProxy.QUERY_MAX_STOCK_WARN, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(GoodsStockServiceProxy.QUERY_MAX_STOCK_WARN)
    public Result<PagedList<GoodsStock>> queryMaxStockWarn(GoodsStockVO sample) {
        Result<PagedList<GoodsStock>> result = new Result<>();
        PagedList<GoodsStock> list = goodsStockService.queryMaxStockWarn(sample);
        result.success(true).data(list);
        return result;
    }

    @ApiOperationSupport(order = 12)
    @SentinelResource(value = GoodsStockServiceProxy.QUERY_SECURITY_STOCK_WARN, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(GoodsStockServiceProxy.QUERY_SECURITY_STOCK_WARN)
    public Result<PagedList<GoodsStock>> querySecurityStockWarn(GoodsStockVO sample) {
        Result<PagedList<GoodsStock>> result = new Result<>();
        PagedList<GoodsStock> list = goodsStockService.querySecurityStockWarn(sample);
        result.success(true).data(list);
        return result;
    }

    @ApiOperationSupport(order = 5, ignoreParameters = { GoodsStockVOMeta.PAGE_INDEX, GoodsStockVOMeta.PAGE_SIZE, GoodsStockVOMeta.SEARCH_FIELD, GoodsStockVOMeta.FUZZY_FIELD, GoodsStockVOMeta.SEARCH_VALUE, GoodsStockVOMeta.DIRTY_FIELDS, GoodsStockVOMeta.SORT_FIELD, GoodsStockVOMeta.SORT_TYPE, GoodsStockVOMeta.IDS })
    @SentinelResource(value = GoodsStockServiceProxy.SAVE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(GoodsStockServiceProxy.SAVE_BY_IDS)
    public Result saveByIds(List<String> ids, String selectedCode, String ownerTmpId, String ownerType, String operType) {
        for (String id : ids) {
            GoodsStockVO e = new GoodsStockVO();
            // e.setOwnerCode(ownerType);
            e.setOwnerType(ownerType);
            if (AssetOperateEnum.EAM_ASSET_CONSUMABLES_GOODS_IN.code().equals(operType) || AssetOperateEnum.EAM_ASSET_STOCK_GOODS_IN.code().equals(operType) || AssetOperateEnum.EAM_ASSET_PART_GOODS_IN.code().equals(operType)) {
                // ????????????
                e.setGoodsId(id);
            } else {
                // ????????????????????????,????????????????????????
                GoodsStock goods = goodsStockService.getById(id);
                if (goods != null) {
                    e.setGoodsId(goods.getGoodsId());
                }
            }
            e.setRealStockId(id);
            if (!StringUtil.isBlank(ownerTmpId)) {
                e.setOwnerTmpId(ownerTmpId);
            }
            e.setSelectedCode(selectedCode);
            e.setNotes("");
            goodsStockService.insert(e, false);
        }
        return ErrorDesc.success();
    }

    @ApiOperation(value = "??????????????????")
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = GoodsStockServiceProxy.QUERY_GOODS_STOCK_REAL_ALL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(GoodsStockServiceProxy.QUERY_GOODS_STOCK_REAL_ALL)
    public Result queryGoodsStockRealAll(GoodsStockVO goodsStockVO) {
        Result<PagedList<GoodsStock>> result = new Result<>();
        PagedList<GoodsStock> list = goodsStockService.queryGoodsStockRealAll(goodsStockVO);
        // join ???????????????
        goodsStockService.dao().fill(list).with("ownerCompany").with("useOrganization").with("manager").with("originator").with(GoodsStockMeta.CATEGORY).with(GoodsStockMeta.GOODS).with(GoodsStockMeta.SOURCE).with(GoodsStockMeta.WAREHOUSE).with(GoodsMeta.CATEGORY).with(GoodsStockMeta.BRAND).with(GoodsMeta.MANUFACTURER).execute();
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = GoodsStockServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(GoodsStockServiceProxy.EXPORT_EXCEL)
    public void exportExcel(GoodsStockVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = goodsStockService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = GoodsStockServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(GoodsStockServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = goodsStockService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = GoodsStockServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(GoodsStockServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = goodsStockService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
