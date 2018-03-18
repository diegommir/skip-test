package com.example.diegommir.skiptest.controller;

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
import com.example.diegommir.skiptest.entity.Business;
import com.example.diegommir.skiptest.exception.ResourceNotFoundException;
import com.example.diegommir.skiptest.repository.BusinessRepository;

/**
 * Rest controller for the Business entity.
 * 
 * @author Diego Miranda
 */
@RestController
public class BusinessController {
	@Autowired
	private BusinessBO businessBO;

	@Autowired
	private BusinessRepository repository;

	/**
	 * Rest method called only by GET that return all Businesses stored.
	 * 
	 * @return Json representing a list of Businesses
	 */
	@GetMapping("/business")
	public List<Business> list() {
		List<Business> business = repository.findAll();
		return business;
	}

	/**
	 * Rest method called only by GET that return a single Business according with the 
	 * informed id.
	 * 
	 * @param id
	 * @return Json representing the Business
	 * @throws ResourceNotFoundException if the Business or the Product does not exist
	 */
	@GetMapping("/business/{id}")
	public Business get(@PathVariable Long id) {
		Business business = businessBO.getBusiness(id);
		return business;
	}

	/**
	 * Rest method called only by POST that create a new Business according with the 
	 * request parameters.
	 * 
	 * @param business
	 * @return Json representing the newly created Product
	 */
	@PostMapping("/business")
	public Business create(@Valid @RequestBody Business business) {
		business = repository.save(business);
		return business;
	}

	/**
	 * Rest method called only by PUT that update the Business represented by the id param
	 * with the data of the request parameters.
	 * 
	 * @param id
	 * @param business
	 * @return Json representing the updated Business
	 * @throws ResourceNotFoundException if the Business does not exist
	 */
	@PutMapping("/business/{id}")
	public Business update(@PathVariable Long id, @Valid @RequestBody Business business) {
		Business result = businessBO.getBusiness(id);

		business.setId(result.getId());
		business = repository.save(business);
		return business;
	}

	/**
	 * Rest method called only by DELETE that remove the Business represented by the id
	 * param from the database.
	 * 
	 * @param id
	 * @throws ResourceNotFoundException if the Business does not exist
	 */
	@DeleteMapping("/business/{id}")
	public void delete(@PathVariable Long id) {
		Business business = businessBO.getBusiness(id);

		repository.delete(business);
	}
}
