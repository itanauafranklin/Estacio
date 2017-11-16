package br.com.locadora.vmoura.dominio.controle;

import javax.faces.context.FacesContext;

public class GenericController {
	
	protected String avancarPagina(String url) {
		if (FacesContext.getCurrentInstance().isValidationFailed()) {
			return "";
		} else {
			return url;
		}
	}

}
