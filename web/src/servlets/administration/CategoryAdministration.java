package servlets.administration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Category;
import ejb.Facade;
import servlets.RedirectServlet;

/**
 * Servlet implementation class CategoryAdministration
 */
@WebServlet(name="/Administration/Category", urlPatterns={"/Administration/Category"})
public class CategoryAdministration extends RedirectServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	Facade facade;

    /**
     * Default constructor. 
     */
    public CategoryAdministration() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Iterable<Category> categories = facade.getAllCategories();
		request.setAttribute("categories", categories);
		request.getRequestDispatcher("/jsp/administration/category.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String categoryId = request.getParameter("categoryId");
		String categoryName = request.getParameter("name");
		
		Map<String, String> parameters = new HashMap<String, String>();
		
		// 1er cas: on veux ajouter une categorie
		if (categoryName != null && !categoryName.isEmpty()) {
			Category created = facade.createCategory(categoryName);
			if (created == null) {
				parameters.put("creationError", "notUnique");
			}
		}
		// 2nd cas: on veut supprimer une catégorie
		else if (categoryId != null && !categoryId.isEmpty()) {
			System.out.println("CATEGORY DELETE : " + categoryId);
			boolean delete = facade.deleteCategoryById(Integer.parseInt(categoryId));
			System.out.println("SUPRESSION : " + delete);
		}
		
		redirectTo(CategoryAdministration.class, response, parameters);
	}

}
