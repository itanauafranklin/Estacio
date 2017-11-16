package br.com.locadora.vmoura.dominio.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.locadora.vmoura.dominio.entidade.ItemAdicional;

public interface ItemAdicionalRepositorio extends JpaRepository<ItemAdicional, Long> {
	
	@Query("select item from ItemAdicional item where item.tipoItemAdicional.nome = :nome")
	List<ItemAdicional> buscarPorItem(@Param("nome") String nome);


}
