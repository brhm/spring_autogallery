package com.brhmkskn.service;

import com.brhmkskn.dto.DtoUser;
import com.brhmkskn.dto.RefreshTokenRequest;
import com.brhmkskn.dto.AuthReponse;
import com.brhmkskn.dto.AuthRequest;

public interface IAuthenticationService {
	public DtoUser register(AuthRequest request);
	public AuthReponse authenticate(AuthRequest request);
	public AuthReponse refreshToken(RefreshTokenRequest request);
}
