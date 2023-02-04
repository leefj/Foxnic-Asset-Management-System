package com.dt.platform.hr.page;

import org.github.foxnic.web.framework.view.controller.ViewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.dt.platform.proxy.hr.PositionServiceProxy;
import javax.servlet.http.HttpServletRequest;
/**
 * <p>
 * 工作岗位模版页面控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-01-02 14:12:25
*/

@Controller("HrPositionPageController")
@RequestMapping(PositionPageController.prefix)
public class PositionPageController extends ViewController {
	
	public static final String prefix="business/hr/position";

	private PositionServiceProxy proxy;
	
	/**
	 * 获得代理对象<br> 
	 * 1、单体应用时，在应用内部调用；<br> 
	 * 2、前后端分离时，通过配置，以Rest方式调用后端；<br> 
	 * 3、微服务时，通过feign调用; <br> 
	 * */
	public PositionServiceProxy proxy() {
		if(proxy==null) {
			proxy=PositionServiceProxy.api();
		}
		return proxy;
	}
	
	/**
	 * 工作岗位 功能主页面
	 */
	@RequestMapping("/position_list.html")
	public String list(Model model,HttpServletRequest request) {
		return prefix+"/position_list";
	}

	/**
	 * 工作岗位 表单页面
	 */
	@RequestMapping("/position_form.html")
	public String form(Model model,HttpServletRequest request , String id) {
		return prefix+"/position_form";
	}
}