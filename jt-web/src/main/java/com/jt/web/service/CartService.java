package com.jt.web.service;

import com.jt.common.po.Cart;

import java.util.List;

/**
 * @author wangning
 * @create 2021-02-07 15:52
 */
public interface CartService {
	List<Cart> findCartListByUserId(Long userId);

	void updateCart(Cart cart);

	void saveCart(Cart cart);

	void deleteCart(Long userId, Long itemId);
}
