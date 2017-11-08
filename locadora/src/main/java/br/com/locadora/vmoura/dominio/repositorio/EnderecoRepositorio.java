package br.com.locadora.vmoura.dominio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.locadora.vmoura.dominio.entidade.Endereco;

public interface EnderecoRepositorio extends JpaRepository<Endereco, Long> {

}
