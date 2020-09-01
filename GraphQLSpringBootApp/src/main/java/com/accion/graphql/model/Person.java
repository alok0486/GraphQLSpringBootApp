package com.accion.graphql.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
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
	@Generated
	private int id;
	private String name;
	private String email;
	private int addressId;
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

	
	
}
