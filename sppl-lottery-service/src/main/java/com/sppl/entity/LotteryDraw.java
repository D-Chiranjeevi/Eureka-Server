package com.sppl.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name ="TOTO_DRAW_INFO")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LotteryDraw implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "DRAW_NO")
	private String drawNo;
	@Column(name = "DRAW_DATE")
	@JsonFormat(pattern = "yyyy-mm-dd")
	private Date drawDate;
	@Column(name = "DRAW_Type")
	private String drawType;
	@Column(name = "DRAW_NATURE")
	private String drawNature;
	@Column(name = "STATUS")
	private String status;
	@Column(name = "PRIZE_AMOUNT")
	private BigDecimal prizeAmount;
	@Column(name = "LAST_UPDATED_DATE")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS")
	private Timestamp lastUpdatedDate;

	

}
