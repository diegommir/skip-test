package com.example.diegommir.skiptest.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.diegommir.skiptest.business.OrderBO;
import com.example.diegommir.skiptest.entity.Item;
import com.example.diegommir.skiptest.entity.Order;
import com.example.diegommir.skiptest.entity.OrderStatus;
import com.example.diegommir.skiptest.exception.ResourceNotFoundException;
import com.example.diegommir.skiptest.repository.ItemRepository;

/**
 * Rest controller for the Item entity.
 * 
 * @author Diego Miranda
 */
public class ItemController {
	@Autowired
	private OrderBO orderBO;

	@Autowired
	private ItemRepository itemRepository;

	/**
	 * Rest method called only by GET that return a list of Items according with the 
	 * informed businessId and orderId.
	 * 
	 * @param businessId
	 * @param orderId
	 * @return Json representing a list of found Items
	 * @throws ResourceNotFoundException if the Business or the Order does not exist
	 */
	@GetMapping("/business/{businessId}/order/{orderId}/item")
	public List<Item> list(@PathVariable Long businessId, @PathVariable Long orderId) {
		Order order = orderBO.getOrder(businessId, orderId);

		List<Item> result = itemRepository.findByOrder(order);
		return result;
	}

	/**
	 * Rest method called only by POST that create a new Item for the informed orderId according with the 
	 * request parameters.
	 * 
	 * @param businessId
	 * @param orderId
	 * @param item
	 * @return Json representing the newly created Item
	 * @throws ResourceNotFoundException if the Business or the Order does not exist
	 */
	@PostMapping(path="/business/{businessId}/order/{orderId}/item")
	public Item create(@PathVariable Long businessId, @PathVariable Long orderId, @Valid @RequestBody Item item) {
		Order order = orderBO.getOrder(businessId, orderId);

		OrderStatus status = order.getStatus();
		if (status != OrderStatus.CREATING && status != OrderStatus.OPEN) {
			//TODO Create a custom exception
			throw new RuntimeException("Cannot update this Order anymore. Order.status: " + order.getStatus());
		}

		item.setOrder(order);
		item = itemRepository.save(item);
		return item;
	}

	/**
	 * Rest method called only by PUT that update the Order represented by the id param
	 * with the data of the request parameters.
	 * 
	 * @param businessId
	 * @param orderId
	 * @param id of the Item
	 * @param item
	 * @return Json representing the updated Item
	 * @throws ResourceNotFoundException if the Business, the Order or the Item does not exist
	 */
	@PutMapping("/{id}")
	public Item update(@PathVariable Long businessId, @PathVariable Long orderId, @PathVariable Long id, @Valid @RequestBody Item item) {
		Order order = orderBO.getOrder(businessId, orderId);

		OrderStatus status = order.getStatus();
		if (status != OrderStatus.CREATING && status != OrderStatus.OPEN) {
			//TODO Create a custom exception
			throw new RuntimeException("Cannot update this Order anymore. Order.status: " + order.getStatus());
		}

		Optional<Item> result = itemRepository.findById(id);

		if (result.isPresent()) {
			item.setId(id);
			item = itemRepository.save(item);
			return item;
		} else {
			throw new ResourceNotFoundException("Item not found. Id: " + id);
		}
	}

	/**
	 * Rest method called only by DELETE that remove the informed Item from the Order.
	 * 
	 * @param businessId
	 * @param orderId
	 * @param id of the order
	 * @throws ResourceNotFoundException if the Business or the Order does not exist
	 */
	@DeleteMapping(path="/business/{businessId}/order/{id}")
	public void delete(@PathVariable Long businessId, @PathVariable Long orderId, @PathVariable Long id) {
		Order order = orderBO.getOrder(businessId, orderId);

		OrderStatus status = order.getStatus();
		if (status != OrderStatus.CREATING && status != OrderStatus.OPEN) {
			//TODO Create a custom exception
			throw new RuntimeException("Cannot update this Order anymore. Order.status: " + order.getStatus());
		}

		itemRepository.deleteById(id);
	}
}
