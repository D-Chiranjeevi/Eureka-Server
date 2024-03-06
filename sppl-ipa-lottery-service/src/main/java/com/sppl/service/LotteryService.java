package com.sppl.service;

import java.text.ParseException;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sppl.exceptions.LotteryFunctionalException;
import com.sppl.response.model.LotterySummaryResp;

@Service
public interface LotteryService {

	LotterySummaryResp getLotterySummary(String lotteryType, String channel) throws ParseException, JsonMappingException, JsonProcessingException, LotteryFunctionalException;

}
