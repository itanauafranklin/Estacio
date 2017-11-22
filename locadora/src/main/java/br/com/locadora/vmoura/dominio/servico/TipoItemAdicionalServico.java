package br.com.locadora.vmoura.dominio.servico;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.locadora.vmoura.dominio.entidade.TipoItemAdicional;
import br.com.locadora.vmoura.dominio.repositorio.ItemAdicionalRepositorio;
import br.com.locadora.vmoura.dominio.repositorio.ReservaRepositorio;
import br.com.locadora.vmoura.dominio.repositorio.TipoItemAdicionalRepositorio;

@Service
public class TipoItemAdicionalServico extends AbstractServico<TipoItemAdicional> {

	@Autowired
    private TipoItemAdicionalRepositorio tipoItemAdicionalRepositorio;

	@Autowired
    private ItemAdicionalRepositorio itemAdicionalRepositorio;
	
	@Autowired
    private ReservaRepositorio reservaRepositorio;
	
	@Override
	protected void salvar(TipoItemAdicional tipoItemAdicional) {
		tipoItemAdicionalRepositorio.save(tipoItemAdicional);
	}

	@Override
	public void excluir(TipoItemAdicional tipoItemAdicional) {
		if (reservaRepositorio.existsByTiposItensAdicionais(tipoItemAdicional)
				|| itemAdicionalRepositorio.existsByTipoItemAdicional(tipoItemAdicional)) {
			tipoItemAdicional.setExcluido(true);
			tipoItemAdicionalRepositorio.save(tipoItemAdicional);
		} else {
			tipoItemAdicionalRepositorio.delete(tipoItemAdicional);
		}
	}
	
	public List<TipoItemAdicional> pesquisar(String nome) {
		if (nome == null || nome.trim().isEmpty()) {
    		return buscarTodos();
    	} else {
    		return tipoItemAdicionalRepositorio.buscarPorNome(nome);
    	}
	}
	
	public boolean isTipoItemAdicionalDisponivel(TipoItemAdicional tipoVeiculo, Date dataInicio, Date dataFim) {
		return false;
	}

	public List<TipoItemAdicional> buscarTodos() {
		return tipoItemAdicionalRepositorio.buscarTodosAtivos();
	}
}
