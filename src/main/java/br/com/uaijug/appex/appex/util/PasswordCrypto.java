package br.com.uaijug.appex.appex.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordCrypto {

	@Autowired
	private PasswordEncoder passwordEncoder;

	public String encrypt(String str) {
		return passwordEncoder.encode(str);
	}
}