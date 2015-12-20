package managers.interfaces;

import javax.ejb.Stateless;

import beans.User;

@Stateless
public interface UserManager extends DefaultManager<User> {
	// Méthodes spécifiques à l'entité 'User' à déclarer ici :
}
