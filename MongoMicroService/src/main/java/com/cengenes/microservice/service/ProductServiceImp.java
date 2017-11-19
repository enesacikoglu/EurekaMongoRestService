package com.cengenes.microservice.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.cengenes.microservice.exceptions.DuplicateNumberException;
import com.cengenes.microservice.exceptions.InvalidProductNumberException;
import com.cengenes.microservice.models.Product;
import com.cengenes.microservice.repositories.ProductRepository;

@Service
public class ProductServiceImp implements ProductService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public void save(Product product) {

		logger.info("product save called");

		try {
			product.setInsertDate(LocalDateTime.now());
			productRepository.save(product);
		} catch (DuplicateKeyException e) {
			logger.error("Error on save product", e);
			throw new DuplicateNumberException("Can not insert same number twice!");
		} catch (Exception e) {
			throw e;
		}

	}

	public Product getMaxNumberedProduct() {
		final Query query = new Query().limit(1).with(new Sort(Sort.Direction.DESC, "number"));
		return mongoTemplate.findOne(query, Product.class);
	}

	public Product getMinNumberedProduct() {
		final Query query = new Query().limit(1).with(new Sort(Sort.Direction.ASC, "number"));
		return mongoTemplate.findOne(query, Product.class);
	}

	@Override
	public void delete(Double number) {

		// final Query query = new Query(Criteria.where("number").is(number));

		// Product foundedProduct = mongoTemplate.findOne(query, Product.class);

		Product foundedProduct = productRepository.findByNumber(number);

		if (foundedProduct != null) {

			productRepository.delete(foundedProduct);

		} else {
			logger.error("Error on delete product {Requested number not found!}");
			throw new InvalidProductNumberException("Requested number not found!");
		}

	}

	public List<Product> findAllOrderedAsc() {

		return productRepository.findAllByOrderByNumberAsc();

	}

	public List<Product> findAllOrderedDesc() {

		return productRepository.findAllByOrderByNumberDesc();

	}

	@Override
	public Product findByNumber(Double number) throws Exception {

		Product foundedProduct = productRepository.findByNumber(number);

		if (foundedProduct == null) {
			logger.error("Error on delete product {Requested number not found!}");
			throw new InvalidProductNumberException("Requested number not found!");
		}
		return foundedProduct;
	}

	@Override
	public List<Product> findAllByOrderByNumberDesc() {
		return productRepository.findAllByOrderByNumberDesc();
	}

	@Override
	public List<Product> findAllByOrderByNumberAsc() {
		return productRepository.findAllByOrderByNumberAsc();
	}

}
