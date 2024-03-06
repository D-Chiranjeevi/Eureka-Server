package com.sppl.model.response;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DrawSelectionGroup", propOrder = {
    "selectionGroupRef",
    "availableItems"
})
@Data
public class DrawSelectionGroup {

	
	 @XmlAccessorType(XmlAccessType.FIELD)
	    @XmlType(name = "", propOrder = {
	        "availableItem"
	    })
	    public static class AvailableItems {

	        @XmlElement(required = true)
	        protected List<String> availableItem;

	        public List<String> getAvailableItem() {
	            if (availableItem == null) {
	                availableItem = new ArrayList<String>();
	            }
	            return this.availableItem;
	        }
        
	        
}
	  protected DrawSelectionGroup.AvailableItems availableItems;

	    protected String selectionGroupRef;

}