package br.com.locadora.vmoura.dominio.servico;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.locadora.vmoura.dominio.entidade.Cliente;
import br.com.locadora.vmoura.dominio.entidade.ItemAdicional;
import br.com.locadora.vmoura.dominio.entidade.Locacao;
import br.com.locadora.vmoura.dominio.repositorio.ClienteRepositorio;
import br.com.locadora.vmoura.dominio.repositorio.LocacaoRepositorio;
import br.com.locadora.vmoura.util.DataUtil;

@Service
public class LocacaoServico extends AbstractServico<Locacao> {

	@Autowired
    private LocacaoRepositorio locacaoRepositorio;
	
	@Autowired
    private ClienteRepositorio clienteRepositorio;
	
	@Autowired
    private TipoVeiculoServico tipoVeiculoServico;
	
	@Autowired
    private TipoItemAdicionalServico tipoItemAdicionalServico;
	
	@Override
	protected void salvar(Locacao locacao) {
		locacao.setDevolvido(false);
		locacaoRepositorio.save(locacao);
	}

	public void validarInclusaoLocacao(Locacao locacao) {
		Cliente cliente = clienteRepositorio.buscarPorCPF(locacao.getCpfCliente());
		locacao.setCliente(cliente);
		boolean isValido = true;
		if (locacao.getCliente() == null) {
			adicionarMensagemErro("Cliente não encontrado.");
			isValido = false;
		} else if (locacao.getDataRetirada().before(DataUtil.dataAtualMeiaNoite())) {
			adicionarMensagemErro("A 'Data de retirada' não pode ser menor que a data de hoje.");
			isValido = false;
		} else if (locacao.getDataRetirada().after(locacao.getDataEntrega())) {
			adicionarMensagemErro("A 'Data de retirada' não pode ser maior que a 'Data de entrega'.");
			isValido = false;
		} else if (locacao.getDataEntrega().before(locacao.getDataRetirada())) {
			adicionarMensagemErro("A 'Data de entrega' não pode ser menor que a 'Data de retirada'.");
			isValido = false;
		} else if (!tipoVeiculoServico.isTipoVeiculoDisponivel(
				locacao.getVeiculo().getTipoVeiculo(), locacao.getDataRetirada(), locacao.getDataEntrega())) {
			adicionarMensagemErro("'Veículo' indisponível para as datas informadas.");
			isValido = false;
		} else {
			for (ItemAdicional item : locacao.getItensAdicionais()) {
				if (!tipoItemAdicionalServico.isTipoItemAdicionalDisponivel(
						item.getTipoItemAdicional(), locacao.getDataRetirada(), locacao.getDataEntrega())) {
					adicionarMensagemErro("Item adicional '" + item.getTipoItemAdicional().getNome() + "' indisponível para as datas informadas.");
					isValido = false;
				}
			}
		}
		
		if (isValido) {
			locacao.setValor(calcularValorTotalReserva(locacao));
		}
	}
	
	private Double calcularValorTotalReserva(Locacao locacao) {
		Double valorTotal = 0.0;
		
		int qtdeDias = DataUtil.diasEntreDatas(
				locacao.getDataRetirada(), locacao.getDataEntrega());
		
		valorTotal += qtdeDias * locacao.getVeiculo().getTipoVeiculo().getValorDiario();
		
		for (ItemAdicional item : locacao.getItensAdicionais()) {
			valorTotal += qtdeDias * item.getTipoItemAdicional().getValorDiario();
		}
		
		return valorTotal;
	}

	@Override
	public void excluir(Locacao locacao) {
		locacaoRepositorio.delete(locacao);
	}
	
	public List<Locacao> pesquisar(String cpf) {
		if (cpf == null || cpf.trim().isEmpty()) {
    		return locacaoRepositorio.findAll();
    	} else {
    		return locacaoRepositorio.buscarPorCPFCliente(cpf);
    	}
	}
	
	@Transactional
	public void devolver(Locacao locacao) {
		locacao.setDevolvido(true);
		locacaoRepositorio.save(locacao);
	}
}
