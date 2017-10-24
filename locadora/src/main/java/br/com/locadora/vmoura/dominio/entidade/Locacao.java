package br.com.locadora.vmoura.dominio.entidade;

import java.util.Date;
import java.util.List;

public class Locacao extends ObjetoPersistente {
	
	private Cliente cliente;
	private Date dataRetirada;
	private Date dataEntrega;
	private Veiculo veiculo;
	private Double valor;
	private String tipoKilometragem;
	private List<ItemAdicional> itensAdicionais;
	
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

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getTipoKilometragem() {
		return tipoKilometragem;
	}

	public void setTipoKilometragem(String tipoKilometragem) {
		this.tipoKilometragem = tipoKilometragem;
	}

	public List<ItemAdicional> getItensAdicionais() {
		return itensAdicionais;
	}

	public void setItensAdicionais(List<ItemAdicional> itensAdicionais) {
		this.itensAdicionais = itensAdicionais;
	}
	
}
