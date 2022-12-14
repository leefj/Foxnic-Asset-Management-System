package com.dt.platform.eam.controller;

import java.util.ArrayList;
import java.util.List;
import com.dt.platform.domain.eam.InspectionGroup;
import com.dt.platform.domain.eam.meta.InspectionGroupMeta;
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
import com.dt.platform.proxy.eam.RepairGroupServiceProxy;
import com.dt.platform.domain.eam.meta.RepairGroupVOMeta;
import com.dt.platform.domain.eam.RepairGroup;
import com.dt.platform.domain.eam.RepairGroupVO;
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
import com.dt.platform.domain.eam.meta.RepairGroupMeta;
import org.github.foxnic.web.domain.hrm.Employee;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IRepairGroupService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-05-30 12:58:15
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("EamRepairGroupController")
public class RepairGroupController extends SuperController {

    @Autowired
    private IRepairGroupService repairGroupService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairGroupVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "583572042100506624"),
		@ApiImplicitParam(name = RepairGroupVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = RepairGroupVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = RepairGroupVOMeta.LEADER_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "558321538131034112"),
		@ApiImplicitParam(name = RepairGroupVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "121212")
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = RepairGroupServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairGroupServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(RepairGroupVO repairGroupVO) {
        Result result = repairGroupService.insert(repairGroupVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairGroupVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "583572042100506624")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = RepairGroupServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairGroupServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = repairGroupService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairGroupVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = RepairGroupServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairGroupServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = repairGroupService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairGroupVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "583572042100506624"),
		@ApiImplicitParam(name = RepairGroupVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = RepairGroupVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = RepairGroupVOMeta.LEADER_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "558321538131034112"),
		@ApiImplicitParam(name = RepairGroupVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "121212")
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { RepairGroupVOMeta.PAGE_INDEX, RepairGroupVOMeta.PAGE_SIZE, RepairGroupVOMeta.SEARCH_FIELD, RepairGroupVOMeta.FUZZY_FIELD, RepairGroupVOMeta.SEARCH_VALUE, RepairGroupVOMeta.DIRTY_FIELDS, RepairGroupVOMeta.SORT_FIELD, RepairGroupVOMeta.SORT_TYPE, RepairGroupVOMeta.IDS })
    @SentinelResource(value = RepairGroupServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairGroupServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(RepairGroupVO repairGroupVO) {
        Result result = repairGroupService.update(repairGroupVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairGroupVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "583572042100506624"),
		@ApiImplicitParam(name = RepairGroupVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = RepairGroupVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = RepairGroupVOMeta.LEADER_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "558321538131034112"),
		@ApiImplicitParam(name = RepairGroupVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "121212")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { RepairGroupVOMeta.PAGE_INDEX, RepairGroupVOMeta.PAGE_SIZE, RepairGroupVOMeta.SEARCH_FIELD, RepairGroupVOMeta.FUZZY_FIELD, RepairGroupVOMeta.SEARCH_VALUE, RepairGroupVOMeta.DIRTY_FIELDS, RepairGroupVOMeta.SORT_FIELD, RepairGroupVOMeta.SORT_TYPE, RepairGroupVOMeta.IDS })
    @SentinelResource(value = RepairGroupServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairGroupServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(RepairGroupVO repairGroupVO) {
        Result result = repairGroupService.save(repairGroupVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairGroupVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = RepairGroupServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairGroupServiceProxy.GET_BY_ID)
    public Result<RepairGroup> getById(String id) {
        Result<RepairGroup> result = new Result<>();
        RepairGroup repairGroup = repairGroupService.getById(id);
        // join ???????????????
        repairGroupService.dao().fill(repairGroup).with("leader").with(InspectionGroupMeta.MEMBER_LIST).execute();
        repairGroupService.dao().join(repairGroup.getLeader(), Person.class);
        repairGroupService.dao().join(repairGroup.getMemberList(), Person.class);
        result.success(true).data(repairGroup);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairGroupVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = RepairGroupServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairGroupServiceProxy.GET_BY_IDS)
    public Result<List<RepairGroup>> getByIds(List<String> ids) {
        Result<List<RepairGroup>> result = new Result<>();
        List<RepairGroup> list = repairGroupService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairGroupVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "583572042100506624"),
		@ApiImplicitParam(name = RepairGroupVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = RepairGroupVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = RepairGroupVOMeta.LEADER_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "558321538131034112"),
		@ApiImplicitParam(name = RepairGroupVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "121212")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { RepairGroupVOMeta.PAGE_INDEX, RepairGroupVOMeta.PAGE_SIZE })
    @SentinelResource(value = RepairGroupServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairGroupServiceProxy.QUERY_LIST)
    public Result<List<RepairGroup>> queryList(RepairGroupVO sample) {
        Result<List<RepairGroup>> result = new Result<>();
        List<RepairGroup> list = repairGroupService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = RepairGroupVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "583572042100506624"),
		@ApiImplicitParam(name = RepairGroupVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = RepairGroupVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = RepairGroupVOMeta.LEADER_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "558321538131034112"),
		@ApiImplicitParam(name = RepairGroupVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "121212")
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = RepairGroupServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(RepairGroupServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<RepairGroup>> queryPagedList(RepairGroupVO sample) {
        Result<PagedList<RepairGroup>> result = new Result<>();
        PagedList<RepairGroup> list = repairGroupService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        repairGroupService.dao().fill(list).with("leader").with(RepairGroupMeta.MEMBER_LIST).execute();
        List<Employee> leader = CollectorUtil.collectList(list.getList(), RepairGroup::getLeader);
        repairGroupService.dao().join(leader, Person.class);
        List<List<Employee>> memberList = CollectorUtil.collectList(list.getList(), RepairGroup::getMemberList);
        List<Employee> member = memberList.stream().collect(ArrayList::new, ArrayList::addAll, ArrayList::addAll);
        repairGroupService.dao().join(member, Person.class);
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = RepairGroupServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(RepairGroupServiceProxy.EXPORT_EXCEL)
    public void exportExcel(RepairGroupVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = repairGroupService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = RepairGroupServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(RepairGroupServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = repairGroupService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = RepairGroupServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(RepairGroupServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = repairGroupService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
