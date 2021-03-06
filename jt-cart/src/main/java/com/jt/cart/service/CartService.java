package com.jt.cart.service;

import com.jt.cart.pojo.Cart;

import java.util.List;

/**
 * @author wangning
 * @create 2021-02-07 15:42
 */
public interface CartService {
	List<Cart> findCartListByUserId(Long userId);

	void updateCartNum(Cart cart);

	void saveCart(Cart cart);

	void deleteCart(Long userId, Long itemId);
}
