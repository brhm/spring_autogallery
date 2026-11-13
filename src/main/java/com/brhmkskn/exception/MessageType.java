package com.brhmkskn.exception;

import lombok.Getter;

@Getter
public enum MessageType {

	NO_RECORD_EXIST("1004","No Record!"),
	TOKEN_IS_EXPIRED("1005","Token is expired!"),
	USERNAME_NOT_FOUND("1006","User is not found!"),
	USERNAME_IS_EXIST("1007","User is exist!"),
	USERNAME_OR_PASSWORD_INVALID("1008","Username or Password Invalid!"),
	REFRESH_TOKEN_NOT_FOUND("1009","Refresh token is not found!"),
	REFRESH_TOKEN_IS_EXPIRED("1010","Refresh token is expired!"),
	GENERAL_EXCEPTION("9999","General Exception!");
	
	private String code;
	private String message;
	
	MessageType(String code, String message) {
		this.code=code;
		this.message=message;
	}
	
}
