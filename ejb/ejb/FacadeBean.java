package ejb;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import managers.interfaces.CompensationManager;
import beans.Answer;
import beans.Category;
import beans.Compensation;
import beans.Donation;
import beans.Message;
import beans.Project;
import beans.TopProject;
import beans.User;

/**
 * Session Bean implementation class FacadeBean
 */
@Stateless
public class FacadeBean implements Facade {
	@EJB
	private UserManagement userManagement;
	@EJB
	private ProjectManagement projectManagement;
	@EJB
	private ActivityManagement activityManagement;
	@EJB
	private AdministrationManagement administrationManagement;
	
	@PersistenceContext(unitName="monUnite")
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public FacadeBean() {}

	// PROJECT ----------------------------------------------------------------
	@Override
	public Project createProject(String title, String description, int requiredAmount,
			String amountDescription, String image, Date limitDate, User user) {
		return projectManagement.create(title, description, requiredAmount,
				amountDescription, image, limitDate, user);
	}
	
	@Override
	public Iterable<Project> getSomeProjects(int from, int to) {
		return projectManagement.getSomeProject(from, to);
	}

	@Override
	public Project getProjectById(int projectId, String ... fields) {
		return projectManagement.get(projectId, fields);
	}
	
	@Override
	public boolean updateProjectCategories(Project project, Set<Category> categories) {
		return projectManagement.updateCategories(project, categories);
	}
	
	@Override
	public boolean removeProjectCategories(Project created) {
		return projectManagement.removeCategories(created);
	}

	@Override
	public boolean updateProjectCompensations(Project created, Set<Compensation> compensations) {
		return projectManagement.updateCompensations(created, compensations);
	}
	
	@Override
	public boolean updateProject(Project existingProject, String description, String amountDescription, String image) {
		return projectManagement.update(existingProject,description, amountDescription, image);
	}

	@Override
	public Message createMessage(String message, int i, int j, Date date, Project project, User user) {
		return projectManagement.createMessage(message, i, j, date, project, user);
	}
	
	@Override
	public Iterable<Project> getSomeProjectsBy(String keyword) {
		return projectManagement.getProjectsBy(keyword);
	}
	
	@Override
	public Iterable<Project> getSomeProjectsBy(String keyword,
			List<Integer> categoriesIds) {
		return projectManagement.getProjectsBy(keyword, categoriesIds);
	}

	@Override
	public Iterable<Project> getSomeProjectsBy(List<Integer> categoriesIds) {
		return projectManagement.getProjectsBy(categoriesIds);
	}

	// TOP PROJECT ------------------------------------------------------------
	@Override
	public TopProject createTopProject(Project project, String description) {
		return administrationManagement.createTopProject(project, description);
	}

	@Override
	public Iterable<TopProject> getAllTopProjects(String ... fields) {
		return administrationManagement.getAllTopProjects(fields);
	}
	
	@Override
	public boolean deleteTopProjectById(int topProjectId) {
		return administrationManagement.deleteTopProject(topProjectId);
	}
	
	// MESSAGE ----------------------------------------------------------------
	
	@Override
	public Message getMessageById(int id, String ... fields) {
		return projectManagement.getMessage(id, fields);
	}

	@Override
	public Answer createAnswer(String answer, int i, int j, Date date,
			Message message, User user) {
		return projectManagement.createAnswer(answer, i, j, date,
				message, user);
	}

	@Override
	public void addMessageAdvise(int id, int rep) {
		projectManagement.addMessageAdvise(id, rep);
	}

	@Override
	public void addAnswerAdvise(int id, int rep) {
		projectManagement.addAnswerAdvise(id, rep);
	}
	
	@Override
	public Iterable<Message> getAllProjectMessagesOrderBySuccess(int projectId) {
		return projectManagement.getAllProjectMessagesOrderBySuccess(projectId);
	}
	
	
	// ANSWER -----------------------------------------------------------------
	
	@Override
	public Iterable<Answer> getAllMessageAnswersOrderBySuccess(int messageId) {
		return projectManagement.getAllMessageAnswersOrderBySuccess(messageId);
	}
	
	
	// CATEGORY ---------------------------------------------------------------
	@Override
	public Category getCategoryById(int id, String ... fields) {
		return administrationManagement.getCategory(id, fields);
	}
	
	@Override
	public Category createCategory(String name) {
		return administrationManagement.createCategory(name);
	}
	
	@Override
	public boolean deleteCategoryById(int categoryId) {
		return administrationManagement.deleteCategory(categoryId);
	}
	
	@Override
	public Iterable<Category> getAllCategories(String ... fields) {
		return administrationManagement.getAllCategories(fields);
	}

	
	// DONATION ---------------------------------------------------------------
	@Override
	public Donation getDonationById(int id, String ... fields) {
		return projectManagement.getDonationById(id, fields);
	}
	
	@Override
	public Donation createDonation(Integer amount, Date date,
			Project project, User user) {
		return projectManagement.createDonation(amount, date, project, user);
	}
	
	// COMPENSATION -----------------------------------------------------------
	@Override
	public Compensation getCompensationById(int compensationId, String ... fields) {
		return projectManagement.getCompensation(compensationId, fields);
	}
	
	@Override
	public Compensation createCompensation(int compensationAmount,
			String compensationDescription, Project project) {
		return projectManagement.createCompensation(compensationAmount, compensationDescription, project);
	}
	
	@Override
	public Iterable<Compensation> getAllProjectCompensationsOrderByAmount(
			int projectId) {
		return projectManagement.getAllProjectCompensationsOrderByAmount(projectId);
	}
	
	@Override
	public Compensation getLinkedCompensation(Donation donation, String ... fields) {
		return projectManagement.getLinkedCompensation(donation, fields);
	}
	
	// USER -------------------------------------------------------------------
	@Override
	public User createUser(String login, String password, String firstName, String lastName, String email) {
		return userManagement.signUp(login, password, firstName, lastName, email);
	}
	
	@Override
	public User getUser(int id, String ... fields) {
		return userManagement.get(id, fields);
	}
	
	@Override
	public User login(String login, String password) {
		return userManagement.login(login, password);
	}
	
	@Override
	public User getUserByLogin(String login, String ... fields) {
		return userManagement.getByLogin(login, fields);
	}
	
	@Override
	public Iterable<User> getSomeUsers(int from, int to, String ... fields) {
		return userManagement.getSome(from, to, fields);
	}
	
	@Override
	public int getUserCount() {
		return userManagement.getUserCount();
	}

	@Override
	public boolean updateUser(User existingUser, String pwd) {
		return userManagement.update(existingUser, pwd);
	}

	@Override
	public Iterable<User> getAllUsers() {
		return userManagement.getAll();
	}
	// TO SORT PLEASE! --------------------------------------------------------
	
}
