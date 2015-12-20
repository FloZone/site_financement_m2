package servlets.project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.AbstractDocument.LeafElement;

import servlets.RedirectServlet;
import beans.Answer;
import beans.Project;
import beans.User;
import ejb.Facade;

/**
 * Servlet implementation class Message
 */
@WebServlet("/Message")
public class Message extends RedirectServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	Facade facade;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Message() {
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
		int id = Integer.parseInt(request.getParameter("project"));
		Project project = facade.getProjectById(id);
		
		String message = request.getParameter("message");
		if (message != null && !message.isEmpty()){
			// récupérer l'utilisateur en session
			Object userId = request.getSession().getAttribute("userId");
			User user = facade.getUser((Integer)userId);
			
			// créer le message en base
			beans.Message m = facade.createMessage(message, 0, 0, new Date(), project, user);
		}
		HashMap<String, String> parameters = new HashMap<String, String>();
		parameters.put("id", project.getId().toString());
		redirectTo(servlets.project.Show.class, response, parameters);
	}

}
