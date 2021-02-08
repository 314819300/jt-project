package com.jt.web.service;

import com.jt.common.po.Cart;
import com.jt.common.service.HttpClientService;
import com.jt.common.util.MapperUtil;
import com.jt.common.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.MarshalledObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangning
 * @create 2021-02-07 15:52
 */
@Service
public class CartServiceImpl implements CartService {
	@Autowired
	private HttpClientService httpClient;


	@Override
	public List<Cart> findCartListByUserId(Long userId) {
		String url = "http://cart.jt.com/cart/query/" + userId;

		String result = httpClient.doGet(url);
		SysResult sysResult = MapperUtil.toObject1(result, SysResult.class);
		List<Cart> cartList = (List<Cart>) sysResult.getData();

		return cartList;
	}

	@Override
	public void updateCart(Cart cart) {
		String url = "http://cart.jt.com/cart/update/num/" + cart.getUserId() + "/" + cart.getItemId() + "/" + cart.getNum();
		httpClient.doGet(url);
	}

	@Override
	public void saveCart(Cart cart) {
		String url = "http://cart.jt.com/cart/save";
		//按照原来的这种方法，封装数据的弊端
		//如果数据套多就会很繁琐
		String cartJSON = MapperUtil.toJSON(cart);
		Map<String, String> params = new HashMap<>();
		params.put("cartJSON", cartJSON);
//		params.put("", "");
//		params.put("", "");
//		params.put("", "");
		httpClient.doPost(url, params);
	}

	@Override
	public void deleteCart(Long userId, Long itemId) {
		String url = "http://cart.jt.com/cart/delete/"+ userId+"/"+itemId;
		httpClient.doGet(url);
	}
}
