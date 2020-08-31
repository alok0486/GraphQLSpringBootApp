package com.accion.graphql.service.datafetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.accion.graphql.model.Address;
import com.accion.graphql.model.Person;
import com.accion.graphql.repository.AddressRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class AllAddressDataFetcher implements DataFetcher<Address>{
	
	@Autowired
	AddressRepository addressRepository;

	@Override
	public Address get(DataFetchingEnvironment environment) {
		Person person = environment.getSource();
		return addressRepository.findById(person.getAddressId()).get();
	}
}
