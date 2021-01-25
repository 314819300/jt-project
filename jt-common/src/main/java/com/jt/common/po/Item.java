package com.jt.common.po;

/**
 * @author wangning
 * @create 2021-01-24 15:04
 */
public class Item extends BasePojo{
	private static final long serialVersionUID = -9219915256526652056L;
	/**
	 * 标题id
	 */
	private Long id;//bigint(10) not null auto_increment comment '商品ID，也是商品编号',
	/**
	 * 标题元素
	 */
	private String title;//varchar(100),
	/**
	 * 卖点信息
	 */
	private String sellPoint;//varchar(150),
	/**
	 * 价格
	 */
	private Long price;//bigint(20) comment '单位为：分',
	/**
	 * 数量
	 */
	private Integer num;//int(10),
	/**
	 * 二维码信息
	 */
	private String barcode;//varchar(30),
	/**
	 * 图片信息
	 */
	private String image;//varchar(500) comment '最多5张图片',
	/**
	 * 商品分类信息
	 */
	private Long cid;//bigint(10),
	/**
	 * 状态，默认值为1，可选值：1正常，2下架，3删除
	 */
	private Integer status;//int(1) default 1 comment '默认值为1，可选值：1正常，2下架，3删除',
	/**
	 * 创建时间
	 */
//	private Date created;//datetime,
	/**
	 * 修改时间
	 */
//	private Date updated;//datetime comment '列表排序时按修改时间排序，所以在新增时需要设置此值。',

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSellPoint() {
		return sellPoint;
	}

	public void setSellPoint(String sellPoint) {
		this.sellPoint = sellPoint;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
