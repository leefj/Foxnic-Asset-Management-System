package com.dt.platform.ops.controller;

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
import com.dt.platform.proxy.ops.IpAddressRangeServiceProxy;
import com.dt.platform.domain.ops.meta.IpAddressRangeVOMeta;
import com.dt.platform.domain.ops.IpAddressRange;
import com.dt.platform.domain.ops.IpAddressRangeVO;
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
import com.dt.platform.domain.ops.meta.IpAddressRangeMeta;
import org.github.foxnic.web.domain.system.DictItem;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.IIpAddressRangeService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * IP????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-07-30 07:16:58
 */
@Api(tags = "IP?????????")
@ApiSort(0)
@RestController("OpsIpAddressRangeController")
public class IpAddressRangeController extends SuperController {

    @Autowired
    private IIpAddressRangeService ipAddressRangeService;

    /**
     * ??????IP?????????
     */
    @ApiOperation(value = "??????IP?????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = IpAddressRangeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "605516543861719040"),
		@ApiImplicitParam(name = IpAddressRangeVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = IpAddressRangeVOMeta.ADDRESS_SEGMENT, value = "?????????", required = false, dataTypeClass = String.class, example = "10.224.18.0/24"),
		@ApiImplicitParam(name = IpAddressRangeVOMeta.REGION_ID, value = "??????", required = false, dataTypeClass = String.class, example = "dc"),
		@ApiImplicitParam(name = IpAddressRangeVOMeta.CLASS_ID, value = "??????", required = false, dataTypeClass = String.class, example = "admin_network"),
		@ApiImplicitParam(name = IpAddressRangeVOMeta.PROD_ID, value = "??????", required = false, dataTypeClass = String.class, example = "prod"),
		@ApiImplicitParam(name = IpAddressRangeVOMeta.SCENE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = IpAddressRangeVOMeta.VLAN, value = "VLAN", required = false, dataTypeClass = String.class, example = "100"),
		@ApiImplicitParam(name = IpAddressRangeVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = IpAddressRangeServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(IpAddressRangeServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(IpAddressRangeVO ipAddressRangeVO) {
        Result result = ipAddressRangeService.insert(ipAddressRangeVO, false);
        return result;
    }

    /**
     * ??????IP?????????
     */
    @ApiOperation(value = "??????IP?????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = IpAddressRangeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "605516543861719040")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = IpAddressRangeServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(IpAddressRangeServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  ipAddressRangeService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = ipAddressRangeService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ????????????IP????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????IP?????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = IpAddressRangeVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = IpAddressRangeServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(IpAddressRangeServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = ipAddressRangeService.hasRefers(ids);
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
            Result result = ipAddressRangeService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = ipAddressRangeService.deleteByIdsLogical(canDeleteIds);
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
     * ??????IP?????????
     */
    @ApiOperation(value = "??????IP?????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = IpAddressRangeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "605516543861719040"),
		@ApiImplicitParam(name = IpAddressRangeVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = IpAddressRangeVOMeta.ADDRESS_SEGMENT, value = "?????????", required = false, dataTypeClass = String.class, example = "10.224.18.0/24"),
		@ApiImplicitParam(name = IpAddressRangeVOMeta.REGION_ID, value = "??????", required = false, dataTypeClass = String.class, example = "dc"),
		@ApiImplicitParam(name = IpAddressRangeVOMeta.CLASS_ID, value = "??????", required = false, dataTypeClass = String.class, example = "admin_network"),
		@ApiImplicitParam(name = IpAddressRangeVOMeta.PROD_ID, value = "??????", required = false, dataTypeClass = String.class, example = "prod"),
		@ApiImplicitParam(name = IpAddressRangeVOMeta.SCENE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = IpAddressRangeVOMeta.VLAN, value = "VLAN", required = false, dataTypeClass = String.class, example = "100"),
		@ApiImplicitParam(name = IpAddressRangeVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { IpAddressRangeVOMeta.PAGE_INDEX, IpAddressRangeVOMeta.PAGE_SIZE, IpAddressRangeVOMeta.SEARCH_FIELD, IpAddressRangeVOMeta.FUZZY_FIELD, IpAddressRangeVOMeta.SEARCH_VALUE, IpAddressRangeVOMeta.DIRTY_FIELDS, IpAddressRangeVOMeta.SORT_FIELD, IpAddressRangeVOMeta.SORT_TYPE, IpAddressRangeVOMeta.IDS })
    @SentinelResource(value = IpAddressRangeServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(IpAddressRangeServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(IpAddressRangeVO ipAddressRangeVO) {
        Result result = ipAddressRangeService.update(ipAddressRangeVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????IP?????????
     */
    @ApiOperation(value = "??????IP?????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = IpAddressRangeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "605516543861719040"),
		@ApiImplicitParam(name = IpAddressRangeVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = IpAddressRangeVOMeta.ADDRESS_SEGMENT, value = "?????????", required = false, dataTypeClass = String.class, example = "10.224.18.0/24"),
		@ApiImplicitParam(name = IpAddressRangeVOMeta.REGION_ID, value = "??????", required = false, dataTypeClass = String.class, example = "dc"),
		@ApiImplicitParam(name = IpAddressRangeVOMeta.CLASS_ID, value = "??????", required = false, dataTypeClass = String.class, example = "admin_network"),
		@ApiImplicitParam(name = IpAddressRangeVOMeta.PROD_ID, value = "??????", required = false, dataTypeClass = String.class, example = "prod"),
		@ApiImplicitParam(name = IpAddressRangeVOMeta.SCENE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = IpAddressRangeVOMeta.VLAN, value = "VLAN", required = false, dataTypeClass = String.class, example = "100"),
		@ApiImplicitParam(name = IpAddressRangeVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { IpAddressRangeVOMeta.PAGE_INDEX, IpAddressRangeVOMeta.PAGE_SIZE, IpAddressRangeVOMeta.SEARCH_FIELD, IpAddressRangeVOMeta.FUZZY_FIELD, IpAddressRangeVOMeta.SEARCH_VALUE, IpAddressRangeVOMeta.DIRTY_FIELDS, IpAddressRangeVOMeta.SORT_FIELD, IpAddressRangeVOMeta.SORT_TYPE, IpAddressRangeVOMeta.IDS })
    @SentinelResource(value = IpAddressRangeServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(IpAddressRangeServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(IpAddressRangeVO ipAddressRangeVO) {
        Result result = ipAddressRangeService.save(ipAddressRangeVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????IP?????????
     */
    @ApiOperation(value = "??????IP?????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = IpAddressRangeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = IpAddressRangeServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(IpAddressRangeServiceProxy.GET_BY_ID)
    public Result<IpAddressRange> getById(String id) {
        Result<IpAddressRange> result = new Result<>();
        IpAddressRange ipAddressRange = ipAddressRangeService.getById(id);
        // join ???????????????
        ipAddressRangeService.dao().fill(ipAddressRange).with(IpAddressRangeMeta.REGION_DICT).with(IpAddressRangeMeta.PROD_DICT).with(IpAddressRangeMeta.CLASS_DICT).execute();
        result.success(true).data(ipAddressRange);
        return result;
    }

    /**
     * ????????????IP????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????IP?????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = IpAddressRangeVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = IpAddressRangeServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(IpAddressRangeServiceProxy.GET_BY_IDS)
    public Result<List<IpAddressRange>> getByIds(List<String> ids) {
        Result<List<IpAddressRange>> result = new Result<>();
        List<IpAddressRange> list = ipAddressRangeService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????IP?????????
     */
    @ApiOperation(value = "??????IP?????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = IpAddressRangeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "605516543861719040"),
		@ApiImplicitParam(name = IpAddressRangeVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = IpAddressRangeVOMeta.ADDRESS_SEGMENT, value = "?????????", required = false, dataTypeClass = String.class, example = "10.224.18.0/24"),
		@ApiImplicitParam(name = IpAddressRangeVOMeta.REGION_ID, value = "??????", required = false, dataTypeClass = String.class, example = "dc"),
		@ApiImplicitParam(name = IpAddressRangeVOMeta.CLASS_ID, value = "??????", required = false, dataTypeClass = String.class, example = "admin_network"),
		@ApiImplicitParam(name = IpAddressRangeVOMeta.PROD_ID, value = "??????", required = false, dataTypeClass = String.class, example = "prod"),
		@ApiImplicitParam(name = IpAddressRangeVOMeta.SCENE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = IpAddressRangeVOMeta.VLAN, value = "VLAN", required = false, dataTypeClass = String.class, example = "100"),
		@ApiImplicitParam(name = IpAddressRangeVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { IpAddressRangeVOMeta.PAGE_INDEX, IpAddressRangeVOMeta.PAGE_SIZE })
    @SentinelResource(value = IpAddressRangeServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(IpAddressRangeServiceProxy.QUERY_LIST)
    public Result<List<IpAddressRange>> queryList(IpAddressRangeVO sample) {
        Result<List<IpAddressRange>> result = new Result<>();
        List<IpAddressRange> list = ipAddressRangeService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????IP?????????
     */
    @ApiOperation(value = "????????????IP?????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = IpAddressRangeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "605516543861719040"),
		@ApiImplicitParam(name = IpAddressRangeVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = IpAddressRangeVOMeta.ADDRESS_SEGMENT, value = "?????????", required = false, dataTypeClass = String.class, example = "10.224.18.0/24"),
		@ApiImplicitParam(name = IpAddressRangeVOMeta.REGION_ID, value = "??????", required = false, dataTypeClass = String.class, example = "dc"),
		@ApiImplicitParam(name = IpAddressRangeVOMeta.CLASS_ID, value = "??????", required = false, dataTypeClass = String.class, example = "admin_network"),
		@ApiImplicitParam(name = IpAddressRangeVOMeta.PROD_ID, value = "??????", required = false, dataTypeClass = String.class, example = "prod"),
		@ApiImplicitParam(name = IpAddressRangeVOMeta.SCENE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = IpAddressRangeVOMeta.VLAN, value = "VLAN", required = false, dataTypeClass = String.class, example = "100"),
		@ApiImplicitParam(name = IpAddressRangeVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = IpAddressRangeServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(IpAddressRangeServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<IpAddressRange>> queryPagedList(IpAddressRangeVO sample) {
        Result<PagedList<IpAddressRange>> result = new Result<>();
        PagedList<IpAddressRange> list = ipAddressRangeService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        ipAddressRangeService.dao().fill(list).with(IpAddressRangeMeta.REGION_DICT).with(IpAddressRangeMeta.PROD_DICT).with(IpAddressRangeMeta.CLASS_DICT).execute();
        result.success(true).data(list);
        return result;
    }
}
