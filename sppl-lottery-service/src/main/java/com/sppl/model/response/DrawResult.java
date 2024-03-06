package com.sppl.model.response;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DrawResult", propOrder = {
    "type",
    "outcome"
})
@Data
public class DrawResult {
	@XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "pick"
    })
    public static class Outcome {

        @XmlElement(required = true)
        protected List<Pick> pick;
        
        public List<Pick> getPick() {
            if (pick == null) {
                pick = new ArrayList<Pick>();
            }
            return this.pick;
        }

	}
	 protected DrawResult.Outcome outcome;

	    protected String type;
}
