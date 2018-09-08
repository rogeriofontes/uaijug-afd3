package br.com.uaijug.appex.appex.model.service;

public interface PasswordCrypto {

	String encrypt(String str);

	boolean matches(String rawPassword, String encodedPassword);

}
