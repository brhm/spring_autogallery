package com.brhmkskn.service.impl;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.brhmkskn.dto.DtoUser;
import com.brhmkskn.exception.BaseException;
import com.brhmkskn.exception.ErrorMessage;
import com.brhmkskn.exception.MessageType;
import com.brhmkskn.jwt.JWTService;
import com.brhmkskn.dto.RefreshTokenRequest;
import com.brhmkskn.model.RefreshToken;
import com.brhmkskn.model.User;
import com.brhmkskn.repository.IRefreshTokenRepository;
import com.brhmkskn.repository.IUserRepository;
import com.brhmkskn.dto.AuthReponse;
import com.brhmkskn.dto.AuthRequest;
import com.brhmkskn.service.IAuthenticationService;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService{
	@Autowired
	private IUserRepository userRepository;
	@Autowired
	private IRefreshTokenRepository refreshTokenRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@Autowired
	private JWTService jwtService;
	
	
	@Override
	public DtoUser register(AuthRequest request) {
		DtoUser dtoUser=new DtoUser();
		User user=new User();
		
		Optional<User> optionalUser= userRepository.findByUsername(request.getUsername());
		if(optionalUser.isPresent())
		{
			throw new BaseException(new ErrorMessage(MessageType.USERNAME_IS_EXIST, request.getUsername()));
		}
		
		user.setUsername(request.getUsername());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setCreateDate(new Date());
				
		User saveUser =userRepository.save(user);
		BeanUtils.copyProperties(saveUser, dtoUser);
		
		return dtoUser;
	}

	@Override
	public AuthReponse authenticate(AuthRequest request) {

		try {
			UsernamePasswordAuthenticationToken authenticationToken=
					new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword());
			
			authenticationProvider.authenticate(authenticationToken);
			Optional<User> optionalUser= userRepository.findByUsername(request.getUsername());
			
			String accessToken= jwtService.generateToken(optionalUser.get());
			RefreshToken refreshToken= refreshTokenRepository.save(createRefreshToken(optionalUser.get()));

			return new AuthReponse(accessToken,refreshToken.getRefreshToken());
			
		} catch (Exception e) {
			throw new BaseException(new ErrorMessage(MessageType.USERNAME_OR_PASSWORD_INVALID, e.getMessage()));
		}
		
	}
	@Override
	public AuthReponse refreshToken(RefreshTokenRequest request) {
		
		Optional<RefreshToken> optional= refreshTokenRepository.findByRefreshToken(request.getRefreshToken());
		if(optional.isEmpty())
		{
			throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_NOT_FOUND, request.getRefreshToken()));
			
		}
		RefreshToken refreshToken= optional.get();
		if(!isRefreshTokenExpired(refreshToken.getExpiredDate()))
		{
			throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_IS_EXPIRED, request.getRefreshToken()));
		}
		
		User user=optional.get().getUser();
		String accessToken= jwtService.generateToken(refreshToken.getUser());
		RefreshToken newRefreshToken= refreshTokenRepository.save(createRefreshToken(refreshToken.getUser()));
		
		return new AuthReponse(accessToken,newRefreshToken.getRefreshToken());
	}
	
	public boolean isRefreshTokenExpired(Date expDate) {
		return new Date().before(expDate);
	}
	private RefreshToken createRefreshToken(User user)
	{
		RefreshToken refreshToken=new RefreshToken();
		refreshToken.setRefreshToken(UUID.randomUUID().toString());
		refreshToken.setCreateDate(new Date());
		refreshToken.setExpiredDate(new Date(System.currentTimeMillis()+1000*60*60*4));
		refreshToken.setUser(user);
		
		return refreshToken;
	}

}
