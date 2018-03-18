package com.example.diegommir.skiptest.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.diegommir.skiptest.entity.Product;
import com.example.diegommir.skiptest.exception.ResourceNotFoundException;
import com.example.diegommir.skiptest.repository.ProductRepository;

/**
 * Rest controller for the Product entity.
 * 
 * @author Diego Miranda
 */
@RestController
@RequestMapping("product")
public class ProductController {
	@Autowired
	private ProductRepository repository;

	/**
	 * Rest method called only by GET that return all Products stored.
	 * 
	 * @return Json list of all Products stored
	 */
	@GetMapping
	public List<Product> list() {
		List<Product> products = repository.findAll(new Sort(Direction.DESC, "id"));
		return products;
	}

	/**
	 * Rest method called only by GET that return a single Product according with the 
	 * informed id.
	 * 
	 * @param id
	 * @return Json representing the Product
	 * @throws ResourceNotFoundException if the Product does not exist
	 */
	@GetMapping("/{id}")
	public Product get(@PathVariable Long id) {
		Optional<Product> result = repository.findById(id);

		if (result.isPresent()) {
			return result.get();
		} else {
			throw new ResourceNotFoundException("Person not found. Id: " + id);
		}
	}

	/**
	 * Rest method called only by POST that create a new Product according with the 
	 * request parameters.
	 * 
	 * @param product
	 * @return Json representing the newly created Product
	 */
	@PostMapping
	public Product create(@Valid @RequestBody Product product) {
		product = repository.save(product);
		return product;
	}

	/**
	 * Rest method called only by PUT that update the Product represented by the id param
	 * with the data of the request parameters.
	 * 
	 * @param id
	 * @param person
	 * @return Json represent the updated Product
	 * @throws ResourceNotFoundException if the Product does not exist
	 */
	@PutMapping("/{id}")
	public Product update(@PathVariable Long id, @Valid @RequestBody Product person) {
		Optional<Product> result = repository.findById(id);

		if (result.isPresent()) {
			person.setId(id);
			person = repository.save(person);
			return person;
		} else {
			throw new ResourceNotFoundException("Product not found. Id: " + id);
		}
	}

	/**
	 * Rest method called only by DELETE that remove the Product represented by the id
	 * param from the database.
	 * 
	 * @param id
	 * @throws ResourceNotFoundException if the Product does not exist
	 */
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Product> result = repository.findById(id);

		if (result.isPresent()) {
			repository.deleteById(id);
		} else {
			throw new ResourceNotFoundException("Product not found. Id: " + id);
		}
	}
}
