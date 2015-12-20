package servlets.user;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.http.HTTPException;

import beans.Compensation;
import beans.Donation;
import beans.User;
import ejb.Facade;
import servlets.Index;
import servlets.RedirectServlet;

/**
 * Servlet implementation class Show
 */
@WebServlet(name = "/Show", urlPatterns = { "/User/Show" })
public class Show extends RedirectServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	Facade facade;

    /**
     * Default constructor. 
     */
    public Show() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// récupérer l'utilisateur en session
		Object userId = request.getSession().getAttribute("userId");
		final String[] userFields = {"projects", "donations"};
		final String[] donationFields = {"project", "user"};
//		final String[] CompensationFields = {"project"};
		User user = facade.getUser((Integer)userId, userFields);
		
		// tester si la valeur n'est pas nulle dans la jsp
		if(user != null) {		
			Set<Donation> donations = new HashSet<Donation>();
			Collection<AbstractMap.SimpleImmutableEntry<Donation, Compensation>> donationsAndCompensations = new ArrayList<>();
			
			for (Donation d : user.getDonations()) {
				Donation tmp = facade.getDonationById(d.getId(), donationFields);
				donations.add(tmp);
				
				Compensation c = facade.getLinkedCompensation(tmp);
				AbstractMap.SimpleImmutableEntry<Donation, Compensation> newEntry =
						new AbstractMap.SimpleImmutableEntry<Donation, Compensation>(tmp, c);
				donationsAndCompensations.add(newEntry);
			}
			
			user.setDonations(donations);
			request.setAttribute("donationsAndCompensations", donationsAndCompensations);
		}
		request.setAttribute("user", user);
		
		request.getRequestDispatcher("/jsp/user/show.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		throw new HTTPException(405);
	}

}
