package com.dt.platform.mobile.controller;

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
import com.dt.platform.proxy.mobile.ModuleInfoServiceProxy;
import com.dt.platform.domain.mobile.meta.ModuleInfoVOMeta;
import com.dt.platform.domain.mobile.ModuleInfo;
import com.dt.platform.domain.mobile.ModuleInfoVO;
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
import com.dt.platform.domain.mobile.meta.ModuleInfoMeta;
import com.dt.platform.domain.mobile.ModuleGroup;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.mobile.service.IModuleInfoService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ??????????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-07-20 22:31:33
 */
@Api(tags = "???????????????")
@ApiSort(0)
@RestController("AppModuleInfoController")
public class ModuleInfoController extends SuperController {

    @Autowired
    private IModuleInfoService moduleInfoService;

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ModuleInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = ModuleInfoVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "eam_mobile_asset_mgr_repair"),
		@ApiImplicitParam(name = ModuleInfoVOMeta.LABEL, value = "??????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = ModuleInfoVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = ModuleInfoVOMeta.GROUP_ID, value = "??????", required = false, dataTypeClass = String.class, example = "2"),
		@ApiImplicitParam(name = ModuleInfoVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "/static/functionIcon/setting.png"),
		@ApiImplicitParam(name = ModuleInfoVOMeta.PATH, value = "??????", required = false, dataTypeClass = String.class, example = "/pages/index/repair/repair"),
		@ApiImplicitParam(name = ModuleInfoVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ModuleInfoVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class, example = "0")
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = ModuleInfoServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ModuleInfoServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(ModuleInfoVO moduleInfoVO) {
        Result result = moduleInfoService.insert(moduleInfoVO, false);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ModuleInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = ModuleInfoServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ModuleInfoServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  moduleInfoService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = moduleInfoService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ??????????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "???????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ModuleInfoVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = ModuleInfoServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ModuleInfoServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = moduleInfoService.hasRefers(ids);
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
            Result result = moduleInfoService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = moduleInfoService.deleteByIdsLogical(canDeleteIds);
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
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ModuleInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = ModuleInfoVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "eam_mobile_asset_mgr_repair"),
		@ApiImplicitParam(name = ModuleInfoVOMeta.LABEL, value = "??????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = ModuleInfoVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = ModuleInfoVOMeta.GROUP_ID, value = "??????", required = false, dataTypeClass = String.class, example = "2"),
		@ApiImplicitParam(name = ModuleInfoVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "/static/functionIcon/setting.png"),
		@ApiImplicitParam(name = ModuleInfoVOMeta.PATH, value = "??????", required = false, dataTypeClass = String.class, example = "/pages/index/repair/repair"),
		@ApiImplicitParam(name = ModuleInfoVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ModuleInfoVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class, example = "0")
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { ModuleInfoVOMeta.PAGE_INDEX, ModuleInfoVOMeta.PAGE_SIZE, ModuleInfoVOMeta.SEARCH_FIELD, ModuleInfoVOMeta.FUZZY_FIELD, ModuleInfoVOMeta.SEARCH_VALUE, ModuleInfoVOMeta.DIRTY_FIELDS, ModuleInfoVOMeta.SORT_FIELD, ModuleInfoVOMeta.SORT_TYPE, ModuleInfoVOMeta.IDS })
    @SentinelResource(value = ModuleInfoServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ModuleInfoServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(ModuleInfoVO moduleInfoVO) {
        Result result = moduleInfoService.update(moduleInfoVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ModuleInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = ModuleInfoVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "eam_mobile_asset_mgr_repair"),
		@ApiImplicitParam(name = ModuleInfoVOMeta.LABEL, value = "??????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = ModuleInfoVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = ModuleInfoVOMeta.GROUP_ID, value = "??????", required = false, dataTypeClass = String.class, example = "2"),
		@ApiImplicitParam(name = ModuleInfoVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "/static/functionIcon/setting.png"),
		@ApiImplicitParam(name = ModuleInfoVOMeta.PATH, value = "??????", required = false, dataTypeClass = String.class, example = "/pages/index/repair/repair"),
		@ApiImplicitParam(name = ModuleInfoVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ModuleInfoVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class, example = "0")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { ModuleInfoVOMeta.PAGE_INDEX, ModuleInfoVOMeta.PAGE_SIZE, ModuleInfoVOMeta.SEARCH_FIELD, ModuleInfoVOMeta.FUZZY_FIELD, ModuleInfoVOMeta.SEARCH_VALUE, ModuleInfoVOMeta.DIRTY_FIELDS, ModuleInfoVOMeta.SORT_FIELD, ModuleInfoVOMeta.SORT_TYPE, ModuleInfoVOMeta.IDS })
    @SentinelResource(value = ModuleInfoServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ModuleInfoServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(ModuleInfoVO moduleInfoVO) {
        Result result = moduleInfoService.save(moduleInfoVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ModuleInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = ModuleInfoServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ModuleInfoServiceProxy.GET_BY_ID)
    public Result<ModuleInfo> getById(String id) {
        Result<ModuleInfo> result = new Result<>();
        ModuleInfo moduleInfo = moduleInfoService.getById(id);
        // join ???????????????
        moduleInfoService.dao().fill(moduleInfo).with(ModuleInfoMeta.MODULE_GROUP).execute();
        result.success(true).data(moduleInfo);
        return result;
    }

    /**
     * ??????????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "???????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ModuleInfoVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = ModuleInfoServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ModuleInfoServiceProxy.GET_BY_IDS)
    public Result<List<ModuleInfo>> getByIds(List<String> ids) {
        Result<List<ModuleInfo>> result = new Result<>();
        List<ModuleInfo> list = moduleInfoService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ModuleInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = ModuleInfoVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "eam_mobile_asset_mgr_repair"),
		@ApiImplicitParam(name = ModuleInfoVOMeta.LABEL, value = "??????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = ModuleInfoVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = ModuleInfoVOMeta.GROUP_ID, value = "??????", required = false, dataTypeClass = String.class, example = "2"),
		@ApiImplicitParam(name = ModuleInfoVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "/static/functionIcon/setting.png"),
		@ApiImplicitParam(name = ModuleInfoVOMeta.PATH, value = "??????", required = false, dataTypeClass = String.class, example = "/pages/index/repair/repair"),
		@ApiImplicitParam(name = ModuleInfoVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ModuleInfoVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class, example = "0")
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { ModuleInfoVOMeta.PAGE_INDEX, ModuleInfoVOMeta.PAGE_SIZE })
    @SentinelResource(value = ModuleInfoServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ModuleInfoServiceProxy.QUERY_LIST)
    public Result<List<ModuleInfo>> queryList(ModuleInfoVO sample) {
        Result<List<ModuleInfo>> result = new Result<>();
        List<ModuleInfo> list = moduleInfoService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ???????????????????????????
     */
    @ApiOperation(value = "???????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ModuleInfoVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = ModuleInfoVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class, example = "eam_mobile_asset_mgr_repair"),
		@ApiImplicitParam(name = ModuleInfoVOMeta.LABEL, value = "??????", required = false, dataTypeClass = String.class, example = "????????????"),
		@ApiImplicitParam(name = ModuleInfoVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = ModuleInfoVOMeta.GROUP_ID, value = "??????", required = false, dataTypeClass = String.class, example = "2"),
		@ApiImplicitParam(name = ModuleInfoVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "/static/functionIcon/setting.png"),
		@ApiImplicitParam(name = ModuleInfoVOMeta.PATH, value = "??????", required = false, dataTypeClass = String.class, example = "/pages/index/repair/repair"),
		@ApiImplicitParam(name = ModuleInfoVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = ModuleInfoVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class, example = "0")
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = ModuleInfoServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ModuleInfoServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<ModuleInfo>> queryPagedList(ModuleInfoVO sample) {
        Result<PagedList<ModuleInfo>> result = new Result<>();
        PagedList<ModuleInfo> list = moduleInfoService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        moduleInfoService.dao().fill(list).with(ModuleInfoMeta.MODULE_GROUP).execute();
        result.success(true).data(list);
        return result;
    }
}
