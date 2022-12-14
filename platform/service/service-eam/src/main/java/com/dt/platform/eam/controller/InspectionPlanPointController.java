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
import com.dt.platform.proxy.eam.InspectionPlanPointServiceProxy;
import com.dt.platform.domain.eam.meta.InspectionPlanPointVOMeta;
import com.dt.platform.domain.eam.InspectionPlanPoint;
import com.dt.platform.domain.eam.InspectionPlanPointVO;
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
import com.dt.platform.domain.eam.meta.InspectionPlanPointMeta;
import com.dt.platform.domain.eam.InspectionPoint;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IInspectionPlanPointService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-04-27 07:34:35
 */
@Api(tags = "?????????")
@ApiSort(0)
@RestController("EamInspectionPlanPointController")
public class InspectionPlanPointController extends SuperController {

    @Autowired
    private IInspectionPlanPointService inspectionPlanPointService;

    /**
     * ???????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InspectionPlanPointVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPlanPointVOMeta.POINT_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPlanPointVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = InspectionPlanPointVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = InspectionPlanPointServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionPlanPointServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(InspectionPlanPointVO inspectionPlanPointVO) {
        Result result = inspectionPlanPointService.insert(inspectionPlanPointVO, false);
        return result;
    }

    /**
     * ???????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InspectionPlanPointVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = InspectionPlanPointServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionPlanPointServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = inspectionPlanPointService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InspectionPlanPointVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = InspectionPlanPointServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionPlanPointServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = inspectionPlanPointService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ???????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InspectionPlanPointVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPlanPointVOMeta.POINT_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPlanPointVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = InspectionPlanPointVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { InspectionPlanPointVOMeta.PAGE_INDEX, InspectionPlanPointVOMeta.PAGE_SIZE, InspectionPlanPointVOMeta.SEARCH_FIELD, InspectionPlanPointVOMeta.FUZZY_FIELD, InspectionPlanPointVOMeta.SEARCH_VALUE, InspectionPlanPointVOMeta.DIRTY_FIELDS, InspectionPlanPointVOMeta.SORT_FIELD, InspectionPlanPointVOMeta.SORT_TYPE, InspectionPlanPointVOMeta.IDS })
    @SentinelResource(value = InspectionPlanPointServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionPlanPointServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(InspectionPlanPointVO inspectionPlanPointVO) {
        Result result = inspectionPlanPointService.update(inspectionPlanPointVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ???????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InspectionPlanPointVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPlanPointVOMeta.POINT_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPlanPointVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = InspectionPlanPointVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { InspectionPlanPointVOMeta.PAGE_INDEX, InspectionPlanPointVOMeta.PAGE_SIZE, InspectionPlanPointVOMeta.SEARCH_FIELD, InspectionPlanPointVOMeta.FUZZY_FIELD, InspectionPlanPointVOMeta.SEARCH_VALUE, InspectionPlanPointVOMeta.DIRTY_FIELDS, InspectionPlanPointVOMeta.SORT_FIELD, InspectionPlanPointVOMeta.SORT_TYPE, InspectionPlanPointVOMeta.IDS })
    @SentinelResource(value = InspectionPlanPointServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionPlanPointServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(InspectionPlanPointVO inspectionPlanPointVO) {
        Result result = inspectionPlanPointService.save(inspectionPlanPointVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ???????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InspectionPlanPointVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = InspectionPlanPointServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionPlanPointServiceProxy.GET_BY_ID)
    public Result<InspectionPlanPoint> getById(String id) {
        Result<InspectionPlanPoint> result = new Result<>();
        InspectionPlanPoint inspectionPlanPoint = inspectionPlanPointService.getById(id);
        result.success(true).data(inspectionPlanPoint);
        return result;
    }

    /**
     * ????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InspectionPlanPointVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = InspectionPlanPointServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionPlanPointServiceProxy.GET_BY_IDS)
    public Result<List<InspectionPlanPoint>> getByIds(List<String> ids) {
        Result<List<InspectionPlanPoint>> result = new Result<>();
        List<InspectionPlanPoint> list = inspectionPlanPointService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ???????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InspectionPlanPointVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPlanPointVOMeta.POINT_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPlanPointVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = InspectionPlanPointVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { InspectionPlanPointVOMeta.PAGE_INDEX, InspectionPlanPointVOMeta.PAGE_SIZE })
    @SentinelResource(value = InspectionPlanPointServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionPlanPointServiceProxy.QUERY_LIST)
    public Result<List<InspectionPlanPoint>> queryList(InspectionPlanPointVO sample) {
        Result<List<InspectionPlanPoint>> result = new Result<>();
        List<InspectionPlanPoint> list = inspectionPlanPointService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InspectionPlanPointVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPlanPointVOMeta.POINT_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPlanPointVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = InspectionPlanPointVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = InspectionPlanPointServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionPlanPointServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<InspectionPlanPoint>> queryPagedList(InspectionPlanPointVO sample) {
        Result<PagedList<InspectionPlanPoint>> result = new Result<>();
        PagedList<InspectionPlanPoint> list = inspectionPlanPointService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = InspectionPlanPointServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(InspectionPlanPointServiceProxy.EXPORT_EXCEL)
    public void exportExcel(InspectionPlanPointVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = inspectionPlanPointService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = InspectionPlanPointServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(InspectionPlanPointServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = inspectionPlanPointService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = InspectionPlanPointServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(InspectionPlanPointServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = inspectionPlanPointService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
