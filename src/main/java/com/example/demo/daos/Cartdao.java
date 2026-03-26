package com.example.demo.daos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repos.Cartrepo;
import com.example.demo.services.Cartser;

@Service
public class Cartdao implements Cartser{

	@Autowired
    private Cartrepo crepo;
	
	@Override
	public void deleteItemId(int id) {
		// TODO Auto-generated method stub
		crepo.deleteById(id);
		}

	@Override
	public void deleteCart(int userId) {
		// TODO Auto-generated method stub
		crepo.deleteAllById(userId);
	}
}
