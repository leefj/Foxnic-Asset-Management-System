package com.dt.platform.eam.controller;

import java.util.List;
import com.dt.platform.domain.eam.*;
import com.dt.platform.domain.eam.meta.AssetStockGoodsInVOMeta;
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
import com.dt.platform.proxy.eam.AssetSoftwareMaintenanceServiceProxy;
import com.dt.platform.domain.eam.meta.AssetSoftwareMaintenanceVOMeta;
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
import com.dt.platform.domain.eam.meta.AssetSoftwareMaintenanceMeta;
import java.math.BigDecimal;
import org.github.foxnic.web.domain.hrm.Employee;
import org.github.foxnic.web.domain.hrm.Organization;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IAssetSoftwareMaintenanceService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-05-05 16:44:03
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("EamAssetSoftwareMaintenanceController")
public class AssetSoftwareMaintenanceController extends SuperController {

    @Autowired
    private IAssetSoftwareMaintenanceService assetSoftwareMaintenanceService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "574636732868198400"),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.MAINTAINER_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "472013393872551936"),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.USE_ORG_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "2"),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.MANAGER_ID, value = "???????????????", required = false, dataTypeClass = String.class, example = "558321538131034112"),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.CONTACTS, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.CONTACT_INFO, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.MAINTENANCE_COST, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.MAINTENANCE_START_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class, example = "2022-05-05 12:00:00"),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.MAINTENANCE_END_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class, example = "2022-05-05 12:00:00"),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.ATTACH_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = AssetSoftwareMaintenanceServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetSoftwareMaintenanceServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(AssetSoftwareMaintenanceVO assetSoftwareMaintenanceVO) {
        Result result = assetSoftwareMaintenanceService.insert(assetSoftwareMaintenanceVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "574636732868198400")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = AssetSoftwareMaintenanceServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetSoftwareMaintenanceServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = assetSoftwareMaintenanceService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetSoftwareMaintenanceServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetSoftwareMaintenanceServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = assetSoftwareMaintenanceService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "574636732868198400"),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.MAINTAINER_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "472013393872551936"),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.USE_ORG_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "2"),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.MANAGER_ID, value = "???????????????", required = false, dataTypeClass = String.class, example = "558321538131034112"),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.CONTACTS, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.CONTACT_INFO, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.MAINTENANCE_COST, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.MAINTENANCE_START_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class, example = "2022-05-05 12:00:00"),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.MAINTENANCE_END_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class, example = "2022-05-05 12:00:00"),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.ATTACH_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { AssetSoftwareMaintenanceVOMeta.PAGE_INDEX, AssetSoftwareMaintenanceVOMeta.PAGE_SIZE, AssetSoftwareMaintenanceVOMeta.SEARCH_FIELD, AssetSoftwareMaintenanceVOMeta.FUZZY_FIELD, AssetSoftwareMaintenanceVOMeta.SEARCH_VALUE, AssetSoftwareMaintenanceVOMeta.DIRTY_FIELDS, AssetSoftwareMaintenanceVOMeta.SORT_FIELD, AssetSoftwareMaintenanceVOMeta.SORT_TYPE, AssetSoftwareMaintenanceVOMeta.IDS })
    @SentinelResource(value = AssetSoftwareMaintenanceServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetSoftwareMaintenanceServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(AssetSoftwareMaintenanceVO assetSoftwareMaintenanceVO) {
        Result result = assetSoftwareMaintenanceService.update(assetSoftwareMaintenanceVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "574636732868198400"),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.MAINTAINER_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "472013393872551936"),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.USE_ORG_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "2"),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.MANAGER_ID, value = "???????????????", required = false, dataTypeClass = String.class, example = "558321538131034112"),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.CONTACTS, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.CONTACT_INFO, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.MAINTENANCE_COST, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.MAINTENANCE_START_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class, example = "2022-05-05 12:00:00"),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.MAINTENANCE_END_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class, example = "2022-05-05 12:00:00"),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.ATTACH_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetSoftwareMaintenanceVOMeta.PAGE_INDEX, AssetSoftwareMaintenanceVOMeta.PAGE_SIZE, AssetSoftwareMaintenanceVOMeta.SEARCH_FIELD, AssetSoftwareMaintenanceVOMeta.FUZZY_FIELD, AssetSoftwareMaintenanceVOMeta.SEARCH_VALUE, AssetSoftwareMaintenanceVOMeta.DIRTY_FIELDS, AssetSoftwareMaintenanceVOMeta.SORT_FIELD, AssetSoftwareMaintenanceVOMeta.SORT_TYPE, AssetSoftwareMaintenanceVOMeta.IDS })
    @SentinelResource(value = AssetSoftwareMaintenanceServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetSoftwareMaintenanceServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(AssetSoftwareMaintenanceVO assetSoftwareMaintenanceVO) {
        Result result = assetSoftwareMaintenanceService.save(assetSoftwareMaintenanceVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = AssetSoftwareMaintenanceServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetSoftwareMaintenanceServiceProxy.GET_BY_ID)
    public Result<AssetSoftwareMaintenance> getById(String id) {
        Result<AssetSoftwareMaintenance> result = new Result<>();
        AssetSoftwareMaintenance assetSoftwareMaintenance = assetSoftwareMaintenanceService.getById(id);
        // join ???????????????
        assetSoftwareMaintenanceService.dao().fill(assetSoftwareMaintenance).with("useOrganization").with("manager").with("originator").with(AssetSoftwareMaintenanceMeta.MAINTAINER).execute();
        result.success(true).data(assetSoftwareMaintenance);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetSoftwareMaintenanceServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetSoftwareMaintenanceServiceProxy.GET_BY_IDS)
    public Result<List<AssetSoftwareMaintenance>> getByIds(List<String> ids) {
        Result<List<AssetSoftwareMaintenance>> result = new Result<>();
        List<AssetSoftwareMaintenance> list = assetSoftwareMaintenanceService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "574636732868198400"),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.MAINTAINER_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "472013393872551936"),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.USE_ORG_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "2"),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.MANAGER_ID, value = "???????????????", required = false, dataTypeClass = String.class, example = "558321538131034112"),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.CONTACTS, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.CONTACT_INFO, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.MAINTENANCE_COST, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.MAINTENANCE_START_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class, example = "2022-05-05 12:00:00"),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.MAINTENANCE_END_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class, example = "2022-05-05 12:00:00"),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.ATTACH_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetSoftwareMaintenanceVOMeta.PAGE_INDEX, AssetSoftwareMaintenanceVOMeta.PAGE_SIZE })
    @SentinelResource(value = AssetSoftwareMaintenanceServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetSoftwareMaintenanceServiceProxy.QUERY_LIST)
    public Result<List<AssetSoftwareMaintenance>> queryList(AssetSoftwareMaintenanceVO sample) {
        Result<List<AssetSoftwareMaintenance>> result = new Result<>();
        List<AssetSoftwareMaintenance> list = assetSoftwareMaintenanceService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "574636732868198400"),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.MAINTAINER_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "472013393872551936"),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.USE_ORG_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "2"),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.MANAGER_ID, value = "???????????????", required = false, dataTypeClass = String.class, example = "558321538131034112"),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.CONTACTS, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.CONTACT_INFO, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.MAINTENANCE_COST, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.MAINTENANCE_START_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class, example = "2022-05-05 12:00:00"),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.MAINTENANCE_END_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class, example = "2022-05-05 12:00:00"),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.ATTACH_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = AssetSoftwareMaintenanceServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetSoftwareMaintenanceServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<AssetSoftwareMaintenance>> queryPagedList(AssetSoftwareMaintenanceVO sample) {
        Result<PagedList<AssetSoftwareMaintenance>> result = new Result<>();
        PagedList<AssetSoftwareMaintenance> list = assetSoftwareMaintenanceService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        assetSoftwareMaintenanceService.dao().fill(list).with("useOrganization").with("manager").with("originator").with(AssetSoftwareMaintenanceMeta.MAINTAINER).execute();
        result.success(true).data(list);
        List<Employee> originatorList = CollectorUtil.collectList(list, AssetSoftwareMaintenance::getOriginator);
        assetSoftwareMaintenanceService.dao().join(originatorList, Person.class);
        List<Employee> managerList = CollectorUtil.collectList(list, AssetSoftwareMaintenance::getManager);
        assetSoftwareMaintenanceService.dao().join(managerList, Person.class);
        return result;
    }

    /**
     * ??????
     */
    @ApiOperation(value = "??????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetSoftwareMaintenanceVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 13)
    @SentinelResource(value = AssetSoftwareMaintenanceServiceProxy.CONFIRM_OPERATION, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetSoftwareMaintenanceServiceProxy.CONFIRM_OPERATION)
    public Result confirmOperation(String id) {
        return assetSoftwareMaintenanceService.confirmOperation(id);
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = AssetSoftwareMaintenanceServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetSoftwareMaintenanceServiceProxy.EXPORT_EXCEL)
    public void exportExcel(AssetSoftwareMaintenanceVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = assetSoftwareMaintenanceService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = AssetSoftwareMaintenanceServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetSoftwareMaintenanceServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = assetSoftwareMaintenanceService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = AssetSoftwareMaintenanceServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetSoftwareMaintenanceServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = assetSoftwareMaintenanceService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
