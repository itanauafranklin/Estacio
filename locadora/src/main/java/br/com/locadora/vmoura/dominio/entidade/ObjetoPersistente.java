package br.com.locadora.vmoura.dominio.entidade;

import java.util.Date;

public class ObjetoPersistente {
	
	private int codigo;
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
