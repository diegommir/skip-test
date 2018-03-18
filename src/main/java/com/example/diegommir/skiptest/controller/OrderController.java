package com.example.diegommir.skiptest.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.diegommir.skiptest.business.BusinessBO;
import com.example.diegommir.skiptest.business.CustomerBO;
import com.example.diegommir.skiptest.business.OrderBO;
import com.example.diegommir.skiptest.entity.Business;
import com.example.diegommir.skiptest.entity.Customer;
import com.example.diegommir.skiptest.entity.Order;
import com.example.diegommir.skiptest.entity.OrderStatus;
import com.example.diegommir.skiptest.exception.ResourceNotFoundException;
import com.example.diegommir.skiptest.repository.OrderRepository;

/**
 * Rest controller for the Order entitiy.
 * 
 * @author Diego Miranda
 */
@RestController
public class OrderController {
	@Autowired
	private BusinessBO businessBO;

	@Autowired
	private CustomerBO customerBO;

	@Autowired
	private OrderBO orderBO;

	@Autowired
	private OrderRepository orderRepository;

	/**
	 * Rest method called only by GET that return a list of Orders according with the 
	 * informed businessId.
	 * 
	 * @param businessId
	 * @return Json representing a list of found Orders
	 * @throws ResourceNotFoundException if the Business does not exist
	 */
	@GetMapping("/business/{businessId}/order")
	public List<Order> list(@PathVariable Long businessId) {
		Business business = businessBO.getBusiness(businessId);

		List<Order> result = orderRepository.findByBusiness(business);
		return result;
	}

	/**
	 * Rest method called only by GET that return a single Order according with the 
	 * informed id.
	 * 
	 * @param businessId
	 * @param id
	 * @return Json representing the Order
	 * @throws ResourceNotFoundException if the Business or the Order does not exist
	 */
	@GetMapping("/business/{businessId}/order/{id}")
	public Order get(@PathVariable Long businessId, @PathVariable Long id) {
		Order order = this.get(businessId, id);
		return order;
	}

	/**
	 * Rest method called only by POST that create a new Order according with the 
	 * request parameters.
	 * 
	 * @param businessId
	 * @param order
	 * @return Json representing the newly created Order
	 * @throws ResourceNotFoundException if the Business or the Customer does not exist
	 */
	@PostMapping(path="/business/{businessId}/order")
	public Order create(@PathVariable Long businessId, @Valid @RequestBody Order order) {
		Business business = businessBO.getBusiness(businessId);

		//TODO Customer info
		Customer customer = customerBO.getCustomer(4L);

		order.setStatus(OrderStatus.CREATING);
		order.setCreationDate(LocalDateTime.now());
		order.setLastChangeDate(LocalDateTime.now());
		order.setBusiness(business);
		order.setAddress(customer.getAddress());
		order.setCustomer(customer);
		order = orderRepository.save(order);
		return order;
	}

	/**
	 * Rest method called only by PUT that update the Order represented by the id param
	 * with the data of the request parameters. Can only update address using this method.
	 * 
	 * @param businessId
	 * @param id of the Order
	 * @param order
	 * @return Json representing the updated Order
	 * @throws ResourceNotFoundException if the Business or the Order does not exist
	 */
	@PutMapping("/business/{businessId}/order/{id}")
	public Order update(@PathVariable Long businessId, @PathVariable Long id, @Valid @RequestBody Order order) {
		Order result = orderBO.getOrder(businessId, id);

		//If the courier didn't get the order yet
		OrderStatus status = result.getStatus();
		if (status != OrderStatus.CREATING && status != OrderStatus.OPEN && status != OrderStatus.PREPARING && status != OrderStatus.READY) {
			//TODO Create a custom exception
			throw new RuntimeException("Cannot update this Order anymore. Order.status: " + result.getStatus());
		}

		//Can only change the address from here
		result.setLastChangeDate(LocalDateTime.now());
		result.setAddress(order.getAddress());
		return orderRepository.save(result);
	}

	/**
	 * Rest method called only by PUT that update the Order represented by the id param
	 * to the next status on the flow.
	 * 
	 * @param businessId
	 * @param id of the Order
	 * @return Json representing the updated Order
	 * @throws ResourceNotFoundException if the Business or the Order does not exist
	 */
	@PutMapping("/business/{businessId}/order/{id}/checkout")
	public Order checkout(@PathVariable Long businessId, @PathVariable Long id) {
		Order order = orderBO.getOrder(businessId, id);

		if (order.getStatus() != OrderStatus.CREATING) {
			//TODO Create a custom exception
			throw new RuntimeException("Cannot do checkout. The Order status is not CREATING. Order.status: " + order.getStatus());
		}

		order.setLastChangeDate(LocalDateTime.now());
		order.setStatus(OrderStatus.OPEN);
		return orderRepository.save(order);
	}

	/**
	 * Rest method called only by PUT that update the Order represented by the id param
	 * to the next status on the flow.
	 * 
	 * @param businessId
	 * @param id of the Order
	 * @return Json representing the updated Order
	 * @throws ResourceNotFoundException if the Business or the Order does not exist
	 */
	@PutMapping("/business/{businessId}/order/{id}/start")
	public Order startPreparing(@PathVariable Long businessId, @PathVariable Long id) {
		Order order = orderBO.getOrder(businessId, id);

		if (order.getStatus() != OrderStatus.OPEN) {
			//TODO Create a custom exception
			throw new RuntimeException("Cannot prepare. The Order status is not OPEN. Order.status: " + order.getStatus());
		}

		order.setLastChangeDate(LocalDateTime.now());
		order.setStatus(OrderStatus.PREPARING);
		return orderRepository.save(order);
	}

	/**
	 * Rest method called only by PUT that update the Order represented by the id param
	 * to the next status on the flow.
	 * 
	 * @param businessId
	 * @param id of the Order
	 * @return Json representing the updated Order
	 * @throws ResourceNotFoundException if the Business or the Order does not exist
	 */
	@PutMapping("/business/{businessId}/order/{id}/ready")
	public Order ready(@PathVariable Long businessId, @PathVariable Long id) {
		Order order = orderBO.getOrder(businessId, id);

		if (order.getStatus() != OrderStatus.PREPARING) {
			//TODO Create a custom exception
			throw new RuntimeException("Cannot be ready. The Order status is not PREPARING. Order.status: " + order.getStatus());
		}

		order.setLastChangeDate(LocalDateTime.now());
		order.setStatus(OrderStatus.READY);
		return orderRepository.save(order);
	}

	/**
	 * Rest method called only by PUT that update the Order represented by the id param
	 * to the next status on the flow.
	 * 
	 * @param businessId
	 * @param id of the Order
	 * @return Json representing the updated Order
	 * @throws ResourceNotFoundException if the Business or the Order does not exist
	 */
	@PutMapping("/business/{businessId}/order/{id}/pickup")
	public Order pickup(@PathVariable Long businessId, @PathVariable Long id) {
		Order order = orderBO.getOrder(businessId, id);

		if (order.getStatus() != OrderStatus.READY) {
			//TODO Create a custom exception
			throw new RuntimeException("Cannot pickup. The Order status is not READY. Order.status: " + order.getStatus());
		}

		order.setLastChangeDate(LocalDateTime.now());
		order.setStatus(OrderStatus.ON_DELIVERY);
		return orderRepository.save(order);
	}

	/**
	 * Rest method called only by PUT that update the Order represented by the id param
	 * to the next status on the flow.
	 * 
	 * @param businessId
	 * @param id of the Order
	 * @return Json representing the updated Order
	 * @throws ResourceNotFoundException if the Business or the Order does not exist
	 */
	@PutMapping("/business/{businessId}/order/{id}/delivered")
	public Order delivered(@PathVariable Long businessId, @PathVariable Long id) {
		Order order = orderBO.getOrder(businessId, id);

		if (order.getStatus() != OrderStatus.ON_DELIVERY) {
			//TODO Create a custom exception
			throw new RuntimeException("Cannot deliver. The Order status is not ON_DELIVERY. Order.status: " + order.getStatus());
		}

		order.setLastChangeDate(LocalDateTime.now());
		order.setStatus(OrderStatus.DELIVERED);
		return orderRepository.save(order);
	}

	/**
	 * Rest method called by DELETE or PUT that change the Order status represented 
	 * by the id to OrderStatus.CANCELED.
	 * 
	 * @param businessId
	 * @param id of the order
	 * @throws ResourceNotFoundException if the Business or the Order does not exist
	 */
	@DeleteMapping(path="/business/{businessId}/order/{id}")
	@PutMapping("/business/{businessId}/order/{id}/cancel")
	public void cancel(@PathVariable Long businessId, @PathVariable Long id) {
		Order order = orderBO.getOrder(businessId, id);

		OrderStatus status = order.getStatus();
		if (status != OrderStatus.CREATING && status != OrderStatus.OPEN) {
			//TODO Create a custom exception
			throw new RuntimeException("Cannot cancel. The Order status is not CREATING or OPEN. Order.status: " + order.getStatus());
		}

		order.setLastChangeDate(LocalDateTime.now());
		order.setStatus(OrderStatus.CANCELED);
		orderRepository.save(order);
	}
}
