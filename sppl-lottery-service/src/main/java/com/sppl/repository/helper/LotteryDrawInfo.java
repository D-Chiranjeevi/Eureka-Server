package com.sppl.repository.helper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LotteryDrawInfo  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String drawNo;
	@JsonFormat(pattern = "YYYY-MM-DD")
	private Date drawDate;
	private BigDecimal prizeAmount;
}
