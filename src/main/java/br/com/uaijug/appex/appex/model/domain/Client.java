package br.com.uaijug.appex.appex.model.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "client")
public class Client extends AudityEntity {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Size(min = 2, message = "Name should have atleast 2 characters")
	private String name;
	@NotNull
	@Email
	private String email;
	@NotNull
	@Size(min = 11, message = "cpf should have atleast 11 characters")
	private String cpf;

	@ManyToOne
	@JoinColumn(name = "client_type_id")
	private ClientType clientType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		return super.getId();
	}

	public void setId(Long id) {
		super.setId(id);
	}

	public ClientType getClientType() {
		return clientType;
	}

	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}

	public void updade(Long id, Client client) {
		super.setId(id);
		this.name = client.getName();
		this.email = client.getEmail();
		this.cpf = client.getCpf();
	}
}
