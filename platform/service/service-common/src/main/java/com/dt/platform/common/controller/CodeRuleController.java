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
import com.dt.platform.proxy.common.CodeRuleServiceProxy;
import com.dt.platform.domain.common.meta.CodeRuleVOMeta;
import com.dt.platform.domain.common.CodeRule;
import com.dt.platform.domain.common.CodeRuleVO;
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
import com.dt.platform.domain.common.meta.CodeRuleMeta;
import org.github.foxnic.web.domain.oauth.Menu;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.common.service.ICodeRuleService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2021-10-26 15:26:42
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("SysCodeRuleController")
public class CodeRuleController extends SuperController {

    @Autowired
    private ICodeRuleService codeRuleService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CodeRuleVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = CodeRuleVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "EAM????????????"),
		@ApiImplicitParam(name = CodeRuleVOMeta.MODULE_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "eam"),
		@ApiImplicitParam(name = CodeRuleVOMeta.RULE, value = "????????????", required = false, dataTypeClass = String.class, example = "${string_fix,FZQH-}${number_seq,5,asset}"),
		@ApiImplicitParam(name = CodeRuleVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = CodeRuleServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CodeRuleServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(CodeRuleVO codeRuleVO) {
        Result result = codeRuleService.insert(codeRuleVO);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CodeRuleVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = CodeRuleServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CodeRuleServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = codeRuleService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CodeRuleVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = CodeRuleServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CodeRuleServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = codeRuleService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CodeRuleVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = CodeRuleVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "EAM????????????"),
		@ApiImplicitParam(name = CodeRuleVOMeta.MODULE_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "eam"),
		@ApiImplicitParam(name = CodeRuleVOMeta.RULE, value = "????????????", required = false, dataTypeClass = String.class, example = "${string_fix,FZQH-}${number_seq,5,asset}"),
		@ApiImplicitParam(name = CodeRuleVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { CodeRuleVOMeta.PAGE_INDEX, CodeRuleVOMeta.PAGE_SIZE, CodeRuleVOMeta.SEARCH_FIELD, CodeRuleVOMeta.FUZZY_FIELD, CodeRuleVOMeta.SEARCH_VALUE, CodeRuleVOMeta.SORT_FIELD, CodeRuleVOMeta.SORT_TYPE, CodeRuleVOMeta.IDS })
    @SentinelResource(value = CodeRuleServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CodeRuleServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(CodeRuleVO codeRuleVO) {
        Result result = codeRuleService.update(codeRuleVO, SaveMode.NOT_NULL_FIELDS);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CodeRuleVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = CodeRuleVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "EAM????????????"),
		@ApiImplicitParam(name = CodeRuleVOMeta.MODULE_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "eam"),
		@ApiImplicitParam(name = CodeRuleVOMeta.RULE, value = "????????????", required = false, dataTypeClass = String.class, example = "${string_fix,FZQH-}${number_seq,5,asset}"),
		@ApiImplicitParam(name = CodeRuleVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { CodeRuleVOMeta.PAGE_INDEX, CodeRuleVOMeta.PAGE_SIZE, CodeRuleVOMeta.SEARCH_FIELD, CodeRuleVOMeta.FUZZY_FIELD, CodeRuleVOMeta.SEARCH_VALUE, CodeRuleVOMeta.SORT_FIELD, CodeRuleVOMeta.SORT_TYPE, CodeRuleVOMeta.IDS })
    @SentinelResource(value = CodeRuleServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CodeRuleServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(CodeRuleVO codeRuleVO) {
        Result result = codeRuleService.save(codeRuleVO, SaveMode.NOT_NULL_FIELDS);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CodeRuleVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = CodeRuleServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CodeRuleServiceProxy.GET_BY_ID)
    public Result<CodeRule> getById(String id) {
        Result<CodeRule> result = new Result<>();
        CodeRule codeRule = codeRuleService.getById(id);
        // join ???????????????
        codeRuleService.dao().fill(codeRule).with(CodeRuleMeta.MODULE).execute();
        result.success(true).data(codeRule);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CodeRuleVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = CodeRuleServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CodeRuleServiceProxy.GET_BY_IDS)
    public Result<List<CodeRule>> getByIds(List<String> ids) {
        Result<List<CodeRule>> result = new Result<>();
        List<CodeRule> list = codeRuleService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CodeRuleVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = CodeRuleVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "EAM????????????"),
		@ApiImplicitParam(name = CodeRuleVOMeta.MODULE_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "eam"),
		@ApiImplicitParam(name = CodeRuleVOMeta.RULE, value = "????????????", required = false, dataTypeClass = String.class, example = "${string_fix,FZQH-}${number_seq,5,asset}"),
		@ApiImplicitParam(name = CodeRuleVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { CodeRuleVOMeta.PAGE_INDEX, CodeRuleVOMeta.PAGE_SIZE })
    @SentinelResource(value = CodeRuleServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CodeRuleServiceProxy.QUERY_LIST)
    public Result<List<CodeRule>> queryList(CodeRuleVO sample) {
        Result<List<CodeRule>> result = new Result<>();
        List<CodeRule> list = codeRuleService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CodeRuleVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = CodeRuleVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "EAM????????????"),
		@ApiImplicitParam(name = CodeRuleVOMeta.MODULE_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "eam"),
		@ApiImplicitParam(name = CodeRuleVOMeta.RULE, value = "????????????", required = false, dataTypeClass = String.class, example = "${string_fix,FZQH-}${number_seq,5,asset}"),
		@ApiImplicitParam(name = CodeRuleVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = CodeRuleServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CodeRuleServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<CodeRule>> queryPagedList(CodeRuleVO sample) {
        Result<PagedList<CodeRule>> result = new Result<>();
        PagedList<CodeRule> list = codeRuleService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        codeRuleService.dao().fill(list).with(CodeRuleMeta.MODULE).execute();
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = CodeRuleServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(CodeRuleServiceProxy.EXPORT_EXCEL)
    public void exportExcel(CodeRuleVO sample, HttpServletResponse response) throws Exception {
        // ?????? Excel ??????
        ExcelWriter ew = codeRuleService.exportExcel(sample);
        // ??????
        DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = CodeRuleServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(CodeRuleServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        // ?????? Excel ??????
        ExcelWriter ew = codeRuleService.exportExcelTemplate();
        // ??????
        DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
    }

    @SentinelResource(value = CodeRuleServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(CodeRuleServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = codeRuleService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
