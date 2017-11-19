package br.com.locadora.vmoura.dominio.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.locadora.vmoura.dominio.entidade.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, Integer> {
	
	@Query("select cliente from Cliente cliente where cliente.cpf = :cpf")
	Cliente buscarPorCPF(@Param("cpf") String cpf);
	
	@Query("select cliente from Cliente cliente where UPPER(cliente.nome) like CONCAT('%',UPPER(:nome),'%')")
	List<Cliente> buscarPorNome(@Param("nome") String nome);

}
