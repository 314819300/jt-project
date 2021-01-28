package com.jt.common.po;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.swing.*;

/**
 * @author wangning
 * @create 2021-01-26 10:53
 */
@Table(name = "tb_item_desc")
public class ItemDesc extends BasePojo {
	private static final long serialVersionUID = -4891193517989788253L;
	@Id
	private Long itemId;
	private String itemDesc;

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public ItemDesc() {}

	public ItemDesc(Long itemId, String itemDesc) {
		this.itemId = itemId;
		this.itemDesc = itemDesc;
	}
}
