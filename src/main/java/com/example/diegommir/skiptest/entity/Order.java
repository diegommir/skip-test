package com.example.diegommir.skiptest.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Entity class representing a Order.
 * 
 * @author Diego Miranda
 */
//Had to explicit a non-default name, cause "order" is a reserved word of
//SQL and I was getting errors.
@Entity(name="orders")
public class Order {
	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@JsonFormat(pattern="yyyy-MM-dd hh:mm")
	private LocalDateTime creationDate;

	@NotNull
	@JsonFormat(pattern="yyyy-MM-dd hh:mm")
	private LocalDateTime lastChangeDate;

	@NotNull
	private OrderStatus status;

	@NotNull
	@PositiveOrZero
	private Double total;

	@NotBlank
	@Size(min=3, max=150)
	private String address;

	@ManyToOne
	private Business business;

	@ManyToOne
	private Customer customer;

	/**
	 * Default constructor.
	 */
	public Order() {
		super();
	}

	/**
	 * Constructor using fields.
	 * 
	 * @param id
	 * @param creationDate
	 * @param lastChangeDate
	 * @param status
	 * @param total
	 * @param address
	 */
	public Order(Long id, LocalDateTime creationDate, LocalDateTime lastChangeDate, OrderStatus status, Double total,
			String address, Business business, Customer customer) {
		super();
		this.id = id;
		this.creationDate = creationDate;
		this.lastChangeDate = lastChangeDate;
		this.status = status;
		this.total = total;
		this.address = address;
		this.business = business;
		this.customer = customer;
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
	 * @return the creationDate
	 */
	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the lastChangeDate
	 */
	public LocalDateTime getLastChangeDate() {
		return lastChangeDate;
	}

	/**
	 * @param lastChangeDate the lastChangeDate to set
	 */
	public void setLastChangeDate(LocalDateTime lastChangeDate) {
		this.lastChangeDate = lastChangeDate;
	}

	/**
	 * @return the status
	 */
	public OrderStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	/**
	 * @return the total
	 */
	public Double getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(Double total) {
		this.total = total;
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

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
