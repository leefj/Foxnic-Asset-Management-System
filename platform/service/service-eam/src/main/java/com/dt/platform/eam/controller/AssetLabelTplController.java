package com.dt.platform.eam.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import com.dt.platform.domain.eam.*;
import com.dt.platform.domain.eam.meta.AssetLabelMeta;
import com.dt.platform.domain.eam.meta.AssetLabelTplItemMeta;
import com.dt.platform.eam.service.IAssetLabelService;
import com.dt.platform.eam.service.IAssetLabelTplItemService;
import com.github.foxnic.commons.collection.CollectorUtil;
import org.github.foxnic.web.domain.hrm.Employee;
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
import com.dt.platform.proxy.eam.AssetLabelTplServiceProxy;
import com.dt.platform.domain.eam.meta.AssetLabelTplVOMeta;
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
import com.dt.platform.domain.eam.meta.AssetLabelTplMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IAssetLabelTplService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2022-05-21 13:48:06
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("EamAssetLabelTplController")
public class AssetLabelTplController extends SuperController {

    @Autowired
    private IAssetLabelService assetLabelService;

    @Autowired
    private IAssetLabelTplService assetLabelTplService;

    @Autowired
    private IAssetLabelTplItemService assetLabelTplItemService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetLabelTplVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.TYPE, value = "??????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IS_CUSTOM, value = "???????????????", required = false, dataTypeClass = String.class, example = "0"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.COL_IDS, value = "??????", required = false, dataTypeClass = String.class, example = "[1,2,3]"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_POSITION, value = "??????:u", required = false, dataTypeClass = String.class, example = "d"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_COL_ID, value = "??????", required = false, dataTypeClass = String.class, example = "9"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_LABEL_SHOW, value = "??????label??????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.LABEL_FORMAT_CONTENT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_SHOW, value = "????????????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_TYPE, value = "????????????txm", required = false, dataTypeClass = String.class, example = "txm"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.LABEL_TABLE_MARGIN_TOP, value = "??????marginTop(mm)", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.LABEL_TABLE_MARGIN_BOTTOM, value = "??????marginBottom(mm)", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.LABEL_TABLE_MARGIN_LEFT, value = "??????marginLeft(mm)", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.LABEL_TABLE_MARGIN_RIGHT, value = "??????marginRight(mm)", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_MARGIN_TOP, value = "??????marginTop(mm)", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_MARGIN_BOTTOM, value = "??????marginBottom(mm)", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_MARGIN_LEFT, value = "??????marginLeft(mm)", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_MARGIN_RIGHT, value = "??????marginRight(mm)", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_WIDTH, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_HEIGHT, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.KEY_BOLD, value = "?????????Key????????????", required = false, dataTypeClass = String.class, example = "0"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.VALUE_BLOD, value = "?????????Value????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = AssetLabelTplServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetLabelTplServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(AssetLabelTplVO assetLabelTplVO) {
        Result result = assetLabelTplService.insert(assetLabelTplVO, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetLabelTplVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = AssetLabelTplServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetLabelTplServiceProxy.DELETE)
    public Result deleteById(String id) {
        AssetLabel label = assetLabelService.queryAssetLabel();
        if (label != null && label.getLabelTplId() != null) {
            if (label.getLabelTplId().equals(id)) {
                return ErrorDesc.failureMessage("???????????????????????????????????????");
            }
        }
        Result result = assetLabelTplService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetLabelTplServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetLabelTplServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids) {
        Result result = assetLabelTplService.deleteByIdsLogical(ids);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetLabelTplVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.TYPE, value = "??????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IS_CUSTOM, value = "???????????????", required = false, dataTypeClass = String.class, example = "0"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.COL_IDS, value = "??????", required = false, dataTypeClass = String.class, example = "[1,2,3]"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_POSITION, value = "??????:u", required = false, dataTypeClass = String.class, example = "d"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_COL_ID, value = "??????", required = false, dataTypeClass = String.class, example = "9"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_LABEL_SHOW, value = "??????label??????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.LABEL_FORMAT_CONTENT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_SHOW, value = "????????????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_TYPE, value = "????????????txm", required = false, dataTypeClass = String.class, example = "txm"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.LABEL_TABLE_MARGIN_TOP, value = "??????marginTop(mm)", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.LABEL_TABLE_MARGIN_BOTTOM, value = "??????marginBottom(mm)", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.LABEL_TABLE_MARGIN_LEFT, value = "??????marginLeft(mm)", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.LABEL_TABLE_MARGIN_RIGHT, value = "??????marginRight(mm)", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_MARGIN_TOP, value = "??????marginTop(mm)", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_MARGIN_BOTTOM, value = "??????marginBottom(mm)", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_MARGIN_LEFT, value = "??????marginLeft(mm)", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_MARGIN_RIGHT, value = "??????marginRight(mm)", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_WIDTH, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_HEIGHT, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.KEY_BOLD, value = "?????????Key????????????", required = false, dataTypeClass = String.class, example = "0"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.VALUE_BLOD, value = "?????????Value????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { AssetLabelTplVOMeta.PAGE_INDEX, AssetLabelTplVOMeta.PAGE_SIZE, AssetLabelTplVOMeta.SEARCH_FIELD, AssetLabelTplVOMeta.FUZZY_FIELD, AssetLabelTplVOMeta.SEARCH_VALUE, AssetLabelTplVOMeta.DIRTY_FIELDS, AssetLabelTplVOMeta.SORT_FIELD, AssetLabelTplVOMeta.SORT_TYPE, AssetLabelTplVOMeta.IDS })
    @SentinelResource(value = AssetLabelTplServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetLabelTplServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(AssetLabelTplVO assetLabelTplVO) {
        Result result = assetLabelTplService.update(assetLabelTplVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetLabelTplVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.TYPE, value = "??????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IS_CUSTOM, value = "???????????????", required = false, dataTypeClass = String.class, example = "0"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.COL_IDS, value = "??????", required = false, dataTypeClass = String.class, example = "[1,2,3]"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_POSITION, value = "??????:u", required = false, dataTypeClass = String.class, example = "d"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_COL_ID, value = "??????", required = false, dataTypeClass = String.class, example = "9"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_LABEL_SHOW, value = "??????label??????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.LABEL_FORMAT_CONTENT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_SHOW, value = "????????????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_TYPE, value = "????????????txm", required = false, dataTypeClass = String.class, example = "txm"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.LABEL_TABLE_MARGIN_TOP, value = "??????marginTop(mm)", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.LABEL_TABLE_MARGIN_BOTTOM, value = "??????marginBottom(mm)", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.LABEL_TABLE_MARGIN_LEFT, value = "??????marginLeft(mm)", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.LABEL_TABLE_MARGIN_RIGHT, value = "??????marginRight(mm)", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_MARGIN_TOP, value = "??????marginTop(mm)", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_MARGIN_BOTTOM, value = "??????marginBottom(mm)", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_MARGIN_LEFT, value = "??????marginLeft(mm)", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_MARGIN_RIGHT, value = "??????marginRight(mm)", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_WIDTH, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_HEIGHT, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.KEY_BOLD, value = "?????????Key????????????", required = false, dataTypeClass = String.class, example = "0"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.VALUE_BLOD, value = "?????????Value????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetLabelTplVOMeta.PAGE_INDEX, AssetLabelTplVOMeta.PAGE_SIZE, AssetLabelTplVOMeta.SEARCH_FIELD, AssetLabelTplVOMeta.FUZZY_FIELD, AssetLabelTplVOMeta.SEARCH_VALUE, AssetLabelTplVOMeta.DIRTY_FIELDS, AssetLabelTplVOMeta.SORT_FIELD, AssetLabelTplVOMeta.SORT_TYPE, AssetLabelTplVOMeta.IDS })
    @SentinelResource(value = AssetLabelTplServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetLabelTplServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(AssetLabelTplVO assetLabelTplVO) {
        Result result = assetLabelTplService.save(assetLabelTplVO, SaveMode.DIRTY_OR_NOT_NULL_FIELDS, false);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetLabelTplVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = AssetLabelTplServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetLabelTplServiceProxy.GET_BY_ID)
    public Result<AssetLabelTpl> getById(String id) {
        Result<AssetLabelTpl> result = new Result<>();
        AssetLabelTpl assetLabelTpl = assetLabelTplService.getById(id);
        result.success(true).data(assetLabelTpl);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetLabelTplServiceProxy.GET_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetLabelTplServiceProxy.GET_BY_IDS)
    public Result<List<AssetLabelTpl>> getByIds(List<String> ids) {
        Result<List<AssetLabelTpl>> result = new Result<>();
        List<AssetLabelTpl> list = assetLabelTplService.queryListByIds(ids);
        result.success(true).data(list);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetLabelTplVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.TYPE, value = "??????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IS_CUSTOM, value = "???????????????", required = false, dataTypeClass = String.class, example = "0"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.COL_IDS, value = "??????", required = false, dataTypeClass = String.class, example = "[1,2,3]"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_POSITION, value = "??????:u", required = false, dataTypeClass = String.class, example = "d"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_COL_ID, value = "??????", required = false, dataTypeClass = String.class, example = "9"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_LABEL_SHOW, value = "??????label??????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.LABEL_FORMAT_CONTENT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_SHOW, value = "????????????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_TYPE, value = "????????????txm", required = false, dataTypeClass = String.class, example = "txm"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.LABEL_TABLE_MARGIN_TOP, value = "??????marginTop(mm)", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.LABEL_TABLE_MARGIN_BOTTOM, value = "??????marginBottom(mm)", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.LABEL_TABLE_MARGIN_LEFT, value = "??????marginLeft(mm)", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.LABEL_TABLE_MARGIN_RIGHT, value = "??????marginRight(mm)", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_MARGIN_TOP, value = "??????marginTop(mm)", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_MARGIN_BOTTOM, value = "??????marginBottom(mm)", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_MARGIN_LEFT, value = "??????marginLeft(mm)", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_MARGIN_RIGHT, value = "??????marginRight(mm)", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_WIDTH, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_HEIGHT, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.KEY_BOLD, value = "?????????Key????????????", required = false, dataTypeClass = String.class, example = "0"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.VALUE_BLOD, value = "?????????Value????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetLabelTplVOMeta.PAGE_INDEX, AssetLabelTplVOMeta.PAGE_SIZE })
    @SentinelResource(value = AssetLabelTplServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetLabelTplServiceProxy.QUERY_LIST)
    public Result<List<AssetLabelTpl>> queryList(AssetLabelTplVO sample) {
        Result<List<AssetLabelTpl>> result = new Result<>();
        List<AssetLabelTpl> list = assetLabelTplService.queryList(sample);
        assetLabelTplService.dao().fill(list).with(AssetLabelTplMeta.ASSET_LABEL_ITEM_LIST).execute();
        for (int i = 0; i < list.size(); i++) {
            List<AssetLabelCol> colList = assetLabelService.assetTplJoinTplColumn(list.get(i).getId());
            list.get(i).setAssetLabelColumnlList(colList);
        }
        List<List<AssetLabelTplItem>> itemList = CollectorUtil.collectList(list, AssetLabelTpl::getAssetLabelItemList);
        List<AssetLabelTplItem> items = itemList.stream().collect(ArrayList::new, ArrayList::addAll, ArrayList::addAll);
        assetLabelTplItemService.dao().fill(items).with(AssetLabelTplItemMeta.ASSET_LABEL_COL).execute();
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({
		@ApiImplicitParam(name = AssetLabelTplVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.TYPE, value = "??????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IS_CUSTOM, value = "???????????????", required = false, dataTypeClass = String.class, example = "0"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.COL_IDS, value = "??????", required = false, dataTypeClass = String.class, example = "[1,2,3]"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_POSITION, value = "??????:u", required = false, dataTypeClass = String.class, example = "d"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_COL_ID, value = "??????", required = false, dataTypeClass = String.class, example = "9"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_LABEL_SHOW, value = "??????label??????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.LABEL_FORMAT_CONTENT, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.NOTES, value = "??????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_SHOW, value = "????????????", required = false, dataTypeClass = String.class, example = "1"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_TYPE, value = "????????????txm", required = false, dataTypeClass = String.class, example = "txm"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.LABEL_TABLE_MARGIN_TOP, value = "??????marginTop(mm)", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.LABEL_TABLE_MARGIN_BOTTOM, value = "??????marginBottom(mm)", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.LABEL_TABLE_MARGIN_LEFT, value = "??????marginLeft(mm)", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.LABEL_TABLE_MARGIN_RIGHT, value = "??????marginRight(mm)", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_MARGIN_TOP, value = "??????marginTop(mm)", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_MARGIN_BOTTOM, value = "??????marginBottom(mm)", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_MARGIN_LEFT, value = "??????marginLeft(mm)", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_MARGIN_RIGHT, value = "??????marginRight(mm)", required = false, dataTypeClass = BigDecimal.class, example = "0.00"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_WIDTH, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.IMAGE_HEIGHT, value = "????????????", required = false, dataTypeClass = BigDecimal.class),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.KEY_BOLD, value = "?????????Key????????????", required = false, dataTypeClass = String.class, example = "0"),
		@ApiImplicitParam(name = AssetLabelTplVOMeta.VALUE_BLOD, value = "?????????Value????????????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = AssetLabelTplServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetLabelTplServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<AssetLabelTpl>> queryPagedList(AssetLabelTplVO sample) {
        Result<PagedList<AssetLabelTpl>> result = new Result<>();
        PagedList<AssetLabelTpl> list = assetLabelTplService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = AssetLabelTplServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetLabelTplServiceProxy.EXPORT_EXCEL)
    public void exportExcel(AssetLabelTplVO sample, HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = assetLabelTplService.exportExcel(sample);
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = AssetLabelTplServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetLabelTplServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        try {
            // ?????? Excel ??????
            ExcelWriter ew = assetLabelTplService.exportExcelTemplate();
            // ??????
            DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
        } catch (Exception e) {
            DownloadUtil.writeDownloadError(response, e);
        }
    }

    @SentinelResource(value = AssetLabelTplServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetLabelTplServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = assetLabelTplService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
