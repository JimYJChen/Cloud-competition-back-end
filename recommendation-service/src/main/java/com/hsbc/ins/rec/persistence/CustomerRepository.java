package com.hsbc.ins.rec.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hsbc.ins.rec.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	Customer findByLoginNameAndLoginPassword(String loginName, String loginPassword);
	
	List<Customer> findByLoginName(String loginName);
	
}
