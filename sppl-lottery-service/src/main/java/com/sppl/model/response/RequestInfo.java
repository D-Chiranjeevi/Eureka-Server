package com.sppl.model.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestInfo", propOrder = {
    "customerUUID",
    "clientToken",
    "channel"
})
@Data
public class RequestInfo {
	 protected String channel;
	    protected String clientToken;
	    protected String customerUUID;
	    @XmlAttribute(name = "version")
	    protected String version;

}
