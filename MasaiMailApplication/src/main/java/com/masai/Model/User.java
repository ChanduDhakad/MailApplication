package com.masai.Model;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 private Integer UserId;
	
	@NotNull(message = "Email  cannot set as null")
	@NotEmpty(message = "Email cannot set as empty")
	@NotBlank(message = "Email cannot set as blank")
	@Email(message = "email format is incorrect")
	@Column(unique = true)
	private String email;
	
	
	@NotNull(message = "first name  cannot set as null")
	@NotEmpty(message = "first name cannot set as empty")
	@NotBlank(message = "first name cannot set as blank")
	@Pattern(regexp = "[A-Za-z]")
	private String firstName;
	
	@NotNull(message = "last name cannot set as null")
	@NotEmpty(message = "last name cannot set as empty")
	@NotBlank(message = "last name cannot set as blank")
	@Pattern(regexp = "[A-Za-z]")
	private String lastName;
	
	@NotNull(message = "mobileNumber cannot set as null")
	@Pattern(regexp = "^[789]\\d{9}$")
	@Column(unique = true)
	private String mobileNumber;
	
	@Past(message = "Data Of Birth Should Be Past")
	private LocalDate dateOfBirth;
	
	
    @NotNull(message = "password cannot set as null")
	@NotEmpty(message = "password cannot set as empty")
	@NotBlank(message = "password cannot set as blank")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\\\S+$).{6, 12}$")
	private String password;	
	  

	@JsonIgnore
	@OneToMany
	private List<Emails> listOfinboxMail=new ArrayList<>();
	
	@JsonIgnore
	@OneToMany
	private List<Emails> listOfsentMail=new ArrayList<>();

	    
	
	
		

		
		
	
}
