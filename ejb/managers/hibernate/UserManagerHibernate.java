package managers.hibernate;

import javax.ejb.Stateless;

import managers.interfaces.UserManager;
import beans.User;

@Stateless
public class UserManagerHibernate extends AbstractEJBManagerHibernate<User> implements UserManager {

	public UserManagerHibernate() {
		super(User.class);
	}
}
