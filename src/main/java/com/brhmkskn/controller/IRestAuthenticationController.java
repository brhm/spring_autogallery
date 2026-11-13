package com.brhmkskn.controller;

import com.brhmkskn.dto.DtoUser;
import com.brhmkskn.dto.RefreshTokenRequest;
import com.brhmkskn.dto.RootEntity;
import com.brhmkskn.dto.AuthReponse;
import com.brhmkskn.dto.AuthRequest;
//import com.brhmkskn.dto.RefreshTokenRequest;

public interface IRestAuthenticationController {
	public RootEntity<DtoUser> register(AuthRequest request);
	public RootEntity<AuthReponse> authenticate(AuthRequest request);
	public RootEntity<AuthReponse> refreshToken(RefreshTokenRequest request);
}
