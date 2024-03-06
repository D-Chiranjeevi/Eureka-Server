package com.sppl.helper;

import java.net.URI;
import java.text.ParseException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.sppl.constants.Constants;
import com.sppl.response.model.DrawResults;
import com.sppl.response.model.FourDResults;
import com.sppl.response.model.LatestDrawResults;
import com.sppl.response.model.Pick;
import com.sppl.response.model.SpssResults;
import com.sppl.response.model.TotoResults;
import com.sppl.service.RedisService;

@Component
public class LotteryHelper {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private RedisService redisService;
	
		public LatestDrawResults getLatestDrawResults(String gameRef) throws ParseException {
			Map<String, String> pathVariables = null;
			pathVariables = new HashMap<>();
			pathVariables.put("type", gameRef);

			URI uri = UriComponentsBuilder.fromUriString(Constants.ACCOUNT_SERVICE_BASE_URL + "/account/results/{type}")
					.queryParam(gameRef).build(pathVariables);
			LatestDrawResults setLatestDrawResults = new LatestDrawResults();
			if (gameRef.equals("toto")) {

				 TotoResults totoResults = restTemplate.getForObject(uri, TotoResults.class);
				if ( totoResults != null) {
					boolean isDrawCancelled = totoResults.getResults().getIsDrawCancelled().equalsIgnoreCase("Y");
					int retryCount = 0;

					if (isDrawCancelled) {
						long nextJackPotEstimateAmount = totoResults.getResults().getNextJackpotEstimateAmount();
						redisService.putNextJackpotEstimteAmountFromCache(String.valueOf(nextJackPotEstimateAmount));
						int drawNo = Integer.parseInt(totoResults.getResults().getDrawNo());
						do {
							pathVariables.put("type", gameRef);
							
							drawNo--;
							uri = UriComponentsBuilder
									.fromUriString(Constants.ACCOUNT_SERVICE_BASE_URL + "/account/results/{type}")
									.queryParam("drawNo", drawNo).build(pathVariables);

							totoResults = restTemplate.getForObject(uri, TotoResults.class);
							
							isDrawCancelled = totoResults.getResults().getIsDrawCancelled().equalsIgnoreCase("Y");
							
							
							totoResults.getResults().setJackpotPrize(redisService.getNextJackpotEstimteAmountFromCache(Constants.TOTO_ESTIMATE_JACKPOT_AMOUNT));
							retryCount++;
						} while (isDrawCancelled && retryCount <= 2);
					}
					setLatestDrawResults
							.setDrawDate(convertTimeZone(totoResults.getResults().getDrawDate() + "T00:00:00Z"));
					setLatestDrawResults.setDrawNo(totoResults.getResults().getDrawNo());
					setLatestDrawResults.setJackpotPrize(totoResults.getResults().getJackpotPrize());
				//	setLatestDrawResults.setNextJackpotEstimteAmount(totoResults.getResults().getNextJackpotEstimateAmount());
					List<DrawResults> drawResultsList = new ArrayList<>();
					if (totoResults.getResults().getWinningNumbers() != null) {
						int typeCount = 0;
						// Set Winning numbers
						DrawResults drawResult = new DrawResults();
						drawResult.setType(String.valueOf(++typeCount));
						int position = 0;
						List<Pick> picklist = new ArrayList<>();
						for (String winNo : totoResults.getResults().getWinningNumbers()) {
							Pick pickdetails = new Pick();
							pickdetails.setPosition(String.valueOf(++position));
							pickdetails.setPick(String.valueOf(winNo));
							picklist.add(pickdetails);
						}
						drawResult.setPick(setPicksResult(picklist));
						drawResultsList.add(drawResult);
						// Set Additional Number
						drawResult = new DrawResults();
						drawResult.setType("ball_no");
						position = 0;
						picklist = new ArrayList<>();
						Pick pickdetails = new Pick();
						pickdetails.setPosition(String.valueOf(++position));
						pickdetails.setPick((String.valueOf(totoResults.getResults().getAdditionalNumber())));
						picklist.add(pickdetails);
						drawResult.setPick(setPicksResult(picklist));
						drawResultsList.add(drawResult);
					}

					setLatestDrawResults.setResults(drawResultsList);
				}
			} else if (gameRef.equals("4d")) {
				FourDResults fourDResults = restTemplate.getForObject(uri, FourDResults.class);
				setLatestDrawResults.setDrawDate(convertTimeZone(fourDResults.getResults().getDrawDate() + "T00:00:00Z"));
				setLatestDrawResults.setDrawNo(fourDResults.getResults().getDrawNo());
				List<DrawResults> drawResultsList = new ArrayList<>();
				int typeCount = 0;
				char[] chArray = null;
				List<Pick> picklist = new ArrayList<>();

				// Set First Prize
				DrawResults drawResult = new DrawResults();
				drawResult.setType(String.valueOf(++typeCount));
				int position = 0;
				String firstPrize = fourDResults.getResults().getFirstPrize();
				if (firstPrize != null) {
					chArray = firstPrize.trim().toCharArray();
					for (char digit : chArray) {
						Pick pickdetails = new Pick();
						pickdetails.setPosition(String.valueOf(++position));
						pickdetails.setPick(String.valueOf(digit));
						picklist.add(pickdetails);
					}
					drawResult.setPick(setPicksResult(picklist));
					drawResultsList.add(drawResult);
				}

				// Set Second Prize
				picklist = new ArrayList<>();
				drawResult = new DrawResults();
				drawResult.setType(String.valueOf(++typeCount));
				position = 0;
				String secondPrize = fourDResults.getResults().getSecondPrize();
				if (secondPrize != null) {
					chArray = secondPrize.trim().toCharArray();
					for (char digit : chArray) {
						Pick pickdetails = new Pick();
						pickdetails.setPosition(String.valueOf(++position));
						pickdetails.setPick(String.valueOf(digit));
						picklist.add(pickdetails);
					}
					drawResult.setPick(setPicksResult(picklist));
					drawResultsList.add(drawResult);
				}

				// Set Third Prize
				picklist = new ArrayList<>();
				drawResult = new DrawResults();
				drawResult.setType(String.valueOf(++typeCount));
				position = 0;
				String thirdPrize = fourDResults.getResults().getThirdPrize();
				if (thirdPrize != null) {
					chArray = thirdPrize.trim().toCharArray();
					for (char digit : chArray) {
						Pick pickdetails = new Pick();
						pickdetails.setPosition(String.valueOf(++position));
						pickdetails.setPick(String.valueOf(digit));
						picklist.add(pickdetails);
					}
					drawResult.setPick(setPicksResult(picklist));
					drawResultsList.add(drawResult);
				}
				setLatestDrawResults.setResults(drawResultsList);
			} else{
				SpssResults spssResults = restTemplate.getForObject(uri, SpssResults.class);

				setLatestDrawResults.setDrawDate(convertTimeZone(spssResults.getResults().getDrawDate() + "T00:00:00Z"));

				setLatestDrawResults.setDrawNo(spssResults.getResults().getDrawNo());
				List<DrawResults> drawResultsList = new ArrayList<>();
				List<String> listOfPrize = new ArrayList<>();
				listOfPrize.add(spssResults.getResults().getFirstPrize());
				listOfPrize.add(spssResults.getResults().getSecondPrize());
				listOfPrize.add(spssResults.getResults().getThirdPrize());
				int typeCount = 0;
				List<Pick> picklist = null;
				// Set Sweep Picklist
				int position;
				for (String sweepPrize : listOfPrize) {
					picklist = new ArrayList<>();
					DrawResults drawResult = new DrawResults();
					drawResult.setType(String.valueOf(++typeCount));
					position = 0;
					Pick pickdetails = new Pick();
					pickdetails.setPosition(String.valueOf(++position));
					pickdetails.setPick(sweepPrize);
					picklist.add(pickdetails);
					drawResult.setPick(setPicksResult(picklist));
					drawResultsList.add(drawResult);
				}
				setLatestDrawResults.setResults(drawResultsList);
			}
			return setLatestDrawResults;
		
		}

		private String convertTimeZone(String date) {
				String fd = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6);
				Instant instant = Instant.parse(fd);
				ZonedDateTime z = instant.atZone(ZoneId.systemDefault());
				DateTimeFormatter fmt = DateTimeFormatter.ofPattern(Constants.DEFAULT_DATE_FORMAT, Locale.ENGLISH);
				return fmt.format(z);

			}
		
		private List<Pick> setPicksResult(List<Pick> picks) {
			List<Pick> picklist = new ArrayList<>();

			for (Pick pick : picks) {
				Pick pickdetails = new Pick();
				pickdetails.setPosition(pick.getPosition());
				pickdetails.setPick(pick.getPick());
				picklist.add(pickdetails);
			}
			return picklist;
		}
	}

