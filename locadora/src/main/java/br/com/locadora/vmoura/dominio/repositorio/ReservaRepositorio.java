package br.com.locadora.vmoura.dominio.repositorio;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.locadora.vmoura.dominio.entidade.Cliente;
import br.com.locadora.vmoura.dominio.entidade.Reserva;
import br.com.locadora.vmoura.dominio.entidade.TipoItemAdicional;
import br.com.locadora.vmoura.dominio.entidade.TipoVeiculo;

public interface ReservaRepositorio extends JpaRepository<Reserva, Long> {
	
	@Query("select reserva from Reserva reserva where reserva.cliente.cpf like %:cpf%")
	List<Reserva> buscarPorCPFCliente(@Param("cpf") String cpf);
	
	boolean existsByCliente(Cliente cliente);
	
	boolean existsByTipoVeiculo(TipoVeiculo tipoVeiculo);
	
	boolean existsByTiposItensAdicionais(TipoItemAdicional tipoItemAdicional);
	
	@Query("select count(reserva) from Reserva reserva inner join reserva.tiposItensAdicionais itemAdicional "
			+ "where itemAdicional = :tipoItemAdicional and ("
			+ "(:dataInicial between reserva.dataRetirada and reserva.dataEntrega) "
			+ "or (:dataFinal between reserva.dataRetirada and reserva.dataEntrega) )")
	Long quantidadeReservaPorTipoItemAdicional(@Param("tipoItemAdicional") TipoItemAdicional tipoItemAdicional, 
			@Param("dataInicial") Date dataInicial, @Param("dataFinal") Date dataFinal);
	
	@Query("select count(reserva) from Reserva reserva where reserva.tipoVeiculo = :tipoVeiculo and ("
			+ "(:dataInicial between reserva.dataRetirada and reserva.dataEntrega) "
			+ "or (:dataFinal between reserva.dataRetirada and reserva.dataEntrega) )")
	Long quantidadeReservaPorTipoVeiculo(@Param("tipoVeiculo") TipoVeiculo tipoVeiculo, 
			@Param("dataInicial") Date dataInicial, @Param("dataFinal") Date dataFinal);

}
