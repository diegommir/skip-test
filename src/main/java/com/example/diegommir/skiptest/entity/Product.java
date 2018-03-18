package com.example.diegommir.skiptest.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

/**
 * Entity class representing a Product.
 * 
 * @author Diego Miranda
 */
@Entity
public class Product {
	@Id
	@GeneratedValue
	private Long id;

	@NotBlank
	@Size(min=3, max=100)
	private String name;

	@NotBlank
	@Size(min=3, max=100)
	private String description;

	@NotNull
	@PositiveOrZero
	private Double price;

	@ManyToOne
	private Business business;

	/**
	 * Default constructor.
	 */
	public Product() {
		super();
	}

	/**
	 * Constructor using fields.
	 * 
	 * @param id
	 * @param name
	 * @param description
	 * @param business
	 */
	public Product(Long id, String name, String description, Business business) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.business = business;
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
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @return the business
	 */
	public Business getBusiness() {
		return business;
	}

	/**
	 * @param business the business to set
	 */
	public void setBusiness(Business business) {
		this.business = business;
	}
}
