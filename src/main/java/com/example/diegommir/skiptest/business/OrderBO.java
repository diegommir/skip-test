package com.example.diegommir.skiptest.business;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.diegommir.skiptest.entity.Business;
import com.example.diegommir.skiptest.entity.Order;
import com.example.diegommir.skiptest.exception.ResourceNotFoundException;
import com.example.diegommir.skiptest.repository.OrderRepository;

/**
 * Business class with common methods related to the Order entity.
 * 
 * @author Diego Miranda
 */
public class OrderBO {
	@Autowired
	private BusinessBO businessBO;

	@Autowired
	private OrderRepository repository;

	/**
	 * Internal method that search for and validate a Order based on params.
	 * 
	 * @param businessId
	 * @param id of the Order
	 * @return Order
	 * @throws ResourceNotFoundException if the Business or the Order does not exist
	 */
	public Order getOrder(Long businessId, Long id) {
		Business business = businessBO.getBusiness(businessId);

		Optional<Order> order = repository.findById(id);

		if (!order.isPresent()) {
			throw new ResourceNotFoundException("Order not found. Id: " + id);
		}

		//If the order doesn't belong to this business
		if (!order.get().getBusiness().getId().equals(business.getId())) {
			throw new ResourceNotFoundException("Order not found. Id: " + id);
		}

		return order.get();
	}
}
