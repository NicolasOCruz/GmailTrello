package com.nicolascruz.gmailtrello.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicolascruz.gmailtrello.domain.EmailVO;
import com.nicolascruz.gmailtrello.repository.EmailRepository;

@Service
public class EmailService {
	
	@Autowired
	private EmailRepository emailRepository;
	
	public List<EmailVO> findAll(){
		return emailRepository.findAll();
	}

}
