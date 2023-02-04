package com.dt.platform.eam.page;

import org.github.foxnic.web.framework.view.controller.ViewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.dt.platform.proxy.eam.InventoryFeedbackServiceProxy;
import javax.servlet.http.HttpServletRequest;
/**
 * <p>
 * 盘点反馈模版页面控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2022-11-08 07:00:16
*/

@Controller("EamInventoryFeedbackPageController")
@RequestMapping(InventoryFeedbackPageController.prefix)
public class InventoryFeedbackPageController extends ViewController {
	
	public static final String prefix="business/eam/inventory_feedback";

	private InventoryFeedbackServiceProxy proxy;
	
	/**
	 * 获得代理对象<br> 
	 * 1、单体应用时，在应用内部调用；<br> 
	 * 2、前后端分离时，通过配置，以Rest方式调用后端；<br> 
	 * 3、微服务时，通过feign调用; <br> 
	 * */
	public InventoryFeedbackServiceProxy proxy() {
		if(proxy==null) {
			proxy=InventoryFeedbackServiceProxy.api();
		}
		return proxy;
	}
	
	/**
	 * 盘点反馈 功能主页面
	 */
	@RequestMapping("/inventory_feedback_list.html")
	public String list(Model model,HttpServletRequest request) {
		return prefix+"/inventory_feedback_list";
	}

	/**
	 * 盘点反馈 表单页面
	 */
	@RequestMapping("/inventory_feedback_form.html")
	public String form(Model model,HttpServletRequest request , String id) {
		return prefix+"/inventory_feedback_form";
	}
}