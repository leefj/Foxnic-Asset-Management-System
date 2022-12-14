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
import com.dt.platform.proxy.ops.AutoNodeServiceProxy;
import com.dt.platform.domain.ops.meta.AutoNodeVOMeta;
import com.dt.platform.domain.ops.AutoNode;
import com.dt.platform.domain.ops.AutoNodeVO;
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
import com.dt.platform.domain.ops.meta.AutoNodeMeta;
import org.github.foxnic.web.domain.system.DictItem;
import com.dt.platform.domain.ops.AutoGroup;
import com.dt.platform.domain.ops.AutoVoucher;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.IAutoNodeService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ?????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-08-22 13:57:10
 */
@Api(tags = "??????")
@ApiSort(0)
@RestController("OpsAutoNodeController")
public class AutoNodeController extends SuperController {

    @Autowired
    private IAutoNodeService autoNodeService;

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoNodeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "613661580453740544"),
		@ApiImplicitParam(name = AutoNodeVOMeta.OWNER_CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoNodeVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????1"),
		@ApiImplicitParam(name = AutoNodeVOMeta.GROUP_ID, value = "??????", required = false, dataTypeClass = String.class, example = "613661086134042624"),
		@ApiImplicitParam(name = AutoNodeVOMeta.TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "host"),
		@ApiImplicitParam(name = AutoNodeVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = AutoNodeVOMeta.IP, value = "IP", required = false, dataTypeClass = String.class, example = "127.0.0.1"),
		@ApiImplicitParam(name = AutoNodeVOMeta.PORT, value = "??????", required = false, dataTypeClass = Integer.class, example = "22"),
		@ApiImplicitParam(name = AutoNodeVOMeta.VOUCHER_ID, value = "??????", required = false, dataTypeClass = String.class, example = "613742519708024832"),
		@ApiImplicitParam(name = AutoNodeVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = AutoNodeServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoNodeServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(AutoNodeVO autoNodeVO) {
        Result result = autoNodeService.insert(autoNodeVO, false);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoNodeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "613661580453740544")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = AutoNodeServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoNodeServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  autoNodeService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = autoNodeService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ?????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoNodeVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AutoNodeServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoNodeServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = autoNodeService.hasRefers(ids);
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
            Result result = autoNodeService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = autoNodeService.deleteByIdsLogical(canDeleteIds);
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
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoNodeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "613661580453740544"),
		@ApiImplicitParam(name = AutoNodeVOMeta.OWNER_CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoNodeVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????1"),
		@ApiImplicitParam(name = AutoNodeVOMeta.GROUP_ID, value = "??????", required = false, dataTypeClass = String.class, example = "613661086134042624"),
		@ApiImplicitParam(name = AutoNodeVOMeta.TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "host"),
		@ApiImplicitParam(name = AutoNodeVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = AutoNodeVOMeta.IP, value = "IP", required = false, dataTypeClass = String.class, example = "127.0.0.1"),
		@ApiImplicitParam(name = AutoNodeVOMeta.PORT, value = "??????", required = false, dataTypeClass = Integer.class, example = "22"),
		@ApiImplicitParam(name = AutoNodeVOMeta.VOUCHER_ID, value = "??????", required = false, dataTypeClass = String.class, example = "613742519708024832"),
		@ApiImplicitParam(name = AutoNodeVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { AutoNodeVOMeta.PAGE_INDEX, AutoNodeVOMeta.PAGE_SIZE, AutoNodeVOMeta.SEARCH_FIELD, AutoNodeVOMeta.FUZZY_FIELD, AutoNodeVOMeta.SEARCH_VALUE, AutoNodeVOMeta.DIRTY_FIELDS, AutoNodeVOMeta.SORT_FIELD, AutoNodeVOMeta.SORT_TYPE, AutoNodeVOMeta.IDS })
    @SentinelResource(value = AutoNodeServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoNodeServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(AutoNodeVO autoNodeVO) {
        Result result = autoNodeService.update(autoNodeVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoNodeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "613661580453740544"),
		@ApiImplicitParam(name = AutoNodeVOMeta.OWNER_CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoNodeVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????1"),
		@ApiImplicitParam(name = AutoNodeVOMeta.GROUP_ID, value = "??????", required = false, dataTypeClass = String.class, example = "613661086134042624"),
		@ApiImplicitParam(name = AutoNodeVOMeta.TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "host"),
		@ApiImplicitParam(name = AutoNodeVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = AutoNodeVOMeta.IP, value = "IP", required = false, dataTypeClass = String.class, example = "127.0.0.1"),
		@ApiImplicitParam(name = AutoNodeVOMeta.PORT, value = "??????", required = false, dataTypeClass = Integer.class, example = "22"),
		@ApiImplicitParam(name = AutoNodeVOMeta.VOUCHER_ID, value = "??????", required = false, dataTypeClass = String.class, example = "613742519708024832"),
		@ApiImplicitParam(name = AutoNodeVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AutoNodeVOMeta.PAGE_INDEX, AutoNodeVOMeta.PAGE_SIZE, AutoNodeVOMeta.SEARCH_FIELD, AutoNodeVOMeta.FUZZY_FIELD, AutoNodeVOMeta.SEARCH_VALUE, AutoNodeVOMeta.DIRTY_FIELDS, AutoNodeVOMeta.SORT_FIELD, AutoNodeVOMeta.SORT_TYPE, AutoNodeVOMeta.IDS })
    @SentinelResource(value = AutoNodeServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoNodeServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(AutoNodeVO autoNodeVO) {
        Result result = autoNodeService.save(autoNodeVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoNodeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = AutoNodeServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoNodeServiceProxy.GET_BY_ID)
    public Result<AutoNode> getById(String id) {
        Result<AutoNode> result = new Result<>();
        AutoNode autoNode = autoNodeService.getById(id);
        // join ???????????????
        autoNodeService.dao().fill(autoNode).with(AutoNodeMeta.TYPE_DICT).with(AutoNodeMeta.GROUP).with(AutoNodeMeta.VOUCHER).execute();
        result.success(true).data(autoNode);
        return result;
    }

    /**
     * ?????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoNodeVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AutoNodeServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoNodeServiceProxy.GET_BY_IDS)
    public Result<List<AutoNode>> getByIds(List<String> ids) {
        Result<List<AutoNode>> result = new Result<>();
        List<AutoNode> list = autoNodeService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoNodeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "613661580453740544"),
		@ApiImplicitParam(name = AutoNodeVOMeta.OWNER_CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoNodeVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????1"),
		@ApiImplicitParam(name = AutoNodeVOMeta.GROUP_ID, value = "??????", required = false, dataTypeClass = String.class, example = "613661086134042624"),
		@ApiImplicitParam(name = AutoNodeVOMeta.TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "host"),
		@ApiImplicitParam(name = AutoNodeVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = AutoNodeVOMeta.IP, value = "IP", required = false, dataTypeClass = String.class, example = "127.0.0.1"),
		@ApiImplicitParam(name = AutoNodeVOMeta.PORT, value = "??????", required = false, dataTypeClass = Integer.class, example = "22"),
		@ApiImplicitParam(name = AutoNodeVOMeta.VOUCHER_ID, value = "??????", required = false, dataTypeClass = String.class, example = "613742519708024832"),
		@ApiImplicitParam(name = AutoNodeVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AutoNodeVOMeta.PAGE_INDEX, AutoNodeVOMeta.PAGE_SIZE })
    @SentinelResource(value = AutoNodeServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoNodeServiceProxy.QUERY_LIST)
    public Result<List<AutoNode>> queryList(AutoNodeVO sample) {
        Result<List<AutoNode>> result = new Result<>();
        List<AutoNode> list = autoNodeService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoNodeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "613661580453740544"),
		@ApiImplicitParam(name = AutoNodeVOMeta.OWNER_CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoNodeVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????1"),
		@ApiImplicitParam(name = AutoNodeVOMeta.GROUP_ID, value = "??????", required = false, dataTypeClass = String.class, example = "613661086134042624"),
		@ApiImplicitParam(name = AutoNodeVOMeta.TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "host"),
		@ApiImplicitParam(name = AutoNodeVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = AutoNodeVOMeta.IP, value = "IP", required = false, dataTypeClass = String.class, example = "127.0.0.1"),
		@ApiImplicitParam(name = AutoNodeVOMeta.PORT, value = "??????", required = false, dataTypeClass = Integer.class, example = "22"),
		@ApiImplicitParam(name = AutoNodeVOMeta.VOUCHER_ID, value = "??????", required = false, dataTypeClass = String.class, example = "613742519708024832"),
		@ApiImplicitParam(name = AutoNodeVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = AutoNodeServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoNodeServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<AutoNode>> queryPagedList(AutoNodeVO sample) {
        Result<PagedList<AutoNode>> result = new Result<>();
        PagedList<AutoNode> list = autoNodeService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        autoNodeService.dao().fill(list).with(AutoNodeMeta.TYPE_DICT).with(AutoNodeMeta.GROUP).with(AutoNodeMeta.VOUCHER).execute();
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoNodeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "613661580453740544"),
		@ApiImplicitParam(name = AutoNodeVOMeta.OWNER_CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoNodeVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????1"),
		@ApiImplicitParam(name = AutoNodeVOMeta.GROUP_ID, value = "??????", required = false, dataTypeClass = String.class, example = "613661086134042624"),
		@ApiImplicitParam(name = AutoNodeVOMeta.TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "host"),
		@ApiImplicitParam(name = AutoNodeVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = AutoNodeVOMeta.IP, value = "IP", required = false, dataTypeClass = String.class, example = "127.0.0.1"),
		@ApiImplicitParam(name = AutoNodeVOMeta.PORT, value = "??????", required = false, dataTypeClass = Integer.class, example = "22"),
		@ApiImplicitParam(name = AutoNodeVOMeta.VOUCHER_ID, value = "??????", required = false, dataTypeClass = String.class, example = "613742519708024832"),
		@ApiImplicitParam(name = AutoNodeVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = AutoNodeServiceProxy.QUERY_PAGED_LIST_BY_SELECTED, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoNodeServiceProxy.QUERY_PAGED_LIST_BY_SELECTED)
    public Result<PagedList<AutoNode>> queryPagedListBySelected(AutoNodeVO sample, String ownerId, String ownerSelectedCode) {
        Result<PagedList<AutoNode>> result = new Result<>();
        PagedList<AutoNode> list = autoNodeService.queryPagedListBySelected(sample, ownerId, ownerSelectedCode);
        // join ???????????????
        autoNodeService.dao().fill(list).with(AutoNodeMeta.TYPE_DICT).with(AutoNodeMeta.GROUP).with(AutoNodeMeta.VOUCHER).execute();
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AutoNodeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "613661580453740544"),
		@ApiImplicitParam(name = AutoNodeVOMeta.OWNER_CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AutoNodeVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class, example = "??????1"),
		@ApiImplicitParam(name = AutoNodeVOMeta.GROUP_ID, value = "??????", required = false, dataTypeClass = String.class, example = "613661086134042624"),
		@ApiImplicitParam(name = AutoNodeVOMeta.TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "host"),
		@ApiImplicitParam(name = AutoNodeVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = AutoNodeVOMeta.IP, value = "IP", required = false, dataTypeClass = String.class, example = "127.0.0.1"),
		@ApiImplicitParam(name = AutoNodeVOMeta.PORT, value = "??????", required = false, dataTypeClass = Integer.class, example = "22"),
		@ApiImplicitParam(name = AutoNodeVOMeta.VOUCHER_ID, value = "??????", required = false, dataTypeClass = String.class, example = "613742519708024832"),
		@ApiImplicitParam(name = AutoNodeVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = AutoNodeServiceProxy.QUERY_PAGED_LIST_BY_SELECT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoNodeServiceProxy.QUERY_PAGED_LIST_BY_SELECT)
    public Result<PagedList<AutoNode>> queryPagedListBySelect(AutoNodeVO sample, String ownerId, String ownerSelectedCode) {
        Result<PagedList<AutoNode>> result = new Result<>();
        PagedList<AutoNode> list = autoNodeService.queryPagedListBySelect(sample, ownerId, ownerSelectedCode);
        // join ???????????????
        autoNodeService.dao().fill(list).with(AutoNodeMeta.TYPE_DICT).with(AutoNodeMeta.GROUP).with(AutoNodeMeta.VOUCHER).execute();
        result.success(true).data(list);
        return result;
    }

    /**
     */
    @ApiOperation(value = "")
    @ApiImplicitParams({})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = AutoNodeServiceProxy.SAVE_SELECTED_NODE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoNodeServiceProxy.SAVE_SELECTED_NODE)
    public Result saveSelectedNode(String ownerId, String selectedCode, List<String> nodeIds) {
        return autoNodeService.saveSelectedNode(ownerId, selectedCode, nodeIds);
    }

    /**
     */
    @ApiOperation(value = "")
    @ApiImplicitParams({})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = AutoNodeServiceProxy.REMOVE_SELECTED_NODE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AutoNodeServiceProxy.REMOVE_SELECTED_NODE)
    public Result saveSelectedNode(String ownerId, String selectedCode, String nodeId) {
        return autoNodeService.removeSelectedNode(ownerId, selectedCode, nodeId);
    }
}
