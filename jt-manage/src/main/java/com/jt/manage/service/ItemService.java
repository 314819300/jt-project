package com.jt.manage.service;

import com.jt.manage.vo.EasyUI_Data;

/**
 * @author wangning
 * @create 2021-01-24 15:31
 */
public interface ItemService {

	EasyUI_Data findItemByPage(Integer page, Integer rows);

	String findItemCatNameById(Long itemId);
}
