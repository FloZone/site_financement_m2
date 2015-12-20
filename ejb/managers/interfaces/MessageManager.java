package managers.interfaces;

import javax.ejb.Stateless;

import beans.Message;

@Stateless
public interface MessageManager extends DefaultManager<Message> {
	// Méthodes spécifiques à l'entité 'Message' à déclarer ici :
	/**
	 * Get all messages related to the given project id, sorted by success
	 * @param projectId Project id
	 * @return Returns a list of messages related to the given project id, sorted by success
	 */
	Iterable<Message> getAllProjectMessagesOrderBySuccess(int projectId);

}
