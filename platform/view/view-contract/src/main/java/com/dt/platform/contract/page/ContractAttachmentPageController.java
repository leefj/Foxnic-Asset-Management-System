package com.dt.platform.contract.page;

import org.github.foxnic.web.framework.view.controller.ViewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.dt.platform.proxy.contract.ContractAttachmentServiceProxy;
import javax.servlet.http.HttpServletRequest;
/**
 * <p>
 * 合同附件 模版页面控制器
 * </p>
 * @author 李方捷 , leefangjie@qq.com
 * @since 2021-12-28 15:44:51
*/

@Controller("ContContractAttachmentPageController")
@RequestMapping(ContractAttachmentPageController.prefix)
public class ContractAttachmentPageController extends ViewController {
	
	public static final String prefix="business/contract/contract_attachment";

	private ContractAttachmentServiceProxy proxy;
	
	/**
	 * 获得代理对象<br> 
	 * 1、单体应用时，在应用内部调用；<br> 
	 * 2、前后端分离时，通过配置，以Rest方式调用后端；<br> 
	 * 3、微服务时，通过feign调用; <br> 
	 * */
	public ContractAttachmentServiceProxy proxy() {
		if(proxy==null) {
			proxy=ContractAttachmentServiceProxy.api();
		}
		return proxy;
	}
	
	/**
	 * 合同附件 功能主页面
	 */
	@RequestMapping("/contract_attachment_list.html")
	public String list(Model model,HttpServletRequest request) {
		return prefix+"/contract_attachment_list";
	}

	/**
	 * 合同附件 表单页面
	 */
	@RequestMapping("/contract_attachment_form.html")
	public String form(Model model,HttpServletRequest request , String id) {
		return prefix+"/contract_attachment_form";
	}
}