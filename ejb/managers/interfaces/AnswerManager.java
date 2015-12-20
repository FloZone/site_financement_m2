package managers.interfaces;

import javax.ejb.Stateless;

import beans.Answer;

@Stateless
public interface AnswerManager extends DefaultManager<Answer> {
	// Méthodes spécifiques à l'entité 'Answer' à déclarer ici :
	/**
	 * Get all answers related to the given message id, sorted by success
	 * @param messageId Message id
	 * @return Returns a list of answers related to the given message id, sorted by success
	 */
	Iterable<Answer> getAllMessageAnswersOrderBySuccess(int messageId);

}
