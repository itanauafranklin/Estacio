package br.com.locadora.vmoura.dominio.repositorio;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.locadora.vmoura.dominio.entidade.Cliente;
import br.com.locadora.vmoura.dominio.entidade.ItemAdicional;
import br.com.locadora.vmoura.dominio.entidade.Locacao;
import br.com.locadora.vmoura.dominio.entidade.TipoItemAdicional;
import br.com.locadora.vmoura.dominio.entidade.TipoVeiculo;
import br.com.locadora.vmoura.dominio.entidade.Veiculo;

public interface LocacaoRepositorio extends JpaRepository<Locacao, Long> {
	
	@Query("select locacao from Locacao locacao where locacao.cliente.cpf like %:cpf%")
	List<Locacao> buscarPorCPFCliente(@Param("cpf") String cpf);
	
	boolean existsByCliente(Cliente cliente);
	
	boolean existsByVeiculo(Veiculo cliente);
	
	boolean existsByItensAdicionais(ItemAdicional itemAdicional);
	
	@Query("select count(locacao) from Locacao locacao inner join locacao.itensAdicionais itemAdicional "
			+ "where locacao.devolvido = false itemAdicional.tipoItemAdicional = :tipoItemAdicional and ("
			+ "(:dataInicial between locacao.dataRetirada and locacao.dataEntrega) "
			+ "or (:dataFinal between locacao.dataRetirada and locacao.dataEntrega) )")
	Long quantidadeLocacaoPorTipoItemAdicional(@Param("tipoItemAdicional") TipoItemAdicional tipoItemAdicional, 
			@Param("dataInicial") Date dataInicial, @Param("dataFinal") Date dataFinal);
	
	@Query("select count(locacao) from Locacao locacao where locacao.devolvido = false "
			+ "locacao.veiculo.tipoVeiculo = :tipoVeiculo and ("
			+ "(:dataInicial between locacao.dataRetirada and locacao.dataEntrega) "
			+ "or (:dataFinal between locacao.dataRetirada and locacao.dataEntrega) )")
	Long quantidadeLocacaoPorTipoVeiculo(@Param("tipoVeiculo") TipoVeiculo tipoVeiculo, 
			@Param("dataInicial") Date dataInicial, @Param("dataFinal") Date dataFinal);

}
