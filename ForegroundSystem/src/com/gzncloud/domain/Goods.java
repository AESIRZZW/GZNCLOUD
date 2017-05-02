package com.gzncloud.domain;

import java.util.Date;

public class Goods {
	private Long good;

	private Long user;

	private String name;

	private String image1;

	private String image2;

	private String image3;

	private String description;

	private Date expire_at;

	private Long purchase_price;

	private Long retail_price;

	private String currency;

	private Date created_at;

	private Boolean deleted;

	public Long getGood() {
		return good;
	}

	public void setGood(Long good) {
		this.good = good;
	}

	public Long getUser() {
		return user;
	}

	public void setUser(Long user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1 == null ? null : image1.trim();
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2 == null ? null : image2.trim();
	}

	public String getImage3() {
		return image3;
	}

	public void setImage3(String image3) {
		this.image3 = image3 == null ? null : image3.trim();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	public Date getExpire_at() {
		return expire_at;
	}

	public void setExpire_at(Date expire_at) {
		this.expire_at = expire_at;
	}

	public Long getPurchase_price() {
		return purchase_price;
	}

	public void setPurchase_price(Long purchase_price) {
		this.purchase_price = purchase_price;
	}

	public Long getRetail_price() {
		return retail_price;
	}

	public void setRetail_price(Long retail_price) {
		this.retail_price = retail_price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency == null ? null : currency.trim();
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
}