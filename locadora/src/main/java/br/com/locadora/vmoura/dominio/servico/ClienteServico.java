package br.com.locadora.vmoura.dominio.servico;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.locadora.vmoura.dominio.entidade.Cliente;
import br.com.locadora.vmoura.dominio.repositorio.ClienteRepositorio;
import br.com.locadora.vmoura.dominio.repositorio.LocacaoRepositorio;
import br.com.locadora.vmoura.dominio.repositorio.ReservaRepositorio;

@Service
public class ClienteServico extends AbstractServico<Cliente> {

	@Autowired
    private ClienteRepositorio clienteRepositorio;
	
	@Autowired
    private LocacaoRepositorio locacaoRepositorio;
	
	@Autowired
    private ReservaRepositorio reservaRepositorio;
	
	@Override
	protected void salvar(Cliente cliente) {
		if (validarInclusaoCliente(cliente)) {
			cliente.getEndereco().setDataHoraAtualizacao(new Date());
			cliente.getEndereco().setExcluido(false);
			clienteRepositorio.save(cliente);
		}
		
	}

	private boolean validarInclusaoCliente(Cliente cliente) {
		boolean isValido = true;
		if (cliente.getCodigo() == 0 
				&& clienteRepositorio.buscarPorCPF(cliente.getCpf()) != null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Já existe um cliente com esse CPF."));
			FacesContext.getCurrentInstance().validationFailed();
			isValido = false;
		}
		return isValido;
	}

	@Override
	public void excluir(Cliente cliente) {
		if (locacaoRepositorio.existsByCliente(cliente)
				|| reservaRepositorio.existsByCliente(cliente)) {
			cliente.setExcluido(true);
			clienteRepositorio.save(cliente);
		} else {
			clienteRepositorio.delete(cliente);
		}
	}
	
	public List<Cliente> pesquisar(String nome) {
		if (nome == null || nome.trim().isEmpty()) {
    		return clienteRepositorio.buscarTodosAtivos();
    	} else {
    		return clienteRepositorio.buscarPorNome(nome);
    	}
	}
	
}
