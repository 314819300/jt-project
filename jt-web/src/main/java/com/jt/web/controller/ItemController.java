package com.jt.web.controller;

import com.jt.common.po.Item;
import com.jt.common.po.ItemDesc;
import com.jt.web.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wangning
 * @create 2021-02-04 20:33
 */
@Controller
@RequestMapping("/items")
public class ItemController {

	@Autowired
	private ItemService itemService;
	/**
	 * 实现商品详情的展现
	 */
	@RequestMapping("/{itemId}")
	public String findItemById(@PathVariable Long itemId, Model model) {

		Item item = itemService.findItemById(itemId);
		ItemDesc itemDesc = itemService.findItemDescById(itemId);
		model.addAttribute("item", item);
		model.addAttribute("itemDesc", itemDesc);

		return "item";
	}
}
