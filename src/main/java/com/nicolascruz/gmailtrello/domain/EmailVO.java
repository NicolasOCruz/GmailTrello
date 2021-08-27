package com.nicolascruz.gmailtrello.domain;

public class EmailVO extends AbstractEntity<String>{

	private static final long serialVersionUID = 1L;
	
	private String titulo;
	private String descricao;
	
	public EmailVO() {
		
	}

	public EmailVO(String titulo, String descricao) {
		super();
		this.titulo = titulo;
		this.descricao = descricao;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}