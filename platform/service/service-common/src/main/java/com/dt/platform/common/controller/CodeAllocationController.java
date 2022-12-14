package com.dt.platform.common.controller;

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
import com.dt.platform.proxy.common.CodeAllocationServiceProxy;
import com.dt.platform.domain.common.meta.CodeAllocationVOMeta;
import com.dt.platform.domain.common.CodeAllocation;
import com.dt.platform.domain.common.CodeAllocationVO;
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
import com.dt.platform.domain.common.meta.CodeAllocationMeta;
import com.dt.platform.domain.common.CodeRule;
import com.dt.platform.domain.common.CodeRegister;
import com.dt.platform.domain.common.meta.CodeRuleMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.common.service.ICodeAllocationService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2021-10-26 15:26:32
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("SysCodeAllocationController")
public class CodeAllocationController extends SuperController {

    @Autowired
    private ICodeAllocationService codeAllocationService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CodeAllocationVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = CodeAllocationVOMeta.CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "eam_asset_allocate"),
		@ApiImplicitParam(name = CodeAllocationVOMeta.RULE_ID, value = "??????ID", required = false, dataTypeClass = String.class, example = "5"),
		@ApiImplicitParam(name = CodeAllocationVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "EAM??????????????????????????????")
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = CodeAllocationServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CodeAllocationServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(CodeAllocationVO codeAllocationVO) {
        Result result = codeAllocationService.insert(codeAllocationVO);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CodeAllocationVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = CodeAllocationServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CodeAllocationServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = codeAllocationService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CodeAllocationVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = CodeAllocationServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CodeAllocationServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = codeAllocationService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CodeAllocationVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = CodeAllocationVOMeta.CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "eam_asset_allocate"),
		@ApiImplicitParam(name = CodeAllocationVOMeta.RULE_ID, value = "??????ID", required = false, dataTypeClass = String.class, example = "5"),
		@ApiImplicitParam(name = CodeAllocationVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "EAM??????????????????????????????")
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { CodeAllocationVOMeta.PAGE_INDEX, CodeAllocationVOMeta.PAGE_SIZE, CodeAllocationVOMeta.SEARCH_FIELD, CodeAllocationVOMeta.FUZZY_FIELD, CodeAllocationVOMeta.SEARCH_VALUE, CodeAllocationVOMeta.SORT_FIELD, CodeAllocationVOMeta.SORT_TYPE, CodeAllocationVOMeta.IDS })
    @SentinelResource(value = CodeAllocationServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CodeAllocationServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(CodeAllocationVO codeAllocationVO) {
        Result result = codeAllocationService.update(codeAllocationVO, SaveMode.NOT_NULL_FIELDS);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CodeAllocationVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = CodeAllocationVOMeta.CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "eam_asset_allocate"),
		@ApiImplicitParam(name = CodeAllocationVOMeta.RULE_ID, value = "??????ID", required = false, dataTypeClass = String.class, example = "5"),
		@ApiImplicitParam(name = CodeAllocationVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "EAM??????????????????????????????")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { CodeAllocationVOMeta.PAGE_INDEX, CodeAllocationVOMeta.PAGE_SIZE, CodeAllocationVOMeta.SEARCH_FIELD, CodeAllocationVOMeta.FUZZY_FIELD, CodeAllocationVOMeta.SEARCH_VALUE, CodeAllocationVOMeta.SORT_FIELD, CodeAllocationVOMeta.SORT_TYPE, CodeAllocationVOMeta.IDS })
    @SentinelResource(value = CodeAllocationServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CodeAllocationServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(CodeAllocationVO codeAllocationVO) {
        Result result = codeAllocationService.save(codeAllocationVO, SaveMode.NOT_NULL_FIELDS);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CodeAllocationVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = CodeAllocationServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CodeAllocationServiceProxy.GET_BY_ID)
    public Result<CodeAllocation> getById(String id) {
        Result<CodeAllocation> result = new Result<>();
        CodeAllocation codeAllocation = codeAllocationService.getById(id);
        // join ???????????????
        codeAllocationService.dao().fill(codeAllocation).with(CodeAllocationMeta.RULE).with(CodeAllocationMeta.BUSINESS_CODE).with(CodeAllocationMeta.RULE).execute();
        result.success(true).data(codeAllocation);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CodeAllocationVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = CodeAllocationServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CodeAllocationServiceProxy.GET_BY_IDS)
    public Result<List<CodeAllocation>> getByIds(List<String> ids) {
        Result<List<CodeAllocation>> result = new Result<>();
        List<CodeAllocation> list = codeAllocationService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CodeAllocationVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = CodeAllocationVOMeta.CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "eam_asset_allocate"),
		@ApiImplicitParam(name = CodeAllocationVOMeta.RULE_ID, value = "??????ID", required = false, dataTypeClass = String.class, example = "5"),
		@ApiImplicitParam(name = CodeAllocationVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "EAM??????????????????????????????")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { CodeAllocationVOMeta.PAGE_INDEX, CodeAllocationVOMeta.PAGE_SIZE })
    @SentinelResource(value = CodeAllocationServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CodeAllocationServiceProxy.QUERY_LIST)
    public Result<List<CodeAllocation>> queryList(CodeAllocationVO sample) {
        Result<List<CodeAllocation>> result = new Result<>();
        List<CodeAllocation> list = codeAllocationService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CodeAllocationVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = CodeAllocationVOMeta.CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "eam_asset_allocate"),
		@ApiImplicitParam(name = CodeAllocationVOMeta.RULE_ID, value = "??????ID", required = false, dataTypeClass = String.class, example = "5"),
		@ApiImplicitParam(name = CodeAllocationVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "EAM??????????????????????????????")
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = CodeAllocationServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CodeAllocationServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<CodeAllocation>> queryPagedList(CodeAllocationVO sample) {
        Result<PagedList<CodeAllocation>> result = new Result<>();
        PagedList<CodeAllocation> list = codeAllocationService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        codeAllocationService.dao().fill(list).with(CodeAllocationMeta.RULE).with(CodeAllocationMeta.BUSINESS_CODE).with(CodeAllocationMeta.RULE).execute();
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = CodeAllocationServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(CodeAllocationServiceProxy.EXPORT_EXCEL)
    public void exportExcel(CodeAllocationVO sample, HttpServletResponse response) throws Exception {
        // ?????? Excel ??????
        ExcelWriter ew = codeAllocationService.exportExcel(sample);
        // ??????
        DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = CodeAllocationServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(CodeAllocationServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        // ?????? Excel ??????
        ExcelWriter ew = codeAllocationService.exportExcelTemplate();
        // ??????
        DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
    }

    @SentinelResource(value = CodeAllocationServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(CodeAllocationServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = codeAllocationService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
