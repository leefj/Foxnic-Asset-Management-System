package com.dt.platform.proxy.ops;

import org.github.foxnic.web.domain.system.DictItem;
import org.springframework.web.bind.annotation.RequestMapping;
import org.github.foxnic.web.proxy.api.APIProxy;
import org.github.foxnic.web.proxy.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import com.dt.platform.domain.ops.VoucherPriv;
import com.dt.platform.domain.ops.VoucherPrivVO;
import java.util.List;
import com.github.foxnic.api.transter.Result;
import com.github.foxnic.dao.data.PagedList;
import com.dt.platform.proxy.ServiceNames;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 凭证权限  控制器服务代理
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2021-09-05 12:20:11
 */
@FeignClient(value = ServiceNames.OPS, contextId = VoucherPrivServiceProxy.API_CONTEXT_PATH, configuration = FeignConfiguration.class)
public interface VoucherPrivServiceProxy {

    /**
     * 基础路径 , service-ops
     */
    public static final String API_BASIC_PATH = "service-ops";

    /**
     * API 上下文路径 , ops-voucher-priv
     */
    public static final String API_CONTEXT_PATH = "ops-voucher-priv";

    /**
     * API 基础路径 , 由 API_BASIC_PATH 和 API_CONTEXT_PATH 两部分组成
     */
    public static final String API_PREFIX = "/" + API_BASIC_PATH + "/" + API_CONTEXT_PATH + "/";

    /**
     * 添加凭证权限
     */
    public static final String INSERT = API_PREFIX + "insert";

    /**
     * 删除凭证权限
     */
    public static final String DELETE = API_PREFIX + "delete";

    /**
     * 批量删除凭证权限
     */
    public static final String DELETE_BY_IDS = API_PREFIX + "delete-by-ids";

    /**
     * 更新凭证权限
     */
    public static final String UPDATE = API_PREFIX + "update";

    /**
     * 保存凭证权限
     */
    public static final String SAVE = API_PREFIX + "save";

    /**
     * 获取单个凭证权限
     */
    public static final String GET_BY_ID = API_PREFIX + "get-by-id";

    /**
     * 获取多个凭证权限
     */
    public static final String GET_BY_IDS = API_PREFIX + "get-by-ids";

    /**
     * 查询凭证权限
     */
    public static final String QUERY_LIST = API_PREFIX + "query-list";

    /**
     * 查询凭证类型
     */
    public static final String QUERY_TYPE_LIST = API_PREFIX + "query-type-list";

    /**
     * 分页查询凭证权限
     */
    public static final String QUERY_PAGED_LIST = API_PREFIX + "query-paged-list";

    /**
     * 导出凭证权限数据(Excel)
     */
    public static final String EXPORT_EXCEL = API_PREFIX + "export-excel";

    /**
     * 下载凭证权限导入模版(Excel)
     */
    public static final String EXPORT_EXCEL_TEMPLATE = API_PREFIX + "export-excel-template";

    /**
     * 导入凭证权限数据(Excel)
     */
    public static final String IMPORT_EXCEL = API_PREFIX + "import-excel";

    /**
     * 添加凭证权限
     */
    @RequestMapping(VoucherPrivServiceProxy.INSERT)
    Result insert(@RequestParam(name = "voucherPrivVO") VoucherPrivVO voucherPrivVO);

    /**
     * 删除凭证权限
     */
    @RequestMapping(VoucherPrivServiceProxy.DELETE)
    Result deleteById(@RequestParam(name = "id") String id);

    /**
     * 批量删除凭证权限
     */
    @RequestMapping(VoucherPrivServiceProxy.DELETE_BY_IDS)
    Result deleteByIds(@RequestParam(name = "ids") List<String> ids);

    /**
     * 更新凭证权限
     */
    @RequestMapping(VoucherPrivServiceProxy.UPDATE)
    Result update(@RequestParam(name = "voucherPrivVO") VoucherPrivVO voucherPrivVO);

    /**
     * 更新凭证权限
     */
    @RequestMapping(VoucherPrivServiceProxy.SAVE)
    Result save(@RequestParam(name = "voucherPrivVO") VoucherPrivVO voucherPrivVO);

    /**
     * 获取凭证权限
     */
    @RequestMapping(VoucherPrivServiceProxy.GET_BY_ID)
    Result<VoucherPriv> getById(@RequestParam(name = "id") String id);

    /**
     * 批量删除凭证权限
     */
    @RequestMapping(VoucherPrivServiceProxy.GET_BY_IDS)
    Result<List<VoucherPriv>> getByIds(@RequestParam(name = "ids") List<String> ids);

    /**
     * 查询凭证权限
     */
    @RequestMapping(VoucherPrivServiceProxy.QUERY_LIST)
    Result<List<VoucherPriv>> queryList(@RequestParam(name = "sample") VoucherPrivVO sample);

    /**
     * 查询凭证权限
     */
    @RequestMapping(VoucherPrivServiceProxy.QUERY_TYPE_LIST)
    Result<List<DictItem>> queryTypeList();

    /**
     * 分页查询凭证权限
     */
    @RequestMapping(VoucherPrivServiceProxy.QUERY_PAGED_LIST)
    Result<PagedList<VoucherPriv>> queryPagedList(@RequestParam(name = "sample") VoucherPrivVO sample);

    /**
     * 控制器类名
     */
    public static final String CONTROLLER_CLASS_NAME = "com.dt.platform.ops.controller.VoucherPrivController";

    /**
     * 统一的调用接口，实现在单体应用和微服务应用下的无差异调用
     */
    public static VoucherPrivServiceProxy api() {
        return APIProxy.get(VoucherPrivServiceProxy.class, CONTROLLER_CLASS_NAME);
    }
}