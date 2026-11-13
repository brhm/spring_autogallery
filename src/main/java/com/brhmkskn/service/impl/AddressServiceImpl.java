package com.brhmkskn.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brhmkskn.dto.DtoAddress;
import com.brhmkskn.dto.DtoAddressUI;
import com.brhmkskn.dto.DtoCustomer;
import com.brhmkskn.model.Address;
import com.brhmkskn.model.Customer;
import com.brhmkskn.repository.IAddressRepository;
import com.brhmkskn.service.IAddressService;

@Service
public class AddressServiceImpl implements IAddressService{

	@Autowired
	private IAddressRepository addressRepository;
	
	@Override
	public DtoAddress saveAddress(DtoAddressUI dtoAddress) {
		DtoAddress response=new DtoAddress();
		Address address=new Address();
		address.setCreateDate(new Date());
		
		BeanUtils.copyProperties(dtoAddress, address);
		Address dbAddress=addressRepository.save(address);
		
		BeanUtils.copyProperties(dbAddress, response);
		return response;
	}

	@Override
	public List<DtoAddress> getAllAddresss() {
		List<DtoAddress> dtoList=new ArrayList<>();
		List<Address> list=addressRepository.findAll();
		
		for (Address Address:list) {
			DtoAddress dtoAddress = new DtoAddress();
			BeanUtils.copyProperties(Address, dtoAddress);
			dtoList.add(dtoAddress);
		}
		
		return dtoList;
	}

	@Override
	public DtoAddress getAddressById(Long id) {
		DtoAddress dtoAddress=new DtoAddress();
		DtoCustomer dtoCustomer=new DtoCustomer();
		Optional<Address> optional=addressRepository.findById(id);
		if(optional.isEmpty())
			return null;
		
		Address address=optional.get();
		Customer customer=null;//optional.get().getCustomer();
		
		BeanUtils.copyProperties(customer, dtoCustomer);
		BeanUtils.copyProperties(address, dtoAddress);
		
		return dtoAddress;
	}
	

	@Override
	public void deleteAddress(Long id) {
		Optional<Address> optional=addressRepository.findById(id);		
		if(optional.isPresent())
			addressRepository.delete(optional.get());		
	}

	@Override
	public DtoAddress updateAddress(Long id, DtoAddressUI updateAddress) {
		Optional<Address> optional=addressRepository.findById(id);
		DtoAddress dto=new DtoAddress();
		if(optional.isPresent())
		{
			Address dbAddress=optional.get();
			//dbAddress.setBirthOfDate(updateAddress.getBirthOfDate());
			//dbAddress.setFirstName(updateAddress.getFirstName());
			//dbAddress.setLastName(updateAddress.getLastName());
			
		 Address updatedAddress=	addressRepository.save(dbAddress);
		 BeanUtils.copyProperties(updatedAddress, dto);
		 return dto;
		}
		return dto;
	}

}
