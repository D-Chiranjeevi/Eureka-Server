package com.sppl.model.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Error", propOrder = {
    "code",
    "documentRef",
    "text"
})
@Data
public class Error {
	 @XmlElement(required = true)
	    protected String code;
	    @XmlElement(required = true)
	    protected String documentRef;
	    protected String text;

}
