package com.sppl.model.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Pick", propOrder = { "position", "pick" })
@Data
public class Pick {
	protected String pick;
	protected String position;

}
