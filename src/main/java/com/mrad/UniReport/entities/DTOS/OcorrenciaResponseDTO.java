package com.mrad.UniReport.entities.DTOS;

import java.time.Instant;
import java.util.List;

import com.mrad.UniReport.entities.Localizacao;

public record OcorrenciaResponseDTO(
	    Long id,
	    LocalizacaoSummaryDTO localizacao,
	    String problemas,
	    List<String> imagens,
	    Boolean status,
	    String resolucao,
	    Instant criadoEm,
	    Instant atualizadoEm,
	    UserSummaryDTO atualizadoPor
	) {}
	