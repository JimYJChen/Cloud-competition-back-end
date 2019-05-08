package com.hsbc.ins.rec.utils;

public class JsonRequest {
	
	private Long customerId;
	
	private Page page;
	
	public JsonRequest() {
		
	}
	
	public JsonRequest(Page page) {
		this.page = page;
	}
	
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

}
