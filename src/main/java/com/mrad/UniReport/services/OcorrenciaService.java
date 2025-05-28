package com.mrad.UniReport.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrad.UniReport.entities.Ocorrencia;
import com.mrad.UniReport.repositories.OcorrenciaRepository;
import com.mrad.UniReport.services.exceptions.ResourceNotFoundException;

@Service
public class OcorrenciaService {
	
	@Autowired
	private OcorrenciaRepository repository;
	
	public List<Ocorrencia> findAll(){
		return repository.findAll();
	}
	
	public Ocorrencia findById(Long id) {
		Optional<Ocorrencia> ocorrencia =  repository.findById(id);
		return ocorrencia.get();
	}
	
	public Ocorrencia insert(Ocorrencia obj) {
		return repository.save(obj);
	}
	
	public Ocorrencia update(Long id, Ocorrencia obj) {
		try {
			Ocorrencia entity = repository.getReferenceById(id);
			updateDate(entity, obj);
			return repository.save(entity);
			}
			catch(ResourceNotFoundException e) {
				throw new ResourceNotFoundException(id);
			}
	}
	
	private void updateDate(Ocorrencia entity, Ocorrencia obj) {
		entity.setStatus(obj.getStatus());
		entity.setResolucao(obj.getResolucao());
		entity.setAtualizadoEm(obj.getAtualizadoEm());
		
	}
	
}
