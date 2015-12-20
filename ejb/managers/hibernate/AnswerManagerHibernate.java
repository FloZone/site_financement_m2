package managers.hibernate;

import javax.ejb.Stateless;

import managers.interfaces.AnswerManager;
import beans.Answer;

@Stateless
public class AnswerManagerHibernate extends AbstractEJBManagerHibernate<Answer> implements AnswerManager {

	public AnswerManagerHibernate() {
		super(Answer.class);
	}

	@Override
	public Iterable<Answer> getAllMessageAnswersOrderBySuccess(int messageId) {
		return entityManager.createQuery("SELECT DISTINCT a FROM Answer a JOIN a.message m "
				+ "WHERE m.id = :messageId ORDER BY a.good DESC, a.bad, a.date)",
				Answer.class)
				.setParameter("messageId", messageId)
				.getResultList();
	}
	
}
