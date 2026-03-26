package com.example.demo.daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Cart;
import com.example.demo.entities.Product;
import com.example.demo.entities.User;
import com.example.demo.repos.Cartrepo;
import com.example.demo.repos.Productrepo;
import com.example.demo.repos.Userrepo;
import com.example.demo.services.ProductSer;

@Service
public class Productdao implements ProductSer{

	@Autowired
	private Productrepo prepo;
	
	@Autowired
	Userrepo urepo;
	
	@Autowired
	Cartrepo crepo;
	
	@Override
	public String saveProduct(Product p) {
		// TODO Auto-generated method stub
		prepo.save(p);
		return "Product inserted";
	}

	@Override
	public List<Product> findAllPA() {
		// TODO Auto-generated method stub
		List<Product> li = prepo.findAll();
		return li;
	}

	@Override
	public List<Product> findByCatAvail(String category) {
		// TODO Auto-generated method stub
		return prepo.findByCategoryAndAvailTrue(category);
	}

	@Override
	public List<Product> findByCaCo(String category, String color) {
		// TODO Auto-generated method stub
		return prepo.findByCategoryAndColorAndAvailTrue(category,color);
	}

	@Override
	public List<Product> findInRange(Double min, Double max) {
		// TODO Auto-generated method stub
		return prepo.findByPriceBetweenAndAvailTrue(min,max);
	}

	@Override
	public List<Product> searchByTitle(String keyword) {
		// TODO Auto-generated method stub
		return prepo.findByTitleContainingIgnoreCaseAndAvailTrue(keyword);
	}

	@Override
	public List<Product> findLowStock(Integer quantity) {
		// TODO Auto-generated method stub
		return prepo.findByQuantityLessThan(quantity);
	}

	@Override
	public List<Product> findByCatPriceBet(String category, Double min, Double max) {
		// TODO Auto-generated method stub
		return prepo.findByCategoryAndPriceBetweenAndAvailTrue(category,min,max);
	}

	@Override
	public Product deleteByid(Integer id) {
		// TODO Auto-generated method stub
		Product p = prepo.findById(id).get();
		prepo.deleteById(id);
		return p;
	}

	@Override
	public Product updateP(Integer id, Product p) {
		// TODO Auto-generated method stub
		Product p1 = prepo.findById(id).get();
		
		p1.setId(id);
		p1.setTitle(p.getTitle());
		p1.setCategory(p.getCategory());
		p1.setPrice(p.getPrice());
		p1.setColor(p.getColor());
		p1.setAvail(p.isAvail());
		p1.setImgUrl(p.getImgUrl());
		prepo.save(p1);
		
		return p1;
	}

	@Override
	public Product findAllPA1(Integer pid) {
		// TODO Auto-generated method stub
		return prepo.findById(pid).get();
	}

	@Override
	public void addCart(int uid, int pid) {
		User user = urepo.findById(uid).orElseThrow();
	    Product product = prepo.findById(pid).orElseThrow();

	    Cart cartItem = new Cart();
	    cartItem.setUser(user);
	    cartItem.setProduct(product);

	    crepo.save(cartItem);
	}

	@Override
	public List<Cart> getAllc(int id) {
		List<Cart> li = crepo.findAllByUserId(id);
		return li;
	}



}
