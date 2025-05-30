package com.mrad.UniReport.resources;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mrad.UniReport.entities.Localizacao;
import com.mrad.UniReport.entities.DTOS.LocalizacaoRequestDTO;
import com.mrad.UniReport.entities.DTOS.LocalizacaoResponseDTO;
import com.mrad.UniReport.entities.DTOS.OcorrenciaSummaryDTO;
import com.mrad.UniReport.services.LocalizacaoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/localizacao")
public class LocalizacaoResource {

	@Autowired
	private LocalizacaoService service;
	
	public LocalizacaoResponseDTO toResponseDTO(Localizacao localizacao) {
		List<OcorrenciaSummaryDTO> ocorrenciaSummaries;
		if (localizacao.getOcorrencias() != null && !localizacao.getOcorrencias().isEmpty()) {
			ocorrenciaSummaries = localizacao.getOcorrencias().stream()
				.map(ocorrencia -> new OcorrenciaSummaryDTO(ocorrencia.getId(),ocorrencia.getStatus()))
				.collect(Collectors.toList());
		} else {
			ocorrenciaSummaries = Collections.emptyList();
		}
		
		return new LocalizacaoResponseDTO(
            localizacao.getId(), 
            localizacao.getBloco(), 
            localizacao.getSala(), 
            localizacao.getQrCode(),
            ocorrenciaSummaries
        );
	}
	
	@GetMapping
	public ResponseEntity<List<LocalizacaoResponseDTO>> findAll(){
		List<Localizacao> list = service.findAll();
		List<LocalizacaoResponseDTO> listDTO = list.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
		
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<LocalizacaoResponseDTO> findById(@PathVariable Long id){
		Localizacao obj = service.findById(id);
		return ResponseEntity.ok().body(toResponseDTO(obj));
	}
	
	@PostMapping
	public ResponseEntity<LocalizacaoResponseDTO> insert(@Valid @RequestBody LocalizacaoRequestDTO dto){
		Localizacao localizacao = new Localizacao();
		localizacao.setBloco(dto.bloco());
		localizacao.setSala(dto.sala());
		localizacao.setQrCode(dto.qrCode());
		
		Localizacao novaLocalizacao = service.insert(localizacao);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novaLocalizacao.getId()).toUri();
        return ResponseEntity.created(uri).body(toResponseDTO(novaLocalizacao));
	}
	
}
