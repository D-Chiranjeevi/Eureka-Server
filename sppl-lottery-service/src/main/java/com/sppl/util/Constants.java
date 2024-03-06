package com.sppl.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Constants {

	public static final String ESTIMATED_RESULT = "E";
	public static final String AUDIT_DEVICE_OS_KEY = "deviceOs";
	public static final String DEFAULT_X_SOURCE = "MOBILE";

	public static final String TRANSLATION_GROUP_LOTTERY = "lottery";
	public static final String TRANSLATION_GROUP_BETSTATUS = "betstatus";

	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String STANDARD_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

	public static final String LOTTERY_BET_SOLD_OUT_ERROR_CODE = "LOTTERY_BET_SOLD_OUT";

	public static final List<String> TRANSLATION_GROUP_LIST_LOTTERY_GAMES = Collections
			.unmodifiableList(Arrays.asList(TRANSLATION_GROUP_LOTTERY, TRANSLATION_GROUP_BETSTATUS));

	public static final String LOTTERY_SUMMARY_TYPE_TOTO = "toto";
	public static final String LOTTERY_SUMMARY_TYPE_4D = "4d";
	public static final String LOTTERY_SUMMARY_TYPE_SPSS = "spss";

	public static final String LOTTERY_TOTO_RESULTS_CACHE_PREFIX = "totoresults:";
	public static final String LOTTERY_4D_RESULTS_CACHE_PREFIX = "sp4dresults:";
	public static final String LOTTERY_SWEEP_RESULTS_CACHE_PREFIX = "spssresults:";
	
	public static final String TOTO_ESTIMATE_JACKPOT_AMOUNT = "estimatejackpot";
	public static final String LOTTERY_SUMMARY_PREFIX = "summary";

	public static final String ACCOUNT_SERVICE_BASE_URL = "http://ACCOUNT-SERVICE";
	public static final String GET_ACREG1_URL = "/account/getById/{regUUID}";
	
}
