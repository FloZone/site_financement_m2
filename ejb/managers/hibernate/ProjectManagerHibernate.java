package managers.hibernate;

import java.util.List;

import javax.ejb.Stateless;

import managers.interfaces.ProjectManager;
import beans.Project;

@Stateless
public class ProjectManagerHibernate extends AbstractEJBManagerHibernate<Project> implements ProjectManager {
	
	public ProjectManagerHibernate() {
		super(Project.class);
	}
	
	@Override
	public Iterable<Project> getProjectsBy(String keyword) {
		return entityManager.createQuery("FROM Project p WHERE UPPER(p.title) LIKE UPPER(:keyword)",
				Project.class)
				.setParameter("keyword", "%" + keyword.toString() + "%")
				.getResultList();
	}
	
	@Override
	public Iterable<Project> getProjectsBy(String keyword, List<Integer> categoriesIds) {
		return entityManager.createQuery("SELECT DISTINCT p FROM Project p join p.categories pc "
				+ "WHERE UPPER(p.title) LIKE UPPER(:keyword) AND pc.id IN (:categories)",
				Project.class)
				.setParameter("keyword", "%" + keyword.toString() + "%")
				.setParameter("categories", categoriesIds)
				.getResultList();
	}
	
	@Override
	public Iterable<Project> getProjectsBy(List<Integer> categoriesIds) {
		return entityManager.createQuery("SELECT DISTINCT p FROM Project p join p.categories pc "
				+ "WHERE pc.id IN (:categories)",
				Project.class)
				.setParameter("categories", categoriesIds)
				.getResultList();
	}
	
}
