package com.dt.platform.eam.controller;

import java.util.List;
import com.dt.platform.domain.eam.*;
import com.dt.platform.domain.eam.meta.*;
import com.dt.platform.proxy.eam.AssetStockGoodsInServiceProxy;
import com.dt.platform.proxy.eam.GoodsStockServiceProxy;
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
import com.dt.platform.proxy.eam.AssetSoftwareServiceProxy;
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
import org.github.foxnic.web.domain.pcm.Catalog;
import org.github.foxnic.web.domain.hrm.Organization;
import org.github.foxnic.web.domain.system.DictItem;
import org.github.foxnic.web.domain.hrm.Employee;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IAssetSoftwareService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-05-04 19:59:02
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("EamAssetSoftwareController")
public class AssetSoftwareController extends SuperController {

    @Autowired
    private IAssetSoftwareService assetSoftwareService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetSoftwareVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "574232023040786432"),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.CATEGORY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.SOFTWARE_VERSION, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.COPYRIGHT_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.LICENSE_MODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.COST_PRICE, value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.OWN_COMPANY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.USE_ORGANIZATION_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.SUPPLIER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.MANAGER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.POSITION_DETAIL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.SOURCE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.SOURCE_DETAIL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.AUTHORIZATION_INFO, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.AUTHORIZED_NUMBER, value = "????????????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.AUTHORIZED_AVAILABLE_NUMBER, value = "????????????", required = false, dataTypeClass = Integer.class, example = "0"),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.AUTHORIZED_NUMBER_UNLIMIT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.AUTHORIZATION_CODE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.AUTHORIZATION_EXPIRATION_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.AUTHORIZATION_EXPIRATION_UNLIMIT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.NEED_MAINTENANCE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.MAINTENANCE_START_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.MAINTENANCE_END_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.PURCHASE_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-05-04 12:00:00"),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.REGISTER_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-05-04 12:00:00"),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.ATTACH_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.LABEL1, value = "??????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.LABEL2, value = "??????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.LABEL3, value = "??????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.LABEL4, value = "??????4", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.CHS_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.CHS_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.CHS_VERSION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.CHANGE_INSTANCE_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.SUMMARY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.LATEST_APPROVER_ID, value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.LATEST_APPROVER_NAME, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.NEXT_APPROVER_IDS, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.NEXT_APPROVER_NAMES, value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.APPROVAL_OPINION, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = AssetSoftwareServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetSoftwareServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(AssetSoftwareVO assetSoftwareVO) {
        Result result = assetSoftwareService.insert(assetSoftwareVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetSoftwareVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "574232023040786432")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = AssetSoftwareServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetSoftwareServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = assetSoftwareService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetSoftwareVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetSoftwareServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetSoftwareServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = assetSoftwareService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetSoftwareVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "574232023040786432"),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.CATEGORY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.SOFTWARE_VERSION, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.COPYRIGHT_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.LICENSE_MODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.COST_PRICE, value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.OWN_COMPANY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.USE_ORGANIZATION_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.SUPPLIER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.MANAGER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.POSITION_DETAIL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.SOURCE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.SOURCE_DETAIL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.AUTHORIZATION_INFO, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.AUTHORIZED_NUMBER, value = "????????????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.AUTHORIZED_AVAILABLE_NUMBER, value = "????????????", required = false, dataTypeClass = Integer.class, example = "0"),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.AUTHORIZED_NUMBER_UNLIMIT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.AUTHORIZATION_CODE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.AUTHORIZATION_EXPIRATION_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.AUTHORIZATION_EXPIRATION_UNLIMIT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.NEED_MAINTENANCE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.MAINTENANCE_START_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.MAINTENANCE_END_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.PURCHASE_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-05-04 12:00:00"),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.REGISTER_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-05-04 12:00:00"),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.ATTACH_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.LABEL1, value = "??????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.LABEL2, value = "??????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.LABEL3, value = "??????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.LABEL4, value = "??????4", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.CHS_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.CHS_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.CHS_VERSION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.CHANGE_INSTANCE_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.SUMMARY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.LATEST_APPROVER_ID, value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.LATEST_APPROVER_NAME, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.NEXT_APPROVER_IDS, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.NEXT_APPROVER_NAMES, value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.APPROVAL_OPINION, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { AssetSoftwareVOMeta.PAGE_INDEX, AssetSoftwareVOMeta.PAGE_SIZE, AssetSoftwareVOMeta.SEARCH_FIELD, AssetSoftwareVOMeta.FUZZY_FIELD, AssetSoftwareVOMeta.SEARCH_VALUE, AssetSoftwareVOMeta.DIRTY_FIELDS, AssetSoftwareVOMeta.SORT_FIELD, AssetSoftwareVOMeta.SORT_TYPE, AssetSoftwareVOMeta.IDS })
    @SentinelResource(value = AssetSoftwareServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetSoftwareServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(AssetSoftwareVO assetSoftwareVO) {
        Result result = assetSoftwareService.update(assetSoftwareVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetSoftwareVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "574232023040786432"),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.CATEGORY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.SOFTWARE_VERSION, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.COPYRIGHT_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.LICENSE_MODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.COST_PRICE, value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.OWN_COMPANY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.USE_ORGANIZATION_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.SUPPLIER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.MANAGER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.POSITION_DETAIL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.SOURCE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.SOURCE_DETAIL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.AUTHORIZATION_INFO, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.AUTHORIZED_NUMBER, value = "????????????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.AUTHORIZED_AVAILABLE_NUMBER, value = "????????????", required = false, dataTypeClass = Integer.class, example = "0"),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.AUTHORIZED_NUMBER_UNLIMIT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.AUTHORIZATION_CODE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.AUTHORIZATION_EXPIRATION_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.AUTHORIZATION_EXPIRATION_UNLIMIT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.NEED_MAINTENANCE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.MAINTENANCE_START_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.MAINTENANCE_END_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.PURCHASE_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-05-04 12:00:00"),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.REGISTER_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-05-04 12:00:00"),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.ATTACH_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.LABEL1, value = "??????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.LABEL2, value = "??????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.LABEL3, value = "??????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.LABEL4, value = "??????4", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.CHS_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.CHS_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.CHS_VERSION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.CHANGE_INSTANCE_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.SUMMARY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.LATEST_APPROVER_ID, value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.LATEST_APPROVER_NAME, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.NEXT_APPROVER_IDS, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.NEXT_APPROVER_NAMES, value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.APPROVAL_OPINION, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetSoftwareVOMeta.PAGE_INDEX, AssetSoftwareVOMeta.PAGE_SIZE, AssetSoftwareVOMeta.SEARCH_FIELD, AssetSoftwareVOMeta.FUZZY_FIELD, AssetSoftwareVOMeta.SEARCH_VALUE, AssetSoftwareVOMeta.DIRTY_FIELDS, AssetSoftwareVOMeta.SORT_FIELD, AssetSoftwareVOMeta.SORT_TYPE, AssetSoftwareVOMeta.IDS })
    @SentinelResource(value = AssetSoftwareServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetSoftwareServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(AssetSoftwareVO assetSoftwareVO) {
        Result result = assetSoftwareService.save(assetSoftwareVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetSoftwareVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = AssetSoftwareServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetSoftwareServiceProxy.GET_BY_ID)
    public Result<AssetSoftware> getById(String id) {
        Result<AssetSoftware> result = new Result<>();
        AssetSoftware assetSoftware = assetSoftwareService.getById(id);
        // join ???????????????
        assetSoftwareService.dao().fill(assetSoftware).with("ownerCompany").with("useOrganization").with("manager").with("originator").with(AssetSoftwareMeta.CATEGORY).with(AssetSoftwareMeta.SUPPLIER).with(AssetSoftwareMeta.SOURCE).with(AssetSoftwareMeta.MAINTAINER).with(AssetSoftwareMeta.COPYRIGHT_TYPE_DICT).with(AssetSoftwareMeta.LICENSE_MODE_DICT).execute();
        assetSoftwareService.dao().join(assetSoftware.getOriginator(), Person.class);
        assetSoftwareService.dao().join(assetSoftware.getManager(), Person.class);
        result.success(true).data(assetSoftware);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetSoftwareVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetSoftwareServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetSoftwareServiceProxy.GET_BY_IDS)
    public Result<List<AssetSoftware>> getByIds(List<String> ids) {
        Result<List<AssetSoftware>> result = new Result<>();
        List<AssetSoftware> list = assetSoftwareService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetSoftwareVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "574232023040786432"),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.CATEGORY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.SOFTWARE_VERSION, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.COPYRIGHT_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.LICENSE_MODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.COST_PRICE, value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.OWN_COMPANY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.USE_ORGANIZATION_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.SUPPLIER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.MANAGER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.POSITION_DETAIL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.SOURCE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.SOURCE_DETAIL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.AUTHORIZATION_INFO, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.AUTHORIZED_NUMBER, value = "????????????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.AUTHORIZED_AVAILABLE_NUMBER, value = "????????????", required = false, dataTypeClass = Integer.class, example = "0"),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.AUTHORIZED_NUMBER_UNLIMIT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.AUTHORIZATION_CODE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.AUTHORIZATION_EXPIRATION_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.AUTHORIZATION_EXPIRATION_UNLIMIT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.NEED_MAINTENANCE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.MAINTENANCE_START_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.MAINTENANCE_END_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.PURCHASE_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-05-04 12:00:00"),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.REGISTER_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-05-04 12:00:00"),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.ATTACH_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.LABEL1, value = "??????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.LABEL2, value = "??????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.LABEL3, value = "??????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.LABEL4, value = "??????4", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.CHS_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.CHS_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.CHS_VERSION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.CHANGE_INSTANCE_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.SUMMARY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.LATEST_APPROVER_ID, value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.LATEST_APPROVER_NAME, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.NEXT_APPROVER_IDS, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.NEXT_APPROVER_NAMES, value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.APPROVAL_OPINION, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetSoftwareVOMeta.PAGE_INDEX, AssetSoftwareVOMeta.PAGE_SIZE })
    @SentinelResource(value = AssetSoftwareServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetSoftwareServiceProxy.QUERY_LIST)
    public Result<List<AssetSoftware>> queryList(AssetSoftwareVO sample) {
        Result<List<AssetSoftware>> result = new Result<>();
        List<AssetSoftware> list = assetSoftwareService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetSoftwareVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "574232023040786432"),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.CATEGORY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.SOFTWARE_VERSION, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.COPYRIGHT_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.LICENSE_MODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.COST_PRICE, value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.OWN_COMPANY_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.USE_ORGANIZATION_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.SUPPLIER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.MANAGER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.POSITION_DETAIL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.SOURCE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.SOURCE_DETAIL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.AUTHORIZATION_INFO, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.AUTHORIZED_NUMBER, value = "????????????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.AUTHORIZED_AVAILABLE_NUMBER, value = "????????????", required = false, dataTypeClass = Integer.class, example = "0"),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.AUTHORIZED_NUMBER_UNLIMIT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.AUTHORIZATION_CODE, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.AUTHORIZATION_EXPIRATION_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.AUTHORIZATION_EXPIRATION_UNLIMIT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.NEED_MAINTENANCE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.MAINTENANCE_START_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.MAINTENANCE_END_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.PURCHASE_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-05-04 12:00:00"),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.REGISTER_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-05-04 12:00:00"),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.ATTACH_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.LABEL1, value = "??????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.LABEL2, value = "??????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.LABEL3, value = "??????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.LABEL4, value = "??????4", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.CHS_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.CHS_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.CHS_VERSION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.CHANGE_INSTANCE_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.SUMMARY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.LATEST_APPROVER_ID, value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.LATEST_APPROVER_NAME, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.NEXT_APPROVER_IDS, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.NEXT_APPROVER_NAMES, value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSoftwareVOMeta.APPROVAL_OPINION, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = AssetSoftwareServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetSoftwareServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<AssetSoftware>> queryPagedList(AssetSoftwareVO sample) {
        Result<PagedList<AssetSoftware>> result = new Result<>();
        PagedList<AssetSoftware> list = assetSoftwareService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        assetSoftwareService.dao().fill(list).with("ownerCompany").with("useOrganization").with("manager").with("originator").with(AssetSoftwareMeta.CATEGORY).with(AssetSoftwareMeta.SUPPLIER).with(AssetSoftwareMeta.SOURCE).with(AssetSoftwareMeta.COPYRIGHT_TYPE_DICT).with(AssetSoftwareMeta.LICENSE_MODE_DICT).execute();
        List<Employee> originatorList = CollectorUtil.collectList(list, AssetSoftware::getOriginator);
        assetSoftwareService.dao().join(originatorList, Person.class);
        List<Employee> managerList = CollectorUtil.collectList(list, AssetSoftware::getManager);
        assetSoftwareService.dao().join(managerList, Person.class);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????
     */
    @ApiOperation(value = "??????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetSoftwareVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 13)
    @SentinelResource(value = AssetSoftwareServiceProxy.CONFIRM_OPERATION, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetSoftwareServiceProxy.CONFIRM_OPERATION)
    public Result confirmOperation(String id) {
        return assetSoftwareService.confirmOperation(id);
    }

    @ApiOperationSupport(order = 16)
    @SentinelResource(value = AssetSoftwareServiceProxy.QUERY_PAGED_LIST_BY_SELECTED, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetSoftwareServiceProxy.QUERY_PAGED_LIST_BY_SELECTED)
    public Result<PagedList<AssetSoftware>> queryPagedListBySelected(AssetSoftwareVO sample, String operType, String dataType) {
        Result<PagedList<AssetSoftware>> result = new Result<>();
        PagedList<AssetSoftware> list = assetSoftwareService.queryPagedListBySelected(sample, operType);
        assetSoftwareService.dao().fill(list).with("ownerCompany").with("useOrganization").with(AssetSoftwareMeta.CATEGORY).with(AssetSoftwareMeta.SUPPLIER).with(AssetSoftwareMeta.SOURCE).with(AssetSoftwareMeta.MAINTAINER).with(AssetSoftwareMeta.COPYRIGHT_TYPE_DICT).with(AssetSoftwareMeta.LICENSE_MODE_DICT).execute();
        result.success(true).data(list);
        return result;
    }

    @ApiOperationSupport(order = 17)
    @SentinelResource(value = AssetSoftwareServiceProxy.QUERY_PAGED_LIST_BY_SELECT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetSoftwareServiceProxy.QUERY_PAGED_LIST_BY_SELECT)
    public Result<PagedList<AssetSoftware>> queryPagedListBySelect(AssetSoftwareVO sample, String assetSearcbContent) {
        Result<PagedList<AssetSoftware>> result = new Result<>();
        PagedList<AssetSoftware> list = assetSoftwareService.queryPagedListBySelect(sample, assetSearcbContent);
        assetSoftwareService.dao().fill(list).with("ownerCompany").with("useOrganization").with(AssetSoftwareMeta.CATEGORY).with(AssetSoftwareMeta.SUPPLIER).with(AssetSoftwareMeta.SOURCE).with(AssetSoftwareMeta.MAINTAINER).with(AssetSoftwareMeta.COPYRIGHT_TYPE_DICT).with(AssetSoftwareMeta.LICENSE_MODE_DICT).execute();
        result.success(true).data(list);
        return result;
    }

    @ApiOperationSupport(order = 18)
    @SentinelResource(value = AssetSoftwareServiceProxy.ASSET_SELECTED, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetSoftwareServiceProxy.ASSET_SELECTED)
    public Result assetSelected(List<String> ids, String selectedCode, String operType) {
        return assetSoftwareService.assetSelected(ids, selectedCode, operType);
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = AssetSoftwareServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetSoftwareServiceProxy.EXPORT_EXCEL)
    public void exportExcel(AssetSoftwareVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = assetSoftwareService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = AssetSoftwareServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetSoftwareServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = assetSoftwareService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = AssetSoftwareServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetSoftwareServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = assetSoftwareService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
