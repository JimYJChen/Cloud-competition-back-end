package com.hsbc.ins.rec.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hsbc.ins.rec.domain.CusFavourite;
import com.hsbc.ins.rec.response.JsonResult;
import com.hsbc.ins.rec.service.BehaviorService;
import com.hsbc.ins.rec.utils.JsonRequest;
import com.hsbc.ins.rec.utils.Page;

@RestController
@RequestMapping("/api/v1")
public class BehaviorController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BehaviorController.class);
	
	@Autowired
	BehaviorService behaviorService;
	
	@PostMapping(value = "loadFavouriteProds", produces = "application/json;charset=UTF-8")
	public JsonResult loadingUserFavourites(@RequestBody final String jsonString) throws JsonProcessingException {
		JsonRequest jsonRequest;
		JsonResult jsonResult = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			jsonRequest = objectMapper.readValue(jsonString, JsonRequest.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JsonResult.fail("Request data is invalid. Expect format is " + objectMapper.writeValueAsString(new JsonRequest(new Page())), "501");
		}
		List<CusFavourite> userFavourites = behaviorService.loadUserFavourites(jsonRequest.getCustomerId(), jsonRequest.getPage().getPageNum(), jsonRequest.getPage().getPageLimit());
		jsonResult = JsonResult.success("Load favourite products successful.", userFavourites);
		return jsonResult;
	}
	
	@PostMapping(value = "storeBehavior", produces = "application/json;charset=UTF-8")
	public JsonResult collectUserBehavior(@RequestBody final String jsonString) throws JsonProcessingException {
		List<CusFavourite> cusFavourites = new ArrayList<>();
		CusFavourite cusFavourit = null;
		JsonResult jsonResult = null;
		ObjectMapper objectMapper = new ObjectMapper();
		
		cusFavourit = JSON.parseObject(jsonString, CusFavourite.class);
		cusFavourites.add(cusFavourit);
		/*try {
			cusFavourites = objectMapper.readValue(jsonString, new TypeReference<List<CusFavourite>>() { });
		} catch (IOException e) {
			e.printStackTrace();
			return JsonResult.fail("Request data is invalid. Expect format is " + objectMapper.writeValueAsString(new CusFavourite(new Product())), "501");
		}*/
		List<CusFavourite> savedCusFavourites = behaviorService.collectUserBehavior(cusFavourites);
		if(null != savedCusFavourites) {
			jsonResult = JsonResult.success("Store user behavior successful.", savedCusFavourites);
		}else {
			jsonResult = JsonResult.fail("Store user behavior fail.", "502");
		}
		return jsonResult;
	}
	
}
