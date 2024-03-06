package com.sppl.model.response;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SelectionGroup", propOrder = { "id", "pickPositionLow", "pickPositionHigh", "items" })
@Data
public class SelectionGroup {

	 @XmlAccessorType(XmlAccessType.FIELD)
	    @XmlType(name = "", propOrder = {
	        "item"
	    })
	    public static class Items {

	        @XmlElement(required = true)
	        protected List<String> item;
	        public List<String> getItem() {
	            if (item == null) {
	                item = new ArrayList<String>();
	            }
	            return this.item;
	        }
	 }
	 protected String id;
	    protected SelectionGroup.Items items;
	    protected String pickPositionHigh;

	    protected String pickPositionLow;
}
