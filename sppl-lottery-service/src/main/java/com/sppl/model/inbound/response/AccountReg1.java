package com.sppl.model.inbound.response;

import lombok.Data;

@Data
public class AccountReg1 {
	private String regUUID;
	private String idType;
	private String idNumber;
	private String idExpiryDate;
	private String passportCountry;
	private String isHorseOwner;
	private String mraNumber;
	private String horseValidityStart;
	private String horseValidityEnd;
	private String name;
	private String nationalityCode;
	private String dateOfBirth;
	private String mobileNumber;
	private String username;
	private String password;
	private String language;

}
