package servlets.administration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Project;
import beans.TopProject;
import ejb.Facade;
import servlets.RedirectServlet;

/**
 * Servlet implementation class TopProjectAdministration
 */
@WebServlet(name = "/Administration/TopProject", urlPatterns = { "/Administration/TopProject" })
public class TopProjectAdministration extends RedirectServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	Facade facade;

    /**
     * Default constructor. 
     */
    public TopProjectAdministration() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Iterable<TopProject> topProjects = facade.getAllTopProjects();
		request.setAttribute("topProjects", topProjects);
		request.getRequestDispatcher("/jsp/administration/topProject.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String projectId = request.getParameter("projectId");
		String topProjectDescription = request.getParameter("topProjectDescription");				
		String topProjectId = request.getParameter("topProjectId");
		
		if (projectId != null && topProjectDescription != null 
				&& !projectId.isEmpty() && !topProjectDescription.isEmpty()) {
			Project project = facade.getProjectById(Integer.parseInt(projectId));
			
			TopProject created = null;
			Map<String, String> parameters = new HashMap<String, String>(2);
			parameters.put("id", projectId);
			
			if (project != null) {
				created = facade.createTopProject(project, topProjectDescription);
			}
			
			if (created != null) {
				parameters.put("info", "success");
			}
			else {
				parameters.put("info", "error");
			}
			
			redirectTo(servlets.project.Show.class, response, parameters);
		}
		else if (topProjectId != null && !topProjectId.isEmpty()) {
			facade.deleteTopProjectById(Integer.parseInt(topProjectId));
			redirectTo(TopProjectAdministration.class, response);
		}
	}

}
