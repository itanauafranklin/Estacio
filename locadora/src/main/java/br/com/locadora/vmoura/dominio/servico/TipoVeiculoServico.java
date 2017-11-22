package br.com.locadora.vmoura.dominio.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.locadora.vmoura.dominio.entidade.TipoVeiculo;
import br.com.locadora.vmoura.dominio.repositorio.ReservaRepositorio;
import br.com.locadora.vmoura.dominio.repositorio.TipoVeiculoRepositorio;
import br.com.locadora.vmoura.dominio.repositorio.VeiculoRepositorio;

@Service
public class TipoVeiculoServico extends AbstractServico<TipoVeiculo> {

	@Autowired
    private TipoVeiculoRepositorio tipoVeiculoRepositorio;
	
	@Autowired
    private VeiculoRepositorio veiculoRepositorio;
	
	@Autowired
    private ReservaRepositorio reservaRepositorio;
	
	@Override
	protected void salvar(TipoVeiculo tipoVeiculo) {
		tipoVeiculoRepositorio.save(tipoVeiculo);
	}

	@Override
	public void excluir(TipoVeiculo tipoVeiculo) {
		if (veiculoRepositorio.existsByTipoVeiculo(tipoVeiculo)
				|| reservaRepositorio.existsByTipoVeiculo(tipoVeiculo)) {
			tipoVeiculo.setExcluido(true);
			tipoVeiculoRepositorio.save(tipoVeiculo);
		} else {
			tipoVeiculoRepositorio.delete(tipoVeiculo);
		}
	}
	
	public List<TipoVeiculo> pesquisar(String nome) {
		if (nome == null || nome.trim().isEmpty()) {
    		return buscarTodos();
    	} else {
    		return tipoVeiculoRepositorio.buscarPorNome(nome);
    	}
	}
	
	public List<TipoVeiculo> buscarTodos() {
		return tipoVeiculoRepositorio.buscarTodosAtivos();
	}
}
