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
@Table(name="RES_RESERVA")
@AttributeOverrides({  
    @AttributeOverride(name="codigo", column=@Column(name="RES_ID")),  
    @AttributeOverride(name="dataHoraAtualizacao", column=@Column(name="RES_DH_ATUALIZACAO"))  
})
public class Reserva extends ObjetoPersistente {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9129479598015697193L;

	@PrimaryKeyJoinColumn
	@ManyToOne(targetEntity = Cliente.class, optional = false)
	private Cliente cliente;
	
	@Column(name = "RES_DT_RETIRADA")
	private Date dataRetirada;
	
	@Column(name = "RES_DT_ENTREGA")
	private Date dataEntrega;
	
	@PrimaryKeyJoinColumn
	@ManyToOne(targetEntity = TipoVeiculo.class, optional = false)
	private TipoVeiculo tipoVeiculo;
	
	@Column(name = "RES_TP_KM")
	private String tipoKilometragem;
	
	@Column(name = "RES_VALOR")
	private Double valor;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	   @JoinTable(name = "RES_ITA", uniqueConstraints = {@UniqueConstraint(columnNames = {"RES_ID", "ITA_ID"})}, joinColumns = {
	           @JoinColumn(name = "RES_ID")}, inverseJoinColumns = {@JoinColumn(name = "ITA_ID", nullable = false)})
	private List<TipoItemAdicional> tiposItensAdicionais;
	
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

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}
	
	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Transient
	public Double getCalcularValorTotal() {
		Double valorTotal = 0.0;
		
		if (tipoVeiculo != null) {
			valorTotal += tipoVeiculo.getValorDiario();
		}
		
		return valorTotal;
	}

}
