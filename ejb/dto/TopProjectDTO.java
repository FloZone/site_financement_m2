package dto;

public class TopProjectDTO {

	private Integer id;
	private String description;
	private Integer project;
	
	/**
	 * Default constructor
	 */
	public TopProjectDTO() {}

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
