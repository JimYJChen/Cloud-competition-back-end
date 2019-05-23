package com.hsbc.ins.rec.utils;

public class JsonRequestCategory {
	
	private Long prodCategoryId;
	
	private Page page;
	
	public JsonRequestCategory() {
		
	}
	
	public JsonRequestCategory(Page page) {
		this.page = page;
	}
	
	public Long getProdCategoryId() {
		return prodCategoryId;
	}

	public void setProdCategoryId(Long prodCategoryId) {
		this.prodCategoryId = prodCategoryId;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

}
