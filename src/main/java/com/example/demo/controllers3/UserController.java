
package com.example.demo.controllers3;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.daos.JwtService;
import com.example.demo.entities.AuthRes;
import com.example.demo.entities.OtpRequest;
import com.example.demo.entities.OtpUtil;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.helpers.HelperC;
import com.example.demo.services.Emailser;
import com.example.demo.services.Userser;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true")
public class UserController {	
	
	 @Autowired
	 private Userser seru;
	
	 @Autowired
	 Emailser ser;
	
	 @Autowired
	 private AuthenticationManager authManager;

	 @Autowired
	 private JwtService jwtService;

	 @Autowired
	 private PasswordEncoder encoder;
	
	@PostMapping("/saveu")
	public ResponseEntity<?> saveUser(@RequestBody User u) {	
		User user = seru.findUser(u.getUsername());
		System.out.println(user);
		    
		    String otp = OtpUtil.generateOtp();
		    u.setOtp(otp);
		    u.setOtpExpiry(LocalDateTime.now().plusMinutes(5));
		    u.setVerified(false);
		
		    if(user!=null) {
			if(user.isVerified()) {
			return 
					ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "Email already exists"));
			}
			 user.setOtp(otp);
		        user.setOtpExpiry(LocalDateTime.now().plusMinutes(20));
		        seru.saveUs(user);
		        u = user;
		}
		else {
			u.setPassword(encoder.encode(u.getPassword()));
		   if (u.getRole() == null) {
	            u.setRole(Role.ROLE_USER);
	        }
		   seru.saveUs(u);
		}
		String s= "Email Verification";
		ser.sendEmail(u.getUsername(),s,u.getOtp());
		
		HelperC h = new HelperC();
		h.setMessage("User register successfully");
		h.setData(u);
		return new ResponseEntity<>(h,HttpStatus.OK);
	}
	
	@PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody OtpRequest request) {
        System.out.println(request.getOtp());
        System.out.println(request.getUsername());
		seru.verifyOtp(request);
        return ResponseEntity.ok("Account verified successfully");
    }
	
	@PostMapping("/login")
	public AuthRes userlogin(@RequestBody User u) {
   	 authManager.authenticate(
        new UsernamePasswordAuthenticationToken(
                u.getUsername(),
                u.getPassword()
            )
        );
   	   
     User dbUser = seru.findUser(u.getUsername());

     String token = jwtService.generateToken(dbUser.getUsername(), dbUser.getRole());

     return new AuthRes(token, dbUser);

   
	}
	
	
	@PostMapping("/gettoken")
	public String getTok(@RequestBody User u) {
		String t = seru.getToken1(u);
		return t;
	}
   
	  @GetMapping("/logout")
	    public ResponseEntity<String> logout(HttpServletRequest request) {
	        request.getSession().invalidate();
	        return ResponseEntity.ok("Logged out.");
	    }

}
