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
import com.dt.platform.proxy.eam.RepairCategoryTplServiceProxy;
import com.dt.platform.domain.eam.meta.RepairCategoryTplVOMeta;
import com.dt.platform.domain.eam.RepairCategoryTpl;
import com.dt.platform.domain.eam.RepairCategoryTplVO;
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
import com.dt.platform.domain.eam.meta.RepairCategoryTplMeta;
import com.dt.platform.domain.eam.RepairCategory;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IRepairCategoryTplService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-06-02 05:35:09
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("EamRepairCategoryTplController")
public class RepairCategoryTplController extends SuperController {

    @Autowired
    private IRepairCategoryTplService repairCategoryTplService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "583710499342909440"),
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.CATEGORY_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "583677634362212353"),
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.REPAIR_SOLUTION, value = "????????????", required = false, dataTypeClass = String.class, example = "??????????????????"),
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.COST_TIME, value = "????????????", required = false, dataTypeClass = Integer.class, example = "12"),
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.ATTACH_ID, value = "??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "12")
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = RepairCategoryTplServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairCategoryTplServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(RepairCategoryTplVO repairCategoryTplVO) {
        Result result = repairCategoryTplService.insert(repairCategoryTplVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "583710499342909440")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = RepairCategoryTplServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairCategoryTplServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = repairCategoryTplService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = RepairCategoryTplServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairCategoryTplServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = repairCategoryTplService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "583710499342909440"),
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.CATEGORY_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "583677634362212353"),
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.REPAIR_SOLUTION, value = "????????????", required = false, dataTypeClass = String.class, example = "??????????????????"),
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.COST_TIME, value = "????????????", required = false, dataTypeClass = Integer.class, example = "12"),
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.ATTACH_ID, value = "??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "12")
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { RepairCategoryTplVOMeta.PAGE_INDEX, RepairCategoryTplVOMeta.PAGE_SIZE, RepairCategoryTplVOMeta.SEARCH_FIELD, RepairCategoryTplVOMeta.FUZZY_FIELD, RepairCategoryTplVOMeta.SEARCH_VALUE, RepairCategoryTplVOMeta.DIRTY_FIELDS, RepairCategoryTplVOMeta.SORT_FIELD, RepairCategoryTplVOMeta.SORT_TYPE, RepairCategoryTplVOMeta.IDS })
    @SentinelResource(value = RepairCategoryTplServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairCategoryTplServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(RepairCategoryTplVO repairCategoryTplVO) {
        Result result = repairCategoryTplService.update(repairCategoryTplVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "583710499342909440"),
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.CATEGORY_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "583677634362212353"),
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.REPAIR_SOLUTION, value = "????????????", required = false, dataTypeClass = String.class, example = "??????????????????"),
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.COST_TIME, value = "????????????", required = false, dataTypeClass = Integer.class, example = "12"),
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.ATTACH_ID, value = "??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "12")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { RepairCategoryTplVOMeta.PAGE_INDEX, RepairCategoryTplVOMeta.PAGE_SIZE, RepairCategoryTplVOMeta.SEARCH_FIELD, RepairCategoryTplVOMeta.FUZZY_FIELD, RepairCategoryTplVOMeta.SEARCH_VALUE, RepairCategoryTplVOMeta.DIRTY_FIELDS, RepairCategoryTplVOMeta.SORT_FIELD, RepairCategoryTplVOMeta.SORT_TYPE, RepairCategoryTplVOMeta.IDS })
    @SentinelResource(value = RepairCategoryTplServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairCategoryTplServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(RepairCategoryTplVO repairCategoryTplVO) {
        Result result = repairCategoryTplService.save(repairCategoryTplVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = RepairCategoryTplServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairCategoryTplServiceProxy.GET_BY_ID)
    public Result<RepairCategoryTpl> getById(String id) {
        Result<RepairCategoryTpl> result = new Result<>();
        RepairCategoryTpl repairCategoryTpl = repairCategoryTplService.getById(id);
        // join ???????????????
        repairCategoryTplService.dao().fill(repairCategoryTpl).with(RepairCategoryTplMeta.CATEGORY).execute();
        result.success(true).data(repairCategoryTpl);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = RepairCategoryTplServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairCategoryTplServiceProxy.GET_BY_IDS)
    public Result<List<RepairCategoryTpl>> getByIds(List<String> ids) {
        Result<List<RepairCategoryTpl>> result = new Result<>();
        List<RepairCategoryTpl> list = repairCategoryTplService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "583710499342909440"),
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.CATEGORY_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "583677634362212353"),
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.REPAIR_SOLUTION, value = "????????????", required = false, dataTypeClass = String.class, example = "??????????????????"),
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.COST_TIME, value = "????????????", required = false, dataTypeClass = Integer.class, example = "12"),
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.ATTACH_ID, value = "??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "12")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { RepairCategoryTplVOMeta.PAGE_INDEX, RepairCategoryTplVOMeta.PAGE_SIZE })
    @SentinelResource(value = RepairCategoryTplServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairCategoryTplServiceProxy.QUERY_LIST)
    public Result<List<RepairCategoryTpl>> queryList(RepairCategoryTplVO sample) {
        Result<List<RepairCategoryTpl>> result = new Result<>();
        List<RepairCategoryTpl> list = repairCategoryTplService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "583710499342909440"),
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.CATEGORY_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "583677634362212353"),
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.REPAIR_SOLUTION, value = "????????????", required = false, dataTypeClass = String.class, example = "??????????????????"),
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.COST_TIME, value = "????????????", required = false, dataTypeClass = Integer.class, example = "12"),
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.ATTACH_ID, value = "??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = RepairCategoryTplVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "12")
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = RepairCategoryTplServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairCategoryTplServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<RepairCategoryTpl>> queryPagedList(RepairCategoryTplVO sample) {
        Result<PagedList<RepairCategoryTpl>> result = new Result<>();
        PagedList<RepairCategoryTpl> list = repairCategoryTplService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        repairCategoryTplService.dao().fill(list).with(RepairCategoryTplMeta.CATEGORY).execute();
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = RepairCategoryTplServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(RepairCategoryTplServiceProxy.EXPORT_EXCEL)
    public void exportExcel(RepairCategoryTplVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = repairCategoryTplService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = RepairCategoryTplServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(RepairCategoryTplServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = repairCategoryTplService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = RepairCategoryTplServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(RepairCategoryTplServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = repairCategoryTplService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
