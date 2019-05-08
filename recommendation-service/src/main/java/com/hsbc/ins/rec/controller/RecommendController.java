package com.hsbc.ins.rec.controller;


import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hsbc.ins.rec.response.JsonResult;
import com.hsbc.ins.rec.service.RecommendService;
import com.hsbc.ins.rec.utils.JsonRequest;
import com.hsbc.ins.rec.utils.Page;

@RestController
@RequestMapping("/api/v1")
public class RecommendController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RecommendController.class);
	
	@Autowired
	RecommendService recommendService;
	
	
	@PostMapping(value = "loadRecProds", produces = "application/json;charset=UTF-8")
	public JsonResult loadingRecommendedProducts(@RequestBody final String jsonString) throws JsonProcessingException {
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
		Map <String, Object> recProds = recommendService.recommend(jsonRequest.getCustomerId(), jsonRequest.getPage().getPageNum(), jsonRequest.getPage().getPageLimit());
		jsonResult = JsonResult.success("Load recommended products successful.", recProds);
		return jsonResult;
	}
}
