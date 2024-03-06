package com.sppl.helper;

import com.sppl.constants.Constants;

public class PathResolver {

	public String getDigibcLotterySummaryTotoPath() {
		return Constants.DIGIBC_LOTTERY_SUMMARY_PATH.concat("/TOTO49");
	}
	
	public String getDigibcLotterySummary4dPath() {
		return Constants.DIGIBC_LOTTERY_SUMMARY_PATH.concat("/DIGIT4");
	}

	public String getDigibcLotterySummarySpssPath() {
		return Constants.DIGIBC_LOTTERY_SUMMARY_PATH.concat("/SWEEP");
	}
}
