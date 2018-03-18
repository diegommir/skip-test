package com.example.diegommir.skiptest.business;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.diegommir.skiptest.entity.Customer;
import com.example.diegommir.skiptest.exception.ResourceNotFoundException;
import com.example.diegommir.skiptest.repository.CustomerRepository;

/**
 * Business class with common methods related to the Customer entity.
 * 
 * @author Diego Miranda
 */
@Component
public class CustomerBO {
	@Autowired
	private CustomerRepository repository;

	/**
	 * Internal method that search for and validate a Customer based on params.
	 * 
	 * @param id of the Customer
	 * @return Customer
	 * @throws ResourceNotFoundException if the Customer does not exist
	 */
	public Customer getCustomer(Long id) {
		Optional<Customer> customer = repository.findById(id);

		if (!customer.isPresent()) {
			throw new ResourceNotFoundException("Customer not found. Id: " + id);
		}

		return customer.get();
	}
}
