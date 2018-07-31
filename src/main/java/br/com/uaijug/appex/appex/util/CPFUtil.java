package br.com.uaijug.appex.appex.util;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.uaijug.appex.appex.exception.NotImplementationConstructionException;

public final class CPFUtil {

	private CPFUtil() {
		throw new NotImplementationConstructionException("Essa classe nao pode ser instanciada");
	}

	public static boolean valida(String cpf) {
		CPFValidator cpfValidator = new CPFValidator();
		try {
			cpfValidator.assertValid(cpf);
			return Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}
	}
}
