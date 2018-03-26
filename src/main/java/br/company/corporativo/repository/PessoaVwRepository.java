package br.company.corporativo.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;

import br.company.corporativo.dto.FiltroPessoaDTO;
import br.company.corporativo.entity.Municipio;
import br.company.corporativo.entity.UnidadeFederativa;
import br.company.corporativo.entity.vw.PessoaVw;
import br.company.corporativo.enums.SortOrder;
import br.company.corporativo.enums.TipoPessoaEnum;

@Stateless
public class PessoaVwRepository extends AbstractCrudJPARepository<PessoaVw> implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager entityManager;

	public PessoaVwRepository() {
		super(PessoaVw.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	private List<Predicate> getListaPredicates(FiltroPessoaDTO filtro, CriteriaBuilder builder, Root<PessoaVw> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (StringUtils.isNotBlank(filtro.getNome())) {
			predicates.add(getPredicateLikeNome(filtro, builder, root));
		}

		if (filtro.getData() != null) {
			predicates.add(getPredicateData(filtro, builder, root));
		}

		if (filtro.getUnidadeFederativa() != null) {
			predicates.add(getPredicateUf(filtro, builder, root));
		}

		if (filtro.getMunicipio() != null) {
			predicates.add(getPredicateMunicipio(filtro, builder, root));
		}
		
		if(filtro.getTipoPessoa() != null) {
			predicates.add(getPredicateTipoPessoa(filtro, builder, root));
		}

		return predicates;
	}

	private Predicate getPredicateData(FiltroPessoaDTO filtro, CriteriaBuilder builder, Root<PessoaVw> root) {
		Calendar dateCalendar = Calendar.getInstance();
		dateCalendar.setTime(filtro.getData());
		
		return builder.equal(builder.function("trunc", Date.class, getPathData(root)), filtro.getData());
	}

	private Predicate getPredicateMunicipio(FiltroPessoaDTO filtro, CriteriaBuilder builder, Root<PessoaVw> root) {
		return builder.equal(getPathMunicipio(root), filtro.getMunicipio());
	}
	
	private Predicate getPredicateTipoPessoa(FiltroPessoaDTO filtro, CriteriaBuilder builder, Root<PessoaVw> root) {
		return builder.equal(getPathTipoPessoa(root), filtro.getTipoPessoa());
	}

	private Predicate getPredicateUf(FiltroPessoaDTO filtro, CriteriaBuilder builder, Root<PessoaVw> root) {
		return builder.equal(getPathUnidadeFederativa(root), filtro.getUnidadeFederativa());
	}

	private Predicate getPredicateLikeNome(FiltroPessoaDTO filtro, CriteriaBuilder builder, Root<PessoaVw> root) {
		return builder.like(builder.upper(getPathNome(root)), "%" + filtro.getNome().toUpperCase() + "%");
	}

	private Path<String> getPathNome(Root<PessoaVw> root) {
		return root.get("nome");
	}

	private Path<UnidadeFederativa> getPathUnidadeFederativa(Root<PessoaVw> root) {
		return getPathMunicipio(root).get("unidadeFederativa");
	}

	private Path<Municipio> getPathMunicipio(Root<PessoaVw> root) {
		return root.get("municipio");
	}
	
	private Path<TipoPessoaEnum> getPathTipoPessoa(Root<PessoaVw> root) {
		return root.get("tipoPessoa");
	}

	private Path<Calendar> getPathData(Root<PessoaVw> root) {
		return root.get("dataNascimento");
	}
	
	public int count(FiltroPessoaDTO filtro) {
		
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> qry = builder.createQuery(Long.class);
		Root<PessoaVw> root = qry.from(entityClass);

		List<Predicate> predicates = getListaPredicates(filtro, builder, root);
		if (!predicates.isEmpty()) {
			qry.where(predicates.toArray(new Predicate[predicates.size()]));
		}

		qry.select(builder.count(root));

		return getEntityManager().createQuery(qry).getSingleResult().intValue();
		
	}

	@SuppressWarnings("unchecked")
	public List<PessoaVw> findAll(FiltroPessoaDTO filtro, SortOrder order, String sortField, Integer first,
			Integer pageSize) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<PessoaVw> qry = builder.createQuery(PessoaVw.class);
		Root<PessoaVw> root = qry.from(PessoaVw.class);

		List<Predicate> predicates = getListaPredicates(filtro, builder, root);
		if (!predicates.isEmpty()) {
			qry.where(predicates.toArray(new Predicate[predicates.size()]));
		}

		if (sortField != null) {
			qry.orderBy(getOrder(builder, root.get(sortField), order));
		}

		qry.select(root);
		
		Query query = getEntityManager().createQuery(qry);
		if(first != null && pageSize != null) {
			query.setFirstResult(first).setMaxResults(pageSize);
		}
		return query.getResultList();

	}
	
	public List<PessoaVw> findAll(FiltroPessoaDTO filtro, SortOrder order, String sortField) {
		return findAll(filtro, order, sortField, null, null);

	}

}
