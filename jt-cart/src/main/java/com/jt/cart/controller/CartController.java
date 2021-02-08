package com.jt.cart.controller;

import com.jt.cart.pojo.Cart;
import com.jt.cart.service.CartService;
import com.jt.cart.util.MapperUtil;
import com.jt.cart.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author wangning
 * @create 2021-02-07 15:41
 */
@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	/**
	 * 实现购物车列表的查询
	 */
	@RequestMapping("query/{userId}")
	@ResponseBody
	public SysResult findCartListByUserId(@PathVariable Long userId) {
		List<Cart> cartList = cartService.findCartListByUserId(userId);
		return SysResult.oK(cartList);

	}

	/**
	 * 实现购物车数量的加减
	 */
	@RequestMapping("/update/num/{userId}/{itemId}/{num}")
	@ResponseBody
	public SysResult updateCartNum(Cart cart) {
		try {
			cartService.updateCartNum(cart);
			return SysResult.oK();
		} catch (Exception e) {

		}
		return SysResult.build(201, "购物车数量修改失败");
	}
//	public SysResult updateCartNum(
//			@PathVariable Long userId,
//			@PathVariable Long itemId,
//			@PathVariable Integer num
//	) {
//
//
//	}

	/**
	 * 实现购物车新增
	 */
	@RequestMapping("/save")
	public SysResult saveCart(String cartJSON) {
		try {
			Cart cart = MapperUtil.toObject1(cartJSON, Cart.class);
			cartService.saveCart(cart);
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "购物车新增失败");

	}

	/**
	 * 实现购物车商品的删除
	 */
	@RequestMapping("delete/{userId}/{itemId}")
	public SysResult deleteCart(@PathVariable Long userId, @PathVariable Long itemId) {
		try {
			cartService.deleteCart(userId, itemId);
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "购物车商品删除失败");

	}



}
