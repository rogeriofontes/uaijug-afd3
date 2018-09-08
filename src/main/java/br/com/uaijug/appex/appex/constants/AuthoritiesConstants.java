package br.com.uaijug.appex.appex.constants;

import br.com.uaijug.appex.appex.exception.NotImplementationConstructionException;

public final class AuthoritiesConstants {
	
	public static final String ANONYMOUS = "ROLE_ANONYMOUS";
	public static final String ADMIN = "ROLE_ADMIN";
	public static final String USER = "ROLE_USER";

	private AuthoritiesConstants() {
		throw new NotImplementationConstructionException("Classe não pode ser instanciada");
	}

}
