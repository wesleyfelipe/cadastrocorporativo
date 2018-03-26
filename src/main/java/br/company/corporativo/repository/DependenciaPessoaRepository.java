package br.company.corporativo.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.company.corporativo.entity.DependenciaPessoa;
import br.company.corporativo.entity.PessoaFisica;

@Stateless
public class DependenciaPessoaRepository extends AbstractCrudJPARepository<DependenciaPessoa> implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager entityManager;

	public DependenciaPessoaRepository() {
		super(DependenciaPessoa.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public List<DependenciaPessoa> findAllDependentes(PessoaFisica pf) {
		if(pf == null)
			return new ArrayList<>();
		
		TypedQuery<DependenciaPessoa> query = entityManager.createNamedQuery("DependenciaPessoa.findAllByDependenteDe",
				DependenciaPessoa.class);
		query.setParameter("dependenteDe", pf);
		return query.getResultList();
	}

}
