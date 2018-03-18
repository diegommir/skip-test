package com.example.diegommir.skiptest.entity;

import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Entity class representing a Customer.
 * 
 * @author Diego Miranda
 */
public class Customer {
	@Id
	@GeneratedValue
	private Long id;

	@NotBlank
	@Size(min=3, max=100)
	private String name;

	@NotBlank
	@Email
	@Size(min=3, max=100)
	private String email;

	@NotBlank
	private String password;

	@NotBlank
	@Size(min=3, max=150)
	private String address;

	@NotNull
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate creationDate;

	/**
	 * Default constructor.
	 */
	public Customer() {
		super();
	}

	/**
	 * Constructor using fields.
	 * 
	 * @param id
	 * @param name
	 * @param email
	 * @param password
	 * @param address
	 * @param creationDate
	 */
	public Customer(Long id, String name, String email, String password, String address, LocalDate creationDate) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
		this.creationDate = creationDate;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the creationDate
	 */
	public LocalDate getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}
}
