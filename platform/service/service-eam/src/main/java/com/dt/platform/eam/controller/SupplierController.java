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
import com.dt.platform.proxy.eam.SupplierServiceProxy;
import com.dt.platform.domain.eam.meta.SupplierVOMeta;
import com.dt.platform.domain.eam.Supplier;
import com.dt.platform.domain.eam.SupplierVO;
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
import com.dt.platform.domain.eam.meta.SupplierMeta;
import org.github.foxnic.web.domain.system.DictItem;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.ISupplierService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-07-30 08:31:34
 */
@Api(tags = "?????????")
@ApiSort(0)
@RestController("EamSupplierController")
public class SupplierController extends SuperController {

    @Autowired
    private ISupplierService supplierService;

    /**
     * ???????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SupplierVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "471976330536157184"),
		@ApiImplicitParam(name = SupplierVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SupplierVOMeta.SUPPLIER_NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????BB????????????"),
		@ApiImplicitParam(name = SupplierVOMeta.BUSINESS_CONTACTS, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SupplierVOMeta.GRADE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SupplierVOMeta.BUSINESS_CONTACTS_INFO, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SupplierVOMeta.AFTER_SALES_CONTACTS, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SupplierVOMeta.AFTER_SALES_CONTACTS_INFO, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SupplierVOMeta.UNIT_CODE, value = "????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SupplierVOMeta.ADDRESS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SupplierVOMeta.SUPPLIER_NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "??????BB????????????")
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = SupplierServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SupplierServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(SupplierVO supplierVO) {
        Result result = supplierService.insert(supplierVO, false);
        return result;
    }

    /**
     * ???????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SupplierVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "471976330536157184")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = SupplierServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SupplierServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  supplierService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = supplierService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SupplierVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = SupplierServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SupplierServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = supplierService.hasRefers(ids);
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
            Result result = supplierService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = supplierService.deleteByIdsLogical(canDeleteIds);
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
     * ???????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SupplierVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "471976330536157184"),
		@ApiImplicitParam(name = SupplierVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SupplierVOMeta.SUPPLIER_NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????BB????????????"),
		@ApiImplicitParam(name = SupplierVOMeta.BUSINESS_CONTACTS, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SupplierVOMeta.GRADE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SupplierVOMeta.BUSINESS_CONTACTS_INFO, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SupplierVOMeta.AFTER_SALES_CONTACTS, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SupplierVOMeta.AFTER_SALES_CONTACTS_INFO, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SupplierVOMeta.UNIT_CODE, value = "????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SupplierVOMeta.ADDRESS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SupplierVOMeta.SUPPLIER_NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "??????BB????????????")
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { SupplierVOMeta.PAGE_INDEX, SupplierVOMeta.PAGE_SIZE, SupplierVOMeta.SEARCH_FIELD, SupplierVOMeta.FUZZY_FIELD, SupplierVOMeta.SEARCH_VALUE, SupplierVOMeta.DIRTY_FIELDS, SupplierVOMeta.SORT_FIELD, SupplierVOMeta.SORT_TYPE, SupplierVOMeta.IDS })
    @SentinelResource(value = SupplierServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SupplierServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(SupplierVO supplierVO) {
        Result result = supplierService.update(supplierVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ???????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SupplierVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "471976330536157184"),
		@ApiImplicitParam(name = SupplierVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SupplierVOMeta.SUPPLIER_NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????BB????????????"),
		@ApiImplicitParam(name = SupplierVOMeta.BUSINESS_CONTACTS, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SupplierVOMeta.GRADE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SupplierVOMeta.BUSINESS_CONTACTS_INFO, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SupplierVOMeta.AFTER_SALES_CONTACTS, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SupplierVOMeta.AFTER_SALES_CONTACTS_INFO, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SupplierVOMeta.UNIT_CODE, value = "????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SupplierVOMeta.ADDRESS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SupplierVOMeta.SUPPLIER_NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "??????BB????????????")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { SupplierVOMeta.PAGE_INDEX, SupplierVOMeta.PAGE_SIZE, SupplierVOMeta.SEARCH_FIELD, SupplierVOMeta.FUZZY_FIELD, SupplierVOMeta.SEARCH_VALUE, SupplierVOMeta.DIRTY_FIELDS, SupplierVOMeta.SORT_FIELD, SupplierVOMeta.SORT_TYPE, SupplierVOMeta.IDS })
    @SentinelResource(value = SupplierServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SupplierServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(SupplierVO supplierVO) {
        Result result = supplierService.save(supplierVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ???????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SupplierVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = SupplierServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SupplierServiceProxy.GET_BY_ID)
    public Result<Supplier> getById(String id) {
        Result<Supplier> result = new Result<>();
        Supplier supplier = supplierService.getById(id);
        // join ???????????????
        supplierService.dao().fill(supplier).with(SupplierMeta.GRADE_DICT).execute();
        result.success(true).data(supplier);
        return result;
    }

    /**
     * ????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SupplierVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = SupplierServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SupplierServiceProxy.GET_BY_IDS)
    public Result<List<Supplier>> getByIds(List<String> ids) {
        Result<List<Supplier>> result = new Result<>();
        List<Supplier> list = supplierService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ???????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SupplierVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "471976330536157184"),
		@ApiImplicitParam(name = SupplierVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SupplierVOMeta.SUPPLIER_NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????BB????????????"),
		@ApiImplicitParam(name = SupplierVOMeta.BUSINESS_CONTACTS, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SupplierVOMeta.GRADE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SupplierVOMeta.BUSINESS_CONTACTS_INFO, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SupplierVOMeta.AFTER_SALES_CONTACTS, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SupplierVOMeta.AFTER_SALES_CONTACTS_INFO, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SupplierVOMeta.UNIT_CODE, value = "????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SupplierVOMeta.ADDRESS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SupplierVOMeta.SUPPLIER_NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "??????BB????????????")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { SupplierVOMeta.PAGE_INDEX, SupplierVOMeta.PAGE_SIZE })
    @SentinelResource(value = SupplierServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SupplierServiceProxy.QUERY_LIST)
    public Result<List<Supplier>> queryList(SupplierVO sample) {
        Result<List<Supplier>> result = new Result<>();
        List<Supplier> list = supplierService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SupplierVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "471976330536157184"),
		@ApiImplicitParam(name = SupplierVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SupplierVOMeta.SUPPLIER_NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????BB????????????"),
		@ApiImplicitParam(name = SupplierVOMeta.BUSINESS_CONTACTS, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SupplierVOMeta.GRADE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SupplierVOMeta.BUSINESS_CONTACTS_INFO, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SupplierVOMeta.AFTER_SALES_CONTACTS, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SupplierVOMeta.AFTER_SALES_CONTACTS_INFO, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SupplierVOMeta.UNIT_CODE, value = "????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SupplierVOMeta.ADDRESS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SupplierVOMeta.SUPPLIER_NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "??????BB????????????")
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = SupplierServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SupplierServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<Supplier>> queryPagedList(SupplierVO sample) {
        Result<PagedList<Supplier>> result = new Result<>();
        PagedList<Supplier> list = supplierService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        supplierService.dao().fill(list).with(SupplierMeta.GRADE_DICT).execute();
        result.success(true).data(list);
        return result;
    }
}
