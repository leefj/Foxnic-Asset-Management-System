package com.dt.platform.eam.controller;

import java.util.List;
import java.util.ArrayList;
import com.dt.platform.domain.eam.AssetEmployeeLoss;
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
import org.github.foxnic.web.constants.enums.bpm.BpmEventType;
import org.github.foxnic.web.proxy.bpm.BpmCallbackController;
import org.github.foxnic.web.domain.bpm.BpmActionResult;
import org.github.foxnic.web.domain.bpm.BpmEvent;
import com.dt.platform.proxy.eam.AssetEmployeeRepairServiceProxy;
import com.dt.platform.domain.eam.meta.AssetEmployeeRepairVOMeta;
import com.dt.platform.domain.eam.AssetEmployeeRepair;
import com.dt.platform.domain.eam.AssetEmployeeRepairVO;
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
import com.dt.platform.domain.eam.meta.AssetEmployeeRepairMeta;
import com.dt.platform.domain.eam.Asset;
import org.github.foxnic.web.domain.hrm.Employee;
import org.github.foxnic.web.domain.hrm.Organization;
import org.github.foxnic.web.domain.bpm.ProcessInstance;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IAssetEmployeeRepairService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-07-02 12:53:52
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("EamAssetEmployeeRepairController")
public class AssetEmployeeRepairController extends SuperController implements BpmCallbackController {

    @Autowired
    private IAssetEmployeeRepairService assetEmployeeRepairService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "592479694884962304"),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.ORG_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "500994919175819264"),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "121212"),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.PICTURE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.RECORD_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = AssetEmployeeRepairServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetEmployeeRepairServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(AssetEmployeeRepairVO assetEmployeeRepairVO) {
        Result result = assetEmployeeRepairService.insert(assetEmployeeRepairVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "592479694884962304")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = AssetEmployeeRepairServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetEmployeeRepairServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  assetEmployeeRepairService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = assetEmployeeRepairService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetEmployeeRepairServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetEmployeeRepairServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = assetEmployeeRepairService.hasRefers(ids);
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
            Result result = assetEmployeeRepairService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = assetEmployeeRepairService.deleteByIdsLogical(canDeleteIds);
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
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "592479694884962304"),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.ORG_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "500994919175819264"),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "121212"),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.PICTURE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.RECORD_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { AssetEmployeeRepairVOMeta.PAGE_INDEX, AssetEmployeeRepairVOMeta.PAGE_SIZE, AssetEmployeeRepairVOMeta.SEARCH_FIELD, AssetEmployeeRepairVOMeta.FUZZY_FIELD, AssetEmployeeRepairVOMeta.SEARCH_VALUE, AssetEmployeeRepairVOMeta.DIRTY_FIELDS, AssetEmployeeRepairVOMeta.SORT_FIELD, AssetEmployeeRepairVOMeta.SORT_TYPE, AssetEmployeeRepairVOMeta.IDS })
    @SentinelResource(value = AssetEmployeeRepairServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetEmployeeRepairServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(AssetEmployeeRepairVO assetEmployeeRepairVO) {
        Result result = assetEmployeeRepairService.update(assetEmployeeRepairVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "592479694884962304"),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.ORG_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "500994919175819264"),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "121212"),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.PICTURE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.RECORD_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetEmployeeRepairVOMeta.PAGE_INDEX, AssetEmployeeRepairVOMeta.PAGE_SIZE, AssetEmployeeRepairVOMeta.SEARCH_FIELD, AssetEmployeeRepairVOMeta.FUZZY_FIELD, AssetEmployeeRepairVOMeta.SEARCH_VALUE, AssetEmployeeRepairVOMeta.DIRTY_FIELDS, AssetEmployeeRepairVOMeta.SORT_FIELD, AssetEmployeeRepairVOMeta.SORT_TYPE, AssetEmployeeRepairVOMeta.IDS })
    @SentinelResource(value = AssetEmployeeRepairServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetEmployeeRepairServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(AssetEmployeeRepairVO assetEmployeeRepairVO) {
        Result result = assetEmployeeRepairService.save(assetEmployeeRepairVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = AssetEmployeeRepairServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetEmployeeRepairServiceProxy.GET_BY_ID)
    public Result<AssetEmployeeRepair> getById(String id) {
        Result<AssetEmployeeRepair> result = new Result<>();
        AssetEmployeeRepair assetEmployeeRepair = assetEmployeeRepairService.getById(id);
        // join ???????????????
        assetEmployeeRepairService.dao().fill(assetEmployeeRepair).with("organization").with("originator").execute();
        assetEmployeeRepairService.dao().join(assetEmployeeRepair.getOriginator(), Person.class);
        result.success(true).data(assetEmployeeRepair);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetEmployeeRepairServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetEmployeeRepairServiceProxy.GET_BY_IDS)
    public Result<List<AssetEmployeeRepair>> getByIds(List<String> ids) {
        Result<List<AssetEmployeeRepair>> result = new Result<>();
        List<AssetEmployeeRepair> list = assetEmployeeRepairService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "592479694884962304"),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.ORG_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "500994919175819264"),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "121212"),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.PICTURE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.RECORD_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetEmployeeRepairVOMeta.PAGE_INDEX, AssetEmployeeRepairVOMeta.PAGE_SIZE })
    @SentinelResource(value = AssetEmployeeRepairServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetEmployeeRepairServiceProxy.QUERY_LIST)
    public Result<List<AssetEmployeeRepair>> queryList(AssetEmployeeRepairVO sample) {
        Result<List<AssetEmployeeRepair>> result = new Result<>();
        List<AssetEmployeeRepair> list = assetEmployeeRepairService.queryList(sample);
        List<Employee> employees = CollectorUtil.collectList(list, AssetEmployeeRepair::getOriginator);
        assetEmployeeRepairService.dao().join(employees, Person.class);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "592479694884962304"),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.ORG_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "500994919175819264"),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "121212"),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.PICTURE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.RECORD_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetEmployeeRepairVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = AssetEmployeeRepairServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetEmployeeRepairServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<AssetEmployeeRepair>> queryPagedList(AssetEmployeeRepairVO sample) {
        Result<PagedList<AssetEmployeeRepair>> result = new Result<>();
        PagedList<AssetEmployeeRepair> list = assetEmployeeRepairService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        assetEmployeeRepairService.dao().fill(list).with("organization").with("originator").execute();
        // ???????????????????????????
        assetEmployeeRepairService.joinProcess(list);
        List<Employee> employees = CollectorUtil.collectList(list, AssetEmployeeRepair::getOriginator);
        assetEmployeeRepairService.dao().join(employees, Person.class);
        result.success(true).data(list);
        return result;
    }

    /**
     *  ??????????????????
     */
    @SentinelResource(value = AssetEmployeeRepairServiceProxy.BPM_CALLBACK, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetEmployeeRepairServiceProxy.BPM_CALLBACK)
    public BpmActionResult onProcessCallback(BpmEvent event) {
        return assetEmployeeRepairService.onProcessCallback(event);
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = AssetEmployeeRepairServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetEmployeeRepairServiceProxy.EXPORT_EXCEL)
    public void exportExcel(AssetEmployeeRepairVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = assetEmployeeRepairService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = AssetEmployeeRepairServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetEmployeeRepairServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = assetEmployeeRepairService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = AssetEmployeeRepairServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetEmployeeRepairServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = assetEmployeeRepairService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
