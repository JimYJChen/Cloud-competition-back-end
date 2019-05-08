package com.hsbc.ins.rec.utils;

public class JsonRequestSearch {
	
    private String searchWords;    

	private Page page;
	
	public JsonRequestSearch() {
		
	}
	
	public JsonRequestSearch(Page page) {
		this.page = page;
	}
	
	public String getSearchWords() {
		return searchWords;
	}

	public void setSearchWords(String searchWords) {
		this.searchWords = searchWords;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

}
