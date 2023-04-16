package com.masai.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer emailId;
	
	private String email;
	
	private String description;
	
	private LocalDateTime localDateTime;
	
	private Boolean starred;


	@OneToOne(cascade = CascadeType.ALL)
	private User senderUser;
	
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<User>  listOfReciverUser=new ArrayList<>();
    	
 
 
}
