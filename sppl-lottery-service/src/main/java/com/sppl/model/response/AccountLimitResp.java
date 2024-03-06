package com.sppl.model.response;

import lombok.Data;

@Data
public class AccountLimitResp {
	private String limitAllowIncrement;
	private String limitAmount;
	private String limitAmountRemaining;
	private String limitAmountUsed;
	private String limitCreationDate;
	private String limitId;
	private String limitKind;
	private String limitPeriod;
	private String limitPeriodFromDate;
	private String limitPeriodToDate;
	private String token;
}
