package com.jt.manage.service;

import com.jt.common.po.Item;
import com.jt.common.po.ItemDesc;
import com.jt.manage.mapper.ItemDescMapper;
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
	@Autowired
	private ItemDescMapper itemDescMapper;

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
	public void saveItem(Item item, String desc) {
		item.setStatus(1);//商品正常
		item.setCreated(new Date());
		item.setUpdated(item.getCreated());
		//使用通用mapper实现入库的操作
		itemMapper.insert(item);
		//入库商品详情信息
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemDesc(desc);
		itemDesc.setItemId(item.getId());
		itemDescMapper.insert(itemDesc);
	}

	/**
	 * 商品更新
	 * @param item
	 */
	@Override
	public void updateItem(Item item, String desc) {
		item.setUpdated(new Date());
		itemMapper.updateByPrimaryKeySelective(item);
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(desc);
		itemDescMapper.updateByPrimaryKeySelective(itemDesc);
	}

	@Override
	public void updateStatus(Long[] ids, int status) {
		itemMapper.updateStatus(ids, status);
	}

	@Override
	public ItemDesc findItemDescById(Long itemId) {
		return itemDescMapper.selectByPrimaryKey(itemId);
	}

	/**
	 * 根据id查询商品详细信息
	 * @param itemId
	 * @return
	 */
	@Override
	public Item findItemById(Long itemId) {
		return itemMapper.selectByPrimaryKey(itemId);
	}


}
