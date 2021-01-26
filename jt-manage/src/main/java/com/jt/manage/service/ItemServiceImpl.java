package com.jt.manage.service;

import com.jt.common.po.Item;
import com.jt.manage.mapper.ItemMapper;
import com.jt.manage.vo.EasyUI_Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

	@Override
	public void saveItem(Item item) {
		item.setStatus(1);//商品正常
		item.setCreated(new Date());
		item.setUpdated(item.getCreated());
		//使用通用mapper实现入库的操作
		itemMapper.insert(item);
	}

	/**
	 * 商品更新
	 * @param item
	 */
	@Override
	public void updateItem(Item item) {
		item.setUpdated(new Date());
		itemMapper.updateByPrimaryKeySelective(item);
	}

	@Override
	public void updateStatus(Long[] ids, int status) {
		itemMapper.updateStatus(ids, status);
	}


}
