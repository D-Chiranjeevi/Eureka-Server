package com.sppl.response.model;

import java.util.List;

import lombok.Data;

@Data
public class FourDResult {

	private String drawNo;
	private String drawDate;
	private String isDrawCancelled;
	private String firstPrize;
	private String secondPrize;
	private String thirdPrize;
	private List<String> starterPrize;
	private List<String> consolationPrize;

}
