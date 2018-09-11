package br.com.uaijug.appex.appex.web.convert.from;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.uaijug.appex.appex.model.domain.User;
import br.com.uaijug.appex.appex.web.dto.UserDTO;

@Component
public class UserFromConverter implements Converter<UserDTO, User> {

	@Override
	public User convert(UserDTO source) {
		User target = new User();

		target.setId(source.getId());
		target.setEmail(source.getUsername());
		target.setPassword(source.getPassword());

		return target;
	}

}
