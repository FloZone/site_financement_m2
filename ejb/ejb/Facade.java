package ejb;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.Local;

import beans.Answer;
import beans.Category;
import beans.Compensation;
import beans.Donation;
import beans.Message;
import beans.Project;
import beans.TopProject;
import beans.User;

/**
 * Session Bean implementation class Facade
 */
@Local
public interface Facade {

	// PROJECT ----------------------------------------------------------------
	Project createProject(String title, String description, int requiredAmount,
			String amountDescription, String image, Date limitDate, User user);
	
	Iterable<Project> getSomeProjects(int from, int to);
	
	Project getProjectById(int projectId, String ... fields);
	
	boolean updateProjectCategories(Project created, Set<Category> categories);
	
	boolean updateProjectCompensations(Project created, Set<Compensation> compensations);

	boolean updateProject(Project existingProject, String description,
			String amountDescrString, String image);
	
	boolean removeProjectCategories(Project project);
	
	Message createMessage(String message, int i, int j, Date date, Project project, User user);
	
	Iterable<Project> getSomeProjectsBy(String keyword);
	
	Iterable<Project> getSomeProjectsBy(String keyword,
			List<Integer> categoriesIds);
	
	Iterable<Project> getSomeProjectsBy(List<Integer> categoriesIds);
	
	// TOP PROJECT ------------------------------------------------------------
	TopProject createTopProject(Project project, String description);
	
	Iterable<TopProject> getAllTopProjects(String ... fields);

	boolean deleteTopProjectById(int topProjectId);
	
	// MESSAGE ----------------------------------------------------------------
	
	Message getMessageById(int id, String ... fields);

	Answer createAnswer(String answer, int i, int j, Date date,
			Message message, User user);

	void addMessageAdvise(int id, int rep);

	void addAnswerAdvise(int id, int rep);
	
	Iterable<Message> getAllProjectMessagesOrderBySuccess(int projectId);
	
	
	// ANSWER -----------------------------------------------------------------
	
	Iterable<Answer> getAllMessageAnswersOrderBySuccess(int messageId);
	
	
	// CATEGORY ---------------------------------------------------------------
	Category getCategoryById(int id, String ... fields);
	
	Category createCategory(String name);
	
	boolean deleteCategoryById(int categoryId);
	
	Iterable<Category> getAllCategories(String ... fields);

	
	// DONATION ---------------------------------------------------------------
	Donation getDonationById(int id, String ... fields);
	
	Donation createDonation(Integer amount, Date date, Project project, User user);
	
	// COMPENSATION -----------------------------------------------------------
	Compensation getCompensationById(int compensationId, String ... fields);
	
	Compensation createCompensation(int compensationAmount,
			String compensationDescription, Project project);
	
	Iterable<Compensation> getAllProjectCompensationsOrderByAmount(int projectId);
	
	Compensation getLinkedCompensation(Donation d, String ... fields);
	
	// USER -------------------------------------------------------------------
	User createUser(String login, String password, String firstName, String lastName, String email);
	User getUser(int id, String ... fields);
	User login(String login, String password);
	User getUserByLogin(String login, String ... fields);
	Iterable<User> getSomeUsers(int from, int to, String ... fields);
	int getUserCount();

	boolean updateUser(User existingUser, String pwd);

	Iterable<User> getAllUsers();
	// TO SORT PLEASE! --------------------------------------------------------
}
