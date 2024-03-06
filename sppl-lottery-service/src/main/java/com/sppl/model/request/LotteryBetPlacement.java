package com.sppl.model.request;

import java.util.List;

import lombok.Data;

@Data
public class LotteryBetPlacement {
	private String remoteUniqueId;
	private List<LotteryBets> bets;
}
