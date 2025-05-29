package com.mrad.UniReport.config;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mrad.UniReport.entities.Localizacao;
import com.mrad.UniReport.entities.Ocorrencia;
import com.mrad.UniReport.entities.User;
import com.mrad.UniReport.entities.enums.UserRole;
import com.mrad.UniReport.repositories.OcorrenciaRepository;
import com.mrad.UniReport.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private OcorrenciaRepository ocorrenciaRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		Localizacao l1 = new Localizacao(1L, "B", "304", "testqrcode/");
		List<String> imgs = new ArrayList<>();
		Ocorrencia o1 = new Ocorrencia(null , l1.getBloco(), l1.getSala(), "Lampada queimada", imgs, false, "a", Instant.parse("2019-06-20T19:53:07Z"), Instant.parse("2019-06-20T19:53:07Z"));
		
		ocorrenciaRepository.saveAll(Arrays.asList(o1));
		
		User u1 = new User(null, "Marcos Mrad", "marcos@mrad.com", new BCryptPasswordEncoder().encode("123456"), UserRole.ADMIN);
		User u2 = new User(null, "Rebeca ", "rebeca@mail.com", new BCryptPasswordEncoder().encode("123456"), UserRole.USER);
		
		userRepository.saveAll(Arrays.asList(u1,u2));
	
		
	}

}
