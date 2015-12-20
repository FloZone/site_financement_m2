package managers.hibernate;

import javax.ejb.Stateless;

import managers.interfaces.TopProjectManager;
import beans.TopProject;

@Stateless
public class TopProjectManagerHibernate extends AbstractEJBManagerHibernate<TopProject> implements TopProjectManager {
	
	public TopProjectManagerHibernate() {
		super(TopProject.class);
	}
}
