package com.example.diegommir.skiptest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.diegommir.skiptest.entity.Item;
import com.example.diegommir.skiptest.entity.Order;

/**
 * Repository class for the Item entity.
 * 
 * @author Diego Miranda
 */
public interface ItemRepository extends JpaRepository<Item, Long>  {
	/**
	 * Returns the Item that match with informed id.
	 * 
	 * @param id Long
	 * @return The desired Item
	 */
	Optional<Item> findById(Long id);

	/**
	 * Return a list of Items based on the informed order.
	 * 
	 * @param order
	 * @return list of found Items
	 */
	List<Item> findByOrder(Order order);
}
