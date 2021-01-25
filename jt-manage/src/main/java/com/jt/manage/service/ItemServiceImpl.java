package com.jt.manage.service;

import com.jt.common.po.Item;
import com.jt.manage.mapper.ItemMapper;
import com.jt.manage.vo.EasyUI_Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangning
 * @create 2021-01-24 15:31
 */
@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private ItemMapper itemMapper;

	@Override
	public EasyUI_Data findItemByPage(Integer page, Integer rows) {
//		int total = itemMapper.findCount();//商品记录总数
		//如果没有where条件写null，如果有where条件，则为对象的属性赋值就可以
		int total = itemMapper.selectCount(null);
		int start = (page - 1)*rows;
		List<Item> itemList = itemMapper.findItemByPage(start,rows); //分页后查询结果
		return new EasyUI_Data(total, itemList);
	}

	@Override
	public String findItemCatNameById(Long itemId) {
		return itemMapper.findItemCatNameById(itemId);
	}
}
