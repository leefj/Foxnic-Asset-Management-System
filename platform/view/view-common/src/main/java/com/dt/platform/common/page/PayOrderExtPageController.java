package com.dt.platform.common.page;

import org.github.foxnic.web.framework.view.controller.ViewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.dt.platform.proxy.common.PayOrderExtServiceProxy;
import javax.servlet.http.HttpServletRequest;
/**
 * <p>
 * 支付订单模版页面控制器
 * </p>
 * @author 金杰 , maillank@qq.com
 * @since 2023-07-04 16:25:47
*/

@Controller("SysPayOrderExtPageController")
@RequestMapping(PayOrderExtPageController.prefix)
public class PayOrderExtPageController extends ViewController {

	public static final String prefix="business/common/pay_order_ext";

	private PayOrderExtServiceProxy proxy;

	/**
	 * 获得代理对象<br>
	 * 1、单体应用时，在应用内部调用；<br>
	 * 2、前后端分离时，通过配置，以Rest方式调用后端；<br>
	 * 3、微服务时，通过feign调用; <br>
	 * */
	public PayOrderExtServiceProxy proxy() {
		if(proxy==null) {
			proxy=PayOrderExtServiceProxy.api();
		}
		return proxy;
	}

	/**
	 * 支付订单 功能主页面
	 */
	@RequestMapping("/pay_order_ext_list.html")
	public String list(Model model,HttpServletRequest request) {
		return getTemplatePath(prefix,"pay_order_ext_list");
	}

	/**
	 * 支付订单 表单页面
	 */
	@RequestMapping("/pay_order_ext_form.html")
	public String form(Model model,HttpServletRequest request , String id) {
		return getTemplatePath(prefix,"pay_order_ext_form");
	}
}