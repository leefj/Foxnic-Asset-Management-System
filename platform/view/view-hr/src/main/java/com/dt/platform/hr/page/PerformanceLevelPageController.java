package com.dt.platform.hr.page;

import org.github.foxnic.web.framework.view.controller.ViewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.dt.platform.proxy.hr.PerformanceLevelServiceProxy;
import javax.servlet.http.HttpServletRequest;
/**
 * <p>
 * 考核等级模版页面控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2024-01-28 16:05:00
*/

@Controller("HrPerformanceLevelPageController")
@RequestMapping(PerformanceLevelPageController.prefix)
public class PerformanceLevelPageController extends ViewController {

	public static final String prefix="business/hr/performance_level";

	private PerformanceLevelServiceProxy proxy;

	/**
	 * 获得代理对象<br>
	 * 1、单体应用时，在应用内部调用；<br>
	 * 2、前后端分离时，通过配置，以Rest方式调用后端；<br>
	 * 3、微服务时，通过feign调用; <br>
	 * */
	public PerformanceLevelServiceProxy proxy() {
		if(proxy==null) {
			proxy=PerformanceLevelServiceProxy.api();
		}
		return proxy;
	}

	/**
	 * 考核等级 功能主页面
	 */
	@RequestMapping("/performance_level_list.html")
	public String list(Model model,HttpServletRequest request) {
		return getTemplatePath(prefix,"performance_level_list");
	}

	/**
	 * 考核等级 表单页面
	 */
	@RequestMapping("/performance_level_form.html")
	public String form(Model model,HttpServletRequest request , String id) {
		return getTemplatePath(prefix,"performance_level_form");
	}
}