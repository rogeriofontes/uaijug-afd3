package br.com.uaijug.appex.appex.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.uaijug.appex.appex.model.service.PasswordCrypto;

@Component
public class PasswordCryptoImpl implements PasswordCrypto {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public String encrypt(String str) {
		return passwordEncoder.encode(str);
	}

	@Override
	public boolean matches(String rawPassword, String encodedPassword) {
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}
}