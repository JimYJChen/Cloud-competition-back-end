package com.hsbc.ins.rec.persistence;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hsbc.ins.rec.domain.CfItemBase;

@Repository
public interface CfItemBaseRepository extends JpaRepository<CfItemBase, Long> {
	
	List<CfItemBase> findAllByCustomerIdOrderByRatingDesc(Long customerId, Pageable pageable);
	
}
	