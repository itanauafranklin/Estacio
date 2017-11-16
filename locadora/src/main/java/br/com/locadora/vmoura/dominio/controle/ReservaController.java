package br.com.locadora.vmoura.dominio.controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.locadora.vmoura.dominio.entidade.Cliente;
import br.com.locadora.vmoura.dominio.entidade.Reserva;
import br.com.locadora.vmoura.dominio.entidade.TipoItemAdicional;
import br.com.locadora.vmoura.dominio.entidade.TipoVeiculo;
import br.com.locadora.vmoura.dominio.servico.ClienteServico;
import br.com.locadora.vmoura.dominio.servico.ReservaServico;
import br.com.locadora.vmoura.dominio.servico.TipoItemAdicionalServico;
import br.com.locadora.vmoura.dominio.servico.TipoVeiculoServico;

@Scope(value = "session")
@Component(value = "reservaController")
@ManagedBean
public class ReservaController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ClienteServico clienteServico;
	
	@Autowired
	private TipoVeiculoServico tipoVeiculoServico;
	
	@Autowired
	private TipoItemAdicionalServico tipoItemAdicionalServico;

	@Autowired
	private ReservaServico reservaServico;
	
	private String filtro;
	
	private List<Cliente> clientes = new ArrayList<Cliente>();
	
	private List<TipoVeiculo> tiposVeiculos = new ArrayList<TipoVeiculo>();
	
	private List<TipoItemAdicional> tiposItensAdicionais = new ArrayList<TipoItemAdicional>();
	
	private Reserva reserva;
	
	public Reserva getReserva() {
		return reserva;
	}
	
	public String criar() {
		reserva = new Reserva();
		setValoresIniciais();
		return "criar.jsf?faces-redirect=true";
	}

	private void setValoresIniciais() {
		tiposVeiculos = tipoVeiculoServico.buscarTodos();
		tiposItensAdicionais = tipoItemAdicionalServico.buscarTodos();
		clientes = clienteServico.buscarTodos();
	}
	
	public String detalhar(Reserva reserva) {
		this.reserva = reserva;
		return "detalhar.jsf?faces-redirect=true";
	}
	
	public String salvar() {
		reservaServico.salvarEntidade(reserva);
		return "listar.jsf?faces-redirect=true";
	}
	
	public String excluir(Reserva reserva) {
		reservaServico.excluir(reserva);
		return "listar.jsf?faces-redirect=true";
	}
    
    public List<Reserva> pesquisar() {
    	return reservaServico.pesquisar(getFiltro());
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

	public List<TipoItemAdicional> getTiposItensAdicionais() {
		return tiposItensAdicionais;
	}

	public void setTiposItensAdicionais(List<TipoItemAdicional> tiposItensAdicionais) {
		this.tiposItensAdicionais = tiposItensAdicionais;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

}
