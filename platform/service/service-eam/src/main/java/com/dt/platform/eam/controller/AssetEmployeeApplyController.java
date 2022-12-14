package com.dt.platform.eam.controller;

import java.util.List;
import java.util.ArrayList;
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
import com.dt.platform.proxy.eam.AssetEmployeeApplyServiceProxy;
import com.dt.platform.domain.eam.meta.AssetEmployeeApplyVOMeta;
import com.dt.platform.domain.eam.AssetEmployeeApply;
import com.dt.platform.domain.eam.AssetEmployeeApplyVO;
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
import com.dt.platform.domain.eam.meta.AssetEmployeeApplyMeta;
import org.github.foxnic.web.domain.hrm.Employee;
import com.dt.platform.domain.eam.Asset;
import org.github.foxnic.web.domain.hrm.Organization;
import org.github.foxnic.web.domain.bpm.ProcessInstance;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IAssetEmployeeApplyService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-07-02 22:09:47
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("EamAssetEmployeeApplyController")
public class AssetEmployeeApplyController extends SuperController implements BpmCallbackController {

    @Autowired
    private IAssetEmployeeApplyService assetEmployeeApplyService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "593493381083037696"),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.ORG_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "2"),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.CONTENT, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.APPLY_COUNT, value = "????????????", required = false, dataTypeClass = Integer.class, example = "0"),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.RECORD_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = AssetEmployeeApplyServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetEmployeeApplyServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(AssetEmployeeApplyVO assetEmployeeApplyVO) {
        Result result = assetEmployeeApplyService.insert(assetEmployeeApplyVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "593493381083037696")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = AssetEmployeeApplyServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetEmployeeApplyServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  assetEmployeeApplyService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = assetEmployeeApplyService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetEmployeeApplyServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetEmployeeApplyServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = assetEmployeeApplyService.hasRefers(ids);
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
            Result result = assetEmployeeApplyService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = assetEmployeeApplyService.deleteByIdsLogical(canDeleteIds);
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
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "593493381083037696"),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.ORG_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "2"),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.CONTENT, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.APPLY_COUNT, value = "????????????", required = false, dataTypeClass = Integer.class, example = "0"),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.RECORD_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { AssetEmployeeApplyVOMeta.PAGE_INDEX, AssetEmployeeApplyVOMeta.PAGE_SIZE, AssetEmployeeApplyVOMeta.SEARCH_FIELD, AssetEmployeeApplyVOMeta.FUZZY_FIELD, AssetEmployeeApplyVOMeta.SEARCH_VALUE, AssetEmployeeApplyVOMeta.DIRTY_FIELDS, AssetEmployeeApplyVOMeta.SORT_FIELD, AssetEmployeeApplyVOMeta.SORT_TYPE, AssetEmployeeApplyVOMeta.IDS })
    @SentinelResource(value = AssetEmployeeApplyServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetEmployeeApplyServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(AssetEmployeeApplyVO assetEmployeeApplyVO) {
        Result result = assetEmployeeApplyService.update(assetEmployeeApplyVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "593493381083037696"),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.ORG_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "2"),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.CONTENT, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.APPLY_COUNT, value = "????????????", required = false, dataTypeClass = Integer.class, example = "0"),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.RECORD_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetEmployeeApplyVOMeta.PAGE_INDEX, AssetEmployeeApplyVOMeta.PAGE_SIZE, AssetEmployeeApplyVOMeta.SEARCH_FIELD, AssetEmployeeApplyVOMeta.FUZZY_FIELD, AssetEmployeeApplyVOMeta.SEARCH_VALUE, AssetEmployeeApplyVOMeta.DIRTY_FIELDS, AssetEmployeeApplyVOMeta.SORT_FIELD, AssetEmployeeApplyVOMeta.SORT_TYPE, AssetEmployeeApplyVOMeta.IDS })
    @SentinelResource(value = AssetEmployeeApplyServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetEmployeeApplyServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(AssetEmployeeApplyVO assetEmployeeApplyVO) {
        Result result = assetEmployeeApplyService.save(assetEmployeeApplyVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = AssetEmployeeApplyServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetEmployeeApplyServiceProxy.GET_BY_ID)
    public Result<AssetEmployeeApply> getById(String id) {
        Result<AssetEmployeeApply> result = new Result<>();
        AssetEmployeeApply assetEmployeeApply = assetEmployeeApplyService.getById(id);
        // join ???????????????
        assetEmployeeApplyService.dao().fill(assetEmployeeApply).with("organization").with("originator").execute();
        result.success(true).data(assetEmployeeApply);
        assetEmployeeApplyService.dao().join(assetEmployeeApply.getOriginator(), Person.class);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetEmployeeApplyServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetEmployeeApplyServiceProxy.GET_BY_IDS)
    public Result<List<AssetEmployeeApply>> getByIds(List<String> ids) {
        Result<List<AssetEmployeeApply>> result = new Result<>();
        List<AssetEmployeeApply> list = assetEmployeeApplyService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "593493381083037696"),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.ORG_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "2"),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.CONTENT, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.APPLY_COUNT, value = "????????????", required = false, dataTypeClass = Integer.class, example = "0"),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.RECORD_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetEmployeeApplyVOMeta.PAGE_INDEX, AssetEmployeeApplyVOMeta.PAGE_SIZE })
    @SentinelResource(value = AssetEmployeeApplyServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetEmployeeApplyServiceProxy.QUERY_LIST)
    public Result<List<AssetEmployeeApply>> queryList(AssetEmployeeApplyVO sample) {
        Result<List<AssetEmployeeApply>> result = new Result<>();
        List<AssetEmployeeApply> list = assetEmployeeApplyService.queryList(sample);
        List<Employee> employees = CollectorUtil.collectList(list, AssetEmployeeApply::getOriginator);
        assetEmployeeApplyService.dao().join(employees, Person.class);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "593493381083037696"),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.ORG_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "2"),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.CONTENT, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.APPLY_COUNT, value = "????????????", required = false, dataTypeClass = Integer.class, example = "0"),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.RECORD_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetEmployeeApplyVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = AssetEmployeeApplyServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetEmployeeApplyServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<AssetEmployeeApply>> queryPagedList(AssetEmployeeApplyVO sample) {
        Result<PagedList<AssetEmployeeApply>> result = new Result<>();
        PagedList<AssetEmployeeApply> list = assetEmployeeApplyService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        assetEmployeeApplyService.dao().fill(list).with("organization").with("originator").execute();
        // ???????????????????????????
        assetEmployeeApplyService.joinProcess(list);
        List<Employee> employees = CollectorUtil.collectList(list, AssetEmployeeApply::getOriginator);
        assetEmployeeApplyService.dao().join(employees, Person.class);
        result.success(true).data(list);
        return result;
    }

    /**
     *  ??????????????????
     */
    @SentinelResource(value = AssetEmployeeApplyServiceProxy.BPM_CALLBACK, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetEmployeeApplyServiceProxy.BPM_CALLBACK)
    public BpmActionResult onProcessCallback(BpmEvent event) {
        return assetEmployeeApplyService.onProcessCallback(event);
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = AssetEmployeeApplyServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetEmployeeApplyServiceProxy.EXPORT_EXCEL)
    public void exportExcel(AssetEmployeeApplyVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = assetEmployeeApplyService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = AssetEmployeeApplyServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetEmployeeApplyServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = assetEmployeeApplyService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = AssetEmployeeApplyServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetEmployeeApplyServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = assetEmployeeApplyService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
