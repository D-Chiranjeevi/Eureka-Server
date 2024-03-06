package com.sppl.model.inbound.response;

import java.math.BigInteger;

import lombok.Data;

@Data
public class LotteryBetTypeResp {
	private String id;
	private boolean isFixedPrice;
	private BigInteger lines;
	private String maximumStake;
	private String minimumStake;
	private String name;
}
