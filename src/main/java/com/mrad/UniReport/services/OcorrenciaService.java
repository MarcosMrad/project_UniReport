package com.mrad.UniReport.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrad.UniReport.entities.Ocorrencia;
import com.mrad.UniReport.entities.User;
import com.mrad.UniReport.entities.DTOS.OcorrenciaResponseDTO;
import com.mrad.UniReport.entities.DTOS.OcorrenciaUpdateDTO;
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
		return repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Ocorrencia não encontrada com o ID " + id));
	}
	
	public Ocorrencia insert(Ocorrencia obj) {
		return repository.save(obj);
	}
	
	public Ocorrencia update(Long id, OcorrenciaUpdateDTO dto, User user) {
		try {
			Ocorrencia entity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Ocorrencia não encontrada com o ID: " + id));
			updateDate(entity, dto, user);
			return repository.save(entity);
			}
			catch(ResourceNotFoundException e) {
				throw new ResourceNotFoundException(id);
			}
	}
	
	private void updateDate(Ocorrencia entity, OcorrenciaUpdateDTO dto, User user) {
		entity.setStatus(dto.status());
		entity.setResolucao(dto.resolucao());
		entity.setAtualizadoEm(Instant.now());
		entity.setAtualizadoPor(user);
	}
	
	public List<Ocorrencia> findByBloco(String bloco){
		return repository.findByBloco(bloco);
	}
	
}
