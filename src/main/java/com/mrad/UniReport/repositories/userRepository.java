package com.mrad.UniReport.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.mrad.UniReport.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	UserDetails findByEmail(String email);
	
}
