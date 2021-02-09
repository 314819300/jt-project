package com.jt.web.controller;

import com.jt.common.po.Cart;
import com.jt.common.util.UserThreadLocalUtil;
import com.jt.common.vo.SysResult;
import com.jt.web.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author wangning
 * @create 2021-02-07 15:52
 */
@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private CartService cartService;

	/**
	 * 实现购物车列表的展现
	 */
	@RequestMapping("/show")
	public String findCartList(HttpServletRequest request, Model model) {
//		long userId = 7L;//暂时写死
		//第一种
		Long userId = (Long) request.getSession().getAttribute("JT_WEB_USER");
		//第二种
		userId = UserThreadLocalUtil.get().getId();
		List<Cart> cartList = cartService.findCartListByUserId(userId);
		model.addAttribute("cartList", cartList);
		return "cart";
	}

	/**
	 * 编辑前台controller
	 */
	@RequestMapping("/update/num/{itemId}/{num}")
	@ResponseBody
	public SysResult updateCartNum(HttpServletRequest request, @PathVariable Long itemId, @PathVariable Integer num) {

		try {
			Cart cart = new Cart();
//			Long userId = 7L;
			Long userId = (Long) request.getSession().getAttribute("JT_WEB_USER");
			cart.setUserId(userId);
			cart.setItemId(itemId);
			cart.setNum(num);
			cartService.updateCart(cart);
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "用户修改数量失败");
	}

	/**
	 * 添加购物车
	 * @return
	 */
	@RequestMapping("/add/{itemId}")
	public String saveCart(HttpServletRequest request, Cart cart) {
//		Long userId = 7L;
		Long userId = (Long) request.getSession().getAttribute("JT_WEB_USER");
		cart.setUserId(userId);
		cartService.saveCart(cart);
		return "redirect:/cart/show.html";//直接写是转发不是重定向
	}

	/**
	 * 删除购物车商品
	 */
	@RequestMapping("delete/{itemId}")
	public String deleteCart(HttpServletRequest request, @PathVariable Long itemId) {
//		Long userId = 7L;
		Long userId = (Long) request.getSession().getAttribute("JT_WEB_USER");
		cartService.deleteCart(userId, itemId);
		return "redirect:/cart/show.html";
	}

}
