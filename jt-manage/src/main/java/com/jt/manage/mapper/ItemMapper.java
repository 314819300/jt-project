package com.jt.manage.mapper;

import com.jt.common.mapper.SysMapper;
import com.jt.common.po.Item;
import com.jt.manage.vo.EasyUI_Data;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author wangning
 * @create 2021-01-24 15:30
 */
public interface ItemMapper extends SysMapper<Item> {
	
	@Select("select count(*) from tb_item")
	int findCount();

	/**
	 * Mybatis中如果有多值传参的，则需要将多值封装为单值
	 * 例子：封装为对象， 封装为Map集合 封装为List/array
	 * @param start
	 * @param rows
	 * @return
	 */
	List<Item> findItemByPage(@Param("start") int start, @Param("rows") Integer rows);

	@Select("select name from tb_item_cat where id = #{itemId}")
	String findItemCatNameById(Long itemId);
}
