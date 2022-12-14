package com.dt.platform.proxy.eam;

import org.springframework.web.bind.annotation.RequestMapping;
import org.github.foxnic.web.proxy.api.APIProxy;
import org.github.foxnic.web.proxy.FeignConfiguration;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import com.dt.platform.domain.eam.AssetDataPermissions;
import com.dt.platform.domain.eam.AssetDataPermissionsVO;
import java.util.List;
import com.github.foxnic.api.transter.Result;
import com.github.foxnic.dao.data.PagedList;
import com.dt.platform.proxy.ServiceNames;

/**
 * <p>
 * 资产数据权限  控制器服务代理
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2022-08-17 08:50:08
 */
@FeignClient(value = ServiceNames.EAM, contextId = AssetDataPermissionsServiceProxy.API_CONTEXT_PATH, configuration = FeignConfiguration.class)
public interface AssetDataPermissionsServiceProxy {

    /**
     * 基础路径 , service-eam
     */
    public static final String API_BASIC_PATH = "service-eam";

    /**
     * API 上下文路径 , eam-asset-data-permissions
     */
    public static final String API_CONTEXT_PATH = "eam-asset-data-permissions";

    /**
     * API 基础路径 , 由 API_BASIC_PATH 和 API_CONTEXT_PATH 两部分组成
     */
    public static final String API_PREFIX = "/" + API_BASIC_PATH + "/" + API_CONTEXT_PATH + "/";

    /**
     * 添加资产数据权限
     */
    public static final String INSERT = API_PREFIX + "insert";

    /**
     * 删除资产数据权限
     */
    public static final String DELETE = API_PREFIX + "delete";

    /**
     * 批量删除资产数据权限
     */
    public static final String DELETE_BY_IDS = API_PREFIX + "delete-by-ids";

    /**
     * 更新资产数据权限
     */
    public static final String UPDATE = API_PREFIX + "update";

    /**
     * 保存资产数据权限
     */
    public static final String SAVE = API_PREFIX + "save";

    /**
     * 获取单个资产数据权限
     */
    public static final String GET_BY_ID = API_PREFIX + "get-by-id";

    /**
     * 获取多个资产数据权限
     */
    public static final String GET_BY_IDS = API_PREFIX + "get-by-ids";

    /**
     * 查询资产数据权限
     */
    public static final String QUERY_LIST = API_PREFIX + "query-list";

    /**
     * 分页查询资产数据权限
     */
    public static final String QUERY_PAGED_LIST = API_PREFIX + "query-paged-list";

    /**
     * 添加资产数据权限
     */
    @RequestMapping(AssetDataPermissionsServiceProxy.INSERT)
    Result insert(@RequestParam(name = "assetDataPermissionsVO") AssetDataPermissionsVO assetDataPermissionsVO);

    /**
     * 删除资产数据权限
     */
    @RequestMapping(AssetDataPermissionsServiceProxy.DELETE)
    Result deleteById(@RequestParam(name = "id") String id);

    /**
     * 批量删除资产数据权限
     */
    @RequestMapping(AssetDataPermissionsServiceProxy.DELETE_BY_IDS)
    Result deleteByIds(@RequestParam(name = "ids") List<String> ids);

    /**
     * 更新资产数据权限
     */
    @RequestMapping(AssetDataPermissionsServiceProxy.UPDATE)
    Result update(@RequestParam(name = "assetDataPermissionsVO") AssetDataPermissionsVO assetDataPermissionsVO);

    /**
     * 更新资产数据权限
     */
    @RequestMapping(AssetDataPermissionsServiceProxy.SAVE)
    Result save(@RequestParam(name = "assetDataPermissionsVO") AssetDataPermissionsVO assetDataPermissionsVO);

    /**
     * 获取资产数据权限
     */
    @RequestMapping(AssetDataPermissionsServiceProxy.GET_BY_ID)
    Result<AssetDataPermissions> getById(@RequestParam(name = "id") String id);

    /**
     * 获取多个资产数据权限
     */
    @RequestMapping(AssetDataPermissionsServiceProxy.GET_BY_IDS)
    Result<List<AssetDataPermissions>> getByIds(@RequestParam(name = "ids") List<String> ids);

    /**
     * 查询资产数据权限
     */
    @RequestMapping(AssetDataPermissionsServiceProxy.QUERY_LIST)
    Result<List<AssetDataPermissions>> queryList(@RequestParam(name = "sample") AssetDataPermissionsVO sample);

    /**
     * 分页查询资产数据权限
     */
    @RequestMapping(AssetDataPermissionsServiceProxy.QUERY_PAGED_LIST)
    Result<PagedList<AssetDataPermissions>> queryPagedList(@RequestParam(name = "sample") AssetDataPermissionsVO sample);

    /**
     * 控制器类名
     */
    public static final String CONTROLLER_CLASS_NAME = "com.dt.platform.eam.controller.AssetDataPermissionsController";

    /**
     * 统一的调用接口，实现在单体应用和微服务应用下的无差异调用
     */
    public static AssetDataPermissionsServiceProxy api() {
        return APIProxy.get(AssetDataPermissionsServiceProxy.class, CONTROLLER_CLASS_NAME);
    }
}
