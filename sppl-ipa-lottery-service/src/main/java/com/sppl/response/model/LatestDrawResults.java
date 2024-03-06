package com.sppl.response.model;

import java.util.List;

import lombok.Data;
@Data
public class LatestDrawResults {

	private String id;
	private String drawDate;
	private String drawClosure;
	private String status;
	private String drawNo;
	private String jackpotPrize;
	private List<DrawResults> results;
}
