package com.dt.platform.hr.page;

import org.github.foxnic.web.framework.view.controller.ViewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.dt.platform.proxy.hr.RankServiceProxy;
import javax.servlet.http.HttpServletRequest;
/**
 * <p>
 * 职级序列模版页面控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-01-02 14:12:45
*/

@Controller("HrRankPageController")
@RequestMapping(RankPageController.prefix)
public class RankPageController extends ViewController {
	
	public static final String prefix="business/hr/rank";

	private RankServiceProxy proxy;
	
	/**
	 * 获得代理对象<br> 
	 * 1、单体应用时，在应用内部调用；<br> 
	 * 2、前后端分离时，通过配置，以Rest方式调用后端；<br> 
	 * 3、微服务时，通过feign调用; <br> 
	 * */
	public RankServiceProxy proxy() {
		if(proxy==null) {
			proxy=RankServiceProxy.api();
		}
		return proxy;
	}
	
	/**
	 * 职级序列 功能主页面
	 */
	@RequestMapping("/rank_list.html")
	public String list(Model model,HttpServletRequest request) {
		return prefix+"/rank_list";
	}

	/**
	 * 职级序列 表单页面
	 */
	@RequestMapping("/rank_form.html")
	public String form(Model model,HttpServletRequest request , String id) {
		return prefix+"/rank_form";
	}
}