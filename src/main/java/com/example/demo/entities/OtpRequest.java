package com.example.demo.entities;

public class OtpRequest {

	private String otp;
	private String username;
	public OtpRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OtpRequest(String otp, String username) {
		super();
		this.otp = otp;
		this.username = username;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "OtpRequest [otp=" + otp + ", username=" + username + "]";
	}
	

}
