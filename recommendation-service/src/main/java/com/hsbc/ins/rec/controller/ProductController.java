package com.hsbc.ins.rec.controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hsbc.ins.rec.domain.Order;
import com.hsbc.ins.rec.domain.ProdCategory;
import com.hsbc.ins.rec.domain.Product;
import com.hsbc.ins.rec.response.JsonResult;
import com.hsbc.ins.rec.service.ProductService;
import com.hsbc.ins.rec.utils.JsonRequest;
import com.hsbc.ins.rec.utils.JsonRequestCategory;
import com.hsbc.ins.rec.utils.JsonRequestSearch;
import com.hsbc.ins.rec.utils.Page;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	ProductService productService;
	
	@GetMapping(value = "loadProdCategorys", produces = "application/json;charset=UTF-8")
	public JsonResult loadingloadProdCategorys() throws JsonProcessingException {
		JsonResult jsonResult = null;
		List<ProdCategory> prodCategorys = productService.loadProdCategorys();
		jsonResult = JsonResult.success("Load product categorys successful.", prodCategorys);
		return jsonResult;
	}
	
	@PostMapping(value = "loadProductsByProdCategory", produces = "application/json;charset=UTF-8")
	public JsonResult loadingProductsByProdCategory(@RequestBody final String jsonString) throws JsonProcessingException {
		JsonRequestCategory jsonRequest;
		JsonResult jsonResult = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			jsonRequest = objectMapper.readValue(jsonString, JsonRequestCategory.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JsonResult.fail("Request data is invalid. Expect format " + objectMapper.writeValueAsString(new JsonRequestCategory(new Page())), "501");
		}
		org.springframework.data.domain.Page <Product> products = productService.loadProdsByProdCategory(jsonRequest.getProdCategoryId(), jsonRequest.getPage().getPageNum(), jsonRequest.getPage().getPageLimit());
		Map <String, Object> result = new HashMap<>();
		result.put("data", products.getContent());
		result.put("totalPages", products.getTotalPages());
		result.put("totalElements", products.getTotalElements());
		jsonResult = JsonResult.success("load products from order successful.", result);
		return jsonResult;
	}
	
	@PostMapping(value = "loadProductDetail", produces = "application/json;charset=UTF-8")
	public JsonResult loadingProductDetail(@RequestBody final String jsonString) throws JsonProcessingException {
		Long productId;
		JsonResult jsonResult = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			JsonNode node = objectMapper.readTree(jsonString).get("productId");
			if(null != node) {
				productId = node.asLong();
			}else {
				return JsonResult.fail("Request data is invalid. The field customerId is missing. Expect format {\"productId\":\"\"} ", "501");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JsonResult.fail("Request data is invalid. The field customerId is missing. Expect format {\"productId\":\"\"} ", "501");
		}
		Product product = productService.findProductDetail(productId);
		jsonResult = JsonResult.success("Load product detail successful.", product);
		return jsonResult;
	}
	
	@PostMapping(value = "loadProducts", produces = "application/json;charset=UTF-8")
	public JsonResult loadingProducts(@RequestBody final String jsonString) throws JsonProcessingException {
		JsonResult jsonResult = null;
		ObjectMapper objectMapper = new ObjectMapper();
		Page page;
		try {
			page = objectMapper.readValue(jsonString, Page.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JsonResult.fail("Request data is invalid. Expect format " + objectMapper.writeValueAsString(new Page()), "501");
		}
		List<Product> products = productService.loadDefaultProducts(page.getPageNum(), page.getPageLimit());
		jsonResult = JsonResult.success("Load products successful.", products);
		return jsonResult;
	}
	
	@PostMapping(value = "searchProducts", produces = "application/json;charset=UTF-8")
	public JsonResult searchProducts(@RequestBody final String jsonString) throws JsonProcessingException {
		JsonRequestSearch jsonRequestSearch;
		JsonResult jsonResult = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			jsonRequestSearch = objectMapper.readValue(jsonString, JsonRequestSearch.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JsonResult.fail("Request data is invalid. Expect format " + objectMapper.writeValueAsString(new JsonRequestSearch(new Page())), "501");
		}
		List<Product> products = productService.serchProduct(jsonRequestSearch.getSearchWords(), jsonRequestSearch.getPage().getPageNum(), jsonRequestSearch.getPage().getPageLimit());
		jsonResult = JsonResult.success("Search products successful.", products);
		return jsonResult;
	}
	
	@PostMapping(value = "loadProdsFromOrder", produces = "application/json;charset=UTF-8")
	public JsonResult loadingProductsFromOrders(@RequestBody final String jsonString) throws JsonProcessingException {
		JsonRequest jsonRequest;
		JsonResult jsonResult = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			jsonRequest = objectMapper.readValue(jsonString, JsonRequest.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JsonResult.fail("Request data is invalid. Expect format " + objectMapper.writeValueAsString(new JsonRequest(new Page())), "501");
		}
		List<Product> products = productService.loadProdsFromOrders(jsonRequest.getCustomerId(), jsonRequest.getPage().getPageNum(), jsonRequest.getPage().getPageLimit());
		jsonResult = JsonResult.success("load products from order successful.", products);
		return jsonResult;
	}
	
	@PostMapping(value = "loadOrder", produces = "application/json;charset=UTF-8")
	public JsonResult loadingOrders(@RequestBody final String jsonString) throws JsonProcessingException {
		JsonRequest jsonRequest;
		JsonResult jsonResult = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			jsonRequest = objectMapper.readValue(jsonString, JsonRequest.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JsonResult.fail("Request data is invalid. Expect format " + objectMapper.writeValueAsString(new JsonRequest(new Page())), "501");
		}
		List<Order> orders = productService.loadOrders(jsonRequest.getCustomerId(), jsonRequest.getPage().getPageNum(), jsonRequest.getPage().getPageLimit());
		jsonResult = JsonResult.success("Load orders successful.", orders);
		return jsonResult;
	}
	
	@PostMapping(value = "buyNow", produces = "application/json;charset=UTF-8")
	public JsonResult buyNow(@RequestBody final String jsonString) throws JsonProcessingException {
		Order order;
		JsonResult jsonResult = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			order = objectMapper.readValue(jsonString, Order.class);
		} catch (IOException e) {
			e.printStackTrace();
			return JsonResult.fail("Request data is invalid. Expect format " + objectMapper.writeValueAsString(new Order(new Product())), "501");
		}
		Order tradedOrder = productService.buy(order);
		if(null != order) {
			jsonResult = JsonResult.success("Trade successful.", tradedOrder);
		}else {
			jsonResult = JsonResult.fail("Trade fail.", "502");
		}
		return jsonResult;
	}
	
}
