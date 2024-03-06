package com.sppl.service;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sppl.constant.Constants;
import com.sppl.model.AccountReg1;

import lombok.Data;

@Service

@ConfigurationProperties(prefix = "prereg")
@Data
public class RedisService {
	
	
	
	private long preRegistrationInfoExpiry;
	
	
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	


	public void putPreReg1AccountToCache(AccountReg1 accountReg1) throws JsonProcessingException {
		String redisKey = Constants.PREREGISTER_PREFIX + accountReg1.getUsername();
		String jsonString = objectMapper.writeValueAsString(accountReg1);
		redisTemplate.opsForValue().set(redisKey.toString(), jsonString);
		redisTemplate.expire(redisKey,Duration.ofSeconds(preRegistrationInfoExpiry));
	long expireTimeInterval=	redisTemplate.getExpire(redisKey, TimeUnit.SECONDS);
	System.out.println("Data  expire time "+expireTimeInterval);
	
	System.out.println(" Pre-register expiry info from properties file : "+preRegistrationInfoExpiry);
		
	}
}
