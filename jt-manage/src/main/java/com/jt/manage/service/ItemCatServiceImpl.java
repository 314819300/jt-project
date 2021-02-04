package com.jt.manage.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.po.ItemCat;
import com.jt.common.service.RedisService;
import com.jt.manage.mapper.ItemCatMapper;
import com.jt.manage.vo.EasyUI_Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.ShardedJedis;

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

	@Autowired
//	private Jedis jedis;
//	private ShardedJedis jedis;
//	private RedisService redisService;
	private JedisCluster jedisCluster;

	@Autowired
	private ObjectMapper objectMapper;

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

	/**
	 * 1.用户查询 首先查询redis
	 * 2.如果redis中没有数据，则查询数据,之后将数据写入redis
	 * 3.如果redis中有数据，则查询缓存数据，之后返回
	 * @param parentId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<EasyUI_Tree> findTreeCache(Long parentId) {
		//此处我们为什么不用parentId作为key呢，应为避免有其它的key值相同会造成冲突
		//所以我们最好加一些前缀
		String key = "ITEM_CAT_" + parentId;
		String json = jedisCluster.get(key);
		List<EasyUI_Tree> treeList = new ArrayList<>();
		try {
			if(StringUtils.isEmpty(json)) {
				System.out.println("用户查询数据库！！！");
				treeList = findTree(parentId);
				String listJSON = objectMapper.writeValueAsString(treeList);
				jedisCluster.set(key, listJSON);
			} else {
				System.out.println("用户查询缓存！！！");
				treeList = objectMapper.readValue(json, treeList.getClass());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return treeList;
	}
}
