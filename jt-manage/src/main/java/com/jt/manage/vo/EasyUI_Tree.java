package com.jt.manage.vo;

import java.io.Serializable;

/**
 * @author wangning
 * @create 2021-01-25 19:53
 * 为了展示页面树形结构封住VO对象
 */
public class EasyUI_Tree implements Serializable {
	private static final long serialVersionUID = 6201084284663206900L;
	private Long id;		//id，节点编号
	private String text;	//文本名称
	private String state;	//开闭，closed-关闭，open-展开

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public EasyUI_Tree() {}

	public EasyUI_Tree(Long id, String text, String state) {
		this.id = id;
		this.text = text;
		this.state = state;
	}
}
