package com.hsbc.ins.rec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.hsbc.ins.rec.domain.CusFavourite;
import com.hsbc.ins.rec.persistence.CusFavouriteRepository;
import com.hsbc.ins.rec.service.BehaviorService;

@Service
public class BehaviorServiceImpl implements BehaviorService {
	
	@Autowired
	private CusFavouriteRepository cusFavouriteRepository;

	@Override
	public List<CusFavourite> collectUserBehavior(List<CusFavourite> cusFavourites) {
		return cusFavouriteRepository.saveAll(cusFavourites);
	}

	@Override
	public List<CusFavourite> loadUserFavourites(Long customerId, int pageNum, int pageLimit) {
		return cusFavouriteRepository.findAllByCustomerIdOrderByCreateTimeDesc(customerId, PageRequest.of(pageNum - 1,pageLimit));
	}
	
}
