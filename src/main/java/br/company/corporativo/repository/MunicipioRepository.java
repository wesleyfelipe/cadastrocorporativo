package br.company.corporativo.repository;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.company.corporativo.entity.Municipio;
import br.company.corporativo.entity.UnidadeFederativa;

@Stateless
public class MunicipioRepository extends AbstractCrudJPARepository<Municipio> implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager entityManager;

	public MunicipioRepository() {
		super(Municipio.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public List<Municipio> findAll(UnidadeFederativa uf) {
		TypedQuery<Municipio> query = entityManager.createNamedQuery("Municipio.findAllByUf", Municipio.class);
		query.setParameter("uf", uf);
		return query.getResultList();
	}

}
