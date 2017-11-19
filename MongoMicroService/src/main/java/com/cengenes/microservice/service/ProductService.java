package com.cengenes.microservice.service;

import java.util.List;

import com.cengenes.microservice.models.Product;

public interface ProductService {

	void save(Product product) throws Exception;

	Product getMaxNumberedProduct();

	Product getMinNumberedProduct();

	void delete(Double number) throws Exception;

	Product findByNumber(Double number) throws Exception;

	List<Product> findAllByOrderByNumberDesc();

	List<Product> findAllByOrderByNumberAsc();

}
