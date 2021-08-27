package com.nicolascruz.gmailtrello.domain;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection="email")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class EmailVO extends AbstractEntity<String>{

	private static final long serialVersionUID = 1L;
	
	private String titulo;
	private String descricao;
	private LocalDateTime date;

}
