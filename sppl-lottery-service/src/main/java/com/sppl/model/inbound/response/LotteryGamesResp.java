package com.sppl.model.inbound.response;

import java.util.List;

import lombok.Data;
@Data
public class LotteryGamesResp {
	 private List<String> drawDays;
	    private List<CurrentDrawDetails> drawList;
	    private String gameId;
	    private String gameName;
	    private List<LotteryBetTypeResp> lotteryBetTypes;
}
