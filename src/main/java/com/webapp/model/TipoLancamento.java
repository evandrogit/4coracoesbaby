package com.webapp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "tipos_lancamentos")
public class TipoLancamento implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotBlank
	private String descricao;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 10)
	private OrigemLancamento origem;
	
	@Id
	@GeneratedValue//(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable = false, length = 60)
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String nome) {
		this.descricao = nome;
	}	

	public OrigemLancamento getOrigem() {
		return origem;
	}

	public void setOrigem(OrigemLancamento origem) {
		this.origem = origem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		TipoLancamento other = (TipoLancamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}