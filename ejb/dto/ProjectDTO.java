package dto;

import java.util.Collection;
import java.util.Date;

public class ProjectDTO {

	private Integer id;
	private String title;
	private String description;
	private Integer requiredAmont;
	private String amountDescription;
	private String image;
	private Date creationDate;
	private Date limitDate;
	private Integer totalDonations;
	private Collection<Integer> categories;
	private Collection<Integer> compensations;
	private Collection<Integer> donations;
	private Collection<Integer> comments;
	
	/**
	 * Default constructor
	 */
	public ProjectDTO() {}

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
	 * @return the requiredAmont
	 */
	public Integer getRequiredAmont() {
		return requiredAmont;
	}

	/**
	 * @param requiredAmont the requiredAmont to set
	 */
	public void setRequiredAmont(Integer requiredAmont) {
		this.requiredAmont = requiredAmont;
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

	/**
	 * @return the categories
	 */
	public Collection<Integer> getCategories() {
		return categories;
	}

	/**
	 * @param categories the categories to set
	 */
	public void setCategories(Collection<Integer> categories) {
		this.categories = categories;
	}

	/**
	 * @return the compensations
	 */
	public Collection<Integer> getCompensations() {
		return compensations;
	}

	/**
	 * @param compensations the compensations to set
	 */
	public void setCompensations(Collection<Integer> compensations) {
		this.compensations = compensations;
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

	/**
	 * @return the comments
	 */
	public Collection<Integer> getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(Collection<Integer> comments) {
		this.comments = comments;
	}

}
