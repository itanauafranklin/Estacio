package br.com.locadora.vmoura.dominio.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.locadora.vmoura.dominio.entidade.ItemAdicional;
import br.com.locadora.vmoura.dominio.entidade.TipoItemAdicional;

public interface ItemAdicionalRepositorio extends JpaRepository<ItemAdicional, Long> {
	
	@Query("select item from ItemAdicional item where UPPER(item.tipoItemAdicional.nome) like CONCAT('%',UPPER(:nome),'%')")
	List<ItemAdicional> buscarPorItem(@Param("nome") String nome);

	@Query("select item from ItemAdicional item where item.excluido = false order by item.tipoItemAdicional.nome")
	List<ItemAdicional> buscarTodosAtivos();
	
	boolean existsByTipoItemAdicional(TipoItemAdicional tipoItemAdicional);


}
