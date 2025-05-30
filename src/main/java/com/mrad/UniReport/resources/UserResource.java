package com.mrad.UniReport.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mrad.UniReport.entities.User;
import com.mrad.UniReport.entities.DTOS.UserResponseDTO;
import com.mrad.UniReport.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	@Autowired
	private UserService service;
	
	private UserResponseDTO toUserResponseDTO(User user) {
	    return new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getRole());
	}
	
	@GetMapping
	public ResponseEntity<List<UserResponseDTO>> findAll (){
		List<User> list = service.findAll();
		List<UserResponseDTO> listDto = list.stream()
                .map(this::toUserResponseDTO)
                .collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
		
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id){
		User user = service.findById(id);
		UserResponseDTO userDTO = toUserResponseDTO(user);
		return ResponseEntity.ok().body(userDTO);
	}

	
}
