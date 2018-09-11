package br.com.uaijug.appex.appex.model.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, of = { "email", "password" })
@Data
public class User extends AudityEntity {

	private static final long serialVersionUID = -1162093762286562478L;

	@Getter
	@Setter
	private String email;

	@Getter
	@Setter
	private String password;

	/*@Getter
	@Setter
	@OneToMany(mappedBy = "user")
	private Set<UserRole> roles;*/

}