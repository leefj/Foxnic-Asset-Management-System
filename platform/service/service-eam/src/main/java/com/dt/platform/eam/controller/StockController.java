package com.dt.platform.eam.controller;

import java.util.List;
import com.dt.platform.domain.eam.meta.AssetDataChangeVOMeta;
import com.dt.platform.proxy.eam.AssetDataChangeServiceProxy;
import org.github.foxnic.web.domain.changes.ProcessApproveVO;
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
import com.dt.platform.proxy.eam.StockServiceProxy;
import com.dt.platform.domain.eam.meta.StockVOMeta;
import com.dt.platform.domain.eam.Stock;
import com.dt.platform.domain.eam.StockVO;
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
import com.dt.platform.domain.eam.meta.StockMeta;
import java.math.BigDecimal;
import com.dt.platform.domain.eam.Asset;
import com.dt.platform.domain.eam.Supplier;
import org.github.foxnic.web.domain.changes.ChangeInstance;
import org.github.foxnic.web.domain.hrm.Employee;
import org.github.foxnic.web.domain.hrm.Organization;
import org.github.foxnic.web.domain.system.DictItem;
import com.dt.platform.domain.eam.Warehouse;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IStockService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2021-11-22 13:52:26
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("EamStockController")
public class StockController extends SuperController {

    @Autowired
    private IStockService stockService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = StockVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.OWNER_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.STOCK_BATCH_CODE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.STOCK_NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.SOURCE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.OWN_COMPANY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.SUPPLIER_ID, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.WAREHOUSE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.AMOUNT, value = "?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = StockVOMeta.PURCHASE_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = StockVOMeta.MANAGER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.RECEIVER_USER_NAME, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = StockVOMeta.ATTACH_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.STOCK_NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.CHS_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.CHS_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.CHS_VERSION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.CHANGE_INSTANCE_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.SUMMARY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.LATEST_APPROVER_ID, value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.LATEST_APPROVER_NAME, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.NEXT_APPROVER_IDS, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.NEXT_APPROVER_NAMES, value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.APPROVAL_OPINION, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = StockServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(StockServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(StockVO stockVO) {
        Result result = stockService.insert(stockVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = StockVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = StockServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(StockServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = stockService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = StockVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = StockServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(StockServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = stockService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = StockVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.OWNER_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.STOCK_BATCH_CODE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.STOCK_NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.SOURCE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.OWN_COMPANY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.SUPPLIER_ID, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.WAREHOUSE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.AMOUNT, value = "?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = StockVOMeta.PURCHASE_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = StockVOMeta.MANAGER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.RECEIVER_USER_NAME, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = StockVOMeta.ATTACH_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.STOCK_NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.CHS_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.CHS_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.CHS_VERSION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.CHANGE_INSTANCE_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.SUMMARY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.LATEST_APPROVER_ID, value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.LATEST_APPROVER_NAME, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.NEXT_APPROVER_IDS, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.NEXT_APPROVER_NAMES, value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.APPROVAL_OPINION, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { StockVOMeta.PAGE_INDEX, StockVOMeta.PAGE_SIZE, StockVOMeta.SEARCH_FIELD, StockVOMeta.FUZZY_FIELD, StockVOMeta.SEARCH_VALUE, StockVOMeta.DIRTY_FIELDS, StockVOMeta.SORT_FIELD, StockVOMeta.SORT_TYPE, StockVOMeta.IDS })
    @SentinelResource(value = StockServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(StockServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(StockVO stockVO) {
        Result result = stockService.update(stockVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = StockVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.OWNER_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.STOCK_BATCH_CODE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.STOCK_NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.SOURCE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.OWN_COMPANY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.SUPPLIER_ID, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.WAREHOUSE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.AMOUNT, value = "?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = StockVOMeta.PURCHASE_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = StockVOMeta.MANAGER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.RECEIVER_USER_NAME, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = StockVOMeta.ATTACH_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.STOCK_NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.CHS_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.CHS_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.CHS_VERSION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.CHANGE_INSTANCE_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.SUMMARY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.LATEST_APPROVER_ID, value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.LATEST_APPROVER_NAME, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.NEXT_APPROVER_IDS, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.NEXT_APPROVER_NAMES, value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.APPROVAL_OPINION, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { StockVOMeta.PAGE_INDEX, StockVOMeta.PAGE_SIZE, StockVOMeta.SEARCH_FIELD, StockVOMeta.FUZZY_FIELD, StockVOMeta.SEARCH_VALUE, StockVOMeta.DIRTY_FIELDS, StockVOMeta.SORT_FIELD, StockVOMeta.SORT_TYPE, StockVOMeta.IDS })
    @SentinelResource(value = StockServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(StockServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(StockVO stockVO) {
        Result result = stockService.save(stockVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = StockVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = StockServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(StockServiceProxy.GET_BY_ID)
    public Result<Stock> getById(String id) {
        Result<Stock> result = new Result<>();
        Stock stock = stockService.getById(id);
        // join ???????????????
        stockService.dao().fill(stock).with("ownerCompany").with("manager").with(StockMeta.WAREHOUSE).with(StockMeta.SOURCE).with(StockMeta.SUPPLIER).execute();
        result.success(true).data(stock);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = StockVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = StockServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(StockServiceProxy.GET_BY_IDS)
    public Result<List<Stock>> getByIds(List<String> ids) {
        Result<List<Stock>> result = new Result<>();
        List<Stock> list = stockService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = StockVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.OWNER_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.STOCK_BATCH_CODE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.STOCK_NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.SOURCE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.OWN_COMPANY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.SUPPLIER_ID, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.WAREHOUSE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.AMOUNT, value = "?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = StockVOMeta.PURCHASE_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = StockVOMeta.MANAGER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.RECEIVER_USER_NAME, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = StockVOMeta.ATTACH_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.STOCK_NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.CHS_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.CHS_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.CHS_VERSION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.CHANGE_INSTANCE_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.SUMMARY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.LATEST_APPROVER_ID, value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.LATEST_APPROVER_NAME, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.NEXT_APPROVER_IDS, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.NEXT_APPROVER_NAMES, value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.APPROVAL_OPINION, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { StockVOMeta.PAGE_INDEX, StockVOMeta.PAGE_SIZE })
    @SentinelResource(value = StockServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(StockServiceProxy.QUERY_LIST)
    public Result<List<Stock>> queryList(StockVO sample) {
        Result<List<Stock>> result = new Result<>();
        List<Stock> list = stockService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = StockVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.OWNER_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.STOCK_BATCH_CODE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.STOCK_NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.SOURCE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.OWN_COMPANY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.SUPPLIER_ID, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.WAREHOUSE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.AMOUNT, value = "?????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = StockVOMeta.PURCHASE_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = StockVOMeta.MANAGER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.RECEIVER_USER_NAME, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = StockVOMeta.ATTACH_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.STOCK_NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.CHS_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.CHS_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.CHS_VERSION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.CHANGE_INSTANCE_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.SUMMARY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.LATEST_APPROVER_ID, value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.LATEST_APPROVER_NAME, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.NEXT_APPROVER_IDS, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.NEXT_APPROVER_NAMES, value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = StockVOMeta.APPROVAL_OPINION, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = StockServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(StockServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<Stock>> queryPagedList(StockVO sample) {
        Result<PagedList<Stock>> result = new Result<>();
        PagedList<Stock> list = stockService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        stockService.dao().fill(list).with("ownerCompany").with("manager").with(StockMeta.WAREHOUSE).with(StockMeta.SOURCE).with(StockMeta.SUPPLIER).execute();
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????
     */
    @ApiOperation(value = "??????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = StockVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 10)
    @SentinelResource(value = StockServiceProxy.CONFIRM_OPERATION, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(StockServiceProxy.CONFIRM_OPERATION)
    public Result confirmOperation(String id) {
        return stockService.confirmOperation(id);
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = StockServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(StockServiceProxy.EXPORT_EXCEL)
    public void exportExcel(StockVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = stockService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = StockServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(StockServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = stockService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = StockServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(StockServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = stockService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
