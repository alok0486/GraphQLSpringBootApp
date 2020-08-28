package com.accion.graphql.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accion.graphql.service.PersonService;

import graphql.ExecutionResult;

@RequestMapping("/api/person")
@RestController
public class PersonResource {

    @Autowired
    PersonService personService;

    @PostMapping
    public ResponseEntity<Object> getPersonDetails(@RequestBody String query) {
        ExecutionResult execute = personService.getGraphQL().execute(query);
        return new ResponseEntity<>(execute, HttpStatus.OK);
    }
}
