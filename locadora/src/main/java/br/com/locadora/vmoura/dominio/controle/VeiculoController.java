package br.com.locadora.vmoura.dominio.controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.locadora.vmoura.dominio.entidade.TipoVeiculo;
import br.com.locadora.vmoura.dominio.entidade.Veiculo;
import br.com.locadora.vmoura.dominio.servico.TipoVeiculoServico;
import br.com.locadora.vmoura.dominio.servico.VeiculoServico;

@Scope(value = "session")
@Component(value = "veiculoController")
@ManagedBean
public class VeiculoController extends GenericController {
	

	@Autowired
	private TipoVeiculoServico tipoVeiculoServico;
	
	@Autowired
    private VeiculoServico veiculoServico;
	
	private String filtro;
	
	private Veiculo veiculo = new Veiculo();
	
	private List<TipoVeiculo> tiposVeiculos = new ArrayList<TipoVeiculo>();
	
	public Veiculo getVeiculo() {
		return veiculo;
	}
	
	public String criar() {
		veiculo = new Veiculo();
		tiposVeiculos = tipoVeiculoServico.buscarTodos();
		return avancarPagina("criar.jsf?faces-redirect=true");
	}
	
	public String detalhar(Veiculo tipo) {
		veiculo = tipo;
		return avancarPagina("detalhar.jsf?faces-redirect=true");
	}
	
	public String alterar(Veiculo tipo) {
		veiculo = tipo;
		tiposVeiculos = tipoVeiculoServico.buscarTodos();
		return avancarPagina("alterar.jsf?faces-redirect=true");
	}
	
	public String salvar() {
		veiculoServico.salvarEntidade(veiculo);
		return avancarPagina("listar.jsf?faces-redirect=true");
	}
	
	public String excluir(Veiculo tipo) {
		veiculoServico.excluir(tipo);
		return avancarPagina("listar.jsf?faces-redirect=true");
	}
    
    public List<Veiculo> pesquisar() {
    	return veiculoServico.pesquisar(getFiltro());
    }

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public List<TipoVeiculo> getTiposVeiculos() {
		return tiposVeiculos;
	}

	public void setTiposVeiculos(List<TipoVeiculo> tiposVeiculos) {
		this.tiposVeiculos = tiposVeiculos;
	}

}
