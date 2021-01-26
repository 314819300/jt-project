package com.jt.manage.service;

import com.jt.manage.vo.EasyUI_Tree;

import java.util.List;

/**
 * @author wangning
 * @create 2021-01-25 19:43
 */
public interface ItemCatService {
	List<EasyUI_Tree> findTree(Long parentId);
}
