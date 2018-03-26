package br.company.corporativo.repository;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.company.corporativo.entity.PessoaJuridica;

@Stateless
public class PessoaJuridicaRepository extends AbstractCrudJPARepository<PessoaJuridica> implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager entityManager;

	public PessoaJuridicaRepository() {
		super(PessoaJuridica.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
