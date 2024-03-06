package com.sppl.model.response;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LotteryBetType", propOrder = {
    "id",
    "text",
    "lines",
    "isFixedPrice",
    "minimumStake",
    "maximumStake",
    "payout"
})
@Data
public class LotteryBetType {

	 @XmlElement(required = true)
	    protected String id;
	    protected boolean isFixedPrice;
	    protected BigInteger lines;
	    protected String maximumStake;
	    protected String minimumStake;
	    protected BigDecimal payout;
	    @XmlElement(required = true)
	    protected String text;

}
