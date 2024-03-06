package com.sppl.controller;

import java.io.IOException;
import java.text.ParseException;

import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.catalina.authenticator.Constants;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sppl.exceptions.AccountFunctionalException;
import com.sppl.exceptions.LotteryFunctionalException;
import com.sppl.exceptions.TechnicalException;
import com.sppl.helper.LotteryHelper;
import com.sppl.response.model.LotterySummaryResp;
import com.sppl.service.LotteryService;

@RestController
@RequestMapping("/ipa/lottery")
public class LotteryController {
	@Autowired
	private LotteryService lotteryService;
	@Autowired
	private LotteryHelper lotteryHelper;

	@GetMapping("/{lotteryType}/summary")
	public LotterySummaryResp lotterySummary(@PathVariable("lotteryType") String lotteryType,
			@RequestParam("channel") String channel)
			throws TechnicalException, IOException,  LotteryFunctionalException,
			DatatypeConfigurationException, ParseException  {
		

		return lotteryService.getLotterySummary(lotteryType, channel);
	}

}
