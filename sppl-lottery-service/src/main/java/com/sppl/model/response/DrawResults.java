package com.sppl.model.response;

import java.util.List;

import lombok.Data;
@Data
public class DrawResults {

	private String type;
	private List<Pick> pick;
}
