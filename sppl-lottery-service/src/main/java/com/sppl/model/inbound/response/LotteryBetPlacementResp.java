package com.sppl.model.inbound.response;

import java.util.List;

import com.sppl.model.request.LotteryBets;
import com.sppl.model.response.AccountBalanceResp;
import com.sppl.model.response.AccountLimitResp;

import lombok.Data;

@Data
public class LotteryBetPlacementResp {
	private AccountBalanceResp accountBalance;
	private AccountLimitResp accountLimit;
	private List<LotteryBets> bets;
	private String betslipReceipt;
	private String cartId;
	private String date;
	private boolean isError;
	private String totalStake;
	private String transactionFee;

}
