package com.jt.web.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.po.Item;
import com.jt.common.po.ItemDesc;
import com.jt.common.service.HttpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangning
 * @create 2021-02-04 20:46
 */
@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private HttpClientService httpClient;

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public Item findItemById(Long itemId) {
		//1.定义远程访问后台的url地址
		String url = "http://manage.jt.com/web/item/findItemById";
		//2.定义参数
		Map<String, String> params = new HashMap<>();
		params.put("itemId", itemId + "");
		Item item = null;
		try {
			String result = httpClient.doGet(url, params);
			System.out.println("result = " + result);
			item = objectMapper.readValue(result, Item.class);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return item;
	}

	@Override
	public ItemDesc findItemDescById(Long itemId) {
		//1.定义远程访问后台的url地址
		String url = "http://manage.jt.com/web/item/findItemDescById";
		//2.定义参数
		Map<String, String> params = new HashMap<>();
		params.put("itemId", itemId + "");
		ItemDesc itemDesc = null;
		try {
			String result = httpClient.doGet(url, params);
			System.out.println("result = " + result);
			itemDesc = objectMapper.readValue(result, ItemDesc.class);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return itemDesc;
	}
}
