package beans;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("A")
public class Answer extends BaseMessage {
	@ManyToOne
	private Message message;
	
	/**
	 * Default constructor
	 */
	public Answer() {
		super();
	}

	/**
	 * 
	 * @param content
	 * @param good
	 * @param bad
	 * @param date
	 * @param message
	 */
	public Answer(String content, Integer good, Integer bad, Date date, Message message, User user) {
		super(content, good, bad, date, user);
		this.message = message;
	}

	/**
	 * @return the relatedMessage
	 */
	public Message getMessage() {
		return message;
	}

	/**
	 * @param relatedMessage the relatedMessage to set
	 */
	public void setMessage(Message message) {
		this.message = message;
	}
}
