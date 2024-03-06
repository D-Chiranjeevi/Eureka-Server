package com.sppl.model.request;

import lombok.Data;

@Data
public class LotteryBets {

	private String betTag;
	private String gameRef;
	//private String drawRef;
	private LotteryDrawDetails drawDetail;
	private BetSelection betSelection;
	private String id;
	private String subscriptionRef;
	private String receipt;
	private String gameCategory;
	private String gameSubCategory;
	private String totalStake;
}
