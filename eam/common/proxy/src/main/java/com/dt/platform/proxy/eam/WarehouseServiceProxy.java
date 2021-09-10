package com.dt.platform.proxy.eam;

import org.springframework.web.bind.annotation.RequestMapping;
import org.github.foxnic.web.proxy.api.APIProxy;
import org.github.foxnic.web.proxy.FeignConfiguration;

import org.springframework.cloud.openfeign.FeignClient;


import com.dt.platform.domain.eam.Warehouse;
import com.dt.platform.domain.eam.WarehouseVO;
import java.util.List;
import com.github.foxnic.api.transter.Result;
import com.github.foxnic.dao.data.PagedList;
import com.dt.platform.proxy.ServiceNames;

/**
 * <p>
 * 仓库  控制器服务代理
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2021-09-10 16:44:20
*/

@FeignClient(value = ServiceNames.EAM, contextId = WarehouseServiceProxy.API_CONTEXT_PATH , configuration = FeignConfiguration.class)
public interface WarehouseServiceProxy {
	
	/**
	 * 基础路径 , service-eam
	*/
	public static final String API_BASIC_PATH = "service-eam";
	
	/**
	 * API 上下文路径 , eam-warehouse
	*/
	public static final String API_CONTEXT_PATH = "eam-warehouse";
	
	/**
	 * API 基础路径 , 由 API_BASIC_PATH 和 API_CONTEXT_PATH 两部分组成
	*/
	public static final String API_PREFIX = "/" + API_BASIC_PATH + "/"+API_CONTEXT_PATH+"/";
	
	/**
	 * 添加仓库
	 */
	public static final String INSERT = API_PREFIX + "insert";
	
	/**
	 * 删除仓库
	 */
	public static final String DELETE = API_PREFIX + "delete";

	/**
	 * 批量删除仓库
	 */
	public static final String DELETE_BY_IDS = API_PREFIX + "delete-by-ids";
	
	/**
	 * 更新仓库
	 */
	public static final String UPDATE = API_PREFIX + "update";
	
	
	/**
	 * 保存仓库
	 */
	public static final String SAVE = API_PREFIX + "save";
	
	/**
	 * 获取单个仓库
	 */
	public static final String GET_BY_ID = API_PREFIX + "get-by-id";

	/**
	 * 获取多个仓库
	 */
	public static final String GET_BY_IDS = API_PREFIX + "get-by-ids";
	;

	/**
	 * 查询仓库
	 */
	public static final String QUERY_LIST = API_PREFIX + "query-list";
	
	/**
	 * 分页查询仓库
	 */
	public static final String QUERY_PAGED_LIST = API_PREFIX + "query-paged-list";
	
	/**
	 * 导出仓库数据(Excel)
	 */
	public static final String EXPORT_EXCEL = API_PREFIX + "export-excel";

	/**
	 * 下载仓库导入模版(Excel)
	 */
	public static final String EXPORT_EXCEL_TEMPLATE = API_PREFIX + "export-excel-template";
	
	/**
	 * 导入仓库数据(Excel)
	 */
	public static final String IMPORT_EXCEL = API_PREFIX + "import-excel";
	
	/**
	 * 添加仓库
	*/
	@RequestMapping(WarehouseServiceProxy.INSERT)
	Result insert(WarehouseVO warehouseVO);
	
	/**
	 * 删除仓库
	*/
	@RequestMapping(WarehouseServiceProxy.DELETE)
	Result deleteById(String id);

	/**
	 * 批量删除仓库
	*/
	@RequestMapping(WarehouseServiceProxy.DELETE_BY_IDS)
	Result deleteByIds(List<String> ids);

	/**
	 * 更新仓库
	*/
	@RequestMapping(WarehouseServiceProxy.UPDATE)
	Result update(WarehouseVO warehouseVO);
	
	/**
	 * 更新仓库
	*/
	@RequestMapping(WarehouseServiceProxy.SAVE)
	Result save(WarehouseVO warehouseVO);
	
	/**
	 * 获取仓库
	*/
	@RequestMapping(WarehouseServiceProxy.GET_BY_ID)
	Result<Warehouse> getById(String id);

	/**
	 * 批量删除仓库
	*/
	@RequestMapping(WarehouseServiceProxy.GET_BY_IDS)
	Result<List<Warehouse>> getByIds(List<String> ids);
	/**
	 * 查询仓库
	*/
	@RequestMapping(WarehouseServiceProxy.QUERY_LIST)
	Result<List<Warehouse>> queryList(WarehouseVO sample);
	
	/**
	 * 分页查询仓库
	*/
	@RequestMapping(WarehouseServiceProxy.QUERY_PAGED_LIST)
	Result<PagedList<Warehouse>> queryPagedList(WarehouseVO sample);
	
	
	/**
	 * 控制器类名
	 * */
	public static final String CONTROLLER_CLASS_NAME="com.dt.platform.eam.controller.WarehouseController";

	/**
	 * 统一的调用接口，实现在单体应用和微服务应用下的无差异调用
	 * */
	public static WarehouseServiceProxy api() {
		return APIProxy.get(WarehouseServiceProxy.class,CONTROLLER_CLASS_NAME);
	}

}