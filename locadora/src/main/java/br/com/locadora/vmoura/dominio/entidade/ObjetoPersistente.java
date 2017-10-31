package br.com.locadora.vmoura.dominio.entidade;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Inheritance
public class ObjetoPersistente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo", nullable = false)
	private int codigo;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dataHoraAtualizacao")
	private Date dataHoraAtualizacao;
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public Date getDataHoraAtualizacao() {
		return dataHoraAtualizacao;
	}
	
	public void setDataHoraAtualizacao(Date dataHoraAtualizacao) {
		this.dataHoraAtualizacao = dataHoraAtualizacao;
	}

}
