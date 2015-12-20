package managers.hibernate;

import javax.ejb.Stateless;

import managers.interfaces.MessageManager;
import beans.Message;

@Stateless
public class MessageManagerHibernate extends AbstractEJBManagerHibernate<Message> implements MessageManager {

	public MessageManagerHibernate() {
		super(Message.class);
	}

	@Override
	public Iterable<Message> getAllProjectMessagesOrderBySuccess(
			int projectId) {
		return entityManager.createQuery("SELECT DISTINCT m FROM Message m JOIN m.project p "
				+ "WHERE p.id = :projectId ORDER BY m.good DESC, m.bad, m.date)",
				Message.class)
				.setParameter("projectId", projectId)
				.getResultList();
	}
		
}
