package com.accion.graphql.service;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.accion.graphql.model.Address;
import com.accion.graphql.model.Person;
import com.accion.graphql.repository.AddressRepository;
import com.accion.graphql.repository.PersonRepository;
import com.accion.graphql.service.datafetcher.AddressDataFetcher;
import com.accion.graphql.service.datafetcher.AllPersonAddressDataFetcher;
import com.accion.graphql.service.datafetcher.PersonDataFetcher;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class PersonService  {

	@Value("classpath:person.graphql")
	Resource scheamResource;
	
	private GraphQL graphQL;
	
	@Autowired
	PersonRepository personrepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	private PersonDataFetcher personDataFetcher;
	
	@Autowired
	AddressDataFetcher addressDataFetcher;
	
	@Autowired
	AllPersonAddressDataFetcher allPersonAddressDataFetcher;
	
	
	@PostConstruct
	private void loadSchema() throws IOException{
		LoadData();
        File schemaFile = scheamResource.getFile();
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
	}
	
	private void LoadData() {
		 Stream.of(
				  new Address("001","1", "Ranchi", "834001"),
				  new Address("002","2", "Pune", "410081"),
				  new Address("003","3","Banglore","560031")
	        ).forEach(address -> {
	        	addressRepository.save(address);
	        });
		 
		 Stream.of(
				  new Person("1", "Alok", "alok.ranjan@accionlabs.com",
						  "001" , "Sepetember 2017"),
				  new Person("2", "Ranjan", "ranjan@accionlabs.com",
						 "002", "June 2019"),
				  new Person("3", "Alok Ranjan", "ar_alok@accionlabs.com",
						 "003", "Jan 2012")
	        ).forEach(person -> {
	        	personrepository.save(person);
	        });
	}

	private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                		.dataFetcher("personById", personDataFetcher)
                		.dataFetcher("addressById",addressDataFetcher)
                		.dataFetcher("addressByPersonId",allPersonAddressDataFetcher))
		.type("Person", typeWiring -> typeWiring
                		.dataFetcher("address", allAddressDataFetcher)
                		)
                .build();
    }
	
	public GraphQL getGraphQL(){
		return graphQL;
	}
}
