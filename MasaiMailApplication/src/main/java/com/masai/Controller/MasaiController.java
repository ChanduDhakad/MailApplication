package com.masai.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exception.EmailException;
import com.masai.Exception.UserExcepion;
import com.masai.Model.EmailDetails;
import com.masai.Model.Emails;
import com.masai.Model.User;
import com.masai.Service.EmailService;
import com.masai.Service.UserService;

@RestController
@RequestMapping("/masaimail")
public class MasaiController {

	@Autowired 
	private UserService userService;
	
	
	

	@Autowired 
	private EmailService emailService;
	
   
	@PostMapping("/register")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) throws UserExcepion{
		
		User  user2=userService.regsitorUser(user);
		
		return new ResponseEntity<User>(user2,HttpStatus.CREATED);
	}
 
	@PutMapping("/user")
	public  ResponseEntity<User>  makeChangesInUser(@Valid @RequestBody  User user) throws UserExcepion{

		User  user2=userService.updateUser(user);
		
		return new ResponseEntity<User>(user2,HttpStatus.OK);
	}
	
	
	@GetMapping("/mail")
	public ResponseEntity<List<Emails>> getAllMail(@RequestParam("email") String email) throws EmailException,UserExcepion{
		
	
                 List<Emails> ListEmail=userService.getAllEmail(email);
		return  new ResponseEntity<List<Emails>>(ListEmail,HttpStatus.OK);
	
	
	}
	
	
	@GetMapping("/starred")
	public ResponseEntity<List<Emails>>  getAllstar(@RequestParam("email") String email) throws UserExcepion,EmailException{
		
		

        List<Emails> ListEmail=userService.getAllstarredEmail(email);
return  new ResponseEntity<List<Emails>>(ListEmail,HttpStatus.OK);
	}
	
	

	
	
	@PostMapping("/mail/{senderEmail}")
	public ResponseEntity<Emails> sendMail(@PathVariable("senderEmail")  String senderEmail,@Valid @RequestBody  EmailDetails emailDetails) throws EmailException,UserExcepion{
		
		Emails emails=emailService.sendEmail(senderEmail, emailDetails);
		 
		return new ResponseEntity<Emails>(emails,HttpStatus.OK);
	}
	
	
	
	@PostMapping("/starred/{userEmail}/{mailId}")
	public ResponseEntity<Emails> starMail(@PathVariable("userEmail") String email,@PathVariable("mailId")  Integer mailId) throws EmailException,UserExcepion{
	
		Emails emails=emailService.starredEmail(email, mailId);
		 
		return new ResponseEntity<Emails>(emails,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/delete/{userEmail}/{mailId}")
	public  ResponseEntity<String> deleteMail(String email,Integer mailId) throws EmailException,UserExcepion{
		
		String str=emailService.deleteEmail(email, mailId);
		 
		return new ResponseEntity<String>(str,HttpStatus.OK);
		
	}
	

	
	

}
