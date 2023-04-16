package com.masai.Service;

import com.masai.Exception.EmailException;
import com.masai.Exception.UserExcepion;
import com.masai.Model.EmailDetails;
import com.masai.Model.Emails;

public interface EmailService {

	public Emails sendEmail(String senderEmail,EmailDetails emailDetails) throws EmailException,UserExcepion;
	
	public Emails starredEmail(String email,Integer emailId) throws EmailException,UserExcepion;

	public String deleteEmail(String email,Integer emailId) throws EmailException,UserExcepion;
	

}
