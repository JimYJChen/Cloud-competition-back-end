package com.hsbc.ins.rec.domain;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="t_favourite")
public class CusFavourite implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8094953880591002542L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="fav_id")
	private long favouriteId;
	
	@Column(name = "user_id")
	private Long customerId;
	
	@OneToOne(cascade=CascadeType.DETACH)
	@JoinColumn(name = "goods_id")
	private Product product;
	
	@Column(name="crt_datetime")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	
	public CusFavourite() {
		
	}
	
	public CusFavourite(Product product) {
		this.product = product;
	}
	
	public long getFavouriteId() {
		return favouriteId;
	}

	public void setFavouriteId(long favouriteId) {
		this.favouriteId = favouriteId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
