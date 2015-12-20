package servlets.project;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlets.RedirectServlet;
import ejb.Facade;
import beans.Project;
import beans.User;

/**
 * Servlet implementation class Answer
 */
@WebServlet("/Answer")
public class Answer extends RedirectServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	Facade facade;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Answer() {
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
		int id = Integer.parseInt(request.getParameter("mess"));
		beans.Message message = facade.getMessageById(id);
		Project project = message.getProject();
		
		String answer = request.getParameter("answer");
		if (answer != null && !answer.isEmpty()){
			// récupérer l'utilisateur en session
			Object userId = request.getSession().getAttribute("userId");
			User user = facade.getUser((Integer)userId);
			
			// créer le message en base
			beans.Answer m = facade.createAnswer(answer, 0, 0, new Date(), message, user);
		}
		HashMap<String, String> parameters = new HashMap<String, String>();
		parameters.put("id", project.getId().toString());
		redirectTo(servlets.project.Show.class, response, parameters);
	}

}
