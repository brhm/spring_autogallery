package com.brhmkskn.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.brhmkskn.controller.IRestAuthenticationController;
import com.brhmkskn.dto.DtoUser;
import com.brhmkskn.dto.RefreshTokenRequest;
import com.brhmkskn.dto.RootEntity;
import com.brhmkskn.dto.AuthReponse;
import com.brhmkskn.dto.AuthRequest;
import com.brhmkskn.service.IAuthenticationService;
//import com.brhmkskn.service.IRefreshTokenService;

import jakarta.validation.Valid;

@RestController
public class RestAuthenticationController extends RestBaseController  implements IRestAuthenticationController {
	@Autowired
	private IAuthenticationService authService;
	
	//@Autowired
	//private IRefreshTokenService refreshTokenService;
	
	@PostMapping("/register")
	@Override
	public RootEntity<DtoUser> register(@Valid @RequestBody AuthRequest request) {

		return ok(authService.register(request));
	}
	
	@PostMapping("/authenticate")
	@Override
	public RootEntity<AuthReponse> authenticate(@Valid @RequestBody AuthRequest request) {

		return ok(authService.authenticate(request));
	}

	@PostMapping("/refreshToken")
	@Override
	public RootEntity<AuthReponse> refreshToken(@RequestBody RefreshTokenRequest request) {
		
		return ok(authService.refreshToken(request));
	}
}
