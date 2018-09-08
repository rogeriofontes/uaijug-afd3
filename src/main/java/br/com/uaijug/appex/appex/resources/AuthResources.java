package br.com.uaijug.appex.appex.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.uaijug.appex.appex.exception.UserNotFoundException;
import br.com.uaijug.appex.appex.model.domain.User;
import br.com.uaijug.appex.appex.model.domain.UserRole;
import br.com.uaijug.appex.appex.model.dto.TokenResult;
import br.com.uaijug.appex.appex.model.dto.UserTO;
import br.com.uaijug.appex.appex.model.service.PasswordCrypto;
import br.com.uaijug.appex.appex.model.service.UserService;
import br.com.uaijug.appex.appex.util.JWTUtil;

@RestController
@RequestMapping("/login")	
public class AuthResources {

	@Autowired
	private UserDetailsService customUserDetailsService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordCrypto crypto;

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public TokenResult login(@RequestBody UserTO userAuthentication) throws ServletException {

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

		return setResultLoginInfo(userAuthentication, userResult);
	}

	private TokenResult setResultLoginInfo(UserTO userAuthentication, User userResult) {
		TokenResult tokenResult = new TokenResult();
		tokenResult.setToken(JWTUtil.createToken(userAuthentication.getEmail()));
		tokenResult.setUserId(userResult.getId());

		List<String> rolesResult = new ArrayList<>();
		Set<UserRole> roles = userResult.getRoles();
		for (UserRole userRole : roles) {
			rolesResult.add(userRole.getRoleName());
		}
		tokenResult.setRoles(rolesResult);
		return tokenResult;
	}

}
