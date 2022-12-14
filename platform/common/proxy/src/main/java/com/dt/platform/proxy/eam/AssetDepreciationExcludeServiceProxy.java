package com.dt.platform.proxy.eam;

import org.springframework.web.bind.annotation.RequestMapping;
import org.github.foxnic.web.proxy.api.APIProxy;
import org.github.foxnic.web.proxy.FeignConfiguration;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import com.dt.platform.domain.eam.AssetDepreciationExclude;
import com.dt.platform.domain.eam.AssetDepreciationExcludeVO;
import java.util.List;
import com.github.foxnic.api.transter.Result;
import com.github.foxnic.dao.data.PagedList;
import com.dt.platform.proxy.ServiceNames;

/**
 * <p>
 * 折旧排除 控制器服务代理
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2022-11-28 20:42:05
 */
@FeignClient(value = ServiceNames.EAM, contextId = AssetDepreciationExcludeServiceProxy.API_CONTEXT_PATH, configuration = FeignConfiguration.class)
public interface AssetDepreciationExcludeServiceProxy {

    /**
     * 基础路径 , service-eam
     */
    public static final String API_BASIC_PATH = "service-eam";

    /**
     * API 上下文路径 , eam-asset-depreciation-exclude
     */
    public static final String API_CONTEXT_PATH = "eam-asset-depreciation-exclude";

    /**
     * API 基础路径 , 由 API_BASIC_PATH 和 API_CONTEXT_PATH 两部分组成
     */
    public static final String API_PREFIX = "/" + API_BASIC_PATH + "/" + API_CONTEXT_PATH + "/";

    /**
     * 添加折旧排除
     */
    public static final String INSERT = API_PREFIX + "insert";

    /**
     * 删除折旧排除
     */
    public static final String DELETE = API_PREFIX + "delete";

    /**
     * 批量删除折旧排除
     */
    public static final String DELETE_BY_IDS = API_PREFIX + "delete-by-ids";

    /**
     * 更新折旧排除
     */
    public static final String UPDATE = API_PREFIX + "update";

    /**
     * 保存折旧排除
     */
    public static final String SAVE = API_PREFIX + "save";

    /**
     * 获取单个折旧排除
     */
    public static final String GET_BY_ID = API_PREFIX + "get-by-id";

    /**
     * 获取多个折旧排除
     */
    public static final String GET_BY_IDS = API_PREFIX + "get-by-ids";

    /**
     * 查询折旧排除
     */
    public static final String QUERY_LIST = API_PREFIX + "query-list";

    /**
     * 分页查询折旧排除
     */
    public static final String QUERY_PAGED_LIST = API_PREFIX + "query-paged-list";

    /**
     * 添加折旧排除
     */
    @RequestMapping(AssetDepreciationExcludeServiceProxy.INSERT)
    Result insert(@RequestParam(name = "assetDepreciationExcludeVO") AssetDepreciationExcludeVO assetDepreciationExcludeVO);

    /**
     * 删除折旧排除
     */
    @RequestMapping(AssetDepreciationExcludeServiceProxy.DELETE)
    Result deleteById(@RequestParam(name = "id") String id);

    /**
     * 批量删除折旧排除
     */
    @RequestMapping(AssetDepreciationExcludeServiceProxy.DELETE_BY_IDS)
    Result deleteByIds(@RequestParam(name = "ids") List<String> ids);

    /**
     * 更新折旧排除
     */
    @RequestMapping(AssetDepreciationExcludeServiceProxy.UPDATE)
    Result update(@RequestParam(name = "assetDepreciationExcludeVO") AssetDepreciationExcludeVO assetDepreciationExcludeVO);

    /**
     * 更新折旧排除
     */
    @RequestMapping(AssetDepreciationExcludeServiceProxy.SAVE)
    Result save(@RequestParam(name = "assetDepreciationExcludeVO") AssetDepreciationExcludeVO assetDepreciationExcludeVO);

    /**
     * 获取折旧排除
     */
    @RequestMapping(AssetDepreciationExcludeServiceProxy.GET_BY_ID)
    Result<AssetDepreciationExclude> getById(@RequestParam(name = "id") String id);

    /**
     * 获取多个折旧排除
     */
    @RequestMapping(AssetDepreciationExcludeServiceProxy.GET_BY_IDS)
    Result<List<AssetDepreciationExclude>> getByIds(@RequestParam(name = "ids") List<String> ids);

    /**
     * 查询折旧排除
     */
    @RequestMapping(AssetDepreciationExcludeServiceProxy.QUERY_LIST)
    Result<List<AssetDepreciationExclude>> queryList(@RequestParam(name = "sample") AssetDepreciationExcludeVO sample);

    /**
     * 分页查询折旧排除
     */
    @RequestMapping(AssetDepreciationExcludeServiceProxy.QUERY_PAGED_LIST)
    Result<PagedList<AssetDepreciationExclude>> queryPagedList(@RequestParam(name = "sample") AssetDepreciationExcludeVO sample);

    /**
     * 控制器类名
     */
    public static final String CONTROLLER_CLASS_NAME = "com.dt.platform.eam.controller.AssetDepreciationExcludeController";

    /**
     * 统一的调用接口，实现在单体应用和微服务应用下的无差异调用
     */
    public static AssetDepreciationExcludeServiceProxy api() {
        return APIProxy.get(AssetDepreciationExcludeServiceProxy.class, CONTROLLER_CLASS_NAME);
    }
}
