package br.com.uaijug.appex.appex.web.convert.to;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.uaijug.appex.appex.model.domain.User;
import br.com.uaijug.appex.appex.web.dto.UserDTO;

@Component
public class UserToConverter implements Converter<User, UserDTO> {

	@Override
	public UserDTO convert(User source) {
		UserDTO target = new UserDTO();

		target.setId(source.getId());
		target.setUsername(source.getEmail());
		target.setPassword(source.getPassword());

		return target;
	}

}
