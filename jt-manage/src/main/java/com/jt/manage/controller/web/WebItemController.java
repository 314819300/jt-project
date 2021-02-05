package com.jt.manage.controller.web;

import com.jt.common.po.Item;
import com.jt.common.po.ItemDesc;
import com.jt.manage.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangning
 * @create 2021-02-04 21:04
 */
@RestController
@RequestMapping("/web/item")
public class WebItemController {

	@Autowired
	private ItemService itemService;
//	http://manage.jt.com/web/item/findItemById
	@RequestMapping("/findItemById")
	public Item findItemById(Long itemId) {

		return itemService.findItemById(itemId);
	}

	@RequestMapping("/findItemDescById")
	public ItemDesc findItemDescById(Long itemId) {

		return itemService.findItemDescById(itemId);
	}
}
