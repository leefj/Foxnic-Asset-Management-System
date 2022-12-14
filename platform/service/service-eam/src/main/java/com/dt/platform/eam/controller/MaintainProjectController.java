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
import com.dt.platform.proxy.eam.MaintainProjectServiceProxy;
import com.dt.platform.domain.eam.meta.MaintainProjectVOMeta;
import com.dt.platform.domain.eam.MaintainProject;
import com.dt.platform.domain.eam.MaintainProjectVO;
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
import com.dt.platform.domain.eam.meta.MaintainProjectMeta;
import java.math.BigDecimal;
import org.github.foxnic.web.domain.system.DictItem;
import com.dt.platform.domain.eam.ActionCrontab;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IMaintainProjectService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-06-03 21:20:24
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("MaintainProjectController")
public class MaintainProjectController extends SuperController {

    @Autowired
    private IMaintainProjectService maintainProjectService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintainProjectVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "585209383214907392"),
		@ApiImplicitParam(name = MaintainProjectVOMeta.CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = MaintainProjectVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = MaintainProjectVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = MaintainProjectVOMeta.MAINTAIN_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "default"),
		@ApiImplicitParam(name = MaintainProjectVOMeta.BASE_COST, value = "????????????(??????)", required = false, dataTypeClass = BigDecimal.class, example = "12.00"),
		@ApiImplicitParam(name = MaintainProjectVOMeta.ACTION_CYCLE_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = MaintainProjectVOMeta.ATTACH_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainProjectVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainProjectVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = MaintainProjectServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintainProjectServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(MaintainProjectVO maintainProjectVO) {
        Result result = maintainProjectService.insert(maintainProjectVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintainProjectVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "585209383214907392")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = MaintainProjectServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintainProjectServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = maintainProjectService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintainProjectVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = MaintainProjectServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintainProjectServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = maintainProjectService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintainProjectVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "585209383214907392"),
		@ApiImplicitParam(name = MaintainProjectVOMeta.CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = MaintainProjectVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = MaintainProjectVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = MaintainProjectVOMeta.MAINTAIN_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "default"),
		@ApiImplicitParam(name = MaintainProjectVOMeta.BASE_COST, value = "????????????(??????)", required = false, dataTypeClass = BigDecimal.class, example = "12.00"),
		@ApiImplicitParam(name = MaintainProjectVOMeta.ACTION_CYCLE_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = MaintainProjectVOMeta.ATTACH_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainProjectVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainProjectVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { MaintainProjectVOMeta.PAGE_INDEX, MaintainProjectVOMeta.PAGE_SIZE, MaintainProjectVOMeta.SEARCH_FIELD, MaintainProjectVOMeta.FUZZY_FIELD, MaintainProjectVOMeta.SEARCH_VALUE, MaintainProjectVOMeta.DIRTY_FIELDS, MaintainProjectVOMeta.SORT_FIELD, MaintainProjectVOMeta.SORT_TYPE, MaintainProjectVOMeta.IDS })
    @SentinelResource(value = MaintainProjectServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintainProjectServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(MaintainProjectVO maintainProjectVO) {
        Result result = maintainProjectService.update(maintainProjectVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintainProjectVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "585209383214907392"),
		@ApiImplicitParam(name = MaintainProjectVOMeta.CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = MaintainProjectVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = MaintainProjectVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = MaintainProjectVOMeta.MAINTAIN_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "default"),
		@ApiImplicitParam(name = MaintainProjectVOMeta.BASE_COST, value = "????????????(??????)", required = false, dataTypeClass = BigDecimal.class, example = "12.00"),
		@ApiImplicitParam(name = MaintainProjectVOMeta.ACTION_CYCLE_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = MaintainProjectVOMeta.ATTACH_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainProjectVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainProjectVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { MaintainProjectVOMeta.PAGE_INDEX, MaintainProjectVOMeta.PAGE_SIZE, MaintainProjectVOMeta.SEARCH_FIELD, MaintainProjectVOMeta.FUZZY_FIELD, MaintainProjectVOMeta.SEARCH_VALUE, MaintainProjectVOMeta.DIRTY_FIELDS, MaintainProjectVOMeta.SORT_FIELD, MaintainProjectVOMeta.SORT_TYPE, MaintainProjectVOMeta.IDS })
    @SentinelResource(value = MaintainProjectServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintainProjectServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(MaintainProjectVO maintainProjectVO) {
        Result result = maintainProjectService.save(maintainProjectVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintainProjectVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = MaintainProjectServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintainProjectServiceProxy.GET_BY_ID)
    public Result<MaintainProject> getById(String id) {
        Result<MaintainProject> result = new Result<>();
        MaintainProject maintainProject = maintainProjectService.getById(id);
        // join ???????????????
        maintainProjectService.dao().fill(maintainProject).with(MaintainProjectMeta.MAINTAIN_TYPE_DICT).with(MaintainProjectMeta.ACTION_CRONTAB).execute();
        result.success(true).data(maintainProject);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintainProjectVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = MaintainProjectServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintainProjectServiceProxy.GET_BY_IDS)
    public Result<List<MaintainProject>> getByIds(List<String> ids) {
        Result<List<MaintainProject>> result = new Result<>();
        List<MaintainProject> list = maintainProjectService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintainProjectVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "585209383214907392"),
		@ApiImplicitParam(name = MaintainProjectVOMeta.CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = MaintainProjectVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = MaintainProjectVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = MaintainProjectVOMeta.MAINTAIN_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "default"),
		@ApiImplicitParam(name = MaintainProjectVOMeta.BASE_COST, value = "????????????(??????)", required = false, dataTypeClass = BigDecimal.class, example = "12.00"),
		@ApiImplicitParam(name = MaintainProjectVOMeta.ACTION_CYCLE_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = MaintainProjectVOMeta.ATTACH_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainProjectVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainProjectVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { MaintainProjectVOMeta.PAGE_INDEX, MaintainProjectVOMeta.PAGE_SIZE })
    @SentinelResource(value = MaintainProjectServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintainProjectServiceProxy.QUERY_LIST)
    public Result<List<MaintainProject>> queryList(MaintainProjectVO sample) {
        Result<List<MaintainProject>> result = new Result<>();
        List<MaintainProject> list = maintainProjectService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = MaintainProjectVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "585209383214907392"),
		@ApiImplicitParam(name = MaintainProjectVOMeta.CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = MaintainProjectVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = MaintainProjectVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = MaintainProjectVOMeta.MAINTAIN_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "default"),
		@ApiImplicitParam(name = MaintainProjectVOMeta.BASE_COST, value = "????????????(??????)", required = false, dataTypeClass = BigDecimal.class, example = "12.00"),
		@ApiImplicitParam(name = MaintainProjectVOMeta.ACTION_CYCLE_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = MaintainProjectVOMeta.ATTACH_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainProjectVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = MaintainProjectVOMeta.SELECTED_CODE, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = MaintainProjectServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintainProjectServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<MaintainProject>> queryPagedList(MaintainProjectVO sample) {
        Result<PagedList<MaintainProject>> result = new Result<>();
        PagedList<MaintainProject> list = maintainProjectService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        maintainProjectService.dao().fill(list).with(MaintainProjectMeta.MAINTAIN_TYPE_DICT).execute();
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({})
    @ApiOperationSupport(order = 10)
    @SentinelResource(value = MaintainProjectServiceProxy.QUERY_PAGED_LIST_BY_SELECT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintainProjectServiceProxy.QUERY_PAGED_LIST_BY_SELECT)
    public Result<PagedList<MaintainProject>> queryPagedList(MaintainProjectVO sample, String ownerId, String ownerType) {
        Result<PagedList<MaintainProject>> result = new Result<>();
        PagedList<MaintainProject> list = maintainProjectService.queryPagedListBySelect(sample, ownerId, ownerType);
        // join ???????????????
        maintainProjectService.dao().fill(list).with(MaintainProjectMeta.MAINTAIN_TYPE_DICT).execute();
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({})
    @ApiOperationSupport(order = 11)
    @SentinelResource(value = MaintainProjectServiceProxy.QUERY_PAGED_LIST_BY_SELECTED, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintainProjectServiceProxy.QUERY_PAGED_LIST_BY_SELECTED)
    public Result<PagedList<MaintainProject>> queryPagedListBySelected(MaintainProjectVO sample, String ownerId, String ownerType) {
        Result<PagedList<MaintainProject>> result = new Result<>();
        PagedList<MaintainProject> list = maintainProjectService.queryPagedListBySelected(sample, ownerId, ownerType);
        // join ???????????????
        maintainProjectService.dao().fill(list).with(MaintainProjectMeta.MAINTAIN_TYPE_DICT).execute();
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({})
    @ApiOperationSupport(order = 12)
    @SentinelResource(value = MaintainProjectServiceProxy.SELECTED, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(MaintainProjectServiceProxy.SELECTED)
    public Result selected(List<String> ids, String ownerId, String selectedCode) {
        return maintainProjectService.selected(ids, ownerId, selectedCode);
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = MaintainProjectServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(MaintainProjectServiceProxy.EXPORT_EXCEL)
    public void exportExcel(MaintainProjectVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = maintainProjectService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = MaintainProjectServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(MaintainProjectServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = maintainProjectService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = MaintainProjectServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(MaintainProjectServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = maintainProjectService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
