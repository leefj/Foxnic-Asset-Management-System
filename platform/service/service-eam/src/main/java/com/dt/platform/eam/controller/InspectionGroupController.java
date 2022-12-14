package com.dt.platform.eam.controller;

import java.util.ArrayList;
import java.util.List;
import com.dt.platform.domain.eam.Inventory;
import com.github.foxnic.commons.collection.CollectorUtil;
import org.github.foxnic.web.domain.hrm.Person;
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
import com.dt.platform.proxy.eam.InspectionGroupServiceProxy;
import com.dt.platform.domain.eam.meta.InspectionGroupVOMeta;
import com.dt.platform.domain.eam.InspectionGroup;
import com.dt.platform.domain.eam.InspectionGroupVO;
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
import com.dt.platform.domain.eam.meta.InspectionGroupMeta;
import org.github.foxnic.web.domain.hrm.Employee;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IInspectionGroupService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-05-30 12:57:14
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("EamInspectionGroupController")
public class InspectionGroupController extends SuperController {

    @Autowired
    private IInspectionGroupService inspectionGroupService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InspectionGroupVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "571667599155269632"),
		@ApiImplicitParam(name = InspectionGroupVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????1"),
		@ApiImplicitParam(name = InspectionGroupVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = InspectionGroupVOMeta.LEADER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionGroupVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "??????2")
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = InspectionGroupServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionGroupServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(InspectionGroupVO inspectionGroupVO) {
        Result result = inspectionGroupService.insert(inspectionGroupVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InspectionGroupVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "571667599155269632")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = InspectionGroupServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionGroupServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = inspectionGroupService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InspectionGroupVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = InspectionGroupServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionGroupServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = inspectionGroupService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InspectionGroupVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "571667599155269632"),
		@ApiImplicitParam(name = InspectionGroupVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????1"),
		@ApiImplicitParam(name = InspectionGroupVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = InspectionGroupVOMeta.LEADER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionGroupVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "??????2")
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { InspectionGroupVOMeta.PAGE_INDEX, InspectionGroupVOMeta.PAGE_SIZE, InspectionGroupVOMeta.SEARCH_FIELD, InspectionGroupVOMeta.FUZZY_FIELD, InspectionGroupVOMeta.SEARCH_VALUE, InspectionGroupVOMeta.DIRTY_FIELDS, InspectionGroupVOMeta.SORT_FIELD, InspectionGroupVOMeta.SORT_TYPE, InspectionGroupVOMeta.IDS })
    @SentinelResource(value = InspectionGroupServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionGroupServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(InspectionGroupVO inspectionGroupVO) {
        Result result = inspectionGroupService.update(inspectionGroupVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InspectionGroupVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "571667599155269632"),
		@ApiImplicitParam(name = InspectionGroupVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????1"),
		@ApiImplicitParam(name = InspectionGroupVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = InspectionGroupVOMeta.LEADER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionGroupVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "??????2")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { InspectionGroupVOMeta.PAGE_INDEX, InspectionGroupVOMeta.PAGE_SIZE, InspectionGroupVOMeta.SEARCH_FIELD, InspectionGroupVOMeta.FUZZY_FIELD, InspectionGroupVOMeta.SEARCH_VALUE, InspectionGroupVOMeta.DIRTY_FIELDS, InspectionGroupVOMeta.SORT_FIELD, InspectionGroupVOMeta.SORT_TYPE, InspectionGroupVOMeta.IDS })
    @SentinelResource(value = InspectionGroupServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionGroupServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(InspectionGroupVO inspectionGroupVO) {
        Result result = inspectionGroupService.save(inspectionGroupVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InspectionGroupVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = InspectionGroupServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionGroupServiceProxy.GET_BY_ID)
    public Result<InspectionGroup> getById(String id) {
        Result<InspectionGroup> result = new Result<>();
        InspectionGroup inspectionGroup = inspectionGroupService.getById(id);
        // join ???????????????
        inspectionGroupService.dao().fill(inspectionGroup).with("leader").with(InspectionGroupMeta.MEMBER_LIST).execute();
        inspectionGroupService.dao().join(inspectionGroup.getLeader(), Person.class);
        inspectionGroupService.dao().join(inspectionGroup.getMemberList(), Person.class);
        result.success(true).data(inspectionGroup);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InspectionGroupVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = InspectionGroupServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionGroupServiceProxy.GET_BY_IDS)
    public Result<List<InspectionGroup>> getByIds(List<String> ids) {
        Result<List<InspectionGroup>> result = new Result<>();
        List<InspectionGroup> list = inspectionGroupService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InspectionGroupVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "571667599155269632"),
		@ApiImplicitParam(name = InspectionGroupVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????1"),
		@ApiImplicitParam(name = InspectionGroupVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = InspectionGroupVOMeta.LEADER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionGroupVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "??????2")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { InspectionGroupVOMeta.PAGE_INDEX, InspectionGroupVOMeta.PAGE_SIZE })
    @SentinelResource(value = InspectionGroupServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionGroupServiceProxy.QUERY_LIST)
    public Result<List<InspectionGroup>> queryList(InspectionGroupVO sample) {
        Result<List<InspectionGroup>> result = new Result<>();
        List<InspectionGroup> list = inspectionGroupService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = InspectionGroupVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "571667599155269632"),
		@ApiImplicitParam(name = InspectionGroupVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????1"),
		@ApiImplicitParam(name = InspectionGroupVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = InspectionGroupVOMeta.LEADER_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = InspectionGroupVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "??????2")
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = InspectionGroupServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(InspectionGroupServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<InspectionGroup>> queryPagedList(InspectionGroupVO sample) {
        Result<PagedList<InspectionGroup>> result = new Result<>();
        PagedList<InspectionGroup> list = inspectionGroupService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        inspectionGroupService.dao().fill(list).with("leader").with(InspectionGroupMeta.MEMBER_LIST).execute();
        List<Employee> leader = CollectorUtil.collectList(list.getList(), InspectionGroup::getLeader);
        inspectionGroupService.dao().join(leader, Person.class);
        List<List<Employee>> memberList = CollectorUtil.collectList(list.getList(), InspectionGroup::getMemberList);
        List<Employee> member = memberList.stream().collect(ArrayList::new, ArrayList::addAll, ArrayList::addAll);
        inspectionGroupService.dao().join(member, Person.class);
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = InspectionGroupServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(InspectionGroupServiceProxy.EXPORT_EXCEL)
    public void exportExcel(InspectionGroupVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = inspectionGroupService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = InspectionGroupServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(InspectionGroupServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = inspectionGroupService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = InspectionGroupServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(InspectionGroupServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = inspectionGroupService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
