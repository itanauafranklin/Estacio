package br.com.locadora.vmoura.dominio.servico;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.locadora.vmoura.dominio.entidade.Cliente;
import br.com.locadora.vmoura.dominio.entidade.Locacao;
import br.com.locadora.vmoura.dominio.repositorio.ClienteRepositorio;
import br.com.locadora.vmoura.dominio.repositorio.LocacaoRepositorio;

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
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente não encontrado."));
			FacesContext.getCurrentInstance().validationFailed();
			isValido = false;
		}
		if (isValido) {
//			locacao.setValor(locacao.getCalcularValorTotal());
		}
	}

//	private Double calcularValorTotal(Locacao locacao) {
//		return locacao.getDataRetirada() locacao.getDataEntrega() locacao.getItensAdicionais() locacao.getVeiculo().getTipoVeiculo().getValorDiario();
//	}

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
