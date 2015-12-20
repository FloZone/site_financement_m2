package managers.hibernate;

import javax.ejb.Stateless;

import managers.interfaces.CategoryManager;
import beans.Category;

@Stateless
public class CategoryManagerHibernate extends AbstractEJBManagerHibernate<Category> implements CategoryManager {

	public CategoryManagerHibernate() {
		super(Category.class);
	}
}
