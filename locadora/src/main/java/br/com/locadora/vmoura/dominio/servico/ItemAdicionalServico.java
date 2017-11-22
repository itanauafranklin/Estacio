package br.com.locadora.vmoura.dominio.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.locadora.vmoura.dominio.entidade.ItemAdicional;
import br.com.locadora.vmoura.dominio.repositorio.ItemAdicionalRepositorio;
import br.com.locadora.vmoura.dominio.repositorio.LocacaoRepositorio;

@Service
public class ItemAdicionalServico extends AbstractServico<ItemAdicional> {

	@Autowired
    private ItemAdicionalRepositorio itemAdicionalRepositorio;
	@Autowired
    private LocacaoRepositorio locacaoRepositorio;
	
	@Override
	protected void salvar(ItemAdicional itemAdicional) {
		itemAdicionalRepositorio.save(itemAdicional);
	}

	@Override
	public void excluir(ItemAdicional itemAdicional) {
		if (locacaoRepositorio.existsByItensAdicionais(itemAdicional)) {
			itemAdicional.setExcluido(true);
			itemAdicionalRepositorio.save(itemAdicional);
		} else {
			itemAdicionalRepositorio.delete(itemAdicional);
		}
	}
	
	public List<ItemAdicional> pesquisar(String nome) {
		if (nome == null || nome.trim().isEmpty()) {
			return buscarTodos();
    	} else {
    		return itemAdicionalRepositorio.buscarPorItem(nome);
    	}
	}

	public List<ItemAdicional> buscarTodos() {
		return itemAdicionalRepositorio.buscarTodosAtivos();
	}	
}
