package com.sppl.exceptions;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Setter
@Getter
@NoArgsConstructor

public class TechnicalException extends Exception implements Serializable {
	private static final long serialVersionUID = 1L;

	private int status;
	private String code;
	private String redirectUrl;

	public TechnicalException(int status, String code, String message) {
		super(message);
	    this.status = status;
	    this.code = code;
	}
	
	public TechnicalException(int status, String code, String message, String redirectUrl) {
        super(message);
        this.status = status;
        this.code = code;
        this.redirectUrl = redirectUrl;
    }
}
