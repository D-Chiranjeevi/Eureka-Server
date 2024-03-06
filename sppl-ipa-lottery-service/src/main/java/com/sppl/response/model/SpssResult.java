package com.sppl.response.model;

import lombok.Data;

@Data
public class SpssResult {

	private String drawNo;
	private String drawDate;
	private String isDrawCancelled;
	private String firstPrize;
	private String secondPrize;
	private String thirdPrize;
	private String[] jackpotPrize;
	private String[] luckyPrize;
	private String[] giftPrize;
	private String[] consolationPrize;
	private String[] participationPrize;
	private String[] delightPrize;

}
