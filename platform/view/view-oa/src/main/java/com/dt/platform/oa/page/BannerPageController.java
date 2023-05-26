package com.dt.platform.oa.page;

import org.github.foxnic.web.framework.view.controller.ViewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.dt.platform.proxy.oa.BannerServiceProxy;
import javax.servlet.http.HttpServletRequest;
/**
 * <p>
 * 横幅模版页面控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-05-26 13:13:20
*/

@Controller("OaBannerPageController")
@RequestMapping(BannerPageController.prefix)
public class BannerPageController extends ViewController {

	public static final String prefix="business/oa/banner";

	private BannerServiceProxy proxy;

	/**
	 * 获得代理对象<br>
	 * 1、单体应用时，在应用内部调用；<br>
	 * 2、前后端分离时，通过配置，以Rest方式调用后端；<br>
	 * 3、微服务时，通过feign调用; <br>
	 * */
	public BannerServiceProxy proxy() {
		if(proxy==null) {
			proxy=BannerServiceProxy.api();
		}
		return proxy;
	}

	/**
	 * 横幅 功能主页面
	 */
	@RequestMapping("/banner_list.html")
	public String list(Model model,HttpServletRequest request) {
		return getTemplatePath(prefix,"banner_list");
	}

	/**
	 * 横幅 表单页面
	 */
	@RequestMapping("/banner_form.html")
	public String form(Model model,HttpServletRequest request , String id) {
		return getTemplatePath(prefix,"banner_form");
	}
}