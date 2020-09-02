package com.accion.graphql.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Setter
@Getter
@Builder
@Table
@Entity
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private String name;
	private String email;
	
	private int addressId;
	@DateTimeFormat
	private Date dob;
	
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

	
	
}
