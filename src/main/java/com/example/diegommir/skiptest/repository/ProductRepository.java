package com.example.diegommir.skiptest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.diegommir.skiptest.entity.Product;

/**
 * Repository class for the Product entity.
 * 
 * @author Diego Miranda
 */
public interface ProductRepository extends JpaRepository<Product, Long>  {
	/**
	 * Returns the Product that match with informed id.
	 * 
	 * @param id Long
	 * @return The desired Product
	 */
	Optional<Product> findById(Long id);

	/**
	 * Returns the first Product that match with informed name.
	 * 
	 * @param name String
	 * @return The desired Product
	 */
	Product findByName(String name);
}
