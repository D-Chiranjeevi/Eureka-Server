package com.sppl.helper;

import org.springframework.stereotype.Component;

@Component
public class PathResolver {
	
	 private String osbLotteryPath;

	public String getOsbLotteryPath() {
		return osbLotteryPath;
	}

	public void setOsbLotteryPath(String osbLotteryPath) {
		this.osbLotteryPath = osbLotteryPath;
	}
	 
}
