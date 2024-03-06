package com.sppl.inbout.response;

import lombok.Data;

@Data
public class AccountReg1Resp {

	private String mobileNumber;
	private String otpSessionId;
	private String regUUID;

}
