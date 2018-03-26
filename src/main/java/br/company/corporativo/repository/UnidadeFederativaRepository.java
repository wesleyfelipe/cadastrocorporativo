package br.company.corporativo.repository;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.company.corporativo.entity.UnidadeFederativa;

@Stateless
public class UnidadeFederativaRepository extends AbstractCrudJPARepository<UnidadeFederativa> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public UnidadeFederativaRepository() {
		super(UnidadeFederativa.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
