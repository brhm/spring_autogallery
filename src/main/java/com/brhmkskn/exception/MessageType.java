package com.brhmkskn.exception;

import lombok.Getter;

@Getter
public enum MessageType {

	NO_RECORD_EXIST("1004","No Record!"),
	TOKEN_IS_EXPIRED("1005","Token is expired!"),
	USERNAME_NOT_FOUND("1006","User is not found!"),
	GENERAL_EXCEPTION("9999","General Exception!");
	
	private String code;
	private String message;
	
	MessageType(String code, String message) {
		this.code=code;
		this.message=message;
	}
	
}
