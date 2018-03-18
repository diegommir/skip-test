package com.example.diegommir.skiptest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.diegommir.skiptest.entity.Business;

/**
 * Repository class for the Business entity.
 * 
 * @author Diego Miranda
 */
public interface BusinessRepository extends JpaRepository<Business, Long>  {
	/**
	 * Returns the Business that match with informed id.
	 * 
	 * @param id Long
	 * @return The desired Business
	 */
	Optional<Business> findById(Long id);

	/**
	 * Returns the first Business that match with informed name.
	 * 
	 * @param name String
	 * @return The desired Business
	 */
	Business findByName(String name);
}
