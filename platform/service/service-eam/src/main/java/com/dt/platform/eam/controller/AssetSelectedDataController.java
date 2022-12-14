package com.dt.platform.eam.controller;

import java.util.ArrayList;
import java.util.List;

import com.dt.platform.constants.enums.eam.AssetInventoryDetailStatusEnum;
import com.dt.platform.constants.enums.eam.AssetOperateEnum;
import com.dt.platform.domain.eam.AssetItem;
import com.dt.platform.domain.eam.AssetItemVO;
import com.dt.platform.eam.service.IAssetItemService;
import com.github.foxnic.commons.busi.id.IDGenerator;
import com.github.foxnic.commons.lang.StringUtil;
import com.github.foxnic.dao.data.Rcd;
import com.github.foxnic.sql.expr.ConditionExpr;
import com.github.foxnic.commons.collection.CollectorUtil;
import com.github.foxnic.dao.entity.ReferCause;
import com.github.foxnic.sql.expr.Insert;
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
import com.dt.platform.proxy.eam.AssetSelectedDataServiceProxy;
import com.dt.platform.domain.eam.meta.AssetSelectedDataVOMeta;
import com.dt.platform.domain.eam.AssetSelectedData;
import com.dt.platform.domain.eam.AssetSelectedDataVO;
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
import com.dt.platform.domain.eam.meta.AssetSelectedDataMeta;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dt.platform.eam.service.IAssetSelectedDataService;
import com.github.foxnic.api.swagger.ApiParamSupport;

/**
 * <p>
 * ???????????? ???????????????
 * </p>
 * @author ?????? , maillank@qq.com
 * @since 2021-09-20 19:01:00
 */
@Api(tags = "????????????")
@ApiSort(0)
@RestController("EamAssetSelectedDataController")
public class AssetSelectedDataController extends SuperController {

    @Autowired
    private IAssetSelectedDataService assetSelectedDataService;

    @Autowired
    private IAssetItemService assetItemService;

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetSelectedDataVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSelectedDataVOMeta.ASSET_SELECTED_CODE, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSelectedDataVOMeta.ASSET_ID, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 1)
    @SentinelResource(value = AssetSelectedDataServiceProxy.INSERT, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetSelectedDataServiceProxy.INSERT)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true, ignorePrimaryKey = true)
    public Result insert(AssetSelectedDataVO assetSelectedDataVO) {
        Result result = assetSelectedDataService.insert(assetSelectedDataVO);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetSelectedDataVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 2)
    @SentinelResource(value = AssetSelectedDataServiceProxy.DELETE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetSelectedDataServiceProxy.DELETE)
    public Result deleteById(String id) {
        Result result = assetSelectedDataService.deleteByIdLogical(id);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetSelectedDataVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]")
	})
    @ApiOperationSupport(order = 3)
    @SentinelResource(value = AssetSelectedDataServiceProxy.DELETE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetSelectedDataServiceProxy.DELETE_BY_IDS)
    public Result deleteByIds(List<String> ids, String assetSelectedCode, String assetOwnerId) {
        if (assetOwnerId != null && assetOwnerId.length() > 0) {
            AssetItemVO vo = new AssetItemVO();
            vo.setHandleId(assetOwnerId);
            ConditionExpr q = new ConditionExpr();
            q.andInIf("asset_id", ids);
            q.and("handle_id=?", assetOwnerId);
            List<AssetItem> list = assetItemService.queryList(q);
            for (int i = 0; i < list.size(); i++) {
                if ("r".equals(list.get(i).getCrd())) {
                    list.get(i).setCrd("d");
                } else {
                    list.get(i).setCrd("cd");
                }
            }
            return assetItemService.updateList(list, SaveMode.NOT_NULL_FIELDS);
        } else {
            List<String> deleteList = new ArrayList<>();
            AssetSelectedDataVO vo = new AssetSelectedDataVO();
            vo.setAssetSelectedCode(assetSelectedCode);
            ConditionExpr q = new ConditionExpr();
            q.andInIf("asset_id", ids);
            q.and("asset_selected_code=?", assetSelectedCode);
            List<AssetSelectedData> list = assetSelectedDataService.queryList(q);
            for (int i = 0; i < list.size(); i++) {
                deleteList.add(list.get(i).getId());
            }
            return assetSelectedDataService.deleteByIdsLogical(deleteList);
        }
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetSelectedDataVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSelectedDataVOMeta.ASSET_SELECTED_CODE, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSelectedDataVOMeta.ASSET_ID, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 4, ignoreParameters = { AssetSelectedDataVOMeta.PAGE_INDEX, AssetSelectedDataVOMeta.PAGE_SIZE, AssetSelectedDataVOMeta.SEARCH_FIELD, AssetSelectedDataVOMeta.FUZZY_FIELD, AssetSelectedDataVOMeta.SEARCH_VALUE, AssetSelectedDataVOMeta.SORT_FIELD, AssetSelectedDataVOMeta.SORT_TYPE, AssetSelectedDataVOMeta.IDS })
    @SentinelResource(value = AssetSelectedDataServiceProxy.UPDATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetSelectedDataServiceProxy.UPDATE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result update(AssetSelectedDataVO assetSelectedDataVO) {
        Result result = assetSelectedDataService.update(assetSelectedDataVO, SaveMode.NOT_NULL_FIELDS);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetSelectedDataVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSelectedDataVOMeta.ASSET_SELECTED_CODE, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSelectedDataVOMeta.ASSET_ID, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetSelectedDataVOMeta.PAGE_INDEX, AssetSelectedDataVOMeta.PAGE_SIZE, AssetSelectedDataVOMeta.SEARCH_FIELD, AssetSelectedDataVOMeta.FUZZY_FIELD, AssetSelectedDataVOMeta.SEARCH_VALUE, AssetSelectedDataVOMeta.SORT_FIELD, AssetSelectedDataVOMeta.SORT_TYPE, AssetSelectedDataVOMeta.IDS })
    @SentinelResource(value = AssetSelectedDataServiceProxy.SAVE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetSelectedDataServiceProxy.SAVE)
    @ApiParamSupport(ignoreDBTreatyProperties = true, ignoreDefaultVoProperties = true)
    public Result save(AssetSelectedDataVO assetSelectedDataVO) {
        Result result = assetSelectedDataService.save(assetSelectedDataVO, SaveMode.NOT_NULL_FIELDS);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetSelectedDataVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class, example = "1")
	})
    @ApiOperationSupport(order = 6)
    @SentinelResource(value = AssetSelectedDataServiceProxy.GET_BY_ID, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetSelectedDataServiceProxy.GET_BY_ID)
    public Result<AssetSelectedData> getById(String id) {
        Result<AssetSelectedData> result = new Result<>();
        AssetSelectedData assetSelectedData = assetSelectedDataService.getById(id);
        result.success(true).data(assetSelectedData);
        return result;
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetSelectedDataVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSelectedDataVOMeta.ASSET_SELECTED_CODE, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSelectedDataVOMeta.ASSET_ID, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 5, ignoreParameters = { AssetSelectedDataVOMeta.PAGE_INDEX, AssetSelectedDataVOMeta.PAGE_SIZE })
    @SentinelResource(value = AssetSelectedDataServiceProxy.QUERY_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetSelectedDataServiceProxy.QUERY_LIST)
    public Result<List<AssetSelectedData>> queryList(AssetSelectedDataVO sample) {
        Result<List<AssetSelectedData>> result = new Result<>();
        List<AssetSelectedData> list = assetSelectedDataService.queryList(sample);
        result.success(true).data(list);
        return result;
    }

    /**
     * ????????????????????????
     */
    @ApiOperation(value = "????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetSelectedDataVOMeta.ID, value = "??????", required = true, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSelectedDataVOMeta.ASSET_SELECTED_CODE, value = "??????????????????", required = false, dataTypeClass = String.class),
		@ApiImplicitParam(name = AssetSelectedDataVOMeta.ASSET_ID, value = "??????", required = false, dataTypeClass = String.class)
	})
    @ApiOperationSupport(order = 8)
    @SentinelResource(value = AssetSelectedDataServiceProxy.QUERY_PAGED_LIST, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetSelectedDataServiceProxy.QUERY_PAGED_LIST)
    public Result<PagedList<AssetSelectedData>> queryPagedList(AssetSelectedDataVO sample) {
        Result<PagedList<AssetSelectedData>> result = new Result<>();
        PagedList<AssetSelectedData> list = assetSelectedDataService.queryPagedList(sample, sample.getPageSize(), sample.getPageIndex());
        result.success(true).data(list);
        return result;
    }

    /**
     * ???????????????????????? <br>
     * ???????????????????????????????????????
     */
    @ApiOperation(value = "???????????????????????????")
    @ApiImplicitParams({ 
		@ApiImplicitParam(name = AssetSelectedDataVOMeta.IDS, value = "????????????", required = true, dataTypeClass = List.class, example = "[1,3,4]"),
		@ApiImplicitParam(name = AssetSelectedDataVOMeta.ASSET_SELECTED_CODE, value = "??????", required = true, dataTypeClass = List.class, example = "1234")
	})
    @ApiOperationSupport(order = 9)
    @SentinelResource(value = AssetSelectedDataServiceProxy.SAVE_BY_IDS, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @PostMapping(AssetSelectedDataServiceProxy.SAVE_BY_IDS)
    public Result saveByIds(List<String> ids, String assetSelectedCode, String assetOwnerId,String assetBusinessType) {


        if(!StringUtil.isBlank(assetBusinessType)){
            //????????????
            if(AssetOperateEnum.EAM_ASSET_INVENTORY.code().equals(assetBusinessType)){
                //assetSelectedCode???inventory_id,?????????????????????
                for(int i=0;i<ids.size();i++){
                   Rcd rs= assetItemService.dao().queryRecord("select * from eam_inventory_asset where deleted=0 and inventory_id=? and asset_id=?",assetSelectedCode,ids.get(i));
                   if(rs==null){
                       Insert ins=new Insert("eam_inventory_asset");
                       ins.set("id",IDGenerator.getSnowflakeIdString());
                       ins.setIf("inventory_id",assetSelectedCode);
                       ins.setIf("asset_id",ids.get(i));
                       ins.setIf("status", AssetInventoryDetailStatusEnum.NOT_COUNTED.code());
                       ins.setIf("asset_plus_action_type","none");
                       ins.setIf("asset_loss_action_type","none");
                       ins.setIf("source","asset");
                       assetItemService.dao().execute(ins);
                   }
                }
                return ErrorDesc.success();
            }
        }


        //??????ownerId
        if (assetOwnerId != null && assetOwnerId.length() > 0) {
            List<AssetItem> list = new ArrayList<>();
            for (int i = 0; i < ids.size(); i++) {
                AssetItem obj = new AssetItem();
                obj.setId(IDGenerator.getSnowflakeIdString());
                obj.setAssetId(ids.get(i));
                obj.setCrd("c");
                obj.setHandleId(assetOwnerId);
                obj.setSelectedCode(assetSelectedCode);
                list.add(obj);
            }
            return assetItemService.insertList(list);
        } else {
            //?????????ownerId
            List<AssetSelectedData> list = new ArrayList<>();
            for (int i = 0; i < ids.size(); i++) {
                AssetSelectedData obj = new AssetSelectedData();
                obj.setId(IDGenerator.getSnowflakeIdString());
                obj.setAssetSelectedCode(assetSelectedCode);
                obj.setAssetId(ids.get(i));
                list.add(obj);
            }
            return assetSelectedDataService.insertList(list);
        }
    }

    /**
     * ?????? Excel
     */
    @SentinelResource(value = AssetSelectedDataServiceProxy.EXPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetSelectedDataServiceProxy.EXPORT_EXCEL)
    public void exportExcel(AssetSelectedDataVO sample, HttpServletResponse response) throws Exception {
        // ?????? Excel ??????
        ExcelWriter ew = assetSelectedDataService.exportExcel(sample);
        // ??????
        DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
    }

    /**
     * ?????? Excel ??????
     */
    @SentinelResource(value = AssetSelectedDataServiceProxy.EXPORT_EXCEL_TEMPLATE, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetSelectedDataServiceProxy.EXPORT_EXCEL_TEMPLATE)
    public void exportExcelTemplate(HttpServletResponse response) throws Exception {
        // ?????? Excel ??????
        ExcelWriter ew = assetSelectedDataService.exportExcelTemplate();
        // ??????
        DownloadUtil.writeToOutput(response, ew.getWorkBook(), ew.getWorkBookName());
    }

    @SentinelResource(value = AssetSelectedDataServiceProxy.IMPORT_EXCEL, blockHandlerClass = { SentinelExceptionUtil.class }, blockHandler = SentinelExceptionUtil.HANDLER)
    @RequestMapping(AssetSelectedDataServiceProxy.IMPORT_EXCEL)
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
        List<ValidateResult> errors = assetSelectedDataService.importExcel(input, 0, true);
        if (errors == null || errors.isEmpty()) {
            return ErrorDesc.success();
        } else {
            return ErrorDesc.failure().message("????????????").data(errors);
        }
    }
}
