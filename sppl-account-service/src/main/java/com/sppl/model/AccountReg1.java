package com.sppl.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="account1_reg_info")
public class AccountReg1 implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="reg_uuid")
	private String regUUID;
	@Column(name="id_type")
	private String idType;
	@Column(name="id_number")
	private String idNumber;
	@Column(name="id_expiry_date")
	private String idExpiryDate;
	@Column(name="passport_country")
	private String passportCountry;
	@Column(name="is_horse_owner")
	private String isHorseOwner;
	@Column(name="mra_number")
	private String mraNumber;
	@Column(name="horse_validity_start")
	private String horseValidityStart;
	@Column(name="horse_validity_end")
	private String horseValidityEnd;
	private String name;
	@Column(name="nationality_code")
	private String nationalityCode;
	@Column(name="date_of_birth")
	private String dateOfBirth;
	@Column(name="mobile_number")
	private String mobileNumber;
	private String username;
	private String password;
	private String language;
}
