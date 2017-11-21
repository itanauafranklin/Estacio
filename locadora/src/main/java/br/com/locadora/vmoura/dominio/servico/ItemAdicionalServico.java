package br.com.locadora.vmoura.dominio.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.locadora.vmoura.dominio.entidade.ItemAdicional;
import br.com.locadora.vmoura.dominio.repositorio.ItemAdicionalRepositorio;

@Service
public class ItemAdicionalServico extends AbstractServico<ItemAdicional> {

	@Autowired
    private ItemAdicionalRepositorio itemAdicionalRepositorio;
	
	@Override
	protected void salvar(ItemAdicional ItemAdicional) {
		itemAdicionalRepositorio.save(ItemAdicional);
	}

	@Override
	public void excluir(ItemAdicional ItemAdicional) {
		itemAdicionalRepositorio.delete(ItemAdicional);
	}
	
	public List<ItemAdicional> pesquisar(String nome) {
		if (nome == null || nome.trim().isEmpty()) {
			return buscarTodos();
    	} else {
    		return itemAdicionalRepositorio.buscarPorItem(nome);
    	}
	}

	public List<ItemAdicional> buscarTodos() {
		return itemAdicionalRepositorio.findAll();
	}	
}
