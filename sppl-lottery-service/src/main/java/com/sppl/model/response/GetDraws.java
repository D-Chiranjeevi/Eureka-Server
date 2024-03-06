package com.sppl.model.response;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "requestInfo",
    "data"
})
@XmlRootElement(name = "GetDraws")
@lombok.Data
public class GetDraws {

	
	 @XmlAccessorType(XmlAccessType.FIELD)
	    @XmlType(name = "", propOrder = {
	        "gameRef",
	        "startDate",
	        "endDate",
	        "count",
	        "paginationToken",
	        "isActive"
	    })
	 @lombok.Data
	    public static class Data {

	        protected BigInteger count;
	        @XmlSchemaType(name = "dateTime")
	        protected XMLGregorianCalendar endDate;
	        protected String gameRef;
	        protected Boolean isActive;
	        protected String paginationToken;
	        @XmlSchemaType(name = "dateTime")
	        protected XMLGregorianCalendar startDate;
	        
}
	 
	 @XmlElement(required = true)
	    protected GetDraws.Data data;

	    @XmlElement(required = true)
	    protected RequestInfo requestInfo;
}
