package com.dt.platform.proxy.hr;

import org.springframework.web.bind.annotation.RequestMapping;
import org.github.foxnic.web.proxy.api.APIProxy;
import org.github.foxnic.web.proxy.FeignConfiguration;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import com.dt.platform.domain.hr.Rank;
import com.dt.platform.domain.hr.RankVO;
import java.util.List;
import com.github.foxnic.api.transter.Result;
import com.github.foxnic.dao.data.PagedList;
import com.dt.platform.proxy.ServiceNames;

/**
 * <p>
 * 职级序列 控制器服务代理
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-01-02 14:12:45
 */
@FeignClient(value = ServiceNames.HR, contextId = RankServiceProxy.API_CONTEXT_PATH, configuration = FeignConfiguration.class)
public interface RankServiceProxy {

    /**
     * 基础路径 , service-hr
     */
    public static final String API_BASIC_PATH = "service-hr";

    /**
     * API 上下文路径 , hr-rank
     */
    public static final String API_CONTEXT_PATH = "hr-rank";

    /**
     * API 基础路径 , 由 API_BASIC_PATH 和 API_CONTEXT_PATH 两部分组成
     */
    public static final String API_PREFIX = "/" + API_BASIC_PATH + "/" + API_CONTEXT_PATH + "/";

    /**
     * 添加职级序列
     */
    public static final String INSERT = API_PREFIX + "insert";

    /**
     * 删除职级序列
     */
    public static final String DELETE = API_PREFIX + "delete";

    /**
     * 批量删除职级序列
     */
    public static final String DELETE_BY_IDS = API_PREFIX + "delete-by-ids";

    /**
     * 更新职级序列
     */
    public static final String UPDATE = API_PREFIX + "update";

    /**
     * 保存职级序列
     */
    public static final String SAVE = API_PREFIX + "save";

    /**
     * 获取单个职级序列
     */
    public static final String GET_BY_ID = API_PREFIX + "get-by-id";

    /**
     * 获取多个职级序列
     */
    public static final String GET_BY_IDS = API_PREFIX + "get-by-ids";

    /**
     * 查询职级序列
     */
    public static final String QUERY_LIST = API_PREFIX + "query-list";

    /**
     * 分页查询职级序列
     */
    public static final String QUERY_PAGED_LIST = API_PREFIX + "query-paged-list";

    /**
     * 添加职级序列
     */
    @RequestMapping(RankServiceProxy.INSERT)
    Result insert(@RequestParam(name = "rankVO") RankVO rankVO);

    /**
     * 删除职级序列
     */
    @RequestMapping(RankServiceProxy.DELETE)
    Result deleteById(@RequestParam(name = "id") String id);

    /**
     * 批量删除职级序列
     */
    @RequestMapping(RankServiceProxy.DELETE_BY_IDS)
    Result deleteByIds(@RequestParam(name = "ids") List<String> ids);

    /**
     * 更新职级序列
     */
    @RequestMapping(RankServiceProxy.UPDATE)
    Result update(@RequestParam(name = "rankVO") RankVO rankVO);

    /**
     * 更新职级序列
     */
    @RequestMapping(RankServiceProxy.SAVE)
    Result save(@RequestParam(name = "rankVO") RankVO rankVO);

    /**
     * 获取职级序列
     */
    @RequestMapping(RankServiceProxy.GET_BY_ID)
    Result<Rank> getById(@RequestParam(name = "id") String id);

    /**
     * 获取多个职级序列
     */
    @RequestMapping(RankServiceProxy.GET_BY_IDS)
    Result<List<Rank>> getByIds(@RequestParam(name = "ids") List<String> ids);

    /**
     * 查询职级序列
     */
    @RequestMapping(RankServiceProxy.QUERY_LIST)
    Result<List<Rank>> queryList(@RequestParam(name = "sample") RankVO sample);

    /**
     * 分页查询职级序列
     */
    @RequestMapping(RankServiceProxy.QUERY_PAGED_LIST)
    Result<PagedList<Rank>> queryPagedList(@RequestParam(name = "sample") RankVO sample);

    /**
     * 控制器类名
     */
    public static final String CONTROLLER_CLASS_NAME = "com.dt.platform.hr.controller.RankController";

    /**
     * 统一的调用接口，实现在单体应用和微服务应用下的无差异调用
     */
    public static RankServiceProxy api() {
        return APIProxy.get(RankServiceProxy.class, CONTROLLER_CLASS_NAME);
    }
}
