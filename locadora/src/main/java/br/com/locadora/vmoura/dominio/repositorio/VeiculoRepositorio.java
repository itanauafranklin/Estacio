package br.com.locadora.vmoura.dominio.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.locadora.vmoura.dominio.entidade.TipoVeiculo;
import br.com.locadora.vmoura.dominio.entidade.Veiculo;

public interface VeiculoRepositorio extends JpaRepository<Veiculo, Long> {
	
	@Query("select veiculo from Veiculo veiculo where UPPER(veiculo.placa) like CONCAT('%',UPPER(:placa),'%')"
			+ " and veiculo.excluido = false")
	List<Veiculo> buscarPorPlaca(@Param("placa") String placa);
	
	@Query("select veiculo from Veiculo veiculo where veiculo.excluido = false")
	List<Veiculo> buscarTodosAtivos();
	
	boolean existsByTipoVeiculo(TipoVeiculo tipoVeiculo);

}
