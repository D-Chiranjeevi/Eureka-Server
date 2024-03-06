package com.sppl.service;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;

import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sppl.entity.LotteryDraw;
import com.sppl.exceptions.AccountFunctionalException;
import com.sppl.exceptions.LotteryFunctionalException;
import com.sppl.exceptions.TechnicalException;
import com.sppl.model.inbound.response.LotteryGamesResp;
import com.sppl.model.response.GetDrawsResponse;
import com.sppl.model.response.GetGamesResponse;
import com.sppl.model.response.LotterySummaryResp;
import com.sppl.repository.helper.LotteryDrawInfo;

public interface LotteryService {
	public LotteryGamesResp getLotteryGames(String gameRef, int drawCount, String lang, String channel)
			throws AccountFunctionalException, TechnicalException, LotteryFunctionalException,
			DatatypeConfigurationException, JsonProcessingException, UnsupportedEncodingException, ParseException;

	public GetGamesResponse getLotteryGamesResponse(String channel) throws TechnicalException,
			LotteryFunctionalException, DatatypeConfigurationException, JsonProcessingException;

	public GetDrawsResponse getDrawsResponse(String getGameRef, String channel) throws TechnicalException,
			LotteryFunctionalException, DatatypeConfigurationException, JsonProcessingException;

	public LotterySummaryResp getLotterySummary(String gameRef) throws JsonProcessingException, RestClientException,
			DatatypeConfigurationException, TechnicalException, LotteryFunctionalException, ParseException;

	public LotteryDraw save(LotteryDraw lottryDraw);

	public LotteryDraw getById(String drawNo);

	public List<LotteryDrawInfo> findByDrawNoAndStatus(String drawNo, String status);

}
