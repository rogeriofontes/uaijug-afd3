package br.com.uaijug.appex.appex.web.support;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import br.com.uaijug.appex.appex.exception.UserNotFoundException;
import br.com.uaijug.appex.appex.model.domain.User;
import br.com.uaijug.appex.appex.model.domain.UserRole;
import br.com.uaijug.appex.appex.model.dto.TokenResult;
import br.com.uaijug.appex.appex.model.dto.UserTO;
import br.com.uaijug.appex.appex.model.repository.UserRoleRepository;
import br.com.uaijug.appex.appex.model.service.PasswordCrypto;
import br.com.uaijug.appex.appex.model.service.UserService;
import br.com.uaijug.appex.appex.util.JWTUtil;

@Component
public class AuthSupport {

	@Autowired
	private UserDetailsService customUserDetailsService;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private PasswordCrypto crypto;

	public TokenResult validate(UserTO userAuthentication) throws ServletException {
		if (userAuthentication.getEmail() == null || userAuthentication.getPassword() == null) {
			throw new ServletException("Please fill in username and password");
		}

		UserDetails user = customUserDetailsService.loadUserByUsername(userAuthentication.getEmail());
		if (user == null) {
			throw new UserNotFoundException("User not found.");
		}

		User userResult = userService.findByUsername(userAuthentication.getEmail());
		if (userResult != null) {
			boolean isValid = crypto.matches(userAuthentication.getPassword(), userResult.getPassword());
			if (!isValid) {
				throw new UserNotFoundException("User not found.");
			}

		} else {
			throw new UserNotFoundException("User not found.");
		}

		TokenResult tokenResult = setResultLoginInfo(userAuthentication, userResult);
		return tokenResult;
	}

	private TokenResult setResultLoginInfo(UserTO userAuthentication, User userResult) {
		TokenResult tokenResult = new TokenResult();
		tokenResult.setToken(JWTUtil.createToken(userAuthentication.getEmail()));
		tokenResult.setUserId(userResult.getId());

		List<String> rolesResult = new ArrayList<>();
		Set<UserRole> roles = userRoleRepository.findRoleNameByUserId(userResult.getId());
		for (UserRole userRole : roles) {
			rolesResult.add(userRole.getRoleName());
		}
		tokenResult.setRoles(rolesResult);
		return tokenResult;
	}
}
