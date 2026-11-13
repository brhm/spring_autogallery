package com.brhmkskn.dto;

import org.springframework.http.HttpStatus;

import com.brhmkskn.dto.RootEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RootEntity<T> {

	private Integer status;
	private String errorMessage;
	private T payload;
	
	public static <T> RootEntity<T> ok(T payload) {
		RootEntity<T> rootEntity=new RootEntity<>();
		rootEntity.setPayload(payload);
		rootEntity.setStatus(HttpStatus.OK.value());
		rootEntity.setErrorMessage(null);
		return rootEntity;		
	}
	public static <T> RootEntity<T> error(String errorMessage) {
		RootEntity<T> rootEntity=new RootEntity<>();
		rootEntity.setPayload(null);
		rootEntity.setStatus(HttpStatus.EXPECTATION_FAILED.value());
		rootEntity.setErrorMessage(errorMessage);
		return rootEntity;		
	}
}