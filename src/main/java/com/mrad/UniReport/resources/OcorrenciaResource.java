package com.mrad.UniReport.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mrad.UniReport.entities.Ocorrencias;
import com.mrad.UniReport.services.OcorrenciaService;

@RestController
@RequestMapping(value = "/ocorrencias")
public class OcorrenciaResource {
	
	@Autowired
	private OcorrenciaService service;
	
	@GetMapping
	public ResponseEntity<List<Ocorrencias>> findAll(){
		List<Ocorrencias> list = service.findAll();
		return ResponseEntity.ok().body(list);
		
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Ocorrencias> findById(@PathVariable Long id){
		Ocorrencias ocorrencia = service.findById(id);
		return ResponseEntity.ok().body(ocorrencia);
	}

}
