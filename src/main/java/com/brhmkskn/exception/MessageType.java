package com.brhmkskn.exception;

import lombok.Getter;

@Getter
public enum MessageType {

	NO_RECORD_EXIST("1004","No Record"),
	GENERAL_EXCEPTION("9999","General Exception");
	
	private String code;
	private String message;
	
	MessageType(String code, String message) {
		this.code=code;
		this.message=message;
	}
	
}
