package com.jt.cart.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class BasePojo implements Serializable{
	/**
	 * 添加序列号
	 */

	private static final long serialVersionUID = -771233981887371667L;
	private Date created;
	private Date updated;
}
