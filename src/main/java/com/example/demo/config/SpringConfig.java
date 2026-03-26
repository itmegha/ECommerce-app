package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringConfig {
	
	 private final JwtFilter jwtFilter;

	    public SpringConfig(JwtFilter jwtFilter) {
	        this.jwtFilter = jwtFilter;
	    }
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

	    http
	        .csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(auth -> auth

	        	.requestMatchers("/user/saveu","/user/login",
	        			"/user/verify-otp",
	        			"/user/gettoken",
	        			"/product/availp",
	        			"/product/availBCat",
	        			"/product/searchBTitle",
	        			"/product/product/{pid}",
	        			"/user/logout"
	        			).permitAll()
	        	.requestMatchers("/product/addtocart","/product/create-order",
	        			"/product/deleteItem/{id}","/product/deleteCart/{userId}",
	        			"/product/showCart").hasRole("USER")
	        	.requestMatchers("/product/savep",
	        			"/product/availp",
	        			"/product/delete/{id}",
	        			"/product/updateP/{id}"
	        			).hasRole("ADMIN")
	            

	            .anyRequest().authenticated()
	        )
	    
     .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);


	    return http.build();
	}
	
	   @Bean
	    public BCryptPasswordEncoder encoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Bean
	    public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
	        return config.getAuthenticationManager();
	    }
	    
	    
	 
}
