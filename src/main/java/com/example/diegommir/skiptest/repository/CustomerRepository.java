package com.example.diegommir.skiptest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.diegommir.skiptest.entity.Customer;

/**
 * Repository class for the Customer entity.
 * 
 * @author Diego Miranda
 */
public interface CustomerRepository extends JpaRepository<Customer, Long>  {
	/**
	 * Returns the Customer that match with informed id.
	 * 
	 * @param id Long
	 * @return The desired Customer
	 */
	Optional<Customer> findById(Long id);

	/**
	 * Returns the first Customer that match with informed name.
	 * 
	 * @param name String
	 * @return The desired Customer
	 */
	Customer findByName(String name);
}
