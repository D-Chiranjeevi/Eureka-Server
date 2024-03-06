package com.sppl.response.model;

import java.util.Map;

import lombok.Data;

@Data
public class TotoResult {

	private String drawNo;
	private String drawDate;
	
	
	private String isDrawCancelled;
	private String jackpotPrize;
	private String[] winningNumbers;
	private String additionalNumber;
	private long nextJackpotEstimateAmount;
	private String nextDrawNo;
	private String nextDrawDate;
	private String nextDrawTime;
	private String nextDrawType;
	private String specialDrawWin;
	private Map<String, WinningShare> winningShares;
	private WinningOutlets winningOutlets;

}
