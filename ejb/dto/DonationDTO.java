package dto;

import java.util.Date;

public class DonationDTO {

	private Integer id;
	private Integer amount;
	private Date date;
	private Integer project;
	private Integer user;
	
	/**
	 * Default constructor
	 */
	public DonationDTO() {}

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
	 * @return the amount
	 */
	public Integer getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Integer amount) {
		this.amount = amount;
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

	/**
	 * @return the user
	 */
	public Integer getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(Integer user) {
		this.user = user;
	}

}
