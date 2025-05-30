package com.mrad.UniReport.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrad.UniReport.entities.Localizacao;
import com.mrad.UniReport.repositories.LocalizacaoRepository;

@Service
public class LocalizacaoService {
	@Autowired
	private LocalizacaoRepository repository;
	
	
	public List<Localizacao> findAll(){
		return repository.findAll();
	}
	
	public Localizacao findById(Long id) {
		Optional<Localizacao> obj = repository.findById(id);
		return obj.get();
	}
	
	public Localizacao insert(Localizacao obj) {
		return repository.save(obj);
	}
	
	
}
