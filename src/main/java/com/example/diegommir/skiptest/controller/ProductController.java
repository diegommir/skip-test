package com.example.diegommir.skiptest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.diegommir.skiptest.entity.Product;
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
		List<Product> products = repository.findAll();
		return products;
	}

	@GetMapping(path="new")
	public String createNew() {
		Product product = new Product(null, "LG Q6", "Smartphone produced by LG.");
		repository.save(product);

		return product.getName();
	}
}
