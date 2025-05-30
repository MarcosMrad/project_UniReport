package com.mrad.UniReport.entities.DTOS;

import com.mrad.UniReport.entities.enums.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserCreateDTO(
	    @NotBlank String name,
	    @NotBlank @Email String email,
	    @NotBlank String password,
	    @NotNull UserRole role
	) {}
