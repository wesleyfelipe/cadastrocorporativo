package br.company.corporativo.repository;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.company.corporativo.entity.PessoaFisica;

@Stateless
public class PessoaFisicaRepository extends AbstractCrudJPARepository<PessoaFisica> implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager entityManager;

	public PessoaFisicaRepository() {
		super(PessoaFisica.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
