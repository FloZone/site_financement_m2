package managers.interfaces;

import java.util.List;

import javax.ejb.Stateless;

import beans.Project;

@Stateless
public interface ProjectManager extends DefaultManager<Project> {
	// Méthodes spécifiques à l'entité 'Project' à déclarer ici :
	/**
	 * Get projects where the title correspond to the given keyword
	 * @param keyword Keyword filter
	 * @return Returns a list of project which correspond to the keyword
	 */
	Iterable<Project> getProjectsBy(String keyword);
	
	/**
	 * Get projects where the title correspond to the given keyword
	 * and where at least one category is in the given categories list
	 * @param keyword Keyword filter
	 * @param categoriesIds categories filter
	 * @return Returns a list of project which correspond to the keyword and categories
	 */
	Iterable<Project> getProjectsBy(String keyword, List<Integer> categoriesIds);
	
	/**
	 * Get projects where at least one category is in the given categories list
	 * @param categoriesIds categories filter
	 * @return Returns a list of project which correspond and categories
	 */
	Iterable<Project> getProjectsBy(List<Integer> categoriesIds);
}
