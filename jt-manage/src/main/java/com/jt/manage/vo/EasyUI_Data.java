package com.jt.manage.vo;

import com.jt.common.po.Item;

import java.io.Serializable;
import java.util.List;

/**
 * @author wangning
 * @create 2021-01-24 15:37
 * 封装商品查询展示信息VO
 */
public class EasyUI_Data implements Serializable {
	private static final long serialVersionUID = 1553483981441213873L;
	private Integer total;//商品的记录总数
	private List<Item> rows;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<Item> getRows() {
		return rows;
	}

	public void setRows(List<Item> rows) {
		this.rows = rows;
	}

	public EasyUI_Data() {}

	public EasyUI_Data(Integer total, List<Item> rows) {
		this.total = total;
		this.rows = rows;
	}
}
