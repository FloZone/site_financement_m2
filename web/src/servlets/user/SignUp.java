package servlets.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ejb.Facade;
import beans.User;
import servlets.Index;
import servlets.RedirectServlet;

/**
 * Servlet implementation class signup
 */
@WebServlet(name="/User/SignUp", urlPatterns={"/User/SignUp"})
public class SignUp extends RedirectServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	Facade facade;

    /**
     * Default constructor. 
     */
    public SignUp() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		
		if (userId != null) {
			redirectTo(Index.class, response);
		}
		else {
			User u = new User();
			request.setAttribute("user", u);
			request.getRequestDispatcher("/jsp/user/signup.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String passwordConfirmation = request.getParameter("password-confirmation");
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String email = request.getParameter("email");
		
		User existingUser = facade.getUserByLogin(login);
		boolean isUserUnique = existingUser == null;
		
		final int MIN_PASSWORD_LENGTH = 5;
		boolean lengthOk = MIN_PASSWORD_LENGTH <= password.length();
		boolean confirmationOk = password.equals(passwordConfirmation);
		
		if (!lengthOk || !confirmationOk || !isUserUnique) {
			if (!lengthOk) {
				request.setAttribute("passwordError", "Le mot de passe doit faire au moins " + MIN_PASSWORD_LENGTH + " charactères.");
			}
			if (!confirmationOk) {
				request.setAttribute("confirmationError", "Le mot de passe et la confirmation ne concordent pas.");
			}
			if (!isUserUnique) {
				request.setAttribute("loginError", "Le login est déjà attribué.");
			}
			
			User u = new User(login, "", firstName, lastName, email, false);
			request.setAttribute("user", u);
			request.getRequestDispatcher("/jsp/user/signup.jsp").forward(request, response);
		}
		else {
			User createdUser = facade.createUser(login, passwordConfirmation, firstName, lastName, email);
			redirectTo(Login.class, response);
		}
	}

}
