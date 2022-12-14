package com.dt.platform.eam.controller;

import java.util.List;
import com.dt.platform.domain.eam.*;
import com.dt.platform.domain.eam.meta.MaintainProjectMeta;
import com.dt.platform.proxy.eam.MaintainProjectServiceProxy;
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
import com.dt.platform.proxy.eam.InspectionPointServiceProxy;
import com.dt.platform.domain.eam.meta.InspectionPointVOMeta;
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
import com.dt.platform.domain.eam.meta.InspectionPointMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IInspectionPointService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-06-11 08:31:05
 */
@Api(tags = "?????????")
@ApiSort(0)
@RestController("EamInspectionPointController")
public class InspectionPointController extends SuperController {

    @Autowired
    private IInspectionPointService inspectionPointService;

    /**
     * ???????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InspectionPointVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "571598323383795712"),
		@ApiImplicitParam(name = InspectionPointVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "?????????1"),
		@ApiImplicitParam(name = InspectionPointVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "?????????1"),
		@ApiImplicitParam(name = InspectionPointVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = InspectionPointVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "??????1"),
		@ApiImplicitParam(name = InspectionPointVOMeta.ROUTE_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPointVOMeta.RFID, value = "RFID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPointVOMeta.POS, value = "??????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = InspectionPointVOMeta.POS_LONGITUDE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPointVOMeta.POS_LATITUDE, value = "????????????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = InspectionPointVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPointVOMeta.PICTURE_ID, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = InspectionPointServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionPointServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(InspectionPointVO inspectionPointVO) {
        Result result = inspectionPointService.insert(inspectionPointVO, false);
        return result;
    }

    /**
     * ???????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InspectionPointVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "571598323383795712")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = InspectionPointServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionPointServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = inspectionPointService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InspectionPointVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = InspectionPointServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionPointServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = inspectionPointService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ???????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InspectionPointVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "571598323383795712"),
		@ApiImplicitParam(name = InspectionPointVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "?????????1"),
		@ApiImplicitParam(name = InspectionPointVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "?????????1"),
		@ApiImplicitParam(name = InspectionPointVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = InspectionPointVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "??????1"),
		@ApiImplicitParam(name = InspectionPointVOMeta.ROUTE_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPointVOMeta.RFID, value = "RFID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPointVOMeta.POS, value = "??????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = InspectionPointVOMeta.POS_LONGITUDE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPointVOMeta.POS_LATITUDE, value = "????????????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = InspectionPointVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPointVOMeta.PICTURE_ID, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { InspectionPointVOMeta.PAGE_INDEX, InspectionPointVOMeta.PAGE_SIZE, InspectionPointVOMeta.SEARCH_FIELD, InspectionPointVOMeta.FUZZY_FIELD, InspectionPointVOMeta.SEARCH_VALUE, InspectionPointVOMeta.DIRTY_FIELDS, InspectionPointVOMeta.SORT_FIELD, InspectionPointVOMeta.SORT_TYPE, InspectionPointVOMeta.IDS })
    @SentinelResource(value = InspectionPointServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionPointServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(InspectionPointVO inspectionPointVO) {
        Result result = inspectionPointService.update(inspectionPointVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ???????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InspectionPointVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "571598323383795712"),
		@ApiImplicitParam(name = InspectionPointVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "?????????1"),
		@ApiImplicitParam(name = InspectionPointVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "?????????1"),
		@ApiImplicitParam(name = InspectionPointVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = InspectionPointVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "??????1"),
		@ApiImplicitParam(name = InspectionPointVOMeta.ROUTE_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPointVOMeta.RFID, value = "RFID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPointVOMeta.POS, value = "??????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = InspectionPointVOMeta.POS_LONGITUDE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPointVOMeta.POS_LATITUDE, value = "????????????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = InspectionPointVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPointVOMeta.PICTURE_ID, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { InspectionPointVOMeta.PAGE_INDEX, InspectionPointVOMeta.PAGE_SIZE, InspectionPointVOMeta.SEARCH_FIELD, InspectionPointVOMeta.FUZZY_FIELD, InspectionPointVOMeta.SEARCH_VALUE, InspectionPointVOMeta.DIRTY_FIELDS, InspectionPointVOMeta.SORT_FIELD, InspectionPointVOMeta.SORT_TYPE, InspectionPointVOMeta.IDS })
    @SentinelResource(value = InspectionPointServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionPointServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(InspectionPointVO inspectionPointVO) {
        Result result = inspectionPointService.save(inspectionPointVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ???????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InspectionPointVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = InspectionPointServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionPointServiceProxy.GET_BY_ID)
    public Result<InspectionPoint> getById(String id) {
        Result<InspectionPoint> result = new Result<>();
        InspectionPoint inspectionPoint = inspectionPointService.getById(id);
        // join ???????????????
        inspectionPointService.dao().fill(inspectionPoint).with(InspectionPointMeta.ROUTE).execute();
        result.success(true).data(inspectionPoint);
        return result;
    }

    /**
     * ????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InspectionPointVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = InspectionPointServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionPointServiceProxy.GET_BY_IDS)
    public Result<List<InspectionPoint>> getByIds(List<String> ids) {
        Result<List<InspectionPoint>> result = new Result<>();
        List<InspectionPoint> list = inspectionPointService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ???????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InspectionPointVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "571598323383795712"),
		@ApiImplicitParam(name = InspectionPointVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "?????????1"),
		@ApiImplicitParam(name = InspectionPointVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "?????????1"),
		@ApiImplicitParam(name = InspectionPointVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = InspectionPointVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "??????1"),
		@ApiImplicitParam(name = InspectionPointVOMeta.ROUTE_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPointVOMeta.RFID, value = "RFID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPointVOMeta.POS, value = "??????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = InspectionPointVOMeta.POS_LONGITUDE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPointVOMeta.POS_LATITUDE, value = "????????????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = InspectionPointVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPointVOMeta.PICTURE_ID, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { InspectionPointVOMeta.PAGE_INDEX, InspectionPointVOMeta.PAGE_SIZE })
    @SentinelResource(value = InspectionPointServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionPointServiceProxy.QUERY_LIST)
    public Result<List<InspectionPoint>> queryList(InspectionPointVO sample) {
        Result<List<InspectionPoint>> result = new Result<>();
        List<InspectionPoint> list = inspectionPointService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InspectionPointVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "571598323383795712"),
		@ApiImplicitParam(name = InspectionPointVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "?????????1"),
		@ApiImplicitParam(name = InspectionPointVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "?????????1"),
		@ApiImplicitParam(name = InspectionPointVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = InspectionPointVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "??????1"),
		@ApiImplicitParam(name = InspectionPointVOMeta.ROUTE_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPointVOMeta.RFID, value = "RFID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPointVOMeta.POS, value = "??????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = InspectionPointVOMeta.POS_LONGITUDE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPointVOMeta.POS_LATITUDE, value = "????????????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = InspectionPointVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPointVOMeta.PICTURE_ID, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = InspectionPointServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionPointServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<InspectionPoint>> queryPagedList(InspectionPointVO sample) {
        Result<PagedList<InspectionPoint>> result = new Result<>();
        PagedList<InspectionPoint> list = inspectionPointService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        inspectionPointService.dao().fill(list).with(InspectionPointMeta.ROUTE).execute();
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({})
    @ApiOperationSupport(order = 10)
    @SentinelResource(value = InspectionPointServiceProxy.QUERY_PAGED_LIST_BY_SELECT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionPointServiceProxy.QUERY_PAGED_LIST_BY_SELECT)
    public Result<PagedList<InspectionPoint>> queryPagedList(InspectionPointVO sample, String ownerId, String ownerType) {
        Result<PagedList<InspectionPoint>> result = new Result<>();
        PagedList<InspectionPoint> list = inspectionPointService.queryPagedListBySelect(sample, ownerId, ownerType);
        // join ???????????????
        inspectionPointService.dao().fill(list).with(InspectionPointMeta.ROUTE).execute();
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({})
    @ApiOperationSupport(order = 11)
    @SentinelResource(value = InspectionPointServiceProxy.QUERY_PAGED_LIST_BY_SELECTED, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionPointServiceProxy.QUERY_PAGED_LIST_BY_SELECTED)
    public Result<PagedList<InspectionPoint>> queryPagedListBySelected(InspectionPointVO sample, String ownerId, String ownerType) {
        Result<PagedList<InspectionPoint>> result = new Result<>();
        PagedList<InspectionPoint> list = inspectionPointService.queryPagedListBySelected(sample, ownerId, ownerType);
        // join ???????????????
        inspectionPointService.dao().fill(list).with(InspectionPointMeta.ROUTE).execute();
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "??????")
    @ApiImplicitParams({})
    @ApiOperationSupport(order = 12)
    @SentinelResource(value = InspectionPointServiceProxy.SELECTED, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionPointServiceProxy.SELECTED)
    public Result selected(List<String> ids, String ownerId, String selectedCode) {
        return inspectionPointService.selected(ids, ownerId, selectedCode);
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = InspectionPointServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(InspectionPointServiceProxy.EXPORT_EXCEL)
    public void exportExcel(InspectionPointVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = inspectionPointService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = InspectionPointServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(InspectionPointServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = inspectionPointService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = InspectionPointServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(InspectionPointServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = inspectionPointService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
