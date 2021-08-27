package com.nicolascruz.gmailtrello.config;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.nicolascruz.gmailtrello.domain.EmailVO;
import com.nicolascruz.gmailtrello.repository.EmailRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	
	@Autowired
	private EmailRepository emailRepository;

	@Override
	public void run(String... args) throws Exception {
		emailRepository.deleteAll();
		EmailVO email1 = new EmailVO("Relatório", "Loren ipsun dolor at", LocalDateTime.now());
		EmailVO email2 = new EmailVO("Ajuste", "Loren ipsun dolor at", LocalDateTime.now());
		EmailVO email3 = new EmailVO("Saída", "Loren ipsun dolor at", LocalDateTime.now());
		
		emailRepository.saveAll(Arrays.asList(email1, email2, email3));
		
	}

}
