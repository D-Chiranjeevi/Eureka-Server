package com.sppl.model.request;

import java.math.BigInteger;
import java.util.List;

import com.sppl.model.response.Pick;

import lombok.Data;
@Data
public class BetSelection {
	String betTypeRef;
	String betTypeName;
	boolean autoPick;
	BigInteger board;
	String totalboardValue;
	List<Pick> picks;
	String betType;
	List<Stakes> stakes;
	String fraction;
}
