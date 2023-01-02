package com.dt.platform.hr.page;

import org.github.foxnic.web.framework.view.controller.ViewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.dt.platform.proxy.hr.PersonContractServiceProxy;
import javax.servlet.http.HttpServletRequest;
/**
 * <p>
 * 人员合同模版页面控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2022-12-30 19:28:51
*/

@Controller("HrPersonContractPageController")
@RequestMapping(PersonContractPageController.prefix)
public class PersonContractPageController extends ViewController {
	
	public static final String prefix="business/hr/person_contract";

	private PersonContractServiceProxy proxy;
	
	/**
	 * 获得代理对象<br> 
	 * 1、单体应用时，在应用内部调用；<br> 
	 * 2、前后端分离时，通过配置，以Rest方式调用后端；<br> 
	 * 3、微服务时，通过feign调用; <br> 
	 * */
	public PersonContractServiceProxy proxy() {
		if(proxy==null) {
			proxy=PersonContractServiceProxy.api();
		}
		return proxy;
	}
	
	/**
	 * 人员合同 功能主页面
	 */
	@RequestMapping("/person_contract_list.html")
	public String list(Model model,HttpServletRequest request) {
		return prefix+"/person_contract_list";
	}

	/**
	 * 人员合同 表单页面
	 */
	@RequestMapping("/person_contract_form.html")
	public String form(Model model,HttpServletRequest request , String id) {
		return prefix+"/person_contract_form";
	}
}