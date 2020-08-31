package com.accion.graphql.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
public class Person {
	
	public Person(){}

	public Person(String id, String name, String email,String addressId, String dob) {
		this.id = id;
		this.name = name;
		this.addressId = addressId;
		this.email = email;
		this.dob = dob;
	}
	@Id
	private String id;
	private String name;
	private String email;
	private String addressId;
	private String dob;
	
	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "author_id", nullable = false, updatable = false) private
	 * Address addresses;
	 * 
	 * public Address getAddresses() { return addresses; }
	 * 
	 * public void setAddresses(Address addresses) { this.addresses = addresses; }
	 */

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	
	
}
