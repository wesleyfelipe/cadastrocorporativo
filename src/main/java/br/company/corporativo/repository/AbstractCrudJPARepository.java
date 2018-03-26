package br.company.corporativo.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;

import br.company.corporativo.entity.AbstractEntity;
import br.company.corporativo.enums.SortOrder;

public abstract class AbstractCrudJPARepository<T extends AbstractEntity> {

	protected Class<T> entityClass;

	protected AbstractCrudJPARepository() {
		super();
	}

	public AbstractCrudJPARepository(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	protected abstract EntityManager getEntityManager();

	public T save(T entity) {
		if (entity.getId() == null) {
			getEntityManager().persist(entity);
		} else {
			return getEntityManager().merge(entity);
		}
		return entity;
	}

	public void delete(T entity) {
		if (entity.getId() != null) {
			T newEntity = getEntityManager().find(entityClass, entity.getId());
			getEntityManager().remove(newEntity);
		}
	}

	public T findOne(Serializable id) {
		return getEntityManager().find(entityClass, id);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Long countAll() {
		CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
		cq.select(getEntityManager().getCriteriaBuilder().count(rt));
		javax.persistence.Query q = getEntityManager().createQuery(cq);
		return (Long) q.getSingleResult();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> findAll() {
		CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		return getEntityManager().createQuery(cq).getResultList();
	}
	
	protected Order getOrder(CriteriaBuilder builder, Path<Object> path, SortOrder sortOrder) {
		return sortOrder == SortOrder.ASC ? builder.asc(path) : builder.desc(path);
	}

}