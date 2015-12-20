package dto;

import java.util.Collection;
import java.util.Date;

public class MessageDTO {

	private Integer id;
	private String content;
	private Integer good;
	private Integer bad;
	private Date date;
	private Collection<Integer> answers;
	private Integer project;
	
	public MessageDTO() {}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the good
	 */
	public Integer getGood() {
		return good;
	}

	/**
	 * @param good the good to set
	 */
	public void setGood(Integer good) {
		this.good = good;
	}

	/**
	 * @return the bad
	 */
	public Integer getBad() {
		return bad;
	}

	/**
	 * @param bad the bad to set
	 */
	public void setBad(Integer bad) {
		this.bad = bad;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the answers
	 */
	public Collection<Integer> getAnswers() {
		return answers;
	}

	/**
	 * @param answers the answers to set
	 */
	public void setAnswers(Collection<Integer> answers) {
		this.answers = answers;
	}

	/**
	 * @return the project
	 */
	public Integer getProject() {
		return project;
	}

	/**
	 * @param project the project to set
	 */
	public void setProject(Integer project) {
		this.project = project;
	}

}
