package com.dt.platform.eam.controller;

import java.util.List;
import com.dt.platform.constants.enums.eam.AssetHandleStatusEnum;
import com.dt.platform.domain.eam.*;
import com.dt.platform.domain.eam.meta.AssetCollectionVOMeta;
import com.dt.platform.proxy.eam.AssetCollectionServiceProxy;
import com.github.foxnic.commons.collection.CollectorUtil;
import com.github.foxnic.commons.lang.StringUtil;
import org.github.foxnic.web.domain.bpm.BpmActionResult;
import org.github.foxnic.web.domain.bpm.BpmEvent;
import org.github.foxnic.web.domain.changes.ProcessApproveVO;
import org.github.foxnic.web.domain.hrm.Person;
import org.github.foxnic.web.proxy.bpm.BpmCallbackController;
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
import com.dt.platform.proxy.eam.AssetCollectionReturnServiceProxy;
import com.dt.platform.domain.eam.meta.AssetCollectionReturnVOMeta;
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
import com.dt.platform.domain.eam.meta.AssetCollectionReturnMeta;
import org.github.foxnic.web.domain.hrm.Employee;
import org.github.foxnic.web.domain.hrm.Organization;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IAssetCollectionReturnService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2021-09-20 17:01:53
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("EamAssetCollectionReturnController")
public class AssetCollectionReturnController extends SuperController implements BpmCallbackController {

    @Autowired
    private IAssetCollectionReturnService assetCollectionReturnService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "480667671264768000"),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.USE_ORGANIZATION_ID, value = "???????????????/??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.POSITION_ID, value = "???????????????", required = false, dataTypeClass = String.class, example = "473623769873645568"),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.POSITION_DETAIL, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.RETURN_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = "2021-08-02 12:00:00"),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = AssetCollectionReturnServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetCollectionReturnServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(AssetCollectionReturnVO assetCollectionReturnVO) {
        return assetCollectionReturnService.insert(assetCollectionReturnVO);
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "480667671264768000")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = AssetCollectionReturnServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetCollectionReturnServiceProxy.DELETE)
    public Result deleteById(String id) {
        AssetCollectionReturn assetCollectionReturn = assetCollectionReturnService.getById(id);
        if (AssetHandleStatusEnum.CANCEL.code().equals(assetCollectionReturn.getStatus()) || AssetHandleStatusEnum.INCOMPLETE.code().equals(assetCollectionReturn.getStatus())) {
            Result result = assetCollectionReturnService.deleteByIdLogical(id);
            return result;
        } else {
            return ErrorDesc.failure().message("???????????????????????????");
        }
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetCollectionReturnServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetCollectionReturnServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = assetCollectionReturnService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "480667671264768000"),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.USE_ORGANIZATION_ID, value = "???????????????/??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.POSITION_ID, value = "???????????????", required = false, dataTypeClass = String.class, example = "473623769873645568"),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.POSITION_DETAIL, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.RETURN_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = "2021-08-02 12:00:00"),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { AssetCollectionReturnVOMeta.PAGE_INDEX, AssetCollectionReturnVOMeta.PAGE_SIZE, AssetCollectionReturnVOMeta.SEARCH_FIELD, AssetCollectionReturnVOMeta.FUZZY_FIELD, AssetCollectionReturnVOMeta.SEARCH_VALUE, AssetCollectionReturnVOMeta.SORT_FIELD, AssetCollectionReturnVOMeta.SORT_TYPE, AssetCollectionReturnVOMeta.IDS })
    @SentinelResource(value = AssetCollectionReturnServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetCollectionReturnServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(AssetCollectionReturnVO assetCollectionReturnVO) {
        AssetCollectionReturn assetCollectionReturn = assetCollectionReturnService.getById(assetCollectionReturnVO.getId());
        if (AssetHandleStatusEnum.COMPLETE.code().equals(assetCollectionReturn.getStatus()) || AssetHandleStatusEnum.APPROVAL.code().equals(assetCollectionReturn.getStatus())) {
            return ErrorDesc.failure().message("???????????????????????????");
        }
        Result result = assetCollectionReturnService.update(assetCollectionReturnVO, SaveMode.NOT_NULL_FIELDS);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "480667671264768000"),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.USE_ORGANIZATION_ID, value = "???????????????/??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.POSITION_ID, value = "???????????????", required = false, dataTypeClass = String.class, example = "473623769873645568"),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.POSITION_DETAIL, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.RETURN_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = "2021-08-02 12:00:00"),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetCollectionReturnVOMeta.PAGE_INDEX, AssetCollectionReturnVOMeta.PAGE_SIZE, AssetCollectionReturnVOMeta.SEARCH_FIELD, AssetCollectionReturnVOMeta.FUZZY_FIELD, AssetCollectionReturnVOMeta.SEARCH_VALUE, AssetCollectionReturnVOMeta.SORT_FIELD, AssetCollectionReturnVOMeta.SORT_TYPE, AssetCollectionReturnVOMeta.IDS })
    @SentinelResource(value = AssetCollectionReturnServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetCollectionReturnServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(AssetCollectionReturnVO assetCollectionReturnVO) {
        Result result = assetCollectionReturnService.save(assetCollectionReturnVO, SaveMode.NOT_NULL_FIELDS);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = AssetCollectionReturnServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetCollectionReturnServiceProxy.GET_BY_ID)
    public Result<AssetCollectionReturn> getById(String id) {
        Result<AssetCollectionReturn> result = new Result<>();
        AssetCollectionReturn assetCollectionReturn = assetCollectionReturnService.getById(id);
        // ????????? ???????????????/?????? ??????
        assetCollectionReturnService.join(assetCollectionReturn, AssetCollectionReturnMeta.USE_ORGANIZATION);
        // ????????? ???????????? ??????
        assetCollectionReturnService.join(assetCollectionReturn, AssetCollectionReturnMeta.POSITION);
        // ????????? ????????? ??????
        assetCollectionReturnService.join(assetCollectionReturn, AssetCollectionReturnMeta.ORIGINATOR);
        assetCollectionReturnService.dao().join(assetCollectionReturn.getOriginator(), Person.class);
        result.success(true).data(assetCollectionReturn);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetCollectionReturnServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetCollectionReturnServiceProxy.GET_BY_IDS)
    public Result<List<AssetCollectionReturn>> getByIds(List<String> ids) {
        Result<List<AssetCollectionReturn>> result = new Result<>();
        List<AssetCollectionReturn> list = assetCollectionReturnService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "480667671264768000"),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.USE_ORGANIZATION_ID, value = "???????????????/??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.POSITION_ID, value = "???????????????", required = false, dataTypeClass = String.class, example = "473623769873645568"),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.POSITION_DETAIL, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.RETURN_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = "2021-08-02 12:00:00"),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetCollectionReturnVOMeta.PAGE_INDEX, AssetCollectionReturnVOMeta.PAGE_SIZE })
    @SentinelResource(value = AssetCollectionReturnServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetCollectionReturnServiceProxy.QUERY_LIST)
    public Result<List<AssetCollectionReturn>> queryList(AssetCollectionReturnVO sample) {
        Result<List<AssetCollectionReturn>> result = new Result<>();
        List<AssetCollectionReturn> list = assetCollectionReturnService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "480667671264768000"),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.PROC_ID, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.STATUS, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.USE_ORGANIZATION_ID, value = "???????????????/??????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.POSITION_ID, value = "???????????????", required = false, dataTypeClass = String.class, example = "473623769873645568"),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.POSITION_DETAIL, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.RETURN_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = "2021-08-02 12:00:00"),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.ATTACH, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.SELECTED_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = AssetCollectionReturnServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetCollectionReturnServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<AssetCollectionReturn>> queryPagedList(AssetCollectionReturnVO sample) {
        Result<PagedList<AssetCollectionReturn>> result = new Result<>();
        PagedList<AssetCollectionReturn> list = assetCollectionReturnService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // ????????? ???????????????/?????? ??????
        assetCollectionReturnService.join(list, AssetCollectionReturnMeta.USE_ORGANIZATION);
        // ????????? ???????????? ??????
        assetCollectionReturnService.join(list, AssetCollectionReturnMeta.POSITION);
        // ????????? ????????? ??????
        assetCollectionReturnService.join(list, AssetCollectionReturnMeta.ORIGINATOR);
        result.success(true).data(list);
        List<Employee> employees = CollectorUtil.collectList(list, AssetCollectionReturn::getOriginator);
        assetCollectionReturnService.dao().join(employees, Person.class);
        return result;
    }

    /**
     * ??????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetCollectionReturnVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 13)
    @SentinelResource(value = AssetCollectionReturnServiceProxy.CONFIRM_OPERATION, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetCollectionReturnServiceProxy.CONFIRM_OPERATION)
    public Result confirmOperation(String id) {
        return assetCollectionReturnService.confirmOperation(id);
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = AssetCollectionReturnServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetCollectionReturnServiceProxy.EXPORT_EXCEL)
    public void exportExcel(AssetCollectionReturnVO sample, HttpServletResponse response) throws Exception {
        // ?????? Excel ??????
        ExcelWriter ew = assetCollectionReturnService.exportExcel(sample);
        // ??????
        DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = AssetCollectionReturnServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetCollectionReturnServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        // ?????? Excel ??????
        ExcelWriter ew = assetCollectionReturnService.exportExcelTemplate();
        // ??????
        DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
    }

    /**
     *  ??????????????????
     */
    @SentinelResource(value = AssetCollectionReturnServiceProxy.BPM_CALLBACK, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetCollectionReturnServiceProxy.BPM_CALLBACK)
    public BpmActionResult onProcessCallback(BpmEvent event) {
        return assetCollectionReturnService.onProcessCallback(event);
    }

    @SentinelResource(value = AssetCollectionReturnServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetCollectionReturnServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = assetCollectionReturnService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
