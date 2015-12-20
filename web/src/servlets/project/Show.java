package servlets.project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlets.RedirectServlet;
import utils.MessageHierarchy;
import beans.Answer;
import beans.Compensation;
import beans.Project;
import ejb.Facade;

/**
 * Servlet implementation class Show
 */
@WebServlet(name = "Project/Show", urlPatterns = { "/Project/Show" })
public class Show extends RedirectServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	Facade facade;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Show() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int projectId = Integer.parseInt(request.getParameter("id"));
    	String[] projectFields = {"donations", "comments", "compensations", "categories"};
		// Récupération du projet
    	Project project = facade.getProjectById(projectId, projectFields);

		Iterable<Compensation> projCompensations;
		Iterable<beans.Message> projMessages;
		ArrayList<MessageHierarchy> messHier;
		
		// si le projet n'existe pas, accueil
		if(project == null) {
			redirectTo(servlets.Index.class, response);
		}
		// sinon tout va bien
		else {
			// Récupération des compensations du projet dans l'ordre croissant
			projCompensations = facade.getAllProjectCompensationsOrderByAmount(projectId);
			// Récupération des messages du projet dans l'ordre de notation
			projMessages = facade.getAllProjectMessagesOrderBySuccess(projectId);
			// Hiérarchie des messages et réponses à afficher
			messHier = new ArrayList<MessageHierarchy>();
			
			// Pour chaque message du projet on 
			for (beans.Message m : projMessages) {
				messHier.add(new MessageHierarchy(facade, m.getId()));
			}
			
			request.setAttribute("project", project);
			request.setAttribute("compensations", projCompensations);
			request.setAttribute("messages", messHier);
			this.getServletContext().getRequestDispatcher("/jsp/project/show.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
