package com.mrad.UniReport.entities.DTOS;
import jakarta.validation.constraints.NotNull;

public record OcorrenciaUpdateDTO(
    @NotNull(message = "Status não pode ser nulo")
    Boolean status,
    String resolucao
) {}
