package com.sppl.service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sppl.model.response.LotterySummaryResp;

@Service
public interface RedisService {

	public void putLotterySummaryResultInCache(String gameRef, String resultsJsonString);


	public LotterySummaryResp getLotterySummaryResultsCache(String lotteryType) throws JsonMappingException, JsonProcessingException;
	public void putNextJackpotEstimteAmountFromCache(String nextJackpotExtimteAmount );
	public String getNextJackpotEstimteAmountFromCache(String key);
}
