package com.hsbc.ins.rec.controller;


import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hsbc.ins.rec.domain.Customer;
import com.hsbc.ins.rec.response.JsonResult;
import com.hsbc.ins.rec.service.CustomerService;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	CustomerService customerService;
	
	@PostMapping(value = "login", produces = "application/json;charset=UTF-8")
	public JsonResult login(@RequestBody final String jsonString) throws JsonProcessingException {
		JsonResult jsonResult = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			JsonNode nameNode = objectMapper.readTree(jsonString).get("loginName");
			JsonNode pwdNode = objectMapper.readTree(jsonString).get("loginPassWord");
			if(null != nameNode && null != pwdNode) {
				Customer customer = customerService.identify(nameNode.asText(), pwdNode.asText());
				if(null != customer) {
					jsonResult = JsonResult.success("Login successful", customer);
				}else {
					jsonResult = JsonResult.fail("Login FAILURE", "User name or password is incorrect.");
				}
			}else {
				return JsonResult.fail("Request data is invalid. Expect format { \"loginName\":\"\", \"loginPassWord\":\"\" } ", "501");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JsonResult.fail("Request data is invalid. Expect format { \"loginName\":\"\", \"loginPassWord\":\"\" } ", "501");
		}
		return jsonResult;
	}
}
