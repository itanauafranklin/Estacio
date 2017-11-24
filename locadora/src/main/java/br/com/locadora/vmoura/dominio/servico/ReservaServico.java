package br.com.locadora.vmoura.dominio.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.locadora.vmoura.dominio.entidade.Cliente;
import br.com.locadora.vmoura.dominio.entidade.Reserva;
import br.com.locadora.vmoura.dominio.entidade.TipoItemAdicional;
import br.com.locadora.vmoura.dominio.repositorio.ClienteRepositorio;
import br.com.locadora.vmoura.dominio.repositorio.ReservaRepositorio;
import br.com.locadora.vmoura.util.DataUtil;

@Service
public class ReservaServico extends AbstractServico<Reserva> {

	@Autowired
    private ReservaRepositorio reservaRepositorio;
	
	@Autowired
    private ClienteRepositorio clienteRepositorio;
	
	@Autowired
    private TipoVeiculoServico tipoVeiculoServico;
	
	@Autowired
    private TipoItemAdicionalServico tipoItemAdicionalServico;
	
	@Override
	protected void salvar(Reserva reserva) {
		reservaRepositorio.save(reserva);
	}

	public void validarInclusaoReserva(Reserva reserva) {
		Cliente cliente = clienteRepositorio.buscarPorCPF(reserva.getCpfCliente());
		reserva.setCliente(cliente);
		boolean isValido = true;
		if (reserva.getCliente() == null) {
			adicionarMensagemErro("Cliente não encontrado.");
			isValido = false;
		}
		
		if (reserva.getDataRetirada().before(DataUtil.dataAtualMeiaNoite())) {
			adicionarMensagemErro("A 'Data de retirada' não pode ser menor que a data de hoje.");
			isValido = false;
		} else if (reserva.getDataRetirada().after(reserva.getDataEntrega())) {
			adicionarMensagemErro("A 'Data de retirada' não pode ser maior que a 'Data de entrega'.");
			isValido = false;
		} else if (reserva.getDataEntrega().before(reserva.getDataRetirada())) {
			adicionarMensagemErro("A 'Data de entrega' não pode ser menor que a 'Data de retirada'.");
			isValido = false;
		} else if (!tipoVeiculoServico.isTipoVeiculoDisponivel(
				reserva.getTipoVeiculo(), reserva.getDataRetirada(), reserva.getDataEntrega())) {
			adicionarMensagemErro("'Tipo de veículo' indisponível para as datas informadas.");
			isValido = false;
		} else {
			for (TipoItemAdicional tipoItem : reserva.getTiposItensAdicionais()) {
				if (!tipoItemAdicionalServico.isTipoItemAdicionalDisponivel(
						tipoItem, reserva.getDataRetirada(), reserva.getDataEntrega())) {
					adicionarMensagemErro("Item adicional '" + tipoItem.getNome() + "' indisponível para as datas informadas.");
					isValido = false;
				}
			}
		}
		
		if (isValido) {
			reserva.setValor(calcularValorTotalReserva(reserva));
		}
	}

	private Double calcularValorTotalReserva(Reserva reserva) {
		Double valorTotal = 0.0;
		
		int qtdeDias = DataUtil.diasEntreDatas(
				reserva.getDataRetirada(), reserva.getDataEntrega());
		
		valorTotal += qtdeDias * reserva.getTipoVeiculo().getValorDiario();
		
		for (TipoItemAdicional tipoItem : reserva.getTiposItensAdicionais()) {
			valorTotal += qtdeDias * tipoItem.getValorDiario();
		}
		
		return valorTotal;
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
