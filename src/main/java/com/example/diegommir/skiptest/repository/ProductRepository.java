package com.example.diegommir.skiptest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.diegommir.skiptest.entity.Business;
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
	 * Returns a list of Products based on the given business.
	 * 
	 * @param business
	 * @return a list of Products
	 */
	List<Product> findByBusiness(Business business);

	/**
	 * Returns a list of Products based on the given name.
	 * 
	 * @param name
	 * @return a list of Products
	 */
	List<Product> findByName(String name);
}
