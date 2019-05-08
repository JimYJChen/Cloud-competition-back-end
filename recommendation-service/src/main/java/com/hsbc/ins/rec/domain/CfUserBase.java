package com.hsbc.ins.rec.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_recommendation_cf_user")
public class CfUserBase implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4319104429974075388L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="rec_id")
	private long cfUserBaseId;
	
	@Column(name="user_id")
	private long customerId;
	
	@OneToOne(cascade=CascadeType.DETACH)
	@JoinColumn(name = "goods_id")
	private Product product;
	
	@Column(name="rating")
	private BigDecimal rating;

	public long getCfUserBaseId() {
		return cfUserBaseId;
	}

	public void setCfUserBaseId(long cfUserBaseId) {
		this.cfUserBaseId = cfUserBaseId;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public BigDecimal getRating() {
		return rating;
	}

	public void setRating(BigDecimal rating) {
		this.rating = rating;
	}
	
}
