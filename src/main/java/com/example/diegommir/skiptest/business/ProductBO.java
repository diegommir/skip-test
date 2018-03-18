package com.example.diegommir.skiptest.business;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.diegommir.skiptest.entity.Business;
import com.example.diegommir.skiptest.entity.Product;
import com.example.diegommir.skiptest.exception.ResourceNotFoundException;
import com.example.diegommir.skiptest.repository.ProductRepository;

/**
 * Business class with common methods related to the Product entity.
 * 
 * @author Diego Miranda
 */
@Component
public class ProductBO {
	@Autowired
	private BusinessBO businessBO;

	@Autowired
	private ProductRepository repository;

	/**
	 * Internal method that search for and validate a Product based on params.
	 * 
	 * @param businessId
	 * @param id of the Product
	 * @return Product
	 * @throws ResourceNotFoundException if the Business or the Product does not exist
	 */
	public Product getProduct(Long businessId, Long id) {
		Business business = businessBO.getBusiness(businessId);

		Optional<Product> product = repository.findById(id);

		if (!product.isPresent()) {
			throw new ResourceNotFoundException("Product not found. Id: " + id);
		}

		//If the product doesn't belong to this business
		if (!product.get().getBusiness().getId().equals(business.getId())) {
			throw new ResourceNotFoundException("Product not found. Id: " + id);
		}

		return product.get();
	}
}
