package br.company.corporativo.repository;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.company.corporativo.entity.TipoDependencia;

@Stateless
public class TipoDependenciaRepository extends AbstractCrudJPARepository<TipoDependencia> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public TipoDependenciaRepository() {
		super(TipoDependencia.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
