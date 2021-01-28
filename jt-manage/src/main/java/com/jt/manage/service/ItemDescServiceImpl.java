package com.jt.manage.service;

import com.jt.manage.mapper.ItemDescMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangning
 * @create 2021-01-26 10:59
 */
@Service
public class ItemDescServiceImpl implements ItemDescService {
	@Autowired
	private ItemDescMapper itemDescMapper;
}
