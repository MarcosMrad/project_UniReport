package com.mrad.UniReport.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrad.UniReport.entities.Ocorrencias;
import com.mrad.UniReport.repositories.OcorrenciaRepository;

@Service
public class OcorrenciaService {
	
	@Autowired
	private OcorrenciaRepository repository;
	
	public List<Ocorrencias> findAll(){
		return repository.findAll();
	}
	
	public Ocorrencias findById(Long id) {
		Optional<Ocorrencias> ocorrencia =  repository.findById(id);
		return ocorrencia.get();
	}

}
