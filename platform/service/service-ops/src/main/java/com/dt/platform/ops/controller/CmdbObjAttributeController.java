package com.dt.platform.ops.controller;

import java.util.List;
import java.util.ArrayList;
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
import com.dt.platform.proxy.ops.CmdbObjAttributeServiceProxy;
import com.dt.platform.domain.ops.meta.CmdbObjAttributeVOMeta;
import com.dt.platform.domain.ops.CmdbObjAttribute;
import com.dt.platform.domain.ops.CmdbObjAttributeVO;
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
import com.dt.platform.domain.ops.meta.CmdbObjAttributeMeta;
import com.dt.platform.domain.ops.CmdbModel;
import com.dt.platform.domain.ops.CmdbAttributeC;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.ops.service.ICmdbObjAttributeService;

/**
 * <p>
 * ???????????????????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-10-23 22:49:17
 */
@InDoc
@Api(tags = "null/????????????")
@RestController("OpsCmdbObjAttributeController")
public class CmdbObjAttributeController extends SuperController {

    @Autowired
    private ICmdbObjAttributeService cmdbObjAttributeService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "636664521074671616"),
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.MODEL_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.ATTRIBUTE_NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.ATTRIBUTE_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "STRING"),
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.ATTRIBUTE_COL_SOURCE, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.ATTRIBUTE_COL_FILL, value = "????????????", required = false, dataTypeClass = String.class, example = "desc"),
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.ATTRIBUTE_COL_SHOW, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class, example = "0"),
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1212")
	})
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    @ApiOperationSupport(order = 1, author = "?????? , maillank@qq.com")
    @SentinelResource(value = CmdbObjAttributeServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CmdbObjAttributeServiceProxy.INSERT)
    public Result insert(CmdbObjAttributeVO cmdbObjAttributeVO) {
        Result result = cmdbObjAttributeService.insert(cmdbObjAttributeVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "636664521074671616")
	})
    @ApiOperationSupport(order = 2, author = "?????? , maillank@qq.com")
    @SentinelResource(value = CmdbObjAttributeServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CmdbObjAttributeServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  cmdbObjAttributeService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = cmdbObjAttributeService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3, author = "?????? , maillank@qq.com")
    @SentinelResource(value = CmdbObjAttributeServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CmdbObjAttributeServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = cmdbObjAttributeService.hasRefers(ids);
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
            Result result = cmdbObjAttributeService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = cmdbObjAttributeService.deleteByIdsLogical(canDeleteIds);
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
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "636664521074671616"),
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.MODEL_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.ATTRIBUTE_NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.ATTRIBUTE_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "STRING"),
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.ATTRIBUTE_COL_SOURCE, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.ATTRIBUTE_COL_FILL, value = "????????????", required = false, dataTypeClass = String.class, example = "desc"),
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.ATTRIBUTE_COL_SHOW, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class, example = "0"),
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1212")
	})
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    @ApiOperationSupport(order = 4, author = "?????? , maillank@qq.com", ignoreParameters = { CmdbObjAttributeVOMeta.PAGE_INDEX, CmdbObjAttributeVOMeta.PAGE_SIZE, CmdbObjAttributeVOMeta.SEARCH_FIELD, CmdbObjAttributeVOMeta.FUZZY_FIELD, CmdbObjAttributeVOMeta.SEARCH_VALUE, CmdbObjAttributeVOMeta.DIRTY_FIELDS, CmdbObjAttributeVOMeta.SORT_FIELD, CmdbObjAttributeVOMeta.SORT_TYPE, CmdbObjAttributeVOMeta.IDS })
    @SentinelResource(value = CmdbObjAttributeServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CmdbObjAttributeServiceProxy.UPDATE)
    public Result update(CmdbObjAttributeVO cmdbObjAttributeVO) {
        Result result = cmdbObjAttributeService.update(cmdbObjAttributeVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "636664521074671616"),
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.MODEL_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.ATTRIBUTE_NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.ATTRIBUTE_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "STRING"),
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.ATTRIBUTE_COL_SOURCE, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.ATTRIBUTE_COL_FILL, value = "????????????", required = false, dataTypeClass = String.class, example = "desc"),
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.ATTRIBUTE_COL_SHOW, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class, example = "0"),
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1212")
	})
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    @ApiOperationSupport(order = 5, ignoreParameters = { CmdbObjAttributeVOMeta.PAGE_INDEX, CmdbObjAttributeVOMeta.PAGE_SIZE, CmdbObjAttributeVOMeta.SEARCH_FIELD, CmdbObjAttributeVOMeta.FUZZY_FIELD, CmdbObjAttributeVOMeta.SEARCH_VALUE, CmdbObjAttributeVOMeta.DIRTY_FIELDS, CmdbObjAttributeVOMeta.SORT_FIELD, CmdbObjAttributeVOMeta.SORT_TYPE, CmdbObjAttributeVOMeta.IDS })
    @SentinelResource(value = CmdbObjAttributeServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CmdbObjAttributeServiceProxy.SAVE)
    public Result save(CmdbObjAttributeVO cmdbObjAttributeVO) {
        Result result = cmdbObjAttributeService.save(cmdbObjAttributeVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6, author = "?????? , maillank@qq.com")
    @SentinelResource(value = CmdbObjAttributeServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CmdbObjAttributeServiceProxy.GET_BY_ID)
    public Result<CmdbObjAttribute> getById(String id) {
        Result<CmdbObjAttribute> result = new Result<>();
        CmdbObjAttribute cmdbObjAttribute = cmdbObjAttributeService.getById(id);
        // join ???????????????
        cmdbObjAttributeService.dao().fill(cmdbObjAttribute).with(CmdbObjAttributeMeta.CMDB_ATTRIBUTE_C).with(CmdbObjAttributeMeta.CMDB_MODEL).execute();
        result.success(true).data(cmdbObjAttribute);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3, author = "?????? , maillank@qq.com")
    @SentinelResource(value = CmdbObjAttributeServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CmdbObjAttributeServiceProxy.GET_BY_IDS)
    public Result<List<CmdbObjAttribute>> getByIds(List<String> ids) {
        Result<List<CmdbObjAttribute>> result = new Result<>();
        List<CmdbObjAttribute> list = cmdbObjAttributeService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "636664521074671616"),
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.MODEL_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.ATTRIBUTE_NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.ATTRIBUTE_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "STRING"),
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.ATTRIBUTE_COL_SOURCE, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.ATTRIBUTE_COL_FILL, value = "????????????", required = false, dataTypeClass = String.class, example = "desc"),
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.ATTRIBUTE_COL_SHOW, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class, example = "0"),
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1212")
	})
    @ApiOperationSupport(order = 5, author = "?????? , maillank@qq.com", ignoreParameters = { CmdbObjAttributeVOMeta.PAGE_INDEX, CmdbObjAttributeVOMeta.PAGE_SIZE })
    @SentinelResource(value = CmdbObjAttributeServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CmdbObjAttributeServiceProxy.QUERY_LIST)
    public Result<List<CmdbObjAttribute>> queryList(CmdbObjAttributeVO sample) {
        Result<List<CmdbObjAttribute>> result = new Result<>();
        List<CmdbObjAttribute> list = cmdbObjAttributeService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "636664521074671616"),
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.MODEL_ID, value = "????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.STATUS, value = "??????", required = false, dataTypeClass = String.class, example = "enable"),
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.ATTRIBUTE_NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.ATTRIBUTE_TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "STRING"),
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.ATTRIBUTE_COL_SOURCE, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.ATTRIBUTE_COL_FILL, value = "????????????", required = false, dataTypeClass = String.class, example = "desc"),
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.ATTRIBUTE_COL_SHOW, value = "????????????", required = false, dataTypeClass = String.class, example = "12"),
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.SORT, value = "??????", required = false, dataTypeClass = Integer.class, example = "0"),
		@ApiImplicitParam(name = CmdbObjAttributeVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class, example = "1212")
	})
    @ApiOperationSupport(order = 8, author = "?????? , maillank@qq.com")
    @SentinelResource(value = CmdbObjAttributeServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(CmdbObjAttributeServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<CmdbObjAttribute>> queryPagedList(CmdbObjAttributeVO sample) {
        Result<PagedList<CmdbObjAttribute>> result = new Result<>();
        PagedList<CmdbObjAttribute> list = cmdbObjAttributeService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        // join ???????????????
        cmdbObjAttributeService.dao().fill(list).with(CmdbObjAttributeMeta.CMDB_ATTRIBUTE_C).with(CmdbObjAttributeMeta.CMDB_MODEL).execute();
        result.success(true).data(list);
        return result;
    }
}
