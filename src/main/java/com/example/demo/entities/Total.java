package com.example.demo.entities;

public class Total {
	
	private double total;

	public Total() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Total(double total) {
		super();
		this.total = total;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Total [total=" + total + "]";
	}
	

}
