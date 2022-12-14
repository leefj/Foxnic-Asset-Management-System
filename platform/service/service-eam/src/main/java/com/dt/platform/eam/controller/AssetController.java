package com.dt.platform.eam.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.deepoove.poi.util.PoitlIOUtils;
import com.dt.platform.constants.enums.eam.*;
import com.dt.platform.constants.enums.ops.OpsOperateEnum;
import com.dt.platform.domain.eam.*;
import com.dt.platform.eam.service.*;
import com.dt.platform.proxy.common.TplFileServiceProxy;
import com.dt.platform.proxy.eam.AssetDataChangeServiceProxy;
import com.dt.platform.proxy.eam.OperateServiceProxy;
import com.github.foxnic.commons.busi.id.IDGenerator;
import com.github.foxnic.commons.collection.CollectorUtil;
import com.github.foxnic.commons.lang.StringUtil;
import com.github.foxnic.commons.log.Logger;
import com.github.foxnic.dao.data.Rcd;
import com.github.foxnic.dao.data.RcdSet;
import com.github.foxnic.sql.expr.ConditionExpr;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.github.foxnic.web.domain.changes.ChangeDefinition;
import org.github.foxnic.web.domain.changes.ChangeDefinitionVO;
import org.github.foxnic.web.domain.changes.ProcessApproveVO;
import org.github.foxnic.web.domain.hrm.Employee;
import org.github.foxnic.web.domain.pcm.CatalogAttribute;
import org.github.foxnic.web.domain.pcm.CatalogData;
import org.github.foxnic.web.proxy.changes.ChangeDefinitionServiceProxy;
import org.github.foxnic.web.proxy.pcm.CatalogServiceProxy;
import org.github.foxnic.web.session.SessionUser;
import org.springframework.util.IdGenerator;
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
import com.dt.platform.proxy.eam.AssetServiceProxy;
import com.dt.platform.domain.eam.meta.AssetVOMeta;
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
import com.dt.platform.domain.eam.meta.AssetMeta;
import java.math.BigDecimal;
import org.github.foxnic.web.domain.pcm.Catalog;
import org.github.foxnic.web.domain.hrm.Person;
import org.github.foxnic.web.domain.system.DictItem;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ?????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2021-09-20 21:49:26
 */
@Api(tags = "??????")
@ApiSort(0)
@RestController("EamAssetController")
public class AssetController extends SuperController {

    @Autowired
    private IAssetService assetService;

    @Autowired
    private IAssetItemService assetItemService;

    @Autowired
    private IOperateService operateService;

    @Autowired
    private IAssetDataService assetDataService;

    @Autowired
    private IAssetCategoryService assetCategoryService;

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "489517168661102592"),
		@ApiImplicitParam(name = AssetVOMeta.CATEGORY_ID, value = "????????????", required = true, dataTypeClass = String.class, example = "486917781384597505"),
		@ApiImplicitParam(name = AssetVOMeta.CATEGORY_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.BATCH_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.DISPLAY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.GOODS_ID, value = "??????????????????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.NAME, value = "????????????????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetVOMeta.MANUFACTURER_ID, value = "??????????????????", required = false, dataTypeClass = String.class, example = "473623897560842240"),
		@ApiImplicitParam(name = AssetVOMeta.MODEL, value = "????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.PICTURE_ID, value = "????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.UNIT, value = "????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.SERVICE_LIFE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "1212.00"),
		@ApiImplicitParam(name = AssetVOMeta.SAFETY_LEVEL_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.SERIAL_NUMBER, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.OWN_COMPANY_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetVOMeta.MANAGER_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.USE_ORGANIZATION_ID, value = "????????????/??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetVOMeta.USE_USER_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.POSITION_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.POSITION_DETAIL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.WAREHOUSE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.SOURCE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_NUMBER, value = "????????????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = AssetVOMeta.REMAIN_NUMBER, value = "????????????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = AssetVOMeta.PURCHASE_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetVOMeta.RFID, value = "RFID??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTAINER_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTAINER_NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetVOMeta.MAINTENANCE_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.CONTACTS, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.CONTACT_INFORMATION, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.DIRECTOR, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTENANCE_START_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTENANCE_END_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTENANCE_NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.FINANCIAL_CATEGORY_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.FINANCIAL_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.SUPPLIER_ID, value = "???????????????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.ORIGINAL_UNIT_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.ACCUMULATED_DEPRECIATION, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.RESIDUALS_RATE, value = "residuals_rate", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.NAV_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.PURCHASE_UNIT_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.ENTRY_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetVOMeta.FINANCIAL_NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_IP, value = "??????IP", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.MANAGE_IP, value = "??????IP", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_CPU, value = "??????CPU", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_MEMORY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_LABEL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_CONF, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_ENVIRONMENT_CODE, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.RACK_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.RACK_UP_NUMBER, value = "?????????????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetVOMeta.RACK_DOWN_NUMBER, value = "?????????????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetVOMeta.LABEL, value = "?????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.OWNER_CODE", value = "??????", required = false, dataTypeClass = String.class, example = "asset_change_record"),
		@ApiImplicitParam(name = "AssetVOMeta.CLEAN_OUT", value = "????????????", required = false, dataTypeClass = String.class, example = "0"),
		@ApiImplicitParam(name = "AssetVOMeta.REGION_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.GOODS_STOCK_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.PRODUCTION_DATE", value = "????????????", required = false, dataTypeClass = Date.class, example = ""),
		@ApiImplicitParam(name = "AssetVOMeta.REGISTER_DATE", value = "????????????", required = false, dataTypeClass = Date.class, example = ""),
		@ApiImplicitParam(name = "AssetVOMeta.LAST_VERIFICATION_DATE", value = "??????????????????", required = false, dataTypeClass = Date.class, example = "2022-01-06 12:00:00"),
		@ApiImplicitParam(name = "AssetVOMeta.PURPOSE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.TAX_AMOUNT_RATE", value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.TAX_AMOUNT_PRICE", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.TOTAL_AMOUNT_PRICE", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.CURRENT_YEAR_DEPRECIATION", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.DEPRECIATION_YEAR", value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = "AssetVOMeta.MONTH_DEPRECIATION_PRICE", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.RESIDUALS_PRICE", value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.EQUIPMENT_SERIAL_NUMBER", value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL2", value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL3", value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL4", value = "?????????4", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL5", value = "?????????5", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.INTERNAL_CONTROL_LABEL", value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.BILL_ID", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CHS_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CHS_STATUS", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CHS_VERSION", value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CHANGE_INSTANCE_ID", value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.SUMMARY", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LATEST_APPROVER_ID", value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LATEST_APPROVER_NAME", value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.NEXT_APPROVER_IDS", value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.NEXT_APPROVER_NAMES", value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.APPROVAL_OPINION", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.ASSET_SELECTED_DATA", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.OWNER_CODE", value = "??????", required = false, dataTypeClass = String.class, example = "asset_change_record"),
		@ApiImplicitParam(name = "AssetVOMeta.CLEAN_OUT", value = "????????????", required = false, dataTypeClass = String.class, example = "0"),
		@ApiImplicitParam(name = "AssetVOMeta.REGION_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.GOODS_STOCK_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.PRODUCTION_DATE", value = "????????????", required = false, dataTypeClass = Date.class, example = ""),
		@ApiImplicitParam(name = "AssetVOMeta.REGISTER_DATE", value = "????????????", required = false, dataTypeClass = Date.class, example = ""),
		@ApiImplicitParam(name = "AssetVOMeta.LAST_VERIFICATION_DATE", value = "??????????????????", required = false, dataTypeClass = Date.class, example = "2022-01-06 12:00:00"),
		@ApiImplicitParam(name = "AssetVOMeta.PURPOSE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.TAX_AMOUNT_RATE", value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.TAX_AMOUNT_PRICE", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.TOTAL_AMOUNT_PRICE", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.CURRENT_YEAR_DEPRECIATION", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.DEPRECIATION_YEAR", value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = "AssetVOMeta.MONTH_DEPRECIATION_PRICE", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.RESIDUALS_PRICE", value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.EQUIPMENT_SERIAL_NUMBER", value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL2", value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL3", value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL4", value = "?????????4", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL5", value = "?????????5", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.INTERNAL_CONTROL_LABEL", value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.BILL_ID", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CHS_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CHS_STATUS", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CHS_VERSION", value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CHANGE_INSTANCE_ID", value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.SUMMARY", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LATEST_APPROVER_ID", value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LATEST_APPROVER_NAME", value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.NEXT_APPROVER_IDS", value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.NEXT_APPROVER_NAMES", value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.APPROVAL_OPINION", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.ASSET_SELECTED_DATA", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.OWNER_CODE", value = "??????", required = false, dataTypeClass = String.class, example = "asset_change_record"),
		@ApiImplicitParam(name = "AssetVOMeta.CLEAN_OUT", value = "????????????", required = false, dataTypeClass = String.class, example = "0"),
		@ApiImplicitParam(name = "AssetVOMeta.REGION_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.GOODS_STOCK_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.PRODUCTION_DATE", value = "????????????", required = false, dataTypeClass = Date.class, example = ""),
		@ApiImplicitParam(name = "AssetVOMeta.REGISTER_DATE", value = "????????????", required = false, dataTypeClass = Date.class, example = ""),
		@ApiImplicitParam(name = "AssetVOMeta.LAST_VERIFICATION_DATE", value = "??????????????????", required = false, dataTypeClass = Date.class, example = "2022-01-06 12:00:00"),
		@ApiImplicitParam(name = "AssetVOMeta.PURPOSE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.TAX_AMOUNT_RATE", value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.TAX_AMOUNT_PRICE", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.TOTAL_AMOUNT_PRICE", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.CURRENT_YEAR_DEPRECIATION", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.DEPRECIATION_YEAR", value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = "AssetVOMeta.MONTH_DEPRECIATION_PRICE", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.RESIDUALS_PRICE", value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.EQUIPMENT_SERIAL_NUMBER", value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL2", value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL3", value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL4", value = "?????????4", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL5", value = "?????????5", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.INTERNAL_CONTROL_LABEL", value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.BILL_ID", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CHS_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CHS_STATUS", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CHS_VERSION", value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CHANGE_INSTANCE_ID", value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.SUMMARY", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LATEST_APPROVER_ID", value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LATEST_APPROVER_NAME", value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.NEXT_APPROVER_IDS", value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.NEXT_APPROVER_NAMES", value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.APPROVAL_OPINION", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.ASSET_SELECTED_DATA", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.OWNER_CODE", value = "??????", required = false, dataTypeClass = String.class, example = "asset_change_record"),
		@ApiImplicitParam(name = "AssetVOMeta.CLEAN_OUT", value = "????????????", required = false, dataTypeClass = String.class, example = "0"),
		@ApiImplicitParam(name = "AssetVOMeta.REGION_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.GOODS_STOCK_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.PRODUCTION_DATE", value = "????????????", required = false, dataTypeClass = Date.class, example = ""),
		@ApiImplicitParam(name = "AssetVOMeta.REGISTER_DATE", value = "????????????", required = false, dataTypeClass = Date.class, example = ""),
		@ApiImplicitParam(name = "AssetVOMeta.LAST_VERIFICATION_DATE", value = "??????????????????", required = false, dataTypeClass = Date.class, example = "2022-01-06 12:00:00"),
		@ApiImplicitParam(name = "AssetVOMeta.PURPOSE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.TAX_AMOUNT_RATE", value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.TAX_AMOUNT_PRICE", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.TOTAL_AMOUNT_PRICE", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.CURRENT_YEAR_DEPRECIATION", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.DEPRECIATION_YEAR", value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = "AssetVOMeta.MONTH_DEPRECIATION_PRICE", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.RESIDUALS_PRICE", value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.EQUIPMENT_SERIAL_NUMBER", value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL2", value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL3", value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL4", value = "?????????4", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL5", value = "?????????5", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.INTERNAL_CONTROL_LABEL", value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.BILL_ID", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CHS_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CHS_STATUS", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CHS_VERSION", value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CHANGE_INSTANCE_ID", value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.SUMMARY", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LATEST_APPROVER_ID", value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LATEST_APPROVER_NAME", value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.NEXT_APPROVER_IDS", value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.NEXT_APPROVER_NAMES", value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.APPROVAL_OPINION", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.ASSET_SELECTED_DATA", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.OWNER_CODE", value = "??????", required = false, dataTypeClass = String.class, example = "asset_change_record"),
		@ApiImplicitParam(name = "AssetVOMeta.CLEAN_OUT", value = "????????????", required = false, dataTypeClass = String.class, example = "0"),
		@ApiImplicitParam(name = "AssetVOMeta.REGION_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.GOODS_STOCK_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.PRODUCTION_DATE", value = "????????????", required = false, dataTypeClass = Date.class, example = ""),
		@ApiImplicitParam(name = "AssetVOMeta.REGISTER_DATE", value = "????????????", required = false, dataTypeClass = Date.class, example = ""),
		@ApiImplicitParam(name = "AssetVOMeta.LAST_VERIFICATION_DATE", value = "??????????????????", required = false, dataTypeClass = Date.class, example = "2022-01-06 12:00:00"),
		@ApiImplicitParam(name = "AssetVOMeta.PURPOSE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.TAX_AMOUNT_RATE", value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.TAX_AMOUNT_PRICE", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.TOTAL_AMOUNT_PRICE", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.CURRENT_YEAR_DEPRECIATION", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.DEPRECIATION_YEAR", value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = "AssetVOMeta.MONTH_DEPRECIATION_PRICE", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.RESIDUALS_PRICE", value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.EQUIPMENT_SERIAL_NUMBER", value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL2", value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL3", value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL4", value = "?????????4", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL5", value = "?????????5", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.INTERNAL_CONTROL_LABEL", value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.BILL_ID", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CHS_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CHS_STATUS", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CHS_VERSION", value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CHANGE_INSTANCE_ID", value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.SUMMARY", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LATEST_APPROVER_ID", value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LATEST_APPROVER_NAME", value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.NEXT_APPROVER_IDS", value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.NEXT_APPROVER_NAMES", value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.APPROVAL_OPINION", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.ASSET_SELECTED_DATA", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.OWNER_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "asset_change_record"),
		@ApiImplicitParam(name = AssetVOMeta.CLEAN_OUT, value = "????????????", required = false, dataTypeClass = String.class, example = "0"),
		@ApiImplicitParam(name = AssetVOMeta.REGION_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.GOODS_STOCK_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.PRODUCTION_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = ""),
		@ApiImplicitParam(name = AssetVOMeta.REGISTER_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = ""),
		@ApiImplicitParam(name = AssetVOMeta.LAST_VERIFICATION_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class, example = "2022-01-06 12:00:00"),
		@ApiImplicitParam(name = AssetVOMeta.PURPOSE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTENANCE_METHOD, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.SUGGEST_MAINTENANCE_METHOD, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.TAX_AMOUNT_RATE, value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetVOMeta.TAX_AMOUNT_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetVOMeta.TOTAL_AMOUNT_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetVOMeta.CURRENT_YEAR_DEPRECIATION, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetVOMeta.DEPRECIATION_YEAR, value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetVOMeta.MONTH_DEPRECIATION_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetVOMeta.RESIDUALS_PRICE, value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_SERIAL_NUMBER, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.LABEL2, value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.LABEL3, value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.LABEL4, value = "?????????4", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.LABEL5, value = "?????????5", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.INTERNAL_CONTROL_LABEL, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.BILL_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.CHS_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.CHS_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.CHS_VERSION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.CHANGE_INSTANCE_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.SUMMARY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.LATEST_APPROVER_ID, value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.LATEST_APPROVER_NAME, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.NEXT_APPROVER_IDS, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.NEXT_APPROVER_NAMES, value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.APPROVAL_OPINION, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_SELECTED_DATA, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTENANCE_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.COLLECTION_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.SCRAP_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.BORROW_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_USED_SERVICE_LIFE, value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetVOMeta.FINANCIAL_OPTION, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EXPENSE_ITEM, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.CUSTOMER_INFO, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.DEPRECIATION_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.DEPRECIATION_OPER_TIME, value = "??????????????????", required = false, dataTypeClass = Date.class, example = "")
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = AssetServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(Asset assetVO) {
        String id = IDGenerator.getSnowflakeIdString();
        assetVO.setId(id);
        // ????????????????????????
        if (assetVO.getPcmData() != null && assetVO.getPcmData().size() > 0) {
            CatalogData pcmData = new CatalogData();
            pcmData.setData(assetVO.getPcmData());
            pcmData.setOwnerId(id);
            pcmData.setCatalogId(assetVO.getCategoryId());
            pcmData.setTenantId(SessionUser.getCurrent().getActivatedTenantId());
			Logger.info(pcmData);
            Result pcmResult = CatalogServiceProxy.api().saveData(pcmData);
            if (!pcmResult.isSuccess()) {
                return pcmResult;
            }
        }
        Result result = assetService.insert(assetVO);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "489517168661102592")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = AssetServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetServiceProxy.DELETE)
    public Result deleteById(String id) {
        // Asset data=assetService.getById(id);
        // if(AssetHandleStatusEnum.CANCEL.code().equals(data.getStatus())
        // ||AssetHandleStatusEnum.INCOMPLETE.code().equals(data.getStatus()) ){
        // 
        // return result;
        // }else{
        // return ErrorDesc.failureMessage("????????????????????????????????????");
        // }
        return assetService.deleteByIdLogical(id);
    }

    /**
     * ?????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = assetService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "489517168661102592"),
		@ApiImplicitParam(name = AssetVOMeta.CATEGORY_ID, value = "????????????", required = true, dataTypeClass = String.class, example = "486917781384597505"),
		@ApiImplicitParam(name = AssetVOMeta.CATEGORY_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.BATCH_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.DISPLAY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.GOODS_ID, value = "??????????????????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.NAME, value = "????????????????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetVOMeta.MANUFACTURER_ID, value = "??????????????????", required = false, dataTypeClass = String.class, example = "473623897560842240"),
		@ApiImplicitParam(name = AssetVOMeta.MODEL, value = "????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.PICTURE_ID, value = "????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.UNIT, value = "????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.SERVICE_LIFE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "1212.00"),
		@ApiImplicitParam(name = AssetVOMeta.SAFETY_LEVEL_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.SERIAL_NUMBER, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.OWN_COMPANY_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetVOMeta.MANAGER_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.USE_ORGANIZATION_ID, value = "????????????/??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetVOMeta.USE_USER_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.POSITION_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.POSITION_DETAIL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.WAREHOUSE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.SOURCE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_NUMBER, value = "????????????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = AssetVOMeta.REMAIN_NUMBER, value = "????????????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = AssetVOMeta.PURCHASE_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetVOMeta.RFID, value = "RFID??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTAINER_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTAINER_NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetVOMeta.MAINTENANCE_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.CONTACTS, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.CONTACT_INFORMATION, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.DIRECTOR, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTENANCE_START_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTENANCE_END_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTENANCE_NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.FINANCIAL_CATEGORY_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.FINANCIAL_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.SUPPLIER_ID, value = "???????????????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.ORIGINAL_UNIT_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.ACCUMULATED_DEPRECIATION, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.RESIDUALS_RATE, value = "residuals_rate", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.NAV_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.PURCHASE_UNIT_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.ENTRY_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetVOMeta.FINANCIAL_NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_IP, value = "??????IP", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.MANAGE_IP, value = "??????IP", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_CPU, value = "??????CPU", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_MEMORY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_LABEL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_CONF, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_ENVIRONMENT_CODE, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.RACK_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.RACK_UP_NUMBER, value = "?????????????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetVOMeta.RACK_DOWN_NUMBER, value = "?????????????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetVOMeta.LABEL, value = "?????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.OWNER_CODE", value = "??????", required = false, dataTypeClass = String.class, example = "asset_change_record"),
		@ApiImplicitParam(name = "AssetVOMeta.CLEAN_OUT", value = "????????????", required = false, dataTypeClass = String.class, example = "0"),
		@ApiImplicitParam(name = "AssetVOMeta.REGION_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.GOODS_STOCK_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.PRODUCTION_DATE", value = "????????????", required = false, dataTypeClass = Date.class, example = ""),
		@ApiImplicitParam(name = "AssetVOMeta.REGISTER_DATE", value = "????????????", required = false, dataTypeClass = Date.class, example = ""),
		@ApiImplicitParam(name = "AssetVOMeta.LAST_VERIFICATION_DATE", value = "??????????????????", required = false, dataTypeClass = Date.class, example = "2022-01-06 12:00:00"),
		@ApiImplicitParam(name = "AssetVOMeta.PURPOSE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.TAX_AMOUNT_RATE", value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.TAX_AMOUNT_PRICE", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.TOTAL_AMOUNT_PRICE", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.CURRENT_YEAR_DEPRECIATION", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.DEPRECIATION_YEAR", value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = "AssetVOMeta.MONTH_DEPRECIATION_PRICE", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.RESIDUALS_PRICE", value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.EQUIPMENT_SERIAL_NUMBER", value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL2", value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL3", value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL4", value = "?????????4", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL5", value = "?????????5", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.INTERNAL_CONTROL_LABEL", value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.BILL_ID", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CHS_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CHS_STATUS", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CHS_VERSION", value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CHANGE_INSTANCE_ID", value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.SUMMARY", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LATEST_APPROVER_ID", value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LATEST_APPROVER_NAME", value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.NEXT_APPROVER_IDS", value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.NEXT_APPROVER_NAMES", value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.APPROVAL_OPINION", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.ASSET_SELECTED_DATA", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.OWNER_CODE", value = "??????", required = false, dataTypeClass = String.class, example = "asset_change_record"),
		@ApiImplicitParam(name = "AssetVOMeta.CLEAN_OUT", value = "????????????", required = false, dataTypeClass = String.class, example = "0"),
		@ApiImplicitParam(name = "AssetVOMeta.REGION_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.GOODS_STOCK_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.PRODUCTION_DATE", value = "????????????", required = false, dataTypeClass = Date.class, example = ""),
		@ApiImplicitParam(name = "AssetVOMeta.REGISTER_DATE", value = "????????????", required = false, dataTypeClass = Date.class, example = ""),
		@ApiImplicitParam(name = "AssetVOMeta.LAST_VERIFICATION_DATE", value = "??????????????????", required = false, dataTypeClass = Date.class, example = "2022-01-06 12:00:00"),
		@ApiImplicitParam(name = "AssetVOMeta.PURPOSE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.TAX_AMOUNT_RATE", value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.TAX_AMOUNT_PRICE", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.TOTAL_AMOUNT_PRICE", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.CURRENT_YEAR_DEPRECIATION", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.DEPRECIATION_YEAR", value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = "AssetVOMeta.MONTH_DEPRECIATION_PRICE", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.RESIDUALS_PRICE", value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.EQUIPMENT_SERIAL_NUMBER", value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL2", value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL3", value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL4", value = "?????????4", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL5", value = "?????????5", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.INTERNAL_CONTROL_LABEL", value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.BILL_ID", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CHS_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CHS_STATUS", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CHS_VERSION", value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CHANGE_INSTANCE_ID", value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.SUMMARY", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LATEST_APPROVER_ID", value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LATEST_APPROVER_NAME", value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.NEXT_APPROVER_IDS", value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.NEXT_APPROVER_NAMES", value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.APPROVAL_OPINION", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.ASSET_SELECTED_DATA", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.OWNER_CODE", value = "??????", required = false, dataTypeClass = String.class, example = "asset_change_record"),
		@ApiImplicitParam(name = "AssetVOMeta.CLEAN_OUT", value = "????????????", required = false, dataTypeClass = String.class, example = "0"),
		@ApiImplicitParam(name = "AssetVOMeta.REGION_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.GOODS_STOCK_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.PRODUCTION_DATE", value = "????????????", required = false, dataTypeClass = Date.class, example = ""),
		@ApiImplicitParam(name = "AssetVOMeta.REGISTER_DATE", value = "????????????", required = false, dataTypeClass = Date.class, example = ""),
		@ApiImplicitParam(name = "AssetVOMeta.LAST_VERIFICATION_DATE", value = "??????????????????", required = false, dataTypeClass = Date.class, example = "2022-01-06 12:00:00"),
		@ApiImplicitParam(name = "AssetVOMeta.PURPOSE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.TAX_AMOUNT_RATE", value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.TAX_AMOUNT_PRICE", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.TOTAL_AMOUNT_PRICE", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.CURRENT_YEAR_DEPRECIATION", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.DEPRECIATION_YEAR", value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = "AssetVOMeta.MONTH_DEPRECIATION_PRICE", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.RESIDUALS_PRICE", value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.EQUIPMENT_SERIAL_NUMBER", value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL2", value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL3", value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL4", value = "?????????4", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL5", value = "?????????5", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.INTERNAL_CONTROL_LABEL", value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.BILL_ID", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CHS_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CHS_STATUS", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CHS_VERSION", value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CHANGE_INSTANCE_ID", value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.SUMMARY", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LATEST_APPROVER_ID", value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LATEST_APPROVER_NAME", value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.NEXT_APPROVER_IDS", value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.NEXT_APPROVER_NAMES", value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.APPROVAL_OPINION", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.ASSET_SELECTED_DATA", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.OWNER_CODE", value = "??????", required = false, dataTypeClass = String.class, example = "asset_change_record"),
		@ApiImplicitParam(name = "AssetVOMeta.CLEAN_OUT", value = "????????????", required = false, dataTypeClass = String.class, example = "0"),
		@ApiImplicitParam(name = "AssetVOMeta.REGION_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.GOODS_STOCK_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.PRODUCTION_DATE", value = "????????????", required = false, dataTypeClass = Date.class, example = ""),
		@ApiImplicitParam(name = "AssetVOMeta.REGISTER_DATE", value = "????????????", required = false, dataTypeClass = Date.class, example = ""),
		@ApiImplicitParam(name = "AssetVOMeta.LAST_VERIFICATION_DATE", value = "??????????????????", required = false, dataTypeClass = Date.class, example = "2022-01-06 12:00:00"),
		@ApiImplicitParam(name = "AssetVOMeta.PURPOSE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.TAX_AMOUNT_RATE", value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.TAX_AMOUNT_PRICE", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.TOTAL_AMOUNT_PRICE", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.CURRENT_YEAR_DEPRECIATION", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.DEPRECIATION_YEAR", value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = "AssetVOMeta.MONTH_DEPRECIATION_PRICE", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.RESIDUALS_PRICE", value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.EQUIPMENT_SERIAL_NUMBER", value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL2", value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL3", value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL4", value = "?????????4", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL5", value = "?????????5", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.INTERNAL_CONTROL_LABEL", value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.BILL_ID", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CHS_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CHS_STATUS", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CHS_VERSION", value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CHANGE_INSTANCE_ID", value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.SUMMARY", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LATEST_APPROVER_ID", value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LATEST_APPROVER_NAME", value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.NEXT_APPROVER_IDS", value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.NEXT_APPROVER_NAMES", value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.APPROVAL_OPINION", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.ASSET_SELECTED_DATA", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.OWNER_CODE", value = "??????", required = false, dataTypeClass = String.class, example = "asset_change_record"),
		@ApiImplicitParam(name = "AssetVOMeta.CLEAN_OUT", value = "????????????", required = false, dataTypeClass = String.class, example = "0"),
		@ApiImplicitParam(name = "AssetVOMeta.REGION_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.GOODS_STOCK_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.PRODUCTION_DATE", value = "????????????", required = false, dataTypeClass = Date.class, example = ""),
		@ApiImplicitParam(name = "AssetVOMeta.REGISTER_DATE", value = "????????????", required = false, dataTypeClass = Date.class, example = ""),
		@ApiImplicitParam(name = "AssetVOMeta.LAST_VERIFICATION_DATE", value = "??????????????????", required = false, dataTypeClass = Date.class, example = "2022-01-06 12:00:00"),
		@ApiImplicitParam(name = "AssetVOMeta.PURPOSE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.TAX_AMOUNT_RATE", value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.TAX_AMOUNT_PRICE", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.TOTAL_AMOUNT_PRICE", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.CURRENT_YEAR_DEPRECIATION", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.DEPRECIATION_YEAR", value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = "AssetVOMeta.MONTH_DEPRECIATION_PRICE", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.RESIDUALS_PRICE", value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.EQUIPMENT_SERIAL_NUMBER", value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL2", value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL3", value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL4", value = "?????????4", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL5", value = "?????????5", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.INTERNAL_CONTROL_LABEL", value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.BILL_ID", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CHS_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CHS_STATUS", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CHS_VERSION", value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CHANGE_INSTANCE_ID", value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.SUMMARY", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LATEST_APPROVER_ID", value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LATEST_APPROVER_NAME", value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.NEXT_APPROVER_IDS", value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.NEXT_APPROVER_NAMES", value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.APPROVAL_OPINION", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.ASSET_SELECTED_DATA", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.OWNER_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "asset_change_record"),
		@ApiImplicitParam(name = AssetVOMeta.CLEAN_OUT, value = "????????????", required = false, dataTypeClass = String.class, example = "0"),
		@ApiImplicitParam(name = AssetVOMeta.REGION_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.GOODS_STOCK_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.PRODUCTION_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = ""),
		@ApiImplicitParam(name = AssetVOMeta.REGISTER_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = ""),
		@ApiImplicitParam(name = AssetVOMeta.LAST_VERIFICATION_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class, example = "2022-01-06 12:00:00"),
		@ApiImplicitParam(name = AssetVOMeta.PURPOSE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTENANCE_METHOD, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.SUGGEST_MAINTENANCE_METHOD, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.TAX_AMOUNT_RATE, value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetVOMeta.TAX_AMOUNT_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetVOMeta.TOTAL_AMOUNT_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetVOMeta.CURRENT_YEAR_DEPRECIATION, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetVOMeta.DEPRECIATION_YEAR, value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetVOMeta.MONTH_DEPRECIATION_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetVOMeta.RESIDUALS_PRICE, value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_SERIAL_NUMBER, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.LABEL2, value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.LABEL3, value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.LABEL4, value = "?????????4", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.LABEL5, value = "?????????5", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.INTERNAL_CONTROL_LABEL, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.BILL_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.CHS_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.CHS_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.CHS_VERSION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.CHANGE_INSTANCE_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.SUMMARY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.LATEST_APPROVER_ID, value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.LATEST_APPROVER_NAME, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.NEXT_APPROVER_IDS, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.NEXT_APPROVER_NAMES, value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.APPROVAL_OPINION, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_SELECTED_DATA, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTENANCE_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.COLLECTION_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.SCRAP_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.BORROW_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_USED_SERVICE_LIFE, value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetVOMeta.FINANCIAL_OPTION, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EXPENSE_ITEM, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.CUSTOMER_INFO, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.DEPRECIATION_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.DEPRECIATION_OPER_TIME, value = "??????????????????", required = false, dataTypeClass = Date.class, example = "")
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { AssetVOMeta.PAGE_INDEX, AssetVOMeta.PAGE_SIZE, AssetVOMeta.SEARCH_FIELD, AssetVOMeta.FUZZY_FIELD, AssetVOMeta.SEARCH_VALUE, AssetVOMeta.SORT_FIELD, AssetVOMeta.SORT_TYPE, AssetVOMeta.IDS })
    @SentinelResource(value = AssetServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(Asset assetVO) {
        // ?????????????????????
        if (assetVO.getPcmData() != null && assetVO.getPcmData().size() > 0) {
            // ????????????
            String idValue = assetVO.getPcmData().get("id").toString();
            CatalogData pcmData = new CatalogData();
            pcmData.setId(idValue);
            pcmData.setData(assetVO.getPcmData());
            pcmData.setOwnerId(assetVO.getId());
            pcmData.setCatalogId(assetVO.getCategoryId());
            pcmData.setTenantId(SessionUser.getCurrent().getActivatedTenantId());
            Logger.info("update pcm data:");
			Logger.info("getId:" + pcmData.getId());
			Logger.info("getOwnerId:" + pcmData.getOwnerId());
			Logger.info("getTenantId:" + pcmData.getTenantId());
			Logger.info("getCatalogId:" + pcmData.getCatalogId());
			Logger.info("getData:" + pcmData.getData());
            Result pcmResult = CatalogServiceProxy.api().saveData(pcmData);
            if (!pcmResult.isSuccess()) {
                return pcmResult;
            }
        }
        Result result = assetService.update(assetVO, SaveMode.NOT_NULL_FIELDS);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "489517168661102592"),
		@ApiImplicitParam(name = AssetVOMeta.CATEGORY_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "486917781384597505"),
		@ApiImplicitParam(name = AssetVOMeta.CATEGORY_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.BATCH_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.OWNER_CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.DISPLAY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.CLEAN_OUT, value = "????????????", required = false, dataTypeClass = String.class, example = "0"),
		@ApiImplicitParam(name = AssetVOMeta.GOODS_ID, value = "??????????????????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.NAME, value = "????????????????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetVOMeta.MANUFACTURER_ID, value = "??????????????????", required = false, dataTypeClass = String.class, example = "473623897560842240"),
		@ApiImplicitParam(name = AssetVOMeta.MODEL, value = "????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.PICTURE_ID, value = "????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.UNIT, value = "????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.SERVICE_LIFE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "1212.00"),
		@ApiImplicitParam(name = AssetVOMeta.SAFETY_LEVEL_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.SERIAL_NUMBER, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.OWN_COMPANY_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetVOMeta.MANAGER_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.USE_ORGANIZATION_ID, value = "????????????/??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetVOMeta.USE_USER_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.POSITION_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.POSITION_DETAIL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.WAREHOUSE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.SOURCE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_NUMBER, value = "????????????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = AssetVOMeta.REMAIN_NUMBER, value = "????????????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = AssetVOMeta.PURCHASE_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetVOMeta.PRODUCTION_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetVOMeta.REGISTER_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetVOMeta.RFID, value = "RFID??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.LAST_VERIFICATION_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetVOMeta.PURPOSE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTAINER_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTAINER_NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetVOMeta.MAINTENANCE_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.CONTACTS, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.CONTACT_INFORMATION, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.DIRECTOR, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTENANCE_START_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTENANCE_END_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTENANCE_NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.FINANCIAL_CATEGORY_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.FINANCIAL_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.SUPPLIER_ID, value = "???????????????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.TAX_AMOUNT_RATE, value = "??????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.TAX_AMOUNT_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.TOTAL_AMOUNT_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetVOMeta.ORIGINAL_UNIT_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.ACCUMULATED_DEPRECIATION, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.RESIDUALS_RATE, value = "residuals_rate", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.NAV_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.PURCHASE_UNIT_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.ENTRY_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetVOMeta.FINANCIAL_NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_IP, value = "??????IP", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.MANAGE_IP, value = "??????IP", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_CPU, value = "??????CPU", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_MEMORY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_LABEL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_CONF, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_ENVIRONMENT_CODE, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_SERIAL_NUMBER, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.RACK_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.RACK_UP_NUMBER, value = "?????????????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetVOMeta.RACK_DOWN_NUMBER, value = "?????????????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetVOMeta.LABEL, value = "?????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.INTERNAL_CONTROL_LABEL, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.BILL_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.CHS_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.CHS_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.CHS_VERSION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.CHANGE_INSTANCE_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.SUMMARY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.LATEST_APPROVER_ID, value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.LATEST_APPROVER_NAME, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.NEXT_APPROVER_IDS, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.NEXT_APPROVER_NAMES, value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.APPROVAL_OPINION, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.REGION_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.GOODS_STOCK_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CURRENT_YEAR_DEPRECIATION", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.DEPRECIATION_YEAR", value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = "AssetVOMeta.MONTH_DEPRECIATION_PRICE", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.RESIDUALS_PRICE", value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL2", value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL3", value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL4", value = "?????????4", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL5", value = "?????????5", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.ASSET_SELECTED_DATA", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.REGION_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.GOODS_STOCK_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CURRENT_YEAR_DEPRECIATION", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.DEPRECIATION_YEAR", value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = "AssetVOMeta.MONTH_DEPRECIATION_PRICE", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.RESIDUALS_PRICE", value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL2", value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL3", value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL4", value = "?????????4", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL5", value = "?????????5", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.ASSET_SELECTED_DATA", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.REGION_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.GOODS_STOCK_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CURRENT_YEAR_DEPRECIATION", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.DEPRECIATION_YEAR", value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = "AssetVOMeta.MONTH_DEPRECIATION_PRICE", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.RESIDUALS_PRICE", value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL2", value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL3", value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL4", value = "?????????4", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL5", value = "?????????5", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.ASSET_SELECTED_DATA", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.REGION_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.GOODS_STOCK_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CURRENT_YEAR_DEPRECIATION", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.DEPRECIATION_YEAR", value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = "AssetVOMeta.MONTH_DEPRECIATION_PRICE", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.RESIDUALS_PRICE", value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL2", value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL3", value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL4", value = "?????????4", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL5", value = "?????????5", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.ASSET_SELECTED_DATA", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.REGION_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.GOODS_STOCK_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CURRENT_YEAR_DEPRECIATION", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.DEPRECIATION_YEAR", value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = "AssetVOMeta.MONTH_DEPRECIATION_PRICE", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.RESIDUALS_PRICE", value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL2", value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL3", value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL4", value = "?????????4", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL5", value = "?????????5", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.ASSET_SELECTED_DATA", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.REGION_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.GOODS_STOCK_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTENANCE_METHOD, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.SUGGEST_MAINTENANCE_METHOD, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.CURRENT_YEAR_DEPRECIATION, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetVOMeta.DEPRECIATION_YEAR, value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetVOMeta.MONTH_DEPRECIATION_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetVOMeta.RESIDUALS_PRICE, value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetVOMeta.LABEL2, value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.LABEL3, value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.LABEL4, value = "?????????4", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.LABEL5, value = "?????????5", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_SELECTED_DATA, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTENANCE_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.COLLECTION_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.SCRAP_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.BORROW_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_USED_SERVICE_LIFE, value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetVOMeta.FINANCIAL_OPTION, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EXPENSE_ITEM, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.CUSTOMER_INFO, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.DEPRECIATION_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.DEPRECIATION_OPER_TIME, value = "??????????????????", required = false, dataTypeClass = Date.class, example = "")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetVOMeta.PAGE_INDEX, AssetVOMeta.PAGE_SIZE, AssetVOMeta.SEARCH_FIELD, AssetVOMeta.FUZZY_FIELD, AssetVOMeta.SEARCH_VALUE, AssetVOMeta.SORT_FIELD, AssetVOMeta.SORT_TYPE, AssetVOMeta.IDS })
    @SentinelResource(value = AssetServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(AssetVO assetVO) {
        Result result = assetService.save(assetVO, SaveMode.NOT_NULL_FIELDS);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = AssetServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetServiceProxy.GET_BY_ID)
    public Result<Asset> getById(String id) {
        Result<Asset> result = new Result<>();
        Asset asset = assetService.getById(id);
        assetService.dao().fill(asset).with(AssetMeta.CATEGORY).with(AssetMeta.CATEGORY_FINANCE).with(AssetMeta.GOODS).with(AssetMeta.MANUFACTURER).with(AssetMeta.MAINTENANCE_METHOD_DATA).with(AssetMeta.SUGGEST_MAINTENANCE_METHOD_DATA).with(AssetMeta.POSITION).with(AssetMeta.MAINTNAINER).with(AssetMeta.SUPPLIER).with(AssetMeta.OWNER_COMPANY).with(AssetMeta.USE_ORGANIZATION).with(AssetMeta.MANAGER).with(AssetMeta.REGION).with(AssetMeta.USE_USER).with(AssetMeta.ORIGINATOR).with(AssetMeta.ASSET_CYCLE_STATUS).with(AssetMeta.RACK).with(AssetMeta.SOURCE).with(AssetMeta.SAFETY_LEVEL).with(AssetMeta.EQUIPMENT_ENVIRONMENT).with(AssetMeta.ASSET_MAINTENANCE_STATUS).with(AssetMeta.EXPENSE_ITEM_DICT).with(AssetMeta.FINANCIAL_OPTION_DICT).execute();
        assetService.dao().join(asset.getManager(), Person.class);
        assetService.dao().join(asset.getUseUser(), Person.class);
        // ?????????????????????
        asset.setCatalogAttribute(assetCategoryService.queryCatalogAttributeByAssetCategory(asset.getCategoryId()));
        asset.setExtData(assetService.getExtDataById(asset.getId(), asset.getCategoryId()));
        result.success(true).data(asset);
        return result;
    }

    /**
     * ?????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetServiceProxy.GET_BY_IDS)
    public Result<List<Asset>> getByIds(List<String> ids) {
        Result<List<Asset>> result = new Result<>();
        List<Asset> list = assetService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "489517168661102592"),
		@ApiImplicitParam(name = AssetVOMeta.CATEGORY_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "486917781384597505"),
		@ApiImplicitParam(name = AssetVOMeta.CATEGORY_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.BATCH_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.OWNER_CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.DISPLAY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.CLEAN_OUT, value = "????????????", required = false, dataTypeClass = String.class, example = "0"),
		@ApiImplicitParam(name = AssetVOMeta.GOODS_ID, value = "??????????????????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.NAME, value = "????????????????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetVOMeta.MANUFACTURER_ID, value = "??????????????????", required = false, dataTypeClass = String.class, example = "473623897560842240"),
		@ApiImplicitParam(name = AssetVOMeta.MODEL, value = "????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.PICTURE_ID, value = "????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.UNIT, value = "????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.SERVICE_LIFE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "1212.00"),
		@ApiImplicitParam(name = AssetVOMeta.SAFETY_LEVEL_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.SERIAL_NUMBER, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.OWN_COMPANY_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetVOMeta.MANAGER_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.USE_ORGANIZATION_ID, value = "????????????/??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetVOMeta.USE_USER_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.POSITION_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.POSITION_DETAIL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.WAREHOUSE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.SOURCE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_NUMBER, value = "????????????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = AssetVOMeta.REMAIN_NUMBER, value = "????????????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = AssetVOMeta.PURCHASE_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetVOMeta.PRODUCTION_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetVOMeta.REGISTER_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetVOMeta.RFID, value = "RFID??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.LAST_VERIFICATION_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetVOMeta.PURPOSE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTAINER_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTAINER_NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetVOMeta.MAINTENANCE_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.CONTACTS, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.CONTACT_INFORMATION, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.DIRECTOR, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTENANCE_START_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTENANCE_END_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTENANCE_NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.FINANCIAL_CATEGORY_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.FINANCIAL_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.SUPPLIER_ID, value = "???????????????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.TAX_AMOUNT_RATE, value = "??????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.TAX_AMOUNT_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.TOTAL_AMOUNT_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetVOMeta.ORIGINAL_UNIT_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.ACCUMULATED_DEPRECIATION, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.RESIDUALS_RATE, value = "residuals_rate", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.NAV_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.PURCHASE_UNIT_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.ENTRY_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetVOMeta.FINANCIAL_NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_IP, value = "??????IP", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.MANAGE_IP, value = "??????IP", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_CPU, value = "??????CPU", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_MEMORY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_LABEL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_CONF, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_ENVIRONMENT_CODE, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_SERIAL_NUMBER, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.RACK_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.RACK_UP_NUMBER, value = "?????????????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetVOMeta.RACK_DOWN_NUMBER, value = "?????????????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetVOMeta.LABEL, value = "?????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.INTERNAL_CONTROL_LABEL, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.BILL_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "E001"),
		@ApiImplicitParam(name = AssetVOMeta.CHS_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.CHS_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.CHS_VERSION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.CHANGE_INSTANCE_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.SUMMARY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.LATEST_APPROVER_ID, value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.LATEST_APPROVER_NAME, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.NEXT_APPROVER_IDS, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.NEXT_APPROVER_NAMES, value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.APPROVAL_OPINION, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.REGION_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.GOODS_STOCK_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CURRENT_YEAR_DEPRECIATION", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.DEPRECIATION_YEAR", value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = "AssetVOMeta.MONTH_DEPRECIATION_PRICE", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.RESIDUALS_PRICE", value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL2", value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL3", value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL4", value = "?????????4", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL5", value = "?????????5", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.ASSET_SELECTED_DATA", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.REGION_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.GOODS_STOCK_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CURRENT_YEAR_DEPRECIATION", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.DEPRECIATION_YEAR", value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = "AssetVOMeta.MONTH_DEPRECIATION_PRICE", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.RESIDUALS_PRICE", value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL2", value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL3", value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL4", value = "?????????4", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL5", value = "?????????5", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.ASSET_SELECTED_DATA", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.REGION_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.GOODS_STOCK_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CURRENT_YEAR_DEPRECIATION", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.DEPRECIATION_YEAR", value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = "AssetVOMeta.MONTH_DEPRECIATION_PRICE", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.RESIDUALS_PRICE", value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL2", value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL3", value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL4", value = "?????????4", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL5", value = "?????????5", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.ASSET_SELECTED_DATA", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.REGION_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.GOODS_STOCK_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CURRENT_YEAR_DEPRECIATION", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.DEPRECIATION_YEAR", value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = "AssetVOMeta.MONTH_DEPRECIATION_PRICE", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.RESIDUALS_PRICE", value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL2", value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL3", value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL4", value = "?????????4", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL5", value = "?????????5", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.ASSET_SELECTED_DATA", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.REGION_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.GOODS_STOCK_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CURRENT_YEAR_DEPRECIATION", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.DEPRECIATION_YEAR", value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = "AssetVOMeta.MONTH_DEPRECIATION_PRICE", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.RESIDUALS_PRICE", value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL2", value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL3", value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL4", value = "?????????4", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL5", value = "?????????5", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.ASSET_SELECTED_DATA", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.REGION_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.GOODS_STOCK_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTENANCE_METHOD, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.SUGGEST_MAINTENANCE_METHOD, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.CURRENT_YEAR_DEPRECIATION, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetVOMeta.DEPRECIATION_YEAR, value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetVOMeta.MONTH_DEPRECIATION_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetVOMeta.RESIDUALS_PRICE, value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetVOMeta.LABEL2, value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.LABEL3, value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.LABEL4, value = "?????????4", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.LABEL5, value = "?????????5", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_SELECTED_DATA, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTENANCE_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.COLLECTION_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.SCRAP_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.BORROW_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_USED_SERVICE_LIFE, value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetVOMeta.FINANCIAL_OPTION, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EXPENSE_ITEM, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.CUSTOMER_INFO, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.DEPRECIATION_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.DEPRECIATION_OPER_TIME, value = "??????????????????", required = false, dataTypeClass = Date.class, example = "")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetVOMeta.PAGE_INDEX, AssetVOMeta.PAGE_SIZE })
    @SentinelResource(value = AssetServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetServiceProxy.QUERY_LIST)
    public Result<List<Asset>> queryList(AssetVO sample) {
        Result<List<Asset>> result = new Result<>();
        List<Asset> list = assetService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "489517168661102592"),
		@ApiImplicitParam(name = AssetVOMeta.CATEGORY_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "486917781384597505"),
		@ApiImplicitParam(name = AssetVOMeta.CATEGORY_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.BATCH_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.OWNER_CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.DISPLAY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.CLEAN_OUT, value = "????????????", required = false, dataTypeClass = String.class, example = "0"),
		@ApiImplicitParam(name = AssetVOMeta.GOODS_ID, value = "??????????????????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.NAME, value = "????????????????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetVOMeta.MANUFACTURER_ID, value = "??????????????????", required = false, dataTypeClass = String.class, example = "473623897560842240"),
		@ApiImplicitParam(name = AssetVOMeta.MODEL, value = "????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.PICTURE_ID, value = "????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.UNIT, value = "????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.SERVICE_LIFE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "1212.00"),
		@ApiImplicitParam(name = AssetVOMeta.SAFETY_LEVEL_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.SERIAL_NUMBER, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.OWN_COMPANY_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetVOMeta.MANAGER_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.USE_ORGANIZATION_ID, value = "????????????/??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetVOMeta.USE_USER_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.POSITION_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.POSITION_DETAIL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.WAREHOUSE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.SOURCE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_NUMBER, value = "????????????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = AssetVOMeta.REMAIN_NUMBER, value = "????????????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = AssetVOMeta.PURCHASE_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetVOMeta.PRODUCTION_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetVOMeta.REGISTER_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetVOMeta.RFID, value = "RFID??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.LAST_VERIFICATION_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetVOMeta.PURPOSE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTAINER_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTAINER_NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetVOMeta.MAINTENANCE_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.CONTACTS, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.CONTACT_INFORMATION, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.DIRECTOR, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTENANCE_START_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTENANCE_END_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTENANCE_NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.FINANCIAL_CATEGORY_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.FINANCIAL_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.SUPPLIER_ID, value = "???????????????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.TAX_AMOUNT_RATE, value = "??????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.TAX_AMOUNT_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.TOTAL_AMOUNT_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetVOMeta.ORIGINAL_UNIT_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.ACCUMULATED_DEPRECIATION, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.RESIDUALS_RATE, value = "residuals_rate", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.NAV_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.PURCHASE_UNIT_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.ENTRY_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetVOMeta.FINANCIAL_NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_IP, value = "??????IP", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.MANAGE_IP, value = "??????IP", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_CPU, value = "??????CPU", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_MEMORY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_LABEL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_CONF, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_ENVIRONMENT_CODE, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_SERIAL_NUMBER, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.RACK_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.RACK_UP_NUMBER, value = "?????????????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetVOMeta.RACK_DOWN_NUMBER, value = "?????????????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetVOMeta.LABEL, value = "?????????1", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.INTERNAL_CONTROL_LABEL, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.BILL_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "E001"),
		@ApiImplicitParam(name = AssetVOMeta.CHS_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.CHS_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.CHS_VERSION, value = "???????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.CHANGE_INSTANCE_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.SUMMARY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.LATEST_APPROVER_ID, value = "?????????????????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.LATEST_APPROVER_NAME, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.NEXT_APPROVER_IDS, value = "?????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.NEXT_APPROVER_NAMES, value = "????????????????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.APPROVAL_OPINION, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.REGION_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.GOODS_STOCK_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CURRENT_YEAR_DEPRECIATION", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.DEPRECIATION_YEAR", value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = "AssetVOMeta.MONTH_DEPRECIATION_PRICE", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.RESIDUALS_PRICE", value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL2", value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL3", value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL4", value = "?????????4", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL5", value = "?????????5", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.ASSET_SELECTED_DATA", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.REGION_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.GOODS_STOCK_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CURRENT_YEAR_DEPRECIATION", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.DEPRECIATION_YEAR", value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = "AssetVOMeta.MONTH_DEPRECIATION_PRICE", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.RESIDUALS_PRICE", value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL2", value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL3", value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL4", value = "?????????4", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL5", value = "?????????5", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.ASSET_SELECTED_DATA", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.REGION_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.GOODS_STOCK_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CURRENT_YEAR_DEPRECIATION", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.DEPRECIATION_YEAR", value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = "AssetVOMeta.MONTH_DEPRECIATION_PRICE", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.RESIDUALS_PRICE", value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL2", value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL3", value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL4", value = "?????????4", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL5", value = "?????????5", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.ASSET_SELECTED_DATA", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.REGION_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.GOODS_STOCK_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CURRENT_YEAR_DEPRECIATION", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.DEPRECIATION_YEAR", value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = "AssetVOMeta.MONTH_DEPRECIATION_PRICE", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.RESIDUALS_PRICE", value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL2", value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL3", value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL4", value = "?????????4", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL5", value = "?????????5", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.ASSET_SELECTED_DATA", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.REGION_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.GOODS_STOCK_ID", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.CURRENT_YEAR_DEPRECIATION", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.DEPRECIATION_YEAR", value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = "AssetVOMeta.MONTH_DEPRECIATION_PRICE", value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.RESIDUALS_PRICE", value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL2", value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL3", value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL4", value = "?????????4", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.LABEL5", value = "?????????5", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "AssetVOMeta.ASSET_SELECTED_DATA", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.REGION_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.GOODS_STOCK_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTENANCE_METHOD, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.SUGGEST_MAINTENANCE_METHOD, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.CURRENT_YEAR_DEPRECIATION, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetVOMeta.DEPRECIATION_YEAR, value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetVOMeta.MONTH_DEPRECIATION_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetVOMeta.RESIDUALS_PRICE, value = "??????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetVOMeta.LABEL2, value = "?????????2", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.LABEL3, value = "?????????3", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.LABEL4, value = "?????????4", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.LABEL5, value = "?????????5", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_SELECTED_DATA, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTENANCE_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.COLLECTION_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.SCRAP_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.BORROW_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_USED_SERVICE_LIFE, value = "????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetVOMeta.FINANCIAL_OPTION, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EXPENSE_ITEM, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.CUSTOMER_INFO, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.DEPRECIATION_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.DEPRECIATION_OPER_TIME, value = "??????????????????", required = false, dataTypeClass = Date.class, example = "")
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = AssetServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<Asset>> queryPagedList(AssetVO sample) {
        Result<PagedList<Asset>> result = new Result<>();
        PagedList<Asset> list = assetService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        assetService.joinData(list.getList());
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "489517168661102592"),
		@ApiImplicitParam(name = AssetVOMeta.CATEGORY_ID, value = "????????????", required = true, dataTypeClass = String.class, example = "486917781384597505"),
		@ApiImplicitParam(name = AssetVOMeta.CATEGORY_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.BATCH_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.DISPLAY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.GOODS_ID, value = "??????????????????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.NAME, value = "????????????????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetVOMeta.MANUFACTURER_ID, value = "??????????????????", required = false, dataTypeClass = String.class, example = "473623897560842240"),
		@ApiImplicitParam(name = AssetVOMeta.MODEL, value = "????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.PICTURE_ID, value = "????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.UNIT, value = "????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.SERVICE_LIFE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "1212.00"),
		@ApiImplicitParam(name = AssetVOMeta.SERIAL_NUMBER, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.OWN_COMPANY_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetVOMeta.MANAGER_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.USE_ORGANIZATION_ID, value = "????????????/??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetVOMeta.USE_USER_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.POSITION_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.POSITION_DETAIL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.WAREHOUSE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.SOURCE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_NUMBER, value = "????????????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = AssetVOMeta.REMAIN_NUMBER, value = "????????????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = AssetVOMeta.PURCHASE_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetVOMeta.RFID, value = "??????RFID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTAINER_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTAINER_NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetVOMeta.CONTACTS, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.CONTACT_INFORMATION, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.DIRECTOR, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTENANCE_START_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTENANCE_END_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTENANCE_NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.FINANCIAL_CATEGORY_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.FINANCIAL_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.SUPPLIER_ID, value = "???????????????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.ORIGINAL_UNIT_PRICE, value = "????????????(??????)", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.ACCUMULATED_DEPRECIATION, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.RESIDUALS_RATE, value = "?????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.NAV_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.PURCHASE_UNIT_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.ENTRY_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetVOMeta.FINANCIAL_NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_IP, value = "??????IP", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.MANAGE_IP, value = "??????IP", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_CPU, value = "??????CPU", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_MEMORY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.RACK_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.RACK_UP_NUMBER, value = "?????????????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetVOMeta.RACK_DOWN_NUMBER, value = "?????????????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetVOMeta.LABEL, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 9)
    @SentinelResource(value = AssetServiceProxy.QUERY_PAGED_LIST_BY_SELECT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetServiceProxy.QUERY_PAGED_LIST_BY_SELECT)
    public Result<PagedList<Asset>> queryPagedListBySelect(AssetVO sample, String assetBusinessType, String assetOwnerId, String assetSelectedCode, String assetSearchContent) {
        Result<PagedList<Asset>> result = new Result<>();
        // if(StringUtil.isBlank(sample.getCategoryId())){
        // sample.setCategoryId(assetDataService.queryPcmIdByCode(PcmCodeEnum.ASSET.code()));
        // }
        PagedList<Asset> list = assetService.queryPagedListBySelect(sample, assetBusinessType, assetOwnerId, assetSelectedCode, assetSearchContent);
        assetService.joinData(list.getList());
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "489517168661102592"),
		@ApiImplicitParam(name = AssetVOMeta.CATEGORY_ID, value = "????????????", required = true, dataTypeClass = String.class, example = "486917781384597505"),
		@ApiImplicitParam(name = AssetVOMeta.CATEGORY_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.BATCH_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.DISPLAY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.GOODS_ID, value = "??????????????????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.NAME, value = "????????????????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetVOMeta.MANUFACTURER_ID, value = "??????????????????", required = false, dataTypeClass = String.class, example = "473623897560842240"),
		@ApiImplicitParam(name = AssetVOMeta.MODEL, value = "????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.PICTURE_ID, value = "????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.UNIT, value = "????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.SERVICE_LIFE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "1212.00"),
		@ApiImplicitParam(name = AssetVOMeta.SERIAL_NUMBER, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.OWN_COMPANY_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetVOMeta.MANAGER_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.USE_ORGANIZATION_ID, value = "????????????/??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetVOMeta.USE_USER_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.POSITION_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.POSITION_DETAIL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.WAREHOUSE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.SOURCE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_NUMBER, value = "????????????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = AssetVOMeta.REMAIN_NUMBER, value = "????????????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = AssetVOMeta.PURCHASE_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetVOMeta.RFID, value = "??????RFID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTAINER_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTAINER_NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetVOMeta.CONTACTS, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.CONTACT_INFORMATION, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.DIRECTOR, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTENANCE_START_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTENANCE_END_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTENANCE_NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.FINANCIAL_CATEGORY_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.FINANCIAL_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.SUPPLIER_ID, value = "???????????????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.ORIGINAL_UNIT_PRICE, value = "????????????(??????)", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.ACCUMULATED_DEPRECIATION, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.RESIDUALS_RATE, value = "?????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.NAV_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.PURCHASE_UNIT_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.ENTRY_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetVOMeta.FINANCIAL_NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_IP, value = "??????IP", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.MANAGE_IP, value = "??????IP", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_CPU, value = "??????CPU", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_MEMORY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.RACK_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.RACK_UP_NUMBER, value = "?????????????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetVOMeta.RACK_DOWN_NUMBER, value = "?????????????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetVOMeta.LABEL, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 9)
    @SentinelResource(value = AssetServiceProxy.QUERY_PAGED_LIST_BY_EMPLOYEE_SELECT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetServiceProxy.QUERY_PAGED_LIST_BY_EMPLOYEE_SELECT)
    public Result<PagedList<Asset>> queryPagedListByEmploySelect(AssetVO sample, String selectedCode, String ownerId) {
        Result<PagedList<Asset>> result = new Result<>();
        PagedList<Asset> list = assetService.queryPagedListByEmployeeSelect(sample, selectedCode, ownerId);
        assetService.joinData(list.getList());
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "489517168661102592"),
		@ApiImplicitParam(name = AssetVOMeta.CATEGORY_ID, value = "????????????", required = true, dataTypeClass = String.class, example = "486917781384597505"),
		@ApiImplicitParam(name = AssetVOMeta.CATEGORY_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.BATCH_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.DISPLAY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.GOODS_ID, value = "??????????????????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.NAME, value = "????????????????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetVOMeta.MANUFACTURER_ID, value = "??????????????????", required = false, dataTypeClass = String.class, example = "473623897560842240"),
		@ApiImplicitParam(name = AssetVOMeta.MODEL, value = "????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.PICTURE_ID, value = "????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.UNIT, value = "????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.SERVICE_LIFE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "1212.00"),
		@ApiImplicitParam(name = AssetVOMeta.SERIAL_NUMBER, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.OWN_COMPANY_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetVOMeta.MANAGER_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.USE_ORGANIZATION_ID, value = "????????????/??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetVOMeta.USE_USER_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.POSITION_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.POSITION_DETAIL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.WAREHOUSE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.SOURCE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_NUMBER, value = "????????????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = AssetVOMeta.REMAIN_NUMBER, value = "????????????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = AssetVOMeta.PURCHASE_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetVOMeta.RFID, value = "??????RFID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTAINER_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTAINER_NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetVOMeta.CONTACTS, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.CONTACT_INFORMATION, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.DIRECTOR, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTENANCE_START_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTENANCE_END_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTENANCE_NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.FINANCIAL_CATEGORY_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.FINANCIAL_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.SUPPLIER_ID, value = "???????????????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.ORIGINAL_UNIT_PRICE, value = "????????????(??????)", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.ACCUMULATED_DEPRECIATION, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.RESIDUALS_RATE, value = "?????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.NAV_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.PURCHASE_UNIT_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.ENTRY_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetVOMeta.FINANCIAL_NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_IP, value = "??????IP", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.MANAGE_IP, value = "??????IP", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_CPU, value = "??????CPU", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_MEMORY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.RACK_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.RACK_UP_NUMBER, value = "?????????????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetVOMeta.RACK_DOWN_NUMBER, value = "?????????????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetVOMeta.LABEL, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 9)
    @SentinelResource(value = AssetServiceProxy.QUERY_PAGED_LIST_BY_EMPLOYEE_SELECTED, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetServiceProxy.QUERY_PAGED_LIST_BY_EMPLOYEE_SELECTED)
    public Result<PagedList<Asset>> queryPagedListByEmploySelected(AssetVO sample, String selectedCode, String ownerId, String dataType) {
        Result<PagedList<Asset>> result = new Result<>();
        PagedList<Asset> list = assetService.queryPagedListByEmployeeSelected(sample, selectedCode, ownerId, dataType);
        assetService.joinData(list.getList());
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "489517168661102592"),
		@ApiImplicitParam(name = AssetVOMeta.CATEGORY_ID, value = "????????????", required = true, dataTypeClass = String.class, example = "486917781384597505"),
		@ApiImplicitParam(name = AssetVOMeta.CATEGORY_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.BATCH_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.DISPLAY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.GOODS_ID, value = "??????????????????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.NAME, value = "????????????????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetVOMeta.MANUFACTURER_ID, value = "??????????????????", required = false, dataTypeClass = String.class, example = "473623897560842240"),
		@ApiImplicitParam(name = AssetVOMeta.MODEL, value = "????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.PICTURE_ID, value = "????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.UNIT, value = "????????????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.SERVICE_LIFE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "1212.00"),
		@ApiImplicitParam(name = AssetVOMeta.SERIAL_NUMBER, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.OWN_COMPANY_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetVOMeta.MANAGER_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.USE_ORGANIZATION_ID, value = "????????????/??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetVOMeta.USE_USER_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.POSITION_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.POSITION_DETAIL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.WAREHOUSE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.SOURCE_ID, value = "??????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_NUMBER, value = "????????????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = AssetVOMeta.REMAIN_NUMBER, value = "????????????", required = false, dataTypeClass = Integer.class, example = "1"),
		@ApiImplicitParam(name = AssetVOMeta.PURCHASE_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetVOMeta.RFID, value = "??????RFID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.ASSET_NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTAINER_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTAINER_NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetVOMeta.CONTACTS, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.CONTACT_INFORMATION, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.DIRECTOR, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTENANCE_START_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTENANCE_END_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetVOMeta.MAINTENANCE_NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.FINANCIAL_CATEGORY_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.FINANCIAL_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.SUPPLIER_ID, value = "???????????????", required = false, dataTypeClass = String.class, example = "[]"),
		@ApiImplicitParam(name = AssetVOMeta.ORIGINAL_UNIT_PRICE, value = "????????????(??????)", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.ACCUMULATED_DEPRECIATION, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.RESIDUALS_RATE, value = "?????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.NAV_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.PURCHASE_UNIT_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetVOMeta.ENTRY_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetVOMeta.FINANCIAL_NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_IP, value = "??????IP", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.MANAGE_IP, value = "??????IP", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_CPU, value = "??????CPU", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_MEMORY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.RACK_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetVOMeta.RACK_UP_NUMBER, value = "?????????????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetVOMeta.RACK_DOWN_NUMBER, value = "?????????????????????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = AssetVOMeta.LABEL, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 10)
    @SentinelResource(value = AssetServiceProxy.QUERY_PAGED_LIST_BY_SELECTED, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetServiceProxy.QUERY_PAGED_LIST_BY_SELECTED)
    public Result<PagedList<Asset>> queryPagedListBySelected(AssetVO sample, String assetSelectedCode, String assetOwnerId, String dataType) {
        Result<PagedList<Asset>> result = new Result<>();
        PagedList<Asset> list = assetService.queryPagedListBySelected(sample, assetSelectedCode, assetOwnerId, dataType);
        assetService.joinData(list.getList());
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????
     */
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 12)
    @SentinelResource(value = AssetServiceProxy.FOR_BATCH_APPROVAL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetServiceProxy.FOR_BATCH_APPROVAL)
    public Result forBatchApproval(List<String> ids) {
        return assetService.forBatchApproval(ids);
    }

    /**
     * ????????????
     */
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 13)
    @SentinelResource(value = AssetServiceProxy.BATCH_CONFIRM_OPERATION, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetServiceProxy.BATCH_CONFIRM_OPERATION)
    public Result batchConfirmOperation(List<String> ids) {
        return assetService.batchConfirmOperation(ids, IDGenerator.getSnowflakeIdString());
    }

    /**
     * ??????????????????
     */
    @ApiOperationSupport(order = 14)
    @SentinelResource(value = AssetServiceProxy.QUERY_ASSET_STATUS_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetServiceProxy.QUERY_ASSET_STATUS_LIST)
    public Result<JSONArray> queryAssetStatusList(String owner) {
        Result<JSONArray> result = new Result<>();
        JSONArray list = assetService.queryAssetStatusList(owner);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperationSupport(order = 15)
    @SentinelResource(value = AssetServiceProxy.QUERY_ASSET_INSERT_APPROVAL_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetServiceProxy.QUERY_ASSET_INSERT_APPROVAL_LIST)
    public Result<PagedList<Asset>> queryAssetInsertApprovalList(AssetVO sample) {
        Result<PagedList<Asset>> result = new Result<>();
        sample.setStatus(AssetHandleStatusEnum.APPROVAL.code());
        PagedList<Asset> list = null;
        String employId = SessionUser.getCurrent().getActivatedEmployeeId();
        ChangeDefinitionVO chs = new ChangeDefinitionVO();
        chs.setValid(1);
        chs.setCode(AssetOperateEnum.EAM_ASSET_INSERT.code());
        Result<List<ChangeDefinition>> chsListResult = ChangeDefinitionServiceProxy.api().queryList(chs);
        if (!chsListResult.isSuccess()) {
            return result.success(false).data(list).message("????????????????????????");
        }
        if (chsListResult.getData().size() == 0) {
            return result.success(false).data(list).message("???????????????????????????????????????");
        }
        ChangeDefinition insertChsDef = chsListResult.getData().get(0);
        String approvers = insertChsDef.getSimpleApprovers();
        JSONArray approversArr = JSONArray.parseArray(approvers);
        if (StringUtil.isBlank(approvers) || approversArr == null || approversArr.size() == 0) {
            return result.success(false).data(list).message("???????????????????????????????????????");
        }
        boolean approval = true;
        if (approval) {
            list = assetService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
            assetService.joinData(list.getList());
        } else {
            // list=new PagedList<>();
        }
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????
     */
    @ApiOperation(value = "??????")
    @ApiOperationSupport(order = 15)
    @SentinelResource(value = AssetServiceProxy.APPROVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetServiceProxy.APPROVE)
    public Result approve(ProcessApproveVO approveVO) {
        return assetService.approve(approveVO);
    }

    /**
     * ????????????
     */
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 16)
    @SentinelResource(value = AssetServiceProxy.BATCH_REVOKE_OPERATION, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetServiceProxy.BATCH_REVOKE_OPERATION)
    public Result batchRevokeOperation(List<String> ids) {
        return assetService.batchRevokeOperation(ids);
    }

    /**
     * ??????
     */
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetVOMeta.INTERNAL_CONTROL_LABEL, value = "????????????", required = true, dataTypeClass = List.class, example = "12")
	})
    @ApiOperationSupport(order = 17)
    @SentinelResource(value = AssetServiceProxy.QUERY_INTERNAL_CONTROL_LABEL_DATA, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetServiceProxy.QUERY_INTERNAL_CONTROL_LABEL_DATA)
    public Result<List<Asset>> queryInternalControlLabelData(String internalControlLabel) {
        Result<List<Asset>> result = new Result<>();
        AssetVO vo = new AssetVO();
        vo.setInternalControlLabel(internalControlLabel);
        List<Asset> list = assetService.queryList(vo);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "489517168661102592"),
		@ApiImplicitParam(name = "number", value = "??????", required = true, dataTypeClass = Integer.class, example = "0")
	})
    @ApiOperationSupport(order = 18)
    @SentinelResource(value = AssetServiceProxy.ASSET_COPY, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetServiceProxy.ASSET_COPY)
    public Result<List<String>> assetCopy(String id, int number) {
        return assetService.assetCopy(id, number);
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = AssetServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetServiceProxy.EXPORT_EXCEL)
    public void exportExcel(AssetVO sample, HttpServletResponse response, String categoryId) throws Exception {
        try {
            // ?????? Excel ??????
            InputStream inputstream = assetService.buildExcelTemplate(categoryId, AssetOperateEnum.EAM_DOWNLOAD_ASSET.code());
            if (inputstream == null) {
                return;
            }
            File f = assetDataService.saveTempFile(inputstream, "tmp_download_asset_data.xls");
            Map<String, Object> map = assetDataService.queryAssetMap(assetDataService.queryAssetList(null, sample), categoryId);
            TemplateExportParams templateExportParams = new TemplateExportParams(f.getPath());
            templateExportParams.setScanAllsheet(true);
            Workbook workbook = ExcelExportUtil.exportExcel(templateExportParams, map);
            // response.setCharacterEncoding("UTF-8");
            // response.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode("????????????.xls", "UTF-8"))));
            // response.setContentType("application/vnd.ms-excel");
            DownloadUtil.writeToOutput(response, workbook, "????????????.xls");
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiOperationSupport(order = 16)
    @SentinelResource(value = AssetServiceProxy.USER_LOGIN_DATA, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetServiceProxy.USER_LOGIN_DATA)
    public Result<JSONObject> userLoginData() {
        // {account:"admin",password:"123456",captcha:"123456"}
        Result<JSONObject> result = new Result<>();
        JSONObject obj = new JSONObject();
        obj.put("account", "");
        obj.put("password", "");
        obj.put("captcha", "");
        Rcd rs = assetService.dao().queryRecord("select * from sys_config where id='system.login.default'");
        if (rs != null) {
            String value = rs.getString("value");
            if (!StringUtil.isBlank(value)) {
                JSONObject valueObj = JSONObject.parseObject(value);
                if (valueObj != null) {
                    obj = valueObj;
                }
            }
        }
        result.success(true).data(obj);
        return result;
    }

    /**
     * ???????????????????????????,??????????????????
     */
    @ApiOperation(value = "????????????????????????????????????????????????")
    @ApiOperationSupport(order = 17)
    @SentinelResource(value = AssetServiceProxy.QUERY_EMPLOYEE_HAVE_ASSET, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetServiceProxy.QUERY_EMPLOYEE_HAVE_ASSET)
    public Result<JSONObject> queryEmployeeHaveAsset(String userId, String employeeNumber) {
        String emplId = "";
        // userId ??????
        if (StringUtil.isBlank(userId)) {
            if (StringUtil.isBlank(employeeNumber)) {
                return ErrorDesc.failureMessage("???????????????ID??????????????????");
            } else {
                RcdSet rs = assetService.dao().query("select * from hrm_employee where deleted=0 and badge=?", employeeNumber);
                if (rs.size() == 0) {
                    return ErrorDesc.failureMessage("????????????????????????");
                }
                if (rs.size() > 1) {
                    return ErrorDesc.failureMessage("???????????????????????????????????????????????????");
                }
                if (rs.size() == 1) {
                    emplId = rs.getRcd(0).getString("id");
                }
            }
        } else {
            emplId = userId;
        }
        return assetService.queryEmployeeHaveAsset(emplId);
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = AssetServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response, String categoryId) throws Exception {
        // ?????? Excel ??????
        // categoryId="497488128370540545";
        try {
            InputStream inputstream = assetService.buildExcelTemplate(categoryId, AssetOperateEnum.EAM_DOWNLOAD_ASSET.code());
            if (inputstream == null) {
            }
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode("????????????.xls", "UTF-8"))));
            response.setContentType("application/vnd.ms-excel");
            OutputStream out = response.getOutputStream();
            IOUtils.copy(inputstream, out);
            out.flush();
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = AssetServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetServiceProxy.IMPORT_EXCEL)
    public Result importExcel(MultipartHttpServletRequest request, HttpServletResponse response, String dataType, String categoryId, String ownerCode) throws Exception {
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
        boolean dataFill = false;
        Result dataFillResult = OperateServiceProxy.api().queryAssetDirectUpdateMode();
        if (dataFillResult.isSuccess()) {
            dataFill = (boolean) dataFillResult.getData();
        }
        if (StringUtil.isBlank(ownerCode)) {
            return ErrorDesc.failure().message("????????????,?????????OwnerCode");
        }
        List<ValidateResult> errors = assetService.importExcel(input, 0, true, ownerCode, dataFill, AssetOperateEnum.EAM_DOWNLOAD_ASSET.code(), IDGenerator.getSnowflakeIdString());
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
			Logger.info("import Result:");
            String msg = "????????????";
            for (int i = 0; i < errors.size(); i++) {
				Logger.info(i + ":" + errors.get(i).message);
                msg = errors.get(i).message;
            }
            return ErrorDesc.failure().message(msg).data(errors);
        }
    }
}
