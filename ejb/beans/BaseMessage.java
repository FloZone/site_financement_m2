package beans;

import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="discriminator", discriminatorType=DiscriminatorType.CHAR)
@MappedSuperclass
public abstract class BaseMessage {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Integer id;
	private String content;
	private Integer good;
	private Integer bad;
	private Date date;
	
	@ManyToOne
	User user;
	
	/**
	 * Default construtor
	 */
	public BaseMessage() {}
	
	/**
	 * 
	 * @param content
	 * @param good
	 * @param bad
	 * @param date
	 */
	public BaseMessage(String content, Integer good, Integer bad, Date date, User user) {
		super();
		this.content = content;
		this.good = good;
		this.bad = bad;
		this.date = date;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
