package br.com.uaijug.appex.appex.model.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotNull
	@Size(min = 6, message = "Name should have atleast 6 characters")
	private String nome;
	@NotNull
	@Email
	private String email;
	@NotNull
	@Size(min = 11, message = "Name should have atleast 11 characters")
	private String cpf;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void updade(Long id, Cliente cliente) {
		this.id = id;
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
		this.cpf = cliente.getCpf();
	}
}
