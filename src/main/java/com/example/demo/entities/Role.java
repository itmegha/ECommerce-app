package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Role {

	 ROLE_ADMIN,
	   ROLE_USER;
	   
		@JsonCreator
	    public static Role fromString(String value) {
	        return Role.valueOf(value.toUpperCase());
	    }

			
}
