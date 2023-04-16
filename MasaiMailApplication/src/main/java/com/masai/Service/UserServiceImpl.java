package com.masai.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Email;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.EmailException;
import com.masai.Exception.UserExcepion;
import com.masai.Model.Emails;
import com.masai.Model.User;
import com.masai.Repository.EmailDao;
import com.masai.Repository.UserDao;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private EmailDao emailDao;

	
	
	
	
	
	
	
	
	

	@Override
	public User regsitorUser(User user) throws UserExcepion {
		
		User isUserExit = userDao.findByMobileNumber(user.getMobileNumber());

		if(isUserExit!=null) {
			userDao.save(user);
			return user;
			
		}else throw new UserExcepion("User is Already Exist with  Mobile number "+user.getMobileNumber());
	
	}
	
	
	
	
	

	@Override
	public User updateUser(User user) throws UserExcepion  {
   
		User isUserExit=userDao.findByEmail(user.getEmail());
		
	  if(isUserExit!=null) {
		return  userDao.save(user);
	 
	  }
	  else {
		  throw new UserExcepion("User Is not present with Given Email Id");
	  }
		 
		
	}


	@Override
	public List<Emails> getAllEmail(String email) throws EmailException, UserExcepion {
		List<Emails> emails=null;
		User isUserExit=userDao.findByEmail(email);
		
		if(isUserExit!=null) {
		
		if(isUserExit.getListOfinboxMail().size()>0) {
			
			emails=isUserExit.getListOfinboxMail();
			
		}
		else {
			
			throw new EmailException("User Not Recive Any EMail");
		}
		}
		else {
			
			throw new UserExcepion("User Not available by given EmailID "+email);
		}
		return emails;
	}


	@Override
	public List<Emails> getAllstarredEmail(String email) throws UserExcepion, EmailException {
		List<Emails> emails=null;
		User isUserExit=userDao.findByEmail(email);
		
		if(isUserExit!=null) {
		
		if(isUserExit.getListOfinboxMail().size()>0 || isUserExit.getListOfsentMail().size()>0) {
			
		
		for(Emails emailObj:isUserExit.getListOfsentMail()) {
			if(emailObj.getStarred()==true) {
				emails.add(emailObj);
			}
		}
			
		
	     for(Emails emailObj:isUserExit.getListOfinboxMail()) {
			if(emailObj.getStarred()==true) {
				emails.add(emailObj);
			}
		}	
		}
		else {	
			throw new EmailException("User Not Recive Any Email and Send any Email");
		}
		}
		else {
			
			throw new UserExcepion("User Not available by given EMail");
		}
		return emails;
		 
		 
		 
		 
	}






	
	
	

}
