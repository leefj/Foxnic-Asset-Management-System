package com.dt.platform.eam.controller;

import java.math.BigDecimal;
import java.util.List;
import com.dt.platform.domain.eam.Category;
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
import com.dt.platform.proxy.eam.CategoryFinanceServiceProxy;
import com.dt.platform.domain.eam.meta.CategoryFinanceVOMeta;
import com.dt.platform.domain.eam.CategoryFinance;
import com.dt.platform.domain.eam.CategoryFinanceVO;
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
import com.dt.platform.domain.eam.meta.CategoryFinanceMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.ICategoryFinanceService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2021-09-10 13:01:35
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("EamCategoryFinanceController")
public class CategoryFinanceController extends SuperController {

    @Autowired
    private ICategoryFinanceService categoryFinanceService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = CategoryFinanceVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.CATEGORY_NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.CATEGORY_FULLNAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.CATEGORY_CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.PARENT_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.HIERARCHY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.HIERARCHY_NAME, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.TENANT_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.SERVICE_LIFE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "36.00")
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = CategoryFinanceServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CategoryFinanceServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(CategoryFinanceVO categoryFinanceVO) {
        categoryFinanceVO.setSort(9999);
        if (StringUtil.isBlank(categoryFinanceVO.getParentId())) {
            categoryFinanceVO.setParentId("0");
        }
        Result result = categoryFinanceService.insert(categoryFinanceVO);
        if (result.success()) {
            CategoryFinance currentCategory = new CategoryFinance();
            currentCategory.setId(categoryFinanceVO.getId());
            if ("0".equals(categoryFinanceVO.getParentId())) {
                currentCategory.setHierarchy(categoryFinanceVO.getId());
                currentCategory.setHierarchyName(categoryFinanceVO.getCategoryName());
            } else {
                CategoryFinance parentCategory = categoryFinanceService.getById(categoryFinanceVO.getParentId());
                currentCategory.setHierarchy(parentCategory.getHierarchy() + "/" + categoryFinanceVO.getId());
                currentCategory.setHierarchyName(parentCategory.getHierarchyName() + "/" + categoryFinanceVO.getCategoryName());
            }
            categoryFinanceService.update(currentCategory, SaveMode.NOT_NULL_FIELDS);
            categoryFinanceVO.setHierarchy(currentCategory.getHierarchy());
            categoryFinanceVO.setHierarchyName(currentCategory.getHierarchyName());
            result.data(categoryFinanceVO);
        }
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = CategoryFinanceVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = CategoryFinanceServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CategoryFinanceServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result<CategoryFinance> result = new Result<>();
        List<CategoryFinance> list = categoryFinanceService.queryList(CategoryFinance.create().setParentId(id));
        if (list.size() > 0) {
            result.success(false).message("????????????????????????");
        } else {
            return categoryFinanceService.deleteByIdLogical(id);
        }
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = CategoryFinanceVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = CategoryFinanceServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CategoryFinanceServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = categoryFinanceService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = CategoryFinanceVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.CATEGORY_NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.CATEGORY_FULLNAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.CATEGORY_CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.PARENT_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.HIERARCHY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.HIERARCHY_NAME, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.TENANT_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.SERVICE_LIFE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "36.00")
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { CategoryFinanceVOMeta.PAGE_INDEX, CategoryFinanceVOMeta.PAGE_SIZE, CategoryFinanceVOMeta.SEARCH_FIELD, CategoryFinanceVOMeta.FUZZY_FIELD, CategoryFinanceVOMeta.SEARCH_VALUE, CategoryFinanceVOMeta.SORT_FIELD, CategoryFinanceVOMeta.SORT_TYPE, CategoryFinanceVOMeta.IDS })
    @SentinelResource(value = CategoryFinanceServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CategoryFinanceServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(CategoryFinanceVO categoryFinanceVO) {
        Result result = categoryFinanceService.update(categoryFinanceVO, SaveMode.NOT_NULL_FIELDS);
        if (result.isSuccess()) {
            // ??????????????????
            return categoryFinanceService.updateHierarchy(categoryFinanceVO.getId());
        }
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = CategoryFinanceVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.CATEGORY_NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.CATEGORY_FULLNAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.CATEGORY_CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.PARENT_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.HIERARCHY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.HIERARCHY_NAME, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.TENANT_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.SERVICE_LIFE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "36.00")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { CategoryFinanceVOMeta.PAGE_INDEX, CategoryFinanceVOMeta.PAGE_SIZE, CategoryFinanceVOMeta.SEARCH_FIELD, CategoryFinanceVOMeta.FUZZY_FIELD, CategoryFinanceVOMeta.SEARCH_VALUE, CategoryFinanceVOMeta.SORT_FIELD, CategoryFinanceVOMeta.SORT_TYPE, CategoryFinanceVOMeta.IDS })
    @SentinelResource(value = CategoryFinanceServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CategoryFinanceServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(CategoryFinanceVO categoryFinanceVO) {
        Result result = categoryFinanceService.update(categoryFinanceVO, SaveMode.NOT_NULL_FIELDS);
        if (result.isSuccess()) {
            // ??????????????????
            return categoryFinanceService.updateHierarchy(categoryFinanceVO.getId());
        }
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = CategoryFinanceVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = CategoryFinanceServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CategoryFinanceServiceProxy.GET_BY_ID)
    public Result<CategoryFinance> getById(String id) {
        Result<CategoryFinance> result = new Result<>();
        CategoryFinance categoryFinance = categoryFinanceService.getById(id);
        result.success(true).data(categoryFinance);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = CategoryFinanceVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = CategoryFinanceServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CategoryFinanceServiceProxy.GET_BY_IDS)
    public Result<List<CategoryFinance>> getByIds(List<String> ids) {
        Result<List<CategoryFinance>> result = new Result<>();
        List<CategoryFinance> list = categoryFinanceService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = CategoryFinanceVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.CATEGORY_NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.CATEGORY_FULLNAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.CATEGORY_CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.PARENT_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.HIERARCHY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.HIERARCHY_NAME, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.TENANT_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.SERVICE_LIFE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "36.00")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { CategoryFinanceVOMeta.PAGE_INDEX, CategoryFinanceVOMeta.PAGE_SIZE })
    @SentinelResource(value = CategoryFinanceServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CategoryFinanceServiceProxy.QUERY_LIST)
    public Result<List<CategoryFinance>> queryList(CategoryFinanceVO sample) {
        Result<List<CategoryFinance>> result = new Result<>();
        List<CategoryFinance> list = categoryFinanceService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = CategoryFinanceVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.CATEGORY_NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.CATEGORY_FULLNAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.CATEGORY_CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.PARENT_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.HIERARCHY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.HIERARCHY_NAME, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.TENANT_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CategoryFinanceVOMeta.SERVICE_LIFE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "36.00")
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = CategoryFinanceServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CategoryFinanceServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<CategoryFinance>> queryPagedList(CategoryFinanceVO sample) {
        Result<PagedList<CategoryFinance>> result = new Result<>();
        PagedList<CategoryFinance> list = categoryFinanceService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = CategoryFinanceServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(CategoryFinanceServiceProxy.EXPORT_EXCEL)
    public void exportExcel(CategoryFinanceVO sample, HttpServletResponse response) throws Exception {
        // ?????? Excel ??????
        ExcelWriter ew = categoryFinanceService.exportExcel(sample);
        // ??????
        DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = CategoryFinanceServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(CategoryFinanceServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        // ?????? Excel ??????
        ExcelWriter ew = categoryFinanceService.exportExcelTemplate();
        // ??????
        DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
    }

    @SentinelResource(value = CategoryFinanceServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(CategoryFinanceServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = categoryFinanceService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
