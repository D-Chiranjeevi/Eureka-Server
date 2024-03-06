package com.sppl.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sppl.model.inbound.response.CurrentDrawDetails;

import lombok.Data;

@Data
public class LotterySummaryResp {
	private CurrentDrawDetails currentDrawDetails;
	private String gameRef;
	@JsonInclude
	private LatestDrawResults latestDrawResults;
}
