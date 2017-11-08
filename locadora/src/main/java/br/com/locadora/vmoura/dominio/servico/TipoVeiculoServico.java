package br.com.locadora.vmoura.dominio.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.locadora.vmoura.dominio.entidade.TipoVeiculo;
import br.com.locadora.vmoura.dominio.repositorio.TipoVeiculoRepositorio;

@Service
public class TipoVeiculoServico extends AbstractServico<TipoVeiculo> {

	@Autowired
    private TipoVeiculoRepositorio tipoVeiculoRepositorio;
	
	@Override
	protected void salvar(TipoVeiculo tipoVeiculo) {
		tipoVeiculoRepositorio.save(tipoVeiculo);
	}

	@Override
	public void excluir(TipoVeiculo tipoVeiculo) {
		tipoVeiculoRepositorio.delete(tipoVeiculo);
	}
	
	public List<TipoVeiculo> pesquisar(String nome) {
		if (nome == null || nome.trim().isEmpty()) {
    		return tipoVeiculoRepositorio.findAll();
    	} else {
    		return tipoVeiculoRepositorio.buscarPorNome(nome);
    	}
	}
}
