package com.nicolascruz.gmailtrello.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nicolascruz.gmailtrello.domain.EmailVO;

@RestController
@RequestMapping("/emails")
public class EmailResource {
	
	@GetMapping
	public ResponseEntity<List<EmailVO>> findAll(){
		EmailVO email = new EmailVO("Teste 1", "To testando");
		EmailVO email2 = new EmailVO("Teste 2", "To testando 2");
		List<EmailVO> lista = new ArrayList<>();
		lista.addAll(Arrays.asList(email, email2));
		return ResponseEntity.ok(lista);
	}

}
