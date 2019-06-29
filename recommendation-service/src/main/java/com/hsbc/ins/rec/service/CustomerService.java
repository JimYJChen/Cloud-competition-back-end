package com.hsbc.ins.rec.service;

import com.hsbc.ins.rec.domain.Customer;

public interface CustomerService {
	
	public Customer identify(String userName, String password);
	
	public Boolean register(Customer customer);
	
}
