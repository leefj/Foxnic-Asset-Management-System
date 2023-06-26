package com.dt.platform.proxy.eam;

import org.springframework.web.bind.annotation.RequestMapping;
import org.github.foxnic.web.proxy.api.APIProxy;
import org.github.foxnic.web.proxy.FeignConfiguration;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import com.dt.platform.domain.eam.RfidLabel;
import com.dt.platform.domain.eam.RfidLabelVO;
import java.util.List;
import com.github.foxnic.api.transter.Result;
import com.github.foxnic.dao.data.PagedList;
import com.dt.platform.proxy.ServiceNames;

/**
 * <p>
 * RFID标签 控制器服务代理
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-06-23 18:16:25
 */
@FeignClient(value = ServiceNames.EAM, contextId = RfidLabelServiceProxy.API_CONTEXT_PATH, configuration = FeignConfiguration.class)
public interface RfidLabelServiceProxy {

    /**
     * 基础路径 , service-eam
     */
    public static final String API_BASIC_PATH = "service-eam";

    /**
     * API 上下文路径 , eam-rfid-label
     */
    public static final String API_CONTEXT_PATH = "eam-rfid-label";

    /**
     * API 基础路径 , 由 API_BASIC_PATH 和 API_CONTEXT_PATH 两部分组成
     */
    public static final String API_PREFIX = "/" + API_BASIC_PATH + "/" + API_CONTEXT_PATH + "/";

    /**
     * 添加RFID标签
     */
    public static final String INSERT = API_PREFIX + "insert";

    /**
     * 删除RFID标签
     */
    public static final String DELETE = API_PREFIX + "delete";

    /**
     * 批量删除RFID标签
     */
    public static final String DELETE_BY_IDS = API_PREFIX + "delete-by-ids";

    /**
     * 更新RFID标签
     */
    public static final String UPDATE = API_PREFIX + "update";

    /**
     * 保存RFID标签
     */
    public static final String SAVE = API_PREFIX + "save";

    /**
     * 获取单个RFID标签
     */
    public static final String GET_BY_ID = API_PREFIX + "get-by-id";

    /**
     * 获取多个RFID标签
     */
    public static final String GET_BY_IDS = API_PREFIX + "get-by-ids";

    /**
     * 查询RFID标签
     */
    public static final String QUERY_LIST = API_PREFIX + "query-list";

    /**
     * 分页查询RFID标签
     */
    public static final String QUERY_PAGED_LIST = API_PREFIX + "query-paged-list";

    /**
     * 导出RFID标签数据(Excel)
     */
    public static final String EXPORT_EXCEL = API_PREFIX + "export-excel";

    /**
     * 下载RFID标签导入模版(Excel)
     */
    public static final String EXPORT_EXCEL_TEMPLATE = API_PREFIX + "export-excel-template";

    /**
     * 导入RFID标签数据(Excel)
     */
    public static final String IMPORT_EXCEL = API_PREFIX + "import-excel";

    /**
     * 导入RFID标签数据(Excel)
     */
    public static final String READ = API_PREFIX + "read";

    /**
     * 发卡
     */
    public static final String RELEASE = API_PREFIX + "release";
    /**
     * 添加RFID标签
     */
    @RequestMapping(RfidLabelServiceProxy.INSERT)
    Result insert(@RequestParam(name = "rfidLabelVO") RfidLabelVO rfidLabelVO);

    /**
     * 删除RFID标签
     */
    @RequestMapping(RfidLabelServiceProxy.DELETE)
    Result deleteById(@RequestParam(name = "id") String id);

    /**
     * 批量删除RFID标签
     */
    @RequestMapping(RfidLabelServiceProxy.DELETE_BY_IDS)
    Result deleteByIds(@RequestParam(name = "ids") List<String> ids);

    /**
     * 更新RFID标签
     */
    @RequestMapping(RfidLabelServiceProxy.UPDATE)
    Result update(@RequestParam(name = "rfidLabelVO") RfidLabelVO rfidLabelVO);

    /**
     * 更新RFID标签
     */
    @RequestMapping(RfidLabelServiceProxy.SAVE)
    Result save(@RequestParam(name = "rfidLabelVO") RfidLabelVO rfidLabelVO);

    /**
     * 获取RFID标签
     */
    @RequestMapping(RfidLabelServiceProxy.GET_BY_ID)
    Result<RfidLabel> getById(@RequestParam(name = "id") String id);

    /**
     * 获取多个RFID标签
     */
    @RequestMapping(RfidLabelServiceProxy.GET_BY_IDS)
    Result<List<RfidLabel>> getByIds(@RequestParam(name = "ids") List<String> ids);

    /**
     * 查询RFID标签
     */
    @RequestMapping(RfidLabelServiceProxy.QUERY_LIST)
    Result<List<RfidLabel>> queryList(@RequestParam(name = "sample") RfidLabelVO sample);

    /**
     * 导出 Excel
     */
    @RequestMapping(RfidLabelServiceProxy.EXPORT_EXCEL)
    void exportExcel(@RequestParam(name = "sample") RfidLabelVO sample, @RequestParam(name = "response") HttpServletResponse response) throws Exception;

    /**
     * 导出 Excel 模板
     */
    @RequestMapping(RfidLabelServiceProxy.EXPORT_EXCEL_TEMPLATE)
    void exportExcelTemplate(@RequestParam(name = "response") HttpServletResponse response) throws Exception;

    /**
     * 导入 Excel
     */
    @RequestMapping(RfidLabelServiceProxy.IMPORT_EXCEL)
    Result importExcel(@RequestParam(name = "request") MultipartHttpServletRequest request, @RequestParam(name = "response") HttpServletResponse response) throws Exception;

    /**
     * 分页查询RFID标签
     */
    @RequestMapping(RfidLabelServiceProxy.QUERY_PAGED_LIST)
    Result<PagedList<RfidLabel>> queryPagedList(@RequestParam(name = "sample") RfidLabelVO sample);

    /**
     * 控制器类名
     */
    public static final String CONTROLLER_CLASS_NAME = "com.dt.platform.eam.controller.RfidLabelController";

    /**
     * 统一的调用接口，实现在单体应用和微服务应用下的无差异调用
     */
    public static RfidLabelServiceProxy api() {
        return APIProxy.get(RfidLabelServiceProxy.class, CONTROLLER_CLASS_NAME);
    }
}
