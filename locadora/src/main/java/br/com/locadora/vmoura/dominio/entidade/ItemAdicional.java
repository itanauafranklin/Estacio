package br.com.locadora.vmoura.dominio.entidade;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="ITA_ITEM_ADICIONAL")
@AttributeOverrides({  
    @AttributeOverride(name="codigo", column=@Column(name="ITA_ID")),  
    @AttributeOverride(name="dataHoraAtualizacao", column=@Column(name="ITA_DH_ATUALIZACAO"))  
})
public class ItemAdicional extends ObjetoPersistente {
	
	@Column(name = "ITA_MARCA")
	private String marca;
	
	@Column(name = "ITA_COR")
	private String cor;
	
	@Column(name = "ITA_TAMANHO")
	private String tamanho;
	
	@Column(name = "ITA_ESPECIFICACAO")
	private String especificacao;
	
	@PrimaryKeyJoinColumn
    @ManyToOne(targetEntity = TipoItemAdicional.class, optional = false)
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