package com.dt.platform.eam.page;

import org.github.foxnic.web.framework.view.controller.ViewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.dt.platform.proxy.eam.ResidualStrategyServiceProxy;
import javax.servlet.http.HttpServletRequest;
/**
 * <p>
 * 折旧策略 模版页面控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2021-08-11 15:52:52
*/

@Controller("EamResidualStrategyPageController")
@RequestMapping(ResidualStrategyPageController.prefix)
public class ResidualStrategyPageController extends ViewController {
	
	public static final String prefix="business/eam/residual_strategy";

	private ResidualStrategyServiceProxy proxy;
	
	/**
	 * 获得代理对象<br> 
	 * 1、单体应用时，在应用内部调用；<br> 
	 * 2、前后端分离时，通过配置，以Rest方式调用后端；<br> 
	 * 3、微服务时，通过feign调用; <br> 
	 * */
	public ResidualStrategyServiceProxy proxy() {
		if(proxy==null) {
			proxy=ResidualStrategyServiceProxy.api();
		}
		return proxy;
	}
	
	/**
	 * 折旧策略 功能主页面
	 */
	@RequestMapping("/residual_strategy_list.html")
	public String list(Model model,HttpServletRequest request) {
		return prefix+"/residual_strategy_list";
	}

	/**
	 * 折旧策略 表单页面
	 */
	@RequestMapping("/residual_strategy_form.html")
	public String form(Model model,HttpServletRequest request , String id) {
		return prefix+"/residual_strategy_form";
	}
}