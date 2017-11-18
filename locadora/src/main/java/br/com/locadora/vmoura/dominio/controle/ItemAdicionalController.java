package br.com.locadora.vmoura.dominio.controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.locadora.vmoura.dominio.entidade.ItemAdicional;
import br.com.locadora.vmoura.dominio.entidade.TipoItemAdicional;
import br.com.locadora.vmoura.dominio.servico.ItemAdicionalServico;
import br.com.locadora.vmoura.dominio.servico.TipoItemAdicionalServico;

@Scope(value = "session")
@Component(value = "ItemAdicionalController")
@ManagedBean
public class ItemAdicionalController extends GenericController {
	

	@Autowired
	private TipoItemAdicionalServico tipoItemAdicionalServico;
	
	@Autowired
    private ItemAdicionalServico itemAdicionalServico;
	
	private String filtro;
	
	private ItemAdicional itemAdicional = new ItemAdicional();
	
	private List<TipoItemAdicional> tiposItemAdicional = new ArrayList<TipoItemAdicional>();
	
	public ItemAdicional getItemAdicional() {
		return itemAdicional;
	}
	
	public String criar() {
		itemAdicional = new ItemAdicional();
		tiposItemAdicional = tipoItemAdicionalServico.buscarTodos();
		return avancarPagina("criar.jsf?faces-redirect=true");
	}
	
	public String detalhar(ItemAdicional tipo) {
		itemAdicional = tipo;
		return avancarPagina("detalhar.jsf?faces-redirect=true");
	}
	
	public String alterar(ItemAdicional tipo) {
		itemAdicional = tipo;
		tiposItemAdicional = tipoItemAdicionalServico.buscarTodos();
		return avancarPagina("alterar.jsf?faces-redirect=true");
	}
	
	public String salvar() {
		itemAdicionalServico.salvarEntidade(itemAdicional);
		return avancarPagina("listar.jsf?faces-redirect=true");
	}
	
	public String excluir(ItemAdicional tipo) {
		itemAdicionalServico.excluir(tipo);
		return avancarPagina("listar.jsf?faces-redirect=true");
	}
    
    public List<ItemAdicional> pesquisar() {
    	return itemAdicionalServico.pesquisar(getFiltro());
    }

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public List<TipoItemAdicional> getTiposItemAdicional() {
		return tiposItemAdicional;
	}

	public void setTiposItemAdicional(List<TipoItemAdicional> tiposItemAdicional) {
		this.tiposItemAdicional = tiposItemAdicional;
	}

	

}
