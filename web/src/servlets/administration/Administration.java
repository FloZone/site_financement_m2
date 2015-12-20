package servlets.administration;

import java.io.IOException;
import java.util.Collection;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Category;
import beans.TopProject;
import ejb.Facade;

/**
 * Servlet implementation class Administration
 */
@WebServlet(name="/Administration", urlPatterns={"/Administration"})
public class Administration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	Facade facade;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Administration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doIt(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// buttonAddCat
		String buttonAdmin = request.getParameter("buttonAdmin");
		// Si le bouton a été trouvé
		if(buttonAdmin != null) {
			if (buttonAdmin.equals("buttonAddCat")) {
				facade.createCategory(request.getParameter("nameCat"));
			}
			else if (buttonAdmin.equals("buttonDeleteTp")) {
				facade.deleteTopProjectById(Integer.parseInt(request.getParameter("topProject")));
			}
			else if (buttonAdmin.equals("buttonDeleteCat")) {
				facade.deleteCategoryById(Integer.parseInt(request.getParameter("category")));
			}
		}
		
		doIt(request, response);
	}
	
	private void doIt(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String[] topProjectFields = {"project"};
		
		Iterable <TopProject> topProjects = facade.getAllTopProjects(topProjectFields);
		Iterable <Category> categories = facade.getAllCategories();
		
		request.setAttribute("topProjects", topProjects);
		request.setAttribute("categories", categories);
		request.getRequestDispatcher("jsp/administration/homeAdmin.jsp").forward(request, response);
	}

}
