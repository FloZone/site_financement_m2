package beans;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Transient;

@Entity
public class Project {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String title;
	
	@Column(columnDefinition="VARCHAR2")
	private String description;
	private Integer requiredAmount;
	@Column(columnDefinition="VARCHAR2")
	private String amountDescription;
	private String image;
	private Date creationDate;
	private Date limitDate;
	@Transient
	private Integer totalDonations;
	
	@ManyToMany(fetch=FetchType.LAZY)
	private Set<Category> categories;
	
	@OneToMany(mappedBy="project", fetch=FetchType.LAZY)
	private Set<Compensation> compensations;
	
	@OneToMany(mappedBy="project", fetch=FetchType.LAZY)
	private Set<Donation> donations;
	
	@OneToMany(mappedBy="project", fetch=FetchType.LAZY)
	private Set<Message> comments;
	
	@ManyToOne
	private User user;
	
	/**
	 * Default constructor
	 */
	public Project() {}
	
	/**
	 * 
	 * @param title
	 * @param description
	 * @param requiredAmount
	 * @param amountDescription
	 * @param image
	 * @param creationDate
	 * @param limitDate
	 * @param categories
	 * @param compensations
	 */
	public Project(String title, String description, Integer requiredAmount,
			String amountDescription, String image, Date creationDate, Date limitDate, User user) {
		super();
		this.title = title;
		this.description = description;
		this.requiredAmount = requiredAmount;
		this.amountDescription = amountDescription;
		this.image = image;
		this.creationDate = creationDate;
		this.limitDate = limitDate;
		
		this.categories = new HashSet<Category>();
		this.compensations = new HashSet<Compensation>();
		this.donations = new HashSet<Donation>();
		this.comments = new HashSet<Message>();
		
		this.user = user;
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the requiredAmount
	 */
	public Integer getRequiredAmount() {
		return requiredAmount;
	}

	/**
	 * @param requiredAmount the requiredAmount to set
	 */
	public void setRequiredAmount(Integer requiredAmount) {
		this.requiredAmount = requiredAmount;
	}

	/**
	 * @return the amountDescription
	 */
	public String getAmountDescription() {
		return amountDescription;
	}

	/**
	 * @param amountDescription the amountDescription to set
	 */
	public void setAmountDescription(String amountDescription) {
		this.amountDescription = amountDescription;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the limitDate
	 */
	public Date getLimitDate() {
		return limitDate;
	}

	/**
	 * @param limitDate the limitDate to set
	 */
	public void setLimitDate(Date limitDate) {
		this.limitDate = limitDate;
	}

	/**
	 * @return the categories
	 */
	public Set<Category> getCategories() {
		return categories;
	}

	/**
	 * @param categories the categories to set
	 */
	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	/**
	 * @return the compensations
	 */
	public Set<Compensation> getCompensations() {
		return compensations;
	}

	/**
	 * @param compensations the compensations to set
	 */
	public void setCompensations(Set<Compensation> compensations) {
		this.compensations = compensations;
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

	/**
	 * @return the comments
	 */
	public Set<Message> getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(Set<Message> comments) {
		this.comments = comments;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the totalDonations
	 */
	public Integer getTotalDonations() {
		return totalDonations;
	}

	/**
	 * @param totalDonations the totalDonations to set
	 */
	public void setTotalDonations(Integer totalDonations) {
		this.totalDonations = totalDonations;
	}

	@PostLoad
	public void onPostLoad() {
		Integer totalDonations = new Integer(0);
		
		for (Donation donation : this.donations) {
			totalDonations += donation.getAmount();
		}
		
		this.totalDonations = totalDonations;
	}
	
}
