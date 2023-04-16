package com.masai.Model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetails {

	private String email;
	private String body;
	private List<String> listOfSendToEmails=new ArrayList<>();
	
}
