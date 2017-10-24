package br.com.locadora.vmoura.dominio.entidade;

public class ItemAdicional extends ObjetoPersistente {
	
	private String marca;
	private String cor;
	private String tamanho;
	private String especificacao;
	private TipoItemAdicional tipoItemAdicional;
	
	public String getCor() {
		return cor;
	}
	
	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	public String getEspecificacao() {
		return especificacao;
	}

	public void setEspecificacao(String especificacao) {
		this.especificacao = especificacao;
	}

	public TipoItemAdicional getTipoItemAdicional() {
		return tipoItemAdicional;
	}

	public void setTipoItemAdicional(TipoItemAdicional tipoItemAdicional) {
		this.tipoItemAdicional = tipoItemAdicional;
	}

}
