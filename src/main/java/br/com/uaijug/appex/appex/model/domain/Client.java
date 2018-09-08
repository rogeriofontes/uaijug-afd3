package br.com.uaijug.appex.appex.model.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "client")
public class Client extends AudityEntity {
	private static final long serialVersionUID = 8401721385730134860L;

	@NotNull
	@Size(min = 2, message = "Name should have atleast 2 characters")
	private String name;
	@NotNull
	@Email
	private String email;
	@NotNull
	@Size(min = 11, message = "Address should have atleast 11 characters")
	private String address;
	@ManyToOne
	@JoinColumn(name = "client_type_id")
	private ClientType clientType;
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@Column(name = "birth_date")
	private LocalDate birthDate;
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "person_type")
	private PersonType personType;
	private String phone;
	@NotNull
	private String mobile;
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "document_region")
	private DocumentRegion documentRegion;
	@NotNull
	@Column(name = "document_id")
	//@Size(min = 1, message = "cpf should have atleast 11 characters")
	private Long documentId;
	@NotNull
	//@Size(min = 11, max = 11, message = "cpf should have atleast 11 characters")
	@Column(name = "social_id")
	private Long socialId;
	@NotNull
	private Sex sex;
	private String nationality;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public PersonType getPersonType() {
		return personType;
	}

	public void setPersonType(PersonType personType) {
		this.personType = personType;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public DocumentRegion getDocumentRegion() {
		return documentRegion;
	}

	public void setDocumentRegion(DocumentRegion documentRegion) {
		this.documentRegion = documentRegion;
	}

	public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	public Long getSocialId() {
		return socialId;
	}

	public void setSocialId(Long socialId) {
		this.socialId = socialId;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public void updade(Long id, Client client) {
		super.setId(id);
		this.name = client.getName();
		this.email = client.getEmail();
		this.clientType = client.getClientType();
		this.address = client.getAddress();
		this.birthDate = client.getBirthDate();
		this.personType = client.getPersonType();
		this.documentRegion = client.getDocumentRegion();
		this.documentId = client.getDocumentId();
		this.socialId = client.getSocialId();
		this.sex = client.getSex();
		this.socialId = client.getSocialId();
		this.nationality = client.getNationality();
	}
}
