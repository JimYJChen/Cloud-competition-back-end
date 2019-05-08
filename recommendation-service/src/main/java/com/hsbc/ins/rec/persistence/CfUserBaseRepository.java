package com.hsbc.ins.rec.persistence;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hsbc.ins.rec.domain.CfUserBase;

@Repository
public interface CfUserBaseRepository extends JpaRepository<CfUserBase, Long> {
	
	List<CfUserBase> findAllByCustomerIdOrderByRatingDesc(Long customerId, Pageable pageable);

}
