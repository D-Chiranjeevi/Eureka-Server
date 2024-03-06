package com.sppl.model.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "requestInfo"
})
@XmlRootElement(name = "GetGames")
@Data
public class GetGames {
    @XmlElement(required = true)
    protected com.sppl.model.response.RequestInfo requestInfo;
    

	
    
}
