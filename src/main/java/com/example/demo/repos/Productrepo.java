package com.example.demo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Product;

@Repository
public interface Productrepo extends JpaRepository<Product,Integer>{

	List<Product> findByAvailTrue();

	List<Product> findByCategoryAndAvailTrue(String category);

	List<Product> findByCategoryAndColorAndAvailTrue(String category, String color);

	List<Product> findByPriceBetweenAndAvailTrue(Double min, Double max);

	List<Product> findByTitleContainingIgnoreCaseAndAvailTrue(String keyword);

	List<Product> findByQuantityLessThan(Integer quantity);

	List<Product> findByCategoryAndPriceBetweenAndAvailTrue(String category, Double min, Double max);
	

}
