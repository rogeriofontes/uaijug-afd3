package br.com.uaijug.appex.appex.constants;

import br.com.uaijug.appex.appex.exception.NotImplementationConstructionException;

public class AppConstants {

	// Auxs Constants for Controllers
	public static final String RESPONSE_UNSUCCESS = "unsuccess";

	public static final String RESPONSE_SUCCESS = "success";

	public static final String CURRENT_USER = "root@localhost";

	private AppConstants() {
		throw new NotImplementationConstructionException("Classe não pode ser instanciada");
	}

}
