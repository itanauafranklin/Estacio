package br.com.locadora.vmoura.dominio.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.locadora.vmoura.dominio.entidade.TipoVeiculo;

public interface TipoVeiculoRepositorio extends JpaRepository<TipoVeiculo, Long> {
	
	@Query("select tipo from TipoVeiculo tipo where tipo.nome like %:nome% and tipo.excluido = false")
	List<TipoVeiculo> buscarPorNome(@Param("nome") String nome);
	
	@Query("select tipo from TipoVeiculo tipo where tipo.excluido = false")
	List<TipoVeiculo> buscarTodosAtivos();

}
