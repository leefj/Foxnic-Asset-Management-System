package com.dt.platform.proxy.eam;

import org.springframework.web.bind.annotation.RequestMapping;
import org.github.foxnic.web.proxy.api.APIProxy;
import org.github.foxnic.web.proxy.FeignConfiguration;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.cloud.openfeign.FeignClient;
import com.dt.platform.domain.eam.AssetSoftwareDistribute;
import com.dt.platform.domain.eam.AssetSoftwareDistributeVO;
import java.util.List;
import com.github.foxnic.api.transter.Result;
import com.github.foxnic.dao.data.PagedList;
import com.dt.platform.proxy.ServiceNames;

/**
 * <p>
 * 软件分发  控制器服务代理
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2022-05-04 20:10:32
 */
@FeignClient(value = ServiceNames.EAM, contextId = AssetSoftwareDistributeServiceProxy.API_CONTEXT_PATH, configuration = FeignConfiguration.class)
public interface AssetSoftwareDistributeServiceProxy {

    /**
     * 基础路径 , service-eam
     */
    public static final String API_BASIC_PATH = "service-eam";

    /**
     * API 上下文路径 , eam-asset-software-distribute
     */
    public static final String API_CONTEXT_PATH = "eam-asset-software-distribute";

    /**
     * API 基础路径 , 由 API_BASIC_PATH 和 API_CONTEXT_PATH 两部分组成
     */
    public static final String API_PREFIX = "/" + API_BASIC_PATH + "/" + API_CONTEXT_PATH + "/";

    /**
     * 添加软件分发
     */
    public static final String INSERT = API_PREFIX + "insert";

    /**
     * 删除软件分发
     */
    public static final String DELETE = API_PREFIX + "delete";

    /**
     * 批量删除软件分发
     */
    public static final String DELETE_BY_IDS = API_PREFIX + "delete-by-ids";

    /**
     * 更新软件分发
     */
    public static final String UPDATE = API_PREFIX + "update";

    /**
     * 保存软件分发
     */
    public static final String SAVE = API_PREFIX + "save";

    /**
     * 获取单个软件分发
     */
    public static final String GET_BY_ID = API_PREFIX + "get-by-id";

    /**
     * 获取多个软件分发
     */
    public static final String GET_BY_IDS = API_PREFIX + "get-by-ids";

    /**
     * 查询软件分发
     */
    public static final String QUERY_LIST = API_PREFIX + "query-list";

    /**
     * 分页查询软件分发
     */
    public static final String QUERY_PAGED_LIST = API_PREFIX + "query-paged-list";

    /**
     * 导出软件分发数据(Excel)
     */
    public static final String EXPORT_EXCEL = API_PREFIX + "export-excel";

    /**
     * 下载软件分发导入模版(Excel)
     */
    public static final String EXPORT_EXCEL_TEMPLATE = API_PREFIX + "export-excel-template";

    /**
     * 导入软件分发数据(Excel)
     */
    public static final String IMPORT_EXCEL = API_PREFIX + "import-excel";



    /**
     * 确认操作
     */
    public static final String CONFIRM_OPERATION = API_PREFIX + "confirm-operation";

    /**
     * 添加软件分发
     */
    @RequestMapping(AssetSoftwareDistributeServiceProxy.INSERT)
    Result insert(@RequestParam(name = "assetSoftwareDistributeVO") AssetSoftwareDistributeVO assetSoftwareDistributeVO);

    /**
     * 删除软件分发
     */
    @RequestMapping(AssetSoftwareDistributeServiceProxy.DELETE)
    Result deleteById(@RequestParam(name = "id") String id);

    /**
     * 批量删除软件分发
     */
    @RequestMapping(AssetSoftwareDistributeServiceProxy.DELETE_BY_IDS)
    Result deleteByIds(@RequestParam(name = "ids") List<String> ids);

    /**
     * 更新软件分发
     */
    @RequestMapping(AssetSoftwareDistributeServiceProxy.UPDATE)
    Result update(@RequestParam(name = "assetSoftwareDistributeVO") AssetSoftwareDistributeVO assetSoftwareDistributeVO);

    /**
     * 更新软件分发
     */
    @RequestMapping(AssetSoftwareDistributeServiceProxy.SAVE)
    Result save(@RequestParam(name = "assetSoftwareDistributeVO") AssetSoftwareDistributeVO assetSoftwareDistributeVO);

    /**
     * 获取软件分发
     */
    @RequestMapping(AssetSoftwareDistributeServiceProxy.GET_BY_ID)
    Result<AssetSoftwareDistribute> getById(@RequestParam(name = "id") String id);

    /**
     * 获取多个软件分发
     */
    @RequestMapping(AssetSoftwareDistributeServiceProxy.GET_BY_IDS)
    Result<List<AssetSoftwareDistribute>> getByIds(@RequestParam(name = "ids") List<String> ids);

    /**
     * 查询软件分发
     */
    @RequestMapping(AssetSoftwareDistributeServiceProxy.QUERY_LIST)
    Result<List<AssetSoftwareDistribute>> queryList(@RequestParam(name = "sample") AssetSoftwareDistributeVO sample);

    /**
     * 分页查询软件分发
     */
    @RequestMapping(AssetSoftwareDistributeServiceProxy.QUERY_PAGED_LIST)
    Result<PagedList<AssetSoftwareDistribute>> queryPagedList(@RequestParam(name = "sample") AssetSoftwareDistributeVO sample);

    /**
     * 控制器类名
     */
    public static final String CONTROLLER_CLASS_NAME = "com.dt.platform.eam.controller.AssetSoftwareDistributeController";

    /**
     * 统一的调用接口，实现在单体应用和微服务应用下的无差异调用
     */
    public static AssetSoftwareDistributeServiceProxy api() {
        return APIProxy.get(AssetSoftwareDistributeServiceProxy.class, CONTROLLER_CLASS_NAME);
    }
}
