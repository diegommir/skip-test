package com.example.diegommir.skiptest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.diegommir.skiptest.entity.Item;

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
}
