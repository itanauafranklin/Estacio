package br.com.locadora.vmoura.dominio.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.locadora.vmoura.dominio.entidade.TipoItemAdicional;
import br.com.locadora.vmoura.dominio.repositorio.TipoItemAdicionalRepositorio;

@Service
public class TipoItemAdicionalServico extends AbstractServico<TipoItemAdicional> {

	@Autowired
    private TipoItemAdicionalRepositorio tipoItemAdicionalRepositorio;
	
	@Override
	protected void salvar(TipoItemAdicional tipoItemAdicional) {
		tipoItemAdicionalRepositorio.save(tipoItemAdicional);
	}

	@Override
	public void excluir(TipoItemAdicional tipoItemAdicional) {
		tipoItemAdicionalRepositorio.delete(tipoItemAdicional);
	}
	
	public List<TipoItemAdicional> pesquisar(String nome) {
		if (nome == null || nome.trim().isEmpty()) {
    		return tipoItemAdicionalRepositorio.findAll();
    	} else {
    		return tipoItemAdicionalRepositorio.buscarPorNome(nome);
    	}
	}
}
