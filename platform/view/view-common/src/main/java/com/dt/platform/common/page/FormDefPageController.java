package com.dt.platform.common.page;

import org.github.foxnic.web.framework.view.controller.ViewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.dt.platform.proxy.common.FormDefServiceProxy;
import javax.servlet.http.HttpServletRequest;
/**
 * <p>
 * 表单定义模版页面控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-05-21 12:56:31
*/

@Controller("SysFormDefPageController")
@RequestMapping(FormDefPageController.prefix)
public class FormDefPageController extends ViewController {

	public static final String prefix="business/common/form_def";

	private FormDefServiceProxy proxy;

	/**
	 * 获得代理对象<br>
	 * 1、单体应用时，在应用内部调用；<br>
	 * 2、前后端分离时，通过配置，以Rest方式调用后端；<br>
	 * 3、微服务时，通过feign调用; <br>
	 * */
	public FormDefServiceProxy proxy() {
		if(proxy==null) {
			proxy=FormDefServiceProxy.api();
		}
		return proxy;
	}

	/**
	 * 表单定义 功能主页面
	 */
	@RequestMapping("/form_def_list.html")
	public String list(Model model,HttpServletRequest request,String formId) {
		model.addAttribute("formId",formId);
		return getTemplatePath(prefix,"form_def_list");
	}

	/**
	 * 表单定义 表单页面
	 */
	@RequestMapping("/form_def_form.html")
	public String form(Model model,HttpServletRequest request , String id,String formId) {
		model.addAttribute("formId",formId);
		return getTemplatePath(prefix,"form_def_form");
	}
}