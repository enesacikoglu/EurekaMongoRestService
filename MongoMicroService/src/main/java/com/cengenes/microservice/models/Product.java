/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cengenes.microservice.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.cengenes.microservice.serializer.LocalDateTimeDeserializer;
import com.cengenes.microservice.serializer.LocalDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 *
 * @author enes.acikoglu
 */
@Document(collection = "products")
public class Product {

	@JsonIgnore
	@Id
	private String id;

	@Indexed(unique = true)
	private Double number;

	@JsonProperty(value = "insert_date")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	// @CreatedDate
	private LocalDateTime insertDate;

	public Product(String id, Double number, LocalDateTime insertDate) {
		super();
		this.id = id;
		this.number = number;
		this.insertDate = insertDate;
	}

	public Double getNumber() {
		return number;
	}

	public void setNumber(Double number) {
		this.number = number;
	}

	public LocalDateTime getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(LocalDateTime insertDate) {
		this.insertDate = insertDate;
	}

	public String getId() {
		return id;
	}

}
