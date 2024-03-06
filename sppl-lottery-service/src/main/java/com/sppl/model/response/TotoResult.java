package com.sppl.model.response;

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

	public String getDrawDate() {
		return drawDate;
	}
	public void setDrawDate(String drawDate) {
		this.drawDate = drawDate.replaceAll("(\\d{4})(\\d{2})(\\d{2})", "$1-$2-$3");
	}
}
