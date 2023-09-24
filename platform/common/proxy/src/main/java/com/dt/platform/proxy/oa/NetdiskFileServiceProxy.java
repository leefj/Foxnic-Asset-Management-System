package com.dt.platform.proxy.oa;

import org.springframework.web.bind.annotation.RequestMapping;
import org.github.foxnic.web.proxy.api.APIProxy;
import org.github.foxnic.web.proxy.FeignConfiguration;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import com.dt.platform.domain.oa.NetdiskFile;
import com.dt.platform.domain.oa.NetdiskFileVO;
import java.util.List;
import com.github.foxnic.api.transter.Result;
import com.github.foxnic.dao.data.PagedList;
import com.dt.platform.proxy.ServiceNames;

/**
 * <p>
 * 文件 控制器服务代理
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-09-18 14:19:47
 */
@FeignClient(value = ServiceNames.OA, contextId = NetdiskFileServiceProxy.API_CONTEXT_PATH, configuration = FeignConfiguration.class)
public interface NetdiskFileServiceProxy {

    /**
     * 基础路径 , service-oa
     */
    public static final String API_BASIC_PATH = "service-oa";

    /**
     * API 上下文路径 , oa-netdisk-file
     */
    public static final String API_CONTEXT_PATH = "oa-netdisk-file";

    /**
     * API 基础路径 , 由 API_BASIC_PATH 和 API_CONTEXT_PATH 两部分组成
     */
    public static final String API_PREFIX = "/" + API_BASIC_PATH + "/" + API_CONTEXT_PATH + "/";

    /**
     * 添加文件
     */
    public static final String INSERT = API_PREFIX + "insert";

    /**
     * 删除文件
     */
    public static final String DELETE = API_PREFIX + "delete";

    /**
     * 批量删除文件
     */
    public static final String DELETE_BY_IDS = API_PREFIX + "delete-by-ids";

    /**
     * 更新文件
     */
    public static final String UPDATE = API_PREFIX + "update";

    /**
     * 保存文件
     */
    public static final String SAVE = API_PREFIX + "save";

    /**
     * 获取单个文件
     */
    public static final String GET_BY_ID = API_PREFIX + "get-by-id";

    /**
     * 获取多个文件
     */
    public static final String GET_BY_IDS = API_PREFIX + "get-by-ids";

    /**
     * 查询文件
     */
    public static final String QUERY_LIST = API_PREFIX + "query-list";

    /**
     * 分页查询文件
     */
    public static final String QUERY_PAGED_LIST = API_PREFIX + "query-paged-list";


    public static final String RENAME = API_PREFIX + "rename";


    public static final String UPLOAD_FILES_INFO= API_PREFIX + "upload-files-info";
    /**
     * 添加文件
     */
    @RequestMapping(NetdiskFileServiceProxy.INSERT)
    Result insert(@RequestParam(name = "netdiskFileVO") NetdiskFileVO netdiskFileVO);

    /**
     * 删除文件
     */
    @RequestMapping(NetdiskFileServiceProxy.DELETE)
    Result deleteById(@RequestParam(name = "id") String id);

    /**
     * 批量删除文件
     */
    @RequestMapping(NetdiskFileServiceProxy.DELETE_BY_IDS)
    Result deleteByIds(@RequestParam(name = "ids") List<String> ids);

    /**
     * 更新文件
     */
    @RequestMapping(NetdiskFileServiceProxy.UPDATE)
    Result update(@RequestParam(name = "netdiskFileVO") NetdiskFileVO netdiskFileVO);

    /**
     * 更新文件
     */
    @RequestMapping(NetdiskFileServiceProxy.SAVE)
    Result save(@RequestParam(name = "netdiskFileVO") NetdiskFileVO netdiskFileVO);

    /**
     * 获取文件
     */
    @RequestMapping(NetdiskFileServiceProxy.GET_BY_ID)
    Result<NetdiskFile> getById(@RequestParam(name = "id") String id);

    /**
     * 获取多个文件
     */
    @RequestMapping(NetdiskFileServiceProxy.GET_BY_IDS)
    Result<List<NetdiskFile>> getByIds(@RequestParam(name = "ids") List<String> ids);

    /**
     * 查询文件
     */
    @RequestMapping(NetdiskFileServiceProxy.QUERY_LIST)
    Result<List<NetdiskFile>> queryList(@RequestParam(name = "sample") NetdiskFileVO sample);

    /**
     * 分页查询文件
     */
    @RequestMapping(NetdiskFileServiceProxy.QUERY_PAGED_LIST)
    Result<PagedList<NetdiskFile>> queryPagedList(@RequestParam(name = "sample") NetdiskFileVO sample);

    /**
     * 控制器类名
     */
    public static final String CONTROLLER_CLASS_NAME = "com.dt.platform.oa.controller.NetdiskFileController";

    /**
     * 统一的调用接口，实现在单体应用和微服务应用下的无差异调用
     */
    public static NetdiskFileServiceProxy api() {
        return APIProxy.get(NetdiskFileServiceProxy.class, CONTROLLER_CLASS_NAME);
    }
}
