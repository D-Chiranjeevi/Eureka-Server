package com.sppl.model.inbound.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CurrentDrawDetails {
	String id;
	String drawDate;
	String drawClosure;
	String status;
	String drawNo;
	String jackpot;

}
