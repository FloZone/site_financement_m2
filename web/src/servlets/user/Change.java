package servlets.user;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servlets.RedirectServlet;

import beans.User;
import ejb.Facade;

/**
 * Servlet implementation class Change
 */
@WebServlet(name="/Change", urlPatterns = { "/User/Change" })
public class Change extends RedirectServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	Facade facade;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Change() {
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
		String password = request.getParameter("password");
		String passwordConfirmation = request.getParameter("password-confirmation");
		String email = request.getParameter("email");
		String id = request.getParameter("id");
		
		User existingUser = facade.getUser(Integer.parseInt(id));
		
		final int MIN_PASSWORD_LENGTH = 5;
		boolean lengthOk = MIN_PASSWORD_LENGTH <= password.length();
		boolean confirmationOk = password.equals(passwordConfirmation);
		boolean change = false;
		String pwd = "";
		
		
		
		if (!lengthOk || !confirmationOk || existingUser == null) {
			if (password.length() > 0 && passwordConfirmation.length()>0){
				if (!lengthOk) {
						request.setAttribute("passwordError", "Le mot de passe doit faire au moins " + MIN_PASSWORD_LENGTH + " charactères.");
				}
				if (!confirmationOk) {
					request.setAttribute("confirmationError", "Le mot de passe et la confirmation ne concordent pas.");
				}
			}
		}else{
			pwd = passwordConfirmation;
			change = true;
		}
		
		if (email != null && email != "" && email.length() > 5){
			existingUser.setEmail(email);
			change = true;
		}
			
		if (change)
			facade.updateUser(existingUser, pwd);
		
		redirectTo(Show.class, response);
	}

}
