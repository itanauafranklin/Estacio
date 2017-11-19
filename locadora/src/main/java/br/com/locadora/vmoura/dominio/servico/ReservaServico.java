package br.com.locadora.vmoura.dominio.servico;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.locadora.vmoura.dominio.entidade.Cliente;
import br.com.locadora.vmoura.dominio.entidade.Reserva;
import br.com.locadora.vmoura.dominio.repositorio.ClienteRepositorio;
import br.com.locadora.vmoura.dominio.repositorio.ReservaRepositorio;

@Service
public class ReservaServico extends AbstractServico<Reserva> {

	@Autowired
    private ReservaRepositorio reservaRepositorio;
	
	@Autowired
    private ClienteRepositorio clienteRepositorio;
	
	@Override
	protected void salvar(Reserva reserva) {
		reservaRepositorio.save(reserva);
	}

	public void validarInclusaoReserva(Reserva reserva) {
		Cliente cliente = clienteRepositorio.buscarPorCPF(reserva.getCpfCliente());
		reserva.setCliente(cliente);
		boolean isValido = true;
		if (reserva.getCliente() == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente não encontrado."));
			FacesContext.getCurrentInstance().validationFailed();
			isValido = false;
		}
		
		if (isValido) {
			reserva.setValor(reserva.getCalcularValorTotal());
		}
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
