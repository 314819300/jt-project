package com.jt.sso.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class BasePojo implements Serializable{
	/**
	 * 添加序列号
	 */

	private static final long serialVersionUID = -3552306420197341645L;
	private Date created;
	private Date updated;
}
