package br.com.locadora.vmoura.dominio.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.locadora.vmoura.dominio.entidade.TipoItemAdicional;
import br.com.locadora.vmoura.dominio.servico.TipoItemAdicionalServico;

@Scope(value = "session")
@Component(value = "tipoItemAdicionalController")
public class TipoItemAdicionalController extends GenericController {
	
	@Autowired
    private TipoItemAdicionalServico tipoItemAdicionalServico;
	
	private String filtro;
	
	private TipoItemAdicional tipoItemAdicional;
	
	public TipoItemAdicional getTipoItemAdicional() {
		return tipoItemAdicional;
	}
	
	public String novoTipo() {
		tipoItemAdicional = new TipoItemAdicional();
		return avancarPagina("criar.jsf?faces-redirect=true");
	}
	
	public String detalhar(TipoItemAdicional tipo) {
		tipoItemAdicional = tipo;
		return avancarPagina("detalhar.jsf?faces-redirect=true");
	}
	
	public String alterar(TipoItemAdicional tipo) {
		tipoItemAdicional = tipo;
		return avancarPagina("alterar.jsf?faces-redirect=true");
	}
	
	public String salvar() {
		tipoItemAdicionalServico.salvarEntidade(tipoItemAdicional);
		tipoItemAdicional = new TipoItemAdicional();
		return avancarPagina("listar.jsf?faces-redirect=true");
	}
	
	public String excluir(TipoItemAdicional tipo) {
		tipoItemAdicionalServico.excluir(tipo);
		return avancarPagina("listar.jsf?faces-redirect=true");
	}
    
    public List<TipoItemAdicional> pesquisar() {
    	return tipoItemAdicionalServico.pesquisar(getFiltro());
    }

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

}
