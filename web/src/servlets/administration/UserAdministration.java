package servlets.administration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;
import ejb.Facade;
import servlets.RedirectServlet;

/**
 * Servlet implementation class UserAdministration
 */
@WebServlet(name = "/Administration/User", urlPatterns = { "/Administration/User" })
public class UserAdministration extends RedirectServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	Facade facade;

    /**
     * Default constructor. 
     */
    public UserAdministration() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Iterable<User> users = facade.getAllUsers();
		
		request.setAttribute("users", users);
		request.getRequestDispatcher("/jsp/administration/user.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
