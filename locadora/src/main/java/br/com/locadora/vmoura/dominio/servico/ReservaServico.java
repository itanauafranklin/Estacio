package br.com.locadora.vmoura.dominio.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.locadora.vmoura.dominio.entidade.Reserva;
import br.com.locadora.vmoura.dominio.repositorio.ReservaRepositorio;

@Service
public class ReservaServico extends AbstractServico<Reserva> {

	@Autowired
    private ReservaRepositorio reservaRepositorio;
	
	@Override
	protected void salvar(Reserva reserva) {
		reservaRepositorio.save(reserva);
	}

	@Override
	public void excluir(Reserva reserva) {
		reservaRepositorio.delete(reserva);
	}
	
	public List<Reserva> pesquisar(String cpf) {
		if (cpf == null || cpf.trim().isEmpty()) {
    		return reservaRepositorio.findAll();
    	} else {
    		return reservaRepositorio.buscarPorCPFCliente(cpf);
    	}
	}
}
