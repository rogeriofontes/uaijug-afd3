package br.com.uaijug.appex.appex.web.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString(callSuper = true, of = { "username", "password" })
@Data
public class UserDTO implements Serializable {

	private static final long serialVersionUID = 6280539273496622196L;

	@Getter
	@Setter
	private Long id;
	
	@Getter
	@Setter
	private String username;

	@Getter
	@Setter
	private String password;

}