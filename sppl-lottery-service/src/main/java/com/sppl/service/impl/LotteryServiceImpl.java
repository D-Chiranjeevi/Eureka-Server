package com.sppl.service.impl;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.sppl.connectors.OSBHttpConnector;
import com.sppl.entity.LotteryDraw;
import com.sppl.exceptions.AccountFunctionalException;
import com.sppl.exceptions.LotteryFunctionalException;
import com.sppl.exceptions.TechnicalException;
import com.sppl.helper.LotteryHelper;
import com.sppl.helper.PathResolver;
import com.sppl.model.inbound.response.CurrentDrawDetails;
import com.sppl.model.inbound.response.LotteryGamesResp;
import com.sppl.model.response.GetDraws;
import com.sppl.model.response.GetDrawsResponse;
import com.sppl.model.response.GetGames;
import com.sppl.model.response.GetGamesResponse;
import com.sppl.model.response.LotterySummaryResp;
import com.sppl.model.response.ResponseInfo;
import com.sppl.repository.LotteryDrawInfoRepository;
import com.sppl.repository.helper.LotteryDrawInfo;
import com.sppl.service.LotteryService;
import com.sppl.service.RedisService;
import com.sppl.transformer.LotteryTransformer;
import com.sppl.util.Constants;

@Service
public class LotteryServiceImpl implements LotteryService {
	@Autowired
	private LotteryHelper lotteryHelper;
	@Autowired
	private OSBHttpConnector osbHttpConnector;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private XmlMapper xmlMapper;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private PathResolver pathResolver;
	@Autowired
	private LotteryTransformer lotteryTransformer;
	@Autowired
	private LotteryDrawInfoRepository lotteryRepository;
	@Autowired
	private RedisService redisService;

	@Override
	public LotteryGamesResp getLotteryGames(String gameRef, int drawCount, String lang, String channel)
			throws AccountFunctionalException, TechnicalException, LotteryFunctionalException,
			DatatypeConfigurationException, JsonProcessingException, UnsupportedEncodingException, ParseException {

		String getGameRef = null;
		if (gameRef.equals("toto")) {
			getGameRef = "SPTOTO49";
		}
		GetGamesResponse respGamesInfo = getLotteryGamesResponse(channel);
		GetDrawsResponse respDrawInfo = getDrawsResponse(getGameRef, channel);
		LotteryGamesResp lotteryGamesResp = lotteryHelper.setLotteryGamesResp(respGamesInfo.getLotteryGames(),
				getGameRef, lang);

		lotteryGamesResp
				.setDrawList(lotteryHelper.getLatestGamesDrawList(respDrawInfo.getDraws(), drawCount, lang, gameRef));

		return lotteryGamesResp;
	}

	@Override
	public GetGamesResponse getLotteryGamesResponse(String channel) throws TechnicalException,
			LotteryFunctionalException, DatatypeConfigurationException, JsonProcessingException {

		GetGames setGetGamesReq = lotteryTransformer.convertToLotteryGetGames(channel);
		String setGetGamesReqXml = xmlMapper.writeValueAsString(setGetGamesReq);

		ResponseEntity<GetGamesResponse> getGamesResponseResp = osbHttpConnector.sendPostRequest(restTemplate,
				setGetGamesReqXml, pathResolver.getOsbLotteryPath(), GetGamesResponse.class);
		ResponseInfo respInfo = getGamesResponseResp.getBody().getResponseInfo();
		checkForResponseFailedStatus(respInfo);

		return getGamesResponseResp.getBody();
	}

	private void checkForResponseFailedStatus(ResponseInfo response) throws LotteryFunctionalException {
		if (!response.getStatus().equals("200")) {
			com.sppl.model.response.Error error = response.getErrors().getError().get(0);
			if (!error.getCode().equals(Constants.LOTTERY_BET_SOLD_OUT_ERROR_CODE)) {
				throw new LotteryFunctionalException(0, error.getCode(), error.getText());
			}
		}
	}

	@Override
	public GetDrawsResponse getDrawsResponse(String getGameRef, String channel) throws TechnicalException,
			LotteryFunctionalException, DatatypeConfigurationException, JsonProcessingException {
		GetDraws setGetDrawsReq = lotteryTransformer.convertToLotteryGetDrawsDetail(getGameRef, channel);
		String setGetDrawsReqXml = xmlMapper.writeValueAsString(setGetDrawsReq);

		ResponseEntity<GetDrawsResponse> getDrawsResponseResp = osbHttpConnector.sendPostRequest(restTemplate,
				setGetDrawsReqXml, pathResolver.getOsbLotteryPath(), GetDrawsResponse.class);
		ResponseInfo respInfo = getDrawsResponseResp.getBody().getResponseInfo();
		checkForResponseFailedStatus(respInfo);

		return getDrawsResponseResp.getBody();
	}

	@Override
	public LotterySummaryResp getLotterySummary(String gameRef) throws JsonProcessingException, RestClientException,
			DatatypeConfigurationException, TechnicalException, LotteryFunctionalException, ParseException {
		LotterySummaryResp lotterySummary = redisService.getLotterySummaryResultsCache(gameRef);
		if (lotterySummary != null) {
			return lotterySummary;
		}
	//	String getGameRef = lotteryHelper.getGameRef(gameRef);
		//GetDraws setGetDrawsReq = lotteryTransformer.convertToLotteryGetDrawsDetail(getGameRef, null);
		//String setGetDrawsReqXml = xmlMapper.writeValueAsString(setGetDrawsReq);

		/*
		 * ResponseEntity<GetDrawsResponse> getDrawsResponseResp = null;
		 * getDrawsResponseResp = osbHttpConnector.sendPostRequest(null,
		 * setGetDrawsReqXml, pathResolver.getOsbLotteryPath(), GetDrawsResponse.class);
		 * ResponseInfo respInfo = getDrawsResponseResp.getBody().getResponseInfo();
		 * checkForResponseFailedStatus(respInfo);
		 */
		LotterySummaryResp lotterySummaryResp = new LotterySummaryResp();
		CurrentDrawDetails currentDrawDetails = new CurrentDrawDetails();
		currentDrawDetails.setId("830216");
		currentDrawDetails.setDrawDate("2024-02-08 18:30:00");
		currentDrawDetails.setDrawClosure("2024-02-08 18:00:00");
		currentDrawDetails.setStatus("A");
		currentDrawDetails.setDrawNo("3946");
//		if (gameRef.equalsIgnoreCase("toto")) {
//			currentDrawDetails.setJackpot("2500000.00");
//		}
		
		
		lotterySummaryResp.setLatestDrawResults(lotteryHelper.getLatestDrawResults(gameRef));
		if (gameRef.equalsIgnoreCase("toto")) {
			currentDrawDetails.setJackpot(lotterySummaryResp.getLatestDrawResults().getJackpotPrize());
		}
		lotterySummaryResp.setCurrentDrawDetails(currentDrawDetails);
		lotterySummaryResp.setGameRef(gameRef);
		String resultsJsonString = objectMapper.writeValueAsString(lotterySummaryResp);
		redisService.putLotterySummaryResultInCache(gameRef, resultsJsonString);
		return lotterySummaryResp;
	}

	public String getGameRef(String gameRef) {
		String retrievedGameRef = null;
		switch (gameRef.toLowerCase()) {
		case "4d":
			retrievedGameRef = "SP4D";
			break;
		case "toto":
			retrievedGameRef = "SPTOTO49";
			break;
		case "spss":
			retrievedGameRef = "SPSS";
			break;
		}
		return retrievedGameRef;
	}

	@Override
	public LotteryDraw save(LotteryDraw lottryDraw) {
		return lotteryRepository.save(lottryDraw);
	}

	@Override
	public LotteryDraw getById(String drawNo) {
		return lotteryRepository.findById(drawNo).get();
	}

	@Override
	public List<LotteryDrawInfo> findByDrawNoAndStatus(String drawNo, String status) {
		return lotteryRepository.findByDrawNoAndStatus(drawNo, status);
	}

}
