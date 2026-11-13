package com.brhmkskn.controller.impl;

import com.brhmkskn.dto.RootEntity;

public class RestBaseController {
	
	public <T> RootEntity<T> ok(T data) {
		return RootEntity.ok(data);
		
	}
	public <T> RootEntity<T> error(String errorMessage) {
		return RootEntity.error(errorMessage);
		
	}
}
