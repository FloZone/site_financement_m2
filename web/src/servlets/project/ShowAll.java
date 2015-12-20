package servlets.project;

import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Category;
import beans.Project;
import ejb.Facade;

/**
 * Servlet implementation class ShowAll
 */
@WebServlet("Project/ShowAll")
public class ShowAll extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private Facade facade;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowAll() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO : Remplacer les valeurs en dur par un calcul des projets à afficher
		Iterable<Project> projects = facade.getSomeProjects(0, 10);
		Iterable<Category> allCategories = facade.getAllCategories();

//		int projectsCount = facade.getProjectsCount();
//		request.setAttribute("projectsCount", projectsCount);
		
		doIt(request, response, projects, allCategories);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Attention, si une catégorie est créé pendant que l'utilisateur consulte la page, la nouvelle catégorie n'est pas prise en compte
		Iterable<Project> projects;
		Iterable<Category> allCategories = facade.getAllCategories();
		ArrayList<Integer> selectedCatIds = new ArrayList<Integer>();
		String keyword = request.getParameter("search").toString();

		// Gérer les catégories	
		// Parcourir toutes les catégories en base
		for(Category category : allCategories) {
			// si la catégorie courante est cochée, l'ajouter à notre liste d'id
			if( request.getParameter(category.getId().toString()) != null ) {
				selectedCatIds.add(category.getId());
			}
		}
		
//		int projectsCount;
		
		// S'il y a quelque chose dans la recherche
		if (!keyword.isEmpty()) {
			// Si aucune catégorie n'est cochée
			if (selectedCatIds.isEmpty()) {
				// On affiche les projets correspondant au champ recherche
				projects = facade.getSomeProjectsBy(keyword);
			}
			// Sinon au moins une catégorie a été cochée
			else {
				// On affiche les projets correspondant à la recher et
				// Ayant au moins l'une des catégories cochées
				projects = facade.getSomeProjectsBy(keyword, selectedCatIds); 
			}
		}
		// Sinon aucun mot clé n'est rentré. Si des catégories ont été cochées
		else if (!selectedCatIds.isEmpty()) {
			projects = facade.getSomeProjectsBy(selectedCatIds);
		}
		// Sinon aucun critère de filtre n'a été utilisé
		else {
			// Afficher tous les projets
			projects = facade.getSomeProjects(0, 10);
		}
		
//		projectsCount = facade.getProjectsCount();
		
		doIt(request, response, projects, allCategories);
	}
	
	private void doIt(HttpServletRequest request, HttpServletResponse response,
			Iterable<Project> projects, Iterable<Category> categories) throws ServletException, IOException {
		request.setAttribute("categories", categories);
		request.setAttribute("projects", projects);
		request.getRequestDispatcher("/jsp/project/showAll.jsp").forward(request, response);
	}

}
