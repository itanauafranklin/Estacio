package br.com.locadora.vmoura.dominio.controle.validador;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class CpfValidator implements Validator {
	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object valorTela) throws ValidatorException {
		if (!validaCPF(String.valueOf(valorTela))) {
			FacesMessage message = new FacesMessage();
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			message.setSummary("CPF inválido.");
			throw new ValidatorException(message);
		}
	}

	/**
	 * Valida CPF do usuário. Não aceita CPF's padrões como
	 * 11111111111 ou 22222222222
	 *
	 * @param cpf String valor com 11 dígitos
	 */
	private static boolean validaCPF(String cpf) {
		if (cpf == null || cpf.length() != 11)
			return false;

		try {
			Long.parseLong(cpf);
		} catch (NumberFormatException e) { // CPF não possui somente números
			return false;
		}

		return true;
	}

}
