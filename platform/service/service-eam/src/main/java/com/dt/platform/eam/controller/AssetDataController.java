package com.dt.platform.eam.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.deepoove.poi.util.PoitlIOUtils;
import com.dt.platform.constants.enums.common.CodeModuleEnum;
import com.dt.platform.constants.enums.eam.AssetOperateEnum;
import com.dt.platform.constants.enums.eam.AssetOwnerCodeEnum;
import com.dt.platform.domain.eam.Asset;
import com.dt.platform.domain.eam.AssetVO;
import com.dt.platform.domain.eam.meta.AssetMeta;
import com.dt.platform.domain.eam.meta.AssetVOMeta;
import com.dt.platform.eam.service.IAssetDataService;
import com.dt.platform.eam.service.IAssetService;
import com.dt.platform.eam.service.ITplFileService;
import com.dt.platform.proxy.common.TplFileServiceProxy;
import com.dt.platform.proxy.eam.AssetDataServiceProxy;
import com.dt.platform.proxy.eam.OperateServiceProxy;
import com.github.foxnic.api.error.ErrorDesc;
import com.github.foxnic.api.transter.Result;
import com.github.foxnic.api.web.MimeUtil;
import com.github.foxnic.commons.bean.BeanUtil;
import com.github.foxnic.commons.busi.id.IDGenerator;
import com.github.foxnic.commons.io.StreamUtil;
import com.github.foxnic.commons.lang.StringUtil;
import com.github.foxnic.dao.data.PagedList;
import com.github.foxnic.dao.excel.ExcelWriter;
import com.github.foxnic.dao.excel.ValidateResult;
import com.github.foxnic.springboot.web.DownloadUtil;
import com.github.foxnic.sql.expr.ConditionExpr;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.github.foxnic.web.framework.sentinel.SentinelExceptionUtil;
import org.github.foxnic.web.framework.web.SuperController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import com.github.foxnic.commons.collection.CollectorUtil;
import com.github.foxnic.dao.entity.ReferCause;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;

@Api(tags = "?????????????????????")
@ApiSort(0)
@RestController("EAMAssetDataController")
public class AssetDataController extends SuperController {



    @Autowired
    private IAssetDataService assetDataService;

    @Autowired
    private IAssetService assetService;

    /**
     * ??????PCM??????ID
     */
    @ApiOperation(value = "??????PCM??????ID")
    @ApiOperationSupport(order=1)
    @SentinelResource(value = AssetDataServiceProxy.QUERY_PCM_ID_BY_CODE , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
    @PostMapping(AssetDataServiceProxy.QUERY_PCM_ID_BY_CODE)
    public String queryPcmIdByCode(String code) {
       return assetDataService.queryPcmIdByCode(code);
    }

    /**
     * ??????????????????
     */
    @ApiOperation(value = "??????????????????")

    @ApiOperationSupport(order=2)
    @SentinelResource(value = AssetDataServiceProxy.IMPORT_ASSET , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
    @PostMapping(AssetDataServiceProxy.IMPORT_ASSET)
    public Result importAsset( HttpServletResponse response) throws Exception {

        return ErrorDesc.success();

    }
    /**
     * ????????????
     */
    @ApiOperation(value = "????????????")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AssetVOMeta.ID , value = "??????" , required = false , dataTypeClass=String.class),
            @ApiImplicitParam(name = AssetVOMeta.CATEGORY_ID , value = "????????????" , required = false , dataTypeClass=String.class),
            @ApiImplicitParam(name = AssetVOMeta.CATEGORY_CODE , value = "????????????" , required = false , dataTypeClass=String.class),
            @ApiImplicitParam(name = AssetVOMeta.BUSINESS_CODE , value = "????????????" , required = false , dataTypeClass=String.class),
            @ApiImplicitParam(name = AssetVOMeta.PROC_ID , value = "??????" , required = false , dataTypeClass=String.class),
            @ApiImplicitParam(name = AssetVOMeta.STATUS , value = "????????????" , required = false , dataTypeClass=String.class),
            @ApiImplicitParam(name = AssetVOMeta.BATCH_CODE , value = "????????????" , required = false , dataTypeClass=String.class),
            @ApiImplicitParam(name = AssetVOMeta.ASSET_CODE , value = "????????????" , required = false , dataTypeClass=String.class),
            @ApiImplicitParam(name = AssetVOMeta.ASSET_STATUS , value = "????????????" , required = false , dataTypeClass=String.class),
            @ApiImplicitParam(name = AssetVOMeta.DISPLAY , value = "????????????" , required = false , dataTypeClass=String.class),
            @ApiImplicitParam(name = AssetVOMeta.GOODS_ID , value = "??????????????????" , required = false , dataTypeClass=String.class),
            @ApiImplicitParam(name = AssetVOMeta.NAME , value = "????????????????????????" , required = false , dataTypeClass=String.class),
            @ApiImplicitParam(name = AssetVOMeta.MANUFACTURER_ID , value = "??????????????????" , required = false , dataTypeClass=String.class),
            @ApiImplicitParam(name = AssetVOMeta.MODEL , value = "????????????????????????" , required = false , dataTypeClass=String.class),
            @ApiImplicitParam(name = AssetVOMeta.PICTURE_ID , value = "????????????????????????" , required = false , dataTypeClass=String.class),
            @ApiImplicitParam(name = AssetVOMeta.UNIT , value = "????????????????????????" , required = false , dataTypeClass=String.class),
            @ApiImplicitParam(name = AssetVOMeta.SERVICE_LIFE , value = "????????????" , required = false , dataTypeClass= BigDecimal.class),
            @ApiImplicitParam(name = AssetVOMeta.SERIAL_NUMBER , value = "?????????" , required = false , dataTypeClass=String.class),
            @ApiImplicitParam(name = AssetVOMeta.OWN_COMPANY_ID , value = "????????????" , required = false , dataTypeClass=String.class),
            @ApiImplicitParam(name = AssetVOMeta.MANAGER_ID , value = "????????????" , required = false , dataTypeClass=String.class),
            @ApiImplicitParam(name = AssetVOMeta.USE_ORGANIZATION_ID , value = "????????????/??????" , required = false , dataTypeClass=String.class),
            @ApiImplicitParam(name = AssetVOMeta.USE_USER_ID , value = "????????????" , required = false , dataTypeClass=String.class),
            @ApiImplicitParam(name = AssetVOMeta.POSITION_ID , value = "????????????" , required = false , dataTypeClass=String.class),
            @ApiImplicitParam(name = AssetVOMeta.POSITION_DETAIL , value = "????????????" , required = false , dataTypeClass=String.class),
            @ApiImplicitParam(name = AssetVOMeta.WAREHOUSE_ID , value = "??????" , required = false , dataTypeClass=String.class),
            @ApiImplicitParam(name = AssetVOMeta.SOURCE_ID , value = "??????" , required = false , dataTypeClass=String.class),
            @ApiImplicitParam(name = AssetVOMeta.ASSET_NUMBER , value = "????????????" , required = false , dataTypeClass=Integer.class),
            @ApiImplicitParam(name = AssetVOMeta.REMAIN_NUMBER , value = "????????????" , required = false , dataTypeClass=Integer.class),
            @ApiImplicitParam(name = AssetVOMeta.PURCHASE_DATE , value = "????????????" , required = false , dataTypeClass=Date.class),
            @ApiImplicitParam(name = AssetVOMeta.RFID , value = "??????RFID" , required = false , dataTypeClass=String.class),
            @ApiImplicitParam(name = AssetVOMeta.ATTACH , value = "??????" , required = false , dataTypeClass=String.class),
            @ApiImplicitParam(name = AssetVOMeta.ASSET_NOTES , value = "????????????" , required = false , dataTypeClass=String.class),
            @ApiImplicitParam(name = AssetVOMeta.MAINTAINER_ID , value = "????????????" , required = false , dataTypeClass=String.class),
            @ApiImplicitParam(name = AssetVOMeta.MAINTAINER_NAME , value = "????????????" , required = false , dataTypeClass=String.class),
            @ApiImplicitParam(name = AssetVOMeta.CONTACTS , value = "?????????" , required = false , dataTypeClass=String.class),
            @ApiImplicitParam(name = AssetVOMeta.CONTACT_INFORMATION , value = "????????????" , required = false , dataTypeClass=String.class),
            @ApiImplicitParam(name = AssetVOMeta.DIRECTOR , value = "?????????" , required = false , dataTypeClass=String.class),
            @ApiImplicitParam(name = AssetVOMeta.MAINTENANCE_START_DATE , value = "??????????????????" , required = false , dataTypeClass=Date.class),
            @ApiImplicitParam(name = AssetVOMeta.MAINTENANCE_END_DATE , value = "??????????????????" , required = false , dataTypeClass=Date.class),
            @ApiImplicitParam(name = AssetVOMeta.MAINTENANCE_NOTES , value = "????????????" , required = false , dataTypeClass=String.class),
            @ApiImplicitParam(name = AssetVOMeta.FINANCIAL_CATEGORY_ID , value = "????????????" , required = false , dataTypeClass=String.class),
            @ApiImplicitParam(name = AssetVOMeta.FINANCIAL_CODE , value = "????????????" , required = false , dataTypeClass=String.class),
            @ApiImplicitParam(name = AssetVOMeta.SUPPLIER_ID , value = "???????????????" , required = false , dataTypeClass=String.class),
            @ApiImplicitParam(name = AssetVOMeta.ORIGINAL_UNIT_PRICE , value = "????????????(??????)" , required = false , dataTypeClass=BigDecimal.class),
            @ApiImplicitParam(name = AssetVOMeta.ACCUMULATED_DEPRECIATION , value = "????????????" , required = false , dataTypeClass=BigDecimal.class),
            @ApiImplicitParam(name = AssetVOMeta.RESIDUALS_RATE , value = "?????????" , required = false , dataTypeClass=BigDecimal.class),
            @ApiImplicitParam(name = AssetVOMeta.NAV_PRICE , value = "????????????" , required = false , dataTypeClass=BigDecimal.class),
            @ApiImplicitParam(name = AssetVOMeta.PURCHASE_UNIT_PRICE , value = "????????????" , required = false , dataTypeClass=BigDecimal.class),
            @ApiImplicitParam(name = AssetVOMeta.ENTRY_TIME , value = "????????????" , required = false , dataTypeClass=Date.class),
            @ApiImplicitParam(name = AssetVOMeta.FINANCIAL_NOTES , value = "????????????" , required = false , dataTypeClass=String.class),
            @ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_CODE , value = "????????????" , required = false , dataTypeClass=String.class),
            @ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_STATUS , value = "????????????" , required = false , dataTypeClass=String.class),
            @ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_IP , value = "??????IP" , required = false , dataTypeClass=String.class),
            @ApiImplicitParam(name = AssetVOMeta.MANAGE_IP , value = "??????IP" , required = false , dataTypeClass=String.class),
            @ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_CPU , value = "??????CPU" , required = false , dataTypeClass=String.class),
            @ApiImplicitParam(name = AssetVOMeta.EQUIPMENT_MEMORY , value = "????????????" , required = false , dataTypeClass=String.class),
            @ApiImplicitParam(name = AssetVOMeta.RACK_ID , value = "????????????" , required = false , dataTypeClass=String.class),
            @ApiImplicitParam(name = AssetVOMeta.RACK_UP_NUMBER , value = "?????????????????????" , required = false , dataTypeClass=Integer.class),
            @ApiImplicitParam(name = AssetVOMeta.RACK_DOWN_NUMBER , value = "?????????????????????" , required = false , dataTypeClass=Integer.class),
            @ApiImplicitParam(name = AssetVOMeta.LABEL , value = "??????" , required = false , dataTypeClass=String.class),
 })
    @ApiOperationSupport(order=1)
    @SentinelResource(value = AssetDataServiceProxy.EXPORT_ASSET , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
    @PostMapping(AssetDataServiceProxy.EXPORT_ASSET)
    public Result exportAsset(AssetVO sample,HttpServletResponse response) throws Exception {
        return ErrorDesc.success();

    }

    /**
     * ??????ids????????????????????????
     */
    @ApiOperation(value = "????????????ids????????????????????????")

    @ApiOperationSupport(order=2)
    @SentinelResource(value = AssetDataServiceProxy.EXPORT_ASSET_BY_IDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
    @PostMapping(AssetDataServiceProxy.EXPORT_ASSET_BY_IDS)
    public Result exportAssetByIds(List<String> ids, HttpServletResponse response) throws Exception {
        return ErrorDesc.success();

    }

    /**
     * ??????ids????????????????????????
     */
    @ApiOperation(value = "????????????ids????????????????????????")

    @ApiOperationSupport(order=3)
    @SentinelResource(value = AssetDataServiceProxy.PRINT_PDF , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
    @PostMapping(AssetDataServiceProxy.PRINT_PDF)
    public void printPdf(List<String> ids, HttpServletRequest request, HttpServletResponse response) throws Exception {
       // ids=new ArrayList<>();
      //  ids.add("580066766915371008");
        ConditionExpr expr=new ConditionExpr();
        expr.andIn("id",ids);
        List<Asset> list=assetService.queryList(expr);
        Result result=assetDataService.pdfPrint(list);
        if(result.isSuccess()){
            String path = System.getProperty("java.io.tmpdir");
            String id= result.getData().toString();
            String pdfFileName =path +File.separator+ id+".pdf";
            response.setContentType("application/pdf");
            File f=new File(pdfFileName);
            if (!f.exists()) {
                request.setAttribute("error", "PDF????????????");
            }
            InputStream in = null;
            OutputStream os = null;
            try {
                in = new FileInputStream(f);   //?????????????????????????????????
                os = response.getOutputStream();  //???????????????
                byte[] b = new byte[1024];
                while (in.read(b) != -1) {
                    os.write(b);
                }
                in.close();
                os.flush();
                os.close();
            }
            catch (Exception e) {
                try {
                    if (null != in) {
                        in.close();
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                try {
                    if (null != os) {
                        os.close();
                    }
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }

        }
    }


    /**
     * ??????Excel????????????
     */
    @ApiOperation(value = "??????Excel????????????")
    @ApiOperationSupport(order=10)
    @SentinelResource(value = AssetDataServiceProxy.BATCT_IMPORT_ASSET_BY_LUCKY_SHEET , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
    @PostMapping(AssetDataServiceProxy.BATCT_IMPORT_ASSET_BY_LUCKY_SHEET)
    public Result batchImportAsset(String content) {
        //
        return assetDataService.importAssetByLuckySheet(AssetOwnerCodeEnum.ASSET.code(),content,IDGenerator.getSnowflakeIdString());
    }


    /**
     * ????????????Excel????????????????????????
     */
    @ApiOperation(value = "????????????Excel????????????????????????")
    @ApiOperationSupport(order=12)
    @SentinelResource(value = AssetDataServiceProxy.QUERY_ASSET_LUCKY_SHEET , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
    @PostMapping(AssetDataServiceProxy.QUERY_ASSET_LUCKY_SHEET)
    public Result<JSONObject> queryAssetLuckySheet(String oper,int row,String id){
        return assetDataService.queryAssetLuckySheet(oper,row,id);
    }


}
