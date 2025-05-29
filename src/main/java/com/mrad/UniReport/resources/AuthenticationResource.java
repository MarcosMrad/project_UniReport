package com.mrad.UniReport.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mrad.UniReport.entities.User;
import com.mrad.UniReport.entities.DTOS.AuthenticationDTO;
import com.mrad.UniReport.entities.DTOS.RegisterDTO;
import com.mrad.UniReport.repositories.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationResource {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository repository;
	
	
	@PostMapping(value = "/login")
	public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
		var auth = this.authenticationManager.authenticate(usernamePassword);
		
		return ResponseEntity.ok().build();
	}
	
	@PostMapping(value = "/register")
	public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
		if(this.repository.findByEmail(data.email()) != null) {
			return ResponseEntity.badRequest().build();
		}
		else {
			String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
			User newUser = new User(null, data.name(), data.email(), encryptedPassword, data.role());
			
			this.repository.save(newUser);
			
			return ResponseEntity.ok().build();
		}
	}
	
}
