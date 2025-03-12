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
import com.mrad.UniReport.entities.Ocorrencia;
import com.mrad.UniReport.entities.User;
import com.mrad.UniReport.repositories.OcorrenciaRepository;
import com.mrad.UniReport.repositories.userRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private OcorrenciaRepository ocorrenciaRepository;
	
	@Autowired
	private userRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		Localizacao l1 = new Localizacao(1L, "B", "304", "testqrcode/");
		List<String> imgs = new ArrayList<>();
		Ocorrencia o1 = new Ocorrencia(null , l1.getBloco(), l1.getSala(), "Lampada queimada", imgs, false, "a", Instant.parse("2019-06-20T19:53:07Z"), Instant.parse("2019-06-20T19:53:07Z"));
		
		ocorrenciaRepository.saveAll(Arrays.asList(o1));
		
		User u1 = new User(null, "Marcos Mrad", "marcos@mrad.com", "123456");
		User u2 = new User(null, "Rebeca ", "rebeca@mail.com", "123456");
		
		userRepository.saveAll(Arrays.asList(u1));
	
		
	}

}
