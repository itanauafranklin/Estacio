package br.com.locadora.vmoura.dominio.entidade;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="TIA_TIPO_ITEM_ADICIONAL")
@AttributeOverrides({  
    @AttributeOverride(name="codigo", column=@Column(name="TIA_ID")),  
    @AttributeOverride(name="dataHoraAtualizacao", column=@Column(name="TIA_DH_ATUALIZACAO")),
    @AttributeOverride(name="excluido", column=@Column(name="TIA_EXCLUIDO"))
})
public class TipoItemAdicional extends ObjetoPersistente {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6756455622776526440L;

	@Column(name = "TIA_NOME")
	private String nome;

	@Column(name = "TIA_DESCRICAO")
	private String descricao;

	@Column(name = "TIA_VL_DIARIO")
	private Double valorDiario;

	@Column(name = "TIA_QT_TOTAL")
	private Integer quantidadeTotal;
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getValorDiario() {
		return valorDiario;
	}

	public void setValorDiario(Double valorDiario) {
		this.valorDiario = valorDiario;
	}

	public Integer getQuantidadeTotal() {
		return quantidadeTotal;
	}

	public void setQuantidadeTotal(Integer quantidadeTotal) {
		this.quantidadeTotal = quantidadeTotal;
	}
	
	public String getValorDiarioFormatado() {
		String valorFormatado = "R$ " + getValorDiario() + " p/ dia";
		return valorFormatado.replace(".", ",");
	}
	
	@Transient 
	public String getNomeFormatado() {
		return getNome() + " (" + getValorDiarioFormatado() + ")";
	}
	
}
