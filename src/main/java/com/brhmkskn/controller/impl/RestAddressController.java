package com.brhmkskn.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brhmkskn.controller.IRestAddressController;
import com.brhmkskn.dto.RootEntity;
import com.brhmkskn.dto.DtoAddress;
import com.brhmkskn.dto.DtoAddressUI;
import com.brhmkskn.service.IAddressService;
//import com.brhmkskn.service.IRefreshTokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/address")
public class RestAddressController extends RestBaseController  implements IRestAddressController {
	@Autowired
	private IAddressService addressService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoAddress> saveAddress(@Valid @RequestBody DtoAddressUI request) {
		return ok(addressService.saveAddress(request));
	}
}
