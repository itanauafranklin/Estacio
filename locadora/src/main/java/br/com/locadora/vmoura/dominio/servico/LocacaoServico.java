package br.com.locadora.vmoura.dominio.servico;

import java.util.List;

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
	
	@Override
	protected void salvar(Locacao locacao) {
		locacaoRepositorio.save(locacao);
	}

	public void validarInclusaoLocacao(Locacao locacao) {
		Cliente cliente = clienteRepositorio.buscarPorCPF(locacao.getCpfCliente());
		locacao.setCliente(cliente);
		boolean isValido = true;
		if (locacao.getCliente() == null) {
			adicionarMensagemErro("Cliente não encontrado.");
			isValido = false;
		}
		
		if (locacao.getDataRetirada().before(DataUtil.dataAtualMeiaNoite())) {
			adicionarMensagemErro("A 'Data de retirada' não pode ser menor que a data de hoje.");
			isValido = false;
		}
		
		if (locacao.getDataRetirada().after(locacao.getDataEntrega())) {
			adicionarMensagemErro("A 'Data de retirada' não pode ser maior que a 'Data de entrega'.");
			isValido = false;
		}
		
		if (locacao.getDataEntrega().before(locacao.getDataRetirada())) {
			adicionarMensagemErro("A 'Data de entrega' não pode ser menor que a 'Data de retirada'.");
			isValido = false;
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
}
