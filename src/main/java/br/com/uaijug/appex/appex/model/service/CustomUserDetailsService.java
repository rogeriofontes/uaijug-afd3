package br.com.uaijug.appex.appex.model.service;

import br.com.uaijug.appex.appex.model.domain.User;

public interface CustomUserDetailsService {

	User loadCurrentUser();

}
