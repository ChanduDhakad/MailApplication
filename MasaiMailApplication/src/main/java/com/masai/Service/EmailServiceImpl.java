package com.masai.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.EmailException;
import com.masai.Exception.UserExcepion;
import com.masai.Model.EmailDetails;
import com.masai.Model.Emails;
import com.masai.Model.User;
import com.masai.Repository.EmailDao;
import com.masai.Repository.UserDao;

@Service()
public class EmailServiceImpl implements EmailService{

	
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private EmailDao emailDao;
	
	
	@Override
	public Emails sendEmail(String senderEmail,EmailDetails emailDetails) throws EmailException, UserExcepion {
	 
		User isUserExist=userDao.findByEmail(senderEmail);
		
		if(isUserExist!=null) {
	      Emails emails=new Emails();
	      emails.setDescription(emailDetails.getBody());
	      emails.setSenderUser(isUserExist);
	      emails.setLocalDateTime(LocalDateTime.now());
		  emails.setStarred(null);
		
		
		  for(String recive:emailDetails.getListOfSendToEmails()) {
			 
			  User userReciver=userDao.findByEmail(recive);
			  if(userReciver!=null) {
				  emails.getListOfReciverUser().add(userReciver);
				  userReciver.getListOfinboxMail().add(emails);
				   
				  userDao.save(userReciver);
			  }
		  }
		  
		 Emails emails2=emailDao.save(emails);
		 isUserExist.getListOfsentMail().add(emails2); 
		 userDao.save(isUserExist);
		 return emails2;
		}
		else {
			throw new  UserExcepion("User Is Not Prsent with Given Email Address "+senderEmail);
		}
		
	
	}

	@Override
	public Emails starredEmail(String email, Integer emailId) throws EmailException, UserExcepion {
		
	User isUserExist=userDao.findByEmail(email);
	    
		if(isUserExist==null) {
		  throw new  UserExcepion("User Is Not Found In tHe Database");
			
		}
		else {
			
			Optional<Emails> opt=emailDao.findById(emailId);
			if(opt.isPresent()) {
			 Emails emailOpt=opt.get();	
			
			 List<Emails> InboxemailsList=isUserExist.getListOfinboxMail();
		     List<Emails> SentMailList=isUserExist.getListOfsentMail();
			 
		     boolean b=false;
		     Emails emailsIdObj=null; 
		     for(Emails inboxEmail:InboxemailsList) {
				 
		    	 if(emailId==inboxEmail.getEmailId()) {
		    		 inboxEmail.setStarred(true);
		    		 emailsIdObj=inboxEmail;
		    		 b=true;
		    	 }
		    	 
			 }
			
		     for(Emails sentEmail:SentMailList) {
				 
		    	 if(emailId==sentEmail.getEmailId()) {
		    		 sentEmail.setStarred(true);
		    		 emailsIdObj=sentEmail;
		    	     b=true;
		    	 }
		     }
		     if(b==false) {
		    	 throw new EmailException("This Email ID Is Not Present IN Inbox and Sent Box");
		     }
		     else {
		    	 isUserExist.setListOfsentMail(SentMailList);
		    	 isUserExist.setListOfinboxMail(InboxemailsList);
		         userDao.save(isUserExist);
		         emailDao.save(emailsIdObj); 
		        return emailsIdObj;
		     }
			}
			else {
				throw new EmailException("Email is not found");
			 
			}
			
			
			
			
		}
	}

	@Override
	public String deleteEmail(String email, Integer emailId) throws EmailException, UserExcepion {
	String result="mail  Is Not Deleted yet";
		
		User isUserExist=userDao.findByEmail(email);
	    
		if(isUserExist==null) {
		  throw new  UserExcepion("User Is Not Found in the  Database");
			
		}
		else {
			
			List<Emails>  emails=isUserExist.getListOfinboxMail();
			
			Optional<Emails> opt=emailDao.findById(emailId);
  			
			if(opt.isPresent()) {
		       List<Emails> listOfEmail=new ArrayList<>();
				
				for(Emails e:emails) {
				 	if(e.getEmailId()!=emailId) {
				 		listOfEmail.add(e);
				 	}
				}

				 Emails emailObj=opt.get();
				 
				 isUserExist.setListOfinboxMail(emails);
				 userDao.save(isUserExist);
				 emailDao.delete(emailObj);
				 
				 result="mail Is Deleted Successfully";
			}
			else {
				 throw new  EmailException("Email Is Not Found in The DataBase");
				
			}
			
		}
		
		return result;
	}



}
