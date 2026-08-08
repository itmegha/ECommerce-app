package com.example.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Order1;

@Repository
public interface OrderRepository extends JpaRepository<Order1,Long>{

}
