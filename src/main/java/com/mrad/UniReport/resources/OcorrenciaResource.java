package com.mrad.UniReport.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mrad.UniReport.entities.Ocorrencia;
import com.mrad.UniReport.services.OcorrenciaService;

@RestController
@RequestMapping(value = "/ocorrencias")
public class OcorrenciaResource {
	
	@Autowired
	private OcorrenciaService service;
	
	@GetMapping
	public ResponseEntity<List<Ocorrencia>> findAll(){
		List<Ocorrencia> list = service.findAll();
		return ResponseEntity.ok().body(list);
		
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Ocorrencia> findById(@PathVariable Long id){
		Ocorrencia ocorrencia = service.findById(id);
		return ResponseEntity.ok().body(ocorrencia);
	}

	@PostMapping
	public ResponseEntity<Ocorrencia> insert(@RequestBody Ocorrencia obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	@PutMapping(value = "/{id}")
	public ResponseEntity<Ocorrencia> update(@PathVariable Long id, @RequestBody Ocorrencia obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);	}
}
