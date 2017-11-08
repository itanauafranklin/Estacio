package br.com.locadora.vmoura.dominio.servico;

import java.util.Date;

import javax.transaction.Transactional;

import br.com.locadora.vmoura.dominio.entidade.ObjetoPersistente;

public abstract class AbstractServico<T extends ObjetoPersistente> {
	
	@Transactional
	public void salvarEntidade(T objeto) {
		objeto.setDataHoraAtualizacao(new Date());
		salvar(objeto);
	}

	@Transactional
	protected abstract void salvar(T objeto);
	
	@Transactional
	public abstract void excluir(T objeto);

}
