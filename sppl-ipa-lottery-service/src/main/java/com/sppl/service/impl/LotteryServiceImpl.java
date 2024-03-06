package com.sppl.service.impl;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sppl.helper.LotteryHelper;
import com.sppl.response.model.LotterySummaryResp;
import com.sppl.service.LotteryService;
import com.sppl.service.RedisService;
@Service
public class LotteryServiceImpl implements LotteryService {
   @Autowired
   private RedisService redisService;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private LotteryHelper lotteryHelper;
	@Override
	public LotterySummaryResp getLotterySummary(String gameRef, String channel) throws ParseException, JsonMappingException, JsonProcessingException {
		LotterySummaryResp lotterySummary = redisService.getLotterySummaryResultsCache(gameRef);
		if (lotterySummary != null) {
			return lotterySummary;
		}

//		String path = null;
//		if (gameRef.equals("toto")) {
//			path = pathResolver.getDigibcLotterySummaryTotoPath();
//		} else if (gameRef.equals("4d")) {
//			path = pathResolver.getDigibcLotterySummary4dPath();
//		} else if (gameRef.equals("spss")) {
//			path = pathResolver.getDigibcLotterySummarySpssPath();
//		}
		LotterySummaryResp lotterySummaryResp=new LotterySummaryResp();
		
		//We need to call current draw details from digibc
		
		lotterySummaryResp.setLatestDrawResults(lotteryHelper.getLatestDrawResults(gameRef));
		lotterySummaryResp.setGameRef(gameRef);
		String resultsJsonString = objectMapper.writeValueAsString(lotterySummaryResp);
		redisService.putLotterySummaryResultInCache(gameRef, resultsJsonString);

		return lotterySummaryResp;
	}
	}


