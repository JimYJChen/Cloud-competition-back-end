package com.hsbc.ins.rec.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.hsbc.ins.rec.domain.Order;
import com.hsbc.ins.rec.domain.ProdCategory;
import com.hsbc.ins.rec.domain.Product;

public interface ProductService {
	
	public Product findProductDetail(Long productId);
	
	public List<Product> serchProduct(String searchWords, int pageNum, int pageLimit);
	
	public List<Product> serchProductByConditions(String searchWords, int pageNum, int pageLimit);
	
	public List<Product> loadDefaultProducts(int pageNum, int pageLimit);
	
	public List<Product> loadProdsFromOrders(Long customerId, int pageNum, int pageLimit);
	
	public List<Order> loadOrders(Long customerId, int pageNum, int pageLimit);
	
	public Order buy(Order order);
	
	public List<ProdCategory> loadProdCategorys();
	
	public Page<Product> loadProdsByProdCategory(Long prodCategoryId, int pageNum, int pageLimit);
	
}
