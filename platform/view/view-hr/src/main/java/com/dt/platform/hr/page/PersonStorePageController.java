package com.dt.platform.hr.page;

import org.github.foxnic.web.framework.view.controller.ViewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.dt.platform.proxy.hr.PersonStoreServiceProxy;
import javax.servlet.http.HttpServletRequest;
/**
 * <p>
 * 人员信息模版页面控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2024-02-19 19:55:05
*/

@Controller("HrPersonStorePageController")
@RequestMapping(PersonStorePageController.prefix)
public class PersonStorePageController extends ViewController {

	public static final String prefix="business/hr/person_store";

	private PersonStoreServiceProxy proxy;

	/**
	 * 获得代理对象<br>
	 * 1、单体应用时，在应用内部调用；<br>
	 * 2、前后端分离时，通过配置，以Rest方式调用后端；<br>
	 * 3、微服务时，通过feign调用; <br>
	 * */
	public PersonStoreServiceProxy proxy() {
		if(proxy==null) {
			proxy=PersonStoreServiceProxy.api();
		}
		return proxy;
	}

	/**
	 * 人员信息 功能主页面
	 */
	@RequestMapping("/person_store_list.html")
	public String list(Model model,HttpServletRequest request) {
		return getTemplatePath(prefix,"person_store_list");
	}

	/**
	 * 人员信息 表单页面
	 */
	@RequestMapping("/person_store_form.html")
	public String form(Model model,HttpServletRequest request , String id) {
		return getTemplatePath(prefix,"person_store_form");
	}
}