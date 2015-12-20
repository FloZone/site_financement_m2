package ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import managers.interfaces.CategoryManager;
import managers.interfaces.TopProjectManager;
import beans.Category;
import beans.Project;
import beans.TopProject;

/**
 * Session Bean implementation class AdministrationManagement
 */
@Stateless
@LocalBean
public class AdministrationManagement {
	
	@EJB
	CategoryManager categoryManager;
	
	@EJB
	TopProjectManager topProjectManager;

    /**
     * Default constructor. 
     */
    public AdministrationManagement() {
        
    }
    
    /// Category ///
    
    public Category getCategory(int id, String ... fields) {
    	return categoryManager.get(id, fields);
    }
    
    public Iterable<Category> getAllCategories(String ... fields) {
    	return categoryManager.getAll(fields);
    }
    
    public Iterable<Category> getSomeCategories(int from, int to, String ... fields) {
    	return categoryManager.getSome(from, to, fields);
    }

    /**
     * Create a category
     * @return
     */
    public Category createCategory(String name) {
    	if (name == null || name.equals("")) {
    		return null;
    	}
    	
    	Category created = null;
    	try {
    		return categoryManager.create(name);
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    		created = null;
    	}
    	
    	return created;
    }
    
    /**
     * Delete a category
     * @return
     */
    public boolean deleteCategory(int categoryId) {
    	return categoryManager.delete(categoryManager.getReference(categoryId));
    }
    
    /**
     * Update category information
     * @return
     */
    public boolean updateCategory(Category categoryToUpdate) {
    	return categoryManager.update(categoryToUpdate);
    }
    
    
    /// TopProject ///
    
	public Iterable<TopProject> getAllTopProjects(String ... fields) {
		return topProjectManager.getAll(fields);
	}
	
	public Iterable<TopProject> getSomeTopProjects(int from, int to, String ... fields) {
		return topProjectManager.getSome(from, to, fields);
	}
	
	public TopProject getTopProject (int id, String ... fields) {
		return topProjectManager.get(id, fields);
	}
	
    public TopProject createTopProject(Project project, String description) {
    	return topProjectManager.create(project, description);
    }
    
    public boolean updateTopProject(TopProject topProjectToUpdate) {
    	return topProjectManager.update(topProjectToUpdate);
    }
    
    public boolean deleteTopProject (int topProjectId) {
    	return topProjectManager.delete(topProjectManager.get(topProjectId));
    }
}
