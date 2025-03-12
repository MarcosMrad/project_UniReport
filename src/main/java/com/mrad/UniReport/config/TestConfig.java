package com.mrad.UniReport.config;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.mrad.UniReport.entities.Localizacao;
import com.mrad.UniReport.entities.Ocorrencias;
import com.mrad.UniReport.repositories.OcorrenciaRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private OcorrenciaRepository ocorrenciaRepository;

	@Override
	public void run(String... args) throws Exception {
		Localizacao l1 = new Localizacao(1L, "B", "304", "testqrcode/");
		List<String> imgs = new ArrayList<>();
		Ocorrencias o1 = new Ocorrencias(null , l1.getBloco(), l1.getSala(), "Lampada queimada", imgs, false, "a", Instant.parse("2019-06-20T19:53:07Z"), Instant.parse("2019-06-20T19:53:07Z"));
		
		ocorrenciaRepository.saveAll(Arrays.asList(o1));
		
	}

}
