package br.com.locadora.vmoura.dominio.entidade;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="TIV_TIPO_VEICULO")
@AttributeOverrides({  
    @AttributeOverride(name="codigo", column=@Column(name="TIV_ID")),  
    @AttributeOverride(name="dataHoraAtualizacao", column=@Column(name="TIV_DH_ATUALIZACAO"))  
})
public class TipoVeiculo extends ObjetoPersistente {
	
	@Column(name = "TIV_NOME")
	private String nome;
	
	@Column(name = "TIV_DESCRICAO")
	private String descricao;
	
	@Column(name = "TIV_VL_DIARIO")
	private Double valorDiario;
	
	@Column(name = "TIV_VL_KM")
	private Double valorKilometragem;
	
	@Column(name = "TIV_QTD_TOTAL")
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
