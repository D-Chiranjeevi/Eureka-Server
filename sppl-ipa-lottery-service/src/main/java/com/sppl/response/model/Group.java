package com.sppl.response.model;

import lombok.Data;

@Data
public class Group {
	private String retailNumber;
	private long betType;
	private long syndicateType;
	private long totalParts;
	private long numberOfParts;
	private long partsID;
	private String quickPick;
	private String syndicateID;
	private String syndicateName;
	private String syndicateSetID;
}
