package br.com.locadora.vmoura.dominio.entidade;

import java.util.Date;
import java.util.List;

public class Reserva extends ObjetoPersistente {
	
	private Cliente cliente;
	private Date dataRetirada;
	private Date dataEntrega;
	private TipoVeiculo tipoVeiculo;
	private String tipoKilometragem;
	private List<TipoItemAdicional> tiposItensAdicionais;
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getDataRetirada() {
		return dataRetirada;
	}

	public void setDataRetirada(Date dataRetirada) {
		this.dataRetirada = dataRetirada;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public TipoVeiculo getTipoVeiculo() {
		return tipoVeiculo;
	}

	public void setTipoVeiculo(TipoVeiculo tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}

	public String getTipoKilometragem() {
		return tipoKilometragem;
	}

	public void setTipoKilometragem(String tipoKilometragem) {
		this.tipoKilometragem = tipoKilometragem;
	}

	public List<TipoItemAdicional> getTiposItensAdicionais() {
		return tiposItensAdicionais;
	}

	public void setTiposItensAdicionais(List<TipoItemAdicional> tiposItensAdicionais) {
		this.tiposItensAdicionais = tiposItensAdicionais;
	}

}
