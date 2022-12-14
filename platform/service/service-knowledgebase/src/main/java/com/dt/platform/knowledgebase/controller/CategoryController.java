package com.dt.platform.knowledgebase.controller;

import java.util.List;
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
import com.dt.platform.proxy.knowledgebase.CategoryServiceProxy;
import com.dt.platform.domain.knowledgebase.meta.CategoryVOMeta;
import com.dt.platform.domain.knowledgebase.Category;
import com.dt.platform.domain.knowledgebase.CategoryVO;
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
import com.dt.platform.domain.knowledgebase.meta.CategoryMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.knowledgebase.service.ICategoryService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2021-08-26 09:23:37
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("KnCategoryController")
public class CategoryController extends SuperController {

    @Autowired
    private ICategoryService categoryService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CategoryVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "479027782127452160"),
		@ApiImplicitParam(name = CategoryVOMeta.CATEGORY_NAME, value = "??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = CategoryVOMeta.CATEGORY_FULLNAME, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = CategoryVOMeta.CATEGORY_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = CategoryVOMeta.PARENT_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = CategoryVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class, example = "12"),
		@ApiImplicitParam(name = CategoryVOMeta.HIERARCHY, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = CategoryVOMeta.HIERARCHY_NAME, value = "??????????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = CategoryVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "12")
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = CategoryServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CategoryServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(CategoryVO categoryVO) {
        categoryVO.setSort(9999);
        if (StringUtil.isBlank(categoryVO.getParentId())) {
            categoryVO.setParentId("0");
        }
        Result result = categoryService.insert(categoryVO);
        if (result.success()) {
            Category currentCategory = new Category();
            currentCategory.setId(categoryVO.getId());
            if ("0".equals(categoryVO.getParentId())) {
                currentCategory.setHierarchy(categoryVO.getId());
                currentCategory.setHierarchyName(categoryVO.getCategoryName());
            } else {
                Category parentCategory = categoryService.getById(categoryVO.getParentId());
                currentCategory.setHierarchy(parentCategory.getHierarchy() + "/" + categoryVO.getId());
                currentCategory.setHierarchyName(parentCategory.getHierarchyName() + "/" + categoryVO.getCategoryName());
            }
            categoryService.update(currentCategory, SaveMode.NOT_NULL_FIELDS);
            categoryVO.setHierarchy(currentCategory.getHierarchy());
            categoryVO.setHierarchyName(currentCategory.getHierarchyName());
            result.data(categoryVO);
        }
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CategoryVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "479027782127452160")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = CategoryServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CategoryServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result<Category> result = new Result<>();
        List<Category> list = categoryService.queryList(Category.create().setParentId(id));
        if (list.size() > 0) {
            result.success(false).message("????????????????????????");
        } else {
            return categoryService.deleteByIdLogical(id);
        }
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CategoryVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = CategoryServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CategoryServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = categoryService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CategoryVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "479027782127452160"),
		@ApiImplicitParam(name = CategoryVOMeta.CATEGORY_NAME, value = "??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = CategoryVOMeta.CATEGORY_FULLNAME, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = CategoryVOMeta.CATEGORY_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = CategoryVOMeta.PARENT_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = CategoryVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class, example = "12"),
		@ApiImplicitParam(name = CategoryVOMeta.HIERARCHY, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = CategoryVOMeta.HIERARCHY_NAME, value = "??????????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = CategoryVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "12")
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { CategoryVOMeta.PAGE_INDEX, CategoryVOMeta.PAGE_SIZE, CategoryVOMeta.SEARCH_FIELD, CategoryVOMeta.FUZZY_FIELD, CategoryVOMeta.SEARCH_VALUE, CategoryVOMeta.SORT_FIELD, CategoryVOMeta.SORT_TYPE, CategoryVOMeta.IDS })
    @SentinelResource(value = CategoryServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CategoryServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(CategoryVO categoryVO) {
        Result result = categoryService.update(categoryVO, SaveMode.NOT_NULL_FIELDS);
        if (result.isSuccess()) {
            // ??????????????????
            return categoryService.updateHierarchy(categoryVO.getId());
        }
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CategoryVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "479027782127452160"),
		@ApiImplicitParam(name = CategoryVOMeta.CATEGORY_NAME, value = "??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = CategoryVOMeta.CATEGORY_FULLNAME, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = CategoryVOMeta.CATEGORY_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = CategoryVOMeta.PARENT_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = CategoryVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class, example = "12"),
		@ApiImplicitParam(name = CategoryVOMeta.HIERARCHY, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = CategoryVOMeta.HIERARCHY_NAME, value = "??????????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = CategoryVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "12")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { CategoryVOMeta.PAGE_INDEX, CategoryVOMeta.PAGE_SIZE, CategoryVOMeta.SEARCH_FIELD, CategoryVOMeta.FUZZY_FIELD, CategoryVOMeta.SEARCH_VALUE, CategoryVOMeta.SORT_FIELD, CategoryVOMeta.SORT_TYPE, CategoryVOMeta.IDS })
    @SentinelResource(value = CategoryServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CategoryServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(CategoryVO categoryVO) {
        Result result = categoryService.save(categoryVO, SaveMode.NOT_NULL_FIELDS);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CategoryVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = CategoryServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CategoryServiceProxy.GET_BY_ID)
    public Result<Category> getById(String id) {
        Result<Category> result = new Result<>();
        Category category = categoryService.getById(id);
        result.success(true).data(category);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CategoryVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = CategoryServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CategoryServiceProxy.GET_BY_IDS)
    public Result<List<Category>> getByIds(List<String> ids) {
        Result<List<Category>> result = new Result<>();
        List<Category> list = categoryService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CategoryVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "479027782127452160"),
		@ApiImplicitParam(name = CategoryVOMeta.CATEGORY_NAME, value = "??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = CategoryVOMeta.CATEGORY_FULLNAME, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = CategoryVOMeta.CATEGORY_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = CategoryVOMeta.PARENT_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = CategoryVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class, example = "12"),
		@ApiImplicitParam(name = CategoryVOMeta.HIERARCHY, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = CategoryVOMeta.HIERARCHY_NAME, value = "??????????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = CategoryVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "12")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { CategoryVOMeta.PAGE_INDEX, CategoryVOMeta.PAGE_SIZE })
    @SentinelResource(value = CategoryServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CategoryServiceProxy.QUERY_LIST)
    public Result<List<Category>> queryList(CategoryVO sample) {
        Result<List<Category>> result = new Result<>();
        List<Category> list = categoryService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CategoryVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "479027782127452160"),
		@ApiImplicitParam(name = CategoryVOMeta.CATEGORY_NAME, value = "??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = CategoryVOMeta.CATEGORY_FULLNAME, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = CategoryVOMeta.CATEGORY_CODE, value = "??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = CategoryVOMeta.PARENT_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = CategoryVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class, example = "12"),
		@ApiImplicitParam(name = CategoryVOMeta.HIERARCHY, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = CategoryVOMeta.HIERARCHY_NAME, value = "??????????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = CategoryVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "12")
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = CategoryServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CategoryServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<Category>> queryPagedList(CategoryVO sample) {
        Result<PagedList<Category>> result = new Result<>();
        PagedList<Category> list = categoryService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = CategoryServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(CategoryServiceProxy.EXPORT_EXCEL)
    public void exportExcel(CategoryVO sample, HttpServletResponse response) throws Exception {
        // ?????? Excel ??????
        ExcelWriter ew = categoryService.exportExcel(sample);
        // ??????
        DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = CategoryServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(CategoryServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        // ?????? Excel ??????
        ExcelWriter ew = categoryService.exportExcelTemplate();
        // ??????
        DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
    }

    @SentinelResource(value = CategoryServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(CategoryServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = categoryService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
