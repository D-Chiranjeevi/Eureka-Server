package com.sppl.service.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sppl.model.response.LotterySummaryResp;
import com.sppl.service.RedisService;
import com.sppl.util.Constants;

@Service
public class RedisServiceImpl implements RedisService {
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	@Autowired
	private ObjectMapper objectMapper;
	@Value("${lottery-result-cache-expiry}")
	private long lotteryResultCacheExpiry;

	public void putLotterySummaryResultInCache(String gameRef, String jsonString) {

		String redisKey = createLotterySummaryRedisKey(gameRef);
		redisTemplate.opsForValue().set(redisKey, jsonString);
		
		//redisTemplate.expire(redisKey, lotteryResultCacheExpiry, TimeUnit.SECONDS);
		
	}

	public LotterySummaryResp getLotterySummaryResultsCache(String lotteryType)
			throws JsonMappingException, JsonProcessingException {
		String redisKey = createLotterySummaryRedisKey(lotteryType);

		String resultJsonString = redisTemplate.opsForValue().get(redisKey);
		if (resultJsonString != null) {
			return objectMapper.readValue(resultJsonString, LotterySummaryResp.class);
		} else {
			return null;
		}
	}
	
	
	public void putNextJackpotEstimteAmountFromCache(String nextJackpotExtimteAmount ) {
		String redisKey=Constants.LOTTERY_TOTO_RESULTS_CACHE_PREFIX.concat(Constants.TOTO_ESTIMATE_JACKPOT_AMOUNT);
		 redisTemplate.opsForValue().set(redisKey, nextJackpotExtimteAmount);
		// redisTemplate.expire(redisKey, 10,TimeUnit.SECONDS);
	}

	public String getNextJackpotEstimteAmountFromCache(String key ) {
		String redisKey=Constants.LOTTERY_TOTO_RESULTS_CACHE_PREFIX.concat(key);
		 
		 return	 redisTemplate.opsForValue().get(redisKey);
				 
	}
	private String createLotterySummaryRedisKey(String lotteryType) {
		String redisKey = new String();
		if (lotteryType.equals(Constants.LOTTERY_SUMMARY_TYPE_TOTO)) {
			redisKey = Constants.LOTTERY_TOTO_RESULTS_CACHE_PREFIX.concat(Constants.LOTTERY_SUMMARY_PREFIX);
		} else if (lotteryType.equals(Constants.LOTTERY_SUMMARY_TYPE_4D)) {
			redisKey = Constants.LOTTERY_4D_RESULTS_CACHE_PREFIX.concat(Constants.LOTTERY_SUMMARY_PREFIX);
		} else if (lotteryType.equals(Constants.LOTTERY_SUMMARY_TYPE_SPSS)) {
			redisKey = Constants.LOTTERY_SWEEP_RESULTS_CACHE_PREFIX.concat(Constants.LOTTERY_SUMMARY_PREFIX);
		}

		return redisKey;
	}

	
}
