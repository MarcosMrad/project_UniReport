package com.mrad.UniReport.resources;

import java.net.URI;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mrad.UniReport.entities.Ocorrencia;
import com.mrad.UniReport.entities.User;
import com.mrad.UniReport.entities.DTOS.OcorrenciaRequestDTO;
import com.mrad.UniReport.entities.DTOS.OcorrenciaResponseDTO;
import com.mrad.UniReport.entities.DTOS.OcorrenciaUpdateDTO;
import com.mrad.UniReport.entities.DTOS.UserSummaryDTO;
import com.mrad.UniReport.services.OcorrenciaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/ocorrencias")
public class OcorrenciaResource {
	
	@Autowired
	private OcorrenciaService service;
	
	private OcorrenciaResponseDTO toResponseDTO(Ocorrencia ocorrencia) {
        UserSummaryDTO userSummary = null;
        if (ocorrencia.getAtualizadoPor() != null) {
            userSummary = new UserSummaryDTO(
                ocorrencia.getAtualizadoPor().getId(),
                ocorrencia.getAtualizadoPor().getName()
            );
        }
        return new OcorrenciaResponseDTO(
            ocorrencia.getId(),
            ocorrencia.getBloco(),
            ocorrencia.getSala(),
            ocorrencia.getProblemas(),
            ocorrencia.getImagens(),
            ocorrencia.getStatus(),
            ocorrencia.getResolucao(),
            ocorrencia.getCriadoEm(),
            ocorrencia.getAtualizadoEm(),
            userSummary
        );
    }
	
	
	@GetMapping
	public ResponseEntity<List<OcorrenciaResponseDTO>> findAll(){
		List<Ocorrencia> list = service.findAll();
		List<OcorrenciaResponseDTO> listDto = list.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
		
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<OcorrenciaResponseDTO> findById(@PathVariable Long id){
		Ocorrencia ocorrencia = service.findById(id);
        return ResponseEntity.ok().body(toResponseDTO(ocorrencia));
	}

	@PostMapping
	public ResponseEntity<OcorrenciaResponseDTO> insert(@Valid @RequestBody OcorrenciaRequestDTO dto) {
        Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setBloco(dto.bloco());
        ocorrencia.setSala(dto.sala());
        ocorrencia.setProblemas(dto.problemas());
        ocorrencia.setImagens(dto.imagens() != null ? dto.imagens() : new ArrayList<>());
        ocorrencia.setStatus(false); 
        ocorrencia.setCriadoEm(Instant.now()); 
        ocorrencia.setAtualizadoEm(Instant.now());

        Ocorrencia novaOcorrencia = service.insert(ocorrencia);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novaOcorrencia.getId()).toUri();
        return ResponseEntity.created(uri).body(toResponseDTO(novaOcorrencia));
    }
	

	@PutMapping(value = "/{id}")
	public ResponseEntity<OcorrenciaResponseDTO> update(@PathVariable Long id, @Valid @RequestBody OcorrenciaUpdateDTO dto,  @AuthenticationPrincipal User user) {
		Ocorrencia ocorrenciaAtualizada = service.update(id, dto, user);
		return ResponseEntity.ok().body(toResponseDTO(ocorrenciaAtualizada));
}
}
