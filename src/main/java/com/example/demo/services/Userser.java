package com.example.demo.services;

import com.example.demo.entities.OtpRequest;
import com.example.demo.entities.User;

public interface Userser {

	User findUser(String username);

	void saveUs(User u);

	void verifyOtp(OtpRequest request);

	String verify(User u);

	String getToken1(User u);

	User findUser(int id);


}
