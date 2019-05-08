package com.hsbc.ins.rec.persistence;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hsbc.ins.rec.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	
	List<Order>  findAllByCustomerIdOrderByCreateTimeDesc(Long customerId, Pageable pageable);
	
}
