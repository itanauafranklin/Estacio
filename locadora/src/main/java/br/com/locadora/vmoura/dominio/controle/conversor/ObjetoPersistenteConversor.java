package br.com.locadora.vmoura.dominio.controle.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import br.com.locadora.vmoura.dominio.entidade.ObjetoPersistente;

public class ObjetoPersistenteConversor<T extends ObjetoPersistente> implements Converter {

	@SuppressWarnings("unchecked")
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) throws ConverterException {
		if (value != null && !value.isEmpty()) {
            return (T) uiComponent.getAttributes().get(value);
        }
        return null;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) throws ConverterException {
		if (value instanceof ObjetoPersistente) {
			@SuppressWarnings("unchecked")
			T entity= (T) value;
            if (entity != null && entity instanceof ObjetoPersistente && entity.getCodigo() != 0) {
                uiComponent.getAttributes().put(Integer.toString(entity.getCodigo()), entity);
                return Integer.toString(entity.getCodigo());
            }
        }
        return "";
	}

}
