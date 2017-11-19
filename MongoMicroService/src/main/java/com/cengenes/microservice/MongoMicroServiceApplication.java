package com.cengenes.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MongoMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoMicroServiceApplication.class, args);
	}
}
