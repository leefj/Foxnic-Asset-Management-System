package com.dt.platform.eam.controller;

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
import com.dt.platform.proxy.eam.InventoryPlanServiceProxy;
import com.dt.platform.domain.eam.meta.InventoryPlanVOMeta;
import com.dt.platform.domain.eam.InventoryPlan;
import com.dt.platform.domain.eam.InventoryPlanVO;
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
import com.dt.platform.domain.eam.meta.InventoryPlanMeta;
import com.dt.platform.domain.eam.Inventory;
import org.github.foxnic.web.domain.system.DictItem;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IInventoryPlanService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-01-03 10:30:37
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("EamInventoryPlanController")
public class InventoryPlanController extends SuperController {

    @Autowired
    private IInventoryPlanService inventoryPlanService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InventoryPlanVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "529341603614035968"),
		@ApiImplicitParam(name = InventoryPlanVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = InventoryPlanVOMeta.OWNER_CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InventoryPlanVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = InventoryPlanVOMeta.PLAN_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "normal"),
		@ApiImplicitParam(name = InventoryPlanVOMeta.TPL_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InventoryPlanVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1212")
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = InventoryPlanServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InventoryPlanServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(InventoryPlanVO inventoryPlanVO) {
        Result result = inventoryPlanService.insert(inventoryPlanVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InventoryPlanVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "529341603614035968")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = InventoryPlanServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InventoryPlanServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = inventoryPlanService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InventoryPlanVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = InventoryPlanServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InventoryPlanServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = inventoryPlanService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InventoryPlanVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "529341603614035968"),
		@ApiImplicitParam(name = InventoryPlanVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = InventoryPlanVOMeta.OWNER_CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InventoryPlanVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = InventoryPlanVOMeta.PLAN_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "normal"),
		@ApiImplicitParam(name = InventoryPlanVOMeta.TPL_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InventoryPlanVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1212")
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { InventoryPlanVOMeta.PAGE_INDEX, InventoryPlanVOMeta.PAGE_SIZE, InventoryPlanVOMeta.SEARCH_FIELD, InventoryPlanVOMeta.FUZZY_FIELD, InventoryPlanVOMeta.SEARCH_VALUE, InventoryPlanVOMeta.DIRTY_FIELDS, InventoryPlanVOMeta.SORT_FIELD, InventoryPlanVOMeta.SORT_TYPE, InventoryPlanVOMeta.IDS })
    @SentinelResource(value = InventoryPlanServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InventoryPlanServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(InventoryPlanVO inventoryPlanVO) {
        Result result = inventoryPlanService.update(inventoryPlanVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InventoryPlanVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "529341603614035968"),
		@ApiImplicitParam(name = InventoryPlanVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = InventoryPlanVOMeta.OWNER_CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InventoryPlanVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = InventoryPlanVOMeta.PLAN_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "normal"),
		@ApiImplicitParam(name = InventoryPlanVOMeta.TPL_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InventoryPlanVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1212")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { InventoryPlanVOMeta.PAGE_INDEX, InventoryPlanVOMeta.PAGE_SIZE, InventoryPlanVOMeta.SEARCH_FIELD, InventoryPlanVOMeta.FUZZY_FIELD, InventoryPlanVOMeta.SEARCH_VALUE, InventoryPlanVOMeta.DIRTY_FIELDS, InventoryPlanVOMeta.SORT_FIELD, InventoryPlanVOMeta.SORT_TYPE, InventoryPlanVOMeta.IDS })
    @SentinelResource(value = InventoryPlanServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InventoryPlanServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(InventoryPlanVO inventoryPlanVO) {
        Result result = inventoryPlanService.save(inventoryPlanVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InventoryPlanVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = InventoryPlanServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InventoryPlanServiceProxy.GET_BY_ID)
    public Result<InventoryPlan> getById(String id) {
        Result<InventoryPlan> result = new Result<>();
        InventoryPlan inventoryPlan = inventoryPlanService.getById(id);
        // join ???????????????
        inventoryPlanService.dao().fill(inventoryPlan).with(InventoryPlanMeta.INVENTORY_PLAN_TYPE).with(InventoryPlanMeta.INVENTORY).execute();
        result.success(true).data(inventoryPlan);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InventoryPlanVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = InventoryPlanServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InventoryPlanServiceProxy.GET_BY_IDS)
    public Result<List<InventoryPlan>> getByIds(List<String> ids) {
        Result<List<InventoryPlan>> result = new Result<>();
        List<InventoryPlan> list = inventoryPlanService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InventoryPlanVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "529341603614035968"),
		@ApiImplicitParam(name = InventoryPlanVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = InventoryPlanVOMeta.OWNER_CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InventoryPlanVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = InventoryPlanVOMeta.PLAN_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "normal"),
		@ApiImplicitParam(name = InventoryPlanVOMeta.TPL_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InventoryPlanVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1212")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { InventoryPlanVOMeta.PAGE_INDEX, InventoryPlanVOMeta.PAGE_SIZE })
    @SentinelResource(value = InventoryPlanServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InventoryPlanServiceProxy.QUERY_LIST)
    public Result<List<InventoryPlan>> queryList(InventoryPlanVO sample) {
        Result<List<InventoryPlan>> result = new Result<>();
        List<InventoryPlan> list = inventoryPlanService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InventoryPlanVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "529341603614035968"),
		@ApiImplicitParam(name = InventoryPlanVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = InventoryPlanVOMeta.OWNER_CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InventoryPlanVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = InventoryPlanVOMeta.PLAN_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "normal"),
		@ApiImplicitParam(name = InventoryPlanVOMeta.TPL_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InventoryPlanVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1212")
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = InventoryPlanServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InventoryPlanServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<InventoryPlan>> queryPagedList(InventoryPlanVO sample) {
        Result<PagedList<InventoryPlan>> result = new Result<>();
        PagedList<InventoryPlan> list = inventoryPlanService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        inventoryPlanService.dao().fill(list).with(InventoryPlanMeta.INVENTORY_PLAN_TYPE).with(InventoryPlanMeta.INVENTORY).execute();
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InventoryPlanVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 9)
    @SentinelResource(value = InventoryPlanServiceProxy.APPLY_TPL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InventoryPlanServiceProxy.APPLY_TPL)
    public Result applyTpl(String id) {
        return inventoryPlanService.applyTpl(id);
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = InventoryPlanServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(InventoryPlanServiceProxy.EXPORT_EXCEL)
    public void exportExcel(InventoryPlanVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = inventoryPlanService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = InventoryPlanServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(InventoryPlanServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = inventoryPlanService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = InventoryPlanServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(InventoryPlanServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = inventoryPlanService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
