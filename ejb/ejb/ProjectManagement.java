package ejb;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import managers.interfaces.AnswerManager;
import managers.interfaces.CompensationManager;
import managers.interfaces.DonationManager;
import managers.interfaces.MessageManager;
import managers.interfaces.ProjectManager;
import beans.Answer;
import beans.Category;
import beans.Compensation;
import beans.Donation;
import beans.Message;
import beans.Project;
import beans.User;

/**
 * Session Bean implementation class ProjectManagement
 */
@Stateless
@LocalBean
public class ProjectManagement {

	@EJB
	private ProjectManager projectManager;
	@EJB
	private DonationManager donationManager;
	@EJB
	private CompensationManager compensationManager;
	@EJB
	private MessageManager messageManager;
	@EJB
	private AnswerManager answerManager;
	
	/**
	 * Default constructor.
	 */
	public ProjectManagement() {}
	
	/**
	 * Ftech a donation
	 * @param id The donation's identifier
	 * @param fields Additional fields to retrieve
	 * @return The donation
	 */
	public Donation getDonationById(int id, String ... fields) {
		return donationManager.get(id, fields);
	}

	/**
	 * Create a project
	 * @return Returns the created project
	 */
	public Project create(String title, String description, int requiredAmount,
			String amountDescription, String image, Date limitDate, User user) {
		Date now = new Date();
		return projectManager.create(title, description, requiredAmount,
				amountDescription, image, now, limitDate, user);
	}

	/**
	 * Update project information
	 * @return Returns true when successful updated
	 */
	public boolean update(Project projectToUpdate) {
		return projectManager.update(projectToUpdate);
	}

	/**
	 * Delete a project
	 * @param projectToDelete Project to delete
	 * @return Returns true when successful deleted
	 */
	public boolean delete(Project projectToDelete) {
		return projectManager.delete(projectToDelete);
	}

	/**
	 * Get project by id
	 * @param id Project id to get
	 * @return Returns the corresponding project
	 */
	public Project get(int id, String ... fields) {
		return projectManager.get(id, fields);
	}

	/**
	 * Get projects within a range
	 * @param from Begining
	 * @param to End
	 * @return Returns a projects Collection
	 */
	public Iterable<Project> getSomeProject(int from, int to, String ... fields) {
		return projectManager.getSome(from, to, fields);
	}

	/**
	 * Get the current amount
	 * @param project related project to the amount
	 * @return Returns the current amount
	 */
	public Integer currentAmount(Project project) {
		Integer current = 0;
		Iterable<Donation> donations = project.getDonations();
		if (donations != null) {
			for (Donation donation:donations){
				current += donation.getAmount();
			}
		}
		return current;
	}
	
	/**
	 * Update categories list of a project
	 * @param project Project to update
	 * @param categories Categories to add
	 * @return Returns true when successful updated
	 */
	public boolean updateCategories(Project project, Set<Category> categories) {
		Set<Category> c = project.getCategories();
		boolean success = c.addAll(categories);

		if (success) {
			project.setCategories(c);
			projectManager.update(project);
		}
		
		return success;
	}
	
	public boolean removeCategories(Project project) {
		HashSet<Category> categories = new HashSet<Category>();
		project.setCategories(categories);
		projectManager.update(project);
		return true;
	}
	
	/**
	 * Update compensations list of a project
	 * @param project Project to update
	 * @param compensations Compensations to add
	 * @return Returns true when successful updated
	 */
	public boolean updateCompensations(Project project, Set<Compensation> compensations) {
		Set<Compensation> c = project.getCompensations();
		boolean success = c.addAll(compensations);
		
		if (success) {
			project.setCompensations(c);
			projectManager.update(project);
		}
		
		return success;
	}

	/**
	 * Create a compensation related to the given project
	 * @param compensationAmount Compensation amount
	 * @param compensationDescription Compensation description
	 * @param project Related project
	 * @return Returns the created compensation
	 */
	public Compensation createCompensation(int compensationAmount,
			String compensationDescription, Project project) {
		return compensationManager.create(compensationAmount, compensationDescription, project);
	}

	// TODO: Function to delete, just for testing
	/**
	 * Get all projects
	 * @return Returns an iterable with all projects
	 */
	public Iterable<Project> getAllProjects(String ... fields) {
		return projectManager.getAll(fields);
	}
	
	public Message getMessage(int id, String ... fields) {
		return messageManager.get(id, fields);
	}

	public Message createMessage(String message, int i, int j, Date date, Project project, User user) {
		return messageManager.create(message, i, j, date, project, user);
	}

	public Iterable<Project> getProjectsBy(String keyword) {
		return projectManager.getProjectsBy(keyword);
	}
	
	public Donation createDonation(Integer amount, Date date, Project project, User user) {
		return donationManager.create(amount, date, project, user);
	}
	
	public Compensation getCompensation(int id, String ... fields) {
		return compensationManager.get(id, fields);
	}

	public boolean update(Project projectToUpdate, String description, String amountDescription, String image) {
		Date today = new Date();
		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		String editStr = "<br><br>Mise à jour du " + formater.format(today)+ ":<br>";
		
		if(!description.trim().equals("")) {
			String digest = projectToUpdate.getDescription();
			digest += editStr + description.trim();
			
			projectToUpdate.setDescription(digest);
		}
		if(!amountDescription.trim().equals("")) {
			String digest = projectToUpdate.getAmountDescription();
			digest += editStr + amountDescription.trim();
			
			projectToUpdate.setAmountDescription(digest);
		}
		if(!image.trim().equals("")) {
			projectToUpdate.setImage(image);
		}
		else {
			projectToUpdate.setImage("/images/project.jpg");			
		}
		
		return projectManager.update(projectToUpdate);
	}

	public Answer createAnswer(String answer, int i, int j, Date date,
			Message message, User user) {
		return answerManager.create(answer, i, j, date, message, user);
	}

	public void addMessageAdvise(int id, int rep) {
		Message message = messageManager.get(id);
		int number;
		if (rep == 0){
			number = message.getBad();
			message.setBad(number+1);
		}
		if (rep == 1){
			number = message.getGood();
			message.setGood(number+1);
		}
		messageManager.update(message);
	}

	public void addAnswerAdvise(int id, int rep) {
		Answer answer = answerManager.get(id);
		int number;
		if (rep == 0){
			number = answer.getBad();
			answer.setBad(number+1);
		}
		if (rep == 1){
			number = answer.getGood();
			answer.setGood(number+1);
		}
		answerManager.update(answer);
	}

	public Iterable<Project> getProjectsBy(String keyword,
			List<Integer> categoriesIds) {
		return projectManager.getProjectsBy(keyword, categoriesIds);
	}

	public Iterable<Project> getProjectsBy(List<Integer> categoriesIds) {
		return projectManager.getProjectsBy(categoriesIds);
	}

	public Iterable<Compensation> getAllProjectCompensationsOrderByAmount(
			int projectId) {
		return compensationManager.getAllProjectCompensationsOrderByAmount(projectId);
	}

	public Iterable<Message> getAllProjectMessagesOrderBySuccess(int projectId) {
		return messageManager.getAllProjectMessagesOrderBySuccess(projectId);
	}

	public Iterable<Answer> getAllMessageAnswersOrderBySuccess(int messageId) {
		return answerManager.getAllMessageAnswersOrderBySuccess(messageId);
	}
	
	public Compensation getLinkedCompensation(Donation donation, String ... fields) {
		return compensationManager.getLinkedCompensation(donation, fields);
	}
}
