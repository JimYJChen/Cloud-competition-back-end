package com.hsbc.ins.rec.service;

import java.util.List;

import com.hsbc.ins.rec.domain.CusFavourite;

public interface BehaviorService {
	
	public List<CusFavourite> collectUserBehavior(List<CusFavourite> cusFavourites);
	
	public List<CusFavourite> loadUserFavourites(Long customerId, int pageNum, int pageLimit);
	
}
