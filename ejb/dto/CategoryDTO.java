package dto;

import java.util.Collection;

public class CategoryDTO {

	private Integer id;
	private String name;
	private Collection<Integer> projects;
	
	/**
	 * Default constructor
	 */
	public CategoryDTO() {}
	
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the projects
	 */
	public Collection<Integer> getProjects() {
		return projects;
	}

	/**
	 * @param projects the projects to set
	 */
	public void setProjects(Collection<Integer> projects) {
		this.projects = projects;
	}

}
