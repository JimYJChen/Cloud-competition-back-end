package com.hsbc.ins.rec.domain;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
/*import lombok.Data;
import lombok.Getter;
import lombok.Setter;
*/
@Entity
@Table(name="t_goods")
public class Product implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4811424425739985751L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="goods_id")
	private long productId;
	
	@Column(name="goods_title")
	private String title;
	
	@Column(name="goods_desc")
	private String description;
	
	@Column(name="goods_pic1")
	private String imagePath1;
	
	@Column(name="goods_pic2")
	private String imagePath2;
	
	@Column(name = "goods_category_id")
	private long prodCategoryId;
	
	@Embedded
	private CurrencyAmount prodAmount;
	
	@Column(name="crt_datetime")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	
	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImagePath1() {
		return imagePath1;
	}

	public void setImagePath1(String imagePath1) {
		this.imagePath1 = imagePath1;
	}

	public String getImagePath2() {
		return imagePath2;
	}

	public void setImagePath2(String imagePath2) {
		this.imagePath2 = imagePath2;
	}

	public long getProdCategoryId() {
		return prodCategoryId;
	}

	public void setProdCategoryId(long prodCategoryId) {
		this.prodCategoryId = prodCategoryId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public CurrencyAmount getProdAmount() {
		return prodAmount;
	}

	public void setProdAmount(CurrencyAmount prodAmount) {
		this.prodAmount = prodAmount;
	}
	
}
