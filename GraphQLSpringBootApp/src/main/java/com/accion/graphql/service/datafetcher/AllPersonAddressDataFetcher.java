package com.accion.graphql.service.datafetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.accion.graphql.model.Address;
import com.accion.graphql.model.Person;
import com.accion.graphql.repository.AddressRepository;
import com.accion.graphql.repository.PersonRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class AllPersonAddressDataFetcher implements DataFetcher<Address>{

	@Autowired
	PersonRepository personRespository;
	
	@Autowired
	AddressRepository addressRepository;
	
	@Override
	public Address get(DataFetchingEnvironment environment) {
		int id = environment.getArgument("id");
		Person person = personRespository.findById(id).get();
		
		int addressId = person.getAddressId();
		System.out.println("Address Id >> "+addressId);

		Address address = addressRepository.findById(addressId).get();
		System.out.println("Addresssss >>>"+address.getCity());
		
		return address;
	}
	
}
