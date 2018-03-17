package com.example.diegommir.skiptest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest API controller used as default landpath for the application.
 * 
 * @author Diego Miranda
 */
@RestController
public class HomeController {
	/**
	 * Root landpath.
	 * 
	 * @return Json
	 */
	@GetMapping(path="home")
	public String home() {
		return "Hello world!";
	}
}
