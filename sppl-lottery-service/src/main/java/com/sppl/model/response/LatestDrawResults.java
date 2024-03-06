package com.sppl.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LatestDrawResults {

	private String id;
	private String drawDate;
	private String drawClosure;
	private String status;
	private String drawNo;
	
	@JsonIgnore
	private String jackpotPrize;
	@JsonIgnore
	private long nextJackpotEstimteAmount;
	private List<DrawResults> results;
}
