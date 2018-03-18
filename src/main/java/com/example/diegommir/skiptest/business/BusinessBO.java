package com.example.diegommir.skiptest.business;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.diegommir.skiptest.entity.Business;
import com.example.diegommir.skiptest.exception.ResourceNotFoundException;
import com.example.diegommir.skiptest.repository.BusinessRepository;

/**
 * @author Diego Miranda
 */
@Component
public class BusinessBO {
	@Autowired
	private BusinessRepository repository;

	/**
	 * Internal method that search for and validate a Order based on params.
	 * 
	 * @param id of the Business
	 * @return Business
	 * @throws ResourceNotFoundException if the Business does not exist
	 */
	public Business getBusiness(Long id) {
		Optional<Business> business = repository.findById(id);

		if (!business.isPresent()) {
			throw new ResourceNotFoundException("Business not found. Id: " + id);
		}

		return business.get();
	}
}
