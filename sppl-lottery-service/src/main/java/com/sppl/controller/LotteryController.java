package com.sppl.controller;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.xml.datatype.DatatypeConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sppl.entity.LotteryDraw;
import com.sppl.exceptions.AccountFunctionalException;
import com.sppl.exceptions.LotteryFunctionalException;
import com.sppl.exceptions.TechnicalException;
import com.sppl.helper.LotteryHelper;
import com.sppl.model.inbound.response.AccountReg1;
import com.sppl.model.inbound.response.LotteryGamesResp;
import com.sppl.model.response.LotterySummaryResp;
import com.sppl.model.response.TotoResult;
import com.sppl.repository.helper.LotteryDrawInfo;
import com.sppl.service.LotteryService;
import com.sppl.util.Constants;

@RestController
@RequestMapping("/lottery")
public class LotteryController {
	@Autowired
	private LotteryHelper lotteryHelper;
	@Autowired
	private LotteryService lotteryService;
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	@Autowired
	private RestTemplate restTemplate;

	private Map<String, String> pathVariables = null;

	@PostMapping("/toto/save")
	public LotteryDraw saveDraw(@RequestBody LotteryDraw lotteryDraw) {
		return lotteryService.save(lotteryDraw);
	}

	@GetMapping("/get/{drawNo}")
	public LotteryDraw getById(@PathVariable("drawNo") String drawNo) {
		LotteryDraw drawDetails = null;
		drawDetails = (LotteryDraw) redisTemplate.opsForValue().get(drawNo);

		if (drawDetails == null) {

			drawDetails = lotteryService.getById(drawNo);

			redisTemplate.opsForValue().set(drawNo, drawDetails);

			return drawDetails;
		}
		return drawDetails;
	}

	@GetMapping(value = "find/{regUUID}/{drawNo}")
	public List<LotteryDrawInfo> findByDrawNoAndStatus(@PathVariable("regUUID") String regUUID,
			@PathVariable("drawNo") String drawNo, @RequestParam String status) {

		String resourceUrl = Constants.ACCOUNT_SERVICE_BASE_URL + Constants.GET_ACREG1_URL;
		pathVariables = new HashMap<>();
		pathVariables.put("regUUID", regUUID);

		URI uri = UriComponentsBuilder.fromUriString(resourceUrl).build(pathVariables);

		AccountReg1 reg1 = restTemplate.getForObject(uri, AccountReg1.class);
		System.out.println(" Data reterived by calling another microservice   :: " + reg1);
		return lotteryService.findByDrawNoAndStatus(drawNo, status);
	}

	@GetMapping("/games/{type}")
	public LotteryGamesResp lotteryGames(@PathVariable("type") String lotteryType, @RequestParam int drawCount)
			throws AccountFunctionalException, TechnicalException, LotteryFunctionalException,
			DatatypeConfigurationException, JsonProcessingException, UnsupportedEncodingException, ParseException {
		// String channel = lotteryHelper.getChannelId(Constants.AUDIT_DEVICE_OS_KEY);
		String channel = "M";
		String lang = "eng";
		return lotteryService.getLotteryGames(lotteryType, drawCount, lang, channel);
	}

	@GetMapping("/{lotteryType}/summary")
	@Validated
	public LotterySummaryResp lotterySummary(@PathVariable(value = "lotteryType", required = false) String lotteryType)
			throws JsonProcessingException, RestClientException, DatatypeConfigurationException, TechnicalException,
			LotteryFunctionalException, ParseException {
		return lotteryService.getLotterySummary(lotteryType);
	}
}
