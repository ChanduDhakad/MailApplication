package com.masai.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.Model.Emails;


@Repository
public interface EmailDao extends JpaRepository<Emails, Integer> {

	 

}
