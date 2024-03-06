package com.sppl.helper;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sppl.model.inbound.response.CurrentDrawDetails;
import com.sppl.model.inbound.response.LotteryBetTypeResp;
import com.sppl.model.inbound.response.LotteryGamesResp;
import com.sppl.model.response.Draw;
import com.sppl.model.response.DrawResults;
import com.sppl.model.response.FourDResults;
import com.sppl.model.response.GetDrawsResponse;
import com.sppl.model.response.GetGamesResponse;
import com.sppl.model.response.LatestDrawResults;
import com.sppl.model.response.LotteryBetType;
import com.sppl.model.response.LotteryGame;
import com.sppl.model.response.LotteryGame.BetTypes;
import com.sppl.model.response.LotteryGame.DrawDays;
import com.sppl.model.response.Pick;
import com.sppl.model.response.SpssResults;
import com.sppl.model.response.TotoResults;
import com.sppl.repository.LotteryDrawInfoRepository;
import com.sppl.repository.helper.LotteryDrawInfo;
import com.sppl.service.RedisService;
import com.sppl.util.Constants;

@Component
public class LotteryHelper {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private LotteryDrawInfoRepository lotteryRepository;

	@Autowired
	private RedisService redisService;

	public String getChannelId(String deviceOs) {
		String channel = "I";
		if (!deviceOs.equals("web")) {
			channel = "M";
		}
		return channel;

	}

	public String getGameRef(String gameRef) {

		String retriveGameRef = null;
		if (gameRef.equals("4d")) {
			retriveGameRef = "SP4D";
		} else if (gameRef.equals("toto")) {
			retriveGameRef = "SPTOTO49";
		} else if (gameRef.equals("spss")) {
			retriveGameRef = "SPSS";
		}

		return retriveGameRef;
	}

	public LotteryGamesResp setLotteryGamesResp(GetGamesResponse.LotteryGames gamesList, String getGameRef, String lang)
			throws ParseException, UnsupportedEncodingException {
		LotteryGamesResp setLotteryGamesResp = new LotteryGamesResp();

		for (LotteryGame game : gamesList.getLotteryGame()) {

			if (game.getId().equals(getGameRef)) {

				setLotteryGamesResp.setGameId(game.getId());
//				setLotteryGamesResp.setGameName(translationDao
//						.getOpenBetTranslationFromCache(Constants.TRANSLATION_GROUP_LOTTERY, game.getText(), lang));
				setLotteryGamesResp.setGameName(null);
				setLotteryGamesResp.setDrawDays(getLotteryDays(game.getDrawDays(), lang));
				setLotteryGamesResp.setLotteryBetTypes(getLotteryBetTypes(game.getBetTypes(), lang));
			}
		}
		return setLotteryGamesResp;

	}

	private List<String> getLotteryDays(DrawDays days, String lang) throws UnsupportedEncodingException {

		List<String> drawDays = new ArrayList<>();

		for (String drawDay : days.getDrawDay()) {
			// drawDays.add(null);
//					translationDao.getOpenBetTranslationFromCache(Constants.TRANSLATION_GROUP_LOTTERY, drawDay, lang));
			drawDays.add(null);
		}
		return drawDays;
	}

	private List<LotteryBetTypeResp> getLotteryBetTypes(BetTypes betTypeList, String lang)
			throws UnsupportedEncodingException {

		List<LotteryBetTypeResp> lotteryBetTypeList = new ArrayList<>();

		for (LotteryBetType lotteryBetType : betTypeList.getLotteryBetType()) {
			LotteryBetTypeResp lotteryBetTypeResp = new LotteryBetTypeResp();

			lotteryBetTypeResp.setId(lotteryBetType.getId());
//			lotteryBetTypeResp.setName(translationDao.getOpenBetTranslationFromCache(
//					Constants.TRANSLATION_GROUP_LOTTERY, lotteryBetType.getText(), lang));
			lotteryBetTypeResp.setName(null);
			lotteryBetTypeResp.setLines(lotteryBetType.getLines());
			lotteryBetTypeResp.setFixedPrice(lotteryBetType.isFixedPrice());
			lotteryBetTypeResp.setMinimumStake(lotteryBetType.getMinimumStake());
			lotteryBetTypeResp.setMaximumStake(lotteryBetType.getMaximumStake());

			lotteryBetTypeList.add(lotteryBetTypeResp);
		}
		return lotteryBetTypeList;
	}

	public List<CurrentDrawDetails> getLatestGamesDrawList(GetDrawsResponse.Draws drawslist, int drawCount, String lang,
			String gameRef) throws ParseException, UnsupportedEncodingException {

		List<CurrentDrawDetails> setDrawList = new ArrayList<>();
		List<String> drawDateList = getLatestDrawsSet(drawslist, drawCount);

		for (Draw drawResp : drawslist.getDraw()) {

			if (drawDateList.contains(drawResp.getDrawClosure())) {
				CurrentDrawDetails setDraws = new CurrentDrawDetails();

				setDraws.setId(drawResp.getId());
				setDraws.setDrawDate(convertTimeZone(drawResp.getDrawDate()));
				setDraws.setDrawClosure(convertTimeZone(drawResp.getDrawClosure()));
//				setDraws.setStatus(translationDao.getOpenBetTranslationFromCache(Constants.TRANSLATION_GROUP_BETSTATUS,
//						drawResp.getStatus(),
//						lang));

				setDraws.setStatus(null);
				if (drawResp.getDrawNo() != null) {
					setDraws.setDrawNo(drawResp.getDrawNo());
				}
				if (gameRef.equals("toto")) {
					LotteryDrawInfo lotteryDrawInfo = lotteryRepository
							.findByDrawNoAndStatus(drawResp.getDrawNo(), Constants.ESTIMATED_RESULT).get(0);
					if (lotteryDrawInfo.getPrizeAmount() != null)
						setDraws.setJackpot(lotteryDrawInfo.getPrizeAmount().toString());
				} else {
					if (drawResp.getJackpot() != null) {
						setDraws.setJackpot(drawResp.getJackpot());
					}
				}

				setDrawList.add(setDraws);
			}
		}

		return setDrawList;
	}

	private List<String> getLatestDrawsSet(GetDrawsResponse.Draws drawslists, int drawCount) throws ParseException {

		String currentDate = getCurrentSystemDate();
		List<String> drawNoLists = new ArrayList<>();
		List<String> drawList = new ArrayList<>();

		Date date1 = new SimpleDateFormat(Constants.STANDARD_DATE_FORMAT).parse(currentDate);

		for (Draw drawlist : drawslists.getDraw()) {

			String format = drawlist.getDrawClosure();
			Date date2 = new SimpleDateFormat(Constants.STANDARD_DATE_FORMAT).parse(format);

			if (date1.compareTo(date2) < 0) {
				drawNoLists.add(drawlist.getDrawClosure());
			}
		}

		if (drawNoLists.size() > 0) {

			if (drawNoLists.size() > drawCount) {
				Collections.sort(drawNoLists);
				for (int i = 0; i < drawCount; i++) {
					drawList.add(drawNoLists.get(i));
				}
			} else {
				drawList = drawNoLists;
			}
		}
		return drawList;

	}

	public String getCurrentSystemDate() {
		LocalDateTime localDateTime = LocalDateTime.now();
		LocalDateTime gmtDateAndTime = localDateTime.atZone(ZoneId.of("GMT")).toLocalDateTime();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(Constants.STANDARD_DATE_FORMAT);
		return dtf.format(gmtDateAndTime);
	}

	private String convertTimeZone(String date) {

	//	String fd = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6);
		Instant instant = Instant.parse(date);
		ZonedDateTime z = instant.atZone(ZoneId.systemDefault());
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern(Constants.DEFAULT_DATE_FORMAT, Locale.ENGLISH);
		return fmt.format(z);

	}

	public String getLotteryGameRef(String gameRef) {
		return getGameRefFromResp(gameRef);
	}

	private String getGameRefFromResp(String gameRef) {

		String retriveGameRef = null;
		if (gameRef.equals("SP4D")) {
			retriveGameRef = "4d";
		} else if (gameRef.equals("SPTOTO49")) {
			retriveGameRef = "toto";
		} else if (gameRef.equals("SPSS")) {
			retriveGameRef = "spss";
		}

		return retriveGameRef;
	}

	public CurrentDrawDetails getCurrentDrawDetails(GetDrawsResponse.Draws drawslist, String gameRef)
			throws ParseException {
		CurrentDrawDetails setcurrentDrawDetails = new CurrentDrawDetails();
		String currentDrawNo = getCurrentDrawNo(drawslist);

		for (Draw drawResp : drawslist.getDraw()) {
			if (currentDrawNo != null && currentDrawNo.equals(drawResp.getDrawDate())) {
				setcurrentDrawDetails.setId(drawResp.getId());
				setcurrentDrawDetails.setDrawDate(convertTimeZone(drawResp.getDrawDate()));
				setcurrentDrawDetails.setDrawClosure(convertTimeZone(drawResp.getDrawClosure()));
				setcurrentDrawDetails.setStatus(drawResp.getStatus());
				if (drawResp.getDrawNo() != null) {
					setcurrentDrawDetails.setDrawNo(drawResp.getDrawNo());
				}

				if (gameRef.equals("toto")) {
					LotteryDrawInfo lotteryDrawInfo = lotteryRepository
							.findByDrawNoAndStatus(drawResp.getDrawNo(), Constants.ESTIMATED_RESULT).get(0);
					if (lotteryDrawInfo.getPrizeAmount() != null)
						setcurrentDrawDetails.setJackpot(lotteryDrawInfo.getPrizeAmount().toString());
				} else {
					setcurrentDrawDetails.setJackpot(drawResp.getJackpot());
				}
			}
		}
		return setcurrentDrawDetails;
	}

	private String getCurrentDrawNo(GetDrawsResponse.Draws drawslists) throws ParseException {
		String currentDate = getCurrentSystemDate();
		List<String> drawNoLists = new ArrayList<>();
		Date date1 = new SimpleDateFormat(Constants.STANDARD_DATE_FORMAT).parse(currentDate);

		for (Draw drawlist : drawslists.getDraw()) {

			String format = drawlist.getDrawDate();
			Date date2 = new SimpleDateFormat(Constants.STANDARD_DATE_FORMAT).parse(format);

			if (date1.compareTo(date2) < 0) {
				drawNoLists.add(drawlist.getDrawDate());
			}

		}
		String currentDrawNo = null;
		if (drawNoLists.size() > 0) {
			Collections.sort(drawNoLists);
			currentDrawNo = drawNoLists.get(0);
		}
		return currentDrawNo;
	}

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
		} else {
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
