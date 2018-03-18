package com.example.diegommir.skiptest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * Spring boot initial class.
 * 
 * @author Diego Miranda
 */
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class Application {
	/**
	 * System entry point.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
