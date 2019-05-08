package com.hsbc.ins.rec.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.hsbc.ins.rec.persistence.CfContentBaseRepository;
import com.hsbc.ins.rec.persistence.CfItemBaseRepository;
import com.hsbc.ins.rec.persistence.CfUserBaseRepository;
import com.hsbc.ins.rec.service.RecommendService;
import com.hsbc.ins.rec.utils.CommonConstants;

@Service
public class RecommendServiceImpl implements RecommendService {
	
	@Autowired
	private CfContentBaseRepository cfContentBaseRepository;
	
	@Autowired
	private CfItemBaseRepository cfItemBaseRepository;
	
	@Autowired
	private CfUserBaseRepository cfUserBaseRepository;

	@Override
	public Map <String, Object> recommend(Long customerId, int pageNum, int pageLimit) {
		Map <String, Object> recommendResult = new HashMap<>();
		recommendResult.put(CommonConstants.CF_CONTENT_BASE, cfContentBaseRepository.findAllByCustomerIdOrderByRatingDesc(customerId, PageRequest.of(pageNum - 1,pageLimit)));
		recommendResult.put(CommonConstants.CF_ITEM_BASE, cfItemBaseRepository.findAllByCustomerIdOrderByRatingDesc(customerId, PageRequest.of(pageNum - 1,pageLimit)));
		recommendResult.put(CommonConstants.CF_USER_BASE, cfUserBaseRepository.findAllByCustomerIdOrderByRatingDesc(customerId, PageRequest.of(pageNum - 1,pageLimit)));
		return recommendResult;
	}
	
}
