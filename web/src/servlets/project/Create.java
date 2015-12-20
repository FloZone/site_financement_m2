package servlets.project;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.SendResult;

import beans.Project;
import beans.Category;
import beans.Compensation;
import beans.User;
import servlets.RedirectServlet;
import ejb.Facade;
import ejb.ProjectManagement;

/**
 * Servlet implementation class Create
 */
@WebServlet(name="Project/Create", urlPatterns={ "/Project/Create" })
public class Create extends RedirectServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	Facade facade;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Create() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("categories", facade.getAllCategories());
		request.getRequestDispatcher("/jsp/project/create.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String requiredAmountStr = request.getParameter("requiredAmount");
		
		// récupération des informations du projet
		// on impose titre, description et montant
		if(title != null && !title.isEmpty()
				&& description != null && !description.isEmpty()
				&& requiredAmountStr != null && !requiredAmountStr.isEmpty()) {
			Integer requiredAmount = new Integer(requiredAmountStr);
			
			// récupérer la description du montant
			String amountDescription = request.getParameter("amountDescription");
			if(amountDescription == null) {
				amountDescription = "";
			}
			
			// récupérer l'image
			String image = request.getParameter("image");
			if(image == null || image.equals("")) {
				// image par défaut
				image = "/images/project.jpg";
			}
			
			// gérer les catégories
			Set<Category> categories =  new HashSet<Category>();
			String[] selectedCategoriesStr = request.getParameterValues("categories");
			if (selectedCategoriesStr != null && selectedCategoriesStr.length > 0) {
				int[] selectedCategories = new int[selectedCategoriesStr.length];
				for (int i = 0 ; i < selectedCategoriesStr.length ; ++i) {
					selectedCategories[i] = Integer.parseInt(selectedCategoriesStr[i]);
				}
				
				for (int id : selectedCategories) {
					categories.add(facade.getCategoryById(id));
				}
			}
			
			// si la date n'est pas saisie, mettre 1mois après aujourd'hui
			// sinon vérifier que ce n'est pas 1mois après aujourd'hui
			
			Date limitDate;
			// si la date a été saisie
			if(request.getParameter("limitDate") != null) {
				String limitDateStr = (String) request.getParameter("limitDate");
				// format HTML5
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				// si la date est valide, la récupérer
				try {
					limitDate = formatter.parse(limitDateStr);
					
					// tester que la date est <= +60, sinon prendre +60j
					Date date = getDelayedDate(60);
					if(limitDate.after(date) || getDelayedDate(0).after(limitDate)) {
						limitDate = date;
					}
				}
				// sinon date + 30j
				catch(ParseException e) {
					limitDate = getDelayedDate(60);
				}
			}
			// sinon la date n'a pas été saisie, faire aujourd'hui +60j
			else {					
				limitDate = getDelayedDate(60);
			}
			
			// récupérer l'utilisateur en session
			Object userId = request.getSession().getAttribute("userId");
			User user = facade.getUser((Integer)userId);
			
			// ajouter le projet en base
			Project project = facade.createProject(title, description, requiredAmount, amountDescription, image, limitDate, user);
			
			// ajouter ses catégories
			facade.updateProjectCategories(project, categories);
			
			// créer les compensations
//			Collection<Compensation> compensations =  new ArrayList<Compensation>();
			// pour chaque compensation
			int compensationNumber = new Integer((String) request.getParameter("compensationNumber"));
			for(int i = 1; i <= compensationNumber; ++i) {
				// vérifier qu'elle existe et que l'utilisateur à bien rempli les champs
				if( request.getParameter("compensationAmount"+i) != null && request.getParameter("compensationAmount"+i).length() != 0 &&
						request.getParameter("compensationDescription"+i) != null && request.getParameter("compensationDescription"+i).length() != 0) {
					// récupérer les infos
					int compensationAmount = new Integer(request.getParameter("compensationAmount"+i));
					String compensationDescription = (String) request.getParameter("compensationDescription"+i);
					// ajouter la compensation en base
					Compensation newCompensation = facade.createCompensation(compensationAmount, compensationDescription, project);
//					if(newCompensation != null) {
//						compensations.add(newCompensation);
//					}
				}
			}
			// lier les compensations au projet
			//facade.updateProjectCompensations(project, compensations);
		
			HashMap<String, String> parameters = new HashMap<String, String>();
			parameters.put("id", project.getId().toString());
			redirectTo(servlets.project.Show.class, response, parameters);
		}
		// sinon, les champs obligatoires n'ont pas été remplis
		else {
			request.setAttribute("errorMessage", "Veuillez remplir au minimum le titre, la description et le montant");
			request.getRequestDispatcher("/jsp/project/create.jsp").forward(request, response);
		}
	}
	
	private Date getDelayedDate(int delayed) {
		Date date;
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, delayed);
		date = c.getTime();
		return date;
	}

}
