package com.mrad.UniReport.entities.DTOS;

import jakarta.validation.constraints.NotBlank;

public record LocalizacaoRequestDTO(
	    @NotBlank String bloco,
	    @NotBlank String sala
	) {}
