package com.sppl.response.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
@Data
public class LotterySummaryResp {
	private CurrentDrawDetails currentDrawDetails;
	private String gameRef;
	@JsonInclude
	private LatestDrawResults latestDrawResults;
}
