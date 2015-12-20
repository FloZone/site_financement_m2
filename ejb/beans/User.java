package beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Transient;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique=true)
	private String login;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private Boolean administrator;
	
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private Set<Donation> donations;
	
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private Set<Project> projects;
	
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private Set<Message> comments;
	
	@Transient
	String fullname;
	
	/**
	 * Default constructor
	 */
	public User() {}

	/**
	 * @param login
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param administrator
	 */
	public User(String login, String password, String firstName,
			String lastName, String email, Boolean administrator) {
		super();
		this.login = login;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.administrator = administrator;
		this.donations = new HashSet<>();
		this.projects = new HashSet<Project>();
	}

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
	public Set<Donation> getDonations() {
		return donations;
	}

	/**
	 * @param donations the donations to set
	 */
	public void setDonations(Set<Donation> donations) {
		this.donations = donations;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	public Set<Message> getUsers() {
		return comments;
	}

	public void setUsers(Set<Message> comments) {
		this.comments = comments;
	}
	
	public String getFullname() {
		return fullname;
	}
	
	public void setFullname(String fullname) {
		this.fullname= fullname ;
	}

	@PostLoad
	public void onPostLoad() {
		StringBuilder sb = new StringBuilder();
		sb.append(Character.toUpperCase(firstName.charAt(0)));
		sb.append(firstName.substring(1));
		sb.append(' ');
		sb.append(lastName.toUpperCase());
		fullname = sb.toString();
	}
}
