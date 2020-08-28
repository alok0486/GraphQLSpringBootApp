package com.accion.graphql.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
public class Address {
	@Id
	private	String id;
	private	String house_no;
	private	String city;
	private	String pin;
	
	public Address() {}
	
	public Address(String id, String house_no, String city, String pin) {
		this.id = id;
		this.house_no = house_no;
		this.city = city;
		this.pin = pin;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getHouse_no() {
		return house_no;
	}
	public void setHouse_no(String house_no) {
		this.house_no = house_no;
	}
	
}
