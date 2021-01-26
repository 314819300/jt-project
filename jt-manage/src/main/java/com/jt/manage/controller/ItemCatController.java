package com.jt.manage.controller;

import com.jt.common.po.Item;
import com.jt.common.vo.SysResult;
import com.jt.manage.service.ItemCatService;
import com.jt.manage.vo.EasyUI_Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.Year;
import java.util.List;

/**
 * @author wangning
 * @create 2021-01-25 19:45
 */
@Controller
@RequestMapping("/item")
public class ItemCatController {
	@Autowired
	private ItemCatService itemCatService;

	/**
	 * 实现商品属性结构的展现
	 * 当页面中没有传递id时，默认值为0，查询一级商品分类信息
	 * 如果页面中传递了参数，则获取我们的参数
	 * @param parentId
	 * @return
	 */
	@RequestMapping("/cat/list")
	@ResponseBody
	public List<EasyUI_Tree> findTree(@RequestParam(value = "id",defaultValue = "0") Long parentId) {
		//展现一级信息列表
		return itemCatService.findTree(parentId);
	}
}
