package br.com.locadora.vmoura.dominio.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ObjetoPersistente implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2053375431519121659L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "codigo", nullable = false)
	private int codigo;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dataHoraAtualizacao", nullable = false)
	private Date dataHoraAtualizacao;
	
    @Column(name = "excluido", nullable = false)
	private Boolean excluido;
	
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

	public Boolean getExcluido() {
		return excluido;
	}

	public void setExcluido(Boolean excluido) {
		this.excluido = excluido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ObjetoPersistente other = (ObjetoPersistente) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}

}
