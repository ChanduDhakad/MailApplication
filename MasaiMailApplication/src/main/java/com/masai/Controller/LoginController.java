package com.masai.Controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Model.User;
import com.masai.Repository.UserDao;

@RestController
public class LoginController {
 
	@Autowired
	private UserDao userRepo;
	
	@GetMapping("/masaimail/login")
	public ResponseEntity<User> customeraLogin(Authentication auth){
		 User existingUser=null;
		
	
		   existingUser= userRepo.findByMobileNumber(auth.name());
	      
		 if(existingUser==null) {
			 throw new BadCredentialsException("Invalid Username or password");
		 }
		  
		 return new ResponseEntity<>(existingUser, HttpStatus.ACCEPTED);
		
		
	}
}
