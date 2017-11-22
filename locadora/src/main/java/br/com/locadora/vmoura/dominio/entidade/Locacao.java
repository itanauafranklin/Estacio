package br.com.locadora.vmoura.dominio.entidade;

import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
@Table(name="LOC_LOCACAO")
@AttributeOverrides({  
    @AttributeOverride(name="codigo", column=@Column(name="LOC_ID")),  
    @AttributeOverride(name="dataHoraAtualizacao", column=@Column(name="LOC_DH_ATUALIZACAO"))  
})
public class Locacao extends ObjetoPersistente {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1229454931509831634L;

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
	
	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	   @JoinTable(name = "LOC_ITA", uniqueConstraints = {@UniqueConstraint(columnNames = {"LOC_ID", "ITA_ID"})}, joinColumns = {
	           @JoinColumn(name = "LOC_ID")}, inverseJoinColumns = {@JoinColumn(name = "ITA_ID", nullable = false)})
	private List<ItemAdicional> itensAdicionais;
	
	@Transient
	private String cpfCliente;
	
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

	public List<ItemAdicional> getItensAdicionais() {
		return itensAdicionais;
	}

	public void setItensAdicionais(List<ItemAdicional> itensAdicionais) {
		this.itensAdicionais = itensAdicionais;
	}
	
	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}
	
	@Transient
	public Double getCalcularValorTotal() {
		Double valorTotal = 0.0;
		
		if (veiculo != null) {
			valorTotal += veiculo.getValorDiario();
		}
		
		return valorTotal;
	}

	
}
