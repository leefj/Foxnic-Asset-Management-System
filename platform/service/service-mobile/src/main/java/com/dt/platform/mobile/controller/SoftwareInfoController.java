package com.dt.platform.mobile.controller;

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
import com.dt.platform.proxy.mobile.SoftwareInfoServiceProxy;
import com.dt.platform.domain.mobile.meta.SoftwareInfoVOMeta;
import com.dt.platform.domain.mobile.SoftwareInfo;
import com.dt.platform.domain.mobile.SoftwareInfoVO;
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
import com.dt.platform.domain.mobile.meta.SoftwareInfoMeta;
import com.dt.platform.domain.mobile.SoftwareGroup;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.mobile.service.ISoftwareInfoService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-06-26 15:08:05
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("AppSoftwareInfoController")
public class SoftwareInfoController extends SuperController {

    @Autowired
    private ISoftwareInfoService softwareInfoService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SoftwareInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "593357632526356480"),
		@ApiImplicitParam(name = SoftwareInfoVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "eam_mobile_android"),
		@ApiImplicitParam(name = SoftwareInfoVOMeta.GROUP_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "593357452821401600"),
		@ApiImplicitParam(name = SoftwareInfoVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "???????????????"),
		@ApiImplicitParam(name = SoftwareInfoVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = SoftwareInfoVOMeta.TYPE, value = "??????", required = false, dataTypeClass = String.class, example = "android"),
		@ApiImplicitParam(name = SoftwareInfoVOMeta.SOFTWARE_VERSION, value = "????????????", required = false, dataTypeClass = String.class, example = "1.0.9"),
		@ApiImplicitParam(name = SoftwareInfoVOMeta.PICTURE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SoftwareInfoVOMeta.FILE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SoftwareInfoVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = SoftwareInfoServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SoftwareInfoServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(SoftwareInfoVO softwareInfoVO) {
        Result result = softwareInfoService.insert(softwareInfoVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SoftwareInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "593357632526356480")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = SoftwareInfoServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SoftwareInfoServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = softwareInfoService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SoftwareInfoVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = SoftwareInfoServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SoftwareInfoServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = softwareInfoService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SoftwareInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "593357632526356480"),
		@ApiImplicitParam(name = SoftwareInfoVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "eam_mobile_android"),
		@ApiImplicitParam(name = SoftwareInfoVOMeta.GROUP_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "593357452821401600"),
		@ApiImplicitParam(name = SoftwareInfoVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "???????????????"),
		@ApiImplicitParam(name = SoftwareInfoVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = SoftwareInfoVOMeta.TYPE, value = "??????", required = false, dataTypeClass = String.class, example = "android"),
		@ApiImplicitParam(name = SoftwareInfoVOMeta.SOFTWARE_VERSION, value = "????????????", required = false, dataTypeClass = String.class, example = "1.0.9"),
		@ApiImplicitParam(name = SoftwareInfoVOMeta.PICTURE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SoftwareInfoVOMeta.FILE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SoftwareInfoVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { SoftwareInfoVOMeta.PAGE_INDEX, SoftwareInfoVOMeta.PAGE_SIZE, SoftwareInfoVOMeta.SEARCH_FIELD, SoftwareInfoVOMeta.FUZZY_FIELD, SoftwareInfoVOMeta.SEARCH_VALUE, SoftwareInfoVOMeta.DIRTY_FIELDS, SoftwareInfoVOMeta.SORT_FIELD, SoftwareInfoVOMeta.SORT_TYPE, SoftwareInfoVOMeta.IDS })
    @SentinelResource(value = SoftwareInfoServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SoftwareInfoServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(SoftwareInfoVO softwareInfoVO) {
        Result result = softwareInfoService.update(softwareInfoVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SoftwareInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "593357632526356480"),
		@ApiImplicitParam(name = SoftwareInfoVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "eam_mobile_android"),
		@ApiImplicitParam(name = SoftwareInfoVOMeta.GROUP_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "593357452821401600"),
		@ApiImplicitParam(name = SoftwareInfoVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "???????????????"),
		@ApiImplicitParam(name = SoftwareInfoVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = SoftwareInfoVOMeta.TYPE, value = "??????", required = false, dataTypeClass = String.class, example = "android"),
		@ApiImplicitParam(name = SoftwareInfoVOMeta.SOFTWARE_VERSION, value = "????????????", required = false, dataTypeClass = String.class, example = "1.0.9"),
		@ApiImplicitParam(name = SoftwareInfoVOMeta.PICTURE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SoftwareInfoVOMeta.FILE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SoftwareInfoVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { SoftwareInfoVOMeta.PAGE_INDEX, SoftwareInfoVOMeta.PAGE_SIZE, SoftwareInfoVOMeta.SEARCH_FIELD, SoftwareInfoVOMeta.FUZZY_FIELD, SoftwareInfoVOMeta.SEARCH_VALUE, SoftwareInfoVOMeta.DIRTY_FIELDS, SoftwareInfoVOMeta.SORT_FIELD, SoftwareInfoVOMeta.SORT_TYPE, SoftwareInfoVOMeta.IDS })
    @SentinelResource(value = SoftwareInfoServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SoftwareInfoServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(SoftwareInfoVO softwareInfoVO) {
        Result result = softwareInfoService.save(softwareInfoVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SoftwareInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = SoftwareInfoServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SoftwareInfoServiceProxy.GET_BY_ID)
    public Result<SoftwareInfo> getById(String id) {
        Result<SoftwareInfo> result = new Result<>();
        SoftwareInfo softwareInfo = softwareInfoService.getById(id);
        // join ???????????????
        softwareInfoService.dao().fill(softwareInfo).with(SoftwareInfoMeta.GROUP).execute();
        result.success(true).data(softwareInfo);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SoftwareInfoVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = SoftwareInfoServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SoftwareInfoServiceProxy.GET_BY_IDS)
    public Result<List<SoftwareInfo>> getByIds(List<String> ids) {
        Result<List<SoftwareInfo>> result = new Result<>();
        List<SoftwareInfo> list = softwareInfoService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SoftwareInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "593357632526356480"),
		@ApiImplicitParam(name = SoftwareInfoVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "eam_mobile_android"),
		@ApiImplicitParam(name = SoftwareInfoVOMeta.GROUP_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "593357452821401600"),
		@ApiImplicitParam(name = SoftwareInfoVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "???????????????"),
		@ApiImplicitParam(name = SoftwareInfoVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = SoftwareInfoVOMeta.TYPE, value = "??????", required = false, dataTypeClass = String.class, example = "android"),
		@ApiImplicitParam(name = SoftwareInfoVOMeta.SOFTWARE_VERSION, value = "????????????", required = false, dataTypeClass = String.class, example = "1.0.9"),
		@ApiImplicitParam(name = SoftwareInfoVOMeta.PICTURE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SoftwareInfoVOMeta.FILE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SoftwareInfoVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { SoftwareInfoVOMeta.PAGE_INDEX, SoftwareInfoVOMeta.PAGE_SIZE })
    @SentinelResource(value = SoftwareInfoServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SoftwareInfoServiceProxy.QUERY_LIST)
    public Result<List<SoftwareInfo>> queryList(SoftwareInfoVO sample) {
        Result<List<SoftwareInfo>> result = new Result<>();
        List<SoftwareInfo> list = softwareInfoService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = SoftwareInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "593357632526356480"),
		@ApiImplicitParam(name = SoftwareInfoVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "eam_mobile_android"),
		@ApiImplicitParam(name = SoftwareInfoVOMeta.GROUP_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "593357452821401600"),
		@ApiImplicitParam(name = SoftwareInfoVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "???????????????"),
		@ApiImplicitParam(name = SoftwareInfoVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = SoftwareInfoVOMeta.TYPE, value = "??????", required = false, dataTypeClass = String.class, example = "android"),
		@ApiImplicitParam(name = SoftwareInfoVOMeta.SOFTWARE_VERSION, value = "????????????", required = false, dataTypeClass = String.class, example = "1.0.9"),
		@ApiImplicitParam(name = SoftwareInfoVOMeta.PICTURE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SoftwareInfoVOMeta.FILE_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = SoftwareInfoVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = SoftwareInfoServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(SoftwareInfoServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<SoftwareInfo>> queryPagedList(SoftwareInfoVO sample) {
        Result<PagedList<SoftwareInfo>> result = new Result<>();
        PagedList<SoftwareInfo> list = softwareInfoService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        softwareInfoService.dao().fill(list).with(SoftwareInfoMeta.GROUP).execute();
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = SoftwareInfoServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(SoftwareInfoServiceProxy.EXPORT_EXCEL)
    public void exportExcel(SoftwareInfoVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = softwareInfoService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = SoftwareInfoServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(SoftwareInfoServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = softwareInfoService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = SoftwareInfoServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(SoftwareInfoServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = softwareInfoService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
