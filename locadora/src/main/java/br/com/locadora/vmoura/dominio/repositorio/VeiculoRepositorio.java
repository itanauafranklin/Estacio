package br.com.locadora.vmoura.dominio.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.locadora.vmoura.dominio.entidade.Veiculo;

public interface VeiculoRepositorio extends JpaRepository<Veiculo, Long> {
	
	@Query("select tipo from Veiculo tipo where UPPER(tipo.placa) like CONCAT('%',UPPER(:placa),'%')")
	List<Veiculo> buscarPorPlaca(@Param("placa") String placa);

}
