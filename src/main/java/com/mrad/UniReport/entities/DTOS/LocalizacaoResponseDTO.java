package com.mrad.UniReport.entities.DTOS;

import java.util.List;

public record LocalizacaoResponseDTO(
	    Long id,
	    String bloco,
	    String sala,
	    String qrCode,
	    List<OcorrenciaSummaryDTO> ocorrencias
	) {}
