package com.accion.graphql.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.accion.graphql.model.Address;
import com.accion.graphql.model.Person;
import com.accion.graphql.repository.AddressRepository;
import com.accion.graphql.repository.PersonRepository;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;

public class PersonMutation implements GraphQLMutationResolver {

	@Autowired
	PersonRepository  personRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	public Person createPerson(final int id, final String name, final String email, final String dob, final int addressId) {
		Person person = Person.builder().id(id).name(name).email(email).dob(new Date()).addressId(addressId).build();
		return personRepository.save(person);
	 }
	 
	 public Address createAddress(final int id, final String city , final String house_no, final long pin ) {
		 Address address = Address.builder().id(id).city(city).houseNo(house_no).pin(pin).build();
		 return addressRepository.save(address);
	 }
}
