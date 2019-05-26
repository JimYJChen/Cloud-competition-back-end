package com.hsbc.ins.rec.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.hsbc.ins.rec.domain.CfContentBase;
import com.hsbc.ins.rec.domain.CfItemBase;
import com.hsbc.ins.rec.domain.CfUserBase;
import com.hsbc.ins.rec.domain.Order;
import com.hsbc.ins.rec.domain.Product;
import com.hsbc.ins.rec.persistence.CfContentBaseRepository;
import com.hsbc.ins.rec.persistence.CfItemBaseRepository;
import com.hsbc.ins.rec.persistence.CfUserBaseRepository;
import com.hsbc.ins.rec.persistence.OrderRepository;
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
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Map <String, Object> recommend(Long customerId, int pageNum, int pageLimit) {
		Map <String, Object> recommendResult = new HashMap<>();
		recommendResult.put(CommonConstants.CF_CONTENT_BASE, cfContentBaseRepository.findAllByCustomerIdOrderByRatingDesc(customerId, PageRequest.of(pageNum - 1,pageLimit)));
		recommendResult.put(CommonConstants.CF_ITEM_BASE, cfItemBaseRepository.findAllByCustomerIdOrderByRatingDesc(customerId, PageRequest.of(pageNum - 1,pageLimit)));
		recommendResult.put(CommonConstants.CF_USER_BASE, cfUserBaseRepository.findAllByCustomerIdOrderByRatingDesc(customerId, PageRequest.of(pageNum - 1,pageLimit)));
		filterOrderProds(recommendResult, orderRepository.findAllByCustomerIdOrderByCreateTimeDesc(customerId));
		return recommendResult;
	}
	
	public void filterOrderProds(Map <String, Object> recommendResult, List<Order> orders) {
		if(!CollectionUtils.isEmpty(orders)) {
			List<Product> products = new ArrayList<>();
			for(Order order : orders) {
				products.add(order.getProduct());
			}
			List<CfItemBase>  cfItemBases = (List<CfItemBase>)recommendResult.get(CommonConstants.CF_ITEM_BASE);
			Iterator<CfItemBase> cfItemBaseIterator = cfItemBases.iterator();
			while(cfItemBaseIterator.hasNext()) {
				if(products.contains(cfItemBaseIterator.next().getProduct())) {
					System.out.print("Remove " + cfItemBaseIterator.next().getProduct().getProductId());
					cfItemBaseIterator.remove();
				}
			}
			List<CfUserBase>  cfUserBases = (List<CfUserBase>)recommendResult.get(CommonConstants.CF_USER_BASE);
			Iterator<CfUserBase> cfUserBaseIterator = cfUserBases.iterator();
			while(cfUserBaseIterator.hasNext()) {
				if(products.contains(cfUserBaseIterator.next().getProduct())) {
					System.out.print("Remove " + cfUserBaseIterator.next().getProduct().getProductId());
					cfUserBaseIterator.remove();
				}
			}
			List<CfContentBase>  cfContentBase = (List<CfContentBase>)recommendResult.get(CommonConstants.CF_CONTENT_BASE);
			Iterator<CfContentBase> cfContentBaseIterator = cfContentBase.iterator();
			while(cfContentBaseIterator.hasNext()) {
				if(products.contains(cfContentBaseIterator.next().getProduct())) {
					System.out.print("Remove " + cfContentBaseIterator.next().getProduct().getProductId());
					cfContentBaseIterator.remove();
				}
			}
		}
	}
}
