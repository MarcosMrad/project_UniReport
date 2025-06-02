package com.mrad.UniReport.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrad.UniReport.entities.User;
import com.mrad.UniReport.repositories.UserRepository;
import com.mrad.UniReport.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado com o ID " + id));
	}

	
}
