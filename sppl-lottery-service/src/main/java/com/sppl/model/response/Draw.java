package com.sppl.model.response;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Draw", propOrder = {
    "id",
    "gameRef",
    "drawDate",
    "drawClosure",
    "status",
    "drawNo",
    "jackpot",
    "results",
    "drawSelectionGroups"
})
@Data
public class Draw {

	 @XmlAccessorType(XmlAccessType.FIELD)
	    @XmlType(name = "", propOrder = {
	        "drawSelectionGroup"
	    })
	    public static class DrawSelectionGroups {

	        @XmlElement(required = true)
	        protected List<DrawSelectionGroup> drawSelectionGroup;
	        public List<DrawSelectionGroup> getDrawSelectionGroup() {
	            if (drawSelectionGroup == null) {
	                drawSelectionGroup = new ArrayList<DrawSelectionGroup>();
	            }
	            return this.drawSelectionGroup;
	        }
	 }
	 @XmlAccessorType(XmlAccessType.FIELD)
	    @XmlType(name = "", propOrder = {
	        "result"
	    })
	    public static class Results {

	        @XmlElement(required = true)
	        protected List<DrawResult> result;
	        public List<DrawResult> getResult() {
	            if (result == null) {
	                result = new ArrayList<DrawResult>();
	            }
	            return this.result;
	        }
	        
	 }
	 protected String drawClosure;
	    protected String drawDate;
	    protected String drawNo;
	    protected Draw.DrawSelectionGroups drawSelectionGroups;
	    @XmlElement(required = true)
	    protected String gameRef;
	    @XmlElement(required = true)
	    protected String id;
	    protected String jackpot;

	    protected Draw.Results results;

	    protected String status;
}
