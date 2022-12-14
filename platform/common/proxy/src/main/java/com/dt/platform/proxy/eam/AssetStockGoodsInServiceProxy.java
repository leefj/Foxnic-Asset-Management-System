package com.dt.platform.proxy.eam;

import org.springframework.web.bind.annotation.RequestMapping;
import org.github.foxnic.web.proxy.api.APIProxy;
import org.github.foxnic.web.proxy.FeignConfiguration;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.cloud.openfeign.FeignClient;
import com.dt.platform.domain.eam.AssetStockGoodsIn;
import com.dt.platform.domain.eam.AssetStockGoodsInVO;
import java.util.List;
import com.github.foxnic.api.transter.Result;
import com.github.foxnic.dao.data.PagedList;
import com.dt.platform.proxy.ServiceNames;

/**
 * <p>
 * 库存物品单  控制器服务代理
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2022-04-21 13:05:35
 */
@FeignClient(value = ServiceNames.EAM, contextId = AssetStockGoodsInServiceProxy.API_CONTEXT_PATH, configuration = FeignConfiguration.class)
public interface AssetStockGoodsInServiceProxy {

    /**
     * 基础路径 , service-eam
     */
    public static final String API_BASIC_PATH = "service-eam";

    /**
     * API 上下文路径 , eam-asset-stock-goods-in
     */
    public static final String API_CONTEXT_PATH = "eam-asset-stock-goods-in";

    /**
     * API 基础路径 , 由 API_BASIC_PATH 和 API_CONTEXT_PATH 两部分组成
     */
    public static final String API_PREFIX = "/" + API_BASIC_PATH + "/" + API_CONTEXT_PATH + "/";

    /**
     * 添加库存物品单
     */
    public static final String INSERT = API_PREFIX + "insert";

    /**
     * 删除库存物品单
     */
    public static final String DELETE = API_PREFIX + "delete";

    /**
     * 批量删除库存物品单
     */
    public static final String DELETE_BY_IDS = API_PREFIX + "delete-by-ids";

    /**
     * 更新库存物品单
     */
    public static final String UPDATE = API_PREFIX + "update";

    /**
     * 保存库存物品单
     */
    public static final String SAVE = API_PREFIX + "save";

    /**
     * 获取单个库存物品单
     */
    public static final String GET_BY_ID = API_PREFIX + "get-by-id";

    /**
     * 获取多个库存物品单
     */
    public static final String GET_BY_IDS = API_PREFIX + "get-by-ids";

    /**
     * 查询库存物品单
     */
    public static final String QUERY_LIST = API_PREFIX + "query-list";

    /**
     * 分页查询库存物品单
     */
    public static final String QUERY_PAGED_LIST = API_PREFIX + "query-paged-list";

    /**
     * 导出库存物品单数据(Excel)
     */
    public static final String EXPORT_EXCEL = API_PREFIX + "export-excel";

    /**
     * 下载库存物品单导入模版(Excel)
     */
    public static final String EXPORT_EXCEL_TEMPLATE = API_PREFIX + "export-excel-template";

    /**
     * 导入库存物品单数据(Excel)
     */
    public static final String IMPORT_EXCEL = API_PREFIX + "import-excel";


    /**
     * 确认操作
     */
    public static final String CONFIRM_OPERATION = API_PREFIX + "confirm-operation";



    /**
     * 添加库存物品单
     */
    @RequestMapping(AssetStockGoodsInServiceProxy.INSERT)
    Result insert(@RequestParam(name = "assetStockGoodsInVO") AssetStockGoodsInVO assetStockGoodsInVO);

    /**
     * 删除库存物品单
     */
    @RequestMapping(AssetStockGoodsInServiceProxy.DELETE)
    Result deleteById(@RequestParam(name = "id") String id);

    /**
     * 批量删除库存物品单
     */
    @RequestMapping(AssetStockGoodsInServiceProxy.DELETE_BY_IDS)
    Result deleteByIds(@RequestParam(name = "ids") List<String> ids);

    /**
     * 更新库存物品单
     */
    @RequestMapping(AssetStockGoodsInServiceProxy.UPDATE)
    Result update(@RequestParam(name = "assetStockGoodsInVO") AssetStockGoodsInVO assetStockGoodsInVO);

    /**
     * 更新库存物品单
     */
    @RequestMapping(AssetStockGoodsInServiceProxy.SAVE)
    Result save(@RequestParam(name = "assetStockGoodsInVO") AssetStockGoodsInVO assetStockGoodsInVO);

    /**
     * 获取库存物品单
     */
    @RequestMapping(AssetStockGoodsInServiceProxy.GET_BY_ID)
    Result<AssetStockGoodsIn> getById(@RequestParam(name = "id") String id);

    /**
     * 获取多个库存物品单
     */
    @RequestMapping(AssetStockGoodsInServiceProxy.GET_BY_IDS)
    Result<List<AssetStockGoodsIn>> getByIds(@RequestParam(name = "ids") List<String> ids);

    /**
     * 查询库存物品单
     */
    @RequestMapping(AssetStockGoodsInServiceProxy.QUERY_LIST)
    Result<List<AssetStockGoodsIn>> queryList(@RequestParam(name = "sample") AssetStockGoodsInVO sample);

    /**
     * 分页查询库存物品单
     */
    @RequestMapping(AssetStockGoodsInServiceProxy.QUERY_PAGED_LIST)
    Result<PagedList<AssetStockGoodsIn>> queryPagedList(@RequestParam(name = "sample") AssetStockGoodsInVO sample);

    /**
     * 控制器类名
     */
    public static final String CONTROLLER_CLASS_NAME = "com.dt.platform.eam.controller.AssetStockGoodsInController";

    /**
     * 统一的调用接口，实现在单体应用和微服务应用下的无差异调用
     */
    public static AssetStockGoodsInServiceProxy api() {
        return APIProxy.get(AssetStockGoodsInServiceProxy.class, CONTROLLER_CLASS_NAME);
    }
}
