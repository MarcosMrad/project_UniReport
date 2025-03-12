package com.mrad.UniReport.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mrad.UniReport.entities.User;

public interface userRepository extends JpaRepository<User, Long>{

	
}
