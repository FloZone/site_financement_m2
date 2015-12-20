package ejb;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import managers.interfaces.AnswerManager;
import managers.interfaces.DonationManager;
import managers.interfaces.MessageManager;
import beans.Answer;
import beans.BaseMessage;
import beans.Donation;
import beans.Message;
import beans.Project;
import beans.User;

/**
 * Session Bean implementation class ActivityManagement
 */
@Stateless
@LocalBean
public class ActivityManagement {
	
	@EJB
	MessageManager messageManager;
	
	@EJB
	AnswerManager answerManager;
	
	@EJB
	DonationManager donationManager;

    /**
     * Default constructor. 
     */
    public ActivityManagement() {
        
    }
    
    /// BaseMessage ///
    
    public void likeBaseMessage(BaseMessage message) {
    	message.setGood(message.getGood() + 1);
    }
    
    public void dontLikeBaseMessage(BaseMessage message) {
    	message.setBad(message.getBad() + 1);
    }
    
    /// Message ///

    /**
     * Create a message
     * @return The created message
     */
    public Message createMessage(String content, Integer good, Integer bad, Project project) {
    	Date date = new Date();
    	return messageManager.create(content, good, bad, date, project);
    }
    
    public boolean likeMessage(Message message) {
    	likeBaseMessage(message);
    	return messageManager.update(message);
    }
    
    public boolean dontLikeMessage(Message message) {
    	dontLikeBaseMessage(message);
    	return messageManager.update(message);
    }
    
    /// Answer ///
    
    /**
     * Create an answer
     * @return the created answer
     */
    public Answer createAnswer(String content, Integer good, Integer bad, Message message) {
    	Date date = new Date();
    	return answerManager.create(content, good, bad, date, message);
    }
    
    public boolean likeAnswer(Answer answer) {
    	likeBaseMessage(answer);
    	return answerManager.update(answer);
    }
    
    public boolean dontLikeAnswer(Answer answer) {
    	dontLikeBaseMessage(answer);
    	return answerManager.update(answer);
    }
    
    /// Donation ///
    
    /**
     * Add a donation
     * @return the created donation
     */
    public Donation addDonation(Integer amount, Project project, User user) {
    	Date date = new Date();
    	return donationManager.create(amount, date, project, user);
    }
}
