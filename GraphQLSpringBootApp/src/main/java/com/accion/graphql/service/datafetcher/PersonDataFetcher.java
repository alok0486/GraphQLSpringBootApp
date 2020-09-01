package com.accion.graphql.service.datafetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.accion.graphql.model.Person;
import com.accion.graphql.repository.PersonRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class PersonDataFetcher implements DataFetcher<Person>{
	
	@Autowired
	PersonRepository personRepository;
	
	@Override
	public Person get(DataFetchingEnvironment environment) {
		int id = environment.getArgument("id");
		return personRepository.findById(id).get();
	}
	
	

}
