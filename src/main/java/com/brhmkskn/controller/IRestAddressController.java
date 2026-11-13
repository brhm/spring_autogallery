package com.brhmkskn.controller;

import com.brhmkskn.dto.DtoAddress;
import com.brhmkskn.dto.DtoAddressUI;
import com.brhmkskn.dto.RootEntity;

public interface IRestAddressController {
	public RootEntity<DtoAddress> saveAddress(DtoAddressUI request);
}
