package com.sppl.model.response;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "responseInfo",
    "draws"
})
@Data
public class GetDrawsResponse {

	
	 @XmlAccessorType(XmlAccessType.FIELD)
	    @XmlType(name = "", propOrder = {
	        "draw"
	    })
	    public static class Draws {

	        @XmlElement(required = true)
	        protected List<Draw> draw;

	        
	        public List<Draw> getDraw() {
	            if (draw == null) {
	                draw = new ArrayList<Draw>();
	            }
	            return this.draw;
	        }
}
	 protected GetDrawsResponse.Draws draws;

	    @XmlElement(required = true)
	    protected ResponseInfo responseInfo;

}