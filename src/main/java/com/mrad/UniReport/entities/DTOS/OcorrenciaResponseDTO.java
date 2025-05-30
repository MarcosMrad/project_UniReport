package com.mrad.UniReport.entities.DTOS;

import java.time.Instant;
import java.util.List;

public record OcorrenciaResponseDTO(
	    Long id,
	    String bloco,
	    String sala,
	    String problemas,
	    List<String> imagens,
	    Boolean status,
	    String resolucao,
	    Instant criadoEm,
	    Instant atualizadoEm,
	    UserSummaryDTO atualizadoPor
	) {}
	