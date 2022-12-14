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
import com.dt.platform.proxy.eam.ResidualStrategyServiceProxy;
import com.dt.platform.domain.eam.meta.ResidualStrategyVOMeta;
import com.dt.platform.domain.eam.ResidualStrategy;
import com.dt.platform.domain.eam.ResidualStrategyVO;
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
import com.dt.platform.domain.eam.meta.ResidualStrategyMeta;
import java.math.BigDecimal;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IResidualStrategyService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2021-10-26 15:28:04
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("EamResidualStrategyController")
public class ResidualStrategyController extends SuperController {

    @Autowired
    private IResidualStrategyService residualStrategyService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ResidualStrategyVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "477868649709830144"),
		@ApiImplicitParam(name = ResidualStrategyVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = ResidualStrategyVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = ResidualStrategyVOMeta.STRATEGY_DESCRIBE, value = "????????????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = ResidualStrategyVOMeta.RESIDUALVALUE_RATE, value = "?????????", required = false, dataTypeClass = BigDecimal.class, example = "2.00"),
		@ApiImplicitParam(name = ResidualStrategyVOMeta.DEPRECIATION_RATE, value = "?????????", required = false, dataTypeClass = BigDecimal.class, example = "2.00"),
		@ApiImplicitParam(name = ResidualStrategyVOMeta.VALUE, value = "?????????", required = false, dataTypeClass = String.class, example = "4"),
		@ApiImplicitParam(name = ResidualStrategyVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "????????????")
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = ResidualStrategyServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ResidualStrategyServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(ResidualStrategyVO residualStrategyVO) {
        Result result = residualStrategyService.insert(residualStrategyVO);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ResidualStrategyVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "477868649709830144")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = ResidualStrategyServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ResidualStrategyServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = residualStrategyService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ResidualStrategyVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = ResidualStrategyServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ResidualStrategyServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = residualStrategyService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ResidualStrategyVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "477868649709830144"),
		@ApiImplicitParam(name = ResidualStrategyVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = ResidualStrategyVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = ResidualStrategyVOMeta.STRATEGY_DESCRIBE, value = "????????????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = ResidualStrategyVOMeta.RESIDUALVALUE_RATE, value = "?????????", required = false, dataTypeClass = BigDecimal.class, example = "2.00"),
		@ApiImplicitParam(name = ResidualStrategyVOMeta.DEPRECIATION_RATE, value = "?????????", required = false, dataTypeClass = BigDecimal.class, example = "2.00"),
		@ApiImplicitParam(name = ResidualStrategyVOMeta.VALUE, value = "?????????", required = false, dataTypeClass = String.class, example = "4"),
		@ApiImplicitParam(name = ResidualStrategyVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "????????????")
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { ResidualStrategyVOMeta.PAGE_INDEX, ResidualStrategyVOMeta.PAGE_SIZE, ResidualStrategyVOMeta.SEARCH_FIELD, ResidualStrategyVOMeta.FUZZY_FIELD, ResidualStrategyVOMeta.SEARCH_VALUE, ResidualStrategyVOMeta.SORT_FIELD, ResidualStrategyVOMeta.SORT_TYPE, ResidualStrategyVOMeta.IDS })
    @SentinelResource(value = ResidualStrategyServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ResidualStrategyServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(ResidualStrategyVO residualStrategyVO) {
        Result result = residualStrategyService.update(residualStrategyVO, SaveMode.NOT_NULL_FIELDS);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ResidualStrategyVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "477868649709830144"),
		@ApiImplicitParam(name = ResidualStrategyVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = ResidualStrategyVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = ResidualStrategyVOMeta.STRATEGY_DESCRIBE, value = "????????????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = ResidualStrategyVOMeta.RESIDUALVALUE_RATE, value = "?????????", required = false, dataTypeClass = BigDecimal.class, example = "2.00"),
		@ApiImplicitParam(name = ResidualStrategyVOMeta.DEPRECIATION_RATE, value = "?????????", required = false, dataTypeClass = BigDecimal.class, example = "2.00"),
		@ApiImplicitParam(name = ResidualStrategyVOMeta.VALUE, value = "?????????", required = false, dataTypeClass = String.class, example = "4"),
		@ApiImplicitParam(name = ResidualStrategyVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "????????????")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { ResidualStrategyVOMeta.PAGE_INDEX, ResidualStrategyVOMeta.PAGE_SIZE, ResidualStrategyVOMeta.SEARCH_FIELD, ResidualStrategyVOMeta.FUZZY_FIELD, ResidualStrategyVOMeta.SEARCH_VALUE, ResidualStrategyVOMeta.SORT_FIELD, ResidualStrategyVOMeta.SORT_TYPE, ResidualStrategyVOMeta.IDS })
    @SentinelResource(value = ResidualStrategyServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ResidualStrategyServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(ResidualStrategyVO residualStrategyVO) {
        Result result = residualStrategyService.save(residualStrategyVO, SaveMode.NOT_NULL_FIELDS);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ResidualStrategyVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = ResidualStrategyServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ResidualStrategyServiceProxy.GET_BY_ID)
    public Result<ResidualStrategy> getById(String id) {
        Result<ResidualStrategy> result = new Result<>();
        ResidualStrategy residualStrategy = residualStrategyService.getById(id);
        // join ???????????????
        residualStrategyService.dao().fill(residualStrategy).execute();
        result.success(true).data(residualStrategy);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ResidualStrategyVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = ResidualStrategyServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ResidualStrategyServiceProxy.GET_BY_IDS)
    public Result<List<ResidualStrategy>> getByIds(List<String> ids) {
        Result<List<ResidualStrategy>> result = new Result<>();
        List<ResidualStrategy> list = residualStrategyService.getByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ResidualStrategyVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "477868649709830144"),
		@ApiImplicitParam(name = ResidualStrategyVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = ResidualStrategyVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = ResidualStrategyVOMeta.STRATEGY_DESCRIBE, value = "????????????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = ResidualStrategyVOMeta.RESIDUALVALUE_RATE, value = "?????????", required = false, dataTypeClass = BigDecimal.class, example = "2.00"),
		@ApiImplicitParam(name = ResidualStrategyVOMeta.DEPRECIATION_RATE, value = "?????????", required = false, dataTypeClass = BigDecimal.class, example = "2.00"),
		@ApiImplicitParam(name = ResidualStrategyVOMeta.VALUE, value = "?????????", required = false, dataTypeClass = String.class, example = "4"),
		@ApiImplicitParam(name = ResidualStrategyVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "????????????")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { ResidualStrategyVOMeta.PAGE_INDEX, ResidualStrategyVOMeta.PAGE_SIZE })
    @SentinelResource(value = ResidualStrategyServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ResidualStrategyServiceProxy.QUERY_LIST)
    public Result<List<ResidualStrategy>> queryList(ResidualStrategyVO sample) {
        Result<List<ResidualStrategy>> result = new Result<>();
        List<ResidualStrategy> list = residualStrategyService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ResidualStrategyVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "477868649709830144"),
		@ApiImplicitParam(name = ResidualStrategyVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = ResidualStrategyVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = ResidualStrategyVOMeta.STRATEGY_DESCRIBE, value = "????????????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = ResidualStrategyVOMeta.RESIDUALVALUE_RATE, value = "?????????", required = false, dataTypeClass = BigDecimal.class, example = "2.00"),
		@ApiImplicitParam(name = ResidualStrategyVOMeta.DEPRECIATION_RATE, value = "?????????", required = false, dataTypeClass = BigDecimal.class, example = "2.00"),
		@ApiImplicitParam(name = ResidualStrategyVOMeta.VALUE, value = "?????????", required = false, dataTypeClass = String.class, example = "4"),
		@ApiImplicitParam(name = ResidualStrategyVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "????????????")
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = ResidualStrategyServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ResidualStrategyServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<ResidualStrategy>> queryPagedList(ResidualStrategyVO sample) {
        Result<PagedList<ResidualStrategy>> result = new Result<>();
        PagedList<ResidualStrategy> list = residualStrategyService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        residualStrategyService.dao().fill(list).execute();
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = ResidualStrategyServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(ResidualStrategyServiceProxy.EXPORT_EXCEL)
    public void exportExcel(ResidualStrategyVO sample, HttpServletResponse response) throws Exception {
        // ?????? Excel ??????
        ExcelWriter ew = residualStrategyService.exportExcel(sample);
        // ??????
        DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = ResidualStrategyServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(ResidualStrategyServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        // ?????? Excel ??????
        ExcelWriter ew = residualStrategyService.exportExcelTemplate();
        // ??????
        DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
    }

    @SentinelResource(value = ResidualStrategyServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(ResidualStrategyServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = residualStrategyService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
