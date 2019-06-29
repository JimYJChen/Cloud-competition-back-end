package com.hsbc.ins.rec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.hsbc.ins.rec.domain.Customer;
import com.hsbc.ins.rec.persistence.CustomerRepository;
import com.hsbc.ins.rec.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	
	@Override
	public Customer identify(String userName, String password) {
		
		return customerRepository.findByLoginNameAndLoginPassword(userName, password);
		
	}

	@Override
	public Boolean register(Customer customer) {
		List <Customer> customers = customerRepository.findByLoginName(customer.getLoginName());
		if(!CollectionUtils.isEmpty(customers)){
			return false;
		}
		customerRepository.save(customer);
		return true; 
	}
	
	

}
