package com.hsbc.ins.rec.persistence;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hsbc.ins.rec.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	List <Product> findByTitleLike(String searchStrTitle, Pageable pageable);
	
	@Query("select u from Product u where u.title like %?1% or u.description like %?2%")
	List <Product> queryByTitleAndDescriptionLike(String searchWords1, String searchWords2, Pageable pageable);
	
	List <Product> findAllByOrderByCreateTimeDesc(Pageable pageable);
}
