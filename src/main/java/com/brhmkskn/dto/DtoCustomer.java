package com.brhmkskn.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoCustomer extends DtoBase{
	
	private String firstName;
	
	private String lastName;
	
	private String tckn;
	
	private DtoAddress address;

	
	private DtoAccount account;
}
