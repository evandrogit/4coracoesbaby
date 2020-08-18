package com.webapp.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.webapp.model.Bairro;
import com.webapp.repository.filter.BairroFilter;
import com.webapp.util.jpa.Transacional;

public class Bairros implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Bairro porId(Long id) {
		return this.manager.find(Bairro.class, id);
	}
	
	@Transacional
	public Bairro save(Bairro bairro) {
		return this.manager.merge(bairro);
	}

	@Transacional
	public void remove(Bairro bairro) {
		Bairro bairroTemp = new Bairro();
		bairroTemp = this.manager.merge(bairro);

		this.manager.remove(bairroTemp);
	}
	
	public List<Bairro> todos() {
		return this.manager.createQuery("from Bairro order by nome", Bairro.class).getResultList();
	}
	
	public Bairro porNome(String nome) {
		try {
			return this.manager.createQuery("from Bairro i where upper(i.nome) = :nome", Bairro.class)
					.setParameter("nome", nome.toUpperCase()).getSingleResult();
		} catch(NoResultException e) {
			return null;
		}
	}
	
	public List<Bairro> filtrados(BairroFilter filter) {
		return this.manager.createQuery("from Bairro i where upper(i.nome) like :nome order by nome", Bairro.class)
				.setParameter("nome", "%" + filter.getNome().toUpperCase() + "%").getResultList();
	}
	
	
	public Bairro porNomeCadastrado(Bairro bairro) {
		try {
			return this.manager
					.createQuery("from Bairro e where upper(e.nome) = :nome and e.id != :id", Bairro.class)
					.setParameter("nome", bairro.getNome().toUpperCase())
					.setParameter("id", bairro.getId()).getSingleResult();
		} catch(NoResultException e) {
			return null;
		}
	}
	
}