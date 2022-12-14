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
import com.dt.platform.proxy.eam.InspectionPointOwnerServiceProxy;
import com.dt.platform.domain.eam.meta.InspectionPointOwnerVOMeta;
import com.dt.platform.domain.eam.InspectionPointOwner;
import com.dt.platform.domain.eam.InspectionPointOwnerVO;
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
import com.dt.platform.domain.eam.meta.InspectionPointOwnerMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IInspectionPointOwnerService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-06-11 17:33:04
 */
@Api(tags = "?????????")
@ApiSort(0)
@RestController("EamInspectionPointOwnerController")
public class InspectionPointOwnerController extends SuperController {

    @Autowired
    private IInspectionPointOwnerService inspectionPointOwnerService;

    /**
     * ???????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InspectionPointOwnerVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPointOwnerVOMeta.OWNER_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPointOwnerVOMeta.POINT_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPointOwnerVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = InspectionPointOwnerVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPointOwnerVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = InspectionPointOwnerServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionPointOwnerServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(InspectionPointOwnerVO inspectionPointOwnerVO) {
        Result result = inspectionPointOwnerService.insert(inspectionPointOwnerVO, false);
        return result;
    }

    /**
     * ???????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InspectionPointOwnerVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = InspectionPointOwnerServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionPointOwnerServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = inspectionPointOwnerService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InspectionPointOwnerVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = InspectionPointOwnerServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionPointOwnerServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = inspectionPointOwnerService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ???????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InspectionPointOwnerVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPointOwnerVOMeta.OWNER_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPointOwnerVOMeta.POINT_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPointOwnerVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = InspectionPointOwnerVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPointOwnerVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { InspectionPointOwnerVOMeta.PAGE_INDEX, InspectionPointOwnerVOMeta.PAGE_SIZE, InspectionPointOwnerVOMeta.SEARCH_FIELD, InspectionPointOwnerVOMeta.FUZZY_FIELD, InspectionPointOwnerVOMeta.SEARCH_VALUE, InspectionPointOwnerVOMeta.DIRTY_FIELDS, InspectionPointOwnerVOMeta.SORT_FIELD, InspectionPointOwnerVOMeta.SORT_TYPE, InspectionPointOwnerVOMeta.IDS })
    @SentinelResource(value = InspectionPointOwnerServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionPointOwnerServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(InspectionPointOwnerVO inspectionPointOwnerVO) {
        Result result = inspectionPointOwnerService.update(inspectionPointOwnerVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ???????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InspectionPointOwnerVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPointOwnerVOMeta.OWNER_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPointOwnerVOMeta.POINT_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPointOwnerVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = InspectionPointOwnerVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPointOwnerVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { InspectionPointOwnerVOMeta.PAGE_INDEX, InspectionPointOwnerVOMeta.PAGE_SIZE, InspectionPointOwnerVOMeta.SEARCH_FIELD, InspectionPointOwnerVOMeta.FUZZY_FIELD, InspectionPointOwnerVOMeta.SEARCH_VALUE, InspectionPointOwnerVOMeta.DIRTY_FIELDS, InspectionPointOwnerVOMeta.SORT_FIELD, InspectionPointOwnerVOMeta.SORT_TYPE, InspectionPointOwnerVOMeta.IDS })
    @SentinelResource(value = InspectionPointOwnerServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionPointOwnerServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(InspectionPointOwnerVO inspectionPointOwnerVO) {
        Result result = inspectionPointOwnerService.save(inspectionPointOwnerVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ???????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InspectionPointOwnerVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = InspectionPointOwnerServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionPointOwnerServiceProxy.GET_BY_ID)
    public Result<InspectionPointOwner> getById(String id) {
        Result<InspectionPointOwner> result = new Result<>();
        InspectionPointOwner inspectionPointOwner = inspectionPointOwnerService.getById(id);
        result.success(true).data(inspectionPointOwner);
        return result;
    }

    /**
     * ????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InspectionPointOwnerVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = InspectionPointOwnerServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionPointOwnerServiceProxy.GET_BY_IDS)
    public Result<List<InspectionPointOwner>> getByIds(List<String> ids) {
        Result<List<InspectionPointOwner>> result = new Result<>();
        List<InspectionPointOwner> list = inspectionPointOwnerService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ???????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InspectionPointOwnerVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPointOwnerVOMeta.OWNER_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPointOwnerVOMeta.POINT_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPointOwnerVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = InspectionPointOwnerVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPointOwnerVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { InspectionPointOwnerVOMeta.PAGE_INDEX, InspectionPointOwnerVOMeta.PAGE_SIZE })
    @SentinelResource(value = InspectionPointOwnerServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionPointOwnerServiceProxy.QUERY_LIST)
    public Result<List<InspectionPointOwner>> queryList(InspectionPointOwnerVO sample) {
        Result<List<InspectionPointOwner>> result = new Result<>();
        List<InspectionPointOwner> list = inspectionPointOwnerService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InspectionPointOwnerVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPointOwnerVOMeta.OWNER_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPointOwnerVOMeta.POINT_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPointOwnerVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = InspectionPointOwnerVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionPointOwnerVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = InspectionPointOwnerServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionPointOwnerServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<InspectionPointOwner>> queryPagedList(InspectionPointOwnerVO sample) {
        Result<PagedList<InspectionPointOwner>> result = new Result<>();
        PagedList<InspectionPointOwner> list = inspectionPointOwnerService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = InspectionPointOwnerServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(InspectionPointOwnerServiceProxy.EXPORT_EXCEL)
    public void exportExcel(InspectionPointOwnerVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = inspectionPointOwnerService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = InspectionPointOwnerServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(InspectionPointOwnerServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = inspectionPointOwnerService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = InspectionPointOwnerServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(InspectionPointOwnerServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = inspectionPointOwnerService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
