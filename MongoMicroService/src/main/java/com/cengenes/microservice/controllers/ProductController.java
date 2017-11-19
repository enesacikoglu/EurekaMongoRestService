/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cengenes.microservice.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cengenes.microservice.models.Product;
import com.cengenes.microservice.service.ProductService;

/**
 *
 * @author enes.acikoglu
 */
@RestController
public class ProductController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProductService productService;

	@RequestMapping(method = RequestMethod.GET, value = "/products/sample")
	public Product getProduct() {
		return new Product("61", 215d, LocalDateTime.now());
	}

	@RequestMapping(method = RequestMethod.GET, value = "/products/max")
	public Product getMaxProduct() {
		logger.info("product with max number called");
		return productService.getMaxNumberedProduct();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/products/min")
	public Product getMinProduct() {
		logger.info("product with min number called");
		return productService.getMinNumberedProduct();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/products")
	public List<Product> product(@RequestParam(value = "order", defaultValue = "ascending") String ordering) {
		logger.info("product with order called");
		return ordering.equalsIgnoreCase("descending") ? productService.findAllByOrderByNumberDesc()
				: productService.findAllByOrderByNumberAsc();

	}

	@RequestMapping(method = RequestMethod.POST, value = "/products")
	public String save(@RequestBody Product product) throws Exception {
		logger.info("product save called");
		productService.save(product);
		return product.getId();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/products/{number}")
	public Product show(@PathVariable Double number) throws Exception {
		logger.info("product find called");
		return productService.findByNumber(number);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{number}")
	public String delete(@PathVariable Double number) throws Exception {
		logger.info("product delete called");
		productService.delete(number);
		return "product deleted";
	}
}