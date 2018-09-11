package br.com.uaijug.appex.appex.model.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uaijug.appex.appex.model.domain.Roles;
import br.com.uaijug.appex.appex.model.domain.User;
import br.com.uaijug.appex.appex.model.domain.UserRole;
import br.com.uaijug.appex.appex.model.dto.UserTO;
import br.com.uaijug.appex.appex.model.repository.UserRepository;
import br.com.uaijug.appex.appex.model.service.PasswordCrypto;
import br.com.uaijug.appex.appex.model.service.UserService;
import br.com.uaijug.appex.appex.web.dto.UserDTO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private PasswordCrypto passwordCrypto;

	@Autowired
	private UserRepository userRepository;

	@Override
	public User createUser(UserDTO dto) {
		User user = new User();
		Set<UserRole> roles = new HashSet<>();

		user.setEmail(dto.getUsername());
		user.setPassword(passwordCrypto.encrypt(dto.getPassword()));

		// create a new user with basic user privileges
		roles.add(new UserRole(Roles.USER.toString(), user));
		roles.add(new UserRole(Roles.ADMIN.toString(), user));

		// user.setRoles(roles);

		return user;
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		return userRepository.findByEmailAndPassword(username, password);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByEmail(username);
	}

	@Override
	public String validatePasswordResetToken(User user, String token) {
		return null;
	}

	@Override
	public String changeUserPassword(User user, String token) {
		return null;
	}

	@Override
	public boolean register(UserTO userTO) {
		return false;
	}

}
