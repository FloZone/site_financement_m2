package managers.hibernate;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import managers.interfaces.CompensationManager;
import beans.Compensation;
import beans.Donation;

@Stateless
public class CompensationManagerHibernate extends AbstractEJBManagerHibernate<Compensation> implements CompensationManager {

	public CompensationManagerHibernate() {
		super(Compensation.class);
	}

	@Override
	public Iterable<Compensation> getAllProjectCompensationsOrderByAmount(
			int projectId) {
		return entityManager.createQuery("SELECT DISTINCT c FROM Compensation c JOIN c.project p "
				+ "WHERE p.id = :projectId ORDER BY c.amount",
				Compensation.class)
				.setParameter("projectId", projectId)
				.getResultList();
	}
	
	@Override
	public Compensation getLinkedCompensation(Donation donation, String ... fields) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Compensation> cq = cb.createQuery(Compensation.class);
		Metamodel m = entityManager.getMetamodel();
		
		EntityType<Compensation> Compensation_ = m.entity(Compensation.class);
		Root<Compensation> root = cq.from(Compensation_);
		
		setFetchCriteria(root, fields);
		
		cq.where(cb.equal(root.get("project"), donation.getProject()),
				 cb.le(root.get(Compensation_.getSingularAttribute("amount", Integer.class)), donation.getAmount()));
		cq.orderBy(cb.desc(root.get("amount")));
		
		Query q = entityManager.createQuery(cq);
		q.setMaxResults(1);
		
		List res = q.getResultList();
		return (Compensation) (res.size() > 0 ? res.get(0) : null);
	}
}
