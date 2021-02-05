package com.jt.web.service;

import com.jt.common.po.Item;
import com.jt.common.po.ItemDesc;

/**
 * @author wangning
 * @create 2021-02-04 20:46
 */
public interface ItemService {
	Item findItemById(Long itemId);

	ItemDesc findItemDescById(Long itemId);
}
