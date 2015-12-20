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

import com.sun.mail.handlers.message_rfc822;

import ejb.Facade;
import beans.User;
import servlets.Index;
import servlets.RedirectServlet;

/**
 * Servlet implementation class Login
 */
@WebServlet(name = "/Login", urlPatterns = { "/User/Login" })
public class Login extends RedirectServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	Facade facade;

    /**
     * Default constructor. 
     */
    public Login() {
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
			request.getRequestDispatcher("/jsp/user/login.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		User u = facade.login(login, password);
		
		if (u == null) {
			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("loginError", "error");
			redirectTo(Login.class, response, parameters);
		}
		else {
			HttpSession session = request.getSession();
			session.setAttribute("userId", u.getId());
			
			// a virer ?
			session.setAttribute("user", u);
			
			if(u.getAdministrator()) {
				session.setAttribute("admin", "true");
			}

			//request.getRequestDispatcher("/jsp/user/show.jsp").forward(request, response);
			redirectTo(servlets.user.Show.class, response);
		}
	}

}
