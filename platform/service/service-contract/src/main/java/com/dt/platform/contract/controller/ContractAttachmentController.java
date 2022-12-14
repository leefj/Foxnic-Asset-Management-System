package com.dt.platform.contract.controller;

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
import com.dt.platform.proxy.contract.ContractAttachmentServiceProxy;
import com.dt.platform.domain.contract.meta.ContractAttachmentVOMeta;
import com.dt.platform.domain.contract.ContractAttachment;
import com.dt.platform.domain.contract.ContractAttachmentVO;
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
import com.dt.platform.domain.contract.meta.ContractAttachmentMeta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.contract.service.IContractAttachmentService;

/**
 * <p>
 * ???????????????????????????
 * </p>
 * @author ????????? , leefangjie@qq.com
 * @since 2022-10-21 15:39:34
 */
@InDoc
@Api(tags = "????????????/????????????")
@RestController("ContContractAttachmentController")
public class ContractAttachmentController extends SuperController {

    @Autowired
    private IContractAttachmentService contractAttachmentService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ContractAttachmentVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "565281472081035264"),
		@ApiImplicitParam(name = ContractAttachmentVOMeta.OWNER_ID, value = "?????????ID", required = false, dataTypeClass = String.class, example = "565281320981233664"),
		@ApiImplicitParam(name = ContractAttachmentVOMeta.OWNER_TYPE, value = "???????????????", required = false, dataTypeClass = String.class, example = "contract"),
		@ApiImplicitParam(name = ContractAttachmentVOMeta.TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "contract_text"),
		@ApiImplicitParam(name = ContractAttachmentVOMeta.FILE_ID, value = "??????ID", required = false, dataTypeClass = String.class, example = "565281467555381248"),
		@ApiImplicitParam(name = ContractAttachmentVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = ContractAttachmentVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    @ApiOperationSupport(order = 1, author = "????????? , leefangjie@qq.com")
    @SentinelResource(value = ContractAttachmentServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ContractAttachmentServiceProxy.INSERT)
    public Result insert(ContractAttachmentVO contractAttachmentVO) {
        Result result = contractAttachmentService.insert(contractAttachmentVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ContractAttachmentVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "565281472081035264")
	})
    @ApiOperationSupport(order = 2, author = "????????? , leefangjie@qq.com")
    @SentinelResource(value = ContractAttachmentServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ContractAttachmentServiceProxy.DELETE)
    public Result deleteById(String id) {
        this.validator().asserts(id).require("??????id???");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        ReferCause cause =  contractAttachmentService.hasRefers(id);
        // ????????????????????????
        this.validator().asserts(cause.hasRefer()).requireEqual("??????????????????????????????"+cause.message(),false);
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        Result result = contractAttachmentService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ContractAttachmentVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3, author = "????????? , leefangjie@qq.com")
    @SentinelResource(value = ContractAttachmentServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ContractAttachmentServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        // ????????????
        this.validator().asserts(ids).require("??????ids??????");
        if (this.validator().failure()) {
            return this.validator().getFirstResult();
        }
        // ????????????
        Map<String, ReferCause> causeMap = contractAttachmentService.hasRefers(ids);
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
            Result result = contractAttachmentService.deleteByIdsLogical(canDeleteIds);
            return result;
        } else if (canDeleteIds.size() > 0 && canDeleteIds.size() < ids.size()) {
            // ???????????????????????????
            Result result = contractAttachmentService.deleteByIdsLogical(canDeleteIds);
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
		@ApiImplicitParam(name = ContractAttachmentVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "565281472081035264"),
		@ApiImplicitParam(name = ContractAttachmentVOMeta.OWNER_ID, value = "?????????ID", required = false, dataTypeClass = String.class, example = "565281320981233664"),
		@ApiImplicitParam(name = ContractAttachmentVOMeta.OWNER_TYPE, value = "???????????????", required = false, dataTypeClass = String.class, example = "contract"),
		@ApiImplicitParam(name = ContractAttachmentVOMeta.TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "contract_text"),
		@ApiImplicitParam(name = ContractAttachmentVOMeta.FILE_ID, value = "??????ID", required = false, dataTypeClass = String.class, example = "565281467555381248"),
		@ApiImplicitParam(name = ContractAttachmentVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = ContractAttachmentVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    @ApiOperationSupport(order = 4, author = "????????? , leefangjie@qq.com", ignoreParameters = { ContractAttachmentVOMeta.PAGE_INDEX, ContractAttachmentVOMeta.PAGE_SIZE, ContractAttachmentVOMeta.SEARCH_FIELD, ContractAttachmentVOMeta.FUZZY_FIELD, ContractAttachmentVOMeta.SEARCH_VALUE, ContractAttachmentVOMeta.DIRTY_FIELDS, ContractAttachmentVOMeta.SORT_FIELD, ContractAttachmentVOMeta.SORT_TYPE, ContractAttachmentVOMeta.IDS })
    @SentinelResource(value = ContractAttachmentServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ContractAttachmentServiceProxy.UPDATE)
    public Result update(ContractAttachmentVO contractAttachmentVO) {
        Result result = contractAttachmentService.update(contractAttachmentVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ContractAttachmentVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "565281472081035264"),
		@ApiImplicitParam(name = ContractAttachmentVOMeta.OWNER_ID, value = "?????????ID", required = false, dataTypeClass = String.class, example = "565281320981233664"),
		@ApiImplicitParam(name = ContractAttachmentVOMeta.OWNER_TYPE, value = "???????????????", required = false, dataTypeClass = String.class, example = "contract"),
		@ApiImplicitParam(name = ContractAttachmentVOMeta.TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "contract_text"),
		@ApiImplicitParam(name = ContractAttachmentVOMeta.FILE_ID, value = "??????ID", required = false, dataTypeClass = String.class, example = "565281467555381248"),
		@ApiImplicitParam(name = ContractAttachmentVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = ContractAttachmentVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    @ApiOperationSupport(order = 5, ignoreParameters = { ContractAttachmentVOMeta.PAGE_INDEX, ContractAttachmentVOMeta.PAGE_SIZE, ContractAttachmentVOMeta.SEARCH_FIELD, ContractAttachmentVOMeta.FUZZY_FIELD, ContractAttachmentVOMeta.SEARCH_VALUE, ContractAttachmentVOMeta.DIRTY_FIELDS, ContractAttachmentVOMeta.SORT_FIELD, ContractAttachmentVOMeta.SORT_TYPE, ContractAttachmentVOMeta.IDS })
    @SentinelResource(value = ContractAttachmentServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ContractAttachmentServiceProxy.SAVE)
    public Result save(ContractAttachmentVO contractAttachmentVO) {
        Result result = contractAttachmentService.save(contractAttachmentVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ContractAttachmentVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6, author = "????????? , leefangjie@qq.com")
    @SentinelResource(value = ContractAttachmentServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ContractAttachmentServiceProxy.GET_BY_ID)
    public Result<ContractAttachment> getById(String id) {
        Result<ContractAttachment> result = new Result<>();
        ContractAttachment contractAttachment = contractAttachmentService.getById(id);
        result.success(true).data(contractAttachment);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ContractAttachmentVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3, author = "????????? , leefangjie@qq.com")
    @SentinelResource(value = ContractAttachmentServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ContractAttachmentServiceProxy.GET_BY_IDS)
    public Result<List<ContractAttachment>> getByIds(List<String> ids) {
        Result<List<ContractAttachment>> result = new Result<>();
        List<ContractAttachment> list = contractAttachmentService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ContractAttachmentVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "565281472081035264"),
		@ApiImplicitParam(name = ContractAttachmentVOMeta.OWNER_ID, value = "?????????ID", required = false, dataTypeClass = String.class, example = "565281320981233664"),
		@ApiImplicitParam(name = ContractAttachmentVOMeta.OWNER_TYPE, value = "???????????????", required = false, dataTypeClass = String.class, example = "contract"),
		@ApiImplicitParam(name = ContractAttachmentVOMeta.TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "contract_text"),
		@ApiImplicitParam(name = ContractAttachmentVOMeta.FILE_ID, value = "??????ID", required = false, dataTypeClass = String.class, example = "565281467555381248"),
		@ApiImplicitParam(name = ContractAttachmentVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = ContractAttachmentVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, author = "????????? , leefangjie@qq.com", ignoreParameters = { ContractAttachmentVOMeta.PAGE_INDEX, ContractAttachmentVOMeta.PAGE_SIZE })
    @SentinelResource(value = ContractAttachmentServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ContractAttachmentServiceProxy.QUERY_LIST)
    public Result<List<ContractAttachment>> queryList(ContractAttachmentVO sample) {
        Result<List<ContractAttachment>> result = new Result<>();
        List<ContractAttachment> list = contractAttachmentService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = ContractAttachmentVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "565281472081035264"),
		@ApiImplicitParam(name = ContractAttachmentVOMeta.OWNER_ID, value = "?????????ID", required = false, dataTypeClass = String.class, example = "565281320981233664"),
		@ApiImplicitParam(name = ContractAttachmentVOMeta.OWNER_TYPE, value = "???????????????", required = false, dataTypeClass = String.class, example = "contract"),
		@ApiImplicitParam(name = ContractAttachmentVOMeta.TYPE, value = "????????????", required = false, dataTypeClass = String.class, example = "contract_text"),
		@ApiImplicitParam(name = ContractAttachmentVOMeta.FILE_ID, value = "??????ID", required = false, dataTypeClass = String.class, example = "565281467555381248"),
		@ApiImplicitParam(name = ContractAttachmentVOMeta.NAME, value = "????????????", required = false, dataTypeClass = String.class, example = "1212"),
		@ApiImplicitParam(name = ContractAttachmentVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8, author = "????????? , leefangjie@qq.com")
    @SentinelResource(value = ContractAttachmentServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(ContractAttachmentServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<ContractAttachment>> queryPagedList(ContractAttachmentVO sample) {
        Result<PagedList<ContractAttachment>> result = new Result<>();
        PagedList<ContractAttachment> list = contractAttachmentService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }
}
