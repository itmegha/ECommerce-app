package com.example.demo.entities;

public class AuthRes {
	
	 private String jwt;
	    private User user;
		public AuthRes() {
			super();
			// TODO Auto-generated constructor stub
		}
		public AuthRes(String jwt, User user) {
			super();
			this.jwt = jwt;
			this.user = user;
		}
		public String getJwt() {
			return jwt;
		}
		public void setJwt(String jwt) {
			this.jwt = jwt;
		}
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
		@Override
		public String toString() {
			return "AuthRes [jwt=" + jwt + ", user=" + user + "]";
		}
	    
	    

}
