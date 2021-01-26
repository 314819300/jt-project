package com.jt.manage.service;

import com.jt.common.po.ItemCat;
import com.jt.manage.mapper.ItemCatMapper;
import com.jt.manage.vo.EasyUI_Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangning
 * @create 2021-01-25 19:43
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private ItemCatMapper itemCatMapper;

	//基于通用Mapper查询数据信息
	public List<ItemCat> findItemCatByParentId(Long parentId) {
		ItemCat itemCat = new ItemCat();
		itemCat.setParentId(parentId);
		return itemCatMapper.select(itemCat);
	}

	@Override
	public List<EasyUI_Tree> findTree(Long parentId) {
		List<ItemCat> catList = findItemCatByParentId(parentId);
		//把ItemCat转化为EasyUI_Tree
		List<EasyUI_Tree> treeList = new ArrayList<>();
		for(ItemCat itemCat : catList) {
			EasyUI_Tree tree = new EasyUI_Tree();
			tree.setId(itemCat.getId());
			tree.setText(itemCat.getName());
			String state = itemCat.getIsParent() ? "closed" : "open";
			tree.setState(state);
			treeList.add(tree);
		}
		return treeList;
	}
}
