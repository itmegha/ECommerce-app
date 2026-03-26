package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.Cart;
import com.example.demo.entities.Product;

public interface ProductSer {

	String saveProduct(Product p);

	List<Product> findAllPA();

	List<Product> findByCatAvail(String category);

	List<Product> findByCaCo(String category, String color);

	List<Product> findInRange(Double min, Double max);

	List<Product> searchByTitle(String keyword);

	List<Product> findLowStock(Integer quantity);

	List<Product> findByCatPriceBet(String category, Double min, Double max);

	Product deleteByid(Integer id);

	Product updateP(Integer id, Product p);

	Product findAllPA1(Integer pid);

	void addCart(int uid, int pid);

	List<Cart> getAllc(int id);

	

}
