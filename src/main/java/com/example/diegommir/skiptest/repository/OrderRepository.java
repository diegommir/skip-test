package com.example.diegommir.skiptest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.diegommir.skiptest.entity.Business;
import com.example.diegommir.skiptest.entity.Order;

/**
 * Repository class for the Order entity.
 * 
 * @author Diego Miranda
 */
public interface OrderRepository extends JpaRepository<Order, Long>  {
	/**
	 * Returns the Order that match with informed id.
	 * 
	 * @param id Long
	 * @return The desired Order
	 */
	Optional<Order> findById(Long id);

	/**
	 * Returns a list for Orders from the indicated Business.
	 * 
	 * @param business
	 * @return a list of Orders
	 */
	List<Order> findByBusiness(Business business);
}
