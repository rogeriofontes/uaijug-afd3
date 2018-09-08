package br.com.uaijug.appex.appex.model.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import br.com.uaijug.appex.appex.model.domain.User;
import br.com.uaijug.appex.appex.model.domain.UserRole;
import br.com.uaijug.appex.appex.model.repository.UserRepository;
import br.com.uaijug.appex.appex.model.repository.UserRoleRepository;
import br.com.uaijug.appex.appex.model.service.CustomUserDetailsService;
import br.com.uaijug.appex.appex.util.SecurityUtils;

@Service
@Qualifier("customUserDetailsService")
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService, UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Override
	public UserDetails loadUserByUsername(final String email) {
		List<GrantedAuthority> authorities = null;
		User user = userRepository.findByUsername(email);
		if (user != null) {
			Set<UserRole> roles = userRoleRepository.findRoleNameByUserId(user.getId());
			authorities = buildUserAuthority(roles);
		}
	
		
		return buildUserForAuthentication(user, authorities);

	}

	@Transactional
	@Override
	public User loadCurrentUser() {
		return userRepository.findByUsername(SecurityUtils.getCurrentLogin());
	}

	private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true,
				true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<>();

		// Build user's authorities
		for (UserRole userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRoleName()));
		}

		return new ArrayList<>(setAuths);
	}

}
