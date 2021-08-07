package com.dt.platform.proxy.eam;

import org.springframework.web.bind.annotation.RequestMapping;
import org.github.foxnic.web.proxy.api.APIProxy;
import org.github.foxnic.web.proxy.FeignConfiguration;

import org.springframework.cloud.openfeign.FeignClient;


import com.dt.platform.domain.eam.CodeAttr;
import com.dt.platform.domain.eam.CodeAttrVO;
import java.util.List;
import com.github.foxnic.api.transter.Result;
import com.github.foxnic.dao.data.PagedList;
import com.dt.platform.proxy.ServiceNames;

/**
 * <p>
 * 编码分配属性  控制器服务代理
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2021-08-07 09:36:53
*/

@FeignClient(value = ServiceNames.EAM, contextId = CodeAttrServiceProxy.API_CONTEXT_PATH , configuration = FeignConfiguration.class)
public interface CodeAttrServiceProxy {
	
	/**
	 * 基础路径 , service-eam
	*/
	public static final String API_BASIC_PATH = "service-eam";
	
	/**
	 * API 上下文路径 , eam-code-attr
	*/
	public static final String API_CONTEXT_PATH = "eam-code-attr";
	
	/**
	 * API 基础路径 , 由 API_BASIC_PATH 和 API_CONTEXT_PATH 两部分组成
	*/
	public static final String API_PREFIX = "/" + API_BASIC_PATH + "/"+API_CONTEXT_PATH+"/";
	
	/**
	 * 添加编码分配属性
	 */
	public static final String INSERT = API_PREFIX + "insert";
	
	/**
	 * 删除编码分配属性
	 */
	public static final String DELETE = API_PREFIX + "delete";

	/**
	 * 批量删除编码分配属性
	 */
	public static final String DELETE_BY_IDS = API_PREFIX + "delete-by-ids";
	;
	
	/**
	 * 更新编码分配属性
	 */
	public static final String UPDATE = API_PREFIX + "update";
	
	
	/**
	 * 保存编码分配属性
	 */
	public static final String SAVE = API_PREFIX + "save";
	
	/**
	 * 获取单个编码分配属性
	 */
	public static final String GET_BY_ID = API_PREFIX + "get-by-id";

	/**
	 * 获取多个编码分配属性
	 */
	public static final String GET_BY_IDS = API_PREFIX + "get-by-ids";
	;

	/**
	 * 查询编码分配属性
	 */
	public static final String QUERY_LIST = API_PREFIX + "query-list";
	
	/**
	 * 分页查询编码分配属性
	 */
	public static final String QUERY_PAGED_LIST = API_PREFIX + "query-paged-list";
	
	/**
	 * 导出编码分配属性数据(Excel)
	 */
	public static final String EXPORT_EXCEL = API_PREFIX + "export-excel";

	/**
	 * 下载编码分配属性导入模版(Excel)
	 */
	public static final String EXPORT_EXCEL_TEMPLATE = API_PREFIX + "export-excel-template";
	
	/**
	 * 导入编码分配属性数据(Excel)
	 */
	public static final String IMPORT_EXCEL = API_PREFIX + "import-excel";
	
	/**
	 * 添加编码分配属性
	*/
	@RequestMapping(CodeAttrServiceProxy.INSERT)
	Result insert(CodeAttrVO codeAttrVO);
	
	/**
	 * 删除编码分配属性
	*/
	@RequestMapping(CodeAttrServiceProxy.DELETE)
	Result deleteById(String id);

	/**
	 * 批量删除编码分配属性
	*/
	@RequestMapping(CodeAttrServiceProxy.DELETE_BY_IDS)
	Result deleteByIds(List<String> ids);

	/**
	 * 更新编码分配属性
	*/
	@RequestMapping(CodeAttrServiceProxy.UPDATE)
	Result update(CodeAttrVO codeAttrVO);
	
	/**
	 * 更新编码分配属性
	*/
	@RequestMapping(CodeAttrServiceProxy.SAVE)
	Result save(CodeAttrVO codeAttrVO);
	
	/**
	 * 获取编码分配属性
	*/
	@RequestMapping(CodeAttrServiceProxy.GET_BY_ID)
	Result<CodeAttr> getById(String id);

	/**
	 * 批量删除编码分配属性
	*/
	@RequestMapping(CodeAttrServiceProxy.GET_BY_IDS)
	Result<List<CodeAttr>> getByIds(List<String> ids);
	/**
	 * 查询编码分配属性
	*/
	@RequestMapping(CodeAttrServiceProxy.QUERY_LIST)
	Result<List<CodeAttr>> queryList(CodeAttrVO sample);
	
	/**
	 * 分页查询编码分配属性
	*/
	@RequestMapping(CodeAttrServiceProxy.QUERY_PAGED_LIST)
	Result<PagedList<CodeAttr>> queryPagedList(CodeAttrVO sample);
	
	
	/**
	 * 控制器类名
	 * */
	public static final String CONTROLLER_CLASS_NAME="com.dt.platform.eam.controller.CodeAttrController";

	/**
	 * 统一的调用接口，实现在单体应用和微服务应用下的无差异调用
	 * */
	public static CodeAttrServiceProxy api() {
		return APIProxy.get(CodeAttrServiceProxy.class,CONTROLLER_CLASS_NAME);
	}

}