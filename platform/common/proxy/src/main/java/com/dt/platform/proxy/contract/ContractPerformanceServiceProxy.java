package com.dt.platform.proxy.contract;

import org.springframework.web.bind.annotation.RequestMapping;
import org.github.foxnic.web.proxy.api.APIProxy;
import org.github.foxnic.web.proxy.FeignConfiguration;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import com.dt.platform.domain.contract.ContractPerformance;
import com.dt.platform.domain.contract.ContractPerformanceVO;
import java.util.List;
import com.github.foxnic.api.transter.Result;
import com.github.foxnic.dao.data.PagedList;
import com.dt.platform.proxy.ServiceNames;

/**
 * <p>
 * 合同履行情况 控制器服务代理
 * </p>
 * @author 李方捷 , leefangjie@qq.com
 * @since 2022-10-21 15:39:35
 */
@FeignClient(value = ServiceNames.CONTRACT, contextId = ContractPerformanceServiceProxy.API_CONTEXT_PATH, configuration = FeignConfiguration.class)
public interface ContractPerformanceServiceProxy {

    /**
     * 基础路径 , service-contract
     */
    public static final String API_BASIC_PATH = "service-contract";

    /**
     * API 上下文路径 , cont-contract-performance
     */
    public static final String API_CONTEXT_PATH = "cont-contract-performance";

    /**
     * API 基础路径 , 由 API_BASIC_PATH 和 API_CONTEXT_PATH 两部分组成
     */
    public static final String API_PREFIX = "/" + API_BASIC_PATH + "/" + API_CONTEXT_PATH + "/";

    /**
     * 添加合同履行情况
     */
    public static final String INSERT = API_PREFIX + "insert";

    /**
     * 删除合同履行情况
     */
    public static final String DELETE = API_PREFIX + "delete";

    /**
     * 批量删除合同履行情况
     */
    public static final String DELETE_BY_IDS = API_PREFIX + "delete-by-ids";

    /**
     * 更新合同履行情况
     */
    public static final String UPDATE = API_PREFIX + "update";

    /**
     * 保存合同履行情况
     */
    public static final String SAVE = API_PREFIX + "save";

    /**
     * 获取单个合同履行情况
     */
    public static final String GET_BY_ID = API_PREFIX + "get-by-id";

    /**
     * 获取多个合同履行情况
     */
    public static final String GET_BY_IDS = API_PREFIX + "get-by-ids";

    /**
     * 查询合同履行情况
     */
    public static final String QUERY_LIST = API_PREFIX + "query-list";

    /**
     * 分页查询合同履行情况
     */
    public static final String QUERY_PAGED_LIST = API_PREFIX + "query-paged-list";

    /**
     * 添加合同履行情况
     */
    @RequestMapping(ContractPerformanceServiceProxy.INSERT)
    Result insert(@RequestParam(name = "contractPerformanceVO") ContractPerformanceVO contractPerformanceVO);

    /**
     * 删除合同履行情况
     */
    @RequestMapping(ContractPerformanceServiceProxy.DELETE)
    Result deleteById(@RequestParam(name = "id") String id);

    /**
     * 批量删除合同履行情况
     */
    @RequestMapping(ContractPerformanceServiceProxy.DELETE_BY_IDS)
    Result deleteByIds(@RequestParam(name = "ids") List<String> ids);

    /**
     * 更新合同履行情况
     */
    @RequestMapping(ContractPerformanceServiceProxy.UPDATE)
    Result update(@RequestParam(name = "contractPerformanceVO") ContractPerformanceVO contractPerformanceVO);

    /**
     * 更新合同履行情况
     */
    @RequestMapping(ContractPerformanceServiceProxy.SAVE)
    Result save(@RequestParam(name = "contractPerformanceVO") ContractPerformanceVO contractPerformanceVO);

    /**
     * 获取合同履行情况
     */
    @RequestMapping(ContractPerformanceServiceProxy.GET_BY_ID)
    Result<ContractPerformance> getById(@RequestParam(name = "id") String id);

    /**
     * 获取多个合同履行情况
     */
    @RequestMapping(ContractPerformanceServiceProxy.GET_BY_IDS)
    Result<List<ContractPerformance>> getByIds(@RequestParam(name = "ids") List<String> ids);

    /**
     * 查询合同履行情况
     */
    @RequestMapping(ContractPerformanceServiceProxy.QUERY_LIST)
    Result<List<ContractPerformance>> queryList(@RequestParam(name = "sample") ContractPerformanceVO sample);

    /**
     * 分页查询合同履行情况
     */
    @RequestMapping(ContractPerformanceServiceProxy.QUERY_PAGED_LIST)
    Result<PagedList<ContractPerformance>> queryPagedList(@RequestParam(name = "sample") ContractPerformanceVO sample);

    /**
     * 控制器类名
     */
    public static final String CONTROLLER_CLASS_NAME = "com.dt.platform.contract.controller.ContractPerformanceController";

    /**
     * 统一的调用接口，实现在单体应用和微服务应用下的无差异调用
     */
    public static ContractPerformanceServiceProxy api() {
        return APIProxy.get(ContractPerformanceServiceProxy.class, CONTROLLER_CLASS_NAME);
    }
}
