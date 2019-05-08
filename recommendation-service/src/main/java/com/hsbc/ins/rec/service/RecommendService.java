package com.hsbc.ins.rec.service;

import java.util.Map;

public interface RecommendService {
	
	public Map <String, Object>  recommend(Long customerId, int pageNum, int pageLimit);
	
}
