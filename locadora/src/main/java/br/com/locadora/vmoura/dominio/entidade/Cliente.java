package br.com.locadora.vmoura.dominio.entidade;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="CLI_CLIENTE")
@AttributeOverrides({  
    @AttributeOverride(name="codigo", column=@Column(name="CLI_ID")),  
    @AttributeOverride(name="dataHoraAtualizacao", column=@Column(name="CLI_DH_ATUALIZACAO"))  
})
public class Cliente extends ObjetoPersistente {
	
	@OneToOne(targetEntity = Endereco.class, mappedBy="cliente", cascade = CascadeType.ALL)
	private Endereco endereco;
	
	@Column(name = "CLI_TELEFONE")
	private String telefone;
	
	@Column(name = "CLI_NOME")
	private String nome;
	
	@Column(name = "CLI_CPF")
	private String cpf;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "CLI_DT_NASCIMENTO")
	private Date dataNascimento;
	
	@Column(name = "CLI_RG")
	private String rg;
	
	@Column(name = "CLI_EMAIL")
	private String email;
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
