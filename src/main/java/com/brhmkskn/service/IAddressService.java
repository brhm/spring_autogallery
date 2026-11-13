package com.brhmkskn.service;

import java.util.List;

import com.brhmkskn.dto.DtoAddress;
import com.brhmkskn.dto.DtoAddressUI;


public interface IAddressService {

	public DtoAddress saveAddress(DtoAddressUI Address);
	
	public List<DtoAddress> getAllAddresss();
	
	public DtoAddress getAddressById(Long id);
	
	public void deleteAddress(Long id);
	
	public DtoAddress updateAddress(Long id,DtoAddressUI updateAddress);
	
}
