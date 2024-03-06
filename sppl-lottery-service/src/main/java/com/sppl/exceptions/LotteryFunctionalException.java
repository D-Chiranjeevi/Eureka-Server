package com.sppl.exceptions;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Component
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LotteryFunctionalException extends Exception implements Serializable {
	private static final long serialVersionUID = 1L;

    private int code;
    private String key;
    
    public LotteryFunctionalException(int code, String key, String message) {
        super(message);
        this.code = code;
        this.key = key;
    }
}
