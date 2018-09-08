package br.com.uaijug.appex.appex.model.dto;

import java.io.Serializable;

public class UserTO implements Serializable {
	private static final long serialVersionUID = -4435625289563703924L;

	private String email;
	private String password;
	private String userType;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}
