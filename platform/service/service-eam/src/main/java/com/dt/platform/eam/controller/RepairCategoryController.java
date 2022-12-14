package com.dt.platform.eam.controller;

import java.util.List;
import com.dt.platform.domain.eam.CategoryFinance;
import com.github.foxnic.commons.busi.id.IDGenerator;
import com.github.foxnic.commons.lang.StringUtil;
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
import com.dt.platform.proxy.eam.RepairCategoryServiceProxy;
import com.dt.platform.domain.eam.meta.RepairCategoryVOMeta;
import com.dt.platform.domain.eam.RepairCategory;
import com.dt.platform.domain.eam.RepairCategoryVO;
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
import com.dt.platform.domain.eam.meta.RepairCategoryMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IRepairCategoryService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-05-30 14:35:56
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("EamRepairCategoryController")
public class RepairCategoryController extends SuperController {

    @Autowired
    private IRepairCategoryService repairCategoryService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairCategoryVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairCategoryVOMeta.REPAIR_NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairCategoryVOMeta.REPAIR_CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairCategoryVOMeta.PARENT_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairCategoryVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = RepairCategoryVOMeta.HIERARCHY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairCategoryVOMeta.HIERARCHY_NAME, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairCategoryVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = RepairCategoryServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairCategoryServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(RepairCategoryVO repairCategoryVO) {
        repairCategoryVO.setSort(9999);
        repairCategoryVO.setRepairCode(IDGenerator.getSnowflakeIdString());
        repairCategoryVO.setRepairName("?????????");
        if (StringUtil.isBlank(repairCategoryVO.getParentId())) {
            repairCategoryVO.setParentId("0");
        }
        Result result = repairCategoryService.insert(repairCategoryVO);
        if (result.success()) {
            RepairCategory currentCategory = new RepairCategory();
            currentCategory.setId(repairCategoryVO.getId());
            if ("0".equals(repairCategoryVO.getParentId())) {
                currentCategory.setHierarchy(repairCategoryVO.getId());
                currentCategory.setHierarchyName(repairCategoryVO.getRepairName());
            } else {
                RepairCategory parentCategory = repairCategoryService.getById(repairCategoryVO.getParentId());
                currentCategory.setHierarchy(parentCategory.getHierarchy() + "/" + repairCategoryVO.getId());
                currentCategory.setHierarchyName(parentCategory.getHierarchyName() + "/" + repairCategoryVO.getRepairName());
            }
            repairCategoryService.update(currentCategory, SaveMode.NOT_NULL_FIELDS, false);
            repairCategoryVO.setHierarchy(currentCategory.getHierarchy());
            repairCategoryVO.setHierarchyName(currentCategory.getHierarchyName());
            result.data(repairCategoryVO);
        }
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairCategoryVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = RepairCategoryServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairCategoryServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result<RepairCategory> result = new Result<>();
        List<RepairCategory> list = repairCategoryService.queryList(RepairCategory.create().setParentId(id));
        if (list.size() > 0) {
            result.success(false).message("????????????????????????");
        } else {
            return repairCategoryService.deleteByIdLogical(id);
        }
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairCategoryVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = RepairCategoryServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairCategoryServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = repairCategoryService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairCategoryVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairCategoryVOMeta.REPAIR_NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairCategoryVOMeta.REPAIR_CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairCategoryVOMeta.PARENT_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairCategoryVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = RepairCategoryVOMeta.HIERARCHY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairCategoryVOMeta.HIERARCHY_NAME, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairCategoryVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { RepairCategoryVOMeta.PAGE_INDEX, RepairCategoryVOMeta.PAGE_SIZE, RepairCategoryVOMeta.SEARCH_FIELD, RepairCategoryVOMeta.FUZZY_FIELD, RepairCategoryVOMeta.SEARCH_VALUE, RepairCategoryVOMeta.DIRTY_FIELDS, RepairCategoryVOMeta.SORT_FIELD, RepairCategoryVOMeta.SORT_TYPE, RepairCategoryVOMeta.IDS })
    @SentinelResource(value = RepairCategoryServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairCategoryServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(RepairCategoryVO repairCategoryVO) {
        Result result = repairCategoryService.update(repairCategoryVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        if (result.isSuccess()) {
            // ??????????????????
            return repairCategoryService.updateHierarchy(repairCategoryVO.getId());
        }
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairCategoryVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairCategoryVOMeta.REPAIR_NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairCategoryVOMeta.REPAIR_CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairCategoryVOMeta.PARENT_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairCategoryVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = RepairCategoryVOMeta.HIERARCHY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairCategoryVOMeta.HIERARCHY_NAME, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairCategoryVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { RepairCategoryVOMeta.PAGE_INDEX, RepairCategoryVOMeta.PAGE_SIZE, RepairCategoryVOMeta.SEARCH_FIELD, RepairCategoryVOMeta.FUZZY_FIELD, RepairCategoryVOMeta.SEARCH_VALUE, RepairCategoryVOMeta.DIRTY_FIELDS, RepairCategoryVOMeta.SORT_FIELD, RepairCategoryVOMeta.SORT_TYPE, RepairCategoryVOMeta.IDS })
    @SentinelResource(value = RepairCategoryServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairCategoryServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(RepairCategoryVO repairCategoryVO) {
        Result result = repairCategoryService.update(repairCategoryVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        if (result.isSuccess()) {
            // ??????????????????
            return repairCategoryService.updateHierarchy(repairCategoryVO.getId());
        }
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairCategoryVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = RepairCategoryServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairCategoryServiceProxy.GET_BY_ID)
    public Result<RepairCategory> getById(String id) {
        Result<RepairCategory> result = new Result<>();
        RepairCategory repairCategory = repairCategoryService.getById(id);
        result.success(true).data(repairCategory);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairCategoryVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = RepairCategoryServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairCategoryServiceProxy.GET_BY_IDS)
    public Result<List<RepairCategory>> getByIds(List<String> ids) {
        Result<List<RepairCategory>> result = new Result<>();
        List<RepairCategory> list = repairCategoryService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairCategoryVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairCategoryVOMeta.REPAIR_NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairCategoryVOMeta.REPAIR_CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairCategoryVOMeta.PARENT_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairCategoryVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = RepairCategoryVOMeta.HIERARCHY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairCategoryVOMeta.HIERARCHY_NAME, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairCategoryVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { RepairCategoryVOMeta.PAGE_INDEX, RepairCategoryVOMeta.PAGE_SIZE })
    @SentinelResource(value = RepairCategoryServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairCategoryServiceProxy.QUERY_LIST)
    public Result<List<RepairCategory>> queryList(RepairCategoryVO sample) {
        Result<List<RepairCategory>> result = new Result<>();
        List<RepairCategory> list = repairCategoryService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairCategoryVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairCategoryVOMeta.REPAIR_NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairCategoryVOMeta.REPAIR_CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairCategoryVOMeta.PARENT_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairCategoryVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = RepairCategoryVOMeta.HIERARCHY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairCategoryVOMeta.HIERARCHY_NAME, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = RepairCategoryVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = RepairCategoryServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairCategoryServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<RepairCategory>> queryPagedList(RepairCategoryVO sample) {
        Result<PagedList<RepairCategory>> result = new Result<>();
        PagedList<RepairCategory> list = repairCategoryService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = RepairCategoryServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(RepairCategoryServiceProxy.EXPORT_EXCEL)
    public void exportExcel(RepairCategoryVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = repairCategoryService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = RepairCategoryServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(RepairCategoryServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = repairCategoryService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = RepairCategoryServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(RepairCategoryServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = repairCategoryService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
