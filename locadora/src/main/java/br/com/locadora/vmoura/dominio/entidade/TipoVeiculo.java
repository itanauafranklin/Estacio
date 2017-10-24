package br.com.locadora.vmoura.dominio.entidade;

public class TipoVeiculo extends ObjetoPersistente {
	
	private String nome;
	private String descricao;
	private Double valorDiario;
	private Double valorKilometragem;
	private Integer quantidadeTotal;
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValorDiario() {
		return valorDiario;
	}

	public void setValorDiario(Double valorDiario) {
		this.valorDiario = valorDiario;
	}

	public Double getValorKilometragem() {
		return valorKilometragem;
	}

	public void setValorKilometragem(Double valorKilometragem) {
		this.valorKilometragem = valorKilometragem;
	}

	public Integer getQuantidadeTotal() {
		return quantidadeTotal;
	}

	public void setQuantidadeTotal(Integer quantidadeTotal) {
		this.quantidadeTotal = quantidadeTotal;
	}

}
