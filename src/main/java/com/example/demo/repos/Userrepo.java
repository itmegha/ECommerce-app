package com.example.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Role;
import com.example.demo.entities.User;

@Repository
public interface Userrepo extends JpaRepository<User,Integer>{

	User findByUsername(String username);

	User findByUsernameAndRoleAndPassword(String username, Role role, String password);

}
