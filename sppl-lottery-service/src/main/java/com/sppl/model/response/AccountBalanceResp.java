package com.sppl.model.response;

import lombok.Data;

@Data
public class AccountBalanceResp {
    private String availableFunds;
    private String balance;
    private String bonusBarPercentage;
    private String currency;
    private String heldFunds;
    private String lockedInFunds;
    private String pendingWithdrawals;
    private String withdrawableFunds;
}
