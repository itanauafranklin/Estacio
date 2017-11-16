package br.com.locadora.vmoura.dominio.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.locadora.vmoura.dominio.entidade.TipoVeiculo;
import br.com.locadora.vmoura.dominio.servico.TipoVeiculoServico;

@Scope(value = "session")
@Component(value = "tipoVeiculoController")
public class TipoVeiculoController extends GenericController {
	
	@Autowired
    private TipoVeiculoServico tipoVeiculoServico;
	
	private String filtro;
	
	private TipoVeiculo tipoVeiculo;
	
	public TipoVeiculo getTipoVeiculo() {
		return tipoVeiculo;
	}
	
	public String novoTipo() {
		tipoVeiculo = new TipoVeiculo();
		return avancarPagina("criar.jsf?faces-redirect=true");
	}
	
	public String detalhar(TipoVeiculo tipo) {
		tipoVeiculo = tipo;
		return avancarPagina("detalhar.jsf?faces-redirect=true");
	}
	
	public String alterar(TipoVeiculo tipo) {
		tipoVeiculo = tipo;
		return avancarPagina("alterar.jsf?faces-redirect=true");
	}
	
	public String salvar() {
		tipoVeiculoServico.salvarEntidade(tipoVeiculo);
		tipoVeiculo = new TipoVeiculo();
		return avancarPagina("listar.jsf?faces-redirect=true");
	}
	
	public String excluir(TipoVeiculo tipo) {
		tipoVeiculoServico.excluir(tipo);
		return avancarPagina("listar.jsf?faces-redirect=true");
	}
    
    public List<TipoVeiculo> pesquisar() {
    	return tipoVeiculoServico.pesquisar(getFiltro());
    }

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

}
