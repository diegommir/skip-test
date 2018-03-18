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
import com.example.diegommir.skiptest.business.ProductBO;
import com.example.diegommir.skiptest.entity.Business;
import com.example.diegommir.skiptest.entity.Product;
import com.example.diegommir.skiptest.exception.ResourceNotFoundException;
import com.example.diegommir.skiptest.repository.ProductRepository;

/**
 * Rest controller for the Product entity.
 * 
 * @author Diego Miranda
 */
@RestController
public class ProductController {
	@Autowired
	private BusinessBO businessBO;

	@Autowired
	private ProductBO productBO;

	@Autowired
	private ProductRepository repository;

	/**
	 * Rest method called only by GET that return all Products stored for an
	 * given businessId.
	 * 
	 * @param businessId
	 * @return Json representing a list of Products
	 * @throws ResourceNotFoundException if the Business does not exist
	 */
	@GetMapping("/business/{businessId}/product")
	public List<Product> list(@PathVariable Long businessId) {
		Business business = businessBO.getBusiness(businessId);

		List<Product> products = repository.findByBusiness(business);
		return products;
	}

	/**
	 * Rest method called only by GET that return a single Product according with the 
	 * informed id.
	 * 
	 * @param businessId
	 * @param id
	 * @return Json representing the Product
	 * @throws ResourceNotFoundException if the Business or the Product does not exist
	 */
	@GetMapping("/business/{businessId}/product/{id}")
	public Product get(@PathVariable Long businessId, @PathVariable Long id) {
		Product product = productBO.getProduct(businessId, id);
		return product;
	}

	/**
	 * Rest method called only by POST that create a new Product according with the 
	 * request parameters.
	 * 
	 * @param businessId
	 * @param product
	 * @return Json representing the newly created Product
	 * @throws ResourceNotFoundException if the Business does not exist
	 */
	@PostMapping("/business/{businessId}/product")
	public Product create(@PathVariable Long businessId, @Valid @RequestBody Product product) {
		Business business = businessBO.getBusiness(businessId);

		product.setBusiness(business);
		product = repository.save(product);
		return product;
	}

	/**
	 * Rest method called only by PUT that update the Product represented by the id param
	 * with the data of the request parameters.
	 * 
	 * @param businessId
	 * @param id
	 * @param product
	 * @return Json representing the updated Product
	 * @throws ResourceNotFoundException if the Business or the Product does not exist
	 */
	@PutMapping("/business/{businessId}/product/{id}")
	public Product update(@PathVariable Long businessId, @PathVariable Long id, @Valid @RequestBody Product product) {
		Product result = productBO.getProduct(businessId, id);

		product.setId(result.getId());
		product = repository.save(product);
		return product;
	}

	/**
	 * Rest method called only by DELETE that remove the Product represented by the id
	 * param from the database.
	 * 
	 * @param businessId
	 * @param id
	 * @throws ResourceNotFoundException if the Business or the Product does not exist
	 */
	@DeleteMapping("/business/{businessId}/product/{id}")
	public void delete(@PathVariable Long businessId, @PathVariable Long id) {
		Product product = productBO.getProduct(businessId, id);

		repository.delete(product);
	}

	/**
	 * Rest method called only by GET that return all Products stored 
	 * that mach with the given description.
	 * 
	 * @param desc
	 * @return Json representing a list of Products
	 */
	@GetMapping(path="/product/desc/{desc}")
	public List<Product> list(@PathVariable String desc) {
		List<Product> products = repository.findByName(desc);
		return products;
	}
}
