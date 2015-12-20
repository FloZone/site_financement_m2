package servlets.project;

import java.io.IOException;
import java.util.HashMap;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlets.RedirectServlet;
import beans.Project;
import ejb.Facade;

/**
 * Servlet implementation class GoodorBad
 */
@WebServlet("/GoodorBad")
public class GoodorBad extends RedirectServlet {
	private static final long serialVersionUID = 1L;
      
	@EJB
	Facade facade;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodorBad() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int rep = Integer.parseInt(request.getParameter("goodorbad"));
		int id = Integer.parseInt(request.getParameter("goodorbadId"));
		String type = request.getParameter("goodorbadType");
		int idProject = Integer.parseInt(request.getParameter("projectGoB"));
		Project project = facade.getProjectById(idProject);
		
		if (type.equals("message")){
			facade.addMessageAdvise(id, rep);
		}
		if (type.equals("answer")){
			facade.addAnswerAdvise(id, rep);
		}

		HashMap<String, String> parameters = new HashMap<String, String>();
		parameters.put("id", project.getId().toString());
		redirectTo(servlets.project.Show.class, response, parameters);
	}

}
