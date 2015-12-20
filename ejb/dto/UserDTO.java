package dto;

import java.util.Collection;

public class UserDTO {

	private Integer id;
	private String login;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private Boolean administrator;
	private Collection<Integer> donations;
	
	/**
	 * Default constructor
	 */
	public UserDTO() {}

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
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the administrator
	 */
	public Boolean getAdministrator() {
		return administrator;
	}

	/**
	 * @param administrator the administrator to set
	 */
	public void setAdministrator(Boolean administrator) {
		this.administrator = administrator;
	}

	/**
	 * @return the donations
	 */
	public Collection<Integer> getDonations() {
		return donations;
	}

	/**
	 * @param donations the donations to set
	 */
	public void setDonations(Collection<Integer> donations) {
		this.donations = donations;
	}

}
