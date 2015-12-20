package managers.interfaces;

import javax.ejb.Stateless;

import beans.Category;

@Stateless
public interface CategoryManager extends DefaultManager<Category> {
	// Méthodes spécifiques à l'entité 'Category' à déclarer ici :
}
