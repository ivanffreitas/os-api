package com.ivanilson.os.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import com.ivanilson.os.domain.Tecnico;

public class TecnicoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//Padrão DTO
	//comente o que você não gostaria de exibir para o usuario
	//exeplo o CPF, senhas, ID.... lembrando que neste projeto está tudo ativo
	
	private Integer id;
	@NotEmpty(message = "O campo NOME é requerido")
	private String nome;
	
	@CPF
	@NotEmpty(message = "O campo CPF é requerido")
	private String cpf;
	
	@NotEmpty(message = "O campo TELEFONE é requerido")
	private String telefone;
	
	public TecnicoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TecnicoDTO(Tecnico obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.telefone = obj.getTelefone();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
}
