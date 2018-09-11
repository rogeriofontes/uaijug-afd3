package br.com.uaijug.appex.appex.web.support;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.uaijug.appex.appex.model.domain.User;
import br.com.uaijug.appex.appex.model.repository.UserRepository;
import br.com.uaijug.appex.appex.web.dto.UserDTO;

@Component
public class UserSupport {

	private static final Logger logger = LogManager.getLogger(UserSupport.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ConversionService userConvert;

	public UserDTO findUserByUsername(@PathVariable("username") String username) {
		User user = userRepository.findByEmail(username);
		UserDTO result = userConvert.convert(user, UserDTO.class);
		logger.info("User: " + user.toString());
		return result;
	}

	public List<UserDTO> list() {
		List<UserDTO> dtos = new ArrayList<>();
		userRepository.findAll().forEach(user -> {
			UserDTO result = userConvert.convert(user, UserDTO.class);
			dtos.add(result);
		});
		return dtos;
	}
}
