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
import com.dt.platform.proxy.common.CodeRegisterServiceProxy;
import com.dt.platform.domain.common.meta.CodeRegisterVOMeta;
import com.dt.platform.domain.common.CodeRegister;
import com.dt.platform.domain.common.CodeRegisterVO;
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
import com.dt.platform.domain.common.meta.CodeRegisterMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.common.service.ICodeRegisterService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2021-10-26 15:26:22
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("SysCodeRegisterController")
public class CodeRegisterController extends SuperController {

    @Autowired
    private ICodeRegisterService codeRegisterService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CodeRegisterVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "499322169352781824"),
		@ApiImplicitParam(name = CodeRegisterVOMeta.CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "eam_asset_change_base_info"),
		@ApiImplicitParam(name = CodeRegisterVOMeta.TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "eam"),
		@ApiImplicitParam(name = CodeRegisterVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "??????????????????"),
		@ApiImplicitParam(name = CodeRegisterVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = CodeRegisterServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CodeRegisterServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(CodeRegisterVO codeRegisterVO) {
        Result result = codeRegisterService.insert(codeRegisterVO);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CodeRegisterVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "499322169352781824")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = CodeRegisterServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CodeRegisterServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = codeRegisterService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CodeRegisterVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = CodeRegisterServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CodeRegisterServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = codeRegisterService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CodeRegisterVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "499322169352781824"),
		@ApiImplicitParam(name = CodeRegisterVOMeta.CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "eam_asset_change_base_info"),
		@ApiImplicitParam(name = CodeRegisterVOMeta.TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "eam"),
		@ApiImplicitParam(name = CodeRegisterVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "??????????????????"),
		@ApiImplicitParam(name = CodeRegisterVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { CodeRegisterVOMeta.PAGE_INDEX, CodeRegisterVOMeta.PAGE_SIZE, CodeRegisterVOMeta.SEARCH_FIELD, CodeRegisterVOMeta.FUZZY_FIELD, CodeRegisterVOMeta.SEARCH_VALUE, CodeRegisterVOMeta.SORT_FIELD, CodeRegisterVOMeta.SORT_TYPE, CodeRegisterVOMeta.IDS })
    @SentinelResource(value = CodeRegisterServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CodeRegisterServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(CodeRegisterVO codeRegisterVO) {
        Result result = codeRegisterService.update(codeRegisterVO, SaveMode.NOT_NULL_FIELDS);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CodeRegisterVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "499322169352781824"),
		@ApiImplicitParam(name = CodeRegisterVOMeta.CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "eam_asset_change_base_info"),
		@ApiImplicitParam(name = CodeRegisterVOMeta.TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "eam"),
		@ApiImplicitParam(name = CodeRegisterVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "??????????????????"),
		@ApiImplicitParam(name = CodeRegisterVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { CodeRegisterVOMeta.PAGE_INDEX, CodeRegisterVOMeta.PAGE_SIZE, CodeRegisterVOMeta.SEARCH_FIELD, CodeRegisterVOMeta.FUZZY_FIELD, CodeRegisterVOMeta.SEARCH_VALUE, CodeRegisterVOMeta.SORT_FIELD, CodeRegisterVOMeta.SORT_TYPE, CodeRegisterVOMeta.IDS })
    @SentinelResource(value = CodeRegisterServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CodeRegisterServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(CodeRegisterVO codeRegisterVO) {
        Result result = codeRegisterService.save(codeRegisterVO, SaveMode.NOT_NULL_FIELDS);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CodeRegisterVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = CodeRegisterServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CodeRegisterServiceProxy.GET_BY_ID)
    public Result<CodeRegister> getById(String id) {
        Result<CodeRegister> result = new Result<>();
        CodeRegister codeRegister = codeRegisterService.getById(id);
        // join ???????????????
        codeRegisterService.dao().fill(codeRegister).execute();
        result.success(true).data(codeRegister);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CodeRegisterVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = CodeRegisterServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CodeRegisterServiceProxy.GET_BY_IDS)
    public Result<List<CodeRegister>> getByIds(List<String> ids) {
        Result<List<CodeRegister>> result = new Result<>();
        List<CodeRegister> list = codeRegisterService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CodeRegisterVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "499322169352781824"),
		@ApiImplicitParam(name = CodeRegisterVOMeta.CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "eam_asset_change_base_info"),
		@ApiImplicitParam(name = CodeRegisterVOMeta.TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "eam"),
		@ApiImplicitParam(name = CodeRegisterVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "??????????????????"),
		@ApiImplicitParam(name = CodeRegisterVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { CodeRegisterVOMeta.PAGE_INDEX, CodeRegisterVOMeta.PAGE_SIZE })
    @SentinelResource(value = CodeRegisterServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CodeRegisterServiceProxy.QUERY_LIST)
    public Result<List<CodeRegister>> queryList(CodeRegisterVO sample) {
        Result<List<CodeRegister>> result = new Result<>();
        List<CodeRegister> list = codeRegisterService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CodeRegisterVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "499322169352781824"),
		@ApiImplicitParam(name = CodeRegisterVOMeta.CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "eam_asset_change_base_info"),
		@ApiImplicitParam(name = CodeRegisterVOMeta.TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "eam"),
		@ApiImplicitParam(name = CodeRegisterVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "??????????????????"),
		@ApiImplicitParam(name = CodeRegisterVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = CodeRegisterServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CodeRegisterServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<CodeRegister>> queryPagedList(CodeRegisterVO sample) {
        Result<PagedList<CodeRegister>> result = new Result<>();
        PagedList<CodeRegister> list = codeRegisterService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        codeRegisterService.dao().fill(list).execute();
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = CodeRegisterServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(CodeRegisterServiceProxy.EXPORT_EXCEL)
    public void exportExcel(CodeRegisterVO sample, HttpServletResponse response) throws Exception {
        // ?????? Excel ??????
        ExcelWriter ew = codeRegisterService.exportExcel(sample);
        // ??????
        DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = CodeRegisterServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(CodeRegisterServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        // ?????? Excel ??????
        ExcelWriter ew = codeRegisterService.exportExcelTemplate();
        // ??????
        DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
    }

    @SentinelResource(value = CodeRegisterServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(CodeRegisterServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = codeRegisterService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
