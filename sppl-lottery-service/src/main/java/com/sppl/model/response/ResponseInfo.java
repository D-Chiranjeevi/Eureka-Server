package com.sppl.model.response;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseInfo", propOrder = {
    "status",
    "clientToken",
    "errors"
})
public class ResponseInfo {

	  @XmlAccessorType(XmlAccessType.FIELD)
	    @XmlType(name = "", propOrder = {
	        "error"
	    })
	    public static class Errors {

	        @XmlElement(required = true)
	        protected List<Error> error;
	        
	        public List<Error> getError() {
	            if (error == null) {
	                error = new ArrayList<Error>();
	            }
	            return this.error;
	        }
	  }
	  protected String clientToken;
	    protected ResponseInfo.Errors errors;

	    @XmlElement(required = true)
	    protected String status;

		public String getClientToken() {
			return clientToken;
		}

		public void setClientToken(String clientToken) {
			this.clientToken = clientToken;
		}

		public ResponseInfo.Errors getErrors() {
			return errors;
		}

		public void setErrors(ResponseInfo.Errors errors) {
			this.errors = errors;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

}
