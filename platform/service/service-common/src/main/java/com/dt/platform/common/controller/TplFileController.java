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
import com.dt.platform.proxy.common.TplFileServiceProxy;
import com.dt.platform.domain.common.meta.TplFileVOMeta;
import com.dt.platform.domain.common.TplFile;
import com.dt.platform.domain.common.TplFileVO;
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
import com.dt.platform.domain.common.meta.TplFileMeta;
import com.dt.platform.domain.common.CodeRegister;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.common.service.ITplFileService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2021-09-14 19:38:58
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("SysTplFileController")
public class TplFileController extends SuperController {

    @Autowired
    private ITplFileService tplFileService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = TplFileVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = TplFileVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = TplFileVOMeta.TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = TplFileVOMeta.CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = TplFileVOMeta.TPL_FILE_TYPE, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = TplFileVOMeta.FILE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = TplFileVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = TplFileServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(TplFileServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(TplFileVO tplFileVO) {
        Result result = tplFileService.insert(tplFileVO);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = TplFileVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = TplFileServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(TplFileServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = tplFileService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = TplFileVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = TplFileServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(TplFileServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = tplFileService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = TplFileVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = TplFileVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = TplFileVOMeta.TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = TplFileVOMeta.CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = TplFileVOMeta.TPL_FILE_TYPE, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = TplFileVOMeta.FILE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = TplFileVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { TplFileVOMeta.PAGE_INDEX, TplFileVOMeta.PAGE_SIZE, TplFileVOMeta.SEARCH_FIELD, TplFileVOMeta.FUZZY_FIELD, TplFileVOMeta.SEARCH_VALUE, TplFileVOMeta.SORT_FIELD, TplFileVOMeta.SORT_TYPE, TplFileVOMeta.IDS })
    @SentinelResource(value = TplFileServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(TplFileServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(TplFileVO tplFileVO) {
        Result result = tplFileService.update(tplFileVO, SaveMode.NOT_NULL_FIELDS);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = TplFileVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = TplFileVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = TplFileVOMeta.TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = TplFileVOMeta.CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = TplFileVOMeta.TPL_FILE_TYPE, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = TplFileVOMeta.FILE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = TplFileVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { TplFileVOMeta.PAGE_INDEX, TplFileVOMeta.PAGE_SIZE, TplFileVOMeta.SEARCH_FIELD, TplFileVOMeta.FUZZY_FIELD, TplFileVOMeta.SEARCH_VALUE, TplFileVOMeta.SORT_FIELD, TplFileVOMeta.SORT_TYPE, TplFileVOMeta.IDS })
    @SentinelResource(value = TplFileServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(TplFileServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(TplFileVO tplFileVO) {
        Result result = tplFileService.save(tplFileVO, SaveMode.NOT_NULL_FIELDS);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = TplFileVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = TplFileServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(TplFileServiceProxy.GET_BY_ID)
    public Result<TplFile> getById(String id) {
        Result<TplFile> result = new Result<>();
        TplFile tplFile = tplFileService.getById(id);
        // ????????? ???????????? ??????
        tplFileService.join(tplFile, TplFileMeta.BUSINESS_CODE);
        result.success(true).data(tplFile);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = TplFileVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = TplFileServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(TplFileServiceProxy.GET_BY_IDS)
    public Result<List<TplFile>> getByIds(List<String> ids) {
        Result<List<TplFile>> result = new Result<>();
        List<TplFile> list = tplFileService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = TplFileVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = TplFileVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = TplFileVOMeta.TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = TplFileVOMeta.CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = TplFileVOMeta.TPL_FILE_TYPE, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = TplFileVOMeta.FILE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = TplFileVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { TplFileVOMeta.PAGE_INDEX, TplFileVOMeta.PAGE_SIZE })
    @SentinelResource(value = TplFileServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(TplFileServiceProxy.QUERY_LIST)
    public Result<List<TplFile>> queryList(TplFileVO sample) {
        Result<List<TplFile>> result = new Result<>();
        List<TplFile> list = tplFileService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = TplFileVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = TplFileVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = TplFileVOMeta.TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = TplFileVOMeta.CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = TplFileVOMeta.TPL_FILE_TYPE, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = TplFileVOMeta.FILE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = TplFileVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = TplFileServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(TplFileServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<TplFile>> queryPagedList(TplFileVO sample) {
        Result<PagedList<TplFile>> result = new Result<>();
        PagedList<TplFile> list = tplFileService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // ????????? ???????????? ??????
        tplFileService.join(list, TplFileMeta.BUSINESS_CODE);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = TplFileVOMeta.CODE, value = "??????", required = true, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 9)
    @SentinelResource(value = TplFileServiceProxy.GET_TPL_FILE_STREAM_BY_CODE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(TplFileServiceProxy.GET_TPL_FILE_STREAM_BY_CODE)
    public InputStream getTplFileStreamByCode(String code) {
        return tplFileService.getTplFileStreamByCode(code);
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = TplFileServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(TplFileServiceProxy.EXPORT_EXCEL)
    public void exportExcel(TplFileVO sample, HttpServletResponse response) throws Exception {
        // ?????? Excel ??????
        ExcelWriter ew = tplFileService.exportExcel(sample);
        // ??????
        DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = TplFileServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(TplFileServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        // ?????? Excel ??????
        ExcelWriter ew = tplFileService.exportExcelTemplate();
        // ??????
        DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
    }

    @SentinelResource(value = TplFileServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(TplFileServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = tplFileService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
