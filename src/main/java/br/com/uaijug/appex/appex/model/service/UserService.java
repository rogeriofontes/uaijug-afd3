package br.com.uaijug.appex.appex.model.service;

import br.com.uaijug.appex.appex.model.domain.User;
import br.com.uaijug.appex.appex.model.dto.UserTO;
import br.com.uaijug.appex.appex.web.dto.UserDTO;

public interface UserService {
	User createUser(UserDTO dto);

	String validatePasswordResetToken(User user, String token);

	String changeUserPassword(User user, String token);

	boolean register(UserTO userTO);

	User findByUsernameAndPassword(String username, String password);
	
	User findByUsername(String username);
}
