package br.com.locadora.vmoura.dominio.entidade;

import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name="LOC_LOCACAO")
@AttributeOverrides({  
    @AttributeOverride(name="codigo", column=@Column(name="LOC_ID")),  
    @AttributeOverride(name="dataHoraAtualizacao", column=@Column(name="LOC_DH_ATUALIZACAO"))  
})
public class Locacao extends ObjetoPersistente {
	
	@PrimaryKeyJoinColumn
	@ManyToOne(targetEntity = Cliente.class, optional = false)
	private Cliente cliente;
	
	@Column(name = "LOC_DT_RETIRADA")
	private Date dataRetirada;
	
	@Column(name = "LOC_DT_ENTREGA")
	private Date dataEntrega;
	
	@PrimaryKeyJoinColumn
	@ManyToOne(targetEntity = Veiculo.class, optional = false)
	private Veiculo veiculo;
	
	@Column(name = "LOC_VALOR")
	private Double valor;
	
	@Column(name = "LOC_TP_KM")
	private String tipoKilometragem;
	
	@ManyToMany
	   @JoinTable(name = "LOC_ITA", uniqueConstraints = {@UniqueConstraint(columnNames = {"LOC_ID", "ITA_ID"})}, joinColumns = {
	           @JoinColumn(name = "LOC_ID")}, inverseJoinColumns = {@JoinColumn(name = "ITA_ID", nullable = false)})
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
