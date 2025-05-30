package com.mrad.UniReport.entities.DTOS;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record OcorrenciaRequestDTO(
	    @NotBlank(message = "Bloco não pode ser vazio")
	    String bloco,

	    @NotBlank(message = "Sala não pode ser vazia")
	    String sala,

	    @NotBlank(message = "A descrição do problema não pode ser vazia")
	    @Size(min = 10, message = "A descrição do problema deve ter no mínimo 10 caracteres")
	    String problemas,

	    List<String> imagens
	) {}