package com.masai.Service;

import java.util.List;

import javax.validation.constraints.Email;



import com.masai.Exception.EmailException;
import com.masai.Exception.UserExcepion;
import com.masai.Model.Emails;
import com.masai.Model.User;


public interface UserService {

	public User regsitorUser(User user) throws UserExcepion;
 
	
	public User updateUser(User user) throws UserExcepion;
	
	public List<Emails> getAllEmail(String email) throws EmailException,UserExcepion;
	
	public List<Emails> getAllstarredEmail(String email) throws UserExcepion,EmailException;
	
	

}
