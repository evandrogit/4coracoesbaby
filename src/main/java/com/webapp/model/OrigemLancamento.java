package com.webapp.model;

public enum OrigemLancamento {

	DEBITO("Débito"), CREDITO("Crédito");

	private String descricao;

	OrigemLancamento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
