package com.dt.platform.ops.controller;

import java.util.List;
import java.util.ArrayList;
import com.dt.platform.domain.eam.Position;
import com.github.foxnic.commons.busi.id.IDGenerator;
import com.github.foxnic.commons.lang.StringUtil;
import com.github.foxnic.commons.collection.CollectorUtil;
import com.github.foxnic.dao.entity.ReferCause;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import com.github.foxnic.api.swagger.InDoc;
import org.github.foxnic.web.framework.web.SuperController;
import org.github.foxnic.web.framework.sentinel.SentinelExceptionUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.github.foxnic.api.swagger.ApiParamSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.proxy.ops.CmdbModelServiceProxy;
import com.dt.platform.domain.ops.meta.CmdbModelVOMeta;
import com.dt.platform.domain.ops.CmdbModel;
import com.dt.platform.domain.ops.CmdbModelVO;
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
import com.dt.platform.domain.ops.meta.CmdbModelMeta;
import com.dt.platform.domain.ops.CmdbObjAttribute;
import com.dt.platform.domain.ops.CmdbModelV;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.ICmdbModelService;

/**
 * <p>
 * ????????????????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-10-22 14:28:39
 */
@InDoc
@Api(tags = "null/?????????")
@RestController("OpsCmdbModelController")
public class CmdbModelController extends SuperController {

    @Autowired
    private ICmdbModelService cmdbModelService;

    /**
     * ???????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = CmdbModelVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.PARENT_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.HIERARCHY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.HIERARCHY_NAME, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.FULL_NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.MODEL_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.SOURCE_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.DATA_SOURCE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.STORAGE_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.STORAGE_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.STORAGE_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.STORAGE_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.STORAGE_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.STORAGE_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.STORAGE_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.STORAGE_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.STORAGE_TYPE", value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    @ApiOperationSupport(order = 1, author = "?????? , maillank@qq.com")
    @SentinelResource(value = CmdbModelServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CmdbModelServiceProxy.INSERT)
    public Result insert(CmdbModelVO cmdbModelVO) {
        cmdbModelVO.setSort(9999);
        if (StringUtil.isBlank(cmdbModelVO.getParentId())) {
            cmdbModelVO.setParentId("0");
        }
        cmdbModelVO.setFullName(cmdbModelVO.getName());
        cmdbModelVO.setCode(IDGenerator.getSnowflakeIdString());
        Result result = cmdbModelService.insert(cmdbModelVO, false);
        if (result.success()) {
            CmdbModel currentCmdbModel = new CmdbModel();
            currentCmdbModel.setId(cmdbModelVO.getId());
            if ("0".equals(cmdbModelVO.getParentId())) {
                currentCmdbModel.setHierarchy(cmdbModelVO.getId());
                currentCmdbModel.setHierarchyName(cmdbModelVO.getName());
            } else {
                CmdbModel parentCmdbModel = cmdbModelService.getById(cmdbModelVO.getParentId());
                currentCmdbModel.setHierarchy(parentCmdbModel.getHierarchy() + "/" + cmdbModelVO.getId());
                currentCmdbModel.setHierarchyName(parentCmdbModel.getHierarchyName() + "/" + cmdbModelVO.getName());
            }
            cmdbModelService.update(currentCmdbModel, SaveMode.NOT_NULL_FIELDS);
            cmdbModelVO.setHierarchy(currentCmdbModel.getHierarchy());
            cmdbModelVO.setHierarchyName(currentCmdbModel.getHierarchyName());
            result.data(cmdbModelVO);
        }
        return result;
    }

    /**
     * ???????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = CmdbModelVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 2, author = "?????? , maillank@qq.com")
    @SentinelResource(value = CmdbModelServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CmdbModelServiceProxy.DELETE)
    public Result deleteById(String id) {
        List<CmdbModel> list = cmdbModelService.queryList(CmdbModel.create().setParentId(id));
        if (list.size() > 0) {
            Result<CmdbModel> result = new Result<>();
            result.success(false).message("????????????????????????");
            return result;
        }
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  cmdbModelService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = cmdbModelService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = CmdbModelVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3, author = "?????? , maillank@qq.com")
    @SentinelResource(value = CmdbModelServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CmdbModelServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = cmdbModelService.hasRefers(ids);
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
            Result result = cmdbModelService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = cmdbModelService.deleteByIdsLogical(canDeleteIds);
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
     * ???????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = CmdbModelVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.PARENT_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.HIERARCHY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.HIERARCHY_NAME, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.FULL_NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.MODEL_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.SOURCE_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.DATA_SOURCE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.STORAGE_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.STORAGE_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.STORAGE_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.STORAGE_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.STORAGE_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.STORAGE_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.STORAGE_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.STORAGE_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.STORAGE_TYPE", value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    @ApiOperationSupport(order = 4, author = "?????? , maillank@qq.com", ignoreParameters = { CmdbModelVOMeta.PAGE_INDEX, CmdbModelVOMeta.PAGE_SIZE, CmdbModelVOMeta.SEARCH_FIELD, CmdbModelVOMeta.FUZZY_FIELD, CmdbModelVOMeta.SEARCH_VALUE, CmdbModelVOMeta.DIRTY_FIELDS, CmdbModelVOMeta.SORT_FIELD, CmdbModelVOMeta.SORT_TYPE, CmdbModelVOMeta.IDS })
    @SentinelResource(value = CmdbModelServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CmdbModelServiceProxy.UPDATE)
    public Result update(CmdbModelVO cmdbModelVO) {
        Result result = cmdbModelService.update(cmdbModelVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        if (result.isSuccess()) {
            return cmdbModelService.updateHierarchy(cmdbModelVO.getId());
        }
        return result;
    }

    /**
     * ???????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = CmdbModelVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.PARENT_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.HIERARCHY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.HIERARCHY_NAME, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.FULL_NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.MODEL_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.SOURCE_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.DATA_SOURCE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.STORAGE_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.STORAGE_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.STORAGE_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.STORAGE_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.STORAGE_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.STORAGE_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.STORAGE_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.STORAGE_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.STORAGE_TYPE", value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    @ApiOperationSupport(order = 5, ignoreParameters = { CmdbModelVOMeta.PAGE_INDEX, CmdbModelVOMeta.PAGE_SIZE, CmdbModelVOMeta.SEARCH_FIELD, CmdbModelVOMeta.FUZZY_FIELD, CmdbModelVOMeta.SEARCH_VALUE, CmdbModelVOMeta.DIRTY_FIELDS, CmdbModelVOMeta.SORT_FIELD, CmdbModelVOMeta.SORT_TYPE, CmdbModelVOMeta.IDS })
    @SentinelResource(value = CmdbModelServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CmdbModelServiceProxy.SAVE)
    public Result save(CmdbModelVO cmdbModelVO) {
        Result result = cmdbModelService.save(cmdbModelVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        if (result.isSuccess()) {
            return cmdbModelService.updateHierarchy(cmdbModelVO.getId());
        }
        return result;
    }

    /**
     * ???????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = CmdbModelVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6, author = "?????? , maillank@qq.com")
    @SentinelResource(value = CmdbModelServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CmdbModelServiceProxy.GET_BY_ID)
    public Result<CmdbModel> getById(String id) {
        Result<CmdbModel> result = new Result<>();
        CmdbModel cmdbModel = cmdbModelService.getById(id);
        result.success(true).data(cmdbModel);
        return result;
    }

    /**
     * ????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = CmdbModelVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3, author = "?????? , maillank@qq.com")
    @SentinelResource(value = CmdbModelServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CmdbModelServiceProxy.GET_BY_IDS)
    public Result<List<CmdbModel>> getByIds(List<String> ids) {
        Result<List<CmdbModel>> result = new Result<>();
        List<CmdbModel> list = cmdbModelService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ???????????????
     */
    @ApiOperation(value = "???????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = CmdbModelVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.PARENT_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.HIERARCHY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.HIERARCHY_NAME, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.FULL_NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.MODEL_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.SOURCE_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.DATA_SOURCE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.STORAGE_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.STORAGE_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.STORAGE_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.STORAGE_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.STORAGE_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.STORAGE_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.STORAGE_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.STORAGE_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.STORAGE_TYPE", value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, author = "?????? , maillank@qq.com", ignoreParameters = { CmdbModelVOMeta.PAGE_INDEX, CmdbModelVOMeta.PAGE_SIZE })
    @SentinelResource(value = CmdbModelServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CmdbModelServiceProxy.QUERY_LIST)
    public Result<List<CmdbModel>> queryList(CmdbModelVO sample) {
        Result<List<CmdbModel>> result = new Result<>();
        List<CmdbModel> list = cmdbModelService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????????????????????
     */
    @ApiOperation(value = "?????????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = CmdbModelVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.PARENT_ID, value = "?????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.HIERARCHY, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.HIERARCHY_NAME, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.FULL_NAME, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.CODE, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.MODEL_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.SOURCE_TYPE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.DATA_SOURCE, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = CmdbModelVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.STORAGE_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.STORAGE_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.STORAGE_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.STORAGE_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.STORAGE_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.STORAGE_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.STORAGE_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.STORAGE_TYPE", value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.SOURCE", value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = "CmdbModelVOMeta.STORAGE_TYPE", value = "????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8, author = "?????? , maillank@qq.com")
    @SentinelResource(value = CmdbModelServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CmdbModelServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<CmdbModel>> queryPagedList(CmdbModelVO sample) {
        Result<PagedList<CmdbModel>> result = new Result<>();
        PagedList<CmdbModel> list = cmdbModelService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }
}
