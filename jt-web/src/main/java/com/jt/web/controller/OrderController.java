package com.jt.web.controller;

import com.jt.common.po.Cart;
import com.jt.common.util.UserThreadLocalUtil;
import com.jt.web.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.WebParam;
import java.util.List;

/**
 * @author wangning
 * @create 2021-02-09 10:47
 */
@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private CartService cartService;

	@RequestMapping("/create")
	public String create(Model model) {
		//准备用的的购物车记录
		//此处需要配置拦截器，否则没有数据
		Long userId = UserThreadLocalUtil.get().getId();
		//根据userId获取购物车记录
		List<Cart> cartList = cartService.findCartListByUserId(userId);
		model.addAttribute("carts", cartList);

		return "order-cart";
	}
}
