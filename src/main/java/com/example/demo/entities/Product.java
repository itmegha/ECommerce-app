package com.example.demo.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_info")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String title;
	private String category;
	private String color;
	private String imgUrl;
	private double price;
	private Integer quantity;
	private boolean avail;
	
	
//	Whatever I do to the parent, do the same to its children.
//	Related entities are NOT loaded immediately
//	They are fetched only when you access them
	 @JsonIgnore
	 @OneToMany(mappedBy = "product",fetch = FetchType.LAZY)
	 private List<Cart> cartItems = new ArrayList<>();


	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Product(Integer id, String title, String category, String color, String imgUrl, double price,
			Integer quantity, boolean avail, List<Cart> cartItems) {
		super();
		this.id = id;
		this.title = title;
		this.category = category;
		this.color = color;
		this.imgUrl = imgUrl;
		this.price = price;
		this.quantity = quantity;
		this.avail = avail;
		this.cartItems = cartItems;
	}



	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public String getImgUrl() {
		return imgUrl;
	}


	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public boolean isAvail() {
		return avail;
	}


	public void setAvail(boolean avail) {
		this.avail = avail;
	}


	public List<Cart> getCartItems() {
		return cartItems;
	}


	public void setCartItems(List<Cart> cartItems) {
		this.cartItems = cartItems;
	}


	@Override
	public String toString() {
		return "Product [id=" + id + ", title=" + title + ", category=" + category + ", color=" + color + ", imgUrl="
				+ imgUrl + ", price=" + price + ", quantity=" + quantity + ", avail=" + avail + ", cartItems="
				+ cartItems + "]";
	}

	
	 
}
