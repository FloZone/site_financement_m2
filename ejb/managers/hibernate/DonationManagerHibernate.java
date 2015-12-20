package managers.hibernate;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import managers.interfaces.DonationManager;
import beans.Compensation;
import beans.Donation;

@Stateless
public class DonationManagerHibernate extends AbstractEJBManagerHibernate<Donation> implements DonationManager {
	
	public DonationManagerHibernate() {
		super(Donation.class);
	}
}
