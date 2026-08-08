package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Order1 {
	
	@Id
    @GeneratedValue(strategy  = GenerationType.AUTO)
    private Long id;

    private String status; // Shipped, Delivered, Processing
    private String location;
    private String deliveryDate;
	public Order1() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order1(Long id, String status, String location, String deliveryDate) {
		super();
		this.id = id;
		this.status = status;
		this.location = location;
		this.deliveryDate = deliveryDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", status=" + status + ", location=" + location + ", deliveryDate=" + deliveryDate
				+ "]";
	}
    
    

}
