package com.dt.platform.ops.page;

import org.github.foxnic.web.framework.view.controller.ViewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.dt.platform.proxy.ops.MonitorNodeGroupServiceProxy;
import javax.servlet.http.HttpServletRequest;
/**
 * <p>
 * 节点分组 模版页面控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2022-07-13 07:13:47
*/

@Controller("OpsMonitorNodeGroupPageController")
@RequestMapping(MonitorNodeGroupPageController.prefix)
public class MonitorNodeGroupPageController extends ViewController {
	
	public static final String prefix="business/ops/monitor_node_group";

	private MonitorNodeGroupServiceProxy proxy;
	
	/**
	 * 获得代理对象<br> 
	 * 1、单体应用时，在应用内部调用；<br> 
	 * 2、前后端分离时，通过配置，以Rest方式调用后端；<br> 
	 * 3、微服务时，通过feign调用; <br> 
	 * */
	public MonitorNodeGroupServiceProxy proxy() {
		if(proxy==null) {
			proxy=MonitorNodeGroupServiceProxy.api();
		}
		return proxy;
	}
	
	/**
	 * 节点分组 功能主页面
	 */
	@RequestMapping("/monitor_node_group_list.html")
	public String list(Model model,HttpServletRequest request) {
		return prefix+"/monitor_node_group_list";
	}

	/**
	 * 节点分组 表单页面
	 */
	@RequestMapping("/monitor_node_group_form.html")
	public String form(Model model,HttpServletRequest request , String id) {
		return prefix+"/monitor_node_group_form";
	}
}