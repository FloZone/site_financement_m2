package servlets.project;

import java.io.Console;
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

import servlets.RedirectServlet;
import sun.rmi.server.UnicastServerRef;
import ejb.Facade;
import beans.Answer;
import beans.Compensation;
import beans.Project;
import beans.User;

/**
 * Servlet implementation class Donation
 */
@WebServlet("/Project/Donation")
public class Donation extends RedirectServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	Facade facade;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Donation() {
        super();
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
		Integer messageCode = 0;
		
		HashMap<String, String> parameters = new HashMap<String, String>();
		
		// récupérer le projet
		int idProjet = Integer.parseInt(request.getParameter("project"));
		Project project = facade.getProjectById(idProjet);
		// le mettre dans les paramètres
		parameters.put("id", project.getId().toString());
		
		// récupérer le montant saisi
		int  amountDonation;
		if(request.getParameter("amountDonation") == null || request.getParameter("amountDonation").length() == 0) {
			messageCode = 2;
			
			// Afficher la page en donnant le message d'erreur
			parameters.put("msg", messageCode.toString());
			redirectTo(servlets.project.Show.class, response, parameters);
		}
		else {
			// récupérer le montant saisi
			amountDonation = new Integer((String) request.getParameter("amountDonation"));
			
			// si le montant saisi est <= 0, erreur
			if(amountDonation <= 0) {
				messageCode = 3;
				
				// Afficher la page en donnant le message d'erreur
				parameters.put("msg", messageCode.toString());
				redirectTo(servlets.project.Show.class, response, parameters);
			}
			
			// sinon tout est en ordre, enregister le don dans la bdd
			else {
				// récupérer l'utilisateur en session
				Object userId = request.getSession().getAttribute("userId");
				User user = facade.getUser((Integer)userId);
				
				// si l'utilisateur n'existe pas, erreur
				if(user == null) {
					messageCode= 5;
					// Afficher la page en donnant le message
					parameters.put("msg", messageCode.toString());
					redirectTo(servlets.project.Show.class, response, parameters);
				}
				// sinon, enregistrer le don
				else {
					beans.Donation d = facade.createDonation(amountDonation, new Date(), project, user);
				
					messageCode= 1;
					// Afficher la page en donnant le message
					parameters.put("msg", messageCode.toString());
					redirectTo(servlets.project.Show.class, response, parameters);
				}
			}
		}
	}
}
