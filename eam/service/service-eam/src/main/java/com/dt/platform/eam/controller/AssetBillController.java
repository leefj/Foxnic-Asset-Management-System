package com.dt.platform.eam.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.config.ConfigureBuilder;
import com.deepoove.poi.policy.RenderPolicy;
import com.deepoove.poi.template.ElementTemplate;
import com.deepoove.poi.util.PoitlIOUtils;
import com.deepoove.poi.policy.HackLoopTableRenderPolicy;
import com.dt.platform.constants.enums.common.CodeModuleEnum;
import com.dt.platform.constants.enums.eam.AssetOperateEnum;
import com.dt.platform.domain.eam.*;
import com.dt.platform.eam.service.*;
import com.dt.platform.proxy.common.TplFileServiceProxy;
import com.github.foxnic.commons.bean.BeanUtil;
import com.github.foxnic.commons.busi.id.IDGenerator;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.Api;
import org.github.foxnic.web.framework.sentinel.SentinelExceptionUtil;
import org.github.foxnic.web.framework.web.SuperController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dt.platform.proxy.eam.AssetBillServiceProxy;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * <p>
 * 资产单据下载
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2021-09-07 14:32:58
 */
@Api(tags = "资产单据")
@ApiSort(0)
@RestController("EamAssetBillController")
public class AssetBillController extends SuperController {


    @Autowired
    private IAssetBorrowService assetBorrowService;

    @Autowired
    private IAssetAllocationService assetAllocationService;

    @Autowired
    private IAssetCollectionService assetCollectionService;

    @Autowired
    private IAssetService assetService;

    @Autowired
    private IAssetCollectionReturnService assetCollectionReturnService;

    @Autowired
    private IAssetRepairService assetRepairService;

    @Autowired
    private IAssetScrapService assetScrapService;

    @Autowired
    private IAssetTranferService assetTranferService;


    private  InputStream cloneInputStream(InputStream input) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = input.read(buffer)) > -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();
            return new ByteArrayInputStream(baos.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @SentinelResource(value = AssetBillServiceProxy.QUERY_ASSET_REGISTER_BILLS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
    @RequestMapping(AssetBillServiceProxy.QUERY_ASSET_REGISTER_BILLS)
    public void queryAssetRegisterBills(List<String> ids,HttpServletResponse response) throws Exception {

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode("入库单.zip", "UTF-8"))));
        InputStream inputstream= TplFileServiceProxy.api().getTplFileStreamByCode(AssetOperateEnum.EAM_DOWNLOAD_ASSET_REGISTER_BILL.code());
        HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();
        List<Map<String,Object>> list=assetService.getBills(ids);
        OutputStream outputStream = response.getOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
        for(int i=0;i<list.size();i++){
            InputStream ins= TplFileServiceProxy.api().getTplFileStreamByCode(AssetOperateEnum.EAM_DOWNLOAD_ASSET_REGISTER_BILL.code());
            Map<String,Object> map=list.get(i);
            System.out.println("mapdata:"+map.toString());
            XWPFTemplate template = XWPFTemplate.compile( ins ).render(map);
            String assetCode=map.getOrDefault("assetCode", IDGenerator.getSnowflakeIdString()).toString();
            ZipEntry entry = new ZipEntry( assetCode+ ".docx");
            zipOutputStream.putNextEntry(entry);
            template.write(zipOutputStream);
            zipOutputStream.flush();
            template.close();
        }
        zipOutputStream.close();
        outputStream.flush();
        outputStream.close();
    }



    @SentinelResource(value = AssetBillServiceProxy.QUERY_ASSET_TRANSFER_BILL , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
    @RequestMapping(AssetBillServiceProxy.QUERY_ASSET_TRANSFER_BILL)
    public void queryTransferBill(String id,HttpServletResponse response) throws Exception {

        InputStream inputstream= TplFileServiceProxy.api().getTplFileStreamByCode(AssetOperateEnum.EAM_DOWNLOAD_ASSET_TRANFER_BILL.code());

        Map<String,Object> map=assetTranferService.getBill(id);
        HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();
        Configure config = Configure.builder().bind("assetList",policy).build();
        XWPFTemplate template = XWPFTemplate.compile(inputstream,config).render(map);
        response.setContentType("application/msword");
        response.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode("转移单据-"+map.get("businessCode")+".docx", "UTF-8"))));
        OutputStream out = response.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(out);
        template.write(bos);
        bos.flush();
        out.flush();
        PoitlIOUtils.closeQuietlyMulti(template, bos, out);

    }

    @SentinelResource(value = AssetBillServiceProxy.QUERY_ASSET_TRANSFER_BILLS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
    @RequestMapping(AssetBillServiceProxy.QUERY_ASSET_TRANSFER_BILLS)
    public void queryTransferBills(List<String> ids,HttpServletResponse response) throws Exception {

    }


    @SentinelResource(value = AssetBillServiceProxy.QUERY_BORROW_BILLS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
    @RequestMapping(AssetBillServiceProxy.QUERY_BORROW_BILLS)
    public void queryBorrowBills(List<String> ids,HttpServletResponse response) throws Exception {
        InputStream inputstream=TplFileServiceProxy.api().getTplFileStreamByCode(AssetOperateEnum.EAM_DOWNLOAD_ASSET_BORROW_BILL.code());
        HashMap<String, Object> map=new HashMap<String, Object>();
        XWPFTemplate template = XWPFTemplate.compile(inputstream).render(map);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition","attachment;filename=\""+"asset.docx"+"\"");
        OutputStream out = response.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(out);
        template.write(bos);
        bos.flush();
        out.flush();
        PoitlIOUtils.closeQuietlyMulti(template, bos, out);
    }

    @SentinelResource(value = AssetBillServiceProxy.QUERY_BORROW_BILL , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
    @RequestMapping(AssetBillServiceProxy.QUERY_BORROW_BILL)
    public void queryBorrowBill(String id,HttpServletResponse response) throws Exception {

        InputStream inputstream= TplFileServiceProxy.api().getTplFileStreamByCode(AssetOperateEnum.EAM_DOWNLOAD_ASSET_BORROW_BILL.code());
        Map<String,Object> map=assetBorrowService.getBill(id);
        HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();
        Configure config = Configure.builder().bind("assetList",policy).build();
        XWPFTemplate template = XWPFTemplate.compile(inputstream,config).render(map);
        response.setContentType("application/msword");
        response.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode("借用单据-"+map.get("businessCode")+".docx", "UTF-8"))));
        OutputStream out = response.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(out);
        template.write(bos);
        bos.flush();
        out.flush();
        PoitlIOUtils.closeQuietlyMulti(template, bos, out);
    }



    @SentinelResource(value = AssetBillServiceProxy.QUERY_ALLOCATION_BILL , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
    @RequestMapping(AssetBillServiceProxy.QUERY_ALLOCATION_BILL)
    public void queryAllocationBill(String id,HttpServletResponse response) throws Exception {
        InputStream inputstream= TplFileServiceProxy.api().getTplFileStreamByCode(AssetOperateEnum.EAM_DOWNLOAD_ASSET_ALLOCATE_BILL.code());
        Map<String,Object> map=assetAllocationService.getBill(id);
        HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();
        Configure config = Configure.builder().bind("assetList",policy).build();
        XWPFTemplate template = XWPFTemplate.compile(inputstream,config).render(map);
        response.setContentType("application/msword");
        response.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode("调拨单据-"+map.get("businessCode")+".docx", "UTF-8"))));
        OutputStream out = response.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(out);
        template.write(bos);
        bos.flush();
        out.flush();
        PoitlIOUtils.closeQuietlyMulti(template, bos, out);
    }

    @SentinelResource(value = AssetBillServiceProxy.QUERY_ALLOCATION_BILLS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
    @RequestMapping(AssetBillServiceProxy.QUERY_ALLOCATION_BILLS)
    public void queryAllocationBills(String id,HttpServletResponse response) throws Exception {
        InputStream inputstream=TplFileServiceProxy.api().getTplFileStreamByCode(AssetOperateEnum.EAM_DOWNLOAD_ASSET_ALLOCATE_BILL.code());
        Map<String,Object> map=assetAllocationService.getBill(id);
        HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();
        Configure config = Configure.builder().bind("assetList",policy).build();
        XWPFTemplate template = XWPFTemplate.compile(inputstream,config).render(map);
        response.setContentType("application/msword");
        response.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode("调拨单据-"+map.get("businessCode")+".docx", "UTF-8"))));
        OutputStream out = response.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(out);
        template.write(bos);
        bos.flush();
        out.flush();
        PoitlIOUtils.closeQuietlyMulti(template, bos, out);
    }


    @SentinelResource(value = AssetBillServiceProxy.QUERY_COLLECTION_BILL , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
    @RequestMapping(AssetBillServiceProxy.QUERY_COLLECTION_BILL)
    public void queryCollectionBill(String id,HttpServletResponse response) throws Exception {
        InputStream inputstream=TplFileServiceProxy.api().getTplFileStreamByCode(AssetOperateEnum.EAM_DOWNLOAD_ASSET_COLLECTION_BILL.code());
        Map<String,Object> map=assetCollectionService.getBill(id);
        HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();
        Configure config = Configure.builder().bind("assetList",policy).build();
        XWPFTemplate template = XWPFTemplate.compile(inputstream,config).render(map);
        response.setContentType("application/msword");
        response.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode("领用单据-"+map.get("businessCode")+".docx", "UTF-8"))));
        OutputStream out = response.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(out);
        template.write(bos);
        bos.flush();
        out.flush();
        PoitlIOUtils.closeQuietlyMulti(template, bos, out);
    }

    @SentinelResource(value = AssetBillServiceProxy.QUERY_COLLECTION_BILLS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
    @RequestMapping(AssetBillServiceProxy.QUERY_COLLECTION_BILLS)
    public void queryCollectionBills(String id,HttpServletResponse response) throws Exception {
        InputStream inputstream=TplFileServiceProxy.api().getTplFileStreamByCode(AssetOperateEnum.EAM_DOWNLOAD_ASSET_COLLECTION_BILL.code());
        AssetCollection billdata=assetCollectionService.getById(id);
        Map<String,Object> data=new HashMap<String,Object>();
        data=BeanUtil.toMap(billdata);
        System.out.println(data.toString());
        XWPFTemplate template = XWPFTemplate.compile(inputstream).render(data);
        response.setContentType("application/msword");
        response.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode("领用单据-"+billdata.getBusinessCode()+".docx", "UTF-8"))));
        OutputStream out = response.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(out);
        template.write(bos);
        bos.flush();
        out.flush();
        PoitlIOUtils.closeQuietlyMulti(template, bos, out);
    }

    @SentinelResource(value = AssetBillServiceProxy.QUERY_COLLECTION_RETURN_BILL , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
    @RequestMapping(AssetBillServiceProxy.QUERY_COLLECTION_RETURN_BILL)
    public void queryCollectionReturnBill(String id,HttpServletResponse response) throws Exception {
        InputStream inputstream= TplFileServiceProxy.api().getTplFileStreamByCode(AssetOperateEnum.EAM_DOWNLOAD_ASSET_COLLECTION_RETURN_BILL.code());
        Map<String,Object> map=assetCollectionReturnService.getBill(id);
        HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();
        Configure config = Configure.builder().bind("assetList",policy).build();
        XWPFTemplate template = XWPFTemplate.compile(inputstream,config).render(map);
        response.setContentType("application/msword");
        response.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode("领用退库单据-"+map.get("businessCode")+".docx", "UTF-8"))));
        OutputStream out = response.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(out);
        template.write(bos);
        bos.flush();
        out.flush();
        PoitlIOUtils.closeQuietlyMulti(template, bos, out);

    }

    @SentinelResource(value = AssetBillServiceProxy.QUERY_COLLECTION_RETURN_BILLS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
    @RequestMapping(AssetBillServiceProxy.QUERY_COLLECTION_RETURN_BILLS)
    public void queryCollectionReturnBills(String id,HttpServletResponse response) throws Exception {
        InputStream inputstream=TplFileServiceProxy.api().getTplFileStreamByCode(AssetOperateEnum.EAM_DOWNLOAD_ASSET_COLLECTION_RETURN_BILL.code());
        HashMap<String, Object> map=new HashMap<String, Object>();
        map.put("name","121212");
        XWPFTemplate template = XWPFTemplate.compile(inputstream).render(map);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition","attachment;filename=\""+"asset.docx"+"\"");
        OutputStream out = response.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(out);
        template.write(bos);
        bos.flush();
        out.flush();
        PoitlIOUtils.closeQuietlyMulti(template, bos, out);
    }



    @SentinelResource(value = AssetBillServiceProxy.QUERY_REAPIR_BILL , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
    @RequestMapping(AssetBillServiceProxy.QUERY_REAPIR_BILL)
    public void queryRepairBill(String id,HttpServletResponse response) throws Exception {

        InputStream inputstream= TplFileServiceProxy.api().getTplFileStreamByCode(AssetOperateEnum.EAM_DOWNLOAD_ASSET_REPAIR_BILL.code());
        Map<String,Object> map=assetRepairService.getBill(id);
        HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();
        Configure config = Configure.builder().bind("assetList",policy).build();
        XWPFTemplate template = XWPFTemplate.compile(inputstream,config).render(map);
        response.setContentType("application/msword");
        response.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode("维修单据-"+map.get("businessCode")+".docx", "UTF-8"))));
        OutputStream out = response.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(out);
        template.write(bos);
        bos.flush();
        out.flush();
        PoitlIOUtils.closeQuietlyMulti(template, bos, out);



    }

    @SentinelResource(value = AssetBillServiceProxy.QUERY_REAPIR_BILLS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
    @RequestMapping(AssetBillServiceProxy.QUERY_REAPIR_BILLS)
    public void queryRepairBills(String id,HttpServletResponse response) throws Exception {
        InputStream inputstream=TplFileServiceProxy.api().getTplFileStreamByCode(AssetOperateEnum.EAM_DOWNLOAD_ASSET_REPAIR_BILL.code());
        AssetRepair billdata=assetRepairService.getById(id);
        Map<String,Object> data=new HashMap<String,Object>();
        data=BeanUtil.toMap(billdata);
        System.out.println(data.toString());
        XWPFTemplate template = XWPFTemplate.compile(inputstream).render(data);
        response.setContentType("application/msword");
        response.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode("维修单据-"+billdata.getBusinessCode()+".docx", "UTF-8"))));
        OutputStream out = response.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(out);
        template.write(bos);
        bos.flush();
        out.flush();
        PoitlIOUtils.closeQuietlyMulti(template, bos, out);
    }

    @SentinelResource(value = AssetBillServiceProxy.QUERY_SCRAP_BILL , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
    @RequestMapping(AssetBillServiceProxy.QUERY_SCRAP_BILL)
    public void queryScrapBill(String id,HttpServletResponse response) throws Exception {

        InputStream inputstream= TplFileServiceProxy.api().getTplFileStreamByCode(AssetOperateEnum.EAM_DOWNLOAD_ASSET_SCRAP_BILL.code());
        Map<String,Object> map=assetScrapService.getBill(id);
        HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();
        Configure config = Configure.builder().bind("assetList",policy).build();
        XWPFTemplate template = XWPFTemplate.compile(inputstream,config).render(map);
        response.setContentType("application/msword");
        response.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode("报废单据-"+map.get("businessCode")+".docx", "UTF-8"))));
        OutputStream out = response.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(out);
        template.write(bos);
        bos.flush();
        out.flush();
        PoitlIOUtils.closeQuietlyMulti(template, bos, out);


    }

    @SentinelResource(value = AssetBillServiceProxy.QUERY_SCRAP_BILLS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
    @RequestMapping(AssetBillServiceProxy.QUERY_SCRAP_BILLS)
    public void queryScrapBills(String id,HttpServletResponse response) throws Exception {
        InputStream inputstream=TplFileServiceProxy.api().getTplFileStreamByCode(AssetOperateEnum.EAM_DOWNLOAD_ASSET_SCRAP_BILL.code());
        HashMap<String, Object> map=new HashMap<String, Object>();
        map.put("name","121212");
        XWPFTemplate template = XWPFTemplate.compile(inputstream).render(map);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition","attachment;filename=\""+"asset.docx"+"\"");
        OutputStream out = response.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(out);
        template.write(bos);
        bos.flush();
        out.flush();
        PoitlIOUtils.closeQuietlyMulti(template, bos, out);
    }


    @SentinelResource(value = AssetBillServiceProxy.QUERY_ASSET_CARD , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
    @RequestMapping(AssetBillServiceProxy.QUERY_ASSET_CARD)
    public void queryAssetCard(String id,HttpServletResponse response) throws Exception {
        InputStream inputstream=TplFileServiceProxy.api().getTplFileStreamByCode(AssetOperateEnum.EAM_DOWNLOAD_ASSET_CARD.code());
        Asset billdata=assetService.getById(id);
        Map<String,Object> data=new HashMap<String,Object>();
        data=BeanUtil.toMap(billdata);
        System.out.println(data.toString());
        XWPFTemplate template = XWPFTemplate.compile(inputstream).render(data);
        response.setContentType("application/msword");
        response.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode("资产卡片-"+billdata.getAssetCode()+".docx", "UTF-8"))));
        OutputStream out = response.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(out);
        template.write(bos);
        bos.flush();
        out.flush();
        PoitlIOUtils.closeQuietlyMulti(template, bos, out);
    }


    @SentinelResource(value = AssetBillServiceProxy.QUERY_ASSET_CARDS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
    @RequestMapping(AssetBillServiceProxy.QUERY_ASSET_CARDS)
    public void queryAssetCards(String id,HttpServletResponse response) throws Exception {
        InputStream inputstream=TplFileServiceProxy.api().getTplFileStreamByCode(AssetOperateEnum.EAM_DOWNLOAD_ASSET_CARD.code());
        HashMap<String, Object> map=new HashMap<String, Object>();
        map.put("name","121212");
        XWPFTemplate template = XWPFTemplate.compile(inputstream).render(map);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition","attachment;filename=\""+"asset.docx"+"\"");
        OutputStream out = response.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(out);
        template.write(bos);
        bos.flush();
        out.flush();
        PoitlIOUtils.closeQuietlyMulti(template, bos, out);
    }


    @SentinelResource(value = AssetBillServiceProxy.QUERY_ASSET_LABEL , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
    @RequestMapping(AssetBillServiceProxy.QUERY_ASSET_LABEL)
    public void queryAssetLabel(String id,HttpServletResponse response) throws Exception {
        InputStream inputstream=TplFileServiceProxy.api().getTplFileStreamByCode(AssetOperateEnum.EAM_DOWNLOAD_ASSET_LABEl.code());
        Asset billdata=assetService.getById(id);
        Map<String,Object> data=new HashMap<String,Object>();
        data=BeanUtil.toMap(billdata);
        System.out.println(data.toString());
        XWPFTemplate template = XWPFTemplate.compile(inputstream).render(data);
        response.setContentType("application/msword");
        response.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode("资产标签-"+billdata.getAssetCode()+".docx", "UTF-8"))));
        OutputStream out = response.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(out);
        template.write(bos);
        bos.flush();
        out.flush();
        PoitlIOUtils.closeQuietlyMulti(template, bos, out);
    }


    @SentinelResource(value = AssetBillServiceProxy.QUERY_ASSET_LABELS , blockHandlerClass = { SentinelExceptionUtil.class } , blockHandler = SentinelExceptionUtil.HANDLER )
    @RequestMapping(AssetBillServiceProxy.QUERY_ASSET_LABELS)
    public void queryAssetLabels(String id,HttpServletResponse response) throws Exception {
        InputStream inputstream=TplFileServiceProxy.api().getTplFileStreamByCode(AssetOperateEnum.EAM_DOWNLOAD_ASSET_LABEl.code());
        HashMap<String, Object> map=new HashMap<String, Object>();
        map.put("name","121212");
        XWPFTemplate template = XWPFTemplate.compile(inputstream).render(map);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition","attachment;filename=\""+"asset.docx"+"\"");
        OutputStream out = response.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(out);
        template.write(bos);
        bos.flush();
        out.flush();
        PoitlIOUtils.closeQuietlyMulti(template, bos, out);
    }


}
