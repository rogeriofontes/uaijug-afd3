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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "client")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, of = { "name", "email", "address", "clientType", "birthDate", "personType", "phone",
		"mobile", "documentRegion", "documentId", "socialId", "sex", "nationality" })
@Data
public class Client extends AudityEntity {
	private static final long serialVersionUID = 8401721385730134860L;

	@NotNull
	@Size(min = 2, message = "Name should have atleast 2 characters")
	@Getter
	@Setter
	private String name;
	
	@NotNull
	@Email
	@Getter
	@Setter
	private String email;
	
	@NotNull
	@Size(min = 11, message = "Address should have atleast 11 characters")
	@Getter
	@Setter
	private String address;
	
	@ManyToOne
	@JoinColumn(name = "client_type_id")
	@Getter
	@Setter
	private ClientType clientType;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@Column(name = "birth_date")
	@Getter
	@Setter
	private LocalDate birthDate;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "person_type")
	@Getter
	@Setter
	private PersonType personType;
	
	@Getter
	@Setter
	private String phone;
	
	@NotNull
	@Getter
	@Setter
	private String mobile;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "document_region")
	@Getter
	@Setter
	private DocumentRegion documentRegion;
	
	@NotNull
	@Column(name = "document_id")
	// @Size(min = 1, message = "cpf should have atleast 11 characters")
	@Getter
	@Setter
	private Long documentId;
	
	@NotNull
	// @Size(min = 11, max = 11, message = "cpf should have atleast 11 characters")
	@Column(name = "social_id")
	@Getter
	@Setter
	private Long socialId;
	
	@NotNull
	@Getter
	@Setter
	private Sex sex;
	
	@Getter
	@Setter
	private String nationality;

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
