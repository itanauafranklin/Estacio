package br.com.locadora.vmoura.dominio.controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.locadora.vmoura.dominio.entidade.ItemAdicional;
import br.com.locadora.vmoura.dominio.entidade.Locacao;
import br.com.locadora.vmoura.dominio.entidade.Veiculo;
import br.com.locadora.vmoura.dominio.servico.ItemAdicionalServico;
import br.com.locadora.vmoura.dominio.servico.LocacaoServico;
import br.com.locadora.vmoura.dominio.servico.VeiculoServico;

@Scope(value = "session")
@Component(value = "locacaoController")
@ManagedBean
public class LocacaoController extends GenericController {
	
	@Autowired
	private VeiculoServico veiculoServico;
	
	@Autowired
	private ItemAdicionalServico itemAdicionalServico;

	@Autowired
	private LocacaoServico locacaoServico;
	
	private String filtro;
	
	private List<Veiculo> veiculos = new ArrayList<Veiculo>();
	
	private List<ItemAdicional> itensAdicionais = new ArrayList<ItemAdicional>();
	
	private Locacao locacao;
	
	public Locacao getLocacao() {
		return locacao;
	}

	public String criar() {
		locacao = new Locacao();
		setValoresIniciais();
		return avancarPagina("criar.jsf?faces-redirect=true");
	}

	private void setValoresIniciais() {
		veiculos = veiculoServico.buscarTodos();
		itensAdicionais = itemAdicionalServico.buscarTodos();
	}
	
	public String detalhar(Locacao locacao) {
		this.locacao = locacao;
		return avancarPagina("detalhar.jsf?faces-redirect=true");
	}
	
	public String confirmar() {
		locacaoServico.validarInclusaoLocacao(locacao);
		return avancarPagina("confirmarCriar.jsf?faces-redirect=true");
	}
	
	
	public String salvar() {
		locacaoServico.salvarEntidade(locacao);
		return avancarPagina("listar.jsf?faces-redirect=true");
	}
	
	public String excluir(Locacao locacao) {
		locacaoServico.excluir(locacao);
		return avancarPagina("listar.jsf?faces-redirect=true");
	}
    
    public List<Locacao> pesquisar() {
    	return locacaoServico.pesquisar(getFiltro());
    }

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public List<Veiculo> getVeiculos() {
		return veiculos;
	}

	public void setVeiculos(List<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}

	public List<ItemAdicional> getItensAdicionais() {
		return itensAdicionais;
	}

	public void setItensAdicionais(List<ItemAdicional> itensAdicionais) {
		this.itensAdicionais = itensAdicionais;
	}

}
