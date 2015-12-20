package servlets.project;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.EJB;
import javax.print.attribute.standard.MediaSize.ISO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Category;
import beans.Compensation;
import beans.Project;
import servlets.RedirectServlet;
import sun.awt.image.OffScreenImage;
import ejb.Facade;

/**
 * Servlet implementation class Create
 */
@WebServlet(name="Project/Edit", urlPatterns={ "/Project/Edit" })
public class Edit extends RedirectServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	Facade facade;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Edit() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// première arrivée de sur la page
		
		String projectId = request.getParameter("id");
		
		// id inexistant
		if(projectId == null) {
			redirectTo(servlets.user.Show.class, response);
		}
		else {
			// essayer de récupérer le projet
			try {
		    	String[] projectFields = {"compensations", "categories", "donations", "categories"};
				Project project = facade.getProjectById(Integer.parseInt(projectId), projectFields);

				// si l'utilisateur n'est pas le créateur
				if(project.getUser().getId() != (Integer)request.getSession().getAttribute("userId")) {
					redirectTo(servlets.user.Show.class, response);
				}
				// sinon éditer le projet
				else {
					request.setAttribute("project", project);
					request.setAttribute("categories", facade.getAllCategories());
					request.getRequestDispatcher("/jsp/project/edit.jsp").forward(request, response);
				}
				
			}
			// si le projet n'existe pas
			catch(Exception e) {
				redirectTo(servlets.user.Show.class, response);
			}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// modification du projet
		boolean success = true;
		String idStr = request.getParameter("id");
		String description = request.getParameter("description");
		String amountDescription = request.getParameter("amountDescription");
		String image = request.getParameter("image");
		
		// erreur si l'id n'existe pas
		/*if(idStr == null || idStr.equals("")) {}
		else {}*/
		
		String[] projectFields = {"compensations", "categories", "donations", "categories"};
		Project project = facade.getProjectById(Integer.parseInt(idStr), projectFields);
		
		HashMap<String, String> parameters = new HashMap<String, String>();
		parameters.put("id", project.getId().toString());
		
		// mise à jour de la base du projet
		if(facade.updateProject(project, description, amountDescription, image)) {
			parameters.put("message", "1");
		}
		else {
			parameters.put("message", "2");
		}


		// récupérer les catégories cochées
		Set<Category> categories =  new HashSet<Category>();
		String[] selectedCategoriesStr = request.getParameterValues("categories");
		// s'il y a de nouvelles catégories
		if (selectedCategoriesStr != null && selectedCategoriesStr.length > 0) {
			int[] selectedCategories = new int[selectedCategoriesStr.length];
			for (int i = 0 ; i < selectedCategoriesStr.length ; ++i) {
				selectedCategories[i] = Integer.parseInt(selectedCategoriesStr[i]);
			}
			
			for (int id : selectedCategories) {
				categories.add(facade.getCategoryById(id));
			}
			// supprimer les catégories
			project.setCategories(new HashSet<Category>());
			// ajouter les nouvelles catégories
			facade.updateProjectCategories(project, categories);
		}
		// s'il faut supprimer toutes les catégories
		else {
			System.out.println("SUPRIIIIIME TOUUUUUT");
			facade.removeProjectCategories(project);
		}

		
		
		// récupérer les nouvelles compensations
		int compensationNumber = new Integer(request.getParameter("compensationNumber"));
		for(int i = 1; i <= compensationNumber; ++i) {
			// vérifier qu'elle existe et que l'utilisateur à bien rempli les champs
			if( request.getParameter("compensationAmount"+i) != null && request.getParameter("compensationAmount"+i).length() != 0 &&
					request.getParameter("compensationDescription"+i) != null && request.getParameter("compensationDescription"+i).length() != 0) {
				// récupérer les infos
				int compensationAmount = new Integer(request.getParameter("compensationAmount"+i));
				String compensationDescription = (String) request.getParameter("compensationDescription"+i);
				// ajouter la compensation en base
				Compensation newCompensation = facade.createCompensation(compensationAmount, compensationDescription, project);
			}
		}
	
		redirectTo(servlets.project.Edit.class, response, parameters);
	}
}
