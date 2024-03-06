package com.sppl.service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sppl.response.model.LotterySummaryResp;

@Service
public interface RedisService {

	public LotterySummaryResp getLotterySummaryResultsCache(String lotteryType) throws JsonMappingException, JsonProcessingException;
	public void putNextJackpotEstimteAmountFromCache(String nextJackpotExtimteAmount );
	public String getNextJackpotEstimteAmountFromCache(String key);
	public void putLotterySummaryResultInCache(String gameRef, String jsonString);
}
