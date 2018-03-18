package com.example.diegommir.skiptest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.diegommir.skiptest.business.CustomerBO;
import com.example.diegommir.skiptest.entity.Customer;
import com.example.diegommir.skiptest.exception.ResourceNotFoundException;
import com.example.diegommir.skiptest.repository.CustomerRepository;

/**
 * Rest controller for the Customer entity.
 * 
 * @author Diego Miranda
 */
@RestController
public class CustomerController {
	@Autowired
	private CustomerBO customerBO;

	@Autowired
	private CustomerRepository repository;

	/**
	 * Rest method called only by GET that return all Customers stored.
	 * 
	 * @return Json representing a list of Customers
	 */
	@GetMapping("/customer")
	public List<Customer> list() {
		List<Customer> customer = repository.findAll();
		return customer;
	}

	/**
	 * Rest method called only by GET that return a single Customer according with the 
	 * informed id.
	 * 
	 * @param id
	 * @return Json representing the Customer
	 * @throws ResourceNotFoundException if the Customer or the Product does not exist
	 */
	@GetMapping("/customer/{id}")
	public Customer get(@PathVariable Long id) {
		Customer customer = customerBO.getCustomer(id);
		return customer;
	}

	/**
	 * Rest method called only by POST that create a new Customer according with the 
	 * request parameters.
	 * 
	 * @param customer
	 * @return Json representing the newly created Product
	 */
	@PostMapping("/customer")
	public Customer create(@Valid @RequestBody Customer customer) {
		customer = repository.save(customer);
		return customer;
	}

	/**
	 * Rest method called only by PUT that update the Customer represented by the id param
	 * with the data of the request parameters.
	 * 
	 * @param id
	 * @param customer
	 * @return Json representing the updated Customer
	 * @throws ResourceNotFoundException if the Customer does not exist
	 */
	@PutMapping("/customer/{id}")
	public Customer update(@PathVariable Long id, @Valid @RequestBody Customer customer) {
		Customer result = customerBO.getCustomer(id);

		//Cannot change creationDate
		customer.setId(result.getId());
		customer.setCreationDate(result.getCreationDate());
		customer = repository.save(customer);
		return customer;
	}

	/**
	 * Rest method called only by DELETE that remove the Customer represented by the id
	 * param from the database.
	 * 
	 * @param id
	 * @throws ResourceNotFoundException if the Customer does not exist
	 */
	@DeleteMapping("/customer/{id}")
	public void delete(@PathVariable Long id) {
		Customer customer = customerBO.getCustomer(id);

		repository.delete(customer);
	}
}
