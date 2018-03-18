package com.example.diegommir.skiptest.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Entity class representing a Business.
 * 
 * @author Diego Miranda
 */
@Entity
public class Business {
	@Id
	@GeneratedValue
	private Long id;

	@NotBlank
	@Size(min=3, max=100)
	private String name;

	@NotBlank
	@Size(min=3, max=150)
	private String description;

	@NotBlank
	@Size(min=3, max=150)
	private String address;

	/**
	 * Default constructor.
	 */
	public Business() {
		super();
	}

	/**
	 * Constructor using fields.
	 * 
	 * @param id
	 * @param name
	 * @param description
	 * @param address
	 */
	public Business(Long id, String name, String description, String address) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.address = address;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
}
