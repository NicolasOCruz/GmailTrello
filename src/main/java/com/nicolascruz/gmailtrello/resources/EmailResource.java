package com.nicolascruz.gmailtrello.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nicolascruz.gmailtrello.domain.EmailVO;
import com.nicolascruz.gmailtrello.services.EmailService;

@RestController
@RequestMapping("/emails")
public class EmailResource {
	
	@Autowired
	private EmailService emailService;
	
	@GetMapping
	public ResponseEntity<List<EmailVO>> findAll(){
		List<EmailVO> lista = emailService.findAll();
		System.out.println(lista.size());
		return ResponseEntity.ok(lista);
	}

}
