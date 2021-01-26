package com.jt.common.po;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author wangning
 * @create 2021-01-25 13:24
 */
@Table(name = "tb_item_cat")
public class ItemCat extends BasePojo {
	private static final long serialVersionUID = 1825183538215329140L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;			//主键
	private Long parentId;		//父ID=0时，代表一级类目
	private String name;		//分类名称
	private Integer status;		//默认值为1，可选值：1正常，2删除'
	private Integer sortOrder;	//排序号
	private Boolean isParent;	//是否为父级 true closed  false open

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(Boolean parent) {
		isParent = parent;
	}


	public ItemCat() {}

	public ItemCat(Long id, Long parentId, String name, Integer status, Integer sortOrder, Boolean isParent) {
		this.id = id;
		this.parentId = parentId;
		this.name = name;
		this.status = status;
		this.sortOrder = sortOrder;
		this.isParent = isParent;
	}
}
