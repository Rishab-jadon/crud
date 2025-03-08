package com.rishab.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
public class Product {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@NotBlank(message = "name can not be blank")
	@Size(min=5,max=15,message = "name should be minimum 3 or maximum 15 characters")
	private String name;
	
	@NotNull(message = "price cannot be empty")
	@Positive(message = "price should be greator than 0")
	private double price;
	
	@NotNull(message = "quantity must be required")
	@Positive(message = "quantity should be greator than 0")
	private Integer qty;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(Integer id, String name, double price, Integer qty) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.qty = qty;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

}
