package com.dt.platform.vehicle.controller;

import java.util.List;
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
import com.dt.platform.proxy.vehicle.ApplyServiceProxy;
import com.dt.platform.domain.vehicle.meta.ApplyVOMeta;
import com.dt.platform.domain.vehicle.Apply;
import com.dt.platform.domain.vehicle.ApplyVO;
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
import com.dt.platform.domain.vehicle.meta.ApplyMeta;
import com.dt.platform.domain.vehicle.Info;
import org.github.foxnic.web.domain.hrm.Employee;
import org.github.foxnic.web.domain.hrm.Organization;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.vehicle.service.IApplyService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-04-02 19:49:06
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("VehicleApplyController")
public class ApplyController extends SuperController {

    @Autowired
    private IApplyService applyService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ApplyVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "562620443333234688"),
		@ApiImplicitParam(name = ApplyVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ApplyVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ApplyVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = ApplyVOMeta.ORG_ID, value = "??????", required = false, dataTypeClass = String.class, example = "500994919175819264"),
		@ApiImplicitParam(name = ApplyVOMeta.RECEIVER_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "558321538131034112"),
		@ApiImplicitParam(name = ApplyVOMeta.DRIVER, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ApplyVOMeta.CONTACT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ApplyVOMeta.COLLECTION_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-04-06 12:00:00"),
		@ApiImplicitParam(name = ApplyVOMeta.PLAN_RETURN_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = ApplyVOMeta.ACT_RETURN_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = ApplyVOMeta.CONTENT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ApplyVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ApplyVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ApplyVOMeta.RETURN_NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ApplyVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ApplyVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = ApplyServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ApplyServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(ApplyVO applyVO) {
        Result result = applyService.insert(applyVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ApplyVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "562620443333234688")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = ApplyServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ApplyServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = applyService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ApplyVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = ApplyServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ApplyServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = applyService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ApplyVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "562620443333234688"),
		@ApiImplicitParam(name = ApplyVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ApplyVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ApplyVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = ApplyVOMeta.ORG_ID, value = "??????", required = false, dataTypeClass = String.class, example = "500994919175819264"),
		@ApiImplicitParam(name = ApplyVOMeta.RECEIVER_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "558321538131034112"),
		@ApiImplicitParam(name = ApplyVOMeta.DRIVER, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ApplyVOMeta.CONTACT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ApplyVOMeta.COLLECTION_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-04-06 12:00:00"),
		@ApiImplicitParam(name = ApplyVOMeta.PLAN_RETURN_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = ApplyVOMeta.ACT_RETURN_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = ApplyVOMeta.CONTENT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ApplyVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ApplyVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ApplyVOMeta.RETURN_NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ApplyVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ApplyVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { ApplyVOMeta.PAGE_INDEX, ApplyVOMeta.PAGE_SIZE, ApplyVOMeta.SEARCH_FIELD, ApplyVOMeta.FUZZY_FIELD, ApplyVOMeta.SEARCH_VALUE, ApplyVOMeta.DIRTY_FIELDS, ApplyVOMeta.SORT_FIELD, ApplyVOMeta.SORT_TYPE, ApplyVOMeta.IDS })
    @SentinelResource(value = ApplyServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ApplyServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(ApplyVO applyVO) {
        Result result = applyService.update(applyVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ApplyVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "562620443333234688"),
		@ApiImplicitParam(name = ApplyVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ApplyVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ApplyVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = ApplyVOMeta.ORG_ID, value = "??????", required = false, dataTypeClass = String.class, example = "500994919175819264"),
		@ApiImplicitParam(name = ApplyVOMeta.RECEIVER_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "558321538131034112"),
		@ApiImplicitParam(name = ApplyVOMeta.DRIVER, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ApplyVOMeta.CONTACT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ApplyVOMeta.COLLECTION_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-04-06 12:00:00"),
		@ApiImplicitParam(name = ApplyVOMeta.PLAN_RETURN_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = ApplyVOMeta.ACT_RETURN_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = ApplyVOMeta.CONTENT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ApplyVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ApplyVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ApplyVOMeta.RETURN_NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ApplyVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ApplyVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { ApplyVOMeta.PAGE_INDEX, ApplyVOMeta.PAGE_SIZE, ApplyVOMeta.SEARCH_FIELD, ApplyVOMeta.FUZZY_FIELD, ApplyVOMeta.SEARCH_VALUE, ApplyVOMeta.DIRTY_FIELDS, ApplyVOMeta.SORT_FIELD, ApplyVOMeta.SORT_TYPE, ApplyVOMeta.IDS })
    @SentinelResource(value = ApplyServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ApplyServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(ApplyVO applyVO) {
        Result result = applyService.save(applyVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ApplyVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = ApplyServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ApplyServiceProxy.GET_BY_ID)
    public Result<Apply> getById(String id) {
        Result<Apply> result = new Result<>();
        Apply apply = applyService.getById(id);
        // join ???????????????
        applyService.dao().fill(apply).with("useOrganization").with("receiver").with(ApplyMeta.VEHICLE_INFO_LIST).execute();
        result.success(true).data(apply);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ApplyVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = ApplyServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ApplyServiceProxy.GET_BY_IDS)
    public Result<List<Apply>> getByIds(List<String> ids) {
        Result<List<Apply>> result = new Result<>();
        List<Apply> list = applyService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ApplyVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "562620443333234688"),
		@ApiImplicitParam(name = ApplyVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ApplyVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ApplyVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = ApplyVOMeta.ORG_ID, value = "??????", required = false, dataTypeClass = String.class, example = "500994919175819264"),
		@ApiImplicitParam(name = ApplyVOMeta.RECEIVER_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "558321538131034112"),
		@ApiImplicitParam(name = ApplyVOMeta.DRIVER, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ApplyVOMeta.CONTACT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ApplyVOMeta.COLLECTION_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-04-06 12:00:00"),
		@ApiImplicitParam(name = ApplyVOMeta.PLAN_RETURN_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = ApplyVOMeta.ACT_RETURN_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = ApplyVOMeta.CONTENT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ApplyVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ApplyVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ApplyVOMeta.RETURN_NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ApplyVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ApplyVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { ApplyVOMeta.PAGE_INDEX, ApplyVOMeta.PAGE_SIZE })
    @SentinelResource(value = ApplyServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ApplyServiceProxy.QUERY_LIST)
    public Result<List<Apply>> queryList(ApplyVO sample) {
        Result<List<Apply>> result = new Result<>();
        List<Apply> list = applyService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ApplyVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "562620443333234688"),
		@ApiImplicitParam(name = ApplyVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ApplyVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ApplyVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = ApplyVOMeta.ORG_ID, value = "??????", required = false, dataTypeClass = String.class, example = "500994919175819264"),
		@ApiImplicitParam(name = ApplyVOMeta.RECEIVER_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "558321538131034112"),
		@ApiImplicitParam(name = ApplyVOMeta.DRIVER, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ApplyVOMeta.CONTACT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ApplyVOMeta.COLLECTION_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-04-06 12:00:00"),
		@ApiImplicitParam(name = ApplyVOMeta.PLAN_RETURN_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = ApplyVOMeta.ACT_RETURN_DATE, value = "??????????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = ApplyVOMeta.CONTENT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ApplyVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ApplyVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ApplyVOMeta.RETURN_NOTES, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ApplyVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ApplyVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = ApplyServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ApplyServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<Apply>> queryPagedList(ApplyVO sample) {
        Result<PagedList<Apply>> result = new Result<>();
        PagedList<Apply> list = applyService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        applyService.dao().fill(list).with("useOrganization").with("receiver").with(ApplyMeta.VEHICLE_INFO_LIST).execute();
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ApplyVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "562620443333234688")
	})
    @ApiOperationSupport(order = 9)
    @SentinelResource(value = ApplyServiceProxy.CONFIRM, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ApplyServiceProxy.CONFIRM)
    public Result confirm(String id) {
        Result result = applyService.confirm(id);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ApplyVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "562620443333234688")
	})
    @ApiOperationSupport(order = 10)
    @SentinelResource(value = ApplyServiceProxy.CANCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ApplyServiceProxy.CANCEL)
    public Result cancel(String id) {
        Result result = applyService.cancel(id);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ApplyVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "562620443333234688")
	})
    @ApiOperationSupport(order = 10)
    @SentinelResource(value = ApplyServiceProxy.ACTION_RETURN, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ApplyServiceProxy.ACTION_RETURN)
    public Result actionReturn(String id, String notes) {
        Result result = applyService.actionReturn(id, notes);
        return result;
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = ApplyServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(ApplyServiceProxy.EXPORT_EXCEL)
    public void exportExcel(ApplyVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = applyService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = ApplyServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(ApplyServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = applyService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = ApplyServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(ApplyServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = applyService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
