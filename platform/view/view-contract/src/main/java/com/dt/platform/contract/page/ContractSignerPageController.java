package com.dt.platform.contract.page;

import org.github.foxnic.web.framework.view.controller.ViewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.dt.platform.proxy.contract.ContractSignerServiceProxy;
import javax.servlet.http.HttpServletRequest;
/**
 * <p>
 * 合同签订方模版页面控制器
 * </p>
 * @author 李方捷 , leefangjie@qq.com
 * @since 2022-10-21 15:39:33
*/

@Controller("ContContractSignerPageController")
@RequestMapping(ContractSignerPageController.prefix)
public class ContractSignerPageController extends ViewController {
	
	public static final String prefix="business/contract/contract_signer";

	private ContractSignerServiceProxy proxy;
	
	/**
	 * 获得代理对象<br> 
	 * 1、单体应用时，在应用内部调用；<br> 
	 * 2、前后端分离时，通过配置，以Rest方式调用后端；<br> 
	 * 3、微服务时，通过feign调用; <br> 
	 * */
	public ContractSignerServiceProxy proxy() {
		if(proxy==null) {
			proxy=ContractSignerServiceProxy.api();
		}
		return proxy;
	}
	
	/**
	 * 合同签订方 功能主页面
	 */
	@RequestMapping("/contract_signer_list.html")
	public String list(Model model,HttpServletRequest request) {
		return prefix+"/contract_signer_list";
	}

	/**
	 * 合同签订方 表单页面
	 */
	@RequestMapping("/contract_signer_form.html")
	public String form(Model model,HttpServletRequest request , String id) {
		return prefix+"/contract_signer_form";
	}
}