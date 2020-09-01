package com.accion.graphql.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
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
import com.accion.graphql.service.datafetcher.AllAddressDataFetcher;
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
	
	@Autowired
	AllAddressDataFetcher allAddressDataFetcher;
	
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
		Stream<Address> stream1 =  Stream.of(Address.builder().city("Ranchi").pin(834001).id(1).houseNo("20").build()); 
		Stream<Address> stream2 = Stream.of(Address.builder().city("Banglore").pin(560032).id(2).houseNo("1").build());
		Stream<Address> stream3 = Stream.of(Address.builder().city("Delhi").pin(123210).id(3).houseNo("1").build());
		
		Stream<Address> stream4 =  Stream.concat(stream1, stream2);
		Stream<Address> stream5 = Stream.concat(stream4, stream3);
		
		List<Address> addresslist = stream5.collect(Collectors.toList());
		addresslist.forEach(address -> {
	       	addressRepository.save(address);
	    });
		
		Stream<Person> person1 = Stream.of( Person.builder().id(1).addressId(1).dob("Jan 2020").name("Alok").email("Alok.Ranjan@accionlabs.com").build());
		Stream<Person> person2 = Stream.of( Person.builder().id(2).addressId(2).dob("March 2020").name("Alok Ranjan").email("Alok.Ranjan@accionlabs.com").build());
		Stream<Person> person3 = Stream.of( Person.builder().id(3).addressId(3).dob("May 2020").name("Ranjan").email("Alok.Ranjan@accionlabs.com").build());
		
		Stream<Person> personStream1 =   Stream.concat(person1, person2);
		Stream<Person> personStream2 =   Stream.concat(personStream1, person3);

		List<Person> personList = personStream2.collect(Collectors.toList());
		personList.forEach(person -> {
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
