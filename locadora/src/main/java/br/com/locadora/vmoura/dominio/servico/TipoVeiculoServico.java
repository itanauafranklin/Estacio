package br.com.locadora.vmoura.dominio.servico;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.locadora.vmoura.dominio.entidade.TipoVeiculo;
import br.com.locadora.vmoura.dominio.repositorio.TipoVeiculoRepositorio;

@Service
public class TipoVeiculoServico extends AbstractServico<TipoVeiculo> {

	@Autowired
    private TipoVeiculoRepositorio tipoVeiculoRepositorio;
	
	@Override
	protected void salvar(TipoVeiculo tipoVeiculo) {
		tipoVeiculoRepositorio.save(tipoVeiculo);
	}

	@Override
	public void excluir(TipoVeiculo tipoVeiculo) {
		try {
			tipoVeiculoRepositorio.delete(tipoVeiculo);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensagem de erro", "Detalhe"));
			FacesContext.getCurrentInstance().validationFailed();
		}
	}
	
	public List<TipoVeiculo> pesquisar(String nome) {
		if (nome == null || nome.trim().isEmpty()) {
    		return buscarTodos();
    	} else {
    		return tipoVeiculoRepositorio.buscarPorNome(nome);
    	}
	}
	
	public List<TipoVeiculo> buscarTodos() {
		return tipoVeiculoRepositorio.findAll();
	}
}
