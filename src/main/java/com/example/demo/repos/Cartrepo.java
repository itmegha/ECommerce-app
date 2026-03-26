package com.example.demo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Cart;

@Repository
public interface Cartrepo extends JpaRepository<Cart,Integer>{

	List<Cart> findAllByUserId(int id);

	void deleteAllById(int userId);

}
