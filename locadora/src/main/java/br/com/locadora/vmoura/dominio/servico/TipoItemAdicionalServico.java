package br.com.locadora.vmoura.dominio.servico;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.locadora.vmoura.dominio.entidade.TipoItemAdicional;
import br.com.locadora.vmoura.dominio.repositorio.ItemAdicionalRepositorio;
import br.com.locadora.vmoura.dominio.repositorio.LocacaoRepositorio;
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
	
	@Autowired
    private LocacaoRepositorio locacaoRepositorio;
	
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
	
	public boolean isTipoItemAdicionalDisponivel(TipoItemAdicional tipoItemAdicional, Date dataInicio, Date dataFim) {
		Long qtdeReservas = 
				reservaRepositorio.quantidadeReservaPorTipoItemAdicional(
						tipoItemAdicional, dataInicio, dataFim);
		
		Long qtdeLocacoes = 
				locacaoRepositorio.quantidadeLocacaoPorTipoItemAdicional(
						tipoItemAdicional, dataInicio, dataFim);
		
		if (tipoItemAdicional.getQuantidadeTotal() - (qtdeReservas + qtdeLocacoes) == 0) {
			return false;
		} else {
			return true;
		}
	}

	public List<TipoItemAdicional> buscarTodos() {
		return tipoItemAdicionalRepositorio.buscarTodosAtivos();
	}
}
