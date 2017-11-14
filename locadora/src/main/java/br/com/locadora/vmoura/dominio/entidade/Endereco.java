package br.com.locadora.vmoura.dominio.entidade;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="END_ENDERECO")  
@AttributeOverrides({  
    @AttributeOverride(name="codigo", column=@Column(name="END_ID")),  
    @AttributeOverride(name="dataHoraAtualizacao", column=@Column(name="END_DH_ATUALIZACAO"))  
})
public class Endereco extends ObjetoPersistente {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6942789943442090124L;

	@OneToOne
	@PrimaryKeyJoinColumn
	private Cliente cliente;
	
	@Column(name = "END_LOGRADOURO")
	private String logradouro;
	
	@Column(name = "END_NUMERO")
	private String numero;
	
	@Column(name = "END_COMPLEMENTO")
	private String complemento;
	
	@Column(name = "END_CEP")
	private String cep;
	
	@Column(name = "END_BAIRRO")
	private String bairro;
	
	@Column(name = "END_CIDADE")
	private String cidade;
	
	@Column(name = "END_ESTADO")
	private String estado;
	
	public String getLogradouro() {
		return logradouro;
	}
	
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	
	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	public String getCep() {
		return cep;
	}
	
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public String getBairro() {
		return bairro;
	}
	
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public String getCidade() {
		return cidade;
	}
	
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
