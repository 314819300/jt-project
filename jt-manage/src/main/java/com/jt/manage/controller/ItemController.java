package com.jt.manage.controller;

import com.jt.manage.service.ItemService;
import com.jt.manage.vo.EasyUI_Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @author wangning
 * @create 2021-01-24 15:30
 */
@Controller
@RequestMapping("/item")
public class ItemController {
	@Autowired
	private ItemService itemService;

	//实现商品列表的分页查询
	@RequestMapping("/query")
	@ResponseBody
	public EasyUI_Data findItemByPage(Integer page,Integer rows) {
		return itemService.findItemByPage(page,rows);
	}

	/**
	 * 根据商品分类ID查询分类名称
	 * spring4级以下，如果返回值是String类型，则采用ISO-8859-1格式转换
	 * 在StringHttpMessageConverter这个类中，声明了默认的编码格式
	 * public static final Charset DEFAULT_CHARSET = Charset.forName("ISO-8859-1");
	 *
	 * 如果返回值是对象，则会采用UTF-8格式编码
	 * 在AbstractJackson2HttpMessageConverter类中，声明了默认的
	 * public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
	 *
	 * string的编码格式错乱问题，
	 * 此问题在spring5中已经解决
	 *
	 */
	@RequestMapping(value = "/cat/queryItemName",produces = "text/html;charset=utf-8")
	@ResponseBody
	public String findItemCatNameById(Long itemId, HttpServletResponse response) {
//		response.setContentType("text/html");//实际的源码中是这样写的
//		response.setCharacterEncoding("utf-8");
		return itemService.findItemCatNameById(itemId);

	}
}