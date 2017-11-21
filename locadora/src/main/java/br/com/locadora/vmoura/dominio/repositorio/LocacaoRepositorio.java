package br.com.locadora.vmoura.dominio.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.locadora.vmoura.dominio.entidade.Locacao;

public interface LocacaoRepositorio extends JpaRepository<Locacao, Long> {
	
	@Query("select locacao from Locacao locacao where locacao.cliente.cpf like %:cpf%")
	List<Locacao> buscarPorCPFCliente(@Param("cpf") String cpf);

}
