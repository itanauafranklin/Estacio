package br.com.locadora.vmoura.dominio.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.locadora.vmoura.dominio.entidade.Cliente;
import br.com.locadora.vmoura.dominio.servico.ClienteServico;

@Scope(value = "session")
@Component(value = "clienteController")
public class ClienteController extends GenericController {
	
	@Autowired
    private ClienteServico clienteServico;
	
	private String filtro;
	
	private Cliente cliente;
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public String criar() {
		cliente = new Cliente();
		return avancarPagina("criar.jsf?faces-redirect=true");
	}
	
	public String detalhar(Cliente tipo) {
		cliente = tipo;
		return avancarPagina("detalhar.jsf?faces-redirect=true");
	}
	
	public String alterar(Cliente tipo) {
		cliente = tipo;
		return avancarPagina("alterar.jsf?faces-redirect=true");
	}
	
	public String salvar() {
		clienteServico.salvarEntidade(cliente);
		cliente = new Cliente();
		return avancarPagina("listar.jsf?faces-redirect=true");
	}
	
	public String excluir(Cliente tipo) {
		clienteServico.excluir(tipo);
		return avancarPagina("listar.jsf?faces-redirect=true");
	}
    
    public List<Cliente> pesquisar() {
    	return clienteServico.pesquisar(getFiltro());
    }

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

}
