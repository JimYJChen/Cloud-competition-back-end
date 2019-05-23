package com.hsbc.ins.rec.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.hsbc.ins.rec.domain.Order;
import com.hsbc.ins.rec.domain.ProdCategory;
import com.hsbc.ins.rec.domain.Product;
import com.hsbc.ins.rec.persistence.OrderRepository;
import com.hsbc.ins.rec.persistence.ProdCategoryRepository;
import com.hsbc.ins.rec.persistence.ProductRepository;
import com.hsbc.ins.rec.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired 
	ProdCategoryRepository prodCategoryRepository;

	@Override
	public Product findProductDetail(Long productId) {
		Product product = null;
		Optional<Product> optional = productRepository.findById(productId);
		if(optional.isPresent()) {
			product = optional.get();
		}
		return product;
	}

	@Override
	public List<Product> serchProduct(String searchWords, int pageNum, int pageLimit) {
		// TODO Auto-generated method stub
		return productRepository.queryByTitleAndDescriptionLike(searchWords, searchWords, PageRequest.of(pageNum - 1, pageLimit));
	}

	@Override
	public List<Product> serchProductByConditions(String searchWords, int pageNum, int pageLimit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> loadDefaultProducts(int pageNum, int pageLimit) {
		// TODO Auto-generated method stub
		return productRepository.findAllByOrderByCreateTimeDesc(PageRequest.of(pageNum - 1, pageLimit));
	}
	
	public List<Product> loadProdsFromOrders(Long customerId, int pageNum, int pageLimit){
		List<Product> products = new ArrayList<>(); 
		List<Order> orders = orderRepository.findAllByCustomerIdOrderByCreateTimeDesc(customerId, PageRequest.of(pageNum - 1, pageLimit));
		if(!CollectionUtils.isEmpty(orders)) {
			for(Order order : orders) {
				products.add(order.getProduct());
			}
		}
		return products;
	}
	
	public List<Order> loadOrders(Long customerId, int pageNum, int pageLimit){
		List<Order> orders = orderRepository.findAllByCustomerIdOrderByCreateTimeDesc(customerId, PageRequest.of(pageNum - 1, pageLimit));
		return orders;
	}
	
	public Order buy(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public List<ProdCategory> loadProdCategorys() {
		return prodCategoryRepository.findAll();
	}

	@Override
	public Page <Product> loadProdsByProdCategory(Long prodCategoryId, int pageNum, int pageLimit) {
		return  productRepository.findAllByProdCategoryIdOrderByCreateTimeDesc(prodCategoryId, PageRequest.of(pageNum - 1, pageLimit));
	}
}
