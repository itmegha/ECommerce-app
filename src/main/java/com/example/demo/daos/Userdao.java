package com.example.demo.daos;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entities.OtpRequest;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repos.Userrepo;
import com.example.demo.services.Userser;

@Service
public class Userdao implements Userser{

	@Autowired
	Userrepo urepo;
	
	@Autowired
	AuthenticationManager authman;
	
	@Autowired
	JwtService jwtservice;
	
	
	@Override
	public void saveUs(User u) {
		// TODO Auto-generated method stub
		urepo.save(u);
	}

	@Override
	public String verify(User u) {
		
		Authentication authenticate = authman.authenticate(new UsernamePasswordAuthenticationToken(u.getUsername(),u.getPassword()));
		// TODO Auto-generated method stub
		if(authenticate.isAuthenticated()) {
		
			return jwtservice.generateToken(u.getUsername(),u.getRole());
		}
		else {
			return "Fail";
		}
	}


	@Override
	public String getToken1(User u) {
		// TODO Auto-generated method stub
		System.out.println(u);
		return jwtservice.generateToken(u.getUsername(),u.getRole());
	}

	@Override
	public User findUser(int id) {
		User u = urepo.findById(id).get();
		return u;
	}

	@Override
	public User findUser(String username) {
		// TODO Auto-generated method stub
		User u = urepo.findByUsername(username);
		return u;
	}


	@Override
	public void verifyOtp(OtpRequest request) {
		// TODO Auto-generated method stub
		   User user = urepo.findByUsername(request.getUsername());

		        System.out.println(user);
				if (!user.getOtp().equals(request.getOtp())) {
				  throw new RuntimeException("Invalid OTP");
				}

				if (user.getOtpExpiry().isBefore(LocalDateTime.now())) {
				  throw new RuntimeException("OTP expired");
				}

				user.setVerified(true);;
				user.setOtp(user.getOtp());
				urepo.save(user);
	}




}



