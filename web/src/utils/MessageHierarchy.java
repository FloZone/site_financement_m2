package utils;

import ejb.Facade;
import beans.Answer;
import beans.Message;

public class MessageHierarchy {
	
	private Message message = null;
	private Iterable<Answer> answers = null;

	/**
	 * Build a MessageHierarchy object corresponding to the given message id
	 * @param messageId message id
	 */
	public MessageHierarchy(Facade facade, int messageId) {
		super();
		this.message = facade.getMessageById(messageId);
		this.answers = facade.getAllMessageAnswersOrderBySuccess(messageId);
	}
	
	/**
	 * @param message the message to set
	 * @param answers the answers to set
	 */
	public MessageHierarchy(Message message, Iterable<Answer> answers) {
		super();
		this.message = message;
		this.answers = answers;
	}

	/**
	 * @return the message
	 */
	public Message getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(Message message) {
		this.message = message;
	}

	/**
	 * @return the answers
	 */
	public Iterable<Answer> getAnswers() {
		return answers;
	}

	/**
	 * @param answers the answers to set
	 */
	public void setAnswers(Iterable<Answer> answers) {
		this.answers = answers;
	}

}
