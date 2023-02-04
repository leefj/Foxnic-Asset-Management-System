package com.dt.platform.ops.page;

import org.github.foxnic.web.framework.view.controller.ViewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.dt.platform.proxy.ops.AutoTaskLogServiceProxy;
import javax.servlet.http.HttpServletRequest;
/**
 * <p>
 * 明细日志 模版页面控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2022-08-21 16:06:23
*/

@Controller("OpsAutoTaskLogPageController")
@RequestMapping(AutoTaskLogPageController.prefix)
public class AutoTaskLogPageController extends ViewController {
	
	public static final String prefix="business/ops/auto_task_log";

	private AutoTaskLogServiceProxy proxy;
	
	/**
	 * 获得代理对象<br> 
	 * 1、单体应用时，在应用内部调用；<br> 
	 * 2、前后端分离时，通过配置，以Rest方式调用后端；<br> 
	 * 3、微服务时，通过feign调用; <br> 
	 * */
	public AutoTaskLogServiceProxy proxy() {
		if(proxy==null) {
			proxy=AutoTaskLogServiceProxy.api();
		}
		return proxy;
	}
	
	/**
	 * 明细日志 功能主页面
	 */
	@RequestMapping("/auto_task_log_list.html")
	public String list(Model model,HttpServletRequest request,String mLogId) {
		model.addAttribute("mLogId",mLogId);
		return prefix+"/auto_task_log_list";
	}

	/**
	 * 明细日志 表单页面
	 */
	@RequestMapping("/auto_task_log_form.html")
	public String form(Model model,HttpServletRequest request , String id) {
		return prefix+"/auto_task_log_form";
	}
}