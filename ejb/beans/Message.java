package beans;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("M")
public class Message extends BaseMessage {
	
	@OneToMany(mappedBy="message", fetch=FetchType.LAZY)
	private Set<Answer> answers;
	
	@ManyToOne
	private Project project;
	
	/**
	 * Default constructor
	 */
	public Message() {
		super();
	}

	/**
	 * 
	 * @param content
	 * @param good
	 * @param bad
	 * @param date
	 * @param project
	 */
	public Message(String content, Integer good, Integer bad, Date date, Project project, User user) {
		super(content, good, bad, date, user);
		this.project = project;
		this.answers = new HashSet<>();
	}

	/**
	 * @return the answers
	 */
	public Set<Answer> getAnswers() {
		return answers;
	}

	/**
	 * @param answers the answers to set
	 */
	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}

	/**
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * @param project the project to set
	 */
	public void setProject(Project project) {
		this.project = project;
	}
	
	
}
