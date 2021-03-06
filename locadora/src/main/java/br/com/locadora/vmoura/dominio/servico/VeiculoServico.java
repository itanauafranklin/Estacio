package br.com.locadora.vmoura.dominio.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.locadora.vmoura.dominio.entidade.Veiculo;
import br.com.locadora.vmoura.dominio.repositorio.LocacaoRepositorio;
import br.com.locadora.vmoura.dominio.repositorio.VeiculoRepositorio;

@Service
public class VeiculoServico extends AbstractServico<Veiculo> {

	@Autowired
    private VeiculoRepositorio veiculoRepositorio;
	
	@Autowired
    private LocacaoRepositorio locacaoRepositorio;
	
	@Override
	protected void salvar(Veiculo veiculo) {
		veiculoRepositorio.save(veiculo);
	}

	@Override
	public void excluir(Veiculo veiculo) {
		if (locacaoRepositorio.existsByVeiculo(veiculo)) {
			veiculo.setExcluido(true);
			veiculoRepositorio.save(veiculo);
		} else {
			veiculoRepositorio.delete(veiculo);
		}
	}
	
	public List<Veiculo> pesquisar(String nome) {
		if (nome == null || nome.trim().isEmpty()) {
    		return buscarTodos();
    	} else {
    		return veiculoRepositorio.buscarPorPlaca(nome);
    	}
	}

	public List<Veiculo> buscarTodos() {
		return veiculoRepositorio.buscarTodosAtivos();
	}
}
