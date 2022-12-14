package com.dt.platform.eam.controller;

import java.util.List;
import java.util.ArrayList;
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
import com.dt.platform.proxy.eam.ManufacturerServiceProxy;
import com.dt.platform.domain.eam.meta.ManufacturerVOMeta;
import com.dt.platform.domain.eam.Manufacturer;
import com.dt.platform.domain.eam.ManufacturerVO;
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
import com.dt.platform.domain.eam.meta.ManufacturerMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IManufacturerService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-08-30 22:05:30
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("EamManufacturerController")
public class ManufacturerController extends SuperController {

    @Autowired
    private IManufacturerService manufacturerService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ManufacturerVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "471669992140570624"),
		@ApiImplicitParam(name = ManufacturerVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ManufacturerVOMeta.MANUFACTURER_NAME, value = "??????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = ManufacturerVOMeta.LOCATION, value = "?????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = ManufacturerVOMeta.MANUFACTURER_NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = ManufacturerServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ManufacturerServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(ManufacturerVO manufacturerVO) {
        Result result = manufacturerService.insert(manufacturerVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ManufacturerVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "471669992140570624")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = ManufacturerServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ManufacturerServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  manufacturerService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = manufacturerService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ManufacturerVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = ManufacturerServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ManufacturerServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = manufacturerService.hasRefers(ids);
        // ?????????????????????ID???
        List<String> canDeleteIds = new ArrayList<>();
        for (Map.Entry<String, ReferCause> e : causeMap.entrySet()) {
            if (!e.getValue().hasRefer()) {
                canDeleteIds.add(e.getKey());
            }
        }
        // ????????????
        if (canDeleteIds.isEmpty()) {
            // ?????????????????????????????????
            return ErrorDesc.failure().message("????????????????????????????????????").data(0)
				.addErrors(CollectorUtil.collectArray(CollectorUtil.filter(causeMap.values(),(e)->{return e.hasRefer();}),ReferCause::message,String.class))
				.messageLevel4Confirm();
        } else if (canDeleteIds.size() == ids.size()) {
            // ????????????????????????
            Result result = manufacturerService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = manufacturerService.deleteByIdsLogical(canDeleteIds);
            if (result.failure()) {
                return result;
            } else {
                return ErrorDesc.success().message("????????? " + canDeleteIds.size() + " ??????????????? " + (ids.size() - canDeleteIds.size()) + " ?????????????????????").data(canDeleteIds.size())
					.addErrors(CollectorUtil.collectArray(CollectorUtil.filter(causeMap.values(),(e)->{return e.hasRefer();}),ReferCause::message,String.class))
					.messageLevel4Confirm();
            }
        } else {
            // ?????????????????????????????????
            return ErrorDesc.success().message("?????????????????????");
        }
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ManufacturerVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "471669992140570624"),
		@ApiImplicitParam(name = ManufacturerVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ManufacturerVOMeta.MANUFACTURER_NAME, value = "??????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = ManufacturerVOMeta.LOCATION, value = "?????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = ManufacturerVOMeta.MANUFACTURER_NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { ManufacturerVOMeta.PAGE_INDEX, ManufacturerVOMeta.PAGE_SIZE, ManufacturerVOMeta.SEARCH_FIELD, ManufacturerVOMeta.FUZZY_FIELD, ManufacturerVOMeta.SEARCH_VALUE, ManufacturerVOMeta.DIRTY_FIELDS, ManufacturerVOMeta.SORT_FIELD, ManufacturerVOMeta.SORT_TYPE, ManufacturerVOMeta.IDS })
    @SentinelResource(value = ManufacturerServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ManufacturerServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(ManufacturerVO manufacturerVO) {
        Result result = manufacturerService.update(manufacturerVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ManufacturerVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "471669992140570624"),
		@ApiImplicitParam(name = ManufacturerVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ManufacturerVOMeta.MANUFACTURER_NAME, value = "??????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = ManufacturerVOMeta.LOCATION, value = "?????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = ManufacturerVOMeta.MANUFACTURER_NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { ManufacturerVOMeta.PAGE_INDEX, ManufacturerVOMeta.PAGE_SIZE, ManufacturerVOMeta.SEARCH_FIELD, ManufacturerVOMeta.FUZZY_FIELD, ManufacturerVOMeta.SEARCH_VALUE, ManufacturerVOMeta.DIRTY_FIELDS, ManufacturerVOMeta.SORT_FIELD, ManufacturerVOMeta.SORT_TYPE, ManufacturerVOMeta.IDS })
    @SentinelResource(value = ManufacturerServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ManufacturerServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(ManufacturerVO manufacturerVO) {
        Result result = manufacturerService.save(manufacturerVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ManufacturerVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = ManufacturerServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ManufacturerServiceProxy.GET_BY_ID)
    public Result<Manufacturer> getById(String id) {
        Result<Manufacturer> result = new Result<>();
        Manufacturer manufacturer = manufacturerService.getById(id);
        result.success(true).data(manufacturer);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ManufacturerVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = ManufacturerServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ManufacturerServiceProxy.GET_BY_IDS)
    public Result<List<Manufacturer>> getByIds(List<String> ids) {
        Result<List<Manufacturer>> result = new Result<>();
        List<Manufacturer> list = manufacturerService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ManufacturerVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "471669992140570624"),
		@ApiImplicitParam(name = ManufacturerVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ManufacturerVOMeta.MANUFACTURER_NAME, value = "??????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = ManufacturerVOMeta.LOCATION, value = "?????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = ManufacturerVOMeta.MANUFACTURER_NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { ManufacturerVOMeta.PAGE_INDEX, ManufacturerVOMeta.PAGE_SIZE })
    @SentinelResource(value = ManufacturerServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ManufacturerServiceProxy.QUERY_LIST)
    public Result<List<Manufacturer>> queryList(ManufacturerVO sample) {
        Result<List<Manufacturer>> result = new Result<>();
        List<Manufacturer> list = manufacturerService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ManufacturerVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "471669992140570624"),
		@ApiImplicitParam(name = ManufacturerVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ManufacturerVOMeta.MANUFACTURER_NAME, value = "??????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = ManufacturerVOMeta.LOCATION, value = "?????????", required = false, dataTypeClass = String.class, example = "??????"),
		@ApiImplicitParam(name = ManufacturerVOMeta.MANUFACTURER_NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = ManufacturerServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ManufacturerServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<Manufacturer>> queryPagedList(ManufacturerVO sample) {
        Result<PagedList<Manufacturer>> result = new Result<>();
        PagedList<Manufacturer> list = manufacturerService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }
}
