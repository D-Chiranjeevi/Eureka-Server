package com.sppl.controller;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sppl.inbout.response.AccountReg1Resp;
import com.sppl.model.AccountReg1;
import com.sppl.service.AccountService;
import com.sppl.service.RedisService;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private RedisService redisService;

	@Autowired
	private AccountService accountService;

	@Value("${result-file-4d}")
	private String fourdResults;

	@Value("${result-file-toto}")
	private String totoResults;
	
	@Value("${result-file-toto1}")
	private String totoResults1;
	
	@Value("${result-file-toto2}")
	private String totoResults2;
	
	@Value("${result-file-toto3}")
	private String totoResults3;

	@Value("${result-file-sweep}")
	private String sweepResults;

	private static Logger logger = LoggerFactory.getLogger(AccountController.class);

	@PostMapping("reg/part1")
	public AccountReg1Resp registerAccount1(@RequestBody AccountReg1 accountReg1) throws JsonProcessingException {

		String regUUID = String.valueOf(UUID.randomUUID());
		accountReg1.setRegUUID(regUUID);
		accountService.saveAccount(accountReg1);
		redisService.putPreReg1AccountToCache(accountReg1);

		String otpSessionId = "Session id need to be update";
		AccountReg1Resp accountReg1Resp = new AccountReg1Resp();
		accountReg1Resp.setRegUUID(regUUID);
		accountReg1Resp.setOtpSessionId(otpSessionId);
		accountReg1Resp.setMobileNumber(accountReg1.getMobileNumber());
		System.out.println(accountReg1Resp);
		return accountReg1Resp;
	}

	@GetMapping("/getById/{regUUID}")
	public AccountReg1 getById(@PathVariable("regUUID") String regUUID) {
		// logger.info("----------------------fetch data from db with
		// key----------------------------- : "+regUUID);
		return accountService.getById(regUUID);

	}

	@GetMapping("/results/{type}")
	public JsonNode getMethodName(@PathVariable("type") String type, @RequestParam(required = false) String drawNo) {
		String file = readJsonByType(type,drawNo);
		ClassPathResource classPathResource = new ClassPathResource(file);
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = null;
		try {
			jsonNode = objectMapper.readTree(classPathResource.getInputStream());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonNode;
	}

	public String readJsonByType(String type, String drawNo) {
		if (type == null) {
			return null;
		}
		String filePath;
		if (type.equalsIgnoreCase("4d")) {
			filePath = fourdResults;
		} else if (type.equalsIgnoreCase("spss")) {
			filePath = sweepResults;
		} else if (type.equalsIgnoreCase("toto") && drawNo ==null ) {
 			filePath = totoResults;
		}else if (type.equalsIgnoreCase("toto") && drawNo.equalsIgnoreCase("3882")){
			filePath=totoResults1;
		}else if(type.equalsIgnoreCase("toto") && drawNo.equalsIgnoreCase("3881")) {
			filePath=totoResults2;
		}else if(type.equalsIgnoreCase("toto") && drawNo.equalsIgnoreCase("3880")) {
			filePath=totoResults3;
		}
		 else {
			return null;
		}
		return filePath;
	}

	

	
}
