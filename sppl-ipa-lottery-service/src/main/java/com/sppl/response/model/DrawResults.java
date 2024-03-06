package com.sppl.response.model;

import java.util.List;

import lombok.Data;

@Data
public class DrawResults {
	private String Type;
	private List<Pick> pick;
}
