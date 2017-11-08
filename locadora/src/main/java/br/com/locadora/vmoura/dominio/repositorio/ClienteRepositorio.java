package br.com.locadora.vmoura.dominio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.locadora.vmoura.dominio.entidade.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

}
