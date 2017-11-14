package br.com.locadora.vmoura.dominio.entidade;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="VEI_VEICULO")  
@AttributeOverrides({  
    @AttributeOverride(name="codigo", column=@Column(name="VEI_ID")),  
    @AttributeOverride(name="dataHoraAtualizacao", column=@Column(name="VEI_DH_ATUALIZACAO"))  
})
public class Veiculo extends ObjetoPersistente {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5575276363996769910L;

	@Column(name = "VEI_FABRICANTE")
	private String fabricante;
	
	@Column(name = "VEI_MODELO")
	private String modelo;

	@Column(name = "VEI_ANO_FABRICACAO")
	private Integer anoFabricacao;

	@Column(name = "VEI_ANO_MODELO")
	private String anoModelo;

	@Column(name = "VEI_COR")
	private String cor;

	@Column(name = "VEI_PLACA")
	private String placa;

	@Column(name = "VEI_TIPO_COMBUSTIVEL")
	private String tipoCombustivel;

	@Column(name = "VEI_CHASSI")
	private String chassi;

	@PrimaryKeyJoinColumn
	@ManyToOne(targetEntity = TipoVeiculo.class, optional = false)
	private TipoVeiculo tipoVeiculo;
	
	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Integer getAnoFabricacao() {
		return anoFabricacao;
	}

	public void setAnoFabricacao(Integer anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	public String getAnoModelo() {
		return anoModelo;
	}

	public void setAnoModelo(String anoModelo) {
		this.anoModelo = anoModelo;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getTipoCombustivel() {
		return tipoCombustivel;
	}

	public void setTipoCombustivel(String tipoCombustivel) {
		this.tipoCombustivel = tipoCombustivel;
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public TipoVeiculo getTipoVeiculo() {
		return tipoVeiculo;
	}

	public void setTipoVeiculo(TipoVeiculo tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}
	
}
