package com.accion.graphql.service;

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
	
	public Person createPerson(final String id, final String name, final String email, final String dob, final String addressId) {
		 Person person = new Person();
		 person.setId(id);
		 person.setName(name);
		 person.setEmail(email);
		 person.setDob(dob);
		 person.setAddressId(addressId);
		return personRepository.save(person);
	 }
	 
	 public Address createAddress(final String id, final String city , final String house_no, final String pin ) {
		 Address address = new Address();
		 address.setId(id);
		 address.setCity(city);
		 address.setHouse_no(house_no);
		 address.setPin(pin);
		 return addressRepository.save(address);
	 }
}
