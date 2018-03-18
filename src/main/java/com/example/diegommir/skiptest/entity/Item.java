package com.example.diegommir.skiptest.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

/**
 * Entity class representing on Item of an Order.
 * 
 * @author Diego Miranda
 */
@Entity
public class Item {
	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@PositiveOrZero
	private Double price;

	@NotNull
	@Positive
	private Integer qtd;

	@ManyToOne
	private Product product;

	@ManyToOne
	private Order order;

	/**
	 * 
	 */
	public Item() {
		super();
	}

	/**
	 * @param id
	 * @param price
	 * @param qtd
	 * @param product
	 * @param order
	 */
	public Item(Long id, Double price, Integer qtd, Product product, Order order) {
		super();
		this.id = id;
		this.price = price;
		this.qtd = qtd;
		this.product = product;
		this.order = order;
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
	 * @return the qtd
	 */
	public Integer getQtd() {
		return qtd;
	}

	/**
	 * @param qtd the qtd to set
	 */
	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}

	/**
	 * Get method for a ghost attribute representing the total value of the 
	 * item. Which means price * qtd.
	 *  
	 * @return the total
	 */
	public Double getTotal() {
		return this.price * this.qtd;
	}

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * @return the order
	 */
	public Order getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(Order order) {
		this.order = order;
	}
}
