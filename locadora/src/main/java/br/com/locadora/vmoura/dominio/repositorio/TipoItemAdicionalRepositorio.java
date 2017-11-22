package br.com.locadora.vmoura.dominio.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.locadora.vmoura.dominio.entidade.TipoItemAdicional;

public interface TipoItemAdicionalRepositorio extends JpaRepository<TipoItemAdicional, Long> {
	
	@Query("select tipo from TipoItemAdicional tipo where tipo.nome like %:nome% and tipo.excluido = false")
	List<TipoItemAdicional> buscarPorNome(@Param("nome") String nome);
	
	@Query("select tipo from TipoItemAdicional tipo where tipo.excluido = false")
	List<TipoItemAdicional> buscarTodosAtivos();

}
