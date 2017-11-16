package br.com.locadora.vmoura.dominio.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.locadora.vmoura.dominio.entidade.Cliente;
import br.com.locadora.vmoura.dominio.repositorio.ClienteRepositorio;

@Service
public class ClienteServico extends AbstractServico<Cliente> {

	@Autowired
    private ClienteRepositorio clienteRepositorio;
	
	@Override
	protected void salvar(Cliente cliente) {
		clienteRepositorio.save(cliente);
	}

	@Override
	public void excluir(Cliente cliente) {
		clienteRepositorio.delete(cliente);
	}
	
	public List<Cliente> buscarTodos() {
		return clienteRepositorio.findAll();
	}
	
}
