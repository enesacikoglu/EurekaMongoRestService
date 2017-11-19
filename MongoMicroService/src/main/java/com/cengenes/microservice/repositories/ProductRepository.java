/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cengenes.microservice.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cengenes.microservice.models.Product;

/**
 *
 * @author enes.acikoglu
 */
public interface ProductRepository extends MongoRepository<Product, String> {

	@Override
	Product findOne(String id);

	Product findByNumber(Double number);

	List<Product> findAllByOrderByNumberAsc();

	List<Product> findAllByOrderByNumberDesc();

}
