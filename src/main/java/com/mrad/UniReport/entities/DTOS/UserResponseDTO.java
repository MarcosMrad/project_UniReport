package com.mrad.UniReport.entities.DTOS;

import com.mrad.UniReport.entities.enums.UserRole;

public record UserResponseDTO(
	    Long id,
	    String name,
	    String email,
	    UserRole role
	) {}
