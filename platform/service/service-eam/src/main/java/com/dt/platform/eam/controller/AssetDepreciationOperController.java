package com.dt.platform.eam.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.List;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.deepoove.poi.util.PoitlIOUtils;
import com.dt.platform.constants.enums.eam.AssetDepreciationStatusEnum;
import com.dt.platform.constants.enums.eam.AssetDetailDepreciationResultEnum;
import com.dt.platform.constants.enums.eam.AssetOperateEnum;
import com.dt.platform.constants.enums.ops.OpsOperateEnum;
import com.dt.platform.domain.eam.*;
import com.dt.platform.domain.eam.meta.AssetDepreciationDetailMeta;
import com.dt.platform.domain.eam.meta.AssetDepreciationDetailVOMeta;
import com.dt.platform.eam.service.IAssetDataService;
import com.dt.platform.eam.service.IAssetDepreciationDetailService;
import com.dt.platform.eam.service.IAssetService;
import com.dt.platform.proxy.common.TplFileServiceProxy;
import com.dt.platform.proxy.eam.AssetDepreciationDetailServiceProxy;
import com.dt.platform.proxy.eam.AssetServiceProxy;
import com.github.foxnic.commons.busi.id.IDGenerator;
import com.github.foxnic.commons.collection.CollectorUtil;
import com.github.foxnic.commons.lang.StringUtil;
import com.github.foxnic.dao.data.Rcd;
import com.github.foxnic.sql.expr.Insert;
import com.github.foxnic.sql.expr.Update;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Workbook;
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
import com.dt.platform.proxy.eam.AssetDepreciationOperServiceProxy;
import com.dt.platform.domain.eam.meta.AssetDepreciationOperVOMeta;
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
import com.dt.platform.domain.eam.meta.AssetDepreciationOperMeta;
import org.github.foxnic.web.domain.hrm.Employee;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IAssetDepreciationOperService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-05-03 14:47:45
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("EamAssetDepreciationOperController")
public class AssetDepreciationOperController extends SuperController {

    @Autowired
    private IAssetDepreciationOperService assetDepreciationOperService;

    @Autowired
    private IAssetDataService assetDataService;

    @Autowired
    private IAssetDepreciationDetailService assetDepreciationDetailService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.DEPRECIATION_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.EXECUTION_START_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.EXECUTION_END_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.DEPRECIATION_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = AssetDepreciationOperServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDepreciationOperServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(AssetDepreciationOperVO assetDepreciationOperVO) {
        Result result = assetDepreciationOperService.insert(assetDepreciationOperVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = AssetDepreciationOperServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDepreciationOperServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = assetDepreciationOperService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetDepreciationOperServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDepreciationOperServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = assetDepreciationOperService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.DEPRECIATION_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.EXECUTION_START_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.EXECUTION_END_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.DEPRECIATION_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { AssetDepreciationOperVOMeta.PAGE_INDEX, AssetDepreciationOperVOMeta.PAGE_SIZE, AssetDepreciationOperVOMeta.SEARCH_FIELD, AssetDepreciationOperVOMeta.FUZZY_FIELD, AssetDepreciationOperVOMeta.SEARCH_VALUE, AssetDepreciationOperVOMeta.DIRTY_FIELDS, AssetDepreciationOperVOMeta.SORT_FIELD, AssetDepreciationOperVOMeta.SORT_TYPE, AssetDepreciationOperVOMeta.IDS })
    @SentinelResource(value = AssetDepreciationOperServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDepreciationOperServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(AssetDepreciationOperVO assetDepreciationOperVO) {
        Result result = assetDepreciationOperService.update(assetDepreciationOperVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.DEPRECIATION_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.EXECUTION_START_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.EXECUTION_END_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.DEPRECIATION_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetDepreciationOperVOMeta.PAGE_INDEX, AssetDepreciationOperVOMeta.PAGE_SIZE, AssetDepreciationOperVOMeta.SEARCH_FIELD, AssetDepreciationOperVOMeta.FUZZY_FIELD, AssetDepreciationOperVOMeta.SEARCH_VALUE, AssetDepreciationOperVOMeta.DIRTY_FIELDS, AssetDepreciationOperVOMeta.SORT_FIELD, AssetDepreciationOperVOMeta.SORT_TYPE, AssetDepreciationOperVOMeta.IDS })
    @SentinelResource(value = AssetDepreciationOperServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDepreciationOperServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(AssetDepreciationOperVO assetDepreciationOperVO) {
        Result result = assetDepreciationOperService.save(assetDepreciationOperVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = AssetDepreciationOperServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDepreciationOperServiceProxy.GET_BY_ID)
    public Result<AssetDepreciationOper> getById(String id) {
        Result<AssetDepreciationOper> result = new Result<>();
        AssetDepreciationOper assetDepreciationOper = assetDepreciationOperService.getById(id);
        // join ???????????????
        assetDepreciationOperService.dao().fill(assetDepreciationOper).with("originator").with(AssetDepreciationOperMeta.ASSET_DEPRECIATION).execute();
        result.success(true).data(assetDepreciationOper);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetDepreciationOperServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDepreciationOperServiceProxy.GET_BY_IDS)
    public Result<List<AssetDepreciationOper>> getByIds(List<String> ids) {
        Result<List<AssetDepreciationOper>> result = new Result<>();
        List<AssetDepreciationOper> list = assetDepreciationOperService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.DEPRECIATION_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.EXECUTION_START_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.EXECUTION_END_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.DEPRECIATION_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetDepreciationOperVOMeta.PAGE_INDEX, AssetDepreciationOperVOMeta.PAGE_SIZE })
    @SentinelResource(value = AssetDepreciationOperServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDepreciationOperServiceProxy.QUERY_LIST)
    public Result<List<AssetDepreciationOper>> queryList(AssetDepreciationOperVO sample) {
        Result<List<AssetDepreciationOper>> result = new Result<>();
        List<AssetDepreciationOper> list = assetDepreciationOperService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.BUSINESS_CODE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.DEPRECIATION_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.CONTENT, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.EXECUTION_START_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.EXECUTION_END_TIME, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.ORIGINATOR_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.DEPRECIATION_CODE, value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = AssetDepreciationOperServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDepreciationOperServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<AssetDepreciationOper>> queryPagedList(AssetDepreciationOperVO sample) {
        Result<PagedList<AssetDepreciationOper>> result = new Result<>();
        PagedList<AssetDepreciationOper> list = assetDepreciationOperService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        assetDepreciationOperService.dao().fill(list).with("originator").with(AssetDepreciationOperMeta.ASSET_DEPRECIATION).execute();
        List<Employee> originatorList = CollectorUtil.collectList(list, AssetDepreciationOper::getOriginator);
        assetDepreciationOperService.dao().join(originatorList, Person.class);
        result.success(true).data(list);
        return result;
    }

    /**
     */
    @ApiOperation(value = "")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 9)
    @SentinelResource(value = AssetDepreciationOperServiceProxy.SYNCDATA, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDepreciationOperServiceProxy.SYNCDATA)
    public Result syncData(String id) {
        return assetDepreciationOperService.syncData(id);
    }

    /**
     */
    @ApiOperation(value = "")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 10)
    @SentinelResource(value = AssetDepreciationOperServiceProxy.ROLLBACK, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDepreciationOperServiceProxy.ROLLBACK)
    public Result rollback(String id) {
        return assetDepreciationOperService.rollback(id);
    }

    /**
     */
    @ApiOperation(value = "")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 11)
    @SentinelResource(value = AssetDepreciationOperServiceProxy.START, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDepreciationOperServiceProxy.START)
    public Result start(String id) {
        return assetDepreciationOperService.start(id);
    }

    /**
     */
    @ApiOperation(value = "")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetDepreciationOperVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 12)
    @SentinelResource(value = AssetDepreciationOperServiceProxy.EXECUTE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDepreciationOperServiceProxy.EXECUTE)
    public Result execute(String id) {
        return assetDepreciationOperService.execute(id);
    }


    /**
     */
    @ApiOperation(value = "")
    @ApiOperationSupport(order = 13)
    @SentinelResource(value = AssetDepreciationOperServiceProxy.ASSET_EXCLUDE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDepreciationOperServiceProxy.ASSET_EXCLUDE)
    public Result assetExclude(List<String> ids,String operId,String mark) {

        if(StringUtil.isBlank(operId)){
            return ErrorDesc.failureMessage("??????????????????");
        }
        if(StringUtil.isBlank(ids)){
            return ErrorDesc.failureMessage("?????????????????????");
        }
        AssetDepreciationOper assetDepreciationOper=assetDepreciationOperService.getById(operId);
        String depId=assetDepreciationOper.getDepreciationId();
        if(AssetDepreciationStatusEnum.ACTING.code().equals(assetDepreciationOper.getStatus())){

        }else{
            return ErrorDesc.failureMessage("????????????????????????????????????????????????");
        }

        if(StringUtil.isBlank(operId)){
            return ErrorDesc.failureMessage("??????????????????");
        }
        for(String id:ids){
            AssetDepreciationDetail assetDepreciationDetail=assetDepreciationDetailService.getById(id);
            //??????????????????????????????????????????
            Update ups=new Update("eam_asset_depreciation_detail");
            ups.set("result",AssetDetailDepreciationResultEnum.NOT_CALCULATE.code());
            ups.set("result_detail",mark==null?"":mark);
            ups.where().and("id=?",id);
            assetDepreciationOperService.dao().execute(ups);
            //???????????????????????????
            Rcd rs=assetDepreciationOperService.dao().queryRecord("select 1 from eam_asset_depreciation_exclude where depreciation_id=? and asset_id=?",depId,assetDepreciationDetail.getAssetId());
            if(rs==null){
                //add
                Insert ins=new Insert("eam_asset_depreciation_exclude");
                ins.set("id", IDGenerator.getSnowflakeIdString());
                ins.set("depreciation_id", depId);
                ins.set("asset_id", assetDepreciationDetail.getAssetId());
                ins.set("notes",mark==null?"":mark);
                assetDepreciationOperService.dao().execute(ins);
            }else{
                if(!StringUtil.isBlank(mark)){
                    Update ups1=new Update("eam_asset_depreciation_exclude");
                    ups1.set("notes",mark==null?"":mark);
                    ups1.where().and("depreciation_id=?",operId).and("asset_id=?",assetDepreciationDetail.getAssetId());
                    assetDepreciationOperService.dao().execute(ups1);
                }
            }
        }
        return ErrorDesc.success();
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = AssetDepreciationOperServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetDepreciationOperServiceProxy.EXPORT_EXCEL)
    public void exportExcel(AssetDepreciationOperVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = assetDepreciationOperService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = AssetDepreciationOperServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetDepreciationOperServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = assetDepreciationOperService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = AssetDepreciationOperServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetDepreciationOperServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = assetDepreciationOperService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = AssetDepreciationOperServiceProxy.ASSET_DOWNLOAD_DEPRECIATION_REPORT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetDepreciationOperServiceProxy.ASSET_DOWNLOAD_DEPRECIATION_REPORT)
    public Result assetDownloadDepreciationReport(String id, HttpServletResponse response) throws Exception {
        // ?????? Excel ??????
        String code = AssetOperateEnum.EAM_ASSET_DOWNLOAD_DEPRECIATION_REPORT.code();
        InputStream inputstream = TplFileServiceProxy.api().getTplFileStreamByCode(code);
        if (inputstream == null) {
            return ErrorDesc.failure().message("????????????????????????");
        }
        File f = assetDataService.saveTempFile(inputstream, "TMP_" + code + ".xls");
        Map<String, Object> map = assetDepreciationOperService.queryDepreciationAssetMap(id);
        TemplateExportParams templateExportParams = new TemplateExportParams(f.getPath());
        Workbook workbook = ExcelExportUtil.exportExcel(templateExportParams, map);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode("??????????????????.xls", "UTF-8"))));
        response.setContentType("application/vnd.ms-excel");
        DownloadUtil.writeDownloadSuccess(response);
        OutputStream out = response.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(out);
        workbook.write(bos);
        bos.flush();
        out.flush();
        PoitlIOUtils.closeQuietlyMulti(workbook, bos, out);
        return ErrorDesc.success();
    }

    /**
     * ????????????eam_asset_depreciation_detail
     */
    @ApiOperation(value = "???????????? eam_asset_depreciation_detail")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "648956390479495168"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.DEPRECIATION_ID, value = "????????????", required = false, dataTypeClass = String.class, example = "647736203386290176"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.OPER_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.FIRST_DEPRECIATION_METHOD, value = "??????????????????", required = false, dataTypeClass = String.class, example = "purchase_next_month"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.DEPRECIATION_METHOD, value = "????????????", required = false, dataTypeClass = String.class, example = "average_age"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.BUSINESS_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-11-25 12:00:00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT, value = "????????????", required = false, dataTypeClass = String.class, example = "wait_calculate"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.RESULT_DETAIL, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_ID, value = "??????", required = false, dataTypeClass = String.class, example = "648874943362105344"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_CATEGORY_NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_CODE, value = "????????????", required = false, dataTypeClass = String.class, example = "AS2022112609345"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "001"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_MODEL, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_STATUS_NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "idle"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_PURCHASE_DATE, value = "????????????", required = false, dataTypeClass = Date.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_REGISTER_DATE, value = "????????????", required = false, dataTypeClass = Date.class, example = "2022-11-25 12:00:00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_ORIGINAL_UNIT_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "7682.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_PURCHASE_UNIT_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_NAV_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_TAX_AMOUNT_RATE, value = "??????", required = false, dataTypeClass = BigDecimal.class, example = "2.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_SERVICE_LIFE, value = "???????????????", required = false, dataTypeClass = BigDecimal.class, example = "12.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_RESIDUALS_RATE, value = "???????????????", required = false, dataTypeClass = BigDecimal.class, example = "5.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ASSET_RESIDUALS_PRICE, value = "????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.S_ORIGINAL_PRICE, value = "(??????)????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.S_DEPRECIATION_AMOUNT, value = "(??????)??????????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.S_NAV_AMOUNT, value = "(??????)????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.S_RECOVERABLE_AMOUNT, value = "(??????)?????????????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.C_USED_SERVICE_LIFE, value = "???????????????", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.C_ORIGINAL_PRICE_INCREASE, value = "(????????????)????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.C_DEPRECIATION_AMOUNT, value = "(????????????)???????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.C_YEAR_DEPRECIATION_AMOUNT, value = "(????????????)?????????????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.E_ORIGINAL_PRICE, value = "(??????)????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.E_DEPRECIATION_AMOUNT, value = "(??????)??????????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.E_NAV_AMOUNT, value = "(??????)????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.E_RECOVERABLE_AMOUNT, value = "(??????)?????????????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.ACCOUNTING_SERVICE_LIFE, value = "???????????????????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.FIRST_DEPRECIATION, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.USE_USER_ID, value = "?????????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.USE_USER_NAME, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.USE_ORG_ID, value = "??????ID", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.USE_ORG_NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.FINANCIAL_OPTION_KEY, value = "????????????KEY", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.EXPENSE_ITEM_KEY, value = "????????????KEY", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.FINANCIAL_OPTION_NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.EXPENSE_ITEM_NAME, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.CUSTOMER_INFO, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.DETAIL_ID_SOURCE, value = "?????????", required = false, dataTypeClass = String.class, example = "648956390253002752"),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.DETAIL_ID_TARGET, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.LAST_OPER_ID, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetDepreciationDetailVOMeta.LAST_OPER_TIME, value = "??????????????????", required = false, dataTypeClass = Date.class)
	})
    @ApiOperationSupport(order = 8, author = "?????? , maillank@qq.com")
    @SentinelResource(value = AssetDepreciationOperServiceProxy.QUERY_ASSET_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetDepreciationOperServiceProxy.QUERY_ASSET_PAGED_LIST)
    public Result<PagedList<AssetDepreciationDetail>> queryAssetPagedList(AssetDepreciationDetailVO sample) {
        Result<PagedList<AssetDepreciationDetail>> result = new Result<>();
        //-- sample.setResult(AssetDetailDepreciationResultEnum.SUCCESS.code());
        PagedList<AssetDepreciationDetail> list = assetDepreciationDetailService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        assetDepreciationDetailService.dao().fill(list).with("useOrganization").with("useUser").with(AssetDepreciationDetailMeta.FINANCIAL_OPTION_DICT).with(AssetDepreciationDetailMeta.EXPENSE_ITEM_DICT).with(AssetDepreciationDetailMeta.ASSET_DEPRECIATION).with(AssetDepreciationDetailMeta.ASSET_DEPRECIATION_OPER).execute();
        result.success(true).data(list);
        return result;
    }
}
